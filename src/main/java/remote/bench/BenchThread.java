package remote.bench;

import remote.bench.marker.BenchMarker;

import javax.jms.MessageProducer;
import java.io.Serializable;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

class BenchThread implements Callable<Object>, Serializable {

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

    public Object call() {
        try {
            long start = System.currentTimeMillis();
            System.out.println("thread "+threadNumber+" "+id+" "+clazzName+" started");

            benchMarker.bench(bench);

            long sec = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() - start);
            System.out.println("thread "+threadNumber+" "+id+" "+clazzName+" finished duration "+sec+" seconds");

        }catch (Exception e) {
            return e;
        }
        return this;
    }

    public void setReplyProducer(MessageProducer replyProducer) {
        this.replyProducer = replyProducer;
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