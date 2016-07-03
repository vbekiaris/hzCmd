package local;

import global.ClusterType;
import vendor.gem.GemJvmFactory;
import vendor.gg.GgJvmFactory;
import vendor.hz.HzJvmFactory;
import vendor.redis.RedisJvmFactory;

import javax.jms.JMSException;
import java.io.*;
import java.util.*;
import static global.Utils.myIp;


public class ClusterManager implements Serializable {

    private String brokerIP;
    private Map<String, ClusterContainer> clusters = new HashMap();

    public ClusterManager() { }

    public ClusterContainer initCluster(String id, ClusterType type) throws Exception{

        ClusterContainer cluster = clusters.get(id);

        if(cluster==null) {
            if (brokerIP == null) {
                brokerIP = myIp();
            }
            switch (type) {
                case HZ:
                    cluster = new ClusterContainer(id, brokerIP, new HzJvmFactory());
                    break;
                case GG:
                    cluster = new ClusterContainer(id, brokerIP, new GgJvmFactory());
                    break;
                case GEM:
                    cluster = new ClusterContainer(id, brokerIP, new GemJvmFactory());
                    break;
                case RED:
                    cluster = new ClusterContainer(id, brokerIP, new RedisJvmFactory());
                    break;
            }
            clusters.put(cluster.getClusterId(), cluster);
        }
        return cluster;
    }


    public List<ClusterContainer> getClusters(String id) {
        List<ClusterContainer> matching = new ArrayList();

        for(ClusterContainer c : clusters.values()){
            if ( c.getClusterId().matches(".*" + id + ".*") ){
                matching.add(c);
            }
        }
        return matching;
    }



}