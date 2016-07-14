package remote.main;

import com.google.gson.Gson;
import global.Args;
import global.NodeType;
import global.ReplyMsg;
import mq.MQ;
import javax.jms.JMSException;
import javax.jms.MessageProducer;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.util.Enumeration;
import java.util.Properties;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import remote.bench.BenchManager;
import global.BenchType;
import remote.command.Cmd;

public abstract class Controler{

    private BenchManager benchManager;
    private static final Gson GSON = new Gson();

    public static final String ID = System.getProperty(Args.ID.name());
    public static final String Q = System.getProperty(Args.Q.name());
    public static final String LIB = System.getProperty(Args.VENDOR_LIB.name());

    private static final String jvmPidId = ManagementFactory.getRuntimeMXBean().getName();

    public final NodeType type;

    private ExecutorService executor = Executors.newFixedThreadPool(3);


    public Controler(NodeType type) throws Exception {
        this.type=type;
        printProperties();
        System.out.println(this);
    }

    public void startEmbeddedObject(MessageProducer replyProducer) throws Exception{
        ReplyMsg msg = new ReplyMsg();
        msg.id=ID;

        try {
            System.out.println("startEmbeddedObject "+replyProducer);
            init(type);
            benchManager = new BenchManager(getVendorObject());
            msg.msg = "Started on " + InetAddress.getLocalHost().getHostAddress() +" "+ jvmPidId +" "+ LIB;

            MQ.sendReply(replyProducer, GSON.toJson(msg));
        }catch (Exception e){
            Utils.recordeException(e);
            msg.error=true;
            msg.msg = e.toString();
            MQ.sendReply(replyProducer, GSON.toJson(msg));
            throw e;
        }
    }

    public abstract void init(NodeType type)  throws Exception ;

    public abstract Object getVendorObject();

    public void load(MessageProducer replyProducer, String id, String clazz){
        benchManager.loadClass(replyProducer, id, clazz);
    }

    public void setThreadCount(MessageProducer replyProducer, String id, int threadCount){
        benchManager.setThreadCount(replyProducer, id, threadCount);
    }

    public void setBenchType(MessageProducer replyProducer, String id, BenchType type, long intervalNanos, boolean allowException, String outFile) {
        benchManager.setBenchType(replyProducer, id, type, intervalNanos, allowException, outFile);
    }

    public void setField(MessageProducer replyProducer, String id, String field, String value){
        benchManager.setField(replyProducer, id, field, value);
    }

    public void writeMetaData(MessageProducer replyProducer, String id, String metaData) {
        benchManager.writeMetaData(replyProducer, id, metaData);
    }

    public void initBench(MessageProducer replyProducer, String id){
        benchManager.init(replyProducer, id);
    }

    public void warmupBench(MessageProducer replyProducer, String id, int seconds){
        executor.submit(new WarmupRunner(replyProducer, id, seconds));
    }

    public void runBench(MessageProducer replyProducer, String id, int seconds){
        executor.submit(new BenchRunner(replyProducer, id, seconds));
    }

    public void cleanup(MessageProducer replyProducer, String id) {
        benchManager.cleanUp(replyProducer, id);
    }


    public void run() throws Exception {
        while (true){
            try {
                Object obj = MQ.receiveObj(Q);
                MessageProducer replyProducer = MQ.getReplyProducer();

                System.out.println("MQ msg in = " + obj);
                System.out.println("MQ replyProducer = " + replyProducer);

                if (obj instanceof Cmd) {
                    ((Cmd) obj).exicute(this, replyProducer);
                }
            } catch (JMSException e){
                Utils.recordeExceptionJms(e);
            } catch (Exception e) {
                Utils.recordeException(e);
            }
        }
    }

    private class WarmupRunner implements Callable<Object> {
        private MessageProducer replyProducer;
        private String id;
        private int seconds;

        public WarmupRunner(MessageProducer replyProducer, String id, int seconds){
            this.replyProducer=replyProducer;
            this.id=id;
            this.seconds=seconds;
        }

        public Object call() {
            benchManager.warmup(replyProducer, id, seconds);
            return null;
        }
    }

    private class BenchRunner implements Callable<Object> {
        private MessageProducer replyProducer;
        private String id;
        private int seconds;

        public BenchRunner(MessageProducer replyProducer, String id, int seconds){
            this.replyProducer=replyProducer;
            this.id=id;
            this.seconds=seconds;
        }

        public Object call() {
            benchManager.bench(replyProducer, id, seconds);
            return null;
        }
    }

    private static void printProperties(){
        try {
            PrintStream ps = new PrintStream(new FileOutputStream("jvm.properties", true));

            Properties p = System.getProperties();
            Enumeration keys = p.keys();
            while (keys.hasMoreElements()) {
                String key = (String)keys.nextElement();
                String value = (String)p.get(key);
                ps.println(key + ": " + value);
            }

            ps.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String toString() {
        return "HzCmd{" +
                " ID=" + ID +
                " Q=" + Q +
                " jvmPidId=" + jvmPidId +
                " lib=" + LIB +
                "}";
    }
}