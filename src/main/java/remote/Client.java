package remote;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.client.config.XmlClientConfigBuilder;
import com.hazelcast.config.Config;
import com.hazelcast.config.XmlConfigBuilder;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import local.JvmType;

import java.io.IOException;

/**
 * Created by danny on 8/29/15.
 */
public class Client {

    public static void main(String[] args) throws InterruptedException, IOException {

        Controler c = new Controler(JvmType.hz.Client);
        c.run();
    }
}