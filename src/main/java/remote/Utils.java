package remote;

import jms.MQ;

import javax.jms.JMSException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;

public abstract class Utils {

    public static <T> T instantiate(final String className, final Class<T> type) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> c = Class.forName(className);
        Object o = c.getConstructor().newInstance();
        return type.cast(o);
    }

    public static void recordeException(Exception e) {
        e.printStackTrace();
        try {
            e.printStackTrace( new PrintStream(new FileOutputStream("exception.txt", true)) );
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
    }

    public static void sendBAckException(Exception e) throws JMSException {
        MQ.sendObj("exception", e);
    }
}
