package wjh.pmis.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestBase {
	public Properties prop;
	public Properties prop_product;
	public int RESPONSE_STATUS_CODE_200 = 200;
	public int RESPONSE_STATUS_CODE_201 = 201;
	public int RESPONSE_STATUS_CODE_404 = 404;
	public int RESPONSE_STATUS_CODE_500 = 500;
	/*
	 * 接口URL
	 */
	public TestBase(){
		try{
			//测试服配置
			prop = new Properties();
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/wjh/pmis/config/config.properties");
			prop.load(fis);
			//产品化配置
			prop_product = new Properties();
			FileInputStream fis_prod = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/wjh/pmis/config/config_product.properties");
			prop_product.load(fis_prod);
		}catch (FileNotFoundException e){
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	 

}
