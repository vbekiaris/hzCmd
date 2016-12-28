package remote.bench.marker;

import com.codahale.metrics.MetricFilter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.graphite.Graphite;
import com.codahale.metrics.graphite.GraphiteReporter;
import global.Args;
import remote.bench.Bench;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;


public class GraphiteMarker extends BenchMarker {

    private static MetricRegistry metrics = new MetricRegistry();
    private int reportSecondsInterval = 5;
    private GraphiteReporter reporter;
    private String graphiteServer = "10.212.1.107";
    private int graphitePort = 2003;

    public GraphiteMarker(long expectedIntervalNanos, boolean recordException){
        super(expectedIntervalNanos, recordException);
    }

    public void preBench(String fileName){
        outputFileName=fileName;

        final Graphite graphite = new Graphite(new InetSocketAddress(graphiteServer, graphitePort));
        reporter = GraphiteReporter.forRegistry(metrics)
                .prefixedWith( System.getProperty(Args.ID.name()))
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .filter(MetricFilter.ALL)
                .build(graphite);
        reporter.start(reportSecondsInterval, TimeUnit.SECONDS);
    }

    public void postBench(){
        reporter.stop();
    }

    public void bench(Bench bench) throws Exception{
        long startTime = System.currentTimeMillis();
        long endTime = startTime + (durationSeconds * 1000);

        com.codahale.metrics.Timer timer = metrics.timer(bench.getClass().getName());
        if(expectedIntervalNanos==0){
            while(System.currentTimeMillis() < endTime && bench.isRunning()){
                flatOut(bench, timer);
            }
        }else{
            while(System.currentTimeMillis() < endTime && bench.isRunning()){
                interval(bench, timer);
            }
        }
        metrics.remove(bench.getClass().getName());
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