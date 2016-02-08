package vendor.gg;

import global.NodeType;
import local.Box;
import local.ClusterManager;
import local.RemoteJvm;

import java.io.IOException;


public class RemoteGgJvm extends RemoteJvm {


    public RemoteGgJvm(Box box, NodeType type, String id) throws IOException, InterruptedException {
        super(box, type, id);
    }

    public String getClassToRun() {
        if (isMember()){
            return GgMember.class.getName();
        }
        return GgClient.class.getName();
    }

    public void beforeJvmStart(ClusterManager myCluster) throws Exception {
        box.upload("gg-config.xml", dir+"/");
        //box.upload("ignite-base-config.xml", dir+"/");
        box.mkdir(dir + "/" +"config");
        box.upload("java.util.logging.properties", dir+"/"+"config");

    }

}