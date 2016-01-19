package remote;

import global.NodeType;

public class Client {
    public static void main(String[] args) throws Throwable {
        Controler c = new HzControler(NodeType.Client);
        c.run();
    }
}