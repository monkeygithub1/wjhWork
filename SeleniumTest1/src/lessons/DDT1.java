package lessons;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DDT1 {
  @Test(dataProvider = "testdata")
  public void TestLogin(String username, String password) {
	  System.setProperty("webdriver.chrome.driver", ".\\Tools\\chromedriver.exe");
	  WebDriver driver = new ChromeDriver();
	  driver.manage().window().maximize();
	  driver.get("http://pmistest.womow.cn/pmis");
	  
	  driver.findElement(By.id("phoneUn")).sendKeys(username);
	  driver.findElement(By.id("password")).sendKeys(password);
	  driver.findElement(By.id("loginBtn")).click();
	  
  }
  @DataProvider(name = "testdata")
  public Object[][] TestDataFeed(){
	//创建一个二维数组
	  Object[][] pmisdata = new Object[2][2];
	  pmisdata[0][0] = "18602634891";
	  pmisdata[0][1] = "qw";
	  pmisdata[1][0] = "13910623294";
	  pmisdata[1][1] = "qw";
	  return pmisdata;
  }
}
