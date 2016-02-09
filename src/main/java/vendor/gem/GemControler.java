package vendor.gem;

import com.gemstone.gemfire.cache.*;
import com.gemstone.gemfire.cache.client.ClientCache;
import com.gemstone.gemfire.cache.client.ClientCacheFactory;
import com.gemstone.gemfire.distributed.LocatorLauncher;
import global.NodeType;
import remote.Controler;

public class GemControler extends Controler {

    private Cache serverCache;
    private ClientCache clientCache;

    private NodeType type;

    public GemControler(NodeType type) throws Exception {
        super(type);
        this.type = type;
    }

    public void init(NodeType type) throws Exception {
        if(type == NodeType.Member){

            if(ID.equals("GemMember1F")){


                //54.173.123.102  //10.0.0.193
                serverCache = new CacheFactory().set("cache-xml-file", "server-cache.xml")
                        .set("mcast-port", "0")
                        .set("bind-address", "10.0.0.193")
                        .set("start-locator", "11001")
                        .set("locators", "localhost[11001],10.0.0.192[11002]").create();

            } else if ( ID.equals("GemMember2F") ) {

                //54.172.198.157 //10.0.0.192
                serverCache = new CacheFactory().set("cache-xml-file", "server-cache.xml")
                        .set("mcast-port", "0")
                        .set("bind-address", "10.0.0.192")
                        .set("start-locator", "11002")
                        .set("locators", "10.0.0.193[11001],localhost[11002]").create();

                //serverCache = new CacheFactory().set("cache-xml-file", "server-cache.xml").set("mcast-port", "0").set("locators", "10.0.0.193[13489]").create();
            }
            //.set("start-locator","[50505]").create();

            //Region r = c.createRegionFactory(RegionShortcut.REPLICATE).create("customers");
            //Region r = c.createRegionFactory(RegionShortcut.PARTITION_REDUNDANT).create("customers");

            //Example 3: Construct the cache region declaratively in cache.xml
            //Now, create the cache telling it to read your cache.xml file:

        }else{
            //Connect to a CacheServer on the default host and port and access a region "customers"

            clientCache = new ClientCacheFactory().set("cache-xml-file", "client-cache.xml").create();

            //Region r = c.createClientRegionFactory(ClientRegionShortcut.PROXY).create("customers");


            //Example 2: Connect using the GemFire locator and create a local LRU cache
            //ClientCache c = new ClientCacheFactory()
            //            .addPoolLocator(host, port)
            //            .create();


            //Region r = c.createClientRegionFactory(CACHING_PROXY_HEAP_LRU)
            //           .create("customers");
            //The local LRU "customers" data region will automatically start evicting, by default, at 80% heap utilization threshold

        }
    }

    public Object getVendorObject(){
        if(type == NodeType.Member){
            return serverCache;
        }
        return clientCache;
    }
}