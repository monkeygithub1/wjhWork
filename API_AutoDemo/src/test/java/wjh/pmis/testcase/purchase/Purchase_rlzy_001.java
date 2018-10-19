package wjh.pmis.testcase.purchase;

import java.io.IOException;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.testng.annotations.Test;

import wjh.pmis.data.Request;
import wjh.pmis.restclient.RestClient;
	/*
	 * 职能部门（安质部）
	 * 采购类型：6-人力资源；
	 * 采购金额>10K，总经理审批
	 */
public class Purchase_rlzy_001 {
	String phone_create = "13910623294";//发起人 
	String phone_pm = "13910623294";
	String phone_hr = "18698057506";
	String phone_boss = "13502103187";
	
	String purchase_type = "6";
	String money_ = "3000";
	String proj_id_ = "0f241d5264ad44f58798ad93c645836e";
	RestClient restClient = new RestClient();
	Request request = new Request();
	//一次性通过
	@Test
	public void purchase_rlzy_001() throws ClientProtocolException, IOException {
		Map<String, String> headermap = restClient.header();//获取信息头（带cookie）
		String purchaseId = "";
		String proc_id_ = "";
		request.login(headermap, phone_create);//登录
		request.purchaseApply(headermap, proj_id_, money_, purchase_type, purchaseId, proc_id_);
		purchaseId = request.getPurchaseId(headermap);//获取ID
		request.purchaseSp(headermap, phone_pm, purchaseId, "1", "顺顺ZZ");
		request.purchaseSp(headermap, phone_hr, purchaseId, "1", "顺顺ZZ");
		request.purchaseSp(headermap, phone_boss, purchaseId, "1", "顺顺ZZ");
	}
	//每个节点拒绝一次
	@Test (priority=1)
	public void purchase_rlzy_001_Refuse() throws ClientProtocolException, IOException {
		Map<String, String> headermap = restClient.header();//获取信息头（带cookie）
		String purchaseId = "";
		String proc_id_ = "";
		request.login(headermap, phone_create);//登录
		request.purchaseApply(headermap, proj_id_, money_, purchase_type, purchaseId, proc_id_);
		purchaseId = request.getPurchaseId(headermap);//获取ID
		proc_id_ = request.getPurchaseProcId(headermap);
		//项目经理
		request.purchaseSp(headermap, phone_pm, purchaseId, "2", "顺顺ZZ");
		//人资
		this.purchaseEdit(headermap, purchaseId, proc_id_);
		request.purchaseSp(headermap, phone_pm, purchaseId, "1", "顺顺ZZ");
		request.purchaseSp(headermap, phone_hr, purchaseId, "2", "顺顺ZZ");
		//总经理
		this.purchaseEdit(headermap, purchaseId, proc_id_);
		request.purchaseSp(headermap, phone_pm, purchaseId, "1", "顺顺ZZ");
		request.purchaseSp(headermap, phone_hr, purchaseId, "1", "顺顺ZZ");
		request.purchaseSp(headermap, phone_boss, purchaseId, "2", "顺顺ZZ");
		//通过
		this.purchaseEdit(headermap, purchaseId, proc_id_);
		request.purchaseSp(headermap, phone_pm, purchaseId, "1", "顺顺ZZ");
		request.purchaseSp(headermap, phone_hr, purchaseId, "1", "顺顺ZZ");
		request.purchaseSp(headermap, phone_boss, purchaseId, "1", "顺顺ZZ");
	}
	//采购申请简易修改（什么信息都不改）
	public void purchaseEdit (Map<String, String> headermap, String purchaseId, String proc_id_) throws ClientProtocolException, IOException{
		request.login(headermap, phone_create);
		request.purchaseUpdate(headermap, proj_id_, money_, purchase_type, purchaseId, proc_id_);
	}
}
