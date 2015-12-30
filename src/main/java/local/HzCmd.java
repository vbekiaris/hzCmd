package local;

import cmdline.CmdLine;

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


    public void addBoxes(String user, String file){
        try {
            boxes.addBoxes(user, file);
        }catch (Exception e){

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
*/


/*
    private Collection<ClusterManager> getClusterManagers(HzCmdParser.StatementContext cmd) {
        Collection<ClusterManager> c = new ArrayList();
        if( cmd.ALL(0) != null) {
            c = clusters.values();
        }else {
            String clusterId = cmd.VAR(0).getText();
            c.add( clusters.get(clusterId) );
        }
        return c;
    }
*/


    /*
    *
    *
    private void setHomeIp(HzCmdParser.StatementContext cmd){
        homeIp = cmd.STRING(0).getText().replace("\"", "");
    }


    private void boxesCmd(HzCmdParser.StatementContext cmd) throws IOException {
        String user = cmd.STRING(0).getText().replace("\"","");
        String file = cmd.STRING(1).getText().replace("\"","");
        boxes.addBoxes(user, file);
    }


    private void cluster(HzCmdParser.StatementContext cmd) throws Exception{
        String clusterID = cmd.VAR(0).getText();
        int start = Integer.parseInt( cmd.NUMBER(0).getText() );
        int end = Integer.parseInt( cmd.NUMBER(1).getText() );

        BoxManager clusterBoxes = boxes.getBoxes(start, end);
        ClusterManager jvmManager = new ClusterManager(clusterID, clusterBoxes);
        clusters.put(jvmManager.getClusterId(), jvmManager);

        System.out.println(jvmManager);
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

    private void dedicatedMembers(HzCmdParser.StatementContext cmd) {

        Collection<ClusterManager> selected = getClusterManagers(cmd);

        int memberBox = Integer.parseInt(cmd.NUMBER(0).getText());

        for (ClusterManager c : selected) {
            c.setMembersOnlyCount(memberBox);
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

    private void info(CommonTokenStream tokens ) throws Exception {

        Collection<ClusterManager> clusterSet = selectClusterSet(tokens.get(1));

        for (ClusterManager c : clusterSet) {
            c = selectSubCluster(c, tokens.get(2));
            c.info();
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


    private void sleep(HzCmdParser.StatementContext cmd){
        int seconds = Integer.parseInt(cmd.NUMBER(0).getText());
        sleepSeconds(seconds);
    }



    private void showSSH(HzCmdParser.StatementContext cmd){
        boolean show = Boolean.parseBoolean( cmd.BOOL().getText() );
        Bash.showSSH = show;
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


    public static HzCmd loadHzCmd(){
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

    public static void saveHzCmd(HzCmd hzCmd){
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

        com.github.rvesse.airline.Cli<CmdLine.Command> parser = CmdLine.getParser();

        parser.parse(args).exe(hzCmd);

        saveHzCmd(hzCmd);
    }

}