package vendor.redis;

import global.NodeType;
import local.*;
import vendor.hz.HzClient;
import vendor.hz.HzMember;
import vendor.hz.RemoteHzJvm;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by danny on 21/01/2016.
 */
public class RedisJvmFactory implements JvmFactory, Serializable {

    private static final String redisPath = Installer.REMOTE_HZCMD_ROOT_FULL_PATH+"/"+"redis-lib";


    public String getVendorLibDir(String version) {
        return redisPath+"/"+version;
    }

    public List<String> getVendorLibNames(String version, boolean ee) {
        List<String> jars = new ArrayList();

        jars.add("embedded-redis-0.6.jar");
        jars.add("commons-io-2.3.jar");
        return jars;
    }

    public void clusterInit(BoxManager boxes) {  }


    public RemoteJvm createJvm(Box box, NodeType type, int count, String clusterId) throws IOException, InterruptedException {

        String id;
        if ( type == NodeType.Member ){
            id = RedisMember.class.getSimpleName();
            id += count+""+clusterId;

            return new RemoteRedisMember(box, type, id);

        }else {
            id = RedisClient.class.getSimpleName();
            id += count+""+clusterId;

            return new RemoteRedisJvm(box, type, id);
        }
    }
}
