package lessons;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class PuTongBaoXiaoTest1 {

	public static void main(String[] args) throws Exception {
		String pmis_url = "http://pmistest.womow.cn/pmis";
		String username = "18795039941";//����������Ŀ����
		String password = "qw";
		
		///ʹ�ùȸ������
		System.setProperty("webdriver.chrome.driver", ".\\Tools\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		driver.get(pmis_url);

		//�����û���������
		driver.findElement(By.id("phoneUn")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.xpath(".//*[@id='loginBtn']")).click();
		
		//�رջ�ӭҳ
		Thread.sleep(4000);
		driver.findElement(By.xpath(".//*[@id='layui-layer1']/span[@class=\"layui-layer-setwin\"]/a")).click();		
		
		//�򿪷��ñ���
		driver.findElement(By.xpath(".//*[@id='win10-shortcuts']/div[contains(@onclick,\"���ñ���\")]")).click();
		//�����ҵ�����
		driver.switchTo().frame(0);
		driver.findElement(By.xpath("html/body/div[@class=\"layui-tab layui-tab-brief\"]/ul/li[text()='�ҵ�����']")).click();	
		//ѡ����
		Thread.sleep(2000);
		driver.switchTo().frame("waitSpFrame");
		//driver.findElement(By.xpath("html/body/div[2]/div[2]/table/tbody/tr[1]/td[7]/div/a")).click();
		driver.findElement(By.xpath(".//td[@data-content=\"FB201801158367\"]/div/a")).click();
	}

}
