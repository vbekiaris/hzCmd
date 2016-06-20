package remote.bench;

import mq.MQ;
import remote.Utils;
import remote.bench.marker.BenchMarker;
import remote.bench.marker.HdrMarker;
import remote.bench.marker.MetricsMarker;

import javax.jms.JMSException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static remote.Utils.instantiate;

public class BenchContainer {

    private String id;
    private String clazzName;
    private Object vendorObject;
    private static String outputFileName;
    private BenchMarker benchMarker;

    private List<Bench> benchThreads = new ArrayList();

    public BenchContainer(Object vendorObject, String id, String clazz){
        this.vendorObject=vendorObject;
        this.id=id;
        this.clazzName=clazz;
    }

    public String getId(){return id;}

    public void writeMetaData(String metaData) {
        benchMarker.writeMeataDataFile(outputFileName, metaData);
    }

    public void setThreadCount(int count) throws Exception {

        count = count - benchThreads.size();

        if(count>0){
            for(int i=0; i<count; i++){
                benchThreads.add(instantiate(clazzName, Bench.class));
            }
        }else{
            count = Math.abs(count);
            for(int i=0; i<count; i++){
                benchThreads.remove(i);
            }
        }

        for (Bench b : benchThreads) {
            b.setVendorObject(vendorObject);
        }
    }


    public void setField(String field, String value) throws Exception{
        for (Bench b : benchThreads) {
            Utils.setField(field, value, b);
        }
    }

    public void init(){
        for (Bench b : benchThreads) {
            b.init();
        }
    }

    public void cleanUp(){
        if ( benchThreads.size() >0 ){
            benchThreads.get(0).cleanup();
        }
    }

    public void setBenchType(BenchType type, long expectedIntervalNanos, boolean allowException, String outFile){
        outputFileName=outFile;
        switch (type){
            case Metrics:
                benchMarker = new MetricsMarker(expectedIntervalNanos, allowException);
                break;
            case Hdr :
                benchMarker = new HdrMarker(expectedIntervalNanos, allowException);
                break;
        }
    }

    public void warmup(int sec) throws InterruptedException {
        benchMarker.preBench(outputFileName+"-warmup");
        invokeSync(sec);
        benchMarker.postBench();
    }

    public void bench(int sec) throws InterruptedException {
        benchMarker.preBench(outputFileName+"-bench");
        invokeSync(sec);
        benchMarker.postBench();
    }

    private boolean invokeSync(int seconds) throws InterruptedException {
        benchMarker.setDurationSeconds(seconds);

        ExecutorService executor = Executors.newFixedThreadPool(benchThreads.size());

        int number=0;
        for (Bench b : benchThreads) {
            executor.submit( new BenchRunner(b, ++number) );
        }
        executor.shutdown();
        return executor.awaitTermination(seconds+300, TimeUnit.SECONDS);
    }


    private class BenchRunner implements Callable<Object>{

        private Bench mark;
        private int threadNumber;

        public BenchRunner(Bench bench, int threadNumber){
            mark=bench;
            this.threadNumber=threadNumber;
        }

        public Object call() {
            try {
                long start = System.currentTimeMillis();
                System.out.println("thread "+threadNumber+" "+id+" "+clazzName+" started");

                benchMarker.bench(mark);

                long sec = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() - start);
                System.out.println("thread "+threadNumber+" "+id+" "+clazzName+" end "+sec);

            }catch (Exception e) {
                try {
                    MQ.sendReply(e);
                } catch (JMSException jmsError) {
                    jmsError.printStackTrace();
                }
            }
            return null;
        }
    }
}