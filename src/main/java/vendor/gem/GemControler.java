package vendor.gem;

import com.gemstone.gemfire.cache.*;
import com.gemstone.gemfire.cache.client.ClientCache;
import com.gemstone.gemfire.cache.client.ClientCacheFactory;
import com.gemstone.gemfire.distributed.LocatorLauncher;
import global.Args;
import global.NodeType;
import local.Box;
import remote.Controler;

import java.util.Arrays;
import java.util.List;

public class GemControler extends Controler {

    private Cache serverCache;
    private ClientCache clientCache;

    private NodeType type;

    public GemControler(NodeType type) throws Exception {
        super(type);
        this.type = type;
    }

    public void init(NodeType type) throws Exception {

        String pubIp = System.getProperty("MY_PUB_IP");
        String priIp = System.getProperty("MY_PRI_IP");
        String peersIp = System.getProperty("MY_PEERS_LIST");

        System.out.println("pub="+pubIp+", pri="+priIp+" peers="+peersIp);

        if(type == NodeType.Member){

            serverCache = new CacheFactory().set("cache-xml-file", "server-cache.xml")
                    .set("mcast-port", "0")
                    .set("bind-address", priIp)
                    .set("start-locator", "11001")
                    .set("locators", peersIp).create();


            /*
            if(ID.equals("GemMember1F")){
                //54.173.123.102  //10.0.0.193
                serverCache = new CacheFactory().set("cache-xml-file", "server-cache.xml")
                        .set("mcast-port", "0")
                        .set("bind-address", "10.0.0.193")
                        .set("start-locator", "11001")
                        .set("locators", "10.0.0.192[11001]").create();
                //localhost[11001]

            } else if ( ID.equals("GemMember2F") ) {

                //54.172.198.157 //10.0.0.192
                serverCache = new CacheFactory().set("cache-xml-file", "server-cache.xml")
                        .set("mcast-port", "0")
                        .set("bind-address", "10.0.0.192")
                        .set("start-locator", "11001")
                        .set("locators", "10.0.0.193[11001]").create();
            }
            */


            //.set("start-locator","[50505]").create();
            //Region r = c.createRegionFactory(RegionShortcut.REPLICATE).create("customers");
            //Region r = c.createRegionFactory(RegionShortcut.PARTITION_REDUNDANT).create("customers");
            //Example 3: Construct the cache region declaratively in cache.xml
            //Now, create the cache telling it to read your cache.xml file:

        }else{
            //Connect to a CacheServer on the default host and port and access a region "customers"

            ClientCacheFactory clientCacheFactory = new ClientCacheFactory();
            clientCacheFactory.set("cache-xml-file", "client-cache.xml");
            clientCacheFactory.set("mcast-port", "0");

            List<String> locators = Arrays.asList(peersIp.split(","));
            for (String locator : locators) {
                //clientCacheFactory.addPoolLocator(locator, 11001);
                clientCacheFactory.addPoolServer(locator, 40404);
            }


            clientCache = clientCacheFactory.create();

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