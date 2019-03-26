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
	 * 职能部门（安质部）
	 * 采购类型：2-人力外包；
	 * 采购金额>10K，总经理审批
	 */
public class Purchase_rlwb_099 {
	DeptAndPhone dept_phone = new DeptAndPhone();
	String dept_name = "空间数据加工厂";
	Map<String , String> dept_para = dept_phone.getDeptInfo(dept_name);
	String phone_create = dept_phone.phone_create;
	String phone_dept = dept_para.get("phone_dept");
	String phone_pm = dept_para.get("phone_pm");
	String phone_fg = dept_para.get("phone_fg");
	String phone_sybzjl = dept_para.get("phone_sybzjl");
	String phone_sybdsz = dept_para.get("phone_sybdsz");
	String proj_id_ = dept_para.get("proj_id_");
	String phone_boss = dept_phone.phone_boss;
	String phone_wzzz = dept_phone.phone_wzzz;
	String phone_azmgr = dept_phone.phone_azmgr;
	
	String purchase_type = "2";
	String money_ = "10000";
	RestClient restClient = new RestClient();
	Request request = new Request();
	//一次性通过
	@Test
	public void purchase_rlwb_001_() throws ClientProtocolException, IOException{
		this.purchase_rlwb_001();
	}
	//直接把这个方法放在@test下貌似不执行，原因未知
	public String purchase_rlwb_001() throws ClientProtocolException, IOException {
		Map<String, String> headermap = restClient.header();//获取信息头（带cookie）
		String purchaseId = "";
		String proc_id_ = "";
		request.login(headermap, phone_create);//登录
		Map<String, String> para = new RequestPara().purchase_para(proj_id_, money_, purchaseId, proc_id_, purchase_type);
		request.purchaseApply(headermap, para);
		purchaseId = request.getPurchaseId(headermap);//获取ID
		request.purchaseSp(headermap, phone_pm, purchaseId, "1", "顺顺ZZ");
		if(phone_dept != ""){
			request.purchaseSp(headermap, phone_dept, purchaseId, "1", "顺顺ZZ");//部门
		}
		if(phone_fg != ""){
			request.purchaseSp(headermap, phone_fg, purchaseId, "1", "顺顺ZZ");//分管领导
		}else if(phone_sybdsz !=""){
			request.purchaseSp(headermap, phone_sybzjl, purchaseId, "1", "顺顺ZZ");
			request.purchaseSp(headermap, phone_sybdsz, purchaseId, "1", "顺顺ZZ");
		}else{
			request.purchaseSp(headermap, phone_sybzjl, purchaseId, "1", "顺顺ZZ");
		}
		request.purchaseSp(headermap, phone_azmgr, purchaseId, "1", "顺顺ZZ");
		request.purchaseSp(headermap, phone_boss, purchaseId, "1", "顺顺ZZ");
		request.purchaseSp(headermap, phone_wzzz, purchaseId, "1", "顺顺ZZ");
		return purchaseId;
	}
}
