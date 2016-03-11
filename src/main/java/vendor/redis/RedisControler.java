package vendor.redis;

import global.NodeType;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import remote.Controler;

import java.util.HashSet;

public class RedisControler extends Controler {

    private JedisCluster jc;
    private Jedis jedisClient;
    private NodeType type;


    public RedisControler(NodeType type) throws Exception {
        super(type);
    }

    public void init(NodeType type) throws Exception {
        this.type=type;

        if (type == NodeType.Member) {

            /*
            String version="3.0.7";
            System.out.println( Bash.executeCommand("sudo yum install -y expect") );
            System.out.println( Bash.executeCommand("sudo yum install -y gcc-c++") );
            System.out.println( Bash.executeCommand("wget http://download.redis.io/releases/redis-" + version + ".tar.gz") );
            System.out.println( Bash.executeCommand("tar xzf redis-" + version + ".tar.gz") );

            System.out.println( Bash.executeCommand("sudo make -C redis-"+version+"/deps lua hiredis linenoise") );
            System.out.println( Bash.executeCommand("sudo make -C redis-"+version+" MALLOC=libc install") );

            System.out.println( Bash.executeCommand("gem install redis") );


            String pidStr = Bash.executeCommand("nohup redis-server >> out.txt 2>&1 & echo $!");
            //redis.conf
            //String pidStr = Bash.executeCommand("redis-server >> out.txt 2>&1 & echo $!");
            int pid = Integer.parseInt(pidStr.trim());

            System.out.println("redis-server pid="+pid);

            //redisServer = new RedisServer(6379);
            //redisServer.start();
            //System.out.println("redisServer.isActive()="+redisServer.isActive());
            */
        } else {

            java.util.Set<HostAndPort> jedisClusterNodes = new HashSet();

            jedisClusterNodes.add(new HostAndPort("127.0.0.1", RedisJvmFactory.redisMemberPort));
            jc = new JedisCluster(jedisClusterNodes);

            System.out.println(jc);
            System.out.println( jedisClient.set("k", "val") );
            System.out.println( jedisClient.get("k") );


            /*
            jedisClient = new Jedis("localhost", RedisJvmFactory.redisMemberPort);
            jedisClient.clientSetname(jvmPidId);
            System.out.println(jedisClient.clusterInfo());
            System.out.println( jedisClient.set("k", "val") );
            System.out.println( jedisClient.get("k") );
            */
        }
    }

    public Object getVendorObject(){
        if(type == NodeType.Member){
            return null;
        }
        return jedisClient;
    }
}