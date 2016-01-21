package hz;

import global.NodeType;
import remote.Controler;

public class HzClient {
    public static void main(String[] args) throws Throwable {
        Controler c = new HzControler(NodeType.Client);
        c.run();
    }
}