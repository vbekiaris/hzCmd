package local.bench;

import global.BenchType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BenchMark implements Serializable {

    private List<FieldValue> attributes = new ArrayList();

    private String id;

    private String clazz;

    private String drivers="Member";

    private String threads="1";

    private String warmupSec="30";

    private String durationSec="60";

    private String types="Metrics";

    private boolean allowException=false;

    private String intervalNanos="0";

    public BenchMark(){}

    public void setDrivers(String drivers) {
        this.drivers = drivers;
    }

    public void setDurationSecs(String durationSec) {
        this.durationSec = durationSec;
    }

    public void setIntervalByMsec(String intervalMillis) {
        intervalNanos=intervalMillis;
    }

    public void setType(String types) { this.types = types; }

    public void setWarmupSec(String warmupSec) {
        this.warmupSec = warmupSec;
    }

    public void setThreads(String threads) {
        this.threads = threads;
    }


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

    public List<BenchType> getTypes() {
        List<BenchType> list = new ArrayList();
        for (String t : types.split(",")) {
            list.add(BenchType.valueOf(t));
        }
        return list;
    }


    public int[] getWarmupSec() {
        String[] numberStrs = warmupSec.split(",");
        int[] numbers = new int[numberStrs.length];
        for(int i=0; i<numberStrs.length; i++) {
            numbers[i] = Integer.parseInt(numberStrs[i]);
        }
        return numbers;
    }

    public int[] getDurationSec() {
        String[] numberStrs = durationSec.split(",");
        int[] numbers = new int[numberStrs.length];
        for(int i=0; i<numberStrs.length; i++) {
            numbers[i] = Integer.parseInt(numberStrs[i]);
        }
        return numbers;
    }

    public long[] getIntervalNanos() {
        String[] numberStrs = intervalNanos.split(",");
        long[] numbers = new long[numberStrs.length];
        for(int i=0; i<numberStrs.length; i++) {
            numbers[i] = TimeUnit.MILLISECONDS.toNanos(Long.parseLong(numberStrs[i]));
        }
        return numbers;
    }


    public boolean getAllowException() {
        return allowException;
    }

    public void setAllowException(boolean allowException) {
        this.allowException = allowException;
    }
}
