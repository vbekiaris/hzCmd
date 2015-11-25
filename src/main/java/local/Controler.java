package local;

import global.Args;

import java.io.*;

import static global.Utils.sleepMilli;
import static global.Utils.sleepSeconds;

public class Controler {

    public static String msgFile = "msg.txt";

    private BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private BufferedReader msg;
    private RemoteBoxes boxes = new RemoteBoxes();

    public Controler() throws IOException {

        File f = new File(msgFile);
        if(!f.exists()) {
            f.createNewFile();
        }
        msg = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
    }

    public void run(){
        try{
            while (true){
                String line=in.readLine();
                if (line!=null) {
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
                            case start:
                                boxes.startJvms();
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

                if ( (line = msg.readLine()) != null ){
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