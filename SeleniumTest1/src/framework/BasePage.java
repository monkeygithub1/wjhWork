package framework;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BasePage {
	public static WebDriver driver;
	public static String pageTitle;
	public static String pageUrl;
	
	/*
	 * 构造方法
	 */
	protected BasePage(WebDriver driver){
		BasePage.driver=driver;
	}
	/* 
	 * 在文本框内输入字符
	 */
	protected void type(WebElement element, String text){
		try {
			if (element.isEnabled()){
				element.clear();
				Logger.Output(LogType.LogTypeName.INFO, "Clean the value if any in " + element.toString() + ".");
				element.sendKeys(text);
				Logger.Output(LogType.LogTypeName.INFO, "Type value is: " + text + ".");
			}
		}catch(Exception e){
			Logger.Output(LogType.LogTypeName.ERROR, e.getMessage() + ".");
			//throw e;
		}
	}
	/* 
	 * 点击元素，这里指点击鼠标左键
	 */
	protected void click(WebElement element){
		try {
			if (element.isEnabled()){
				element.click();
				Logger.Output(LogType.LogTypeName.INFO, "Element:" + element.toString() + "was clicked.");
			}
		}catch (Exception e){
			Logger.Output(LogType.LogTypeName.ERROR, e.getMessage() + ".");
			//throw e;
			
		}
	}
	/* 
	 * 在文本输入框执行清除操作
	 */
	protected void clean(WebElement element){
		try {
			if (element.isEnabled()){
				element.clear();
				Logger.Output(LogType.LogTypeName.INFO, "Element:" + element.toString() + "was cleaned.");
			}
		}catch (Exception e){
			Logger.Output(LogType.LogTypeName.ERROR, e.getMessage() + ".");
		}
	}
	/*
	 * 判断一个页面元素是否显示在当前页面
	 */
	protected void verifyElementIsPresent(WebElement element){
		try {
			if (element.isEnabled()){
				Logger.Output(LogType.LogTypeName.INFO, "Element:" + element.toString() + "is present.");
			}
		}catch (Exception e){
			Logger.Output(LogType.LogTypeName.ERROR, e.getMessage() + ".");
		}
	}
	/*
	 * 获取页面标题
	 */
	protected String getCurrentPageTitle(){
		pageTitle = driver.getTitle();
		Logger.Output(LogType.LogTypeName.INFO, "Current page title is:" + pageTitle + ".");
		return pageTitle;
	}
	/*
	 * 获取页面URL
	 */
	protected String getCurrentPageUrl(){
		pageUrl = driver.getCurrentUrl();
		Logger.Output(LogType.LogTypeName.INFO, "Current page URL is:" + pageUrl + ".");
		return pageUrl;
	}
	/*
	 * 处理多窗口之间切换
	 */
	public void switchWindow(){
		String currentWindow = driver.getWindowHandle();//获取当前窗口句柄
		Set<String> handles = driver.getWindowHandles();//获取所有窗口句柄
		Logger.Output(LogType.LogTypeName.INFO, "当前窗口数量:" + handles.size() + ".");
		Iterator<String> it = handles.iterator();
		while (it.hasNext()){
			if (currentWindow == it.next()){
				continue;
			}
			try {
				driver.close();//关闭旧窗口
				WebDriver window = driver.switchTo().window(it.next());//切换到新窗口
				Logger.Output(LogType.LogTypeName.INFO, "new page title is:" + window.getTitle() + ".");
			}catch (Exception e){
				Logger.Output(LogType.LogTypeName.ERROR, "无法切换到新打开窗口" + e.getMessage() + ".");
			}
			//driver.close();
		}
		//driver.switchTo().window(currentWindow);
	}
	/*
	 * 从下拉菜单选择值
	 */
	protected WebElement xiala(String text){
		return driver.findElement(By.xpath(".//dd[text()='"+text+"']"));
	}
	protected WebElement input(String input){
		return driver.findElement(By.xpath(".//input[@value=\""+input+"\"]"));
	}
}
