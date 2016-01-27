package remote;

import com.codahale.metrics.CsvReporter;
import com.codahale.metrics.MetricRegistry;
import org.HdrHistogram.ConcurrentHistogram;
import org.HdrHistogram.Histogram;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.concurrent.TimeUnit;

public abstract class Bench extends Task{

    public BenchType benchType = BenchType.Metrics;

    public int warmupSec=30;
    public int durationSec = 60;
    public int reportSecondsInterval=5;
    private CsvReporter csvReporter;
    private MetricRegistry metrics = new MetricRegistry();

    public void init(){
        setup();
    }

    public abstract void setup();

    public abstract String setTitle();

    public void warmup(){
        runBench(benchType, warmupSec, setTitle() + "-Warmup");
    }

    public void run() throws InterruptedException {
        runBench(benchType, durationSec, setTitle());
    }

    private void runBench(BenchType type, int seconds, String title){
        switch (type){
            case Metrics:
                benchMetric(durationSec, title);
                break;
            case Hdr:
                benchHdr(durationSec, title);
                break;
        }
    }

    private void benchMetric(int seconds, String metricsCsvtitle){
        csvReporter = CsvReporter.forRegistry(metrics).build(new File(System.getProperty("user.dir")) );
        csvReporter.start(reportSecondsInterval, TimeUnit.SECONDS);
        com.codahale.metrics.Timer timer = metrics.timer(metricsCsvtitle);
        com.codahale.metrics.Timer.Context context;

        long startTime = System.currentTimeMillis();
        long endTime = startTime + (seconds * 1000);
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
            histogram = histogram.copyCorrectedForCoordinatedOmission(0);
            histogram.outputPercentileDistribution(ps, 1000.0);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public abstract void timeStep( );
}