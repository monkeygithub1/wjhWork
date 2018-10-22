package wjh.pmis.data;

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
	public String projSave (Map<String, String> headermap, String projId, String proj_name_, String proj_mgr_, String proj_mgr_id_, String dept_, String proj_mgr_type_) throws ClientProtocolException, IOException{
		RestClient restClient =  new RestClient();
		Map<String, String> para = new RequestPara().proj_save_para(projId, proj_name_, proj_mgr_, proj_mgr_id_, dept_, proj_mgr_type_);
		String url = restClient.url("api_proj_save");
		String responseString = restClient.post(url, para, headermap);//返回结果为projId
		return responseString;
	}
	//项目立项：预算保存
	public void projBudge (Map<String, String> headermap, String proj_id_, String ys_kphte) throws ClientProtocolException, IOException{
		RestClient restClient =  new RestClient();
		Map<String, String> para = new RequestPara().proj_budge_para(proj_id_, ys_kphte);
		String url = restClient.url("api_proj_budge");
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
		Assert.assertEquals(responseString, "ok", "ProjApply : request is not ok.");
	}
	//项目立项审批
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
		Assert.assertEquals(responseString, "ok", "ProjSp : request is not ok.");	
	}
	//甲方合同：保存（返回cpaId）发起和编辑
	public String cpaSave (Map<String, String> headermap, String id_, String name_, String money_, String dept_, String nature_, String type_, String proj_, String proj_id_, String purchase_code_, String purchase_id_) throws ClientProtocolException, IOException{
		RestClient restClient =  new RestClient();
		Map<String, String> para = new RequestPara().cpa_sava_para(id_, name_, money_, dept_, nature_, type_, proj_, proj_id_, purchase_code_, purchase_id_);
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
		Map<String, String> para = new HashMap<String, String>();
		para.put("cpaId", cpaId);
		String url = restClient.url("api_cpa_apply");
		String responseString = restClient.post(url, para, headermap);
		Assert.assertEquals(responseString, "ok", "CpaApply : request is not ok.");
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
		Assert.assertEquals(responseString, "ok", "CpaSp : request is not ok.");	
	}
	//乙方合同：保存（返回cpbId）发起和编辑
	public String cpbSave (Map<String, String> headermap, String id_, String name_, String money_, String dept_, String nature_, String type_, String proj_, String proj_id_) throws ClientProtocolException, IOException{
		RestClient restClient =  new RestClient();
		Map<String, String> para = new RequestPara().cpb_sava_para(id_, name_, money_, dept_, nature_, type_, proj_, proj_id_);
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
		Map<String, String> para = new HashMap<String, String>();
		para.put("cpbId", cpbId);
		String url = restClient.url("api_cpb_apply");
		String responseString = restClient.post(url, para, headermap);
		Assert.assertEquals(responseString, "ok", "CpbApply : request is not ok.");
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
		Assert.assertEquals(responseString, "ok", "CpbSp : request is not ok.");	
	}
	//费用报销：发起申请
	public void fybxApply (Map<String, String> headermap, String projId, String projCode, String projName, String money, String bxType, String bxrIds, String bxrNames, String pmo, String typeFlag) throws ClientProtocolException, IOException{
		RestClient restClient =  new RestClient();
		Map<String, String> para = new RequestPara().fybx_para(projId, projCode, projName, money, bxType, bxrIds, bxrNames, pmo, typeFlag);
		String url = restClient.url("api_fybx_apply");
		String responseString = restClient.post(url, para, headermap);//返回结果为ok
		Assert.assertEquals(responseString, "ok", "FybxApply : request is not ok.");
	}
	//费用报销：编辑
	public void fybxUpdate (Map<String, String> headermap, String projId, String projCode, String projName, String money, String bxType, String bxrIds, String bxrNames, String pmo, String typeFlag, String fybxId) throws ClientProtocolException, IOException{
		RestClient restClient =  new RestClient();
		Map<String, String> para = new RequestPara().fybx_update_para(projId, projCode, projName, money, bxType, bxrIds, bxrNames, pmo, typeFlag, fybxId);
		String url = restClient.url("api_fybx_update");
		String responseString = restClient.post(url, para, headermap);//返回结果为ok
		Assert.assertEquals(responseString, "ok", "FybxApply : request is not ok.");
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
	public void clbxApply (Map<String, String> headermap, String projId, String projCode, String projName, double money, String bxrIds, String bxrNames, String pmo, String typeFlag) throws ClientProtocolException, IOException{
		RestClient restClient =  new RestClient();
		Map<String, String> para = new RequestPara().clbx_para(headermap, projId, projCode, projName, money, bxrIds, bxrNames, pmo, typeFlag);
		String url = restClient.url("api_clbx_apply");
		String responseString = restClient.post(url, para, headermap);//返回结果为[{"code":1,"data":"ok","msg":"操作成功！"}]
		Assert.assertEquals(restClient.getValue(responseString, "msg"), "操作成功！", "ClbxApply : request is not 操作成功！.");
	}
	//差旅报销：编辑
	public void clbxUpdate (Map<String, String> headermap, String projId, String projCode, String projName, double money, String bxrIds, String bxrNames, String pmo, String typeFlag, String clbxId) throws ClientProtocolException, IOException{
		RestClient restClient =  new RestClient();
		Map<String, String> para = new RequestPara().clbx_update_para(headermap, projId, projCode, projName, money, bxrIds, bxrNames, pmo, typeFlag, clbxId);
		String url = restClient.url("api_clbx_update");
		String responseString = restClient.post(url, para, headermap);//返回结果为[{"code":1,"data":"ok","msg":"操作成功！"}]
		Assert.assertEquals(restClient.getValue(responseString, "msg"), "操作成功！", "ClbxApply : request is not 操作成功！.");
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
	public void purchaseApply (Map<String, String> headermap, String proj_id_, String money_, String purchase_type, String purchaseId, String proc_id_) throws ClientProtocolException, IOException{
		RestClient restClient =  new RestClient();
		Map<String, String> para = null;
		switch (purchase_type){
		case "0":
			para = new RequestPara().purchase_zywz_para(proj_id_, money_, purchaseId, proc_id_);
			break;
		case "1":
			para = new RequestPara().purchase_xmwz_para(proj_id_, money_, purchaseId, proc_id_);
			break;
		case "2":
			para = new RequestPara().purchase_rlwb_para(proj_id_, money_, purchaseId, proc_id_);
			break;
		case "3":
			para = new RequestPara().purchase_ywqt_para(proj_id_, money_, purchaseId, proc_id_);
			break;
		case "4":
			para = new RequestPara().purchase_xmwzkj_para(proj_id_, money_, purchaseId, proc_id_);
			break;
		case "5":
			para = new RequestPara().purchase_zhfy_para(proj_id_, money_, purchaseId, proc_id_);
			break;
		case "6":
			para = new RequestPara().purchase_rlzy_para(proj_id_, money_, purchaseId, proc_id_);
			break;
		case "7":
			para = new RequestPara().purchase_zf_para(proj_id_, money_, purchaseId, proc_id_);
			break;
		default:
			System.out.println("PurchaseApply: 木有这个采购类型");
			break;
		}
		String url = restClient.url("api_purchase_apply");
		String responseString = restClient.post(url, para, headermap);//返回结果为[{"code":1,"data":"ok","msg":"操作成功！"}]
		Assert.assertEquals(restClient.getValue(responseString, "msg"), "操作成功！", "PurchaseApply : request is not 操作成功！.");
	}
	//采购申请：编辑
	public void purchaseUpdate (Map<String, String> headermap, String proj_id_, String money_, String purchase_type, String purchaseId, String proc_id_) throws ClientProtocolException, IOException{
		RestClient restClient =  new RestClient();
		Map<String, String> para = null;
		switch (purchase_type){
		case "0":
			para = new RequestPara().purchase_zywz_para(proj_id_, money_, purchaseId, proc_id_);
			break;
		case "1":
			para = new RequestPara().purchase_xmwz_para(proj_id_, money_, purchaseId, proc_id_);
			break;
		case "2":
			para = new RequestPara().purchase_rlwb_para(proj_id_, money_, purchaseId, proc_id_);
			break;
		case "3":
			para = new RequestPara().purchase_ywqt_para(proj_id_, money_, purchaseId, proc_id_);
			break;
		case "4":
			para = new RequestPara().purchase_xmwzkj_para(proj_id_, money_, purchaseId, proc_id_);
			break;
		case "5":
			para = new RequestPara().purchase_zhfy_para(proj_id_, money_, purchaseId, proc_id_);
			break;
		case "6":
			para = new RequestPara().purchase_rlzy_para(proj_id_, money_, purchaseId, proc_id_);
			break;
		case "7":
			para = new RequestPara().purchase_zf_para(proj_id_, money_, purchaseId, proc_id_);
			break;
		default:
			System.out.println("PurchaseApply: 木有这个采购类型");
			break;
		}
		String url = restClient.url("api_purchase_update");
		String responseString = restClient.post(url, para, headermap);//返回结果为[{"code":1,"data":"ok","msg":"操作成功！"}]
		Assert.assertEquals(restClient.getValue(responseString, "msg"), "操作成功！", "PurchaseApply : request is not 操作成功！.");
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
		Assert.assertEquals(responseString, "ok", "PurchaseSp : request is not ok.");	
	}
	//采购付款：发起
	public void paymentApply (Map<String, String> headermap, String proj_id_, String money_, String purchase_type, String is_ds_pur_, String cpaId,String cpbId, String purchaseId, String paymentId, String proc_id_) throws ClientProtocolException, IOException{
		RestClient restClient =  new RestClient();
		Map<String, String> para = null;
		switch (purchase_type){
		case "0":
			para = new RequestPara().payment_zywz_para(proj_id_, money_, is_ds_pur_, cpaId, cpbId, purchaseId, paymentId, proc_id_);
			break;
		case "1":
			para = new RequestPara().payment_xmwz_para(proj_id_, money_, is_ds_pur_, cpaId, cpbId, purchaseId, paymentId, proc_id_);
			break;
		case "2":
			para = new RequestPara().payment_rlwb_para(proj_id_, money_, is_ds_pur_, cpaId, cpbId, purchaseId, paymentId, proc_id_);
			break;
		case "3":
			para = new RequestPara().payment_ywqt_para(proj_id_, money_, is_ds_pur_, cpaId, cpbId, purchaseId, paymentId, proc_id_);
			break;
		case "4":
			para = new RequestPara().payment_xmwzkj_para(proj_id_, money_, is_ds_pur_, cpaId, cpbId, purchaseId, paymentId, proc_id_);
			break;
		case "5":
			para = new RequestPara().payment_zhfy_para(proj_id_, money_, is_ds_pur_, cpaId, cpbId, purchaseId, paymentId, proc_id_);
			break;
		case "6":
			para = new RequestPara().payment_rlzy_para(proj_id_, money_, is_ds_pur_, cpaId, cpbId, purchaseId, paymentId, proc_id_);
			break;
		case "7":
			para = new RequestPara().payment_zf_para(proj_id_, money_, is_ds_pur_, cpaId, cpbId, purchaseId, paymentId, proc_id_);
			break;
		default:
			System.out.println("PaymentApply: 木有这个采购类型");
			break;
		}
		String url = restClient.url("api_payment_apply");
		String responseString = restClient.post(url, para, headermap);//返回结果为[{"code":1,"data":"ok","msg":"操作成功！"}]
		Assert.assertEquals(restClient.getValue(responseString, "msg"), "操作成功！", "PaymentApply : request is not 操作成功！.");
	}
	//采购付款：编辑
	public void paymentUpdate (Map<String, String> headermap, String proj_id_, String money_, String purchase_type, String is_ds_pur_, String cpaId,String cpbId, String purchaseId, String paymentId, String proc_id_) throws ClientProtocolException, IOException{
		RestClient restClient =  new RestClient();
		Map<String, String> para = null;
		switch (purchase_type){
		case "0":
			para = new RequestPara().payment_zywz_para(proj_id_, money_, is_ds_pur_, cpaId, cpbId, purchaseId, paymentId, proc_id_);
			break;
			case "1":
				para = new RequestPara().payment_xmwz_para(proj_id_, money_, is_ds_pur_, cpaId, cpbId, purchaseId, paymentId, proc_id_);
				break;
			case "2":
				para = new RequestPara().payment_rlwb_para(proj_id_, money_, is_ds_pur_, cpaId, cpbId, purchaseId, paymentId, proc_id_);
				break;
			case "3":
				para = new RequestPara().payment_ywqt_para(proj_id_, money_, is_ds_pur_, cpaId, cpbId, purchaseId, paymentId, proc_id_);
				break;
			case "4":
				para = new RequestPara().payment_xmwzkj_para(proj_id_, money_, is_ds_pur_, cpaId, cpbId, purchaseId, paymentId, proc_id_);
				break;
			case "5":
				para = new RequestPara().payment_zhfy_para(proj_id_, money_, is_ds_pur_, cpaId, cpbId, purchaseId, paymentId, proc_id_);
				break;
			case "6":
				para = new RequestPara().payment_rlzy_para(proj_id_, money_, is_ds_pur_, cpaId, cpbId, purchaseId, paymentId, proc_id_);
				break;
			case "7":
				para = new RequestPara().payment_zf_para(proj_id_, money_, is_ds_pur_, cpaId, cpbId, purchaseId, paymentId, proc_id_);
				break;
		default:
			System.out.println("PaymentApply: 木有这个采购类型");
			break;
		}
		String url = restClient.url("api_payment_update");
		String responseString = restClient.post(url, para, headermap);//返回结果为[{"code":1,"data":"ok","msg":"操作成功！"}]
		Assert.assertEquals(restClient.getValue(responseString, "msg"), "操作成功！", "PaymentApply : request is not 操作成功！.");
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
		Assert.assertEquals(responseString, "ok", "PaymentSp : request is not ok.");
	}
}
