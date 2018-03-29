
package ToolingObjects;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import DataContainer.DataWarehouse;
import credentials.RestLogin;

public class ConnectedApplicationM {

	public static void main(String[] args) throws Exception {

		JSONObject loginObject = RestLogin.GetLoginObject();

		String startdate = "2018-01-01T00:00:00.000Z";
		String enddate = "2018-03-22T00:00:00.000Z";

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.newDocument();
		Element xmlroot = doc.createElement("Package");
		Attr attrType1 = doc.createAttribute("xmlns");
		attrType1.setValue("http://soap.sforce.com/2006/04/metadata");
		xmlroot.setAttributeNode(attrType1);
		doc.appendChild(xmlroot);

		/* ---------------------connectedApplication Start--------------- */
		JSONArray connectedApplicationList = DataWarehouse.getConnectedApplicationList(loginObject, startdate, enddate);
		if (connectedApplicationList != null) {
			if (connectedApplicationList.length() > 0) {
				Element xmlconnectedApplicationtype = doc.createElement("types");
				xmlroot.appendChild(xmlconnectedApplicationtype);
				for (int i = 0; i < connectedApplicationList.length(); i++) {
					try {
						Element xmlconnectedApplicationMembers = doc.createElement("members");
						xmlconnectedApplicationMembers.appendChild(
								doc.createTextNode(connectedApplicationList.getJSONObject(i).getString("Name")));
						xmlconnectedApplicationtype.appendChild(xmlconnectedApplicationMembers);
						System.out.println("connectedApplication Name : "
								+ connectedApplicationList.getJSONObject(i).getString("Name"));

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				Element xmlconnectedApplicationName = doc.createElement("name");
				xmlconnectedApplicationName.appendChild(doc.createTextNode("ConnectedApplication"));// Make it dynamic
				xmlconnectedApplicationtype.appendChild(xmlconnectedApplicationName);
			}
		}
		/* ----------------------Connected Application End--------------- */

		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File("D:\\XML\\ConnectedApplication.xml"
				+ (new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss").format(new Date()).toString()) + ".xml"));
		transformer.transform(source, result);
	}

}
