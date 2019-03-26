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
	 * 职能部门（安质部）-差旅费-综合管理部审批
	 * 最后一个节点为事业部财务
	 */
public class Loan_099 {
	DeptAndPhone dept_phone = new DeptAndPhone();
	String dept_name = "业务部门";
	Map<String , String> dept_para = dept_phone.getDeptInfo(dept_name);
	String phone_create = dept_phone.phone_create;
	String phone_dept = dept_para.get("phone_dept");
	String phone_fg = dept_para.get("phone_fg");
	String phone_sybcw = dept_para.get("phone_sybcw");
	String phone_sybzjl = dept_para.get("phone_sybzjl");
	String phone_sybdsz = dept_para.get("phone_sybdsz");
	String phone_boss = dept_phone.phone_boss;
	String phone_cwcn = dept_phone.phone_loan_cwcn;//与事业部财务任一审批通过
	String proj_name = dept_para.get("proj_name");
	String proj_id_ = dept_para.get("proj_id_");
	
	String type_ = "办公费";
	String money_ = "666";
	
	RestClient restClient = new RestClient();
	Request request = new Request();
	String phone_special = request.phone_loan(type_);
	//一次性通过
	@Test
	public void loan_001() throws ClientProtocolException, IOException {
		Map<String, String> headermap = restClient.header();//获取信息头（带cookie）
		String loanId = "";
		request.login(headermap, phone_create);//登录
		Map<String, String> para = new RequestPara().loan_para(money_, type_, proj_name, proj_id_, loanId);
		if (type_ == "标书费"){
			request.loan_bsfApply(headermap, para);
		}else{
			request.loanApply(headermap, para);
		}
		
		loanId = request.getLoanId(headermap);//获取ID
		if(phone_dept != ""){
			request.loanSp(headermap, phone_dept, loanId, "1", "顺顺大猪蹄子");//部门
		}
		if(phone_fg != ""){
			request.loanSp(headermap, phone_fg, loanId, "1", "顺顺大猪蹄子");//分管领导
		}else if(phone_sybdsz !=""){
			request.loanSp(headermap, phone_sybzjl, loanId, "1", "顺顺大猪蹄子");
			request.loanSp(headermap, phone_sybdsz, loanId, "1", "顺顺大猪蹄子");
		}else{
			request.loanSp(headermap, phone_sybzjl, loanId, "1", "顺顺大猪蹄子");
		}
		
		request.loanSp(headermap, phone_special, loanId, "1", "顺顺大猪蹄子");//特殊节点
		request.loanSp(headermap, phone_boss, loanId, "1", "顺顺大猪蹄子");//总经理
		request.loanSp(headermap, phone_sybcw, loanId, "1", "顺顺大猪蹄子");//事业部财务
	}
}