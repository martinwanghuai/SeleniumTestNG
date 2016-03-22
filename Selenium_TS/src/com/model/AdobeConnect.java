package com.model;


import java.util.ArrayList;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.abstractclasses.TestObject;
import com.config.Config;
import com.config.Labels;
import com.junit.JUnitAssert;
import com.utils.PropertiesFileUtils;
import com.utils.WebDriverUtils;
import com.view.Navigator;

/** 
 * @author martin.wang
 *
 */
public class AdobeConnect extends com.model.Classroom{

	
	public AdobeConnect(){
		super();
	}

	

	public void checkExpectedResult_UI(org.openqa.selenium.WebDriver driver,java.lang.String para1){
		super.checkExpectedResult_UI(driver, para1);
	}

	public void runCreate(WebDriver driver, ArrayList<TestObject> inputs){
		Properties prop= PropertiesFileUtils.loadProperties(Config.getInstance().getProperty("AdobeAccountConfFile"));
		Account account = new Account();
		account.setType(Labels.AdobeConnect.toString());
		account.setDisplayName(prop.getProperty("DisplayName"));
		account.setAccountURL(prop.getProperty("AccountURL"));
		System.out.println("AccountURL=" + prop.getProperty("AccountURL"));
		account.setUserName(prop.getProperty("UserName"));
		account.setUserPWD(prop.getProperty("UserPWD"));
		account.setAPIKey( prop.getProperty("APIKey"));
		account.setSiteName( prop.getProperty("SiteName"));
		account.setSiteID( prop.getProperty("SiteID"));
		account.setPartnerID( prop.getProperty("PartnerID"));
		
		/**Account account = new Account();
		account = account.runCreateAdobeConnectAccount(driver);**/
		account.runCreate(driver);
		
		//create courses
		super.runCreate(driver);
		WebDriverUtils.switchToFrame(driver, "BSCAT_LEFT");

		By by = By.linkText(Config.getInstance().getProperty("label.ModuleProperties"));
		Navigator.explicitWait(3000);
		WebDriverUtils.clickLink(driver, by);
		
		Navigator.explicitWait(3000);
		by = By.partialLinkText(Config.getInstance().getProperty("label.Define_Launch_Properties"));
		WebDriverUtils.clickLink(driver, by);
		WebDriverUtils.switchToFrame(driver, "BSCAT_MAIN");
		by = By.id("LINT");
		String str = "Adobe Connect";
		WebDriverUtils.select_selector(driver, by, str);
		Navigator.explicitWait(3000);
		by = By.name("selectAcct");
		str = account.getDisplayName();
		WebDriverUtils.select_selector(driver, by, str);

		Navigator.explicitWait(3000);
		
		by = By.id("sessionPassword");
		str = account.getUserPWD();
		WebDriverUtils.fillin_textbox(driver, by, str);
		

		by = By.id("testConnButton");
		WebDriverUtils.clickButton(driver, by);
		//Make sure to setup successful connection
		WebDriverUtils.switchToPopUpWin(driver);
		JUnitAssert.assertTrue(WebDriverUtils.textPresentInPage(driver, "Successful"), "Fail to connect Adobe server");
		
		by = By.name("close");
		WebDriverUtils.clickButton(driver, by);
		
		WebDriverUtils.switchToParentWin(driver);
		WebDriverUtils.switchToFrame(driver, "BSCAT_TOP");
		by = By.cssSelector("img[alt=\"Save\"]");
		WebDriverUtils.clickButton(driver, by);
	}
	
	/**Empty method body since it's a test suite
	 * 
	 * @param driver
	 */
	public void runCreateAccountAndSetupModule(WebDriver driver){
		
	}
}