package remote;

import com.hazelcast.core.HazelcastInstance;
import global.Execute;
import global.Task;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;

import static global.Utils.exceptionStacktraceToString;
import static remote.Utils.instantiate;
import static remote.Utils.sendBack;
import static remote.Utils.sendBackError;


public class TaskClazz implements Callable<Object> {

    private final String id;
    Task task;
    private String targetFunction;
    private Method method;
    private Execute execute;


    public TaskClazz(String id, String clasz, HazelcastInstance hazelcastInstance){
        this.id=id;
        task = instantiate(clasz, Task.class);
        task.setId(id);
        task.setHazelcastInstance(hazelcastInstance);
    }

    public void setMethod(String function){
        targetFunction = function;
        try {
            method = task.getClass().getMethod(function);
            execute = method.getAnnotation(Execute.class);
        } catch (NoSuchMethodException e) {
            method = null;
            execute = null;
            onException(e);
        }
    }

    public void stop(){
        task.setRunning(false);
    }


    //TODO KEEP track of number of threads exicuting a task method in a map
    //key taskId-method value thread count
    public Object call() {
        try {
            if (method!=null) {
                System.out.println(infoStart());
                sendBack(infoStart());
                task.setRunning(true);
                method.invoke(task);
                sendBack(infoStop());
                System.out.println(infoStop());
            }
        }catch (Exception e){
            onException(e);
        }
        return null;
    }

    //TODO write exception out to file
    private void onException(Exception e){
        e.printStackTrace();
        sendBackError(infoString() + " " +  exceptionStacktraceToString(e) );
    }


    public boolean willExicute(){
        if (method==null)
            return false;
        if (executeOnMember() && executeOnClient())
            return true;

        HazelcastInstance hz = task.getHazelcastInstance();

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
        return task.getId();
    }


    protected String infoStart(){ return infoString() + " started"; }

    protected String infoStop(){ return infoString() +  " stopped"; }

    protected String infoString(){
        return Controler.ID+" "+ task.getId()+" "+ task.getClass().getSimpleName()+" "+targetFunction;
    }


    @Override
    public String toString() {
        return "TaskClazz{" +
                "task=" + task +
                ", method=" + method +
                ", execute=" + execute +
                '}';
    }
}