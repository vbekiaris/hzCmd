package gf;

import global.NodeType;
import remote.Controler;

public class GfMember {
    public static void main(String[] args) throws Throwable {
        Controler c = new GfControler(NodeType.Member);
        c.run();
    }
}
