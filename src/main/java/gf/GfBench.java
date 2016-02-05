package gf;

import org.apache.ignite.Ignite;
import remote.Bench;

/**
 * Created by danny on 24/01/2016.
 */
public abstract class GfBench extends Bench{




    @Override
    public void setVendorObject(Object vendorObject) {
        (Ignite) vendorObject;
    }

}
