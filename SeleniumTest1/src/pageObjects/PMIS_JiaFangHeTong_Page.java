package pageObjects;

import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import framework.BasePage;
import framework.Logger;

public class PMIS_JiaFangHeTong_Page extends BasePage{

	public PMIS_JiaFangHeTong_Page(WebDriver driver) {
		super(driver);
	}
	
	/*
	 * 页面元素
	 */
	//甲方合同按钮
	@FindBy (xpath=".//*[@id='win10-shortcuts']/div[contains(@onclick,\"甲方合同\")]")
	WebElement home_cpa;
	//我的申请
	
	//申请按钮
	@FindBy (xpath=".//button[@onclick=\"toAdd()\"]")
	WebElement cpa_toAdd;
	//编辑按钮(tr几就是第几行的编辑按钮）
	@FindBy (xpath="/html/body/div[2]/div[2]/table/tbody/tr[1]/td[8]/div/a")
	WebElement cpa_edit;
	
	/*
	 * 编辑合同信息
	 */
	//合同名称
	@FindBy (id="name_")
	WebElement cpa_name;
	String name_time = Logger.getDateTimeByFormat(new Date(), "yyyyMMddHHmmss");
	//合同金额
	@FindBy (id="money_")
	WebElement cpa_money;
	//所属部门
	@FindBy (xpath="//*[@id=\"baseInfoDiv\"]/form/div/table/tbody/tr[2]/td[2]/div/div/input")
	WebElement cpa_dept;
	
	//质保期限
	@FindBy (id="qa_time_")
	WebElement cpa_qatime;//输入框
	@FindBy (id="qa_time_unit_")
	WebElement cpa_qatime_unit;//下拉按钮
								//下拉值(暂没实现)
	
	//合同性质
	@FindBy (xpath="//*[@id=\"baseInfoDiv\"]/form/div/table/tbody/tr[3]/td[2]/div/div/input")
	WebElement cpa_xingzhi;
	//合同类型
	@FindBy (xpath="//*[@id=\"baseInfoDiv\"]/form/div/table/tbody/tr[3]/td[4]/div/div/input")
	WebElement cpa_type;
	//甲方签订单位
	@FindBy (xpath="//*[@id=\"baseInfoDiv\"]/form/div/table/tbody/tr[4]/td[2]/div/div/input")
	WebElement cpa_jia;
	//乙方签订单位
	@FindBy (id="pb_qddw_")
	WebElement cpa_yi;
	
	//关联项目
	@FindBy (xpath=".//a[text()=\"选择\"]")
	WebElement cpa_chooseProj;//选择按钮
	@FindBy (id="spuQueryParam")
	WebElement cpa_chooseProj_name;//搜索文本框
	@FindBy (xpath=".//input[@value=\"搜索\"]")
	WebElement cpa_chooseProj_find;//搜索按钮
	@FindBy (xpath="//*[@id=\"findResultDiv\"]/input")
	WebElement cpa_chooseProj_result;//搜索结果列表
	@FindBy (linkText="确定选择")
	WebElement cpa_chooseProj_ok;//确定选择按钮
	//备注
	@FindBy (id="descript_")
	WebElement cpa_descript;
	//保存基本信息按钮
	@FindBy (id="cpaBaseSaveBtn")
	WebElement cpa_baseSave;
	
	//付款计划
	@FindBy (id="moneyOutPlanH2")
	WebElement cpa_moneyOut;
	//付款比例
	@FindBy (xpath="//*[@id=\"contrPlan\"]/div[1]/div[1]/div/input")
	WebElement cpa_moneyOut_bili;
	//选择日期
	@FindBy (id="date0")
	WebElement cpa_moneyOut_date;
	//付款条件
	@FindBy (xpath="//*[@id=\"contrPlan\"]/div[2]/table/tbody/tr/td[4]/textarea")
	WebElement cpa_moneyOut_tiaojian;
	
	//确定-保存付款计划
	@FindBy (xpath="//*[@id=\"contrPlan\"]//button[text()='确定']")
	WebElement cpa_moneyOut_ok;
	
	//收票计划
	@FindBy (id="invoiceInH2")
	WebElement cpa_invoiceIn;
	//收票比例
	@FindBy (xpath="//*[@id=\"contrPlan\"]/div[1]/div[1]/div/input")
	WebElement cpa_invoiceIn_bili;
	//选择日期
	@FindBy (id="date0")
	WebElement cpa_invoiceIn_date;
	//确定-保存收票计划
	@FindBy (xpath="//*[@id=\"contrPlan\"]//button[text()='确定']")
	WebElement cpa_invoiceIn_ok;
	//提交申请
	@FindBy (xpath="//button[text()='提交申请']")
	WebElement cpa_applydo;
	
	/*
	 * 审批相关
	 */
	//我的审批
	@FindBy (xpath=".//li[text()='我的审批']")
	WebElement cpa_myApply;
	//项目列表第一行的审批按钮
	@FindBy (xpath="/html/body/div[2]/div[4]/div[2]/table/tbody/tr/td/div/a")
	WebElement cpa_list1Apply;
	//审批理由
	@FindBy (id="spCommont")
	WebElement cpa_myApply_spCommont;
	//同意
	@FindBy (id="sp1Btn")
	WebElement cpa_myApply_spAgree;
	//拒绝
	@FindBy (id="sp2Btn")
	WebElement cpa_myApply_spRefuse;
	/*
	 * 构造方法
	 */
	//选择关联项目
	public void chooseProj(String proj_name){
		click(cpa_chooseProj);
		type(cpa_chooseProj_name,proj_name);
		click(cpa_chooseProj_find);
		click(cpa_chooseProj_result);
		click(cpa_chooseProj_ok);
	}
	//新建甲方合同
	public void applyNewCpa(String name, String money, String dept, String time, String xingzhi, String type, String jia, String yi, String proj_name, String descript, String moneyOut_bili, String moneyOut_date1, String moneyOut_tiaojian, String invoiceIN_bili, String invoiceIN_date1) throws InterruptedException{
		click(home_cpa);
		driver.switchTo().frame(0);
		Thread.sleep(1000);
		driver.switchTo().frame("cpaApplyFrame");
		click(cpa_toAdd);
		Thread.sleep(2000);
		driver.switchTo().defaultContent(); 
		driver.switchTo().frame(1);
		type(cpa_name, name+name_time);
		type(cpa_money, money);
		click(cpa_dept);
		click(xiala(dept));
		type(cpa_qatime, time);//此处还应选择年月日，暂没有实现
		click(cpa_xingzhi);
		click(xiala(xingzhi));
		click(cpa_type);
		click(xiala(type));
		click(cpa_jia);
		click(xiala(jia));
		type(cpa_yi, yi);
		chooseProj(proj_name);
		type(cpa_descript, descript);
		Thread.sleep(500);
		click(cpa_baseSave);
		Thread.sleep(1000);
		click(cpa_moneyOut);
		driver.switchTo().frame("moneyOutPlanFrame");
		type(cpa_moneyOut_bili, moneyOut_bili+"\n");
		type(cpa_moneyOut_date, moneyOut_date1);
		type(cpa_moneyOut_tiaojian, moneyOut_tiaojian);
		click(cpa_moneyOut_ok);
		Thread.sleep(2000);
		driver.switchTo().parentFrame();
		click(cpa_invoiceIn);
		driver.switchTo().frame("invoiceInPlanFrame");
		type(cpa_invoiceIn_bili, invoiceIN_bili+"\n");
		type(cpa_invoiceIn_date, invoiceIN_date1);
		click(cpa_invoiceIn_ok);
		Thread.sleep(2000);
		driver.switchTo().parentFrame();
		click(cpa_applydo);
		Thread.sleep(2000);
		driver.switchTo().alert().accept();
	}
	//编辑甲方合同（简易版）
	public void simpleCpaEdit(String name, String dept, String xingzhi, String type) throws InterruptedException{
		click(home_cpa);
		driver.switchTo().frame(0);
		Thread.sleep(1000);
		driver.switchTo().frame("cpaApplyFrame");
		click(cpa_edit);
		Thread.sleep(2000);
		driver.switchTo().defaultContent(); 
		driver.switchTo().frame(1);
		type(cpa_name, name+name_time);
		click(cpa_dept);
		click(xiala(dept));
		click(cpa_xingzhi);
		click(xiala(xingzhi));
		click(cpa_type);
		click(xiala(type));
		click(cpa_baseSave);
		Thread.sleep(1000);
		click(cpa_applydo);
		Thread.sleep(2000);
		driver.switchTo().alert().accept();
	}
	//审批-同意
	public void cpaSpAgree(String spCommont) throws InterruptedException{
		click(home_cpa);
		Thread.sleep(2000);
		driver.switchTo().frame(0);
		click(cpa_myApply);
		driver.switchTo().frame("waitSpFrame");
		click(cpa_list1Apply);
		Thread.sleep(10000);
		driver.switchTo().defaultContent(); 
		driver.switchTo().frame(1);
		type(cpa_myApply_spCommont, spCommont);
		click(cpa_myApply_spAgree);
		Thread.sleep(3000);
		driver.switchTo().alert().accept();
		
	}
	//审批-拒绝
	public void cpaSpRefuse(String spCommont) throws InterruptedException{
		click(home_cpa);
		Thread.sleep(2000);
		driver.switchTo().frame(0);
		click(cpa_myApply);
		driver.switchTo().frame("waitSpFrame");
		click(cpa_list1Apply);
		Thread.sleep(2000);
		driver.switchTo().defaultContent(); 
		driver.switchTo().frame(1);
		type(cpa_myApply_spCommont, spCommont);
		click(cpa_myApply_spRefuse);
		Thread.sleep(3000);
		driver.switchTo().alert().accept();
	}
	

}
