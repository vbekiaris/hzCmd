package gf;

import global.NodeType;
import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;
import remote.Controler;

public class GfControler extends Controler {


    public GfControler(NodeType type) throws Exception {
        super(type);
    }

    public void init(NodeType type) throws Exception {
        if(type == NodeType.Member){


        }else{

        }
    }

    public Object getVendorObject(){



    }
}