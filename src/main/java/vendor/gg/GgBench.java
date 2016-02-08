package vendor.gg;

import org.apache.ignite.Ignite;
import remote.Bench;

/**
 * Created by danny on 24/01/2016.
 */
public abstract class GgBench extends Bench{

    protected Ignite ignite;

    @Override
    public void setVendorObject(Object vendorObject) { ignite = (Ignite) vendorObject; }

}
