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

public class WebLink {

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
//----------------------------WebLink------------------
		JSONArray WebLinkList = DataWarehouse.geWebLinkList(loginObject, startdate, enddate);
		if (WebLinkList != null) {
			if (WebLinkList.length() > 0) {
				Element xmlWebLinkType = doc.createElement("types");
				xmlroot.appendChild(xmlWebLinkType);
				for (int i = 0; i < WebLinkList.length(); i++) {
					try {
						Element xmlWebLinkMembers = doc.createElement("members");
						xmlWebLinkMembers
								.appendChild(doc.createTextNode(WebLinkList.getJSONObject(i).getString("Name")));
						xmlWebLinkType.appendChild(xmlWebLinkMembers);
System.out.println(WebLinkList.getJSONObject(i).getString("Name"));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				Element xmlWebLinkName = doc.createElement("name");
				xmlWebLinkName.appendChild(doc.createTextNode("WebLink"));
				xmlWebLinkType.appendChild(xmlWebLinkName);
			}
		}
		//----------------------------WebLink------------------
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(
				"D:\\XML\\" + (new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss").format(new Date()).toString()) + ".xml"));
		transformer.transform(source, result);
	}

}
