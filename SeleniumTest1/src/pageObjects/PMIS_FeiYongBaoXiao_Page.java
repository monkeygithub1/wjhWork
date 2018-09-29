package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import framework.BasePage;

public class PMIS_FeiYongBaoXiao_Page extends BasePage {

	protected PMIS_FeiYongBaoXiao_Page(WebDriver driver) {
		super(driver);
	}	
	/*
	 * ҳ��Ԫ��
	 */
	//���ñ�����ť
	@FindBy (xpath=".//*[@id='win10-shortcuts']/div[contains(@onclick,\"���ñ���\")]")
	WebElement home_fybx;
	//������ñ�����ť
	@FindBy (xpath=".//button[@onclick=\"toApply()\"]")
	WebElement fybx_toApply;
	//������ѡ��
	@FindBy (xpath=".//a[@onclick=\"fybxApply.chooseBxr()\"]")
	WebElement fybx_toApply_chooseBxr;//ѡ��ť
	@FindBy (id="search_input")
	WebElement fybx_toApply_chooseBxr_input;//������������
	@FindBy (xpath="//*[@id=\"control\"]/div[@class=\"submit_control\"]/button[text()='ȷ��']")
	WebElement fybx_toApply_chooseBxr_OKt;//������������
	
	//����PMO
	@FindBy (xpath="//*[@id=\"pmo\"]/div/div/input")
	WebElement fybx_toApply_PMO;
	
	//��Ŀ��Ϣ
	@FindBy (xpath="//*[@id=\"baseInfoDiv\"]/div[1]/table/tbody/tr[2]/td[2]/a[1]")
	WebElement fybx_toApply_proj;
	@FindBy (id="spuQueryParam")
	WebElement fybx_toApply_proj_input;
	@FindBy (xpath=".//a[@onclick=\"fybxApply.chooseBxr()\"]")
	WebElement fybx_toApply_proj_serch;//������ť
	//�ض�����
	
	//
	
	
	

}
