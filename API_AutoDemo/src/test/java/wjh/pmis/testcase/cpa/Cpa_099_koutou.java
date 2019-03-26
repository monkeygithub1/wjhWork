package wjh.pmis.testcase.cpa;

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
public class Cpa_099_koutou {
	DeptAndPhone dept_phone = new DeptAndPhone();
	String dept_name = "空间数据加工厂";
	Map<String , String> dept_para = dept_phone.getDeptInfo(dept_name);
	String phone_create = dept_phone.phone_create;
	String phone_dept = dept_para.get("phone_dept");
	String phone_fg = dept_para.get("phone_fg");
	String phone_sybzjl = dept_para.get("phone_sybzjl");
	String phone_sybdsz = dept_para.get("phone_sybdsz");
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
	
	String cpa_name_= "甲方口头合同-顺顺最帅-一次性通过";
	String cpa_name_refuse= "甲方合同-顺顺最帅-每个节点拒绝一次";
//	String money_ = "10000";
	String nature_= "口头";
	String is_frame_ = "0";//1为框架合同，其他值为非框架合同
	String money_ = "1001";
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
		if (is_frame_ == "1"){
			money_ = "0";
			para.put("money_", money_);
			para.put("is_frame_", is_frame_);
		}
		cpaId = request.cpaSave(headermap, para);//保存合同信息
		System.out.println("cpaId = " + cpaId);
		request.cpa_mo_Save(headermap, money_, cpaId);//保存付款计划
		request.cpa_ii_Save(headermap, money_, cpaId);//保存收票计划
		request.cpaApply(headermap, cpaId);//提交
		if(phone_dept != ""){
			request.cpaSp(headermap, phone_dept, cpaId, "1", "长鹏大沙雕");//部门
		}
		request.cpaSp(headermap, phone_sybzjl, cpaId, "1", "长鹏大沙雕");
		request.cpaSp(headermap, phone_fw, cpaId, "1", "长鹏大沙雕");//法务
	}
}
