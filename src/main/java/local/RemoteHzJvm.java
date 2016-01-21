package local;

import global.NodeType;
import remote.HzClient;
import remote.HzMember;
import xml.HzXml;

import java.io.IOException;


public class RemoteHzJvm extends RemoteJvm {

    public static final String hzPath ="$HOME/"+Installer.REMOTE_HZ_LIB+"/";

    public RemoteHzJvm(Box box, NodeType type, String id) throws IOException, InterruptedException {
        super(box, type, id);
    }

    public String getClassToRun() {
        if (isMember()){
            return HzMember.class.getName();
        }
        return HzClient.class.getName();
    }

    public String getVendorLibDir(String version) {
        return hzPath+version;
    }

    public void beforeJvmStart(ClusterManager myCluster) throws Exception {

        HzXml.makeMemberXml(myCluster);
        HzXml.makeClientXml(myCluster);

        box.upload(HzXml.memberXmlFileForCluster(myCluster), dir+"/"+ HzXml.memberXml);
        box.upload(HzXml.clientXmlFileForCluster(myCluster), dir + "/" + HzXml.clientXml);
    }

}