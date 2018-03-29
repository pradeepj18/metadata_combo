
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

public class CustomTabM {

	public static void main(String[] args) throws Exception {

		JSONObject loginObject = RestLogin.GetLoginObject();

		String startdate = "";
		String enddate = "";

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.newDocument();
		Element xmlroot = doc.createElement("Package");
		Attr attrType1 = doc.createAttribute("xmlns");
		attrType1.setValue("http://soap.sforce.com/2006/04/metadata");
		xmlroot.setAttributeNode(attrType1);
		doc.appendChild(xmlroot);

		/* ----------------------customtab Start--------------- */
		JSONArray customTabList = DataWarehouse.getCustomTabList(loginObject, startdate, enddate);
		if (customTabList != null) {
			if (customTabList.length() > 0) {
				Element xmlcustomTabtype = doc.createElement("types");
				xmlroot.appendChild(xmlcustomTabtype);
				for (int i = 0; i < customTabList.length(); i++) {
					try {
						JSONArray customTabFullname = DataWarehouse.getFullname("CustomTab", customTabList.getJSONObject(i).getString("Id"), loginObject);
						Element xmlcustomTabMembers = doc.createElement("members");
						xmlcustomTabMembers.appendChild(
								doc.createTextNode(customTabFullname.getJSONObject(0).getString("FullName")));
						xmlcustomTabtype.appendChild(xmlcustomTabMembers);
						System.out.println(
								"customTab Name : " + customTabFullname.getJSONObject(0).getString("FullName"));

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				Element xmlcustomTabName = doc.createElement("name");
				xmlcustomTabName.appendChild(doc.createTextNode("customTab"));// Make it dynamic
				xmlcustomTabtype.appendChild(xmlcustomTabName);
			}
		}
		/* ----------------------customtab End--------------- */

		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File("D:\\XML\\customTab.xml"
				+ (new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss").format(new Date()).toString()) + ".xml"));
		transformer.transform(source, result);
	}

}
