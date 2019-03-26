package wjh.pmisproduct.data;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.testng.Assert;

import wjh.pmis.restclient.RestClient;

public class Request {
	String upload_file = ".\\files_tools\\接口测试专用附件-顺顺最帅.txt";
	//登录（密码和验证码写死了）
	public void login(Map<String, String> headermap, String phone_) throws ClientProtocolException, IOException{
		RestClient restClient =  new RestClient();
		//生成参数集合、生成请求串  
		Map<String, String> para = new RequestPara().login_para(phone_, "65bec0bd5cd4e10818ef9370cf33fa3c", "1");
		String url = restClient.url_product("api_login");
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
	//获取ID
	public String getId (Map<String, String> headermap, String api) throws ClientProtocolException, IOException{
		RestClient restClient = new RestClient();
		String url_list = restClient.url_product(api);
		String list = this.queryList(url_list, headermap);//查询我的申请列表
		String id = restClient.getValue(list, "data[0]/id_");
		return id;
	}
	//项目立项：保存
	public String projSave (Map<String, String> headermap, Map<String, String> para) throws ClientProtocolException, IOException{
		RestClient restClient =  new RestClient();
		String url = restClient.url_product("api_proj_save");
		String responseString = restClient.post(url, para, headermap);//返回结果为{"code":1,"data":"projId","msg":"操作成功！"}
		Assert.assertEquals(restClient.getValue(responseString, "msg"), "操作成功！", "ProjApply : request is not 操作成功！.");
		String id = restClient.getValue(responseString, "data");
		return id;
	}
	//项目立项：预算保存
	public void projBudget (Map<String, String> headermap, Map<String, String> para) throws ClientProtocolException, IOException{
		RestClient restClient =  new RestClient();
		String url = restClient.url_product("api_proj_budget");
		String responseString = restClient.post(url, para, headermap);//返回结果为{"code":1,"data":"ok","msg":"操作成功！"}
		Assert.assertEquals(restClient.getValue(responseString, "msg"), "操作成功！", "ProjBudgeSave : request is not 操作成功！");
	}
	//项目立项：提交
	public void projApply (String projId, Map<String, String> headermap) throws ClientProtocolException, IOException{
		RestClient restClient =  new RestClient();
		Map<String, String> para = new HashMap<String, String>();
		para.put("projId", projId);
		String url = restClient.url_product("api_proj_apply");
		String responseString = restClient.post(url, para, headermap);//返回结果为{"code":1,"data":"ok","msg":"操作成功！"}
		Assert.assertEquals(restClient.getValue(responseString, "msg"), "操作成功！", "ProjApply : request is not 操作成功！");
	}
	//项目立项：获取projId
	public String getProjId (Map<String, String> headermap) throws ClientProtocolException, IOException{
		String id = this.getId(headermap, "api_proj_mylist");
		return id;
	}
	//项目立项：审批
	public void projSp (String phone_, Map<String, String> headermap, String projId, String sp, String comment) throws ClientProtocolException, IOException{
		this.login(headermap, phone_);
		RestClient restClient =  new RestClient();
		String url_list = restClient.url_product("api_proj_splist");
		String list = this.queryList(url_list, headermap);//查询待审列表
		String count = restClient.getValue(list, "count");
		if (count.equals("0")){
			Assert.assertEquals(count, "not 0", "ProjSp : 没有这条待审！");
		}
		String id_ = restClient.getValue(list, "data[0]/id_");
		Assert.assertEquals(id_, projId, "ProjSp : 没有这条待审！");
		String taskId = restClient.getValue(list, "data[0]/taskId");
		Map<String, String> para = new RequestPara().proj_sp_para(projId, taskId, sp, comment);
		String url = restClient.url_product("api_proj_sp");
		//发起请求并获取响应
		String responseString = restClient.post(url, para, headermap);//返回结果为{"code":1,"data":"ok","msg":"操作成功！"}
		Assert.assertEquals(restClient.getValue(responseString, "msg"), "操作成功！", "ProjSp : request is not 操作成功！");	
	}
	//甲方合同：保存（返回cpaId）发起和编辑
	public String cpaSave (Map<String, String> headermap, Map<String, String> para) throws ClientProtocolException, IOException{
		RestClient restClient =  new RestClient();
		String url = restClient.url_product("api_cpa_save");
		String responseString = restClient.post(url, para, headermap);//返回结果为cpaId
		if (responseString.equals("fail")){
			Assert.assertEquals(responseString, "cpa id", "CpaSave : request is not cpaId.");
		}
		return responseString;
	}
	//甲方合同：保存付款计划
	public void cpa_mo_Save (Map<String, String> headermap, Map<String, String> para) throws ClientProtocolException, IOException{
		RestClient restClient =  new RestClient();
		String url = restClient.url_product("api_cpa_mo");
		String responseString = restClient.post(url, para, headermap);//返回结果为"ok"
		Assert.assertEquals(responseString, "ok", "CpaMoSave : request is not ok.");
	}
	//甲方合同：保存收票计划
	public void cpa_ii_Save (Map<String, String> headermap, Map<String, String> para) throws ClientProtocolException, IOException{
		RestClient restClient =  new RestClient();
		String url = restClient.url_product("api_cpa_ii");
		String responseString = restClient.post(url, para, headermap);//返回结果为"ok"
		Assert.assertEquals(responseString, "ok", "CpaInSave : request is not ok.");
	}
	//甲方合同：发起申请
	public void cpaApply (Map<String, String> headermap, String cpaId) throws ClientProtocolException, IOException{
		RestClient restClient =  new RestClient();
		Map<String, String> para = new HashMap<String, String>();
		para.put("cpaId", cpaId);
		String url = restClient.url_product("api_cpa_apply");
		String responseString = restClient.post(url, para, headermap);
		Assert.assertEquals(restClient.getValue(responseString, "msg"), "操作成功！", "CpaApply : request is not 操作成功！.");
	}
	//甲方合同：审批
	public void cpaSp(Map<String, String> headermap, String phone_, String cpaId, String sp, String comment) throws ClientProtocolException, IOException{
		this.login(headermap, phone_);
		RestClient restClient = new RestClient();
		String url_list = restClient.url_product("api_cpa_splist");
		String list = this.queryList(url_list, headermap);//查询待审列表
		String count = restClient.getValue(list, "count");
		if (count.equals("0")){
			Assert.assertEquals(count, "not 0", "CpaSp : 没有这条待审！");
		}
		String id_ = restClient.getValue(list, "data[0]/id_");
		Assert.assertEquals(id_, cpaId, "CpaSp : 没有这条待审！");
		String taskId = restClient.getValue(list, "data[0]/taskId");
		Map<String, String> para = new RequestPara().cpa_sp_para(cpaId, taskId, sp, comment);
		String url = restClient.url_product("api_cpa_sp");
		String responseString = restClient.post(url, para, headermap);//返回结果为"ok"
		Assert.assertEquals(restClient.getValue(responseString, "data"), "ok", "CpaSp : request is not ok.");
	}
}
