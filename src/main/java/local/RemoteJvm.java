package local;

import global.Bash;
import remote.Client;
import remote.Member;

import java.io.IOException;
import java.net.InetAddress;

public class RemoteJvm {

    public static final String classPath="-cp \"$HOME"+Installer.REMOTE_ROOT+"/lib/*\"";

    public static final String inFile  =  "in.txt";
    public static final String outFile =  "out.txt";

    private String dir;

    public static enum JVM_TYPE {
        Client, Member
    }

    IpPair ips;
    JVM_TYPE type;
    int id;

    public RemoteJvm(IpPair ips, JVM_TYPE type, int id){
        this.ips = ips;
        this.type = type;
        this.id = id;
        this.dir = getDirName();
    }

    private String getDirName(){ return Installer.REMOTE_ROOT+"/"+type.name()+id; }

    public void start() throws IOException, InterruptedException {

        System.out.println("start "+this);

        Bash.ssh(RemoteBoxes.getUser(), ips.pub, "mkdir -p " + dir + ";  cd " + dir + ";  touch in.txt");

        String type;
        if (isMember()){
            type = Member.class.getName();
            Bash.scpUp(RemoteBoxes.getUser(), ips.pub, "hazelcast.xml", dir+"/");

        }else{
            type = Client.class.getName();
            Bash.scpUp(RemoteBoxes.getUser(), ips.pub, "client-hazelcast.xml", dir+"/");
        }
        send("homeUser "+System.getProperty("user.name"));
        send("homeIp " + InetAddress.getLocalHost().getHostAddress());
        send("homeCwd " + System.getProperty("user.dir"));
        send("homeInfile " + Controler.msgFile);

        Bash.ssh(RemoteBoxes.getUser(), ips.pub, "cd "+dir+"; nohup java "+classPath+" "+type+" < "+inFile+" &> "+outFile+" &");
    }

    public void clean() throws IOException, InterruptedException {
        Bash.ssh(RemoteBoxes.getUser(), ips.pub, "rm -r " + dir);
    }

    public void send(String cmd) throws IOException, InterruptedException{
        Bash.ssh(RemoteBoxes.getUser(), ips.pub, "echo "+cmd+" >> "+dir+"/"+inFile);
    }

    public String toString() {
        return "RemoteHzJvm{" +
                "ips=" + ips +
                ", type=" + type +
                ", id=" + id +
                '}';
    }

    public boolean isMember(){
        return type == JVM_TYPE.Member;
    }

    public boolean isClient(){
        return type == JVM_TYPE.Client;
    }
}
