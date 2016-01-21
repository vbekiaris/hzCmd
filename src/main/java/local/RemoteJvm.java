package local;

import global.Args;
import global.Bash;
import global.NodeType;
import jms.MQ;

import javax.jms.JMSException;
import java.io.IOException;
import java.io.Serializable;

public abstract class RemoteJvm implements Serializable {

    public static final String libPath ="$HOME/"+Installer.REMOTE_LIB+"/*";

    public static final String outFile =  "out.txt";

    protected final Box box;
    protected final NodeType type;
    protected final String id;
    protected final String dir;
    protected int pid = 0;

    public RemoteJvm(Box box, NodeType type, String id) {
        this.box = box;
        this.type = type;
        this.id = id;
        this.dir = Installer.REMOTE_HZCMD_ROOT +"/"+id;
    }

    public abstract String getClassToRun();
    public abstract String getVendorLibDir(String version);

    public abstract void beforeJvmStart(ClusterManager myCluster) throws Exception;


    public final void startJvm(String version, String jvmOptions) throws IOException, InterruptedException {

        if(isRunning()){
            System.out.println("all ready started "+this);
            return;
        }

        box.ssh("mkdir -p " + dir);

        String classToRun = getClassToRun();
        String vendorLibDir = getVendorLibDir(version)+"/*";

        String jvmArgs = new String();
        jvmArgs += "-D"+Args.ID +"="+id+" ";
        jvmArgs += "-XX:OnOutOfMemoryError=\"touch " +id+".oome"+"\" ";

        String pidStr = box.ssh("cd " + dir + "; nohup java -agentlib:TakipiAgent -cp \"" + libPath +":"+ vendorLibDir + "\" " + jvmArgs +" "+"-Dtakipi.name="+id+" "+ jvmOptions +" "+ classToRun + " >> " + outFile + " 2>&1 & echo $!");
        pid = Integer.parseInt(pidStr.trim());
    }

    public void clean() throws IOException, InterruptedException {
        box.rm(dir + "/*");
    }

    public void kill() throws IOException, InterruptedException {
        if(pid!=0){
            box.killHard(pid);
            pid=0;
        }
    }

    public boolean isRunning() {
        try {
            if(pid==0){
               return false;
            }

            boolean running =  box.sshWithExitCode("ps -p "+pid) == 0;

            if(!running){
                pid=0;
            }
            return running;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    public void invoke(int threadCount, String method, String taskId) throws IOException, InterruptedException, JMSException {
        MQ.send(id, threadCount + " " + method + " " + taskId );
    }

    public String getResponse() throws IOException, InterruptedException, JMSException {
        return MQ.receiveAnyResponse();
    }

    public String cat() throws IOException, InterruptedException {
        return box.cat(dir + "/" + outFile);
    }

    public void tail() throws IOException, InterruptedException {
         box.tail(dir+"/"+outFile);
    }

    public String grep(String args) throws IOException, InterruptedException {
        return box.grep("'"+args+"' "+dir+"/"+outFile);
    }

    public void downlonad(String destDir) throws IOException, InterruptedException {
        box.downlonad(dir+"/*", destDir+"/"+id+"-"+box.pri);
    }

    public String getId(){ return id; }

    public String toString() {
        boolean running = isRunning();
        String color = running ? Bash.ANSI_GREEN : Bash.ANSI_RED;
        return color + "RemoteHzJvm{" +
                " ID=" + id +
                ", isRunning=" + running +
                ", pid=" + pid +
                ", type=" + type +
                ", dir=" + dir +
                " ip=" + box +
                '}' + Bash.ANSI_RESET;
    }

    public boolean isMember(){
        return type == NodeType.Member;
    }

    public boolean isClient(){
        return type == NodeType.Client;
    }
}