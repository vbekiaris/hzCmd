package remote.bench.marker;

import remote.bench.Bench;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.TimeUnit;


public abstract class BenchMarker {

    protected static boolean stopAtException=true;
    protected static long expectedIntervalNanos=0;
    protected static String outputFileName;
    protected static int durationSeconds;

    public BenchMarker(long expectedIntervalNanos, boolean stop, String outFile){
        this.expectedIntervalNanos = expectedIntervalNanos;
        stopAtException = stop;
        outputFileName = outFile;
    }

    public String getOutputFileName(){
        return outputFileName;
    }

    public void setDurationSeconds(int seconds){
        durationSeconds=seconds;
    }

    public void setStopAtException(boolean stop){stopAtException = stop;}

    public void preBench(String fileName){ }

    public void postBench(){ }

    public abstract void bench(Bench bench) throws Exception;

    public void writeMeataDataFile(String metaData){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(outputFileName+"-meta.txt"));
            bw.write(metaData);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}