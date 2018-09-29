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
	 * 页面元素
	 */
	//乙方合同按钮
	@FindBy (xpath=".//*[@id='win10-shortcuts']/div[contains(@onclick,\"乙方合同\")]")
	WebElement home_cpb;
	//我的申请
	
	//申请按钮
	@FindBy (xpath=".//button[@onclick=\"toAdd()\"]")
	WebElement cpb_toAdd;
	//编辑按钮
	@FindBy (xpath="/html/body/div[2]/div[2]/table/tbody/tr[1]/td[8]/div/a")
	WebElement cpb_edit;
	/*
	 * 编辑合同信息
	 */
	//合同名称
	@FindBy (id="name_")
	WebElement cpb_name;
	String name_time = Logger.getDateTimeByFormat(new Date(), "yyyyMMddHHmmss");
	//合同金额
	@FindBy (id="money_")
	WebElement cpb_money;
	//所属部门
	@FindBy (xpath="//*[@id=\"baseInfoDiv\"]/form/div/table/tbody/tr[2]/td[2]/div/div/input")
	WebElement cpb_dept;
	
	//质保期限
	@FindBy (id="qa_time_")
	WebElement cpb_qatime;//输入框
	@FindBy (id="qa_time_unit_")
	WebElement cpb_qatime_unit;//下拉按钮
								//下拉值(暂没实现)
	
	//合同性质
	@FindBy (xpath="//*[@id=\"baseInfoDiv\"]/form/div/table/tbody/tr[3]/td[2]/div/div/input")
	WebElement cpb_xingzhi;
	//合同类型
	@FindBy (xpath="//*[@id=\"baseInfoDiv\"]/form/div/table/tbody/tr[3]/td[4]/div/div/input")
	WebElement cpb_type;
	//甲方签订单位
	@FindBy (id="pa_qddw_")
	WebElement cpb_jia;
	//乙方签订单位
	@FindBy (xpath="//*[@id=\"baseInfoDiv\"]/form/div/table/tbody/tr[4]/td[4]/div/div/input")
	WebElement cpb_yi;
	
	//关联项目
	@FindBy (xpath=".//a[text()=\"选择\"]")
	WebElement cpb_chooseProj;//选择按钮
	@FindBy (id="spuQueryParam")
	WebElement cpb_chooseProj_name;//搜索文本框
	@FindBy (xpath=".//input[@value=\"搜索\"]")
	WebElement cpb_chooseProj_find;//搜索按钮
	@FindBy (xpath="//*[@id=\"findResultDiv\"]/input")
	WebElement cpb_chooseProj_result;//搜索结果列表
	@FindBy (linkText="确定选择")
	WebElement cpb_chooseProj_ok;//确定选择按钮
	//备注
	@FindBy (id="descript_")
	WebElement cpb_descript;
	//保存基本信息按钮
	@FindBy (id="cpbBaseSaveBtn")
	WebElement cpb_baseSave;
	
	//收款计划
	@FindBy (id="moneyInPlanH2")
	WebElement cpb_moneyIn;
	//收款比例
	@FindBy (xpath="//*[@id=\"contrPlan\"]/div[1]/div[1]/div/input")
	WebElement cpb_moneyIn_bili;
	//选择日期
	@FindBy (id="date0")
	WebElement cpb_moneyIn_date;
	//确定-保存收款计划
	@FindBy (xpath="//*[@id=\"contrPlan\"]//button[text()='确定']")
	WebElement cpb_moneyIn_ok;
	
	//开票计划
	@FindBy (id="invoiceOutH2")
	WebElement cpb_invoiceOut;
	//开票比例
	@FindBy (xpath="//*[@id=\"contrPlan\"]/div[1]/div[1]/div/input")
	WebElement cpb_invoiceOut_bili;
	//选择日期
	@FindBy (id="date0")
	WebElement cpb_invoiceOut_date;
	//确定-保存开票计划
	@FindBy (xpath="//*[@id=\"contrPlan\"]//button[text()='确定']")
	WebElement cpb_invoiceOut_ok;
	//提交申请
	@FindBy (xpath="//button[text()='提交申请']")
	WebElement cpb_applydo;
	
	/*
	 * 审批相关
	 */
	//我的审批
	@FindBy (xpath=".//li[text()='我的审批']")
	WebElement cpb_myApply;
	//项目列表第一行的审批按钮
	@FindBy (xpath="/html/body/div[2]/div[2]/table/tbody/tr/td[8]/div/a")
	WebElement cpb_list1Apply;
	//审批理由
	@FindBy (id="spCommont")
	WebElement cpb_myApply_spCommont;
	//同意
	@FindBy (id="sp1Btn")
	WebElement cpb_myApply_spAgree;
	//拒绝
	@FindBy (id="sp2Btn")
	WebElement cpb_myApply_spRefuse;
	/*
	 * 构造方法
	 */
	//选择关联项目
	public void chooseProj(String proj_name){
		click(cpb_chooseProj);
		type(cpb_chooseProj_name,proj_name);
		click(cpb_chooseProj_find);
		click(cpb_chooseProj_result);
		click(cpb_chooseProj_ok);
	}
	//新建甲方合同
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
		type(cpb_qatime, time);//此处还应选择年月日，暂没有实现
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
	//编辑乙方合同（简易版）
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
	//审批-同意
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
		//审批-拒绝
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
