
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

public class CompactLayoutM {

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

		/* ---------------------CompactLayout Start--------------- */
		JSONArray compactLayoutList = DataWarehouse.getCompactLayoutList(loginObject, startdate, enddate);
		if (compactLayoutList != null) {
			if (compactLayoutList.length() > 0) {
				Element xmlcompactLayouttype = doc.createElement("types");
				xmlroot.appendChild(xmlcompactLayouttype);

				for (int i = 0; i < compactLayoutList.length(); i++) {
					try {
						Element xmlcompactLayoutMembers = doc.createElement("members");
						xmlcompactLayoutMembers.appendChild(
								doc.createTextNode(compactLayoutList.getJSONObject(i).getString("SobjectType") + "."
										+ compactLayoutList.getJSONObject(i).getString("DeveloperName")));
						xmlcompactLayouttype.appendChild(xmlcompactLayoutMembers);
						System.out.println("CompactLayout Name : "
								+ compactLayoutList.getJSONObject(i).getString("DeveloperName"));

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				Element xmlcompactLayoutName = doc.createElement("name");
				xmlcompactLayoutName.appendChild(doc.createTextNode("CompactLayout"));// Make it dynamic
				xmlcompactLayouttype.appendChild(xmlcompactLayoutName);
			}
		}
		/* ----------------------CompactLayout End--------------- */

		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File("D:\\XML\\CompactLayout.xml"
				+ (new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss").format(new Date()).toString()) + ".xml"));
		transformer.transform(source, result);
	}

}
