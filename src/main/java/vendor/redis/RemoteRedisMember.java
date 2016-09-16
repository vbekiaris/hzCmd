package vendor.redis;

import global.Bash;
import global.NodeType;
import local.ClusterContainer;
import mq.MQ;
import local.Box;
import local.RemoteJvm;

import java.io.IOException;
import java.util.List;


public class RemoteRedisMember extends RemoteJvm {

    public static final String version="3.2.3";

    public RemoteRedisMember(Box box, NodeType type, String id, String clusterId) throws IOException, InterruptedException {
        super(box, type, id, clusterId);
    }

    public String getClassToRun() {
        if (isMember()){
            return RedisMember.class.getName();
        }
        return RedisClient.class.getName();
    }

    @Override
    public List<String> stuffToPutInDir() throws Exception {
        return null;
    }

    public void beforeJvmStart(ClusterContainer myCluster) throws Exception {

        if(isMember()) {
            box.upload("config-redis/redis.conf", dir + "/");
        }else {

        }
    }


    public final String startJvm(String jvmOptions, String vendorLibDir, ClusterContainer myCluster, String brokerIP) throws Exception {

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


        String pidStr = box.ssh("cd "+dir+";"+" nohup redis-server redis.conf --portected-mode no >> out.txt 2>&1 & echo $!");
        pid = Integer.parseInt(pidStr.trim());

        System.out.println("redis-server pid="+pid);

        MQ.sendObj(id + "reply", "started "+id);

        return "";
    }

    public static String getRedisInstallHome(){
        return "redis-"+version;
    }


    @Override
    public String setJvmStartOptions(Box thisBox, ClusterContainer myCluster) throws Exception {

       return null;
    }

}