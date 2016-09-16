package vendor.redis;

import global.NodeType;
import local.Box;
import local.ClusterContainer;
import local.RemoteJvm;

import java.io.IOException;
import java.util.Collections;
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
        return Collections.emptyList();
    }

    public void beforeJvmStart(ClusterContainer myCluster) throws Exception {

    }

    @Override
    public String setJvmStartOptions(Box thisBox, ClusterContainer myCluster) throws Exception {

       return null;
    }

}