package remote;

import com.codahale.metrics.CsvReporter;
import com.codahale.metrics.MetricRegistry;

import java.io.File;
import java.util.concurrent.TimeUnit;

public abstract class Bench extends Task{

    public int durationSec = 60;
    public int reportSecondsInterval=5;
    private CsvReporter csvReporter;
    private MetricRegistry metrics = new MetricRegistry();

    public void init(){
        setup();
        csvReporter = CsvReporter.forRegistry(metrics).build(new File(System.getProperty("user.dir")) );
        csvReporter.start(reportSecondsInterval, TimeUnit.SECONDS);
    }

    public abstract void setup();

    public abstract String setTitle();

    public void run() throws InterruptedException {

        com.codahale.metrics.Timer timer = metrics.timer(setTitle());
        com.codahale.metrics.Timer.Context context;

        long startTime = System.currentTimeMillis();
        long endTime = startTime + (durationSec * 1000);
        while(System.currentTimeMillis() < endTime){
            context = timer.time();
            timeStep();
            context.stop();
        }
        csvReporter.stop();
    }
    public abstract void timeStep( );
}