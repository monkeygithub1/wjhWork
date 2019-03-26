package wjh.pmis.testcase.clbx;

import java.io.IOException;
import java.util.Map;
import org.apache.http.client.ClientProtocolException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import wjh.pmis.data.DeptAndPhone;
import wjh.pmis.data.Request;
import wjh.pmis.data.RequestPara;
import wjh.pmis.restclient.RestClient;

public class Clbx_9999 {
	DeptAndPhone dept_phone = new DeptAndPhone();
	String dept_name = "职能部门";
	Map<String , String> dept_para = dept_phone.getDeptInfo(dept_name);
	String phone_create = dept_phone.phone_create;
	String phone_dept = dept_para.get("phone_dept");
	String phone_pm = dept_para.get("phone_pm");
	String phone_sybzjl = dept_para.get("phone_sybzjl");
	String phone_sybcw = dept_para.get("phone_sybcw");
	String phone_boss = dept_phone.phone_boss;
	String phone_sybdsz = dept_para.get("phone_sybdsz");
	String phone_bxType = dept_phone.phone_type_zhgl;//特殊节点
	double money = 100;
	String pmo = "";
	String typeFlag = "";//报销现金（空）、cz（冲账）
	//项目
	String proj_ = dept_para.get("proj_name");
	String proj_id_ = dept_para.get("proj_id_");
	String proj_code = dept_para.get("proj_code");
	//策划项目
//	String proj_ = "8012-天津-长鹏智障学院-策划成本专用测试";
//	String proj_code = "CH-YWZX201811001";
//	String proj_id_ = "3756350f529b4876a46f82344db32f4e";
	RestClient restClient = new RestClient();
	Request request = new Request();
	@BeforeClass
	public void setUp() {
	}
	//一次性通过
	@Test
	public void clbxDemo() throws ClientProtocolException, IOException {
		Map<String, String> headermap = restClient.header();//获取信息头（带cookie）
		request.login(headermap, phone_create);//登录
		Map<String, String> para = new RequestPara().clbx_para(headermap, proj_id_, proj_code, proj_, money, pmo, typeFlag);
		request.clbxApply(headermap, para);//发起申请
		String clbxId = request.getClbxId(headermap);//获取报销ID
		request.clbxSp(headermap, phone_pm, clbxId, "1", "顺顺制杖");//项目经理
		request.clbxSp(headermap, phone_dept, clbxId, "1", "顺顺制杖");//部门
		request.clbxSp(headermap, phone_sybzjl, clbxId, "1", "顺顺制杖");//事业部总经理
		request.clbxSp(headermap, phone_bxType, clbxId, "1", "顺顺制杖");//特殊费用（交通费用）
		request.clbxSp(headermap, phone_sybcw, clbxId, "1", "顺顺制杖");//财务初审
		request.clbxSp(headermap, phone_sybcw, clbxId, "1", "顺顺制杖");//财务核票
		request.clbxSp(headermap, phone_boss, clbxId, "1", "顺顺制杖");//总经理
		if (phone_sybdsz != ""){
			request.clbxSp(headermap, phone_sybdsz, clbxId, "1", "顺顺制杖");//事业部董事长
		}
		request.clbxSp(headermap, phone_sybcw, clbxId, "1", "顺顺制杖");//财务付款
	}
}
