package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import framework.BasePage;

public class PMIS_Page extends BasePage{
	public PMIS_Page(WebDriver driver) {
		super(driver);
	}

	/*
	 * 页面元素
	 */
	//登录页面-用户名
	@FindBy (id="phoneUn")
	WebElement login_username;
	//登录页面-密码
	@FindBy (id="password")
	WebElement login_password;
	//登录页面-登录按钮
	@FindBy (id="loginBtn")
	WebElement login_btn;
	//欢迎页
	@FindBy (xpath=".//*[@id='layui-layer1']/span[@class=\"layui-layer-setwin\"]/a")
	WebElement home_welcome;
	//开始按钮
	@FindBy (id="win10_btn_win")
	WebElement home_beginBtn;
	//登出
	@FindBy (xpath=".//div[@onclick=\"logoutDo()\"]")
	WebElement logout;
	/*
	 * 构造方法
	 */
	//登录并关闭欢迎页
	public void login(String username, String passwd) throws InterruptedException{
//		login_username.sendKeys(username);
//		login_password.sendKeys(passwd);
//		login_btn.click();
//		Thread.sleep(4000);
//		home_welcome.click();
		type(login_username, username);
		type(login_password, passwd);
		click(login_btn);
		click(home_welcome);
		
	}
	//换号登录
	public void loginSwitch(String username, String passwd) throws InterruptedException{
//		driver.switchTo().defaultContent();
//		home_beginBtn.click();
//		Thread.sleep(2000);
//		logout.click();
//		driver.switchTo().alert().accept();
//		Thread.sleep(2000);
//		login_username.sendKeys(username);
//		login_password.sendKeys(passwd);
//		login_btn.click();
//		Thread.sleep(4000);
//		home_welcome.click();
		driver.switchTo().defaultContent();
		click(home_beginBtn);
		Thread.sleep(3000);
		click(logout);
		Thread.sleep(1000);
		driver.switchTo().alert().accept();
		type(login_username, username);
		type(login_password, passwd);
		click(login_btn);
		//click(home_welcome);
		
	}
	
	
}
