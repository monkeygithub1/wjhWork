package lessons;

import java.io.File;
import java.io.FileInputStream;
//import java.io.IOException;  

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DDT2 {
    
@Test(dataProvider="testdata")     
public void TestLogin(String username, String password){     

   System.setProperty("webdriver.chrome.driver", ".\\Tools\\chromedriver.exe");
   WebDriver driver = new ChromeDriver();
   Reporter.log("Luanch Chrome");
   driver.manage().window().maximize();
   Reporter.log("maximize windown");
   driver.get("http://pmistest.womow.cn/pmis");
   Reporter.log("open pmis site");
        
   // click login link     
   driver.findElement(By.id("phoneUn")).sendKeys(username);
   driver.findElement(By.id("password")).sendKeys(password);
   driver.findElement(By.id("loginBtn")).click();
   Reporter.log(username+" log in success");
}     
    
@DataProvider(name="testdata")    
public Object[][] TestDataFeed(){   
	Object [][] pmisdata=null;
   try{
	File src = new File(".\\Files\\twousers.xlsx");     
   FileInputStream fis = new FileInputStream(src);     
         
   @SuppressWarnings("resource")     
   XSSFWorkbook wb=new XSSFWorkbook(fis);       
   XSSFSheet sh1= wb.getSheetAt(0);     
   int numberrow = sh1.getPhysicalNumberOfRows();   
          
   pmisdata=new Object[numberrow][2];   
      
   for(int i=0;i<numberrow;i++){     
          
           pmisdata[i][0] = sh1.getRow(i).getCell(0).getStringCellValue();   
           pmisdata[i][1] = sh1.getRow(i).getCell(1).getStringCellValue();   
       }   
   }catch(Exception e){
	   e.printStackTrace();
   }
   return pmisdata;   
      
}   
  
}  
