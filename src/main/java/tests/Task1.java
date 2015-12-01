package tests;

import com.hazelcast.core.IMap;
import global.Execute;
import global.Task;

public class Task1 extends Task {

    public String mapName="a";

    private IMap map;
    private String keyPrefix;

    @Execute(where=Execute.On.ALL, with=Execute.On.ALL)
    public void setup(){
        map = hazelcastInstance.getMap(mapName);
        System.out.println(map.getName()+" = "+map.size());
        keyPrefix = Utils.getUniqueKeyPrefix(hazelcastInstance);
    }

    @Execute(where=Execute.On.MEMBER, with=Execute.On.ALL)
    public void run() throws InterruptedException {
        while (isRunning()) {
            int i = random.nextInt(3);
            map.put(keyPrefix+i, i);
        }

        send(map.keySet().toString());
    }

    @Execute(where=Execute.On.ALL, with=Execute.On.ONE)
    public void tearDown(){
        System.out.println("TEST1 "+map.keySet());
    }

}