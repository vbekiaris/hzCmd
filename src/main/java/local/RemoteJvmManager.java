package local;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static global.Utils.rangeMap;
import static global.Utils.sleepSeconds;

public class RemoteJvmManager {

    private int membersOnlyCount;
    private Map<String, RemoteJvm> jvms = new HashMap();;

    private int memberCount=0;
    private int clientCount=0;

    private List<IpPair> boxes;

    public RemoteJvmManager(List<IpPair> boxes){
        this.boxes=boxes;
        membersOnlyCount=boxes.size();
        System.out.println("membersOnlyCount="+membersOnlyCount);
    }

    public void setMembersOnlyCount(int count) {
        if(count <= 0 || count > boxes.size()){
            count=boxes.size();
        }
        this.membersOnlyCount = count;
    }

    public void addMembers(int qty) {
        for(int i=0; i<qty; i++)
            addMember();
    }

    public void addMember(){
        int memberIdx = rangeMap(memberCount++, 0, membersOnlyCount);
        RemoteJvm jvm = new RemoteJvm(boxes.get(memberIdx), RemoteJvm.JVM_TYPE.Member, memberCount);
        jvms.put(jvm.getId(), jvm);
    }

    public void addClients(int qty) {
        for(int i=0; i<qty; i++)
            addClient();
    }

    public void addClient(){
        int clientIdx = rangeMap(clientCount++, membersOnlyCount, boxes.size());
        RemoteJvm jvm = new RemoteJvm(boxes.get(clientIdx), RemoteJvm.JVM_TYPE.Client, clientCount);
        jvms.put(jvm.getId(), jvm);
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
        membersOnlyCount=0;
        memberCount=0;
        clientCount=0;
        jvms.clear();
    }

    public void clean() throws IOException, InterruptedException {
        for(RemoteJvm jvm : jvms.values()){
            jvm.clean();
        }
    }

    public void killAllJava() throws IOException, InterruptedException {
        for(RemoteJvm jvm : jvms.values()){
            jvm.killAllJava();
        }
        zeroOut();
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

    @Override
    public String toString() {

        String jvms = new String();
        for(RemoteJvm jvm : this.jvms.values()){
            jvms+=jvm+"\n";
        }
        jvms=jvms.trim();

        return "RemoteJvmManager{" +
                "membersOnlyCount=" + membersOnlyCount +
                ", memberCount=" + memberCount +
                ", clientCount=" + clientCount +"," + "jvms" +
                "\n"+jvms +
                "}";
    }
}