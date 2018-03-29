package DataContainer;

public class ToolingQueryList {
	public static String getCustomObjects(String startdate, String enddate) {

		if ((startdate.equalsIgnoreCase("") || startdate == null)
				&& (enddate.equalsIgnoreCase("") || enddate == null)) {
			return "select+Id,DeveloperName,CreatedById,CreatedDate,LastModifiedDate+from+CustomObject+order+by+DeveloperName+asc";

		} else if (enddate.equalsIgnoreCase("") || enddate == null) {
			return "select+Id,DeveloperName,CreatedById,CreatedDate,LastModifiedDate+from+CustomObject+where+LastModifiedDate%3E"
					+ startdate + "+order+by+DeveloperName+asc";

		} else if (startdate.equalsIgnoreCase("") || startdate == null) {
			return "select+Id,DeveloperName,CreatedById,CreatedDate,LastModifiedDate+from+CustomObject+where+LastModifiedDate%3E"
					+ enddate + "+order+by+DeveloperName+asc";
		} else {

			return "select+Id,DeveloperName,CreatedById,CreatedDate,LastModifiedDate+from+CustomObject+where+LastModifiedDate%3E"
					+ startdate + "+and+LastModifiedDate%3C" + enddate + "+order+by+DeveloperName+asc";
		}
	}

	public static String getCustomFields(String startdate, String enddate) {

		if ((startdate.equalsIgnoreCase("") || startdate == null)
				&& (enddate.equalsIgnoreCase("") || enddate == null)) {
			return "select+Id,DeveloperName,TableEnumOrId,LastModifiedDate+from+CustomField+order+by+TableEnumOrId+desc";

		} else if (enddate.equalsIgnoreCase("") || enddate == null) {
			return "select+Id,DeveloperName,TableEnumOrId,LastModifiedDate+from+CustomField+where+LastModifiedDate%3E"
					+ startdate + "+order+by+TableEnumOrId+desc";

		} else if (startdate.equalsIgnoreCase("") || startdate == null) {
			return "select+Id,DeveloperName,TableEnumOrId,LastModifiedDate+from+CustomField+where+LastModifiedDate%3E"
					+ enddate + "+order+by+TableEnumOrId+desc";
		} else {

			return "select+Id,DeveloperName,TableEnumOrId,LastModifiedDate+from+CustomField+where+LastModifiedDate%3E"
					+ startdate + "+and+LastModifiedDate%3C" + enddate + "+order+by+TableEnumOrId+desc";
		}
	}

	public static String getCustom_StdObjectFields(String startdate, String enddate, String objectId) {

		if ((startdate.equalsIgnoreCase("") || startdate == null)
				&& (enddate.equalsIgnoreCase("") || enddate == null)) {
			return "select+Id,DeveloperName,CreatedById,TableEnumOrId,LastModifiedDate+from+CustomField+where+TableEnumOrId='"
					+ objectId + "'+order+by+DeveloperName+asc";

		} else if (enddate.equalsIgnoreCase("") || enddate == null) {
			return "select+Id,DeveloperName,CreatedById,TableEnumOrId,LastModifiedDate+from+CustomField+where+TableEnumOrId='"
					+ objectId + "'+and+LastModifiedDate%3E" + startdate + "+order+by+DeveloperName+asc";

		} else if (startdate.equalsIgnoreCase("") || startdate == null) {
			return "select+Id,DeveloperName,CreatedById,TableEnumOrId,LastModifiedDate+from+CustomField+where+TableEnumOrId='"
					+ objectId + "'+and+LastModifiedDate%3E" + enddate + "+order+by+DeveloperName+asc";
		} else {

			return "select+Id,DeveloperName,CreatedById,TableEnumOrId,LastModifiedDate+from+CustomField+where+TableEnumOrId='"
					+ objectId + "'+and+LastModifiedDate%3E" + startdate + "+and+LastModifiedDate%3C" + enddate
					+ "+order+by+DeveloperName+asc";
		}
	}

	public static String getApexTriggers(String startdate, String enddate) {

		if ((startdate.equalsIgnoreCase("") || startdate == null)
				&& (enddate.equalsIgnoreCase("") || enddate == null)) {
			return "select+Id,Name,CreatedById,LastModifiedDate+from+ApexTrigger+order+by+Name+asc";

		} else if (enddate.equalsIgnoreCase("") || enddate == null) {
			return "select+Id,Name,CreatedById,LastModifiedDate+from+ApexTrigger+where+LastModifiedDate%3E" + startdate
					+ "+order+by+Name+asc";

		} else if (startdate.equalsIgnoreCase("") || startdate == null) {
			return "select+Id,Name,CreatedById,LastModifiedDate+from+ApexTrigger+where+LastModifiedDate%3E" + enddate
					+ "+order+by+Name+asc";
		} else {

			return "select+Id,Name,CreatedById,LastModifiedDate+from+ApexTrigger+where+LastModifiedDate%3E" + startdate
					+ "+and+LastModifiedDate%3C" + enddate + "+order+by+Name+asc";
		}
	}

	public static String getApexClasses(String startdate, String enddate) {

		if ((startdate.equalsIgnoreCase("") || startdate == null)
				&& (enddate.equalsIgnoreCase("") || enddate == null)) {
			return "select+Id,Name,CreatedById,LastModifiedDate+from+ApexClass+order+by+name+asc";

		} else if (enddate.equalsIgnoreCase("") || enddate == null) {
			return "select+Id,Name,CreatedById,LastModifiedDate+from+ApexClass+where+LastModifiedDate%3E" + startdate
					+ "+order+by+name+asc";

		} else if (startdate.equalsIgnoreCase("") || startdate == null) {
			return "select+Id,Name,CreatedById,LastModifiedDate+from+ApexClass+where+LastModifiedDate%3E" + enddate
					+ "+order+by+name+asc";
		} else {

			return "select+Id,Name,CreatedById,LastModifiedDate+from+ApexClass+where+LastModifiedDate%3E" + startdate
					+ "+and+LastModifiedDate%3C" + enddate + "+order+by+name+asc";
		}
	}

	public static String getObjectNameQuery(String CustomObjectId) {
		return "select+Id,CreatedById,DeveloperName+from+CustomObject+where+Id='" + CustomObjectId + "'";
	}

	public static String getCustomObjects() {
		return "select+Id,CreatedById,DeveloperName,LastModifiedDate+from+CustomObject";
	}

	public static String getStdObject(String startdate, String enddate) {
		if ((startdate.equalsIgnoreCase("") || startdate == null)
				&& (enddate.equalsIgnoreCase("") || enddate == null)) {
			return "select+Id,CreatedById,TableEnumOrId,LastModifiedDate+from+CustomField+order+by+TableEnumOrId+desc";

		} else if (enddate.equalsIgnoreCase("") || enddate == null) {
			return "select+Id,CreatedById,TableEnumOrId,LastModifiedDate+from+CustomField+where+LastModifiedDate%3E"
					+ startdate + "+order+by+TableEnumOrId+desc";

		} else if (startdate.equalsIgnoreCase("") || startdate == null) {
			return "select+Id,CreatedById,TableEnumOrId,LastModifiedDate+from+CustomField+where+LastModifiedDate%3E"
					+ enddate + "+order+by+TableEnumOrId+desc";
		} else {

			return "select+Id,CreatedById,TableEnumOrId,LastModifiedDate+from+CustomField+where+LastModifiedDate%3E"
					+ startdate + "+and+LastModifiedDate%3C" + enddate + "+order+by+TableEnumOrId+desc";
		}
	}

	public static String getApexComponents(String startdate, String enddate) {
		if ((startdate.equalsIgnoreCase("") || startdate == null)
				&& (enddate.equalsIgnoreCase("") || enddate == null)) {
			return "select+Id,Name,CreatedById,LastModifiedDate+from+ApexComponent+order+by+Name+asc";

		} else if (enddate.equalsIgnoreCase("") || enddate == null) {
			return "select+Id,Name,CreatedById,LastModifiedDate+from+ApexComponent+where+LastModifiedDate%3E"
					+ startdate + "+order+by+Name+asc";

		} else if (startdate.equalsIgnoreCase("") || startdate == null) {
			return "select+Id,Name,CreatedById,LastModifiedDate+from+ApexComponent+where+LastModifiedDate%3E" + enddate
					+ "+order+by+Name+asc";
		} else {
			return "select+Id,Name,CreatedById,LastModifiedDate+from+ApexComponent+where+LastModifiedDate%3E"
					+ startdate + "+and+LastModifiedDate%3C" + enddate + "+order+by+Name+asc";
		}
	}

	public static String getApexPages(String startdate, String enddate) {
		if ((startdate.equalsIgnoreCase("") || startdate == null)
				&& (enddate.equalsIgnoreCase("") || enddate == null)) {
			return "select+Id,Name,CreatedById,LastModifiedDate+from+ApexPage+order+by+Name+asc";

		} else if (enddate.equalsIgnoreCase("") || enddate == null) {
			return "select+Id,Name,CreatedById,LastModifiedDate+from+ApexPage+where+LastModifiedDate%3E" + startdate
					+ "+order+by+Name+asc";

		} else if (startdate.equalsIgnoreCase("") || startdate == null) {
			return "select+Id,Name,CreatedById,LastModifiedDate+from+ApexPage+where+LastModifiedDate%3E" + enddate
					+ "+order+by+Name+asc";
		} else {
			return "select+Id,Name,CreatedById,LastModifiedDate+from+ApexPage+where+LastModifiedDate%3E" + startdate
					+ "+and+LastModifiedDate%3C" + enddate + "+order+by+Name+asc";
		}
	}

	public static String getAuraDefinitionBundles(String startdate, String enddate) {

		if ((startdate.equalsIgnoreCase("") || startdate == null)
				&& (enddate.equalsIgnoreCase("") || enddate == null)) {
			return "select+Id,DeveloperName,CreatedById,LastModifiedDate+from+AuraDefinitionBundle+order+by+DeveloperName+asc";

		} else if (enddate.equalsIgnoreCase("") || enddate == null) {
			return "select+Id,DeveloperName,CreatedById,LastModifiedDate+from+AuraDefinitionBundle+where+LastModifiedDate%3E"
					+ startdate + "+order+by+DeveloperName+asc";

		} else if (startdate.equalsIgnoreCase("") || startdate == null) {
			return "select+Id,DeveloperName,CreatedById,LastModifiedDate+from+AuraDefinitionBundle+where+LastModifiedDate%3E"
					+ enddate + "+order+by+DeveloperName+asc";
		} else {

			return "select+Id,DeveloperName,CreatedById,LastModifiedDate+from+AuraDefinitionBundle+where+LastModifiedDate%3E"
					+ startdate + "+and+LastModifiedDate%3C" + enddate + "+order+by+DeveloperName+asc";
		}
	}

	public static String getAssignmentRules(String startdate, String enddate) {

		if ((startdate.equalsIgnoreCase("") || startdate == null)
				&& (enddate.equalsIgnoreCase("") || enddate == null)) {
			return "select+Id,Name,EntityDefinitionId,CreatedById,LastModifiedDate+from+AssignmentRule+order+by+Name+asc";

		} else if (enddate.equalsIgnoreCase("") || enddate == null) {
			return "select+Id,Name,EntityDefinitionId,CreatedById,LastModifiedDate+from+AssignmentRule+where+LastModifiedDate%3E"
					+ startdate + "+order+by+Name+asc";

		} else if (startdate.equalsIgnoreCase("") || startdate == null) {
			return "select+Id,Name,EntityDefinitionId,CreatedById,LastModifiedDate+from+AssignmentRule+where+LastModifiedDate%3E"
					+ enddate + "+order+by+Name+asc";
		} else {

			return "select+Id,Name,EntityDefinitionId,CreatedById,LastModifiedDate+from+AssignmentRule+where+LastModifiedDate%3E"
					+ startdate + "+and+LastModifiedDate%3C" + enddate + "+order+by+Name+asc";
		}
	}

	public static String getAutoResponseRules(String startdate, String enddate) {

		if ((startdate.equalsIgnoreCase("") || startdate == null)
				&& (enddate.equalsIgnoreCase("") || enddate == null)) {
			return "select+Id,Name,EntityDefinitionId,CreatedById,LastModifiedDate+from+AutoResponseRule+order+by+Name+asc";

		} else if (enddate.equalsIgnoreCase("") || enddate == null) {
			return "select+Id,Name,EntityDefinitionId,CreatedById,LastModifiedDate+from+AutoResponseRule+where+LastModifiedDate%3E"
					+ startdate + "+order+by+Name+asc";

		} else if (startdate.equalsIgnoreCase("") || startdate == null) {
			return "select+Id,Name,EntityDefinitionId,CreatedById,LastModifiedDate+from+AutoResponseRule+where+LastModifiedDate%3E"
					+ enddate + "+order+by+Name+asc";
		} else {

			return "select+Id,Name,EntityDefinitionId,CreatedById,LastModifiedDate+from+AutoResponseRule+where+LastModifiedDate%3E"
					+ startdate + "+and+LastModifiedDate%3C" + enddate + "+order+by+Name+asc";
		}
	}

	public static String getBusinessProcess(String startdate, String enddate) {

		if ((startdate.equalsIgnoreCase("") || startdate == null)
				&& (enddate.equalsIgnoreCase("") || enddate == null)) {
			return "select+Id,Name,EntityDefinitionId,CreatedById,LastModifiedDate+from+BusinessProcess+order+by+Name+asc";

		} else if (enddate.equalsIgnoreCase("") || enddate == null) {
			return "select+Id,Name,EntityDefinitionId,CreatedById,LastModifiedDate+from+BusinessProcess+where+LastModifiedDate%3E"
					+ startdate + "+order+by+Name+asc";

		} else if (startdate.equalsIgnoreCase("") || startdate == null) {
			return "select+Id,Name,EntityDefinitionId,CreatedById,LastModifiedDate+from+BusinessProcess+where+LastModifiedDate%3E"
					+ enddate + "+order+by+Name+asc";
		} else {

			return "select+Id,Name,EntityDefinitionId,CreatedById,LastModifiedDate+from+BusinessProcess+where+LastModifiedDate%3E"
					+ startdate + "+and+LastModifiedDate%3C" + enddate + "+order+by+Name+asc";
		}
	}

	public static String getEmailTemplates(String startdate, String enddate) {

		if ((startdate.equalsIgnoreCase("") || startdate == null)
				&& (enddate.equalsIgnoreCase("") || enddate == null)) {
			return "select+Id,Name,CreatedById,LastModifiedDate+from+EmailTemplate+order+by+Name+asc";

		} else if (enddate.equalsIgnoreCase("") || enddate == null) {
			return "select+Id,Name,CreatedById,LastModifiedDate+from+EmailTemplate+where+LastModifiedDate%3E"
					+ startdate + "+order+by+Name+asc";

		} else if (startdate.equalsIgnoreCase("") || startdate == null) {
			return "select+Id,Name,CreatedById,LastModifiedDate+from+EmailTemplate+where+LastModifiedDate%3E" + enddate
					+ "+order+by+Name+asc";
		} else {

			return "select+Id,Name,CreatedById,LastModifiedDate+from+EmailTemplate+where+LastModifiedDate%3E"
					+ startdate + "+and+LastModifiedDate%3C" + enddate + "+order+by+Name+asc";
		}
	}

	public static String getFieldSet(String startdate, String enddate) {

		if ((startdate.equalsIgnoreCase("") || startdate == null)
				&& (enddate.equalsIgnoreCase("") || enddate == null)) {
			return "select+Id,DeveloperName,CreatedById,LastModifiedDate+from+FieldSet+order+by+DeveloperName+asc";

		} else if (enddate.equalsIgnoreCase("") || enddate == null) {
			return "select+Id,DeveloperName,CreatedById,LastModifiedDate+from+FieldSet+where+LastModifiedDate%3E"
					+ startdate + "+order+by+DeveloperName+asc";

		} else if (startdate.equalsIgnoreCase("") || startdate == null) {
			return "select+Id,DeveloperName,CreatedById,LastModifiedDate+from+FieldSet+where+LastModifiedDate%3E"
					+ enddate + "+order+by+DeveloperName+asc";
		} else {

			return "select+Id,DeveloperName,CreatedById,LastModifiedDate+from+FieldSet+where+LastModifiedDate%3E"
					+ startdate + "+and+LastModifiedDate%3C" + enddate + "+order+by+DeveloperName+asc";
		}
	}

	public static String getFlexiPage(String startdate, String enddate) {

		if ((startdate.equalsIgnoreCase("") || startdate == null)
				&& (enddate.equalsIgnoreCase("") || enddate == null)) {
			return "select+Id,DeveloperName,LastModifiedDate+from+FlexiPage+order+by+DeveloperName+asc";

		} else if (enddate.equalsIgnoreCase("") || enddate == null) {
			return "select+Id,DeveloperName,LastModifiedDate+from+FlexiPage+where+LastModifiedDate%3E" + startdate
					+ "+order+by+DeveloperName+asc";

		} else if (startdate.equalsIgnoreCase("") || startdate == null) {
			return "select+Id,DeveloperName,LastModifiedDate+from+FlexiPage+where+LastModifiedDate%3E" + enddate
					+ "+order+by+DeveloperName+asc";
		} else {

			return "select+Id,DeveloperName,LastModifiedDate+from+FlexiPage+where+LastModifiedDate%3E" + startdate
					+ "+and+LastModifiedDate%3C" + enddate + "+order+by+DeveloperName+asc";
		}
	}

	public static String getGlobalValueSet(String startdate, String enddate) {

		if ((startdate.equalsIgnoreCase("") || startdate == null)
				&& (enddate.equalsIgnoreCase("") || enddate == null)) {
			return "select+Id,DeveloperName,LastModifiedDate+from+GlobalValueSet+order+by+DeveloperName+asc";

		} else if (enddate.equalsIgnoreCase("") || enddate == null) {
			return "select+Id,DeveloperName,LastModifiedDate+from+GlobalValueSet+where+LastModifiedDate%3E" + startdate
					+ "+order+by+DeveloperName+asc";

		} else if (startdate.equalsIgnoreCase("") || startdate == null) {
			return "select+Id,DeveloperName,LastModifiedDate+from+GlobalValueSet+where+LastModifiedDate%3E" + enddate
					+ "+order+by+DeveloperName+asc";
		} else {

			return "select+Id,DeveloperName,LastModifiedDate+from+GlobalValueSet+where+LastModifiedDate%3E" + startdate
					+ "+and+LastModifiedDate%3C" + enddate + "+order+by+DeveloperName+asc";
		}
	}

	public static String getHomePageLayout(String startdate, String enddate) {

		if ((startdate.equalsIgnoreCase("") || startdate == null)
				&& (enddate.equalsIgnoreCase("") || enddate == null)) {
			return "select+Id,Name,CreatedById,LastModifiedDate+from+HomePageLayout+order+by+Name+asc";

		} else if (enddate.equalsIgnoreCase("") || enddate == null) {
			return "select+Id,Name,CreatedById,LastModifiedDate+from+HomePageLayout+where+LastModifiedDate%3E"
					+ startdate + "+order+by+Name+asc";

		} else if (startdate.equalsIgnoreCase("") || startdate == null) {
			return "select+Id,Name,CreatedById,LastModifiedDate+from+HomePageLayout+where+LastModifiedDate%3E" + enddate
					+ "+order+by+Name+asc";
		} else {

			return "select+Id,Name,CreatedById,LastModifiedDate+from+HomePageLayout+where+LastModifiedDate%3E"
					+ startdate + "+and+LastModifiedDate%3C" + enddate + "+order+by+Name+asc";
		}
	}

	public static String getRecordType(String startdate, String enddate) {

		if ((startdate.equalsIgnoreCase("") || startdate == null)
				&& (enddate.equalsIgnoreCase("") || enddate == null)) {
			return "select+Id,Name,SobjectType,CreatedById,LastModifiedDate+from+RecordType+order+by+Name+asc";

		} else if (enddate.equalsIgnoreCase("") || enddate == null) {
			return "select+Id,Name,SobjectType,CreatedById,LastModifiedDate+from+RecordType+where+LastModifiedDate%3E"
					+ startdate + "+order+by+Name+asc";

		} else if (startdate.equalsIgnoreCase("") || startdate == null) {
			return "select+Id,Name,SobjectType,CreatedById,LastModifiedDate+from+RecordType+where+LastModifiedDate%3E"
					+ enddate + "+order+by+Name+asc";
		} else {

			return "select+Id,Name,SobjectType,CreatedById,LastModifiedDate+from+RecordType+where+LastModifiedDate%3E"
					+ startdate + "+and+LastModifiedDate%3C" + enddate + "+order+by+Name+asc";
		}
	}

	public static String getStaticResource(String startdate, String enddate) {

		if ((startdate.equalsIgnoreCase("") || startdate == null)
				&& (enddate.equalsIgnoreCase("") || enddate == null)) {
			return "select+Id,Name,CreatedById,LastModifiedDate+from+StaticResource+order+by+Name+asc";

		} else if (enddate.equalsIgnoreCase("") || enddate == null) {
			return "select+Id,Name,CreatedById,LastModifiedDate+from+StaticResource+where+LastModifiedDate%3E"
					+ startdate + "+order+by+Name+asc";

		} else if (startdate.equalsIgnoreCase("") || startdate == null) {
			return "select+Id,Name,CreatedById,LastModifiedDate+from+StaticResource+where+LastModifiedDate%3E" + enddate
					+ "+order+by+Name+asc";
		} else {

			return "select+Id,Name,CreatedById,LastModifiedDate+from+StaticResource+where+LastModifiedDate%3E"
					+ startdate + "+and+LastModifiedDate%3C" + enddate + "+order+by+Name+asc";
		}
	}

	public static String getValidationRuleID(String startdate, String enddate) {

		if ((startdate.equalsIgnoreCase("") || startdate == null)
				&& (enddate.equalsIgnoreCase("") || enddate == null)) {
			return "select+Id,ValidationName,CreatedById,LastModifiedDate+from+ValidationRule+order+by+ValidationName+asc";

		} else if (enddate.equalsIgnoreCase("") || enddate == null) {
			return "select+Id,ValidationName,CreatedById,LastModifiedDate+from+ValidationRule+where+LastModifiedDate%3E"
					+ startdate + "+order+by+ValidationName+asc";

		} else if (startdate.equalsIgnoreCase("") || startdate == null) {
			return "select+Id,ValidationName,CreatedById,LastModifiedDate+from+ValidationRule+where+LastModifiedDate%3E"
					+ enddate + "+order+by+ValidationName+asc";
		} else {

			return "select+Id,ValidationName,CreatedById,LastModifiedDate+from+ValidationRule+where+LastModifiedDate%3E"
					+ startdate + "+and+LastModifiedDate%3C" + enddate + "+order+by+ValidationName+asc";
		}
	}

	public static String getValidationRuleFullName(String objId) {
		return "select+Id,FullName+from+ValidationRule+where+id='" + objId + "'";
	}
	//-------------------------gupta start-----------------------------
	public static String getWebLinks(String startdate, String enddate) {
		if ((startdate.equalsIgnoreCase("") || startdate == null)
				&& (enddate.equalsIgnoreCase("") || enddate == null)) {
			return "select+Id,Name,LastModifiedDate+from+WebLink+order+by+Name+asc";

		} else if (enddate.equalsIgnoreCase("") || enddate == null) {
			return "select+Id,Name,LastModifiedDate+from+WebLink+where+LastModifiedDate%3E"
					+ startdate + "+order+by+Name+asc";

		} else if (startdate.equalsIgnoreCase("") || startdate == null) {
			return "select+Id,Name,LastModifiedDate+from+WebLink+where+LastModifiedDate%3E"
					+ enddate + "+order+by+Name+asc";
		} else {
		return "select+Id,Name,LastModifiedDate+from+WebLink+where+LastModifiedDate%3E" 
				+startdate+"+and+LastModifiedDate%3C" + enddate + "+order+by+Name+asc";
		}
	}
	public static String getWorkflowAlertid(String startdate, String enddate) {
		if ((startdate.equalsIgnoreCase("") || startdate == null)
				&& (enddate.equalsIgnoreCase("") || enddate == null)) {
			return "select+Id,LastModifiedDate+from+WorkflowAlert";

		} else if (enddate.equalsIgnoreCase("") || enddate == null) {
			return "select+Id,LastModifiedDate+from+WorkflowAlert+where+LastModifiedDate%3E"
					+ startdate + "";

		} else if (startdate.equalsIgnoreCase("") || startdate == null) {
			return "select+Id,LastModifiedDate+from+WorkflowAlert+where+LastModifiedDate%3E"
					+ enddate + "";
		} else {
			return "select+Id,LastModifiedDate+from+WorkflowAlert+where+LastModifiedDate%3E" 
					+startdate+"+and+LastModifiedDate%3C" + enddate + "";
		}
	}


	public static String getWorkflowTaskid(String startdate, String enddate) {
		if ((startdate.equalsIgnoreCase("") || startdate == null)
				&& (enddate.equalsIgnoreCase("") || enddate == null)) {
			return "select+Id,LastModifiedDate+from+WorkflowTask+order+by+FullName+asc";

		} else if (enddate.equalsIgnoreCase("") || enddate == null) {
			return "select+Id,LastModifiedDate+from+WorkflowTask+where+LastModifiedDate%3E"
					+ startdate + "+order+by+FullName+asc";

		} else if (startdate.equalsIgnoreCase("") || startdate == null) {
			return "select+Id,LastModifiedDate+from+WorkflowTask+where+LastModifiedDate%3E"
					+ enddate + "+order+by+FullName+asc";
		} else {
			return "select+Id,LastModifiedDate+from+WorkflowTask+where+LastModifiedDate%3E" 
					+startdate+"+and+LastModifiedDate%3C" + enddate + "";
		}
	}
	public static String getUsers(String startdate, String enddate) {
		if ((startdate.equalsIgnoreCase("") || startdate == null)
				&& (enddate.equalsIgnoreCase("") || enddate == null)) {
			return "select+Id,Name,LastModifiedDate+from+User+order+by+Name+asc";

		} else if (enddate.equalsIgnoreCase("") || enddate == null) {
			return "select+Id,Name,LastModifiedDate+from+User+where+LastModifiedDate%3E"
					+ startdate + "+order+by+Name+asc";

		} else if (startdate.equalsIgnoreCase("") || startdate == null) {
			return "select+Id,Name,LastModifiedDate+from+User+where+LastModifiedDate%3E"
					+ enddate + "+order+by+Name+asc";
		} else {
		return "select+Name,Id,LastModifiedDate+from+User+where+LastModifiedDate%3E" 
				+startdate+"+and+LastModifiedDate%3C" + enddate + "+order+by+name+asc";
		}
	}
	//-------------------------gupta end-----------------------------
	//-------------------------monty start-----------------------------
	public static String getProfile(String startdate, String enddate) {

		if ((startdate.equalsIgnoreCase("") || startdate == null)
				&& (enddate.equalsIgnoreCase("") || enddate == null)) {
			return "select Id,Name,LastModifiedDate from Profile order by Name asc";

		} else if (enddate.equalsIgnoreCase("") || enddate == null) {
			return "select Id,Name,LastModifiedDate from Profile where LastModifiedDate>" + startdate
					+ "+order by Name asc";

		} else if (startdate.equalsIgnoreCase("") || startdate == null) {
			return "select Id,Name,LastModifiedDate from Profile where LastModifiedDate<" + enddate
					+ " order by Name asc";
		} else {

			return "select Id,Name,LastModifiedDate from Profile where LastModifiedDate>" + startdate
					+ "and LastModifiedDate<" + enddate + "order by Name asc";
		}
	}

	public static String getCustomApplication(String startdate, String enddate) {

		if ((startdate.equalsIgnoreCase("") || startdate == null)
				&& (enddate.equalsIgnoreCase("") || enddate == null)) {
			return "select Id,DeveloperName,LastModifiedDate from CustomApplication order by DeveloperName asc";

		} else if (enddate.equalsIgnoreCase("") || enddate == null) {
			return "select Id,DeveloperName,LastModifiedDate from CustomApplication where LastModifiedDate>" + startdate
					+ "+order by DeveloperName asc";

		} else if (startdate.equalsIgnoreCase("") || startdate == null) {
			return "select Id,DeveloperName,LastModifiedDate from CustomApplication where LastModifiedDate<" + enddate
					+ " order by DeveloperName asc";
		} else {

			return "select Id,DeveloperName,LastModifiedDate from CustomApplication where LastModifiedDate>" + startdate
					+ "and LastModifiedDate<" + enddate + "order by DeveloperName asc";
		}
	}

	
	public static String getCompactLayout(String startdate, String enddate) {

		if ((startdate.equalsIgnoreCase("") || startdate == null)
				&& (enddate.equalsIgnoreCase("") || enddate == null)) {
			return "select SobjectType,Id,DeveloperName,LastModifiedDate from CompactLayout order by DeveloperName asc";

		} else if (enddate.equalsIgnoreCase("") || enddate == null) {
			return "select SobjectType,Id,DeveloperName,LastModifiedDate from CompactLayout where LastModifiedDate>"
					+ startdate + "+order by DeveloperName asc";

		} else if (startdate.equalsIgnoreCase("") || startdate == null) {
			return "select SobjectType,Id,DeveloperName,LastModifiedDate from CompactLayout where LastModifiedDate<"
					+ enddate + " order by DeveloperName asc";
		} else {

			return "select SobjectType,Id,DeveloperName,LastModifiedDate from CompactLayout where LastModifiedDate>"
					+ startdate + "and LastModifiedDate<" + enddate + "order by DeveloperName asc";
		}
	}

	public static String getWorkflowFieldUpdate(String startdate, String enddate) {

		if ((startdate.equalsIgnoreCase("") || startdate == null)
				&& (enddate.equalsIgnoreCase("") || enddate == null)) {
			return "select Id,Name,LastModifiedDate,SourceTableEnumOrId  from WorkflowFieldUpdate order by Name asc";

		} else if (enddate.equalsIgnoreCase("") || enddate == null) {
			return "select Id,Name,LastModifiedDate,SourceTableEnumOrId  from WorkflowFieldUpdate where LastModifiedDate>"
					+ startdate + "+order by Name asc";

		} else if (startdate.equalsIgnoreCase("") || startdate == null) {
			return "select Id,Name,LastModifiedDate,SourceTableEnumOrId  from WorkflowFieldUpdate where LastModifiedDate<"
					+ enddate + " order by Name asc";
		} else {

			return "select Id,Name,LastModifiedDate,SourceTableEnumOrId from WorkflowFieldUpdate where LastModifiedDate>"
					+ startdate + "and LastModifiedDate<" + enddate + "order by Name asc";
		}
	}

	public static String getWorkflowRule(String startdate, String enddate) {

		if ((startdate.equalsIgnoreCase("") || startdate == null)
				&& (enddate.equalsIgnoreCase("") || enddate == null)) {
			return "select TableEnumOrId,Name,LastModifiedDate from WorkflowRule order by Name asc";

		} else if (enddate.equalsIgnoreCase("") || enddate == null) {
			return "select TableEnumOrId,Name,LastModifiedDate  from WorkflowRule where LastModifiedDate>" + startdate
					+ "+order by Name asc";

		} else if (startdate.equalsIgnoreCase("") || startdate == null) {
			return "select TableEnumOrId,Name,LastModifiedDate from WorkflowRule where LastModifiedDate<" + enddate
					+ " order by Name asc";
		} else {

			return "select TableEnumOrId,Name,LastModifiedDate from WorkflowRule where LastModifiedDate>" + startdate
					+ "and LastModifiedDate<" + enddate + "order by Name asc";
		}
	}

	public static String getLayout(String startdate, String enddate) {

		if ((startdate.equalsIgnoreCase("") || startdate == null)
				&& (enddate.equalsIgnoreCase("") || enddate == null)) {
			return "select TableEnumOrId,Name,LastModifiedDate,ID from Layout order by Name asc";

		} else if (enddate.equalsIgnoreCase("") || enddate == null) {
			return "select TableEnumOrId,Name,LastModifiedDate,ID  from Layout where LastModifiedDate>" + startdate
					+ "+order by Name asc";

		} else if (startdate.equalsIgnoreCase("") || startdate == null) {
			return "select TableEnumOrId,Name,LastModifiedDate,ID from Layout where LastModifiedDate<" + enddate
					+ " order by Name asc";
		} else {

			return "select TableEnumOrId,Name,LastModifiedDate,ID from Layout where LastModifiedDate>" + startdate
					+ "and LastModifiedDate<" + enddate + "order by Name asc";
		}
	}

	public static String getFlow(String startdate, String enddate) {

		if ((startdate.equalsIgnoreCase("") || startdate == null)
				&& (enddate.equalsIgnoreCase("") || enddate == null)) {
			return "select LastModifiedDate,ID from Flow";

		} else if (enddate.equalsIgnoreCase("") || enddate == null) {
			return "select LastModifiedDate,ID  from Flow where LastModifiedDate>" + startdate;

		} else if (startdate.equalsIgnoreCase("") || startdate == null) {
			return "select LastModifiedDate,ID from Flow where LastModifiedDate<" + enddate;
		} else {

			return "select LastModifiedDate,ID from Flow where LastModifiedDate>" + startdate + "and LastModifiedDate<"
					+ enddate;
		}
	}
	
	public static String getPermissionSet(String startdate, String enddate) {

		if ((startdate.equalsIgnoreCase("") || startdate == null)
				&& (enddate.equalsIgnoreCase("") || enddate == null)) {
			return "select Name,LastModifiedDate from PermissionSet order by Name asc";

		} else if (enddate.equalsIgnoreCase("") || enddate == null) {
			return "select Name,LastModifiedDate from PermissionSet where LastModifiedDate>" + startdate
					+ "+order by Name asc";

		} else if (startdate.equalsIgnoreCase("") || startdate == null) {
			return "select Name,LastModifiedDate from PermissionSet where LastModifiedDate<" + enddate
					+ " order by Name asc";
		} else {

			return "select Name,LastModifiedDate from PermissionSet where LastModifiedDate>" + startdate
					+ "and LastModifiedDate<" + enddate + "order by Name asc";
		}
	}
	
	public static String getConnectedApplication(String startdate, String enddate) {

		if ((startdate.equalsIgnoreCase("") || startdate == null)
				&& (enddate.equalsIgnoreCase("") || enddate == null)) {
			return "select Name,LastModifiedDate from ConnectedApplication order by Name asc";

		} else if (enddate.equalsIgnoreCase("") || enddate == null) {
			return "select Name,LastModifiedDate from ConnectedApplication where LastModifiedDate>" + startdate
					+ "+order by Name asc";

		} else if (startdate.equalsIgnoreCase("") || startdate == null) {
			return "select Name,LastModifiedDate from ConnectedApplication where LastModifiedDate<" + enddate
					+ " order by Name asc";
		} else {

			return "select Name,LastModifiedDate from ConnectedApplication where LastModifiedDate>" + startdate
					+ "and LastModifiedDate<" + enddate + "order by Name asc";
		}
	}
	

	public static String getCustomTab(String startdate, String enddate) {

		if ((startdate.equalsIgnoreCase("") || startdate == null)
				&& (enddate.equalsIgnoreCase("") || enddate == null)) {
			return "select LastModifiedDate,ID from CustomTab";

		} else if (enddate.equalsIgnoreCase("") || enddate == null) {
			return "select LastModifiedDate,ID  from CustomTab where LastModifiedDate>" + startdate;

		} else if (startdate.equalsIgnoreCase("") || startdate == null) {
			return "select LastModifiedDate,ID from CustomTab where LastModifiedDate<" + enddate;
		} else {

			return "select LastModifiedDate,ID from CustomTab where LastModifiedDate>" + startdate + "and LastModifiedDate<"
					+ enddate;
		}
	}
	
	public static String getDashboard(String startdate, String enddate) {

		if ((startdate.equalsIgnoreCase("") || startdate == null)
				&& (enddate.equalsIgnoreCase("") || enddate == null)) {
			return "select DeveloperName,LastModifiedDate from Dashboard order by DeveloperName asc";

		} else if (enddate.equalsIgnoreCase("") || enddate == null) {
			return "select DeveloperName,LastModifiedDate from Dashboard where LastModifiedDate>" + startdate
					+ "+order by DeveloperName asc";

		} else if (startdate.equalsIgnoreCase("") || startdate == null) {
			return "select DeveloperName,LastModifiedDate from Dashboard where LastModifiedDate<" + enddate
					+ " order by DeveloperName asc";
		} else {

			return "select DeveloperName,LastModifiedDate from Dashboard where LastModifiedDate>" + startdate
					+ "and LastModifiedDate<" + enddate + "order by DeveloperName asc";
		}
	}
	
	public static String getReport(String startdate, String enddate) {

		if ((startdate.equalsIgnoreCase("") || startdate == null)
				&& (enddate.equalsIgnoreCase("") || enddate == null)) {
			return "select DeveloperName,LastModifiedDate from Report order by DeveloperName asc";

		} else if (enddate.equalsIgnoreCase("") || enddate == null) {
			return "select DeveloperName,LastModifiedDate from Report where LastModifiedDate>" + startdate
					+ "+order by DeveloperName asc";

		} else if (startdate.equalsIgnoreCase("") || startdate == null) {
			return "select DeveloperName,LastModifiedDate from Report where LastModifiedDate<" + enddate
					+ " order by DeveloperName asc";
		} else {

			return "select DeveloperName,LastModifiedDate from Report where LastModifiedDate>" + startdate
					+ "and LastModifiedDate<" + enddate + "order by DeveloperName asc";
		}
	}

	
	public static String getFullnameQuery(String ToolingSobjectName,String ID) {
		String query="select FullName from "+ToolingSobjectName+" where Id='"+ ID+"'";
		
		return query;
				
		
	}

	//-------------------------monty end-----------------------------
	
}
