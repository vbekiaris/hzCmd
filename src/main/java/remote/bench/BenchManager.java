package remote.bench;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BenchManager {

    private Object vendorObject;
    private Map<String, BenchContainer> benchs = new HashMap();

    public BenchManager(Object vendorObject){
        this.vendorObject=vendorObject;
    }

    private List<BenchContainer> getMatchingBenchContainers(String id) {
        List<BenchContainer> matching = new ArrayList();

        for(BenchContainer bench : benchs.values()){
            if ( bench.getId().matches(id) ){
                matching.add(bench);
            }
        }
        return matching;
    }


    public void loadClass(String id, String clazz) throws Exception{
        BenchContainer bc = new BenchContainer(vendorObject, id, clazz);
        benchs.put(id, bc);
    }

    public void setThreadCount(String id, int threadCount) throws Exception {
        for (BenchContainer benchContainer : getMatchingBenchContainers(id)) {
            benchContainer.setThreadCount(threadCount);
        }
    }

    public void writeMetaData(String id, String metaData) {
        for (BenchContainer benchContainer : getMatchingBenchContainers(id)) {
            benchContainer.writeMetaData(metaData);
        }
    }

    public void setField(String id, String field, String value) throws Exception{
        for (BenchContainer benchContainer : getMatchingBenchContainers(id)) {
            benchContainer.setField(field, value);
        }
    }

    public void init(String id){
        for (BenchContainer benchContainer : getMatchingBenchContainers(id)) {
            benchContainer.init();
        }
    }

    public void cleanUp(String id){
        for (BenchContainer benchContainer : getMatchingBenchContainers(id)) {
            benchContainer.cleanUp();
        }
    }

    public void setBenchType(String id, BenchType type, long expectedIntervalNanos, boolean stop, String outFile){
        for (BenchContainer benchContainer : getMatchingBenchContainers(id)) {
            benchContainer.setBenchType(type, expectedIntervalNanos, stop, outFile);
        }
    }

    public void warmup(String id, int sec) throws InterruptedException {
        for (BenchContainer benchContainer : getMatchingBenchContainers(id)) {
            benchContainer.warmup(sec);
        }
    }

    public void bench(String id, int sec) throws InterruptedException {
        for (BenchContainer benchContainer : getMatchingBenchContainers(id)) {
            benchContainer.bench(sec);
        }
    }

}