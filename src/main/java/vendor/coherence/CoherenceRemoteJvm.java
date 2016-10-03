package vendor.coherence;

import global.NodeType;
import local.Box;
import local.ClusterContainer;
import local.RemoteJvm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class CoherenceRemoteJvm extends RemoteJvm {

    public CoherenceRemoteJvm(Box box, NodeType type, String id, String clusterId) throws IOException, InterruptedException {
        super(box, type, id, clusterId);
    }

    public String getClassToRun() {
        if (isMember()){
            return CoherenceMember.class.getName();
        }
        return CoherenceClient.class.getName();
    }


    public List<String> stuffToPutInDir() throws Exception{

        List<String> upStuff = new ArrayList<String>();

        upStuff.add("tangosol-coherence-override-dev.xml");
        upStuff.add("coherence-cache-config.xml");
        return upStuff;
    }

    public void beforeJvmStart(ClusterContainer myCluster) throws Exception{

    }

    public String setJvmStartOptions(Box thisBox, ClusterContainer myCluster) throws Exception {
        return null;
    }
}