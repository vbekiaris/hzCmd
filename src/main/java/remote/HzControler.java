package remote;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.client.config.XmlClientConfigBuilder;
import com.hazelcast.config.Config;
import com.hazelcast.config.XmlConfigBuilder;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import global.HzType;

public class HzControler extends Controler {

    private HazelcastInstance hazelcastInstance;

    public static final String memberXml = System.getProperty("memberXml", "hazelcast.xml");
    public static final String clientXml = System.getProperty("memberXml", "client-hazelcast.xml");

    public HzControler(HzType type) throws Exception {
        super(type);
    }

    public void init(HzType type) throws Exception {
        if (type == HzType.Member) {
            XmlConfigBuilder configBuilder = new XmlConfigBuilder(memberXml);
            Config config = configBuilder.build();
            hazelcastInstance = Hazelcast.newHazelcastInstance(config);
        } else {
            XmlClientConfigBuilder configBuilder = new XmlClientConfigBuilder(clientXml);
            ClientConfig config = configBuilder.build();
            hazelcastInstance = HazelcastClient.newHazelcastClient(config);
        }
        tasks = new TaskManager(hazelcastInstance);
    }

}