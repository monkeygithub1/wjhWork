package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import framework.BasePage;

public class PMIS_FeiYongBaoXiao_Page extends BasePage {

	protected PMIS_FeiYongBaoXiao_Page(WebDriver driver) {
		super(driver);
	}	
	/*
	 * 页面元素
	 */
	//费用报销按钮
	@FindBy (xpath=".//*[@id='win10-shortcuts']/div[contains(@onclick,\"费用报销\")]")
	WebElement home_fybx;
	//申请费用报销按钮
	@FindBy (xpath=".//button[@onclick=\"toApply()\"]")
	WebElement fybx_toApply;
	//报销人选择
	@FindBy (xpath=".//a[@onclick=\"fybxApply.chooseBxr()\"]")
	WebElement fybx_toApply_chooseBxr;//选择按钮
	@FindBy (id="search_input")
	WebElement fybx_toApply_chooseBxr_input;//报销人搜索框
	@FindBy (xpath="//*[@id=\"control\"]/div[@class=\"submit_control\"]/button[text()='确定']")
	WebElement fybx_toApply_chooseBxr_OKt;//报销人搜索框
	
	//所属PMO
	@FindBy (xpath="//*[@id=\"pmo\"]/div/div/input")
	WebElement fybx_toApply_PMO;
	
	//项目信息
	@FindBy (xpath="//*[@id=\"baseInfoDiv\"]/div[1]/table/tbody/tr[2]/td[2]/a[1]")
	WebElement fybx_toApply_proj;
	@FindBy (id="spuQueryParam")
	WebElement fybx_toApply_proj_input;
	@FindBy (xpath=".//a[@onclick=\"fybxApply.chooseBxr()\"]")
	WebElement fybx_toApply_proj_serch;//搜索按钮
	//特定类型
	
	//
	
	
	

}
