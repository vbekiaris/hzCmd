package remote.bench.marker;

import remote.bench.Bench;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public abstract class BenchMarker {

    protected boolean throwException =false;
    protected long expectedIntervalNanos=0;
    protected String outputFileName;
    protected int durationSeconds;

    public BenchMarker(long expectedIntervalNanos, boolean throwException){
        this.expectedIntervalNanos = expectedIntervalNanos;
        this.throwException = throwException;
    }

    public void setDurationSeconds(int seconds){
        durationSeconds=seconds;
    }

    public void preBench(String fileName){ }

    public void postBench(){ }

    public abstract void bench(Bench bench) throws Exception;

    public void writeMeataDataFile(String outFile, String metaData){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(outFile+"-meta.txt"));
            bw.write(metaData);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}