package local;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static global.Utils.rangeMap;
import static global.Utils.sleepSeconds;

public class RemoteJvmManager {

    private final String user;

    private final String clusterId;

    private Map<String, RemoteJvm> jvms = new HashMap();
    private List<IpPair> boxes;

    private int membersOnlyCount;
    private int memberCount=0;
    private int clientCount=0;


    public RemoteJvmManager(String user, String clusterId, List<IpPair> boxes){
        this.user=user;
        this.clusterId =clusterId;
        this.boxes=boxes;
        membersOnlyCount=boxes.size();
    }

    public void setMembersOnlyCount(int count) {
        if(count <= 0 || count > boxes.size()){
            membersOnlyCount = boxes.size();
        }else{
            membersOnlyCount = count;
        }
    }

    public void addMembers(int qty) throws IOException, InterruptedException {
        for(int i=0; i<qty; i++)
            addMember();
    }

    public void addMember() throws IOException, InterruptedException {
        int memberIdx = rangeMap(memberCount++, 0, membersOnlyCount);
        String id = RemoteJvm.JVM_TYPE.Member.name() + memberCount + clusterId;
        RemoteJvm jvm = new RemoteJvm(user, boxes.get(memberIdx), RemoteJvm.JVM_TYPE.Member, id);
        jvms.put(jvm.getId(), jvm);
        jvm.initilize();
    }

    public void addClients(int qty) throws IOException, InterruptedException {
        for(int i=0; i<qty; i++)
            addClient();
    }

    public void addClient() throws IOException, InterruptedException {
        int clientIdx = rangeMap(clientCount++, membersOnlyCount, boxes.size());
        String id =  RemoteJvm.JVM_TYPE.Client.name() + memberCount + clusterId;
        RemoteJvm jvm = new RemoteJvm(user, boxes.get(clientIdx), RemoteJvm.JVM_TYPE.Client, id);
        jvms.put(jvm.getId(), jvm);
        jvm.initilize();
    }

    public void initilizeJvms() throws IOException, InterruptedException {
        for(RemoteJvm jvm : jvms.values()){
            if(jvm.isMember())
                jvm.initilize();
        }

        sleepSeconds(10);
        for(RemoteJvm jvm : jvms.values()){
            if(jvm.isClient())
                jvm.initilize();
        }
    }

    private void zeroOut(){
        membersOnlyCount=boxes.size();
        memberCount=0;
        clientCount=0;
        jvms.clear();
    }

    public void clean() throws IOException, InterruptedException {
        for(RemoteJvm jvm : jvms.values()){
            jvm.clean();
        }
    }

    public void kill(String id) throws IOException, InterruptedException {
        RemoteJvm jvm = jvms.get(id);
        jvm.kill();
    }

    public void start(String id) throws IOException, InterruptedException {
        RemoteJvm jvm = jvms.get(id);
        jvm.initilize();
    }

    public void send(String cmd) throws IOException, InterruptedException {
        for(RemoteJvm jvm : jvms.values()){
            jvm.send(cmd);
        }
    }

    public void catMemberLogs() throws IOException, InterruptedException {
        for(RemoteJvm jvm : jvms.values()){
            if(jvm.isMember()){
                System.out.println(jvm);
                jvm.cat();
            }
        }
    }

    public void grepMembers(String args) throws IOException, InterruptedException {
        for(RemoteJvm jvm : jvms.values()){
            if(jvm.isMember()){
                System.out.println(jvm);
                jvm.grep(args);
            }
        }
    }

    public String getClusterId() {
        return clusterId;
    }


    @Override
    public String toString() {

        String ips="";
        for(IpPair ipPair : boxes){
            ips+=ipPair+", ";
        }


        String jvms = new String();
        for(RemoteJvm jvm : this.jvms.values()){
            jvms+=jvm+"\n";
        }
        jvms=jvms.trim();

        return "RemoteJvmManager{" +
                " clusterId=" + clusterId +
                ", membersOnlyCount=" + membersOnlyCount +
                ", memberCount=" + memberCount +
                ", clientCount=" + clientCount +
                ", " + ips +
                ", jvms=" + jvms +
                "}";
    }
}