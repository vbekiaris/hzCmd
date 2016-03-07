package remote;

import com.codahale.metrics.CsvReporter;
import com.codahale.metrics.MetricRegistry;
import net.openhft.chronicle.Chronicle;
import net.openhft.chronicle.ChronicleQueueBuilder;
import net.openhft.chronicle.ExcerptAppender;
import org.HdrHistogram.ConcurrentHistogram;
import org.HdrHistogram.Histogram;

import java.io.*;
import java.util.concurrent.TimeUnit;

public abstract class Bench extends Task{

    public BenchType benchType = BenchType.Metrics;
    public String title;

    public int warmupSec=30;
    public int durationSec = 60;
    public int reportSecondsInterval=5;
    private CsvReporter csvReporter;
    private MetricRegistry metrics = new MetricRegistry();

    public void init(){
        setup();
    }

    public abstract void setup();

    public void warmup(){
        runBench(benchType, warmupSec, title + "_warmup-"+warmupSec+"Sec");
    }

    public void run() {
        runBench(benchType, durationSec, title + "_bench-"+durationSec+"Sec");
    }

    private void runBench(BenchType type, int seconds, String title){
        switch (type){
            case Metrics:
                benchMetric(seconds, title);
                break;
            case Hdr:
                benchHdr(seconds, title);
                break;
            case Counter:
                counter(seconds, title);
                break;
            case Recorder:
                recorder(seconds, title);
                break;
            default :
                throw new RuntimeException("BenchType invalid");
        }
    }

    private void counter(int seconds, String outputTitle){
        long count=0;
        long startTime = System.currentTimeMillis();
        long endTime = startTime + (seconds * 1000);

        while(System.currentTimeMillis() < endTime){
            timeStep();
            count++;
        }
        try {
            PrintWriter output = new PrintWriter(new FileWriter(outputTitle+".txt", true));
            output.println(count);
            output.flush();
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void recorder(int seconds, String title) {
        String basePath = System.getProperty("user.dir");
        Chronicle chronicle;
        ExcerptAppender appender;

        try {
            chronicle = ChronicleQueueBuilder.indexed(basePath + "/" + title).build();
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
                //appender.flush();
                appender.finish();
            }

            appender.close();
            chronicle.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void benchMetric(int seconds, String metricsCsvtitle){
        csvReporter = CsvReporter.forRegistry(metrics).build(new File(System.getProperty("user.dir")) );
        com.codahale.metrics.Timer timer = metrics.timer(metricsCsvtitle);
        com.codahale.metrics.Timer.Context context;

        long startTime = System.currentTimeMillis();
        long endTime = startTime + (seconds * 1000);

        csvReporter.start(reportSecondsInterval, TimeUnit.SECONDS);
        while(System.currentTimeMillis() < endTime){
            context = timer.time();
            timeStep();
            context.stop();
        }
        csvReporter.stop();
        metrics.remove(metricsCsvtitle);
    }


    private void benchHdr(int seconds, String title){

        Histogram histogram = new ConcurrentHistogram(TimeUnit.SECONDS.toNanos(30), 3);

        histogram.reset();
        long startTime = System.currentTimeMillis();
        long endTime = startTime + (seconds * 1000);

        histogram.setStartTimeStamp(System.nanoTime());
        while(System.currentTimeMillis() < endTime){

            long start = System.nanoTime();
            timeStep();
            long end = System.nanoTime();
            histogram.recordValue(end-start);
        }
        histogram.setEndTimeStamp(System.nanoTime());

        PrintStream ps;
        try {
            ps = new PrintStream(new FileOutputStream(title, true));
            histogram.outputPercentileDistribution(ps, 1000.0);

            ps = new PrintStream(new FileOutputStream(title+"-CO", true));
            histogram = histogram.copyCorrectedForCoordinatedOmission(1);
            histogram.outputPercentileDistribution(ps, 1000.0);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public abstract void timeStep( );
}