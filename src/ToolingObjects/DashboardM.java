
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

public class DashboardM {

	public static void main(String[] args) throws Exception {

		JSONObject loginObject = RestLogin.GetLoginObject();

		String startdate = "2018-01-01T00:00:00.000Z";
		String enddate = "2018-03-22T00:00:00.000Z";

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.newDocument();
		Element xmlroot = doc.createElement("Package");
		Attr attrType1 = doc.createAttribute("xmlns");
		attrType1.setValue("http://soap.sforce.com/2006/04/metadata");
		xmlroot.setAttributeNode(attrType1);
		doc.appendChild(xmlroot);

		/* ---------------------dashboard Start--------------- */
		JSONArray dashboardList = DataWarehouse.getDashboardList(loginObject, startdate, enddate);
		if (dashboardList != null) {
			if (dashboardList.length() > 0) {
				Element xmldashboardtype = doc.createElement("types");
				xmlroot.appendChild(xmldashboardtype);
				for (int i = 0; i < dashboardList.length(); i++) {
					try {
						Element xmldashboardMembers = doc.createElement("members");
						xmldashboardMembers.appendChild(
								doc.createTextNode(dashboardList.getJSONObject(i).getString("DeveloperName")));
						xmldashboardtype.appendChild(xmldashboardMembers);
						System.out.println(
								"dashboard Name : " + dashboardList.getJSONObject(i).getString("DeveloperName"));

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				Element xmldashboardName = doc.createElement("name");
				xmldashboardName.appendChild(doc.createTextNode("Dashboard"));// Make it dynamic
				xmldashboardtype.appendChild(xmldashboardName);
			}
		}
		/* ----------------------Connected Application End--------------- */

		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File("D:\\XML\\dashboard.xml"
				+ (new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss").format(new Date()).toString()) + ".xml"));
		transformer.transform(source, result);
	}

}
