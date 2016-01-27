package tests;

import com.hazelcast.core.IMap;
import hz.HzBench;

public class HzMapGetBench extends HzBench {

    public String title="default";
    public String name = "a";
    private IMap map;
    public int keyDomain = 10000;
    public int valueSize = 10;

    public byte[] value;

    public void setup(){
        value = new byte[valueSize];
        map = hzInstance.getMap(name);

        for(int i =0 ;i<keyDomain; i++){
            map.put(i, value);
        }
    }

    @Override
    public String setTitle() { return title; }

    @Override
    public void timeStep() {
        int k = random.nextInt(keyDomain);
        map.get(k);
    }
}