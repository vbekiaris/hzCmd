package local;

import global.NodeType;

import java.io.IOException;

/**
 * Created by danny on 21/01/2016.
 */
public interface JvmFactory {

    RemoteJvm createJvm(Box box, NodeType type, int count, String clusterId) throws IOException, InterruptedException;

}
