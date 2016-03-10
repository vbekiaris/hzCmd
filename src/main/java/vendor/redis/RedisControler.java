package vendor.redis;

import global.Bash;
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
        } else {
            jedisClient = new Jedis( "host", 6379);
        }
    }

    public Object getVendorObject(){
        if(type == NodeType.Member){
            return redisServer;
        }
        return jedisClient;
    }
}