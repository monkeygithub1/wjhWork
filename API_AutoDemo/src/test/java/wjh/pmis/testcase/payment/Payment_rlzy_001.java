package wjh.pmis.testcase.payment;

import java.io.IOException;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import wjh.pmis.data.DeptAndPhone;
import wjh.pmis.data.Request;
import wjh.pmis.data.RequestPara;
import wjh.pmis.restclient.RestClient;
import wjh.pmis.testcase.purchase.Purchase_rlzy_001;
	/*
	 * 职能部门（安质部）
	 * 采购类型：6-人力资源
	 * 是否电商：固定为0
	 */
public class Payment_rlzy_001 {
	DeptAndPhone dept_phone = new DeptAndPhone();
	String dept_name = "职能部门";
	Map<String , String> dept_para = dept_phone.getDeptInfo(dept_name);
	String phone_create = dept_phone.phone_create;
	String phone_pm = dept_para.get("phone_pm");
	String phone_dept = dept_para.get("phone_dept");
	String phone_fg = dept_para.get("phone_fg");
	String phone_sybcw = dept_para.get("phone_sybcw");
	String phone_boss = dept_phone.phone_boss;
	String phone_azmgr = dept_phone.phone_azmgr;
	String phone_zhglmgr = dept_phone.phone_zhglmgr;
	String phone_wzzz = dept_phone.phone_wzzz;
	String phone_wzmgr = dept_phone.phone_wzmgr;
	String phone_hrmgr = dept_phone.phone_hrmgr;
	String phone_cwmgr = dept_phone.phone_cwmgr;
	String proj_id_ = dept_para.get("proj_id_");
	String cpaId = dept_para.get("cpaId");
	String cpbId = dept_para.get("cpbId");
	String purchaseId = "";
	
	
	String purchase_type = "6";
	String is_ds_pur_ = "0";
	String money_ = "1400";
	
	RestClient restClient = new RestClient();
	Request request = new Request();
	@BeforeClass
	public void setUp() throws InterruptedException, ClientProtocolException, IOException {
		purchaseId = new Purchase_rlzy_001().purchase_rlzy_001();
	}
	//一次性通过
	@Test
	public void payment_rlzy_001() throws ClientProtocolException, IOException {
		Map<String, String> headermap = restClient.header();//获取信息头（带cookie）
		String paymentId = "";
		String proc_id_ = "";
		request.login(headermap, phone_create);//登录
		Map<String, String> para = new RequestPara().payment_para(proj_id_, money_, is_ds_pur_, cpaId, cpbId, purchaseId, paymentId, proc_id_, purchase_type);
		request.paymentApply(headermap, para);
		paymentId = request.getPaymentId(headermap);//获取ID
		request.paymentSp(headermap, phone_pm, paymentId, "1", "顺顺ZZ");//项目经理
		request.paymentSp(headermap, phone_hrmgr, paymentId, "1", "顺顺ZZ");//人资
		request.paymentSp(headermap, phone_cwmgr, paymentId, "1", "顺顺ZZ");//财务资产
		request.paymentSp(headermap, phone_boss, paymentId, "1", "顺顺ZZ");//总经理
		request.paymentSp(headermap, phone_sybcw, paymentId, "1", "顺顺ZZ");//事业部财务
	}
	//每个节点拒绝一次
//	@Test(priority=1)
	public void payment_rlzy_001_Refuse() throws ClientProtocolException, IOException {
		Map<String, String> headermap = restClient.header();//获取信息头（带cookie）
		String paymentId = "";
		String proc_id_ = "";
		request.login(headermap, phone_create);//登录
		Map<String, String> para = new RequestPara().payment_para(proj_id_, money_, is_ds_pur_, cpaId, cpbId, purchaseId, paymentId, proc_id_, purchase_type);
		request.paymentApply(headermap, para);
		paymentId = request.getPaymentId(headermap);//获取ID
		proc_id_ = request.getPaymentProcId(headermap);
		//项目经理拒绝
		request.paymentSp(headermap, phone_pm, paymentId, "2", "顺顺ZZ");
		//人资拒绝
		this.paymentEdit(headermap, paymentId, proc_id_);
		request.paymentSp(headermap, phone_pm, paymentId, "1", "顺顺ZZ");
		request.paymentSp(headermap, phone_hrmgr, paymentId, "2", "顺顺ZZ");
		//财务资产拒绝
		this.paymentEdit(headermap, paymentId, proc_id_);
		request.paymentSp(headermap, phone_pm, paymentId, "1", "顺顺ZZ");
		request.paymentSp(headermap, phone_hrmgr, paymentId, "1", "顺顺ZZ");
		request.paymentSp(headermap, phone_cwmgr, paymentId, "2", "顺顺ZZ");
		//总经理拒绝
		this.paymentEdit(headermap, paymentId, proc_id_);
		request.paymentSp(headermap, phone_pm, paymentId, "1", "顺顺ZZ");
		request.paymentSp(headermap, phone_hrmgr, paymentId, "1", "顺顺ZZ");
		request.paymentSp(headermap, phone_cwmgr, paymentId, "1", "顺顺ZZ");
		request.paymentSp(headermap, phone_boss, paymentId, "2", "顺顺ZZ");
		//事业部财务拒绝
		this.paymentEdit(headermap, paymentId, proc_id_);
		request.paymentSp(headermap, phone_pm, paymentId, "1", "顺顺ZZ");
		request.paymentSp(headermap, phone_hrmgr, paymentId, "1", "顺顺ZZ");
		request.paymentSp(headermap, phone_cwmgr, paymentId, "1", "顺顺ZZ");
		request.paymentSp(headermap, phone_boss, paymentId, "1", "顺顺ZZ");
		request.paymentSp(headermap, phone_sybcw, paymentId, "2", "顺顺ZZ");
		//通过
		this.paymentEdit(headermap, paymentId, proc_id_);
		request.paymentSp(headermap, phone_pm, paymentId, "1", "顺顺ZZ");
		request.paymentSp(headermap, phone_hrmgr, paymentId, "1", "顺顺ZZ");
		request.paymentSp(headermap, phone_cwmgr, paymentId, "1", "顺顺ZZ");
		request.paymentSp(headermap, phone_boss, paymentId, "1", "顺顺ZZ");
		request.paymentSp(headermap, phone_sybcw, paymentId, "1", "顺顺ZZ");
	}
	//采购付款简易修改（什么信息都不改）
	public void paymentEdit (Map<String, String> headermap, String paymentId, String proc_id_) throws ClientProtocolException, IOException{
		request.login(headermap, phone_create);
		Map<String, String> para = new RequestPara().payment_para(proj_id_, money_, is_ds_pur_, cpaId, cpbId, purchaseId, paymentId, proc_id_, purchase_type);
		request.paymentUpdate(headermap, para);
	}
}
