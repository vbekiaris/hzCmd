package local;

import global.NodeType;

import java.io.IOException;
import java.io.Serializable;

/**
 * Created by danny on 21/01/2016.
 */
public class HzJvmFactory implements JvmFactory, Serializable {

    public RemoteJvm createJvm(Box box, NodeType type, String id) throws IOException, InterruptedException {

        return new RemoteHzJvm(box, type, id);

    }
}
