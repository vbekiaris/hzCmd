package remote.bench;

import java.util.HashMap;
import java.util.Random;

public class TestBench implements Bench{

    private Random random = new Random();
    private HashMap map;

    public int valueSize=0;

    public void init() {
        map = new HashMap();
        System.out.println(random +"  valueSize="+valueSize);
    }

    public void timeStep()throws Exception{
        int x = random.nextInt(5);

        switch (x){
            case 0:
                Thread.sleep(1);
                break;
            case 1:
                map.put(random.nextInt(3), 0);
                break;
            case 2:
                break;
            case 3:
                map.remove(random.nextInt(3));
                break;
            case 5:
                throw new Exception("test Exception");
        }
    }

    public void cleanup(){
        System.out.println("clean up");
    }
}
