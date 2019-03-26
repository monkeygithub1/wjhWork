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
	 * 职能部门（安质部）-增值税专用发票
	 * 开票类型：增值税专用发票、普通发票
	 */
public class FinIoDemo {
	DeptAndPhone dept_phone = new DeptAndPhone();
	String dept_name = "职能部门";
	Map<String , String> dept_para = dept_phone.getDeptInfo(dept_name);
	String phone_create = dept_phone.phone_create;
	String phone_sybzjl = dept_para.get("phone_sybzjl");
	String phone_sybcw = dept_para.get("phone_sybcw");
	String phone_cwmgr = dept_phone.phone_cwmgr;
	String cpbId = dept_para.get("cpbId");
	
	String type_ = "增值税专用发票";
	String money_ = "100";
	
	
	RestClient restClient = new RestClient();
	Request request = new Request();
	//一次性通过
	@Test
	public void finIoDemo() throws ClientProtocolException, IOException {
		Map<String, String> headermap = restClient.header();//获取信息头（带cookie）
		String finIoId = "";
		request.login(headermap, phone_create);//登录
		Map<String, String> para = new RequestPara().finIo_para(finIoId, money_, type_, cpbId);
		request.FinIoApply(headermap, para);
		finIoId = request.getFinIoId(headermap);//获取ID
		request.finIoSp(headermap, phone_sybzjl, finIoId, "1", "顺顺ZZ", "");//事业部总经理
		request.finIoSp(headermap, phone_cwmgr, finIoId, "1", "顺顺ZZ", "");//财务
		request.finIoSp(headermap, phone_sybcw, finIoId, "1", "顺顺ZZ", "123123123");//事业部财务
		
	}
	//每个节点拒绝一次
	@Test(priority=1)
	public void finIoDemoRefuse() throws ClientProtocolException, IOException {
		Map<String, String> headermap = restClient.header();//获取信息头（带cookie）
		String finIoId = "";
		request.login(headermap, phone_create);//登录
		Map<String, String> para = new RequestPara().finIo_para(finIoId, money_, type_, cpbId);
		request.FinIoApply(headermap, para);
		finIoId = request.getFinIoId(headermap);//获取ID
		//事业部总经理拒绝
		request.finIoSp(headermap, phone_sybzjl, finIoId, "2", "顺顺ZZ", "");
		//财务拒绝
		this.finIoEdit(headermap, finIoId);
		request.finIoSp(headermap, phone_sybzjl, finIoId, "1", "顺顺ZZ", "");
		request.finIoSp(headermap, phone_cwmgr, finIoId, "2", "顺顺ZZ", "");
		//事业部财务拒绝
		this.finIoEdit(headermap, finIoId);
		request.finIoSp(headermap, phone_sybzjl, finIoId, "1", "顺顺ZZ", "");
		request.finIoSp(headermap, phone_cwmgr, finIoId, "1", "顺顺ZZ", "");
		request.finIoSp(headermap, phone_sybcw, finIoId, "2", "顺顺ZZ", "123123123");
		//通过
		this.finIoEdit(headermap, finIoId);
		request.finIoSp(headermap, phone_sybzjl, finIoId, "1", "顺顺ZZ", "");
		request.finIoSp(headermap, phone_cwmgr, finIoId, "1", "顺顺ZZ", "");
		request.finIoSp(headermap, phone_sybcw, finIoId, "1", "顺顺ZZ", "123123123");
		
	}
	//开票简易修改（什么信息都不改）
	public void finIoEdit (Map<String, String> headermap, String finIoId) throws ClientProtocolException, IOException{
		request.login(headermap, phone_create);
		Map<String, String> para = new RequestPara().finIo_para(finIoId, money_, type_, cpbId);
		request.FinIoApply(headermap, para);
	}
}
