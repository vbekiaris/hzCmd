package vendor.hz;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.instance.HazelcastInstanceProxy;
import remote.Bench;

/**
 * Created by danny on 24/01/2016.
 */
public abstract class HzBench extends Bench{

    protected HazelcastInstance hzInstance;

    @Override
    public void setVendorObject(Object vendorObject) {
        hzInstance = (HazelcastInstance) vendorObject;
    }

    public  boolean isMember() {
        return hzInstance instanceof HazelcastInstanceProxy;
    }

    public boolean isClient() {
        return ! isMember();
    }
}
