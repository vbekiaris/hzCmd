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

import static global.Utils.*;


//TODO WAN REP xml SETUP
//TODO man center integ
//TODO tail -f grepping logs and pop up display
//TODO exampe 3 3node cluster wan replicate ring, with hotrestart,  members putting,  clients getting,  kill restart members,  man center running
public class HzCmd {

    public static String homeIp;
    public static String commsFile = "commsIn.txt";
    private ReadComms readComms = new ReadComms(commsFile);

    private BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private List<String> history = new ArrayList();

    private BoxManager boxes = new BoxManager();
    private Map<String, ClusterManager> clusters = new HashMap();
    private Map<String, String> vars = new HashMap();

    private boolean repeatProppt = true;

    public HzCmd() throws IOException, InterruptedException {
        readComms.start();
    }

    public void run() throws IOException {
        while (true){
            String line=in.readLine();
            history.add(line);
            if (line!=null && !line.equals("") && !line.startsWith("#") && !line.startsWith("//")) {
                try{
                    HzCmdLexer lexer = new HzCmdLexer(new ANTLRInputStream(line));
                    CommonTokenStream tokens = new CommonTokenStream(lexer);
                    HzCmdParser parser = new HzCmdParser(tokens);
                    HzCmdParser.StatementContext cmd = parser.statement();

                    org.antlr.v4.runtime.Token token;
                    for (token = lexer.nextToken(); token.getType() != Token.EOF;  token = lexer.nextToken() ) {
                        System.out.println(token.getText());
                    }

                    if(repeatProppt) { System.out.println("=>" + line); }

                    switch (cmd.start.getType()) {
                        case HzCmdParser.BOXES:
                            boxesCmd(cmd);
                            break;
                        case HzCmdParser.VAR:
                            assignment(cmd);
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
                        case HzCmdParser.SLEEP:
                            sleep(cmd);
                            break;
                        case HzCmdParser.SHOWSSH:
                            showSSH(cmd);
                            break;
                        case HzCmdParser.PROMPT:
                            prompt(cmd);
                            break;
                        case HzCmdParser.EXIT:
                            exit();
                            break;
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }else {
                sleepMilli(500);
            }
        }
    }

    public void exit(){
        readComms.running=false;
        while (! readComms.dead() ){
            sleepMilli(100);
        }
        System.exit(0);
    }

    private void assignment(HzCmdParser.StatementContext cmd){
        if( cmd.ASSIGN() != null){
            String var = cmd.start.getText();
            String value = cmd.STRING(0).getText().replace("\"", "");
            vars.put(var, value);
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


    private void sleep(HzCmdParser.StatementContext cmd){
        int seconds = Integer.parseInt(cmd.NUMBER(0).getText());
        sleepSeconds(seconds);
    }



    private void showSSH(HzCmdParser.StatementContext cmd){
        boolean show = Boolean.parseBoolean( cmd.BOOL().getText() );
        Bash.showSSH = show;
    }

    private void prompt(HzCmdParser.StatementContext cmd){
        boolean show = Boolean.parseBoolean( cmd.BOOL().getText() );
        repeatProppt = show;
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

    public static void main(String[] args) throws InterruptedException, IOException {
        HzCmd c = new HzCmd();
        c.run();
    }

}