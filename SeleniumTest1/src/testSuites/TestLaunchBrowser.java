package testSuites;

import org.testng.annotations.Test;

import framework.BrowserEngine;

import org.testng.annotations.BeforeClass;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;

public class TestLaunchBrowser {
	public WebDriver driver;
  @Test
  public void clickNews() {
//	  driver.findElement(By.id("key")).sendKeys("iPhone 7");
//	  driver.findElement(By.xpath("//*[@id='search']/div/div[2]/button")).click();
	  driver.switchTo().frame(0);
	  driver.findElement(By.name("email")).sendKeys("abc");
	  driver.findElement(By.name("email")).sendKeys(Keys.BACK_SPACE);
	  driver.findElement(By.name("email")).sendKeys("110"+Keys.SPACE+"120");
//	  driver.findElement(By.name("email")).sendKeys("120");
	  
  }
  @BeforeClass
  public void setUp() throws IOException {
	  BrowserEngine browserEngine = new BrowserEngine();
	  browserEngine.initConfigData();
	  driver = browserEngine.getBrowser();
  }

  @AfterClass
  public void tearDown() {
//	  driver.quit();
  }

}
