package remote.command;

import remote.main.Controler;

import javax.jms.MessageProducer;
import java.io.Serializable;

/**
 * Created by danny on 22/01/2016.
 */
public class StopBenchCmd implements Cmd, Serializable{

    private String taskId;

    public StopBenchCmd(String taskId){
        this.taskId = taskId;
    }

    public void exicute(Controler c, MessageProducer replyProducer){
        c.stopBench(replyProducer, taskId);
    }

    @Override
    public String toString() {
        return "StopBenchCmd{" +
                "taskId='" + taskId + '\'' +
                '}';
    }
}
