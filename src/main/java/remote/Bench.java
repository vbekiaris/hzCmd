package remote;

import com.codahale.metrics.CsvReporter;
import com.codahale.metrics.MetricRegistry;

import java.io.File;
import java.util.concurrent.TimeUnit;

public abstract class Bench extends Task{

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
        exicute(warmupSec, setTitle()+"Warmup");
    }

    public void run() throws InterruptedException {
        exicute(durationSec, setTitle());
    }


    private void exicute(int seconds, String title){
        csvReporter = CsvReporter.forRegistry(metrics).build(new File(System.getProperty("user.dir")) );
        csvReporter.start(reportSecondsInterval, TimeUnit.SECONDS);
        com.codahale.metrics.Timer timer = metrics.timer(title);
        com.codahale.metrics.Timer.Context context;

        long startTime = System.currentTimeMillis();
        long endTime = startTime + (seconds * 1000);
        while(System.currentTimeMillis() < endTime){
            context = timer.time();
            timeStep();
            context.stop();
        }
        csvReporter.stop();
        metrics.remove(title);
    }


    public abstract void timeStep( );
}