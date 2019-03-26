package wjh.pmis.data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.testng.DependencyMap;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;

import wjh.pmis.restclient.RestClient;

public class RequestPara {
	//截取部门和部门ID
	public String dept_String(String dept){
		int first = dept.indexOf("$");
		String dept_ = dept.substring(0, first);
		return dept_;
	}
	public String dept_id_String(String dept){
		int first = dept.indexOf("$");
		String dept_id_ = dept.substring(first+1);
		return dept_id_;
	}
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
		para.put("dept_", this.dept_String(dept_));
		para.put("dept_id_", this.dept_id_String(dept_));
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
	//项目立项：预算信息保存（写死的数不要改，后面统计会用到）
	public Map<String, String> proj_budget_para(String proj_id_, String ys_kphte){
		Map<String, String> para = new HashMap<String, String>();
		para.put("proj_id_", proj_id_);
		para.put("ys_kphte", ys_kphte);
		//人工成本
		para.put("ys_gsygcb", "100");//公司员工成本
		para.put("ys_wxygcb", "200");//外协员工成本
		para.put("ys_clfhs", "300");//材料费（含税）
		para.put("ys_fwfywwbhs", "400");//服务费
		//办公费
		para.put("ys_tszlf", "500");//图书资料费
		para.put("ys_ysf", "600");//印刷费
		para.put("ys_yjf", "700");//邮寄费
		para.put("ys_clf", "800");//差旅费
		para.put("ys_hyf", "900");//会议费
		para.put("ys_pxf", "100");//培训费
		//租赁费
		para.put("ys_fwzlf", "110");//房屋租赁费（宿舍租赁）
		para.put("ys_sbzlf", "120");//设备租赁费
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
		para.put("btnType", "1");
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
		para.put("btnType", "1");
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
//		recieve_user.put("paymentAmount", money);
		recieve_user.put("paymentAmount", "0");
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
			double money, String pmo, String typeFlag) throws ClientProtocolException, IOException{
		//获取出行信息（搜索，并截取第一个信息）
		RestClient restClient =  new RestClient();
		Map<String, String> para_cx = new HashMap<String,String>();
		para_cx.put("keyword", "张");//搜索“李”
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
		clbxDto.put("totalAmount", String.valueOf(money));//总报销金额
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
		details_type.put("amount", String.valueOf(money-100.00));//明细报销金额
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
		recieve_user.put("userId", cxrId);//收款人是出行人
		recieve_user.put("userName", cxrName);
		recieve_user.put("paymentAmount", String.valueOf(money));
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
			double money, String pmo, String typeFlag, String clbxId) throws ClientProtocolException, IOException{
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
		recieve_user.put("userId", cxrId);//收款人是发起人
		recieve_user.put("userName", cxrName);
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
	//采购申请
	public Map<String, String> purchase_para (String proj_id_, String money_, String purchaseId, String proc_id_, String purchase_type){
		Map<String, String> para = new HashMap<String, String>();
		if (purchaseId != ""){
			para.put("id_", purchaseId);
			para.put("proc_id_", proc_id_);
		}
		para.put("type_", purchase_type);
		para.put("proj_id_", proj_id_);
		para.put("money_", money_);
		para.put("fileTag", "");
		para.put("plan_match_", "计划内");
		para.put("simple_tip_", "木有简述");
		//以下为json_text
		JSONObject json_text_ = new JSONObject();
		JSONObject table = new JSONObject();
		JSONArray goods = new JSONArray();
		JSONObject key = new JSONObject();
		JSONObject value = new JSONObject();
		switch (purchase_type){
		//自用物资
		case "0":
			table.put("reason", "顺顺活得不如咸鱼！！");
			table.put("content", "");
			table.put("supplier", "");
			table.put("sjr", "武先森");
			table.put("lxdh", "110");
			table.put("shdz", "大王庄CBD");
			key.put("a", "物资名称");
			key.put("b", "品牌");
			key.put("c", "具体型号配置");
			key.put("num", "数量");
			key.put("e", "单位");
			key.put("unitPrice", "预计采购单价/元");
			key.put("totalPrice", "预计采购总价/元");
			key.put("h", "推荐供应商");
			value.put("a", "手机");
			value.put("b", "苹果");
			value.put("c", "苹果XSSSSSSS");
			value.put("num", "1");
			value.put("e", "车");
			value.put("unitPrice", "998");
			value.put("totalPrice", "998");
			value.put("h", "二手东");
			goods.add(0, key);
			goods.add(1, value);
			json_text_.put("table", table);
			json_text_.put("goods", goods);
			break;
		//项目物资
		case "1":
			table.put("reason", "顺顺活得不如咸鱼！！");
			table.put("sjr", "武先森");
			table.put("lxdh", "110");
			table.put("shdz", "大王庄CBD");
			key.put("a", "物资名称");
			key.put("b", "具体型号配置");
			key.put("num", "数量");
			key.put("d", "单位");
			key.put("unitPrice", "预计采购单价/元");
			key.put("totalPrice", "预计采购总价/元");
			key.put("gongyingshang", "推荐供应商");
			value.put("a", "手机");
			value.put("b", "苹果");
			value.put("num", "1");
			value.put("d", "车");
			value.put("unitPrice", "998");
			value.put("totalPrice", "998");
			value.put("gongyingshang", "二手东");
			goods.add(0, key);
			goods.add(1, value);
			json_text_.put("table", table);
			json_text_.put("goods", goods);
			break;
		//人力外包
		case "2":
			table.put("reason", "顺顺活得不如咸鱼！！");
			key.put("a", "类别");
			key.put("b", "级别");
			key.put("c", "人数/人");
			key.put("d", "预计人天单价/元");
			key.put("e", "开始日期");
			key.put("f", "结束日期");
			key.put("g", "地点");
			key.put("h", "推荐供应商");
			key.put("i", "报价");
			value.put("a", "外包测试");
			value.put("b", "SSS");
			value.put("c", "10");
			value.put("d", "250");
			value.put("e", "2018-10-10");
			value.put("f", "2019-10-10");
			value.put("g", "大王庄CBD");
			value.put("h", "淘客科技");
			value.put("i", "2500");
			goods.add(0, key);
			goods.add(1, value);
			json_text_.put("table", table);
			json_text_.put("goods", goods);
			break;
		//业务外包/其他服务采购
		case "3":
			table.put("reason", "顺顺活得不如咸鱼！！");
			table.put("content", "云服务");
			table.put("supplier", "华为云");
			json_text_.put("table", table);
			json_text_.put("goods", goods);
			break;
		//项目物资框架
		case "4":
			table.put("reason", "顺顺活得不如咸鱼！！");
			table.put("sjr", "常小黑");
			table.put("lxdh", "110");
			table.put("shdz", "大王庄CBD");
			key.put("a", "名称");
			key.put("b", "品牌");
			key.put("c", "具体型号配置");
			key.put("num", "数量");
			key.put("d", "单位");
			key.put("unitPrice", "预计采购单价/元");
			key.put("unitPrice1", "销售单价/元");
			key.put("totalPrice", "预计采购总价/元");
			key.put("totalPrice1", "销售总价/元");
			key.put("gongyingshang", "推荐供应商");
			value.put("a", "手机");
			value.put("b", "苹果");
			value.put("c", "苹果XSSSSSSS");
			value.put("num", "1");
			value.put("d", "车");
			value.put("unitPrice", "998");
			value.put("unitPrice1", "1998");
			value.put("totalPrice", "998");
			value.put("totalPrice1", "1998");
			value.put("gongyingshang", "二手东");
			goods.add(0, key);
			goods.add(1, value);
			json_text_.put("table", table);
			json_text_.put("goods", goods);
			break;
		//综合费用
		case "5":
			table.put("tip", "顺顺翻身");
			key.put("a", "地点及费用名称");
			key.put("b", "费用开始时间");
			key.put("c", "费用截至时间");
			key.put("d", "供应商");
			key.put("e", "单位");
			key.put("f", "开户行");
			key.put("g", "账号");
			key.put("totalPrice", "金额");
			value.put("a", "鼎泰买电脑");
			value.put("b", "2018-10-10");
			value.put("c", "2019-10-10");
			value.put("d", "某宝");
			value.put("e", "个");
			value.put("f", "大王庄银行");
			value.put("g", "88772288");
			value.put("totalPrice", "1998");
			goods.add(0, key);
			goods.add(1, value);
			json_text_.put("table", table);
			json_text_.put("goods", goods);
			break;
		//人力资源
		case "6":
			table.put("tip", "顺顺翻身");
			table.put("money_neibu", "1000.00");
			table.put("money_waibu", "2000.00");
			JSONArray table_neibu = new JSONArray();
			JSONObject key_neibu = new JSONObject();
			key_neibu.put("totalPrice1", "金额/元");
			key_neibu.put("b1", "分类");
			key_neibu.put("c1", "供应商");
			key_neibu.put("d1", "备注");
			JSONObject value_neibu = new JSONObject();
			value_neibu.put("totalPrice1", "1000");
			value_neibu.put("b1", "工资");
			value_neibu.put("c1", "万贸");
			value_neibu.put("d1", "发工资");
			table_neibu.add(0, key_neibu);
			table_neibu.add(1, value_neibu);
			JSONArray table_waibu = new JSONArray();
			JSONObject key_waibubu = new JSONObject();
			key_waibubu.put("totalPrice1", "金额/元");
			key_waibubu.put("b1", "分类");
			key_waibubu.put("c1", "供应商");
			key_waibubu.put("d1", "备注");
			JSONObject value_waibubu = new JSONObject();
			value_waibubu.put("totalPrice1", "1000");
			value_waibubu.put("b1", "工资");
			value_waibubu.put("c1", "万贸");
			value_waibubu.put("d1", "发工资");
			table_waibu.add(0, key_waibubu);
			table_waibu.add(1, value_waibubu);
			
			json_text_.put("table", table);
			json_text_.put("table_neibu", table_neibu);
			json_text_.put("table_waibu", table_waibu);
			break;
		//租房
		case "7":
			table.put("fwjtdz", "大王庄CBD");
			table.put("fwjnjn", "1男1女");
			table.put("fwrzsj", "2018-10-10");
			table.put("fwlksj", "2018-10-31");
			key.put("a", "房东姓名");
			key.put("b", "房屋平米数");
			key.put("c", "租金");
			key.put("d", "押金");
			key.put("e", "几室几厅");
			key.put("f", "押几付几");
			value.put("a", "常小黑");
			value.put("b", "100");
			value.put("c", "2400");
			value.put("d", "1200");
			value.put("e", "1室1厅");
			value.put("f", "押1付3");
			goods.add(0, key);
			goods.add(1, value);
			json_text_.put("table", table);
			json_text_.put("goods", goods);
			break;
		default:
			System.out.println("PurchaseApply: 木有这个采购类型");
			break;
		}
		para.put("json_text_", json_text_.toString());
		return para;
	}
	//采购申请：审批
	public Map<String, String> purchase_sp_para (String purchaseId, String taskId, String sp, String comment){
		Map<String, String> para = new HashMap<String, String>();
		para.put("purchaseId", purchaseId);
		para.put("taskId", taskId);
		para.put("sp", sp);
		para.put("comment", comment);
		return para;
	}
	//采购付款
	public Map<String, String> payment_para (String proj_id_, String money_, String is_ds_pur_, String cpaId,String cpbId, String purchaseId, String paymentId, String proc_id_, String purchase_type){
		Map<String, String> para = new HashMap<String, String>();
		if (paymentId != ""){
			para.put("id_", paymentId);
			para.put("proc_id_", proc_id_);
		}
		para.put("pur_id_", purchaseId);
		para.put("proj_id_", proj_id_);
		para.put("money_", money_);
		para.put("cpa_id_", cpaId);
		para.put("cpb_id_", cpbId);
		para.put("khh_name_", "天津市河东区大王庄CBD第一个胡同进去第二个口拐弯直行三个胡同就在墙边拐角处看起来很猥琐的豪氏银行");
		para.put("khh_account_", "1234567890123456789");
		para.put("company_name_", "天津市淘客科技有限公司");
		para.put("supplier_", "京东");
		para.put("is_ds_pur_", is_ds_pur_);
		para.put("simple_tip_", "木有简述");
		
		//以下为json_text
		JSONObject json_text_ = new JSONObject();
		JSONObject table = new JSONObject();
		JSONArray goods = new JSONArray();
		JSONObject key = new JSONObject();
		JSONObject value = new JSONObject();
		switch (purchase_type){
		case "0":
			table.put("payReason", "顺顺活得不如咸鱼！！");
			key.put("a", "物资名称");
			key.put("b", "品牌");
			key.put("c", "具体型号配置");
			key.put("num", "数量");
			key.put("e", "单位");
			key.put("unitPrice", "采购单价/元");
			key.put("totalPrice", "采购总价/元");
			key.put("totalPrice", "采购总价/元");
			key.put("realPrice", "实付金额/元");
			key.put("gongyingshang", "推荐供应商");
			value.put("a", "手机");
			value.put("b", "苹果");
			value.put("c", "苹果XSSSSSSS");
			value.put("num", "1");
			value.put("e", "车");
			value.put("unitPrice", "998");
			value.put("totalPrice", "998");
			value.put("realPrice", "998");
			value.put("gongyingshang", "京东");
			goods.add(0, key);
			goods.add(1, value);
			json_text_.put("table", table);
			json_text_.put("goods", goods);
			break;
		case "1":
			table.put("payReason", "顺顺活得不如咸鱼！！");
			key.put("a", "物资名称");
			key.put("b", "具体型号配置");
			key.put("num", "数量");
			key.put("d", "单位");
			key.put("unitPrice", "采购单价/元");
			key.put("totalPrice", "采购总价/元");
			key.put("realPrice", "实付金额/元");
			key.put("gongyingshang", "推荐供应商");
			value.put("a", "手机");
			value.put("b", "苹果");
			value.put("num", "1");
			value.put("d", "车");
			value.put("unitPrice", "998");
			value.put("totalPrice", "998");
			value.put("realPrice", "999");
			value.put("gongyingshang", "二手东");
			goods.add(0, key);
			goods.add(1, value);
			json_text_.put("table", table);
			json_text_.put("goods", goods);
			break;
		case "2":
			table.put("payReason", "顺顺活得不如咸鱼！！");
			json_text_.put("table", table);
			json_text_.put("goods", goods);
			break;
		case "3":
			table.put("payReason", "顺顺活得不如咸鱼！！");
			json_text_.put("table", table);
			json_text_.put("goods", goods);
			break;
		case "4":
			table.put("payReason", "顺顺活得不如咸鱼！！");
			key.put("a", "名称");
			key.put("b", "品牌");
			key.put("c", "具体型号配置");
			key.put("num", "数量");
			key.put("d", "单位");
			key.put("unitPrice", "采购单价/元");
			key.put("totalPrice", "采购总价/元");
			key.put("realPrice", "实付金额/元");
			key.put("gongyingshang", "推荐供应商");
			value.put("a", "手机");
			value.put("b", "苹果");
			value.put("c", "苹果XSSSSSSS");
			value.put("num", "1");
			value.put("d", "车");
			value.put("unitPrice", "998");
			value.put("totalPrice", "998");
			value.put("realPrice", "999");
			value.put("gongyingshang", "二手东");
			goods.add(0, key);
			goods.add(1, value);
			json_text_.put("table", table);
			json_text_.put("goods", goods);
			break;
		case "5":
			table.put("payReason", "顺顺活得不如咸鱼！！");
			key.put("a", "地点及费用名称");
			key.put("b", "费用开始时间");
			key.put("c", "费用截至时间");
			key.put("d", "供应商");
			key.put("e", "单位");
			key.put("f", "开户行");
			key.put("g", "账号");
			key.put("totalPrice", "金额");
			value.put("a", "鼎泰买电脑");
			value.put("b", "2018-10-10");
			value.put("c", "2019-10-10");
			value.put("d", "某宝");
			value.put("e", "个");
			value.put("f", "大王庄银行");
			value.put("g", "88772288");
			value.put("totalPrice", "1998");
			goods.add(0, key);
			goods.add(1, value);
			json_text_.put("table", table);
			json_text_.put("goods", goods);
			break;
		case "6":
			table.put("payReason", "顺顺活得不如咸鱼！！");
			table.put("money_neibu_pay", "600");
			table.put("money_waibu_pay", "800");
			JSONArray table_neibu_pay = new JSONArray();
			JSONObject key_neibu = new JSONObject();
			key_neibu.put("totalPrice1", "金额/元");
			key_neibu.put("b1", "分类");
			key_neibu.put("c1", "供应商");
			key_neibu.put("d1", "备注");
			JSONObject value_neibu = new JSONObject();
			value_neibu.put("totalPrice1", "1000");
			value_neibu.put("b1", "工资");
			value_neibu.put("c1", "万贸");
			value_neibu.put("d1", "发工资");
			table_neibu_pay.add(0, key_neibu);
			table_neibu_pay.add(1, value_neibu);
			JSONArray table_waibu_pay = new JSONArray();
			JSONObject key_waibubu = new JSONObject();
			key_waibubu.put("totalPrice1", "金额/元");
			key_waibubu.put("b1", "分类");
			key_waibubu.put("c1", "供应商");
			key_waibubu.put("d1", "备注");
			JSONObject value_waibubu = new JSONObject();
			value_waibubu.put("totalPrice1", "1000");
			value_waibubu.put("b1", "工资");
			value_waibubu.put("c1", "万贸");
			value_waibubu.put("d1", "发工资");
			table_waibu_pay.add(0, key_waibubu);
			table_waibu_pay.add(1, value_waibubu);
			
			json_text_.put("table", table);
			json_text_.put("table_neibu_pay", table_neibu_pay);
			json_text_.put("table_waibu_pay", table_waibu_pay);
			break;
		case "7":
			table.put("payReason", "顺顺活得不如咸鱼！！");
			json_text_.put("table", table);
			break;
		default:
			System.out.println("PaymentApply: 木有这个采购类型");
			break;
		}
		para.put("json_text_", json_text_.toString());
		return para;
	}
	//采购付款：审批
	public Map<String, String> payment_sp_para (String paymentId, String taskId, String sp, String comment){
		Map<String, String> para = new HashMap<String, String>();
		para.put("paymentId", paymentId);
		para.put("taskId", taskId);
		para.put("sp", sp);
		para.put("comment", comment);
		para.put("money", "");
		para.put("jsonStr", "");
		return para;
	}
	//借款（除标书费）
	public Map<String, String> loan_para (String money_, String type_, String proj_, String proj_id_, String loadId){
		Map<String, String> para = new HashMap<String, String>();
		if (loadId != ""){
			para.put("id_", loadId);
		}
		para.put("money_", money_);
		para.put("type_", type_);
		para.put("proj_", proj_);
		para.put("proj_id_", proj_id_);
		para.put("fk_name_", "天津市万贸科技有限公司");
		para.put("skf_name_", "常小黑");
		para.put("khh_name_", "大王庄银行");
		para.put("khh_account_", "987654321");
		para.put("tip_", "长鹏ZZ");
		para.put("is_zd_proj_", "1");
		return para;
	}
	//借款（标书费）
	public Map<String, String> loan_bsf_para (String money_, String type_, String proj_, String proj_id_, String loadId){
		Map<String, String> para = new HashMap<String, String>();
		if (loadId != ""){
			para.put("id_", loadId);
		}
		para.put("money_", money_);
		para.put("type_", type_);
		para.put("proj_", proj_);
		para.put("proj_id_", proj_id_);
		para.put("fk_name_", "天津市万贸科技有限公司");
		para.put("skf_name_", "常小黑");
		para.put("khh_name_", "大王庄银行");
		para.put("khh_account_", "987654321");
		para.put("tip_", "长鹏ZZ");
		para.put("is_zd_proj_", "0");
		para.put("bd", "111");
		para.put("bh", "222");
		para.put("fileTag", "");
		JSONObject json_text_ = new JSONObject();
		json_text_.put("bd", "111");
		json_text_.put("bh", "222");
		para.put("json_text_", json_text_.toString());
		return para;
	}
	//借款：审批
	public Map<String, String> loan_sp_para (String loanId, String taskId, String sp, String comment){
		Map<String, String> para = new HashMap<String, String>();
		para.put("loanId", loanId);
		para.put("taskId", taskId);
		para.put("sp", sp);
		para.put("comment", comment);
		return para;
	}
	//开票：发起
	public Map<String, String> finIo_para(String finIoId ,String money_, String type_, String cpbId){
		Map<String, String> para = new HashMap<String, String>();
		para.put("id_", finIoId);
		para.put("money_", money_);
		para.put("type_", type_);
		para.put("contr_id_", cpbId);
		para.put("time_", "2018-11-23");
		para.put("content_", "大吃大喝");
		para.put("tip_", "餐饮补助");
		para.put("pa_name_", "大王庄饭馆");
		para.put("taxpayer_id_", "7622189987211");
		para.put("address_", "大王庄CBD");
		para.put("tel_", "110");
		para.put("khh_name_", "天津银行");
		para.put("khh_account_", "22110099888");
		para.put("company_", "天津市万贸科技有限公司");
		JSONArray goods_json_ = new JSONArray();
		JSONObject goods = new JSONObject();
		goods.put("goods_model_", "阳澄湖大闸蟹");
		goods.put("goods_count_", "2");
		goods.put("goods_unit_price_", "100");
		goods.put("goods_money_", 200);
		goods.put("tax_rate_", "13");
		goods_json_.add(goods);
		para.put("goods_json_", goods_json_.toString());
		return para;
	}
	//开票：审批
	public Map<String, String> finIo_sp_para (String finIoId, String taskId, String sp, String comment, String invoiceNum){
		Map<String, String> para = new HashMap<String, String>();
		para.put("finIoId", finIoId);
		para.put("taskId", taskId);
		para.put("sp", sp);
		para.put("comment", comment);
		para.put("invoiceNum", invoiceNum);
		return para;
	}
	//回款认领：发布
	public Map<String, String> mc_upsert_para (String mcId, String money_, String isHuiPiao){
		Map<String, String> para = new HashMap<String, String>();
		if (mcId != ""){
			para.put("id_", mcId);
		}
		para.put("money_", money_);
		para.put("isHuiPiao", isHuiPiao);
		switch (isHuiPiao){
		case "0":
			para.put("to_company_", "");
			para.put("to_account_", "万贸（民生）");
			break;
		case "1":
			para.put("to_company_", "天津市淘客科技有限公司");
			para.put("to_account_", "银行承兑汇票");
			break;
		default:
				System.out.println("是否银行承况汇票类型有误！");
		}
		para.put("source_company_", "大王庄CBD");
		para.put("due_date_", "2018-10-24");
		para.put("remark_", "从甲方爸爸身上薅下来的");
		return para;
	}
	//回款认领：认领
	public Map<String, String> mc_claim_para (String mcId, String type_, String ywId, String update_time_, String deptId){
		Map<String, String> para = new HashMap<String, String>();
		para.put("id", mcId);
		para.put("type_", type_);
		para.put("ywId", ywId);
		para.put("update_time_", update_time_);
		para.put("deptId", deptId);
		return para;
	}
	//回款认领：确认
	public Map<String, String> mc_approve_para (String mcId, String approved, String comment){
		Map<String, String> para = new HashMap<String, String>();
		para.put("id", mcId);
		para.put("approved", approved);
		para.put("comment", comment);
		return para;
	}
	//调账：确认
	public Map<String,String> money_adjust_approve_para(String code, String time_, String approved, String comment){
		Map<String, String> para = new HashMap<String, String>();
		para.put("code", code);
		para.put("time_", time_);
		para.put("approved", approved);
		para.put("comment", comment);
		return para;
	}
}
