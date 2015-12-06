package local;

import global.Args;
import global.Bash;
import global.HzType;
import remote.Client;
import remote.Member;

import java.io.IOException;
import java.net.InetAddress;

public class RemoteJvm {

    public static final String libPath ="$HOME/"+Installer.REMOTE_LIB+"/*";
    public static final String hzPath ="$HOME/"+Installer.REMOTE_HZ_LIB+"/";


    public static final String inFile  =  "in.txt";
    public static final String outFile =  "out.txt";

    private final String user;
    private final IpPair ips;
    private final HzType type;
    private final String id;
    private final String dir;
    private int pid = 0;

    public RemoteJvm(String user, IpPair ips, HzType type, String id) {
        this.user = user;
        this.ips = ips;
        this.type = type;
        this.id = id;
        this.dir = Installer.REMOTE_ROOT+"/"+id;
    }

    public void initilize(String hzVersion) throws IOException, InterruptedException {

        if(running()){
            System.out.println(this + "is running !");
            return;
        }

        Bash.ssh(user, ips.pub, "mkdir -p " + dir + ";  cd " + dir + ";  touch in.txt");

        String classToRun;
        if (isMember()){
            classToRun = Member.class.getName();
            Bash.scpUp(user, ips.pub, "hazelcast.xml", dir+"/");
        }else{
            classToRun = Client.class.getName();
            Bash.scpUp(user, ips.pub, "client-hazelcast.xml", dir+"/");
        }

        String jvmArgs = new String();
        jvmArgs += "-D"+Args.homeUser+"="+System.getProperty("user.name")+" ";
        jvmArgs += "-D"+Args.homeIp+"="+InetAddress.getLocalHost().getHostAddress()+" ";
        jvmArgs += "-D"+Args.homeCwd+"="+System.getProperty("user.dir")+" ";
        jvmArgs += "-D"+Args.homeInfile+"="+ HzCmd.commsFile+" ";
        jvmArgs += "-D"+Args.ID +"="+id+" ";


        String hzLib = hzPath+hzVersion+"/*";
        String pidStr = Bash.ssh(user, ips.pub, "cd " + dir + "; nohup java -cp \"" + libPath +":"+ hzLib + "\" " + jvmArgs + " " + classToRun + " < " + inFile + " &> " + outFile + " & echo $!");
        pid = Integer.parseInt(pidStr.trim());
        System.out.println("started "+this);
    }

    public void clean() throws IOException, InterruptedException {
        Bash.ssh(user, ips.pub, "rm " + dir+"/*");
    }

    public void kill() throws IOException, InterruptedException {
        Bash.ssh(user, ips.pub, "kill -9 "+pid);
        pid=0;
    }

    public boolean running() {
        try {
            return Bash.sshWithExitCode(user, ips.pub, "ps -p "+pid) == 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void send(String cmd) throws IOException, InterruptedException {
        Bash.ssh(user, ips.pub, "echo "+cmd+" >> "+dir+"/"+inFile);
    }

    public String cat() throws IOException, InterruptedException {
        return Bash.ssh(user, ips.pub, "cat "+dir+"/"+outFile);
    }

    public void tail() throws IOException, InterruptedException {
        Bash.ssh(user, ips.pub, "tail "+dir+"/"+outFile);
    }

    public void grep(String args) throws IOException, InterruptedException {
        Bash.ssh(user, ips.pub, "grep "+args+" "+dir+"/"+outFile);
    }


    public String getId(){ return id; }

    public String toString() {

        boolean running = running();

        return "RemoteJvm{" +
                " ip=" + ips +
                ", ID=" + id +
                ", running=" + running +
                ", pid=" + pid +
                ", type=" + type +
                ", dir=" + dir +
                '}';
    }

    public boolean isMember(){
        return type == HzType.Member;
    }

    public boolean isClient(){
        return type == HzType.Client;
    }
}
