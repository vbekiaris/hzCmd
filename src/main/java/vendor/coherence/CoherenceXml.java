package vendor.coherence;

import global.Bash;
import local.Box;
import local.ClusterContainer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

public class CoherenceXml {

    public static final String xmlDir="config-co";

    public static final String FILE = "tangosol-coherence-override.xml";

    public static final String Xml = xmlDir+"/"+FILE;

    public static void makeMemberXml(ClusterContainer m) throws Exception{

        Document document = getDocument(Xml);

        int id=1;
        for (Box box : m.getBoxManager().getBoxList()) {

            Element address = document.createElement("address");
            address.setTextContent(box.pri);

            Element port = document.createElement("port");
            address.setTextContent("8088");

            Element socket = document.createElement("socket-address");
            socket.setAttribute("id", id+"");
            id++;
            socket.appendChild(address);
            socket.appendChild(port);

            document.getElementsByTagName("well-known-addresses").item(0).appendChild(socket);
        }

        Bash.mkdir(xmlDir+"/"+m.getClusterId());
        writeXmlFile(document, memberXmlFileForCluster(m));
    }


    public static Document getDocument(String file) throws ParserConfigurationException, SAXException, IOException {
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
        return xmlDir+"/"+m.getClusterId()+"/"+ FILE;
    }

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException, TransformerException {


        Document document = getDocument(FILE);

        int id=1;

            Element address = document.createElement("address");
            address.setTextContent("0.0.0.0.1");

            Element socket = document.createElement("socket-address");
            socket.setAttribute("id", id+"");
            id++;
            socket.appendChild(address);

            document.getElementsByTagName("well-known-addresses").item(0).appendChild(socket);


       writeXmlFile(document, "res.txt");

    }

}