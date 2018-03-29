package SFDCMetadata;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import DataContainer.GenericData;
import credentials.RestLogin;
public class GenericDataTest {
	public static void main(String... ss) throws Exception {
		JSONObject loginObject1 = RestLogin.GetLoginObject();
	
		JSONObject loginObject = new JSONObject ();
		loginObject.put("access_token", loginObject1.get("access_token"));
		loginObject.put("instance_url", loginObject1.get("instance_url"));
		
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

		List<String> objlist_Name = new ArrayList<String>();
		objlist_Name.add("ApexComponent");
		objlist_Name.add("ApexClass");
		objlist_Name.add("ApexTrigger");

		for (int index = 0; index < objlist_Name.size(); index++) {
			JSONArray NameList = GenericData.getComponentList_Name(objlist_Name.get(index), loginObject,
					startdate, enddate);
			if (NameList != null) {
				if (NameList.length() > 0) {
					Element xmlNametype = doc.createElement("types");
					xmlroot.appendChild(xmlNametype);
					for (int i = 0; i < NameList.length(); i++) {
						try {
							Element xmlNameMembers = doc.createElement("members");
							xmlNameMembers.appendChild(
									doc.createTextNode(NameList.getJSONObject(i).getString("Name")));
							xmlNametype.appendChild(xmlNameMembers);
//							System.out.println("Class Name : " + NameList.getJSONObject(i).getString("Name"));

						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					Element xmlNameName = doc.createElement("name");
					xmlNameName.appendChild(doc.createTextNode(objlist_Name.get(index)));// Make it dynamic
					xmlNametype.appendChild(xmlNameName);
				}
			}

		}

		List<String> objlist_DevName = new ArrayList<String>();
		objlist_DevName.add("CustomObject");
		objlist_DevName.add("CustomField");
		objlist_DevName.add("AuraDefinitionBundle");
		objlist_DevName.add("FieldSet");
		
		for (int index = 0; index < objlist_DevName.size(); index++) {
			JSONArray DevNameList = GenericData.getComponentList_DevName(objlist_DevName.get(index), loginObject,
					startdate, enddate);
			if (DevNameList != null) {
				if (DevNameList.length() > 0) {
					Element xmlDevNametype = doc.createElement("types");
					xmlroot.appendChild(xmlDevNametype);
					for (int i = 0; i < DevNameList.length(); i++) {
						try {
							Element xmlDevNameMembers = doc.createElement("members");
							xmlDevNameMembers.appendChild(
									doc.createTextNode(DevNameList.getJSONObject(i).getString("DeveloperName")));
							xmlDevNametype.appendChild(xmlDevNameMembers);
							//System.out.println(objlist_DevName.get(index)+" Name : " + DevNameList.getJSONObject(i).getString("DeveloperName"));

						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					Element xmlDevNameName = doc.createElement("name");
					xmlDevNameName.appendChild(doc.createTextNode(objlist_DevName.get(index)));// Make it dynamic
					xmlDevNametype.appendChild(xmlDevNameName);
				}
			}

		}

		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(
				"E:\\XML\\" + (new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss").format(new Date()).toString()) + ".xml"));
		transformer.transform(source, result);
	}
}
