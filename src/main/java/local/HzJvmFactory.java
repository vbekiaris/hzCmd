package local;

import global.NodeType;

/**
 * Created by danny on 21/01/2016.
 */
public class HzJvmFactory implements JvmFactory {


    public RemoteJvm createJvm(Box box, NodeType type, String id) {
        return new RemoteHzJvm(box, type, id);
    }
}
