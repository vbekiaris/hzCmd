package vendor.gem;

import com.gemstone.gemfire.cache.*;
import com.gemstone.gemfire.cache.client.ClientCache;
import com.gemstone.gemfire.cache.client.ClientCacheFactory;
import global.NodeType;
import remote.main.Controler;

import java.util.Arrays;
import java.util.List;

public class GemControler extends Controler {

    private Cache serverCache;
    private ClientCache clientCache;

    private NodeType type;

    public GemControler(NodeType type) throws Exception {
        super(type);
    }

    public void init(NodeType type) throws Exception {
        String pubIp = System.getProperty("MY_PUB_IP");
        String priIp = System.getProperty("MY_PRI_IP");
        String peersIp = System.getProperty("MY_PEERS_LIST");
        System.out.println("pub="+pubIp+", pri="+priIp+" peers="+peersIp);

        this.type = type;
        if(type == NodeType.Member){

            serverCache = new CacheFactory().set("cache-xml-file", "server-cache.xml")
                    .set("mcast-port", "0")
                    .set("bind-address", priIp)
                    .set("start-locator", "11001")
                    .set("locators", peersIp).create();

        }else{

            ClientCacheFactory clientCacheFactory = new ClientCacheFactory();
            clientCacheFactory.set("cache-xml-file", "client-cache.xml");
            //clientCacheFactory.set("mcast-port", "0");

            List<String> locators = Arrays.asList(peersIp.split(","));
            for (String locator : locators) {
                clientCacheFactory.addPoolLocator(locator, 11001);
                //clientCacheFactory.addPoolServer(locator, 40404);
            }
            clientCache = clientCacheFactory.create();
            System.out.println( clientCache.getCurrentServers() );
        }
    }

    public Object getVendorObject(){
        if(type == NodeType.Member){
            return serverCache;
        }
        return clientCache;
    }
}