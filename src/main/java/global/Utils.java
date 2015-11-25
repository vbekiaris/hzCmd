package global;

import com.hazelcast.cache.ICache;
import com.hazelcast.cache.impl.HazelcastServerCachingProvider;
import com.hazelcast.cache.impl.nearcache.NearCache;
import com.hazelcast.cache.impl.nearcache.NearCacheManager;
import com.hazelcast.client.cache.impl.HazelcastClientCacheManager;
import com.hazelcast.client.cache.impl.HazelcastClientCachingProvider;
import com.hazelcast.config.CacheConfig;
import com.hazelcast.core.*;
import com.hazelcast.instance.HazelcastInstanceProxy;
import javax.cache.CacheManager;
import javax.cache.spi.CachingProvider;
import java.util.*;

public abstract class Utils {

    public static void sleepMilli(int mills){
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void sleepSeconds(int sec){
        sleepMilli(sec*1000);
    }
}
