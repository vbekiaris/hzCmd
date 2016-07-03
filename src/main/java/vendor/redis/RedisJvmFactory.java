package vendor.redis;

import global.NodeType;
import local.*;
import local.properties.HzCmdProperties;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by danny on 21/01/2016.
 */
public class RedisJvmFactory implements JvmFactory, Serializable {

    private static final int clusterStartPause=5000;

    private static final String redisPath = Installer.REMOTE_HZCMD_ROOT_FULL_PATH+"/"+"redis-lib";

    public static final int redisMemberPort = 6379;

    public String getVendorLibDir(String version) {
        return redisPath+"/"+version;
    }

    public List<String> getVendorLibNames(String version, boolean ee) {
        List<String> jars = new ArrayList();

        jars.add("jedis-2.8.0.jar");
        jars.add("commons-pool2-2.3.jar");
        return jars;
    }

    public List<String> stuffToUpload(ClusterContainer myCluster) throws Exception{

        List<String> upStuff = new ArrayList<String>();

        return upStuff;
    }

    public void clusterInit(BoxManager boxes) {  }

    public void membersAdded(List<RemoteJvm> memberJmvs) throws IOException, InterruptedException {

        String boxs = new String();
        for (RemoteJvm memberJmv : memberJmvs) {
            boxs += memberJmv.getBox().pri + ":" + redisMemberPort + " ";
        }

        RemoteJvm remoteJvm = memberJmvs.get(0);
        String red = RemoteRedisMember.getRedisInstallHome();

        HzCmdProperties p = new HzCmdProperties();

        String replicasCount=p.readPropertie(HzCmdProperties.redisReplicas, "1");
        String res = remoteJvm.getBox().ssh("echo yes | " + red + "/src/redis-trib.rb create --replicas "+replicasCount+" "+boxs );
        System.out.println(res);

        Thread.sleep(clusterStartPause);
    }


    public RemoteJvm createJvm(Box box, NodeType type, int count, String clusterId) throws IOException, InterruptedException {

        String id;
        if ( type == NodeType.Member ){
            id = RedisMember.class.getSimpleName();
            id += count+""+clusterId;

            return new RemoteRedisMember(box, type, id, clusterId);

        }else {
            id = RedisClient.class.getSimpleName();
            id += count+""+clusterId;

            return new RemoteJedisJvm(box, type, id, clusterId);
        }
    }
}
