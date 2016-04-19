package remote.bench;

import remote.Utils;
import remote.bench.marker.BenchMarker;
import remote.bench.marker.HdrMarker;
import remote.bench.marker.MetricsMarker;

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
    private BenchMarker benchMarker;

    private List<Bench> benchs = new ArrayList();

    public BenchContainer(Object vendorObject, String id, String clazz){
        this.vendorObject=vendorObject;
        this.id=id;
        this.clazzName=clazz;
    }

    public String getId(){return id;}

    public void writeMetaData(String metaData) {
        benchMarker.writeMeataDataFile(metaData);
    }

    public void setThreadCount(int count) throws Exception {

        count = count - benchs.size();

        if(count>0){
            for(int i=0; i<count; i++){
                benchs.add(instantiate(clazzName, Bench.class));
            }
        }else{
            count = Math.abs(count);
            for(int i=0; i<count; i++){
                benchs.remove(i);
            }
        }

        for (Bench b : benchs) {
            b.setVendorObject(vendorObject);
        }
    }


    public void setField(String field, String value) throws Exception{
        for (Bench b : benchs) {
            Utils.setField(field, value, b);
        }
    }

    public void init(){
        for (Bench b : benchs) {
            b.init();
        }
    }

    public void cleanUp(){
        for (Bench b : benchs) {
            b.cleanup();
        }
    }

    public void setBenchType(BenchType type, long expectedIntervalNanos, boolean stop, String outFile){
        switch (type){
            case Metrics:
                benchMarker = new MetricsMarker(expectedIntervalNanos, stop, outFile);
                break;
            case Hdr :
                benchMarker = new HdrMarker(expectedIntervalNanos, stop, outFile);
                break;
        }
    }

    public void warmup(int sec) throws InterruptedException {
        benchMarker.preBench(benchMarker.getOutputFileName()+"-warmup");
        invokeSync(sec);
        benchMarker.postBench();
    }

    public void bench(int sec) throws InterruptedException {
        benchMarker.preBench(benchMarker.getOutputFileName()+"-bench");
        invokeSync(sec);
        benchMarker.postBench();
    }

    private boolean invokeSync(int seconds) throws InterruptedException {
        benchMarker.setDurationSeconds(seconds);

        ExecutorService executor = Executors.newFixedThreadPool(benchs.size());

        for (Bench b : benchs) {
            executor.submit( new Runner( b ) );
        }
        executor.shutdown();
        return executor.awaitTermination(seconds+300, TimeUnit.SECONDS);
    }


    private class Runner implements Callable<Object>{

        private Bench mark;

        public Runner(Bench bench){
            mark=bench;
        }

        public Object call() {
            try {
                long start = System.currentTimeMillis();
                System.out.println("start "+start);

                benchMarker.bench(mark);

                long end = System.currentTimeMillis();
                long elapsed = end - start;
                System.out.println("elapsed " + elapsed);

            }catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}