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

public class RecordType {
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

		/* ----------------------RecordType Start--------------- */
		JSONArray recordTypeList = DataWarehouse.getRecordTypeList(loginObject, startdate, enddate);
		if (recordTypeList != null) {
			if (recordTypeList.length() > 0) {
				Element xmlrecordTypetype = doc.createElement("types");
				xmlroot.appendChild(xmlrecordTypetype);
				for (int i = 0; i < recordTypeList.length(); i++) {
					try {
						Element xmlrecordTypeMembers = doc.createElement("members");
						xmlrecordTypeMembers.appendChild(
								doc.createTextNode(recordTypeList.getJSONObject(i).getString("Name")+"."+recordTypeList.getJSONObject(i).getString("Name")));
						xmlrecordTypetype.appendChild(xmlrecordTypeMembers);
						System.out.println("emailTemplateList Name : " + recordTypeList.getJSONObject(i).getString("Name"));

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				Element xmlrecordTypeName = doc.createElement("name");
				xmlrecordTypeName.appendChild(doc.createTextNode("RecordType"));// Make it dynamic
				xmlrecordTypetype.appendChild(xmlrecordTypeName);
			}
		}
		/* ----------------------RecordType End--------------- */

		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(
				"E:\\XML\\" + (new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss").format(new Date()).toString()) + ".xml"));
		transformer.transform(source, result);
	}
}
