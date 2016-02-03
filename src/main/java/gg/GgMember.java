package gg;

import global.NodeType;
import hz.HzControler;
import remote.Controler;

public class GgMember {
    public static void main(String[] args) throws Throwable {
        Controler c = new GgControler(NodeType.Member);
        c.run();
    }
}
