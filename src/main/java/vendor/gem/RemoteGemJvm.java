package vendor.gem;

import global.NodeType;
import local.Box;
import local.ClusterManager;
import local.RemoteJvm;

import java.io.IOException;


public class RemoteGemJvm extends RemoteJvm {


    public RemoteGemJvm(Box box, NodeType type, String id) throws IOException, InterruptedException {
        super(box, type, id);
    }

    public String getClassToRun() {
        if (isMember()){
            return GemMember.class.getName();
        }
        return GemClient.class.getName();
    }

    public void beforeJvmStart(ClusterManager myCluster) throws Exception {

        if(isMember()) {
            box.upload("server-cache.xml", dir + "/");
        }else {
            box.upload("client-cache.xml", dir + "/");
        }
    }

}