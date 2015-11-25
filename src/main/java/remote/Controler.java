package remote;

import com.hazelcast.core.HazelcastInstance;
import global.Args;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

import static global.Bash.killAllJava;
import static global.Utils.sleepMilli;
import static remote.Utils.isMember;
import static remote.Utils.sendBack;
import static remote.Utils.sendBackError;

public class Controler{

    public static String homeUser;
    public static String homeIp;
    public static String homeCwd = System.getProperty("user.dir");
    public static String homeInfile;

    private BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private ExecutorService executorService =  Executors.newCachedThreadPool();

    private Map<String, Task> tasks = new HashMap();
    private HazelcastInstance hazelcastInstance;


    public Controler(HazelcastInstance hazelcastInstance){
        this.hazelcastInstance = hazelcastInstance;
    }

    public void run(){
        System.out.println("CWD="+homeCwd);
        try{
            while (true){
                String line=in.readLine();
                if (line!=null){
                    String[] words = line.split("\\s+");
                    Args arg = Args.valueOf(words[0]);
                    switch (arg) {
                        case exit:
                            killAllJava();
                            System.exit(0);

                        case load:
                            loadClass(words[1], words[2]);
                            break;

                        case invoke:
                            invoke(words[1]);
                            break;

                        case bench:
                            //bench(words[1]);
                            break;

                        case stop:
                            stop();
                            break;

                        case homeUser:
                            homeUser=words[1];
                            break;
                        case homeIp:
                            homeIp=words[1];
                            break;
                        case homeInfile:
                            homeInfile=words[1];
                            break;
                        case clean:
                            homeInfile=words[1];
                            break;
                        case info:
                            info();
                            break;
                    }
                }else {
                    sleepMilli(500);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void submit(String function) throws InterruptedException, ExecutionException {
        for(Task t : tasks.values()){
            t.setMethod(function);
            if(t.willExicute())
                executorService.submit(t);
        }
    }

    private void invoke(String function) throws InterruptedException, ExecutionException {
        Collection<Task> running = new ArrayList();

        for(Task t : tasks.values()){
            t.setMethod(function);
            if(t.willExicute())
                running.add(t);
        }
        executorService.invokeAll(running);
        sendBack("all "+function+" finished");
    }

    private void stop(){
        for(Task t : tasks.values()){
            t.stop();
        }
    }

    private void loadClass(String taskId, String className){

        if(tasks.containsKey(taskId)){
            sendBackError("duplicate task ID "+taskId);
            return;
        }

        Task task = new Task(taskId, className, hazelcastInstance);
        tasks.put(task.getId(), task);
    }

    private void info(){
        String info = new String();
        if(isMember(hazelcastInstance)){
            info+="Member"+"\n";
        }else{
            info+="Client"+"\n";
        }

        info += homeUser+"\n";
        info += homeIp+"\n";
        info += homeCwd+"\n";
        info += homeInfile+"\n";

        for(Task t : tasks.values()){
            info += t.toString();
        }
        sendBack(info);
    }
}