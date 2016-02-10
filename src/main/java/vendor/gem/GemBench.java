package vendor.gem;

import com.gemstone.gemfire.cache.GemFireCache;
import remote.Bench;

/**
 * Created by danny on 24/01/2016.
 */
public abstract class GemBench extends Bench{

    protected GemFireCache gemFireCache;

    @Override
    public void setVendorObject(Object vendorObject) {
        gemFireCache = (GemFireCache) vendorObject;

        if(gemFireCache==null){
            System.out.println("gemFireCache==null");
        }
    }
}
