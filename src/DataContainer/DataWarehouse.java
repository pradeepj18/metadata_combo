package DataContainer;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class DataWarehouse {
	public static JSONArray getCustomObjectList(JSONObject loginObject, String startdate, String enddate) {
		// List<String> customObjectList = new ArrayList<String>();
		JSONArray jsonArray = null;
		String ObjectRestURL = ToolingQueryList.getCustomObjects(startdate, enddate);
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
				/*
				 * for (int i = 0; i < jsonArray.length(); i++) {
				 * System.out.println(jsonArray.getJSONObject(i).getString("DeveloperName")); }
				 */
			}
		} catch (Exception e) {
			System.out.println("Error in getCustomObjectList : " + e);
		}
		return jsonArray;
	}

	public static JSONArray getCustomObjectList(JSONObject loginObject) {
		// List<String> customObjectList = new ArrayList<String>();
		JSONArray jsonArray = null;
		String ObjectRestURL = ToolingQueryList.getCustomObjects();
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
				/*
				 * for (int i = 0; i < jsonArray.length(); i++) {
				 * System.out.println(jsonArray.getJSONObject(i).getString("DeveloperName")); }
				 */
			}
		} catch (Exception e) {
			System.out.println("Error in getCustomObjectList : " + e);
		}
		return jsonArray;
	}

	public static JSONArray getCustomFieldList(JSONObject loginObject, String startdate, String enddate,
			String objectname) {
		// List<String> customObjectList = new ArrayList<String>();
		JSONArray jsonArray = null;
		String ObjectRestURL;

		ObjectRestURL = ToolingQueryList.getCustom_StdObjectFields(startdate, enddate, objectname);
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
				/*
				 * for (int i = 0; i < jsonArray.length(); i++) {
				 * System.out.println(jsonArray.getJSONObject(i).getString("DeveloperName")); }
				 */
			}
		} catch (Exception e) {
			System.out.println("Error in getCustomFieldList : " + e);
		}
		return jsonArray;
	}

	public static JSONArray __getCustomFieldList(JSONObject loginObject, String startdate, String enddate) {
		// List<String> customObjectList = new ArrayList<String>();
		JSONArray jsonArray = null;
		String ObjectRestURL;

		ObjectRestURL = ToolingQueryList.getCustomFields(startdate, enddate);
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
				/*
				 * for (int i = 0; i < jsonArray.length(); i++) {
				 * System.out.println(jsonArray.getJSONObject(i).getString("DeveloperName")); }
				 */
			}
		} catch (Exception e) {
			System.out.println("Error in __getCustomFieldList : " + e);
		}
		return jsonArray;
	}

	public static String getCustomObjectName(JSONObject loginObject, String ObjectID) {
		String objname = null;
		String ObjectRestURL = ToolingQueryList.getObjectNameQuery(ObjectID);
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
				JSONArray jsonArray = jsonObject.getJSONArray("records");
				if (jsonArray.length() > 0)
					objname = jsonArray.getJSONObject(0).getString("DeveloperName");
				/*
				 * for (int i = 0; i < jsonArray.length(); i++) {
				 * System.out.println(jsonArray.getJSONObject(i).getString("DeveloperName")); }
				 */
			}
		} catch (Exception e) {
			System.out.println("Error in getCustomObjectName : " + e);
		}
		return objname;
	}

	public static JSONArray getApexTriggerList(JSONObject loginObject, String startdate, String enddate) {
		// List<String> metadtalist = new ArrayList<String>();
		JSONArray jsonArray = null;
		String ObjectRestURL = ToolingQueryList.getApexTriggers(startdate, enddate);

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
				/*
				 * for(int i=0;i<jsonArray.length();i++) {
				 * metadtalist.add(jsonArray.getJSONObject(i).getString("Id")); }
				 */
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonArray;

	}

	public static JSONArray getApexClassList(JSONObject loginObject, String startdate, String enddate) {
		// List<String> metadtalist = new ArrayList<String>();
		JSONArray jsonArray = null;
		String ObjectRestURL = ToolingQueryList.getApexClasses(startdate, enddate);
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonArray;

	}

	public static JSONArray getStdSobjectObjectList(JSONObject loginObject) {

		JSONArray jsonArray = null;
		String ObjectRestURL = RestResourceURL.getSobjectURL();
		HttpClient httpClient = HttpClientBuilder.create().build();

		String instanceURL = loginObject.getString("instance_url");
		String AccessToken = loginObject.getString("access_token");

		Header oauthHeader = new BasicHeader("Authorization", "OAuth " + AccessToken);
		String uri = instanceURL + ObjectRestURL;

		HttpResponse response = null;
		HttpGet httpget = new HttpGet(uri);
		httpget.addHeader(oauthHeader);
		try {
			response = httpClient.execute(httpget);
			if (response.getStatusLine().getStatusCode() == 200) {
				String Result = EntityUtils.toString(response.getEntity());
				JSONObject jsonObject = new JSONObject(Result);
				jsonArray = jsonObject.getJSONArray("sobjects");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonArray;
	}

	public static List<String> getStaticStdObjectList() {
		List<String> objectlist = new ArrayList<String>();
		objectlist.add("Account");
		objectlist.add("Asset");
		objectlist.add("Campaign");
		objectlist.add("Case");
		objectlist.add("Contact");
		objectlist.add("Lead");
		objectlist.add("Opportunity");
		objectlist.add("Product2");
		objectlist.add("User");

		return objectlist;
	}

	public static JSONArray getStdObjectList(JSONObject loginObject, String startdate, String enddate) {
		// List<String> metadtalist = new ArrayList<String>();
		JSONArray jsonArray = null;
		String ObjectRestURL = ToolingQueryList.getStdObject(startdate, enddate);

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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonArray;
	}

	public static JSONArray getApexComponentList(JSONObject loginObject, String startdate, String enddate) {
		// List<String> customObjectList = new ArrayList<String>();
		JSONArray jsonArray = null;
		String ObjectRestURL = ToolingQueryList.getApexComponents(startdate, enddate);
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
		} catch (Exception e) {
			System.out.println("Error in getApexComponentList : " + e);
		}
		return jsonArray;
	}

	public static JSONArray getApexPageList(JSONObject loginObject, String startdate, String enddate) {
		// List<String> customObjectList = new ArrayList<String>();
		JSONArray jsonArray = null;
		String ObjectRestURL = ToolingQueryList.getApexComponents(startdate, enddate);
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
		} catch (Exception e) {
			System.out.println("Error in getApexPageList : " + e);
		}
		return jsonArray;
	}

	public static JSONArray getAuraDefinitionBundleList(JSONObject loginObject, String startdate, String enddate) {
		// List<String> customObjectList = new ArrayList<String>();
		JSONArray jsonArray = null;
		String ObjectRestURL = ToolingQueryList.getAuraDefinitionBundles(startdate, enddate);
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
			} else {
				System.out.println("getAuraDefinitionBundleList error " + response.getStatusLine());
			}
		} catch (Exception e) {
			System.out.println("Error in getAuraDefinitionBundleList : " + e);
		}
		return jsonArray;
	}

	public static JSONArray getAssignmentRuleList(JSONObject loginObject, String startdate, String enddate) {
		// List<String> customObjectList = new ArrayList<String>();
		JSONArray jsonArray = null;
		String ObjectRestURL = ToolingQueryList.getAssignmentRules(startdate, enddate);
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
			} else {
				System.out.println("getAssignmentRulesList error " + response.getStatusLine());
			}
		} catch (Exception e) {
			System.out.println("Error in getAssignmentRulesList : " + e);
		}
		return jsonArray;
	}

	public static JSONArray getAutoResponseList(JSONObject loginObject, String startdate, String enddate) {
		// List<String> customObjectList = new ArrayList<String>();
		JSONArray jsonArray = null;
		String ObjectRestURL = ToolingQueryList.getAutoResponseRules(startdate, enddate);
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
			} else {
				System.out.println("getAutoResponseList error " + response.getStatusLine());
			}
		} catch (Exception e) {
			System.out.println("Error in getAutoResponseList : " + e);
		}
		return jsonArray;
	}

	public static JSONArray getBusinessProcessList(JSONObject loginObject, String startdate, String enddate) {
		// List<String> customObjectList = new ArrayList<String>();
		JSONArray jsonArray = null;
		String ObjectRestURL = ToolingQueryList.getBusinessProcess(startdate, enddate);
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
			} else {
				System.out.println("getBusinessProcessList error " + response.getStatusLine());
			}
		} catch (Exception e) {
			System.out.println("Error in getBusinessProcessList : " + e);
		}
		return jsonArray;
	}

	public static JSONArray getEmailTemplateList(JSONObject loginObject, String startdate, String enddate) {
		// List<String> customObjectList = new ArrayList<String>();
		JSONArray jsonArray = null;
		String ObjectRestURL = ToolingQueryList.getEmailTemplates(startdate, enddate);
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
			} else {
				System.out.println("getEmailTemplateList error " + response.getStatusLine());
			}
		} catch (Exception e) {
			System.out.println("Error in getEmailTemplateList : " + e);
		}
		return jsonArray;
	}

	public static JSONArray getFieldSetList(JSONObject loginObject, String startdate, String enddate) {
		// List<String> customObjectList = new ArrayList<String>();
		JSONArray jsonArray = null;
		String ObjectRestURL = ToolingQueryList.getFieldSet(startdate, enddate);
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
			} else {
				System.out.println("getEmailTemplateList error " + response.getStatusLine());
			}
		} catch (Exception e) {
			System.out.println("Error in getEmailTemplateList : " + e);
		}
		return jsonArray;
	}

	public static JSONArray getFlexiPageList(JSONObject loginObject, String startdate, String enddate) {
		// List<String> customObjectList = new ArrayList<String>();
		JSONArray jsonArray = null;
		String ObjectRestURL = ToolingQueryList.getFlexiPage(startdate, enddate);
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
			} else {
				System.out.println("getFlexiPageList error " + response.getStatusLine());
			}
		} catch (Exception e) {
			System.out.println("Error in getFlexiPageList : " + e);
		}
		return jsonArray;
	}

	public static JSONArray getGlobalValueSetList(JSONObject loginObject, String startdate, String enddate) {
		// List<String> customObjectList = new ArrayList<String>();
		JSONArray jsonArray = null;
		String ObjectRestURL = ToolingQueryList.getGlobalValueSet(startdate, enddate);
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
			} else {
				System.out.println("getGlobalValueSetList error " + response.getStatusLine());
			}
		} catch (Exception e) {
			System.out.println("Error in getGlobalValueSetList : " + e);
		}
		return jsonArray;
	}

	public static JSONArray getHomePageLayoutList(JSONObject loginObject, String startdate, String enddate) {
		// List<String> customObjectList = new ArrayList<String>();
		JSONArray jsonArray = null;
		String ObjectRestURL = ToolingQueryList.getHomePageLayout(startdate, enddate);
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
			} else {
				System.out.println("getHomePageLayoutList error " + response.getStatusLine());
			}
		} catch (Exception e) {
			System.out.println("Error in getHomePageLayoutList : " + e);
		}
		return jsonArray;
	}

	public static JSONArray getRecordTypeList(JSONObject loginObject, String startdate, String enddate) {
		// List<String> customObjectList = new ArrayList<String>();
		JSONArray jsonArray = null;
		String ObjectRestURL = ToolingQueryList.getRecordType(startdate, enddate);
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
			} else {
				System.out.println("getHomePageLayoutList error " + response.getStatusLine());
			}
		} catch (Exception e) {
			System.out.println("Error in getHomePageLayoutList : " + e);
		}
		return jsonArray;
	}

	public static JSONArray getStaticResourceList(JSONObject loginObject, String startdate, String enddate) {
		// List<String> customObjectList = new ArrayList<String>();
		JSONArray jsonArray = null;
		String ObjectRestURL = ToolingQueryList.getStaticResource(startdate, enddate);
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
			} else {
				System.out.println("getStaticResourceList error " + response.getStatusLine());
			}
		} catch (Exception e) {
			System.out.println("Error in getStaticResourceList : " + e);
		}
		return jsonArray;
	}

	public static JSONArray getValidationRuleList(JSONObject loginObject, String startdate, String enddate) {
		// List<String> customObjectList = new ArrayList<String>();
		JSONArray jsonArray = null;
		String ObjectRestURL = ToolingQueryList.getValidationRuleID(startdate, enddate);
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
			} else {
				System.out.println("getValidationRuleList error " + response.getStatusLine());
			}
		} catch (Exception e) {
			System.out.println("Error in getValidationRuleList : " + e);
		}
		return jsonArray;
	}

	public static String getValidationRuleObjectName(JSONObject loginObject, String ObjectID) {
		String objname = null;
		String ObjectRestURL = ToolingQueryList.getValidationRuleFullName(ObjectID);
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
				JSONArray jsonArray = jsonObject.getJSONArray("records");
				if (jsonArray.length() > 0)
					objname = jsonArray.getJSONObject(0).getString("FullName");
			}
		} catch (Exception e) {
			System.out.println("Error in getValidationRuleObjectName : " + e);
		}
		return objname;
	}

	// --------------------gupta start-------------------------
	public static JSONArray getWorkflowTaskList(JSONObject loginObject, String startdate, String enddate) {
		JSONArray jsonArray = null;
		String query = ToolingQueryList.getWorkflowTaskid(startdate,enddate);
		String instanceURL = loginObject.getString("instance_url");
		String AccessToken = loginObject.getString("access_token");
		Header oauthHeader = new BasicHeader("Authorization", "OAuth " + AccessToken);
		String uri = instanceURL + RestResourceURL.getToolingQueryURL(query);
		HttpGet getResult = new HttpGet(uri);
		getResult.addHeader(oauthHeader);
		try {
			HttpClient httpClient = HttpClientBuilder.create().build();
			HttpResponse response = null;
			response = httpClient.execute(getResult);
			if (response.getStatusLine().getStatusCode() == 200) {
				String Result = EntityUtils.toString(response.getEntity());
				JSONObject jsonObject = new JSONObject(Result);
				jsonArray = jsonObject.getJSONArray("records");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonArray;
	}

	public static JSONArray getWorkflowAlertList(JSONObject loginObject, String startdate, String enddate) {
		JSONArray jsonArray = null;
		String query = ToolingQueryList.getWorkflowAlertid(startdate,enddate);
		String instanceURL = loginObject.getString("instance_url");
		String AccessToken = loginObject.getString("access_token");
		Header oauthHeader = new BasicHeader("Authorization", "OAuth " + AccessToken);
		String uri = instanceURL + RestResourceURL.getToolingQueryURL(query);
		HttpGet getResult = new HttpGet(uri);
		getResult.addHeader(oauthHeader);
		try {
			HttpClient httpClient = HttpClientBuilder.create().build();
			HttpResponse response = null;
			response = httpClient.execute(getResult);
			if (response.getStatusLine().getStatusCode() == 200) {
				String Result = EntityUtils.toString(response.getEntity());
				JSONObject jsonObject = new JSONObject(Result);
				jsonArray = jsonObject.getJSONArray("records");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonArray;
	}

	public static JSONArray geWebLinkList(JSONObject loginObject, String startdate, String enddate) {
		JSONArray jsonArray = null;
		String ObjectRestURL = ToolingQueryList.getWebLinks(startdate, enddate);
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonArray;
	}

	public static JSONArray getUserList(JSONObject loginObject, String startdate, String enddate) {
		JSONArray jsonArray = null;
		String ObjectRestURL = ToolingQueryList.getUsers(startdate, enddate);
		HttpClient httpClient = HttpClientBuilder.create().build();
		String instanceURL = loginObject.getString("instance_url");
		String AccessToken = loginObject.getString("access_token");
		Header oauthHeader = new BasicHeader("Authorization", "OAuth " + AccessToken);
		String uri = instanceURL + RestResourceURL.getRestQueryURL(ObjectRestURL);
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonArray;
	}

	// --------------------gupta end---------------------------
	// -------------------------monty start-----------------------------
	public static JSONArray getProfileList(JSONObject loginObject, String startdate, String enddate)
			throws UnsupportedEncodingException {
		// List<String> metadtalist = new ArrayList<String>();
		JSONArray jsonArray = null;
		String URL = ToolingQueryList.getProfile(startdate, enddate);
		String ObjectRestURL = URLEncoder.encode(URL, "UTF-8");
		// System.out.println("uri :"+uri);

		HttpClient httpClient = HttpClientBuilder.create().build();

		String instanceURL = loginObject.getString("instance_url");
		String AccessToken = loginObject.getString("access_token");

		Header oauthHeader = new BasicHeader("Authorization", "OAuth " + AccessToken);

		String uri = instanceURL + RestResourceURL.getToolingQueryURL(ObjectRestURL);
		// String uri= URLEncoder.encode(URL, "UTF-8");
		// System.out.println("uri :"+uri);

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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonArray;

	}

	public static JSONArray getCustomApplicationList(JSONObject loginObject, String startdate, String enddate)
			throws UnsupportedEncodingException {
		// List<String> metadtalist = new ArrayList<String>();
		JSONArray jsonArray = null;
		String URL = ToolingQueryList.getCustomApplication(startdate, enddate);
		String ObjectRestURL = URLEncoder.encode(URL, "UTF-8");
		// System.out.println("uri :"+uri);

		HttpClient httpClient = HttpClientBuilder.create().build();

		String instanceURL = loginObject.getString("instance_url");
		String AccessToken = loginObject.getString("access_token");

		Header oauthHeader = new BasicHeader("Authorization", "OAuth " + AccessToken);

		String uri = instanceURL + RestResourceURL.getToolingQueryURL(ObjectRestURL);
		// String uri= URLEncoder.encode(URL, "UTF-8");
		// System.out.println("uri :"+uri);

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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonArray;

	}

	public static JSONArray getWorkflowFieldUpdateList(JSONObject loginObject, String startdate, String enddate)
			throws UnsupportedEncodingException {
		// List<String> metadtalist = new ArrayList<String>();
		JSONArray jsonArray = null;
		String URL = ToolingQueryList.getWorkflowFieldUpdate(startdate, enddate);
		String ObjectRestURL = URLEncoder.encode(URL, "UTF-8");
		// System.out.println("uri :"+uri);

		HttpClient httpClient = HttpClientBuilder.create().build();

		String instanceURL = loginObject.getString("instance_url");
		String AccessToken = loginObject.getString("access_token");

		Header oauthHeader = new BasicHeader("Authorization", "OAuth " + AccessToken);

		String uri = instanceURL + RestResourceURL.getToolingQueryURL(ObjectRestURL);
		// String uri= URLEncoder.encode(URL, "UTF-8");
		// System.out.println("uri :"+uri);

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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonArray;

	}

	public static JSONArray getCompactLayoutList(JSONObject loginObject, String startdate, String enddate)
			throws UnsupportedEncodingException {
		JSONArray jsonArray = null;
		String URL = ToolingQueryList.getCompactLayout(startdate, enddate);
		String ObjectRestURL = URLEncoder.encode(URL, "UTF-8");
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonArray;

	}

	public static JSONArray getWorkflowRuleList(JSONObject loginObject, String startdate, String enddate)
			throws UnsupportedEncodingException {
		// List<String> metadtalist = new ArrayList<String>();
		JSONArray jsonArray = null;
		String URL = ToolingQueryList.getWorkflowRule(startdate, enddate);
		String ObjectRestURL = URLEncoder.encode(URL, "UTF-8");
		// System.out.println("uri :"+uri);

		HttpClient httpClient = HttpClientBuilder.create().build();

		String instanceURL = loginObject.getString("instance_url");
		String AccessToken = loginObject.getString("access_token");

		Header oauthHeader = new BasicHeader("Authorization", "OAuth " + AccessToken);

		String uri = instanceURL + RestResourceURL.getToolingQueryURL(ObjectRestURL);
		// String uri= URLEncoder.encode(URL, "UTF-8");
		// System.out.println("uri :"+uri);

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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonArray;

	}

	public static JSONArray getLayoutList(JSONObject loginObject, String startdate, String enddate)
			throws UnsupportedEncodingException {
		// List<String> metadtalist = new ArrayList<String>();
		JSONArray jsonArray = null;
		String URL = ToolingQueryList.getLayout(startdate, enddate);
		String ObjectRestURL = URLEncoder.encode(URL, "UTF-8");
		// System.out.println("uri :"+uri);

		HttpClient httpClient = HttpClientBuilder.create().build();

		String instanceURL = loginObject.getString("instance_url");
		String AccessToken = loginObject.getString("access_token");

		Header oauthHeader = new BasicHeader("Authorization", "OAuth " + AccessToken);

		String uri = instanceURL + RestResourceURL.getToolingQueryURL(ObjectRestURL);
		// String uri= URLEncoder.encode(URL, "UTF-8");
		// System.out.println("uri :"+uri);

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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonArray;

	}

	public static JSONArray getFlowList(JSONObject loginObject, String startdate, String enddate)
			throws UnsupportedEncodingException {
		// List<String> metadtalist = new ArrayList<String>();
		JSONArray jsonArray = null;
		String URL = ToolingQueryList.getFlow(startdate, enddate);
		String ObjectRestURL = URLEncoder.encode(URL, "UTF-8");
		// System.out.println("uri :"+uri);

		HttpClient httpClient = HttpClientBuilder.create().build();

		String instanceURL = loginObject.getString("instance_url");
		String AccessToken = loginObject.getString("access_token");

		Header oauthHeader = new BasicHeader("Authorization", "OAuth " + AccessToken);

		String uri = instanceURL + RestResourceURL.getToolingQueryURL(ObjectRestURL);
		// String uri= URLEncoder.encode(URL, "UTF-8");
		// System.out.println("uri :"+uri);

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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonArray;

	}

	
	public static JSONArray getFullname(String ToolingSobjectName, String ID, JSONObject loginObject)
			throws UnsupportedEncodingException {
		// List<String> metadtalist = new ArrayList<String>();
		JSONArray jsonArray = null;

		String URL = ToolingQueryList.getFullnameQuery(ToolingSobjectName, ID);
		String ObjectRestURL = URLEncoder.encode(URL, "UTF-8");
		// System.out.println("uri :"+uri);

		HttpClient httpClient = HttpClientBuilder.create().build();

		String instanceURL = loginObject.getString("instance_url");
		String AccessToken = loginObject.getString("access_token");

		Header oauthHeader = new BasicHeader("Authorization", "OAuth " + AccessToken);

		String uri = instanceURL + RestResourceURL.getToolingQueryURL(ObjectRestURL);
		// String uri= URLEncoder.encode(URL, "UTF-8");
		// System.out.println("uri :"+uri);

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
		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsonArray;

	}

	public static JSONArray getPermissionSetList(JSONObject loginObject, String startdate, String enddate)
			throws UnsupportedEncodingException {
		// List<String> metadtalist = new ArrayList<String>();
		JSONArray jsonArray = null;
		String URL = ToolingQueryList.getPermissionSet(startdate, enddate);
		String ObjectRestURL = URLEncoder.encode(URL, "UTF-8");
		// System.out.println("uri :"+uri);

		HttpClient httpClient = HttpClientBuilder.create().build();

		String instanceURL = loginObject.getString("instance_url");
		String AccessToken = loginObject.getString("access_token");

		Header oauthHeader = new BasicHeader("Authorization", "OAuth " + AccessToken);

		String uri = instanceURL + RestResourceURL.getRestQueryURL(ObjectRestURL);
		// String uri= URLEncoder.encode(URL, "UTF-8");
		// System.out.println("uri :"+uri);

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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonArray;

	}

	public static JSONArray getConnectedApplicationList(JSONObject loginObject, String startdate, String enddate)
			throws UnsupportedEncodingException {
		JSONArray jsonArray = null;
		String URL = ToolingQueryList.getConnectedApplication(startdate, enddate);
		String ObjectRestURL = URLEncoder.encode(URL, "UTF-8");
		HttpClient httpClient = HttpClientBuilder.create().build();

		String instanceURL = loginObject.getString("instance_url");
		String AccessToken = loginObject.getString("access_token");

		Header oauthHeader = new BasicHeader("Authorization", "OAuth " + AccessToken);

		String uri = instanceURL + RestResourceURL.getRestQueryURL(ObjectRestURL);
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonArray;

	}

	public static JSONArray getCustomTabList(JSONObject loginObject, String startdate, String enddate)
			throws UnsupportedEncodingException {
		// List<String> metadtalist = new ArrayList<String>();
		JSONArray jsonArray = null;
		String URL = ToolingQueryList.getCustomTab(startdate, enddate);
		String ObjectRestURL = URLEncoder.encode(URL, "UTF-8");
		// System.out.println("uri :"+uri);

		HttpClient httpClient = HttpClientBuilder.create().build();

		String instanceURL = loginObject.getString("instance_url");
		String AccessToken = loginObject.getString("access_token");

		Header oauthHeader = new BasicHeader("Authorization", "OAuth " + AccessToken);

		String uri = instanceURL + RestResourceURL.getToolingQueryURL(ObjectRestURL);
		// String uri= URLEncoder.encode(URL, "UTF-8");
		// System.out.println("uri :"+uri);

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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonArray;

	}

	public static JSONArray getDashboardList(JSONObject loginObject, String startdate, String enddate)
			throws UnsupportedEncodingException {
		// List<String> metadtalist = new ArrayList<String>();
		JSONArray jsonArray = null;
		String URL = ToolingQueryList.getDashboard(startdate, enddate);
		String ObjectRestURL = URLEncoder.encode(URL, "UTF-8");
		// System.out.println("uri :"+uri);

		HttpClient httpClient = HttpClientBuilder.create().build();

		String instanceURL = loginObject.getString("instance_url");
		String AccessToken = loginObject.getString("access_token");

		Header oauthHeader = new BasicHeader("Authorization", "OAuth " + AccessToken);

		String uri = instanceURL + RestResourceURL.getRestQueryURL(ObjectRestURL);
		// String uri= URLEncoder.encode(URL, "UTF-8");
		// System.out.println("uri :"+uri);

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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonArray;

	}

	public static JSONArray getReportList(JSONObject loginObject, String startdate, String enddate)
			throws UnsupportedEncodingException {
		// List<String> metadtalist = new ArrayList<String>();
		JSONArray jsonArray = null;
		String URL = ToolingQueryList.getReport(startdate, enddate);
		String ObjectRestURL = URLEncoder.encode(URL, "UTF-8");
		// System.out.println("uri :"+uri);

		HttpClient httpClient = HttpClientBuilder.create().build();

		String instanceURL = loginObject.getString("instance_url");
		String AccessToken = loginObject.getString("access_token");

		Header oauthHeader = new BasicHeader("Authorization", "OAuth " + AccessToken);

		String uri = instanceURL + RestResourceURL.getRestQueryURL(ObjectRestURL);
		// String uri= URLEncoder.encode(URL, "UTF-8");
		// System.out.println("uri :"+uri);

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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonArray;

	}
	// -------------------------monty end-----------------------------

}
