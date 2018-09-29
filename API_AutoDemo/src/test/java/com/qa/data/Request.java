package com.qa.data;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.client.ClientProtocolException;
import org.testng.Assert;
import com.qa.restclient.RestClient;

public class Request {
	String upload_file = ".\\files_tools\\接口测试专用附件-顺顺最帅.txt";
	//登录（密码和验证码写死了）
	public void login(Map<String, String> headermap, String phone_) throws ClientProtocolException, IOException{
		RestClient restClient =  new RestClient();
		//生成参数集合、生成请求串  
		Map<String, String> para = new RequestPara().login_para(phone_, "65bec0bd5cd4e10818ef9370cf33fa3c", "1");
		String url = restClient.url("api_login");
		//发起请求并获取响应  （登录）
		String responseString = restClient.post(url, para, headermap);//返回结果为ok
		//断言：响应文本  
		Assert.assertEquals(responseString, "ok", "Login : request is not ok.");		
	}
	//获取列表（各种列表）
	public String queryList(String url, Map<String, String> headermap) throws ClientProtocolException, IOException{
		RestClient restClient =  new RestClient();
		//获取待审列表接口  
		//生成参数集合、生成请求串
		Map<String, String> para = new HashMap<String, String>();
		para.put("page", "1");
		para.put("limit", "10");
		//发起请求并获取响应（获取待审列表） 
		String responseString = restClient.post(url, para, headermap);
		return responseString;
	}
	//项目立项：保存（返回projId）发起和编辑
	public String projSave (Map<String, String> headermap, String projId, String proj_name_, String proj_mgr_, String proj_mgr_id_, String dept_, String proj_mgr_type_) throws ClientProtocolException, IOException{
		RestClient restClient =  new RestClient();
		Map<String, String> para = new RequestPara().proj_save_Para(projId, proj_name_, proj_mgr_, proj_mgr_id_, dept_, proj_mgr_type_);
		String url = restClient.url("api_proj_save");
		String responseString = restClient.post(url, para, headermap);//返回结果为projId
		return responseString;
	}
	//项目立项：预算保存
	public void projBudge (Map<String, String> headermap, String proj_id_, String ys_kphte) throws ClientProtocolException, IOException{
		RestClient restClient =  new RestClient();
		Map<String, String> para = new RequestPara().proj_budge_Para(proj_id_, ys_kphte);
		String url = restClient.url("api_proj_budge");
		String responseString = restClient.post(url, para, headermap);//返回结果为ok
		Assert.assertEquals(responseString, "ok", "BudgeSave : request is not ok.");
	}
	//项目立项：上传附件
	public void projUpload (String projId, Map<String, String> headermap) throws ClientProtocolException, IOException{
		RestClient restClient =  new RestClient();
		Map<String, String> para = new HashMap<String, String>();
		para.put("projId", projId);
		Map<String,String> fileData = new HashMap<String,String>();
		fileData.put("file", this.upload_file);
		String url = restClient.url("api_proj_upload");
		String responseString = restClient.post(url, para, headermap, fileData);
		Assert.assertEquals(restClient.getValue(responseString, "code"), "1", "upload : code is not 1.");
	}
	//项目立项：提交
	public void projApply (String projId, Map<String, String> headermap) throws ClientProtocolException, IOException{
		RestClient restClient =  new RestClient();
		Map<String, String> para = new HashMap<String, String>();
		para.put("projId", projId);
		String url = restClient.url("api_proj_apply");
		String responseString = restClient.post(url, para, headermap);
		Assert.assertEquals(responseString, "ok", "upload : code is not ok.");
	}
	//项目立项审批
	public void projSp (String phone_, Map<String, String> headermap, String projId, String sp, String comment) throws ClientProtocolException, IOException{
		this.login(headermap, phone_);
		RestClient restClient =  new RestClient();
		String url_list = restClient.url("api_proj_splist");
		String list = this.queryList(url_list, headermap);//查询待审列表
		String taskId = restClient.getValue(list, "data[0]/taskId");
		Map<String, String> para = new RequestPara().proj_sp_para(projId, taskId, sp, comment);
		String url = restClient.url("api_proj_sp");
		//发起请求并获取响应（获取待审列表） 
		String responseString = restClient.post(url, para, headermap);//返回结果为"ok"
		Assert.assertEquals(responseString, "ok", "ProjSp : request is not ok.");	
	}

}
