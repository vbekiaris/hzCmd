package remote;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.client.config.XmlClientConfigBuilder;
import com.hazelcast.config.Config;
import com.hazelcast.config.XmlConfigBuilder;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import global.Args;
import global.HzType;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.management.ManagementFactory;

import static global.Utils.exceptionStacktraceToString;
import static global.Utils.sleepMilli;
import static remote.Utils.sendBack;
import static remote.Utils.sendBackError;

public class Controler{

    private BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private HazelcastInstance hazelcastInstance;

    public static HomeSettings home = new HomeSettings();
    private static TaskManager tasks;

    public static final String ID = System.getProperty(Args.ID.name());
    public static final String jvmPidId = ManagementFactory.getRuntimeMXBean().getName();

    public final PrintWriter exceptionWrite = new PrintWriter ( new FileWriter("exception.txt", true) );

    //TODO why arnt theses errors sent bck to the home, user box
    public Controler(HzType type) throws Throwable {
        try {
            if (type == HzType.Member) {
                XmlConfigBuilder configBuilder = new XmlConfigBuilder("hazelcast.xml");
                Config config = configBuilder.build();
                hazelcastInstance = Hazelcast.newHazelcastInstance(config);
            } else {
                XmlClientConfigBuilder configBuilder = new XmlClientConfigBuilder("client-hazelcast.xml");
                ClientConfig config = configBuilder.build();
                hazelcastInstance = HazelcastClient.newHazelcastClient(config);
            }
        }catch (Throwable e){
            //sendBackError("starting "+idString()+" "+exceptionStacktraceToString(e));
            e.printStackTrace(exceptionWrite);
            throw e;
        }
        tasks = new TaskManager(hazelcastInstance);
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
                            System.exit(0);

                        case load:
                            tasks.loadClass(words[1], words[2]);
                            break;

                        case invoke:
                            tasks.invokeNonBlocking(Integer.parseInt(words[1]), words[2], words[3]);
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
        return "HzCmd{" + "ID=" + ID +", "+ "jvmPidId=" + jvmPidId + '}';
    }

    @Override
    public String toString() {
        return "HzCmd{" +
                "ID=" + ID +
                "jvmPidId=" + jvmPidId +
                ", tasks=" + tasks +
                '}';
    }
}