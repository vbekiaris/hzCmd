package local;

import global.Args;
import global.Bash;
import global.NodeType;
import mq.MQ;
import remote.bench.BenchType;
import remote.command.*;
import remote.command.bench.*;

import javax.jms.JMSException;
import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public abstract class RemoteJvm implements Serializable {

    public static final String outFile = "out.txt";

    protected String jhicAgent="";

    protected final Box box;
    protected final NodeType type;
    protected final String id;
    protected final String Q;
    protected final String REPLYQ;
    protected final String clusterId;

    protected final String dir;

    protected String vendorLibDir;

    private String launchCmd;
    protected int pid = 0;

    public RemoteJvm(Box box, NodeType type, String id, String clusterId) throws IOException, InterruptedException {
        this.box = box;
        this.type = type;
        this.id = id;
        this.Q = System.getProperty("user.dir")+"/"+id;
        this.REPLYQ = Q+"reply";
        this.clusterId = clusterId;
        this.dir = Installer.REMOTE_HZCMD_ROOT + "/" + id;
        box.mkdir(dir);
    }

    public abstract String getClassToRun();

    public abstract void beforeJvmStart(ClusterManager myCluster) throws Exception;

    public abstract String setJvmStartOptions(Box thisBox, ClusterManager myCluster) throws Exception;


    public void startJvm(String jvmOptions, String libDir, ClusterManager myCluster, String brokerIP) throws Exception {

        this.vendorLibDir = libDir;

        if (isRunning()) {
            System.out.println(Bash.ANSI_CYAN+"all ready started " + this +Bash.ANSI_RESET);
            return;
        }

        beforeJvmStart(myCluster);

        String jvmArgs = setJvmStartOptions(box, myCluster);
        if(jvmArgs==null){
            jvmArgs = new String();
        }

        String classToRun = getClassToRun();

        jvmArgs +=" ";
        jvmArgs += "-D"+Args.MQIP+"="+brokerIP+" ";
        jvmArgs += "-D"+Args.Q+"="+Q+" ";
        jvmArgs += "-D"+Args.ID+"="+id+" ";
        jvmArgs += "-XX:+HeapDumpOnOutOfMemoryError" + " ";
        jvmArgs += "-XX:HeapDumpPath="+id+".hprof" + " ";
        jvmArgs += "-XX:OnOutOfMemoryError=\" date >> " + id + ".oome" + "\" ";

        //String takipiJavaAgent = "-agentlib:TakipiAgent";    String takipiProp = "\"-Dtakipi.name=\"" + id;

        HzCmdProperties properties = new HzCmdProperties();
        if(properties.getBoolean(HzCmdProperties.jhic, "false")) {
            String hz_cmd_src = System.getenv("HZ_CMD_SRC");
            this.uploadcwd(hz_cmd_src+"/lib-jars/jHiccup.jar");

            String jhicArgs = properties.readPropertie(HzCmdProperties.jhicArgs, "-d 0 -i 1000");
            jhicAgent = "-javaagent:jHiccup.jar=\""+jhicArgs+" -l "+clusterId+"-hiccuplog -c\"";
        }

        if(properties.getBoolean(HzCmdProperties.JFR, "false") && type == NodeType.Member) {
            jvmArgs += "-XX:+UnlockCommercialFeatures -XX:+FlightRecorder -XX:StartFlightRecording=delay=5m,duration=1h,dumponexit=true,filename="+id+".jfr,settings=debug.jfc" + " ";
            //jvmArgs += "-XX:+UnlockCommercialFeatures -XX:+FlightRecorder -XX:StartFlightRecording=name="+id+".jfr,settings=debug.jfc -XX:FlightRecorderOptions=defaultrecording=true,disk=true,maxsize=1g,maxage=1h,dumponexit=true,dumponexitpath=./"+id+".jfr" + " ";
        }

        launchCmd = "cd " + dir + "; nohup java "+jhicAgent+" -cp \"" + Installer.REMOTE_HZCMD_LIB_FULL_PATH+"/*" + ":" +  vendorLibDir+"/*"  + "\" " + jvmArgs + " " + jvmOptions + " " + classToRun + " >> " + outFile + " 2>&1 & echo $!";

        launchJvm(launchCmd);
    }


    public final void reStartJvm(ClusterManager myCluster) throws Exception {
        if(launchCmd==null){
            System.out.println(Bash.ANSI_RED+"cant restart jvm never started"+this+Bash.ANSI_RESET);
            return;
        }
        if(  isRunning() ){
            System.out.println(Bash.ANSI_RED+"JVM is Running "+this+Bash.ANSI_RESET);
            return;
        }
        beforeJvmStart(myCluster);
        launchJvm(launchCmd);
    }

    private void launchJvm(String launch) throws IOException, InterruptedException {
        String pidStr = box.ssh(launch);
        pid = Integer.parseInt(pidStr.trim());
    }

    public void clean() throws IOException, InterruptedException {
        kill();
        box.rm(dir + "/*");
    }

    public void kill() throws IOException, InterruptedException {
        if (pid != 0) {
            box.killHard(pid);
            pid = 0;
        }
    }

    public boolean isRunning() {
        try {
            if (pid == 0) {
                return false;
            }

            boolean running = box.sshWithExitCode("ps -p " + pid) == 0;

            if (!running) {
                pid = 0;
            }
            return running;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void exit() throws JMSException {
        MQ.sendObj(Q, new ExitCmd());
    }

    public void load(String taskId, String className) throws IOException, InterruptedException, JMSException {
        MQ.sendObj(Q, new LoadCmd(taskId, className) );
    }

    public void setThreadCount(String taskId, int threadCount) throws IOException, InterruptedException, JMSException {
        MQ.sendObj(Q, new ThreadCountCmd(taskId, threadCount));
    }

    public void setBenchType(String taskId, BenchType type, long intervalNanos, boolean allowException, String outFile) throws IOException, InterruptedException, JMSException{
        MQ.sendObj(Q, new SetBenchTypeCmd(taskId, type, intervalNanos, allowException, outFile) );
    }

    public void writeMetaDataCmd(String taskId, String metaData) throws IOException, InterruptedException, JMSException {
        MQ.sendObj(Q, new MetaDataCmd(taskId, metaData) );
    }

    public void initBench(String taskId) throws IOException, InterruptedException, JMSException {
        MQ.sendObj(Q, new InitCmd(taskId) );
    }

    public void warmupBench(String taskId, int seconds) throws IOException, InterruptedException, JMSException {
        MQ.sendObj(Q, new WarmupCmd(taskId, seconds) );
    }

    public void runBench(String taskId, int seconds) throws IOException, InterruptedException, JMSException {
        MQ.sendObj(Q, new RunBenchCmd(taskId, seconds) );
    }

    public void cleanupBench(String taskId) throws IOException, InterruptedException, JMSException {
        MQ.sendObj(Q, new CleanUpCmd(taskId) );
    }

    public void setField(String taskId, String field, String value) throws Exception {
        MQ.sendObj(Q, new SetFieldCmd(taskId, field, value) );
    }

    public void invokeAsync(int threadCount, String method, String taskId) throws IOException, InterruptedException, JMSException {
        MQ.sendObj(Q, new InvokeAsyncCmd(threadCount, method, taskId) );
    }

    public void invokeSync(int threadCount, String method, String taskId) throws IOException, InterruptedException, JMSException {
        MQ.sendObj(Q, new InvokeSyncCmd(threadCount, method, taskId) );
    }

    public void ping() throws IOException, InterruptedException, JMSException {
        MQ.sendObj(Q, new PingCmd() );
    }

    public Object getResponse() throws IOException, InterruptedException, JMSException {
        return MQ.receiveObj(REPLYQ);
    }

    public Object getResponse(long timeout) throws IOException, InterruptedException, JMSException {
        return MQ.receiveObj(REPLYQ, timeout);
    }



    public String cat() throws IOException, InterruptedException {
        return box.cat(dir + "/" + outFile);
    }

    public String findHprof() throws IOException, InterruptedException {
        return box.find(dir, "*.hprof");
    }

    public String findOOME() throws IOException, InterruptedException {
        return box.find(dir, "*.oome");
    }

    public String findException() throws IOException, InterruptedException {
        return box.find(dir, "exception.txt");
    }

    public String findHsError() throws IOException, InterruptedException {
        return box.find(dir, "hs_err_pid*");
    }

    public String findErrors() throws IOException, InterruptedException {
        return findHprof()+"\n"+findOOME()+"\n"+findException()+"\n"+findHsError();
    }


    public String ls() throws IOException, InterruptedException {
        return box.ssh("ls " + dir + "/");
    }

    public String ssh(String cmd) throws IOException, InterruptedException {
        return box.ssh("cd " + dir + "; " + cmd);
    }

    public void tail() throws IOException, InterruptedException {
         box.tail(dir+"/"+outFile);
    }

    public String grep(String args) throws IOException, InterruptedException {
        return box.grep("'"+args+"' "+dir+"/"+outFile);
    }

    public void downlonad(String destDir) throws IOException, InterruptedException {
        box.downlonad(dir + "/*", destDir + "/" + id);
    }

    public void upload(String src, String dst) throws IOException, InterruptedException {
        box.upload(src, dst);
    }

    public void uploadcwd(String src) throws IOException, InterruptedException {
        if (src!=null){
            List<String> files = Arrays.asList(src.split(","));
            for (String file : files) {
                box.upload(file, dir + "/");
            }
        }
    }

    public Box getBox(){return box;}

    public String getId(){ return id; }

    public String toString() {
        boolean running = isRunning();
        String color = running ? Bash.ANSI_GREEN : Bash.ANSI_RED;
        return color + "jvm{" +
                "id=" + id +
                " running=" + running +
                " pid=" + pid +
                " lib=" + vendorLibDir +
                " dir=" + dir +
                " ip=" + box +
                "}" + Bash.ANSI_RESET;
    }

    public boolean isMember(){
        return type == NodeType.Member;
    }

    public boolean isClient(){
        return type == NodeType.Client;
    }

}