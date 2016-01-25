package remote.command;

import remote.Controler;

import java.io.Serializable;

/**
 * Created by danny on 22/01/2016.
 */
public class InvokeAsyncCmd implements Cmd, Serializable{

    private int threadCount;
    private String function;
    private String taskId;

    public InvokeAsyncCmd(int threadCount, String function, String taskId){
        this.threadCount = threadCount;
        this.function = function;
        this.taskId = taskId;
    }

    public void exicute(Controler c){
        c.invokeNonBlocking(threadCount, function, taskId);
    }

    @Override
    public String toString() {
        return "InvokeAsyncCmd{" +
                "function='" + function + '\'' +
                ", threadCount=" + threadCount +
                ", taskId='" + taskId + '\'' +
                '}';
    }
}
