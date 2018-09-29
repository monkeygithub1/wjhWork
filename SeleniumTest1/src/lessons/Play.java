package lessons;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Play {

	public static void main(String[] args) {

			System.setProperty("webdriver.chrome.driver",".\\Tools\\chromedriver.exe");     
	        WebDriver driver = new ChromeDriver();
	        driver.manage().window().maximize();
	        driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
	        driver.get("http://account.b2q.com/login.html");
			
		    driver.findElement(By.id("UserName")).sendKeys("chuhui");
		    driver.findElement(By.id("Password")).sendKeys("123456");
		    driver.findElement(By.cssSelector("input.logb.tc")).click();
		    driver.findElement(By.linkText("发布产品")).click();
		    driver.switchTo().frame(0);
		    driver.findElement(By.xpath("/html/body")).sendKeys("慧慧你是不是傻？");

	}
}