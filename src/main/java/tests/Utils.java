package tests;

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

    public static int streamInAsyncCount=500;

    public static boolean isMember(HazelcastInstance instance) {
        return instance instanceof HazelcastInstanceProxy;
    }

    public static boolean isClient(HazelcastInstance instance) {
        return ! isMember(instance);
    }

    public static void sleepSeconds(int sec){
        try {
            Thread.sleep(sec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static String getUniqueKeyPrefix(HazelcastInstance hazelcastInstance){
        if (Utils.isMember(hazelcastInstance)){
            IAtomicLong count =  hazelcastInstance.getAtomicLong("m-");
            return "m"+count.getAndIncrement()+"-";
        }else{
            IAtomicLong count = hazelcastInstance.getAtomicLong("c-");
            return "c"+count.getAndIncrement()+"-";
        }
    }

    public static boolean localKey(int key, HazelcastInstance instance) {
        PartitionService partitionService = instance.getPartitionService();
        Partition partition = partitionService.getPartition(key);
        while (true) {
            com.hazelcast.core.Member owner = partition.getOwner();
            if (owner != null) {
                return owner.equals(instance.getLocalEndpoint());
            }
            sleepSeconds(1);
        }
    }

    public static void putLocalKeyVals(HazelcastInstance instance, ICache cache, int keyDomainMin, int keyDomainMax, List valueSet){
        Random random = new Random();
        for (int k = keyDomainMin; k < keyDomainMax; k++) {
            if (localKey(k, instance)) {
                int valueIdx = random.nextInt(valueSet.size());
                if (k % streamInAsyncCount == 0) {
                    cache.put(k, valueSet.get(valueIdx));
                } else {
                    cache.putAsync(k, valueSet.get(valueIdx));
                }
            }
        }
    }

    public static List<byte[]> dymanicValues(int setSize, int middleSize){
        Random random = new Random();
        List valueSet = new ArrayList(setSize);

        if(setSize==1){
            byte[] value = new byte[middleSize];
            random.nextBytes(value);
            valueSet.add(value);
            return valueSet;
        }

        int min = middleSize / 2;
        int max = middleSize + min;

        for(int i=0; i<setSize; i++){
            int sz = randInt(random, min, max);
            byte[] value = new byte[sz];
            random.nextBytes(value);
            valueSet.add(value);
        }
        return valueSet;
    }

    public static void warmupCache(ICache cache, Collection keys){
        int count=0;
        for (Object k : keys){
            if( count++ % streamInAsyncCount==0){
                cache.get(k);
            }else{
                cache.getAsync(k);
            }
        }
    }

    public static NearCache getNearCache(CacheManager cacheManager, ICache cache){
        CacheConfig cacheConfig = (CacheConfig) cache.getConfiguration(CacheConfig.class);
        String cacheFullName = cacheConfig.getNameWithPrefix();

        HazelcastClientCacheManager clientCacheManager = (HazelcastClientCacheManager)cacheManager;
        NearCacheManager nearCacheManager = clientCacheManager.getNearCacheManager();
        NearCache nearCache = nearCacheManager.getNearCache(cacheFullName);
        return nearCache;
    }

    public static CacheManager getCacheManager(HazelcastInstance instance){
        CachingProvider provider;
        if (isMember(instance)) {
            provider = HazelcastServerCachingProvider.createCachingProvider(instance);
        } else {
            provider = HazelcastClientCachingProvider.createCachingProvider(instance);
        }
        return provider.getCacheManager(provider.getDefaultURI(),provider.getDefaultClassLoader(), null);
    }


    public static List<Integer> listKeyDomain(int keyDomainMin, int keyDomainMax){
        ArrayList<Integer> keys = new ArrayList( keyDomainMax - keyDomainMin );
        for (int k=keyDomainMin; k<keyDomainMax; k++) {
            keys.add(k);
        }
        return keys;
    }

    public static int[] keyDomain(int keyDomainMin, int keyDomainMax){
        int[] keys = new int[keyDomainMax - keyDomainMin ];
        int idx=0;
        for (int k=keyDomainMin; k<keyDomainMax; k++) {
            keys[idx++] = k;
        }
        return keys;
    }

    /**
     * Returns a pseudo-random number between min and max, inclusive.
     * The difference between min and max can be at most
     * <code>Integer.MAX_VALUE - 1</code>.
     *
     * @param min Minimum value
     * @param max Maximum value.  Must be greater than min.
     * @return Integer between min inclusive and max, ex-inclusive.
     * @see java.util.Random#nextInt(int)
     */
    public static int randInt(Random random, int min, int max) {
        int randomNum = random.nextInt( max-min ) + min;
        return randomNum;
    }
}
