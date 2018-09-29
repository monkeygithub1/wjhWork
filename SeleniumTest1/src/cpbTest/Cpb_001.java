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
 * �ҷ���ͬ
 * ����
 * ��ͨ���ţ�����ҵ�񲿣�
 * ���̽���
 * һ����ͨ��
 */
public class Cpb_001 {
	WebDriver driver;
	String username = "13910623294";
	String username_fawu = "18602634891";//����
	String username_sc = "18602270056";
	String username_dept = "13752506056";//����ҵ��
	String username_sybzjl = "13612194369";
	String username_sybdsz = "15522219091";
	String passwd = "qw";
	String cpb_name = "�Զ����ҷ���ͬ-����-����ҵ��";
	String cpb_money = "1000";
	String cpb_dept = "����ҵ��";
	String cpb_time = "1";
 	String cpb_xingzhi = "����";
 	String cpb_type = "���̽���";
 	String cpb_jia = "����";
 	String cpb_yi = "�������ó�Ƽ����޹�˾";
 	String proj_name="��ͬר����Ŀ1";
 	String cpb_descript = "��ע�Ǳ���";
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
		//����
		pmis.login(username, passwd);
		Thread.sleep(1000);
		pmis_cpb.applyNewCpb(cpb_name, cpb_money, cpb_dept, cpb_time, cpb_xingzhi, cpb_type, cpb_jia, cpb_yi, proj_name, cpb_descript, 
				cpb_moneyIn_bili, cpb_moneyIn_date1, cpb_invoiceOut_bili, cpb_invoiceOut_date1);
		//��������
		pmis.loginSwitch(username_dept, passwd);
		pmis_cpb.cpbSpAgree("OK");
		//��������
		pmis.loginSwitch(username_fawu, passwd);
		pmis_cpb.cpbSpAgree("OK");
	 	//��ҵ���ܾ�������
		pmis.loginSwitch(username_sybzjl, passwd);
		pmis_cpb.cpbSpAgree("OK");
	 	//��ҵ�����³�����
		pmis.loginSwitch(username_sybdsz, passwd);
		pmis_cpb.cpbSpAgree("OK");
	}
 	//@Test(priority=1)//��������
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