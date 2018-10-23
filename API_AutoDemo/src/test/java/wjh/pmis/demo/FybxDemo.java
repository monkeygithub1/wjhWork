package wjh.pmis.demo;

import java.io.IOException;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import wjh.pmis.data.Request;
import wjh.pmis.restclient.RestClient;
	/*
	 * 费用报销-安质部-办公其他
	 * 特殊节点：待优化，由办公类型自动获取
	 * 综合管理：住宿费、宿舍杂费、宿舍房租、市内交通费、油费、租车费、办公房租、新闻、图书、快递；耗材、办公用品；通讯费、办公打印、办公维修、办公其他
	 * 安质/物资：培训相关费用、会议费、评审费、项目打印、外业补助、项目奖
	 * 商务部：标书费、中标服务费
	 * 营销：招待费
	 * 人资：实习生工资
	 * 无特殊节点：加班餐费
	 */
public class FybxDemo {
	String phone_create = "13910623294";//发起人 
	String phone_dept = "18502285517";//安质部
	String phone_pm = "13910623294";
	String phone_sybzjl = "13502103187";
	String phone_bxType = "15122681282";//特殊节点
	String phone_sybcw = "15822316986";
	String phone_boss = "13502103187";
	String money = "500";
	String bxType = "办公其他";//特殊类型
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
	public void fybxDemo() throws ClientProtocolException, IOException {
		Map<String, String> headermap = restClient.header();//获取信息头（带cookie）
		request.login(headermap, phone_create);//登录
		request.fybxApply(headermap, proj_id_, proj_code, proj_, money, bxType, bxrIds, bxrNames, pmo, typeFlag);//发起申请
		String fybxId = request.getFybxId(headermap);//获取报销ID
		request.fybxSp(headermap, phone_pm, fybxId, "1", "顺顺制杖");//项目经理
		request.fybxSp(headermap, phone_dept, fybxId, "1", "顺顺制杖");//部门
		request.fybxSp(headermap, phone_sybzjl, fybxId, "1", "顺顺制杖");//事业部经理
		request.fybxSp(headermap, phone_bxType, fybxId, "1", "顺顺制杖");//特殊费用
		request.fybxSp(headermap, phone_sybcw, fybxId, "1", "顺顺制杖");//财务初审
		request.fybxSp(headermap, phone_sybcw, fybxId, "1", "顺顺制杖");//财务核票
		request.fybxSp(headermap, phone_boss, fybxId, "1", "顺顺制杖");//总经理
		request.fybxSp(headermap, phone_sybcw, fybxId, "1", "顺顺制杖");//财务付款
	}
	//每个节点拒绝一次
	@Test(priority=1)
	public void fybxDemoRefuse() throws ClientProtocolException, IOException, InterruptedException {
		Map<String, String> headermap = restClient.header();//获取信息头（带cookie）
		request.login(headermap, phone_create);//登录
		request.fybxApply(headermap, proj_id_, proj_code, proj_, money, bxType, bxrIds, bxrNames, pmo, typeFlag);//发起申请
		String fybxId = request.getFybxId(headermap);//获取报销ID
		//项目经理拒绝
		request.fybxSp(headermap, phone_pm, fybxId, "2", "顺顺制杖");//项目经理
		//部门拒绝
		this.fybxEdit(headermap, fybxId);
		request.fybxSp(headermap, phone_pm, fybxId, "1", "顺顺制杖");//项目经理
		request.fybxSp(headermap, phone_dept, fybxId, "2", "顺顺制杖");//部门
		//事业部总经理拒绝
		Thread.sleep(500);
		this.fybxEdit(headermap, fybxId);
		request.fybxSp(headermap, phone_pm, fybxId, "1", "顺顺制杖");//项目经理
		request.fybxSp(headermap, phone_dept, fybxId, "1", "顺顺制杖");//部门
		request.fybxSp(headermap, phone_sybzjl, fybxId, "2", "顺顺制杖");//事业部经理
		//特殊费用节点拒绝
		this.fybxEdit(headermap, fybxId);
		request.fybxSp(headermap, phone_pm, fybxId, "1", "顺顺制杖");//项目经理
		request.fybxSp(headermap, phone_dept, fybxId, "1", "顺顺制杖");//部门
		request.fybxSp(headermap, phone_sybzjl, fybxId, "1", "顺顺制杖");//事业部经理
		request.fybxSp(headermap, phone_bxType, fybxId, "2", "顺顺制杖");//特殊费用
		//财务初审拒绝
		this.fybxEdit(headermap, fybxId);
		request.fybxSp(headermap, phone_pm, fybxId, "1", "顺顺制杖");//项目经理
		request.fybxSp(headermap, phone_dept, fybxId, "1", "顺顺制杖");//部门
		request.fybxSp(headermap, phone_sybzjl, fybxId, "1", "顺顺制杖");//事业部经理
		request.fybxSp(headermap, phone_bxType, fybxId, "1", "顺顺制杖");//特殊费用
		request.fybxSp(headermap, phone_sybcw, fybxId, "2", "顺顺制杖");//财务初审
		//财务核票拒绝
		this.fybxEdit(headermap, fybxId);
		request.fybxSp(headermap, phone_pm, fybxId, "1", "顺顺制杖");//项目经理
		request.fybxSp(headermap, phone_dept, fybxId, "1", "顺顺制杖");//部门
		request.fybxSp(headermap, phone_sybzjl, fybxId, "1", "顺顺制杖");//事业部经理
		request.fybxSp(headermap, phone_bxType, fybxId, "1", "顺顺制杖");//特殊费用
		request.fybxSp(headermap, phone_sybcw, fybxId, "1", "顺顺制杖");//财务初审
		request.fybxSp(headermap, phone_sybcw, fybxId, "2", "顺顺制杖");//财务核票
		//总经理拒绝
		this.fybxEdit(headermap, fybxId);
		request.fybxSp(headermap, phone_pm, fybxId, "1", "顺顺制杖");//项目经理
		request.fybxSp(headermap, phone_dept, fybxId, "1", "顺顺制杖");//部门
		request.fybxSp(headermap, phone_sybzjl, fybxId, "1", "顺顺制杖");//事业部经理
		request.fybxSp(headermap, phone_bxType, fybxId, "1", "顺顺制杖");//特殊费用
		request.fybxSp(headermap, phone_sybcw, fybxId, "1", "顺顺制杖");//财务初审
		request.fybxSp(headermap, phone_sybcw, fybxId, "1", "顺顺制杖");//财务核票
		request.fybxSp(headermap, phone_boss, fybxId, "2", "顺顺制杖");//总经理
		//通过
		this.fybxEdit(headermap, fybxId);
		request.fybxSp(headermap, phone_pm, fybxId, "1", "顺顺制杖");//项目经理
		request.fybxSp(headermap, phone_dept, fybxId, "1", "顺顺制杖");//部门
		request.fybxSp(headermap, phone_sybzjl, fybxId, "1", "顺顺制杖");//事业部经理
		request.fybxSp(headermap, phone_bxType, fybxId, "1", "顺顺制杖");//特殊费用
		request.fybxSp(headermap, phone_sybcw, fybxId, "1", "顺顺制杖");//财务初审
		request.fybxSp(headermap, phone_sybcw, fybxId, "1", "顺顺制杖");//财务核票
		request.fybxSp(headermap, phone_boss, fybxId, "1", "顺顺制杖");//总经理
		request.fybxSp(headermap, phone_sybcw, fybxId, "1", "顺顺制杖");//财务付款
	}
	//费用报销简易修改（什么信息都不改）
	public void fybxEdit (Map<String, String> headermap, String fybxId) throws ClientProtocolException, IOException{
		request.login(headermap, phone_create);
		request.fybxUpdate(headermap, proj_id_, proj_code, proj_, money, bxType, bxrIds, bxrNames, pmo, typeFlag, fybxId);
	}
}
