package vendor.coherence;

import global.NodeType;
import remote.main.Controler;

public class CoherenceClient {
    public static void main(String[] args) throws Throwable {
        Controler c = new CoherenceControler(NodeType.Client);
        c.run();
    }
}