package vendor.gg;

import global.NodeType;
import remote.main.Controler;

public class GgClient {
    public static void main(String[] args) throws Throwable {
        Controler c = new GgControler(NodeType.Client);
        c.run();
    }
}
