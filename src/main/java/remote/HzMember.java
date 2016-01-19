package remote;

import global.NodeType;

public class HzMember {
    public static void main(String[] args) throws Throwable {
        Controler c = new HzControler(NodeType.Member);
        c.run();
    }
}
