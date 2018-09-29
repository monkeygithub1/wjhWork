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
	String username = "18795039941";//����������Ŀ����
	String username_syb = "13612194369";
	String username_azb = "18502285517";
	String username_sc = "18602270056";
	String passwd = "qetqet";
	String projName = "��ȫ��������δ򱬳���˳�Ĺ�ͷ";
	String PM = "������";
	String dept = "��ȫ������";
	String cydept = "���벿��-�Ǳ��";
	String quyu = "��������";
 	String kehu = "�ͻ�1-2-3-13910623294";
 	String xingzhi = "ǣͷ";
 	String type = "R���о���";
 	String biz = "��ҵ��ҵ����ѯ-������ѯ-�����׼����ѯ-��-��";
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
		pmis_proj.applyNewProj(projName, PM, dept, cydept, quyu, kehu, xingzhi, biz, type, yuji, descript, kphte);
	}

}
