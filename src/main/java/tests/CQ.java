package tests;

import com.hazelcast.core.IMap;
import com.hazelcast.query.Predicate;
import global.Task;

import java.io.*;
import java.util.Map;

import static tests.Utils.sleepSeconds;

public class CQ extends Task {

    public LatencyListener latency = new LatencyListener();
    public String mapName="a";
    public int keyDomain = Integer.MAX_VALUE;
    private IMap map;

    public void addListener() throws InterruptedException {
        map = hazelcastInstance.getMap(mapName);
        latency = new LatencyListener();
        map.addEntryListener(latency, new EvenKey(), true) ;
    }

    public void put() throws InterruptedException {
        map = hazelcastInstance.getMap(mapName);
        while (isRunning()) {
            int k = random.nextInt(keyDomain);
            long now = System.nanoTime();
            map.put(k, now);
        }
    }

    public void printLatency() throws Exception {

        PrintStream p = new PrintStream(new File ("latencys.txt"));

        /*
        latency.h.outputPercentileDistribution(p, 1.0);

        for (int i = 0; i < latency.latencys.length; i++) {
            bw.write(i+", "+latency.latencys[i]);
            bw.newLine();
        }
        bw.flush();
        */
    }

}