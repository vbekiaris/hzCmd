package remote.command;

import remote.Controler;

import java.io.Serializable;

/**
 * Created by danny on 22/01/2016.
 */
public class InvokeCmd implements Cmd, Serializable{

    private int threadCount;
    private String function;
    private String taskId;

    public void exicute(Controler c){
        c.invokeNonBlocking(threadCount, function, taskId);
    }
}
