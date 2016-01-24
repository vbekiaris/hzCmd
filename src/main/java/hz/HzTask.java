package hz;

import com.hazelcast.core.HazelcastInstance;
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
}
