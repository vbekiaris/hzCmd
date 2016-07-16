package remote.command;

import global.BenchType;
import remote.main.Controler;

import javax.jms.MessageProducer;
import java.io.Serializable;

/**
 * Created by danny on 22/01/2016.
 */
public class RemoveBenchCmd implements Cmd, Serializable{

    private String taskId;

    public RemoveBenchCmd(String taskId){
        this.taskId = taskId;
    }

    public void exicute(Controler c, MessageProducer replyProducer){
        c.removeBench(replyProducer, taskId);
    }

    @Override
    public String toString() {
        return "RemoveBenchCmd{ taskId='" + taskId + '\'' + '}';
    }
}
