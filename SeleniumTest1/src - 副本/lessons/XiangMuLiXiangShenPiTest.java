package lessons;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class XiangMuLiXiangShenPiTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		String pmis_url = "http://pmistest.womow.cn/pmis";
		String username = "13612194369";//��������ҵ���ܾ���
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
		
		//��Ŀ�������
		driver.findElement(By.xpath(".//*[@id='win10-shortcuts']/div[contains(@onclick,\"��Ŀ�������\")]")).click();
		
		//�ҵ�����
		driver.switchTo().frame(0);
		driver.findElement(By.xpath(".//li[text()='�ҵ�����']")).click();
		//����б��е�����
		Thread.sleep(2000);
		driver.switchTo().frame("waitSpFrame");
		driver.findElement(By.xpath("html/body/div[2]/div[2]/table/tbody/tr[1]/td[6]/div/a[text()='����']")).click();//��һ�е�����
		//����
		driver.switchTo().defaultContent(); 
		driver.switchTo().frame(1);
//		//ͬ��
//		driver.findElement(By.id("spCommont")).sendKeys("OK");
//		driver.findElement(By.id("sp1Btn")).click();
		//�ܾ�
		driver.findElement(By.id("spCommont")).sendKeys("NOT OK");
		driver.findElement(By.id("sp2Btn")).click();
		Thread.sleep(2000);
		driver.switchTo().alert().accept();//�رյ���
	}

}
