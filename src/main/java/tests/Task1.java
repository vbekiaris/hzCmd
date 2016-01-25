package tests;

import com.hazelcast.core.IMap;
import hz.HzTask;
import remote.Task;

import java.util.ArrayList;
import java.util.List;

import static tests.Utils.sleepSeconds;

public class Task1 extends HzTask {

    public String mapName="a";
    public int keyDomain = 1000000;
    private IMap map;

    public void setup(){
        map = hzInstance.getMap(mapName);
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
}