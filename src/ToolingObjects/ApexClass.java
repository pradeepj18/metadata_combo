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

public class ApexClass {
	 

	
	public static void main(String[] args) throws Exception {
		System.out.println("Total memory before - "+Runtime.getRuntime().totalMemory());
		System.out.println("Free memory before  - "+Runtime.getRuntime().freeMemory());
		long beforeUsedMem=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
		System.out.println("Before program execution memory - "+beforeUsedMem);
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

		/* ----------------------Apex class Start---------------*/
		JSONArray apexTriggerList = DataWarehouse.getApexClassList(loginObject,startdate,enddate);
		System.out.println("SIZE : "+apexTriggerList.length());
		Element xmlapexclasstype = doc.createElement("types");
		xmlroot.appendChild(xmlapexclasstype);
		for (int i = 0; i < apexTriggerList.length(); i++) {
			try {
					Element xmlapexclassMembers = doc.createElement("members");
					xmlapexclassMembers.appendChild(doc.createTextNode(apexTriggerList.getJSONObject(i).getString("Name")));
					xmlapexclasstype.appendChild(xmlapexclassMembers);
				//	System.out.println("Class Name : " + apexTriggerList.getJSONObject(i).getString("Name"));
					
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		Element xmlapexclassName = doc.createElement("name");
		xmlapexclassName.appendChild(doc.createTextNode("ApexClass"));//Make it dynamic
		xmlapexclasstype.appendChild(xmlapexclassName);
		/* ----------------------Apex class End---------------*/
		
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(
				"E:\\XML\\" + (new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss").format(new Date()).toString()) + ".xml"));
		transformer.transform(source, result);
		System.out.println("Total memory after - "+Runtime.getRuntime().totalMemory());
		System.out.println("Free memory after  - "+Runtime.getRuntime().freeMemory());
		long afterUsedMem=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
		System.out.println("after program execution memory - "+afterUsedMem);
		
		
		long actualMemUsed=afterUsedMem-beforeUsedMem;
		System.out.println("Used memory is bytes: " + actualMemUsed);
        System.out.println("Used memory is megabytes: "+ (actualMemUsed)/(1024L * 1024L));
	}

}
