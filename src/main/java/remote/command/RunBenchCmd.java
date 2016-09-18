package remote.command;

import remote.main.Controler;
import remote.command.Cmd;

import javax.jms.MessageProducer;
import java.io.Serializable;

/**
 * Created by danny on 22/01/2016.
 */
public class RunBenchCmd implements Cmd, Serializable{

    private String taskId;
    private long seconds;

    public RunBenchCmd(String taskId, long seconds){
        this.taskId = taskId;
        this.seconds = seconds;
    }

    public void exicute(Controler c, MessageProducer replyProducer){
        c.runBench(replyProducer, taskId, seconds);
    }

    @Override
    public String toString() {
        return "RunBenchCmd{" +
                "seconds=" + seconds +
                ", taskId='" + taskId + '\'' +
                '}';
    }
}
