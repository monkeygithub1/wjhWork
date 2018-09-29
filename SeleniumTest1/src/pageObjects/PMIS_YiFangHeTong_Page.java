package pageObjects;

import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import framework.BasePage;
import framework.Logger;

public class PMIS_YiFangHeTong_Page extends BasePage{

	public PMIS_YiFangHeTong_Page(WebDriver driver) {
		super(driver);
	}
	/*
	 * ҳ��Ԫ��
	 */
	//�ҷ���ͬ��ť
	@FindBy (xpath=".//*[@id='win10-shortcuts']/div[contains(@onclick,\"�ҷ���ͬ\")]")
	WebElement home_cpb;
	//�ҵ�����
	
	//���밴ť
	@FindBy (xpath=".//button[@onclick=\"toAdd()\"]")
	WebElement cpb_toAdd;
	//�༭��ť
	@FindBy (xpath="/html/body/div[2]/div[2]/table/tbody/tr[1]/td[8]/div/a")
	WebElement cpb_edit;
	/*
	 * �༭��ͬ��Ϣ
	 */
	//��ͬ����
	@FindBy (id="name_")
	WebElement cpb_name;
	String name_time = Logger.getDateTimeByFormat(new Date(), "yyyyMMddHHmmss");
	//��ͬ���
	@FindBy (id="money_")
	WebElement cpb_money;
	//��������
	@FindBy (xpath="//*[@id=\"baseInfoDiv\"]/form/div/table/tbody/tr[2]/td[2]/div/div/input")
	WebElement cpb_dept;
	
	//�ʱ�����
	@FindBy (id="qa_time_")
	WebElement cpb_qatime;//�����
	@FindBy (id="qa_time_unit_")
	WebElement cpb_qatime_unit;//������ť
								//����ֵ(��ûʵ��)
	
	//��ͬ����
	@FindBy (xpath="//*[@id=\"baseInfoDiv\"]/form/div/table/tbody/tr[3]/td[2]/div/div/input")
	WebElement cpb_xingzhi;
	//��ͬ����
	@FindBy (xpath="//*[@id=\"baseInfoDiv\"]/form/div/table/tbody/tr[3]/td[4]/div/div/input")
	WebElement cpb_type;
	//�׷�ǩ����λ
	@FindBy (id="pa_qddw_")
	WebElement cpb_jia;
	//�ҷ�ǩ����λ
	@FindBy (xpath="//*[@id=\"baseInfoDiv\"]/form/div/table/tbody/tr[4]/td[4]/div/div/input")
	WebElement cpb_yi;
	
	//������Ŀ
	@FindBy (xpath=".//a[text()=\"ѡ��\"]")
	WebElement cpb_chooseProj;//ѡ��ť
	@FindBy (id="spuQueryParam")
	WebElement cpb_chooseProj_name;//�����ı���
	@FindBy (xpath=".//input[@value=\"����\"]")
	WebElement cpb_chooseProj_find;//������ť
	@FindBy (xpath="//*[@id=\"findResultDiv\"]/input")
	WebElement cpb_chooseProj_result;//��������б�
	@FindBy (linkText="ȷ��ѡ��")
	WebElement cpb_chooseProj_ok;//ȷ��ѡ��ť
	//��ע
	@FindBy (id="descript_")
	WebElement cpb_descript;
	//���������Ϣ��ť
	@FindBy (id="cpbBaseSaveBtn")
	WebElement cpb_baseSave;
	
	//�տ�ƻ�
	@FindBy (id="moneyInPlanH2")
	WebElement cpb_moneyIn;
	//�տ����
	@FindBy (xpath="//*[@id=\"contrPlan\"]/div[1]/div[1]/div/input")
	WebElement cpb_moneyIn_bili;
	//ѡ������
	@FindBy (id="date0")
	WebElement cpb_moneyIn_date;
	//ȷ��-�����տ�ƻ�
	@FindBy (xpath="//*[@id=\"contrPlan\"]//button[text()='ȷ��']")
	WebElement cpb_moneyIn_ok;
	
	//��Ʊ�ƻ�
	@FindBy (id="invoiceOutH2")
	WebElement cpb_invoiceOut;
	//��Ʊ����
	@FindBy (xpath="//*[@id=\"contrPlan\"]/div[1]/div[1]/div/input")
	WebElement cpb_invoiceOut_bili;
	//ѡ������
	@FindBy (id="date0")
	WebElement cpb_invoiceOut_date;
	//ȷ��-���濪Ʊ�ƻ�
	@FindBy (xpath="//*[@id=\"contrPlan\"]//button[text()='ȷ��']")
	WebElement cpb_invoiceOut_ok;
	//�ύ����
	@FindBy (xpath="//button[text()='�ύ����']")
	WebElement cpb_applydo;
	
	/*
	 * �������
	 */
	//�ҵ�����
	@FindBy (xpath=".//li[text()='�ҵ�����']")
	WebElement cpb_myApply;
	//��Ŀ�б��һ�е�������ť
	@FindBy (xpath="/html/body/div[2]/div[2]/table/tbody/tr/td[8]/div/a")
	WebElement cpb_list1Apply;
	//��������
	@FindBy (id="spCommont")
	WebElement cpb_myApply_spCommont;
	//ͬ��
	@FindBy (id="sp1Btn")
	WebElement cpb_myApply_spAgree;
	//�ܾ�
	@FindBy (id="sp2Btn")
	WebElement cpb_myApply_spRefuse;
	/*
	 * ���췽��
	 */
	//ѡ�������Ŀ
	public void chooseProj(String proj_name){
		click(cpb_chooseProj);
		type(cpb_chooseProj_name,proj_name);
		click(cpb_chooseProj_find);
		click(cpb_chooseProj_result);
		click(cpb_chooseProj_ok);
	}
	//�½��׷���ͬ
	public void applyNewCpb(String name, String money, String dept, String time, String xingzhi, String type, String jia, String yi, String proj_name, String descript, String moneyIn_bili, String moneyIn_date1, String invoiceOut_bili, String invoiceOut_date1) throws InterruptedException{
		click(home_cpb);
		driver.switchTo().frame(0);
		Thread.sleep(1000);
		driver.switchTo().frame("cpbApplyFrame");
		click(cpb_toAdd);
		Thread.sleep(2000);
		driver.switchTo().defaultContent(); 
		driver.switchTo().frame(1);
		type(cpb_name, name+name_time);
		type(cpb_money, money);
		click(cpb_dept);
		click(xiala(dept));
		type(cpb_qatime, time);//�˴���Ӧѡ�������գ���û��ʵ��
		click(cpb_xingzhi);
		click(xiala(xingzhi));
		click(cpb_type);
		click(xiala(type));
		type(cpb_jia, jia);
		click(cpb_yi);
		click(xiala(yi));
		chooseProj(proj_name);
		type(cpb_descript, descript);
		click(cpb_baseSave);
		Thread.sleep(2000);
		click(cpb_moneyIn);
		Thread.sleep(1000);
		driver.switchTo().frame("moneyInPlanFrame");
		type(cpb_moneyIn_bili, moneyIn_bili+"\n");
		type(cpb_moneyIn_date, moneyIn_date1);
		click(cpb_moneyIn_ok);
		Thread.sleep(2000);
		driver.switchTo().parentFrame();
		click(cpb_invoiceOut);
		driver.switchTo().frame("invoiceOutPlanFrame");
		type(cpb_invoiceOut_bili, invoiceOut_bili+"\n");
		type(cpb_invoiceOut_date, invoiceOut_date1);
		click(cpb_invoiceOut_ok);
		Thread.sleep(2000);
		driver.switchTo().parentFrame();
		click(cpb_applydo);
		Thread.sleep(2000);
		driver.switchTo().alert().accept();
	}
	//�༭�ҷ���ͬ�����װ棩
		public void simplecpbEdit(String name, String dept, String xingzhi, String type) throws InterruptedException{
			click(home_cpb);
			driver.switchTo().frame(0);
			Thread.sleep(1000);
			driver.switchTo().frame("cpbApplyFrame");
			click(cpb_edit);
			Thread.sleep(2000);
			driver.switchTo().defaultContent(); 
			driver.switchTo().frame(1);
			type(cpb_name, name+name_time);
			click(cpb_dept);
			click(xiala(dept));
			click(cpb_xingzhi);
			click(xiala(xingzhi));
			click(cpb_type);
			click(xiala(type));
			click(cpb_baseSave);
			Thread.sleep(1000);
			click(cpb_applydo);
			Thread.sleep(2000);
			driver.switchTo().alert().accept();
		}
	//����-ͬ��
		public void cpbSpAgree(String spCommont) throws InterruptedException{
			click(home_cpb);
			Thread.sleep(2000);
			driver.switchTo().frame(0);
			click(cpb_myApply);
			driver.switchTo().frame("waitSpFrame");
			click(cpb_list1Apply);
			Thread.sleep(2000);
			driver.switchTo().defaultContent(); 
			driver.switchTo().frame(1);
			type(cpb_myApply_spCommont, spCommont);
			click(cpb_myApply_spAgree);
			Thread.sleep(3000);
			driver.switchTo().alert().accept();
			
		}
		//����-�ܾ�
		public void cpbSpRefuse(String spCommont) throws InterruptedException{
			click(home_cpb);
			Thread.sleep(2000);
			driver.switchTo().frame(0);
			click(cpb_myApply);
			driver.switchTo().frame("waitSpFrame");
			click(cpb_list1Apply);
			Thread.sleep(2000);
			driver.switchTo().defaultContent(); 
			driver.switchTo().frame(1);
			type(cpb_myApply_spCommont, spCommont);
			click(cpb_myApply_spRefuse);
			Thread.sleep(3000);
			driver.switchTo().alert().accept();
		}

}
