package wjh.pmis.testcase.cpb;

import java.io.IOException;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import wjh.pmis.data.Request;
import wjh.pmis.restclient.RestClient;
	/*
	 * 乙方合同-安质部-书面
	 * 框架合同需要单独加一个参数：is_frame_=1
	 */
public class CpbDemo {
	String phone_create = "13910623294";//发起人 
	String phone_dept = "18502285517";//安质部
	String phone_fw = "15822223160";
	String phone_sybzjl = "13502103187";
	String phone_boss = "13502103187";
	String cpb_name_= "乙方合同-顺顺最帅-一次性通过";
	String cpb_name_refuse= "乙方合同-顺顺最帅-每个节点拒绝一次";
	String money_ = "10000";
	String dept_ = "安全质量部#S#c4fb1b9eba214363b23ad5a792897f61";
	String nature_= "书面";
	String type_= "信息化建设";
	String proj_ = "项目流水专用项目V3.0";
	String proj_id_ = "0f241d5264ad44f58798ad93c645836e";
	
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
		cpbId = request.cpbSave(headermap, cpbId, cpb_name_, money_, dept_, nature_, type_, proj_, proj_id_);//保存合同信息
		request.cpb_mi_Save(headermap, money_, cpbId);//保存收款计划
		request.cpb_io_Save(headermap, money_, cpbId);//保存开票计划
		request.cpbApply(headermap, cpbId);//提交
		request.cpbSp(headermap, phone_dept, cpbId, "1", "长鹏大沙雕");//部门
		request.cpbSp(headermap, phone_fw, cpbId, "1", "长鹏大沙雕");//法务
		request.cpbSp(headermap, phone_sybzjl, cpbId, "1", "长鹏大沙雕");//事业部总经理
		request.cpbSp(headermap, phone_boss, cpbId, "1", "长鹏大沙雕");//总经理
	}
	//一次性通过
	@Test(priority=1)
	public void cpbDemoRefuse() throws ClientProtocolException, IOException {
		Map<String, String> headermap = restClient.header();//获取信息头（带cookie）
		String cpbId = "";
		request.login(headermap, phone_create);//登录
		cpbId = request.cpbSave(headermap, cpbId, cpb_name_, money_, dept_, nature_, type_, proj_, proj_id_);//保存合同信息
		request.cpb_mi_Save(headermap, money_, cpbId);//保存收款计划
		request.cpb_io_Save(headermap, money_, cpbId);//保存开票计划
		request.cpbApply(headermap, cpbId);//提交
		//部门拒绝
		request.cpbSp(headermap, phone_dept, cpbId, "2", "长鹏大沙雕");
		//法务拒绝
		this.cpbEdit(headermap, cpbId, "乙方合同-拒绝重发-顺顺还是大猪蹄子");
		request.cpbSp(headermap, phone_dept, cpbId, "1", "长鹏大沙雕");//部门
		request.cpbSp(headermap, phone_fw, cpbId, "2", "长鹏大沙雕");//法务
		//事业部总经理拒绝
		this.cpbEdit(headermap, cpbId, "乙方合同-拒绝重发-顺顺还是大猪蹄子");
		request.cpbSp(headermap, phone_dept, cpbId, "1", "长鹏大沙雕");//部门
		request.cpbSp(headermap, phone_fw, cpbId, "1", "长鹏大沙雕");//法务
		request.cpbSp(headermap, phone_sybzjl, cpbId, "2", "长鹏大沙雕");//事业部总经理
		//总经理拒绝
		this.cpbEdit(headermap, cpbId, "乙方合同-拒绝重发-顺顺还是大猪蹄子");
		request.cpbSp(headermap, phone_dept, cpbId, "1", "长鹏大沙雕");//部门
		request.cpbSp(headermap, phone_fw, cpbId, "1", "长鹏大沙雕");//法务
		request.cpbSp(headermap, phone_sybzjl, cpbId, "1", "长鹏大沙雕");//事业部总经理
		request.cpbSp(headermap, phone_boss, cpbId, "2", "长鹏大沙雕");//总经理
		//通过
		this.cpbEdit(headermap, cpbId, "乙方合同-拒绝重发-顺顺还是大猪蹄子");
		request.cpbSp(headermap, phone_dept, cpbId, "1", "长鹏大沙雕");//部门
		request.cpbSp(headermap, phone_fw, cpbId, "1", "长鹏大沙雕");//法务
		request.cpbSp(headermap, phone_sybzjl, cpbId, "1", "长鹏大沙雕");//事业部总经理
		request.cpbSp(headermap, phone_boss, cpbId, "1", "长鹏大沙雕");//总经理
	}
	//乙方方合同基本信息简易修改（仅修改合同名称）
	public void cpbEdit(Map<String, String> headermap, String cpbId, String cpb_name_edit) throws ClientProtocolException, IOException{
		request.login(headermap, phone_create);
		request.cpbSave(headermap, cpbId, cpb_name_edit, money_, dept_, nature_, type_, proj_, proj_id_);
		request.cpbApply(headermap, cpbId);
	}
}
 
