package remote.command.bench;

import remote.main.Controler;
import remote.command.Cmd;

import javax.jms.MessageProducer;
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

    public void exicute(Controler c, MessageProducer replyProducer){

        c.load(replyProducer, taskId, className);
    }

    @Override
    public String toString() {
        return "LoadCmd{" +
                "className='" + className + '\'' +
                ", taskId='" + taskId + '\'' +
                '}';
    }
}
