package wjh.pmis.testcase.purchase;

import java.io.IOException;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.testng.annotations.Test;

import wjh.pmis.data.DeptAndPhone;
import wjh.pmis.data.Request;
import wjh.pmis.data.RequestPara;
import wjh.pmis.restclient.RestClient;
	/*
	 * 采购类型purchase_type：0-自用物资；1-项目物资；2-人力外包；3-业务外包/其他服务采购；4-项目物资框架；5-综合费用；6-人力资源；7-租房采购
	 * purchase_type不同，审批流程不同
	 */
public class Purchase_zhglzx_zy_001 {
	DeptAndPhone dept_phone = new DeptAndPhone();
	String dept_name = "综合管理中心";
	Map<String , String> dept_para = dept_phone.getDeptInfo(dept_name);
	String phone_create = dept_phone.phone_create;
	String phone_dept = dept_para.get("phone_dept");
	String phone_pm = dept_para.get("phone_pm");
	String phone_fg = dept_para.get("phone_fg");
	String proj_id_ = dept_para.get("proj_id_");
	String phone_zhglmgr = dept_phone.phone_zhglmgr;
	String phone_boss = dept_phone.phone_boss;
	String phone_wzzz = dept_phone.phone_wzzz;
	
	String purchase_type = "0";
	String money_ = "10000";
	
	RestClient restClient = new RestClient();
	Request request = new Request();
	//一次性通过
	@Test
	public void purchaseDemo() throws ClientProtocolException, IOException {
		Map<String, String> headermap = restClient.header();//获取信息头（带cookie）
		String purchaseId = "";
		String proc_id_ = "";
		request.login(headermap, phone_create);//登录
		Map<String, String> para = new RequestPara().purchase_para(proj_id_, money_, purchaseId, proc_id_, purchase_type);
		request.purchaseApply(headermap, para);
		purchaseId = request.getPurchaseId(headermap);//获取ID
		request.purchaseSp(headermap, phone_pm, purchaseId, "1", "顺顺ZZ");//项目经理
		request.purchaseSp(headermap, phone_dept, purchaseId, "1", "顺顺ZZ");//部门
		request.purchaseSp(headermap, phone_fg, purchaseId, "1", "顺顺ZZ");//分管领导
		request.purchaseSp(headermap, phone_zhglmgr, purchaseId, "1", "顺顺ZZ");//综合管理
		request.purchaseSp(headermap, phone_boss, purchaseId, "1", "顺顺ZZ");//总经理
		request.purchaseSp(headermap, phone_wzzz, purchaseId, "1", "顺顺ZZ");//物资专责
	}
	//每个节点拒绝一次
//	@Test (priority=1)
	public void purchaseDemoRefuse() throws ClientProtocolException, IOException {
		Map<String, String> headermap = restClient.header();//获取信息头（带cookie）
		String purchaseId = "";
		String proc_id_ = "";
		request.login(headermap, phone_create);//登录
		Map<String, String> para = new RequestPara().purchase_para(proj_id_, money_, purchaseId, proc_id_, purchase_type);
		request.purchaseApply(headermap, para);
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
		Map<String, String> para = new RequestPara().purchase_para(proj_id_, money_, purchaseId, proc_id_, purchase_type);
		request.purchaseUpdate(headermap, para);
	}
}
