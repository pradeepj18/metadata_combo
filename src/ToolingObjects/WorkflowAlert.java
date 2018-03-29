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

public class WorkflowAlert {

	public static void main(String[] args) throws Exception {
		long startTime = System.currentTimeMillis();
		JSONObject loginObject = RestLogin.GetLoginObject();
		String startdate = "2017-02-01T17:23:04.000Z";
		String enddate = "2018-03-20T17:23:04.000Z";

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.newDocument();
		Element xmlroot = doc.createElement("Package");
		Attr attrType1 = doc.createAttribute("xmlns");
		attrType1.setValue("http://soap.sforce.com/2006/04/metadata");
		xmlroot.setAttributeNode(attrType1);
		doc.appendChild(xmlroot);
		// -------------------------------WorkFlowAlert-------------------
		JSONArray objWorkFlowAlert = DataWarehouse.getWorkflowAlertList(loginObject, startdate, enddate);
		if (objWorkFlowAlert != null) {
			if (objWorkFlowAlert.length() > 0) {
				Element xmlobjWorkFlowAlerttype = doc.createElement("types");
				xmlroot.appendChild(xmlobjWorkFlowAlerttype);
				try {
					for (int k = 0; k < objWorkFlowAlert.length(); k++) {
						JSONArray jsonworkflowaler = DataWarehouse.getFullname("WorkFlowAlert",
								objWorkFlowAlert.getJSONObject(k).getString("Id"), loginObject);
						Element xmlobjworkFlowAlertMembers = doc.createElement("members");
						xmlobjworkFlowAlertMembers.appendChild(
								doc.createTextNode(jsonworkflowaler.getJSONObject(0).getString("FullName")));
						xmlobjWorkFlowAlerttype.appendChild(xmlobjworkFlowAlertMembers);
						System.out.println(jsonworkflowaler.getJSONObject(0).getString("FullName"));
					}
					Element xmlobjWorkFlowAlertName = doc.createElement("name");
					xmlobjWorkFlowAlertName.appendChild(doc.createTextNode("WorkFlowAlert"));// Make it dynamic
					xmlobjWorkFlowAlerttype.appendChild(xmlobjWorkFlowAlertName);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		// -------------------------------WorkFlowAlert-------------------
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(
				"D:\\XML\\" + (new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss").format(new Date()).toString()) + ".xml"));
		transformer.transform(source, result);
		long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.println(totalTime + " Total Time - " + ((double) totalTime / 1000000000.0));
	}
}
