package com.qa.restclient;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.CharsetUtils;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qa.base.TestBase;
import com.qa.util.TestUtil;

public class RestClient {
	String ContentType = "application/x-www-form-urlencoded;charset=utf-8";
	//取响应文本的特定值
	public String getValue(String text, String key){
		JSONObject listJson = JSON.parseObject(text);
		String value = TestUtil.getValueByJPath(listJson, key);
		return value;
	}
	//带cookie的信息头
	public Map<String,String> header() throws ClientProtocolException, IOException {
		//由登录页获取cookie（在响应的headers数组中获取）
		String login = this.url("login");
		CloseableHttpResponse closeableHttpResponse = this.get(login);
		String cookie = closeableHttpResponse.getAllHeaders()[0].getValue();
		//将cookie写到信息头集合
		HashMap<String, String> headermap = new HashMap<String, String>();
		headermap.put("Content-Type", this.ContentType);
		headermap.put("Cookie", cookie);
		return headermap;
	}
	//1、GET请求方法
	public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException{
		//创建一个可关闭的HttpClient对象
		CloseableHttpClient httpclient = HttpClients.createDefault();
		//创建一个HttpGet的请求对象
		HttpGet httpget = new HttpGet(url);
		//执行请求，发送请求并将响应赋值给httpResponse接收
		CloseableHttpResponse httpResponse = httpclient.execute(httpget);
		return httpResponse;
				
	}
	//2、GET请求方法（带请求头信息）
	public CloseableHttpResponse get(String url, HashMap<String, String> headermap) throws ClientProtocolException, IOException{
		//
		CloseableHttpClient httpclient = HttpClients.createDefault();
		
		HttpGet httpget = new HttpGet(url);
		
		for (Map.Entry<String, String> entry : headermap.entrySet()){
			httpget.addHeader(entry.getKey(), entry.getValue());
		}
		
		CloseableHttpResponse httpResponse = httpclient.execute(httpget);
		return httpResponse;
	}
	//3、POST请求方法
	public String post(String url, Map<String, String> entity, Map<String, String> headermap) throws ClientProtocolException, IOException  {
		
		CloseableHttpClient httpclient = HttpClients.createDefault();
		
		HttpPost httppost = new HttpPost(url);
		List<NameValuePair> param=new ArrayList<NameValuePair>(entity.size());
		for(Map.Entry<String, String>one:entity.entrySet()){
			param.add(new BasicNameValuePair(one.getKey(), one.getValue()));
		}
		httppost.setEntity(new UrlEncodedFormEntity(param,CharsetUtils.get("UTF-8")));
		for(Map.Entry<String, String> entry : headermap.entrySet()){
			httppost.addHeader(entry.getKey(), entry.getValue());
		}
		
		
		CloseableHttpResponse httpResponse = httpclient.execute(httppost);
		String responseString = EntityUtils.toString(httpResponse.getEntity());
		return responseString;
	}
	//3.1、POST请求方法（带附件）
	public String post(String url, Map<String, String> entity, Map<String, String> headermap, Map<String,String> fileData) throws ClientProtocolException, IOException  {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url);
		String responseString = "";
		headermap.remove("Content-Type");//上传附件时需要删除信息头里的content-type
		for(Map.Entry<String, String> entry : headermap.entrySet()){
			httppost.addHeader(entry.getKey(), entry.getValue());
		}
		//附件
		try {
			MultipartEntity reqEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE, null, Charset.forName("UTF-8"));
			for(String one: entity.keySet()){
				reqEntity.addPart(one, new StringBody(entity.get(one), Charset.forName("UTF-8")));
			}
			for(String one: fileData.keySet()){
				reqEntity.addPart(one, new FileBody(new File(fileData.get(one))));
			}
			httppost.setEntity(reqEntity);
			CloseableHttpResponse httpResponse = httpclient.execute(httppost);
			responseString = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
		}catch(Exception e){
			e.printStackTrace();
		}
		headermap.put("Content-Type", this.ContentType);
		return responseString;
	}
	//3.1、POST请求方法（带请求头信息，参数是String类型）
	public CloseableHttpResponse post(String url, String entityString, Map<String, String> headermap) throws ClientProtocolException, IOException  {
		
		CloseableHttpClient httpclient = HttpClients.createDefault();
		
		HttpPost httppost = new HttpPost(url);
		
		httppost.setEntity(new StringEntity(entityString));
		
		for(Map.Entry<String, String> entry : headermap.entrySet()){
			httppost.addHeader(entry.getKey(), entry.getValue());
		}
		
		CloseableHttpResponse httpResponse = httpclient.execute(httppost);
		return httpResponse;
	}
	//4、POST请求方法（不带请求头信息）
	public CloseableHttpResponse post(String url, String entityString) throws ClientProtocolException, IOException  {
		
		CloseableHttpClient httpclient = HttpClients.createDefault();
		
		HttpPost httppost = new HttpPost(url);
		httppost.setEntity(new StringEntity(entityString));
							
		CloseableHttpResponse httpResponse = httpclient.execute(httppost);
		return httpResponse;
	}
	//参数拼成串
	public String requestParaString (Map<String,String> para){
		String requestParaString = null;
		boolean isFirst = true;
		for (String key : para.keySet()){
			if (isFirst == true){
				requestParaString = key + "=" + para.get(key);
				isFirst = false;
			}else{
				requestParaString = requestParaString + "&" + key + "=" + para.get(key);
			}
		}
		return requestParaString;
	}
	//拼接口URL
	public String url(String api){
		TestBase testBase = new TestBase();
		String url = testBase.prop.getProperty("HOST")+ testBase.prop.getProperty(api);
		return url;
	}
}
