package wjh.pmis.demo;

import org.testng.annotations.Test;

import wjh.pmis.data.RequestFlow;
import wjh.pmis.restclient.RestClient;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.client.ClientProtocolException;
import org.testng.annotations.BeforeClass;

	/*
	 * 项目流水-整体流程
	 */
public class ProjFlowDemo{
	String name = "父子合同展示修改";
	//项目立项数据
	String proj_name_= name + "专用项目";
	//甲方合同数据
	String cpa_name_= name + "-甲方合同";
	String money_cpa = "15000";
	//乙方合同数据
	String cpb_name_= name + "-乙方合同";
	String money_cpb = "20000";
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
	String money_mc = "15000";
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
	public void setUp() throws InterruptedException {
		RestClient restClient = new RestClient();
		restClient.mi_xin();
	}
	//数据流程、资金流水校验
	@Test(priority=0)
	public void flow() throws ClientProtocolException, IOException, InterruptedException{
		//项目立项
		System.out.print("立        项        中…………………………");
		Map<String,String> proj = flow.proj(proj_name_);
		proj_id_ = proj.get("proj_id_");
		proj_name = proj.get("proj_name");
		proj_code = proj.get("proj_code");
		System.out.println("   OK!");
		//采购申请
		System.out.print("采   购   申   请   中…………………………");
		String purchase_type = "0";//该方法仅限自用物资
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
		flow.mc("500", "bzj", loanId, proj_id_);
		System.out.println("   OK!");
	}
	//项目经理看板-健康卡校验
	@Test(priority=1)
	public void healthCheck() throws ClientProtocolException, IOException{
		System.out.println("项目健康卡校验中…………………………");
		Map<String,String> flow_money = new HashMap<String,String>();
		flow_money.put("contr_money_", money_cpb);//合同额
		flow_money.put("cost_", "3100");//成本
		flow_money.put("profit_", "16900");//利润
		flow_money.put("money_in_fin_", "-10000");///财务应收
		flow_money.put("money_in_pro_", "-10000");//生产应收
		flow_money.put("m_io_ed_", "5000");//开票额（审批通过）
		flow_money.put("money_in_", "15000");//回款
		flow.healthCheck(proj_id_, flow_money);
		System.out.println("   OK!");
	}
	//成本管控统计
	@Test(priority=2)
	public void budgetCheck() throws ClientProtocolException, IOException{
		System.out.print("成本管控统计校验中…………………………");
		String actual_cl = "500";
		String buget_cl = "910";
		String actual_cg = "1000";
		String buget_cg = "2550";
		String actual_bg = "500";
		String buget_bg = "1340";
		String actual_yx = "1100";
		String buget_yx = "0";
		String actual_rycb = "0";
		String buget_rycb = "300";
		String actual_sj = "0";
		String buget_sj = "0";
		String actual_jj = "0";
		String buget_jj = "0";
		String actual_cwfy = "0";
		String buget_cwfy = "0";
		String actual_qt = "0";
		String buget_qt = "150";
		flow.budgetCheck(proj_id_, "差旅", actual_cl, buget_cl);
		flow.budgetCheck(proj_id_, "采购", actual_cg, buget_cg);
		flow.budgetCheck(proj_id_, "办公", actual_bg, buget_bg);
		flow.budgetCheck(proj_id_, "营销", actual_yx, buget_yx);
		flow.budgetCheck(proj_id_, "人员成本", actual_rycb, buget_rycb);
		flow.budgetCheck(proj_id_, "税金", actual_sj, buget_sj);
		flow.budgetCheck(proj_id_, "奖金", actual_jj, buget_jj);
		flow.budgetCheck(proj_id_, "财务费用", actual_cwfy, buget_cwfy);
		flow.budgetCheck(proj_id_, "其他", actual_qt, buget_qt);
		System.out.println("   OK!");
		
	}
	
}
