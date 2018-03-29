package SFDCMetadata;

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

public class FinalMetadata {

	public static void main(String[] args) throws Exception {
		long startTime = System.nanoTime();
		JSONObject loginObject = RestLogin.GetLoginObject();

		String startdate = "2018-02-01T17:23:04.000Z";
		String enddate = "2018-03-01T17:23:04.000Z";
		/*
		 * JSONObject loginObject1 = RestLogin.GetLoginObject();
		 * 
		 * JSONObject loginObject = new JSONObject (); loginObject.put("access_token",
		 * loginObject1.get("access_token")); loginObject.put("instance_url",
		 * loginObject1.get("instance_url"));
		 */

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.newDocument();
		Element xmlroot = doc.createElement("Package");
		Attr attrType1 = doc.createAttribute("xmlns");
		attrType1.setValue("http://soap.sforce.com/2006/04/metadata");
		xmlroot.setAttributeNode(attrType1);
		doc.appendChild(xmlroot);

		/* ----------------------Apex class Start--------------- */
		JSONArray apexclassList = DataWarehouse.getApexClassList(loginObject, startdate, enddate);
		if (apexclassList != null) {
			if (apexclassList.length() > 0) {
				Element xmlapexclasstype = doc.createElement("types");
				xmlroot.appendChild(xmlapexclasstype);
				for (int i = 0; i < apexclassList.length(); i++) {
					try {
						Element xmlapexclassMembers = doc.createElement("members");
						xmlapexclassMembers
								.appendChild(doc.createTextNode(apexclassList.getJSONObject(i).getString("Name")));
						xmlapexclasstype.appendChild(xmlapexclassMembers);
						 System.out.println("Apex Class Name : " +apexclassList.getJSONObject(i).getString("Name"));

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				Element xmlapexclassName = doc.createElement("name");
				xmlapexclassName.appendChild(doc.createTextNode("ApexClass"));// Make it dynamic
				xmlapexclasstype.appendChild(xmlapexclassName);
			}
		}
		/* ----------------------Apex class End--------------- */
		/* ----------------------Apex Component Start--------------- */

		JSONArray apexComponentList = DataWarehouse.getApexComponentList(loginObject, startdate, enddate);
		if (apexComponentList != null) {
			if (apexComponentList.length() > 0) {
				Element xmlapexComponenttype = doc.createElement("types");
				xmlroot.appendChild(xmlapexComponenttype);
				for (int i = 0; i < apexComponentList.length(); i++) {
					try {
						Element xmlapexComponentMembers = doc.createElement("members");
						xmlapexComponentMembers
								.appendChild(doc.createTextNode(apexComponentList.getJSONObject(i).getString("Name")));
						xmlapexComponenttype.appendChild(xmlapexComponentMembers);
						System.out.println("Apex Component Name : " +apexComponentList.getJSONObject(i).getString("Name"));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				Element xmlapexComponentName = doc.createElement("name");
				xmlapexComponentName.appendChild(doc.createTextNode("ApexComponent"));// Make it dynamic
				xmlapexComponenttype.appendChild(xmlapexComponentName);
			}
		}
		/* ----------------------Apex Component End--------------- */
		/* ----------------------Apex page Start--------------- */
		JSONArray apexPageList = DataWarehouse.getApexPageList(loginObject, startdate, enddate);
		if (apexPageList != null) {
			if (apexPageList.length() > 0) {
				Element xmlapexPagetype = doc.createElement("types");
				xmlroot.appendChild(xmlapexPagetype);
				for (int i = 0; i < apexPageList.length(); i++) {
					try {
						Element xmlapexPageMembers = doc.createElement("members");
						xmlapexPageMembers
								.appendChild(doc.createTextNode(apexPageList.getJSONObject(i).getString("Name")));
						xmlapexPagetype.appendChild(xmlapexPageMembers);
						 System.out.println("Apex page Name : " +
						 apexPageList.getJSONObject(i).getString("Name"));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				Element xmlapexPageName = doc.createElement("name");
				xmlapexPageName.appendChild(doc.createTextNode("ApexPage"));// Make it dynamic
				xmlapexPagetype.appendChild(xmlapexPageName);
			}
		}
		/* ----------------------apex page End--------------- */
		/* ----------------------Apex Trigger Start--------------- */
		JSONArray apexTriggerList = DataWarehouse.getApexTriggerList(loginObject, startdate, enddate);
		if (apexTriggerList != null) {
			if (apexTriggerList.length() > 0) {
				Element xmlapextriggertype = doc.createElement("types");
				xmlroot.appendChild(xmlapextriggertype);
				for (int i = 0; i < apexTriggerList.length(); i++) {
					try {
						Element xmlapextriggerMembers = doc.createElement("members");
						xmlapextriggerMembers
								.appendChild(doc.createTextNode(apexTriggerList.getJSONObject(i).getString("Name")));
						xmlapextriggertype.appendChild(xmlapextriggerMembers);
						 System.out.println("Tigger Name : " +
						 apexTriggerList.getJSONObject(i).getString("Name"));

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				Element xmlapextriggerName = doc.createElement("name");
				xmlapextriggerName.appendChild(doc.createTextNode("ApexTrigger"));// Make it dynamic
				xmlapextriggertype.appendChild(xmlapextriggerName);
			}
		}
		/* ----------------------Apex Trigger End--------------- */
		/* ----------------------AssignmentRule Start--------------- */
		JSONArray assignmentRuleList = DataWarehouse.getAssignmentRuleList(loginObject, startdate, enddate);
		if (assignmentRuleList != null) {
			if (assignmentRuleList.length() > 0) {
				Element xmlassignmentRuletype = doc.createElement("types");
				xmlroot.appendChild(xmlassignmentRuletype);
				for (int i = 0; i < assignmentRuleList.length(); i++) {
					try {
						Element xmlassignmentRuleMembers = doc.createElement("members");
						xmlassignmentRuleMembers.appendChild(
								doc.createTextNode(assignmentRuleList.getJSONObject(i).getString("EntityDefinitionId")
										+ "." + assignmentRuleList.getJSONObject(i).getString("Name")));
						xmlassignmentRuletype.appendChild(xmlassignmentRuleMembers);
System.out.println("Assignment rule "+assignmentRuleList.getJSONObject(i).getString("EntityDefinitionId")
										+ "." + assignmentRuleList.getJSONObject(i).getString("Name"));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				Element xmlassignmentRuleName = doc.createElement("name");
				xmlassignmentRuleName.appendChild(doc.createTextNode("AssignmentRule"));// Make it dynamic
				xmlassignmentRuletype.appendChild(xmlassignmentRuleName);
			}
		}
		/* ----------------------AssignmentRule End--------------- */
		/* ----------------------AuraDefinitionBundle Start--------------- */
		JSONArray auraDefinitionBundle = DataWarehouse.getAuraDefinitionBundleList(loginObject, startdate, enddate);
		if (auraDefinitionBundle != null) {
			if (auraDefinitionBundle.length() > 0) {
				Element xmlAuraDefinitionBundletype = doc.createElement("types");
				xmlroot.appendChild(xmlAuraDefinitionBundletype);
				try {
					for (int i = 0; i < auraDefinitionBundle.length(); i++) {
						Element xmlAuraDefinitionBundleMembers = doc.createElement("members");
						xmlAuraDefinitionBundleMembers.appendChild(
								doc.createTextNode(auraDefinitionBundle.getJSONObject(i).getString("DeveloperName")));
						xmlAuraDefinitionBundletype.appendChild(xmlAuraDefinitionBundleMembers);
						System.out.println("auradefinition - "+auraDefinitionBundle.getJSONObject(i).getString("DeveloperName"));
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				Element xmlAuraDefinitionBundleName = doc.createElement("name");
				xmlAuraDefinitionBundleName.appendChild(doc.createTextNode("AuraDefinitionBundle"));// Make it dynamic
				xmlAuraDefinitionBundletype.appendChild(xmlAuraDefinitionBundleName);
			}
		}
		/* ----------------------AuraDefinitionBundle End--------------- */
		/* ----------------------AutoResponse Start--------------- */

		JSONArray autoResponseList = DataWarehouse.getAutoResponseList(loginObject, startdate, enddate);
		if (autoResponseList != null) {
			if (autoResponseList.length() > 0) {
				Element xmlautoResponseListtype = doc.createElement("types");
				xmlroot.appendChild(xmlautoResponseListtype);
				for (int i = 0; i < autoResponseList.length(); i++) {
					try {
						Element xmlautoResponseListMembers = doc.createElement("members");
						xmlautoResponseListMembers
								.appendChild(doc.createTextNode(autoResponseList.getJSONObject(i).getString("Name")));
						xmlautoResponseListtype.appendChild(xmlautoResponseListMembers);
System.out.println("autoResponse - "+autoResponseList.getJSONObject(i).getString("Name"));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				Element xmlautoResponseListName = doc.createElement("name");
				xmlautoResponseListName.appendChild(doc.createTextNode("ApexComponent"));// Make it dynamic
				xmlautoResponseListtype.appendChild(xmlautoResponseListName);
			}
		}
		/* ----------------------Apex Component End--------------- */
		/* ----------------------BusinessProcess Start--------------- */
		JSONArray businessProcessList = DataWarehouse.getBusinessProcessList(loginObject, startdate, enddate);
		if (businessProcessList != null) {
			if (businessProcessList.length() > 0) {
				Element xmlbusinessProcesstype = doc.createElement("types");
				xmlroot.appendChild(xmlbusinessProcesstype);
				for (int i = 0; i < businessProcessList.length(); i++) {
					try {
						Element xmlbusinessProcessMembers = doc.createElement("members");
						xmlbusinessProcessMembers.appendChild(
								doc.createTextNode(businessProcessList.getJSONObject(i).getString("Name")));
						xmlbusinessProcesstype.appendChild(xmlbusinessProcessMembers);
						 System.out.println("Class Name : " +
						 businessProcessList.getJSONObject(i).getString("Name"));

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				Element xmlbusinessProcessName = doc.createElement("name");
				xmlbusinessProcessName.appendChild(doc.createTextNode("BusinessProcess"));// Make it dynamic
				xmlbusinessProcesstype.appendChild(xmlbusinessProcessName);
			}
		}
		/* ----------------------BusinessProcess End--------------- */
		/* ---------------------CompactLayout Start--------------- */
		JSONArray compactLayoutList = DataWarehouse.getCompactLayoutList(loginObject, startdate, enddate);
		if (compactLayoutList != null) {
			if (compactLayoutList.length() > 0) {
				Element xmlcompactLayouttype = doc.createElement("types");
				xmlroot.appendChild(xmlcompactLayouttype);

				for (int i = 0; i < compactLayoutList.length(); i++) {
					try {
						Element xmlcompactLayoutMembers = doc.createElement("members");
						xmlcompactLayoutMembers.appendChild(
								doc.createTextNode(compactLayoutList.getJSONObject(i).getString("SobjectType") + "."
										+ compactLayoutList.getJSONObject(i).getString("DeveloperName")));
						xmlcompactLayouttype.appendChild(xmlcompactLayoutMembers);
						
						  System.out.println("CompactLayout Name : " +
						  compactLayoutList.getJSONObject(i).getString("DeveloperName"));
						 
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				Element xmlcompactLayoutName = doc.createElement("name");
				xmlcompactLayoutName.appendChild(doc.createTextNode("CompactLayout"));// Make it dynamic
				xmlcompactLayouttype.appendChild(xmlcompactLayoutName);
			}
		}
		/* ----------------------CompactLayout End--------------- */
		/* ---------------------connectedApplication Start--------------- */
		JSONArray connectedApplicationList = DataWarehouse.getConnectedApplicationList(loginObject, startdate, enddate);
		if (connectedApplicationList != null) {
			if (connectedApplicationList.length() > 0) {
				Element xmlconnectedApplicationtype = doc.createElement("types");
				xmlroot.appendChild(xmlconnectedApplicationtype);
				for (int i = 0; i < connectedApplicationList.length(); i++) {
					try {
						Element xmlconnectedApplicationMembers = doc.createElement("members");
						xmlconnectedApplicationMembers.appendChild(
								doc.createTextNode(connectedApplicationList.getJSONObject(i).getString("Name")));
						xmlconnectedApplicationtype.appendChild(xmlconnectedApplicationMembers);
						
						  System.out.println("connectedApplication Name : " +
						  connectedApplicationList.getJSONObject(i).getString("Name"));
						 
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				Element xmlconnectedApplicationName = doc.createElement("name");
				xmlconnectedApplicationName.appendChild(doc.createTextNode("ConnectedApplication"));// Make it dynamic
				xmlconnectedApplicationtype.appendChild(xmlconnectedApplicationName);
			}
		}
		/* ----------------------Connected Application End--------------- */
		/* ----------------------CustomApplication Start--------------- */
		JSONArray customApplicationList = DataWarehouse.getCustomApplicationList(loginObject, startdate, enddate);
		if (customApplicationList != null) {
			if (customApplicationList.length() > 0) {
				Element xmlcustomApplicationtype = doc.createElement("types");
				xmlroot.appendChild(xmlcustomApplicationtype);
				for (int i = 0; i < customApplicationList.length(); i++) {
					try {
						Element xmlcustomApplicationMembers = doc.createElement("members");
						xmlcustomApplicationMembers.appendChild(
								doc.createTextNode(customApplicationList.getJSONObject(i).getString("DeveloperName")));
						xmlcustomApplicationtype.appendChild(xmlcustomApplicationMembers);
						
						  System.out.println("CustomApplication Name : " +
						  customApplicationList.getJSONObject(i).getString("DeveloperName"));
						 
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				Element xmlcustomApplicationName = doc.createElement("name");
				xmlcustomApplicationName.appendChild(doc.createTextNode("CustomApplication"));// Make it dynamic
				xmlcustomApplicationtype.appendChild(xmlcustomApplicationName);
			}
		}
		/* ----------------------CustomApplication End--------------- */

		/* ----------------------Custom Object Start--------------- */

		JSONArray customobjectList = DataWarehouse.getCustomObjectList(loginObject, startdate, enddate);
		if (customobjectList != null) {
			if (customobjectList.length() > 0) {
				Element xmlcustomobjecttype = doc.createElement("types");
				xmlroot.appendChild(xmlcustomobjecttype);
				for (int i = 0; i < customobjectList.length(); i++) {
					try {
						Element xmlcustomobjectMembers = doc.createElement("members");
						xmlcustomobjectMembers.appendChild(doc
								.createTextNode(customobjectList.getJSONObject(i).getString("DeveloperName") + "__c"));
						xmlcustomobjecttype.appendChild(xmlcustomobjectMembers);
						 System.out.println("Custom Object Name : " +
						customobjectList.getJSONObject(i).getString("DeveloperName"));

					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				Element xmlcustomobjectName = doc.createElement("name");
				xmlcustomobjectName.appendChild(doc.createTextNode("CustomObject"));// Make it dynamic
				xmlcustomobjecttype.appendChild(xmlcustomobjectName);
			}
		}
		/* ----------------------Custom Object End--------------- */
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
		/* ----------------------Custom Field End--------------- */
		/* ----------------------customtab Start--------------- */
		JSONArray customTabList = DataWarehouse.getCustomTabList(loginObject, startdate, enddate);
		if (customTabList != null) {
			if (customTabList.length() > 0) {
				Element xmlcustomTabtype = doc.createElement("types");
				xmlroot.appendChild(xmlcustomTabtype);
				for (int i = 0; i < customTabList.length(); i++) {
					try {
						JSONArray customTabFullname = DataWarehouse.getFullname("CustomTab",
								customTabList.getJSONObject(i).getString("Id"), loginObject);
						Element xmlcustomTabMembers = doc.createElement("members");
						xmlcustomTabMembers.appendChild(
								doc.createTextNode(customTabFullname.getJSONObject(0).getString("FullName")));
						xmlcustomTabtype.appendChild(xmlcustomTabMembers);
						
						  System.out.println( "customTab Name : " +
						  customTabFullname.getJSONObject(0).getString("FullName"));
						 
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				Element xmlcustomTabName = doc.createElement("name");
				xmlcustomTabName.appendChild(doc.createTextNode("CustomTab"));// Make it dynamic
				xmlcustomTabtype.appendChild(xmlcustomTabName);
			}
		}
		/* ----------------------customtab End--------------- */
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
						
						  System.out.println( "dashboard Name : " +
						  dashboardList.getJSONObject(i).getString("DeveloperName"));
						 
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				Element xmldashboardName = doc.createElement("name");
				xmldashboardName.appendChild(doc.createTextNode("Dashboard"));// Make it dynamic
				xmldashboardtype.appendChild(xmldashboardName);
			}
		}
		/* ----------------------dashboard End--------------- */

		/* ----------------------EmailTemplate Start--------------- */
		JSONArray emailTemplateList = DataWarehouse.getEmailTemplateList(loginObject, startdate, enddate);
		if (emailTemplateList != null) {
			if (emailTemplateList.length() > 0) {
				Element xmlemailTemplateListtype = doc.createElement("types");
				xmlroot.appendChild(xmlemailTemplateListtype);
				for (int i = 0; i < emailTemplateList.length(); i++) {
					try {
						Element xmlemailTemplateListMembers = doc.createElement("members");
						xmlemailTemplateListMembers
								.appendChild(doc.createTextNode(emailTemplateList.getJSONObject(i).getString("Name")));
						xmlemailTemplateListtype.appendChild(xmlemailTemplateListMembers);
						 System.out.println("emailTemplateList Name : " +
						 emailTemplateList.getJSONObject(i).getString("Name"));

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				Element xmlemailTemplateListName = doc.createElement("name");
				xmlemailTemplateListName.appendChild(doc.createTextNode("EmailTemplate"));// Make it dynamic
				xmlemailTemplateListtype.appendChild(xmlemailTemplateListName);
			}
		}
		/* ----------------------EmailTemplate End--------------- */
		/* ----------------------FieldSet Start--------------- */
		JSONArray fieldSetList = DataWarehouse.getFieldSetList(loginObject, startdate, enddate);
		if (fieldSetList != null) {
			if (fieldSetList.length() > 0) {
				Element xmlfieldSettype = doc.createElement("types");
				xmlroot.appendChild(xmlfieldSettype);
				for (int i = 0; i < fieldSetList.length(); i++) {
					try {
						Element xmlfieldSetMembers = doc.createElement("members");
						xmlfieldSetMembers.appendChild(
								doc.createTextNode(fieldSetList.getJSONObject(i).getString("DeveloperName")));
						xmlfieldSettype.appendChild(xmlfieldSetMembers);
						 System.out.println("fieldset Name : " +
						 fieldSetList.getJSONObject(i).getString("DeveloperName"));

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				Element xmlxmlfieldSetName = doc.createElement("name");
				xmlxmlfieldSetName.appendChild(doc.createTextNode("FieldSet"));// Make it dynamic
				xmlfieldSettype.appendChild(xmlxmlfieldSetName);
			}
		}
		/* ----------------------FieldSet End--------------- */
		/* ----------------------FlexiPage Start--------------- */
		JSONArray flexiPageList = DataWarehouse.getFlexiPageList(loginObject, startdate, enddate);
		if (flexiPageList != null) {
			if (flexiPageList.length() > 0) {
				Element xmlflexiPageListtype = doc.createElement("types");
				xmlroot.appendChild(xmlflexiPageListtype);
				for (int i = 0; i < flexiPageList.length(); i++) {
					try {
						Element xmlflexiPageListMembers = doc.createElement("members");
						xmlflexiPageListMembers.appendChild(
								doc.createTextNode(flexiPageList.getJSONObject(i).getString("DeveloperName")));
						xmlflexiPageListtype.appendChild(xmlflexiPageListMembers);
						 System.out.println("flexiPageList Name : " +
						 flexiPageList.getJSONObject(i).getString("DeveloperName"));

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				Element xmlflexiPageListName = doc.createElement("name");
				xmlflexiPageListName.appendChild(doc.createTextNode("FlexiPage"));// Make it dynamic
				xmlflexiPageListtype.appendChild(xmlflexiPageListName);
			}
		}
		/* ----------------------FlexiPage End--------------- */
		/* ----------------------Flow Start--------------- */
		JSONArray flowList = DataWarehouse.getFlowList(loginObject, startdate, enddate);
		if (flowList != null) {
			if (flowList.length() > 0) {
				Element xmlflowtype = doc.createElement("types");
				xmlroot.appendChild(xmlflowtype);
				for (int i = 0; i < flowList.length(); i++) {
					try {
						JSONArray flowFullname = DataWarehouse.getFullname("Flow",
								flowList.getJSONObject(i).getString("Id"), loginObject);
						Element xmlflowMembers = doc.createElement("members");
						xmlflowMembers
								.appendChild(doc.createTextNode(flowFullname.getJSONObject(0).getString("FullName")));
						xmlflowtype.appendChild(xmlflowMembers);
						 System.out.println("Flow Name : " +
						 flowFullname.getJSONObject(0).getString("FullName"));

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
		/* ----------------------GlobalValueSet Start--------------- */
		JSONArray globalValueSetList = DataWarehouse.getGlobalValueSetList(loginObject, startdate, enddate);
		if (globalValueSetList != null) {
			if (globalValueSetList.length() > 0) {
				Element xmlglobalValueSettype = doc.createElement("types");
				xmlroot.appendChild(xmlglobalValueSettype);
				for (int i = 0; i < globalValueSetList.length(); i++) {
					try {
						Element xmlglobalValueSetMembers = doc.createElement("members");
						xmlglobalValueSetMembers.appendChild(
								doc.createTextNode(globalValueSetList.getJSONObject(i).getString("DeveloperName")));
						xmlglobalValueSettype.appendChild(xmlglobalValueSetMembers);
						 System.out.println("GlobalValueSet Name : " +
						 globalValueSetList.getJSONObject(i).getString("DeveloperName"));

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				Element xmlglobalValueSetName = doc.createElement("name");
				xmlglobalValueSetName.appendChild(doc.createTextNode("GlobalValueSet"));// Make it dynamic
				xmlglobalValueSettype.appendChild(xmlglobalValueSetName);
			}
		}
		/* ----------------------GlobalValueSet End--------------- */
		/* ----------------------HomePageLayout Start--------------- */
		JSONArray homePageLayoutList = DataWarehouse.getHomePageLayoutList(loginObject, startdate, enddate);
		if (homePageLayoutList != null) {
			if (homePageLayoutList.length() > 0) {
				Element xmlhomePageLayouttype = doc.createElement("types");
				xmlroot.appendChild(xmlhomePageLayouttype);
				for (int i = 0; i < homePageLayoutList.length(); i++) {
					try {
						Element xmlhomePageLayoutMembers = doc.createElement("members");
						xmlhomePageLayoutMembers
								.appendChild(doc.createTextNode(homePageLayoutList.getJSONObject(i).getString("Name")));
						xmlhomePageLayouttype.appendChild(xmlhomePageLayoutMembers);
						System.out.println("homepagelayout - "+homePageLayoutList.getJSONObject(i).getString("Name"));

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				Element xmlhomePageLayoutName = doc.createElement("name");
				xmlhomePageLayoutName.appendChild(doc.createTextNode("HomePageLayout"));// Make it dynamic
				xmlhomePageLayouttype.appendChild(xmlhomePageLayoutName);
			}
		}
		/* ----------------------HomePageLayout End--------------- */
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
					xmlcustomfieldMembers.appendChild(
							doc.createTextNode(customObjectName + "." + layoutList.getJSONObject(j).getString("Name")));
					xmlcustomfieldtype.appendChild(xmlcustomfieldMembers);
					 System.out.println("layouts - "+customObjectName + "." +
					 layoutList.getJSONObject(j).getString("Name"));
				}

				Element xmlcustomfieldName = doc.createElement("name");
				xmlcustomfieldName.appendChild(doc.createTextNode("Layout"));// Make it dynamic
				xmlcustomfieldtype.appendChild(xmlcustomfieldName);
			}
		}
		/* ----------------------layout End--------------- */
		/* ----------------------PermissionSet Start--------------- */
		JSONArray permissionSetList = DataWarehouse.getPermissionSetList(loginObject, startdate, enddate);
		if (permissionSetList != null) {
			if (permissionSetList.length() > 0) {
				Element xmlpermissionSettype = doc.createElement("types");
				xmlroot.appendChild(xmlpermissionSettype);
				for (int i = 0; i < permissionSetList.length(); i++) {
					try {
						Element xmlpermissionSetMembers = doc.createElement("members");
						xmlpermissionSetMembers
								.appendChild(doc.createTextNode(permissionSetList.getJSONObject(i).getString("Name")));
						xmlpermissionSettype.appendChild(xmlpermissionSetMembers);
						
						  System.out.println( "permissionSet Name : " +
						  permissionSetList.getJSONObject(i).getString("Name"));
						 
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				Element xmlpermissionSetName = doc.createElement("name");
				xmlpermissionSetName.appendChild(doc.createTextNode("PermissionSet"));// Make it dynamic
				xmlpermissionSettype.appendChild(xmlpermissionSetName);
			}
		}
		/* ----------------------permissionSet End--------------- */
		/* ----------------------Profile Start--------------- */
		JSONArray profileList = DataWarehouse.getProfileList(loginObject, startdate, enddate);
		if (profileList != null) {
			if (profileList.length() > 0) {
				Element xmlprofiletype = doc.createElement("types");
				xmlroot.appendChild(xmlprofiletype);
				for (int i = 0; i < profileList.length(); i++) {
					try {
						Element xmlprofileMembers = doc.createElement("members");
						xmlprofileMembers
								.appendChild(doc.createTextNode(profileList.getJSONObject(i).getString("Name")));
						xmlprofiletype.appendChild(xmlprofileMembers);
						 System.out.println("Profile Name : " +
						profileList.getJSONObject(i).getString("Name"));

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				Element xmlprofileName = doc.createElement("name");
				xmlprofileName.appendChild(doc.createTextNode("Profile"));// Make it dynamic
				xmlprofiletype.appendChild(xmlprofileName);
			}
		}
		/* ----------------------profile End--------------- */

		/* ----------------------RecordType Start--------------- */
		JSONArray recordTypeList = DataWarehouse.getRecordTypeList(loginObject, startdate, enddate);
		if (recordTypeList != null) {
			if (recordTypeList.length() > 0) {
				Element xmlrecordTypetype = doc.createElement("types");
				xmlroot.appendChild(xmlrecordTypetype);
				for (int i = 0; i < recordTypeList.length(); i++) {
					try {
						Element xmlrecordTypeMembers = doc.createElement("members");
						xmlrecordTypeMembers
								.appendChild(doc.createTextNode(recordTypeList.getJSONObject(i).getString("Name") + "."
										+ recordTypeList.getJSONObject(i).getString("Name")));
						xmlrecordTypetype.appendChild(xmlrecordTypeMembers);
System.out.println("Rescord type - "+recordTypeList.getJSONObject(i).getString("Name") + "."
										+ recordTypeList.getJSONObject(i).getString("Name"));
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
						 System.out.println("report Name : " +
						 reportList.getJSONObject(i).getString("DeveloperName"));

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				Element xmlreportName = doc.createElement("name");
				xmlreportName.appendChild(doc.createTextNode("Report"));// Make it dynamic
				xmlreporttype.appendChild(xmlreportName);
			}
		}
		/* ---------------------report End--------------- */

		/* ----------------------StaticResource Start--------------- */
		JSONArray staticResourceList = DataWarehouse.getStaticResourceList(loginObject, startdate, enddate);
		if (staticResourceList != null) {
			if (staticResourceList.length() > 0) {
				Element xmlstaticResourcetype = doc.createElement("types");
				xmlroot.appendChild(xmlstaticResourcetype);
				for (int i = 0; i < staticResourceList.length(); i++) {
					try {
						Element xmlstaticResourceMembers = doc.createElement("members");
						xmlstaticResourceMembers
								.appendChild(doc.createTextNode(staticResourceList.getJSONObject(i).getString("Name")));
						xmlstaticResourcetype.appendChild(xmlstaticResourceMembers);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				Element xmlstaticResourceName = doc.createElement("name");
				xmlstaticResourceName.appendChild(doc.createTextNode("StaticResource"));// Make it dynamic
				xmlstaticResourcetype.appendChild(xmlstaticResourceName);
			}
		}
		/* ----------------------StaticResource End--------------- */
		/* ----------------------User Start--------------- */

		JSONArray UserList = DataWarehouse.getUserList(loginObject, startdate, enddate);
		if (UserList != null) {
			if (UserList.length() > 0) {
				Element xmlUserType = doc.createElement("types");
				xmlroot.appendChild(xmlUserType);
				for (int i = 0; i < UserList.length(); i++) {
					try {
						Element xmlUserMembers = doc.createElement("members");
						xmlUserMembers.appendChild(doc.createTextNode(UserList.getJSONObject(i).getString("Name")));
						xmlUserType.appendChild(xmlUserMembers);
						 System.out.println(UserList.getJSONObject(i).getString("Name"));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				Element xmlUserName = doc.createElement("name");
				xmlUserName.appendChild(doc.createTextNode("User"));
				xmlUserType.appendChild(xmlUserName);
			}
		}
		/* ----------------------User end--------------- */
		/* ----------------------ValidationRule Start--------------- */
		JSONArray validationRuleList = DataWarehouse.getValidationRuleList(loginObject, startdate, enddate);
		if (validationRuleList != null) {
			if (validationRuleList.length() > 0) {
				Element xmlvalidationRuletype = doc.createElement("types");
				xmlroot.appendChild(xmlvalidationRuletype);
				for (int i = 0; i < validationRuleList.length(); i++) {
					try {
						Element xmlvalidationRuleMembers = doc.createElement("members");
						xmlvalidationRuleMembers
								.appendChild(doc.createTextNode(DataWarehouse.getValidationRuleObjectName(loginObject,
										validationRuleList.getJSONObject(i).getString("Id"))));
						xmlvalidationRuletype.appendChild(xmlvalidationRuleMembers);
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
		// ----------------------------WebLink------------------
		JSONArray WebLinkList = DataWarehouse.geWebLinkList(loginObject, startdate, enddate);
		if (WebLinkList != null) {
			if (WebLinkList.length() > 0) {
				Element xmlWebLinkType = doc.createElement("types");
				xmlroot.appendChild(xmlWebLinkType);
				for (int i = 0; i < WebLinkList.length(); i++) {
					try {
						Element xmlWebLinkMembers = doc.createElement("members");
						xmlWebLinkMembers
								.appendChild(doc.createTextNode(WebLinkList.getJSONObject(i).getString("Name")));
						xmlWebLinkType.appendChild(xmlWebLinkMembers);

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				Element xmlWebLinkName = doc.createElement("name");
				xmlWebLinkName.appendChild(doc.createTextNode("WebLink"));
				xmlWebLinkType.appendChild(xmlWebLinkName);
			}
		}
		// ----------------------------WebLink------------------
		// -------------------------------WorkFlowAlert-------------------
		JSONArray objWorkFlowAlert = DataWarehouse.getWorkflowAlertList(loginObject, startdate, enddate);
		if (objWorkFlowAlert != null) {
			if (objWorkFlowAlert.length() > 0) {
				Element xmlobjWorkFlowAlerttype = doc.createElement("types");
				xmlroot.appendChild(xmlobjWorkFlowAlerttype);
				try {
					for (int k = 0; k < objWorkFlowAlert.length(); k++) {
						JSONArray jsonworkflowaler = DataWarehouse.getFullname("WorkFlowAlert",
								objWorkFlowAlert.getJSONObject(k).getString("Id"), loginObject);
						Element xmlobjworkFlowAlertMembers = doc.createElement("members");
						xmlobjworkFlowAlertMembers.appendChild(
								doc.createTextNode(jsonworkflowaler.getJSONObject(0).getString("FullName")));
						xmlobjWorkFlowAlerttype.appendChild(xmlobjworkFlowAlertMembers);
						System.out.println(jsonworkflowaler.getJSONObject(0).getString("FullName"));
					}
					Element xmlobjWorkFlowAlertName = doc.createElement("name");
					xmlobjWorkFlowAlertName.appendChild(doc.createTextNode("WorkFlowAlert"));// Make it dynamic
					xmlobjWorkFlowAlerttype.appendChild(xmlobjWorkFlowAlertName);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		// -------------------------------WorkFlowAlert-------------------
		/* ----------------------WorkflowFieldUpdate Start--------------- */
		JSONArray workflowFieldUpdateList = DataWarehouse.getWorkflowFieldUpdateList(loginObject, startdate, enddate);
		if (workflowFieldUpdateList != null) {
			if (workflowFieldUpdateList.length() > 0) {
				Element xmlworkflowFieldUpdatetype = doc.createElement("types");
				xmlroot.appendChild(xmlworkflowFieldUpdatetype);
				for (int i = 0; i < workflowFieldUpdateList.length(); i++) {
					try {
						Element xmlworkflowFieldUpdateMembers = doc.createElement("members");
						xmlworkflowFieldUpdateMembers.appendChild(doc.createTextNode(
								workflowFieldUpdateList.getJSONObject(i).getString("SourceTableEnumOrId") + "."
										+ workflowFieldUpdateList.getJSONObject(i).getString("Name")));
						xmlworkflowFieldUpdatetype.appendChild(xmlworkflowFieldUpdateMembers);
						/*
						 * System.out.println("workflowFieldUpdate Name : "+workflowFieldUpdateList.
						 * getJSONObject(i).getString("SourceTableEnumOrId") + "." +
						 * workflowFieldUpdateList.getJSONObject(i).getString("Name"));
						 */
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				Element xmlworkflowFieldUpdateName = doc.createElement("name");
				xmlworkflowFieldUpdateName.appendChild(doc.createTextNode("WorkflowFieldUpdate"));// Make it dynamic
				xmlworkflowFieldUpdatetype.appendChild(xmlworkflowFieldUpdateName);
			}
		}
		/* ----------------------workflowfieldupdate End--------------- */
		/* ----------------------workflowRule Start--------------- */
		JSONArray workflowRuleList = DataWarehouse.getWorkflowRuleList(loginObject, startdate, enddate);
		if (workflowRuleList != null) {
			if (workflowRuleList.length() > 0) {
				Element xmlworkflowRuletype = doc.createElement("types");
				xmlroot.appendChild(xmlworkflowRuletype);
				for (int i = 0; i < workflowRuleList.length(); i++) {
					try {
						Element xmlworkflowRuleMembers = doc.createElement("members");
						xmlworkflowRuleMembers.appendChild(
								doc.createTextNode(workflowRuleList.getJSONObject(i).getString("TableEnumOrId") + "."
										+ workflowRuleList.getJSONObject(i).getString("Name")));
						xmlworkflowRuletype.appendChild(xmlworkflowRuleMembers);
						/*
						 * System.out .println("WorkflowRule Name : " +
						 * workflowRuleList.getJSONObject(i).getString("Name"));
						 */
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				Element xmlworkflowRuleName = doc.createElement("name");
				xmlworkflowRuleName.appendChild(doc.createTextNode("WorkflowRule"));// Make it dynamic
				xmlworkflowRuletype.appendChild(xmlworkflowRuleName);
			}
		}
		/* ----------------------WorkflowRule End--------------- */
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
		StreamResult result = new StreamResult(new File("E://"
				+ (new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss").format(new Date()).toString()) + ".xml"));
		transformer.transform(source, result);
		long endTime = System.nanoTime();
		long totalTime = endTime - startTime;
		System.out.println(totalTime + " Total Time - " + ((double) totalTime / 1000000000.0));
	}

}
