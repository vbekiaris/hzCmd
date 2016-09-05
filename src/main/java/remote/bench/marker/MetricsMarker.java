package remote.bench.marker;

import com.codahale.metrics.CsvReporter;
import com.codahale.metrics.MetricRegistry;
import global.AssertionException;
import remote.main.Utils;
import remote.bench.Bench;

import java.io.File;
import java.util.concurrent.TimeUnit;


public class MetricsMarker extends BenchMarker {

    private static MetricRegistry metrics = new MetricRegistry();
    private int reportSecondsInterval = 5;
    private CsvReporter csvReporter;

    public MetricsMarker(long expectedIntervalNanos, boolean allowException ){
        super(expectedIntervalNanos, allowException);
    }

    public void preBench(String fileName){
        outputFileName=fileName;
        File file = new File(System.getProperty("user.dir"));
        csvReporter = CsvReporter.forRegistry(metrics).build(file);
        csvReporter.start(reportSecondsInterval, TimeUnit.SECONDS);
    }

    public void postBench(){
        csvReporter.stop();
    }

    public void bench(Bench bench) throws Exception{
        long startTime = System.currentTimeMillis();
        long endTime = startTime + (durationSeconds * 1000);
        if(expectedIntervalNanos==0){
            timeBenchFlatOut(bench, endTime);
        }else{
            timeBenchInterval(bench, endTime);
        }
    }

    private void timeBenchFlatOut(Bench bench, long endTime) throws Exception{
        com.codahale.metrics.Timer timer = metrics.timer(outputFileName);
        while(System.currentTimeMillis() < endTime && bench.isRunning()){
            flatOut(bench, timer);
        }
        metrics.remove(outputFileName);
    }

    private void timeBenchInterval(Bench bench, long endTime) throws Exception {
        com.codahale.metrics.Timer timer = metrics.timer(outputFileName);
        while(System.currentTimeMillis() < endTime && bench.isRunning()){
            interval(bench, timer);
        }
        metrics.remove(outputFileName);
    }

    private void flatOut(Bench bench, com.codahale.metrics.Timer timer) throws Exception{
        try {
            com.codahale.metrics.Timer.Context context = timer.time();
            bench.timeStep();
            context.stop();
        }catch (Exception e){
            handelException(bench, e);
        }
    }

    private void interval(Bench bench, com.codahale.metrics.Timer timer) throws Exception{
        try {
            com.codahale.metrics.Timer.Context context = timer.time();
            long start = System.nanoTime();
            bench.timeStep();
            context.stop();

            long nextStart = start + expectedIntervalNanos;
            while( System.nanoTime() < nextStart ) {
                //busy-waiting until the next expected interval
            }
        }catch (Exception e){
            handelException(bench, e);
        }
    }

}