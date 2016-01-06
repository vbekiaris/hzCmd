package local;

import cmdline.CmdLine;
import cmdline.Command;
import global.Bash;
import global.HzType;

import java.io.*;
import java.util.*;


//TODO WAN REP xml SETUP
//TODO man center integ
//TODO exampe 3 3node cluster wan replicate ring, with rolling upgrade,  members putting,  clients getting,  kill restart members,  man center running,
public class HzCmd implements Serializable {

    public static final String commsFile = "commsIn.txt";

    public String homeIp;

    private BoxManager boxes = new BoxManager();
    private Map<String, ClusterManager> clusters = new HashMap();

    public void showSSH(boolean show){
        Bash.showSSH = show;
    }

    public void setHomeIp(String homeIp){
        this.homeIp = homeIp;
    }

    public void addBoxes(String user, String file) throws IOException, InterruptedException{
        boxes.addBoxes(user, file);
    }

    public void cluster(String id, int start, int end) throws Exception{
        BoxManager clusterBoxes = boxes.getBoxes(start, end);
        ClusterManager jvmManager = new ClusterManager(id, clusterBoxes, homeIp);
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

    public void addJvm(HzType type, int qty, String clusterId, String version,  String options) throws IOException, InterruptedException {
        Collection<ClusterManager> selected = selectClusterSet(clusterId);
        for (ClusterManager c : selected) {
            if( type == HzType.Member ) {
                c.addMembers(qty, version, options);
            }else {
                c.addClients(qty, version, options);
            }
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
        boxes.rm(Installer.REMOTE_HZCMD_ROOT);
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
        for (ClusterManager c : clusters.values()) {
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
        ReadComms readComms = new ReadComms(HzCmd.commsFile);
        readComms.read();

        HzCmd hzCmd = loadHzCmd();

        com.github.rvesse.airline.Cli<Runnable> parser = CmdLine.getParser();
        Runnable r = parser.parse(args);
        if (r instanceof Command){
            Command c = (Command)r;

            try {
                c.exe(hzCmd);
                saveHzCmd(hzCmd);
            }catch (Error e){
                e.printStackTrace();
            }
        }else{
            r.run();
        }
    }
}