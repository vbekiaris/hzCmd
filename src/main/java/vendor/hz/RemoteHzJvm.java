package vendor.hz;

import global.NodeType;
import local.Box;
import local.ClusterManager;
import local.RemoteJvm;

import java.io.IOException;


public class RemoteHzJvm extends RemoteJvm {

    public RemoteHzJvm(Box box, NodeType type, String id) throws IOException, InterruptedException {
        super(box, type, id);
    }

    public String getClassToRun() {
        if (isMember()){
            return HzMember.class.getName();
        }
        return HzClient.class.getName();
    }

    public void beforeJvmStart(ClusterManager myCluster) throws Exception {

        HzXml.makeMemberXml(myCluster);
        HzXml.makeClientXml(myCluster);

        if ( isMember() ) {
            box.upload(HzXml.memberXmlFileForCluster(myCluster), dir + "/" + "hazelcast.xml");
        }else {
            box.upload(HzXml.clientXmlFileForCluster(myCluster), dir + "/" + "client-hazelcast.xml");
        }
    }

    public String setJvmStartOptions(Box thisBox, ClusterManager myCluster) throws Exception {
        return null;
    }
}