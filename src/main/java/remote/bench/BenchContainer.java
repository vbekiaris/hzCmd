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

    private Object vendorObject;
    private String outputFileName="";
    private BenchType benchType = BenchType.Hdr;
    private BenchMarker benchMarker = new HdrMarker();
    private String clazzName;
    private String id;

    private List<Bench> benchs = new ArrayList();

    public BenchContainer(Object vendorObject, String id, String clazz){
        this.vendorObject=vendorObject;
        this.id=id;
        this.clazzName=clazz;
    }

    public String getId(){return id;}

    public void setOutputFileName(String fileName) {
        outputFileName = fileName;
    }

    public void setThreadCount(int count) throws Exception {
        for(int i=0; i<count; i++){
            benchs.add(instantiate(clazzName, Bench.class));
        }
        for (Bench b : benchs) {
            b.setVendorObject(vendorObject);
        }
    }

    public void stopAtException(boolean stop) throws Exception{
        benchMarker.setStopAtException(stop);
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

    public void setBenchType(BenchType type){
        benchType=type;
        switch (benchType){
            case Metrics:
            case MetricsInterval:
                benchMarker = new MetricsMarker();
                break;
            case Hdr :
            case HdrInterval:
                benchMarker = new HdrMarker();
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

                switch (benchType){
                    case Metrics:
                    case Hdr :
                        benchMarker.bench(mark);
                        break;

                    case MetricsInterval:
                    case HdrInterval:
                        benchMarker.benchInterval(mark);
                        break;
                }
                long end = System.currentTimeMillis();
                long elapsed = end - start;
                System.out.println("elapsed "+elapsed);

            }catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}