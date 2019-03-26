package wjh.pmis.demo;

import java.io.IOException;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.testng.annotations.Test;

import wjh.pmis.data.DeptAndPhone;
import wjh.pmis.data.Request;
import wjh.pmis.data.RequestPara;
import wjh.pmis.restclient.RestClient;
	/*
	 * 是否承况汇票isHuiPiao : 0-否, 1-是
	 * 回款类别type_: proj-项目回款, bzj-保证金回款
	 * ywid:项目回款时为乙方合同，保证金时为借款
	 * 保证金回款比较麻烦，暂未处理
	 */
public class McDemo {
	DeptAndPhone dept_phone = new DeptAndPhone();
	String dept_name = "职能部门";
	Map<String , String> dept_para = dept_phone.getDeptInfo(dept_name);
	String deptId = dept_para.get("deptId_mc");
	String ywId = dept_para.get("cpbId");
	String phone_mc_upsert = dept_phone.phone_mc_upsert;//发布人 
	String phone_mc_claim_proj = dept_phone.phone_mc_claim_proj;
	String phone_mc_approve_proj = dept_phone.phone_mc_approve_proj;
	
	String isHuiPiao = "0";
	String money_ = "500";
	String type_ = "proj";
	
	RestClient restClient = new RestClient();
	Request request = new Request();
	//一次性通过
	@Test
	public void mcDemo() throws ClientProtocolException, IOException {
		Map<String, String> headermap = restClient.header();//获取信息头（带cookie）
		String mcId = "";
		request.login(headermap, phone_mc_upsert);//登录
		Map<String, String> para = new RequestPara().mc_upsert_para(mcId, money_, isHuiPiao);
		request.mcUpsert(headermap, para);//发布回款
		mcId = request.getMcId(headermap);
		String update_time_ = request.getUpdateTimeId(headermap);
		request.mcClaim(headermap, phone_mc_claim_proj, mcId, type_, ywId, update_time_, deptId);//认领
		request.mcApprove(headermap, phone_mc_approve_proj, mcId, "1", "");//确认
		
	}
	//认领有误后重新认领
	@Test(priority=1)
	public void mcDemoRefuse() throws ClientProtocolException, IOException {
		Map<String, String> headermap = restClient.header();//获取信息头（带cookie）
		String mcId = "";
		request.login(headermap, phone_mc_upsert);//登录
		Map<String, String> para = new RequestPara().mc_upsert_para(mcId, money_, isHuiPiao);
		request.mcUpsert(headermap, para);//发布回款
		mcId = request.getMcId(headermap);
		String update_time_ = request.getUpdateTimeId(headermap);
		request.mcClaim(headermap, phone_mc_claim_proj, mcId, type_, ywId, update_time_, deptId);//认领
		//认领有误，重新认领
		request.mcApprove(headermap, phone_mc_approve_proj, mcId, "2", "有误");//确认
		this.mcEdit(headermap, mcId);
		update_time_ = request.getUpdateTimeId(headermap);
		request.mcClaim(headermap, phone_mc_claim_proj, mcId, type_, ywId, update_time_, deptId);
		request.mcApprove(headermap, phone_mc_approve_proj, mcId, "1", "");
		
	}
	//回款认领简易修改（什么信息都不改）
	public void mcEdit (Map<String, String> headermap, String mcId) throws ClientProtocolException, IOException{
		request.login(headermap, phone_mc_upsert);
		Map<String, String> para = new RequestPara().mc_upsert_para(mcId, money_, isHuiPiao);
		request.mcUpsert(headermap, para);//发布回款
	}
}
