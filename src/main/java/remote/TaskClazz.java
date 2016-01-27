package remote;

import jms.MQ;

import javax.jms.JMSException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
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

    public void setField(String fieldName, String value) throws NoSuchMethodException, NoSuchFieldException, IllegalAccessException, InvocationTargetException {
        Class clazz = task.getClass();
        Field field = clazz.getField(fieldName);
        Object fieldType = field.getType();

        if(fieldType instanceof String){
            field.set(task, value);
        }else{
            //Method parseMethod = field.get(task).getClass().getMethod("valueOf", new Class[]{String.class});
            Method parseMethod = fieldType.getClass().getMethod("valueOf", new Class[]{String.class});

            field.set(task, parseMethod.invoke(field, value));
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
                String start = infoStart();
                System.out.println(start);
                MQ.sendObj(Controler.EVENTQ, start);
                task.setRunning(true);
                method.invoke(task);
                String stop = infoStop();
                MQ.sendObj(Controler.EVENTQ, stop);
                System.out.println(stop);
            }
        }catch (Exception e){
            e.printStackTrace();
            e.printStackTrace(Controler.exceptionWrite);
            try {
                MQ.sendObj(Controler.EVENTQ, new Exception(infoString(), e.getCause()) );
            } catch (JMSException jmsE) {
                jmsE.printStackTrace();
            }
            throw new RuntimeException(e);
        }
        return null;
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