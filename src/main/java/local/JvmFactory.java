package local;

import global.NodeType;
import vendor.hz.HzJvmFactory;

import java.io.IOException;
import java.util.List;

/**
 * Created by danny on 21/01/2016.
 */
public interface JvmFactory {

    RemoteJvm createJvm(Box box, NodeType type, int count, String clusterId) throws IOException, InterruptedException;

    String getVendorLibDir(String version);

    List<String> getVendorLibNames(String version, boolean ee);

    void clusterInit(BoxManager boxes);

    void membersAdded(List<RemoteJvm> memberJvms) throws IOException, InterruptedException;
}
