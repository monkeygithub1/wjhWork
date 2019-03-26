package duo_kai_chrome;

import java.io.IOException;
import java.util.Scanner;

import org.openqa.selenium.WebDriver;

import framework.BrowserEngine;

public class DuoKai {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BrowserEngine browserEngine = new BrowserEngine();
  		WebDriver driver;
  		Scanner scan = new Scanner(System.in);
  		System.out.println("登录哪个？1-PMIS测试服；2-PMIS产品化开发服；3-PMIS产品化客户服");
  		int url = scan.nextInt();
        System.out.println("开几个Chrome？");   
        int user_input_number = scan.nextInt();   
  		for(int i=1;i<=user_input_number;i++){
  			driver = browserEngine.getBrowser(url);
  		}
  		
	}

}
