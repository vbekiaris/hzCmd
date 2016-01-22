package hz;

import global.NodeType;
import local.Box;
import local.JvmFactory;
import local.RemoteJvm;

import java.io.IOException;
import java.io.Serializable;

/**
 * Created by danny on 21/01/2016.
 */
public class HzJvmFactory implements JvmFactory, Serializable {

    public RemoteJvm createJvm(Box box, NodeType type, int count, String clusterId) throws IOException, InterruptedException {

        String id;
        if ( type == NodeType.Member ){
            id = HzMember.class.getSimpleName();
        }else {
            id = HzClient.class.getSimpleName();
        }
        id += count+""+clusterId;


        return new RemoteHzJvm(box, type, id);

    }
}
