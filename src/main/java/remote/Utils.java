package remote;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.instance.HazelcastInstanceProxy;
import global.Bash;

import java.io.IOException;

public abstract class Utils {

    public static boolean isMember(HazelcastInstance instance) {
        return instance instanceof HazelcastInstanceProxy;
    }

    public static boolean isClient(HazelcastInstance instance) {
        return ! isMember(instance);
    }

    public static <T> T instantiate(final String className, final Class<T> type){
        try{
            return type.cast(Class.forName(className).newInstance());
        } catch(final InstantiationException e){
            throw new IllegalStateException(e);
        } catch(final IllegalAccessException e){
            throw new IllegalStateException(e);
        } catch(final ClassNotFoundException e){
            throw new IllegalStateException(e);
        }
    }

    public static void sendBackError(String msg){
        sendBack("ERROR "+msg);
    }

    public static void sendBack(String msg) {
        try {
            Bash.ssh(Controler.home.user, Controler.home.ip, "echo msg " + msg + " >> " + Controler.home.cwd + "/" + Controler.home.inputFile);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
