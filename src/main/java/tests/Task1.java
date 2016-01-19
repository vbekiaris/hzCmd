package tests;

import com.hazelcast.core.IMap;
import global.Task;

import java.util.ArrayList;
import java.util.List;

import static tests.Utils.sleepSeconds;

public class Task1 extends Task {

    public String mapName="a";
    public int keyDomain = 1000000;
    private IMap map;

    public void setup(){
        map = hazelcastInstance.getMap(mapName);
    }

    public void put() throws InterruptedException {
        while (isRunning()) {
            int k = random.nextInt(keyDomain);
            map.put(k, k);
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

    public void heapOOM() throws InterruptedException {
        List<byte[]> load = new ArrayList<byte[]>(10000);
        while (isRunning()) {
            byte[] data = new byte[10000];
            load.add(data);
        }
    }

    public void spinn() throws InterruptedException {
        while (isRunning()) { }
    }

    public void throwException() throws Exception {
        sleepSeconds(10);
        throw new Exception("Test Exception");
    }
}