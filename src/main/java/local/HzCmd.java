package local;

import antlr4.HzCmdLexer;
import antlr4.HzCmdParser;
import global.Bash;
import org.antlr.runtime.Token;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.io.*;
import java.util.*;



//TODO WAN REP xml SETUP
//TODO man center integ
//TODO tail -f grepping logs and pop up display
//TODO exampe 3 3node cluster wan replicate ring, with hotrestart,  members putting,  clients getting,  kill restart members,  man center running
public class HzCmd implements Serializable {

    public String homeIp;
    public static final String commsFile = "commsIn.txt";

    private BoxManager boxes = new BoxManager();
    private Map<String, ClusterManager> clusters = new HashMap();

    public void exeCmd(String line) throws IOException {
        if (line!=null && !line.equals("")) {
            try{
                HzCmdLexer lexer = new HzCmdLexer(new ANTLRInputStream(line));
                CommonTokenStream tokens = new CommonTokenStream(lexer);
                HzCmdParser parser = new HzCmdParser(tokens);
                HzCmdParser.StatementContext cmd = parser.statement();

                org.antlr.v4.runtime.Token token;
                for (token = lexer.nextToken(); token.getType() != Token.EOF;  token = lexer.nextToken() ) {
                    System.out.println(token.getText());
                }

                switch (cmd.start.getType()) {
                    case HzCmdParser.BOXES:
                        boxesCmd(cmd);
                        break;
                     case HzCmdParser.HOMEIP:
                        setHomeIp(cmd);
                        break;
                    case HzCmdParser.CLUSTER:
                        cluster(cmd);
                        break;
                    case HzCmdParser.INSTALL:
                        install(cmd);
                        break;
                    case HzCmdParser.UNINSTALL:
                        uninstall(cmd);
                        break;

                    case HzCmdParser.MEMBER_BOX:
                        dedicatedMembers(cmd);
                        break;
                    case HzCmdParser.ADD:
                        add(cmd);
                        break;
                    case HzCmdParser.LOAD:
                        load(cmd);
                        break;
                    case HzCmdParser.INVOKE:
                        invoke(tokens);
                        break;

                    case HzCmdParser.INFO:
                        info(tokens);
                        break;
                    case HzCmdParser.KILL:
                        kill(tokens);
                        break;
                    case HzCmdParser.RESTART:
                        restart(tokens);
                        break;
                    case HzCmdParser.CAT:
                        cat(tokens);
                        break;
                    case HzCmdParser.GREP:
                        grep(tokens);
                        break;
                    case HzCmdParser.DOWNLOAD:
                        downlonad(tokens);
                        break;
                    case HzCmdParser.SHOWSSH:
                        showSSH(cmd);
                        break;

                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }


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
        ClusterManager jvmManager = new ClusterManager(clusterID, clusterBoxes, homeIp);
        clusters.put(jvmManager.getClusterId(), jvmManager);

        System.out.println(jvmManager);
    }

    private void install(HzCmdParser.StatementContext cmd) throws IOException, InterruptedException {

        Collection<ClusterManager> selected = getClusterManagers(cmd);

        boolean ee = false;
        if( cmd.EE() != null) {
            ee=true;
        }

        String version = cmd.STRING(0).getText().replace("\"", "");

        for (ClusterManager c : selected) {
            Installer.install(c.getBoxManager(), ee, version);
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

        String version = cmd.STRING(0).getText().replace("\"", "");

        String options = cmd.STRING(1).getText().replace("\"", "");

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
        String options = "";

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

    private void showSSH(HzCmdParser.StatementContext cmd){
        boolean show = Boolean.parseBoolean( cmd.BOOL().getText() );
        Bash.showSSH = show;
    }

    private Collection<ClusterManager> selectClusterSet(org.antlr.v4.runtime.Token criterionToken) {
        Collection<ClusterManager> selected = new ArrayList();
        if(criterionToken.getType() == HzCmdParser.ALL){
            selected = clusters.values();
        }else{
            selected.add(clusters.get(criterionToken.getText()));
        }
        return  selected;
    }


    private void clearStoped() {
        for (ClusterManager c : clusters.values()) {
            c.clearStoped();
        }
    }



    private ClusterManager selectSubCluster(ClusterManager c, org.antlr.v4.runtime.Token criterionToken) throws Exception {
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


    @Override
    public String toString() {
        return "HzCmd{" +
                "boxes=" + boxes +
                "homeIp='" + homeIp + '\'' +
                ", commsFile='" + commsFile + '\'' +
                ", clusters=" + clusters +
                '}';
    }



    public static void main(String[] args) throws InterruptedException, IOException {


        ReadComms readComms = new ReadComms(HzCmd.commsFile);
        readComms.read();

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

        StringBuilder builder = new StringBuilder();
        for (int i = 1; i < args.length; i++) {
            builder.append(args[i]);
        }

        hzCmd.exeCmd( builder.toString() );

//        hzCmd.clearStoped();

//        System.out.println(hzCmd);

//        hzCmd.exeCmd("homeIp \"127.0.0.1\"");
//        hzCmd.exeCmd("boxes add \"danny\" \"agents.txt\" ");
//        hzCmd.exeCmd("install * OS \"3.6-RC2-SNAPSHOT\"");
//        hzCmd.exeCmd("cluster A 1 2");
//        hzCmd.exeCmd("cluster B 2 3");

        //hzCmd.exeCmd("add member A 1 \"3.6-RC2-SNAPSHOT\" \"-Xms400m\"  ");

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

}