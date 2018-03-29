
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

public class ProfileM {

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

		/* ----------------------Profile Start--------------- */
		JSONArray profileList = DataWarehouse.getProfileList(loginObject, startdate, enddate);
		if (profileList != null) {
			if (profileList.length() > 0) {
				Element xmlprofiletype = doc.createElement("types");
				xmlroot.appendChild(xmlprofiletype);
				for (int i = 0; i < profileList.length(); i++) {
					try {
						Element xmlprofileMembers = doc.createElement("members");
						xmlprofileMembers
								.appendChild(doc.createTextNode(profileList.getJSONObject(i).getString("Name")));
						xmlprofiletype.appendChild(xmlprofileMembers);
						System.out.println("Profile Name : " + profileList.getJSONObject(i).getString("Name"));

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				Element xmlprofileName = doc.createElement("name");
				xmlprofileName.appendChild(doc.createTextNode("Profile"));// Make it dynamic
				xmlprofiletype.appendChild(xmlprofileName);
			}
		}
		/* ----------------------profile End--------------- */

		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File("D:\\XML\\Profile.xml"
				+ (new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss").format(new Date()).toString()) + ".xml"));
		transformer.transform(source, result);
	}

}
