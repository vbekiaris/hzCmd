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

    private static Destination replyToDestination;
    private static MessageProducer replyProducer;
    private static MessageConsumer replyConsumer;

    private static Map<String, MessageConsumer> consumerMap = new HashMap();


    private static final String brokerIp = System.getProperty(Args.MQIP.name(), "localhost");

    private static final String brokerUri = "tcp://"+brokerIp+":61616";


    private static synchronized void makeMqConnection() throws JMSException {

        if (session!=null){
            return;
        }

        Random random = new Random();

        System.setProperty("org.apache.activemq.SERIALIZABLE_PACKAGES","*");
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerUri);
        connectionFactory.setMaxThreadPoolSize(4);

        int count = 0;
        int maxTries = 3;
        while(true) {
            try {
                connection = connectionFactory.createConnection();

                connection.start();
                session = connection.createSession(transacted, ackMode);
                replyToDestination = session.createTemporaryQueue();
                replyConsumer = session.createConsumer(replyToDestination);

                break;
            } catch (JMSException e) {

                if (++count == maxTries){

                    System.out.println("failed 60 Mq connections");
                    throw  e;
                }
                try {
                    Thread.sleep(1000 + random.nextInt(5000));
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
        msg.setJMSReplyTo(replyToDestination);
        msg.setObject(obj);
        producer.send(msg);
    }


    public static void sendReply(Serializable obj) throws JMSException {
        ObjectMessage msg = session.createObjectMessage();
        msg.setObject(obj);
        replyProducer.send(msg);
    }

    public static Object receivReply( ) throws JMSException {
        return ((ObjectMessage) replyConsumer.receive()).getObject();
    }

    public static Object receivReply(long timeOut) throws JMSException {
        return ((ObjectMessage) replyConsumer.receive(timeOut)).getObject();
    }


    public static Object receiveObj(String queueName) throws JMSException {
        MessageConsumer consumer = getMessageConsumer(queueName);

        ObjectMessage objMsg = (ObjectMessage) consumer.receive();
        Destination replyToDestination = objMsg.getJMSReplyTo();

        replyProducer = session.createProducer(replyToDestination);

        return  objMsg.getObject();
    }


    public static Object receiveObjNoReply(String queueName, long timeout) throws JMSException {
        MessageConsumer consumer = getMessageConsumer(queueName);
        ObjectMessage objMsg = (ObjectMessage) consumer.receive(timeout);
        return  objMsg.getObject();
    }



    public static void drainQ(String queueName) throws JMSException {

        int count = 0;
        while ( MQ.receiveMsg(queueName, 5) != null) {
            count++;
        }

        if(count!=0){
            System.out.println(count + " msgs removed from queue: " + queueName);
        }
    }

    private static Message receiveMsg(String queueName, long time) throws JMSException {
        MessageConsumer consumer = getMessageConsumer(queueName);
        return consumer.receive(time);
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

            makeMqConnection();


            Destination q = session.createQueue(queueName);
            MessageConsumer consumer = session.createConsumer(q);
            consumerMap.put(queueName, consumer);

        }
        return consumerMap.get(queueName);
    }

}