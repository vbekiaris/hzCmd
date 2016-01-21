package remote;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.client.config.XmlClientConfigBuilder;
import com.hazelcast.config.Config;
import com.hazelcast.config.XmlConfigBuilder;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import global.NodeType;

public class HzControler extends Controler {

    private HazelcastInstance hazelcastInstance;

    public static final String memberXml = System.getProperty("memberXml", "hazelcast.xml");
    public static final String clientXml = System.getProperty("clientXml", "client-hazelcast.xml");

    public HzControler(NodeType type) throws Exception {
        super(type);
    }

    public void init(NodeType type) throws Exception {
        if (type == NodeType.Member) {
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