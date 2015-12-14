package tests;

import com.hazelcast.core.IMap;
import global.Task;
import java.io.*;

import static global.Utils.sleepSeconds;

public class CQ extends Task {

    public LatencyListener latency = new LatencyListener();
    public String mapName="a";
    public int keyDomain = Integer.MAX_VALUE;
    private IMap map;

    public CQ(){}

    public void addListener() throws InterruptedException {
        map = hazelcastInstance.getMap(mapName);
        latency = new LatencyListener();
        map.addEntryListener(latency, new EvenKey(), true) ;
        send("addlistener");
        while (isRunning()) {
            sleepSeconds(3);
        }
    }

    public void put() throws InterruptedException {
        map = hazelcastInstance.getMap(mapName);
        while (isRunning()) {
            int k = random.nextInt(keyDomain);
            long now = System.nanoTime();
            map.put(k, now);
            send("put "+k);
        }
    }

    public void printLatency() throws Exception {

        PrintStream p = new PrintStream(new File ("latencys.txt"));
        latency.h.outputPercentileDistribution(p, 1.0);


        FileWriter fw = new FileWriter(new File ("latencys2.txt"));
        BufferedWriter bw = new BufferedWriter(fw);

        for (int i = 0; i < latency.latencys.length; i++) {
            bw.write(i+", "+latency.latencys[i]);
            bw.newLine();
        }
        bw.flush();

    }

}