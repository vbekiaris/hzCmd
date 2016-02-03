package gf;

import global.NodeType;
import local.Box;
import local.Installer;
import local.JvmFactory;
import local.RemoteJvm;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by danny on 21/01/2016.
 */
public class GfJvmFactory implements JvmFactory, Serializable {

    private static final String ggPath = Installer.REMOTE_HZCMD_ROOT_FULL_PATH+"/"+"gemfire-lib";

    public String getVendorLibDir(String version) {
        return ggPath+"/"+version;
    }

    public List<String> getVendorLibNames(String version, boolean ee) {
        List<String> jars = new ArrayList();

        //8.2.0
        jars.add("gemfire-"+version+".jar");

        return jars;
    }

    public RemoteJvm createJvm(Box box, NodeType type, int count, String clusterId) throws IOException, InterruptedException {

        String id;
        if ( type == NodeType.Member ){
            id = GfMember.class.getSimpleName();
        }else {
            id = GfClient.class.getSimpleName();
        }
        id += count+""+clusterId;

        return new RemoteGfJvm(box, type, id);
    }
}
