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
  		System.out.println("��¼�ĸ���1-PMIS���Է���2-PMIS��Ʒ����������3-PMIS��Ʒ���ͻ���");
  		int url = scan.nextInt();
        System.out.println("������Chrome��");   
        int user_input_number = scan.nextInt();   
  		for(int i=1;i<=user_input_number;i++){
  			driver = browserEngine.getBrowser(url);
  		}
  		
	}

}
