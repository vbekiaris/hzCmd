package vendor.redis;

import redis.clients.jedis.Jedis;
import remote.Bench;

/**
 * Created by danny on 24/01/2016.
 */
public abstract class RedisBench extends Bench{

    protected Jedis jedisClient;

    @Override
    public void setVendorObject(Object vendorObject) { jedisClient = (Jedis) vendorObject; }

}
