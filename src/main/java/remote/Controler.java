package remote;

import com.hazelcast.core.HazelcastInstance;
import global.Args;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;

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
    public static final String jvmPidId = ManagementFactory.getRuntimeMXBean().getName();

    public Controler(HazelcastInstance hazelcastInstance){

        this.hazelcastInstance = hazelcastInstance;
        tasks = new Tasks(hazelcastInstance);
    }

    public void run(){
        try{
            while (true){
                String line=in.readLine();
                if (line!=null){
                    System.out.println(line);
                    String[] words = line.split("\\s+");
                    Args arg = Args.valueOf(words[0]);
                    switch (arg) {
                        case exit:
                            ///System.exit(0);

                        case load:
                            tasks.loadClass(words[1], words[2]);
                            break;

                        case invoke:
                            tasks.invoke(words[1]);
                            break;

                        case stop:
                            tasks.stop();
                            break;

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
            e.printStackTrace();
            sendBackError(idString()+" "+exceptionStacktraceToString(e));
        }
    }

    public String idString(){
        return "Controler{" + "ID=" + ID +", "+ "jvmPidId=" + jvmPidId + '}';
    }

    @Override
    public String toString() {
        return "Controler{" +
                "ID=" + ID +
                "jvmPidId=" + jvmPidId +
                ", tasks=" + tasks +
                '}';
    }
}