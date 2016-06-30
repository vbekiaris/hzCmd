package remote.bench.marker;

import com.codahale.metrics.CsvReporter;
import com.codahale.metrics.MetricRegistry;
import global.AssertionException;
import remote.Utils;
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
            if(bench.isSelfDetermined()){
                selfDeterminedBenchFlatOut(bench);
            }else{
                timeBenchFlatOut(bench, endTime);
            }
        }else{
            if(bench.isSelfDetermined()) {
                selfDeterminedBenchInterval(bench);
            }else{
                timeBenchInterval(bench, endTime);
            }
        }
    }

    private void timeBenchFlatOut(Bench bench, long endTime) throws Exception{
        com.codahale.metrics.Timer timer = metrics.timer(outputFileName);
        while(System.currentTimeMillis() < endTime){
            flatOut(bench, timer);
        }
        metrics.remove(outputFileName);
    }

    private void timeBenchInterval(Bench bench, long endTime) throws Exception {
        com.codahale.metrics.Timer timer = metrics.timer(outputFileName);
        while(System.currentTimeMillis() < endTime){
            interval(bench, timer);
        }
        metrics.remove(outputFileName);
    }

    private void selfDeterminedBenchFlatOut(Bench bench) throws Exception{
        com.codahale.metrics.Timer timer = metrics.timer(outputFileName);
        while(bench.isRunning()){
            flatOut(bench, timer);
        }
        metrics.remove(outputFileName);
    }

    private void selfDeterminedBenchInterval(Bench bench) throws Exception {
        com.codahale.metrics.Timer timer = metrics.timer(outputFileName);
        while(bench.isRunning()){
            interval(bench, timer);
        }
        metrics.remove(outputFileName);
    }



    private void flatOut(Bench bench, com.codahale.metrics.Timer timer) throws Exception{
        com.codahale.metrics.Timer.Context context = timer.time();
        try {
            bench.timeStep();
        }catch (Exception e){
            Utils.recordeException(e);
            if(!allowException){
                throw e;
            }
            if(e instanceof AssertionException){
                throw e;
            }
        }
        context.stop();
    }

    private void interval(Bench bench, com.codahale.metrics.Timer timer) throws Exception{
        com.codahale.metrics.Timer.Context context = timer.time();
        long start = System.nanoTime();
        try {
            bench.timeStep();
        }catch (Exception e){
            Utils.recordeException(e);
            if(!allowException){
                throw e;
            }
            if(e instanceof AssertionException){
                throw e;
            }
        }
        context.stop();

        long nextStart = start + expectedIntervalNanos;
        while( System.nanoTime() < nextStart ) {
            //busy-waiting until the next expected interval
        }
    }
}