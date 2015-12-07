package tests;

import com.hazelcast.core.IMap;
import global.Task;

public class Task1 extends Task {

    public String mapName="a";

    private IMap map;
    private String keyPrefix;

    public void setup(){
        map = hazelcastInstance.getMap(mapName);
        keyPrefix = Utils.getUniqueKeyPrefix(hazelcastInstance);
        send("keyPrefix="+keyPrefix);
    }

    public void run() throws InterruptedException {
        while (isRunning()) {
            int i = random.nextInt(3);
            map.put(keyPrefix+i, i);
        }

        send(map.keySet().toString());
    }

}