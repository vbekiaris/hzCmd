package remote.bench.marker;

import remote.BenchType;
import remote.bench.Bench;

import java.util.concurrent.TimeUnit;


public abstract class BenchMarker {

    protected static boolean stopAtException=false;
    protected static long expectedIntervalNanos = TimeUnit.MILLISECONDS.toNanos(1);
    protected static String outputFileName;
    protected static int durationSeconds;

    public void setDurationSeconds(int seconds){
        durationSeconds=seconds;
    }

    public void setStopAtException(boolean stop){stopAtException = stop;}

    public void preBench(String fileName){ }

    public void postBench(){ }

    public abstract void bench(Bench bench) throws Exception;

    public abstract void benchInterval(Bench bench) throws Exception;
}