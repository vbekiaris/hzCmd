package hz;

import global.NodeType;
import remote.Controler;

public class HzMember {
    public static void main(String[] args) throws Throwable {
        Controler c = new HzControler(NodeType.Member);
        c.run();
    }
}
