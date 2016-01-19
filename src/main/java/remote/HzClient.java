package remote;

import global.NodeType;

public class HzClient {
    public static void main(String[] args) throws Throwable {
        Controler c = new HzControler(NodeType.Client);
        c.run();
    }
}