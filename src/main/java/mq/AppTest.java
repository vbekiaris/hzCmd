package mq;

import javax.jms.JMSException;
import java.util.Random;

public class AppTest {

    public static void main(String[] args) throws JMSException, InterruptedException {

        Random r = new Random();

        for(int i =0; i < 10000; i++) {

            for(int Q=2;  Q<=3; Q++) {
                String s = "send-A"+Q+"-"+r.nextInt(10000);
                MQ.sendObj("A" + Q, s);
                System.out.println(s);
            }

            for(int Q=2;  Q<=3; Q++) {
                Object o = MQ.receivReply();
                System.out.println(o);
            }

            Thread.sleep(1000);
        }
    }

}
