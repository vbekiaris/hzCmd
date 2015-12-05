package remote;

import com.hazelcast.config.Config;
import com.hazelcast.config.XmlConfigBuilder;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import local.JvmType;

import java.io.FileNotFoundException;

public class Member {

    public static void main(String[] args) throws InterruptedException, FileNotFoundException {

        Controler c = new Controler(JvmType.hz.Member);
        c.run();
    }
}
