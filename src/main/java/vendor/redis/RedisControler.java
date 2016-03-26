package vendor.redis;

import global.NodeType;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import remote.Controler;

import java.util.HashSet;

public class RedisControler extends Controler {

    private JedisCluster jedisCluster;
    private NodeType type;


    public RedisControler(NodeType type) throws Exception {
        super(type);
    }

    public void init(NodeType type) throws Exception {
        this.type=type;

        if (type == NodeType.Member) {


        } else {
            java.util.Set<HostAndPort> jedisClusterNodes = new HashSet();
            jedisClusterNodes.add(new HostAndPort("127.0.0.1", RedisJvmFactory.redisMemberPort));

            GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();

            jedisCluster = new JedisCluster(jedisClusterNodes, poolConfig);

            System.out.println(jedisCluster);
        }
    }

    public Object getVendorObject(){
        if(type == NodeType.Member){
            return null;
        }
        return jedisCluster;
    }
}