package wjh.pmis.testcase.purchase;

import java.io.IOException;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.testng.annotations.Test;

import wjh.pmis.data.Request;
import wjh.pmis.restclient.RestClient;
	/*
	 * 职能部门（安质部）
	 * 采购类型：0-自用物资；
	 * 采购金额<10K，无需总经理审批
	 */
public class Purchase_zywz_002 {
	String phone_create = "13910623294";//发起人 
	String phone_dept = "18502285517";//安质部
	String phone_pm = "13910623294";
	String phone_fg = "18602270056";
	String phone_zhglmgr = "15122681282";
	String phone_wzzz = "13820781432";
	
	String purchase_type = "0";
	String money_ = "5000";
	String proj_id_ = "0f241d5264ad44f58798ad93c645836e";
	RestClient restClient = new RestClient();
	Request request = new Request();
	//一次性通过
	@Test
	public void purchase_zywz_002() throws ClientProtocolException, IOException {
		Map<String, String> headermap = restClient.header();//获取信息头（带cookie）
		String purchaseId = "";
		String proc_id_ = "";
		request.login(headermap, phone_create);//登录
		request.purchaseApply(headermap, proj_id_, money_, purchase_type, purchaseId, proc_id_);
		purchaseId = request.getPurchaseId(headermap);//获取ID
		request.purchaseSp(headermap, phone_pm, purchaseId, "1", "顺顺ZZ");//项目经理
		request.purchaseSp(headermap, phone_dept, purchaseId, "1", "顺顺ZZ");//部门
		request.purchaseSp(headermap, phone_fg, purchaseId, "1", "顺顺ZZ");//分管领导
		request.purchaseSp(headermap, phone_zhglmgr, purchaseId, "1", "顺顺ZZ");//综合管理
		request.purchaseSp(headermap, phone_wzzz, purchaseId, "1", "顺顺ZZ");//物资专责
	}
	//每个节点拒绝一次
	@Test (priority=1)
	public void purchase_zywz_002_Refuse() throws ClientProtocolException, IOException {
		Map<String, String> headermap = restClient.header();//获取信息头（带cookie）
		String purchaseId = "";
		String proc_id_ = "";
		request.login(headermap, phone_create);//登录
		request.purchaseApply(headermap, proj_id_, money_, purchase_type, purchaseId, proc_id_);
		purchaseId = request.getPurchaseId(headermap);//获取ID
		proc_id_ = request.getPurchaseProcId(headermap);
		//项目经理
		request.purchaseSp(headermap, phone_pm, purchaseId, "2", "顺顺ZZ");//项目经理
		//部门
		this.purchaseEdit(headermap, purchaseId, proc_id_);
		request.purchaseSp(headermap, phone_pm, purchaseId, "1", "顺顺ZZ");//项目经理
		request.purchaseSp(headermap, phone_dept, purchaseId, "2", "顺顺ZZ");//部门
		//分管领导
		this.purchaseEdit(headermap, purchaseId, proc_id_);
		request.purchaseSp(headermap, phone_pm, purchaseId, "1", "顺顺ZZ");//项目经理
		request.purchaseSp(headermap, phone_dept, purchaseId, "1", "顺顺ZZ");//部门
		request.purchaseSp(headermap, phone_fg, purchaseId, "2", "顺顺ZZ");//分管领导
		//综合管理
		this.purchaseEdit(headermap, purchaseId, proc_id_);
		request.purchaseSp(headermap, phone_pm, purchaseId, "1", "顺顺ZZ");//项目经理
		request.purchaseSp(headermap, phone_dept, purchaseId, "1", "顺顺ZZ");//部门
		request.purchaseSp(headermap, phone_fg, purchaseId, "1", "顺顺ZZ");//分管领导
		request.purchaseSp(headermap, phone_zhglmgr, purchaseId, "2", "顺顺ZZ");//综合管理
		//物资专责
		this.purchaseEdit(headermap, purchaseId, proc_id_);
		request.purchaseSp(headermap, phone_pm, purchaseId, "1", "顺顺ZZ");//项目经理
		request.purchaseSp(headermap, phone_dept, purchaseId, "1", "顺顺ZZ");//部门
		request.purchaseSp(headermap, phone_fg, purchaseId, "1", "顺顺ZZ");//分管领导
		request.purchaseSp(headermap, phone_zhglmgr, purchaseId, "1", "顺顺ZZ");//综合管理
		request.purchaseSp(headermap, phone_wzzz, purchaseId, "2", "顺顺ZZ");//物资专责
		//通过
		this.purchaseEdit(headermap, purchaseId, proc_id_);
		request.purchaseSp(headermap, phone_pm, purchaseId, "1", "顺顺ZZ");//项目经理
		request.purchaseSp(headermap, phone_dept, purchaseId, "1", "顺顺ZZ");//部门
		request.purchaseSp(headermap, phone_fg, purchaseId, "1", "顺顺ZZ");//分管领导
		request.purchaseSp(headermap, phone_zhglmgr, purchaseId, "1", "顺顺ZZ");//综合管理
		request.purchaseSp(headermap, phone_wzzz, purchaseId, "1", "顺顺ZZ");//物资专责
	}
	//采购申请简易修改（什么信息都不改）
	public void purchaseEdit (Map<String, String> headermap, String purchaseId, String proc_id_) throws ClientProtocolException, IOException{
		request.login(headermap, phone_create);
		request.purchaseUpdate(headermap, proj_id_, money_, purchase_type, purchaseId, proc_id_);
	}
}
