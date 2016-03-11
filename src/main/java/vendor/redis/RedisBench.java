package vendor.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import remote.Bench;

/**
 * Created by danny on 24/01/2016.
 */
public abstract class RedisBench extends Bench{


    private JedisCluster jedisCluster;

    @Override
    public void setVendorObject(Object vendorObject) { jedisCluster = (JedisCluster) vendorObject; }

}
