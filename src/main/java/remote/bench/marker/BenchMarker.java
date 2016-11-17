package remote.bench.marker;

import remote.bench.Bench;
import remote.main.Utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.TimeUnit;


public abstract class BenchMarker {

    protected boolean recordException = true;
    protected long expectedIntervalNanos=0;
    protected String outputFileName;
    protected long durationSeconds;

    public BenchMarker(long expectedIntervalNanos, boolean recordException){
        this.expectedIntervalNanos = expectedIntervalNanos;
        this.recordException = recordException;
    }

    public void setDurationSeconds(long seconds){
        durationSeconds=seconds;
        if(durationSeconds==0){
            durationSeconds = TimeUnit.DAYS.toSeconds(365);
        }
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

    protected void handelException(Bench bench, Exception e) throws Exception {

        if(bench.ignore()!=null){
            for (Class aClass : bench.ignore()) {
                if(aClass.isInstance(e)){
                    return;
                }
            }
        }

        if(recordException){
            Utils.recordeException(e);
        }

        throw e;
    }
}