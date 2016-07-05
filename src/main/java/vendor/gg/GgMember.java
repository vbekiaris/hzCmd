package vendor.gg;

import global.NodeType;
import remote.main.Controler;

public class GgMember {
    public static void main(String[] args) throws Throwable {
        Controler c = new GgControler(NodeType.Member);
        c.run();
    }
}
