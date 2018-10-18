package wjh.pmis.demo;

import java.io.IOException;
import java.util.Map;
import org.apache.http.client.ClientProtocolException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import wjh.pmis.data.Request;
import wjh.pmis.restclient.RestClient;

public class ClbxDemo {
	String phone_create = "13910623294";//发起人 
	String phone_dept = "18502285517";//安质部
	String phone_pm = "13910623294";
	String phone_sybzjl = "13502103187";
	String phone_bxType = "15122681282";//特殊节点
	String phone_sybcw = "15822316986";
	String phone_boss = "13502103187";
	double money = 500;
	String pmo = "";
	String typeFlag = "";
	String bxrIds = "3b8f52b4b8f54bd597557361efcb8736";
	String bxrNames = "武军豪";
	String proj_ = "项目流水专用项目V3.0";
	String proj_code = "SC-YWZX2018018";
	String proj_id_ = "0f241d5264ad44f58798ad93c645836e";
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
		request.clbxApply(headermap, proj_id_, proj_code, proj_, money, bxrIds, bxrNames, pmo, typeFlag);//发起申请
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
	public void clbxDemoRefuse() throws ClientProtocolException, IOException {
		Map<String, String> headermap = restClient.header();//获取信息头（带cookie）
		request.login(headermap, phone_create);//登录
		request.clbxApply(headermap, proj_id_, proj_code, proj_, money, bxrIds, bxrNames, pmo, typeFlag);//发起申请
		String clbxId = request.getClbxId(headermap);//获取报销ID
		//项目经理拒绝
		request.clbxSp(headermap, phone_pm, clbxId, "2", "顺顺制杖");//项目经理
		//部门拒绝
		this.clbxEdit(headermap, clbxId);
		request.clbxSp(headermap, phone_pm, clbxId, "1", "顺顺制杖");//项目经理
		request.clbxSp(headermap, phone_dept, clbxId, "2", "顺顺制杖");//部门
		//事业部总经理
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
		request.clbxUpdate(headermap, proj_id_, proj_code, proj_, money, bxrIds, bxrNames, pmo, typeFlag, clbxId);
	}
}
