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

public class ValidationRule {
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

		/* ----------------------ValidationRule Start--------------- */
		JSONArray validationRuleList = DataWarehouse.getValidationRuleList(loginObject, startdate, enddate);
		if (validationRuleList != null) {
			if (validationRuleList.length() > 0) {
				Element xmlvalidationRuletype = doc.createElement("types");
				xmlroot.appendChild(xmlvalidationRuletype);
				for (int i = 0; i < validationRuleList.length(); i++) {
					try {				 
						Element xmlvalidationRuleMembers = doc.createElement("members");
						xmlvalidationRuleMembers.appendChild(
								doc.createTextNode(DataWarehouse.getValidationRuleObjectName(loginObject, validationRuleList.getJSONObject(i).getString("Id"))));
						xmlvalidationRuletype.appendChild(xmlvalidationRuleMembers);
						System.out.println("ValidationRule Name : " + DataWarehouse.getValidationRuleObjectName(loginObject, validationRuleList.getJSONObject(i).getString("Id")));

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				Element xmlvalidationRuleName = doc.createElement("name");
				xmlvalidationRuleName.appendChild(doc.createTextNode("ValidationRule"));// Make it dynamic
				xmlvalidationRuletype.appendChild(xmlvalidationRuleName);
			}
		}
		/* ----------------------ValidationRule End--------------- */

		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(
				"E:\\XML\\" + (new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss").format(new Date()).toString()) + ".xml"));
		transformer.transform(source, result);
	}
}
