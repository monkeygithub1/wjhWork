package wjh.pmis.data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;

import wjh.pmis.restclient.RestClient;

public class RequestPara {
	//登录
	public Map<String, String> login_para (String phone_, String password_, String imageVliadCode){
		Map<String, String> para = new HashMap<String, String>();
		para.put("phone_", phone_);
		para.put("password_", password_);
		para.put("imageVliadCode", imageVliadCode);
		return para;
	}
	//项目立项：保存（发起和编辑）
	public Map<String, String> proj_save_para(String projId, String proj_name_, String proj_mgr_, String proj_mgr_id_, String dept_, String proj_mgr_type_){
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
	//项目立项：预算信息保存
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
//		bz_gsygcb bz_wxygcb bz_kphte bz_fwfywwbhs bz_tszlf bz_ysf bz_yjf bz_clf bz_hyf bz_pxf bz_fwzlf 
//		bz_sbzlf bz_psf bz_ldbhf bz_qt bz_xmfscb bz_rgcb bz_bgf bz_xmcbl bz_zlf bz_xmmll bz_clfhs
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
	public Map<String, String> cpa_sava_para(String id_, String name_, String money_, String dept_, String nature_, String type_, String proj_, String proj_id_, String purchase_code_, String purchase_id_){
		Map<String, String> para = new HashMap<String, String>();
		para.put("id_", id_);
		para.put("name_", name_+ "-" + Long.toString(System.currentTimeMillis()));
		para.put("money_", money_);
		para.put("dept_", dept_);
		para.put("qa_time_", "11");
		para.put("qa_time_unit_", "月");
		para.put("nature_", nature_);
		para.put("type_", type_);
		para.put("pa_qddw_", "北京淇河文化传媒有限公司");
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
	//乙方合同：基本信息保存
	public Map<String, String> cpb_sava_para(String id_, String name_, String money_, String dept_, String nature_, String type_, String proj_, String proj_id_){
		Map<String, String> para = new HashMap<String, String>();
		para.put("id_", id_);
		para.put("name_", name_+ "-" + Long.toString(System.currentTimeMillis()));
		para.put("money_", money_);
		para.put("dept_", dept_);
		para.put("qa_time_", "11");
		para.put("qa_time_unit_", "月");
		para.put("yxq_st_", "2018-08-16");
		para.put("yxq_et_", "2019-08-16");
		para.put("yxq_time_", "12");
		para.put("yxq_time_unit_", "月");
		para.put("nature_", nature_);
		para.put("type_", type_);
		para.put("pa_qddw_", "天津市政府");
		para.put("pb_qddw_", "天津天才博通科技有限公司");
		para.put("proj_", proj_);
		para.put("proj_id_", proj_id_);
		para.put("descript_", "乙方合同专用备注");
		return para;
	}
	//乙方合同：收款计划保存
	public Map<String, String> cpb_mi_para(String plan_money_, String cpbId){
		Map<String, String> para = new HashMap<String, String>();
		para.put("miBili", "10:0");
		JSONArray mip = new JSONArray();
		JSONObject mip_details = new JSONObject();
		mip_details.put("plan_money_", plan_money_);
		mip_details.put("plan_date_", "2018-09-06");
		mip_details.put("condition_", "收款条件很多");
		mip_details.put("remarks_", "要啥备注");
		mip.add(0, mip_details);
		para.put("mip", mip.toString());
		para.put("contrId", cpbId);
		return para;
	}
	//乙方合同：开票计划保存
	public Map<String, String> cpb_io_para(String plan_money_, String cpbId){
		Map<String, String> para = new HashMap<String, String>();
		JSONArray iop = new JSONArray();
		JSONObject iop_details = new JSONObject();
		iop_details.put("plan_money_", plan_money_);
		iop_details.put("plan_date_", "2018-09-06");
		iop_details.put("condition_", "开票条件很多");
		iop_details.put("remarks_", "要啥备注");
		iop.add(0, iop_details);
		para.put("iop", iop.toString());
		para.put("contrId", cpbId);
		return para;
	}
	//乙方合同：审批
	public Map<String, String> cpb_sp_para (String cpbId, String taskId, String sp, String comment){
		Map<String, String> para = new HashMap<String, String>();
		para.put("cpbId", cpbId);
		para.put("taskId", taskId);
		para.put("sp", sp);
		para.put("comment", comment);
		return para;
	}
	/*
	 * 费用报销：发起
	 * 1、报销明细只有一条，所以总金额与明细金额相等
	 * 2、报销人与收款人是同一个人
	 * 样式：
		 {
			"saveFilePath":"",
			"fybxDto":{
		        "projId":"0f241d5264ad44f58798ad93c645836e",
		        ......
		    },
	    	"details":[
	        	{
		            "amount":"111",
		            ...
		        }
	    	],
		    "recieve":[
		        {
		            "userId":"3b8f52b4b8f54bd597557361efcb8736",
		            ...
		        }
		    ]
		}
	 */
	public Map<String, String> fybx_para(String projId, String projCode, String projName, String money, String bxType, String bxrIds, String bxrNames, String pmo, String typeFlag){
		JSONObject fybxDto = new JSONObject();
		fybxDto.put("projId", projId);
		fybxDto.put("projCode", projCode);
		fybxDto.put("projName", projName);
		fybxDto.put("totalAmount", money);//总报销金额
		fybxDto.put("bxrIds", bxrIds);
		fybxDto.put("bxrNames", bxrNames);
		fybxDto.put("pmo", pmo);
		fybxDto.put("typeFlag", typeFlag);//特定类型，cz,cg
		fybxDto.put("xjAmount", 0);
		fybxDto.put("xjTip", "");
		fybxDto.put("czJson", "");

		JSONArray details = new JSONArray();
		JSONObject details_type = new JSONObject();
		details_type.put("amount", money);//明细报销金额
		details_type.put("bxType", bxType);
		details_type.put("tip", "顺顺大猪蹄子");
		details.add(0, details_type);

		JSONArray recieve = new JSONArray();
		JSONObject recieve_user = new JSONObject();
		recieve_user.put("userId", bxrIds);
		recieve_user.put("userName", bxrNames);
		recieve_user.put("paymentAmount", money);
		recieve_user.put("paymentMethod", "银行卡");
		recieve_user.put("tip", "顺顺大猪蹄子");
		recieve.add(0, recieve_user);

		JSONObject fybxJson  = new JSONObject();
		fybxJson.put("saveFilePath","");
		fybxJson.put("fybxDto", fybxDto);
		fybxJson.put("details", details);
		fybxJson.put("recieve", recieve);
		
		Map<String, String> para = new HashMap<String, String>();
		para.put("bxJson", fybxJson.toString());
		return para;
	}
	//费用报销：编辑
	public Map<String, String> fybx_update_para(String projId, String projCode, String projName, String money, String bxType, String bxrIds, String bxrNames, String pmo, String typeFlag, String fybxId){
		JSONObject fybxDto = new JSONObject();
		fybxDto.put("projId", projId);
		fybxDto.put("projCode", projCode);
		fybxDto.put("projName", projName);
		fybxDto.put("totalAmount", money);//总报销金额
		fybxDto.put("bxrIds", bxrIds);
		fybxDto.put("bxrNames", bxrNames);
		fybxDto.put("pmo", pmo);
		fybxDto.put("typeFlag", typeFlag);//特定类型，cz,cg
		fybxDto.put("xjAmount", 0);
		fybxDto.put("xjTip", "");
		fybxDto.put("czJson", "");
		fybxDto.put("id",fybxId);//编辑
		fybxDto.put("procStatus",2);//编辑

		JSONArray details = new JSONArray();
		JSONObject details_type = new JSONObject();
		details_type.put("amount", money);//明细报销金额
		details_type.put("bxType", bxType);
		details_type.put("tip", "顺顺大猪蹄子");
		details.add(0, details_type);

		JSONArray recieve = new JSONArray();
		JSONObject recieve_user = new JSONObject();
		recieve_user.put("userId", bxrIds);
		recieve_user.put("userName", bxrNames);
		recieve_user.put("paymentAmount", money);
		recieve_user.put("paymentMethod", "银行卡");
		recieve_user.put("tip", "顺顺大猪蹄子");
		recieve.add(0, recieve_user);

		JSONObject fybxJson  = new JSONObject();
		fybxJson.put("saveFilePath",fybxId);//编辑
		fybxJson.put("fybxDto", fybxDto);
		fybxJson.put("details", details);
		fybxJson.put("recieve", recieve);
		
		Map<String, String> para = new HashMap<String, String>();
		para.put("bxJson", fybxJson.toString());
		return para;
	}
	//费用报销：审批
	public Map<String, String> fybx_sp_para (String fybxId, String taskId, String sp, String comment){
		Map<String, String> para = new HashMap<String, String>();
		para.put("fybxId", fybxId);
		para.put("taskId", taskId);
		para.put("sp", sp);
		para.put("comment", comment);
		return para;
	}
	//差旅报销：发起
	public Map<String, String> clbx_para(Map<String,String> headermap, String projId, String projCode, String projName, 
			double money, String bxrIds, String bxrNames, String pmo, String typeFlag) throws ClientProtocolException, IOException{
		//获取出行信息（搜索，并截取第一个信息）
		RestClient restClient =  new RestClient();
		Map<String, String> para_cx = new HashMap<String,String>();
		para_cx.put("keyword", "李");//搜索“李”
		String url = restClient.url("api_chuxing_list");
		String cxAll = restClient.post(url, para_cx, headermap);
		String cx = restClient.getValue(cxAll, "data[0]");
		String cxrName = restClient.getValue(cx, "proposerName");
		String cxrId = restClient.getValue(cx, "proposer");
		ObjectMapper om = new ObjectMapper();
		Map<String,Object> chuxing = om.readValue(cx, Map.class);//把响应的出行信息转换
		List<Map<String,Object>> chuxings = new ArrayList<>();
		chuxings.add(chuxing);
		
		JSONObject clbxDto = new JSONObject();
		clbxDto.put("projId", projId);
		clbxDto.put("projCode", projCode);
		clbxDto.put("projName", projName);
		clbxDto.put("totalAmount", String.valueOf(money+100.00));//总报销金额
		clbxDto.put("bxrIds", cxrId);//报销人是出行人，可从出行中获取
		clbxDto.put("bxrNames", cxrName);
		clbxDto.put("pmo", pmo);
		clbxDto.put("typeFlag", typeFlag);//特定类型，cz,cg
		clbxDto.put("subsidyAmount", "100");//补助金额固定为100
		clbxDto.put("xjAmount", 0);
		clbxDto.put("xjTip", "");
		clbxDto.put("czJson", "");

		JSONArray details = new JSONArray();
		JSONObject details_type = new JSONObject();
		details_type.put("bxType", "traffic");
		details_type.put("amount", String.valueOf(money));//明细报销金额
		details_type.put("travelToolName", "火车硬卧");
		details_type.put("travelFromAddr", "北京");
		details_type.put("travelToAddr", "天津");
		details_type.put("stayCity", "");
		details_type.put("st", "2018-10-17 00:00:00");
		details_type.put("et", "2018-10-25 00:00:00");
		details_type.put("clUserIds", cxrId);//出行人，可从出行中获取
		details_type.put("clUserNames", cxrName);
		details_type.put("tip", "顺顺大猪蹄子");
		details.add(0, details_type);

		JSONArray recieve = new JSONArray();
		JSONObject recieve_user = new JSONObject();
		recieve_user.put("userId", bxrIds);//收款人是发起人
		recieve_user.put("userName", bxrNames);
		recieve_user.put("paymentAmount", String.valueOf(money+100.00));
		recieve_user.put("paymentMethod", "银行卡");
		recieve_user.put("tip", "顺顺大猪蹄子");
		recieve.add(0, recieve_user);

		JSONObject cybxJson  = new JSONObject();
		cybxJson.put("saveFilePath","");
		cybxJson.put("clbxDto", clbxDto);
		cybxJson.put("details", details);
		cybxJson.put("recieve", recieve);
		cybxJson.put("chuxings", chuxings);
		
		Map<String, String> para = new HashMap<String, String>();
		para.put("bxJson", cybxJson.toString());
		return para;
	}
	//差旅报销：编辑
	public Map<String, String> clbx_update_para(Map<String,String> headermap, String projId, String projCode, String projName, 
			double money, String bxrIds, String bxrNames, String pmo, String typeFlag, String clbxId) throws ClientProtocolException, IOException{
		//获取出行信息（搜索，并截取第一个信息）
		RestClient restClient =  new RestClient();
		Map<String, String> para_cx = new HashMap<String,String>();
		para_cx.put("keyword", "李");//搜索“李”
		String url = restClient.url("api_chuxing_list");
		String cxAll = restClient.post(url, para_cx, headermap);
		String cx = restClient.getValue(cxAll, "data[0]");
		String cxrName = restClient.getValue(cx, "proposerName");
		String cxrId = restClient.getValue(cx, "proposer");
		ObjectMapper om = new ObjectMapper();
		Map<String,Object> chuxing = om.readValue(cx, Map.class);//把响应的出行信息转换
		List<Map<String,Object>> chuxings = new ArrayList<>();
		chuxings.add(chuxing);
		
		JSONObject clbxDto = new JSONObject();
		clbxDto.put("projId", projId);
		clbxDto.put("projCode", projCode);
		clbxDto.put("projName", projName);
		clbxDto.put("totalAmount", String.valueOf(money+100.00));//总报销金额
		clbxDto.put("bxrIds", cxrId);//报销人是出行人，可从出行中获取
		clbxDto.put("bxrNames", cxrName);
		clbxDto.put("pmo", pmo);
		clbxDto.put("typeFlag", typeFlag);//特定类型，cz,cg
		clbxDto.put("subsidyAmount", "100");//补助金额固定为100
		clbxDto.put("xjAmount", 0);
		clbxDto.put("xjTip", "");
		clbxDto.put("czJson", "");
		clbxDto.put("id", clbxId);
		clbxDto.put("procStatus", "2");

		JSONArray details = new JSONArray();
		JSONObject details_type = new JSONObject();
		details_type.put("bxType", "traffic");
		details_type.put("amount", String.valueOf(money));//明细报销金额
		details_type.put("travelToolName", "火车硬卧");
		details_type.put("travelFromAddr", "北京");
		details_type.put("travelToAddr", "天津");
		details_type.put("stayCity", "");
		details_type.put("st", "2018-10-17 00:00:00");
		details_type.put("et", "2018-10-25 00:00:00");
		details_type.put("clUserIds", cxrId);//出行人，可从出行中获取
		details_type.put("clUserNames", cxrName);
		details_type.put("tip", "顺顺大猪蹄子");
		details.add(0, details_type);

		JSONArray recieve = new JSONArray();
		JSONObject recieve_user = new JSONObject();
		recieve_user.put("userId", bxrIds);//收款人是发起人
		recieve_user.put("userName", bxrNames);
		recieve_user.put("paymentAmount", String.valueOf(money+100.00));
		recieve_user.put("paymentMethod", "银行卡");
		recieve_user.put("tip", "顺顺大猪蹄子");
		recieve.add(0, recieve_user);

		JSONObject cybxJson  = new JSONObject();
		cybxJson.put("saveFilePath",clbxId);
		cybxJson.put("clbxDto", clbxDto);
		cybxJson.put("details", details);
		cybxJson.put("recieve", recieve);
		cybxJson.put("chuxings", chuxings);
		
		Map<String, String> para = new HashMap<String, String>();
		para.put("bxJson", cybxJson.toString());
		return para;
	}
	//差旅报销：审批
	public Map<String, String> clbx_sp_para (String clbxId, String taskId, String sp, String comment){
		Map<String, String> para = new HashMap<String, String>();
		para.put("clbxId", clbxId);
		para.put("taskId", taskId);
		para.put("sp", sp);
		para.put("comment", comment);
		return para;
	}

}
