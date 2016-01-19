package global;

import com.hazelcast.core.HazelcastInstance;

import java.util.Random;

public class Task {

    protected String jvmID;
    protected String taskID;
    protected Random random = new Random();
    protected HazelcastInstance hazelcastInstance;
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

    public void setHazelcastInstance(HazelcastInstance hazelcastInstance) {
        this.hazelcastInstance = hazelcastInstance;
    }

    public HazelcastInstance getHazelcastInstance(){
        return hazelcastInstance;
    }


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