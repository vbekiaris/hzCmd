package local;

import global.Args;
import global.Bash;
import global.HzType;
import remote.Client;
import remote.Member;
import xml.HzXml;

import java.io.IOException;
import java.io.Serializable;
import java.net.InetAddress;

import static xml.HzXml.memberXml;

public class RemoteJvm implements Serializable {

    public static final String libPath ="$HOME/"+Installer.REMOTE_LIB+"/*";
    public static final String hzPath ="$HOME/"+Installer.REMOTE_HZ_LIB+"/";

    public static final String inFile  =  "in.txt";
    public static final String outFile =  "out.txt";

    private final String homeIp;
    private final Box box;
    private final HzType type;
    private final String id;
    private final String dir;
    private final String xmlConfig;
    private int pid = 0;
    private String version;

    public RemoteJvm(Box box, HzType type, String id, String xmlConfig, String homeIp) {
        this.box = box;
        this.type = type;
        this.id = id;
        this.dir = Installer.REMOTE_HZCMD_ROOT +"/"+id;
        this.xmlConfig = xmlConfig;
        this.homeIp = homeIp;
    }

    public void initilize(String hzVersion, String options) throws IOException, InterruptedException {
        version = hzVersion;
        if(running()){
            System.out.println("cannot restart "+this);
            return;
        }

        box.ssh("mkdir -p " + dir + ";  cd " + dir + ";  touch in.txt; > in.txt");

        String classToRun;
        if (isMember()){
            classToRun = Member.class.getName();
            box.upload(xmlConfig, dir+"/"+ HzXml.memberXml);
        }else{
            classToRun = Client.class.getName();
            box.upload(xmlConfig, dir + "/" + HzXml.clientXml);
        }


        String ip = InetAddress.getLocalHost().getHostAddress();
        if(homeIp!=null){
            ip = homeIp;
        }

        String jvmArgs = new String();
        jvmArgs += "-D"+Args.homeUser+"="+System.getProperty("user.name")+" ";
        jvmArgs += "-D"+Args.homeIp+"="+ip+" ";
        jvmArgs += "-D"+Args.homeCwd+"="+System.getProperty("user.dir")+" ";
        jvmArgs += "-D"+Args.homeInfile+"="+ HzCmd.commsFile+" ";
        jvmArgs += "-D"+Args.ID +"="+id+" ";
        jvmArgs += "-XX:OnOutOfMemoryError=\"touch " +id+".oome"+"\" ";


        String hzLib = hzPath+hzVersion+"/*";
        String pidStr = box.ssh("cd " + dir + "; nohup java -cp \"" + libPath +":"+ hzLib + "\" " + jvmArgs +" "+ options +" "+ classToRun + " < " + inFile + " &> " + outFile + " & echo $!");
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

    public boolean running() {
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

    public void send(String cmd) throws IOException, InterruptedException {
        box.ssh("echo " + cmd + " >> " + dir + "/" + inFile);
    }

    public String cat() throws IOException, InterruptedException {
        return box.cat(dir + "/" + outFile);
    }

    public void tail() throws IOException, InterruptedException {
         box.tail(dir+"/"+outFile);
    }

    public String grep(String args) throws IOException, InterruptedException {
        return box.grep(args+" "+dir+"/"+outFile);
    }

    public void downlonad(String destDir) throws IOException, InterruptedException {
        box.downlonad(dir+"/*", destDir+"/"+id+"-"+box.pri);
    }

    public String getId(){ return id; }

    public String toString() {
        boolean running = running();
        String color = running ? Bash.ANSI_GREEN : Bash.ANSI_RED;
        return color + "RemoteJvm{" +
                " ID=" + id +
                ", running=" + running +
                ", pid=" + pid +
                ", type=" + type +
                ", dir=" + dir +
                ", version=" + version +
                " ip=" + box +
                '}' + Bash.ANSI_RESET;
    }

    public boolean isMember(){
        return type == HzType.Member;
    }

    public boolean isClient(){
        return type == HzType.Client;
    }
}
