package local;

import global.Bash;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class RemoteBoxes {

    private static String user;
    private BufferedReader agents = new BufferedReader(new InputStreamReader(new FileInputStream("agents.txt")));
    private List<IpPair> boxes;
    private RemoteJvmManager jvmManager = new RemoteJvmManager();

    public RemoteBoxes() throws IOException {
        setBoxes();
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

    public void setBoxes() throws IOException {
        List<IpPair> ips = new ArrayList();
        String input;
        while( (input=agents.readLine()) !=null ){
            String[] split = input.split(",");
            IpPair ip = new IpPair(split[0], split[1]);
            ips.add(ip);
        }
        boxes = ips;
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

    private void setJvmToBoxes() throws IOException {
        jvmManager.pinJvmsToBoxes(boxes);
    }

    public void setMembersOnlyCount(int membersOnlyCount) throws IOException {
        jvmManager.setMembersOnlyCount(membersOnlyCount);
        setJvmToBoxes();
    }

    public void setMembersCount(int membersCount) throws IOException {
        jvmManager.setMembersCount(membersCount);
        setJvmToBoxes();
    }

    public void setClientsCount(int clientsCount) throws IOException {
        jvmManager.setClientsCount(clientsCount);
        setJvmToBoxes();
    }

    public void catMemberLogs( ) throws IOException, InterruptedException {
        jvmManager.catMemberLogs();
    }



    public static void setUser(String userName) {user = userName;}

    public static String getUser(){return user;}

    @Override
    public String toString() {

        String str = "\n";
        for(IpPair ipPair : boxes){
            str+=ipPair+"\n";
        }

        return "RemoteBoxes{" +
                "user='" + user + '\'' +
                ", boxes=" + str +
                "jvmManager=" + jvmManager;
    }
}