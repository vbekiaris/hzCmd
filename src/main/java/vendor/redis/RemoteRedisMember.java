package vendor.redis;

import global.Args;
import global.Bash;
import global.NodeType;
import local.Box;
import local.ClusterManager;
import local.Installer;
import local.RemoteJvm;

import java.io.IOException;


public class RemoteRedisMember extends RemoteJvm {

    public RemoteRedisMember(Box box, NodeType type, String id) throws IOException, InterruptedException {
        super(box, type, id);
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


    public final void startJvm(String jvmOptions, String vendorLibDir, ClusterManager myCluster, String brokerIP) throws Exception {

        if (isRunning()) {
            System.out.println(Bash.ANSI_CYAN+"all ready started " + this +Bash.ANSI_RESET);
            return;
        }

        beforeJvmStart(myCluster);

        String version="3.0.7";

        System.out.println( box.ssh("sudo yum install -y expect") );
        System.out.println( box.ssh("sudo yum install -y gcc-c++") );
        System.out.println( box.ssh("wget http://download.redis.io/releases/redis-" + version + ".tar.gz") );
        System.out.println( box.ssh("tar xzf redis-" + version + ".tar.gz") );

        System.out.println( box.ssh("sudo make -C redis-"+version+"/deps lua hiredis linenoise") );
        System.out.println( box.ssh("sudo make -C redis-"+version+" MALLOC=libc install") );

        System.out.println( box.ssh("gem install redis") );



        System.out.println("nohup cd " +dir+";" + " redis-server >> out.txt 2>&1 & echo $!");
        String pidStr = box.ssh("nohup cd " +dir+";" + " redis-server >> out.txt 2>&1 & echo $!");
        pid = Integer.parseInt(pidStr.trim());

        System.out.println("redis-server pid="+pid);
    }



    @Override
    public String setJvmStartOptions(Box thisBox, ClusterManager myCluster) throws Exception {

       return null;
    }

}