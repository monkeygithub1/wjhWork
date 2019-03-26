package wjh.pmis.testcase.flow;

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
public class ProjFlow_999{
	
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
	String money_loan = "1100";
	String type_loan = "投标保证金";
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
	public void flow() throws ClientProtocolException, IOException, InterruptedException{
		//项目立项
		System.out.print("立项中…………………………");
		Map<String,String> proj = flow.proj(proj_name_);
		proj_id_ = proj.get("proj_id_");
		proj_name = proj.get("proj_name");
		proj_code = proj.get("proj_code");
		System.out.println("   OK!");
		//采购申请
		System.out.print("采购申请中…………………………");
		String purchase_type = "0";
		Map<String,String> purchase = flow.purchase(money_purchase, proj_id_, purchase_type);
		purchase_id_ = purchase.get("purchaseId");
		purchase_code_ = purchase.get("purchase_code_");
		System.out.println("   OK!");
		//甲方合同
		System.out.print("甲方合同申请中…………………………");
		cpaId = flow.cpa(cpa_name_, money_cpa, proj_name, proj_id_, purchase_code_, purchase_id_);
		System.out.println("   OK!");
		//乙方合同
		System.out.print("乙方合同申请中…………………………");
		cpbId = flow.cpb(cpb_name_, money_cpb, proj_name, proj_id_);
		System.out.println("   OK!");
		//费用报销
		System.out.println("费用报销申请中…………………………");
		flow.fybx(money_fybx, bxType, proj_id_, proj_code, proj_name);
		System.out.println("   OK!");
		//差旅报销
		System.out.println("差旅报销申请中…………………………");
		flow.clbx(money_clbx, proj_id_, proj_code, proj_name);
		System.out.println("   OK!");
		//采购付款
		System.out.println("采购付款申请中…………………………");
		flow.payment(money_payment, proj_id_, purchase_type, cpaId, cpbId, purchase_id_);
		System.out.println("   OK!");
		//借款
		System.out.println("借款申请中…………………………");
		loanId = flow.loan(money_loan, type_loan, proj_name, proj_id_);
		System.out.println("   OK!");
		//开票
		System.out.print("开票申请中…………………………");
		flow.finIo(money_finIo, type_finIo, cpbId);
		System.out.println("   OK!");
		//回款认领
		System.out.print("回款认领中…………………………");
		flow.mc(money_mc, "proj", cpbId, proj_id_);//proj时为cpbId,bzj时为loanId
		flow.mc(money_mc, "bzj", loanId, proj_id_);
		System.out.println("   OK!");
	}
	
	
}
