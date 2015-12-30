package local;

import cmdline.CmdLine;
import cmdline.Command;
import global.Bash;

import java.io.*;
import java.util.*;


//TODO WAN REP xml SETUP
//TODO man center integ
//TODO tail -f grepping logs and pop up display
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

    public void addBoxes(String user, String file) throws IOException {
        boxes.addBoxes(user, file);
    }

    public void cluster(String id, int start, int end) throws Exception{

        BoxManager clusterBoxes = boxes.getBoxes(start, end);
        ClusterManager jvmManager = new ClusterManager(id, clusterBoxes, homeIp);
        clusters.put(jvmManager.getClusterId(), jvmManager);

        System.out.println(jvmManager);
    }

    public void dedicatedMembers(String clusterId, int memberBox) {

        Collection<ClusterManager> selected = selectClusterSet(clusterId);
        for (ClusterManager c : selected) {
            c.setMembersOnlyCount(memberBox);
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



    /*
    *
    *

    private ClusterManager selectSubCluster(ClusterManager c, String criterionToken) throws Exception {

        switch (criterionToken.getType()) {

            case HzCmdParser.MEMBER_ALL:
                return c.getMemberManager();

            case HzCmdParser.CLIENT_ALL:
                return c.getClientManager();

            case HzCmdParser.CLIENT_VAR:
            case HzCmdParser.MEMBER_VAR:
                String id = criterionToken.getText() + c.getClusterId();
                return c.getIDManager(id);

            default:
                return c;
        }
    }


    private void install(HzCmdParser.StatementContext cmd) throws IOException, InterruptedException {

        Collection<ClusterManager> selected = getClusterManagers(cmd);

        boolean ee = false;
        if( cmd.EE() != null) {
            ee=true;
        }

        List<TerminalNode> ids = cmd.VAR();
        String[] versions = new String[ids.size()-1];

        for (int i = 1; i < ids.size(); i++) {
            String key = ids.get(i).getText();
            versions[i-1]=(vars.get(key));
        }

        for (ClusterManager c : selected) {
            Installer.install(c.getBoxManager(), ee, versions);
        }
    }

    private void uninstall(HzCmdParser.StatementContext cmd) throws IOException, InterruptedException {

        Collection<ClusterManager> selected = getClusterManagers(cmd);

        for (ClusterManager c : selected) {
            Installer.uninstall(c.getBoxManager());
        }
    }



    private void add(HzCmdParser.StatementContext cmd) throws IOException, InterruptedException {

        Collection<ClusterManager> c = getClusterManagers(cmd);

        int threadQty = Integer.parseInt(cmd.NUMBER(0).getText());

        String version = cmd.VAR(1).getText();
        version = vars.get(version);

        String options = cmd.VAR(2).getText();
        options = vars.get(options);

        for (ClusterManager jvmManager : c) {
            if( cmd.MEMBER() != null) {
                jvmManager.addMembers(threadQty, version, options);
            }else {
                jvmManager.addClients(threadQty, version, options);
            }
        }

    }

    private void load(HzCmdParser.StatementContext cmd) throws IOException, InterruptedException {

        Collection<ClusterManager> clusterManagers = getClusterManagers(cmd);

        String taskId = cmd.VAR(0).getText();
        String className = cmd.STRING(0).getText().replace("\"", "");

        for (ClusterManager c : clusterManagers) {
            c.load(taskId, className);
        }

    }

    private void invoke(CommonTokenStream tokens ) throws Exception {

        String threadCound = tokens.get(1).getText();
        String method = tokens.get(2).getText();
        String taksId = tokens.get(3).getText();

        Collection<ClusterManager> clusterSet = selectClusterSet(tokens.get(4));

        for (ClusterManager c : clusterSet) {
            c = selectSubCluster(c, tokens.get(5));
            c.invoke(threadCound, method, taksId);
        }
    }

    private void stop(CommonTokenStream tokens ) throws Exception {

        String taskId = tokens.get(1).getText();


        Collection<ClusterManager> clusterSet = selectClusterSet(tokens.get(2));

        for (ClusterManager c : clusterSet) {
            c = selectSubCluster(c, tokens.get(3));
            c.stop(taskId);
        }
    }


    private void clean(CommonTokenStream tokens) throws Exception {
        Collection<ClusterManager> clusterSet = selectClusterSet(tokens.get(1));

        for (ClusterManager c : clusterSet) {
            c = selectSubCluster(c, tokens.get(2));
            c.clean();
        }
    }

    private void kill(CommonTokenStream tokens) throws Exception {
        Collection<ClusterManager> clusterSet = selectClusterSet(tokens.get(1));

        for (ClusterManager c : clusterSet) {
            c = selectSubCluster(c, tokens.get(2));
            c.kill();
        }
    }

    private void restart(CommonTokenStream tokens) throws Exception {
        Collection<ClusterManager> clusterSet = selectClusterSet(tokens.get(1));

        String version = tokens.get(3).getText();
        version = vars.get(version);

        String options = "";
        for(int i=4; i<tokens.size(); i++){
            String key = tokens.get(i).getText();
            options += vars.get(key);
        }

        for (ClusterManager c : clusterSet) {
            c = selectSubCluster(c, tokens.get(2));
            c.restart(version, options);
        }
    }

    private void cat(CommonTokenStream tokens) throws Exception {
        Collection<ClusterManager> clusterSet = selectClusterSet(tokens.get(1));

        for (ClusterManager c : clusterSet) {
            c = selectSubCluster(c, tokens.get(2));
            c.cat();
        }
    }


    private void grep(CommonTokenStream tokens) throws Exception {
        Collection<ClusterManager> clusterSet = selectClusterSet(tokens.get(1));

        String grepArgs = tokens.get(3).getText();

        for (ClusterManager c : clusterSet) {
            c = selectSubCluster(c, tokens.get(2));
            c.grep(grepArgs);
        }
    }

    private void downlonad(CommonTokenStream tokens) throws Exception {
        Collection<ClusterManager> clusterSet = selectClusterSet(tokens.get(1));

        String dir = tokens.get(3).getText();

        for (ClusterManager c : clusterSet) {
            c = selectSubCluster(c, tokens.get(2));
            c.downlonad(dir);
        }
    }



    *
    * */



    @Override
    public String toString() {
        return "HzCmd{" +
                "boxes=" + boxes +
                "homeIp='" + homeIp + '\'' +
                ", commsFile='" + commsFile + '\'' +
                ", clusters=" + clusters +
                '}';
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
            c.exe(hzCmd);
        }else{
            r.run();
        }

        saveHzCmd(hzCmd);
    }
}