package lessons;

import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Parameters;

public class TestCrossBrowser {
  @Test
  @Parameters("Browser")
  public void startBrowser(String browser) {
	  if(browser.equalsIgnoreCase("Firefox")){
		  System.setProperty("webdriver.gecko.driver", ".\\Tools\\geckodriver.exe");
		  WebDriver driver = new FirefoxDriver();
		  driver.manage().window().maximize();
		  driver.get("https://www.baidu.com");
		  driver.quit();
	  }
	  else if(browser.equalsIgnoreCase("Chrome")){
		  System.setProperty("webdriver.chrome.driver", ".\\Tools\\chromedriver.exe");
		  WebDriver driver = new ChromeDriver();
		  driver.get("https://www.baidu.com");
		  driver.manage().window().maximize();
		  driver.quit();		  
	  }
	  
  }
  

}