package local;

import antlr4.HzCmdLexer;
import antlr4.HzCmdParser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.io.*;
import java.util.*;

import static global.Utils.*;

public class HzCmd {

    public static String commsFile = "commsIn.txt";
    private ReadComms readComms = new ReadComms(commsFile);

    private BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private List<String> history = new ArrayList();

    private RemoteBoxManager boxes = new RemoteBoxManager("agents.txt");
    private Map<String, ClusterManager> clusters = new HashMap();
    private Map<String, String> vars = new HashMap();


    //TODO need a variables map,  to store strings
    // v1 = 3.6
    // bigJvm = -xmxm16G -xmsx8G -Dblar=blar
    public HzCmd() throws IOException, InterruptedException {
        readComms.start();
    }

    public void run() throws IOException {
            while (true){
                String line=in.readLine();
                if (line!=null && !line.equals("") && !line.startsWith("#") && !line.startsWith("//")) {
                    try{
                        HzCmdLexer lexer = new HzCmdLexer(new ANTLRInputStream(line));
                        CommonTokenStream tokens = new CommonTokenStream(lexer);
                        HzCmdParser parser = new HzCmdParser(tokens);
                        HzCmdParser.StatementContext cmd = parser.statement();

                        history.add(line);
                        System.out.println("=>"+line);

                        switch (cmd.start.getType()) {
                            case HzCmdParser.VAR:
                                assignment(cmd);
                                break;
                            case HzCmdParser.USER:
                                user(cmd);
                                break;
                            case HzCmdParser.CLUSTER:
                                cluster(cmd);
                                break;
                            case HzCmdParser.INSTALL:
                                install(cmd);
                                break;
                            case HzCmdParser.ADD:
                                add(cmd);
                                break;
                            case HzCmdParser.KILL:
                                kill(cmd);
                                break;
                            case HzCmdParser.SLEEP:
                                sleep(cmd);
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

    private void user(HzCmdParser.StatementContext cmd){
        boxes.setUser( cmd.STRING(0).getText().replace("\"", "") );
    }

    private void cluster(HzCmdParser.StatementContext cmd){
        String clusterID = cmd.VAR(0).getText();
        int start = Integer.parseInt( cmd.NUMBER(0).getText() );
        int end = Integer.parseInt( cmd.NUMBER(1).getText() );

        List<IpPair> ips = boxes.getBoxes(start, end);
        ClusterManager jvmManager = new ClusterManager(boxes.getUser(), clusterID, ips);
        clusters.put(jvmManager.getClusterId(), jvmManager);

        System.out.println(jvmManager);
    }

    private void install(HzCmdParser.StatementContext cmd) throws IOException, InterruptedException {
        String clusterID;
        if( cmd.ALL(0) != null) {
            clusterID = cmd.ALL(0).getText();
        }else{
            clusterID = cmd.VAR(0).getText();
        }

        boolean ee = false;
        if( cmd.EE() != null) {
            ee=true;
        }

        List<TerminalNode> ids = cmd.VAR();
        String[] versions = new String[ids.size()-1];

        for (int i = 1; i < ids.size(); i++) {
            String key = ids.get(i).getText();
            versions[i-1]=(vars.get(key));
            System.out.println(key +"  "+versions[i-1]);
        }

        Installer.install(boxes, ee, versions);
    }

    private void add(HzCmdParser.StatementContext cmd) throws IOException, InterruptedException {

        Collection<ClusterManager> c = new ArrayList();
        if( cmd.ALL(0) != null) {
            c = clusters.values();
        }else {
            String clusterId = cmd.VAR(0).getText();
            c.add( clusters.get(clusterId) );
        }

        int threadQty = Integer.parseInt(cmd.NUMBER(0).getText());

        String version = cmd.VAR(0).getText();
        version = vars.get(version);

        for (ClusterManager jvmManager : c) {
            if( cmd.MEMBER() != null) {
                jvmManager.addMembers(threadQty, version);
            }else {
                jvmManager.addClients(threadQty, version);
            }
        }

    }

    private void kill(HzCmdParser.StatementContext cmd) throws IOException, InterruptedException {

        Collection<ClusterManager> c = new ArrayList();
        if( cmd.ALL(0) != null) {
            c = clusters.values();
        }else {
            String clusterId = cmd.VAR(0).getText();
            c.add( clusters.get(clusterId) );
        }

        if( cmd.ALL(1) != null) {
            for (ClusterManager jvmManager : c) {
                jvmManager.killAll();
            }
        }

    }

    private void sleep(HzCmdParser.StatementContext cmd){
        int seconds = Integer.parseInt(cmd.NUMBER(0).getText());
        sleepSeconds(seconds);
    }






/*
    public void processLine(String line ){
        history.add(line);
        System.out.println("=>"+line);

        String[] words = line.split("\\s+");
        try {
            Args arg = Args.valueOf(words[0]);
            switch (arg) {
                case exit:
                    exit();

                case cluster:
                    if (words.length == 4){
                        String clusterID = words[1];
                        int start = Integer.parseInt(words[2]);
                        int end = Integer.parseInt(words[3]);

                        List<IpPair> ips = boxes.getBoxes(start, end);
                        ClusterManager jvmManager = new ClusterManager(boxes.getUser(), clusterID, ips);
                        clusters.put(jvmManager.getClusterId(), jvmManager);
                        cluster=jvmManager;

                        System.out.println(cluster);
                    }
                    break;

                case addip:
                    boxes.addIp(words[1]);
                    break;

                case install:
                    Installer.install(boxes, false, "3.5", "3.6-EA2");
                    break;
                case uninstall:
                    Installer.uninstall(boxes);
                    break;


                case user:
                    boxes.setUser(words[1]);
                    break;
                case init:
                    cluster.restartJmvs("3.5");
                    break;

                case load:
                case invoke:
                case stop:
                    cluster.send(line);
                    break;
                case info:
                    System.out.println(boxes);
                    cluster.send(line);
                    break;
                case layout:
                    System.out.println(cluster);

                case clean:
                    cluster.clean();


                    break;
                case membersOnly:
                    cluster.setMembersOnlyCount(Integer.parseInt(words[1]));
                    break;
                case members:
                    cluster.addMembers(Integer.parseInt(words[1]), "3.5");
                    break;
                case clients:
                    cluster.addClients(Integer.parseInt(words[1]), "3.5");
                    break;


                case kill:
                    String clusterid = (words[1]);
                    clusters.get(clusterid).kill(words[2]);
                    break;
                case ssh:
                    boxes.sshCmd(line.replace("ssh", ""));
                    break;
                case cat:
                    cluster.catMemberLogs();
                    break;
                case grep:
                    cluster.grepMembers(words[1]);
                    break;
                case jps:
                    boxes.jps();
                    break;


                case msg:
                    System.out.println(line);
                    break;

                case sleep:
                    sleepSeconds(Integer.parseInt(words[1]));
                    break;

                default:
                    System.out.println("unknown cmd =>"+line);
            }
        }catch (Exception e){
            System.out.println("CMD =>"+line);
            e.printStackTrace();
        }
    }
*/


    public static void main(String[] args) throws InterruptedException, IOException {
        HzCmd c = new HzCmd();
        c.run();
    }

}