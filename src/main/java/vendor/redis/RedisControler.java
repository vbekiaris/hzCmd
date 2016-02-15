package vendor.redis;

import global.NodeType;
import redis.clients.jedis.Jedis;
import redis.embedded.RedisServer;
import remote.Controler;

public class RedisControler extends Controler {

    private RedisServer redisServer;
    private Jedis jedisClient;
    private NodeType type;


    public RedisControler(NodeType type) throws Exception {
        super(type);
    }

    public void init(NodeType type) throws Exception {
        this.type=type;

        if (type == NodeType.Member) {

            redisServer = new RedisServer(6379);
            redisServer.start();

            System.out.println("redisServer.isActive()="+redisServer.isActive());

        } else {
            jedisClient = new Jedis("host", 6379);
        }
    }

    public Object getVendorObject(){
        if(type == NodeType.Member){
            return redisServer;
        }
        return jedisClient;
    }
}