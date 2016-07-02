package remote;

import global.Args;
import global.NodeType;
import mq.MQ;
import javax.jms.JMSException;
import javax.jms.MessageProducer;

import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.util.Enumeration;
import java.util.Properties;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import remote.bench.BenchManager;
import global.BenchType;
import remote.command.Cmd;

import static remote.Utils.recordeException;
import static remote.Utils.recordeExceptionJms;

public abstract class Controler{

    private BenchManager benchManager;

    public static final String ID = System.getProperty(Args.ID.name());
    public static final String Q = System.getProperty(Args.Q.name());

    private static final String jvmPidId = ManagementFactory.getRuntimeMXBean().getName();

    public final NodeType type;

    //private ExecutorService executor = Executors.newFixedThreadPool(5);


    public Controler(NodeType type) throws Exception {
        this.type=type;
        printProperties();
        System.out.println(this);
    }

    public void startEmbeddedObject(MessageProducer replyProducer) throws Exception{
        try {
            init(type);
            benchManager = new BenchManager(getVendorObject());
            MQ.sendReply(replyProducer, ID + " Started on " + InetAddress.getLocalHost().getHostAddress());
        }catch (Exception e){
            recordeException(e);
            MQ.sendReply(replyProducer, e);
            throw e;
        }
    }

    public abstract void init(NodeType type)  throws Exception ;

    public abstract Object getVendorObject();

    public void load(MessageProducer replyProducer, String taskId, String clazz){
        benchManager.loadClass(replyProducer, taskId, clazz);
    }

    public void setThreadCount(MessageProducer replyProducer, String taskId, int threadCount){
        benchManager.setThreadCount(replyProducer, taskId, threadCount);
    }

    public void setBenchType(MessageProducer replyProducer, String taskId, BenchType type, long intervalNanos, boolean allowException, String outFile) {
        benchManager.setBenchType(replyProducer, taskId, type, intervalNanos, allowException, outFile);
    }

    public void setField(MessageProducer replyProducer, String taskId, String field, String value){
        benchManager.setField(replyProducer, taskId, field, value);
    }

    public void writeMetaData(MessageProducer replyProducer, String taskId, String metaData) {
        benchManager.writeMetaData(replyProducer, taskId, metaData);
    }

    public void initBench(MessageProducer replyProducer, String taskId){
        benchManager.init(replyProducer, taskId);
    }

    public void warmupBench(MessageProducer replyProducer, String taskId, int seconds){
        benchManager.warmup(replyProducer, taskId, seconds);
    }

    public void runBench(String taskId, int seconds, MessageProducer replyProducer){

//        executor.submit(new BenchRunner(taskId, seconds, replyProducer));
    }


    public void cleanup(MessageProducer replyProducer, String taskId) {
        benchManager.cleanUp(replyProducer, taskId);
    }


    public void run() throws Exception {
        while (true){
            try {
                Object obj = MQ.receiveObj(Q);
                MessageProducer replyProducer = MQ.getReplyProducer();

                System.out.println("MQ msg in = " + obj);
                //System.out.println("MQ msg reply = " + replyProducer);

                if (obj instanceof Cmd) {
                    ((Cmd) obj).exicute(this, replyProducer);
                }
            } catch (JMSException e){
                recordeExceptionJms(e);
            } catch (Exception e) {
                recordeException(e);
            }
        }
    }

    private static void printProperties(){
        Properties p = System.getProperties();
        Enumeration keys = p.keys();
        while (keys.hasMoreElements()) {
            String key = (String)keys.nextElement();
            String value = (String)p.get(key);
            System.out.println(key + ": " + value);
        }
    }

    public String toString() {
        return "HzCmd{" +
                " ID=" + ID +
                " Q=" + Q +
                " jvmPidId=" + jvmPidId +
                "}";
    }
}