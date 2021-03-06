package vendor.gg;

import global.NodeType;
import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;
import remote.main.Controler;

public class GgControler extends Controler {

    private Ignite ignite;

    public GgControler(NodeType type) throws Exception {
        super(type);
    }

    public void init(NodeType type) throws Exception {
        if(type == NodeType.Member){
            ignite = Ignition.start("gg-config.xml");
        }else{
            Ignition.setClientMode(true);
            ignite = Ignition.start("gg-config.xml");
        }
    }

    public Object getVendorObject(){
        return ignite;
    }
}