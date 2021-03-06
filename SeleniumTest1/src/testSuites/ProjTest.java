package testSuites;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import framework.BrowserEngine;
import pageObjects.PMIS_Page;
import pageObjects.PMIS_XiangMuLiXiang_Page;

public class ProjTest {
	WebDriver driver;
	String username = "18795039941";//崔永欢，项目经理
	String username_syb = "13612194369";
	String username_azb = "18502285517";
	String username_sc = "18602270056";
	String passwd = "qetqet";
	String projName = "安全质量部如何打爆常庆顺的狗头";
	String PM = "崔永欢";
	String dept = "安全质量部";
	String cydept = "参与部门-非必填！";
	String quyu = "华北区域";
 	String kehu = "客户1-2-3-13910623294";
 	String xingzhi = "牵头";
 	String type = "R（研究）";
 	String biz = "企业级业务咨询-管理咨询-管理标准化咨询-无-无";
 	String yuji = "10000";
 	String descript = "项目概述非必填";
 	String kphte = "5000";
	
	@BeforeClass
	public void setUp() throws IOException {
		BrowserEngine browserEngine = new BrowserEngine();
		browserEngine.initConfigData();
		driver = browserEngine.getBrowser();
	}
	
	@Test//新建项目立项
	public void applyNewProj() throws InterruptedException {
		PMIS_Page pmis = PageFactory.initElements(driver, PMIS_Page.class);
		PMIS_XiangMuLiXiang_Page pmis_proj = PageFactory.initElements(driver, PMIS_XiangMuLiXiang_Page.class);
		Thread.sleep(1000);
		pmis.login(username, passwd);
		Thread.sleep(1000);
		pmis_proj.applyNewProj(projName, PM, dept, cydept, quyu, kehu, xingzhi, biz, type, yuji, descript, kphte);
	}

}
