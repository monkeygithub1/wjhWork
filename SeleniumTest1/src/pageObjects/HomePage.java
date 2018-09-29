package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import framework.BasePage;

public class HomePage extends BasePage {
	/*
	 * 京东商城的首页
	 */
	//元素定位
	//搜索输入框
	@FindBy (id="key")
	WebElement search_inputBox;
	//搜索提交按钮
	@FindBy (xpath="//*[@id='search']/div/div[2]/button")
	WebElement search_submitBtn;
	
	public HomePage(WebDriver driver){
		super(driver);
	}
	
	/*
	 * 搜索框输入关键字，点击搜索
	 */
	public SearchResultListPage searchWithKeyword(String keyword) throws InterruptedException{
		type(search_inputBox, keyword);
		Thread.sleep(1000);
		click(search_submitBtn);
		return new SearchResultListPage(driver);
	}
	

}
