package vendor.gg;

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
public class GgJvmFactory implements JvmFactory, Serializable {

    private static final String ggPath = Installer.REMOTE_HZCMD_ROOT_FULL_PATH+"/"+"gg-lib";

    public String getVendorLibDir(String version) {
        return ggPath+"/"+version;
    }

    public List<String> getVendorLibNames(String version, boolean ee) {
        List<String> jars = new ArrayList();

        jars.add("ignite-core-"+version+".jar");
        jars.add("ignite-spring-"+version+".jar");
        jars.add("ignite-indexing-"+version+".jar");

        jars.add("spring-aop-4.1.0.RELEASE.jar");
        jars.add("h2-1.3.175.jar");


        return jars;
    }

    public List<String> stuffToUpload(ClusterManager myCluster) throws Exception{
        List<String> upStuff = new ArrayList<String>();

        return upStuff;
    }

    public void clusterInit(BoxManager boxes) {

    }

    public void membersAdded(List<RemoteJvm> memberJvms) throws IOException, InterruptedException {

    }

    public RemoteJvm createJvm(Box box, NodeType type, int count, String clusterId) throws IOException, InterruptedException {

        String id;
        if ( type == NodeType.Member ){
            id = GgMember.class.getSimpleName();
        }else {
            id = GgClient.class.getSimpleName();
        }
        id += count+""+clusterId;

        return new RemoteGgJvm(box, type, id, type+"-"+clusterId);
    }
}
