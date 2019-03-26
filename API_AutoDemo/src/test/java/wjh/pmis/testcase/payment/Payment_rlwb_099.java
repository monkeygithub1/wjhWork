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
import wjh.pmis.testcase.purchase.Purchase_rlwb_001;
	/*
	 * 职能部门（安质部）
	 * 采购类型：2-人力外包
	 * 是否电商：1-电商
	 */
public class Payment_rlwb_099 {
	DeptAndPhone dept_phone = new DeptAndPhone();
	String dept_name = new Purchase_rlwb_001().dept_name;
	Map<String , String> dept_para = dept_phone.getDeptInfo(dept_name);
	String phone_create = dept_phone.phone_create;
	String phone_pm = dept_para.get("phone_pm");
	String phone_dept = dept_para.get("phone_dept");
	String phone_fg = dept_para.get("phone_fg");
	String phone_sybcw = dept_para.get("phone_sybcw");
	String phone_sybzjl = dept_para.get("phone_sybzjl");
	String phone_sybdsz = dept_para.get("phone_sybdsz");
	String phone_boss = dept_phone.phone_boss;
	String phone_azmgr = dept_phone.phone_azmgr;
	String phone_zhglmgr = dept_phone.phone_zhglmgr;
	String phone_wzzz = dept_phone.phone_wzzz;
	String phone_wzmgr = dept_phone.phone_wzmgr;
	String phone_purchasepay_cwcn = dept_phone.phone_purchasepay_cwcn;
	String proj_id_ = dept_para.get("proj_id_");
	String cpaId = dept_para.get("cpaId");
	String cpbId = dept_para.get("cpbId");
	String purchaseId = "";
	
	String purchase_type = "2";
	String is_ds_pur_ = "1";
	String money_ = "100";
	
	RestClient restClient = new RestClient();
	Request request = new Request();
	@BeforeClass
	public void setUp() throws InterruptedException, ClientProtocolException, IOException {
		purchaseId = new Purchase_rlwb_001().purchase_rlwb_001();
//		purchaseId = dept_para.get("purchase_id_");;
	}
	//一次性通过
	@Test
	public void payment_rlwb_001() throws ClientProtocolException, IOException {
		Map<String, String> headermap = restClient.header();//获取信息头（带cookie）
		String paymentId = "";
		String proc_id_ = "";
		request.login(headermap, phone_create);//登录
		Map<String, String> para = new RequestPara().payment_para(proj_id_, money_, is_ds_pur_, cpaId, cpbId, purchaseId, paymentId, proc_id_, purchase_type);
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
		
		request.paymentSp(headermap, phone_azmgr, paymentId, "1", "顺顺ZZ");//安质负责人
		request.paymentSp(headermap, phone_wzmgr, paymentId, "1", "顺顺ZZ");//物资负责人
		request.paymentSp(headermap, phone_boss, paymentId, "1", "顺顺ZZ");//总经理
//		request.paymentSp(headermap, phone_sybcw, paymentId, "1", "顺顺ZZ");//事业部财务
		request.paymentSp(headermap, phone_purchasepay_cwcn, paymentId, "1", "顺顺ZZ");
	}
	
}
