package wjh.pmis.testcase.finIo;

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
public class FinIo_099 {
	DeptAndPhone dept_phone = new DeptAndPhone();
	String dept_name = "空间数据加工厂";
	Map<String , String> dept_para = dept_phone.getDeptInfo(dept_name);
	String phone_create = dept_phone.phone_create;
	String phone_sybzjl = dept_para.get("phone_sybzjl");
	String phone_sybcw = dept_para.get("phone_sybcw");
	String phone_cwmgr = dept_phone.phone_cwmgr;
//	String cpbId = dept_para.get("cpbId");
	String cpbId = "a9acb458e2cc47cfb6cd47047b21316b";
	String type_ = "增值税专用发票";
	String money_ = "250";
	
	RestClient restClient = new RestClient();
	Request request = new Request();
	//一次性通过
	@Test
	public void finIo_001() throws ClientProtocolException, IOException {
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
}
