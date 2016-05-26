package mq;

import global.Args;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public abstract class MQ {

    private static final int ackMode = Session.AUTO_ACKNOWLEDGE;
    private static final boolean transacted = false;

    private static Connection connection;
    private static Session session;

    private static Map<String, MessageProducer> producerMap = new HashMap();
    private static Map<String, MessageConsumer> consumerMap = new HashMap();

    private static Destination replyDestination;


    private static final String brokerIp = System.getProperty(Args.MQIP.name(), "localhost");

    private static final String brokerUri = "tcp://"+brokerIp+":61616";


    private static void makeMqConnection(){

        System.setProperty("org.apache.activemq.SERIALIZABLE_PACKAGES","*");
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerUri);
        connectionFactory.setMaxThreadPoolSize(4);

        int count = 0;
        int maxTries = 3;
        while(true) {
            try {
                if ( connection == null) {
                    connection = connectionFactory.createConnection();
                }
                connection.start();
                session = connection.createSession(transacted, ackMode);
                replyDestination = session.createTemporaryQueue();
                break;
            } catch (JMSException e) {
                if (++count == maxTries){
                    throw new RuntimeException(e);
                }
                System.out.println("retry Mq connection");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException x) {}
            }
        }
    }

    public static void shutdown() throws JMSException {
        if(connection!=null){
            connection.close();
            connection=null;
        }
    }

    public static void sendObj(String queueName, Serializable obj) throws JMSException {
        MessageProducer producer = getMessageProducer(queueName);
        ObjectMessage msg = session.createObjectMessage();
        msg.setObject(obj);

        msg.setJMSReplyTo(replyDestination);
        producer.send(msg);
    }


    public static Object receiveObj(String queueName) throws JMSException {
        MessageConsumer consumer = getMessageConsumer(queueName);
        return  ((ObjectMessage) consumer.receive()).getObject();
    }

    public static Object receiveObj(String queueName, long time) throws JMSException {
        MessageConsumer consumer = getMessageConsumer(queueName);
        return  ((ObjectMessage) consumer.receive(time)).getObject();
    }

    public static Message receiveMsg(String queueName, long time) throws JMSException {
        MessageConsumer consumer = getMessageConsumer(queueName);
        return consumer.receive(time);
    }

    public static Object receiveMsgNoWait(String queueName) throws JMSException {
        MessageConsumer consumer = getMessageConsumer(queueName);
        return consumer.receiveNoWait();
    }

    public static void drainQ(String queueName) throws JMSException {

        //while( MQ.receiveMsgNoWait(queueName) != null) {
        //    System.out.println(queueName+" drain");
        //}

        int count = 0;
        Message msg = null;
        while ((msg = MQ.receiveMsg(queueName, 500)) != null) {
            count++;
        }
        System.out.println(count + " msgs removed from queue: " + queueName);

    }

    private static MessageProducer getMessageProducer(String queueName) throws JMSException {
        if ( ! producerMap.containsKey(queueName) ){

            if (session==null){
                makeMqConnection();
            }

            Destination q = session.createQueue(queueName);
            MessageProducer mp = session.createProducer(q);
            mp.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

            producerMap.put(queueName, mp);
        }
        return producerMap.get(queueName);
    }

    private static MessageConsumer getMessageConsumer(String queueName) throws JMSException {
        if ( ! consumerMap.containsKey(queueName) ){

            if (session==null){
                makeMqConnection();
            }

            Destination q = session.createQueue(queueName);
            MessageConsumer consumer = session.createConsumer(q);
            consumerMap.put(queueName, consumer);
        }
        return consumerMap.get(queueName);
    }

}