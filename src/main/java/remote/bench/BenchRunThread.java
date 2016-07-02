package remote.bench;

import mq.MQ;
import remote.bench.marker.BenchMarker;

import javax.jms.JMSException;
import javax.jms.MessageProducer;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

class BenchRunThread implements Callable<Object> {

    private BenchMarker benchMarker;
    private Bench bench;
    private String id;
    private String clazzName;
    private int threadNumber;
    private MessageProducer replyProducer;

    public BenchRunThread(BenchMarker benchMarker, Bench bench, String id, String clazzName, int threadNumber){
        this.benchMarker=benchMarker;
        this.bench=bench;
        this.id=id;
        this.clazzName=clazzName;
        this.threadNumber=threadNumber;
    }

    public Object call() {
        try {
            long start = System.currentTimeMillis();
            System.out.println("thread "+threadNumber+" "+id+" "+clazzName+" started");

            benchMarker.bench(bench);

            long sec = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() - start);
            System.out.println("thread "+threadNumber+" "+id+" "+clazzName+" finished duration "+sec+" seconds");

        }catch (Exception e) {
            try {
                MQ.sendReply(replyProducer, e);
            } catch (JMSException e1) {
                e1.printStackTrace();
            }
            return e;
        }
        return null;
    }

    public void setReplyProducer(MessageProducer replyProducer) {
        this.replyProducer = replyProducer;
    }
}