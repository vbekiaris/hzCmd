package vendor.coherence;

import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;

import com.tangosol.net.DefaultConfigurableCacheFactory;
import com.tangosol.net.DefaultCacheServer;


import global.NodeType;
import remote.main.Controler;

public class CoherenceControler extends Controler {

    private DefaultCacheServer cacheServer;
    private NamedCache cache;

    public CoherenceControler(NodeType type) throws Exception {
        super(type);
    }

    public void init(NodeType type) throws Exception {

        if (type == NodeType.Member) {

            DefaultConfigurableCacheFactory factory;
            factory = new DefaultConfigurableCacheFactory("coherence-cache-config.xml");

            cacheServer = new DefaultCacheServer(factory);
            //cacheServer.startAndMonitor(5000);
            cacheServer.startDaemon(15000);

        }
        else{
            CacheFactory.ensureCluster();
            cache = CacheFactory.getCache("cache_name");
            cache.put("hello", "world");
            System.out.println( cache.get("hello") );
        }

    }

    public Object getVendorObject(){
        if(type.equals(NodeType.Member)){
            return cacheServer;
        }
        return null;
    }
}