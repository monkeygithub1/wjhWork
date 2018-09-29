package cpaTest;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import framework.BrowserEngine;
import pageObjects.PMIS_Page;

public class Win {
	WebDriver driver;

	@BeforeClass
  	public void setUp() throws IOException {
  		BrowserEngine browserEngine = new BrowserEngine();
  		browserEngine.initConfigData();
  		driver = browserEngine.getBrowser();
  		driver = browserEngine.getBrowser();
  		driver = browserEngine.getBrowser();
  		driver = browserEngine.getBrowser();
  		driver = browserEngine.getBrowser();
  		driver = browserEngine.getBrowser();
  	}
 	@Test//一次通过
	public void applyNewCpa() throws InterruptedException {
		PMIS_Page pmis = PageFactory.initElements(driver, PMIS_Page.class);
		}
}