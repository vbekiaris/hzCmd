package remote;

import javax.jms.JMSException;
import java.lang.reflect.Method;
import java.util.concurrent.Callable;

import static remote.Utils.instantiate;
import static remote.Utils.sendBAckException;


public class TaskClazz implements Callable<Object> {

    private final String id;
    Task task;
    private String targetFunction;
    private Method method;

    public TaskClazz(String id, String clasz,  Object vendorObject) throws Exception{
        this.id=id;
        task = instantiate(clasz, Task.class);
        task.setJvmID(Controler.ID);
        task.setTaskID(id);
        task.setVendorObject(vendorObject);
    }

    public void setMethod(String function) throws NoSuchMethodException {
        targetFunction = function;
        method = task.getClass().getMethod(function);
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
                //sendBack(infoStart());
                task.setRunning(true);
                method.invoke(task);
                //sendBack(infoStop());
                System.out.println(infoStop());
            }
        }catch (Exception e){
            onException(e);
            throw new RuntimeException( e );
        }
        return null;
    }

    //TODO write exception out to file
    private void onException(Exception e){
        e.printStackTrace();
        try {
            sendBAckException(e);
        } catch (JMSException e1) {
            e1.printStackTrace();
        }
    }


    protected String getId(){
        return task.getTaskID();
    }

    protected String infoStart(){ return infoString() + " started"; }

    protected String infoStop(){ return infoString() +  " stopped"; }

    protected String infoString(){
        return Controler.ID+" "+ task.getTaskID()+" "+ task.getClass().getName()+" "+targetFunction;
    }


    @Override
    public String toString() {
        return "TaskClazz{" +
                "task=" + task +
                ", method=" + method +
                '}';
    }
}