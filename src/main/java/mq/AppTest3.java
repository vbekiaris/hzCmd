package mq;

import javax.jms.JMSException;

public class AppTest3 {

    public static void main(String[] args) throws JMSException {

        while(true) {
            Object o = MQ.receiveObj("A3");
            System.out.println(o);
            MQ.sendReply(o + " rely");
        }
    }

}
