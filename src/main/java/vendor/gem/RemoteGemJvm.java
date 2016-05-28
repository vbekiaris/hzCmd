package vendor.gem;

import global.NodeType;
import local.Box;
import local.ClusterManager;
import local.RemoteJvm;

import java.io.IOException;
import java.util.List;


public class RemoteGemJvm extends RemoteJvm {

    private static final int gemFireLocatorPort=11001;

    public RemoteGemJvm(Box box, NodeType type, String id, String clusterId) throws IOException, InterruptedException {
        super(box, type, id, clusterId);
    }

    public String getClassToRun() {
        if (isMember()){
            return GemMember.class.getName();
        }
        return GemClient.class.getName();
    }

    @Override
    public List<String> stuffToPutInDir() throws Exception {
        return null;
    }

    public void beforeJvmStart(ClusterManager myCluster) throws Exception{

        if(isMember()) {
            box.upload("config-gem/server-cache.xml", dir + "/");
        }else {
            box.upload("config-gem/client-cache.xml", dir + "/");
        }
    }

    @Override
    public String setJvmStartOptions(Box thisBox, ClusterManager myCluster) throws Exception {

        StringBuilder jvmArgs = new StringBuilder();
        jvmArgs.append("-D" +"MY_PUB_IP=" + box.pub + " ");
        jvmArgs.append("-D"+"MY_PRI_IP="+box.pri+" ");

        List<Box> peers = myCluster.getBoxManager().getBoxListExcluding(thisBox);

        jvmArgs.append("-D"+"MY_PEERS_LIST=");
        if(isMember()){
            for (Box peer : peers) {
                jvmArgs.append(peer.pri+"["+gemFireLocatorPort+"]"+",");
            }
        }else{
            for (Box peer : peers) {
                jvmArgs.append(peer.pri+",");
            }
        }
        jvmArgs.deleteCharAt(jvmArgs.length()-1);

        return jvmArgs.toString();
    }

}