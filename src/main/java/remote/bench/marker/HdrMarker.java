package remote.bench.marker;

import org.HdrHistogram.ConcurrentHistogram;
import org.HdrHistogram.Histogram;
import remote.bench.Bench;

import java.io.*;
import java.util.concurrent.TimeUnit;


public class HdrMarker extends BenchMarker {

    private static Histogram histogram = new ConcurrentHistogram(TimeUnit.SECONDS.toNanos(30), 3);

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
        if(expectedIntervalNanos==0){
            benchFlatOut(bench);
        }else{
            benchInterval(bench);
        }
    }

    private void benchFlatOut(Bench bench) throws Exception{
        long startTime = System.currentTimeMillis();
        long endTime = startTime + (durationSeconds * 1000);
        while(System.currentTimeMillis() < endTime){

            long start = System.nanoTime();
            try {
                bench.timeStep();
            }catch (Exception e){
                if(stopAtException){
                    System.out.println(e);
                    throw e;
                }
            }
            long end = System.nanoTime();
            histogram.recordValue(end-start);
        }
    }

    private void benchInterval(Bench bench) throws Exception{
        long startTime = System.currentTimeMillis();
        long endTime = startTime + (durationSeconds * 1000);
        while(System.currentTimeMillis() < endTime){

            long start = System.nanoTime();
            try {
                bench.timeStep();
            }catch (Exception e){
                if(stopAtException){
                    throw e;
                }
            }
            long end = System.nanoTime();

            long elapsedNanos = end-start;
            long nextStart = start + expectedIntervalNanos;
            histogram.recordValueWithExpectedInterval(elapsedNanos, expectedIntervalNanos);

            while( System.nanoTime() < nextStart ) {
                //busy-waiting until the next expected interval
            }
        }
    }
}