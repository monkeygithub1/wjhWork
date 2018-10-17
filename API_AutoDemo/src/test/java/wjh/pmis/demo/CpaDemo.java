package wjh.pmis.demo;

import java.io.IOException;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import wjh.pmis.data.Request;
import wjh.pmis.restclient.RestClient;
	/*
	 * 甲方合同-安质部-一次性通过-书面
	 * 框架合同需要单独加一个参数：is_frame_=1
	 */
public class CpaDemo {
	String phone_create = "13910623294";//发起人 
	String phone_dept = "18502285517";//安质部
	String phone_fw = "15822223160";
	String phone_fg = "18602270056";
	String phone_cpaaz = "18502285517";
	String phone_cpawz = "18502285517";
	String phone_cpacw = "13820641813";
	String phone_boss = "13502103187";
	String cpa_name_= "顺顺最帅-一次性通过";
	String cpa_name_refuse= "顺顺最帅-每个节点拒绝一次";
	String money_ = "10000";
	String dept_ = "安全质量部#S#c4fb1b9eba214363b23ad5a792897f61";
	String nature_= "书面";
	String type_= "人力外包";
	String proj_ = "项目流水专用项目V3.0";
	String proj_id_ = "0f241d5264ad44f58798ad93c645836e";
	String purchase_code_= "CG201809068982";
	String purchase_id_= "1b446c1e08e14967932dafe3bcc0a9e6";
	
	RestClient restClient = new RestClient();
	Request request = new Request();
	@BeforeClass
	public void setUp() {
	}
	//一次性通过
	@Test
	public void cpaDemo() throws ClientProtocolException, IOException {
	Map<String, String> headermap = restClient.header();//获取信息头（带cookie）
	String cpaId = "";
	request.login(headermap, phone_create);//登录
	cpaId = request.cpaSave(headermap, cpaId, cpa_name_, money_, dept_, nature_, type_, proj_, proj_id_, purchase_code_, purchase_id_);//保存合同信息
	request.cpa_mo_Save(headermap, money_, cpaId);//保存付款计划
	request.cpa_in_Save(headermap, money_, cpaId);//保存收票计划
	request.cpaApply(headermap, cpaId);//提交
	request.cpaSp(headermap, phone_dept, cpaId, "1", "长鹏大沙雕");
	request.cpaSp(headermap, phone_fw, cpaId, "1", "长鹏大沙雕");
	request.cpaSp(headermap, phone_fg, cpaId, "1", "长鹏大沙雕");
	request.cpaSp(headermap, phone_cpaaz, cpaId, "1", "长鹏大沙雕");
	request.cpaSp(headermap, phone_cpawz, cpaId, "1", "长鹏大沙雕");
	request.cpaSp(headermap, phone_cpacw, cpaId, "1", "长鹏大沙雕");
	request.cpaSp(headermap, phone_boss, cpaId, "1", "长鹏大沙雕");
	}
	//每个节点拒绝一次
	@Test
	public void cpaDemoRefuse() throws ClientProtocolException, IOException {
	Map<String, String> headermap = restClient.header();//获取信息头（带cookie）
	String cpaId = "";
	request.login(headermap, phone_create);//登录
	cpaId = request.cpaSave(headermap, cpaId, cpa_name_refuse, money_, dept_, nature_, type_, proj_, proj_id_, purchase_code_, purchase_id_);//保存合同信息
	request.cpa_mo_Save(headermap, money_, cpaId);//保存付款计划
	request.cpa_in_Save(headermap, money_, cpaId);//保存收票计划
	request.cpaApply(headermap, cpaId);//提交
	//部门拒绝
	request.cpaSp(headermap, phone_dept, cpaId, "2", "长鹏大沙雕");
	//法务拒绝
	this.cpaEdit(headermap, cpaId, "拒绝重发-长鹏还是沙雕");
	request.cpaSp(headermap, phone_dept, cpaId, "1", "长鹏大沙雕");
	request.cpaSp(headermap, phone_fw, cpaId, "2", "长鹏大沙雕");
	//分管领导拒绝
	this.cpaEdit(headermap, cpaId, "拒绝重发-长鹏还是沙雕");
	request.cpaSp(headermap, phone_dept, cpaId, "1", "长鹏大沙雕");
	request.cpaSp(headermap, phone_fw, cpaId, "1", "长鹏大沙雕");
	request.cpaSp(headermap, phone_fg, cpaId, "2", "长鹏大沙雕");
	//安质拒绝
	this.cpaEdit(headermap, cpaId, "拒绝重发-长鹏还是沙雕");
	request.cpaSp(headermap, phone_dept, cpaId, "1", "长鹏大沙雕");
	request.cpaSp(headermap, phone_fw, cpaId, "1", "长鹏大沙雕");
	request.cpaSp(headermap, phone_fg, cpaId, "1", "长鹏大沙雕");
	request.cpaSp(headermap, phone_cpaaz, cpaId, "2", "长鹏大沙雕");
	//物资拒绝
	this.cpaEdit(headermap, cpaId, "拒绝重发-长鹏还是沙雕");
	request.cpaSp(headermap, phone_dept, cpaId, "1", "长鹏大沙雕");
	request.cpaSp(headermap, phone_fw, cpaId, "1", "长鹏大沙雕");
	request.cpaSp(headermap, phone_fg, cpaId, "1", "长鹏大沙雕");
	request.cpaSp(headermap, phone_cpaaz, cpaId, "1", "长鹏大沙雕");
	request.cpaSp(headermap, phone_cpawz, cpaId, "2", "长鹏大沙雕");
	//财务拒绝
	this.cpaEdit(headermap, cpaId, "拒绝重发-长鹏还是沙雕");
	request.cpaSp(headermap, phone_dept, cpaId, "1", "长鹏大沙雕");
	request.cpaSp(headermap, phone_fw, cpaId, "1", "长鹏大沙雕");
	request.cpaSp(headermap, phone_fg, cpaId, "1", "长鹏大沙雕");
	request.cpaSp(headermap, phone_cpaaz, cpaId, "1", "长鹏大沙雕");
	request.cpaSp(headermap, phone_cpawz, cpaId, "1", "长鹏大沙雕");
	request.cpaSp(headermap, phone_cpacw, cpaId, "2", "长鹏大沙雕");
	//总经理拒绝
	this.cpaEdit(headermap, cpaId, "拒绝重发-长鹏还是沙雕");
	request.cpaSp(headermap, phone_dept, cpaId, "1", "长鹏大沙雕");
	request.cpaSp(headermap, phone_fw, cpaId, "1", "长鹏大沙雕");
	request.cpaSp(headermap, phone_fg, cpaId, "1", "长鹏大沙雕");
	request.cpaSp(headermap, phone_cpaaz, cpaId, "1", "长鹏大沙雕");
	request.cpaSp(headermap, phone_cpawz, cpaId, "1", "长鹏大沙雕");
	request.cpaSp(headermap, phone_cpacw, cpaId, "1", "长鹏大沙雕");
	request.cpaSp(headermap, phone_boss, cpaId, "2", "长鹏大沙雕");
	//通过
	this.cpaEdit(headermap, cpaId, "拒绝重发-长鹏还是沙雕");
	request.cpaSp(headermap, phone_dept, cpaId, "1", "长鹏大沙雕");
	request.cpaSp(headermap, phone_fw, cpaId, "1", "长鹏大沙雕");
	request.cpaSp(headermap, phone_fg, cpaId, "1", "长鹏大沙雕");
	request.cpaSp(headermap, phone_cpaaz, cpaId, "1", "长鹏大沙雕");
	request.cpaSp(headermap, phone_cpawz, cpaId, "1", "长鹏大沙雕");
	request.cpaSp(headermap, phone_cpacw, cpaId, "1", "长鹏大沙雕");
	request.cpaSp(headermap, phone_boss, cpaId, "1", "长鹏大沙雕");

	}
	//甲方合同基本信息简易修改（仅修改合同名称）
	public void cpaEdit(Map<String,String> headermap, String cpaId, String cpa_name_edit) throws ClientProtocolException, IOException{
		request.login(headermap, phone_create);
		request.cpaSave(headermap, cpaId, cpa_name_edit, money_, dept_, nature_, type_, proj_, proj_id_, purchase_code_, purchase_id_);//编辑
		request.cpaApply(headermap, cpaId);
	}
}
