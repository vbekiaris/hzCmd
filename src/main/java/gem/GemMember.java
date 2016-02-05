package gem;

import global.NodeType;
import remote.Controler;

public class GemMember {
    public static void main(String[] args) throws Throwable {
        Controler c = new GemControler(NodeType.Member);
        c.run();
    }
}
