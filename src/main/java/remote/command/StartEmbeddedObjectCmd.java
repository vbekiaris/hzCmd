package remote.command;

import remote.main.Controler;

import javax.jms.MessageProducer;
import java.io.Serializable;

/**
 * Created by danny on 22/01/2016.
 */
public class StartEmbeddedObjectCmd implements Cmd, Serializable{
    public void exicute(Controler c, MessageProducer replyProducer) throws Exception {
        c.startEmbeddedObject(replyProducer);
    }
}
