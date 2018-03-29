
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

public class LayoutM {

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

		/* ----------------------Layout(page layouts) Start--------------- */
		JSONArray layoutList = DataWarehouse.getLayoutList(loginObject, startdate, enddate);
		if (layoutList != null) {
			if (layoutList.length() > 0) {
				Element xmlcustomfieldtype = doc.createElement("types");
				xmlroot.appendChild(xmlcustomfieldtype);

				String customObjectName = "";
				for (int j = 0; j < layoutList.length(); j++) {
					if (layoutList.getJSONObject(j).getString("TableEnumOrId").matches("^[A-Za-z]+[0-9]*"))
						customObjectName = layoutList.getJSONObject(j).getString("TableEnumOrId");
					else {
						customObjectName = DataWarehouse.getCustomObjectName(loginObject,
								layoutList.getJSONObject(j).getString("TableEnumOrId"));
						customObjectName += "__c";
					}
					Element xmlcustomfieldMembers = doc.createElement("members");
					xmlcustomfieldMembers.appendChild(doc.createTextNode(customObjectName + "."
							+ layoutList.getJSONObject(j).getString("Name")));
					xmlcustomfieldtype.appendChild(xmlcustomfieldMembers);
				System.out.println(customObjectName + "."
							+ layoutList.getJSONObject(j).getString("Name"));
				}

				Element xmlcustomfieldName = doc.createElement("name");
				xmlcustomfieldName.appendChild(doc.createTextNode("Layout"));// Make it dynamic
				xmlcustomfieldtype.appendChild(xmlcustomfieldName);
			}
		}
		/* ----------------------layout End--------------- */

		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File("D:\\XML\\layoutobjecttest.xml"
				+ (new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss").format(new Date()).toString()) + ".xml"));
		transformer.transform(source, result);
	}

}
