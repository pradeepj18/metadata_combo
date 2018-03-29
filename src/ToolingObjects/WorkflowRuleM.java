
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

public class WorkflowRuleM {

	public static void main(String[] args) throws Exception {

		JSONObject loginObject = RestLogin.GetLoginObject();

		String startdate = "2018-03-20T00:00:00.000Z";
		String enddate = "2018-03-21T00:00:00.000Z";

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.newDocument();
		Element xmlroot = doc.createElement("Package");
		Attr attrType1 = doc.createAttribute("xmlns");
		attrType1.setValue("http://soap.sforce.com/2006/04/metadata");
		xmlroot.setAttributeNode(attrType1);
		doc.appendChild(xmlroot);

		/* ----------------------workflowRule Start--------------- */
		JSONArray workflowRuleList = DataWarehouse.getWorkflowRuleList(loginObject, startdate, enddate);
		if (workflowRuleList != null) {
			if (workflowRuleList.length() > 0) {
				Element xmlworkflowRuletype = doc.createElement("types");
				xmlroot.appendChild(xmlworkflowRuletype);
				for (int i = 0; i < workflowRuleList.length(); i++) {
					try {
						Element xmlworkflowRuleMembers = doc.createElement("members");
						xmlworkflowRuleMembers.appendChild(
								doc.createTextNode(workflowRuleList.getJSONObject(i).getString("TableEnumOrId") + "."
										+ workflowRuleList.getJSONObject(i).getString("Name")));
						xmlworkflowRuletype.appendChild(xmlworkflowRuleMembers);
						System.out
								.println("WorkflowRule Name : " + workflowRuleList.getJSONObject(i).getString("Name"));

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				Element xmlworkflowRuleName = doc.createElement("name");
				xmlworkflowRuleName.appendChild(doc.createTextNode("WorkflowRule"));// Make it dynamic
				xmlworkflowRuletype.appendChild(xmlworkflowRuleName);
			}
		}
		/* ----------------------WorkflowRule End--------------- */

		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File("D:\\XML\\workflowRule.xml"
				+ (new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss").format(new Date()).toString()) + ".xml"));
		transformer.transform(source, result);
	}

}
