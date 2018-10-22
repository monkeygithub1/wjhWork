package wjh.pmis.testcase.payment;

import java.io.IOException;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.testng.annotations.Test;

import wjh.pmis.data.Request;
import wjh.pmis.restclient.RestClient;
	/*
	 * 职能部门（安质部）
	 * 采购类型：4-项目物资框架
	 * 是否电商：1-电商
	 */
public class Payment_xmwzkj_001 {
	String phone_create = "13910623294";//发起人 
	String phone_pm = "13910623294";
	String phone_sybzjl = "13502103187";
	String phone_boss = "13502103187";
	String phone_sybcw = "15822316986";
	
	String purchase_type = "4";
	String is_ds_pur_ = "1";
	String money_ = "500";
	String proj_id_ = "0f241d5264ad44f58798ad93c645836e";
	String cpaId = "b14fecfdb4bd4079bd86c189ae885358";
	String cpbId = "9d8a6dee0e5841999eb9617d9459efd9";
	String purchaseId = "29e66f5b3807414f8cb6ffacc558e0ce";
	
	RestClient restClient = new RestClient();
	Request request = new Request();
	//一次性通过
	@Test
	public void payment_xmwzkj_001() throws ClientProtocolException, IOException {
		Map<String, String> headermap = restClient.header();//获取信息头（带cookie）
		String paymentId = "";
		String proc_id_ = "";
		request.login(headermap, phone_create);//登录
		request.paymentApply(headermap, proj_id_, money_, purchase_type, is_ds_pur_, cpaId, cpbId, purchaseId, paymentId, proc_id_);
		paymentId = request.getPaymentId(headermap);//获取ID
		request.paymentSp(headermap, phone_pm, paymentId, "1", "顺顺ZZ");//项目经理
		request.paymentSp(headermap, phone_sybzjl, paymentId, "1", "顺顺ZZ");//物资专责
		request.paymentSp(headermap, phone_boss, paymentId, "1", "顺顺ZZ");//总经理
		request.paymentSp(headermap, phone_sybcw, paymentId, "1", "顺顺ZZ");//事业部财务
	}
	//每个节点拒绝一次
	@Test(priority=1)
	public void payment_xmwzkj_001_Refuse() throws ClientProtocolException, IOException {
		Map<String, String> headermap = restClient.header();//获取信息头（带cookie）
		String paymentId = "";
		String proc_id_ = "";
		request.login(headermap, phone_create);//登录
		request.paymentApply(headermap, proj_id_, money_, purchase_type, is_ds_pur_, cpaId, cpbId, purchaseId, paymentId, proc_id_);
		paymentId = request.getPaymentId(headermap);//获取ID
		proc_id_ = request.getPaymentProcId(headermap);
		//项目经理拒绝
		request.paymentSp(headermap, phone_pm, paymentId, "2", "顺顺ZZ");
		//事业部总经理拒绝
		this.paymentEdit(headermap, paymentId, proc_id_);
		request.paymentSp(headermap, phone_pm, paymentId, "1", "顺顺ZZ");
		request.paymentSp(headermap, phone_sybzjl, paymentId, "2", "顺顺ZZ");
		
		//总经理拒绝
		this.paymentEdit(headermap, paymentId, proc_id_);
		request.paymentSp(headermap, phone_pm, paymentId, "1", "顺顺ZZ");
		request.paymentSp(headermap, phone_sybzjl, paymentId, "1", "顺顺ZZ");
		request.paymentSp(headermap, phone_boss, paymentId, "2", "顺顺ZZ");
		//事业部财务拒绝
		this.paymentEdit(headermap, paymentId, proc_id_);
		request.paymentSp(headermap, phone_pm, paymentId, "1", "顺顺ZZ");
		request.paymentSp(headermap, phone_sybzjl, paymentId, "1", "顺顺ZZ");
		request.paymentSp(headermap, phone_boss, paymentId, "1", "顺顺ZZ");
		request.paymentSp(headermap, phone_sybcw, paymentId, "2", "顺顺ZZ");
		//通过
		this.paymentEdit(headermap, paymentId, proc_id_);
		request.paymentSp(headermap, phone_pm, paymentId, "1", "顺顺ZZ");
		request.paymentSp(headermap, phone_sybzjl, paymentId, "1", "顺顺ZZ");
		request.paymentSp(headermap, phone_boss, paymentId, "1", "顺顺ZZ");
		request.paymentSp(headermap, phone_sybcw, paymentId, "1", "顺顺ZZ");
	}
	//采购付款简易修改（什么信息都不改）
	public void paymentEdit (Map<String, String> headermap, String paymentId, String proc_id_) throws ClientProtocolException, IOException{
		request.login(headermap, phone_create);
		request.paymentUpdate(headermap, proj_id_, money_, purchase_type, is_ds_pur_, cpaId, cpbId, purchaseId, paymentId, proc_id_);
	}
}
