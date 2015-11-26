package local;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static global.Utils.sleepSeconds;

public class RemoteJvmManager {

    private int membersOnlyCount=0;
    private int membersCount=0;
    private int clientsCount=0;
    private List<RemoteJvm> jvms = new ArrayList();;

    public void pinJvmsToBoxes(List<IpPair> boxes) throws IOException {
        jvms.clear();

        if(membersOnlyCount <= 0 || membersOnlyCount > boxes.size()){
            membersOnlyCount=boxes.size();
        }
        for(int i=0; i<membersCount; i++){
            int memberIdx = i % membersOnlyCount;
            RemoteJvm hz = new RemoteJvm(boxes.get(memberIdx), RemoteJvm.JVM_TYPE.Member, i+1);;
            jvms.add(hz);
        }

        if(membersOnlyCount == boxes.size()){
            membersOnlyCount=0;
        }
        for(int i=0; i<clientsCount; i++) {
            int clientIdx = rangeMap(i, membersOnlyCount, boxes.size());
            RemoteJvm hz = new RemoteJvm(boxes.get(clientIdx), RemoteJvm.JVM_TYPE.Client, i+1);
            jvms.add(hz);
        }
    }

    private int rangeMap(int val, int min, int max) {
        int p = max-min;
        int mod = (val-min)%p;
        if(mod<0)
            mod += p;
        return min+mod;
    }

    public void initilizeJvms() throws IOException, InterruptedException {
        for(RemoteJvm jvm : jvms){
            if(jvm.isMember())
                jvm.initilize();
        }
        sleepSeconds(10);
        for(RemoteJvm jvm : jvms){
            if(jvm.isClient())
                jvm.initilize();
        }
    }

    public void clean() throws IOException, InterruptedException {
        for(RemoteJvm jvm : jvms){
            jvm.clean();
        }
    }

    public void killAllJava() throws IOException, InterruptedException {
        for(RemoteJvm jvm : jvms){
            jvm.killAllJava();
        }
    }

    public void send(String cmd) throws IOException, InterruptedException {
        for(RemoteJvm jvm : jvms){
            jvm.send(cmd);
        }
    }

    public void catMemberLogs() throws IOException, InterruptedException {
        for(RemoteJvm jvm : jvms){
            if(jvm.isMember()){
                System.out.println(jvm);
                jvm.catLogs();
            }
        }
    }

    public void setMembersOnlyCount(int membersOnlyCount) {
        this.membersOnlyCount = membersOnlyCount;
    }

    public void setMembersCount(int membersCount) {
        this.membersCount = membersCount;
    }

    public void setClientsCount(int clientsCount) {
        this.clientsCount = clientsCount;
    }

    @Override
    public String toString() {

        String str = new String();
        for(RemoteJvm jvm : jvms){
            str+=jvm+"\n";
        }

        return "local.RemoteJvmManager{" +
                "membersOnlyCount=" + membersOnlyCount +
                ", membersCount=" + membersCount +
                ", clientsCount=" + clientsCount +
                "}\n" + str;
    }
}