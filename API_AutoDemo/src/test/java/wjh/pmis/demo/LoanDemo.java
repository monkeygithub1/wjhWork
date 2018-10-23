package wjh.pmis.demo;

import java.io.IOException;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.testng.annotations.Test;

import wjh.pmis.data.Request;
import wjh.pmis.restclient.RestClient;
	/*
	 * 职能部门（安质部）-差旅费-综合管理部审批
	 * 综合管理：差旅费、办公费
	 * 安质/物资：采购费、评审费、项目奖
	 * 商务部：标书费、中标服务费、投标保证金
	 * 营销：招待费
	 * 人资：实习生工资
	 */
public class LoanDemo {
	String phone_create = "13910623294";//发起人 
	String phone_dept = "18502285517";//安质部
	String phone_fg = "18602270056";
	String phone_boss = "13502103187";
	String phone_sybcw = "15822316986";
	String phone_cwcn = "18920336967";//与事业部财务任一审批通过
	
	String type_ = "差旅费";
	String money_ = "500";
	String proj_name = "项目流水专用项目V3.0";
	String proj_id_ = "0f241d5264ad44f58798ad93c645836e";
	
	RestClient restClient = new RestClient();
	Request request = new Request();
	String phone_special = request.phone_loan(type_);
	//一次性通过
	@Test
	public void loanDemo() throws ClientProtocolException, IOException {
		Map<String, String> headermap = restClient.header();//获取信息头（带cookie）
		String loanId = "";
		request.login(headermap, phone_create);//登录
		request.loanApply(headermap, money_, type_, proj_name, proj_id_, loanId);
		loanId = request.getLoanId(headermap);//获取ID
		request.loanSp(headermap, phone_dept, loanId, "1", "顺顺大猪蹄子");//部门
		request.loanSp(headermap, phone_fg, loanId, "1", "顺顺大猪蹄子");//分管领导
		request.loanSp(headermap, phone_special, loanId, "1", "顺顺大猪蹄子");//特殊节点
		request.loanSp(headermap, phone_boss, loanId, "1", "顺顺大猪蹄子");//总经理
		request.loanSp(headermap, phone_sybcw, loanId, "1", "顺顺大猪蹄子");//事业部财务
	}
	//每个节点拒绝一次
	@Test(priority=1)
	public void loanDemoRefuse() throws ClientProtocolException, IOException {
		Map<String, String> headermap = restClient.header();//获取信息头（带cookie）
		String loanId = "";
		request.login(headermap, phone_create);//登录
		request.loanApply(headermap, money_, type_, proj_name, proj_id_, loanId);
		loanId = request.getLoanId(headermap);//获取ID
		//部门拒绝
		request.loanSp(headermap, phone_dept, loanId, "2", "顺顺大猪蹄子");
		//分管领导拒绝
		this.loanEdit(headermap, loanId);
		request.loanSp(headermap, phone_dept, loanId, "1", "顺顺大猪蹄子");
		request.loanSp(headermap, phone_fg, loanId, "2", "顺顺大猪蹄子");
		//特殊节点拒绝
		this.loanEdit(headermap, loanId);
		request.loanSp(headermap, phone_dept, loanId, "1", "顺顺大猪蹄子");
		request.loanSp(headermap, phone_fg, loanId, "1", "顺顺大猪蹄子");
		request.loanSp(headermap, phone_special, loanId, "2", "顺顺大猪蹄子");
		//总经理拒绝
		this.loanEdit(headermap, loanId);
		request.loanSp(headermap, phone_dept, loanId, "1", "顺顺大猪蹄子");
		request.loanSp(headermap, phone_fg, loanId, "1", "顺顺大猪蹄子");
		request.loanSp(headermap, phone_special, loanId, "1", "顺顺大猪蹄子");
		request.loanSp(headermap, phone_boss, loanId, "2", "顺顺大猪蹄子");
		//事业部财务拒绝
		this.loanEdit(headermap, loanId);
		request.loanSp(headermap, phone_dept, loanId, "1", "顺顺大猪蹄子");
		request.loanSp(headermap, phone_fg, loanId, "1", "顺顺大猪蹄子");
		request.loanSp(headermap, phone_special, loanId, "1", "顺顺大猪蹄子");
		request.loanSp(headermap, phone_boss, loanId, "1", "顺顺大猪蹄子");
		request.loanSp(headermap, phone_sybcw, loanId, "2", "顺顺大猪蹄子");
		//通过
		this.loanEdit(headermap, loanId);
		request.loanSp(headermap, phone_dept, loanId, "1", "顺顺大猪蹄子");
		request.loanSp(headermap, phone_fg, loanId, "1", "顺顺大猪蹄子");
		request.loanSp(headermap, phone_special, loanId, "1", "顺顺大猪蹄子");
		request.loanSp(headermap, phone_boss, loanId, "1", "顺顺大猪蹄子");
		request.loanSp(headermap, phone_sybcw, loanId, "1", "顺顺大猪蹄子");
	}
	//借款简易修改（什么信息都不改）
	public void loanEdit (Map<String, String> headermap, String loanId) throws ClientProtocolException, IOException{
		request.login(headermap, phone_create);
		request.loanUpdate(headermap, money_, type_, proj_name, proj_id_, loanId);
	}
}
