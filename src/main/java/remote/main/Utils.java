package remote.main;

import mq.MQ;

import javax.jms.JMSException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public abstract class Utils {

    public static <T> T instantiate(final String className, final Class<T> type) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> c = Class.forName(className);
        Object o = c.getConstructor().newInstance();
        return type.cast(o);
    }

    public static void setField(String fieldName, String value, Object obj) throws NoSuchMethodException, NoSuchFieldException, IllegalAccessException, InvocationTargetException {
        Class clazz = obj.getClass();
        Field field = clazz.getField(fieldName);
        Class<?> type = field.getType();

        if(type.isAssignableFrom(String.class) ){
            field.set(obj, value);
        }else{
            Method parseMethod = field.get(obj).getClass().getMethod("valueOf", new Class[]{String.class});
            field.set(obj, parseMethod.invoke(field, value));
        }
    }

    public static void recordeException(Throwable e) {
        e.printStackTrace();
        try {
            PrintStream ps = new PrintStream(new FileOutputStream("exception.txt", true));
            e.printStackTrace(ps);
            ps.close();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
    }

    public static void recordeExceptionJms(Exception e) {
        e.printStackTrace();
        try {
            e.printStackTrace( new PrintStream(new FileOutputStream("jms-exception.txt", true)) );
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
    }

    public static void recordSendException(Exception e, String queueName) {
        recordeException(e);
        try {
            MQ.sendObj(queueName, e);
        } catch (JMSException jmsError) {
            recordeException(jmsError);
        }
    }

}
