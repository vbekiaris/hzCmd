package remote.command.bench;

import remote.Controler;
import remote.command.Cmd;

import java.io.Serializable;

/**
 * Created by danny on 22/01/2016.
 */
public class StopAtExceptionCmd implements Cmd, Serializable{

    private String taskId;
    private boolean stop;

    public StopAtExceptionCmd(String taskId, boolean stop){
        this.taskId = taskId;
        this.stop = stop;
    }

    public void exicute(Controler c){
        c.setStopAtException(taskId, stop);
    }

    @Override
    public String toString() {
        return "StopAtExceptionCmd{" +
                "stop=" + stop +
                ", taskId='" + taskId + '\'' +
                '}';
    }
}
