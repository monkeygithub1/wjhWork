package lessons;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class LuanchFirefox {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//System.setProperty("webdriver.firefox.marionette", ".\\Tools\\geckodriver.exe");  
        
        System.setProperty("webdriver.gecko.driver", ".\\Tools\\geckodriver.exe");  
          
        //��ʼ��һ����������ʵ����ʵ�����ƽ�driver  
        WebDriver driver = new FirefoxDriver();  
        //��󻯴���  
        driver.manage().window().maximize();  
        //�������Եȴ�ʱ��  
        driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);  
          
        // get()��һ��վ��  
        driver.get("http://pmistest.womow.cn/pmis");  
        //getTitle()��ȡ��ǰҳ��title��ֵ  
        System.out.println("��ǰ��ҳ��ı����ǣ� "+ driver.getTitle());  
          
        //�رղ��˳������  
        //driver.quit();  
		System.out.println("Fuck You ");
	}

}
