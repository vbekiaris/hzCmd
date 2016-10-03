package vendor.coherence;

import global.NodeType;
import remote.main.Controler;

public class CoherenceControler extends Controler {

    //private NamedCache cache = CacheFactory.getCache();

    public CoherenceControler(NodeType type) throws Exception {
        super(type);
    }

    public void init(NodeType type) throws Exception {

    }

    public Object getVendorObject(){
        return null;
    }
}