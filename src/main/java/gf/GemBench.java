package gf;

import com.gemstone.gemfire.cache.GemFireCache;
import org.apache.ignite.Ignite;
import remote.Bench;

/**
 * Created by danny on 24/01/2016.
 */
public abstract class GemBench extends Bench{

    GemFireCache gemFireCache;

    @Override
    public void setVendorObject(Object vendorObject) {
        gemFireCache = (GemFireCache) vendorObject;
    }
}
