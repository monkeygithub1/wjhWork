package lessons;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class TestWithoutPOM {
    WebDriver driver;   
    
    @BeforeClass   
    public void setUp() throws Exception{   
           
        System.setProperty("webdriver.chrome.driver", ".\\Tools\\chromedriver.exe");   
        driver = new ChromeDriver();   
        driver.manage().window().maximize();   
        driver.get("https://www.jd.com/");   
        Thread.sleep(2000);   
    }   
       
    @Test   
    public void testLogin() {   
           
        // �����¼   
        driver.findElement(By.id("ttbar-login")).click();   
        // ����˺ŵ�¼   
        driver.findElement(By.xpath("//*/div[@class='login-form']/div[2]/a")).click();   
        // �����û���   
        driver.findElement(By.id("loginname")).sendKeys("user1");   
        // ��������   
        driver.findElement(By.id("nloginpwd")).sendKeys("password");   
        //
        driver.findElement(By.id("loginsubmit")).click();   
           
    }   
       
    @AfterClass   
    public void tearDown(){   
           
        driver.quit();   
    }   

}
