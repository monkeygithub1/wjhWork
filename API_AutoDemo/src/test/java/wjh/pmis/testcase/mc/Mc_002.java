package wjh.pmis.testcase.mc;

import java.io.IOException;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.testng.annotations.Test;

import wjh.pmis.data.Request;
import wjh.pmis.restclient.RestClient;
	/*
	 * 是否承况汇票isHuiPiao : 0-否, 1-是
	 * 回款类别type_: proj-项目回款, bzj-保证金回款
	 */
public class Mc_002 {
	String phone_mc_upsert = "18920336967";//发布人 
	String phone_mc_claim_proj = "13910623294";
	String phone_mc_approve_proj = "15620699121";
	
	String isHuiPiao = "1";
	String money_ = "500";
	String type_ = "proj";
	String ywId = "9d8a6dee0e5841999eb9617d9459efd9";
	String deptId = "c4fb1b9eba214363b23ad5a792897f61";
	
	RestClient restClient = new RestClient();
	Request request = new Request();
	//一次性通过
	@Test
	public void mc_002() throws ClientProtocolException, IOException {
		Map<String, String> headermap = restClient.header();//获取信息头（带cookie）
		String mcId = "";
		request.login(headermap, phone_mc_upsert);//登录
		request.mcUpsert(headermap, mcId, money_, isHuiPiao);//发布回款
		mcId = request.getMcId(headermap);
		String update_time_ = request.getUpdateTimeId(headermap);
		request.mcClaim(headermap, phone_mc_claim_proj, mcId, type_, ywId, update_time_, deptId);//认领
		request.mcApprove(headermap, phone_mc_approve_proj, mcId, "1", "");//确认
		
	}
	//认领有误后重新认领
	@Test(priority=1)
	public void mc_002_Refuse() throws ClientProtocolException, IOException {
		Map<String, String> headermap = restClient.header();//获取信息头（带cookie）
		String mcId = "";
		request.login(headermap, phone_mc_upsert);//登录
		request.mcUpsert(headermap, mcId, money_, isHuiPiao);//发布回款
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
		request.mcUpsert(headermap, mcId, money_, isHuiPiao);
	}
}
