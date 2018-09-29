package cpaTest;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import framework.BrowserEngine;
import pageObjects.PMIS_JiaFangHeTong_Page;
import pageObjects.PMIS_Page;

/*
 * �׷���ͬ
 * ����
 * ְ�ܲ��ţ���ȫ��������
 * �������
 * �°�����
 */
public class CpaNew_001 {
	WebDriver driver;
	String username = "13910623294";
	String username_fawu = "15822223160";//����
	String username_dept = "18502285517";//��ȫ������
	String username_sybzjl = "13502103187";
	//String username_sybdsz = "15522219091";
	String username_anzhi = "18502285517";
	String username_caiwu = "13820641813";
	String username_wuzi = "18502285517";
	String username_zong= "13502103187";
	String username_fg_zhineng= "18602270056";//���ʶ�Ӧ�ֹ��쵼
	String passwd = "qetqet";
	String cpa_name = "����˳��ò���һ������";
	String cpa_money = "998";
	String cpa_dept = "��ȫ������";
	String cpa_time = "1";
 	String cpa_xingzhi = "����";
 	String cpa_type = "�������";
 	String cpa_jia = "�������ó�Ƽ����޹�˾";
 	String cpa_yi = "����";
 	String proj_name="�軹��ͳ��ר����Ŀ";
 	String cpa_descript = "��ע�Ǳ���";
 	String cpa_moneyOut_bili = "10:0";
 	String cpa_moneyOut_date1 = "2018-02-01";
 	String cpa_moneyOut_tiaojian = "��Ʊ����";
 	String cpa_invoiceIn_bili = "10:0";
 	String cpa_invoiceIn_date1 = "2018-03-01";
	
 	@BeforeClass
  	public void setUp() throws IOException {
  		BrowserEngine browserEngine = new BrowserEngine();
  		browserEngine.initConfigData();
  		driver = browserEngine.getBrowser();
  	}
 	@Test//һ��ͨ��
	public void applyNewCpa() throws InterruptedException {
		PMIS_Page pmis = PageFactory.initElements(driver, PMIS_Page.class);
		PMIS_JiaFangHeTong_Page pmis_cpa = PageFactory.initElements(driver, PMIS_JiaFangHeTong_Page.class);
		Thread.sleep(1000);
		//����
		pmis.login(username, passwd);
		Thread.sleep(1000);
//		pmis_cpa.applyNewCpa(cpa_name, cpa_money, cpa_dept, cpa_time, cpa_xingzhi, cpa_type, cpa_jia, cpa_yi, proj_name, cpa_descript, cpa_moneyOut_bili, cpa_moneyOut_date1, cpa_moneyOut_tiaojian, cpa_invoiceIn_bili, cpa_invoiceIn_date1);
		//��������
		pmis.loginSwitch(username_dept, passwd);
		pmis_cpa.cpaSpAgree("OK");
		//��������
		pmis.loginSwitch(username_fawu, passwd);
		pmis_cpa.cpaSpAgree("OK");
//	 	//��ҵ���ܾ�������
//		pmis.loginSwitch(username_sybzjl, passwd);
//		pmis_cpa.cpaSpAgree("OK");
//	 	//��ҵ�����³�����
//		pmis.loginSwitch(username_sybdsz, passwd);
//		pmis_cpa.cpaSpAgree("OK");
		//���ʶ�Ӧ�ֹ��쵼����
		pmis.loginSwitch(username_fg_zhineng, passwd);
		pmis_cpa.cpaSpAgree("OK");
		//��������
		pmis.loginSwitch(username_anzhi, passwd);
		pmis_cpa.cpaSpAgree("OK");
		//��������
		pmis.loginSwitch(username_caiwu, passwd);
		pmis_cpa.cpaSpAgree("OK");
		//��������
		pmis.loginSwitch(username_wuzi, passwd);
		pmis_cpa.cpaSpAgree("OK");
		//�ܾ�������
		pmis.loginSwitch(username_zong, passwd);
		pmis_cpa.cpaSpAgree("OK");
 	}

	@AfterClass
	public void afterClass() {
		//driver.quit();
	}

}
