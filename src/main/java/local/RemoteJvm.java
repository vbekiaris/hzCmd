package local;

import global.Args;
import global.Bash;
import remote.Client;
import remote.Member;

import java.io.IOException;
import java.net.InetAddress;

public class RemoteJvm {

    public static final String classPath="-cp \"$HOME/"+Installer.REMOTE_ROOT+"/lib/*\"";

    public static final String inFile  =  "in.txt";
    public static final String outFile =  "out.txt";

    public static enum JVM_TYPE {
        Client, Member
    }

    private final IpPair ips;
    private final JVM_TYPE type;
    private final int count;
    private final String id;
    private final String dir;
    private String pid = "0";

    public RemoteJvm(IpPair ips, JVM_TYPE type, int count) {
        this.ips = ips;
        this.type = type;
        this.count = count;
        this.id = type.name()+count;
        this.dir = Installer.REMOTE_ROOT+"/"+id;
    }

    public void initilize() throws IOException, InterruptedException {
        System.out.println("starting "+this);

        Bash.ssh(RemoteBoxes.getUser(), ips.pub, "mkdir -p " + dir + ";  cd " + dir + ";  touch in.txt");

        String classToRun;
        if (isMember()){
            classToRun = Member.class.getName();
            Bash.scpUp(RemoteBoxes.getUser(), ips.pub, "hazelcast.xml", dir+"/");
        }else{
            classToRun = Client.class.getName();
            Bash.scpUp(RemoteBoxes.getUser(), ips.pub, "client-hazelcast.xml", dir+"/");
        }

        String jvmArgs = new String();
        jvmArgs += "-D"+Args.homeUser+"="+System.getProperty("user.name")+" ";
        jvmArgs += "-D"+Args.homeIp+"="+InetAddress.getLocalHost().getHostAddress()+" ";
        jvmArgs += "-D"+Args.homeCwd+"="+System.getProperty("user.dir")+" ";
        jvmArgs += "-D"+Args.homeInfile+"="+Controler.commsFile+" ";
        jvmArgs += "-D"+Args.ID +"="+id+" ";

        pid = Bash.ssh(RemoteBoxes.getUser(), ips.pub, "cd " + dir + "; nohup java " + classPath + " " + jvmArgs + " " + classToRun + " < " + inFile + " &> " + outFile + " & echo $!");
        pid = pid.trim();
        System.out.println("started "+this);
    }

    public void clean() throws IOException, InterruptedException {
        Bash.ssh(RemoteBoxes.getUser(), ips.pub, "rm " + dir+"/*");
    }

    public void killAllJava() throws IOException, InterruptedException {
        Bash.ssh(RemoteBoxes.getUser(), ips.pub, "killall -9 java");
    }

    public void send(String cmd) throws IOException, InterruptedException{
        Bash.ssh(RemoteBoxes.getUser(), ips.pub, "echo "+cmd+" >> "+dir+"/"+inFile);
    }

    public void cat() throws IOException, InterruptedException {
        Bash.ssh(RemoteBoxes.getUser(), ips.pub, "cat "+dir+"/"+outFile);
    }

    public void tail() throws IOException, InterruptedException {
        Bash.ssh(RemoteBoxes.getUser(), ips.pub, "tail "+dir+"/"+outFile);
    }

    public void grep(String args) throws IOException, InterruptedException {
        Bash.ssh(RemoteBoxes.getUser(), ips.pub, "grep "+args+" "+dir+"/"+outFile);
    }


    public String toString() {
        return "RemoteJvm{" +
                "ips=" + ips +
                ", type=" + type +
                ", ID=" + id +
                ", dir=" + dir +
                ", pid=" + pid +
                '}';
    }

    public boolean isMember(){
        return type == JVM_TYPE.Member;
    }

    public boolean isClient(){
        return type == JVM_TYPE.Client;
    }
}
