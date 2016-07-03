package vendor.gg;

import local.Box;
import local.ClusterContainer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.IOException;

public class GgXml {

    public static final String ggXml = "config-gg/gg-config.xml";

    public static void addServerIpsToGgXml(ClusterContainer m) throws Exception{

        String addressPath = "//property[@name='addresses']/list";

        Document document = getDocument(ggXml);

        XPath xPath = XPathFactory.newInstance().newXPath();
        NodeList nodes = (NodeList)xPath.evaluate(addressPath, document.getDocumentElement(), XPathConstants.NODESET);
        Node node = nodes.item(0);

        for (Box box : m.getBoxManager().getBoxList()) {
            Element member = document.createElement("value");
            member.setTextContent(box.pri);
            node.appendChild(member);
        }

        String clusterId = m.getClusterId();
        writeXmlFile(document, "config-gg/"+clusterId+"gg-config.xml");
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

}