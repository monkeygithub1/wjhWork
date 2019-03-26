package wjh.pmisproduct.demo;

import java.io.IOException;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.testng.annotations.Test;

import wjh.pmis.restclient.RestClient;
import wjh.pmisproduct.data.Request;
import wjh.pmisproduct.data.RequestPara;

public class ProjDemo {
	RestClient restClient = new RestClient();
	Request request = new Request();
	RequestPara requestPara = new RequestPara();
	
	String phone_PROC_PROJ_DUTY = restClient.info_product("phone_PROC_PROJ_DUTY");
	String phone_boss = restClient.info_product("phone_boss");
	
	String proj_name_ = "项目立项流程";
	String proj_type_ = "管理类型";
	String ys_kphte = "10000";
	
	String phone_PM = restClient.info_product("phone_PM");
	String proj_mgr_ = restClient.info_product("proj_mgr_");
	String proj_mgr_id_ = restClient.info_product("proj_mgr_id_");
	
	String dept_ = restClient.info_product("dept_jishubu");
	String dept_id_ = requestPara.dept(dept_).get("dept_proj");
	String phone_dept = requestPara.dept(dept_).get("phone_dept");
	//一次性通过
	@Test
	public void projDemo() throws ClientProtocolException, IOException {
		String projId = "";
		Map<String,String> headermap = restClient.header_product();
		request.login(headermap, phone_PM);
		Map<String, String> para_save = requestPara.proj_save_para(projId, proj_name_, proj_mgr_, proj_mgr_id_, dept_, dept_id_, proj_type_);
		projId = request.projSave(headermap, para_save);
		Map<String, String> para_budge = requestPara.proj_budge_para(projId, ys_kphte);
		request.projBudget(headermap, para_budge);
		request.projApply(projId, headermap);
		request.projSp(phone_dept, headermap, projId, "1", "长鹏大猪蹄子");
		request.projSp(phone_PROC_PROJ_DUTY, headermap, projId, "1", "长鹏大猪蹄子");
		request.projSp(phone_boss, headermap, projId, "1", "长鹏大猪蹄子");
	}
	//每个节点拒绝后通过
	@Test(priority=1)
	public void projDemoRefuse() throws ClientProtocolException, IOException {
		String projId = "";
		Map<String,String> headermap = restClient.header_product();
		request.login(headermap, phone_PM);
		Map<String, String> para_save = requestPara.proj_save_para(projId, proj_name_, proj_mgr_, proj_mgr_id_, dept_, dept_id_, proj_type_);
		projId = request.projSave(headermap, para_save);
		Map<String, String> para_budge = requestPara.proj_budge_para(projId, ys_kphte);
		request.projBudget(headermap, para_budge);
		request.projApply(projId, headermap);
		//部门拒绝
		request.projSp(phone_dept, headermap, projId, "2", "长鹏大猪蹄子");
		//项目专责拒绝
		this.projEdit(headermap, projId);
		request.projSp(phone_dept, headermap, projId, "1", "长鹏大猪蹄子");
		request.projSp(phone_PROC_PROJ_DUTY, headermap, projId, "2", "长鹏大猪蹄子");
		//总经理拒绝
		this.projEdit(headermap, projId);
		request.projSp(phone_dept, headermap, projId, "1", "长鹏大猪蹄子");
		request.projSp(phone_PROC_PROJ_DUTY, headermap, projId, "1", "长鹏大猪蹄子");
		request.projSp(phone_boss, headermap, projId, "2", "长鹏大猪蹄子");
		//通过
		this.projEdit(headermap, projId);
		request.projSp(phone_dept, headermap, projId, "1", "长鹏大猪蹄子");
		request.projSp(phone_PROC_PROJ_DUTY, headermap, projId, "1", "长鹏大猪蹄子");
		request.projSp(phone_boss, headermap, projId, "1", "长鹏大猪蹄子");
	}
	//简易版修改重发
	public void projEdit(Map<String, String> headermap, String projId) throws ClientProtocolException, IOException{
		request.login(headermap, phone_PM);
		request.projApply(projId, headermap);
	}
}
