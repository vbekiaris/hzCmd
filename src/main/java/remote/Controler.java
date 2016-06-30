package remote;

import global.Args;
import global.NodeType;
import mq.MQ;
import javax.jms.JMSException;
import javax.jms.MessageProducer;

import java.lang.management.ManagementFactory;
import java.util.Enumeration;
import java.util.Properties;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import remote.bench.BenchManager;
import remote.bench.BenchType;
import remote.command.Cmd;

import static remote.Utils.recordeException;
import static remote.Utils.recordeExceptionJms;

public abstract class Controler{

    private BenchManager benchManager;

    public static final String ID = System.getProperty(Args.ID.name());
    public static final String Q = System.getProperty(Args.Q.name());

    private static final String jvmPidId = ManagementFactory.getRuntimeMXBean().getName();

    public final NodeType type;

    private ExecutorService executor = Executors.newFixedThreadPool(5);


    public Controler(NodeType type) throws Exception {
        this.type=type;
        printProperties();
        System.out.println(this);
    }

    public void startEmbeddedObject() throws Exception{
        try {
            init(type);
            benchManager = new BenchManager(getVendorObject());
            MQ.sendReply(ID+" Started");
        }catch (Exception e){
            recordeException(e);
            MQ.sendReply(e);
            throw e;
        }
    }

    public abstract void init(NodeType type)  throws Exception ;

    public abstract Object getVendorObject();

    public void load(String taskId, String clazz){
        try {
            benchManager.loadClass(taskId, clazz);
            MQ.sendReply(ID+" "+taskId+" "+clazz+" loaded");
        } catch (Exception e) {
            try {
                MQ.sendReply(e);
            } catch (JMSException e2) {}
        }
    }

    public void setThreadCount(String taskId, int threadCount){
        try {
            benchManager.setThreadCount(taskId, threadCount);
            MQ.sendReply(ID+" "+taskId+" threadCount="+threadCount);
        } catch (Exception e) {
            try {
                MQ.sendReply(e);
            } catch (JMSException e2) {}
        }
    }

    public void setBenchType(String taskId, BenchType type, long intervalNanos, boolean allowException, String outFile) {
        try {
            benchManager.setBenchType(taskId, type, intervalNanos, allowException, outFile);
            MQ.sendReply(ID+" "+taskId+" BenchType="+type+" intervalNanos="+intervalNanos+" allowException="+allowException+" outFile="+outFile);
        } catch (Exception e) {
            try {
                MQ.sendReply(e);
            } catch (JMSException e2) {}
        }
    }

    public void writeMetaData(String taskId, String metaData) {
        try {
            benchManager.writeMetaData(taskId, metaData);
            MQ.sendReply(ID+" "+taskId+" metaData="+metaData);
        } catch (Exception e) {
            try {
                MQ.sendReply(e);
            } catch (JMSException e2) {}
        }
    }

    public void initBench(String taskId){
        try {
            benchManager.init(taskId);
            MQ.sendReply(ID+" "+taskId+" init end");
        } catch (Exception e) {
            try {
                MQ.sendReply(e);
            } catch (JMSException e2) {}
        }
    }

    public void warmupBench(String taskId, int seconds){
        try {
            benchManager.warmup(taskId, seconds);
            MQ.sendReply(ID+" "+taskId+" warmup end");
        } catch (Exception e) {
            try {
                MQ.sendReply(e);
            } catch (JMSException e2) {}
        }
    }

    public void runBench(String taskId, int seconds, MessageProducer replyProducer){

        executor.submit( new BenchRunner(taskId, seconds, replyProducer)
        );
    }

    private class BenchRunner implements Callable<Object> {
        String taskId;
        int seconds;
        MessageProducer replyProducer;

        public BenchRunner(String taskId, int seconds, MessageProducer replyProducer){
            this.taskId=taskId;
            this.seconds=seconds;
            this.replyProducer=replyProducer;
        }

        public Object call() {

            try {
                long start = System.currentTimeMillis();
                System.out.println("taskId "+taskId+" started");

                benchManager.bench(taskId, seconds);

                long sec = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() - start);
                System.out.println("taskId "+taskId+" finished duration "+sec+" seconds");

                MQ.sendReply(replyProducer, ID+" "+taskId + " bench finished duration "+sec+" seconds");
            } catch (Exception e) {
                try {
                    MQ.sendReply(e);
                } catch (JMSException e2) {}
            }

            return null;
        }
    }





    public void cleanup(String taskId) {
        try {
            benchManager.cleanUp(taskId);
            MQ.sendReply(ID+" "+taskId+" cleanUp end");
        } catch (Exception e) {
            try {
                MQ.sendReply(e);
            } catch (JMSException e2) {}
        }
    }

    public void setField(String taskId, String field, String value){
        try {
            benchManager.setField(taskId, field, value);
            MQ.sendReply(ID+" " + taskId + " " + field + " " + value);
        } catch (Exception e) {
            try {
                MQ.sendReply(e);
            } catch (JMSException e2) {}
        }
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