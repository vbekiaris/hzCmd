package xml;

        import java.io.File;
        import javax.xml.parsers.DocumentBuilder;
        import javax.xml.parsers.DocumentBuilderFactory;
        import javax.xml.transform.Transformer;
        import javax.xml.transform.TransformerFactory;
        import javax.xml.transform.dom.DOMSource;
        import javax.xml.transform.stream.StreamResult;

        import global.Bash;
        import local.Box;
        import local.ClusterManager;
        import org.w3c.dom.Document;
        import org.w3c.dom.Element;

public class HzXml {

    public static final String xmlDir="xml";
    static {
        try {
            Bash.mkdir(xmlDir);
        } catch (Exception e) {
           throw new RuntimeException(e);
        }
    }

    public static final String memberXml = "hazelcast.xml";
    public static final String clientXml = "client-hazelcast.xml";

    public static void makeXml(ClusterManager m) throws Exception{

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(memberXml);


        document.getElementsByTagName("name").item(0).setTextContent(m.getClusterId());

        Element pass = document.createElement("password");
        pass.setTextContent(m.getClusterId());
        document.getElementsByTagName("group").item(0).appendChild(pass);


        for (Box box : m.getBoxManager().getBoxList()) {
            Element member = document.createElement("member");
            member.setTextContent(box.pri);
            document.getElementsByTagName("tcp-ip").item(0).appendChild(member);
        }


        Element wan = document.createElement("wan-replication");
        wan.setAttribute("name", "wanReplication" );

        document.getFirstChild().appendChild(wan);


        document.normalizeDocument();

        TransformerFactory transformerFactory = TransformerFactory.newInstance();

        Transformer transformer = transformerFactory.newTransformer();
        DOMSource domSource = new DOMSource(document);

        StreamResult streamResult = new StreamResult(new File(memberXml(m)));
        transformer.transform(domSource, streamResult);
    }

    public static String memberXml(ClusterManager m ){
        return xmlDir+"/"+m.getClusterId()+"-"+ memberXml;
    }

    public static String clientXml(ClusterManager m ){
        return xmlDir+"/"+m.getClusterId()+"-"+ clientXml;
    }
}