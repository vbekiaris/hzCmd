package remote.command;

import remote.Controler;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * Created by danny on 22/01/2016.
 */
public class InvokeSyncCmd implements Cmd, Serializable{

    private int threadCount;
    private String function;
    private String taskId;
    private long timeOut;
    private TimeUnit timeUnit;

    public InvokeSyncCmd(int threadCount, String function, String taskId){
        this.threadCount = threadCount;
        this.function = function;
        this.taskId = taskId;
        timeOut=3;
        timeUnit = TimeUnit.DAYS;
    }

    public void exicute(Controler c){
        //c.invokeSync(threadCount, function, taskId, timeOut, timeUnit);
    }

    @Override
    public String toString() {
        return "InvokeSyncCmd{" +
                "function='" + function + '\'' +
                ", threadCount=" + threadCount +
                ", taskId='" + taskId + '\'' +
                '}';
    }
}
