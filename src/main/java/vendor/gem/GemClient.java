package vendor.gem;

import global.NodeType;
import remote.Controler;

public class GemClient {
    public static void main(String[] args) throws Throwable {
        Controler c = new GemControler(NodeType.Client);
        c.run();
    }
}
