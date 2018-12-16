package dmcs.excercise.xml;
import javax.xml.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.net.URI;

public class XmlParser {

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        XmlParser xmlParser = new XmlParser("C:\\Users\\howor\\IdeaProjects\\HorizontalOptimizationEx\\recursive\\src\\main\\resources\\example.xml");
    }

    public XmlParser(String filePath) throws ParserConfigurationException, IOException, SAXException {
        File file = new File(filePath);
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(file);
        parseRecursive(document);
    }

    private void parseRecursive(Node node) {
        System.out.println(node.getNodeName());
        NodeList childNodes = node.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            parseRecursive(childNodes.item(i));
        }
    }
}
