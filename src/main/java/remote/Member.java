package remote;

import com.hazelcast.config.Config;
import com.hazelcast.config.XmlConfigBuilder;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

import java.io.FileNotFoundException;

public class Member {

    public static void main(String[] args) throws InterruptedException, FileNotFoundException {

        XmlConfigBuilder configBuilder = new XmlConfigBuilder("hazelcast.xml");
        Config config = configBuilder.build();
        HazelcastInstance server = Hazelcast.newHazelcastInstance(config);

        Controler c = new Controler(server);
        c.run();
    }
}
