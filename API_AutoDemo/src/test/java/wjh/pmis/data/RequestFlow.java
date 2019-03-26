package wjh.pmis.data;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.testng.Assert;

import wjh.pmis.restclient.RestClient;

public class RequestFlow {
	DeptAndPhone dept_phone = new DeptAndPhone();
	//发起人、项目经理、部门配置（立项时，发起人和项目经理必须是同一个人）
	//发起人
	String phone_create = "15822974029";//发起人 -王雨（文化传媒事业部-影视动画部）
//	String phone_create = "13752179542";//发起人 
	//项目经理
	String phone_pm = dept_phone.phone_PM;//项目发起人 &项目经理
	String proj_mgr_ = dept_phone.proj_mgr_;
	String proj_mgr_id_ = dept_phone.proj_mgr_id_;
	//部门信息
//	String dept_name = "职能部门";
	String dept_name = "业务部门";
//	String dept_name = "项目管理中心";
//	String dept_name = "公司本部";
//	String dept_name = "UIUE设计部";
//	String dept_name = "空间数据加工厂";
	Map<String , String> dept_para = dept_phone.getDeptInfo(dept_name);
	String phone_dept = dept_para.get("phone_dept");
	String phone_fg = dept_para.get("phone_fg");//安质分管
	String phone_sybzjl = dept_para.get("phone_sybzjl");//事业部总经理
	String phone_sybdsz = dept_para.get("phone_sybdsz");//事业部董事长
	String phone_sybcw = dept_para.get("phone_sybcw");//事业部财务
	String dept_proj = dept_para.get("dept_proj");
	String dept_cpa = dept_para.get("dept_cpa");
	String dept_cpb = dept_para.get("dept_cpb");
	String deptId_mc = dept_para.get("deptId_mc");
	//审批流程人员
	String phone_azmgr = "13163002079";//安质部负责人
	String phone_sc = "18622708857";//生产副总
	String phone_boss = "13502103187";
	
	String phone_fw = "13820781432";
	String phone_wzzz = "13820781432";
	String phone_cpaaz = "18502285517";
	String phone_cpawz = "18502285517";
	String phone_cpacw = "13820641813";
	String phone_clbxType = "15122681282";//差旅报销出行节点
	String phone_zhglmgr = "15122681282";
	String phone_wzmgr = "18502285517";
	String phone_cwmgr = "13820641813";
	
	String phone_io_apply = "13752791982";//开票发起人
	String phone_mc_upsert = "18920336967";
	String phone_mc_claim_proj = "13910623294";
	String phone_mc_approve_proj = "13910623294";
	String phone_mc_claim_bzj = "13910623294";
	String phone_mc_approve_bzj = "13910623294";
	
	RestClient restClient = new RestClient();
	Request request = new Request();
	
	
	//项目立项
	public Map<String,String> proj(String proj_name_) throws ClientProtocolException, IOException {
		String proj_mgr_type_ = "P";
		String ys_kphte= "2000";
		String proj_id_ = "";
		Map<String, String> headermap = restClient.header();//获取信息头（带cookie）
		request.login(headermap, phone_pm);//登录
		Map<String, String> proj_para = new RequestPara().proj_save_para(proj_id_, proj_name_, proj_mgr_, proj_mgr_id_, dept_proj, proj_mgr_type_);
		proj_id_ = request.projSave(headermap, proj_para);//新建项目信息
		request.projBudget(headermap, proj_id_, ys_kphte);//新建预算信息
		request.projUpload(proj_id_, headermap);//上传附件
		request.projApply(proj_id_, headermap);//提交
		if(phone_dept != ""){
			request.projSp(phone_dept, headermap, proj_id_, "1", "顺顺大猪蹄子");//部门领导审批
		}
		request.projSp(phone_sybzjl, headermap, proj_id_, "1", "顺顺大猪蹄子");//事业部总经理审批
		request.projSp(phone_azmgr, headermap, proj_id_, "1", "顺顺大猪蹄子");//安质负责人审批
		request.projSp(phone_sc, headermap, proj_id_, "1", "顺顺大猪蹄子");//生产副总审批
		request.login(headermap, phone_pm);
		String id = request.getProjId(headermap);
		Assert.assertEquals(id, proj_id_, "ProjSp : 没有这个项目！");
		String proj_name = request.getProjName(headermap);
		String proj_code = request.getProjCode(headermap);
		Map<String,String> get_value = new HashMap<String, String>();
		get_value.put("proj_id_", proj_id_);
		get_value.put("proj_name", proj_name);
		get_value.put("proj_code", proj_code);
		return get_value;
	}
	//甲方合同
	public String cpa(String cpa_name_, String money_cpa, String proj_name, String proj_id_, String purchase_code_, String purchase_id_) throws ClientProtocolException, IOException {
		String nature_ = "书面";
		String type_ = "人力外包";
		Map<String, String> headermap = restClient.header();//获取信息头（带cookie）
		String cpaId = "";
		request.login(headermap, phone_create);//登录
		//审批
		Map<String, String> para = new RequestPara().cpa_sava_para(cpaId, cpa_name_, money_cpa, dept_cpa, nature_, type_, proj_name, proj_id_, purchase_code_, purchase_id_);
		cpaId = request.cpaSave(headermap, para);//保存合同信息
		request.cpa_mo_Save(headermap, money_cpa, cpaId);//保存付款计划
		request.cpa_ii_Save(headermap, money_cpa, cpaId);//保存收票计划
		request.cpaApply(headermap, cpaId);//提交
		if(phone_dept != ""){
			request.cpaSp(headermap, phone_dept, cpaId, "1", "长鹏大沙雕");//部门
		}
		request.cpaSp(headermap, phone_fw, cpaId, "1", "长鹏大沙雕");//法务
		if(phone_fg != ""){
			request.cpaSp(headermap, phone_fg, cpaId, "1", "长鹏大沙雕");//分管领导
		}else if(phone_sybdsz !=""){
			request.cpaSp(headermap, phone_sybzjl, cpaId, "1", "长鹏大沙雕");
			request.cpaSp(headermap, phone_sybdsz, cpaId, "1", "长鹏大沙雕");
		}else{
			request.cpaSp(headermap, phone_sybzjl, cpaId, "1", "长鹏大沙雕");
		}
		request.cpaSp(headermap, phone_cpaaz, cpaId, "1", "长鹏大沙雕");//安质
		request.cpaSp(headermap, phone_cpawz, cpaId, "1", "长鹏大沙雕");//物资
		request.cpaSp(headermap, phone_cpacw, cpaId, "1", "长鹏大沙雕");//财务
		request.cpaSp(headermap, phone_boss, cpaId, "1", "长鹏大沙雕");//总经理
		request.login(headermap, phone_create);
		String id = request.getCpaId(headermap);
		Assert.assertEquals(id, cpaId, "CpaSp : 没有这个甲方合同！");
		//只发起不审批
		cpaId = "";
		Map<String, String> para1 = new RequestPara().cpa_sava_para(cpaId, cpa_name_, money_cpa, dept_cpa, nature_, type_, proj_name, proj_id_, purchase_code_, purchase_id_);
		cpaId = request.cpaSave(headermap, para1);//保存合同信息
		request.cpa_mo_Save(headermap, money_cpa, cpaId);//保存付款计划
		request.cpa_ii_Save(headermap, money_cpa, cpaId);//保存收票计划
		request.cpaApply(headermap, cpaId);//提交
		return id;
	}
	//乙方合同
	public String cpb(String cpb_name_, String money_cpb, String proj_name, String proj_id_) throws ClientProtocolException, IOException {
		String nature_ = "书面";
		String type_ = "信息化建设";
		Map<String, String> headermap = restClient.header();//获取信息头（带cookie）
		request.login(headermap, phone_create);//登录
		
		//审批通过
		String cpbId = "";
		Map<String, String> para = new RequestPara().cpb_sava_para(cpbId, cpb_name_, money_cpb, dept_cpb, nature_, type_, proj_name, proj_id_);
		cpbId = request.cpbSave(headermap, para);//保存合同信息
		request.cpb_mi_Save(headermap, money_cpb, cpbId);//保存收款计划
		request.cpb_io_Save(headermap, money_cpb, cpbId);//保存开票计划
		request.cpbApply(headermap, cpbId);//提交
		if(phone_dept != ""){
			request.cpbSp(headermap, phone_dept, cpbId, "1", "长鹏大沙雕");//部门
		}
		request.cpbSp(headermap, phone_fw, cpbId, "1", "长鹏大沙雕");//法务
		request.cpbSp(headermap, phone_sybzjl, cpbId, "1", "长鹏大沙雕");//事业部总经理
		if(phone_sybdsz !=""){
			request.cpbSp(headermap, phone_sybdsz, cpbId, "1", "长鹏大沙雕");
		}
		request.cpbSp(headermap, phone_boss, cpbId, "1", "长鹏大沙雕");//总经理
		request.login(headermap, phone_create);
		String id = request.getCpbId(headermap);
		Assert.assertEquals(id, cpbId, "CpbSp : 没有这个乙方合同！");
		//只发起
		cpbId = "";
		Map<String, String> para1 = new RequestPara().cpb_sava_para(cpbId, cpb_name_, money_cpb, dept_cpb, nature_, type_, proj_name, proj_id_);
		cpbId = request.cpbSave(headermap, para1);//保存合同信息
		request.cpb_mi_Save(headermap, money_cpb, cpbId);//保存收款计划
		request.cpb_io_Save(headermap, money_cpb, cpbId);//保存开票计划
		request.cpbApply(headermap, cpbId);//提交
		return id;
	}
	//费用报销
	public void fybx(String money_fybx, String bxType, String proj_id_, String proj_code, String proj_name) throws ClientProtocolException, IOException {
		String phone_bxType = request.phone_fybx(bxType);//特殊节点
		String pmo = "";
		String typeFlag = "";
		String bxrIds = "3b8f52b4b8f54bd597557361efcb8736";
		String bxrNames = "武军豪";
		Map<String, String> headermap = restClient.header();//获取信息头（带cookie）
		request.login(headermap, phone_create);//登录
		Map<String, String> para = new RequestPara().fybx_para(proj_id_, proj_code, proj_name, money_fybx, bxType, bxrIds, bxrNames, pmo, typeFlag);
		request.fybxApply(headermap, para);//只发起申请
		request.fybxApply(headermap, para);//发起申请
		String fybxId = request.getFybxId(headermap);//获取报销ID
		request.fybxSp(headermap, phone_pm, fybxId, "1", "顺顺制杖");//项目经理
		if(phone_dept != ""){
			request.fybxSp(headermap, phone_dept, fybxId, "1", "顺顺制杖");//部门
		}
		request.fybxSp(headermap, phone_sybzjl, fybxId, "1", "顺顺制杖");//事业部经理
		if (phone_bxType != null){
			request.fybxSp(headermap, phone_bxType, fybxId, "1", "顺顺制杖");//特殊费用
		}
		request.fybxSp(headermap, phone_sybcw, fybxId, "1", "顺顺制杖");//财务初审
		request.fybxSp(headermap, phone_sybcw, fybxId, "1", "顺顺制杖");//财务核票
		if(phone_sybdsz !=""){
			request.fybxSp(headermap, phone_sybdsz, fybxId, "1", "顺顺制杖");
		}
		request.fybxSp(headermap, phone_boss, fybxId, "1", "顺顺制杖");//总经理
		request.fybxSp(headermap, phone_sybcw, fybxId, "1", "顺顺制杖");//财务付款
		request.login(headermap, phone_create);
		request.checkFlow(headermap, proj_id_, money_fybx, bxType, "费用报销");
	}
	//差旅报销
	public void clbx(double money_clbx, String proj_id_, String proj_code, String proj_name) throws ClientProtocolException, IOException {
		String pmo = "";
		String typeFlag = "";
//		String bxrIds = "3b8f52b4b8f54bd597557361efcb8736";
//		String bxrNames = "武军豪";
		Map<String, String> headermap = restClient.header();//获取信息头（带cookie）
		request.login(headermap, phone_create);//登录
		Map<String, String> para1 = new RequestPara().clbx_para(headermap, proj_id_, proj_code, proj_name, money_clbx, pmo, typeFlag);
		request.clbxApply(headermap, para1);//只发起申请
		Map<String, String> para = new RequestPara().clbx_para(headermap, proj_id_, proj_code, proj_name, money_clbx, pmo, typeFlag);
		request.clbxApply(headermap, para);//发起申请
		String clbxId = request.getClbxId(headermap);//获取报销ID
		request.clbxSp(headermap, phone_pm, clbxId, "1", "顺顺制杖");//项目经理
		if(phone_dept != ""){
			request.clbxSp(headermap, phone_dept, clbxId, "1", "顺顺制杖");//部门
		}
		request.clbxSp(headermap, phone_sybzjl, clbxId, "1", "顺顺制杖");//事业部总经理
		request.clbxSp(headermap, phone_clbxType, clbxId, "1", "顺顺制杖");//特殊费用（交通费用）
		request.clbxSp(headermap, phone_sybcw, clbxId, "1", "顺顺制杖");//财务初审
		request.clbxSp(headermap, phone_sybcw, clbxId, "1", "顺顺制杖");//财务核票
		if(phone_sybdsz !=""){
			request.clbxSp(headermap, phone_sybdsz, clbxId, "1", "顺顺制杖");
		}
		request.clbxSp(headermap, phone_boss, clbxId, "1", "顺顺制杖");//总经理
		request.clbxSp(headermap, phone_sybcw, clbxId, "1", "顺顺制杖");//财务付款
		request.checkFlow(headermap, proj_id_, String.valueOf(money_clbx), "差旅报销", "差旅报销");
	}
	//采购申请（自用物资）
	public Map<String,String> purchase(String money_purchase, String proj_id_, String purchase_type) throws ClientProtocolException, IOException {
		Map<String, String> headermap = restClient.header();//获取信息头（带cookie）
		String purchaseId = "";
		String proc_id_ = "";
		request.login(headermap, phone_create);//登录
		Map<String, String> para = new RequestPara().purchase_para(proj_id_, money_purchase, purchaseId, proc_id_, purchase_type);
		request.purchaseApply(headermap, para);//只发起
		request.purchaseApply(headermap, para);
		purchaseId = request.getPurchaseId(headermap);//获取ID
		request.purchaseSp(headermap, phone_pm, purchaseId, "1", "顺顺ZZ");//项目经理
		if(phone_dept != ""){
			request.purchaseSp(headermap, phone_dept, purchaseId, "1", "顺顺ZZ");//部门
		}
		if(phone_fg != ""){
			request.purchaseSp(headermap, phone_fg, purchaseId, "1", "顺顺ZZ");//分管领导
		}else if(phone_sybdsz !=""){
			request.purchaseSp(headermap, phone_sybzjl, purchaseId, "1", "顺顺ZZ");
			request.purchaseSp(headermap, phone_sybdsz, purchaseId, "1", "顺顺ZZ");
		}else{
			request.purchaseSp(headermap, phone_sybzjl, purchaseId, "1", "顺顺ZZ");
		}
		request.purchaseSp(headermap, phone_zhglmgr, purchaseId, "1", "顺顺ZZ");//综合管理
		request.purchaseSp(headermap, phone_boss, purchaseId, "1", "顺顺ZZ");//总经理
		request.purchaseSp(headermap, phone_wzzz, purchaseId, "1", "顺顺ZZ");//物资专责
		request.login(headermap, phone_create);
		String id = request.getPurchaseId(headermap);
		Assert.assertEquals(id, purchaseId, "PurchaseSp : 没有这个采购申请！");
		String purchase_code_ = request.getPurchaseCode(headermap);
		Map<String,String> get_value = new HashMap<String, String>();
		get_value.put("purchaseId", purchaseId);
		get_value.put("purchase_code_", purchase_code_);
		return get_value;
	}
	//采购付款（自用物资、电商）
	public void payment(String money_payment, String proj_id_, String purchase_type, String cpaId, String cpbId, String purchaseId) throws ClientProtocolException, IOException {
		String is_ds_pur_ = "1";
		Map<String, String> headermap = restClient.header();//获取信息头（带cookie）
		String paymentId = "";
		String proc_id_ = "";
		request.login(headermap, phone_create);//登录
		Map<String, String> para = new RequestPara().payment_para(proj_id_, money_payment, is_ds_pur_, cpaId, cpbId, purchaseId, paymentId, proc_id_, purchase_type);
		request.paymentApply(headermap, para);//只发起
		request.paymentApply(headermap, para);
		paymentId = request.getPaymentId(headermap);//获取ID
		request.paymentSp(headermap, phone_pm, paymentId, "1", "顺顺ZZ");//项目经理
		request.paymentSp(headermap, phone_wzzz, paymentId, "1", "顺顺ZZ");//物资专责
		if(phone_dept != ""){
			request.paymentSp(headermap, phone_dept, paymentId, "1", "顺顺ZZ");//部门
		}
		if(phone_fg != ""){
			request.paymentSp(headermap, phone_fg, paymentId, "1", "顺顺ZZ");//分管领导
		}else if(phone_sybdsz !=""){
			request.paymentSp(headermap, phone_sybzjl, paymentId, "1", "顺顺ZZ");
			request.paymentSp(headermap, phone_sybdsz, paymentId, "1", "顺顺ZZ");
		}else{
			request.paymentSp(headermap, phone_sybzjl, paymentId, "1", "顺顺ZZ");
		}
		request.paymentSp(headermap, phone_zhglmgr, paymentId, "1", "顺顺ZZ");//综合管理
		request.paymentSp(headermap, phone_wzmgr, paymentId, "1", "顺顺ZZ");//物资负责人
		request.paymentSp(headermap, phone_boss, paymentId, "1", "顺顺ZZ");//总经理
		request.paymentSp(headermap, phone_sybcw, paymentId, "1", "顺顺ZZ");//事业部财务
		request.checkFlow(headermap, proj_id_, String.valueOf(money_payment), "采购付款", "采购付款");
	}
	//借款
	public String loan(String money_loan, String type_loan, String proj_name, String proj_id_) throws ClientProtocolException, IOException {
		String phone_special = request.phone_loan(type_loan);
		Map<String, String> headermap = restClient.header();//获取信息头（带cookie）
		String loanId = "";
		request.login(headermap, phone_create);//登录
		Map<String, String> para = new RequestPara().loan_para(money_loan, type_loan, proj_name, proj_id_, loanId);
		if (type_loan == "标书费"){
			request.loan_bsfApply(headermap, para);//只发起
			request.loan_bsfApply(headermap, para);
		}else{
			request.loanApply(headermap, para);//只发起
			request.loanApply(headermap, para);
		}
		
		loanId = request.getLoanId(headermap);//获取ID
		if(phone_dept != ""){
			request.loanSp(headermap, phone_dept, loanId, "1", "顺顺大猪蹄子");//部门
		}
		if(phone_fg != ""){
			request.loanSp(headermap, phone_fg, loanId, "1", "顺顺大猪蹄子");//分管领导
		}else if(phone_sybdsz !=""){
			request.loanSp(headermap, phone_sybzjl, loanId, "1", "顺顺大猪蹄子");
			request.loanSp(headermap, phone_sybdsz, loanId, "1", "顺顺大猪蹄子");
		}else{
			request.loanSp(headermap, phone_sybzjl, loanId, "1", "顺顺大猪蹄子");
		}
		request.loanSp(headermap, phone_special, loanId, "1", "顺顺大猪蹄子");//特殊节点
		request.loanSp(headermap, phone_boss, loanId, "1", "顺顺大猪蹄子");//总经理
		request.loanSp(headermap, phone_sybcw, loanId, "1", "顺顺大猪蹄子");//事业部财务
		request.login(headermap, phone_create);
		String id = request.getLoanId(headermap);
		Assert.assertEquals(id, loanId, "LoanSp : 没有这个借款！");
		if (type_loan != "实习生工资"){
			request.checkFlow(headermap, proj_id_, money_loan, type_loan, "借款");
		}else{
			System.out.println("实习生工资不参与成本 ");//暂时不知道怎么校验
		}
		return loanId;
	}
	//开票
	public void finIo(String money_finIo, String type_finIo, String cpbId) throws ClientProtocolException, IOException {
		Map<String, String> headermap = restClient.header();//获取信息头（带cookie）
		String finIoId = "";
		request.login(headermap, phone_io_apply);//登录
		Map<String, String> para = new RequestPara().finIo_para(finIoId, money_finIo, type_finIo, cpbId);
		request.FinIoApply(headermap, para);//只发起
		request.FinIoApply(headermap, para);
		finIoId = request.getFinIoId(headermap);//获取ID
		request.finIoSp(headermap, phone_sybzjl, finIoId, "1", "顺顺ZZ", "");//事业部总经理
		request.finIoSp(headermap, phone_cwmgr, finIoId, "1", "顺顺ZZ", "");//财务
		request.finIoSp(headermap, phone_sybcw, finIoId, "1", "顺顺ZZ", "123123123");//事业部财务
		
	}
	//回款认领
	public void mc(String money_mc, String type_mc, String ywId, String proj_id_) throws ClientProtocolException, IOException, InterruptedException {
		String isHuiPiao = "0";
		Map<String, String> headermap = restClient.header();//获取信息头（带cookie）
		String mcId = "";
		request.login(headermap, phone_mc_upsert);//登录
		Map<String, String> para = new RequestPara().mc_upsert_para(mcId, money_mc, isHuiPiao);
		request.mcUpsert(headermap, para);//发布回款
		mcId = request.getMcId(headermap);
		String update_time_ = request.getUpdateTimeId(headermap);
		switch (type_mc){
		case "proj":
			request.mcClaim(headermap, phone_mc_claim_proj, mcId, type_mc, ywId, update_time_, deptId_mc);//认领
			request.mcApprove(headermap, phone_mc_approve_proj, mcId, "1", "");//确认
			break;
		case "bzj":
			request.mcClaim(headermap, phone_mc_claim_bzj, mcId, type_mc, ywId, update_time_, deptId_mc);//认领
			request.mcApprove(headermap, phone_mc_approve_bzj, mcId, "1", "");//确认
			break;
		}
		//只认领不确认
		request.login(headermap, phone_mc_upsert);//登录
		request.mcUpsert(headermap, para);//发布回款
		mcId = request.getMcId(headermap);
		String update_time_1 = request.getUpdateTimeId(headermap);
		switch (type_mc){
		case "proj":
			request.mcClaim(headermap, phone_mc_claim_proj, mcId, type_mc, ywId, update_time_1, deptId_mc);//认领
			break;
		case "bzj":
			request.mcClaim(headermap, phone_mc_claim_bzj, mcId, type_mc, ywId, update_time_1, deptId_mc);//认领
			break;
		}
		Thread.sleep(500);
		request.checkFlow(headermap, proj_id_, money_mc, type_mc, "在线认款");
	}
	//看板：健康卡校验数据
	public void healthCheck(String projId, Map<String,String> flow) throws ClientProtocolException, IOException{
		Map<String, String> headermap = restClient.header();//获取信息头（带cookie）
		request.login(headermap, phone_pm);
		Map<String,String> health = request.health(headermap, projId);
		request.checkHealth(health, flow);
	}
	//项目看板：成本管控统计校验数据
	public void budgetCheck(String projId, String flow_kind, String flow_actual, String flow_buget) throws ClientProtocolException, IOException{
		Map<String, String> headermap = restClient.header();//获取信息头（带cookie）
		request.login(headermap, phone_pm);
		Map<String,String> flow = new HashMap<String,String>();
		flow.put("kind", flow_kind);
		flow.put("actual", flow_actual);
		flow.put("buget", flow_buget);
		Map<String,String> budget = request.budget(headermap, projId, flow_kind);
		request.checkBudget(budget, flow);
	}
}
