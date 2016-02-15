package vendor.redis;

import global.NodeType;
import remote.Controler;

public class RedisClient {
    public static void main(String[] args) throws Throwable {
        Controler c = new RedisControler(NodeType.Client);
        c.run();
    }
}