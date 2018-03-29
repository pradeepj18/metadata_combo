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

public class AssignmentRule {
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

		/* ----------------------AssignmentRule Start--------------- */
		JSONArray assignmentRuleList = DataWarehouse.getAssignmentRuleList(loginObject, startdate, enddate);
		if (assignmentRuleList != null) {
			if (assignmentRuleList.length() > 0) {
				Element xmlassignmentRuletype = doc.createElement("types");
				xmlroot.appendChild(xmlassignmentRuletype);
				for (int i = 0; i < assignmentRuleList.length(); i++) {
					try {
						Element xmlassignmentRuleMembers = doc.createElement("members");
						xmlassignmentRuleMembers.appendChild(
								doc.createTextNode(assignmentRuleList.getJSONObject(i).getString("EntityDefinitionId")
										+ "." + assignmentRuleList.getJSONObject(i).getString("Name")));
						xmlassignmentRuletype.appendChild(xmlassignmentRuleMembers);
						System.out.println(
								"Class Name : " + assignmentRuleList.getJSONObject(i).getString("EntityDefinitionId")
										+ "." + assignmentRuleList.getJSONObject(i).getString("Name"));

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				Element xmlassignmentRuleName = doc.createElement("name");
				xmlassignmentRuleName.appendChild(doc.createTextNode("AssignmentRule"));// Make it dynamic
				xmlassignmentRuletype.appendChild(xmlassignmentRuleName);
			}
		}	
		/* ----------------------AssignmentRule End--------------- */

		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(
				"E:\\XML\\" + (new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss").format(new Date()).toString()) + ".xml"));
		transformer.transform(source, result);
	}

}
