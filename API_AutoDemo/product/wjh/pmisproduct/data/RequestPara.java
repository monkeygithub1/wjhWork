package wjh.pmisproduct.data;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import wjh.pmis.restclient.RestClient;

public class RequestPara {
	//参数：部门、人员信息
	public Map<String,String> dept (String dept_name){
		RestClient restClient = new RestClient();
		String id = "";
		String phone_dept = "";
		switch(dept_name){
		case "技术部":
			id = restClient.info_product("dept_jishubu_id");
			phone_dept = restClient.info_product("phone_dept_jishubu");
			break;
		}
		Map<String,String> dept = new HashMap<String, String>();
		dept.put("dept_proj", id);
		dept.put("dept_cpa", id);
		dept.put("dept_cpb", id);
		dept.put("deptId_mc", id);
		dept.put("phone_dept", phone_dept);
		return dept;
	}
	//登录
	public Map<String, String> login_para (String phone_, String password_, String imageVliadCode){
		Map<String, String> para = new HashMap<String, String>();
		para.put("phone_", phone_);
		para.put("password_", password_);
		para.put("imageVliadCode", imageVliadCode);
		return para;
	}
	//项目立项：保存
	public Map<String, String> proj_save_para(String projId, String proj_name_, String proj_mgr_, String proj_mgr_id_, 
			String dept_, String dept_id_, String proj_type_){
		Map<String, String> para = new HashMap<String, String>();
		if (projId != ""){
			para.put("id_", projId);
		}
		para.put("proj_name_", proj_name_+ "-" + Long.toString(System.currentTimeMillis()));
		String time = Long.toString(System.currentTimeMillis()/1000);
		para.put("proj_short_name_", time);
		para.put("dept_", dept_);
		para.put("dept_id_", dept_id_);
		para.put("proj_mgr_", proj_mgr_);
		para.put("proj_mgr_id_", proj_mgr_id_);
		para.put("area_", "天津市-天津市-和平区");
		para.put("area1", "天津市");
		para.put("area2", "天津市");
		para.put("area3", "和平区");
		para.put("area_detail_", "鼎泰大厦");
		para.put("customer_com_", "首钢");
		para.put("customer_dept_", "烧砖");
		para.put("customer_name_", "武先森");
		para.put("customer_phone_", "13900000000");
		para.put("proj_type_", proj_type_);//管理类型等
		para.put("plan_st_", "2018-09-01");
		para.put("plan_et_", "2019-09-01");
		para.put("yuji_contract_sum_", "5000");//预计合同额
		para.put("label_", "战略,重点,风险");
		para.put("descript_", "你们都是大猪蹄子");
		para.put("fileTag", "");
		return para;
	}
	//项目立项：预算信息保存（简易版）
	public Map<String, String> proj_budge_para(String proj_id_, String ys_kphte){
		Map<String, String> para = new HashMap<String, String>();
		para.put("proj_id_", proj_id_);
		para.put("ys_kphte", ys_kphte);
		//人工成本
		para.put("ys_gsygcb", "10");
		para.put("ys_wxygcb", "20");
		para.put("ys_clfhs", "30");//材料费（含税）
		para.put("ys_fwfywwbhs", "40");//服务费
		//办公费
		para.put("ys_tszlf", "50");
		para.put("ys_ysf", "60");
		para.put("ys_yjf", "70");
		para.put("ys_clf", "80");//差旅费
		para.put("ys_hyf", "90");//会议费
		para.put("ys_pxf", "100");//培训费
		//租赁费
		para.put("ys_fwzlf", "110");
		para.put("ys_sbzlf", "120");
		para.put("ys_psf", "130");//评审费
		para.put("ys_ldbhf", "140");//劳动保护费
		para.put("ys_qt", "150");//其他
//			bz_gsygcb bz_wxygcb bz_kphte bz_fwfywwbhs bz_tszlf bz_ysf bz_yjf bz_clf bz_hyf bz_pxf bz_fwzlf 
//			bz_sbzlf bz_psf bz_ldbhf bz_qt bz_xmfscb bz_rgcb bz_bgf bz_xmcbl bz_zlf bz_xmmll bz_clfhs
		return para;
	}
	//项目立项：审批
	public Map<String, String> proj_sp_para (String projId, String taskId, String sp, String comment){
		Map<String, String> para = new HashMap<String, String>();
		para.put("projId", projId);
		para.put("taskId", taskId);
		para.put("sp", sp);
		para.put("comment", comment);
		return para;
	}
	//甲方合同：基本信息保存
	public Map<String, String> cpa_sava_para(String id_, String name_, String money_, String dept_, String dept_id_, String nature_, String type_, 
											String proj_, String proj_id_, String purchase_code_, String purchase_id_){
		Map<String, String> para = new HashMap<String, String>();
		para.put("id_", id_);
		para.put("name_", name_+ "-" + Long.toString(System.currentTimeMillis()));
		para.put("money_", money_);
		para.put("dept_", dept_);
		para.put("dept_id_", dept_id_);
		para.put("qa_time_", "11");
		para.put("qa_time_unit_", "月");
		para.put("nature_", nature_);
		para.put("type_", type_);
		para.put("pa_qddw_", "公司A");
		para.put("pb_qddw_", "大王庄CBD");
		para.put("proj_", proj_);
		para.put("proj_id_", proj_id_);
		para.put("purchase_code_", purchase_code_);
		para.put("purchase_id_", purchase_id_);
		para.put("descript_", "甲方合同专用备注");
		return para;
	}
	//甲方合同：付款计划保存
	public Map<String, String> cpa_mo_para(String plan_money_, String cpaId){
		Map<String, String> para = new HashMap<String, String>();
		para.put("moBili", "10:0");
		JSONArray mop = new JSONArray();
		JSONObject mop_details = new JSONObject();
		mop_details.put("plan_money_", plan_money_);
		mop_details.put("plan_date_", "2018-09-06");
		mop_details.put("condition_", "付款条件很多");
		mop_details.put("remarks_", "要啥备注");
		mop.add(0, mop_details);
		para.put("mop", mop.toString());
		para.put("contrId", cpaId);
		return para;
	}
	//甲方合同：收票计划保存
	public Map<String, String> cpa_ii_para(String plan_money_, String cpaId){
		Map<String, String> para = new HashMap<String, String>();
		JSONArray iip = new JSONArray();
		JSONObject iip_details = new JSONObject();
		iip_details.put("plan_money_", plan_money_);
		iip_details.put("plan_date_", "2018-09-06");
		iip_details.put("condition_", "收票条件很多");
		iip_details.put("remarks_", "要啥备注");
		iip.add(0, iip_details);
		para.put("iip", iip.toString());
		para.put("contrId", cpaId);
		return para;
	}
	//甲方合同：审批
	public Map<String, String> cpa_sp_para (String cpaId, String taskId, String sp, String comment){
		Map<String, String> para = new HashMap<String, String>();
		para.put("cpaId", cpaId);
		para.put("taskId", taskId);
		para.put("sp", sp);
		para.put("comment", comment);
		return para;
	}
}
