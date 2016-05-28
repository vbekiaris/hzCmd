package vendor.redis;

import global.NodeType;
import local.Box;
import local.ClusterManager;
import local.RemoteJvm;

import java.io.IOException;
import java.util.List;


public class RemoteJedisJvm extends RemoteJvm {

    public RemoteJedisJvm(Box box, NodeType type, String id, String clusterId) throws IOException, InterruptedException {
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

    public void beforeJvmStart(ClusterManager myCluster) throws Exception {

    }

    @Override
    public String setJvmStartOptions(Box thisBox, ClusterManager myCluster) throws Exception {

       return null;
    }

}