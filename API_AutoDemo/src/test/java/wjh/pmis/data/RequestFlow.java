package wjh.pmis.data;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.testng.Assert;

import wjh.pmis.restclient.RestClient;

public class RequestFlow {
	//审批流程人员
	String phone_create = "13910623294";//发起人 
	String phone_dept = "18502285517";//安质部
	String phone_sybzjl = "13502103187";//事业部总经理
	String phone_azmgr = "18502285517";//安质部负责人
	String phone_sc = "18602270056";//生产副总
	String phone_boss = "13502103187";
	String phone_sybcw = "15822316986";
	
	String phone_fg = "18602270056";//安质分管
	String phone_fw = "15822223160";
	String phone_wzzz = "13820781432";
	
	String phone_cpaaz = "18502285517";
	String phone_cpawz = "18502285517";
	String phone_cpacw = "13820641813";
	
	String phone_clbxType = "15122681282";//差旅报销出行节点
	
	String phone_zhglmgr = "15122681282";
	String phone_wzmgr = "18502285517";
	String phone_cwmgr = "13820641813";
	String phone_mc_upsert = "18920336967";
	String phone_mc_claim_proj = "13910623294";
	String phone_mc_approve_proj = "15620699121";
	
	String proj_mgr_ = "武军豪";
	String proj_mgr_id_ = "3b8f52b4b8f54bd597557361efcb8736";
	String phone_pm = "13910623294";
	String dept_proj = "安全质量部-c4fb1b9eba214363b23ad5a792897f61";
	String deptId = "c4fb1b9eba214363b23ad5a792897f61";
	RestClient restClient = new RestClient();
	Request request = new Request();
	
	
	
	
	
	
	
	
	
	//项目立项
	public Map<String,String> proj(String proj_name_) throws ClientProtocolException, IOException {
		String proj_mgr_type_ = "P";
		String ys_kphte= "2000";
		String proj_id_ = "";
		Map<String, String> headermap = restClient.header();//获取信息头（带cookie）
		request.login(headermap, phone_create);//登录
		proj_id_ = request.projSave(headermap, proj_id_, proj_name_, proj_mgr_, proj_mgr_id_, dept_proj, proj_mgr_type_);//新建项目信息
		request.projBudge(headermap, proj_id_, ys_kphte);//新建预算信息
		request.projUpload(proj_id_, headermap);//上传附件
		request.projApply(proj_id_, headermap);//提交
		request.projSp(phone_dept, headermap, proj_id_, "1", "顺顺大猪蹄子");//部门领导审批
		request.projSp(phone_sybzjl, headermap, proj_id_, "1", "顺顺大猪蹄子");//事业部总经理审批
		request.projSp(phone_azmgr, headermap, proj_id_, "1", "顺顺大猪蹄子");//安质负责人审批
		request.projSp(phone_sc, headermap, proj_id_, "1", "顺顺大猪蹄子");//生产副总审批
		request.login(headermap, phone_create);
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
		String dept_ = "安全质量部#S#c4fb1b9eba214363b23ad5a792897f61";
		String nature_ = "书面";
		String type_ = "人力外包";
		Map<String, String> headermap = restClient.header();//获取信息头（带cookie）
		String cpaId = "";
		request.login(headermap, phone_create);//登录
		cpaId = request.cpaSave(headermap, cpaId, cpa_name_, money_cpa, dept_, nature_, type_, proj_name, proj_id_, purchase_code_, purchase_id_);//保存合同信息
		request.cpa_mo_Save(headermap, money_cpa, cpaId);//保存付款计划
		request.cpa_ii_Save(headermap, money_cpa, cpaId);//保存收票计划
		request.cpaApply(headermap, cpaId);//提交
		request.cpaSp(headermap, phone_dept, cpaId, "1", "长鹏大沙雕");//部门
		request.cpaSp(headermap, phone_fw, cpaId, "1", "长鹏大沙雕");//法务
		request.cpaSp(headermap, phone_fg, cpaId, "1", "长鹏大沙雕");//分管领导
		request.cpaSp(headermap, phone_cpaaz, cpaId, "1", "长鹏大沙雕");//安质
		request.cpaSp(headermap, phone_cpawz, cpaId, "1", "长鹏大沙雕");//物资
		request.cpaSp(headermap, phone_cpacw, cpaId, "1", "长鹏大沙雕");//财务
		request.cpaSp(headermap, phone_boss, cpaId, "1", "长鹏大沙雕");//总经理
		request.login(headermap, phone_create);
		String id = request.getCpaId(headermap);
		Assert.assertEquals(id, cpaId, "CpaSp : 没有这个甲方合同！");
		return id;
	}
	//乙方合同
	public String cpb(String cpb_name_, String money_cpb, String proj_name, String proj_id_) throws ClientProtocolException, IOException {
		String dept_ = "安全质量部#S#c4fb1b9eba214363b23ad5a792897f61";
		String nature_ = "书面";
		String type_ = "信息化建设";
		Map<String, String> headermap = restClient.header();//获取信息头（带cookie）
		String cpbId = "";
		request.login(headermap, phone_create);//登录
		cpbId = request.cpbSave(headermap, cpbId, cpb_name_, money_cpb, dept_, nature_, type_, proj_name, proj_id_);//保存合同信息
		request.cpb_mi_Save(headermap, money_cpb, cpbId);//保存收款计划
		request.cpb_io_Save(headermap, money_cpb, cpbId);//保存开票计划
		request.cpbApply(headermap, cpbId);//提交
		request.cpbSp(headermap, phone_dept, cpbId, "1", "长鹏大沙雕");//部门
		request.cpbSp(headermap, phone_fw, cpbId, "1", "长鹏大沙雕");//法务
		request.cpbSp(headermap, phone_sybzjl, cpbId, "1", "长鹏大沙雕");//事业部总经理
		request.cpbSp(headermap, phone_boss, cpbId, "1", "长鹏大沙雕");//总经理
		request.login(headermap, phone_create);
		String id = request.getCpbId(headermap);
		Assert.assertEquals(id, cpbId, "CpbSp : 没有这个乙方合同！");
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
		request.fybxApply(headermap, proj_id_, proj_code, proj_name, money_fybx, bxType, bxrIds, bxrNames, pmo, typeFlag);//发起申请
		String fybxId = request.getFybxId(headermap);//获取报销ID
		request.fybxSp(headermap, phone_pm, fybxId, "1", "顺顺制杖");//项目经理
		request.fybxSp(headermap, phone_dept, fybxId, "1", "顺顺制杖");//部门
		request.fybxSp(headermap, phone_sybzjl, fybxId, "1", "顺顺制杖");//事业部经理
		if (bxType != "加班餐费"){
			request.fybxSp(headermap, phone_bxType, fybxId, "1", "顺顺制杖");//特殊费用
		}
		request.fybxSp(headermap, phone_sybcw, fybxId, "1", "顺顺制杖");//财务初审
		request.fybxSp(headermap, phone_sybcw, fybxId, "1", "顺顺制杖");//财务核票
		request.fybxSp(headermap, phone_boss, fybxId, "1", "顺顺制杖");//总经理
		request.fybxSp(headermap, phone_sybcw, fybxId, "1", "顺顺制杖");//财务付款
		request.login(headermap, phone_create);
		request.checkFlow(headermap, proj_id_, money_fybx, bxType, "费用报销");
	}
	//差旅报销
	public void clbx(double money_clbx, String proj_id_, String proj_code, String proj_name) throws ClientProtocolException, IOException {
		String pmo = "";
		String typeFlag = "";
		String bxrIds = "3b8f52b4b8f54bd597557361efcb8736";
		String bxrNames = "武军豪";
		Map<String, String> headermap = restClient.header();//获取信息头（带cookie）
		request.login(headermap, phone_create);//登录
		request.clbxApply(headermap, proj_id_, proj_code, proj_name, money_clbx, bxrIds, bxrNames, pmo, typeFlag);//发起申请
		String clbxId = request.getClbxId(headermap);//获取报销ID
		request.clbxSp(headermap, phone_pm, clbxId, "1", "顺顺制杖");//项目经理
		request.clbxSp(headermap, phone_dept, clbxId, "1", "顺顺制杖");//部门
		request.clbxSp(headermap, phone_sybzjl, clbxId, "1", "顺顺制杖");//事业部总经理
		request.clbxSp(headermap, phone_clbxType, clbxId, "1", "顺顺制杖");//特殊费用（交通费用）
		request.clbxSp(headermap, phone_sybcw, clbxId, "1", "顺顺制杖");//财务初审
		request.clbxSp(headermap, phone_sybcw, clbxId, "1", "顺顺制杖");//财务核票
		request.clbxSp(headermap, phone_boss, clbxId, "1", "顺顺制杖");//总经理
		request.clbxSp(headermap, phone_sybcw, clbxId, "1", "顺顺制杖");//财务付款
	}
	//采购申请
	public Map<String,String> purchase(String money_purchase, String proj_id_, String purchase_type) throws ClientProtocolException, IOException {
		Map<String, String> headermap = restClient.header();//获取信息头（带cookie）
		String purchaseId = "";
		String proc_id_ = "";
		request.login(headermap, phone_create);//登录
		request.purchaseApply(headermap, proj_id_, money_purchase, purchase_type, purchaseId, proc_id_);
		purchaseId = request.getPurchaseId(headermap);//获取ID
		request.purchaseSp(headermap, phone_pm, purchaseId, "1", "顺顺ZZ");//项目经理
		request.purchaseSp(headermap, phone_dept, purchaseId, "1", "顺顺ZZ");//部门
		request.purchaseSp(headermap, phone_fg, purchaseId, "1", "顺顺ZZ");//分管领导
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
	//采购付款
	public void payment(String money_payment, String proj_id_, String purchase_type, String cpaId, String cpbId, String purchaseId) throws ClientProtocolException, IOException {
		String is_ds_pur_ = "1";
		Map<String, String> headermap = restClient.header();//获取信息头（带cookie）
		String paymentId = "";
		String proc_id_ = "";
		request.login(headermap, phone_create);//登录
		request.paymentApply(headermap, proj_id_, money_payment, purchase_type, is_ds_pur_, cpaId, cpbId, purchaseId, paymentId, proc_id_);
		paymentId = request.getPaymentId(headermap);//获取ID
		request.paymentSp(headermap, phone_pm, paymentId, "1", "顺顺ZZ");//项目经理
		request.paymentSp(headermap, phone_wzzz, paymentId, "1", "顺顺ZZ");//物资专责
		request.paymentSp(headermap, phone_dept, paymentId, "1", "顺顺ZZ");//部门
		request.paymentSp(headermap, phone_fg, paymentId, "1", "顺顺ZZ");//分管领导
		request.paymentSp(headermap, phone_zhglmgr, paymentId, "1", "顺顺ZZ");//综合管理
		request.paymentSp(headermap, phone_wzmgr, paymentId, "1", "顺顺ZZ");//物资负责人
		request.paymentSp(headermap, phone_boss, paymentId, "1", "顺顺ZZ");//总经理
		request.paymentSp(headermap, phone_sybcw, paymentId, "1", "顺顺ZZ");//事业部财务
	}
	//借款
	public String loan(String money_loan, String type_loan, String proj_name, String proj_id_) throws ClientProtocolException, IOException {
		String phone_special = request.phone_loan(type_loan);
		Map<String, String> headermap = restClient.header();//获取信息头（带cookie）
		String loanId = "";
		request.login(headermap, phone_create);//登录
		request.loanApply(headermap, money_loan, type_loan, proj_name, proj_id_, loanId);
		loanId = request.getLoanId(headermap);//获取ID
		request.loanSp(headermap, phone_dept, loanId, "1", "顺顺大猪蹄子");//部门
		request.loanSp(headermap, phone_fg, loanId, "1", "顺顺大猪蹄子");//分管领导
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
		request.login(headermap, phone_create);//登录
		request.FinIoApply(headermap, finIoId, money_finIo, type_finIo, cpbId);
		finIoId = request.getFinIoId(headermap);//获取ID
		request.finIoSp(headermap, phone_sybzjl, finIoId, "1", "顺顺ZZ", "");//事业部总经理
		request.finIoSp(headermap, phone_cwmgr, finIoId, "1", "顺顺ZZ", "");//财务
		request.finIoSp(headermap, phone_sybcw, finIoId, "1", "顺顺ZZ", "123123123");//事业部财务
		
	}
	//回款认领
	public void mc(String money_mc, String type_mc, String ywId) throws ClientProtocolException, IOException {
		String isHuiPiao = "0";
		Map<String, String> headermap = restClient.header();//获取信息头（带cookie）
		String mcId = "";
		request.login(headermap, phone_mc_upsert);//登录
		request.mcUpsert(headermap, mcId, money_mc, isHuiPiao);//发布回款
		mcId = request.getMcId(headermap);
		String update_time_ = request.getUpdateTimeId(headermap);
		request.mcClaim(headermap, phone_mc_claim_proj, mcId, type_mc, ywId, update_time_, deptId);//认领
		request.mcApprove(headermap, phone_mc_approve_proj, mcId, "1", "");//确认
		
	}
}
