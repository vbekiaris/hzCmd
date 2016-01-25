package remote;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Task {

    protected String jvmID;
    protected String taskID;
    protected Random random = new Random();
    protected volatile boolean running=false;

    public void setRunning(boolean running){
        this.running=running;
    }

    public boolean isRunning(){
        return running;
    }

    public void send(String msg) {
        //remote.Utils.sendBack(this.toString()+" "+msg);
    }

    public abstract void setVendorObject(Object vendorObject);

    public String getTaskID() {
        return taskID;
    }

    public void setTaskID(String taskID) {
        this.taskID = taskID;
    }

    public void setJvmID(String jvmID) {
        this.jvmID = jvmID;
    }


    public void heapOOM() throws InterruptedException {
        List<byte[]> load = new ArrayList<byte[]>(10000);
        while (isRunning()) {
            byte[] data = new byte[1000+random.nextInt(10000)];
            load.add(data);
        }
    }

    public volatile int spin=0;
    public void spin() throws InterruptedException {
        while (isRunning()) {
            spin=++spin%13;
        }
    }

    public void throwException() throws Exception {
        throw new Exception("Test Exception");
    }

    @Override
    public String toString() {
        return jvmID +" "+ taskID +" "+ this.getClass().getName()+" ";
    }
}