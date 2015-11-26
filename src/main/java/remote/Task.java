package remote;

import com.hazelcast.core.HazelcastInstance;
import global.Execute;
import global.Test;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;

import static remote.Utils.instantiate;
import static remote.Utils.sendBack;
import static remote.Utils.sendBackError;

public class Task implements Callable<Object> {

    Test test;
    private Method method;
    private Execute execute;

    public Task(String id, String clasz, HazelcastInstance hazelcastInstance){
        test = instantiate(clasz, Test.class);
        test.setId(id);
        test.setHazelcastInstance(hazelcastInstance);
    }

    public void setMethod(String function){
        try {
            method = test.getClass().getMethod(function);
            execute = method.getAnnotation(Execute.class);
        } catch (NoSuchMethodException e) {
            method = null;
            execute = null;
        }
    }

    public void stop(){
        test.setRunning(false);
    }

    public Object call() {
        try {
            if (method!=null) {
                System.out.println(infoStart());
                sendBack(infoStart());
                test.setRunning(true);
                method.invoke(test);
                sendBack(infoStop());
                System.out.println(infoStop());
            }
        }catch (Exception e){
            onException(e);
        }
        return null;
    }

    public Object bench() {
        try {
            if (method!=null) {
                System.out.println("bench "+infoStart());
                test.setRunning(true);
                test.runBench(method);
                System.out.println("bench "+infoStop());
            }
        }catch (Exception e){
            onException(e);
        }
        return null;
    }

    public boolean willExicute(){
        if (method==null)
            return false;
        if (executeOnMember() && executeOnClient())
            return true;

        HazelcastInstance hz = test.getHazelcastInstance();

        return Utils.isMember(hz) == executeOnMember() || Utils.isClient(hz) == executeOnClient();
    }

    protected boolean executeOnClient(){
        if(execute==null || execute.where() == Execute.On.ALL)
            return true;
        return execute.where() == Execute.On.CLIENT;
    }

    protected boolean executeOnMember(){
        if(execute==null || execute.where() == Execute.On.ALL)
            return true;
        return execute.where() == Execute.On.MEMBER;
    }

    protected boolean executeOnAll(){
        if(execute==null)
            return true;
        return execute.with() == Execute.On.ALL;
    }

    protected boolean executeOnOne(){
        if(execute==null)
            return false;
        return execute.with() == Execute.On.ONE;
    }

    protected String getId(){
        return test.getId();
    }

    private void onException(Exception e){
        System.out.println(infoString()+" "+e);
        sendBackError(infoString() + " " + e);
    }

    protected String infoStart(){ return infoString() + " initilize"; }

    protected String infoStop(){ return infoString() +  " stop"; }

    protected String infoString(){
        return test.getId()+" " + test.getClass().getSimpleName() + " " + method.getName();
    }


    @Override
    public String toString() {
        return "Task{" +
                "test=" + test +
                ", method=" + method +
                ", execute=" + execute +
                '}';
    }
}