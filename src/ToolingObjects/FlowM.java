
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

public class FlowM {

	public static void main(String[] args) throws Exception {

		JSONObject loginObject = RestLogin.GetLoginObject();

		String startdate = "2018-01-01T00:00:00.000Z";
		String enddate = "2018-03-21T00:00:00.000Z";

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.newDocument();
		Element xmlroot = doc.createElement("Package");
		Attr attrType1 = doc.createAttribute("xmlns");
		attrType1.setValue("http://soap.sforce.com/2006/04/metadata");
		xmlroot.setAttributeNode(attrType1);
		doc.appendChild(xmlroot);

		/* ----------------------Flow Start--------------- */
		JSONArray flowList = DataWarehouse.getFlowList(loginObject, startdate, enddate);
		if (flowList != null) {
			if (flowList.length() > 0) {
				Element xmlflowtype = doc.createElement("types");
				xmlroot.appendChild(xmlflowtype);
				for (int i = 0; i < flowList.length(); i++) {
					try {
						JSONArray flowFullname = DataWarehouse.getFullname("Flow", flowList.getJSONObject(i).getString("Id"), loginObject);
						Element xmlflowMembers = doc.createElement("members");
						xmlflowMembers
								.appendChild(doc.createTextNode(flowFullname.getJSONObject(0).getString("FullName")));
						xmlflowtype.appendChild(xmlflowMembers);
						System.out.println("Flow Name : " + flowFullname.getJSONObject(0).getString("FullName"));

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				Element xmlflowName = doc.createElement("name");
				xmlflowName.appendChild(doc.createTextNode("Flow"));// Make it dynamic
				xmlflowtype.appendChild(xmlflowName);
			}
		}
		/* ----------------------Flow End--------------- */

		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File("D:\\XML\\flow.xml"
				+ (new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss").format(new Date()).toString()) + ".xml"));
		transformer.transform(source, result);
	}

}
