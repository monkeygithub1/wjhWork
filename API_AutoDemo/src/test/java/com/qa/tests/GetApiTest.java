package com.qa.tests;

import java.io.IOException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qa.base.TestBase;
import com.qa.restclient.RestClient;
import com.qa.util.TestUtil;

public class GetApiTest extends TestBase{
	TestBase testBase;
	String host;
	String url;
	RestClient restClient;
	CloseableHttpResponse closeableHttpResponse;
	
	@BeforeClass
	public void setUp(){
		testBase = new TestBase();
		host = prop.getProperty("HOST");
		url = host + "/api/users?page=2";
		
	}
	@Test
	public void getAPITest() throws ClientProtocolException, IOException{
		restClient = new RestClient();
		closeableHttpResponse = restClient.get(url);
		
		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("response status code -->" + statusCode);
		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200, "response status code is not 200.");
		
		
		//////
		String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");
		
		JSONObject responseJson =  JSON.parseObject(responseString);
		System.out.println("respon json from API -->" + responseJson);
		
		String s = TestUtil.getValueByJPath(responseJson, "data[0]/first_name");
		System.out.println(s);
		System.out.println(TestUtil.getValueByJPath(responseJson, "page"));
		System.out.println(TestUtil.getValueByJPath(responseJson, "data[2]/avatar"));
	}

}
