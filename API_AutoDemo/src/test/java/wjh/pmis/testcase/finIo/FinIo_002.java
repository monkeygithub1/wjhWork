package wjh.pmis.testcase.finIo;

import java.io.IOException;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.testng.annotations.Test;

import wjh.pmis.data.Request;
import wjh.pmis.restclient.RestClient;
	/*
	 * 职能部门（安质部）-增值税专用发票
	 * 开票类型：增值税专用发票、普通发票
	 */
public class FinIo_002 {
	String phone_create = "13910623294";//发起人 
	String phone_sybzjl = "13502103187";
	String phone_cwmgr = "13820641813";
	String phone_sybcw = "15822316986";
	
	String type_ = "普通发票";
	String money_ = "500";
	String cpbId = "9d8a6dee0e5841999eb9617d9459efd9";
	
	RestClient restClient = new RestClient();
	Request request = new Request();
	//一次性通过
	@Test
	public void finIo_002() throws ClientProtocolException, IOException {
		Map<String, String> headermap = restClient.header();//获取信息头（带cookie）
		String finIoId = "";
		request.login(headermap, phone_create);//登录
		request.FinIoApply(headermap, finIoId, money_, type_, cpbId);
		finIoId = request.getFinIoId(headermap);//获取ID
		request.finIoSp(headermap, phone_sybzjl, finIoId, "1", "顺顺ZZ", "");//事业部总经理
		request.finIoSp(headermap, phone_cwmgr, finIoId, "1", "顺顺ZZ", "");//财务
		request.finIoSp(headermap, phone_sybcw, finIoId, "1", "顺顺ZZ", "123123123");//事业部财务
		
	}
	//每个节点拒绝一次
	@Test(priority=1)
	public void finIo_002_Refuse() throws ClientProtocolException, IOException {
		Map<String, String> headermap = restClient.header();//获取信息头（带cookie）
		String finIoId = "";
		request.login(headermap, phone_create);//登录
		request.FinIoApply(headermap, finIoId, money_, type_, cpbId);
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
	//采购付款简易修改（什么信息都不改）
	public void finIoEdit (Map<String, String> headermap, String finIoId) throws ClientProtocolException, IOException{
		request.login(headermap, phone_create);
		request.FinIoApply(headermap, finIoId, money_, type_, cpbId);
	}
}
