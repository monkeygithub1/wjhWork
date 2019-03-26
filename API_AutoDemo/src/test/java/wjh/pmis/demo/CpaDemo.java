package wjh.pmis.demo;

import java.io.IOException;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import wjh.pmis.data.DeptAndPhone;
import wjh.pmis.data.Request;
import wjh.pmis.data.RequestPara;
import wjh.pmis.restclient.RestClient;
	/*
	 * 甲方合同-安质部-一次性通过-书面
	 * 框架合同需要单独加一个参数：is_frame_=1
	 */
public class CpaDemo {
	DeptAndPhone dept_phone = new DeptAndPhone();
	String dept_name = "职能部门";
	Map<String , String> dept_para = dept_phone.getDeptInfo(dept_name);
	String phone_create = dept_phone.phone_create;
	String phone_dept = dept_para.get("phone_dept");
	String phone_fg = dept_para.get("phone_fg");
	String phone_fw = dept_phone.phone_fw;
	String phone_cpaaz = dept_phone.phone_cpaaz;
	String phone_cpawz = dept_phone.phone_cpawz;
	String phone_cpacw = dept_phone.phone_cpacw;
	String phone_boss = dept_phone.phone_boss;
	String proj_ = dept_para.get("proj_name");
	String proj_id_ = dept_para.get("proj_id_");
	String purchase_code_ = dept_para.get("purchase_code_");
	String purchase_id_ = dept_para.get("purchase_id_");
	String dept_ = dept_para.get("dept_cpa");
	
	String cpa_name_= "甲方合同-顺顺最帅-一次性通过";
	String cpa_name_refuse= "甲方合同-顺顺最帅-每个节点拒绝一次";
	String money_ = "10000";
	String nature_= "书面";
	String type_= "人力外包";
	
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
		Map<String, String> para = new RequestPara().cpa_sava_para(cpaId, cpa_name_, money_, dept_, nature_, type_, proj_, proj_id_, purchase_code_, purchase_id_);
		cpaId = request.cpaSave(headermap, para);//保存合同信息
		request.cpa_mo_Save(headermap, money_, cpaId);//保存付款计划
		request.cpa_ii_Save(headermap, money_, cpaId);//保存收票计划
		request.cpaApply(headermap, cpaId);//提交
		request.cpaSp(headermap, phone_dept, cpaId, "1", "长鹏大沙雕");//部门
		request.cpaSp(headermap, phone_fw, cpaId, "1", "长鹏大沙雕");//法务
		request.cpaSp(headermap, phone_fg, cpaId, "1", "长鹏大沙雕");//分管领导
		request.cpaSp(headermap, phone_cpaaz, cpaId, "1", "长鹏大沙雕");//安质
		request.cpaSp(headermap, phone_cpawz, cpaId, "1", "长鹏大沙雕");//物资
		request.cpaSp(headermap, phone_cpacw, cpaId, "1", "长鹏大沙雕");//财务
		request.cpaSp(headermap, phone_boss, cpaId, "1", "长鹏大沙雕");//总经理
	}
	//每个节点拒绝一次
	@Test(priority=1)
	public void cpaDemoRefuse() throws ClientProtocolException, IOException {
		Map<String, String> headermap = restClient.header();//获取信息头（带cookie）
		String cpaId = "";
		request.login(headermap, phone_create);//登录
		Map<String, String> para = new RequestPara().cpa_sava_para(cpaId, cpa_name_, money_, dept_, nature_, type_, proj_, proj_id_, purchase_code_, purchase_id_);
		cpaId = request.cpaSave(headermap, para);//保存合同信息
		request.cpa_mo_Save(headermap, money_, cpaId);//保存付款计划
		request.cpa_ii_Save(headermap, money_, cpaId);//保存收票计划
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
		Map<String, String> para = new RequestPara().cpa_sava_para(cpaId, cpa_name_refuse, money_, dept_, nature_, type_, proj_, proj_id_, purchase_code_, purchase_id_);
		cpaId = request.cpaSave(headermap, para);//保存合同信息
		request.cpaApply(headermap, cpaId);
	}
}
