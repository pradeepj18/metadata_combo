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

public class ApexTriger {

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

		/* ----------------------Apex Trigger Start---------------*/
		JSONArray apexTriggerList = DataWarehouse.getApexTriggerList(loginObject,startdate,enddate);
		Element xmlapextriggertype = doc.createElement("types");
		xmlroot.appendChild(xmlapextriggertype);
		for (int i = 0; i < apexTriggerList.length(); i++) {
			try {
					Element xmlapextriggerMembers = doc.createElement("members");
					xmlapextriggerMembers.appendChild(doc.createTextNode(apexTriggerList.getJSONObject(i).getString("Name")));
					xmlapextriggertype.appendChild(xmlapextriggerMembers);
					System.out.println("Tigger Name : " + apexTriggerList.getJSONObject(i).getString("Name"));
					
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		Element xmlapextriggerName = doc.createElement("name");
		xmlapextriggerName.appendChild(doc.createTextNode("ApexTrigger"));//Make it dynamic
		xmlapextriggertype.appendChild(xmlapextriggerName);
		
		/* ----------------------Apex Trigger End---------------*/
		
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(
				"E:\\XML\\" + (new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss").format(new Date()).toString()) + ".xml"));
		transformer.transform(source, result);
	}
}
