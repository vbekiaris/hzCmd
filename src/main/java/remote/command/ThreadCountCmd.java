package remote.command;

import remote.main.Controler;
import remote.command.Cmd;

import javax.jms.MessageProducer;
import java.io.Serializable;

/**
 * Created by danny on 22/01/2016.
 */
public class ThreadCountCmd implements Cmd, Serializable{

    private String taskId;
    private int threadCount;

    public ThreadCountCmd(String taskId, int threadCount){
        this.taskId = taskId;
        this.threadCount = threadCount;
    }

    public void exicute(Controler c, MessageProducer replyProducer){

        c.setThreadCount(replyProducer, taskId, threadCount);
    }

    @Override
    public String toString() {
        return "ThreadCountCmd{" +
                "taskId='" + taskId + '\'' +
                ", threadCount=" + threadCount +
                '}';
    }
}
