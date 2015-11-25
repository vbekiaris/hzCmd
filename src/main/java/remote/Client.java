package remote;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.client.config.XmlClientConfigBuilder;
import com.hazelcast.config.Config;
import com.hazelcast.config.XmlConfigBuilder;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

import java.io.IOException;

/**
 * Created by danny on 8/29/15.
 */
public class Client {

    public static void main(String[] args) throws InterruptedException, IOException {

        XmlClientConfigBuilder configBuilder = new XmlClientConfigBuilder("client-hazelcast.xml");
        ClientConfig config = configBuilder.build();
        HazelcastInstance client = HazelcastClient.newHazelcastClient(config);

        Controler c = new Controler(client);
        c.run();
    }
}