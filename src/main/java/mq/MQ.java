package mq;

import global.Args;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public abstract class MQ {

    private static final int ackMode = Session.AUTO_ACKNOWLEDGE;
    private static final boolean transacted = false;

    private static Connection connection;
    private static Session session;

    private static Map<String, MessageProducer> producerMap = new HashMap();
    private static Map<String, MessageConsumer> consumerMap = new HashMap();


    private static final String brokerIp = System.getProperty(Args.MQIP.name(), "localhost");

    private static final String brokerUri = "tcp://"+brokerIp+":61616";


    private static void makeMqConnection() throws JMSException {

        Random random = new Random();

        System.setProperty("org.apache.activemq.SERIALIZABLE_PACKAGES","*");
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerUri);
        connectionFactory.setMaxThreadPoolSize(4);

        int count = 0;
        int maxTries = 20;
        while(true) {
            try {
                if ( connection == null) {
                    connection = connectionFactory.createConnection();
                }
                connection.start();
                session = connection.createSession(transacted, ackMode);
                break;
            } catch (JMSException e) {

                if (++count == maxTries){
                    throw new RuntimeException(e);
                }
                System.out.println("retry Mq connection");
                try {
                    Thread.sleep(500 + random.nextInt(4000));
                } catch (InterruptedException x) {}

                throw e;
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
        producer.send(msg);
    }


    public static Object receiveObj(String queueName) throws JMSException {
        MessageConsumer consumer = getMessageConsumer(queueName);
        return  ((ObjectMessage) consumer.receive()).getObject();
    }

    public static Object receiveObj(String queueName, long time) throws JMSException {
        MessageConsumer consumer = getMessageConsumer(queueName);
        Message received = consumer.receive(time);
        if(received==null){
            return null;
        }
        return  ((ObjectMessage) received).getObject();
    }

    public static Message receiveMsg(String queueName, long time) throws JMSException {
        MessageConsumer consumer = getMessageConsumer(queueName);
        return consumer.receive(time);
    }

    public static Message receiveMsgNoWait(String queueName) throws JMSException {
        MessageConsumer consumer = getMessageConsumer(queueName);
        return consumer.receiveNoWait();
    }

    public static void drainQ(String queueName) throws JMSException {

        int count = 0;
        while ( MQ.receiveMsg(queueName, 10) != null) {
            count++;
        }

        if(count!=0){
            System.out.println(count + " msgs removed from queue: " + queueName);
        }
    }

    private static MessageProducer getMessageProducer(String queueName) throws JMSException {
        if ( ! producerMap.containsKey(queueName) ){

            //long start = System.currentTimeMillis();
            //System.out.println("start create getMessageProducer");


            if (session==null){
                makeMqConnection();
            }

            Destination q = session.createQueue(queueName);
            MessageProducer mp = session.createProducer(q);
            mp.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

            producerMap.put(queueName, mp);

            //long end = System.currentTimeMillis();
            //System.out.println("end create getMessageProduce seconds "+(end-start)/1000);

        }
        return producerMap.get(queueName);
    }

    private static MessageConsumer getMessageConsumer(String queueName) throws JMSException {
        if ( ! consumerMap.containsKey(queueName) ){

            //long start = System.currentTimeMillis();
            //System.out.println("start create getMessageConsumer");


            if (session==null){
                makeMqConnection();
            }

            Destination q = session.createQueue(queueName);
            MessageConsumer consumer = session.createConsumer(q);
            consumerMap.put(queueName, consumer);

            //long end = System.currentTimeMillis();
            //System.out.println("end create getMessageConsumer seconds "+(end-start)/1000);
        }
        return consumerMap.get(queueName);
    }

}