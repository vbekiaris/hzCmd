package remote;

import global.Args;
import global.NodeType;
import mq.MQ;
import javax.jms.JMSException;

import java.io.*;
import java.lang.management.ManagementFactory;
import java.util.Enumeration;
import java.util.Properties;

import remote.bench.BenchManager;
import remote.bench.BenchType;
import remote.command.Cmd;

import static remote.Utils.recordeException;

public abstract class Controler{

    private BenchManager benchManager;

    public static final String ID = System.getProperty(Args.ID.name());
    public static final String Q = System.getProperty(Args.Q.name());
    public static final String REPLYQ = Q +"reply";

    private static final String jvmPidId = ManagementFactory.getRuntimeMXBean().getName();

    public final NodeType type;

    public Controler(NodeType type) throws Exception {
        this.type=type;
        printProperties();
        System.out.println(this);
        try {
            init(type);
            benchManager = new BenchManager(getVendorObject());
            //MQ.sendObj(REPLYQ, ID+" Started");
        }catch (Exception e){
            recordeException(e);
            //MQ.sendObj(REPLYQ, e);
            throw e;
        }
    }

    public void startEmbeddedObject() throws Exception{
        System.out.println(this);
        System.err.println("startEmbeddedObject "+this);
        try {
            init(type);
            benchManager = new BenchManager(getVendorObject());
            MQ.sendObj(REPLYQ, ID+" Re Started Embedded");
        }catch (Exception e){
            recordeException(e);
            MQ.sendObj(REPLYQ, e);
            throw e;
        }
    }

    public abstract void init(NodeType type)  throws Exception ;

    public abstract Object getVendorObject();

    public void load(String taskId, String clazz){
        try {
            benchManager.loadClass(taskId, clazz);
            MQ.sendObj(REPLYQ, ID+" loaded "+taskId+" "+clazz);
        } catch (Exception e) {
            try {
                MQ.sendObj(REPLYQ, e);
            } catch (JMSException e2) {}
        }
    }

    public void setThreadCount(String taskId, int threadCount){
        try {
            benchManager.setThreadCount(taskId, threadCount);
            MQ.sendObj(REPLYQ, ID+" "+taskId+" threadCount="+threadCount);
        } catch (Exception e) {
            try {
                MQ.sendObj(REPLYQ, e);
            } catch (JMSException e2) {}
        }
    }

    public void setBenchType(String taskId, BenchType type, long intervalNanos, boolean allowException, String outFile) {
        try {
            benchManager.setBenchType(taskId, type, intervalNanos, allowException, outFile);
            MQ.sendObj(REPLYQ, ID+" "+taskId+" BenchType="+type+" intervalNanos="+intervalNanos+" allowException="+allowException+" outFile="+outFile);
        } catch (Exception e) {
            try {
                MQ.sendObj(REPLYQ, e);
            } catch (JMSException e2) {}
        }
    }

    public void writeMetaData(String taskId, String metaData) {
        try {
            benchManager.writeMetaData(taskId, metaData);
            MQ.sendObj(REPLYQ, ID+" "+taskId+" metaData="+metaData);
        } catch (Exception e) {
            try {
                MQ.sendObj(REPLYQ, e);
            } catch (JMSException e2) {}
        }
    }

    public void initBench(String taskId){
        try {
            benchManager.init(taskId);
            MQ.sendObj(REPLYQ, ID+" "+taskId+" init end");
        } catch (Exception e) {
            try {
                MQ.sendObj(REPLYQ, e);
            } catch (JMSException e2) {}
        }
    }

    public void warmupBench(String taskId, int seconds){
        try {
            benchManager.warmup(taskId, seconds);
            MQ.sendObj(REPLYQ, ID+" "+taskId+" warmup end");
        } catch (Exception e) {
            try {
                MQ.sendObj(REPLYQ, e);
            } catch (JMSException e2) {}
        }
    }

    public void runBench(String taskId, int seconds){
        try {
            benchManager.bench(taskId, seconds);
            MQ.sendObj(REPLYQ, ID+" "+taskId + " bench end");
        } catch (Exception e) {
            try {
                MQ.sendObj(REPLYQ, e);
            } catch (JMSException e2) {}
        }
    }

    public void cleanup(String taskId) {
        try {
            benchManager.cleanUp(taskId);
            MQ.sendObj(REPLYQ, ID+" "+taskId+" cleanUp end");
        } catch (Exception e) {
            try {
                MQ.sendObj(REPLYQ, e);
            } catch (JMSException e2) {}
        }
    }

    public void setField(String taskId, String field, String value){
        try {
            benchManager.setField(taskId, field, value);
            MQ.sendObj(REPLYQ, ID+" set " + taskId + " " + field + " " + value);
        } catch (Exception e) {
            try {
                MQ.sendObj(REPLYQ, e);
            } catch (JMSException e2) {}
        }
    }

    public void ping(){
        try {
            MQ.sendObj(REPLYQ, ID +" ping");
        } catch (JMSException jmsError) {
            recordeException(jmsError);
        }
    }

    public void run() throws Exception {
        while (true){
            try {
                Object obj = MQ.receiveObj(Q);
                System.out.println("MQ msg in = "+obj);

                if(obj instanceof Cmd){
                    ((Cmd) obj).exicute(this);
                }
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