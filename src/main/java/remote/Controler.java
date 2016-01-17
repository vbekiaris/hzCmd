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
import jms.MQ;

import javax.jms.JMSException;
import javax.jms.Message;
import java.io.*;
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

    public final PrintStream exceptionWrite = new PrintStream(new FileOutputStream("exception.txt", true));

    public Controler(HzType type) throws Throwable {
        HelloThread h = new HelloThread();
        h.start();
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
            tasks = new TaskManager(hazelcastInstance);

        }catch (Throwable e){
            sendBackError("starting "+idString()+" "+exceptionStacktraceToString(e));
            e.printStackTrace(exceptionWrite);
            throw e;
        }
    }

    public void run() throws IOException {

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
                        tasks.stop(words[1]);
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
    }

    class HelloThread extends Thread {

        public void run() {

            while (true){
                try {
                    Message m = MQ.receive(ID);
                    System.out.println("recived MQ msg = "+m);
                    MQ.acknolage(m);
                    System.out.println("acked MQ msg = "+m);
                } catch (JMSException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
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