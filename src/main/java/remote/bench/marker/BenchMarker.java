package remote.bench.marker;

import global.AssertionException;
import remote.bench.Bench;
import remote.main.Utils;

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

    //if the seconds duration is 0  then set to max int,  if default seconds valuse is allways 0
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

    protected void handelException(Bench bench, Exception e) throws Exception {
        if(bench.ignore()!=null){
            for (Class aClass : bench.ignore()) {
                if(aClass.isInstance(e)){
                    return;
                }
            }
        }

        if(throwException){
            Utils.recordeException(e);
            throw e;
        }
        if(e instanceof AssertionException){
            throw e;
        }
    }
}