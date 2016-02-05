package remote;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class TaskManager {

    private Map<String, TaskRunner> tasks = new HashMap();
    private ExecutorService executorService =  Executors.newCachedThreadPool();

    private Object vendorObject;

    public TaskManager(Object vendorObject) {
        this.vendorObject = vendorObject;
    }

    public void loadClass(String taskId, String className) throws Exception{
        if(tasks.containsKey(taskId)){
           throw new IllegalStateException(Controler.ID+" taskId "+taskId+" all ready loaded");
        }
        TaskRunner task = new TaskRunner(taskId, className, vendorObject);
        tasks.put(task.getId(), task);
    }

    public void setField(String taskId, String field, String value) throws Exception{
        for(TaskRunner t : getMatchingTasks(taskId) ) {
            t.setField(field, value);
        }
    }

    public void invokeAsync(int threadCount, String function, String taskId) throws NoSuchMethodException {
        Collection<TaskRunner> tasks = getMatchingTasks(taskId);

        for(TaskRunner t : tasks) {
            try {
                t.setMethod(function);
            }catch (NoSuchMethodException e){
                throw new NoSuchMethodException(Controler.ID+" No methods invoked. task "+t.getId() + " No Such Method " + function);
            }
        }

        for(TaskRunner t : tasks) {
            for (int i = 0; i <threadCount; i++) {
                executorService.submit(t);
            }
        }
    }

    public void invokeSync(int threadCount, String function, String taskId) throws NoSuchMethodException, InterruptedException {
        Collection<TaskRunner> tasks = getMatchingTasks(taskId);

        for(TaskRunner t : tasks) {
            try {
                t.setMethod(function);
            }catch (NoSuchMethodException e){
                throw new NoSuchMethodException(Controler.ID+" No methods invoked. task "+t.getId() + " No Such Method " + function);
            }
        }

        ExecutorService executor = Executors.newFixedThreadPool(tasks.size()*threadCount);

        for(TaskRunner t : tasks) {
            for (int i = 0; i <threadCount; i++) {
                executor.submit(t);
            }
        }
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.DAYS);
    }


    public void stop(String taskId){
        for(TaskRunner t : getMatchingTasks(taskId)) {
            t.stop();
        }
    }

    private List<TaskRunner> getMatchingTasks(String taskId) {
        List<TaskRunner> matching = new ArrayList();
        for( TaskRunner t : tasks.values()){
            if ( t.getId().matches(taskId) ){
                matching.add(t);
            }
        }
        return matching;
    }

    @Override
    public String toString() {

        String str = new String();

        for(TaskRunner t : tasks.values()){
            str+=t+", ";
        }

        return "TaskManager{" +
                ", tasks=" + str +
                '}';
    }

}