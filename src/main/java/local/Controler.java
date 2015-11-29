package local;

import global.Args;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static global.Utils.*;

public class Controler {

    public static String commsFile = "commsIn.txt";

    private BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private BufferedReader commsIn;
    private List<String> history = new ArrayList();

    private RemoteBoxManager boxes = new RemoteBoxManager("agents.txt");
    private Map<String, RemoteJvmManager> clusters = new HashMap();
    private RemoteJvmManager cluster ;

    public Controler() throws IOException, InterruptedException {

        File f = new File(commsFile);
        if(!f.exists()) {
            f.createNewFile();
        }
        commsIn = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
    }

    public void run(){
        try{
            while (true){
                String line=in.readLine();
                if (line!=null && !line.equals("") && !line.startsWith("#")) {
                    history.add(line);
                    System.out.println("=>"+line);

                    String[] words = line.split("\\s+");
                    try {
                        Args arg = Args.valueOf(words[0]);
                        switch (arg) {
                            case exit:
                                cluster.send(line);
                                System.exit(0);

                            case cluster:
                                if (words.length == 4){
                                    for (int i = 0; i < words.length; i++) {
                                        System.out.println(words[i]);
                                    }
                                    String clusterID = words[1];
                                    int start = Integer.parseInt(words[2]);
                                    int end = Integer.parseInt(words[3]);
                                    List<IpPair> ips = boxes.getBoxes(start, end);
                                    RemoteJvmManager jvmManager = new RemoteJvmManager(boxes.getUser(), clusterID, ips);
                                    clusters.put(jvmManager.getClusterId(), jvmManager);
                                    cluster=jvmManager;
                                    System.out.println(cluster);
                                }
                                break;

                            case addip:
                                boxes.add(words[1]);
                                break;

                            case install:
                                Installer.install(boxes);
                                break;
                            case uninstall:
                                Installer.uninstall(boxes);
                                break;

                            case ee:
                                Installer.ee = Boolean.parseBoolean(words[1]);
                                break;
                            case version:
                                Installer.version = words[1];
                                break;

                            case user:
                                boxes.setUser(words[1]);
                                break;
                            case init:
                                cluster.initilizeJvms();
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
                                cluster.addMembers(Integer.parseInt(words[1]));
                                break;
                            case clients:
                                cluster.addClients(Integer.parseInt(words[1]));
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
                }else {
                    sleepMilli(500);
                }

                while ( (line = commsIn.readLine()) != null ){
                    System.out.println(line);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        Controler c = new Controler();
        c.run();
    }

}