package ToolingObjects;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.*;
import DataContainer.DataWarehouse;
import credentials.RestLogin;

public class User {
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
		/* ----------------------User Start--------------- */
		
		JSONArray UserList = DataWarehouse.getUserList(loginObject, startdate, enddate);
		if (UserList != null) {
			if (UserList.length() > 0) {
				Element xmlUserType = doc.createElement("types");
				xmlroot.appendChild(xmlUserType);
				for (int i = 0; i < UserList.length(); i++) {
					try {
						Element xmlUserMembers = doc.createElement("members");
						xmlUserMembers.appendChild(doc.createTextNode(UserList.getJSONObject(i).getString("Name")));
						xmlUserType.appendChild(xmlUserMembers);
						System.out.println(UserList.getJSONObject(i).getString("Name"));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				Element xmlUserName = doc.createElement("name");
				xmlUserName.appendChild(doc.createTextNode("User"));
				xmlUserType.appendChild(xmlUserName);
			}
		}
		/* ----------------------User end--------------- */
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(
				"D:\\XML\\" + (new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss").format(new Date()).toString()) + ".xml"));
		transformer.transform(source, result);
	}
}
