package vendor.hz;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import global.Bash;
import local.Box;
import local.ClusterContainer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class HzXml {

    public static final String xmlDir="config-hz";

    public static final String memberXml = xmlDir+"/hazelcast.xml";
    public static final String clientXml = xmlDir+"/client-hazelcast.xml";

    public static void makeMemberXml(ClusterContainer m) throws Exception{

        Document document = getDocument(memberXml);

        addGroupElement(m, document);

        for (Box box : m.getBoxManager().getBoxList()) {
            Element member = document.createElement("member");
            member.setTextContent(box.pri);
            document.getElementsByTagName("tcp-ip").item(0).appendChild(member);
        }

        Bash.mkdir(xmlDir+"/"+m.getClusterId());
        writeXmlFile(document, memberXmlFileForCluster(m));
    }


    public static void addManCenter(String file, String ip) throws Exception{

        Document document = getDocument(file);

        Element mc = document.createElement("management-center");
        mc.setAttribute("enabled", "true");
        mc.setTextContent("http://"+ ip +":8080/mancenter");
        document.getFirstChild().appendChild(mc);


        writeXmlFile(document, file);
    }


    /*
    *
    *
    *   <wan-replication name="wanReplication">

        <wan-publisher group-name="group1">
            <class-name>com.hazelcast.enterprise.wan.replication.WanNoDelayReplication</class-name>
            <properties>
            <property name="group.password">pass</property>
            <property name="endpoints">10.0.0.234,10.0.0.233,</property>
            </properties>
        </wan-publisher>

        <wan-publisher group-name="group2">
            <class-name>com.hazelcast.enterprise.wan.replication.WanNoDelayReplication</class-name>
            <properties>
            <property name="group.password">pass</property>
            <property name="endpoints">10.0.0.232,10.0.0.237,</property>
            </properties>
        </wan-publisher>

    </wan-replication>
    *
    *
    * */


    public static void wanReplication(ClusterContainer a,  ClusterContainer b) throws Exception{

        final String classImpleName="com.hazelcast.enterprise.wan.replication.WanNoDelayReplication";

        Document document = getDocument(memberXmlFileForCluster(a));

        Element wanReplication = document.createElement("wan-replication");
        wanReplication.setAttribute("name", "wanReplication");

        Element wanPublisher = document.createElement("wan-publisher");
        wanPublisher.setAttribute("group-name", a.getClusterId()+"-"+b.getClusterId());

        Element className = document.createElement("class-name");
        className.setTextContent(classImpleName);

        Element properties = document.createElement("properties");


        Element propertyPassword = document.createElement("property");
        propertyPassword.setAttribute("name", "group.password");
        propertyPassword.setTextContent(b.getClusterId());

        Element propertyEndpoints = document.createElement("property");
        propertyEndpoints.setAttribute("name", "endpoints");
        propertyEndpoints.setTextContent(b.getPublicMemberIps());


        wanReplication.appendChild(wanPublisher);
        wanPublisher.appendChild(className);
        wanPublisher.appendChild(properties);
        properties.appendChild(propertyPassword);
        properties.appendChild(propertyEndpoints);

        document.getFirstChild().appendChild(wanReplication);

        writeXmlFile(document, memberXmlFileForCluster(a));
    }



    /*
    <wan-replication name="wanReplication">
        <target-cluster group-name="group2" group-password="pass">
            <replication-impl>
                com.hazelcast.enterprise.wan.replication.WanNoDelayReplication
            </replication-impl>
            <end-points>
                <address>10.0.0.162</address>
                <address>10.0.0.163</address>
            </end-points>
        </target-cluster>
        <target-cluster group-name="group3" group-password="pass">
            <replication-impl>
                com.hazelcast.enterprise.wan.replication.WanNoDelayReplication
            </replication-impl>
            <end-points>
                <address>10.0.0.161</address>
                <address>10.0.0.166</address>
            </end-points>
        </target-cluster>
    </wan-replication>
    */

    public static void makeClientXml(ClusterContainer m) throws Exception{

        Document document = getDocument(clientXml);

        addGroupElement(m, document);

        for (Box box : m.getBoxManager().getBoxList()) {
            Element member = document.createElement("address");
            member.setTextContent(box.pri);
            document.getElementsByTagName("cluster-members").item(0).appendChild(member);
        }

        Bash.mkdir(xmlDir+"/"+m.getClusterId());
        writeXmlFile(document, clientXmlFileForCluster(m));
    }


    private static void addGroupElement(ClusterContainer m, Document document) {
        document.getElementsByTagName("name").item(0).setTextContent(m.getClusterId());
        Element pass = document.createElement("password");
        pass.setTextContent(m.getClusterId());
        document.getElementsByTagName("group").item(0).appendChild(pass);
    }

    private static Document getDocument(String file) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        return documentBuilder.parse(file);
    }

    private static void writeXmlFile(Document document, String file) throws TransformerException {
        document.normalizeDocument();
        TransformerFactory transformerFactory = TransformerFactory.newInstance();

        Transformer transformer = transformerFactory.newTransformer();
        DOMSource domSource = new DOMSource(document);

        StreamResult streamResult = new StreamResult(new File(file));
        transformer.transform(domSource, streamResult);
    }

    public static String memberXmlFileForCluster(ClusterContainer m ){
        return xmlDir+"/"+m.getClusterId()+"/"+ "hazelcast.xml";
    }

    public static String clientXmlFileForCluster(ClusterContainer m ){
        return xmlDir+"/"+m.getClusterId()+"/"+ "client-hazelcast.xml";
    }
}