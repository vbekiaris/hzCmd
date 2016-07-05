package remote.command;

import remote.main.Controler;

import javax.jms.MessageProducer;

public interface Cmd {
    void exicute(Controler c, MessageProducer replyProducer) throws Exception;
}
