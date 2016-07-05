package main;

import cmdline.main.CmdLine;
import cmdline.base.Command;
import global.ClusterSize;
import local.*;
import local.bench.BenchManager;
import local.properties.HzCmdProperties;
import global.Bash;
import global.ClusterType;
import mq.MQ;

import javax.jms.JMSException;
import java.io.*;
import java.util.*;

//TODO
//multi files in sequence needs bench number by cluster,
//multi benchID's in file in parellel,

//print cluster layout info

//better dir struct for bench results

//for getting the total ops from hdr
//find . -name *.hgrm | xargs -n1 -I% sh -c 'grep "Total count" % | awk "{print \$7}" | tr -d ] > %.total'

//for auto gcviewer pics
//find . -name verbosegc.log | xargs -n1 -I% sh -c "java -jar $(find ~/.m2 -name gcviewer-1.34.1.jar) % %.csv %.png"

//add JFR cmd settings


public class HzCmd implements Serializable {

    public static volatile boolean saveHzCmd=true;
    public static final String serFile = "HzCmd.ser";
    public static final String propertiesFile = "HzCmd.properties";

    private ClusterManager clusterManager = new ClusterManager();

    private String brokerIP=null;


    public void initCluster(String user, String file, String clusterId, ClusterType type, ClusterSize size, boolean ee, String[] versions, String libFiles, String cwdFiles) throws Exception{

        HzCmdProperties properties = new HzCmdProperties();

        BoxManager boxManager = new BoxManager(file, user);

        ClusterContainer cluster = clusterManager.initCluster(clusterId, type, brokerIP);
        cluster.addUniquBoxes(boxManager);

        Installer.install(cluster.getBoxManager(), cluster.getJvmFactory(), ee, versions, libFiles);
        cluster.addVersions(versions);
        cluster.setMembersOnlyCount(size.dedicatedMemberBox());

        System.out.print(cluster);


        String version=null;
        if(versions==null || versions.length==0 || versions.length>1){
            version = cluster.getLastVersion();
        }else{
            version = versions[0];
        }
        if(version==null){
            System.out.println(Bash.ANSI_RED + "No version" + Bash.ANSI_RESET);
            return;
        }


        String memberOps = properties.readPropertie(HzCmdProperties.MEMBER_OPS, "");
        cluster.addMembers(size.getMemberCount(), version, memberOps, cwdFiles);

        JvmFactory jvmFactory = cluster.getJvmFactory();
        jvmFactory.membersAdded(cluster.getMemberBoxes());

        Thread.sleep(5000);

        String clientOps = properties.readPropertie(HzCmdProperties.CLIENT_OPS, "");
        cluster.addClients(size.getClientCount(), version, clientOps, cwdFiles);
    }





    //getting reply
    public void restart(String jvmId, String version, boolean ee) throws Exception {
        for (ClusterContainer c : clusterManager.getClusters(jvmId)) {
            c.restart(jvmId, version, ee);
        }
    }

    public void exit(String id) throws Exception {
        for (ClusterContainer c : clusterManager.getClusters(id)) {
            c.exit(id);
        }
    }

    public void kill(String id) throws Exception {
        for (ClusterContainer c : clusterManager.getClusters(id)) {
            c.kill(id);
        }
    }

    public void restartEmbeddedObject(String id) throws Exception {
        for (ClusterContainer c : clusterManager.getClusters(id)) {
            c.restartEmbeddedObject(id);
        }
        for (ClusterContainer c : clusterManager.getClusters(id)) {
            c.getResponseExitOnException(id);
        }
    }

    public void bounce(String id, int iterations, int initialDelaySec, int restartDelaySec, int iterationDelaySec) throws Exception {

        if(initialDelaySec!=0) {
            Thread.sleep(initialDelaySec * 1000);
        }

        for(int i=0; i<iterations; i++){
            for (ClusterContainer c : clusterManager.getClusters(id)) {
                c.kill(id);
            }
            for (ClusterContainer c : clusterManager.getClusters(id)) {
                c.printJvmInfo(id);
            }

            if(restartDelaySec!=0) {
                Thread.sleep(restartDelaySec * 1000);
            }

            for (ClusterContainer c : clusterManager.getClusters(id)) {
                c.restart(id);
            }

            restartEmbeddedObject(id);

            if(iterationDelaySec!=0) {
                Thread.sleep(iterationDelaySec * 1000);
            }
        }
    }


    public void ls(String id) throws Exception {
        for (ClusterContainer c : clusterManager.getClusters(id)) {
            c.ls(id);
        }
    }

    public void tail(String id) throws Exception {
        for (ClusterContainer c : clusterManager.getClusters(id)) {
            c.tail(id);
        }
    }

    public void download(String dir) throws Exception {
        for (ClusterContainer c : clusterManager.getClusters()) {
            c.downlonad(dir);
        }
    }

    public boolean printErrors(String id) throws IOException, InterruptedException {
        boolean error=false;
        for (ClusterContainer c : clusterManager.getClusters(id)) {
            error |= c.printErrors(id);
        }
        return error;
    }

    public void clean(String id) throws Exception {
        for (ClusterContainer c : clusterManager.getClusters(id)) {
            c.clean(id);
        }
    }

    public void wipe( ) throws IOException, InterruptedException, JMSException {
        saveHzCmd=false;

        Bash.rmQuite(serFile);
        Bash.rmQuite(propertiesFile);

        for (ClusterContainer c : clusterManager.getClusters()) {
            c.getBoxManager().killAllJava();
        }
        for (ClusterContainer c : clusterManager.getClusters()) {
            c.getBoxManager().rm(Installer.REMOTE_HZCMD_ROOT);
        }
        for (ClusterContainer c : clusterManager.getClusters()) {
            c.drainQ();
        }
    }

    public void wipeLocal( ) throws IOException, InterruptedException, JMSException {
        saveHzCmd=false;
        Bash.rmQuite(serFile);
        Bash.rmQuite(propertiesFile);

        for (ClusterContainer c : clusterManager.getClusters()) {
            c.drainQ();
        }

        for (ClusterContainer c : clusterManager.getClusters()) {
            c.getBoxManager().rm(Installer.REMOTE_HZCMD_ROOT);
        }

        Bash.killAllJava();
    }


    public void invokeBenchMark(String id, String benchFile) throws Exception {

        BenchManager benchManager = new BenchManager(benchFile);

        System.out.println(benchManager);

        while( benchManager.hasBench() ){
            benchManager.popBenchMarks();

            clusterManager.loadBench(id, benchManager);
            clusterManager.setAttributes(id, benchManager);
            clusterManager.initBench(id, benchManager);
            clusterManager.warmupBench(id, benchManager);
            clusterManager.runBench(id, benchManager);
            clusterManager.cleanupBench(id, benchManager);
            clusterManager.writeMetaDataCmd(id, benchManager);
        }
    }



    /*
    public void invokeBenchMark(String clusterId, String benchFile, final boolean warmup) throws Exception {

        String version = cluster.getVersionsString();
        String metaData = "clusterId " + clusterId + " " +
                "version " + version + " " +
                "Members " + cluster.getMemberCount() + " " +
                "Clients " + cluster.getClientCount() + " " +
                "drivers " + drivers + " " +
                "benchType " + benchType + " " +
                "interval " + interval + " " +
                "taskID " + taskId + " " +
                "class " + className + " " +
                "throwException " + benchMarkSettings.getThrowException() + " " +
                "threads " + threadCount + " " +
                "warmupSec " + warmupSec + " " +
                "benchSec " + durationSec + " " +
                "benchNum " + benchNumber + " ";

        String fileName = clusterId + "_" + version + "_" + taskId + "_" + className + "_" + benchNumber;


        properties.writeIntPropertie(HzCmdProperties.BENCH_NUMBER, benchNumber);
        System.out.println(Bash.ANSI_YELLOW + "The End" + Bash.ANSI_RESET);
    }
    */




    public void scpUp(String id, String src, String dst) throws IOException, InterruptedException {
        for (ClusterContainer c : clusterManager.getClusters(id)) {
            for (RemoteJvm jvm : c.getMatchingJvms(id) ) {
                jvm.upload(src, dst);
            }
        }
    }

    public void uploadLib(String id, String src) throws IOException, InterruptedException {
        for (ClusterContainer c : clusterManager.getClusters(id)) {
            System.out.println(Bash.ANSI_YELLOW + "Installing " + src + " for cluster " + c.getClusterId() + Bash.ANSI_RESET);
            c.uploadLib(src);
        }
    }

    public void setBrokerIP(String brokerIP) {
        this.brokerIP = brokerIP;
    }



    private static HzCmd loadHzCmd(){
        HzCmd hzCmd;
        try {
            FileInputStream fileIn = new FileInputStream(serFile);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            hzCmd = (HzCmd) in.readObject();
            in.close();
            fileIn.close();
        }catch(Exception e) {
            hzCmd = new HzCmd();
        }
        return hzCmd;
    }

    private static void saveHzCmd(HzCmd hzCmd){
        if(saveHzCmd){
            try {
                FileOutputStream fileOut = new FileOutputStream(serFile);
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                out.writeObject(hzCmd);
                out.close();
                fileOut.close();
            }catch(IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException, IOException {

        final HzCmd hzCmd = loadHzCmd();

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                saveHzCmd(hzCmd);
                try {
                    MQ.shutdown();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });


        com.github.rvesse.airline.Cli<Runnable> parser = CmdLine.getParser();
        Runnable r = parser.parse(args);
        if (r instanceof Command){
            Command c = (Command)r;

            c.exe(hzCmd);

            try {
                MQ.shutdown();
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }else{
            r.run();
        }
    }

    public void chartAllJavaMetrics(String dir) throws IOException, InterruptedException {
        Bash.executeCommand("chartAll-JavaMetrics "+dir);
    }

    public void chartComparisonMetrics(String dir, String red, String blue) throws IOException, InterruptedException {
        Bash.executeCommand("chart-ComparisonMetrics "+dir+" "+red+" "+blue);
    }

    public void chartComparisonHdr(String dir, String red, String blue) throws IOException, InterruptedException {
        Bash.executeCommand("chart-allComparisonHdr "+dir+" "+red+" "+blue);
    }

    public void chartHdr(String dir, String red) throws IOException, InterruptedException {
        Bash.executeCommand("chart-Hdr "+dir+" "+red);
    }

    public void processJhicOutput(String dir) throws IOException, InterruptedException {
        Bash.executeCommand("process-hic "+dir);
    }

    public void metricsRegresionCheck(String dir, String red, String blue) throws IOException, InterruptedException {
        Bash.executeCommand("driver-wideMetrics "+dir+" "+red+" "+blue);
    }

    public void hdrRegresionCheck(String dir, String red, String blue) throws IOException, InterruptedException {
        Bash.executeCommand("driver-wideHdr "+dir+" "+red+" "+blue);
    }


}