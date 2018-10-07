package com.qa.test.proj;

import java.io.IOException;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.data.Request;
import com.qa.restclient.RestClient;

/**
 * 业务部门（产品研发部）
 * R
 * 一次性通过&每个节点拒绝后重发
 */
public class Proj_004 {
	String phone_create = "13910623294";//发起 
	String phone_sybzjl = "13512968527";//事业部总经理
	String phone_jsyjy = "13910623294";//技术研究院负责人
	String phone_yyzj = "13910623294";//运营总监（哪个权限？）
	String phone_boss = "13502103187";//总经理
	String proj_name_= "顺顺最帅-业务-R-一次性通过";
	String proj_name_refuse= "顺顺最帅-业务-R--每个节点拒绝一次";
	String proj_mgr_ = "武军豪";
	String proj_mgr_id_ = "3b8f52b4b8f54bd597557361efcb8736";
	String dept_ = "产品研发部-c7c2d0bac07a41fabdd22f82dc3501be";
	String proj_mgr_type_ = "R";
	String ys_kphte= "2000";
	RestClient restClient = new RestClient();
	Request request = new Request();
	
	@BeforeClass
	public void setUp() {
	}
	//一次性通过
	@Test
	public void proj() throws ClientProtocolException, IOException {
		Map<String, String> headermap = restClient.header();//获取信息头（带cookie）
		String projId = "";
		request.login(headermap, phone_create);//登录
		projId = request.projSave(headermap, projId, proj_name_, proj_mgr_, proj_mgr_id_, dept_, proj_mgr_type_);//新建项目信息
		request.projBudge(headermap, projId, ys_kphte);//新建预算信息
		request.projUpload(projId, headermap);//上传附件
		request.projApply(projId, headermap);//提交
		request.projSp(phone_sybzjl, headermap, projId, "1", "顺顺大猪蹄子");//事业部总经理审批
		request.projSp(phone_jsyjy, headermap, projId, "1", "顺顺大猪蹄子");//技术研究院负责人审批
		request.projSp(phone_yyzj, headermap, projId, "1", "顺顺大猪蹄子");//运营总监审批
		request.projSp(phone_boss, headermap, projId, "1", "顺顺大猪蹄子");//总经理审批
  }
	//每个节点拒绝重发
	@Test(priority=1)
	public void projRefuse() throws ClientProtocolException, IOException{
		Map<String, String> headermap = restClient.header();
		String projId = "";
		//发起
		request.login(headermap, phone_create);//登录
		projId = request.projSave(headermap, projId, proj_name_refuse, proj_mgr_, proj_mgr_id_, dept_, proj_mgr_type_);//新建项目信息
		request.projBudge(headermap, projId, ys_kphte);//新建预算信息
		request.projUpload(projId, headermap);//上传附件
		request.projApply(projId, headermap);//提交
		//事业部总经理拒绝
		request.projSp(phone_sybzjl, headermap, projId, "2", "顺顺不是大猪蹄子");
		//技术研究院负责人拒绝
		this.projEdit(headermap, "拒绝重发", projId);
		request.projSp(phone_sybzjl, headermap, projId, "1", "顺顺大猪蹄子");
		request.projSp(phone_jsyjy, headermap, projId, "2", "顺顺不是大猪蹄子");
		//运营总监拒绝
		this.projEdit(headermap, "拒绝重发", projId);
		request.projSp(phone_sybzjl, headermap, projId, "1", "顺顺大猪蹄子");
		request.projSp(phone_jsyjy, headermap, projId, "1", "顺顺大猪蹄子");
		request.projSp(phone_yyzj, headermap, projId, "2", "顺顺不是大猪蹄子");
		//总经理拒绝
		this.projEdit(headermap, "拒绝重发", projId);
		request.projSp(phone_sybzjl, headermap, projId, "1", "顺顺大猪蹄子");
		request.projSp(phone_jsyjy, headermap, projId, "1", "顺顺大猪蹄子");
		request.projSp(phone_yyzj, headermap, projId, "1", "顺顺大猪蹄子");
		request.projSp(phone_boss, headermap, projId, "2", "顺顺大猪蹄子");
		//通过
		this.projEdit(headermap, "拒绝重发", projId);
		request.projSp(phone_sybzjl, headermap, projId, "1", "顺顺大猪蹄子");
		request.projSp(phone_jsyjy, headermap, projId, "1", "顺顺大猪蹄子");
		request.projSp(phone_yyzj, headermap, projId, "1", "顺顺大猪蹄子");
		request.projSp(phone_boss, headermap, projId, "1", "顺顺大猪蹄子");
	}
	//简易版修改重发
	public void projEdit(Map<String, String> headermap, String proj_name_edit, String projId) throws ClientProtocolException, IOException{
		request.login(headermap, phone_create);
		request.projSave(headermap, proj_name_edit, projId, proj_mgr_, proj_mgr_id_, dept_, proj_mgr_type_);//编辑项目信息
		request.projApply(projId, headermap);//提交
	}
}