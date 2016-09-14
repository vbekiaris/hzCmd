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

    private ExecutorService executor = Executors.newCachedThreadPool();


    public Controler(NodeType type) throws Exception {
        this.type=type;
        printProperties();
        System.out.println(this);
    }

    public void startEmbeddedObject(MessageProducer replyProducer) throws Exception{
        ReplyMsg msg = new ReplyMsg();
        msg.id=ID;

        try {
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
        executor.submit(new Runner(replyProducer, id, seconds, RunStyle.WARMUP));
    }

    public void runBench(MessageProducer replyProducer, String id, int seconds){
        executor.submit(new Runner(replyProducer, id, seconds, RunStyle.BENCH));
    }

    public void stopBench(MessageProducer replyProducer, String id) {
        benchManager.setRunning(replyProducer, id, false);
    }

    public void removeBench(MessageProducer replyProducer, String id) {
        benchManager.removeBench(replyProducer, id);
    }

    public void postPhase(MessageProducer replyProducer, String id) {
        benchManager.postPhase(replyProducer, id);
    }

    public enum RunStyle {
        WARMUP, BENCH
    }

    private class Runner implements Callable<Object> {

        private MessageProducer replyProducer;
        private String id;
        private int seconds;
        private RunStyle style;

        public Runner(MessageProducer replyProducer, String id, int seconds, RunStyle style){
            this.replyProducer=replyProducer;
            this.id=id;
            this.seconds=seconds;
            this.style=style;
        }

        public Object call() {
            if (style.equals(RunStyle.WARMUP)){
                benchManager.warmup(replyProducer, id, seconds);
            }else if (style.equals(RunStyle.BENCH)){
                benchManager.bench(replyProducer, id, seconds);
            }
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


    public void run() throws Exception {
        while (true){
            try {
                Object obj = MQ.receiveObj(Q);
                MessageProducer replyProducer = MQ.getReplyProducer();

                System.out.println("MQ msg in = " + obj);

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


    public String toString() {
        return "HzCmd{" +
                " ID=" + ID +
                " Q=" + Q +
                " jvmPidId=" + jvmPidId +
                " lib=" + LIB +
                "}";
    }
}