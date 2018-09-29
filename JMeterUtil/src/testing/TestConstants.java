package testing;

public class TestConstants {
	//https://www.app4cm.net:6442/tt
	//http://10.120.20.253:8080/tt
	public final static String baseUrl="http://localhost:8080/tt";
	public final static String testBaseUrl="https://www.app4cm.net:6442/tt";
	public final static String token="(Ljava/lang/String;I)";
	
	public static String getUrl(String url)
	{
		return TestConstants.baseUrl + url;
	}
	
	public static String getUrl(String url,String whichUrl)
	{
		if("localhost".equals(whichUrl))
		{
			return TestConstants.baseUrl + url;
		}else if("test".equals(whichUrl))
		{
			return TestConstants.testBaseUrl + url;
		}
		
		return TestConstants.baseUrl + url;
	}
}
