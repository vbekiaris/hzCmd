package mq;

import javax.jms.JMSException;

public class AppTest2 {

    public static void main(String[] args) throws JMSException {

        while(true) {
            Object o = MQ.receiveObj("A2");
            System.out.println(o);
            MQ.sendReply(o + " reply");
        }
    }

}
