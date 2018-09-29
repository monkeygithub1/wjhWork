package framework;

import java.io.IOException;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class BrowserEngine {
	public String browserName= "Chrome";	
	public String serverURL = "https://pmistest.womow.cn/pmis/views/login.jsp";
	public WebDriver driver;
	public WebDriver getBrowser() throws IOException{
		
		System.setProperty("webdriver.chrome.driver", ".\\Tools\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(serverURL);
		driver.manage().window().maximize();
		callWait(5);
		return driver;
		
	}
	
	//闅愬紡鏃堕棿绛夊緟
	public void callWait(int time){
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
	}
}
