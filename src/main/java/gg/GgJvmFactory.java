package gg;

import global.NodeType;
import hz.HzClient;
import hz.HzMember;
import hz.RemoteHzJvm;
import local.Box;
import local.JvmFactory;
import local.RemoteJvm;

import java.io.IOException;
import java.io.Serializable;

/**
 * Created by danny on 21/01/2016.
 */
public class GgJvmFactory implements JvmFactory, Serializable {

    public RemoteJvm createJvm(Box box, NodeType type, int count, String clusterId) throws IOException, InterruptedException {

        String id;
        if ( type == NodeType.Member ){
            id = GgMember.class.getSimpleName();
        }else {
            id = GgClient.class.getSimpleName();
        }
        id += count+""+clusterId;

        return new RemoteGgJvm(box, type, id);
    }
}
