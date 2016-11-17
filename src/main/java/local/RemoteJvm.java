package local;

import global.Args;
import global.Bash;
import global.NodeType;
import local.properties.HzCmdProperties;
import mq.MQ;
import global.BenchType;
import remote.command.*;

import javax.jms.JMSException;
import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public abstract class RemoteJvm implements Serializable {

    public static final String outFile = "out.txt";

    protected String jhicAgent="";

    protected final Box box;
    protected final NodeType type;
    protected final String id;
    protected final String Q;
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
        this.clusterId = clusterId;
        this.dir = Installer.REMOTE_HZCMD_ROOT + "/" + id;
    }

    public abstract String getClassToRun();

    public abstract List<String> stuffToPutInDir() throws Exception;

    public abstract void beforeJvmStart(ClusterContainer myCluster) throws Exception;

    public abstract String setJvmStartOptions(Box thisBox, ClusterContainer myCluster) throws Exception;


    public String startJvm(String jvmOptions, String libDir, ClusterContainer myCluster, String brokerIP) throws Exception {

        this.vendorLibDir = libDir;

        if(jvmOptions==null){
            jvmOptions="";
        }

        if (isRunning()) {
            System.out.println(Bash.ANSI_CYAN+"all ready started " + this +Bash.ANSI_RESET);
            return "";
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
        jvmArgs += "-D"+Args.VENDOR_LIB+"="+vendorLibDir+" ";
        jvmArgs += "-XX:+HeapDumpOnOutOfMemoryError" + " ";
        jvmArgs += "-XX:HeapDumpPath="+id+".hprof" + " ";
        jvmArgs += "-XX:OnOutOfMemoryError=\" date >> " + id + ".oome" + "\" ";

        HzCmdProperties properties = new HzCmdProperties();
        if(properties.getBoolean(HzCmdProperties.JHIC, "false")) {
            String hz_cmd_src = System.getenv("HZ_CMD_SRC");
            this.uploadcwd(hz_cmd_src+"/lib-jars/jHiccup.jar");

            String jhicArgs = properties.readPropertie(HzCmdProperties.JHIC_ARGS, "-d 0 -i 1000");
            jhicAgent = "-javaagent:jHiccup.jar=\""+jhicArgs+" -l "+clusterId+"-hiccuplog -c\"";
        }

        if(properties.getBoolean(HzCmdProperties.JFR, "false") && type == NodeType.Member) {

            //String jfrArgs = properties.readPropertie(HzCmdProperties.JFR_ARGS, "-XX:+UnlockCommercialFeatures -XX:+FlightRecorder -XX:StartFlightRecording=duration=15m,dumponexit=true");
            String jfrArgs = properties.readPropertie(HzCmdProperties.JFR_ARGS, "-XX:+UnlockCommercialFeatures -XX:+FlightRecorder -XX:StartFlightRecording=duration=15m,settings=debug.jfc");

            jvmArgs += jfrArgs+",filename="+id+".jfr" + " ";
            //jvmArgs += "-XX:+UnlockCommercialFeatures -XX:+FlightRecorder -XX:StartFlightRecording=name="+id+".jfr,settings=debug.jfc -XX:FlightRecorderOptions=defaultrecording=true,disk=true,maxsize=1g,maxage=1h,dumponexit=true,dumponexitpath=./"+id+".jfr" + " ";
        }

        if(properties.getBoolean(HzCmdProperties.GC_LOG, "true")) {
            jvmArgs +="-verbose:gc -Xloggc:verbosegc.log" + " ";
            jvmArgs +="-XX:+PrintGCTimeStamps -XX:+PrintGCDetails -XX:+PrintTenuringDistribution -XX:+PrintGCApplicationStoppedTime -XX:+PrintGCApplicationConcurrentTime" + " ";
        }

        String stuffToCpIntoDir = new String();
        for (String s : stuffToPutInDir()) {
            stuffToCpIntoDir += "cp "+s+" "+dir+" ; ";
        }

        launchCmd = "mkdir -p "+dir+"; "+stuffToCpIntoDir+" cd "+dir+" ; nohup java "+jhicAgent+" -cp \"" + dir+":"+Installer.REMOTE_HZCMD_LIB_FULL_PATH+"/*" + ":" +vendorLibDir+"/*" + "\" " + jvmArgs + " " + jvmOptions + " " + classToRun + " >> " + outFile + " 2>&1 & echo $! "+id+" ; cd - > /dev/null";

        return launchCmd;
    }


    public final void reStartJvm() throws Exception {
        if(launchCmd==null){
            System.out.println(Bash.ANSI_RED+"cant restart jvm never started"+this+Bash.ANSI_RESET);
            return;
        }
        if(  isRunning() ){
            System.out.println(Bash.ANSI_RED+"JVM is Running "+this+Bash.ANSI_RESET);
            return;
        }
        launchJvm(launchCmd);
    }

    public void reStartJvm(String libDir, ClusterContainer myCluster, String brokerIP) throws Exception {
        HzCmdProperties properties = new HzCmdProperties();
        String jvmOptions;
        if (isMember()){
            jvmOptions = properties.readPropertie(HzCmdProperties.MEMBER_OPS, "");

        }else {
            jvmOptions = properties.readPropertie(HzCmdProperties.CLIENT_OPS, "");
        }

        launchCmd = startJvm(jvmOptions, libDir, myCluster, brokerIP);
        launchJvm(launchCmd);
    }

    private void launchJvm(String launch) throws IOException, InterruptedException {
        String launchRes = box.ssh(launch);

        String delim = " \n";
        StringTokenizer st = new StringTokenizer(launchRes,delim);

        String pidStr = st.nextToken();

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

    public void freeze() throws IOException, InterruptedException {
        box.freeze(pid);
    }

    public void unfreeze() throws IOException, InterruptedException {
        box.unfreeze(pid);
    }


    public void renice(int nice) throws IOException, InterruptedException {
        box.renice(pid, nice);
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

    public void startEmbeddedObject() throws JMSException {
        MQ.sendObj(Q, new StartEmbeddedObjectCmd() );
    }

    public void load(String taskId, String className) throws IOException, InterruptedException, JMSException {
        MQ.sendObj(Q, new LoadCmd(taskId, className) );
    }

    public void setThreadCount(String taskId, int threadCount) throws IOException, InterruptedException, JMSException {
        MQ.sendObj(Q, new ThreadCountCmd(taskId, threadCount));
    }

    public void setBenchType(String taskId, BenchType type, long intervalNanos, boolean recordException, String outFile) throws IOException, InterruptedException, JMSException{
        MQ.sendObj(Q, new SetBenchTypeCmd(taskId, type, intervalNanos, recordException, outFile) );
    }

    public void writeMetaDataCmd(String taskId, String metaData) throws IOException, InterruptedException, JMSException {
        MQ.sendObj(Q, new MetaDataCmd(taskId, metaData) );
    }

    public void initBench(String taskId) throws IOException, InterruptedException, JMSException {
        MQ.sendObj(Q, new InitCmd(taskId) );
    }

    public void warmupBench(String taskId, long seconds) throws IOException, InterruptedException, JMSException {
        MQ.sendObj(Q, new WarmupCmd(taskId, seconds));
    }

    public void runBench(String taskId, long seconds) throws IOException, InterruptedException, JMSException {
        MQ.sendObj(Q, new RunBenchCmd(taskId, seconds) );
    }

    public void submitBench(String taskId, long seconds) throws IOException, InterruptedException, JMSException {
        MQ.sendObj(Q, new SubmitBenchCmd(taskId, seconds) );
    }

    public void stopBench(String taskId) throws IOException, InterruptedException, JMSException {
        MQ.sendObj(Q, new StopBenchCmd(taskId) );
    }

    public void postPhaseBench(String taskId) throws IOException, InterruptedException, JMSException {
        MQ.sendObj(Q, new PostPhaseCmd(taskId) );
    }

    public void setField(String taskId, String field, String value) throws IOException, InterruptedException, JMSException {
        MQ.sendObj(Q, new SetFieldCmd(taskId, field, value) );
    }


    public void removeBench(String taskId) throws IOException, InterruptedException, JMSException {
        MQ.sendObj(Q, new RemoveBenchCmd(taskId) );
    }

    public Object getResponse(long timeout) throws IOException, InterruptedException, JMSException {
        return MQ.receivReply(timeout);
    }

    public void drainQ() throws JMSException {
        MQ.drainQ(Q);
    }

    public String cat() throws IOException, InterruptedException {
        return box.cat(dir + "/" + outFile);
    }

    public String jstack(String file) throws IOException, InterruptedException {
        return box.jstack(dir, pid, file);
    }

    public String findError() throws IOException, InterruptedException {
        return box.findArgs(dir, "-name exception.txt -o -name *.hprof -o -name *.oome -o -name hs_err_pid*");
    }


    public boolean printErrors() throws IOException, InterruptedException {
        String err = findError();

        if("".equals(err)){
            System.out.println(this);
            System.out.println(Bash.ANSI_RED+err+Bash.ANSI_RESET);
            return true;
        }

        return false;
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
        String color = running ? Bash.ANSI_GREEN : Bash.ANSI_BLUE;
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

    public void setPid(String pid) {
        int p = Integer.parseInt(pid);
        this.pid = p;
    }

}