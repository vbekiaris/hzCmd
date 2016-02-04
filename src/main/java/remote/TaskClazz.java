package remote;

import jms.MQ;

import javax.jms.JMSException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static remote.Utils.instantiate;
import static remote.Utils.recordeException;

public class TaskClazz implements Callable<Object> {

    private AtomicInteger threadCount = new AtomicInteger();
    private final String id;
    private Task task;
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
        Class<?> type = field.getType();

        if(type.isAssignableFrom(String.class) ){
            field.set(task, value);
        }else{
            Method parseMethod = field.get(task).getClass().getMethod("valueOf", new Class[]{String.class});
            field.set(task, parseMethod.invoke(field, value));
        }
    }

    public void stop(){
        task.setRunning(false);
    }

    //TODO maybe a string builder
    public Object call() {
        int count = threadCount.getAndIncrement();
        long start = System.currentTimeMillis();
        String info = this.toString() + "thread "+ count + "started at "+start;
        try {
            if (method!=null) {
                System.out.println(info);
                MQ.sendObj(Controler.EVENTQ, info);

                task.setRunning(true);
                method.invoke(task);
                long end = System.currentTimeMillis();

                info+=" ended at "+end+" elapsed time "+ (start-end);
                System.out.println(info);
                MQ.sendObj(Controler.EVENTQ, info);
            }
        }catch (Exception e){
            long end = System.currentTimeMillis();
            info+=" Exception at "+end+" elapsed time "+ (start-end);
            System.out.println(info);

            recordeException(e);
            try {
                MQ.sendObj(Controler.EVENTQ, e);
            } catch (JMSException jmsE) {
                jmsE.printStackTrace();
            }
            throw new RuntimeException(e);
        }finally {
            threadCount.getAndDecrement();
        }
        return null;
    }


    protected String getId(){
        return task.getTaskID();
    }

    @Override
    public String toString() {
        return Controler.ID+" "+task.getTaskID()+" "+task.getClass().getName()+" "+targetFunction;
    }
}