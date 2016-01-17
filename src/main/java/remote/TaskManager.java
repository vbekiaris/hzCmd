package remote;

import com.hazelcast.core.HazelcastInstance;
import global.Args;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static remote.Utils.sendBack;
import static remote.Utils.sendBAckException;

public class TaskManager {

    //TODO map of thread and method name count
    private Map<String, TaskClazz> tasks = new HashMap();
    private ExecutorService executorService =  Executors.newCachedThreadPool();

    private HazelcastInstance hazelcastInstance;


    public TaskManager(HazelcastInstance hazelcastInstance) {
        this.hazelcastInstance = hazelcastInstance;
    }


    public void loadClass(String taskId, String className){

        if(tasks.containsKey(taskId)){
            sendBAckException(Args.ID + "=" + Controler.ID + " duplicate task ID " + taskId);
            return;
        }

        try{
            TaskClazz taskManager = new TaskClazz(taskId, className, hazelcastInstance);
            tasks.put(taskManager.getId(), taskManager);
            sendBack(Args.ID +"="+Controler.ID+" "+taskId+" "+className+" loaded");
        } catch (Exception e){
            sendBAckException(Args.ID + "=" + Controler.ID + " taskId=" + taskId + " className=" + className + " " + e.getMessage());
        }
    }


    public void invokeNonBlocking(int threadCount, String function, String taskId) {
        if ("*".equals(taskId) ){
            for(TaskClazz t : tasks.values()){
                submitNonBlocking(t, function, threadCount);
            }
        }else{
            TaskClazz t = tasks.get(taskId);
            submitNonBlocking(t, function, threadCount);
        }
    }

    private void submitNonBlocking(TaskClazz t, String function, int threadCount){
        t.setMethod(function);
        for (int i = 0; i <threadCount; i++) {
            executorService.submit(t);
        }
    }


    public void stop(String taskId){
        if ("*".equals(taskId) ){
            for(TaskClazz t : tasks.values()) {
                t.stop();
            }
        }else{
            TaskClazz t = tasks.get(taskId);
            t.stop();
        }
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