package cpaTest;

import org.testng.annotations.Test;

import framework.BrowserEngine;
import pageObjects.PMIS_JiaFangHeTong_Page;
import pageObjects.PMIS_Page;

import org.testng.annotations.BeforeClass;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;

/*
 * 甲方合同
 * 书面
 * 普通部门（创新业务部）
 * 物资采购
 * 每个节点拒绝重发（不修改）
 */
public class Cpa_021 {
	WebDriver driver;
	String username = "13910623294";
	String username_fawu = "18602634891";//法务
	String username_dept = "13752506056";//创新业务部
	String username_sybzjl = "13612194369";
	String username_sybdsz = "15522219091";
	String username_anzhi = "18822075856";
	String username_caiwu = "18222105899";
	String username_wuzi = "13820781432";
	String username_fg= "13502103187";
	//String username_fg_zhineng= "18602270056";//安质对应分管领导
	String passwd = "qw";
	String cpa_name = "自动化甲方合同-书面-创新业务部-每个节点拒绝重发（不修改）";
	String cpa_money = "998";
	String cpa_dept = "创新业务部";
	String cpa_time = "1";
 	String cpa_xingzhi = "书面";
 	String cpa_type = "物资采购";
 	String cpa_jia = "天津市万贸科技有限公司";
 	String cpa_yi = "国网";
 	String proj_name="自动化项目立项320180207111311";
 	String cpa_descript = "备注非必填";
 	String cpa_moneyOut_bili = "10:0";
 	String cpa_moneyOut_date1 = "2018-02-01";
 	String cpa_invoiceIn_bili = "10:0";
 	String cpa_invoiceIn_date1 = "2018-03-01";
	
 	@BeforeClass
  	public void setUp() throws IOException {
  		BrowserEngine browserEngine = new BrowserEngine();
  		browserEngine.initConfigData();
  		driver = browserEngine.getBrowser();
  	}
 	
 	
 	@Test//发起-拒绝
	public void applyNewCpa() throws InterruptedException {
		PMIS_Page pmis = PageFactory.initElements(driver, PMIS_Page.class);
		PMIS_JiaFangHeTong_Page pmis_cpa = PageFactory.initElements(driver, PMIS_JiaFangHeTong_Page.class);
		Thread.sleep(1000);
		//发起
		pmis.login(username, passwd);
		Thread.sleep(1000);
		pmis_cpa.applyNewCpa(cpa_name, cpa_money, cpa_dept, cpa_time, cpa_xingzhi, cpa_type, cpa_jia, cpa_yi, proj_name, cpa_descript, cpa_moneyOut_bili, cpa_moneyOut_date1, cpa_invoiceIn_bili, cpa_invoiceIn_date1);
		//部门审批-第1次拒绝
		pmis.loginSwitch(username_dept, passwd);
		pmis_cpa.cpaSpRefuse("不OK");
		}
 	@Test(priority=1)//第1次拒绝重发
	public void applyNewCpa_edit1() throws InterruptedException {
		PMIS_Page pmis = PageFactory.initElements(driver, PMIS_Page.class);
		PMIS_JiaFangHeTong_Page pmis_cpa = PageFactory.initElements(driver, PMIS_JiaFangHeTong_Page.class);
		Thread.sleep(1000);
		//编辑
		pmis.loginSwitch(username, passwd);
		Thread.sleep(1000);
		pmis_cpa.simpleCpaEdit(cpa_name+"1", cpa_dept, cpa_xingzhi, cpa_type);
		//部门审批
		pmis.loginSwitch(username_dept, passwd);
		pmis_cpa.cpaSpAgree("OK");
		//法务审批-第2次拒绝
		pmis.loginSwitch(username_fawu, passwd);
		pmis_cpa.cpaSpRefuse("不OK");
	}
 	@Test(priority=2)//第2次拒绝重发
	public void applyNewCpa_edit2() throws InterruptedException {
		PMIS_Page pmis = PageFactory.initElements(driver, PMIS_Page.class);
		PMIS_JiaFangHeTong_Page pmis_cpa = PageFactory.initElements(driver, PMIS_JiaFangHeTong_Page.class);
		Thread.sleep(1000);
		//编辑
		pmis.loginSwitch(username, passwd);
		Thread.sleep(1000);
		pmis_cpa.simpleCpaEdit(cpa_name+"2", cpa_dept, cpa_xingzhi, cpa_type);
		//部门审批
		pmis.loginSwitch(username_dept, passwd);
		pmis_cpa.cpaSpAgree("OK");
		//法务审批
		pmis.loginSwitch(username_fawu, passwd);
		pmis_cpa.cpaSpAgree("OK");
	 	//事业部总经理审批-第3次拒绝
		pmis.loginSwitch(username_sybzjl, passwd);
		pmis_cpa.cpaSpRefuse("不OK");
	}
 	@Test(priority=3)//第3次拒绝重发
	public void applyNewCpa_edit3() throws InterruptedException {
		PMIS_Page pmis = PageFactory.initElements(driver, PMIS_Page.class);
		PMIS_JiaFangHeTong_Page pmis_cpa = PageFactory.initElements(driver, PMIS_JiaFangHeTong_Page.class);
		Thread.sleep(1000);
		//编辑
		pmis.loginSwitch(username, passwd);
		Thread.sleep(1000);
		pmis_cpa.simpleCpaEdit(cpa_name+"3", cpa_dept, cpa_xingzhi, cpa_type);
		//部门审批
		pmis.loginSwitch(username_dept, passwd);
		pmis_cpa.cpaSpAgree("OK");
		//法务审批
		pmis.loginSwitch(username_fawu, passwd);
		pmis_cpa.cpaSpAgree("OK");
		//事业部总经理审批
		pmis.loginSwitch(username_sybzjl, passwd);
		pmis_cpa.cpaSpAgree("OK");
	 	//事业部董事长审批-第4次拒绝
		pmis.loginSwitch(username_sybdsz, passwd);
		pmis_cpa.cpaSpRefuse("不OK");
	}
 	@Test(priority=4)//第4次拒绝重发
	public void applyNewCpa_edit4() throws InterruptedException {
		PMIS_Page pmis = PageFactory.initElements(driver, PMIS_Page.class);
		PMIS_JiaFangHeTong_Page pmis_cpa = PageFactory.initElements(driver, PMIS_JiaFangHeTong_Page.class);
		Thread.sleep(1000);
		//编辑
		pmis.loginSwitch(username, passwd);
		Thread.sleep(1000);
		pmis_cpa.simpleCpaEdit(cpa_name+"4", cpa_dept, cpa_xingzhi, cpa_type);
		//部门审批
		pmis.loginSwitch(username_dept, passwd);
		pmis_cpa.cpaSpAgree("OK");
		//法务审批
		pmis.loginSwitch(username_fawu, passwd);
		pmis_cpa.cpaSpAgree("OK");
		//事业部总经理审批
		pmis.loginSwitch(username_sybzjl, passwd);
		pmis_cpa.cpaSpAgree("OK");
		//事业部董事长审批
		pmis.loginSwitch(username_sybdsz, passwd);
		pmis_cpa.cpaSpAgree("OK");
	 	//安质审批-第5次拒绝
		pmis.loginSwitch(username_anzhi, passwd);
		pmis_cpa.cpaSpRefuse("不OK");
	}
 	@Test(priority=5)//第5次拒绝重发
	public void applyNewCpa_edit5() throws InterruptedException {
		PMIS_Page pmis = PageFactory.initElements(driver, PMIS_Page.class);
		PMIS_JiaFangHeTong_Page pmis_cpa = PageFactory.initElements(driver, PMIS_JiaFangHeTong_Page.class);
		Thread.sleep(1000);
		//编辑
		pmis.loginSwitch(username, passwd);
		Thread.sleep(1000);
		pmis_cpa.simpleCpaEdit(cpa_name+"5", cpa_dept, cpa_xingzhi, cpa_type);
		//部门审批
		pmis.loginSwitch(username_dept, passwd);
		pmis_cpa.cpaSpAgree("OK");
		//法务审批
		pmis.loginSwitch(username_fawu, passwd);
		pmis_cpa.cpaSpAgree("OK");
		//事业部总经理审批
		pmis.loginSwitch(username_sybzjl, passwd);
		pmis_cpa.cpaSpAgree("OK");
		//事业部董事长审批
		pmis.loginSwitch(username_sybdsz, passwd);
		pmis_cpa.cpaSpAgree("OK");
		//安质审批
		pmis.loginSwitch(username_anzhi, passwd);
		pmis_cpa.cpaSpAgree("OK");
		//财务审批-第6次拒绝
		pmis.loginSwitch(username_caiwu, passwd);
		pmis_cpa.cpaSpRefuse("不OK");
	}
 	@Test(priority=6)//第6次拒绝重发
	public void applyNewCpa_edit6() throws InterruptedException {
		PMIS_Page pmis = PageFactory.initElements(driver, PMIS_Page.class);
		PMIS_JiaFangHeTong_Page pmis_cpa = PageFactory.initElements(driver, PMIS_JiaFangHeTong_Page.class);
		Thread.sleep(1000);
		//编辑
		pmis.loginSwitch(username, passwd);
		Thread.sleep(1000);
		pmis_cpa.simpleCpaEdit(cpa_name+"6", cpa_dept, cpa_xingzhi, cpa_type);
		//部门审批
		pmis.loginSwitch(username_dept, passwd);
		pmis_cpa.cpaSpAgree("OK");
		//法务审批
		pmis.loginSwitch(username_fawu, passwd);
		pmis_cpa.cpaSpAgree("OK");
		//事业部总经理审批
		pmis.loginSwitch(username_sybzjl, passwd);
		pmis_cpa.cpaSpAgree("OK");
		//事业部董事长审批
		pmis.loginSwitch(username_sybdsz, passwd);
		pmis_cpa.cpaSpAgree("OK");
		//安质审批
		pmis.loginSwitch(username_anzhi, passwd);
		pmis_cpa.cpaSpAgree("OK");
		//财务审批
		pmis.loginSwitch(username_caiwu, passwd);
		pmis_cpa.cpaSpAgree("OK");
		//物资审批-第7次拒绝
		pmis.loginSwitch(username_wuzi, passwd);
		pmis_cpa.cpaSpRefuse("不OK");
	}
 	@Test(priority=7)//第7次拒绝重发
	public void applyNewCpa_edit7() throws InterruptedException {
		PMIS_Page pmis = PageFactory.initElements(driver, PMIS_Page.class);
		PMIS_JiaFangHeTong_Page pmis_cpa = PageFactory.initElements(driver, PMIS_JiaFangHeTong_Page.class);
		Thread.sleep(1000);
		//编辑
		pmis.loginSwitch(username, passwd);
		Thread.sleep(1000);
		pmis_cpa.simpleCpaEdit(cpa_name+"7", cpa_dept, cpa_xingzhi, cpa_type);
		//部门审批
		pmis.loginSwitch(username_dept, passwd);
		pmis_cpa.cpaSpAgree("OK");
		//法务审批
		pmis.loginSwitch(username_fawu, passwd);
		pmis_cpa.cpaSpAgree("OK");
		//事业部总经理审批
		pmis.loginSwitch(username_sybzjl, passwd);
		pmis_cpa.cpaSpAgree("OK");
		//事业部董事长审批
		pmis.loginSwitch(username_sybdsz, passwd);
		pmis_cpa.cpaSpAgree("OK");
		//安质审批
		pmis.loginSwitch(username_anzhi, passwd);
		pmis_cpa.cpaSpAgree("OK");
		//财务审批
		pmis.loginSwitch(username_caiwu, passwd);
		pmis_cpa.cpaSpAgree("OK");
		//物资审批
		pmis.loginSwitch(username_wuzi, passwd);
		pmis_cpa.cpaSpAgree("OK");
		//分管领导审批-第8次拒绝
		pmis.loginSwitch(username_fg, passwd);
		pmis_cpa.cpaSpRefuse("不OK");
	}
 	@Test(priority=8)//第8次拒绝重发
	public void applyNewCpa_edit8() throws InterruptedException {
		PMIS_Page pmis = PageFactory.initElements(driver, PMIS_Page.class);
		PMIS_JiaFangHeTong_Page pmis_cpa = PageFactory.initElements(driver, PMIS_JiaFangHeTong_Page.class);
		Thread.sleep(1000);
		//编辑
		pmis.loginSwitch(username, passwd);
		Thread.sleep(1000);
		pmis_cpa.simpleCpaEdit(cpa_name+"8", cpa_dept, cpa_xingzhi, cpa_type);
		//部门审批
		pmis.loginSwitch(username_dept, passwd);
		pmis_cpa.cpaSpAgree("OK");
		//法务审批
		pmis.loginSwitch(username_fawu, passwd);
		pmis_cpa.cpaSpAgree("OK");
		//事业部总经理审批
		pmis.loginSwitch(username_sybzjl, passwd);
		pmis_cpa.cpaSpAgree("OK");
		//事业部董事长审批
		pmis.loginSwitch(username_sybdsz, passwd);
		pmis_cpa.cpaSpAgree("OK");
		//安质审批
		pmis.loginSwitch(username_anzhi, passwd);
		pmis_cpa.cpaSpAgree("OK");
		//财务审批
		pmis.loginSwitch(username_caiwu, passwd);
		pmis_cpa.cpaSpAgree("OK");
		//物资审批
		pmis.loginSwitch(username_wuzi, passwd);
		pmis_cpa.cpaSpAgree("OK");
		//分管领导审批
		pmis.loginSwitch(username_fg, passwd);
		pmis_cpa.cpaSpAgree("OK");
//		//安质对应分管领导审批
//		pmis.loginSwitch(username_fg_zhineng, passwd);
//		pmis_cpa.cpaSpAgree("OK");
	}

	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
	

}
