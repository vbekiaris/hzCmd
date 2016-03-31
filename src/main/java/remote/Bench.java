package remote;

import com.codahale.metrics.CsvReporter;
import com.codahale.metrics.MetricRegistry;
import com.google.common.util.concurrent.RateLimiter;
import net.openhft.chronicle.Chronicle;
import net.openhft.chronicle.ChronicleQueueBuilder;
import net.openhft.chronicle.ExcerptAppender;
import org.HdrHistogram.ConcurrentHistogram;
import org.HdrHistogram.Histogram;

import javax.jms.JMSException;
import java.io.*;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public abstract class Bench extends Task{

    public Histogram histogram = new ConcurrentHistogram(TimeUnit.SECONDS.toNanos(30), 3);

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
                File file = new File(System.getProperty("user.dir"));
                csvReporter = CsvReporter.forRegistry(metrics).build(file);
                csvReporter.start(reportSecondsInterval, TimeUnit.SECONDS);
                break;
            case Hdr:
                histogram.reset();
                histogram.setStartTimeStamp(System.nanoTime());
                break;
        }
    }

    public void postBench(){
        switch (benchType){
            case Metrics:
                csvReporter.stop();
                break;
            case Hdr:
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
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileName+".meta"));
            bw.write(metaData);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        switch (type){
            case Metrics:
                benchMetric(seconds);
                break;
            case Hdr:
                benchHdr(seconds);
                //benchHdrAtExpected_1milli_Interval(seconds);
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

    private void benchHdrAtExpected_1milli_Interval(int seconds) {

        long expectedInterval_1Milli_asNanos = 1000000;

        long startTime = System.currentTimeMillis();
        long endTime = startTime + (seconds * 1000);

        while(System.currentTimeMillis() < endTime){

            long start = System.nanoTime();
            timeStep();
            long end = System.nanoTime();
            long elapsedNanos = end-start;
            long nextStart = start + expectedInterval_1Milli_asNanos;
            histogram.recordValueWithExpectedInterval(elapsedNanos, expectedInterval_1Milli_asNanos);

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

}