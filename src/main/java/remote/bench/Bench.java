package remote.bench;

import java.util.List;

public interface Bench {
    void init() throws Exception;
    void timeStep()throws Exception;
    void postPhase();

    List<Class> ignore();


    void setRunning(boolean running);
    boolean isRunning();

    void setVendorObject(Object vendorObject);
}
