package lessons;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class XiangMuLiXiangTest {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String pmis_url = "http://pmistest.womow.cn/pmis";
		String username = "18795039941";//����������Ŀ����
		String password = "qw";
		String project_name = "�Զ�����Ŀ����3";
		String PM = "������";
		String customer = "�ͻ�1-2-3-13910623294";
		
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
		
		//��������
		driver.switchTo().frame(0); //�л�����������ҳ
		driver.switchTo().frame("applyFrame");//�л�����ť����frame
		driver.findElement(By.xpath(".//button[@onclick=\"toAdd()\"]")).click();
		driver.switchTo().defaultContent(); 
		driver.switchTo().frame(1); //�л����½��߻���Ŀҳ
		//��Ŀ����
	    driver.findElement(By.id("proj_name_")).sendKeys(project_name);
	    //��Ŀ����
	    Thread.sleep(2000);
	    driver.findElement(By.linkText("ѡ��")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.id("scuQueryParam")).sendKeys(PM);
	    Thread.sleep(2000);
	    driver.findElement(By.cssSelector("input[type=\"button\"]")).click();
	    driver.findElement(By.name("scuResults")).click();
	    driver.findElement(By.linkText("ȷ��ѡ��")).click();
	    //��������
	    Thread.sleep(2000);
	    driver.findElement(By.cssSelector("input.layui-input")).click();
	    driver.findElement(By.cssSelector("input.layui-input")).sendKeys("�����ҵ��");
	    Thread.sleep(2000);
	    driver.findElement(By.xpath(".//dd[text()='�����ҵ��']")).click();
	    //���벿��
	    driver.findElement(By.id("cy_dept_")).sendKeys("���벿��-�Ǳ��");
	    //��������
	    driver.findElement(By.cssSelector("tr.row0 > td.td_left > div.layui-form-select > div.layui-select-title > input.layui-input")).click();
	    driver.findElement(By.xpath(".//dd[text()='��������']")).click();
	    //�ͻ�
	    driver.findElement(By.id("kehu")).sendKeys(customer);
	    //��Ŀ����
	    driver.findElement(By.xpath("(//input[@value=''])[3]")).click();
	    driver.findElement(By.xpath(".//dd[text()='ǣͷ']")).click();
	    //��Ŀ��������
	    driver.findElement(By.xpath("(//input[@value=''])[4]")).click();
	    driver.findElement(By.xpath(".//dd[text()='R���о���']")).click();
	    //ҵ������
	    driver.findElement(By.xpath("(//a[contains(text(),'ѡ��')])[2]")).click();
	    driver.switchTo().frame(0);
	    driver.findElement(By.xpath("(//input[@name='bizType'])[51]")).click();
	    driver.switchTo().defaultContent();
	    driver.switchTo().frame(1);
	    driver.findElement(By.linkText("ȷ��ѡ��")).click();
	    //Ԥ�ƺ�ͬ��
	    driver.findElement(By.id("yuji_contract_sum_")).sendKeys("1000");
	    //��Ŀ����
	    driver.findElement(By.id("descript_")).sendKeys("��Ŀ�����Ǳ���");
	    //����
	    driver.findElement(By.id("lxBaseSaveBtn")).click();
	    //Ԥ��
	    Thread.sleep(2000);
	    driver.findElement(By.id("yusuanH2")).click();
	    //��Ʊ��ͬ��
	    driver.switchTo().frame("yusuanFrame");
	    driver.findElement(By.name("ys_kphte")).sendKeys("1000");
	    //����
	    driver.findElement(By.id("budgetSaveBtn")).click();
	    //alert����
	    Thread.sleep(2000);
	    System.out.println(driver.switchTo().alert().getText());
	    driver.switchTo().alert().accept();
	    //�ύ����
	    Thread.sleep(2000);
	    driver.switchTo().defaultContent();
	    driver.switchTo().frame(1);
	    driver.findElement(By.xpath("//button[text()='�ύ����']")).click();
	    Thread.sleep(2000);
	    System.out.println(driver.switchTo().alert().getText());
	    driver.switchTo().alert().dismiss();
	  }
}
