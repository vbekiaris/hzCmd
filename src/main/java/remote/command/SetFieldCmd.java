package remote.command;

import remote.Controler;

import javax.jms.MessageProducer;
import java.io.Serializable;

/**
 * Created by danny on 22/01/2016.
 */
public class SetFieldCmd implements Cmd, Serializable{

    private String taskId;
    private String field;
    private String value;

    public SetFieldCmd(String taskId, String field, String value){
        this.taskId = taskId;
        this.field = field;
        this.value = value;
    }

    public void exicute(Controler c, MessageProducer replyProducer){
        c.setField(taskId, field, value);
    }

    @Override
    public String toString() {
        return "SetFieldCmd{" +
                "field='" + field + '\'' +
                ", taskId='" + taskId + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
