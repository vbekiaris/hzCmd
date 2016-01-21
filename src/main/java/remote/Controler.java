package remote;

import global.Args;
import global.NodeType;
import jms.MQ;
import javax.jms.JMSException;
import javax.jms.Message;

import java.io.*;
import java.lang.management.ManagementFactory;

import static remote.Utils.sendBAckException;

public abstract class Controler{

    public static HomeSettings home = new HomeSettings();
    protected static TaskManager tasks;

    public static final String ID = System.getProperty(Args.ID.name());
    public static final String jvmPidId = ManagementFactory.getRuntimeMXBean().getName();

    public final PrintStream exceptionWrite = new PrintStream(new FileOutputStream("exception.txt", true));

    public final NodeType type;

    public Controler(NodeType type) throws Exception {
        this.type=type;
        initilize();
    }

    private void initilize() throws Exception {
        try {
            init(type);
        }catch (Exception e){
            e.printStackTrace(exceptionWrite);
            sendBAckException( new Exception("starting " + idString(), e) );
            throw e;
        }
    }


    public abstract void init(NodeType type)  throws Exception ;

    public void run() throws IOException {
        while (true){
            try {
                Message m = MQ.receive(ID);


                System.out.println("recived MQ msg = "+m);
                MQ.acknolage(m);
                System.out.println("acked MQ msg = "+m);
            } catch (JMSException e) {
                e.printStackTrace();
            }


            /*
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
            */
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