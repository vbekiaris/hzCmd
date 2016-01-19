package remote;

import global.NodeType;

public class Member {
    public static void main(String[] args) throws Throwable {
        Controler c = new HzControler(NodeType.Member);
        c.run();
    }
}
