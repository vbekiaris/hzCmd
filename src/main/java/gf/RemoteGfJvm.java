package gf;

import global.NodeType;
import local.Box;
import local.ClusterManager;
import local.RemoteJvm;

import java.io.IOException;


public class RemoteGfJvm extends RemoteJvm {


    public RemoteGfJvm(Box box, NodeType type, String id) throws IOException, InterruptedException {
        super(box, type, id);
    }

    public String getClassToRun() {
        if (isMember()){
            return GfMember.class.getName();
        }
        return GfClient.class.getName();
    }

    public void beforeJvmStart(ClusterManager myCluster) throws Exception {

    }

}