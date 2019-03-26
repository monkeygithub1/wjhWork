package wjh.pmis.testcase.proj;

import java.io.IOException;
import java.util.Map;
import org.apache.http.client.ClientProtocolException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import wjh.pmis.data.DeptAndPhone;
import wjh.pmis.data.Request;
import wjh.pmis.data.RequestPara;
import wjh.pmis.restclient.RestClient;

/**
 * 职能部门（安质部）
 * R
 * 一次性通过&每个节点拒绝后重发
 */
public class Proj_003_R {
	DeptAndPhone dept_phone = new DeptAndPhone();
	String dept_name = "职能部门";
	Map<String , String> dept_para = dept_phone.getDeptInfo(dept_name);
	String phone_dept = dept_para.get("phone_dept");
	String phone_sybzjl = dept_para.get("phone_sybzjl");//事业部总经理
	String dept_ = dept_para.get("dept_proj");
	
	String phone_PM = dept_phone.phone_PM;//发起人 
	String proj_mgr_ = dept_phone.proj_mgr_;
	String proj_mgr_id_ = dept_phone.proj_mgr_id_;
	
	String phone_az = dept_phone.phone_az;;//安质部负责人
	String phone_sc = dept_phone.phone_sc;//生产副总
	String phone_jsyjy = dept_phone.phone_jsyjy;//技术研究院（项目立项）
	String phone_pmc_mgr = dept_phone.phone_pmc_mgr;//项目管理中心负责人（项目立项）
	String phone_boss = dept_phone.phone_boss;
	
	String proj_name_= "部门调整-R-一次性通过";
	String proj_name_refuse= "部门调整-R-每个节点拒绝一次";
	String proj_mgr_type_ = "R";
	String ys_kphte= "2000";
	RestClient restClient = new RestClient();
	Request request = new Request();
	
	@BeforeClass
	public void setUp() {
	}
	//一次性通过
	@Test
	public void proj() throws ClientProtocolException, IOException {
		Map<String, String> headermap = restClient.header();//获取信息头（带cookie）
		String projId = "";
		request.login(headermap, phone_PM);//登录
		Map<String, String> proj_para = new RequestPara().proj_save_para(projId, proj_name_, proj_mgr_, proj_mgr_id_, dept_, proj_mgr_type_);
		projId = request.projSave(headermap, proj_para);//新建项目信息
		request.projBudget(headermap, projId, ys_kphte);//新建预算信息
		request.projUpload(projId, headermap);//上传附件
		request.projApply(projId, headermap);//提交
		request.projSp(phone_sybzjl, headermap, projId, "1", "顺顺大猪蹄子");//事业部总经理审批
		request.projSp(phone_jsyjy, headermap, projId, "1", "顺顺大猪蹄子");//技术研究院负责人审批
		request.projSp(phone_pmc_mgr, headermap, projId, "1", "顺顺大猪蹄子");//运营总监审批
		request.projSp(phone_boss, headermap, projId, "1", "顺顺大猪蹄子");//总经理审批
  }
	//每个节点拒绝重发
	@Test(priority=1)
	public void projRefuse() throws ClientProtocolException, IOException{
		Map<String, String> headermap = restClient.header();
		String projId = "";
		//发起
		request.login(headermap, phone_PM);//登录
		Map<String, String> proj_para = new RequestPara().proj_save_para(projId, proj_name_, proj_mgr_, proj_mgr_id_, dept_, proj_mgr_type_);
		projId = request.projSave(headermap, proj_para);//新建项目信息
		request.projBudget(headermap, projId, ys_kphte);//新建预算信息
		request.projUpload(projId, headermap);//上传附件
		request.projApply(projId, headermap);//提交
		//事业部总经理拒绝
		request.projSp(phone_sybzjl, headermap, projId, "2", "顺顺不是大猪蹄子");
		//技术研究院负责人拒绝
		this.projEdit(headermap, "拒绝重发", projId);
		request.projSp(phone_sybzjl, headermap, projId, "1", "顺顺大猪蹄子");
		request.projSp(phone_jsyjy, headermap, projId, "2", "顺顺不是大猪蹄子");
		//运营总监拒绝
		this.projEdit(headermap, "拒绝重发", projId);
		request.projSp(phone_sybzjl, headermap, projId, "1", "顺顺大猪蹄子");
		request.projSp(phone_jsyjy, headermap, projId, "1", "顺顺大猪蹄子");
		request.projSp(phone_pmc_mgr, headermap, projId, "2", "顺顺不是大猪蹄子");
		//总经理拒绝
		this.projEdit(headermap, "拒绝重发", projId);
		request.projSp(phone_sybzjl, headermap, projId, "1", "顺顺大猪蹄子");
		request.projSp(phone_jsyjy, headermap, projId, "1", "顺顺大猪蹄子");
		request.projSp(phone_pmc_mgr, headermap, projId, "1", "顺顺大猪蹄子");
		request.projSp(phone_boss, headermap, projId, "2", "顺顺大猪蹄子");
		//通过
		this.projEdit(headermap, "拒绝重发", projId);
		request.projSp(phone_sybzjl, headermap, projId, "1", "顺顺大猪蹄子");
		request.projSp(phone_jsyjy, headermap, projId, "1", "顺顺大猪蹄子");
		request.projSp(phone_pmc_mgr, headermap, projId, "1", "顺顺大猪蹄子");
		request.projSp(phone_boss, headermap, projId, "1", "顺顺大猪蹄子");
	}
	//简易版修改重发
	public void projEdit(Map<String, String> headermap, String proj_name_edit, String projId) throws ClientProtocolException, IOException{
		request.login(headermap, phone_PM);
		Map<String, String> proj_para = new RequestPara().proj_save_para(projId, proj_name_refuse, proj_mgr_, proj_mgr_id_, dept_, proj_mgr_type_);
		projId = request.projSave(headermap, proj_para);//新建项目信息
		request.projApply(projId, headermap);//提交
	}
}