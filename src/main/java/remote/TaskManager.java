package remote;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class TaskManager {

    //TODO map of thread and method name count
    private Map<String, TaskClazz> tasks = new HashMap();
    private ExecutorService executorService =  Executors.newCachedThreadPool();

    private Object vendorObject;


    public TaskManager(Object vendorObject) {
        this.vendorObject = vendorObject;
    }


    public void loadClass(String taskId, String className) throws Exception{
        if(tasks.containsKey(taskId)){
           throw new IllegalStateException(Controler.ID+" taskId "+taskId+" all ready loaded");
        }
        TaskClazz taskManager = new TaskClazz(taskId, className, vendorObject);
        tasks.put(taskManager.getId(), taskManager);
    }

    public void invokeNonBlocking(int threadCount, String function, String taskId) throws NoSuchMethodException {
        Collection<TaskClazz> tasks = selectTasks(taskId);

        for(TaskClazz t : tasks) {
            try {
                t.setMethod(function);
            }catch (NoSuchMethodException e){
                throw new NoSuchMethodException(Controler.ID+" No methods invoked. task "+t.getId() + " No Such Method " + function);
            }
        }

        for(TaskClazz t : tasks) {
            for (int i = 0; i <threadCount; i++) {
                executorService.submit(t);
            }
        }
    }

    public void stop(String taskId){
        for(TaskClazz t : selectTasks(taskId)) {
            t.stop();
        }
    }

    private Collection<TaskClazz> selectTasks(String taskId){
        if ("*".equals(taskId) ){
            return tasks.values();
        }
        return Arrays.asList( tasks.get(taskId) );
    }

    @Override
    public String toString() {

        String str = new String();

        for(TaskClazz t : tasks.values()){
            str+=t+", ";
        }

        return "TaskManager{" +
                ", tasks=" + str +
                '}';
    }

}