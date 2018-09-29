package lessons;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.firefox.FirefoxProfile;
//import org.openqa.selenium.firefox.internal.ProfilesIni; 

/*
 * �½��߻���Ŀ
 */

public class FirstScript {

	public static void main(String[] args) throws Exception {
		//�Զ������
		String pmis_url = "https://pmistest.womow.cn/pmis";
		String username = "13910623294";
		String password = "qetqet";
		String project_name = "�Զ����ű�4";
		String success_rate = "20";
		String planner = "�����";
		String PM = "֣����";
		String customer = "����ׯ����";
		
		///ʹ�ùȸ������
		System.setProperty("webdriver.chrome.driver", ".\\Tools\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		//ʹ�û�������
//		System.setProperty("webdriver.gecko.driver", ".\\Tools\\geckodriver.exe");//���
//		//FirefoxProfile profile = new ProfilesIni().getProfile("default");
//		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		driver.get(pmis_url);
		System.out.println("��ǰ��ҳ��ı����ǣ� " + driver.getTitle());
		
		//������ԣ��ж�ҳ������Ƿ�����ȷ
		try{
			String pmis_title = "��¼ҳ��PMIS��";
			assert pmis_title.equals(driver.getTitle());
			System.out.println("Test pass~");
		}catch(Exception e){
			e.printStackTrace();
			//System.out.println(e.ToString());
		}
		
		//�����û���������
		driver.findElement(By.id("phoneUn")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(password);
		//driver.findElement(By.id("loginBtn")).click();
		//driver.findElement(By.className("login-submit")).click();
		driver.findElement(By.xpath(".//*[@id='loginBtn']")).click();
		//Thread.sleep(5000);
		
		System.out.println(driver.getCurrentUrl());
		//assert driver.getCurrentUrl().equals("http://pmistest.womow.cn/pmis/views/index.jsp");
		
		//�رջ�ӭҳ
		Thread.sleep(4000);
		driver.findElement(By.xpath(".//*[@id='layui-layer1']/span[@class=\"layui-layer-setwin\"]/a")).click();
		
		//�߻���Ŀ
		//driver.findElement(By.xpath(".//*[@id='win10-shortcuts']/div[@onclick=\"Win10.openUrl('/pmis/views/cehua/list.jsp','<img class=\\'icon\\' src=\\'/pmis/res_pmis/common/images/chProj.png\\'/>�߻���Ŀ')\"]")).click();
		driver.findElement(By.xpath(".//*[@id='win10-shortcuts']/div[contains(@onclick,\"�߻���Ŀ'\")]")).click();
		
		//�½��߻���Ŀ
		driver.switchTo().frame(0); //�л����߻���Ŀҳ
		driver.findElement(By.xpath("html/body/fieldset/button[@onclick=\"toAdd()\"]")).click();//����½���ť
		driver.switchTo().defaultContent(); 
		driver.switchTo().frame(1); //�л����½��߻���Ŀҳ
		//��д��Ŀ����
		driver.findElement(By.name("prj_name_")).sendKeys(project_name);
		//��д�ɹ���
		driver.findElement(By.id("success_rate_")).sendKeys(success_rate);
		//ѡ��߻���
		driver.findElement(By.xpath(".//*[@id='planner_']/../a[text()='ѡ��']")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath(".//*[@id='scuQueryParam']")).sendKeys(planner);
		Thread.sleep(200);
		driver.findElement(By.xpath(".//*[@id='scuQueryParam']/../input[@value='����']")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath(".//*[@id='findResultDiv']/input")).click();
		driver.findElement(By.xpath(".//*[@id='layui-layer1']/div[@class='layui-layer-btn layui-layer-btn-']/a[text()='ȷ��ѡ��']")).click();
		//ѡ����Ŀ����
		Thread.sleep(2000);
		driver.findElement(By.xpath(".//*[@id='prj_mgr_']/../a[text()='ѡ��']")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath(".//*[@onkeydown=\"scuHuiche('PM')\"]")).sendKeys(PM);
		Thread.sleep(200);
		driver.findElement(By.xpath(".//*[@id='scuQueryParam']/../input[@onclick=\"findUser('PM')\"]")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath(".//*[@id='findResultDiv']/input")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath(".//*[@id='layui-layer2']/div[@class='layui-layer-btn layui-layer-btn-']/a[text()='ȷ��ѡ��']")).click();
		//ѡ������
		driver.findElement(By.xpath("html/body/form/div/table/tbody/tr[3]/td[2]/div/div/input")).click();
		driver.findElement(By.xpath("html/body/form/div/table/tbody/tr[3]/td[2]/div/dl/dd[2]")).click();
		//��д�ͻ�
		driver.findElement(By.xpath(".//*[@id='customer_']")).sendKeys(customer);
		//ѡ��нӲ���
		driver.findElement(By.xpath("html/body/form/div/table/tbody/tr[4]/td[2]/div/div/input")).click();
		driver.findElement(By.xpath("html/body/form/div/table/tbody/tr[4]/td[2]/div/dl/dd[7]")).click();
		//��д��Ŀ���
		driver.findElement(By.xpath(".//*[@id='money_mini_']")).sendKeys("1000");
		driver.findElement(By.xpath(".//*[@id='money_max_']")).sendKeys("2000");
		//ѡ���ʽ���Դ
		driver.findElement(By.xpath("html/body/form/div/table/tbody/tr[5]/td[4]/div/div/input")).click();
		driver.findElement(By.xpath("html/body/form/div/table/tbody/tr[5]/td[4]/div/dl/dd[2]")).click();
		//ѡ��ҵ������
		driver.findElement(By.xpath("html/body/form/div/table/tbody/tr[6]/td[2]/a")).click();
		//driver.switchTo().frame("layui-layer-iframe3");
//		driver.switchTo().defaultContent();
		driver.switchTo().frame(0);
		driver.findElement(By.xpath(".//input[@value=\"��ҵ��ҵ����ѯ-������ѯ-�����׼����ѯ-��-��\"]")).click();
		driver.switchTo().defaultContent();
		driver.switchTo().frame(1);
		System.out.println(".//*[@id='layui-layer3']/div[@class=\"layui-layer-btn layui-layer-btn-\"]/a[@class=\"layui-layer-btn0\"]");
		driver.findElement(By.xpath(".//*[@id='layui-layer3']/div[@class=\"layui-layer-btn layui-layer-btn-\"]/a[@class=\"layui-layer-btn0\"]")).click();
		//����
		driver.switchTo().defaultContent();
		Thread.sleep(1000);
		driver.switchTo().frame(1);
		driver.findElement(By.xpath("html/body/form[@class=\"layui-form\"]/center/button[text()='����']")).click();
		//�رղ��˳������ 
		//driver.quit();
	}

}
