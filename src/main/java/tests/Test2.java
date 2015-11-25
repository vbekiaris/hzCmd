package tests;

import com.hazelcast.core.IMap;
import global.Execute;
import global.Test;

public class Test2 extends Test {

    public String mapName="z";
    private IMap map;
    private String keyPrefix;

    @Execute(where=Execute.On.ALL, with=Execute.On.ALL)
    public void setup(){
        System.out.println(hazelcastInstance.getName());
        map = hazelcastInstance.getMap(mapName);
        System.out.println(map.getName()+" = "+map.size());
        keyPrefix = Utils.getUniqueKeyPrefix(hazelcastInstance);
        send(this.getClass().getName()+": hi from setup");
    }

    @Execute(where=Execute.On.MEMBER, with=Execute.On.ALL)
    public void run() throws InterruptedException {
        while (isRunning()) {
            int i = random.nextInt(4);
            map.put(keyPrefix+i, i);
        }
    }

    @Execute(where=Execute.On.CLIENT, with=Execute.On.ALL)
    public void run2() throws InterruptedException {
        while (isRunning()) {
            int i = random.nextInt(4);
            map.put(keyPrefix+i, i);
        }
    }

    @Execute(where=Execute.On.MEMBER, with=Execute.On.ONE)
    public void tearDown(){
        System.out.println("TEST2 "+map.keySet());
    }
}