package com.netdimen.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.netdimen.junit.JUnitAssert;
import com.netdimen.utils.CriteriaParser;
import com.netdimen.utils.WebDriverUtils;
import com.netdimen.view.EmailUI;
import com.netdimen.view.FunctionUI;
import com.netdimen.view.Navigator;

/**
 * 
 * @author martin.wang
 *
 */
public class EMailTemplate extends com.netdimen.abstractclasses.TestObject {
	private String ParentTemplate = "", TemplateName = "",ModuleTitle = "";
	
	public void setModuleTitle(String moduleTitle) {
		ModuleTitle = moduleTitle;
	}

	private String ModuleID_search = "";
	
	public boolean equals(com.netdimen.abstractclasses.TestObject para0) {
		boolean result = false;
		return result;
	}

	public EMailTemplate() {
		super();
	}

	public String getParentTemplate() {
		return ParentTemplate;
	}

	public String getTemplateName() {
		return TemplateName;
	}

	public void setParentTemplate(String parenttemplate) {
		ParentTemplate = parenttemplate;
	}

	public void setTemplateName(String templatename) {
		TemplateName = templatename;
	}

	public void setParentTemplate_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
			By by = By.linkText(str);
			WebDriverUtils.clickLink(driver, by);
		}
	}

	public void setTemplateName_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
		}
	}

	public String getModuleID_search() {
		return ModuleID_search;
	}
	
	public void setModuleID_search(String moduleID_search) {
		ModuleID_search = moduleID_search;
	}

	
	public static void selectEmail(WebDriver driver, String email){
		//Expand Email tree
		WebDriverUtils.switchToFrame(driver, "EMAILTEMPLATETREE");
		Navigator.explicitWait(1000);
		By by = null;
		if(!email.equals("")){
			//expand system/user email template
			by = By.xpath("//a[descendant::img[@alt='Expand']]");
			int size = WebDriverUtils.getHowManyByPresntInPage(driver,by,false);
			while(size > 0){
				WebDriverUtils.clickLink(driver, by);
				size--;
			}
			
			Navigator.explicitWait(1000);
			by = By.linkText(email);
			WebDriverUtils.clickLink(driver, by);
			Navigator.explicitWait(3000);
		}
		
		//Select email
		WebDriverUtils.switchToFrame(driver, "EMAILTEMPLATEMENU");
		Navigator.explicitWait(1000);
		by = By.xpath("//td[@class='smalleditorback2']/table/tbody/tr/td[6]/a");
		WebDriverUtils.clickLink(driver, by);
	}

	public void runCheckVisibility(WebDriver driver) {
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
				"ManageCenter", "EmailEditor"));
		
		WebDriverUtils.switchToPopUpWin(driver);
		WebDriverUtils.switchToFrame(driver, "EMAILTEMPLATETREE");
		Navigator.explicitWait(1000);
		
		//1. Expand parent template
		this.setParentTemplate_UI(driver, this.getParentTemplate());
		Navigator.explicitWait(1000);
		
		//2.Check visibility 
		HashMap<String, ArrayList<String>> criteria_emails =CriteriaParser.parseKeyValueList(":", ";", this.getExpectedResult()); //UIFunctionUtils.parseParticipants(this.getExpectedResult());
		Iterator<String> criteria = criteria_emails.keySet().iterator();
		By by = null;
		
		while(criteria.hasNext()){
			String criterion = criteria.next();
			ArrayList<String> emails = criteria_emails.get(criterion);
			for(String email: emails){
				//2.2 check email visibility
				by = By.linkText(email);
				//int size = WebDriverUtils.getHowManyByPresntInPage(by);
				switch(criterion.toLowerCase()){
				case "visible":
					JUnitAssert.assertTrue(WebDriverUtils.getHowManyByPresntInPage(driver,by,true)> 0, "EMail is invisible:" + email);
					break;
				case "invisible":
					JUnitAssert.assertTrue(WebDriverUtils.getHowManyByPresntInPage(driver,by,false)== 0, "EMail is visible:" + email);
					break;
				}						
				
			}
		}
	}
	
	public void runSendInstrcNotification (WebDriver driver) {
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
					"ManageCenter", "Modules"));
		
		By by = By.id("KEYW");
		String ModuleID_search = this.getModuleID_search();/* input userID  'ad hoc o1'*/
		WebDriverUtils.fillin_textbox(driver, by, ModuleID_search);
		Navigator.explicitWait(3000);
		
		By by1 =By.xpath(".//input[@value='Filter']");/*search*/
		WebDriverUtils.clickButton(driver, by1);
		Navigator.explicitWait(3000);
		
		By by2 = By.xpath("/descendant::*[@href='javascript:void(0)'][last()]"); /* click result and wait */
		WebDriverUtils.clickButton(driver, by2);
		Navigator.explicitWait(3000);
		
		WebDriverUtils.switchToPopUpWin(driver);/*PopUp window*/
		WebDriverUtils.switchToFrame(driver, "BSCAT_LEFT");
		By by3 = By.linkText("Session Properties"); /* click session properties and wait */
		WebDriverUtils.clickButton(driver, by3);
		/*click instructors*/
		WebDriverUtils.switchToFrame(driver, "BSCAT_LEFT");
		By by4 = By.linkText("4. Instructors"); /* click instructor and wait */
		WebDriverUtils.clickButton(driver, by4);
		Navigator.explicitWait(3000);
		/*click Send Instructor */
		WebDriverUtils.switchToFrame(driver, "BSCAT_MAIN");
		By by5 = By.linkText("Send Instructor Notifications"); /* click send instructor notification */
		WebDriverUtils.clickButton(driver, by5);
		
		WebDriverUtils.switchToPopUpWin(driver);/*PopUp window*/
		By by6 = By.id("token-input-netd_token_input_CC");/* input CC */
		WebDriverUtils.fillin_textbox(driver, by6, UID);
		Navigator.explicitWait(3000);
		By by7 = By.xpath("html/body/div[3]/ul/li[1]");
		WebDriverUtils.clickButton(driver, by7);
		By by8 = By.id("SUBJECT");/*input subject*/
		WebDriverUtils.fillin_textbox(driver, by8, UID);	
		By by9 = By.id("TAREA1");/*input content*/
		WebDriverUtils.fillin_textbox(driver, by9, UID);
		By by10 = By.id("aBtn");/*click submit*/
		WebDriverUtils.clickButton(driver, by10);
		Navigator.explicitWait(6000);
	}
	
	
/*	public String toString(){
		return new StringBuilder().append(this.getClass().getName()).append("_").
				append(this.getFuncType()).append("_").
				append(this.getUID()).append("_").
				append(this.getTemplateName()).toString();
	}*/


}

