package global;

import com.codahale.metrics.CsvReporter;
import com.codahale.metrics.MetricRegistry;
import com.hazelcast.core.HazelcastInstance;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.lang.reflect.Method;

public class Test{

    protected String id;
    protected Random random = new Random();
    protected HazelcastInstance hazelcastInstance;
    protected volatile boolean running=true;

    private MetricRegistry metrics = new MetricRegistry();

    private int reportSecondsInterval=10;
    private com.codahale.metrics.Timer timer;

    public void setRunning(boolean running){
        this.running=running;
    }

    public boolean isRunning(){
        return running;
    }

    public void send(String msg) {
        remote.Utils.sendBack(msg);
    }

    public void setHazelcastInstance(HazelcastInstance hazelcastInstance) {
        this.hazelcastInstance = hazelcastInstance;
    }

    public HazelcastInstance getHazelcastInstance(){
        return hazelcastInstance;
    }

    private void setBench (String name){

        String cwd = System.getProperty("user.dir");

        CsvReporter csvReporter = CsvReporter.forRegistry(metrics).build(new File(cwd));
        csvReporter.start(reportSecondsInterval, TimeUnit.SECONDS);

        timer = metrics.timer("timer-"+name);
    }

    final public void runBench(Method method) throws InterruptedException, InvocationTargetException, IllegalAccessException {

        setBench(method.getName());
        com.codahale.metrics.Timer.Context context;
        while (isRunning()) {
            context = timer.time();
            method.invoke(this);
            context.stop();
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Test{" +
                "id=" + id +
                ", class=" + this.getClass().getName() +
                ", running=" + running +
                '}';
    }
}