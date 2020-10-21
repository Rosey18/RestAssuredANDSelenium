import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPathExpressionException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Simple {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, XMLStreamException, TransformerException, XPathExpressionException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.newDocument();
        Element root = document.createElement("DataClass");
        Element font = document.createElement("field_name");
        Element font1 = document.createElement("field_name");
        Element font2 = document.createElement("field_name");
        Element font3 = document.createElement("field_name");
        Element font4 = document.createElement("field_name");
        Text text = document.createTextNode("00122");
        Text text1 = document.createTextNode("03");
        Text text2 = document.createTextNode("жеке ќосалќы шаруашылыѓын ж‰ргізу ‰шін");
        Text text3 = document.createTextNode("ведение личного подсобного хозяйства");
        Text text4 = document.createTextNode("02003000");
        document.appendChild(root);
        root.appendChild(font);
        root.appendChild(font1);
        root.appendChild(font2);
        root.appendChild(font3);
        root.appendChild(font4);
        font.appendChild(text);
        font1.appendChild(text1);
        font2.appendChild(text2);
        font3.appendChild(text3);
        font4.appendChild(text4);

        Transformer t = TransformerFactory.newInstance().newTransformer();
        t.setOutputProperty(OutputKeys.INDENT, "yes");
        t.transform(new DOMSource(document), new StreamResult(new FileOutputStream("temp.xml")));
    }
}
