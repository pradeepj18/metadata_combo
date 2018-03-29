
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

public class CustomApplicationM {

	public static void main(String[] args) throws Exception {

		JSONObject loginObject = RestLogin.GetLoginObject();

		String startdate = "2018-03-19T00:00:00.000Z";
		String enddate = "2018-03-20T00:00:00.000Z";

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.newDocument();
		Element xmlroot = doc.createElement("Package");
		Attr attrType1 = doc.createAttribute("xmlns");
		attrType1.setValue("http://soap.sforce.com/2006/04/metadata");
		xmlroot.setAttributeNode(attrType1);
		doc.appendChild(xmlroot);

		/* ----------------------CustomApplication Start--------------- */
		JSONArray customApplicationList = DataWarehouse.getCustomApplicationList(loginObject, startdate, enddate);
		if (customApplicationList != null) {
			if (customApplicationList.length() > 0) {
				Element xmlcustomApplicationtype = doc.createElement("types");
				xmlroot.appendChild(xmlcustomApplicationtype);
				for (int i = 0; i < customApplicationList.length(); i++) {
					try {
						Element xmlcustomApplicationMembers = doc.createElement("members");
						xmlcustomApplicationMembers.appendChild(
								doc.createTextNode(customApplicationList.getJSONObject(i).getString("DeveloperName")));
						xmlcustomApplicationtype.appendChild(xmlcustomApplicationMembers);
						System.out.println("CustomApplication Name : "
								+ customApplicationList.getJSONObject(i).getString("DeveloperName"));

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				Element xmlcustomApplicationName = doc.createElement("name");
				xmlcustomApplicationName.appendChild(doc.createTextNode("CustomApplication"));// Make it dynamic
				xmlcustomApplicationtype.appendChild(xmlcustomApplicationName);
			}
		}
		/* ----------------------profile End--------------- */

		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File("D:\\XML\\CustomApplication.xml"
				+ (new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss").format(new Date()).toString()) + ".xml"));
		transformer.transform(source, result);
	}

}
