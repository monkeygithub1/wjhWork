package testSuit;

import org.testng.annotations.Test;

import framework.BrowserEngine;
import pageObject.PMIS_Page;
import pageObject.PMIS_XiangMuLiXiang_Page;

import org.testng.annotations.BeforeClass;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;

public class projNew {
	WebDriver driver;
//	PMIS_Page pmis = PageFactory.initElements(driver, PMIS_Page.class);
//	PMIS_XiangMuLiXiang_Page pmis_proj = PageFactory.initElements(driver, PMIS_XiangMuLiXiang_Page.class);
	String username = "18795039941";//����������Ŀ����
	String username_syb = "13612194369";
	String username_azb = "18502285517";
	String username_sc = "18602270056";
	String passwd = "qw";
	String projName = "�׷���ͬר����Ŀ1";
	String PM = "������";
	String dept = "�����ҵ��";
	String cydept = "���벿��-�Ǳ��";
	String quyu = "��������";
 	String kehu = "�ͻ�1-2-3-13910623294";
 	String xingzhi = "ǣͷ";
 	String type = "R���о���";
 	String yuji = "10000";
 	String descript = "��Ŀ�����Ǳ���";
 	String kphte = "5000";
	
	@BeforeClass
	public void setUp() throws IOException {
		BrowserEngine browserEngine = new BrowserEngine();
		browserEngine.initConfigData();
		driver = browserEngine.getBrowser();
	}
	
	@Test//�½���Ŀ����
	public void applyNewProj() throws InterruptedException {
		PMIS_Page pmis = PageFactory.initElements(driver, PMIS_Page.class);
		PMIS_XiangMuLiXiang_Page pmis_proj = PageFactory.initElements(driver, PMIS_XiangMuLiXiang_Page.class);
		Thread.sleep(1000);
		pmis.login(username, passwd);
		Thread.sleep(1000);
		pmis_proj.applyNewProj(projName, PM, dept, cydept, quyu, kehu, xingzhi, type, yuji, descript, kphte);
	}
//	@Test(dependsOnMethods={"applyNewProj"})//���Ÿ�����
//	public void shenpi_dept() throws InterruptedException{
//		PMIS_Page pmis = PageFactory.initElements(driver, PMIS_Page.class);
//		pmis.loginSwitch(username, passwd);
//		PMIS_XiangMuLiXiang_Page pmis_proj = PageFactory.initElements(driver, PMIS_XiangMuLiXiang_Page.class);
//		pmis_proj.spAgree("OK");
//	}
	@Test(dependsOnMethods={"applyNewProj"})//��ҵ���ܾ�������
	public void shenpi_syb() throws InterruptedException{
		PMIS_Page pmis = PageFactory.initElements(driver, PMIS_Page.class);
		pmis.loginSwitch(username_syb, passwd);
		PMIS_XiangMuLiXiang_Page pmis_proj = PageFactory.initElements(driver, PMIS_XiangMuLiXiang_Page.class);
		pmis_proj.spAgree("OK");
	}
	@Test(dependsOnMethods={"shenpi_syb"})//���ʲ�����
	public void shenpi_azb() throws InterruptedException{
		PMIS_Page pmis = PageFactory.initElements(driver, PMIS_Page.class);
		pmis.loginSwitch(username_azb, passwd);
		PMIS_XiangMuLiXiang_Page pmis_proj = PageFactory.initElements(driver, PMIS_XiangMuLiXiang_Page.class);
		pmis_proj.spAgree("OK");
	}
	@Test(dependsOnMethods={"shenpi_azb"})//�����ܾ�������
	public void shenpi_sc() throws InterruptedException{
		PMIS_Page pmis = PageFactory.initElements(driver, PMIS_Page.class);
		pmis.loginSwitch(username_sc, passwd);
		PMIS_XiangMuLiXiang_Page pmis_proj = PageFactory.initElements(driver, PMIS_XiangMuLiXiang_Page.class);
		pmis_proj.spAgree("OK");
	}

	@AfterClass
	public void afterClass() {
		
	}

}
