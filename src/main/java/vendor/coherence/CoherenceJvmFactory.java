package vendor.coherence;

import global.NodeType;
import local.*;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by danny on 21/01/2016.
 */
public class CoherenceJvmFactory implements JvmFactory, Serializable {

    private static final String CO_LIB_PATH_BASE= Installer.REMOTE_HZCMD_ROOT_FULL_PATH+"/"+"coherence-lib";


    public String getVendorLibDir(String version) {
        return CO_LIB_PATH_BASE;
    }

    public List<String> getVendorLibNames(String version, boolean ee) {
        List<String> jars = new ArrayList();

        jars.add("coherence-"+version+".jar");

        return jars;
    }

    public List<String> stuffToUpload(ClusterContainer myCluster) throws Exception{

        CoherenceXml.makeMemberXml(myCluster);

        List<String> upStuff = new ArrayList<String>();



        //upStuff.add("tangosol-coherence-override.xml");
        upStuff.add("coherence-cache-config.xml");
        upStuff.add(CoherenceXml.memberXmlFileForCluster(myCluster));

        return upStuff;
    }

    public void clusterInit(BoxManager boxes) {

    }

    public void membersAdded(List<RemoteJvm> memberJvms) throws IOException, InterruptedException {

    }


    public RemoteJvm createJvm(Box box, NodeType type, int count, String clusterId) throws IOException, InterruptedException {

        String id;
        if ( type == NodeType.Member ){
            id = CoherenceMember.class.getSimpleName();
        }else {
            id = CoherenceClient.class.getSimpleName();
        }
        id += count+""+clusterId;

        return new CoherenceRemoteJvm(box, type, id, type+"-"+clusterId);
    }
}
