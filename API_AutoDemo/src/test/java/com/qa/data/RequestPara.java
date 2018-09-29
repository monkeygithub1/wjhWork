package com.qa.data;

import java.util.HashMap;
import java.util.Map;

public class RequestPara {
	//登录
	public Map<String, String> login_para (String phone_, String password_, String imageVliadCode){
		Map<String, String> para = new HashMap<String, String>();
		para.put("phone_", phone_);
		para.put("password_", password_);
		para.put("imageVliadCode", imageVliadCode);
		return para;
	}
	//项目立项保存（发起和编辑）
	public Map<String, String> proj_save_Para(String projId, String proj_name_, String proj_mgr_, String proj_mgr_id_, String dept_, String proj_mgr_type_){
		Map<String, String> para = new HashMap<String, String>();
		para.put("id_", projId);
		para.put("ch_proj_id_", "");
		para.put("proj_name_", proj_name_+ "-" + Long.toString(System.currentTimeMillis()));
		para.put("proj_mgr_", proj_mgr_);
		para.put("proj_mgr_id_", proj_mgr_id_);
		para.put("dept_", dept_);
		para.put("cy_dept_", "产品研发的ZZ们");
		para.put("area_", "华东区域");
		para.put("customer_", "首钢-烧砖-武先森-13900000000");
		para.put("proj_xingzhi_", "1");//牵头、参与
		para.put("proj_mgr_type_", proj_mgr_type_);//R、T等
		para.put("biz_type1_", "企业级业务咨询");//五级大表
		para.put("biz_type2_", "管理咨询");
		para.put("biz_type3_", "管理标准化咨询");
		para.put("biz_type_ext1_", "无");
		para.put("biz_type_ext2_", "无");
		para.put("plan_st_", "2018-09-01");
		para.put("plan_et_", "2019-09-01");
		para.put("is_zl_", "1");//标签
		para.put("yuji_contract_sum_", "5000");//预计合同额
		para.put("basis_", "合同");//立项依据
		para.put("descript_", "你们都是大猪蹄子");
		return para;
	}
	//项目立项预算信息保存
	public Map<String, String> proj_budge_Para(String proj_id_, String ys_kphte){
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
//		bz_gsygcb bz_wxygcb bz_kphte bz_fwfywwbhs bz_tszlf bz_ysf bz_yjf bz_clf bz_hyf bz_pxf bz_fwzlf 
//		bz_sbzlf bz_psf bz_ldbhf bz_qt bz_xmfscb bz_rgcb bz_bgf bz_xmcbl bz_zlf bz_xmmll bz_clfhs
		return para;
	}	
	//项目立项审批
	public Map<String, String> proj_sp_para (String projId, String taskId, String sp, String comment){
		Map<String, String> para = new HashMap<String, String>();
		para.put("projId", projId);
		para.put("taskId", taskId);
		para.put("sp", sp);
		para.put("comment", comment);
		return para;
	}

}
