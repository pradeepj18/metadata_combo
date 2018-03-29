
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

public class ReportM {

	public static void main(String[] args) throws Exception {

		JSONObject loginObject = RestLogin.GetLoginObject();

		String startdate = "";
		String enddate = "2018-01-22T00:00:00.000Z";

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.newDocument();
		Element xmlroot = doc.createElement("Package");
		Attr attrType1 = doc.createAttribute("xmlns");
		attrType1.setValue("http://soap.sforce.com/2006/04/metadata");
		xmlroot.setAttributeNode(attrType1);
		doc.appendChild(xmlroot);

		/* ---------------------Report Start--------------- */
		JSONArray reportList = DataWarehouse.getReportList(loginObject, startdate, enddate);
		if (reportList != null) {
			if (reportList.length() > 0) {
				Element xmlreporttype = doc.createElement("types");
				xmlroot.appendChild(xmlreporttype);
				for (int i = 0; i < reportList.length(); i++) {
					try {
						Element xmlreportMembers = doc.createElement("members");
						xmlreportMembers.appendChild(
								doc.createTextNode(reportList.getJSONObject(i).getString("DeveloperName")));
						xmlreporttype.appendChild(xmlreportMembers);
						System.out.println("report Name : " + reportList.getJSONObject(i).getString("DeveloperName"));

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				Element xmlreportName = doc.createElement("name");
				xmlreportName.appendChild(doc.createTextNode("report"));// Make it dynamic
				xmlreporttype.appendChild(xmlreportName);
			}
		}
		/* ---------------------report Application End--------------- */

		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File("D:\\XML\\report.xml"
				+ (new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss").format(new Date()).toString()) + ".xml"));
		transformer.transform(source, result);
	}

}
