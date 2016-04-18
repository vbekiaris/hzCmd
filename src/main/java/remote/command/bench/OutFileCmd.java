package remote.command.bench;

import remote.Controler;
import remote.command.Cmd;

import java.io.Serializable;

/**
 * Created by danny on 22/01/2016.
 */
public class OutFileCmd implements Cmd, Serializable{

    private String taskId;
    private String filename;

    public OutFileCmd(String taskId, String filename){
        this.taskId = taskId;
        this.filename = filename;
    }

    public void exicute(Controler c){
        c.setOutputFile(taskId, filename);
    }

}
