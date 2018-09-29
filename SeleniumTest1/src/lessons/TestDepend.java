package lessons;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;

public class TestDepend {
	WebDriver driver;
  @Test
  public void openBaidu() {
	  driver.get("https://www.baidu.com");
	  System.out.println(driver.getTitle());
  }
  //@Test(enabled = false)
  @Test(dependsOnMethods={"openBaidu"})
  public void testSearch1(){
	  driver.findElement(By.id("kw")).sendKeys("Selenium");
	  driver.findElement(By.id("su")).click();
  }
  @Test(dependsOnMethods={"testSearch1"})
  public void testSearch(){
	  driver.findElement(By.id("kw")).clear();
	  driver.findElement(By.id("kw")).sendKeys("Selenium+Java");
	  driver.findElement(By.id("su")).click();
  }
  @BeforeClass
  public void beforeClass() {
	  System.setProperty("webdriver.chrome.driver", ".\\Tools\\chromedriver.exe");
	  driver = new ChromeDriver();
	  driver.manage().window().maximize();
  }

  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
