package remote.command;

import remote.main.Controler;

import javax.jms.MessageProducer;
import java.io.Serializable;

/**
 * Created by danny on 22/01/2016.
 */
public class PostPhaseCmd implements Cmd, Serializable{

    private String taskId;

    public PostPhaseCmd(String taskId){
        this.taskId = taskId;
    }

    public void exicute(Controler c, MessageProducer replyProducer){

        c.postPhase(replyProducer, taskId);
    }

    @Override
    public String toString() {
        return "PostPhaseCmd{" +
                "taskId='" + taskId + '\'' +
                '}';
    }
}
