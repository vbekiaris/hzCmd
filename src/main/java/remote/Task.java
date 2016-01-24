package remote;

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

    public String getJvmID() {
        return jvmID;
    }

    public void setJvmID(String jvmID) {
        this.jvmID = jvmID;
    }


    @Override
    public String toString() {
        return jvmID +" "+ taskID +" "+ this.getClass().getName()+" ";
    }
}