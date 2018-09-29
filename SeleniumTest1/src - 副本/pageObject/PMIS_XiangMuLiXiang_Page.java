package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import framework.BasePage;

/*
 * 项目立项及审批相关元素及方法
 */
public class PMIS_XiangMuLiXiang_Page extends BasePage{

	public PMIS_XiangMuLiXiang_Page(WebDriver driver) {
		super(driver);
	}

	/*
	 * 页面元素
	 */
	//项目立项按钮
	@FindBy (xpath=".//*[@id='win10-shortcuts']/div[contains(@onclick,\"项目立项管理\")]")
	WebElement home_projNew;
	//我的申请
	
	//申请立项按钮
	@FindBy (xpath=".//button[@onclick=\"toAdd()\"]")
	WebElement projNew_toAdd;
	/*
	 * 编辑项目信息
	 */
	//项目名称
	@FindBy (id="proj_name_")
	WebElement projNew_toAdd_projName;
	
	//项目经理-选择
	@FindBy (linkText="选择")
	WebElement projNew_toAdd_PM_select;
	//项目经理-输入框
	@FindBy (id="scuQueryParam")
	WebElement projNew_toAdd_PM_scuQueryParam;
	@FindBy (css="input[type=\"button\"]")
	WebElement projNew_toAdd_PM_scuQueryParam_OK;
	//项目经理-搜索列表
	@FindBy (name="scuResults")
	WebElement projNew_toAdd_PM_scuResults;
	//项目经理-确定选择
	@FindBy (linkText="确定选择")
	WebElement projNew_toAdd_PM_OK;
	
			    
	//所属部门-输入框
	@FindBy (css="input.layui-input")
	WebElement projNew_toAdd_dept;
	//所属部门-下拉选择
	@FindBy (xpath=".//dd[text()='监控事业部']")
	WebElement projNew_toAdd_dept_value;
	
	//参与部门
	@FindBy (id="cy_dept_")
	WebElement projNew_toAdd_cydept;
	
	//所属区域-输入框
	@FindBy (css="tr.row0 > td.td_left > div.layui-form-select > div.layui-select-title > input.layui-input")
	WebElement projNew_toAdd_quyu;
	//所属区域-下拉框
	@FindBy (xpath=".//dd[text()='华北区域']")
	WebElement projNew_toAdd_quyu_value;
	
	//客户
	@FindBy (id="kehu")
	WebElement projNew_toAdd_kehu;
	
	//项目性质-输入框
	@FindBy (xpath="(//input[@value=''])[3]")
	WebElement projNew_toAdd_xingzhi;
	//项目性质-下拉框
	@FindBy (xpath=".//dd[text()='牵头']")
	WebElement projNew_toAdd_xingzhi_value;
	
	//项目管理类型-输入框
	@FindBy (xpath="(//input[@value=''])[4]")
	WebElement projNew_toAdd_mgrtype;
	//项目管理类型-下拉框
	@FindBy (xpath=".//dd[text()='R（研究）']")
	WebElement projNew_toAdd_mgrtype_value;	
	
	//业务类型-选择
	@FindBy (xpath="(//a[contains(text(),'选择')])[2]")
	WebElement projNew_toAdd_mgrtype_bizTypeSpan;
	//业务类型-选择类型
	@FindBy (xpath="(//input[@name='bizType'])[51]")
	WebElement projNew_toAdd_mgrtype_bizType;
	//业务类型-确定选择
	@FindBy (linkText="确定选择")
	WebElement projNew_toAdd_mgrtype_bizTypeSpan_OK;
		
	//预计合同额
	@FindBy (id="yuji_contract_sum_")
	WebElement projNew_toAdd_yuji;
	//项目概述
	@FindBy (id="descript_")
	WebElement projNew_toAdd_descript;
	//保存		   
	@FindBy (id="lxBaseSaveBtn")
	WebElement projNew_toAdd_lxBaseSaveBtn;
	
	/*
	 * 预算
	 */
	//预算
	@FindBy (id="yusuanH2")
	WebElement projNew_toAdd_yusuanH2;
	//开票合同额
	@FindBy (name="ys_kphte")
	WebElement projNew_toAdd_kphte;
	//保存
	@FindBy (id="budgetSaveBtn")
	WebElement projNew_toAdd_budgetSaveBtn;
	
	//提交申请
	@FindBy (xpath="//button[text()='提交申请']")
	WebElement projNew_toAdd_applydo;
	
	/*
	 * 审批相关
	 */
	//我的审批
	@FindBy (xpath=".//li[text()='我的审批']")
	WebElement projNew_myApply;
	//项目列表第一行的审批按钮
	@FindBy (xpath="html/body/div[2]/div[2]/table/tbody/tr[1]/td[6]/div/a[text()='审批']")
	WebElement projNew_list1Apply;
	//审批理由
	@FindBy (id="spCommont")
	WebElement projNew_myApply_spCommont;
	//同意
	@FindBy (id="sp1Btn")
	WebElement projNew_myApply_spAgree;
	//拒绝
	@FindBy (id="sp2Btn")
	WebElement projNew_myApply_spRefuse;
	/*
	 * 构造方法
	 */
	//选择项目经理
	public void projPMSelect(String PM) throws InterruptedException{
		projNew_toAdd_PM_select.click();
		Thread.sleep(2000);
		projNew_toAdd_PM_scuQueryParam.sendKeys(PM);
		Thread.sleep(2000);
		projNew_toAdd_PM_scuQueryParam_OK.click();
		projNew_toAdd_PM_scuResults.click();
		projNew_toAdd_PM_OK.click();
	}
	//选择所属部门(监控事业部)
	public void deptSelect(String dept) throws InterruptedException{
		projNew_toAdd_dept.click();
		projNew_toAdd_dept.sendKeys(dept);
		Thread.sleep(2000);
		projNew_toAdd_dept_value.click();
	}
	//所属区域（华北区域）
	public void quyuSelect(String quyu) throws InterruptedException{
		projNew_toAdd_quyu.click();
		projNew_toAdd_quyu.sendKeys(quyu);
		Thread.sleep(2000);
		projNew_toAdd_quyu_value.click();
	}
	//项目性质（牵头）
	public void xingzhiSelect(String xingzhi) throws InterruptedException{
		projNew_toAdd_xingzhi.click();
		projNew_toAdd_xingzhi.sendKeys(xingzhi);
		Thread.sleep(2000);
		projNew_toAdd_xingzhi_value.click();
	}
	//项目管理类型（R-研究）
	public void mgrtypeSelect(String type) throws InterruptedException{
		projNew_toAdd_mgrtype.click();
		projNew_toAdd_mgrtype.sendKeys(type);
		Thread.sleep(2000);
		projNew_toAdd_mgrtype_value.click();
	}
	//业务类型选择（）
	public void bizSelect() throws InterruptedException{
		projNew_toAdd_mgrtype_bizTypeSpan.click();
		Thread.sleep(2000);
		driver.switchTo().frame(0);
		click(projNew_toAdd_mgrtype_bizType);
		driver.switchTo().defaultContent();
		driver.switchTo().frame(1);
		projNew_toAdd_mgrtype_bizTypeSpan_OK.click();
	}
	//新建项目立项（全部）
	public void applyNewProj(String projName, String PM, String dept, String cydept, String quyu, String kehu, String xingzhi, String type, String yuji, String descript, String kphte) throws InterruptedException{
		home_projNew.click();
		driver.switchTo().frame(0);
		Thread.sleep(1000);
		driver.switchTo().frame("applyFrame");
		projNew_toAdd.click();
		driver.switchTo().defaultContent(); 
		driver.switchTo().frame(1);
		projNew_toAdd_projName.sendKeys(projName);
		projPMSelect(PM);
		Thread.sleep(2000);
		deptSelect(dept);
		projNew_toAdd_cydept.sendKeys(cydept);
		quyuSelect(quyu);
		projNew_toAdd_kehu.sendKeys(kehu);
		xingzhiSelect(xingzhi);
		mgrtypeSelect(type);
		bizSelect();
		projNew_toAdd_yuji.sendKeys(yuji);
		projNew_toAdd_descript.sendKeys(descript);	Thread.sleep(1000);
		projNew_toAdd_lxBaseSaveBtn.click();
		Thread.sleep(2000);
		projNew_toAdd_yusuanH2.click();
		driver.switchTo().frame("yusuanFrame");
		projNew_toAdd_kphte.sendKeys(kphte);
		projNew_toAdd_budgetSaveBtn.click();
		Thread.sleep(2000);
	    System.out.println(driver.switchTo().alert().getText());
	    driver.switchTo().alert().accept();
	    Thread.sleep(2000);
	    driver.switchTo().defaultContent();
	    driver.switchTo().frame(1);
	    projNew_toAdd_applydo.click();
	    Thread.sleep(2000);
	    System.out.println(driver.switchTo().alert().getText());
	    driver.switchTo().alert().dismiss();
	}
	//编辑项目立项（简易版）---未完成
	public void applyNewProjEdit(String projName, String descript, String kphte) throws InterruptedException{
		home_projNew.click();
		driver.switchTo().frame(0);
		Thread.sleep(1000);
		//选择我的申请，点击编辑按钮
		driver.switchTo().frame("applyFrame");
		projNew_toAdd.click();
		driver.switchTo().defaultContent(); 
		driver.switchTo().frame(1);
		
		type(projNew_toAdd_projName, projName);
		type(projNew_toAdd_projName, descript);
		projNew_toAdd_lxBaseSaveBtn.click();
		Thread.sleep(2000);
		projNew_toAdd_yusuanH2.click();
		driver.switchTo().frame("yusuanFrame");
		type(projNew_toAdd_kphte, kphte);
		projNew_toAdd_budgetSaveBtn.click();
		Thread.sleep(2000);
	    System.out.println(driver.switchTo().alert().getText());
	    driver.switchTo().alert().accept();
	    Thread.sleep(2000);
	    driver.switchTo().defaultContent();
	    driver.switchTo().frame(1);
	    projNew_toAdd_applydo.click();
	    Thread.sleep(2000);
	    System.out.println(driver.switchTo().alert().getText());
	    driver.switchTo().alert().dismiss();
	}
	//审批-同意
	public void spAgree(String spCommont) throws InterruptedException{
		home_projNew.click();
		driver.switchTo().frame(0);
		Thread.sleep(1000);
		projNew_myApply.click();
		Thread.sleep(2000);
		driver.switchTo().frame("waitSpFrame");
		projNew_list1Apply.click();
		driver.switchTo().defaultContent(); 
		driver.switchTo().frame(1);
		Thread.sleep(2000);
		projNew_myApply_spCommont.sendKeys(spCommont);
		projNew_myApply_spAgree.click();
		Thread.sleep(2000);
		driver.switchTo().alert().accept();
	}
	

}
