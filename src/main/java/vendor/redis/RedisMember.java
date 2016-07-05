package vendor.redis;

import global.NodeType;
import remote.main.Controler;

public class RedisMember {
    public static void main(String[] args) throws Throwable {
        Controler c = new RedisControler(NodeType.Member);
        c.run();
    }
}
