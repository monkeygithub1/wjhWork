package framework;

import org.openqa.selenium.WebDriver;   
import org.openqa.selenium.WebElement;   


public class BasePage {
	public static WebDriver driver;   
    public static String pageTitle;   
    public static String pageUrl;   
       
    /*  
     * ���췽��  
     */   
    protected BasePage (WebDriver driver){   
        BasePage.driver = driver;   
    }   
       
    /*  
     * ���ı����������ַ�  
     */   
    protected void type(WebElement element , String text){   
        try {   
                if (element.isEnabled()) {   
                    element.clear();   
                    Logger.Output(LogType.LogTypeName.INFO, "Clean the value if any in "+ element.toString()+".");   
                    element.sendKeys(text);   
                    Logger.Output(LogType.LogTypeName.INFO, "Type value is: " + text+".");   
                }   
            } catch (Exception e) {   
                Logger.Output(LogType.LogTypeName.ERROR, e.getMessage()+".");   
                }   
               
        }   
       
    /*  
     * ���Ԫ�أ�����ָ���������  
     */    
    protected void click(WebElement element){   
               
        try {   
                if (element.isEnabled()) {   
                        element.click();   
                        Logger.Output(LogType.LogTypeName.INFO, "Element: "+element.toString()+" was clicked.");   
                    }   
                } catch (Exception e) {   
                        Logger.Output(LogType.LogTypeName.ERROR, e.getMessage()+".");   
                        }   
               
        }   
           
    /*  
     * ���ı������ִ���������  
     */   
    protected void clean(WebElement element){   
               
        try {   
                if (element.isEnabled()) {   
                    element.clear();   
                    Logger.Output(LogType.LogTypeName.INFO, "Element "+element.toString()+" was cleaned.");   
                }   
            } catch (Exception e) {   
                Logger.Output(LogType.LogTypeName.ERROR, e.getMessage()+".");   
            }   
               
        }   
       
    /*  
     * �ж�һ��ҳ��Ԫ���Ƿ���ʾ�ڵ�ǰҳ��  
     */     
    protected void verifyElementIsPresent(WebElement element){   
               
        try {   
                if (element.isDisplayed()) {   
                    Logger.Output(LogType.LogTypeName.INFO, "This Element " + element.toString().trim()+" is present.");   
                           
                }   
            } catch (Exception e) {   
                Logger.Output(LogType.LogTypeName.ERROR, e.getMessage()+".");   
            }   
        }   
       
    /*  
     * ��ȡҳ��ı���  
     */   
    protected String getCurrentPageTitle(){   
           
        pageTitle=driver.getTitle();   
        Logger.Output(LogType.LogTypeName.INFO, "Current page title is "+pageTitle);   
        return pageTitle;   
       }   
       
    /*  
     * ��ȡҳ���url  
     */   
    protected String getCurrentPageUrl(){   
           
        pageUrl=driver.getCurrentUrl();   
        Logger.Output(LogType.LogTypeName.INFO, "Current page title is "+pageUrl);   
        return pageUrl;   
       }    


}
