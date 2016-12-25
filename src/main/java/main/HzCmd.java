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
import vendor.hz.HzXml;

import javax.jms.JMSException;
import java.io.*;
import java.util.List;

//add box type,  to ip list,  for starting in unix / win
//print cluster layout info

//JSON msg format,
//https://github.com/fusesource/jansi  terminal colors

//for getting the total ops from hdr
//find . -name *.hgrm | xargs -n1 -I% sh -c 'grep "Total count" % | awk "{print \$7}" | tr -d ] > %.total'

public class HzCmd implements Serializable {

    private static transient boolean stateModified=false;
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

        //cluster.renice();

        //System.out.print(cluster);
        stateModified=true;
    }


    public void restart(String clusterId, String jvmId, String version, boolean ee) throws Exception {
        for (ClusterContainer c : clusterManager.getClusters(clusterId)) {
            c.restart(jvmId, version, ee);
        }
        restartEmbeddedObject(clusterId, jvmId);
        stateModified=true;
    }

    public void reLaunch(String clusterId, String jvmId, String version, boolean ee) throws Exception {
        for (ClusterContainer c : clusterManager.getClusters(clusterId)) {
            c.reLaunch(jvmId, version, ee);
        }
        restartEmbeddedObject(clusterId, jvmId);
        stateModified=true;
    }


    public void exit(String clusterId, String jvmId) throws Exception {
        for (ClusterContainer c : clusterManager.getClusters(clusterId)) {
            c.exit(jvmId);
        }
    }

    public void kill(String clusterId, String jvmId) throws Exception {
        for (ClusterContainer c : clusterManager.getClusters(clusterId)) {
            c.kill(jvmId);
        }
        for (ClusterContainer c : clusterManager.getClusters(clusterId)) {
            c.printJvmInfo(jvmId);
        }
        stateModified=true;
    }

    public void jmapHisto(String clusterId, String jvmId) throws Exception {
        for (ClusterContainer c : clusterManager.getClusters(clusterId)) {
            c.jmapHisto(jvmId);
        }
        stateModified=true;
    }

    public void jmapHprof(String clusterId, String jvmId) throws Exception {
        for (ClusterContainer c : clusterManager.getClusters(clusterId)) {
            c.jmapHprof(jvmId);
        }
        stateModified=true;
    }

    public void bashCmd(String clusterId, String jvmId, String cmd) throws Exception {
        for (ClusterContainer c : clusterManager.getClusters(clusterId)) {
            c.bashCmd(jvmId, cmd);
        }
    }

    public void restartEmbeddedObject(String clusterId, String jvmId) throws Exception {
        for (ClusterContainer c : clusterManager.getClusters(clusterId)) {
            c.restartEmbeddedObject(jvmId);
        }
        for (ClusterContainer c : clusterManager.getClusters(clusterId)) {
            c.getResponseExitOnException(jvmId, null, Utils.TIMEOUT_45MIN);
        }
    }

    public void stopBench(String clusterId, String jvmId, String benchId) throws Exception {
        for (ClusterContainer c : clusterManager.getClusters(clusterId)) {
            c.stopBench(jvmId, benchId);
        }
    }


    public void bounce(String clusterId, String jvmId,  int iterations, int restartDelaySec, int iterationDelaySec, String version, boolean ee) throws Exception {

        List<ClusterContainer> clusters = clusterManager.getClusters(clusterId);
        for(int i=0; i<iterations; i++){

            if(iterationDelaySec!=0) {
                Thread.sleep(iterationDelaySec * 1000);
            }

            for (ClusterContainer c : clusters) {
                c.kill(jvmId);
            }
            for (ClusterContainer c : clusters) {
                c.printJvmInfo(jvmId);
            }

            if(restartDelaySec!=0) {
                Thread.sleep(restartDelaySec * 1000);
            }

            for (ClusterContainer c : clusters) {
                if(version==null){
                    c.restart(jvmId);
                } else {
                    c.restart(jvmId, version, ee);
                }
            }

            restartEmbeddedObject(clusterId, jvmId);
        }
        stateModified=true;
    }

    public void bounceRandomMember(String clusterId, int iterations, int restartDelaySec, int iterationDelaySec, String version, boolean ee) throws Exception {

        List<ClusterContainer> clusters = clusterManager.getClusters(clusterId);

        for(int i=0; i<iterations; i++){

            if(iterationDelaySec!=0) {
                Thread.sleep(iterationDelaySec * 1000);
            }

            for (ClusterContainer c : clusters) {
                c.nominateRandomMemberJvm();
            }
            for (ClusterContainer c : clusters) {
                c.kill(c.getEphemerialMember().getId());
            }
            for (ClusterContainer c : clusters) {
                c.printJvmInfo(c.getEphemerialMember().getId());
            }

            if(restartDelaySec!=0) {
                Thread.sleep(restartDelaySec * 1000);
            }

            for (ClusterContainer c : clusters) {
                if(version==null){
                    c.restart(c.getEphemerialMember().getId());
                } else {
                    c.restart(c.getEphemerialMember().getId(), version, ee);
                }
            }
            for (ClusterContainer c : clusters) {
                restartEmbeddedObject(c.getClusterId(), c.getEphemerialMember().getId());
            }
        }
        stateModified=true;
    }



    public void freeze(String clusterId, String jvmId,  int iterations, int restartDelaySec, int iterationDelaySec) throws Exception {

        List<ClusterContainer> clusters = clusterManager.getClusters(clusterId);
        for(int i=0; i<iterations; i++){

            if(iterationDelaySec!=0) {
                Thread.sleep(iterationDelaySec * 1000);
            }

            for (ClusterContainer c : clusters) {
                c.freeze(jvmId);
            }
            for (ClusterContainer c : clusters) {
                c.printJvmInfo("frozen", jvmId);
            }

            if(restartDelaySec!=0) {
                Thread.sleep(restartDelaySec * 1000);
            }

            for (ClusterContainer c : clusters) {
                c.unfreeze(jvmId);
            }
        }
    }

    public void freezeRandomMember(String clusterId, int iterations, int restartDelaySec, int iterationDelaySec) throws Exception {

        List<ClusterContainer> clusters = clusterManager.getClusters(clusterId);

        for(int i=0; i<iterations; i++){

            if(iterationDelaySec!=0) {
                Thread.sleep(iterationDelaySec * 1000);
            }

            for (ClusterContainer c : clusters) {
                c.nominateRandomMemberJvm();
            }
            for (ClusterContainer c : clusters) {
                c.freeze(c.getEphemerialMember().getId());
            }
            for (ClusterContainer c : clusters) {
                c.printJvmInfo("frozen", c.getEphemerialMember().getId());
            }

            if(restartDelaySec!=0) {
                Thread.sleep(restartDelaySec * 1000);
            }

            for (ClusterContainer c : clusters) {
                c.unfreeze(c.getEphemerialMember().getId());
            }
        }
    }


    public void memberCount(String clusterId) {
        for (ClusterContainer c : clusterManager.getClusters(clusterId)) {
            System.out.println(c.getMemberCount());
        }
    }

    public void ls(String clusterId, String jvmId) throws Exception {
        for (ClusterContainer c : clusterManager.getClusters(clusterId)) {
            c.ls(jvmId);
        }
    }

    public void ip(String clusterId, String jvmId) throws Exception {
        for (ClusterContainer c : clusterManager.getClusters(clusterId)) {
            c.ip(jvmId);
        }
    }


    public void tail(String clusterId, String jvmId) throws Exception {
        for (ClusterContainer c : clusterManager.getClusters(clusterId)) {
            c.tail(jvmId);
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
            c.drainQ();
        }
        stateModified=true;
    }

    public void wipe( ) throws IOException, InterruptedException, JMSException {

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


    public void invokeBenchMark(String clusterId, String benchFile) throws Exception {

        BenchManager benchManager = new BenchManager(benchFile);
        System.out.println(Bash.ANSI_PURPLE+benchManager+Bash.ANSI_RESET);

        while( benchManager.hasBench() ){
            benchManager.popBenchMarks();
            System.out.println(Bash.ANSI_CYAN+benchManager.currentBench_toString()+Bash.ANSI_RESET);

            clusterManager.loadBench(clusterId, benchManager);
            clusterManager.setAttributes(clusterId, benchManager);
            clusterManager.initBench(clusterId, benchManager);
            clusterManager.warmupBench(clusterId, benchManager);
            clusterManager.runBench(clusterId, benchManager);
            clusterManager.postPhaseBench(clusterId, benchManager);
            clusterManager.writeMetaDataCmd(clusterId, benchManager);
            clusterManager.removeBench(clusterId, benchManager);
        }
    }

    public void submitBenchMark(String clusterId, String benchFile) throws Exception {

        BenchManager benchManager = new BenchManager(benchFile);

        benchManager.popBenchMarks();
        System.out.println(Bash.ANSI_CYAN+benchManager.currentBench_toString()+Bash.ANSI_RESET);

        clusterManager.loadBench(clusterId, benchManager);
        clusterManager.setAttributes(clusterId, benchManager);
        clusterManager.initBench(clusterId, benchManager);
        clusterManager.submitBench(clusterId, benchManager);
        clusterManager.writeMetaDataCmd(clusterId, benchManager);
    }

    public void wanXml(List<String> ids) throws Exception {
        String srcId = ids.remove(0);
        for (ClusterContainer srcCluster : clusterManager.getClusters(srcId)) {
            for (String targetClustersId : ids) {
                ClusterContainer targetCluster = clusterManager.getClusters(targetClustersId).get(0);
                HzXml.wanReplication(srcCluster, targetCluster);
            }
            //srcCluster.uploadToMemberCwd( HzXml.memberXmlFileForCluster(srcCluster) );
            srcCluster.uploadToMemberBoxUserRootDir( HzXml.memberXmlFileForCluster(srcCluster) );
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
        stateModified=true;
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
        if(stateModified){
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