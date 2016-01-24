package remote.command;

import remote.Controler;

import java.io.Serializable;

/**
 * Created by danny on 22/01/2016.
 */
public class LoadCmd implements Cmd, Serializable{

    private String taskId;
    private String className;

    public LoadCmd(String taskId, String className){
        this.taskId = taskId;
        this.className = className;
    }

    public void exicute(Controler c){
        c.load(taskId, className);
    }
}
