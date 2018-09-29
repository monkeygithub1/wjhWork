package lessons;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.firefox.FirefoxProfile;
//import org.openqa.selenium.firefox.internal.ProfilesIni; 

/*
 * 新建策划项目
 */

public class FirstScript {

	public static void main(String[] args) throws Exception {
		//自定义参数
		String pmis_url = "https://pmistest.womow.cn/pmis";
		String username = "13910623294";
		String password = "qetqet";
		String project_name = "自动化脚本4";
		String success_rate = "20";
		String planner = "武军豪";
		String PM = "郑成龙";
		String customer = "大王庄人民";
		
		///使用谷歌浏览器
		System.setProperty("webdriver.chrome.driver", ".\\Tools\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		//使用火狐浏览器
//		System.setProperty("webdriver.gecko.driver", ".\\Tools\\geckodriver.exe");//火狐
//		//FirefoxProfile profile = new ProfilesIni().getProfile("default");
//		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		driver.get(pmis_url);
		System.out.println("当前打开页面的标题是： " + driver.getTitle());
		
		//加入断言，判断页面标题是否是正确
		try{
			String pmis_title = "登录页（PMIS）";
			assert pmis_title.equals(driver.getTitle());
			System.out.println("Test pass~");
		}catch(Exception e){
			e.printStackTrace();
			//System.out.println(e.ToString());
		}
		
		//输入用户名、密码
		driver.findElement(By.id("phoneUn")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(password);
		//driver.findElement(By.id("loginBtn")).click();
		//driver.findElement(By.className("login-submit")).click();
		driver.findElement(By.xpath(".//*[@id='loginBtn']")).click();
		//Thread.sleep(5000);
		
		System.out.println(driver.getCurrentUrl());
		//assert driver.getCurrentUrl().equals("http://pmistest.womow.cn/pmis/views/index.jsp");
		
		//关闭欢迎页
		Thread.sleep(4000);
		driver.findElement(By.xpath(".//*[@id='layui-layer1']/span[@class=\"layui-layer-setwin\"]/a")).click();
		
		//策划项目
		//driver.findElement(By.xpath(".//*[@id='win10-shortcuts']/div[@onclick=\"Win10.openUrl('/pmis/views/cehua/list.jsp','<img class=\\'icon\\' src=\\'/pmis/res_pmis/common/images/chProj.png\\'/>策划项目')\"]")).click();
		driver.findElement(By.xpath(".//*[@id='win10-shortcuts']/div[contains(@onclick,\"策划项目'\")]")).click();
		
		//新建策划项目
		driver.switchTo().frame(0); //切换到策划项目页
		driver.findElement(By.xpath("html/body/fieldset/button[@onclick=\"toAdd()\"]")).click();//点击新建按钮
		driver.switchTo().defaultContent(); 
		driver.switchTo().frame(1); //切换到新建策划项目页
		//填写项目名称
		driver.findElement(By.name("prj_name_")).sendKeys(project_name);
		//填写成功率
		driver.findElement(By.id("success_rate_")).sendKeys(success_rate);
		//选择策划人
		driver.findElement(By.xpath(".//*[@id='planner_']/../a[text()='选择']")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath(".//*[@id='scuQueryParam']")).sendKeys(planner);
		Thread.sleep(200);
		driver.findElement(By.xpath(".//*[@id='scuQueryParam']/../input[@value='搜索']")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath(".//*[@id='findResultDiv']/input")).click();
		driver.findElement(By.xpath(".//*[@id='layui-layer1']/div[@class='layui-layer-btn layui-layer-btn-']/a[text()='确定选择']")).click();
		//选择项目经理
		Thread.sleep(2000);
		driver.findElement(By.xpath(".//*[@id='prj_mgr_']/../a[text()='选择']")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath(".//*[@onkeydown=\"scuHuiche('PM')\"]")).sendKeys(PM);
		Thread.sleep(200);
		driver.findElement(By.xpath(".//*[@id='scuQueryParam']/../input[@onclick=\"findUser('PM')\"]")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath(".//*[@id='findResultDiv']/input")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath(".//*[@id='layui-layer2']/div[@class='layui-layer-btn layui-layer-btn-']/a[text()='确定选择']")).click();
		//选择区域
		driver.findElement(By.xpath("html/body/form/div/table/tbody/tr[3]/td[2]/div/div/input")).click();
		driver.findElement(By.xpath("html/body/form/div/table/tbody/tr[3]/td[2]/div/dl/dd[2]")).click();
		//填写客户
		driver.findElement(By.xpath(".//*[@id='customer_']")).sendKeys(customer);
		//选择承接部门
		driver.findElement(By.xpath("html/body/form/div/table/tbody/tr[4]/td[2]/div/div/input")).click();
		driver.findElement(By.xpath("html/body/form/div/table/tbody/tr[4]/td[2]/div/dl/dd[7]")).click();
		//填写项目额度
		driver.findElement(By.xpath(".//*[@id='money_mini_']")).sendKeys("1000");
		driver.findElement(By.xpath(".//*[@id='money_max_']")).sendKeys("2000");
		//选择资金来源
		driver.findElement(By.xpath("html/body/form/div/table/tbody/tr[5]/td[4]/div/div/input")).click();
		driver.findElement(By.xpath("html/body/form/div/table/tbody/tr[5]/td[4]/div/dl/dd[2]")).click();
		//选择业务类型
		driver.findElement(By.xpath("html/body/form/div/table/tbody/tr[6]/td[2]/a")).click();
		//driver.switchTo().frame("layui-layer-iframe3");
//		driver.switchTo().defaultContent();
		driver.switchTo().frame(0);
		driver.findElement(By.xpath(".//input[@value=\"企业级业务咨询-管理咨询-管理标准化咨询-无-无\"]")).click();
		driver.switchTo().defaultContent();
		driver.switchTo().frame(1);
		System.out.println(".//*[@id='layui-layer3']/div[@class=\"layui-layer-btn layui-layer-btn-\"]/a[@class=\"layui-layer-btn0\"]");
		driver.findElement(By.xpath(".//*[@id='layui-layer3']/div[@class=\"layui-layer-btn layui-layer-btn-\"]/a[@class=\"layui-layer-btn0\"]")).click();
		//保存
		driver.switchTo().defaultContent();
		Thread.sleep(1000);
		driver.switchTo().frame(1);
		driver.findElement(By.xpath("html/body/form[@class=\"layui-form\"]/center/button[text()='保存']")).click();
		//关闭并退出浏览器 
		//driver.quit();
	}

}
