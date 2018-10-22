package wjh.pmis.testcase.payment;

import java.io.IOException;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.testng.annotations.Test;

import wjh.pmis.data.Request;
import wjh.pmis.restclient.RestClient;
	/*
	 * 职能部门（安质部）
	 * 采购类型：1-项目物资
	 * 是否电商：0-非电商
	 */
public class Payment_xmwz_002 {
	String phone_create = "13910623294";//发起人 
	String phone_dept = "18502285517";//安质部
	String phone_pm = "13910623294";
	String phone_fg = "18602270056";
	String phone_azmgr = "18502285517";
	String phone_boss = "13502103187";
	String phone_cwmgr = "13820641813";
	String phone_wzmgr = "18502285517";
	String phone_sybcw = "15822316986";
	
	String purchase_type = "1";
	String is_ds_pur_ = "0";
	String money_ = "500";
	String proj_id_ = "0f241d5264ad44f58798ad93c645836e";
	String cpaId = "b14fecfdb4bd4079bd86c189ae885358";
	String cpbId = "9d8a6dee0e5841999eb9617d9459efd9";
	String purchaseId = "c4c864bdcd614cb8a3048e8caeccb560";
	
	RestClient restClient = new RestClient();
	Request request = new Request();
	//一次性通过
	@Test
	public void payment_xmwz_002() throws ClientProtocolException, IOException {
		Map<String, String> headermap = restClient.header();//获取信息头（带cookie）
		String paymentId = "";
		String proc_id_ = "";
		request.login(headermap, phone_create);//登录
		request.paymentApply(headermap, proj_id_, money_, purchase_type, is_ds_pur_, cpaId, cpbId, purchaseId, paymentId, proc_id_);
		paymentId = request.getPaymentId(headermap);//获取ID
		request.paymentSp(headermap, phone_pm, paymentId, "1", "顺顺ZZ");//项目经理
		request.paymentSp(headermap, phone_dept, paymentId, "1", "顺顺ZZ");//部门
		request.paymentSp(headermap, phone_fg, paymentId, "1", "顺顺ZZ");//分管领导
		request.paymentSp(headermap, phone_cwmgr, paymentId, "1", "顺顺ZZ");//财务资产负责人
		request.paymentSp(headermap, phone_azmgr, paymentId, "1", "顺顺ZZ");//安质负责人
		request.paymentSp(headermap, phone_wzmgr, paymentId, "1", "顺顺ZZ");//物资负责人
		request.paymentSp(headermap, phone_boss, paymentId, "1", "顺顺ZZ");//总经理
		request.paymentSp(headermap, phone_sybcw, paymentId, "1", "顺顺ZZ");//事业部财务
	}
	//每个节点拒绝一次
	@Test(priority=1)
	public void payment_xmwz_002_Refuse() throws ClientProtocolException, IOException {
		Map<String, String> headermap = restClient.header();//获取信息头（带cookie）
		String paymentId = "";
		String proc_id_ = "";
		request.login(headermap, phone_create);//登录
		request.paymentApply(headermap, proj_id_, money_, purchase_type, is_ds_pur_, cpaId, cpbId, purchaseId, paymentId, proc_id_);
		paymentId = request.getPaymentId(headermap);//获取ID
		proc_id_ = request.getPaymentProcId(headermap);
		//项目经理拒绝
		request.paymentSp(headermap, phone_pm, paymentId, "2", "顺顺ZZ");
		//部门拒绝
		this.paymentEdit(headermap, paymentId, proc_id_);
		request.paymentSp(headermap, phone_pm, paymentId, "1", "顺顺ZZ");
		request.paymentSp(headermap, phone_dept, paymentId, "2", "顺顺ZZ");
		//分管领导拒绝
		this.paymentEdit(headermap, paymentId, proc_id_);
		request.paymentSp(headermap, phone_pm, paymentId, "1", "顺顺ZZ");
		request.paymentSp(headermap, phone_dept, paymentId, "1", "顺顺ZZ");
		request.paymentSp(headermap, phone_fg, paymentId, "2", "顺顺ZZ");
		//财务资产
		this.paymentEdit(headermap, paymentId, proc_id_);
		request.paymentSp(headermap, phone_pm, paymentId, "1", "顺顺ZZ");
		request.paymentSp(headermap, phone_dept, paymentId, "1", "顺顺ZZ");
		request.paymentSp(headermap, phone_fg, paymentId, "1", "顺顺ZZ");
		request.paymentSp(headermap, phone_cwmgr, paymentId, "2", "顺顺ZZ");
		//安质负责人拒绝
		this.paymentEdit(headermap, paymentId, proc_id_);
		request.paymentSp(headermap, phone_pm, paymentId, "1", "顺顺ZZ");
		request.paymentSp(headermap, phone_dept, paymentId, "1", "顺顺ZZ");
		request.paymentSp(headermap, phone_fg, paymentId, "1", "顺顺ZZ");
		request.paymentSp(headermap, phone_cwmgr, paymentId, "1", "顺顺ZZ");
		request.paymentSp(headermap, phone_azmgr, paymentId, "2", "顺顺ZZ");
		//物资负责人拒绝
		this.paymentEdit(headermap, paymentId, proc_id_);
		request.paymentSp(headermap, phone_pm, paymentId, "1", "顺顺ZZ");
		request.paymentSp(headermap, phone_dept, paymentId, "1", "顺顺ZZ");
		request.paymentSp(headermap, phone_fg, paymentId, "1", "顺顺ZZ");
		request.paymentSp(headermap, phone_cwmgr, paymentId, "1", "顺顺ZZ");
		request.paymentSp(headermap, phone_azmgr, paymentId, "1", "顺顺ZZ");
		request.paymentSp(headermap, phone_wzmgr, paymentId, "2", "顺顺ZZ");
		//总经理拒绝
		this.paymentEdit(headermap, paymentId, proc_id_);
		request.paymentSp(headermap, phone_pm, paymentId, "1", "顺顺ZZ");
		request.paymentSp(headermap, phone_dept, paymentId, "1", "顺顺ZZ");
		request.paymentSp(headermap, phone_fg, paymentId, "1", "顺顺ZZ");
		request.paymentSp(headermap, phone_cwmgr, paymentId, "1", "顺顺ZZ");
		request.paymentSp(headermap, phone_azmgr, paymentId, "1", "顺顺ZZ");
		request.paymentSp(headermap, phone_wzmgr, paymentId, "1", "顺顺ZZ");
		request.paymentSp(headermap, phone_boss, paymentId, "2", "顺顺ZZ");
		//事业部财务拒绝
		this.paymentEdit(headermap, paymentId, proc_id_);
		request.paymentSp(headermap, phone_pm, paymentId, "1", "顺顺ZZ");
		request.paymentSp(headermap, phone_dept, paymentId, "1", "顺顺ZZ");
		request.paymentSp(headermap, phone_fg, paymentId, "1", "顺顺ZZ");
		request.paymentSp(headermap, phone_cwmgr, paymentId, "1", "顺顺ZZ");
		request.paymentSp(headermap, phone_azmgr, paymentId, "1", "顺顺ZZ");
		request.paymentSp(headermap, phone_wzmgr, paymentId, "1", "顺顺ZZ");
		request.paymentSp(headermap, phone_boss, paymentId, "1", "顺顺ZZ");
		request.paymentSp(headermap, phone_sybcw, paymentId, "2", "顺顺ZZ");
		//通过拒绝
		this.paymentEdit(headermap, paymentId, proc_id_);
		request.paymentSp(headermap, phone_pm, paymentId, "1", "顺顺ZZ");
		request.paymentSp(headermap, phone_dept, paymentId, "1", "顺顺ZZ");
		request.paymentSp(headermap, phone_fg, paymentId, "1", "顺顺ZZ");
		request.paymentSp(headermap, phone_cwmgr, paymentId, "1", "顺顺ZZ");
		request.paymentSp(headermap, phone_azmgr, paymentId, "1", "顺顺ZZ");
		request.paymentSp(headermap, phone_wzmgr, paymentId, "1", "顺顺ZZ");
		request.paymentSp(headermap, phone_boss, paymentId, "1", "顺顺ZZ");
		request.paymentSp(headermap, phone_sybcw, paymentId, "1", "顺顺ZZ");
	}
	//采购付款简易修改（什么信息都不改）
	public void paymentEdit (Map<String, String> headermap, String paymentId, String proc_id_) throws ClientProtocolException, IOException{
		request.login(headermap, phone_create);
		request.paymentUpdate(headermap, proj_id_, money_, purchase_type, is_ds_pur_, cpaId, cpbId, purchaseId, paymentId, proc_id_);
	}
}
