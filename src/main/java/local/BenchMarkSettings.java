package local;

import remote.bench.BenchType;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * Created by danny on 04/03/2016.
 */
public class BenchMarkSettings implements Serializable {

    private String drivers="Member";

    private String threads="64";

    private int warmupSec=30;

    private int durationSec=60;

    private int repeatCount=1;

    private String types="Metrics";

    private long intervalNanos= TimeUnit.MILLISECONDS.toNanos(1);

    public BenchMarkSettings(){}

    public void setDrivers(String drivers) {
        this.drivers = drivers;
    }

    public void setDurationSec(int durationSec) {
        this.durationSec = durationSec;
    }

    public void setIntervalByMsec(long intervalMillis) {
        intervalNanos= TimeUnit.MILLISECONDS.toNanos(intervalMillis);
    }

    public void setType(String types) { this.types = types; }

    public void setWarmupSec(int warmupSec) {
        this.warmupSec = warmupSec;
    }

    public void setThreads(String threads) {
        this.threads = threads;
    }

    public void setRepeatCount(int repeatCount) { this.repeatCount = repeatCount; }


    public String[] getDrivers() {
        return drivers.split(",");
    }

    public int[] getThreads() {

        String[] numberStrs = threads.split(",");
        int[] numbers = new int[numberStrs.length];
        for(int i=0; i<numberStrs.length; i++) {
            numbers[i] = Integer.parseInt(numberStrs[i]);
        }
        return numbers;
    }

    public String[] getTypes() {
        return types.split(",");
    }

    public boolean benchTypesContains(BenchType type){

        for (String name : getTypes()) {
            BenchType t = BenchType.valueOf(name);

            if(t.equals(type)){
                return true;
            }
        }
        return false;
    }


    public int getWarmupSec() {
        return warmupSec;
    }

    public int getDurationSec() {
        return durationSec;
    }

    public String getIntervalNanos() {
        return Long.toString(intervalNanos);
    }

    public int repeatCount(){ return repeatCount; }

}
