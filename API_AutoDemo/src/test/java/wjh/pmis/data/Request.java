package wjh.pmis.data;

import java.io.IOException;
import java.math.BigDecimal;
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
		Map<String, String> para = new RequestPara().login_para(phone_, "e4c4e24edd254bb81fc6e3fe7a1a5dd4", "1");
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
	//获取ID
	public String getId (Map<String, String> headermap, String api) throws ClientProtocolException, IOException{
		RestClient restClient = new RestClient();
		String url_list = restClient.url(api);
		String list = this.queryList(url_list, headermap);//查询我的申请列表
		String id = restClient.getValue(list, "data[0]/id_");
		return id;
	}
	//项目立项：保存（返回projId）发起和编辑
	public String projSave (Map<String, String> headermap, Map<String, String> para) throws ClientProtocolException, IOException{
		RestClient restClient =  new RestClient();
		String url = restClient.url("api_proj_save");
		String responseString = restClient.post(url, para, headermap);//返回结果为projId
		return responseString;
	}
	//项目立项：预算保存
	public void projBudget (Map<String, String> headermap, String proj_id_, String ys_kphte) throws ClientProtocolException, IOException{
		RestClient restClient =  new RestClient();
		Map<String, String> para = new RequestPara().proj_budget_para(proj_id_, ys_kphte);
		String url = restClient.url("api_proj_budget");
		String responseString = restClient.post(url, para, headermap);//返回结果为ok
		Assert.assertEquals(responseString, "ok", "ProjBudgeSave : request is not ok.");
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
		Assert.assertEquals(restClient.getValue(responseString, "code"), "1", "ProjUpload : code is not 1.");
	}
	//项目立项：提交
	public void projApply (String projId, Map<String, String> headermap) throws ClientProtocolException, IOException{
		RestClient restClient =  new RestClient();
		Map<String, String> para = new HashMap<String, String>();
		para.put("projId", projId);
		String url = restClient.url("api_proj_apply");
		String responseString = restClient.post(url, para, headermap);
//		System.out.println(responseString);
		Assert.assertEquals(restClient.getValue(responseString, "msg"), "操作成功！", "ProjApply : request is not 操作成功！.");
	}
	//项目立项：审批
	public void projSp (String phone_, Map<String, String> headermap, String projId, String sp, String comment) throws ClientProtocolException, IOException{
		this.login(headermap, phone_);
		RestClient restClient =  new RestClient();
		String url_list = restClient.url("api_proj_splist");
		String list = this.queryList(url_list, headermap);//查询待审列表
		String count = restClient.getValue(list, "count");
		if (count.equals("0")){
			Assert.assertEquals(count, "not 0", "ProjSp : 没有这条待审！");
		}
		String id_ = restClient.getValue(list, "data[0]/id_");
		Assert.assertEquals(id_, projId, "ProjSp : 没有这条待审！");
		String taskId = restClient.getValue(list, "data[0]/taskId");
		Map<String, String> para = new RequestPara().proj_sp_para(projId, taskId, sp, comment);
		String url = restClient.url("api_proj_sp");
		//发起请求并获取响应
		String responseString = restClient.post(url, para, headermap);//返回结果为"ok"
		Assert.assertEquals(restClient.getValue(responseString, "msg"), "操作成功！", "ProjSp : request is not 操作成功！.");
	}
	//项目立项：获取proj_id_
	public String getProjId (Map<String, String> headermap) throws ClientProtocolException, IOException{
		RestClient restClient = new RestClient();
		String url_list = restClient.url("api_proj_mylist");
		String list = this.queryList(url_list, headermap);//查询我的申请列表
		String id = restClient.getValue(list, "data[0]/id_");
		return id;
	}
	//项目立项：获取proj_name_
	public String getProjName (Map<String, String> headermap) throws ClientProtocolException, IOException{
		RestClient restClient = new RestClient();
		String url_list = restClient.url("api_proj_mylist");
		String list = this.queryList(url_list, headermap);//查询我的申请列表
		String id = restClient.getValue(list, "data[0]/proj_name_");
		return id;
	}
	//项目立项：获取proj_code_
	public String getProjCode (Map<String, String> headermap) throws ClientProtocolException, IOException{
		RestClient restClient = new RestClient();
		String url_list = restClient.url("api_proj_mylist");
		String list = this.queryList(url_list, headermap);//查询我的申请列表
		String id = restClient.getValue(list, "data[0]/proj_code_");
		return id;
	}
	//甲方合同：保存（返回cpaId）发起和编辑
	public String cpaSave (Map<String, String> headermap, Map<String, String> para) throws ClientProtocolException, IOException{
		RestClient restClient =  new RestClient();
		String url = restClient.url("api_cpa_sava");
		String responseString = restClient.post(url, para, headermap);//返回结果为cpaId
		return responseString;
	}
	//甲方合同：保存付款计划
	public void cpa_mo_Save (Map<String, String> headermap, String plan_money_, String cpaId) throws ClientProtocolException, IOException{
		RestClient restClient =  new RestClient();
		Map<String, String> para = new RequestPara().cpa_mo_para(plan_money_, cpaId);
		String url = restClient.url("api_cpa_mo");
		String responseString = restClient.post(url, para, headermap);//返回结果为"ok"
		Assert.assertEquals(responseString, "ok", "CpaMoSave : request is not ok.");
	}
	//甲方合同：保存收票计划
	public void cpa_ii_Save (Map<String, String> headermap, String plan_money_, String cpaId) throws ClientProtocolException, IOException{
		RestClient restClient =  new RestClient();
		Map<String, String> para = new RequestPara().cpa_ii_para(plan_money_, cpaId);
		String url = restClient.url("api_cpa_ii");
		String responseString = restClient.post(url, para, headermap);//返回结果为"ok"
		Assert.assertEquals(responseString, "ok", "CpaInSave : request is not ok.");
	}
	//甲方合同：发起申请
	public void cpaApply (Map<String, String> headermap, String cpaId) throws ClientProtocolException, IOException{
		RestClient restClient =  new RestClient();
		//上传附件
		Map<String, String> para_upload = new HashMap<String, String>();
		para_upload.put("cpaId", cpaId);
		Map<String,String> fileData = new HashMap<String,String>();
		fileData.put("file", this.upload_file);
		String url_upload = restClient.url("api_cpa_upload");
		String responseString_upload = restClient.post(url_upload, para_upload, headermap, fileData);
		Assert.assertEquals(restClient.getValue(responseString_upload, "code"), "1", "CpaUpload : code is not 1.");
		//发起申请
		Map<String, String> para = new HashMap<String, String>();
		para.put("cpaId", cpaId);
		String url = restClient.url("api_cpa_apply");
		String responseString = restClient.post(url, para, headermap);
		Assert.assertEquals(restClient.getValue(responseString, "msg"), "操作成功！", "CpaApply : request is not 操作成功！.");
	}
	//甲方合同：审批
	public void cpaSp(Map<String, String> headermap, String phone_, String cpaId, String sp, String comment) throws ClientProtocolException, IOException{
		this.login(headermap, phone_);
		RestClient restClient = new RestClient();
		String url_list = restClient.url("api_cpa_splist");
		String list = this.queryList(url_list, headermap);//查询待审列表
		String count = restClient.getValue(list, "count");
		if (count.equals("0")){
			Assert.assertEquals(count, "not 0", "CpaSp : 没有这条待审！");
		}
		String id_ = restClient.getValue(list, "data[0]/id_");
		Assert.assertEquals(id_, cpaId, "CpaSp : 没有这条待审！");
		String taskId = restClient.getValue(list, "data[0]/taskId");
		Map<String, String> para = new RequestPara().cpa_sp_para(cpaId, taskId, sp, comment);
		String url = restClient.url("api_cpa_sp");
		String responseString = restClient.post(url, para, headermap);//返回结果为"ok"
		Assert.assertEquals(restClient.getValue(responseString, "msg"), "操作成功！", "CpaSp : request is not 操作成功！.");
	}
	//甲方合同：获取cpaId
	public String getCpaId (Map<String, String> headermap) throws ClientProtocolException, IOException{
		String id = this.getId(headermap, "api_cpa_mylist");
		return id;
	}
	//乙方合同：保存（返回cpbId）发起和编辑
	public String cpbSave (Map<String, String> headermap, Map<String, String> para) throws ClientProtocolException, IOException{
		RestClient restClient =  new RestClient();
		String url = restClient.url("api_cpb_save");
		String responseString = restClient.post(url, para, headermap);//返回结果为cpbId
		return responseString;
	}
	//乙方合同：保存收款计划
	public void cpb_mi_Save (Map<String, String> headermap, String plan_money_, String cpbId) throws ClientProtocolException, IOException{
		RestClient restClient =  new RestClient();
		Map<String, String> para = new RequestPara().cpb_mi_para(plan_money_, cpbId);
		String url = restClient.url("api_cpb_mi");
		String responseString = restClient.post(url, para, headermap);//返回结果为"ok"
		Assert.assertEquals(responseString, "ok", "CpbMiSave : request is not ok.");
	}
	//乙方合同：保存开票计划
	public void cpb_io_Save (Map<String, String> headermap, String plan_money_, String cpbId) throws ClientProtocolException, IOException{
		RestClient restClient =  new RestClient();
		Map<String, String> para = new RequestPara().cpb_io_para(plan_money_, cpbId);
		String url = restClient.url("api_cpb_io");
		String responseString = restClient.post(url, para, headermap);//返回结果为"ok"
		Assert.assertEquals(responseString, "ok", "CpbIoSave : request is not ok.");
	}
	//乙方合同：发起申请
	public void cpbApply (Map<String, String> headermap, String cpbId) throws ClientProtocolException, IOException{
		RestClient restClient =  new RestClient();
		//上传附件
		Map<String, String> para_upload = new HashMap<String, String>();
		para_upload.put("cpbId", cpbId);
		Map<String,String> fileData = new HashMap<String,String>();
		fileData.put("file", this.upload_file);
		String url_upload = restClient.url("api_cpb_upload");
		String responseString_upload = restClient.post(url_upload, para_upload, headermap, fileData);
		Assert.assertEquals(restClient.getValue(responseString_upload, "code"), "1", "CpbUpload : code is not 1.");
		//发起申请
		Map<String, String> para = new HashMap<String, String>();
		para.put("cpbId", cpbId);
		String url = restClient.url("api_cpb_apply");
		String responseString = restClient.post(url, para, headermap);
		Assert.assertEquals(restClient.getValue(responseString, "msg"), "操作成功！", "CpbApply : request is not 操作成功！.");
	}
	//乙方合同：审批
	public void cpbSp (Map<String, String> headermap, String phone_, String cpbId, String sp, String comment) throws ClientProtocolException, IOException{
		this.login(headermap, phone_);
		RestClient restClient = new RestClient();
		String url_list = restClient.url("api_cpb_splist");
		String list = this.queryList(url_list, headermap);//查询待审列表
		String count = restClient.getValue(list, "count");
		if (count.equals("0")){
			Assert.assertEquals(count, "not 0", "CpbSp : 没有这条待审！");
		}
		String id_ = restClient.getValue(list, "data[0]/id_");
		Assert.assertEquals(id_, cpbId, "CpbSp : 没有这条待审！");
		String taskId = restClient.getValue(list, "data[0]/taskId");
		Map<String, String> para = new RequestPara().cpb_sp_para(cpbId, taskId, sp, comment);
		String url = restClient.url("api_cpb_sp");
		String responseString = restClient.post(url, para, headermap);//返回结果为"ok"
		Assert.assertEquals(restClient.getValue(responseString, "msg"), "操作成功！", "CpbSp : request is not 操作成功！.");
	}
	//乙方合同：获取cpbId
	public String getCpbId (Map<String, String> headermap) throws ClientProtocolException, IOException{
		String id = this.getId(headermap, "api_cpb_mylist");
		return id;
	}
	//费用报销：发起申请
	public void fybxApply (Map<String, String> headermap, Map<String, String> para) throws ClientProtocolException, IOException{
		RestClient restClient =  new RestClient();
		String url = restClient.url("api_fybx_apply");
		String responseString = restClient.post(url, para, headermap);//返回结果为ok
		Assert.assertEquals(responseString, "ok", "FybxApply : request is not ok.");
	}
	//费用报销：编辑
	public void fybxUpdate (Map<String, String> headermap, Map<String, String> para) throws ClientProtocolException, IOException{
		RestClient restClient =  new RestClient();
		String url = restClient.url("api_fybx_update");
		String responseString = restClient.post(url, para, headermap);//返回结果为ok
		Assert.assertEquals(responseString, "ok", "FybxUpdate : request is not ok.");
	}
	//费用报销：由报销类型自动获取特殊节点
	public String phone_fybx(String type){
		DeptAndPhone dept_phone = new DeptAndPhone();
		String phone_ = "";
		switch (type){
		case "住宿费": case "宿舍杂费": case "宿舍房租": case "市内交通费": case "油费": case "租车费": case "办公房租": case "新闻": 
		case "图书": case "快递": case "耗材、办公用品": case "通讯费": case "办公打印": case "办公维修": case "办公其他": 
			phone_ = dept_phone.phone_type_zhgl;break;//综合管理
		case "培训相关费用": case "会议费": case "评审费": case "项目打印": case "外业补助": case "项目结项杂费": case "项目奖":
			phone_ = dept_phone.phone_type_azwz;break;//安质物资
		case "标书费": case "中标服务费":
			phone_ = dept_phone.phone_type_sw;break;//商务部
		case "招待费": 
			phone_ = dept_phone.phone_type_yx;break;//营销
		case "实习生工资":
			phone_ = dept_phone.phone_type_hr;break;//人资
		case "加班餐费": case "其他":
			phone_ = null;break;
		default:
			System.out.println("ERROR：费用报销类型错误");
		}
		return phone_;
	}
	//费用报销：获取fybxId
	public String getFybxId (Map<String, String> headermap) throws ClientProtocolException, IOException{
		String id = this.getId(headermap, "api_fybx_mylist");
		return id;
	}
	//费用报销：审批
	public void fybxSp (Map<String, String> headermap, String phone_, String fybxId, String sp, String comment) throws ClientProtocolException, IOException{
		this.login(headermap, phone_);
		RestClient restClient = new RestClient();
		String url_list = restClient.url("api_fybx_splist");
		String list = this.queryList(url_list, headermap);//查询待审列表
		String count = restClient.getValue(list, "count");
		if (count.equals("0")){
			Assert.assertEquals(count, "not 0", "FybxSp : 没有这条待审！");
		}
		String id_ = restClient.getValue(list, "data[0]/id_");
		Assert.assertEquals(id_, fybxId, "FybxSp : 没有这条待审！");
		String taskId = restClient.getValue(list, "data[0]/taskId");
		Map<String, String> para = new RequestPara().fybx_sp_para(fybxId, taskId, sp, comment);
		String url = restClient.url("api_fybx_sp");
		String responseString = restClient.post(url, para, headermap);//返回结果为"ok"
		Assert.assertEquals(responseString, "ok", "FybxSp : request is not ok.");	
	}
	//差旅报销：发起申请
	public void clbxApply (Map<String, String> headermap, Map<String, String> para) throws ClientProtocolException, IOException{
		RestClient restClient =  new RestClient();
		String url = restClient.url("api_clbx_apply");
		String responseString = restClient.post(url, para, headermap);//返回结果为[{"code":1,"data":"ok","msg":"操作成功！"}]
		Assert.assertEquals(restClient.getValue(responseString, "msg"), "操作成功！", "ClbxApply : request is not 操作成功！.");
	}
	//差旅报销：编辑
	public void clbxUpdate (Map<String, String> headermap, Map<String, String> para) throws ClientProtocolException, IOException{
		RestClient restClient =  new RestClient();
		String url = restClient.url("api_clbx_update");
		String responseString = restClient.post(url, para, headermap);//返回结果为[{"code":1,"data":"ok","msg":"操作成功！"}]
		Assert.assertEquals(restClient.getValue(responseString, "msg"), "操作成功！", "ClbxUpdate : request is not 操作成功！.");
	}
	//差旅报销：获取clbxId
	public String getClbxId (Map<String, String> headermap) throws ClientProtocolException, IOException{
		String id = this.getId(headermap, "api_clbx_mylist");
		return id;
	}
	//差旅报销：审批
	public void clbxSp (Map<String, String> headermap, String phone_, String clbxId, String sp, String comment) throws ClientProtocolException, IOException{
		this.login(headermap, phone_);
		RestClient restClient = new RestClient();
		String url_list = restClient.url("api_clbx_splist");
		String list = this.queryList(url_list, headermap);//查询待审列表
		String count = restClient.getValue(list, "count");
		if (count.equals("0")){
			Assert.assertEquals(count, "not 0", "ClbxSp : 没有这条待审！");
		}
		String id_ = restClient.getValue(list, "data[0]/id_");
		Assert.assertEquals(id_, clbxId, "ClbxSp : 没有这条待审！");
		String taskId = restClient.getValue(list, "data[0]/taskId");
		Map<String, String> para = new RequestPara().clbx_sp_para(clbxId, taskId, sp, comment);
		String url = restClient.url("api_clbx_sp");
		String responseString = restClient.post(url, para, headermap);//返回结果为"ok"
		Assert.assertEquals(responseString, "ok", "ClbxSp : request is not ok.");	
	}
	//采购申请：发起
	public void purchaseApply (Map<String, String> headermap, Map<String, String> para) throws ClientProtocolException, IOException{
		RestClient restClient =  new RestClient();
		String url = restClient.url("api_purchase_apply");
		String responseString = restClient.post(url, para, headermap);//返回结果为[{"code":1,"data":"ok","msg":"操作成功！"}]
		Assert.assertEquals(restClient.getValue(responseString, "msg"), "操作成功！", "PurchaseApply : request is not 操作成功！.");
	}
	//采购申请：编辑
	public void purchaseUpdate (Map<String, String> headermap, Map<String, String> para) throws ClientProtocolException, IOException{
		RestClient restClient =  new RestClient();
		String url = restClient.url("api_purchase_update");
		String responseString = restClient.post(url, para, headermap);//返回结果为[{"code":1,"data":"ok","msg":"操作成功！"}]
		Assert.assertEquals(restClient.getValue(responseString, "msg"), "操作成功！", "PurchaseUpdate : request is not 操作成功！.");
	}
	//采购申请：获取purchaseId
	public String getPurchaseId (Map<String, String> headermap) throws ClientProtocolException, IOException{
		String id = this.getId(headermap, "api_purchase_mylist");
		return id;
	}
	//采购申请：获取proc_id_
	public String getPurchaseProcId (Map<String, String> headermap) throws ClientProtocolException, IOException{
		RestClient restClient = new RestClient();
		String url_list = restClient.url("api_purchase_mylist");
		String list = this.queryList(url_list, headermap);//查询我的申请列表
		String id = restClient.getValue(list, "data[0]/proc_id_");
		return id;
	}
	//采购申请：审批
	public void purchaseSp (Map<String, String> headermap, String phone_, String purchaseId, String sp, String comment) throws ClientProtocolException, IOException{
		this.login(headermap, phone_);
		RestClient restClient = new RestClient();
		String url_list = restClient.url("api_purchase_splist");
		String list = this.queryList(url_list, headermap);//查询待审列表
		String count = restClient.getValue(list, "count");
		if (count.equals("0")){
			Assert.assertEquals(count, "not 0", "PurchaseSp : 没有这条待审！");
		}
		String id_ = restClient.getValue(list, "data[0]/id_");
		Assert.assertEquals(id_, purchaseId, "PurchaseSp : 没有这条待审！");
		String taskId = restClient.getValue(list, "data[0]/taskId");
		Map<String, String> para = new RequestPara().purchase_sp_para(purchaseId, taskId, sp, comment);
		String url = restClient.url("api_purchase_sp");
		String responseString = restClient.post(url, para, headermap);//返回结果为"ok"
		Assert.assertEquals(restClient.getValue(responseString, "msg"), "操作成功！", "PurchaseSp : request is not 操作成功！.");	
	}
	//采购申请：获取purchaseCode
	public String getPurchaseCode (Map<String, String> headermap) throws ClientProtocolException, IOException{
		RestClient restClient = new RestClient();
		String url_list = restClient.url("api_purchase_mylist");
		String list = this.queryList(url_list, headermap);//查询我的申请列表
		String id = restClient.getValue(list, "data[0]/code_");
		return id;
	}
	//采购付款：发起
	public void paymentApply (Map<String, String> headermap, Map<String, String> para) throws ClientProtocolException, IOException{
		RestClient restClient =  new RestClient();
		String url = restClient.url("api_payment_apply");
		String responseString = restClient.post(url, para, headermap);//返回结果为[{"code":1,"data":"ok","msg":"操作成功！"}]
		Assert.assertEquals(restClient.getValue(responseString, "msg"), "操作成功！", "PaymentApply : request is not 操作成功！.");
	}
	//采购付款：编辑
	public void paymentUpdate (Map<String, String> headermap, Map<String, String> para) throws ClientProtocolException, IOException{
		RestClient restClient =  new RestClient();
		String url = restClient.url("api_payment_update");
		String responseString = restClient.post(url, para, headermap);//返回结果为[{"code":1,"data":"ok","msg":"操作成功！"}]
		Assert.assertEquals(restClient.getValue(responseString, "msg"), "操作成功！", "PaymentUpdate : request is not 操作成功！.");
	}
	//采购付款：获取paymentId
	public String getPaymentId (Map<String, String> headermap) throws ClientProtocolException, IOException{
		String id = this.getId(headermap, "api_payment_mylist");
		return id;
	}
	//采购付款：获取proc_id_
	public String getPaymentProcId (Map<String, String> headermap) throws ClientProtocolException, IOException{
		RestClient restClient = new RestClient();
		String url_list = restClient.url("api_payment_mylist");
		String list = this.queryList(url_list, headermap);//查询我的申请列表
		String id = restClient.getValue(list, "data[0]/proc_id_");
		return id;
	}
	//采购付款：审批
	public void paymentSp (Map<String, String> headermap, String phone_, String paymentId, String sp, String comment) throws ClientProtocolException, IOException{
		this.login(headermap, phone_);
		RestClient restClient = new RestClient();
		String url_list = restClient.url("api_payment_splist");
		String list = this.queryList(url_list, headermap);//查询待审列表
		String count = restClient.getValue(list, "count");
		if (count.equals("0")){
			Assert.assertEquals(count, "not 0", "PaymentSp : 没有这条待审！");
		}
		String id_ = restClient.getValue(list, "data[0]/id_");
		Assert.assertEquals(id_, paymentId, "PaymentSp : 没有这条待审！");
		String taskId = restClient.getValue(list, "data[0]/taskId");
		Map<String, String> para = new RequestPara().payment_sp_para(paymentId, taskId, sp, comment);
		String url = restClient.url("api_payment_sp");
		String responseString = restClient.post(url, para, headermap);//返回结果为"ok"
		Assert.assertEquals(restClient.getValue(responseString, "msg"), "操作成功！", "PaymentSp : request is not 操作成功！.");
	}
	//借款：发起
	public void loanApply (Map<String, String> headermap, Map<String, String> para) throws ClientProtocolException, IOException{
		RestClient restClient =  new RestClient();
		String url = restClient.url("api_loan_apply");
		String responseString = restClient.post(url, para, headermap);//返回结果为{"code":1,"data":"ok","msg":"操作成功！"}
		Assert.assertEquals(restClient.getValue(responseString, "msg"), "操作成功！", "LoanApply : request is not 操作成功！.");
	}
	//借款：编辑
	public void loanUpdate (Map<String, String> headermap, Map<String, String> para) throws ClientProtocolException, IOException{
		RestClient restClient =  new RestClient();
		String url = restClient.url("api_loan_update");
		String responseString = restClient.post(url, para, headermap);//返回结果为{"code":1,"data":"ok","msg":"操作成功！"}
		Assert.assertEquals(restClient.getValue(responseString, "msg"), "操作成功！", "LoanUpdate : request is not 操作成功！.");
	}
	//借款：发起（标书费）
	public void loan_bsfApply (Map<String, String> headermap, Map<String, String> para) throws ClientProtocolException, IOException{
		RestClient restClient =  new RestClient();
		Map<String, String> para_upload = new HashMap<String, String>();
		para_upload.put("loanId", "");
		Map<String,String> fileData = new HashMap<String,String>();
		fileData.put("file", this.upload_file);
		String url_upload = restClient.url("api_loan_upload");
		String responseString_up = restClient.post(url_upload, para_upload, headermap, fileData);
		String saveFilePath = restClient.getValue(responseString_up, "data/saveFilePath");
		Assert.assertEquals(restClient.getValue(responseString_up, "code"), "1", "LoanUpload : code is not 1.");
		para.put("fileTag", saveFilePath);
		String url = restClient.url("api_loan_apply");
		String responseString = restClient.post(url, para, headermap);//返回结果为{"code":1,"data":"ok","msg":"操作成功！"}
		Assert.assertEquals(restClient.getValue(responseString, "msg"), "操作成功！", "LoanApply : request is not 操作成功！.");
	}
	//借款：编辑（标书费）
	public void loan_bsfUpdate (Map<String, String> headermap, Map<String, String> para) throws ClientProtocolException, IOException{
		RestClient restClient =  new RestClient();
		Map<String, String> para_upload = new HashMap<String, String>();
		para_upload.put("loanId", "");
		Map<String,String> fileData = new HashMap<String,String>();
		fileData.put("file", this.upload_file);
		String url_upload = restClient.url("api_loan_upload");
		String responseString_up = restClient.post(url_upload, para_upload, headermap, fileData);
		String saveFilePath = restClient.getValue(responseString_up, "data/saveFilePath");
		Assert.assertEquals(restClient.getValue(responseString_up, "code"), "1", "LoanUpload : code is not 1.");
		para.put("fileTag", saveFilePath);
		String url = restClient.url("api_loan_update");
		String responseString = restClient.post(url, para, headermap);//返回结果为{"code":1,"data":"ok","msg":"操作成功！"}
		Assert.assertEquals(restClient.getValue(responseString, "msg"), "操作成功！", "LoanApply : request is not 操作成功！.");
	}
	//借款：由借款类型自动获取特殊节点
	public String phone_loan(String type){
		DeptAndPhone dept_phone = new DeptAndPhone();
		String phone_loan = "";
		switch (type){
		case "差旅费": case "办公费":
			phone_loan = dept_phone.phone_type_zhgl;break;
		case "采购费": case "评审费": case "项目奖":
			phone_loan = dept_phone.phone_type_azwz;break;
		case "标书费": case "中标服务费": case "投标保证金":
			phone_loan = dept_phone.phone_type_sw;break;
		case "招待费": 
			phone_loan = dept_phone.phone_type_yx;break;
		case "实习生工资":
			phone_loan = dept_phone.phone_type_hr;break;
		default:
			System.out.println("ERROR：借款类型错误");
		}
		return phone_loan;
	}
	//借款：获取loanId
	public String getLoanId (Map<String, String> headermap) throws ClientProtocolException, IOException{
		String id = this.getId(headermap, "api_loan_mylist");
		return id;
	}
	//借款：审批
	public void loanSp (Map<String, String> headermap, String phone_, String loanId, String sp, String comment) throws ClientProtocolException, IOException{
		this.login(headermap, phone_);
		RestClient restClient = new RestClient();
		String url_list = restClient.url("api_loan_splist");
		String list = this.queryList(url_list, headermap);//查询待审列表
		String count = restClient.getValue(list, "count");
		if (count.equals("0")){
			Assert.assertEquals(count, "not 0", "LoanSp : 没有这条待审！");
		}
		String id_ = restClient.getValue(list, "data[0]/id_");
		Assert.assertEquals(id_, loanId, "LoanSp : 没有这条待审！");
		String taskId = restClient.getValue(list, "data[0]/taskId");
		Map<String, String> para = new RequestPara().loan_sp_para(loanId, taskId, sp, comment);
		String url = restClient.url("api_loan_sp");
		String responseString = restClient.post(url, para, headermap);//返回结果为"ok"
		Assert.assertEquals(responseString, "ok", "LoanSp : request is not ok.");
	}
	//开票：发起
	public void FinIoApply (Map<String, String> headermap, Map<String, String> para) throws ClientProtocolException, IOException{
		RestClient restClient =  new RestClient();
		String url = restClient.url("api_finIo_apply");
		String responseString = restClient.post(url, para, headermap);//返回结果为ok
		Assert.assertEquals(responseString, "ok", "FinIoApply : request is not ok！.");
	}
	//开票：获取finIoId
	public String getFinIoId (Map<String, String> headermap) throws ClientProtocolException, IOException{
		String id = this.getId(headermap, "api_finIo_mylist");
		return id;
	}
	//开票：审批
	public void finIoSp (Map<String, String> headermap, String phone_, String finIoId, String sp, String comment, String invoiceNum) throws ClientProtocolException, IOException{
		this.login(headermap, phone_);
		RestClient restClient = new RestClient();
		String url_list = restClient.url("api_finIo_splist");
		String list = this.queryList(url_list, headermap);//查询待审列表
		String count = restClient.getValue(list, "count");
		if (count.equals("0")){
			Assert.assertEquals(count, "not 0", "FinIoSp : 没有这条待审！");
		}
		String id_ = restClient.getValue(list, "data[0]/id_");
		Assert.assertEquals(id_, finIoId, "FinIoSp : 没有这条待审！");
		String taskId = restClient.getValue(list, "data[0]/taskId");
		Map<String, String> para = new RequestPara().finIo_sp_para(finIoId, taskId, sp, comment, invoiceNum);
		String url = restClient.url("api_finIo_sp");
		String responseString = restClient.post(url, para, headermap);//返回结果为"ok"
		Assert.assertEquals(responseString, "ok", "FinIoSp : request is not ok.");
	}
	//回款认领：发布
	public void mcUpsert (Map<String, String> headermap, Map<String, String> para) throws ClientProtocolException, IOException{
		RestClient restClient =  new RestClient();
		String url = restClient.url("api_mc_upsert");
		String responseString = restClient.post(url, para, headermap);//返回结果为{"code":1,"data":"ok","msg":""}
		Assert.assertEquals(restClient.getValue(responseString, "data"), "ok", "McUpsert : request is not ok！.");
	}
	//获取mcId
	public String getMcId (Map<String, String> headermap) throws ClientProtocolException, IOException{
		String id = this.getId(headermap, "api_mc_mylist");
		return id;
	}
	//获取update_time_
	public String getUpdateTimeId (Map<String, String> headermap) throws ClientProtocolException, IOException{
		RestClient restClient = new RestClient();
		String url_list = restClient.url("api_mc_mylist");
		String list = this.queryList(url_list, headermap);//查询我的申请列表
		String id = restClient.getValue(list, "data[0]/update_time_");
		return id;
	}
	//回款认领：认领
	public void mcClaim (Map<String, String> headermap, String phone_, String mcId, String type_, String ywId, String update_time_, String deptId) throws ClientProtocolException, IOException{
		this.login(headermap, phone_);
		RestClient restClient = new RestClient();
		String url_list = restClient.url("api_mc_claim_list");
		String list = this.queryList(url_list, headermap);//查询认领列表
		String count = restClient.getValue(list, "count");
		if (count.equals("0")){
			Assert.assertEquals(count, "not 0", "McClaim : 没有这条认领！");
		}
		String id_ = restClient.getValue(list, "data[0]/id_");
		Assert.assertEquals(id_, mcId, "McClaim : 没有这条认领！");
		Map<String, String> para = new RequestPara().mc_claim_para(mcId, type_, ywId, update_time_, deptId);
		String url = restClient.url("api_mc_claim");
		String responseString = restClient.post(url, para, headermap);//返回结果为{"code":1,"data":"ok","msg":"操作成功！"}
		Assert.assertEquals(restClient.getValue(responseString, "msg"), "操作成功！", "LoanApply : request is not 操作成功！.");
	}
	//回款认领：确认
	public void mcApprove (Map<String, String> headermap, String phone_, String mcId, String approved, String comment) throws ClientProtocolException, IOException{
		this.login(headermap, phone_);
		RestClient restClient = new RestClient();
		String url_list = restClient.url("api_mc_approve_list");
		String list = this.queryList(url_list, headermap);//查询待审列表
		String count = restClient.getValue(list, "count");
		if (count.equals("0")){
			Assert.assertEquals(count, "not 0", "McApprove : 没有这条认领！");
		}
		String id_ = restClient.getValue(list, "data[0]/id_");
		Assert.assertEquals(id_, mcId, "McApprove : 没有这条认领！");
		Map<String, String> para = new RequestPara().mc_approve_para(mcId, approved, comment);
		String url = restClient.url("api_mc_approve");
		String responseString = restClient.post(url, para, headermap);//返回结果为{"code":1,"data":"ok","msg":"操作成功！"}
		Assert.assertEquals(restClient.getValue(responseString, "msg"), "操作成功！", "McApply : request is not 操作成功！.");
	}
	//调账：获取code_
	public String getMoneyAdjustCode (Map<String, String> headermap) throws ClientProtocolException, IOException{
		RestClient restClient = new RestClient();
		String url_list = restClient.url("api_money_adjust_mylist");
		String list = this.queryList(url_list, headermap);//查询我的申请列表
		String id = restClient.getValue(list, "data[0]/code_");
		return id;
	}
	//调账：获取time_
	public String getMoneyAdjustTime (Map<String, String> headermap) throws ClientProtocolException, IOException{
		RestClient restClient = new RestClient();
		String url_list = restClient.url("api_money_adjust_mylist");
		String list = this.queryList(url_list, headermap);//查询我的申请列表
		String id = restClient.getValue(list, "data[0]/time_");
		return id;
	}
	//调账：审批
	public void moneyAdjustApprove (Map<String, String> headermap, String phone_, String code, String time_, String approved, String comment) throws ClientProtocolException, IOException{
		this.login(headermap, phone_);
		RestClient restClient = new RestClient();
		Map<String, String> para = new HashMap<String, String>();
		para.put("code", code);
		para.put("time_", time_);
		para.put("approved", approved);
		para.put("comment", comment);
		String url = restClient.url("api_money_adjust_approve");
		String responseString = restClient.post(url, para, headermap);//返回结果为"ok"
//		Assert.assertEquals(responseString, "ok", "moneyAdjust : request is not ok.");
	}
	//资金流水：获取金额、类型、模块
	public Map<String, String> getProjFlow (Map<String, String> headermap, String projId) throws ClientProtocolException, IOException{
		RestClient restClient = new RestClient();
		String url_list = restClient.url("api_proj_flow");
		Map<String,String> para = new HashMap<>();
		para.put("projId", projId);
		String list = restClient.post(url_list, para, headermap);
		String kind_ = restClient.getValue(list, "data/list[0]/kind_");//费用类型
		String money_ = restClient.getValue(list, "data/list[0]/money_");//金额
		String source_yw_= restClient.getValue(list, "data/list[0]/source_yw_");//业务模块
		Map<String, String> get_value = new HashMap<>();
		get_value.put("kind_", kind_);
		get_value.put("money_", money_);
		get_value.put("source_yw_", source_yw_);
		return get_value;
	}
	//资金流水：
	public void checkFlow (Map<String, String> headermap, String projId, String money, String kind, String mode) throws ClientProtocolException, IOException{
		//从资金流水里获取金额、模块、分类信息
		Map<String, String> flow_value = this.getProjFlow(headermap, projId);
		String kind_flow = flow_value.get("kind_");
		String money_flow = flow_value.get("money_");
		String source_yw_ = flow_value.get("source_yw_");
		//成本时，金额为负
		switch (mode){
		case "费用报销": case "差旅报销": case "采购付款": case "借款":
			money = "-" + money;
		}
		BigDecimal a = new BigDecimal(money_flow);
		BigDecimal b = new BigDecimal(money);
		int r = a.compareTo(b);
		System.out.println("校验：\n" + "发生金额：" + money + "   VS   流水金额：" + money_flow);
		Assert.assertEquals(r, 0, "ProjFlow : 金额有误！");//校验金额
		System.out.println("发生模块：" + mode + "   VS   流水模块：" + source_yw_);
		Assert.assertEquals(source_yw_, mode, "ProjFlow : 功能模块有误！");//校验功能模块
		System.out.println("发生分类：" + kind + "   VS   流水分类：" + kind_flow);
		//校验成本、收入分类
		switch (kind){
		//差旅
		case "住宿费": case "宿舍杂费": case "宿舍房租": case "市内交通费": case "差旅费": case "差旅报销":
			Assert.assertEquals(kind_flow, "差旅", "ProjFlow : 成本分类有误！");break;
		//采购
		case "培训相关费用": case "会议费": case "评审费": case "项目打印": case "采购费": case "采购付款": 
			Assert.assertEquals(kind_flow, "采购", "ProjFlow : 成本分类有误！");break;
		//办公
		case "油费": case "租车费": case "办公房租": case "新闻": case "图书": case "快递": case "耗材、办公用品": case "加班餐费":
		case "通讯费": case "办公打印": case "办公维修": case "办公其他": case "办公费":
			Assert.assertEquals(kind_flow, "办公", "ProjFlow : 成本分类有误！");break;
		//营销
		case "招待费": case "标书费": case "中标服务费": case "投标保证金":
			Assert.assertEquals(kind_flow, "营销", "ProjFlow : 成本分类有误！");break;
		//人员成本
		case "实习生工资": case "外业补助":
			Assert.assertEquals(kind_flow, "人员成本", "ProjFlow : 成本分类有误！");break;
		//奖金
		case "项目奖":
			Assert.assertEquals(kind_flow, "奖金", "ProjFlow : 成本分类有误！");break;
		//其他
		case "其他":
			Assert.assertEquals(kind_flow, "其他", "ProjFlow : 成本分类有误！");break;
		case "proj":
			Assert.assertEquals(kind_flow, "回款", "ProjFlow : 收入 分类有误！");break;
		case "bzj":
			Assert.assertEquals(kind_flow, "保证金", "ProjFlow : 收入 分类有误！");break;
		
		
		
		}
	}
	//项目经理看板：健康卡数据
	public Map<String,String> health (Map<String, String> headermap, String projId) throws ClientProtocolException, IOException{
		RestClient restClient = new RestClient();
		Map<String,String> para = new HashMap<String,String>();
		para.put("projId", projId);
		String url_set = restClient.url("api_pmboard_health_set");
		restClient.post(url_set, para, headermap);
		String url = restClient.url("api_pmboard_health");
		String response = restClient.get(url, headermap);
		String data = restClient.getValue(response, "data[0]");
		Map<String,String> health_proj = new HashMap<String,String>();
		health_proj.put("contr_money_", restClient.getValue(data, "contr_money_"));//合同额
		health_proj.put("money_in_", restClient.getValue(data, "money_in_"));//回款
		health_proj.put("cost_", restClient.getValue(data, "cost_"));//成本
		health_proj.put("profit_", restClient.getValue(data, "profit_"));//利润
		health_proj.put("money_in_fin_", restClient.getValue(data, "money_in_fin_"));///财务应收
		health_proj.put("money_in_pro_", restClient.getValue(data, "money_in_pro_"));//生产应收
		health_proj.put("m_io_ed_", restClient.getValue(data, "m_io_ed_"));//开票额（审批通过）
		return health_proj;
	}
	//项目经理看板：健康卡数据校验
	public void checkHealth(Map<String,String> health, Map<String,String> flow){
		//从健康卡获取的数据
		String health_contr_money_ = health.get("contr_money_");
		String health_money_in_ = health.get("money_in_");
		String health_cost_ = health.get("cost_");
		String health_profit_ = health.get("profit_");
		String health_money_in_fin_ = health.get("money_in_fin_");
		String health_money_in_pro_ = health.get("money_in_pro_");
		String health_m_io_ed_ = health.get("m_io_ed_");
		//实际发生的数据
		String flow_contr_money_ = flow.get("contr_money_");
		String flow_money_in_ = flow.get("money_in_");
		String flow_cost_ = flow.get("cost_");
		String flow_profit_ = flow.get("profit_");
		String flow_money_in_fin_ = flow.get("money_in_fin_");
		String flow_money_in_pro_ = flow.get("money_in_pro_");
		String flow_m_io_ed_ = flow.get("m_io_ed_");
		//校验
		System.out.println("项目健康卡校验中：合同额");
		this.moneyCompare(flow_contr_money_, health_contr_money_);
		System.out.println("项目健康卡校验中：回款");
		this.moneyCompare(flow_money_in_, health_money_in_);
		System.out.println("项目健康卡校验中：成本");
		this.moneyCompare(flow_cost_, health_cost_);
		System.out.println("项目健康卡校验中：利润");
		this.moneyCompare(flow_profit_, health_profit_);
		System.out.println("项目健康卡校验中：财务应收");
		this.moneyCompare(flow_money_in_fin_, health_money_in_fin_);
		System.out.println("项目健康卡校验中：生产应收");
		this.moneyCompare(flow_money_in_pro_, health_money_in_pro_);
		System.out.println("项目健康卡校验中：开票金额");
		this.moneyCompare(flow_m_io_ed_, health_m_io_ed_);
		
	}
	//项目看板：成本管控数据
	public Map<String,String> budget (Map<String, String> headermap, String projId, String kind) throws ClientProtocolException, IOException{
		RestClient restClient = new RestClient();
		Map<String,String> para = new HashMap<String,String>();
		para.put("projId", projId);
		String url = restClient.url("api_projboard_budget");
		String response = restClient.post(url, para, headermap);
		String budget = "";
		int i = 0;
		String kind_budget = "";
		do{
			budget = restClient.getValue(response, "data["+i+"]");
			kind_budget = restClient.getValue(budget, "kind");
			i++;
		}while(!kind_budget.equals(kind));
		Map<String,String> budget_data = new HashMap<String,String>();
		budget_data.put("actual", restClient.getValue(budget, "actual"));
		budget_data.put("buget", restClient.getValue(budget, "buget"));
		budget_data.put("kind", restClient.getValue(budget, "kind"));
		return budget_data;
	}
	//项目看板：成本管控数据校验
	public void checkBudget(Map<String,String> budget, Map<String,String> flow){
		//从成本管控获取的数据
//		String budget_kind = budget.get("kind");//当初不知道为啥写了这个
		String budget_actual = budget.get("actual");
		String budget_buget = budget.get("buget");
		//实际发生的数据
//		String flow_kind = flow.get("kind");//当初不知道为啥写了这个
		String flow_actual = flow.get("actual");
		String flow_buget = flow.get("buget");
		
		//校验
		this.moneyCompare(flow_actual, budget_actual);
		this.moneyCompare(flow_buget, budget_buget);
		
	}
	//金额比较
	public void moneyCompare(String money1, String money2){
		BigDecimal a = new BigDecimal(money1);
		BigDecimal b = new BigDecimal(money2);
		int r = a.compareTo(b);
		Assert.assertEquals(r, 0, "金额有误！预期：" + money1 + "，实际：" + money2);//校验金额
	}
}
