package DataContainer;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class GenericData {
	public static String getQuery_Name(String componentName, String startdate, String enddate)
	{
			if ((startdate.equalsIgnoreCase("") || startdate == null)
					&& (enddate.equalsIgnoreCase("") || enddate == null)) {
				return "select+Id,Name,CreatedById,LastModifiedDate+from+"+componentName+"+order+by+Name+asc";

			} else if (enddate.equalsIgnoreCase("") || enddate == null) {
				return "select+Id,Name,CreatedById,LastModifiedDate+from+"+componentName+"+where+LastModifiedDate%3E"
						+ startdate + "+order+by+Name+asc";

			} else if (startdate.equalsIgnoreCase("") || startdate == null) {
				return "select+Id,Name,CreatedById,LastModifiedDate+from+"+componentName+"+where+LastModifiedDate%3E"
						+ enddate + "+order+by+Name+asc";
			} else {

				return "select+Id,Name,CreatedById,LastModifiedDate+from+"+componentName+"+where+LastModifiedDate%3E"
						+ startdate + "+and+LastModifiedDate%3C" + enddate + "+order+by+Name+asc";
		}
	}
	
	public static String getQuery_DevName(String componentName, String startdate, String enddate) {

		if ((startdate.equalsIgnoreCase("") || startdate == null)
				&& (enddate.equalsIgnoreCase("") || enddate == null)) {
			return "select+Id,DeveloperName,CreatedById,LastModifiedDate+from+"+componentName+"+order+by+DeveloperName+asc";

		} else if (enddate.equalsIgnoreCase("") || enddate == null) {
			return "select+Id,DeveloperName,CreatedById,LastModifiedDate+from+"+componentName+"+where+LastModifiedDate%3E"
					+ startdate + "+order+by+DeveloperName+asc";

		} else if (startdate.equalsIgnoreCase("") || startdate == null) {
			return "select+Id,DeveloperName,CreatedById,LastModifiedDate+from+"+componentName+"+where+LastModifiedDate%3E"
					+ enddate + "+order+by+DeveloperName+asc";
		} else {

			return "select+Id,DeveloperName,CreatedById,LastModifiedDate+from+"+componentName+"+where+LastModifiedDate%3E"
					+ startdate + "+and+LastModifiedDate%3C" + enddate + "+order+by+DeveloperName+asc";
		}
	}
	
	public static String getQuery_NameEntityDef(String componentName, String startdate, String enddate) {

		if ((startdate.equalsIgnoreCase("") || startdate == null)
				&& (enddate.equalsIgnoreCase("") || enddate == null)) {
			return "select+Id,Name,EntityDefinitionId,CreatedById,LastModifiedDate+from+"+componentName+"+order+by+Name+asc";

		} else if (enddate.equalsIgnoreCase("") || enddate == null) {
			return "select+Id,Name,EntityDefinitionId,CreatedById,LastModifiedDate+from+"+componentName+"+where+LastModifiedDate%3E"
					+ startdate + "+order+by+Name+asc";

		} else if (startdate.equalsIgnoreCase("") || startdate == null) {
			return "select+Id,Name,EntityDefinitionId,CreatedById,LastModifiedDate+from+"+componentName+"+where+LastModifiedDate%3E"
					+ enddate + "+order+by+Name+asc";
		} else {

			return "select+Id,Name,EntityDefinitionId,CreatedById,LastModifiedDate+from+"+componentName+"+where+LastModifiedDate%3E"
					+ startdate + "+and+LastModifiedDate%3C" + enddate + "+order+by+Name+asc";
		}
	}
	public static JSONArray getComponentList_Name(String componentName,JSONObject loginObject, String startdate, String enddate) {
		JSONArray jsonArray = null;
		String ObjectRestURL = getQuery_Name(componentName,startdate, enddate);
		HttpClient httpClient = HttpClientBuilder.create().build();

		String instanceURL = loginObject.getString("instance_url");
		String AccessToken = loginObject.getString("access_token");

		Header oauthHeader = new BasicHeader("Authorization", "OAuth " + AccessToken);
		String uri = instanceURL + RestResourceURL.getToolingQueryURL(ObjectRestURL);

		HttpResponse response = null;
		HttpGet httpget = new HttpGet(uri);
		httpget.addHeader(oauthHeader);
		try {
			response = httpClient.execute(httpget);
			if (response.getStatusLine().getStatusCode() == 200) {
				String Result = EntityUtils.toString(response.getEntity());
				JSONObject jsonObject = new JSONObject(Result);
				jsonArray = jsonObject.getJSONArray("records");
			}
			else
			{
				System.out.println("GenericData.getComponentList_Name error " +response.getStatusLine());
			}
		} catch (Exception e) {
			System.out.println("Error in GenericData.getComponentList_Name : " + e);
		}
		return jsonArray;
	}
	
	public static JSONArray getComponentList_DevName(String componentName,JSONObject loginObject, String startdate, String enddate) {
		JSONArray jsonArray = null;
		String ObjectRestURL = getQuery_DevName(componentName,startdate, enddate);
		HttpClient httpClient = HttpClientBuilder.create().build();

		String instanceURL = loginObject.getString("instance_url");
		String AccessToken = loginObject.getString("access_token");

		Header oauthHeader = new BasicHeader("Authorization", "OAuth " + AccessToken);
		String uri = instanceURL + RestResourceURL.getToolingQueryURL(ObjectRestURL);

		HttpResponse response = null;
		HttpGet httpget = new HttpGet(uri);
		httpget.addHeader(oauthHeader);
		try {
			response = httpClient.execute(httpget);
			if (response.getStatusLine().getStatusCode() == 200) {
				String Result = EntityUtils.toString(response.getEntity());
				JSONObject jsonObject = new JSONObject(Result);
				jsonArray = jsonObject.getJSONArray("records");
			}
			else
			{
				System.out.println("GenericData.getComponentList_DevName error " +response.getStatusLine());
			}
		} catch (Exception e) {
			System.out.println("Error in GenericData.getComponentList_DevName : " + e);
		}
		return jsonArray;
	}
	
	public static JSONArray getComponentList_NameEntityDef(String componentName,JSONObject loginObject, String startdate, String enddate) {
		JSONArray jsonArray = null;
		String ObjectRestURL = getQuery_NameEntityDef(componentName,startdate, enddate);
		HttpClient httpClient = HttpClientBuilder.create().build();

		String instanceURL = loginObject.getString("instance_url");
		String AccessToken = loginObject.getString("access_token");

		Header oauthHeader = new BasicHeader("Authorization", "OAuth " + AccessToken);
		String uri = instanceURL + RestResourceURL.getToolingQueryURL(ObjectRestURL);

		HttpResponse response = null;
		HttpGet httpget = new HttpGet(uri);
		httpget.addHeader(oauthHeader);
		try {
			response = httpClient.execute(httpget);
			if (response.getStatusLine().getStatusCode() == 200) {
				String Result = EntityUtils.toString(response.getEntity());
				JSONObject jsonObject = new JSONObject(Result);
				jsonArray = jsonObject.getJSONArray("records");
			}
			else
			{
				System.out.println("GenericData.getComponentList_NameEntityDef error " +response.getStatusLine());
			}
		} catch (Exception e) {
			System.out.println("Error in GenericData.getComponentList_NameEntityDef : " + e);
		}
		return jsonArray;
	}
}
