package lessons;   
   
import java.util.concurrent.TimeUnit;     
   
import org.openqa.selenium.WebDriver;   
import org.openqa.selenium.ie.InternetExplorerDriver;   
   
   
     
public class LaunchIE {     
     
    public static void main(String[] args) {     
             
        System.setProperty("webdriver.ie.driver", ".\\Tools\\IEDriverServer.exe");     
             
        //��ʼ��һ��IE�����ʵ����ʵ�����ƽ�driver     
        WebDriver driver = new  InternetExplorerDriver();    
        //��󻯴���     
        driver.manage().window().maximize();     
        //�������Եȴ�ʱ��     
        driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);     
             
        // get()��һ��վ��     
        driver.get("https://www.baidu.com");     
        //getTitle()��ȡ��ǰҳ��title��ֵ     
        System.out.println("��ǰ��ҳ��ı����ǣ� "+ driver.getTitle());     
             
        //�رղ��˳������     
        driver.quit();     
             
    }     
     
}    
