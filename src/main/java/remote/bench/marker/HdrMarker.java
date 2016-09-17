package remote.bench.marker;

import org.HdrHistogram.ConcurrentHistogram;
import org.HdrHistogram.Histogram;
import remote.bench.Bench;

import java.io.*;
import java.util.concurrent.TimeUnit;


public class HdrMarker extends BenchMarker {

    private Histogram histogram = new ConcurrentHistogram(TimeUnit.SECONDS.toNanos(30), 3);

    public HdrMarker(long expectedIntervalNanos, boolean stop){
        super(expectedIntervalNanos, stop);
    }

    public void preBench(String fileName){
        outputFileName = fileName;
        histogram.reset();
        histogram.setStartTimeStamp(System.nanoTime());
    }

    public void postBench(){
        histogram.setEndTimeStamp(System.nanoTime());
        try {
            PrintStream ps = new PrintStream(new FileOutputStream(outputFileName +".hgrm", true));
            histogram.outputPercentileDistribution(ps, 1000000.0);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
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
        while(System.currentTimeMillis() < endTime && bench.isRunning()){
            flatOut(bench);
        }
    }

    private void timeBenchInterval(Bench bench, long endTime) throws Exception{
        while(System.currentTimeMillis() < endTime && bench.isRunning()){
            interval(bench);
        }
    }

    private void flatOut(Bench bench) throws Exception{
        try {

            long start = System.nanoTime();
            bench.timeStep();
            long end = System.nanoTime();
            histogram.recordValue(end-start);

        }catch (Exception e){
            handelException(bench, e);
        }
    }

    private void interval(Bench bench) throws Exception{
        try {
            long start = System.nanoTime();
            bench.timeStep();
            long end = System.nanoTime();
            histogram.recordValueWithExpectedInterval(end-start, expectedIntervalNanos);

            long nextStart = start + expectedIntervalNanos;
            while( System.nanoTime() < nextStart ) {
                //busy-waiting until the next expected interval
            }

        }catch (Exception e){
            handelException(bench, e);
        }
    }
}