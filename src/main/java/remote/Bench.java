package remote;

import com.codahale.metrics.CsvReporter;
import com.codahale.metrics.MetricRegistry;
import net.openhft.chronicle.Chronicle;
import net.openhft.chronicle.ChronicleQueueBuilder;
import net.openhft.chronicle.ExcerptAppender;
import org.HdrHistogram.ConcurrentHistogram;
import org.HdrHistogram.Histogram;
import remote.bench.BenchType;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public abstract class Bench extends Task{

    public Histogram histogram = new ConcurrentHistogram(TimeUnit.SECONDS.toNanos(30), 3);

    public long expectedIntervalNanos = TimeUnit.MILLISECONDS.toNanos(1);

    public BenchType benchType = BenchType.Metrics;
    public String metaData;
    public String fileName;

    public int warmupSec=30;
    public int durationSec = 60;
    public int reportSecondsInterval=5;

    private CsvReporter csvReporter;
    private MetricRegistry metrics = new MetricRegistry();

    public void init(){
        setup();
    }

    public abstract void setup();

    public void preBench(){
        switch (benchType){
            case Metrics:
            case MetricsInterval:
                File file = new File(System.getProperty("user.dir"));
                csvReporter = CsvReporter.forRegistry(metrics).build(file);
                csvReporter.start(reportSecondsInterval, TimeUnit.SECONDS);
                break;
            case Hdr :
            case HdrInterval:
                histogram.reset();
                histogram.setStartTimeStamp(System.nanoTime());
                break;
        }
    }

    public void postBench(){
        switch (benchType){
            case Metrics:
            case MetricsInterval:
                csvReporter.stop();
                break;
            case Hdr:
            case HdrInterval:
                histogram.setEndTimeStamp(System.nanoTime());
                try {
                    PrintStream ps = new PrintStream(new FileOutputStream(fileName+".hgrm", true));
                    histogram.outputPercentileDistribution(ps, 1000000.0);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    public void warmup(){
        runBench(benchType, warmupSec);
    }

    public void run() {
        runBench(benchType, durationSec);
    }

    private void runBench(BenchType type, int seconds){

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileName+".meta.txt"));
            bw.write(metaData);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        switch (type){
            case Metrics:
                benchMetric(seconds);
                break;
            case MetricsInterval:
                benchMetricAtExpectedInterval(seconds);
                break;
            case Hdr:
                benchHdr(seconds);
                break;
            case HdrInterval:
                benchHdrAtExpectedInterval(seconds);
                break;
            case Recorder:
                recorder(seconds);
                break;
            default :
                throw new RuntimeException("BenchType invalid");
        }
    }


    private void benchMetric(int seconds){
        com.codahale.metrics.Timer timer = metrics.timer(fileName);
        com.codahale.metrics.Timer.Context context;

        long startTime = System.currentTimeMillis();
        long endTime = startTime + (seconds * 1000);
        while(System.currentTimeMillis() < endTime){
            context = timer.time();
            timeStep();
            context.stop();
        }
        metrics.remove(fileName);
    }

    private void benchMetricAtExpectedInterval(int seconds) {
        com.codahale.metrics.Timer timer = metrics.timer(fileName);
        com.codahale.metrics.Timer.Context context;

        long startTime = System.currentTimeMillis();
        long endTime = startTime + (seconds * 1000);
        while(System.currentTimeMillis() < endTime){
            context = timer.time();
            long start = System.nanoTime();
            timeStep();
            context.stop();

            long nextStart = start + expectedIntervalNanos;
            while( System.nanoTime() < nextStart ) {
                //busy-waiting until the next expected interval
            }
        }
        metrics.remove(fileName);
    }


    private void benchHdr(int seconds){

        long startTime = System.currentTimeMillis();
        long endTime = startTime + (seconds * 1000);
        while(System.currentTimeMillis() < endTime){

            long start = System.nanoTime();
            timeStep();
            long end = System.nanoTime();
            histogram.recordValue(end-start);
        }
    }

    private void benchHdrAtExpectedInterval(int seconds) {

        long startTime = System.currentTimeMillis();
        long endTime = startTime + (seconds * 1000);

        while(System.currentTimeMillis() < endTime){

            long start = System.nanoTime();
            timeStep();
            long end = System.nanoTime();
            long elapsedNanos = end-start;
            long nextStart = start + expectedIntervalNanos;
            histogram.recordValueWithExpectedInterval(elapsedNanos, expectedIntervalNanos);

            while( System.nanoTime() < nextStart ) {
                //busy-waiting until the next expected interval
            }
        }
    }


    private void recorder(int seconds) {
        String basePath = System.getProperty("user.dir");
        Chronicle chronicle;
        ExcerptAppender appender;

        try {
            chronicle = ChronicleQueueBuilder.indexed(basePath + "/" +fileName+".chr").build();
            appender = chronicle.createAppender();

            long startTime = System.currentTimeMillis();
            long endTime = startTime + (seconds * 1000);

            while(System.currentTimeMillis() < endTime){
                long start = System.nanoTime();
                timeStep();
                long end = System.nanoTime();

                appender.startExcerpt();
                appender.writeLong(start);
                appender.writeLong(end);
                appender.writeLong(end - start);
                appender.finish();
            }

            appender.close();
            chronicle.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public abstract void timeStep( );


    public static void main(String[] args) throws InterruptedException, IOException {

        Map<String, Integer> benchTypeCountMap = new HashMap();

        String[] s = {"a","b"};

        for (String benchType : s) {
            benchTypeCountMap.put(benchType, new Integer(0));
        }

        for (String benchType : s) {
            Integer c = benchTypeCountMap.get(benchType);

            for(int i=0; i<3; i++){
                c++;
                System.out.println("s="+benchType+" c="+c);
            }
            benchTypeCountMap.put(benchType, c);
        }

        for (String benchType : s) {
            Integer c = benchTypeCountMap.get(benchType);

            for(int i=0; i<3; i++){
                c++;

                System.out.println("s="+benchType+" c="+c);
            }
        }
    }

}