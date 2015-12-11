package tests;

import com.hazelcast.core.IMap;
import global.Task;

import static tests.Utils.sleepSeconds;

public class Task1 extends Task {

    public String mapName="a";
    public int keyDomain = 1000000;
    private IMap map;

    public void setup(){
        map = hazelcastInstance.getMap(mapName);
        //send("map Name="+map.getName());
    }

    public void put() throws InterruptedException {
        //send("starting put");
        while (isRunning()) {
            int k = random.nextInt(keyDomain);
            map.put(k, k);
        }
    }

    public void get() throws InterruptedException {
        //send("starting get");
        while (isRunning()) {
            int k = random.nextInt(keyDomain);
            Object obj = map.get(k);
        }
    }

    public void size() throws InterruptedException {
        //send("starting size");
        while (isRunning()) {
            send("map="+map.getName()+" size="+map.size());
            sleepSeconds(15);
        }
    }

    public void throwException() throws Exception {
        //send("starting throwException");
        sleepSeconds(10);
        throw new Exception("Test Exception");
    }
}