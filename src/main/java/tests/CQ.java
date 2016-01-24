package tests;

import com.hazelcast.core.IMap;
import hz.HzTask;
import remote.Task;
import java.io.*;

public class CQ extends HzTask {

    public LatencyListener latency = new LatencyListener();
    public int keyDomain = 1000;

    public CQ(){}

    public void addListener() throws InterruptedException {
        IMap map = hzInstance.getMap("a");
        map.addEntryListener(latency, new EvenKey(), true) ;
    }

    public void resetHisto() throws InterruptedException {
        latency.h.reset();
    }


    public void put() throws InterruptedException {
        IMap map = hzInstance.getMap("a");

        while (isRunning()) {
            int k = random.nextInt(keyDomain);
            long now = System.currentTimeMillis();
            Data d = new Data();
            d.now = now;
            map.put(k, d);
        }
    }

    public void printLatency() throws Exception {

        PrintStream p = new PrintStream(new File ("latencys.txt"));
        latency.h.outputPercentileDistribution(p, 1.0);


        /*
        FileWriter fw = new FileWriter(new File ("latencys2.txt"));
        BufferedWriter bw = new BufferedWriter(fw);

        for (int i = 0; i < latency.latencys.length; i++) {
            bw.write(i+", "+latency.latencys[i]);
            bw.newLine();
        }
        bw.flush();
        */

    }
}