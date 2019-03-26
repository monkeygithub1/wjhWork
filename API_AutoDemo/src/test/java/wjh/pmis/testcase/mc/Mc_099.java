package wjh.pmis.testcase.mc;

import java.io.IOException;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.testng.annotations.Test;

import wjh.pmis.data.DeptAndPhone;
import wjh.pmis.data.Request;
import wjh.pmis.data.RequestPara;
import wjh.pmis.restclient.RestClient;
import wjh.pmis.testcase.loan.Loan_008_bzj;
	/*
	 * 是否承况汇票isHuiPiao : 0-否, 1-是
	 * 回款类别type_: proj-项目回款, bzj-保证金回款
	 */
public class Mc_099 {
	DeptAndPhone dept_phone = new DeptAndPhone();
	String dept_name = "职能部门";
	Map<String , String> dept_para = dept_phone.getDeptInfo(dept_name);
	String deptId = dept_para.get("deptId_mc");
	String ywId = "";
	String phone_mc_upsert = dept_phone.phone_mc_upsert;//发布人 
	String phone_mc_claim_proj = dept_phone.phone_mc_claim_proj;
	String phone_mc_claim_bzj = dept_phone.phone_mc_claim_bzj;
	String phone_mc_approve_proj = dept_phone.phone_mc_approve_proj;
	String phone_mc_approve_bzj = dept_phone.phone_mc_approve_bzj;
	
	String isHuiPiao = "1";
	String money_ = "100";
	String type_mc = "proj";
	
	RestClient restClient = new RestClient();
	Request request = new Request();
	//一次性通过
	@Test
	public void mc_099() throws ClientProtocolException, IOException {
		String isHuiPiao = "0";
		Map<String, String> headermap = restClient.header();//获取信息头（带cookie）
		String mcId = "";
		request.login(headermap, phone_mc_upsert);//登录
		Map<String, String> para = new RequestPara().mc_upsert_para(mcId, money_, isHuiPiao);
		request.mcUpsert(headermap, para);//发布回款
		mcId = request.getMcId(headermap);
		String update_time_ = request.getUpdateTimeId(headermap);
		switch (type_mc){
		case "proj":
			ywId = dept_para.get("cpbId");
			request.mcClaim(headermap, phone_mc_claim_proj, mcId, type_mc, ywId, update_time_, deptId);//认领
			request.mcApprove(headermap, phone_mc_approve_proj, mcId, "1", "");//确认
			break;
		case "bzj":
			ywId = new Loan_008_bzj().loan_008();
			request.mcClaim(headermap, phone_mc_claim_bzj, mcId, type_mc, ywId, update_time_, deptId);//认领
			request.mcApprove(headermap, phone_mc_approve_bzj, mcId, "1", "");//确认
			break;
		}
		
	}
	
}
