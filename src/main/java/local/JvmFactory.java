package local;

import global.NodeType;

/**
 * Created by danny on 21/01/2016.
 */
public interface JvmFactory {

    RemoteJvm createJvm(Box box, NodeType type, String id);

}
