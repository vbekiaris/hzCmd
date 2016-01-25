package tests;

import com.codahale.metrics.CsvReporter;
import com.codahale.metrics.MetricRegistry;
import com.hazelcast.core.IMap;
import com.hazelcast.core.OperationTimeoutException;
import hz.HzTask;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static tests.Utils.sleepSeconds;

public class Task1 extends HzTask {

    public String name ="a";
    private IMap map;
    public int keyDomain = 10000;

    private MetricRegistry metrics = new MetricRegistry();
    public int reportSecondsInterval=5;


    public void setup(){
        map = hzInstance.getMap(name);
        CsvReporter csvReporter = CsvReporter.forRegistry(metrics).build(new File(System.getProperty("user.dir")) );
        csvReporter.start(reportSecondsInterval, TimeUnit.SECONDS);
    }

    public void put() throws InterruptedException {
        com.codahale.metrics.Timer timer = metrics.timer("putTimer");
        com.codahale.metrics.Timer.Context context;

        while (isRunning()) {
            int k = random.nextInt(keyDomain);
            context = timer.time();
            map.put(k, k);
            context.stop();
        }
    }

    public void get() throws InterruptedException {
        while (isRunning()) {
            int k = random.nextInt(keyDomain);
            Object obj = map.get(k);
        }
    }

    public void size() throws InterruptedException {
        while (isRunning()) {
            send("map="+map.getName()+" size="+map.size());
            sleepSeconds(15);
        }
    }
}