package vendor.gg;

import global.NodeType;
import local.Box;
import local.ClusterManager;
import local.RemoteJvm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static vendor.gg.GgXml.addServerIpsToGgXml;


public class RemoteGgJvm extends RemoteJvm {


    public RemoteGgJvm(Box box, NodeType type, String id, String clusterId) throws IOException, InterruptedException {
        super(box, type, id, clusterId);
    }

    public String getClassToRun() {
        if (isMember()){
            return GgMember.class.getName();
        }
        return GgClient.class.getName();
    }

    @Override
    public List<String> stuffToPutInDir() throws Exception {
        return new ArrayList<String>(0);
    }

    public void beforeJvmStart(ClusterManager myCluster) throws Exception{
        addServerIpsToGgXml(myCluster);

        box.upload("config-gg/"+myCluster.getClusterId()+"gg-config.xml", dir+"/"+"gg-config.xml");
        box.mkdir(dir + "/" +"config");
        box.upload("config-gg/java.util.logging.properties", dir+"/"+"config"+"/");
    }

    public String setJvmStartOptions(Box thisBox, ClusterManager myCluster) throws Exception {
        return null;
    }
}