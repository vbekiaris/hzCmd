package remote.command;

import remote.main.Controler;
import remote.command.Cmd;

import javax.jms.MessageProducer;
import java.io.Serializable;

/**
 * Created by danny on 22/01/2016.
 */
public class WarmupCmd implements Cmd, Serializable{

    private String taskId;
    private int seconds ;

    public WarmupCmd(String taskId, int seconds){
        this.taskId = taskId;
        this.seconds = seconds;
    }

    public void exicute(Controler c, MessageProducer replyProducer){

        c.warmupBench(replyProducer, taskId, seconds);
    }

    @Override
    public String toString() {
        return "WarmupCmd{" +
                "seconds=" + seconds +
                ", taskId='" + taskId + '\'' +
                '}';
    }
}
