package remote;

import com.codahale.metrics.CsvReporter;
import com.codahale.metrics.MetricRegistry;
import net.openhft.chronicle.Chronicle;
import net.openhft.chronicle.ChronicleQueueBuilder;
import net.openhft.chronicle.ExcerptAppender;
import org.HdrHistogram.ConcurrentHistogram;
import org.HdrHistogram.Histogram;

import javax.jms.JMSException;
import java.io.*;
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
                    histogram.outputPercentileDistribution(ps, 1000.0);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                break;
        }
    }


    public void warmup(){
        runBench(benchType, warmupSec, fileName+"_warmup-"+warmupSec+"Sec");
    }

    public void run() {
        runBench(benchType, durationSec, fileName+"_bench-"+durationSec+"Sec");
    }

    private void runBench(BenchType type, int seconds, String title){

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(title+".meta"));
            bw.write(metaData);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        switch (type){
            case Metrics:
                benchMetric(seconds, title);
                break;
            case Hdr:
                benchHdr(seconds, title);
                break;
            case Recorder:
                recorder(seconds, title);
                break;
            default :
                throw new RuntimeException("BenchType invalid");
        }
    }


    private void benchMetric(int seconds, String metricsCsvtitle){
        com.codahale.metrics.Timer timer = metrics.timer(metricsCsvtitle);
        com.codahale.metrics.Timer.Context context;

        long startTime = System.currentTimeMillis();
        long endTime = startTime + (seconds * 1000);
        while(System.currentTimeMillis() < endTime){
            context = timer.time();
            timeStep();
            context.stop();
        }
        metrics.remove(metricsCsvtitle);
    }


    private void benchHdr(int seconds, String title){

        long startTime = System.currentTimeMillis();
        long endTime = startTime + (seconds * 1000);
        while(System.currentTimeMillis() < endTime){

            long start = System.nanoTime();
            timeStep();
            long end = System.nanoTime();
            histogram.recordValue(end-start);
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

    public abstract void timeStep( );
}