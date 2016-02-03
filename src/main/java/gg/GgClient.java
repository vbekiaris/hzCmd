package gg;

import global.NodeType;
import remote.Controler;

public class GgClient {
    public static void main(String[] args) throws Throwable {
        Controler c = new GgControler(NodeType.Client);
        c.run();
    }
}
