package DataContainer;

public class RestResourceURL {
	static final String baseurl = "/services/data/v42.0/";
	public static String getSobjectDescribeURL(String objectname)
	{
		return baseurl+"sobjects/" + objectname + "/describe";
	}
	public static String getToolingQueryURL(String query)
	{
		return baseurl+"tooling/query/?q="+query;
	}
	public static String getSobjectURL()
	{
		return baseurl+"sobjects";
	}
	public static String getApexTriggerURL(String id)
	{
		return baseurl+"tooling/sobjects/ApexTrigger/"+id;
	}
	
	public static String getRestQueryURL(String query)
	{
		return baseurl+"query/?q="+query;
	}
}
