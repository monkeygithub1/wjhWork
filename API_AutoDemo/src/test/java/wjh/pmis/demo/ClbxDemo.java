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

public class ClbxDemo {
	DeptAndPhone dept_phone = new DeptAndPhone();
	String dept_name = "职能部门";
	Map<String , String> dept_para = dept_phone.getDeptInfo(dept_name);
	String phone_create = dept_phone.phone_create;
	String phone_dept = dept_para.get("phone_dept");
	String phone_pm = dept_para.get("phone_pm");
	String phone_sybzjl = dept_para.get("phone_sybzjl");
	String phone_sybcw = dept_para.get("phone_sybcw");
	String proj_ = dept_para.get("proj_name");
	String proj_id_ = dept_para.get("proj_id_");
	String proj_code = dept_para.get("proj_code");
	String phone_boss = dept_phone.phone_boss;
	
	String phone_bxType = dept_phone.phone_type_zhgl;//特殊节点
	double money = 500;
	String pmo = "";
	String typeFlag = "";//报销现金（空）、cz（冲账）
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
		request.clbxSp(headermap, phone_sybcw, clbxId, "1", "顺顺制杖");//财务付款
	}
	//每个节点拒绝一次
	@Test(priority=1)
	public void clbxDemoRefuse() throws ClientProtocolException, IOException, InterruptedException {
		Map<String, String> headermap = restClient.header();//获取信息头（带cookie）
		request.login(headermap, phone_create);//登录
		Map<String, String> para = new RequestPara().clbx_para(headermap, proj_id_, proj_code, proj_, money, pmo, typeFlag);
		request.clbxApply(headermap, para);//发起申请
		String clbxId = request.getClbxId(headermap);//获取报销ID
		//项目经理拒绝
		request.clbxSp(headermap, phone_pm, clbxId, "2", "顺顺制杖");//项目经理
		//部门拒绝
		this.clbxEdit(headermap, clbxId);
		request.clbxSp(headermap, phone_pm, clbxId, "1", "顺顺制杖");//项目经理
		request.clbxSp(headermap, phone_dept, clbxId, "2", "顺顺制杖");//部门
		//事业部总经理
		Thread.sleep(500);
		this.clbxEdit(headermap, clbxId);
		request.clbxSp(headermap, phone_pm, clbxId, "1", "顺顺制杖");//项目经理
		request.clbxSp(headermap, phone_dept, clbxId, "1", "顺顺制杖");//部门
		request.clbxSp(headermap, phone_sybzjl, clbxId, "2", "顺顺制杖");//事业部总经理
		//特殊费用（交通费用）
		this.clbxEdit(headermap, clbxId);
		request.clbxSp(headermap, phone_pm, clbxId, "1", "顺顺制杖");//项目经理
		request.clbxSp(headermap, phone_dept, clbxId, "1", "顺顺制杖");//部门
		request.clbxSp(headermap, phone_sybzjl, clbxId, "1", "顺顺制杖");//事业部总经理
		request.clbxSp(headermap, phone_bxType, clbxId, "2", "顺顺制杖");//特殊费用（交通费用）
		//财务初审
		this.clbxEdit(headermap, clbxId);
		request.clbxSp(headermap, phone_pm, clbxId, "1", "顺顺制杖");//项目经理
		request.clbxSp(headermap, phone_dept, clbxId, "1", "顺顺制杖");//部门
		request.clbxSp(headermap, phone_sybzjl, clbxId, "1", "顺顺制杖");//事业部总经理
		request.clbxSp(headermap, phone_bxType, clbxId, "1", "顺顺制杖");//特殊费用（交通费用）
		request.clbxSp(headermap, phone_sybcw, clbxId, "2", "顺顺制杖");//财务初审
		//财务核票
		this.clbxEdit(headermap, clbxId);
		request.clbxSp(headermap, phone_pm, clbxId, "1", "顺顺制杖");//项目经理
		request.clbxSp(headermap, phone_dept, clbxId, "1", "顺顺制杖");//部门
		request.clbxSp(headermap, phone_sybzjl, clbxId, "1", "顺顺制杖");//事业部总经理
		request.clbxSp(headermap, phone_bxType, clbxId, "1", "顺顺制杖");//特殊费用（交通费用）
		request.clbxSp(headermap, phone_sybcw, clbxId, "1", "顺顺制杖");//财务初审
		request.clbxSp(headermap, phone_sybcw, clbxId, "2", "顺顺制杖");//财务核票
		//总经理
		this.clbxEdit(headermap, clbxId);
		request.clbxSp(headermap, phone_pm, clbxId, "1", "顺顺制杖");//项目经理
		request.clbxSp(headermap, phone_dept, clbxId, "1", "顺顺制杖");//部门
		request.clbxSp(headermap, phone_sybzjl, clbxId, "1", "顺顺制杖");//事业部总经理
		request.clbxSp(headermap, phone_bxType, clbxId, "1", "顺顺制杖");//特殊费用（交通费用）
		request.clbxSp(headermap, phone_sybcw, clbxId, "1", "顺顺制杖");//财务初审
		request.clbxSp(headermap, phone_sybcw, clbxId, "1", "顺顺制杖");//财务核票
		request.clbxSp(headermap, phone_boss, clbxId, "2", "顺顺制杖");//总经理
		//财务拒绝
		this.clbxEdit(headermap, clbxId);
		request.clbxSp(headermap, phone_pm, clbxId, "1", "顺顺制杖");//项目经理
		request.clbxSp(headermap, phone_dept, clbxId, "1", "顺顺制杖");//部门
		request.clbxSp(headermap, phone_sybzjl, clbxId, "1", "顺顺制杖");//事业部总经理
		request.clbxSp(headermap, phone_bxType, clbxId, "1", "顺顺制杖");//特殊费用（交通费用）
		request.clbxSp(headermap, phone_sybcw, clbxId, "1", "顺顺制杖");//财务初审
		request.clbxSp(headermap, phone_sybcw, clbxId, "1", "顺顺制杖");//财务核票
		request.clbxSp(headermap, phone_boss, clbxId, "1", "顺顺制杖");//总经理
		request.clbxSp(headermap, phone_sybcw, clbxId, "2", "顺顺制杖");//财务付款
		//财务付款
		this.clbxEdit(headermap, clbxId);
		request.clbxSp(headermap, phone_pm, clbxId, "1", "顺顺制杖");//项目经理
		request.clbxSp(headermap, phone_dept, clbxId, "1", "顺顺制杖");//部门
		request.clbxSp(headermap, phone_sybzjl, clbxId, "1", "顺顺制杖");//事业部总经理
		request.clbxSp(headermap, phone_bxType, clbxId, "1", "顺顺制杖");//特殊费用（交通费用）
		request.clbxSp(headermap, phone_sybcw, clbxId, "1", "顺顺制杖");//财务初审
		request.clbxSp(headermap, phone_sybcw, clbxId, "1", "顺顺制杖");//财务核票
		request.clbxSp(headermap, phone_boss, clbxId, "1", "顺顺制杖");//总经理
		request.clbxSp(headermap, phone_sybcw, clbxId, "1", "顺顺制杖");//财务付款
	}
	//差旅报销简易修改（什么信息都不改）
	public void clbxEdit (Map<String, String> headermap, String clbxId) throws ClientProtocolException, IOException{
		request.login(headermap, phone_create);
		Map<String, String> para = new RequestPara().clbx_update_para(headermap, proj_id_, proj_code, proj_, money, pmo, typeFlag, clbxId);
		request.clbxUpdate(headermap, para);
	}
}
