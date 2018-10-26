package wjh.pmis.demo;

import org.testng.annotations.Test;

import wjh.pmis.data.RequestFlow;

import java.io.IOException;
import java.util.Map;
import org.apache.http.client.ClientProtocolException;
import org.testng.annotations.BeforeClass;

	/*
	 * 项目流水-整体流程
	 * 职能部门（安质部）
	 */
public class ProjFlowDemo{
	
	//项目立项数据
	String proj_name_= "资金流水";
	//甲方合同数据
	String cpa_name_= "甲方合同-资金流水";
	String money_cpa = "10000";
	//乙方合同数据
	String cpb_name_= "乙方合同-资金流水";
	String money_cpb = "10000";
	//费用报销
	String money_fybx = "500";
	String bxType = "办公其他";
	//差旅报销
	double money_clbx = 500;
	//采购申请
	String money_purchase = "10000";
	//采购付款
	String money_payment = "1000";
	//借款
	String money_loan = "700";
	String type_loan = "差旅费";
	//开票
	String type_finIo = "增值税专用发票";
	String money_finIo = "5000";
	RequestFlow flow = new RequestFlow();
	//回款认领
	String type_mc = "proj";
	String money_mc = "1000";
	//各模块之间流转的变量
	String proj_id_ = "";
	String proj_name = "";
	String proj_code = "";
	String purchase_code_= "";
	String purchase_id_= "";
	String cpaId = "";
	String cpbId = "";
	String loanId = "";
	
	@BeforeClass
	public void setUp() {
	}
	@Test
	public void flow() throws ClientProtocolException, IOException{
		//项目立项
		Map<String,String> proj = flow.proj(proj_name_);
		proj_id_ = proj.get("proj_id_");
		proj_name = proj.get("proj_name");
		proj_code = proj.get("proj_code");
		//采购申请
		String purchase_type = "0";
		Map<String,String> purchase = flow.purchase(money_purchase, proj_id_, purchase_type);
		purchase_id_ = purchase.get("purchaseId");
		purchase_code_ = purchase.get("purchase_code_");
		//甲方合同
		cpaId = flow.cpa(cpa_name_, money_cpa, proj_name, proj_id_, purchase_code_, purchase_id_);
		//乙方合同
		cpbId = flow.cpb(cpb_name_, money_cpb, proj_name, proj_id_);
		//费用报销
		flow.fybx(money_fybx, bxType, proj_id_, proj_code, proj_name);
		//差旅报销
		flow.clbx(money_clbx, proj_id_, proj_code, proj_name);
		//采购付款
		flow.payment(money_payment, proj_id_, purchase_type, cpaId, cpbId, purchase_id_);
		//借款
		loanId = flow.loan(money_loan, type_loan, proj_name, proj_id_);
		//开票
		flow.finIo(money_finIo, type_finIo, cpbId);
		//回款认领
		flow.mc(money_mc, type_mc, cpbId);//proj时为cpbId,bzj时为loanId
	}
	
	
}
