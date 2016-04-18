package remote.command.bench;

import remote.Controler;
import remote.command.Cmd;

import java.io.Serializable;

/**
 * Created by danny on 22/01/2016.
 */
public class CleanUpCmd implements Cmd, Serializable{

    private String taskId;

    public CleanUpCmd(String taskId){
        this.taskId = taskId;
    }

    public void exicute(Controler c){
        c.cleanup(taskId);
    }

}
