package wjh.pmis.demo;

import java.io.IOException;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import wjh.pmis.data.DeptAndPhone;
import wjh.pmis.data.Request;
import wjh.pmis.data.RequestPara;
import wjh.pmis.restclient.RestClient;
import wjh.pmis.testcase.purchase.Purchase_zywz_001;
	/*
	 * 职能部门（安质部）-自用物资-电商
	 * 采购类型purchase_type：0-自用物资；1-项目物资；2-人力外包；3-业务外包/其他服务采购；4-项目物资框架；5-综合费用；6-人力资源；7-租房采购
	 * 是否电商is_ds_pur：0-否；1-是
	 */
public class PaymentDemo {
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
	
	String purchase_type = "0";
	String is_ds_pur_ = "1";
	String money_ = "500";
	
	RestClient restClient = new RestClient();
	Request request = new Request();
	@BeforeClass
	public void setUp() throws InterruptedException, ClientProtocolException, IOException {
		purchaseId = new Purchase_zywz_001().purchase_zywz_001();
	}
	//一次性通过
	@Test
	public void paymentDemo() throws ClientProtocolException, IOException {
		Map<String, String> headermap = restClient.header();//获取信息头（带cookie）
		String paymentId = "";
		String proc_id_ = "";
		request.login(headermap, phone_create);//登录
		Map<String, String> para = new RequestPara().payment_para(proj_id_, money_, is_ds_pur_, cpaId, cpbId, purchaseId, paymentId, proc_id_, purchase_type);
		request.paymentApply(headermap, para);
		paymentId = request.getPaymentId(headermap);//获取ID
		request.paymentSp(headermap, phone_pm, paymentId, "1", "顺顺ZZ");//项目经理
		request.paymentSp(headermap, phone_wzzz, paymentId, "1", "顺顺ZZ");//物资专责
		request.paymentSp(headermap, phone_dept, paymentId, "1", "顺顺ZZ");//部门
		request.paymentSp(headermap, phone_fg, paymentId, "1", "顺顺ZZ");//分管领导
		request.paymentSp(headermap, phone_zhglmgr, paymentId, "1", "顺顺ZZ");//综合管理
		request.paymentSp(headermap, phone_wzmgr, paymentId, "1", "顺顺ZZ");//物资负责人
		request.paymentSp(headermap, phone_boss, paymentId, "1", "顺顺ZZ");//总经理
//		request.paymentSp(headermap, phone_sybcw, paymentId, "1", "顺顺ZZ");//事业部财务
	}
	//每个节点拒绝一次
//	@Test(priority=1)
	public void paymentDemoRefuse() throws ClientProtocolException, IOException {
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
		//物资专责拒绝
		this.paymentEdit(headermap, paymentId, proc_id_);
		request.paymentSp(headermap, phone_pm, paymentId, "1", "顺顺ZZ");
		request.paymentSp(headermap, phone_wzzz, paymentId, "2", "顺顺ZZ");
		//部门拒绝
		this.paymentEdit(headermap, paymentId, proc_id_);
		request.paymentSp(headermap, phone_pm, paymentId, "1", "顺顺ZZ");
		request.paymentSp(headermap, phone_wzzz, paymentId, "1", "顺顺ZZ");
		request.paymentSp(headermap, phone_dept, paymentId, "2", "顺顺ZZ");
		//分管领导拒绝
		this.paymentEdit(headermap, paymentId, proc_id_);
		request.paymentSp(headermap, phone_pm, paymentId, "1", "顺顺ZZ");
		request.paymentSp(headermap, phone_wzzz, paymentId, "1", "顺顺ZZ");
		request.paymentSp(headermap, phone_dept, paymentId, "1", "顺顺ZZ");
		request.paymentSp(headermap, phone_fg, paymentId, "2", "顺顺ZZ");
		//综合管理拒绝
		this.paymentEdit(headermap, paymentId, proc_id_);
		request.paymentSp(headermap, phone_pm, paymentId, "1", "顺顺ZZ");
		request.paymentSp(headermap, phone_wzzz, paymentId, "1", "顺顺ZZ");
		request.paymentSp(headermap, phone_dept, paymentId, "1", "顺顺ZZ");
		request.paymentSp(headermap, phone_fg, paymentId, "1", "顺顺ZZ");
		request.paymentSp(headermap, phone_zhglmgr, paymentId, "2", "顺顺ZZ");
		//物资负责人拒绝
		this.paymentEdit(headermap, paymentId, proc_id_);
		request.paymentSp(headermap, phone_pm, paymentId, "1", "顺顺ZZ");
		request.paymentSp(headermap, phone_wzzz, paymentId, "1", "顺顺ZZ");
		request.paymentSp(headermap, phone_dept, paymentId, "1", "顺顺ZZ");
		request.paymentSp(headermap, phone_fg, paymentId, "1", "顺顺ZZ");
		request.paymentSp(headermap, phone_zhglmgr, paymentId, "1", "顺顺ZZ");
		request.paymentSp(headermap, phone_wzmgr, paymentId, "2", "顺顺ZZ");
		//总经理拒绝
		this.paymentEdit(headermap, paymentId, proc_id_);
		request.paymentSp(headermap, phone_pm, paymentId, "1", "顺顺ZZ");
		request.paymentSp(headermap, phone_wzzz, paymentId, "1", "顺顺ZZ");
		request.paymentSp(headermap, phone_dept, paymentId, "1", "顺顺ZZ");
		request.paymentSp(headermap, phone_fg, paymentId, "1", "顺顺ZZ");
		request.paymentSp(headermap, phone_zhglmgr, paymentId, "1", "顺顺ZZ");
		request.paymentSp(headermap, phone_wzmgr, paymentId, "1", "顺顺ZZ");
		request.paymentSp(headermap, phone_boss, paymentId, "2", "顺顺ZZ");
		//事业部财务拒绝
		this.paymentEdit(headermap, paymentId, proc_id_);
		request.paymentSp(headermap, phone_pm, paymentId, "1", "顺顺ZZ");
		request.paymentSp(headermap, phone_wzzz, paymentId, "1", "顺顺ZZ");
		request.paymentSp(headermap, phone_dept, paymentId, "1", "顺顺ZZ");
		request.paymentSp(headermap, phone_fg, paymentId, "1", "顺顺ZZ");
		request.paymentSp(headermap, phone_zhglmgr, paymentId, "1", "顺顺ZZ");
		request.paymentSp(headermap, phone_wzmgr, paymentId, "1", "顺顺ZZ");
		request.paymentSp(headermap, phone_boss, paymentId, "1", "顺顺ZZ");
		request.paymentSp(headermap, phone_sybcw, paymentId, "2", "顺顺ZZ");
		//通过拒绝
		this.paymentEdit(headermap, paymentId, proc_id_);
		request.paymentSp(headermap, phone_pm, paymentId, "1", "顺顺ZZ");
		request.paymentSp(headermap, phone_wzzz, paymentId, "1", "顺顺ZZ");
		request.paymentSp(headermap, phone_dept, paymentId, "1", "顺顺ZZ");
		request.paymentSp(headermap, phone_fg, paymentId, "1", "顺顺ZZ");
		request.paymentSp(headermap, phone_zhglmgr, paymentId, "1", "顺顺ZZ");
		request.paymentSp(headermap, phone_wzmgr, paymentId, "1", "顺顺ZZ");
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
