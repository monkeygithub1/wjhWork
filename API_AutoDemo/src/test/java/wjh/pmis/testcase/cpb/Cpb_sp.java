package wjh.pmis.testcase.cpb;

import java.io.IOException;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import wjh.pmis.data.DeptAndPhone;
import wjh.pmis.data.Request;
import wjh.pmis.restclient.RestClient;
	/*
	 * 乙方合同-安质部-书面
	 * 框架合同需要单独加一个参数：is_frame_=1
	 */
public class Cpb_sp {
	DeptAndPhone dept_phone = new DeptAndPhone();
	String dept_name = "职能部门";
	Map<String , String> dept_para = dept_phone.getDeptInfo(dept_name);
	String phone_create = dept_phone.phone_create;
	String phone_dept = dept_para.get("phone_dept");
	String phone_fg = dept_para.get("phone_fg");
	String phone_sybzjl = dept_para.get("phone_sybzjl");
	String phone_fw = dept_phone.phone_fw;
	String phone_boss = dept_phone.phone_boss;
	String proj_ = dept_para.get("proj_name");
	String proj_id_ = dept_para.get("proj_id_");
	String dept_ = dept_para.get("dept_cpb");
	
	
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
		cpbId = request.getCpbId(headermap);
		request.cpbSp(headermap, phone_dept, cpbId, "1", "长鹏大沙雕");//部门
//		request.cpbSp(headermap, phone_fw, cpbId, "1", "长鹏大沙雕");//法务
		request.cpbSp(headermap, phone_sybzjl, cpbId, "1", "长鹏大沙雕");//事业部总经理
		request.cpbSp(headermap, phone_fw, cpbId, "1", "长鹏大沙雕");//法务
//		request.cpbSp(headermap, phone_boss, cpbId, "1", "长鹏大沙雕");//总经理
	}
}
 
