package main;

import cmdline.CmdLine;
import cmdline.Command;
import global.ClusterSize;
import local.*;
import remote.BenchType;
import vendor.gem.GemJvmFactory;
import vendor.gg.GgJvmFactory;
import global.Args;
import global.Bash;
import global.ClusterType;
import vendor.hz.HzJvmFactory;
import jms.MQ;
import vendor.redis.RedisJvmFactory;

import javax.jms.JMSException;
import java.io.*;
import java.util.*;

import static global.Utils.myIp;

// mvn clean install dependency:copy-dependencies
//TODO WAN REP xml SETUP
//TODO man center integ
//TODO exampe 3 3node cluster wan replicate ring, with rolling upgrade,  members putting,  clients getting,  kill restart members,  man center isRunning,
public class HzCmd implements Serializable {

    public static final String propertiesFile = "main.properties";

    private Map<String, BoxManager> boxes = new HashMap();
    private Map<String, ClusterManager> clusters = new HashMap();

    private BenchMarkSettings benchMarkSettings = new BenchMarkSettings();

    private String brokerIP=null;

    public void showSSH(boolean show){
        Bash.showSSH = show;
    }

    public void listen() throws IOException, InterruptedException{
        String eventQ = System.getProperty("user.dir")+"/"+Args.EVENTQ.name();
        while (true){
            try {
                Object o = MQ.receiveObj(eventQ);
                if (o instanceof Exception){
                    Exception e = (Exception) o;
                    System.out.println(Bash.ANSI_RED+" "+e+" "+e.getCause()+Bash.ANSI_RESET);
                    e.printStackTrace();
                }else{
                    System.out.println(o);
                }
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }

    public void initCluster(String user, String boxes, String clusterId, ClusterType type, ClusterSize size, boolean ee, String version, String memberJvmOptions, String clientJvmOption, String libFiles, String cwdFiles) throws Exception{

        addBoxes(user, boxes);
        addCluster(clusterId, boxes, type);
        install(clusterId, ee, version);

        if(libFiles!=null) {
            uploadLib(clusterId, libFiles);
        }

        int m = ClusterSize.getMemberCount(size);
        addMembers(clusterId, m, version,  memberJvmOptions, cwdFiles);

        ClusterManager cm = clusters.get(clusterId);
        JvmFactory jvmFactory = cm.getJvmFactory();
        jvmFactory.membersAdded(cm.getMemberBoxes());

        int c = ClusterSize.getClientCount(size);
        addClients(clusterId, c, version, clientJvmOption, cwdFiles);
    }

    public void addBoxes(String user, String file) throws IOException, InterruptedException{
        if (boxes.containsKey(file)){
            System.out.println(Bash.ANSI_RED+"file "+file + " all ready imported"+Bash.ANSI_RESET);
            return;
        }

        BoxManager b = new BoxManager(file);
        b.addBoxes(user, file);
        boxes.put(file, b);
    }

    public void addCluster(String clusterId, String boxGroupId, ClusterType type) throws Exception{
        if (clusters.containsKey(clusterId)){
            System.out.println(Bash.ANSI_RED+"cluster "+clusterId + " all ready created"+Bash.ANSI_RESET);
            return;
        }

        BoxManager boxi = boxes.get(boxGroupId);
        if(boxi == null){
            System.out.println(Bash.ANSI_RED+"box group "+boxGroupId + " not found"+Bash.ANSI_RESET);
            return;
        }

        if(brokerIP==null){
            brokerIP = myIp();
        }

        ClusterManager cluster;
        switch (type){
            case HZ:
                cluster = new ClusterManager(clusterId, boxi, brokerIP, new HzJvmFactory());
                break;
            case GG:
                cluster = new ClusterManager(clusterId, boxi, brokerIP, new GgJvmFactory());
                break;
            case GEM:
                cluster = new ClusterManager(clusterId, boxi, brokerIP, new GemJvmFactory());
                break;
            case RED:
                cluster = new ClusterManager(clusterId, boxi, brokerIP, new RedisJvmFactory());
                break;
            default:
                System.out.println(Bash.ANSI_RED+"box group "+boxGroupId + " not found"+Bash.ANSI_RESET);
                return;
        }

        clusters.put(cluster.getClusterId(), cluster);
        System.out.println(cluster);
    }

    public void install(String clusterId, boolean ee, String... versions) throws IOException, InterruptedException {
        for (ClusterManager c : clusters.values()) {
            if(c.matchClusterId(clusterId)){
                System.out.println(Bash.ANSI_YELLOW+"Installing cluster "+c.getClusterId()+Bash.ANSI_RESET);
                Installer.install(c.getBoxManager(), c.getJvmFactory(), ee, versions);
                c.setVersion(versions);
            }
        }
    }

    public void dedicatedMembers(String clusterId, int memberBox) {
        for (ClusterManager c : clusters.values()) {
            if(c.matchClusterId(clusterId)){
                c.setMembersOnlyCount(memberBox);
            }
        }
    }

    public void addMembers(String clusterId, int qty, String version, String jvmOptions, String cwdfiles) throws Exception {
        List<RemoteJvm> added = new ArrayList();
        for (ClusterManager c : clusters.values()) {
            if(c.matchClusterId(clusterId)){
                added.addAll(c.addMembers(qty, version, jvmOptions, cwdfiles));
            }
        }
        checkAddJvms(added);
    }

    public void addClients(String clusterId, int qty, String version, String jvmOptions, String cwdfiles) throws Exception {
        List<RemoteJvm> added = new ArrayList();
        for (ClusterManager c : clusters.values()) {
            if(c.matchClusterId(clusterId)){
                added.addAll(c.addClients(qty, version, jvmOptions, cwdfiles));
            }
        }
        checkAddJvms(added);
    }

    public void restart(String jvmId, String version,  String options) throws Exception {
        for (ClusterManager c : clusters.values()) {
            c.restart(jvmId, version, options);
        }
    }

    private void checkAddJvms(List<RemoteJvm> added) throws JMSException, IOException, InterruptedException {
        for (RemoteJvm jvm : added) {
            System.out.println(jvm);
        }
        for (RemoteJvm jvm : added) {
            Object o = jvm.getResponse();

            if(o instanceof Exception){
                Exception e = (Exception) o;
                System.out.println(Bash.ANSI_RED+" "+e+" "+e.getCause()+Bash.ANSI_RESET);
                e.printStackTrace();
            }else{
                System.out.println(Bash.ANSI_GREEN + o + Bash.ANSI_RESET);
            }
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


    public void wipe( ) throws IOException, InterruptedException {
        for (ClusterManager c : clusters.values()) {
            c.kill(".*");
        }
        clusters.clear();

        for (BoxManager boxManager : boxes.values()) {
            boxManager.rm(Installer.REMOTE_HZCMD_ROOT);
        }
        boxes.clear();
    }

    public void load(String jvmId,  String taskId, String className) throws Exception {
        for (ClusterManager c : clusters.values()) {
            c.load(jvmId, taskId, className);
        }
    }

    public void setField(String jvmId, String taskId, String field, String value) throws Exception {
        for (ClusterManager c : clusters.values()) {
            c.setField(jvmId, taskId, field, value);
        }
    }

    public void invokeAsync(String jvmId, int threadCound, String method, String taksId) throws Exception {
        for (ClusterManager c : clusters.values()) {
            c.invokeAsync(jvmId, threadCound, method, taksId);
        }
    }

    public void invokeSync(String jvmId, int threadCound, String method, String taksId) throws Exception {
        for (ClusterManager c : clusters.values()) {
            c.invokeSync(jvmId, threadCound, method, taksId);
        }
        for (ClusterManager c : clusters.values()) {
            c.getResponse(jvmId);
        }
    }

    public void ping(String jvmId, long timeout) {
        for (ClusterManager c : clusters.values()) {
            for (RemoteJvm jvm : c.getMatchingJms(jvmId) ) {
                try {
                    jvm.ping();
                } catch (Exception e) {
                    System.out.println(Bash.ANSI_RED+"failed to send ping to "+jvm.getId()+Bash.ANSI_RESET);
                }

                try {
                    jvm.getResponse(timeout);
                    System.out.println(Bash.ANSI_GREEN+jvm.getId()+" ping"+Bash.ANSI_RESET);
                } catch (Exception e) {
                    System.out.println(Bash.ANSI_RED+"failed to recive ping from "+jvm.getId()+Bash.ANSI_RESET);
                }
            }
        }
    }

    public void invokeBenchMarks(String clusterId, String benchFile) throws Exception {
        int benchNumber=0;
        ClusterManager c = clusters.get(clusterId);

        BenchManager bencher = new BenchManager(benchFile);

        for (String drivers : benchMarkSettings.getDrivers()) {

            for (String taskId : bencher.getTaskIds()) {

                String className = bencher.getClassName(taskId);
                load(drivers, taskId, className);

                for (String benchType : benchMarkSettings.getTypes()) {

                    setField(drivers, taskId, "benchType", benchType);

                    setField(drivers, taskId, "warmupSec", benchMarkSettings.getWarmupSec());
                    setField(drivers, taskId, "durationSec", benchMarkSettings.getDurationSec());

                    String filedSetup = new String();
                    for (FieldValue field : bencher.getFieldsToSet(taskId)) {
                        setField(drivers, taskId, field.field, field.value);
                        filedSetup+="_"+field.field+"-"+field.value;
                    }

                    for (List<FieldValue> settings : bencher.getSettings(taskId)) {

                        String itteratedFieldSetup = new String();
                        for (FieldValue setting : settings) {
                            setField(drivers, taskId, setting.field, setting.value);
                            itteratedFieldSetup += "_" + setting.field + "-" + setting.value;
                        }

                        for (int threadCount : benchMarkSettings.getThreads()) {

                            String version = c.getVersionString();
                            String metaData = clusterId+"_"+version+"_"+"M"+c.getMemberCount()+"-C"+c.getClientCount()+"_driver-"+drivers+"_benchType-"+benchType+"_"+taskId+"_"+className + filedSetup + itteratedFieldSetup+"_threads-"+threadCount;
                            setField(drivers, taskId, "metaData", metaData);

                            String fileName = clusterId+"_"+version+"_"+taskId+"_"+className+"_"+benchNumber;
                            invokeBenchMark(drivers, threadCount, taskId, fileName);
                            benchNumber++;
                        }
                    }
                }
            }
        }

        download(clusterId, "output/" + clusterId);

        if(benchMarkSettings.benchTypesContains(BenchType.Metrics)){
            chartAllJavaMetrics("output/" + clusterId);
        }
    }



    public void invokeBenchMark(String jvmId, int threadCound, String taksId, String fileName) throws Exception {
        invokeSync(jvmId, 1, "init", taksId);

        setField(jvmId, taksId, "fileName", fileName+"-warmup");
        invokeSync(jvmId, 1, "preBench", taksId);
        invokeSync(jvmId, threadCound, "warmup", taksId);
        invokeSync(jvmId, 1, "postBench", taksId);


        setField(jvmId, taksId, "fileName", fileName+"-bench");
        invokeSync(jvmId, 1, "preBench", taksId);
        invokeSync(jvmId, threadCound, "run", taksId);
        invokeSync(jvmId, 1, "postBench", taksId);

        //invokeSync(jvmId, 1, "cleanup", taksId);
    }

    public void stop(String jvmId, String taskId) throws Exception {
        for (ClusterManager c : clusters.values()) {
            c.stop(jvmId, taskId);
        }
    }

    public void scpUp(String jvmId, String src, String dst) throws IOException, InterruptedException {
        for (ClusterManager c : clusters.values()) {
            for (RemoteJvm jvm : c.getMatchingJms(jvmId) ) {
                jvm.upload(src, dst);
            }
        }
    }

    public void uploadCwd(String jvmId, String src) throws IOException, InterruptedException {
        for (ClusterManager c : clusters.values()) {
            for (RemoteJvm jvm : c.getMatchingJms(jvmId) ) {
                jvm.uploadcwd(src);
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
        String clu="";
        for(ClusterManager c : clusters.values()) {
            clu += c.toString() + "\n";
        }
        return clu ;
    }

    private static HzCmd loadHzCmd(){
        HzCmd hzCmd = null;
        try {
            FileInputStream fileIn = new FileInputStream("HzCmd.ser");
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
            FileOutputStream fileOut = new FileOutputStream("HzCmd.ser");
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
        Bash.executeCommand("driver-wideMetrics "+dir+" "+red+" "+blue);
    }

    public void chartComparisonHdr(String dir, String red, String blue) throws IOException, InterruptedException {
        Bash.executeCommand("chart-allComparisonHdr "+dir+" "+red+" "+blue);
    }


    public void setBenchDrivers(String drivers){
        benchMarkSettings.setDrivers(drivers);
    }

    public void setBenchThreadCounts(String threadsCount) {
        benchMarkSettings.setThreads(threadsCount);
    }

    public void setBenchWarmupSec(int warmupSec) {
        benchMarkSettings.setWarmupSec(warmupSec);
    }

    public void setBenchBenchDurationSec(int durationSec) {
        benchMarkSettings.setDurationSec(durationSec);
    }

    public void setBenchType(String type) {
        benchMarkSettings.setType(type);
    }
}