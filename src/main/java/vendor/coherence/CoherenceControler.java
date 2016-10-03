package vendor.coherence;

import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;

import com.tangosol.net.DefaultConfigurableCacheFactory;
import com.tangosol.net.DefaultCacheServer;


import global.NodeType;
import remote.main.Controler;

public class CoherenceControler extends Controler {

    private NamedCache cache = CacheFactory.getCache();

    public CoherenceControler(NodeType type) throws Exception {
        super(type);

        DefaultConfigurableCacheFactory factory;
        factory = new DefaultConfigurableCacheFactory("my-cache-config.xml");

        DefaultCacheServer dcs = new DefaultCacheServer(factory);
        dcs.startAndMonitor(5000);

    }

    public void init(NodeType type) throws Exception {

    }

    public Object getVendorObject(){
        return null;
    }
}