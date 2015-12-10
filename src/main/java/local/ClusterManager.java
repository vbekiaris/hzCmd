package local;

import global.Args;
import global.Bash;
import global.HzType;
import xml.HzMemberXmlHelper;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static global.Utils.rangeMap;
import static global.Utils.sleepSeconds;

public class ClusterManager {

    private final String clusterId;
    private BoxManager boxes;
    private Map<String, RemoteJvm> jvms = new HashMap();

    private int membersOnlyCount;
    private int memberCount=0;
    private int clientCount=0;

    public ClusterManager(String clusterId, BoxManager boxes) throws Exception {
        this.clusterId =clusterId;
        this.boxes=boxes;
        HzMemberXmlHelper.makeXml(this);
    }



    public BoxManager getBoxManager( ){
        return boxes;
    }

    private void sendAll(String cmd) throws IOException, InterruptedException {
        for(RemoteJvm jvm : jvms.values()){
            jvm.send(cmd);
        }
    }

    private void sendMembers(String cmd) throws IOException, InterruptedException {
        for(RemoteJvm jvm : jvms.values()){
            if(jvm.isMember()){
                jvm.send(cmd);
            }
        }
    }

    private void sendClients(String cmd) throws IOException, InterruptedException {
        for(RemoteJvm jvm : jvms.values()){
            if(jvm.isClient()){
                jvm.send(cmd);
            }
        }
    }


    public void setMembersOnlyCount(int count) {
        if(count <= 0 || count > boxes.size()){
            membersOnlyCount = boxes.size();
        }else{
            membersOnlyCount = count;
        }
    }

    public void addMembers(int qty, String hzVersion, String options) throws IOException, InterruptedException {
        List<RemoteJvm> check = new ArrayList();
        for(int i=0; i<qty; i++) {
            check.add(addMember(hzVersion, options));
        }
        sleepSeconds(2);
        for (RemoteJvm jvm : check) {
            System.out.println(jvm);
        }
    }

    public RemoteJvm addMember(String hzVersion, String options) throws IOException, InterruptedException {
        int memberIdx = rangeMap(memberCount++, 0, boxes.size()-membersOnlyCount);

        String id = HzType.Member.name() + memberCount + clusterId;

        RemoteJvm jvm = new RemoteJvm(boxes.get(memberIdx), HzType.Member, id);
        jvms.put(jvm.getId(), jvm);
        jvm.initilize(hzVersion, options);
        return jvm;
    }

    public void addClients(int qty, String hzVersion, String options) throws IOException, InterruptedException {
        List<RemoteJvm> check = new ArrayList();
        for(int i=0; i<qty; i++) {
            check.add(addClient(hzVersion, options));
        }
        sleepSeconds(2);
        for (RemoteJvm jvm : check) {
            System.out.println(jvm);
        }
    }

    public RemoteJvm addClient(String hzVersion, String options) throws IOException, InterruptedException {
        int clientIdx = rangeMap(clientCount++, membersOnlyCount, boxes.size());
        String id = HzType.Client.name() + clientCount + clusterId;
        RemoteJvm jvm = new RemoteJvm(boxes.get(clientIdx), HzType.Client, id);
        jvms.put(jvm.getId(), jvm);
        jvm.initilize(hzVersion, options);
        return jvm;
    }


    public void clean() throws IOException, InterruptedException {
        for(RemoteJvm jvm : jvms.values()){
            jvm.clean();
        }
    }


    public void loadAll(String taskId, String className) throws IOException, InterruptedException {
        sendAll(Args.load +" "+taskId+" "+className);
    }

    public void kill(String id) throws IOException, InterruptedException {
        RemoteJvm jvm = jvms.get(id);
        jvm.kill();
    }

    public void killAll() throws IOException, InterruptedException {
        killClients();
        killMembers();
    }

    public void killMembers() throws IOException, InterruptedException {
        for(RemoteJvm jvm : jvms.values()){
            if(jvm.isMember()){
               jvm.kill();
            }
        }
    }

    public void killClients() throws IOException, InterruptedException {
        for(RemoteJvm jvm : jvms.values()){
            if(jvm.isClient()){
                jvm.kill();
            }
        }
    }


    public void reStart(String id, String version, String options) throws IOException, InterruptedException {
        RemoteJvm jvm = jvms.get(id);
        jvm.initilize(version, options);
    }

    public void reStartAll(String version, String options) throws IOException, InterruptedException {
        reStartClients(version, options);
        reStartMembers(version, options);
    }

    public void reStartMembers(String version, String options) throws IOException, InterruptedException {
        for(RemoteJvm jvm : jvms.values()){
            if(jvm.isMember()){
                jvm.initilize(version, options);
            }
        }
    }

    public void reStartClients(String version, String options) throws IOException, InterruptedException {
        for(RemoteJvm jvm : jvms.values()){
            if(jvm.isClient()){
                jvm.initilize(version, options);
            }
        }
    }

    public void cat(String id) throws IOException, InterruptedException {
        RemoteJvm jvm = jvms.get(id);
        System.out.println( jvm.cat() );
    }

    public void catAll() throws IOException, InterruptedException {
        catMembers();
        catClients();
    }

    public void catMembers() throws IOException, InterruptedException {
        for(RemoteJvm jvm : jvms.values()){
            if(jvm.isMember()){
                System.out.println(jvm);
                System.out.println(jvm.cat());
            }
        }
    }

    public void catClients() throws IOException, InterruptedException {
        for(RemoteJvm jvm : jvms.values()){
            if(jvm.isClient()){
                System.out.println(jvm);
                System.out.println( jvm.cat() );
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

    public String memberJvms(){
        String jvms = new String();
        for(RemoteJvm jvm : this.jvms.values()){
            if(jvm.isMember()){
                jvms+=jvm+"\n";
            }
        }
        return jvms;
    }

    public String clientJvms(){
        String jvms = new String();
        for(RemoteJvm jvm : this.jvms.values()){
            if(jvm.isClient()){
                jvms+=jvm+"\n";
            }
        }
        return jvms;
    }

    @Override
    public String toString() {

        String jvms = memberJvms();
        jvms += clientJvms();

        return Bash.ANSI_YELLOW+"ClusterManager" +
                " clusterId=" + clusterId +
                ", membersOnlyCount=" + membersOnlyCount +
                ", memberCount=" + memberCount +
                ", clientCount=" + clientCount +
                ", boxCount==" + boxes.size() +
                ", \n" + boxes +
                "" + jvms +
                Bash.ANSI_RESET;
    }
}