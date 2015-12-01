package local;

import global.Bash;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class RemoteBoxManager {

    private BufferedReader agents;
    private String user;
    private List<IpPair> boxes = new ArrayList();

    public RemoteBoxManager(String file) throws IOException, InterruptedException {

    }

    public void addIps(String file) throws IOException {
        agents = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

        String line;
        while( (line=agents.readLine()) !=null ){
            addIp(line);
        }
    }

    public void addIp(String ipString) {
        String[] split = ipString.split(",");
        IpPair ip = new IpPair(split[0], split[1]);
        boxes.add(ip);
    }

    public void setUser(String user){
        this.user=user;
    }

    public String getUser(){
        return user;
    }

    public List<IpPair> getBoxes(int start, int end){
        return boxes.subList(start-1,  end);
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

    public void jps() throws IOException, InterruptedException {
        sshCmd("jps");
    }

    public int count(){return boxes.size();}




    @Override
    public String toString() {

        String ips="";
        for(IpPair ipPair : boxes){
            ips+=ipPair+", ";
        }

        return "RemoteBoxManager{" +
                "user='" + user + '\'' +
                ", boxes="+boxes.size()+" ["+ ips +"] ";
    }
}