package remote.command;

import remote.Controler;

import java.io.Serializable;

/**
 * Created by danny on 22/01/2016.
 */
public class InvokeSyncCmd implements Cmd, Serializable{

    private int threadCount;
    private String function;
    private String taskId;

    public InvokeSyncCmd(int threadCount, String function, String taskId){
        this.threadCount = threadCount;
        this.function = function;
        this.taskId = taskId;
    }

    public void exicute(Controler c){
        c.invokeBlocking(threadCount, function, taskId);
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
