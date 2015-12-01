package remote;

import com.hazelcast.core.HazelcastInstance;
import global.Args;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static remote.Utils.sendBackError;

public class TaskManager {
    private Map<String, TaskClazz> tasks = new HashMap();
    private ExecutorService executorService =  Executors.newCachedThreadPool();

    private HazelcastInstance hazelcastInstance;


    public TaskManager(HazelcastInstance hazelcastInstance) {
        this.hazelcastInstance = hazelcastInstance;
    }


    public void loadClass(String taskId, String className){

        if(tasks.containsKey(taskId)){
            sendBackError(Args.ID +"="+Controler.ID +" duplicate task ID "+taskId);
            return;
        }

        try{
            TaskClazz taskManager = new TaskClazz(taskId, className, hazelcastInstance);
            tasks.put(taskManager.getId(), taskManager);
        } catch (Exception e){
            sendBackError(Args.ID +"="+Controler.ID +" taskId=" + taskId + " className=" + className + " " + e.getMessage());
        }
    }


    //TODO in invoke function on task ID or *  invoke asyns not wait for result,
    //TODO stop task by task ID or *,  stopping stop all methods running on task ID
    //
    // invoke * * methodA 5
    // invoke * testId methodA 5

    //invoke member*  * methodA 1
    //invoke member*A * methodA 1
    //invoke member1A * methodA 2

    //invoke member*  testid1 methodA 1
    //invoke member*A testID1 methodA 1
    //invoke member1A testId1 methodA 2

    //invoke client*  * methodA 1
    //invoke client*A * methodA 1
    //invoke client1A * methodB 3

    //invoke client*  testid2 methodA 1
    //invoke client*A testid4 methodA 1
    //invoke client1A testid3  methodB 3


    public void invokeNonBlocking(String taskId, String function, int threadCount) {
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




    public void stop(){
        for(TaskClazz t : tasks.values()){
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