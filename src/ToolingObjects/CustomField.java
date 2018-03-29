package ToolingObjects;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

public class CustomField {
	public static void main(String[] args) throws Exception {
		long startTime = System.nanoTime();
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

		/* ----------------------Custom Field Start--------------- */

		/*JSONArray stdbjectList = DataWarehouse.getStdSobjectObjectList(loginObject);
		Element xmlcustomobjecttype = doc.createElement("types");
		xmlroot.appendChild(xmlcustomobjecttype);
		for (int i = 0; i < stdbjectList.length(); i++) {
			try {
				if (!stdbjectList.getJSONObject(i).getBoolean("custom")) {
					JSONArray customfieldList = DataWarehouse.getCustomFieldList(loginObject, startdate, enddate,
							stdbjectList.getJSONObject(i).getString("name"));

					if (customfieldList.length() > 0) {
						for (int j = 0; j < customfieldList.length(); j++) {
							Element xmlcustomobjectMembers = doc.createElement("members");
							xmlcustomobjectMembers.appendChild(
									doc.createTextNode(stdbjectList.getJSONObject(i).getString("name") + "."
											+ customfieldList.getJSONObject(j).getString("DeveloperName") + "__c"));
							xmlcustomobjecttype.appendChild(xmlcustomobjectMembers);
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}*/
		/*List<String> stdbjectList = DataWarehouse.getStaticStdObjectList();
		Element xmlcustomobjecttype = doc.createElement("types");
		xmlroot.appendChild(xmlcustomobjecttype);
		for (int i = 0; i < stdbjectList.size(); i++) {
			try {
					JSONArray customfieldList = DataWarehouse.getCustomFieldList(loginObject, startdate, enddate,
							stdbjectList.get(i));
					if (customfieldList.length() > 0) {
						for (int j = 0; j < customfieldList.length(); j++) {
							Element xmlcustomobjectMembers = doc.createElement("members");
							xmlcustomobjectMembers.appendChild(
									doc.createTextNode(stdbjectList.get(i) + "."
											+ customfieldList.getJSONObject(j).getString("DeveloperName") + "__c"));
							xmlcustomobjecttype.appendChild(xmlcustomobjectMembers);
						}
					}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		JSONArray customobjectList = DataWarehouse.getCustomObjectList(loginObject);

		for (int i = 0; i < customobjectList.length(); i++) {
			try {
				JSONArray customfieldList = DataWarehouse.getCustomFieldList(loginObject, startdate, enddate,
						customobjectList.getJSONObject(i).getString("Id"));
				if (customfieldList.length() > 0) {
					for (int j = 0; j < customfieldList.length(); j++) {
						Element xmlcustomobjectMembers = doc.createElement("members");
						xmlcustomobjectMembers.appendChild(
								doc.createTextNode(customobjectList.getJSONObject(i).getString("DeveloperName") + "__c"
										+ "." + customfieldList.getJSONObject(j).getString("DeveloperName") + "__c"));
						xmlcustomobjecttype.appendChild(xmlcustomobjectMembers);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		Element xmlcustomobjectName = doc.createElement("name");
		xmlcustomobjectName.appendChild(doc.createTextNode("CustomField"));// Make it dynamic
		xmlcustomobjecttype.appendChild(xmlcustomobjectName);

		/* ----------------------Custom Field End---------------264 */
		/* ----------------------Custom Field Start--------------- */
		JSONArray customFieldList = DataWarehouse.__getCustomFieldList(loginObject, startdate, enddate);
		if (customFieldList != null) {
			if (customFieldList.length() > 0) {
				Element xmlcustomfieldtype = doc.createElement("types");
				xmlroot.appendChild(xmlcustomfieldtype);

				String customObjectName = "";
				for (int j = 0; j < customFieldList.length(); j++) {
					if (customFieldList.getJSONObject(j).getString("TableEnumOrId").matches("^[A-Za-z]+[0-9]*"))
						customObjectName = customFieldList.getJSONObject(j).getString("TableEnumOrId");
					else {
						customObjectName = DataWarehouse.getCustomObjectName(loginObject,
								customFieldList.getJSONObject(j).getString("TableEnumOrId"));
						customObjectName += "__c";
					}
					Element xmlcustomfieldMembers = doc.createElement("members");
					xmlcustomfieldMembers.appendChild(doc.createTextNode(customObjectName + "."
							+ customFieldList.getJSONObject(j).getString("DeveloperName") + "__c"));
					xmlcustomfieldtype.appendChild(xmlcustomfieldMembers);
					System.out.println("customfield - "+customObjectName + "."
							+ customFieldList.getJSONObject(j).getString("DeveloperName") + "__c");
				}

				Element xmlcustomfieldName = doc.createElement("name");
				xmlcustomfieldName.appendChild(doc.createTextNode("CustomField"));// Make it dynamic
				xmlcustomfieldtype.appendChild(xmlcustomfieldName);
			}
		}
		/* ----------------------Custom Field End--------------- 431*/
		
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(
				"E:\\XML\\" + (new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss").format(new Date()).toString()) + ".xml"));
		transformer.transform(source, result);
		
		long endTime = System.nanoTime();
		long totalTime = endTime - startTime;
		System.out.println(totalTime + " Total Time - " + ((double) totalTime / 1000000000.0));
	}
}
