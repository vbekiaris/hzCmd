package vendor.redis;

import global.Bash;
import global.NodeType;
import mq.MQ;
import local.Box;
import local.ClusterManager;
import local.RemoteJvm;

import java.io.IOException;


public class RemoteRedisMember extends RemoteJvm {

    public static final String version="3.0.7";

    public RemoteRedisMember(Box box, NodeType type, String id, String clusterId) throws IOException, InterruptedException {
        super(box, type, id, clusterId);
    }

    public String getClassToRun() {
        if (isMember()){
            return RedisMember.class.getName();
        }
        return RedisClient.class.getName();
    }

    public void beforeJvmStart(ClusterManager myCluster) throws Exception {

        if(isMember()) {
            box.upload("config-redis/redis.conf", dir + "/");
        }else {

        }
    }


    public final String startJvm(String jvmOptions, String vendorLibDir, ClusterManager myCluster, String brokerIP) throws Exception {

        if (isRunning()) {
            System.out.println(Bash.ANSI_CYAN+"all ready started " + this +Bash.ANSI_RESET);
            return "";
        }

        beforeJvmStart(myCluster);


        //System.out.println( box.ssh("sudo yum install -y expect") );
        System.out.println( box.ssh("sudo yum install -y gcc-c++") );
        System.out.println( box.ssh("wget http://download.redis.io/releases/redis-" + version + ".tar.gz") );
        System.out.println( box.ssh("tar xzf redis-" + version + ".tar.gz") );
        System.out.println( box.ssh("rm -fr redis-" + version + ".tar.gz") );


        System.out.println( box.ssh("sudo make -C redis-"+version+"/deps lua hiredis linenoise") );
        System.out.println( box.ssh("sudo make -C redis-"+version+" MALLOC=libc install") );

        System.out.println( box.ssh("gem install redis") );


        String pidStr = box.ssh("cd "+dir+";"+" nohup redis-server redis.conf >> out.txt 2>&1 & echo $!");
        pid = Integer.parseInt(pidStr.trim());

        System.out.println("redis-server pid="+pid);

        MQ.sendObj(id + "reply", "started "+id);

        return "";
    }

    public static String getRedisInstallHome(){
        return "redis-"+version;
    }


    @Override
    public String setJvmStartOptions(Box thisBox, ClusterManager myCluster) throws Exception {

       return null;
    }

}