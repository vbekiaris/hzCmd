package remote;

import jms.MQ;

import javax.jms.JMSException;
import java.lang.reflect.InvocationTargetException;

public abstract class Utils {

    public static <T> T instantiate(final String className, final Class<T> type) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> c = Class.forName(className);
        Object o = c.getConstructor().newInstance();
        return type.cast(o);
    }

    public static void sendBAckException(Exception e) throws JMSException {
        MQ.sendObj("exception", e);
    }
}
