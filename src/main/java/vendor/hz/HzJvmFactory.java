package vendor.hz;

import global.NodeType;
import local.*;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by danny on 21/01/2016.
 */
public class HzJvmFactory implements JvmFactory, Serializable {

    private static final String HZ_LIB_PATH_BASE= Installer.REMOTE_HZCMD_ROOT_FULL_PATH+"/"+"hz-lib";

    private static String hazelcast = "hazelcast-";
    private static String hazelcastEE = hazelcast+"enterprise-";

    private static String hazelcastClient = hazelcast+"client-";
    private static String hazelcastClientEE = hazelcastEE+"client-";



    public String getVendorLibDir(String version) {
        return HZ_LIB_PATH_BASE+"/"+version;
    }

    public List<String> getVendorLibNames(String version, boolean ee) {
        List<String> jars = new ArrayList();

        if(ee){
            jars.add(hazelcastEE + version + ".jar");
            jars.add(hazelcastClientEE + version + ".jar");
        }else {
            jars.add(hazelcast + version + ".jar");
            jars.add(hazelcastClient + version + ".jar");
        }
        return jars;
    }

    public void clusterInit(BoxManager boxes) {

    }

    public void membersAdded(List<RemoteJvm> memberJvms) throws IOException, InterruptedException {

    }


    public RemoteJvm createJvm(Box box, NodeType type, int count, String clusterId) throws IOException, InterruptedException {

        String id;
        if ( type == NodeType.Member ){
            id = HzMember.class.getSimpleName();
        }else {
            id = HzClient.class.getSimpleName();
        }
        id += count+""+clusterId;

        return new RemoteHzJvm(box, type, id, type+"-"+clusterId);
    }
}
