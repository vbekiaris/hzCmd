package remote;

import com.hazelcast.core.HazelcastInstance;
import global.Args;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static global.Bash.killAllJava;
import static global.Utils.exceptionStacktraceToString;
import static global.Utils.sleepMilli;
import static remote.Utils.sendBack;
import static remote.Utils.sendBackError;

public class Controler{

    private BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private HazelcastInstance hazelcastInstance;

    public static HomeSettings home = new HomeSettings();
    private static Tasks tasks;

    public static final String ID = System.getProperty(Args.ID.name());

    public Controler(HazelcastInstance hazelcastInstance){
        this.hazelcastInstance = hazelcastInstance;
        tasks = new Tasks(hazelcastInstance);
    }

    public void run(){
        try{
            while (true){
                String line=in.readLine();
                if (line!=null){
                    String[] words = line.split("\\s+");
                    Args arg = Args.valueOf(words[0]);
                    switch (arg) {
                        case exit:
                            killAllJava();
                            System.exit(0);

                        case load:
                            tasks.loadClass(words[1], words[2]);
                            break;

                        case invoke:
                            tasks.invoke(words[1]);
                            break;

                        case stop:
                            tasks.stop();
                            break;

                        /*
                        case homeUser:
                            home.user = words[1];
                            break;
                        case homeIp:
                            home.ip = words[1];
                            break;
                        case homeCwd:
                            home.cwd = words[1];
                            break;
                        case homeInfile:
                            home.inputFile = words[1];
                            break;
                        */

                        case clean:
                            home.inputFile = words[1];
                            break;
                        case info:
                            sendBack(this.toString());
                            break;
                    }
                }else {
                    sleepMilli(500);
                }
            }
        }catch(Exception e){
            System.out.println("Main loop "+e.getMessage());
            System.out.println(exceptionStacktraceToString(e));

            sendBackError(exceptionStacktraceToString(e));
        }
    }

    @Override
    public String toString() {
        return "Controler{" +
                "hazelcastInstance=" + hazelcastInstance.getName() +
                ", tasks=" + tasks +
                ", home=" + home +
                '}';
    }
}