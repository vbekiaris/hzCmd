package remote;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.instance.HazelcastInstanceProxy;
import global.Bash;
import jms.MQ;

import javax.jms.JMSException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public abstract class Utils {

    public static boolean isMember(HazelcastInstance instance) {
        return instance instanceof HazelcastInstanceProxy;
    }

    public static boolean isClient(HazelcastInstance instance) {
        return ! isMember(instance);
    }

    public static <T> T instantiate(final String className, final Class<T> type) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> c = Class.forName(className);
        Object o = c.getConstructor().newInstance();
        return type.cast(o);
    }

    public static void sendBAckException(Exception e) throws JMSException {
        MQ.sendObj("exception", e);
    }
}
