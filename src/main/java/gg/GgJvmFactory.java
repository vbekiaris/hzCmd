package gg;

import global.NodeType;
import hz.HzClient;
import hz.HzMember;
import hz.RemoteHzJvm;
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
public class GgJvmFactory implements JvmFactory, Serializable {

    private static final String ggPath = Installer.REMOTE_HZCMD_ROOT_FULL_PATH+"/"+"gg-lib";

    public String getVendorLibDir(String version) {
        return ggPath+version;
    }

    public List<String> getVendorLibNames(String version) {
        List<String> jars = new ArrayList();

        jars.add("ignite-core-"+version+".jar");

        return jars;
    }

    public RemoteJvm createJvm(Box box, NodeType type, int count, String clusterId) throws IOException, InterruptedException {

        String id;
        if ( type == NodeType.Member ){
            id = GgMember.class.getSimpleName();
        }else {
            id = GgClient.class.getSimpleName();
        }
        id += count+""+clusterId;

        return new RemoteGgJvm(box, type, id);
    }
}
