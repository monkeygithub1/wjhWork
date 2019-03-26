package wjh.pmis.testcase.loan;

import java.io.IOException;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.testng.annotations.Test;

import wjh.pmis.data.DeptAndPhone;
import wjh.pmis.data.Request;
import wjh.pmis.data.RequestPara;
import wjh.pmis.restclient.RestClient;
	/*
	 * 职能部门（安质部）-评审费-安质/物资部审批
	 * 最后一个节点为财务出纳（事业部财务拒绝后财务出纳同意）
	 */
public class Loan_004 {
	DeptAndPhone dept_phone = new DeptAndPhone();
	String dept_name = "职能部门";
	Map<String , String> dept_para = dept_phone.getDeptInfo(dept_name);
	String phone_create = dept_phone.phone_create;
	String phone_dept = dept_para.get("phone_dept");
	String phone_fg = dept_para.get("phone_fg");
	String phone_sybcw = dept_para.get("phone_sybcw");
	String phone_boss = dept_phone.phone_boss;
	String phone_cwcn = dept_phone.phone_loan_cwcn;//与事业部财务任一审批通过
	String proj_name = dept_para.get("proj_name");
	String proj_id_ = dept_para.get("proj_id_");
	
	String type_ = "评审费";
	String money_ = "500";
	
	RestClient restClient = new RestClient();
	Request request = new Request();
	String phone_special = request.phone_loan(type_);
	//一次性通过
	@Test
	public void loan_004() throws ClientProtocolException, IOException {
		Map<String, String> headermap = restClient.header();//获取信息头（带cookie）
		String loanId = "";
		request.login(headermap, phone_create);//登录
		Map<String, String> para = new RequestPara().loan_para(money_, type_, proj_name, proj_id_, loanId);
		request.loanApply(headermap, para);
		loanId = request.getLoanId(headermap);//获取ID
		request.loanSp(headermap, phone_dept, loanId, "1", "顺顺大猪蹄子");//部门
		request.loanSp(headermap, phone_fg, loanId, "1", "顺顺大猪蹄子");//分管领导
		request.loanSp(headermap, phone_special, loanId, "1", "顺顺大猪蹄子");//特殊节点
		request.loanSp(headermap, phone_boss, loanId, "1", "顺顺大猪蹄子");//总经理
		request.loanSp(headermap, phone_cwcn, loanId, "1", "顺顺大猪蹄子");//事业部财务
	}
	//每个节点拒绝一次
	@Test(priority=1)
	public void loan_004_Refuse() throws ClientProtocolException, IOException {
		Map<String, String> headermap = restClient.header();//获取信息头（带cookie）
		String loanId = "";
		request.login(headermap, phone_create);//登录
		Map<String, String> para = new RequestPara().loan_para(money_, type_, proj_name, proj_id_, loanId);
		request.loanApply(headermap, para);
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
		request.loanSp(headermap, phone_cwcn, loanId, "1", "顺顺大猪蹄子");
	}
	//借款简易修改（什么信息都不改）
	public void loanEdit (Map<String, String> headermap, String loanId) throws ClientProtocolException, IOException{
		request.login(headermap, phone_create);
		Map<String, String> para = new RequestPara().loan_para(money_, type_, proj_name, proj_id_, loanId);
		request.loanUpdate(headermap, para);
	}
}
