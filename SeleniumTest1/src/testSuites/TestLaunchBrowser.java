package testSuites;

import org.testng.annotations.Test;

import framework.BrowserEngine;

import org.testng.annotations.BeforeClass;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;

public class TestLaunchBrowser {
	public WebDriver driver;
  @Test
  public void clickNews() {
	  driver.findElement(By.id("key")).sendKeys("iPhone 7");
	  driver.findElement(By.xpath("//*[@id='search']/div/div[2]/button")).click();
  }
  @BeforeClass
  public void setUp() throws IOException {
	  BrowserEngine browserEngine = new BrowserEngine();
	  browserEngine.initConfigData();
	  driver = browserEngine.getBrowser();
  }

  @AfterClass
  public void tearDown() {
	  driver.quit();
  }

}
