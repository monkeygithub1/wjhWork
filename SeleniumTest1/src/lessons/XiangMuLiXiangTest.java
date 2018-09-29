package lessons;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class XiangMuLiXiangTest {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String pmis_url = "http://pmistest.womow.cn/pmis";
		String username = "18795039941";//崔永欢，项目经理
		String password = "qw";
		String project_name = "自动化项目立项3";
		String PM = "崔永欢";
		String customer = "客户1-2-3-13910623294";
		
		///使用谷歌浏览器
		System.setProperty("webdriver.chrome.driver", ".\\Tools\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		driver.get(pmis_url);

		//输入用户名、密码
		driver.findElement(By.id("phoneUn")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.xpath(".//*[@id='loginBtn']")).click();
		
		//关闭欢迎页
		Thread.sleep(4000);
		driver.findElement(By.xpath(".//*[@id='layui-layer1']/span[@class=\"layui-layer-setwin\"]/a")).click();
		
		//项目立项管理
		driver.findElement(By.xpath(".//*[@id='win10-shortcuts']/div[contains(@onclick,\"项目立项管理\")]")).click();
		
		//申请立项
		driver.switchTo().frame(0); //切换到申请立项页
		driver.switchTo().frame("applyFrame");//切换到按钮所在frame
		driver.findElement(By.xpath(".//button[@onclick=\"toAdd()\"]")).click();
		driver.switchTo().defaultContent(); 
		driver.switchTo().frame(1); //切换到新建策划项目页
		//项目名称
	    driver.findElement(By.id("proj_name_")).sendKeys(project_name);
	    //项目经理
	    Thread.sleep(2000);
	    driver.findElement(By.linkText("选择")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.id("scuQueryParam")).sendKeys(PM);
	    Thread.sleep(2000);
	    driver.findElement(By.cssSelector("input[type=\"button\"]")).click();
	    driver.findElement(By.name("scuResults")).click();
	    driver.findElement(By.linkText("确定选择")).click();
	    //所属部门
	    Thread.sleep(2000);
	    driver.findElement(By.cssSelector("input.layui-input")).click();
	    driver.findElement(By.cssSelector("input.layui-input")).sendKeys("监控事业部");
	    Thread.sleep(2000);
	    driver.findElement(By.xpath(".//dd[text()='监控事业部']")).click();
	    //参与部门
	    driver.findElement(By.id("cy_dept_")).sendKeys("参与部门-非必填！");
	    //所属区域
	    driver.findElement(By.cssSelector("tr.row0 > td.td_left > div.layui-form-select > div.layui-select-title > input.layui-input")).click();
	    driver.findElement(By.xpath(".//dd[text()='华北区域']")).click();
	    //客户
	    driver.findElement(By.id("kehu")).sendKeys(customer);
	    //项目性质
	    driver.findElement(By.xpath("(//input[@value=''])[3]")).click();
	    driver.findElement(By.xpath(".//dd[text()='牵头']")).click();
	    //项目管理类型
	    driver.findElement(By.xpath("(//input[@value=''])[4]")).click();
	    driver.findElement(By.xpath(".//dd[text()='R（研究）']")).click();
	    //业务类型
	    driver.findElement(By.xpath("(//a[contains(text(),'选择')])[2]")).click();
	    driver.switchTo().frame(0);
	    driver.findElement(By.xpath("(//input[@name='bizType'])[51]")).click();
	    driver.switchTo().defaultContent();
	    driver.switchTo().frame(1);
	    driver.findElement(By.linkText("确定选择")).click();
	    //预计合同额
	    driver.findElement(By.id("yuji_contract_sum_")).sendKeys("1000");
	    //项目概述
	    driver.findElement(By.id("descript_")).sendKeys("项目概述非必填");
	    //保存
	    driver.findElement(By.id("lxBaseSaveBtn")).click();
	    //预算
	    Thread.sleep(2000);
	    driver.findElement(By.id("yusuanH2")).click();
	    //开票合同额
	    driver.switchTo().frame("yusuanFrame");
	    driver.findElement(By.name("ys_kphte")).sendKeys("1000");
	    //保存
	    driver.findElement(By.id("budgetSaveBtn")).click();
	    //alert弹窗
	    Thread.sleep(2000);
	    System.out.println(driver.switchTo().alert().getText());
	    driver.switchTo().alert().accept();
	    //提交申请
	    Thread.sleep(2000);
	    driver.switchTo().defaultContent();
	    driver.switchTo().frame(1);
	    driver.findElement(By.xpath("//button[text()='提交申请']")).click();
	    Thread.sleep(2000);
	    System.out.println(driver.switchTo().alert().getText());
	    driver.switchTo().alert().dismiss();
	  }
}
