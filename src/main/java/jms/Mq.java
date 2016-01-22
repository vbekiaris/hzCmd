package jms;

import org.apache.activemq.ActiveMQConnection;
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

    private static MessageProducer replyProducer;

    private static Destination replyDestination;
    private static MessageConsumer replyConsumer;

    static {
        System.setProperty("org.apache.activemq.SERIALIZABLE_PACKAGES","*");

        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_BROKER_URL);
        try {
            connection = connectionFactory.createConnection();
            connection.start();
            session = connection.createSession(transacted, ackMode);

            replyDestination = session.createTemporaryQueue();
            replyConsumer = session.createConsumer(replyDestination);

        } catch (JMSException e) {
            e.printStackTrace() ;
        }
    }

    public static void shutdown() throws JMSException {
        if(connection!=null){
            connection.close();
        }
    }

    public static String receiveAnyResponse() throws JMSException {
        Message receive = replyConsumer.receive();
        return receive.toString();
    }

    public static void send(String queueName, String msg) throws JMSException {
        MessageProducer producer = getMessageProducer(queueName);
        TextMessage textMessage = session.createTextMessage(msg);
        textMessage.setJMSReplyTo(replyDestination);
        producer.send(textMessage);
    }

    public static void sendObj(String queueName, Serializable obj) throws JMSException {
        MessageProducer producer = getMessageProducer(queueName);
        ObjectMessage msg = session.createObjectMessage();
        msg.setObject(obj);

        msg.setJMSReplyTo(replyDestination);
        producer.send(msg);
    }

    public static Message receive(String queueName) throws JMSException {
        MessageConsumer consumer = getMessageConsumer(queueName);
        return consumer.receive();
    }

    public static Object receiveObj(String queueName) throws JMSException {
        MessageConsumer consumer = getMessageConsumer(queueName);
        return  ((ObjectMessage) consumer.receive()).getObject();
    }

    public static void acknolage(Message msg) throws JMSException {
        TextMessage ack = session.createTextMessage();
        ack.setText("acked");
        ack.setJMSCorrelationID(msg.getJMSCorrelationID());
        replyProducer = getReplyProducer();
        replyProducer.send(msg.getJMSReplyTo(), ack);
    }



    public static MessageProducer getReplyProducer() throws JMSException {
        if(replyProducer==null){
            replyProducer = session.createProducer(null);
            replyProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
        }
        return replyProducer;
    }

    private static MessageProducer getMessageProducer(String queueName) throws JMSException {
        if ( ! producerMap.containsKey(queueName) ){
            Destination q = session.createQueue(queueName);
            MessageProducer mp = session.createProducer(q);
            mp.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

            producerMap.put(queueName, mp);
        }
        return producerMap.get(queueName);
    }

    private static MessageConsumer getMessageConsumer(String queueName) throws JMSException {
        if ( ! consumerMap.containsKey(queueName) ){
            Destination q = session.createQueue(queueName);
            MessageConsumer consumer = session.createConsumer(q);
            consumerMap.put(queueName, consumer);
        }
        return consumerMap.get(queueName);
    }

}