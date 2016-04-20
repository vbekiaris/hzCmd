package remote.bench;

import java.util.HashMap;
import java.util.Random;

public class TestBench2 implements Bench{

    private Object obj;
    private Random random = new Random();
    private HashMap map;

    public String theName="";

    public void setVendorObject(Object vendorObject){
        obj=vendorObject;
    }

    public void init() {
        map = new HashMap();
        System.out.println("TestBench2 "+random +"  theName="+theName+"  vendorObject="+obj);
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

    public boolean isSelfDetermined() {
        return false;
    }

    public boolean isRunning() {
        return false;
    }
}
