package local;

import global.NodeType;
import hz.HzJvmFactory;

import java.io.IOException;
import java.util.List;

/**
 * Created by danny on 21/01/2016.
 */
public interface JvmFactory {

    RemoteJvm createJvm(Box box, NodeType type, int count, String clusterId) throws IOException, InterruptedException;

    String getVendorLibDir(String version);

    List<String> getVendorLibNames(String version, boolean ee);
}
