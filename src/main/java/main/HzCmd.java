package main;

import cmdline.AddClient;
import cmdline.AddMember;
import cmdline.CmdLine;
import cmdline.Command;
import global.Bash;
import hz.HzJvmFactory;
import jms.MQ;
import local.BoxManager;
import local.ClusterManager;
import local.Installer;

import javax.jms.JMSException;
import java.io.*;
import java.util.*;

// mvn clean install dependency:copy-dependencies
//TODO WAN REP xml SETUP
//TODO man center integ
//TODO exampe 3 3node cluster wan replicate ring, with rolling upgrade,  members putting,  clients getting,  kill restart members,  man center isRunning,
public class HzCmd implements Serializable {

    public static final String commsFile = "commsIn.txt";

    public String homeIp;

    private Map<String, BoxManager> boxes = new HashMap();
    private Map<String, ClusterManager> clusters = new HashMap();

    public void showSSH(boolean show){
        Bash.showSSH = show;
    }

    public void setHomeIp(String homeIp){
        this.homeIp = homeIp;
    }

    public void addBoxes(String boxGroupId, String user, String file) throws IOException, InterruptedException{
        BoxManager b = new BoxManager(boxGroupId);
        b.addBoxes(user, file);
        boxes.put(boxGroupId, b);
    }

    public void cluster(String clusterId, String boxGroupId, int start, int end) throws Exception{
        BoxManager all = boxes.get(boxGroupId);
        BoxManager clusterBoxes = all.getBoxes(start, end);
        ClusterManager jvmManager = new ClusterManager(clusterId, clusterBoxes, homeIp, new HzJvmFactory());
        clusters.put(jvmManager.getClusterId(), jvmManager);
        System.out.println(jvmManager);
    }

    public void install(String clusterId, boolean ee, String... versions) throws IOException, InterruptedException {
        Collection<ClusterManager> selected = selectClusterSet(clusterId);
        for (ClusterManager c : selected) {
            Installer.install(c.getBoxManager(), ee, versions);
        }
    }

    public void dedicatedMembers(String clusterId, int memberBox) {
        Collection<ClusterManager> selected = selectClusterSet(clusterId);
        for (ClusterManager c : selected) {
            c.setMembersOnlyCount(memberBox);
        }
    }

    public void addMembers(AddMember cmd) throws Exception {
        Collection<ClusterManager> selected = selectClusterSet(cmd.cluster);
        for (ClusterManager c : selected) {
            c.addMembers(cmd.qty, cmd.version, cmd.jvmOptions);
        }
    }

    public void addClients(AddClient cmd) throws Exception {
        Collection<ClusterManager> selected = selectClusterSet(cmd.cluster);
        for (ClusterManager c : selected) {
            c.addClients(cmd.qty, cmd.version, cmd.jvmOptions);
        }
    }

    public void exit(String clusterId, String jvmId) throws Exception {
        Collection<ClusterManager> selected = selectClusterSet(clusterId);
        for (ClusterManager c : selected) {
            c = c.selectJvmSet(jvmId);
            c.exit();
        }
    }

    public void kill(String clusterId, String jvmId) throws Exception {
        Collection<ClusterManager> selected = selectClusterSet(clusterId);
        for (ClusterManager c : selected) {
            c = c.selectJvmSet(jvmId);
            c.kill();
        }
    }

    public void restart(String clusterId, String jvmId, String version,  String options) throws Exception {
        Collection<ClusterManager> selected = selectClusterSet(clusterId);
        for (ClusterManager c : selected) {
            c = c.selectJvmSet(jvmId);
            c.restart(version, options);
        }
    }


    public void cat(String clusterId, String jvmId) throws Exception {
        Collection<ClusterManager> selected = selectClusterSet(clusterId);
        for (ClusterManager c : selected) {
            c = c.selectJvmSet(jvmId);
            c.cat();
        }
    }

    public void tail(String clusterId, String jvmId) throws Exception {
        Collection<ClusterManager> selected = selectClusterSet(clusterId);
        for (ClusterManager c : selected) {
            c = c.selectJvmSet(jvmId);
            c.tail();
        }
    }

    public void grep(String clusterId, String jvmId, String grepArgs) throws Exception {
        Collection<ClusterManager> selected = selectClusterSet(clusterId);
        for (ClusterManager c : selected) {
            c = c.selectJvmSet(jvmId);
            c.grep(grepArgs);
        }
    }

    public void downlonad(String clusterId, String jvmId, String dir) throws Exception {
        Collection<ClusterManager> selected = selectClusterSet(clusterId);
        for (ClusterManager c : selected) {
            c = c.selectJvmSet(jvmId);
            c.downlonad(dir);
        }
    }

    public void clean(String clusterId, String jvmId) throws Exception {
        Collection<ClusterManager> selected = selectClusterSet(clusterId);
        for (ClusterManager c : selected) {
            c = c.selectJvmSet(jvmId);
            c.clean();
        }
    }

    public void wipe( ) throws IOException, InterruptedException {
        for (ClusterManager c : clusters.values()) {
            c.kill();
            c.clearStoped();
        }
        for (BoxManager boxManager : boxes.values()) {
            boxManager.rm(Installer.REMOTE_HZCMD_ROOT);
        }
    }

    public void load(String clusterId,  String taskId, String className) throws IOException, InterruptedException {
        Collection<ClusterManager> selected = selectClusterSet(clusterId);
        for (ClusterManager c : selected) {
            c.load(taskId, className);
        }
    }

    public void invoke(String clusterId, String jvmId, int threadCound, String method, String taksId) throws Exception {
        Collection<ClusterManager> selected = selectClusterSet(clusterId);
        for (ClusterManager c : selected) {
            c = c.selectJvmSet(jvmId);
            c.invoke(threadCound, method, taksId);
        }

        for (ClusterManager c : selected) {
            c = c.selectJvmSet(jvmId);
            c.getResponse();
        }
        System.out.println("ALL acked");
        MQ.shutdown();
    }

    public void stop(String clusterId, String jvmId, String taskId) throws Exception {
        Collection<ClusterManager> selected = selectClusterSet(clusterId);
        for (ClusterManager c : selected) {
            c = c.selectJvmSet(jvmId);
            c.stop(taskId);
        }
    }

    private Collection<ClusterManager> selectClusterSet(String cluster) {
        Collection<ClusterManager> selected = new ArrayList();
        if( cluster.equals("*") ){
            selected = clusters.values();
        }else{
            selected.add(clusters.get(cluster));
        }
        return  selected;
    }


    @Override
    public String toString() {
        String clu="";
        for(ClusterManager c : clusters.values()) {
            clu += c.toString() + "\n";
        }

        return "HzCmd" +
                boxes +
                "homeIp='" + homeIp + '\'' +
                ", commsFile='" + commsFile + '\'' +
                "\n" + clu ;
    }


    private static HzCmd loadHzCmd(){
        HzCmd hzCmd = null;
        try {
            FileInputStream fileIn = new FileInputStream("HzCmd.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            hzCmd = (HzCmd) in.readObject();
            in.close();
            fileIn.close();
        }catch(Exception e) {
            hzCmd = new HzCmd();
        }
        return hzCmd;
    }

    private static void saveHzCmd(HzCmd hzCmd){
        try {
            FileOutputStream fileOut = new FileOutputStream("HzCmd.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(hzCmd);
            out.close();
            fileOut.close();
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException, IOException {

        HzCmd hzCmd = loadHzCmd();

        com.github.rvesse.airline.Cli<Runnable> parser = CmdLine.getParser();
        Runnable r = parser.parse(args);
        if (r instanceof Command){
            Command c = (Command)r;

            try {
                c.exe(hzCmd);
                try {
                    MQ.shutdown();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
                saveHzCmd(hzCmd);
            }catch (Error e){
                e.printStackTrace();
            }
        }else{
            r.run();
        }
    }
}