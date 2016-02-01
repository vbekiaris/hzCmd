package remote;

import global.Args;
import global.NodeType;
import jms.MQ;
import javax.jms.JMSException;

import java.io.*;
import java.lang.management.ManagementFactory;

import remote.command.Cmd;

import static remote.Utils.recordeException;

public abstract class Controler{

    protected static TaskManager tasks;

    public static final String ID = System.getProperty(Args.ID.name());
    public static final String EVENTQ = System.getProperty(Args.EVENTQ.name());
    public static final String jvmPidId = ManagementFactory.getRuntimeMXBean().getName();

    public final NodeType type;

    public Controler(NodeType type) throws Exception {
        this.type=type;

        try {
            init(type);
            tasks = new TaskManager(getVendorObject());
            MQ.sendObj(ID, ID+" Started");
        }catch (Exception e){
            recordeException(e);
            MQ.sendObj(ID, e);
            throw e;
        }
        Thread.sleep(9000);
    }


    public abstract void init(NodeType type)  throws Exception ;

    public abstract Object getVendorObject();

    public void load(String taskId, String clazz){
        try {
            tasks.loadClass(taskId, clazz);
        } catch (Exception e) {
            recordeException(e);
            try {
                MQ.sendObj(EVENTQ, e);
            } catch (JMSException jmsError) {
                recordeException(jmsError);
            }
        }
    }

    public void setField(String taskId, String field, String value){
        try {
            tasks.setField(taskId, field, value);
        } catch (Exception e) {
            recordeException(e);
            try {
                MQ.sendObj(EVENTQ, e);
            } catch (JMSException jmsError) {
                recordeException(jmsError);
            }
        }
    }

    public void invokeAsync(int threadCount, String function, String taskId){
        try {
            tasks.invokeAsync(threadCount, function, taskId);
        } catch (NoSuchMethodException e) {
            recordeException(e);
            try {
                MQ.sendObj(EVENTQ, e);
            } catch (JMSException jmsError) {
                recordeException(jmsError);
            }
        }
    }

    public void invokeSync(int threadCount, String function, String taskId){
        try {
            tasks.invokeSync(threadCount, function, taskId);
            MQ.sendObj(ID+"reply", ID+" finished "+function+" on "+taskId);
        } catch (JMSException jmsError) {
            recordeException(jmsError);
        } catch (Exception e) {
            recordeException(e);
            try {
                MQ.sendObj(ID+"reply", e);
            } catch (JMSException jmsError) {
                recordeException(jmsError);
            }
        }
    }

    public void ping(){
        try {
            MQ.sendObj(ID+"reply", ID+" ping");
        } catch (JMSException jmsError) {
            recordeException(jmsError);
        }
    }

    public void run() throws IOException {
        while (true){
            try {
                Object obj = MQ.receiveObj(ID);
                System.out.println("MQ msg in = "+obj);

                if(obj instanceof Cmd){
                    ((Cmd) obj).exicute(this);
                }
            } catch (JMSException e) {
                recordeException(e);
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