package wjh.pmis.testcase.money_adjust;

import java.io.IOException;
import java.util.Map;
import org.apache.http.client.ClientProtocolException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import wjh.pmis.data.DeptAndPhone;
import wjh.pmis.data.Request;
import wjh.pmis.restclient.RestClient;
	
	/*
	 * 调账-无发起，仅审批
	 * A方和B方需手动配
	 * C方写了所有的人
	 * 没有加任何断言
	 */
public class MoneyAdjust_099 {
	DeptAndPhone dept_phone = new DeptAndPhone();
	//A
	//发起人&登录人
//	String phone_a_pm = "13910623294";
	String phone_a_pm = "18920879268";//王素爽
	
	String phone_a_dept = "18502285517";
	String phone_a_sybzjl = "13502103187";
	String phone_a_sybdsz = "";
	
	//信息事业部-项目实施部
	String phone_a_dept2 = "15900289062";
	String phone_a_sybzjl2 = "13512968527";
	String phone_a_sybdsz2 = "18602270056";
	//B
	String phone_b_pm_1 = "13752179542";
	String phone_b_dept_1 = "13920714935";
	String phone_b_sybzjl_1 = "15822417788";
	String phone_b_sybdsz_1 = "18502285589";
	//C
	String phone_boss = dept_phone.phone_boss;
	String phone_c1 = dept_phone.phone_money_adjust_c_1;
	String phone_c2 = dept_phone.phone_money_adjust_c_2;
	String phone_c3 = dept_phone.phone_money_adjust_c_3;
	String phone_c4 = dept_phone.phone_money_adjust_c_4;
	String phone_c5 = dept_phone.phone_money_adjust_c_5;
	String phone_c6 = dept_phone.phone_money_adjust_c_6;
	
	RestClient restClient = new RestClient();
	Request request = new Request();
	@BeforeClass
	public void setUp() {
	}
	//一次性通过
	@Test
	public void moneyAdjustDemo() throws ClientProtocolException, IOException {
		Map<String, String> headermap = restClient.header();//获取信息头（带cookie）
		request.login(headermap, phone_a_pm);//登录
		String code = request.getMoneyAdjustCode(headermap);
		String time_ = request.getMoneyAdjustTime(headermap);
		//A方
		request.moneyAdjustApprove(headermap, phone_a_pm, code, time_, "1", "刚哥说顺顺是SX");
		request.moneyAdjustApprove(headermap, phone_a_dept, code, time_, "1", "刚哥说顺顺是SX");
		request.moneyAdjustApprove(headermap, phone_a_sybzjl, code, time_, "1", "刚哥说顺顺是SX");
//		request.moneyAdjustApprove(headermap, phone_a_sybdsz, code, time_, "1", "刚哥说顺顺是SX");
		request.moneyAdjustApprove(headermap, phone_a_dept2, code, time_, "1", "刚哥说顺顺是SX");
		request.moneyAdjustApprove(headermap, phone_a_sybzjl2, code, time_, "1", "刚哥说顺顺是SX");
		request.moneyAdjustApprove(headermap, phone_a_sybdsz2, code, time_, "1", "刚哥说顺顺是SX");
		//B方
		request.moneyAdjustApprove(headermap, phone_b_pm_1, code, time_, "1", "刚哥说顺顺是SX");
		request.moneyAdjustApprove(headermap, phone_b_dept_1, code, time_, "1", "刚哥说顺顺是SX");
		request.moneyAdjustApprove(headermap, phone_b_sybzjl_1, code, time_, "1", "刚哥说顺顺是SX");
		request.moneyAdjustApprove(headermap, phone_b_sybdsz_1, code, time_, "1", "刚哥说顺顺是SX");
		//C方
		request.moneyAdjustApprove(headermap, phone_boss, code, time_, "1", "刚哥说顺顺是SX");
		request.moneyAdjustApprove(headermap, phone_c1, code, time_, "1", "刚哥说顺顺是SX");
		request.moneyAdjustApprove(headermap, phone_c2, code, time_, "1", "刚哥说顺顺是SX");
		request.moneyAdjustApprove(headermap, phone_c3, code, time_, "1", "刚哥说顺顺是SX");
		request.moneyAdjustApprove(headermap, phone_c4, code, time_, "1", "刚哥说顺顺是SX");
		request.moneyAdjustApprove(headermap, phone_c5, code, time_, "1", "刚哥说顺顺是SX");
		request.moneyAdjustApprove(headermap, phone_c6, code, time_, "1", "刚哥说顺顺是SX");
	}
}
