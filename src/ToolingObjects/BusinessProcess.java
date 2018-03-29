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

public class BusinessProcess {
	public static void main(String[] args) throws Exception {

		JSONObject loginObject = RestLogin.GetLoginObject();

		String startdate = "2018-02-01T17:23:04.000Z";
		String enddate = "2018-03-01T17:23:04.000Z";

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.newDocument();
		Element xmlroot = doc.createElement("Package");
		Attr attrType1 = doc.createAttribute("xmlns");
		attrType1.setValue("http://soap.sforce.com/2006/04/metadata");
		xmlroot.setAttributeNode(attrType1);
		doc.appendChild(xmlroot);

		/* ----------------------BusinessProcess Start--------------- */
		JSONArray businessProcessList = DataWarehouse.getBusinessProcessList(loginObject, startdate, enddate);
		if (businessProcessList != null) {
			if (businessProcessList.length() > 0) {
				Element xmlbusinessProcesstype = doc.createElement("types");
				xmlroot.appendChild(xmlbusinessProcesstype);
				for (int i = 0; i < businessProcessList.length(); i++) {
					try {
						Element xmlbusinessProcessMembers = doc.createElement("members");
						xmlbusinessProcessMembers.appendChild(
								doc.createTextNode(businessProcessList.getJSONObject(i).getString("Name")));
						xmlbusinessProcesstype.appendChild(xmlbusinessProcessMembers);
						System.out.println("Class Name : " + businessProcessList.getJSONObject(i).getString("Name"));

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				Element xmlbusinessProcessName = doc.createElement("name");
				xmlbusinessProcessName.appendChild(doc.createTextNode("BusinessProcess"));// Make it dynamic
				xmlbusinessProcesstype.appendChild(xmlbusinessProcessName);
			}
		}
		/* ----------------------BusinessProcess End--------------- */

		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(
				"E:\\XML\\" + (new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss").format(new Date()).toString()) + ".xml"));
		transformer.transform(source, result);
	}
}
