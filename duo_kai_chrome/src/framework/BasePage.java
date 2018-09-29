package framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BasePage {
	public static WebDriver driver;
	public static String pageTitle;
	public static String pageUrl;
	
	/*
	 * 鏋勯�犳柟娉�
	 */
	protected BasePage(WebDriver driver){
		BasePage.driver=driver;
	}
	
	/*
	 * 鍒ゆ柇涓�涓〉闈㈠厓绱犳槸鍚︽樉绀哄湪褰撳墠椤甸潰
	 */
	protected void verifyElementIsPresent(WebElement element){
		try {
			if (element.isEnabled()){
			}
		}catch (Exception e){
		}
	}
	
}
