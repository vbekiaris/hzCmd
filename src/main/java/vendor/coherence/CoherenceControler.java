package vendor.coherence;

import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;

import com.tangosol.net.DefaultConfigurableCacheFactory;
import com.tangosol.net.DefaultCacheServer;


import global.NodeType;
import remote.main.Controler;

public class CoherenceControler extends Controler {

    private DefaultCacheServer dcs;
    private NamedCache cache;

    public CoherenceControler(NodeType type) throws Exception {
        super(type);

        if (type == NodeType.Member) {

            DefaultConfigurableCacheFactory factory;
            factory = new DefaultConfigurableCacheFactory("my-cache-config.xml");

            dcs = new DefaultCacheServer(factory);
            dcs.startAndMonitor(5000);
        }
        else{
            //CacheFactory.ensureCluster();
            NamedCache cache = CacheFactory.getCache("cache_name");
        }

    }

    public void init(NodeType type) throws Exception {

    }

    public Object getVendorObject(){
        return null;
    }
}