package framework;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class BrowserEngine {
	public String browserName;
	public String serverURL;
	public WebDriver driver;
	
	
	public void initConfigData() throws IOException{
		
		
		Properties p = new Properties();
		//加载配置文件
		InputStream ips = new FileInputStream(".\\TestConfig\\config.properties");
		p.load(ips);
		
		Logger.Output(LogType.LogTypeName.INFO, "Start to select browser name from properties file");
		browserName = p.getProperty("browserName");
		Logger.Output(LogType.LogTypeName.INFO, "You had select test browser type is:" + browserName);
		serverURL = p.getProperty("URL");
		Logger.Output(LogType.LogTypeName.INFO, "The test server URL is:" + serverURL);
		ips.close();
	}
	
	public WebDriver getBrowser(){
		if (browserName.equalsIgnoreCase("Firefox")){
			System.setProperty("webdriver.gecko.driver", ".\\Tools\\geckodriver.exe");
			driver = createFireFoxDriver();
			Logger.Output(LogType.LogTypeName.INFO, "Lauching Firefox...");
		}else if (browserName.equalsIgnoreCase("Chrome")){
			System.setProperty("webdriver.chrome.driver", ".\\Tools\\chromedriver.exe");
			driver = new ChromeDriver();
			Logger.Output(LogType.LogTypeName.INFO, "Lauching Chrome...");
		}else if (browserName.equalsIgnoreCase("IE")){
			System.setProperty("webdriver.ie.driver", ".\\Tools\\IEDdriverServer.exe");
			driver = new InternetExplorerDriver();
			Logger.Output(LogType.LogTypeName.INFO, "Lauching IE...");
		}
		driver.get(serverURL);
		Logger.Output(LogType.LogTypeName.INFO, "Open URL:" + serverURL);
		driver.manage().window().maximize();
		Logger.Output(LogType.LogTypeName.INFO, "Maximize browser...");
		callWait(5);
		return driver;
		
	}
	//关闭浏览器
	public void tearDown() throws InterruptedException{
		Logger.Output(LogType.LogTypeName.INFO, "Closing browser...");
		driver.quit();
		Thread.sleep(3000);
	}
	//隐式时间等待
	public void callWait(int time){
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
		Logger.Output(LogType.LogTypeName.INFO, "Wait for " + time + "seconds.");
	}
	
	public WebDriver createFireFoxDriver(){
		WebDriver driver = null;
		FirefoxProfile firefoxProfile = new FirefoxProfile();
		firefoxProfile.setPreference("prefs.converted-to-utf8", true);
		firefoxProfile.setPreference("browser.download.folderList", 2);
		firefoxProfile.setPreference("browser.download.dir", ".\\TestDownload");
		try {
			driver = new FirefoxDriver(firefoxProfile);
		}catch(Exception e){
			Logger.Output(LogType.LogTypeName.ERROR, e.getMessage());
			Logger.Output(LogType.LogTypeName.ERROR, "Failed to initilize the Firefox driver.");
		}
		return driver;
		
	}

}
