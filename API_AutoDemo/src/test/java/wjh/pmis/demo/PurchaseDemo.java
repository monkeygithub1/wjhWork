package wjh.pmis.demo;

import java.io.IOException;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.testng.annotations.Test;

import wjh.pmis.data.Request;
import wjh.pmis.restclient.RestClient;
	/*
	 * 采购类型purchase_type：0-自用物资；1-项目物资；2-人力外包；3-业务外包/其他服务采购；4-项目物资框架；5-综合费用；6-人力资源；7-租房采购
	 * purchase_type不同，审批流程不同
	 */
public class PurchaseDemo {
	String phone_create = "13910623294";//发起人 
	String phone_dept = "18502285517";//安质部
	String phone_pm = "13910623294";
	String phone_fg = "18602270056";
	String phone_zhglmgr = "15122681282";
	String phone_boss = "13502103187";
	String phone_wzzz = "13820781432";
	
	String purchase_type = "0";
	String money_ = "500";
	String proj_id_ = "0f241d5264ad44f58798ad93c645836e";
	RestClient restClient = new RestClient();
	Request request = new Request();
	//一次性通过
	@Test
	public void purchaseDemo() throws ClientProtocolException, IOException {
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
		request.purchaseSp(headermap, phone_boss, purchaseId, "1", "顺顺ZZ");//总经理
		request.purchaseSp(headermap, phone_wzzz, purchaseId, "1", "顺顺ZZ");//物资专责
	}
	//每个节点拒绝一次
	@Test (priority=1)
	public void purchaseDemoRefuse() throws ClientProtocolException, IOException {
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
		//总经理
		this.purchaseEdit(headermap, purchaseId, proc_id_);
		request.purchaseSp(headermap, phone_pm, purchaseId, "1", "顺顺ZZ");//项目经理
		request.purchaseSp(headermap, phone_dept, purchaseId, "1", "顺顺ZZ");//部门
		request.purchaseSp(headermap, phone_fg, purchaseId, "1", "顺顺ZZ");//分管领导
		request.purchaseSp(headermap, phone_zhglmgr, purchaseId, "1", "顺顺ZZ");//综合管理
		request.purchaseSp(headermap, phone_boss, purchaseId, "2", "顺顺ZZ");//总经理
		//物资专责
		this.purchaseEdit(headermap, purchaseId, proc_id_);
		request.purchaseSp(headermap, phone_pm, purchaseId, "1", "顺顺ZZ");//项目经理
		request.purchaseSp(headermap, phone_dept, purchaseId, "1", "顺顺ZZ");//部门
		request.purchaseSp(headermap, phone_fg, purchaseId, "1", "顺顺ZZ");//分管领导
		request.purchaseSp(headermap, phone_zhglmgr, purchaseId, "1", "顺顺ZZ");//综合管理
		request.purchaseSp(headermap, phone_boss, purchaseId, "1", "顺顺ZZ");//总经理
		request.purchaseSp(headermap, phone_wzzz, purchaseId, "2", "顺顺ZZ");//物资专责
		//通过
		this.purchaseEdit(headermap, purchaseId, proc_id_);
		request.purchaseSp(headermap, phone_pm, purchaseId, "1", "顺顺ZZ");//项目经理
		request.purchaseSp(headermap, phone_dept, purchaseId, "1", "顺顺ZZ");//部门
		request.purchaseSp(headermap, phone_fg, purchaseId, "1", "顺顺ZZ");//分管领导
		request.purchaseSp(headermap, phone_zhglmgr, purchaseId, "1", "顺顺ZZ");//综合管理
		request.purchaseSp(headermap, phone_boss, purchaseId, "1", "顺顺ZZ");//总经理
		request.purchaseSp(headermap, phone_wzzz, purchaseId, "1", "顺顺ZZ");//物资专责
	}
	//采购申请简易修改（什么信息都不改）
	public void purchaseEdit (Map<String, String> headermap, String purchaseId, String proc_id_) throws ClientProtocolException, IOException{
		request.login(headermap, phone_create);
		request.purchaseUpdate(headermap, proj_id_, money_, purchase_type, purchaseId, proc_id_);
	}
}
