import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, XMLStreamException, TransformerException, XPathExpressionException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File("C:\\Users\\Тигр\\IdeaProjects\\project on java\\xml\\00122.xml"));

        XPathFactory xPathFactory = XPathFactory.newInstance();
        XPath xpath = xPathFactory.newXPath();
        System.out.println(xpath.evaluate("/request/dataRecord/dataClass/record/field", document));
        System.out.println(xpath.evaluate("/request/dataRecord/dataClass/record/field[2]", document));
        System.out.println(xpath.evaluate("/request/dataRecord/dataClass/record/field[3]", document));
        System.out.println(xpath.evaluate("/request/dataRecord/dataClass/record/field[4]", document));
        System.out.println(xpath.evaluate("/request/dataRecord/dataClass/record/field[5]", document));
    }
}
