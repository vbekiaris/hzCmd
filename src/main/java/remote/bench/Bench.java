package remote.bench;

public interface Bench {
    void init();
    void timeStep()throws Exception;
    void cleanup();

    void setVendorObject(Object vendorObject);
}
