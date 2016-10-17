package remote.command;

import remote.main.Controler;

import javax.jms.MessageProducer;
import java.io.Serializable;

/**
 * Created by danny on 22/01/2016.
 */
public class SubmitBenchCmd implements Cmd, Serializable{

    private String taskId;
    private long seconds;

    public SubmitBenchCmd(String taskId, long seconds){
        this.taskId = taskId;
        this.seconds = seconds;
    }

    public void exicute(Controler c, MessageProducer replyProducer){
        c.submitBench(replyProducer, taskId, seconds);
    }

    @Override
    public String toString() {
        return "RunBenchCmd{" +
                "seconds=" + seconds +
                ", taskId='" + taskId + '\'' +
                '}';
    }
}
