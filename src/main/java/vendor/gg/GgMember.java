package vendor.gg;

import global.NodeType;
import vendor.hz.HzControler;
import remote.Controler;

public class GgMember {
    public static void main(String[] args) throws Throwable {
        Controler c = new GgControler(NodeType.Member);
        c.run();
    }
}
