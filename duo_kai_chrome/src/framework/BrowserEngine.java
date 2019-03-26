package framework;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class BrowserEngine {
	public String browserName= "Chrome";	
	public String serverURL;
	public WebDriver driver;
	public WebDriver getBrowser(int i) throws IOException{
		
		Properties p = new Properties();
		//加载配置文件
		InputStream ips = new FileInputStream(".\\TestConfig\\config.properties");
		p.load(ips);
		switch (i){
		case 1:
			serverURL = p.getProperty("URL1");
			break;
		case 2:
			serverURL = p.getProperty("URL2");
			break;
		case 3:
			serverURL = p.getProperty("URL3");
			break;
		}
		ips.close();
		
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
