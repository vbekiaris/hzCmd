package gg;

import global.NodeType;
import hz.HzClient;
import hz.HzMember;
import local.Box;
import local.ClusterManager;
import local.Installer;
import local.RemoteJvm;
import xml.HzXml;

import java.io.IOException;


public class RemoteGgJvm extends RemoteJvm {

    public static final String ggPath ="$HOME/"+ Installer.REMOTE_GG_LIB+"/";

    public RemoteGgJvm(Box box, NodeType type, String id) throws IOException, InterruptedException {
        super(box, type, id);
    }

    public String getClassToRun() {
        if (isMember()){
            return GgMember.class.getName();
        }
        return GgClient.class.getName();
    }

    public String getVendorLibDir(String version) {
        System.out.println("gg lib dir = "+ ggPath+version);
        return ggPath+version;
    }

    public void beforeJvmStart(ClusterManager myCluster) throws Exception {

    }

}