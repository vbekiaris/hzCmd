package remote.bench;

import global.BenchType;
import remote.main.Controler;
import remote.main.Utils;
import remote.bench.marker.BenchMarker;
import remote.bench.marker.HdrMarker;
import remote.bench.marker.MetricsMarker;

import java.util.ArrayList;
import java.util.List;

import static remote.main.Utils.instantiate;

public class BenchContainer {

    private String id;
    private String clazzName;
    private Object vendorObject;
    private String outputFileName;
    private BenchMarker benchMarker;
    private List<Bench> benchObjs = new ArrayList();

    public BenchContainer(Object vendorObject, String id, String clazz){
        this.vendorObject=vendorObject;
        this.id=id;
        this.clazzName=clazz;
    }

    public String getId(){return id;}

    public void writeMetaData(String metaData) {
        benchMarker.writeMeataDataFile(outputFileName, metaData);
    }

    public int getThreadCount() {
        return benchObjs.size();
    }

    public void createBenchObjects(int count) throws Exception {

        for(int i=0; i<count; i++){
            Bench benchObj = instantiate(clazzName, Bench.class);
            benchObjs.add(benchObj);
        }

        for (Bench bench : benchObjs) {
            bench.setVendorObject(vendorObject);
        }
    }

    public List<BenchThread> getThreads( ) {
        List<BenchThread> threads = new ArrayList();
        for (int i = 0; i < benchObjs.size(); i++) {
            Bench bench = benchObjs.get(i);
            threads.add(new BenchThread(benchMarker, bench, id, clazzName, i) );
        }
        return threads;
    }

    public void setField(String field, String value) throws Exception{
        for (Bench bench : benchObjs) {
            Utils.setField(field, value, bench);
        }
    }

    public void init() throws Exception {
        for (Bench bench : benchObjs) {
            bench.init();
        }
    }

    public void postPhase(){
        for (Bench bench : benchObjs) {
            bench.postPhase();
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

    public void preBench(String fileNamePostFix) {
        benchMarker.preBench(outputFileName + "-" + fileNamePostFix);
    }

    public void postBench() {
        benchMarker.postBench();
    }

    public void setDuration(int seconds){
        benchMarker.setDurationSeconds(seconds);
    }

    public void setRunning(boolean running){
        for (Bench bench : benchObjs) {
            bench.setRunning(running);
        }
    }

    @Override
    public String toString() {
        return "BenchContainer{" +
                "benchMarker=" + benchMarker +
                ", id='" + id + '\'' +
                ", clazzName='" + clazzName + '\'' +
                ", vendorObject=" + vendorObject +
                ", outputFileName='" + outputFileName + '\'' +
                ", benchObjs=" + benchObjs +
                '}';
    }
}