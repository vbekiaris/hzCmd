package main;

import cmdline.main.CmdLine;
import cmdline.base.Command;
import global.ClusterSize;
import global.Utils;
import local.*;
import local.bench.BenchManager;
import local.properties.HzCmdProperties;
import global.Bash;
import global.ClusterType;
import mq.MQ;

import javax.jms.JMSException;
import java.io.*;

//add box type,  to ip list,  for starting in unix / win

//if a newer serilization file exists,  dont write a serlization file,  as the state of the world has moved on.
//needs time stamp in serlization file.

//print cluster layout info

//JSON msg format,
//Logging System / std Out put to file
//https://github.com/fusesource/jansi  terminal colors

//for getting the total ops from hdr
//find . -name *.hgrm | xargs -n1 -I% sh -c 'grep "Total count" % | awk "{print \$7}" | tr -d ] > %.total'

//for auto gcviewer pics
//find . -name verbosegc.log | xargs -n1 -I% sh -c "java -jar $(find ~/.m2 -name gcviewer-1.34.1.jar) % %.csv %.png"

//add JFR cmd settings

//add time out settings for stages.
//add fast exit on exception boolean.
//add oracal choherance

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

        if( cluster.getBoxCount() == 0){
            System.out.println(Bash.ANSI_RED + "Cluster "+cluster.getClusterId() +" running on "+cluster.getBoxCount()+" Boxes" + Bash.ANSI_RESET);
            System.exit(1);
        }

        Installer.install(cluster.getBoxManager(), cluster.getJvmFactory(), ee, versions, libFiles);
        cluster.addVersions(versions);
        cluster.setMembersOnlyCount(size.dedicatedMemberBox());

        String version=null;
        if(versions==null || versions.length==0 ){
            version = cluster.getLastVersion();
        }else{
            version = versions[0];
        }
        if(version==null){
            System.out.println(Bash.ANSI_RED + "No version" + Bash.ANSI_RESET);
            System.exit(1);
        }


        String memberOps = properties.readPropertie(HzCmdProperties.MEMBER_OPS, "");
        cluster.addMembers(size.getMemberCount(), version, memberOps, cwdFiles);

        JvmFactory jvmFactory = cluster.getJvmFactory();
        jvmFactory.membersAdded(cluster.getMemberJvms());

        Thread.sleep(5000);

        String clientOps = properties.readPropertie(HzCmdProperties.CLIENT_OPS, "");
        cluster.addClients(size.getClientCount(), version, clientOps, cwdFiles);

        cluster.renice();

        System.out.print(cluster);
    }


    public void restart(String id, String version, boolean ee) throws Exception {
        for (ClusterContainer c : clusterManager.getClusters(id)) {
            c.restart(id, version, ee);
        }
        restartEmbeddedObject(id, id);
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

    public void restartEmbeddedObject(String clusterId, String jvmId) throws Exception {
        for (ClusterContainer c : clusterManager.getClusters(clusterId)) {
            c.restartEmbeddedObject(jvmId);
        }
        for (ClusterContainer c : clusterManager.getClusters(clusterId)) {
            c.getResponseExitOnException(jvmId, Utils.TIMEOUT_45MIN);
        }
    }

    public void bounce(String clusterId, String jvmId,  int iterations, int initialDelaySec, int restartDelaySec, int iterationDelaySec, String version, boolean ee) throws Exception {

        if(initialDelaySec!=0) {
            Thread.sleep(initialDelaySec * 1000);
        }

        for(int i=0; i<iterations; i++){
            for (ClusterContainer c : clusterManager.getClusters(clusterId)) {
                c.kill(jvmId);
            }
            for (ClusterContainer c : clusterManager.getClusters(clusterId)) {
                c.printJvmInfo(jvmId);
            }

            if(restartDelaySec!=0) {
                Thread.sleep(restartDelaySec * 1000);
            }

            for (ClusterContainer c : clusterManager.getClusters(clusterId)) {
                if(version==null){
                    c.restart(jvmId);
                } else {
                    c.restart(jvmId, version, ee);
                }
            }

            restartEmbeddedObject(clusterId, jvmId);

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
        boolean errorFound=false;
        for (ClusterContainer c : clusterManager.getClusters()) {
            errorFound |= c.downlonad(dir);
        }
        if(errorFound){
            System.exit(1);
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
        System.out.println(Bash.ANSI_PURPLE+benchManager+Bash.ANSI_RESET);

        while( benchManager.hasBench() ){
            benchManager.popBenchMarks();
            System.out.println(Bash.ANSI_CYAN+benchManager.currentBench_toString()+Bash.ANSI_RESET);

            clusterManager.loadBench(id, benchManager);
            clusterManager.setAttributes(id, benchManager);
            clusterManager.initBench(id, benchManager);
            clusterManager.warmupBench(id, benchManager);
            clusterManager.runBench(id, benchManager);
            clusterManager.cleanupBench(id, benchManager);
            clusterManager.writeMetaDataCmd(id, benchManager);
            clusterManager.removeBench(id, benchManager);
        }
    }



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