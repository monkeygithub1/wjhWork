package lessons;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LuanchChrome {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", ".\\Tools\\chromedriver.exe");
		//��ʼ��һ��chrome�����ʵ����ʵ�����ƽ�driver
		WebDriver driver = new ChromeDriver();
		//��󻯴���
		driver.manage().window().maximize();
		//�������Եȴ�ʱ��
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		// get()��һ��վ��
		driver.get("http://pmistest.womow.cn/pmis");
		//getTitle()��ȡ��ǰҳ��title��ֵ
		System.out.println("��ǰ��ҳ��ı����ǣ� " + driver.getTitle());
		//�رղ��˳������ 
		//driver.quit();
	}

}
