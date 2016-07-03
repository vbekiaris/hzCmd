package vendor.hz;

import global.NodeType;
import local.Box;
import local.ClusterContainer;
import local.RemoteJvm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class RemoteHzJvm extends RemoteJvm {

    public RemoteHzJvm(Box box, NodeType type, String id, String clusterId) throws IOException, InterruptedException {
        super(box, type, id, clusterId);
    }

    public String getClassToRun() {
        if (isMember()){
            return HzMember.class.getName();
        }
        return HzClient.class.getName();
    }


    public List<String> stuffToPutInDir() throws Exception{

        List<String> upStuff = new ArrayList<String>();

        if ( isMember() ) {
            upStuff.add("hazelcast.xml");
        }else {
            upStuff.add("client-hazelcast.xml");
        }

        return upStuff;
    }

    public void beforeJvmStart(ClusterContainer myCluster) throws Exception{

    }

    public String setJvmStartOptions(Box thisBox, ClusterContainer myCluster) throws Exception {
        return null;
    }
}