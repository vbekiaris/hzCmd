package tests;

import com.hazelcast.core.IMap;
import hz.HzBench;

public class HzMapPutBench extends HzBench {

    public String name = "a";
    private IMap map;
    public int keyDomain = 10000;
    public int valueSize = 10;

    public byte[] value;

    public void setup(){
        value = new byte[valueSize];
        map = hzInstance.getMap(name);
    }

    @Override
    public void timeStep() {
        int k = random.nextInt(keyDomain);
        map.put(k, value);
    }
}