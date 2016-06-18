package main;

import cmdline.main.CmdLine;
import cmdline.base.Command;
import global.ClusterSize;
import local.*;
import local.bench.BenchManager;
import local.bench.BenchMarkSettings;
import local.bench.FieldValue;
import remote.bench.BenchType;
import vendor.gem.GemJvmFactory;
import vendor.gg.GgJvmFactory;
import global.Bash;
import global.ClusterType;
import vendor.hz.HzJvmFactory;
import mq.MQ;
import vendor.redis.RedisJvmFactory;

import javax.jms.JMSException;
import java.io.*;
import java.util.*;

import static global.Utils.myIp;

//TODO
//multi files in sequence needs bench number by cluster,
//multi benchID's in file in parellel,

//print cluster layout info

//look for errors on download

//better dir struct for bench results

//for getting the total ops from hdr
//find . -name *.hgrm | xargs -n1 -I% sh -c 'grep "Total count" % | awk "{print \$7}" | tr -d ] > %.total'

//for auto gcviewer pics
//find . -name verbosegc.log | xargs -n1 -I% sh -c "java -jar $(find ~/.m2 -name gcviewer-1.34.1.jar) % %.csv %.png"

//add JFR cmd settings

//MQ Request response, with temp reply Q's for proper com's

//bench cleanup from just 1 thread.


public class HzCmd implements Serializable {

    public static final String serFile = "HzCmd.ser";
    public static final String propertiesFile = "HzCmd.properties";

    private Map<String, ClusterManager> clusters = new HashMap();

    private BenchMarkSettings benchMarkSettings = new BenchMarkSettings();

    private String brokerIP=null;


    public void initCluster(String user, String file, String clusterId, ClusterType type, ClusterSize size, boolean ee, String version, String libFiles, String cwdFiles) throws Exception{

        HzCmdProperties properties = new HzCmdProperties();

        BoxManager boxManager = new BoxManager(file, user);
        System.out.print(boxManager);

        ClusterManager cluster = getCluster(clusterId, type);
        cluster.addUniquBoxes(boxManager);
        Installer.install(cluster.getBoxManager(), cluster.getJvmFactory(), ee, version, libFiles);
        cluster.setMembersOnlyCount(size.dedicatedMemberBox());

        System.out.print(cluster);


        String memberOps = properties.readPropertie(HzCmdProperties.memberOps, "");
        cluster.addMembers(size.getMemberCount(), version, memberOps, cwdFiles);

        JvmFactory jvmFactory = cluster.getJvmFactory();
        jvmFactory.membersAdded(cluster.getMemberBoxes());

        Thread.sleep(5000);

        String clientOps = properties.readPropertie(HzCmdProperties.clientOps, "");
        cluster.addClients(size.getClientCount(), version, clientOps, cwdFiles);
    }


    public ClusterManager getCluster(String clusterId, ClusterType type) throws Exception{

        ClusterManager cluster = clusters.get(clusterId);

        if(cluster==null) {
            if (brokerIP == null) {
                brokerIP = myIp();
            }
            switch (type) {
                case HZ:
                    cluster = new ClusterManager(clusterId, brokerIP, new HzJvmFactory());
                    break;
                case GG:
                    cluster = new ClusterManager(clusterId, brokerIP, new GgJvmFactory());
                    break;
                case GEM:
                    cluster = new ClusterManager(clusterId, brokerIP, new GemJvmFactory());
                    break;
                case RED:
                    cluster = new ClusterManager(clusterId, brokerIP, new RedisJvmFactory());
                    break;
            }
            clusters.put(cluster.getClusterId(), cluster);
        }
        return cluster;
    }


    public void restart(String jvmId, String version,  String options) throws Exception {
        for (ClusterManager c : clusters.values()) {
            c.restart(jvmId, version, options);
        }
    }

    public void exit(String jvmId) throws Exception {
        for (ClusterManager c : clusters.values()) {
            c.exit(jvmId);
        }
    }

    public void kill(String jvmId) throws Exception {
        for (ClusterManager c : clusters.values()) {
            c.kill(jvmId);
        }
    }

    public void restartEmbeddedObject(String jvmId) throws Exception {
        for (ClusterManager c : clusters.values()) {
            c.restartEmbeddedObject(jvmId);
        }
    }

    public void bounce(String jvmId, int iterations, int initialDelaySec, int restartDelaySec, int iterationDelaySec) throws Exception {

        if(initialDelaySec!=0) {
            Thread.sleep(initialDelaySec * 1000);
        }

        for(int i=0; i<iterations; i++){
            for (ClusterManager c : clusters.values()) {
                c.kill(jvmId);
            }
            for (ClusterManager c : clusters.values()) {
                c.printJvmInfo(jvmId);
            }

            if(restartDelaySec!=0) {
                Thread.sleep(restartDelaySec * 1000);
            }

            for (ClusterManager c : clusters.values()) {
                c.restart(jvmId);
            }
            for (ClusterManager c : clusters.values()) {
                c.getResponses(jvmId);
            }

            if(iterationDelaySec!=0) {
                Thread.sleep(iterationDelaySec * 1000);
            }
        }
    }


    public void jps( ) throws IOException, InterruptedException {
        for (ClusterManager c : clusters.values()) {
            c.getBoxManager().jps();
        }
    }

    public void ls(String jvmId) throws Exception {
        for (ClusterManager c : clusters.values()) {
            c.ls(jvmId);
        }
    }

    public void cat(String jvmId) throws Exception {
        for (ClusterManager c : clusters.values()) {
            c.cat(jvmId);
        }
    }

    public void jstack(String jvmId, String file) throws Exception {
        for (ClusterManager c : clusters.values()) {
            c.jstack(jvmId, file);
        }
    }

    public void tail(String jvmId) throws Exception {
        for (ClusterManager c : clusters.values()) {
            c.tail(jvmId);
        }
    }

    public void grep(String jvmId, String grepArgs) throws Exception {
        for (ClusterManager c : clusters.values()) {
            c.grep(jvmId, grepArgs);
        }
    }

    public void download(String jvmId, String dir) throws Exception {
        for (ClusterManager c : clusters.values()) {
            c.downlonad(jvmId, dir);
        }
    }

    public boolean printErrors(String jvmId) throws IOException, InterruptedException {
        boolean error=false;
        for (ClusterManager c : clusters.values()) {
            error |= c.printErrors(jvmId);
        }
        return error;
    }

    public void clean(String jvmId) throws Exception {
        for (ClusterManager c : clusters.values()) {
            c.clean(jvmId);
        }
    }

    public void ssh(String jvmId, String cmd) throws Exception {
        for (ClusterManager c : clusters.values()) {
            c.ssh(jvmId, cmd);
        }
    }

    public void wipe( ) throws IOException, InterruptedException, JMSException {

        Bash.rmQuite(serFile);
        Bash.rmQuite(propertiesFile);

        Map<String, ClusterManager> clustersTemp = clusters;
        for (ClusterManager c : clustersTemp.values()) {
            c.getBoxManager().killAllJava();
        }
        for (ClusterManager c : clustersTemp.values()) {
            c.getBoxManager().rm(Installer.REMOTE_HZCMD_ROOT);
        }
        for (ClusterManager c : clustersTemp.values()) {
            c.drainQ();
        }
        clusters.clear();
    }

    public void wipeLocal( ) throws IOException, InterruptedException, JMSException {

        Bash.rmQuite(serFile);
        Bash.rmQuite(propertiesFile);

        for (ClusterManager c : clusters.values()) {
            c.drainQ();
        }

        for (ClusterManager c : clusters.values()) {
            c.getBoxManager().rm(Installer.REMOTE_HZCMD_ROOT);
        }
        clusters.clear();

        Bash.killAllJava();
    }


    public void invokeBenchMark(String clusterId, String benchFile, final boolean warmup) throws Exception {

        HzCmdProperties properties = new HzCmdProperties();
        int benchNumber = properties.readIntPropertie(HzCmdProperties.BENCH_NUMBER, 0);

        ClusterManager cluster = clusters.get(clusterId);

        BenchManager bencher = new BenchManager(benchFile);

        for (String drivers : benchMarkSettings.getDrivers()) {

            for (String taskId : bencher.getTaskIds()) {

                String className = bencher.getClassName(taskId);

                for (BenchType benchType : benchMarkSettings.getTypes()) {

                    for (List<FieldValue> settings : bencher.getSettings(taskId)) {

                        for (int threadCount : benchMarkSettings.getThreads()) {

                            for (long interval : benchMarkSettings.getIntervalNanos()) {

                                for (int warmupSec : benchMarkSettings.getWarmupSec()) {

                                    for (int durationSec : benchMarkSettings.getDurationSec()) {

                                        for (int repeater = 0; repeater < benchMarkSettings.repeatCount(); repeater++) {

                                            String version = cluster.getVersionsString();
                                            String metaData = "clusterId " + clusterId + "\n" +
                                                    "version " + version + "\n" +
                                                    "Members " + cluster.getMemberCount() + "\n" +
                                                    "Clients " + cluster.getClientCount() + "\n" +
                                                    "drivers " + drivers + "\n" +
                                                    "benchType " + benchType + "\n" +
                                                    "interval " + interval + "\n" +
                                                    "taskID " + taskId + "\n" +
                                                    "class " + className + "\n" +
                                                    "allowException " + benchMarkSettings.getAllowException() + "\n" +
                                                    "threads " + threadCount + "\n" +
                                                    "warmupSec " + warmupSec + "\n" +
                                                    "benchSec " + durationSec + "\n" +
                                                    "benchNum " + benchNumber + "\n";

                                            for (FieldValue field : bencher.getFieldsToSet(taskId)) {
                                                metaData += field.field + " " + field.value + "\n";
                                            }
                                            for (FieldValue setting : settings) {
                                                metaData += setting.field + " " + setting.value + "\n";
                                            }

                                            //split up by dir's  even map/struct name
                                            //String fileName = benchType.name()+"/"+drivers+"/"+taskId+"/"+"threads"+threadCount+"/"+clusterId+"_"+version+"_"+taskId+"_"+className+"_"+benchNumber;

                                            String fileName = clusterId + "_" + version + "_" + taskId + "_" + className + "_" + benchNumber;

                                            cluster.load(drivers, taskId, className);
                                            cluster.setThreadCount(drivers, taskId, threadCount);
                                            cluster.setBenchType(drivers, taskId, benchType, interval, benchMarkSettings.getAllowException(), fileName);

                                            for (FieldValue setting : settings) {
                                                cluster.setField(drivers, taskId, setting.field, setting.value);
                                            }

                                            for (FieldValue field : bencher.getFieldsToSet(taskId)) {
                                                cluster.setField(drivers, taskId, field.field, field.value);
                                            }
                                            cluster.initBench(drivers, taskId);

                                            if (warmup) {
                                                cluster.warmupBench(drivers, taskId, warmupSec);
                                            }
                                            cluster.runBench(drivers, taskId, durationSec);
                                            cluster.cleanupBench(drivers, taskId);
                                            cluster.writeMetaDataCmd(drivers, taskId, metaData);

                                            benchNumber++;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        properties.writeIntPropertie(HzCmdProperties.BENCH_NUMBER, benchNumber);
        System.out.println(Bash.ANSI_YELLOW + "The End" + Bash.ANSI_RESET);
    }


    public void scpUp(String jvmId, String src, String dst) throws IOException, InterruptedException {
        for (ClusterManager c : clusters.values()) {
            for (RemoteJvm jvm : c.getMatchingJms(jvmId) ) {
                jvm.upload(src, dst);
            }
        }
    }

    public void uploadLib(String clusterId, String src) throws IOException, InterruptedException {
        ClusterManager c = clusters.get(clusterId);
        if(c != null){
            System.out.println(Bash.ANSI_YELLOW+"Installing "+src+" for cluster "+c.getClusterId()+Bash.ANSI_RESET);
            c.uploadLib(src);
        }
    }

    public void setBrokerIP(String brokerIP) {
        this.brokerIP = brokerIP;
    }

    @Override
    public String toString() {
        String s="";
        for(ClusterManager c : clusters.values()) {
            s += c.toString() + "\n";
        }
        return s;
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

    public void processJhicOutput(String dir) throws IOException, InterruptedException {
        Bash.executeCommand("process-hic "+dir);
    }

    public void metricsRegresionCheck(String dir, String red, String blue) throws IOException, InterruptedException {
        Bash.executeCommand("driver-wideMetrics "+dir+" "+red+" "+blue);
    }

    public void hdrRegresionCheck(String dir, String red, String blue) throws IOException, InterruptedException {
        Bash.executeCommand("driver-wideHdr "+dir+" "+red+" "+blue);
    }


    public void setBenchDrivers(String drivers){
        benchMarkSettings.setDrivers(drivers);
    }

    public void setBenchAllowException(boolean allow){
        benchMarkSettings.setAllowException(allow);
    }

    public void setBenchThreadCounts(String threadsCount) {
        benchMarkSettings.setThreads(threadsCount);
    }

    public void setBenchWarmupSec(String warmupSec) {
        benchMarkSettings.setWarmupSec(warmupSec);
    }

    public void setBenchBenchDurationSec(String durationSec) {
        benchMarkSettings.setDurationSecs(durationSec);
    }

    public void setBenchType(String type) {
        benchMarkSettings.setType(type);
    }

    public void setBenchInterval(String intervalMillis) {
        benchMarkSettings.setIntervalByMsec(intervalMillis);
    }

    public void setRepeatCount(int repeatCount) {
        benchMarkSettings.setRepeatCount(repeatCount);
    }

}