package testSuites;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Test1111 {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", ".\\Tools\\chromedriver.exe");     
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
        driver.get("https://pmistest.womow.cn/pmis/views/index.jsp");
        driver.findElement(By.id("phoneUn")).sendKeys("13910623294");
        driver.findElement(By.id("password")).sendKeys("qetqet");
        driver.findElement(By.id("loginBtn")).click();
        Thread.sleep(4000);
        driver.findElement(By.xpath(".//*[@id='layui-layer1']/span[@class=\"layui-layer-setwin\"]/a")).click();
        //driver.findElement(By.id("win10_btn_win")).click();
        Thread.sleep(2000);
        //driver.findElement(By.xpath(".//*[@id=\"win10-menu\"]/div[1]/div[30]")).click();
        driver.findElement(By.xpath(".//div[@onclick=\"logoutDo()\"]")).click();
        driver.switchTo().alert().accept();
      //*[@id="win10-menu"]/div[1]/div[30]
	}

}
