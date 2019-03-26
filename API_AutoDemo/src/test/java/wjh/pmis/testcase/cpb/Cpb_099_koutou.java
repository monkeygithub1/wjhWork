package wjh.pmis.testcase.cpb;

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
	 * 乙方合同-安质部-书面
	 * 框架合同需要单独加一个参数：is_frame_=1
	 */
public class Cpb_099_koutou {
	DeptAndPhone dept_phone = new DeptAndPhone();
	String dept_name = "空间数据加工厂";
	Map<String , String> dept_para = dept_phone.getDeptInfo(dept_name);
	String phone_create = dept_phone.phone_create;
	String phone_dept = dept_para.get("phone_dept");
	String phone_fg = dept_para.get("phone_fg");
	String phone_sybzjl = dept_para.get("phone_sybzjl");
	String phone_sybdsz = dept_para.get("phone_sybdsz");
	String phone_fw = dept_phone.phone_fw;
	String phone_boss = dept_phone.phone_boss;
	String proj_ = dept_para.get("proj_name");
	String proj_id_ = dept_para.get("proj_id_");
	String dept_ = dept_para.get("dept_cpb");
	String cpb_name_= "乙方口头合同-顺顺最帅-一次性通过";
	String cpb_name_refuse= "乙方合同-顺顺最帅-每个节点拒绝一次";
	String money_ = "10001";
	String nature_= "口头";
	String is_frame_ = "0";//1为框架合同，其他值为非框架合同
	String type_= "信息化建设";
	
	RestClient restClient = new RestClient();
	Request request = new Request();
	@BeforeClass
	public void setUp() {
	}
	//一次性通过
	@Test
	public void cpbDemo() throws ClientProtocolException, IOException {
		Map<String, String> headermap = restClient.header();//获取信息头（带cookie）
		String cpbId = "";
		request.login(headermap, phone_create);//登录
		Map<String, String> para = new RequestPara().cpb_sava_para(cpbId, cpb_name_, money_, dept_, nature_, type_, proj_, proj_id_);
		if (is_frame_ == "1"){
			money_ = "0";
			para.put("money_", money_);
			para.put("is_frame_", is_frame_);
		}
		cpbId = request.cpbSave(headermap, para);//保存合同信息
		request.cpb_mi_Save(headermap, money_, cpbId);//保存收款计划
		request.cpb_io_Save(headermap, money_, cpbId);//保存开票计划
		request.cpbApply(headermap, cpbId);//提交
		request.cpbSp(headermap, phone_sybzjl, cpbId, "1", "长鹏大沙雕");//事业部总经理
		if(phone_dept != ""){
			request.cpbSp(headermap, phone_dept, cpbId, "1", "长鹏大沙雕");//部门
		}
		request.cpbSp(headermap, phone_fw, cpbId, "1", "长鹏大沙雕");//法务
	}
}
 
