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
 * P（非RT）
 * 一次性通过&每个节点拒绝后重发
 */
public class Proj_999 {
	DeptAndPhone dept_phone = new DeptAndPhone();
//	String dept_name = "职能部门";//安质部
//	String dept_name = "生态建设部";
//	String dept_name = "商务部";
//	String dept_name = "物资管理部";
//	String dept_name = "综合管理部(公司)";
//	String dept_name = "人力资源部";
//	String dept_name = "财务资产部";
//	String dept_name = "市场部";
//	String dept_name = "空间数据加工厂";
	String dept_name = "业务部门";
	Map<String , String> dept_para = dept_phone.getDeptInfo(dept_name);
	String phone_dept = dept_para.get("phone_dept");
	String phone_sybzjl = dept_para.get("phone_sybzjl");//事业部总经理
	String dept_ = dept_para.get("dept_proj");
	
	String phone_PM = dept_phone.phone_PM;//发起人 
	String proj_mgr_ = dept_phone.proj_mgr_;
	String proj_mgr_id_ = dept_phone.proj_mgr_id_;
	
	String phone_az = dept_phone.phone_az;;//安质部负责人
	String phone_sc = dept_phone.phone_sc;//生产副总
	
	String proj_name_= "父子合同调账专用项目";
	String proj_mgr_type_ = "W";
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
//		request.login(headermap, "13910623294");//登录
		Map<String, String> proj_para = new RequestPara().proj_save_para(projId, proj_name_, proj_mgr_, proj_mgr_id_, dept_, proj_mgr_type_);
		projId = request.projSave(headermap, proj_para);//新建项目信息
		System.out.println(projId);
		request.projBudget(headermap, projId, ys_kphte);//新建预算信息
		request.projUpload(projId, headermap);//上传附件
		request.projApply(projId, headermap);//提交
		request.projSp(phone_dept, headermap, projId, "1", "顺顺大猪蹄子");//部门领导审批
		request.projSp(phone_sybzjl, headermap, projId, "1", "顺顺大猪蹄子");//事业部总经理审批
		request.projSp(phone_az, headermap, projId, "1", "顺顺大猪蹄子");//安质负责人审批
		request.projSp(phone_sc, headermap, projId, "1", "顺顺大猪蹄子");//生产副总审批
  }
}