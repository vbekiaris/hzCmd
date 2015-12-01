package global;

import com.hazelcast.core.HazelcastInstance;

import java.util.Random;

public class Task {

    protected String id;
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
        remote.Utils.sendBack(this.toString()+" "+msg);
    }

    public void setHazelcastInstance(HazelcastInstance hazelcastInstance) {
        this.hazelcastInstance = hazelcastInstance;
    }

    public HazelcastInstance getHazelcastInstance(){
        return hazelcastInstance;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Task{" +
                "ID=" + id +
                ", class=" + this.getClass().getName() +
                ", running=" + running +
                '}';
    }
}