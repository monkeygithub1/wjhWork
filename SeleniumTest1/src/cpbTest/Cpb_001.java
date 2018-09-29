package cpbTest;

import org.testng.annotations.Test;

import framework.BrowserEngine;
import pageObjects.PMIS_Page;
import pageObjects.PMIS_YiFangHeTong_Page;

import org.testng.annotations.BeforeClass;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
/*
 * 乙方合同
 * 书面
 * 普通部门（创新业务部）
 * 工程建设
 * 一次性通过
 */
public class Cpb_001 {
	WebDriver driver;
	String username = "13910623294";
	String username_fawu = "18602634891";//法务
	String username_sc = "18602270056";
	String username_dept = "13752506056";//创新业务部
	String username_sybzjl = "13612194369";
	String username_sybdsz = "15522219091";
	String passwd = "qw";
	String cpb_name = "自动化乙方合同-书面-创新业务部";
	String cpb_money = "1000";
	String cpb_dept = "创新业务部";
	String cpb_time = "1";
 	String cpb_xingzhi = "书面";
 	String cpb_type = "工程建设";
 	String cpb_jia = "国网";
 	String cpb_yi = "天津市万贸科技有限公司";
 	String proj_name="合同专用项目1";
 	String cpb_descript = "备注非必填";
 	String cpb_moneyIn_bili = "10:0";
 	String cpb_moneyIn_date1 = "2018-02-01";
 	String cpb_invoiceOut_bili = "10:0";
 	String cpb_invoiceOut_date1 = "2018-03-01";
	
 	@BeforeClass
  	public void setUp() throws IOException {
  		BrowserEngine browserEngine = new BrowserEngine();
  		browserEngine.initConfigData();
  		driver = browserEngine.getBrowser();
  	}
 	
 	@Test
	public void applyNewcpb() throws InterruptedException {
		PMIS_Page pmis = PageFactory.initElements(driver, PMIS_Page.class);
		PMIS_YiFangHeTong_Page pmis_cpb = PageFactory.initElements(driver, PMIS_YiFangHeTong_Page.class);
		Thread.sleep(1000);
		//发起
		pmis.login(username, passwd);
		Thread.sleep(1000);
		pmis_cpb.applyNewCpb(cpb_name, cpb_money, cpb_dept, cpb_time, cpb_xingzhi, cpb_type, cpb_jia, cpb_yi, proj_name, cpb_descript, 
				cpb_moneyIn_bili, cpb_moneyIn_date1, cpb_invoiceOut_bili, cpb_invoiceOut_date1);
		//部门审批
		pmis.loginSwitch(username_dept, passwd);
		pmis_cpb.cpbSpAgree("OK");
		//法务审批
		pmis.loginSwitch(username_fawu, passwd);
		pmis_cpb.cpbSpAgree("OK");
	 	//事业部总经理审批
		pmis.loginSwitch(username_sybzjl, passwd);
		pmis_cpb.cpbSpAgree("OK");
	 	//事业部董事长审批
		pmis.loginSwitch(username_sybdsz, passwd);
		pmis_cpb.cpbSpAgree("OK");
	}
 	//@Test(priority=1)//法务审批
//	public void shenpi_fawu() throws InterruptedException{
//		PMIS_Page pmis = PageFactory.initElements(driver, PMIS_Page.class);
//		pmis.loginSwitch(username_fawu, passwd);
//		PMIS_JiaFangHeTong_Page pmis_cpb = PageFactory.initElements(driver, PMIS_JiaFangHeTong_Page.class);
//		pmis_cpb.cpbSpAgree("OK");
//	}

	@AfterClass
	public void afterClass() {
		
	}

}