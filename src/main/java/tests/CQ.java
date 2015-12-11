package tests;

import com.hazelcast.core.IMap;
import com.hazelcast.query.Predicate;
import global.Task;

import java.util.Map;

import static tests.Utils.sleepSeconds;

public class CQ extends Task {

    public LatencyListener latency = new LatencyListener();
    public String mapName="a";
    public int keyDomain = Integer.MAX_VALUE;
    private IMap map;

    public void setup(){
        map = hazelcastInstance.getMap(mapName);
    }

    public void put() throws InterruptedException {
        while (isRunning()) {
            int k = random.nextInt(keyDomain);
            long now = System.nanoTime();
            map.put(k, now);
        }
    }

    public void addListener() throws InterruptedException {

        map.addEntryListener(latency, new EvenKey(), true) ;

    }


    public void printLatency() throws InterruptedException {

        for (int i = 0; i < latency.latencys.length; i++) {
            System.out.println(i+", "+latency.latencys[i]);
        }

    }



}