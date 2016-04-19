package remote.bench.marker;

import com.codahale.metrics.CsvReporter;
import com.codahale.metrics.MetricRegistry;
import remote.Utils;
import remote.bench.Bench;

import java.io.File;
import java.util.concurrent.TimeUnit;


public class MetricsMarker extends BenchMarker {

    private static MetricRegistry metrics = new MetricRegistry();
    private int reportSecondsInterval = 5;
    private CsvReporter csvReporter;

    public MetricsMarker(long expectedIntervalNanos, boolean stop ){
        super(expectedIntervalNanos, stop);
    }

    public void preBench(String fileName){
        outputFileName=fileName;
        File file = new File(System.getProperty("user.dir"));
        csvReporter = CsvReporter.forRegistry(metrics).build(file);
        csvReporter.start(reportSecondsInterval, TimeUnit.SECONDS);
    }

    public void postBench(){
        System.out.println("postBench");
        csvReporter.stop();
    }

    public void bench(Bench bench) throws Exception{
        if(expectedIntervalNanos==0){
            benchFlatOut(bench);
        }else{
            benchInterval(bench);
        }
    }

    private void benchFlatOut(Bench bench) throws Exception{
        com.codahale.metrics.Timer timer = metrics.timer(outputFileName);
        com.codahale.metrics.Timer.Context context;

        long startTime = System.currentTimeMillis();
        long endTime = startTime + (durationSeconds * 1000);
        while(System.currentTimeMillis() < endTime){
            context = timer.time();

            try {
                bench.timeStep();
            }catch (Exception e){
                Utils.recordeException(e);
                if(stopAtException){
                    throw e;
                }
            }

            context.stop();
        }
        metrics.remove(outputFileName);
    }

    private void benchInterval(Bench bench) throws Exception {
        com.codahale.metrics.Timer timer = metrics.timer(outputFileName);
        com.codahale.metrics.Timer.Context context;

        long startTime = System.currentTimeMillis();
        long endTime = startTime + (durationSeconds * 1000);
        while(System.currentTimeMillis() < endTime){
            context = timer.time();
            long start = System.nanoTime();

            try {
                bench.timeStep();
            }catch (Exception e){
                Utils.recordeException(e);
                if(stopAtException){
                    throw e;
                }
            }

            context.stop();

            long nextStart = start + expectedIntervalNanos;
            while( System.nanoTime() < nextStart ) {
                //busy-waiting until the next expected interval
            }
        }
        metrics.remove(outputFileName);
    }

}