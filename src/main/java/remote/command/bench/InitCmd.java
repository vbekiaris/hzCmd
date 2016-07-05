package remote.command.bench;

import remote.main.Controler;
import remote.command.Cmd;

import javax.jms.MessageProducer;
import java.io.Serializable;

/**
 * Created by danny on 22/01/2016.
 */
public class InitCmd implements Cmd, Serializable{

    private String taskId;

    public InitCmd(String taskId){
        this.taskId = taskId;
    }

    public void exicute(Controler c, MessageProducer replyProducer){

        c.initBench(replyProducer, taskId);
    }

    @Override
    public String toString() {
        return "InitCmd{" +
                "taskId='" + taskId + '\'' +
                '}';
    }
}
