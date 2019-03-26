package wjh.pmis.data;

import java.util.HashMap;
import java.util.Map;

public class DeptAndPhone {
	
	
//---------------------------------------------------------------------------------------------------------------------------------	
	//发起人
	public String phone_create = "13910623294";
//	public String phone_create = "15822974029";// 王雨- 文化传媒事业部-影视动画部
//	public String phone_create = "13910623294";
	//项目立项发起人
//	public String phone_PM = "13910623294";
//	public String proj_mgr_ = "武军豪";
//	public String proj_mgr_id_ = "3b8f52b4b8f54bd597557361efcb8736";
	public String phone_PM = "18920879268";
	public String proj_mgr_ = "王素爽";
	public String proj_mgr_id_ = "6ba2c39b75a94485a89b642554f60ed5";
	//角色&权限
	public String phone_az = "13163002079";//安质部负责人
	public String phone_sc = "18622708857";//生产副总
	public String phone_jsyjy = "18602270056";//技术研究院（项目立项）
	public String phone_yyzj = "13910623294";//运营总监（项目立项）
	public String phone_pmc_mgr = "18502285517";//项目管理中心负责人(R型项目立项）
	public String phone_boss = "13502103187";//总经理
	public String phone_hrmgr = "18698057506";//人力资源部负责人（采购-人资审批）
	public String phone_zhglmgr = "15122681282";//综合管理负责人
	public String phone_zhglzz1 = "18622699252";//综合管理专责1（采购-租房类型）
	public String phone_zhglzz = "13920103101";//综合管理专责（采购-综合费用）
	public String phone_azmgr = "18502285517";//安质负责人
	public String phone_wzzz = "13820781432";//物资专责
	public String phone_wzmgr = "18502285517";//物资负责人
	public String phone_cwmgr = "13820641813";//财务负责人
	public String phone_fw = "13820781432";//法务（合同）
	public String phone_cpaaz = "18502285517";//安质审批（合同）
	public String phone_cpawz = "18502285517";//物资审批（合同）
	public String phone_cpacw = "13820641813";//财务审批（合同）
	public String phone_type_azwz = "18502285517";//报销、借款特殊费用审批（成本分类-安质/物资）
	public String phone_type_zhgl = "15122681282";//报销、借款特殊费用审批（成本分类-综合管理）
	public String phone_type_sw = "18222920165";//报销、借款特殊费用审批（成本分类-商务）
	public String phone_type_yx = "18622708857";//报销、借款特殊费用审批（成本分类-营销）
	public String phone_type_hr = "18698057506";//报销、借款特殊费用审批（成本分类-人资）
	public String phone_loan_cwcn = "18920336967";//财务出纳（借款）
	public String phone_purchasepay_cwcn = "18920336967";//财务出纳（采购付款）
	public String phone_bx_cwcn = "18920336967";//财务出纳（报销）
	public String phone_mc_upsert = "18920336967";//回款认领发布人
	public String phone_mc_claim_proj = "13910623294";//回款认领-项目回款认领人
	public String phone_mc_claim_bzj = "13910623294";//回款认领-保证金回款认领人
	public String phone_mc_approve_proj = "15822460668";//回款认领-项目回款审批人
	public String phone_mc_approve_bzj = "15822460668";//回款认领-保证金回款审批人
	public String phone_money_adjust_c_1 = "13820641813";//调账C方-张盈
	public String phone_money_adjust_c_2 = "15522001783";//调账C方-尹总
	public String phone_money_adjust_c_3 = "18698057506";//调账C方-李宏波
	public String phone_money_adjust_c_4 = "18502285517";//调账C方-王宝娜
	public String phone_money_adjust_c_5 = "15122681282";//调账C方-李腾飞
	public String phone_money_adjust_c_6 = "18622708857";//调账C方-杨总
	//项目、合同等ID
	
	//职能部门（以项目管理中心为例）
	String dept_name_zhineng = "项目管理中心";
	public String id_zhineng = "c4fb1b9eba214363b23ad5a792897f61";
	public String phone_dept_zhineng = "18502285517";
	public String phone_fg_zhineng = "18622708857";//项目管理中心分管领导：杨少鹏
	public String phone_sybzjl_zhineng = "13502103187";
	public String phone_sybdsz_zhineng = "";
	public String phone_sybcw_zhineng = "15822316986";
	//业务部门（以实施服务部为例）
	String dept_name_yewu = "实施服务部";
	public String id_yewu = "d7067d2b56ac4288ba2b81c8248652cf";
	public String phone_dept_yewu = "15900289062";
	public String phone_sybzjl_yewu = "13512968527";
	public String phone_sybdsz_yewu = "18602270056";
	public String phone_sybcw_yewu = "18630830951";
	//综合管理中心
	public String id_zhglzx = "1f4898ce02914c5888c738ae2d0697a9";
	public String phone_dept_zhglzx = "15122681282";
	public String phone_fg_zhglzx = "15522001783";
	public String phone_sybcw_zhglzx = "13920103101";
	//财务管理中心
	public String id_cwglzx = "d7dd8fe572df4ba99917bcb898b0c629";
	public String phone_dept_cwglzx = "13820641813";
	public String phone_fg_cwglzx = "13502103187";
	//市场部
	public String id_scb = "6cc235be8c8d4b50b21e25852eb36b2b";
	public String phone_dept_scb = "13032263028";
	public String phone_fg_scb = "18622708857";
	//技术研究院
	public String id_jsyjy = "5f0874d2cb1f405f956984496cfc2283";
	public String phone_dept_jsyjy = "";
	public String phone_sybzjl_jsyjy = "18602270056";
	public String phone_sybdsz_jsyjy = "";
	public String phone_sybcw_jsyjy = "15822316986";
	//参数：部门、人员、ID信息
	public Map<String,String> getDeptInfo (String dept_name){
		String id = "";
		String phone_dept = "";
		String phone_fg = "";//分管领导
		String phone_sybzjl = "";
		String phone_sybdsz = "";
		String phone_sybcw = "";
		//项目相关
		String proj_name = "";
		String proj_id_ = "";
		String proj_code = "";
		String phone_pm = "";
		String cpaId = "";
		String cpbId = "";
		String purchase_code_= "";
		String purchase_id_= "";
		switch(dept_name){
		case "职能部门":
			dept_name = dept_name_zhineng;
			id = id_zhineng;
			phone_dept = phone_dept_zhineng;
			phone_fg = phone_fg_zhineng;
			phone_sybzjl = phone_sybzjl_zhineng;
			phone_sybdsz = phone_sybdsz_zhineng;
			phone_sybcw = phone_sybcw_zhineng;
			phone_pm = "13910623294";
			proj_name = "项目结项专用项目-1547607278380";
			proj_id_ = "e1546bdd4eda42f9a6bb600f2b9dae37";
			proj_code = "SC-YWZX2019006";
			//策转产项目
//			proj_name = "2018-大王庄CBD-安质-嘲讽众开发专用策划V2.0";
//			proj_id_ = "80b9a4800f7c491b88d872e782405666";
//			proj_code = "SC-DWGH2018012";
			
			
			cpaId = "71573097785948c9836156069dc2fd1d";
			//子合同（开票用）
			cpbId = "9ad4d6925a3a4ea3938985272f316186";
			
//			cpbId = "bc627f567af34d4cbca03136a4ad57dc";
			purchase_code_= "CG201812272618";
			purchase_id_= "7d8cc44c3ccd4223b5dfdd841b44a91a";
			break;
		case "业务部门"://UIUE
			dept_name = dept_name_yewu;
			id = id_yewu;
			phone_dept = phone_dept_yewu;
			phone_sybzjl = phone_sybzjl_yewu;
			phone_sybdsz = phone_sybdsz_yewu;
			phone_sybcw = phone_sybcw_yewu;
			proj_name = "财务流水导出专用项目-1552118373468";
			proj_id_ = "10314b9fcb2548a29ae7acbe76a0bca2";
			proj_code = "SC-YWZX2019101";
			break;
		case "综合管理中心":
			id = id_zhglzx;
			phone_dept = phone_dept_zhglzx;
			phone_fg = phone_fg_zhglzx;
			phone_sybzjl = phone_sybzjl_zhineng;
			phone_sybdsz = phone_sybdsz_zhineng;
			phone_sybcw = phone_sybcw_zhglzx;//综合部单独的财务
			proj_name = "部门调整-P-一次性通过-1545283999580";
			proj_id_ = "92e95130368d42a2875391c3c7157342";
			proj_code = "SC-YWZX2018244";
			phone_pm = "13910623294";
			break;
		case "财务管理中心":
			id = id_cwglzx;
			phone_dept = phone_dept_cwglzx;
			phone_fg = phone_fg_cwglzx;
			phone_sybzjl = phone_sybzjl_zhineng;
			phone_sybdsz = phone_sybdsz_zhineng;
			phone_sybcw = phone_sybcw_zhineng;
			proj_name = "部门调整-P-一次性通过-1545284181763";
			proj_id_ = "08efc23ce4ac40b7afd005aae744e58e";
			proj_code = "SC-YWZX2018245";
			phone_pm = "13910623294";
			break;
		case "市场部":
			id = id_scb;
			phone_dept = phone_dept_scb;
			phone_fg = phone_fg_scb;
			phone_sybzjl = phone_sybzjl_zhineng;
			phone_sybdsz = phone_sybdsz_zhineng;
			phone_sybcw = phone_sybcw_zhineng;
			break;
		case "技术研究院":
			id = id_jsyjy;
			phone_dept = phone_dept_jsyjy;
			phone_sybzjl = phone_sybzjl_jsyjy;
			phone_sybdsz = phone_sybdsz_jsyjy;
			phone_sybcw = phone_sybcw_jsyjy;
			phone_pm = "13910623294";
			break;
		case "空间数据加工厂":
			id = "1d29d922a359431b822892558e353258";
			phone_dept = "13642105201";
			phone_sybzjl = "13820960993";
			phone_sybdsz = "18502285582";
			phone_sybcw = "13752791982";
			phone_pm = "18920879268";//王素爽（企业管理服务事业部-培训部）
//			
			proj_name = "数据治理专题专用项目-1548656888273";
			proj_id_ = "965bc79cf38546f098b6a556cc326d83";
			proj_code = "SC-YWZX2019013";
			cpaId = "344f8972d16b49cc829f09c167368d93";
			cpbId = "a9acb458e2cc47cfb6cd47047b21316b";
			
			
			purchase_id_ = "644a24b40ea34771adb56ea7b7cbd762";
			purchase_code_ = "CG201901240223";
			break;
		}
		Map<String,String> dept = new HashMap<String, String>();
		dept.put("dept_proj", dept_name + "$" + id);
		dept.put("dept_cpa", dept_name + "#S#" + id);
		dept.put("dept_cpb", dept_name + "#S#" + id);
		dept.put("deptId_mc", id);
		dept.put("phone_dept", phone_dept);
		dept.put("phone_fg", phone_fg);
		dept.put("phone_sybzjl", phone_sybzjl);
		dept.put("phone_sybdsz", phone_sybdsz);
		dept.put("phone_sybcw", phone_sybcw);
		dept.put("phone_pm", phone_pm);
		dept.put("proj_name", proj_name);
		dept.put("proj_id_", proj_id_);
		dept.put("proj_code", proj_code);
		dept.put("cpaId", cpaId);
		dept.put("cpbId", cpbId);
		dept.put("purchase_code_", purchase_code_);
		dept.put("purchase_id_", purchase_id_);
		return dept;
	}
	
}
