package DataContainer;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import credentials.RestLogin;

import java.io.File;

public class CreateXmlFileDemo {

	public static void main(String argv[]) {

		try {

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.newDocument();

			// root element
			Element rootElement = doc.createElement("metadata");
			doc.appendChild(rootElement);

			// supercars element
			Element customobject = doc.createElement("customobject");
			rootElement.appendChild(customobject);

			// carname element
			Element name = doc.createElement("name");
			name.appendChild(doc.createTextNode("Book__c"));
			customobject.appendChild(name);

			Element type = doc.createElement("type");
			type.appendChild(doc.createTextNode("String"));
			customobject.appendChild(type);

			Element stdrootElement = doc.createElement("stdbjects");
			rootElement.appendChild(stdrootElement);
			
			name = doc.createElement("name");
			name.appendChild(doc.createTextNode("Book__c"));
			stdrootElement.appendChild(name);

			type = doc.createElement("type");
			type.appendChild(doc.createTextNode("String"));
			stdrootElement.appendChild(type);

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("E:\\cars.xml"));
			transformer.transform(source, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
