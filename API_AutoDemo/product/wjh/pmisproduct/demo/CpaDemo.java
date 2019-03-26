package wjh.pmisproduct.demo;

import java.io.IOException;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.testng.annotations.Test;

import wjh.pmis.restclient.RestClient;
import wjh.pmisproduct.data.Request;
import wjh.pmisproduct.data.RequestPara;

public class CpaDemo {
	RestClient restClient = new RestClient();
	Request request = new Request();
	RequestPara requestPara = new RequestPara();
	
	String phone_create = restClient.info_product("phone_create");
	String phone_PROC_CPA_DUTY = restClient.info_product("phone_PROC_CPA_DUTY");
	String phone_boss = restClient.info_product("phone_boss");
	
	String cpa_name_= "甲方合同-顺顺最帅-一次性通过";
	String cpa_name_refuse= "甲方合同-顺顺最帅-每个节点拒绝一次";
	String money_ = "10000";
	String nature_= "书面";
	String type_= "物资采购";
	String proj_ = restClient.info_product("proj_");
	String proj_id_ = restClient.info_product("proj_id_");
	String purchase_code_ = restClient.info_product("purchase_code_");
	String purchase_id_ = restClient.info_product("purchase_id_");
	
	String dept_ = restClient.info_product("dept_jishubu");
	String dept_id_ = requestPara.dept(dept_).get("dept_cpa");
	String phone_dept = requestPara.dept(dept_).get("phone_dept");
	String phone_PM = restClient.info_product("phone_PM");
	
	//一次性通过
	@Test
	public void cpaDemo() throws ClientProtocolException, IOException {
		Map<String, String> headermap = restClient.header_product();//获取信息头（带cookie）
		String cpaId = "";
		request.login(headermap, phone_create);//登录
		Map<String, String> para_save = new RequestPara().cpa_sava_para(cpaId, cpa_name_, money_, dept_, dept_id_, nature_, type_, proj_, proj_id_, purchase_code_, purchase_id_);
		cpaId = request.cpaSave(headermap, para_save);//保存合同信息
		Map<String, String> para_mo = new RequestPara().cpa_mo_para(money_, cpaId);
		request.cpa_mo_Save(headermap, para_mo);//保存付款计划
		Map<String, String> para_ii = new RequestPara().cpa_ii_para(money_, cpaId);
		request.cpa_ii_Save(headermap, para_ii);//保存收票计划
		request.cpaApply(headermap, cpaId);//提交
		request.cpaSp(headermap, phone_PM, cpaId, "1", "长鹏大沙雕");
		request.cpaSp(headermap, phone_dept, cpaId, "1", "长鹏大沙雕");
		request.cpaSp(headermap, phone_PROC_CPA_DUTY, cpaId, "1", "长鹏大沙雕");
		request.cpaSp(headermap, phone_boss, cpaId, "1", "长鹏大沙雕");
	}
//	//每个节点拒绝一次
//	@Test(priority=1)
//	public void cpaDemoRefuse() throws ClientProtocolException, IOException {
//		Map<String, String> headermap = restClient.header_product();//获取信息头（带cookie）
//		String cpaId = "";
//		request.login(headermap, phone_create);//登录
//		cpaId = request.cpaSave(headermap, cpaId, cpa_name_refuse, money_, dept_, nature_, type_, proj_, proj_id_);//保存合同信息
//		request.cpa_mo_Save(headermap, money_, cpaId);//保存付款计划
//		request.cpa_ii_Save(headermap, money_, cpaId);//保存收票计划
//		request.cpaApply(headermap, cpaId);//提交
//		//部门拒绝
//		request.cpaSp(headermap, phone_dept, cpaId, "2", "长鹏大沙雕");
//		//总经理拒绝
//		this.cpaEdit(headermap, cpaId, "拒绝重发");
//		request.cpaSp(headermap, phone_dept, cpaId, "1", "长鹏大沙雕");
//		request.cpaSp(headermap, phone_boss, cpaId, "2", "长鹏大沙雕");
//		//通过
//		this.cpaEdit(headermap, cpaId, "拒绝重发");
//		request.cpaSp(headermap, phone_dept, cpaId, "1", "长鹏大沙雕");
//		request.cpaSp(headermap, phone_boss, cpaId, "1", "长鹏大沙雕");
//	}
//	//甲方合同基本信息简易修改（仅修改合同名称）
//	public void cpaEdit(Map<String,String> headermap, String cpaId, String cpa_name_edit) throws ClientProtocolException, IOException{
//		request.login(headermap, phone_create);
//		request.cpaSave(headermap, cpaId, cpa_name_edit, money_, dept_, nature_, type_, proj_, proj_id_);//编辑
//		request.cpaApply(headermap, cpaId);
//	}
}
