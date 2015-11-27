package local;

import global.Args;
import global.Bash;

import java.io.*;

import static global.Utils.sleepMilli;
import static global.Utils.sleepSeconds;

public class Controler {

    public static String commsFile = "commsIn.txt";

    private BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private BufferedReader commsIn;
    private RemoteBoxes boxes = new RemoteBoxes();

    public Controler() throws IOException {

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

                    System.out.println("=>"+line);

                    String[] words = line.split("\\s+");
                    try {
                        Args arg = Args.valueOf(words[0]);
                        switch (arg) {
                            case exit:
                                boxes.send(line);
                                System.exit(0);

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
                                boxes.initilizeJvms();
                                break;

                            case load:
                            case invoke:
                            case stop:
                            case homeIp:
                                boxes.send(line);
                                break;
                            case ssh:
                                boxes.sshCmd(line.replace("ssh", ""));
                                break;
                            case info:
                                System.out.println(boxes);
                                boxes.send(line);
                                break;

                            case clean:
                                boxes.clean();
                                break;
                            case membersOnly:
                                boxes.setMembersOnlyCount(Integer.parseInt(words[1]));
                                break;
                            case members:
                                boxes.setMembersCount(Integer.parseInt(words[1]));
                                break;
                            case clients:
                                boxes.setClientsCount(Integer.parseInt(words[1]));
                                break;

                            case membersLogs:
                                boxes.catMemberLogs();
                                break;
                            case grep:
                                boxes.grepMembers(words[1]);
                                break;

                            case msg:
                                System.out.println(line);
                                break;

                            case sleep:
                                sleepSeconds(Integer.parseInt(words[1]));
                                break;

                            default:
                                System.out.println(line + " ???");
                        }
                    }catch (Exception e){
                        System.out.println(line+" ???");
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