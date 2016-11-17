package remote.bench;

import global.ReplyMsg;
import remote.bench.marker.BenchMarker;
import remote.main.Controler;

import javax.jms.MessageProducer;
import java.io.Serializable;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

class BenchThread implements Callable<ReplyMsg>, Serializable {

    private BenchMarker benchMarker;
    private Bench bench;
    private String id;
    private String clazzName;
    private int threadNumber;
    private MessageProducer replyProducer;

    public BenchThread(BenchMarker benchMarker, Bench bench, String id, String clazzName, int threadNumber){
        this.benchMarker=benchMarker;
        this.bench=bench;
        this.id=id;
        this.clazzName=clazzName;
        this.threadNumber=threadNumber;
    }

    public ReplyMsg call() {
        ReplyMsg msg = new ReplyMsg();
        msg.id=Controler.ID;
        msg.benchId=id;
        msg.benchClazz=clazzName;
        try {
            long start = System.currentTimeMillis();
            System.out.println(msg+" started");

            benchMarker.bench(bench);

            long sec = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() - start);
            System.out.println(msg+" finished duration "+sec+" seconds");

        }catch (Throwable e) {
            msg.error=true;
            msg.threadId=threadNumber+"";
            msg.msg=e.toString();
            return msg;
        }
        return msg;
    }

    public void setReplyProducer(MessageProducer replyProducer) {
        this.replyProducer = replyProducer;
    }

    public String getId(){
        return id;
    }

    @Override
    public String toString() {
        return "BenchThread{" +
                "bench=" + bench +
                ", benchMarker=" + benchMarker +
                ", id='" + id + '\'' +
                ", clazzName='" + clazzName + '\'' +
                ", threadNumber=" + threadNumber +
                ", replyProducer=" + replyProducer +
                '}';
    }

}