package gf;

import global.NodeType;
import remote.Controler;

public class GfClient {
    public static void main(String[] args) throws Throwable {
        Controler c = new GfControler(NodeType.Client);
        c.run();
    }
}
