package vendor.hz;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.instance.HazelcastInstanceProxy;
import remote.Task;

/**
 * Created by danny on 24/01/2016.
 */
public class HzTask extends Task {

    protected HazelcastInstance hzInstance;

    @Override
    public void setVendorObject(Object vendorObject) {
        hzInstance = (HazelcastInstance) vendorObject;
    }


    public static boolean isMember(HazelcastInstance instance) {
        return instance instanceof HazelcastInstanceProxy;
    }

    public static boolean isClient(HazelcastInstance instance) {
        return ! isMember(instance);
    }
}
