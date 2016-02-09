package vendor.gem;

import global.NodeType;
import local.Box;
import local.ClusterManager;
import local.RemoteJvm;

import java.io.IOException;
import java.util.List;


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

    @Override
    public String setJvmStartOptions(Box thisBox, ClusterManager myCluster) throws Exception {

        if(isClient()){
            return null;
        }

        String jvmArgs = new String();

        jvmArgs += "-D"+"MY_PUB_IP="+box.pub+" ";
        jvmArgs += "-D"+"MY_PRI_IP="+box.pri+" ";

        List<Box> peers = myCluster.getBoxManager().getBoxListExcluding(thisBox);

        jvmArgs += "-D"+"MY_PEERS_LIST=";
        for (Box peer : peers) {
            jvmArgs += peer.pri+",";
        }

        return jvmArgs;
    }


}