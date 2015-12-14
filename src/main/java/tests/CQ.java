package tests;

import com.hazelcast.core.IMap;
import global.Task;
import java.io.*;

import static global.Utils.sleepSeconds;

public class CQ extends Task {

    public LatencyListener latency = new LatencyListener(this);
    public int keyDomain = Integer.MAX_VALUE;

    public CQ(){}

    public void addListener() throws InterruptedException {
        IMap map = hazelcastInstance.getMap("a");
        map.addEntryListener(latency, new EvenKey(), true) ;
    }

    public void put() throws InterruptedException {
        IMap map = hazelcastInstance.getMap("a");

        while (isRunning()) {
            int k = random.nextInt(keyDomain);
            long now = System.nanoTime();
            map.put(k, now);
        }
    }

    public void printLatency() throws Exception {

        PrintStream p = new PrintStream(new File ("latencys.txt"));
        latency.h.outputPercentileDistribution(p, 1000000.0);


        FileWriter fw = new FileWriter(new File ("latencys2.txt"));
        BufferedWriter bw = new BufferedWriter(fw);

        for (int i = 0; i < latency.latencys.length; i++) {
            bw.write(i+", "+latency.latencys[i]);
            bw.newLine();
        }
        bw.flush();
    }
}