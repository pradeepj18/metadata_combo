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

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import DataContainer.DataWarehouse;
import DataContainer.RestResourceURL;
import DataContainer.ToolingQueryList;
import credentials.RestLogin;

public class WorkflowTask {

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
		// -------------------------------WorkFlowTask-------------------
				JSONArray objWorkFlowTask = DataWarehouse.getWorkflowTaskList(loginObject, startdate, enddate);
				if (objWorkFlowTask != null) {
					if (objWorkFlowTask.length() > 0) {
						Element xmlobjWorkFlowTasktype = doc.createElement("types");
						xmlroot.appendChild(xmlobjWorkFlowTasktype);
						try {
							for (int k = 0; k < objWorkFlowTask.length(); k++) {
								JSONArray jsonworkflowaler = DataWarehouse.getFullname("WorkFlowTask",
										objWorkFlowTask.getJSONObject(k).getString("Id"), loginObject);
								Element xmlobjWorkFlowTaskMembers = doc.createElement("members");
								xmlobjWorkFlowTaskMembers.appendChild(
										doc.createTextNode(jsonworkflowaler.getJSONObject(0).getString("FullName")));
								xmlobjWorkFlowTasktype.appendChild(xmlobjWorkFlowTaskMembers);
								// System.out.println(jsonworkflowaler.getJSONObject(0).getString("FullName"));
							}
							Element xmlobjWorkFlowTaskName = doc.createElement("name");
							xmlobjWorkFlowTaskName.appendChild(doc.createTextNode("WorkFlowTask"));// Make it dynamic
							xmlobjWorkFlowTasktype.appendChild(xmlobjWorkFlowTaskName);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
				// --------------------------WorkFlowTask----------------------
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(
				"D:\\XML\\" + (new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss").format(new Date()).toString()) + ".xml"));
		transformer.transform(source, result);

	}

}
