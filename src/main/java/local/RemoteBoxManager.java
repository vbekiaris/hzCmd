package local;

import global.Bash;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class RemoteBoxManager {

    private static String user;
    private List<IpPair> boxes;
    private RemoteJvmManager jvmManager;

    public RemoteBoxManager(List<IpPair> boxes) throws IOException {
        this.boxes=boxes;
        jvmManager = new RemoteJvmManager(boxes);
    }

    public void upload(String souce, String dest) throws IOException, InterruptedException {
        for(IpPair ip : boxes){
            Bash.scpUp(user, ip.pub, souce, dest);
        }
    }

    public void sshCmd(String cmd) throws IOException, InterruptedException {
        for(IpPair ip : boxes){
            Bash.ssh(user, ip.pub, cmd);
        }
    }


    public void initilizeJvms() throws IOException, InterruptedException {
        jvmManager.initilizeJvms();
    }

    public void clean( ) throws IOException, InterruptedException {
        jvmManager.clean();
        jvmManager.killAllJava();
    }

    public void send(String cmd) throws IOException, InterruptedException {
        jvmManager.send(cmd);
    }

    public void setMembersOnlyCount(int membersOnlyCount) throws IOException {
        jvmManager.setMembersOnlyCount(membersOnlyCount);
    }

    public void setMembersCount(int qty) throws IOException {
        jvmManager.addMembers(qty);
    }

    public void setClientsCount(int qty) throws IOException {
       jvmManager.addClients(qty);
    }

    public void catMemberLogs( ) throws IOException, InterruptedException {
        jvmManager.catMemberLogs();
    }

    public void grepMembers(String args) throws IOException, InterruptedException {
        jvmManager.grepMembers(args);
    }


    public static void setUser(String userName) {user = userName;}

    public static String getUser(){return user;}

    public int count(){return boxes.size();}

    public void jvmLayout(){
        System.out.println(jvmManager);
    }

    @Override
    public String toString() {

        String ips="";
        for(IpPair ipPair : boxes){
            ips+=ipPair+", ";
        }

        return "RemoteBoxManager{" +
                "user='" + user + '\'' +
                ", boxes="+boxes.size()+" ["+ ips +"] " +
                "jvmManager=" + jvmManager;
    }
}