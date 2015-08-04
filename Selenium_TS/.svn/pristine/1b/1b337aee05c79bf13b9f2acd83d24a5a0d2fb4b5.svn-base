package com.netdimen.model;


import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.netdimen.abstractclasses.TestObject;
import com.netdimen.config.Config;
import com.netdimen.config.Labels;
import com.netdimen.controller.TestDriver;
import com.netdimen.junit.JUnitAssert;
import com.netdimen.utils.WebDriverUtils;
import com.netdimen.view.EmailUI;
import com.netdimen.view.Navigator;
import com.netdimen.view.SelectorsUI;
import com.netdimen.view.TSSystemUI;

/**A parent class for sub-class AICC.  
 * 
 * @author martin.wang
 *
 */
public class Online extends LearningModule{
	private String Organization="";
	
	public String getOrganization() {
		return Organization;
	}

	public void setOrganization(String organization) {
		Organization = organization;
	}
			
	public Online(){
		super();
	}

	public void checkExpectedResult_UI(WebDriver driver, String expectedResult){
		super.checkExpectedResult_UI(driver, expectedResult);
	}


	@Override
	public boolean equals(TestObject obj){
		if(obj instanceof Online && ((Online)obj).toString().equals(this.toString())){
			return true;
		}else{
			return false;
		}
	}


	public void importContentPackage_UI(WebDriver driver){
		/*WebElement we = driver.findElement(By.xpath("//button/span/span[contains(text(),'Import New Revision')]"));
		WebDriverUtils.mouseOver_UI(driver, we);*/

		//		driver.findElement(By.linkText("Import content package")).click();
	}
	/**
	 * set a module completion deadline for module, and check if system send Gentle Deadline Reminder to learner
	 * @param driver
	 * @param sysSetup
	 */
	public void runSendDeadlineReminder (WebDriver driver){
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
				"ManageCenter", "Modules"));
		
		//open catalog editor
		this.searchModule(driver);
		WebDriverUtils.switchToPopUpWin(driver);
		WebDriverUtils.switchToFrame(driver, "BSCAT_LEFT");
		Navigator.explicitWait(1000);
		
		//go to session
		By by = By.linkText(Labels.Link_Sess_Properties.toString());
		Navigator.explicitWait(1000);
		WebDriverUtils.clickLink(driver, by);
		WebDriverUtils.switchToFrame(driver, "BSCAT_MAIN");
		Navigator.explicitWait(1000);
		//set deadline : x Days from Enrollment
		by = By.id("DAYRAD");
		Navigator.waitForAjax(driver, by);
		WebDriverUtils.checkRadio(driver, by);
		by = By.id("COMPLETEDAYS");
		Navigator.waitForAjax(driver, by);
		WebDriverUtils.fillin_textbox(driver, by, this.getEnrollDeadline());
		
		Navigator.explicitWait(1000);
		//save change
		WebDriverUtils.switchToFrame(driver, "BSCAT_TOP");
		by = By.cssSelector("img[alt=\"Save\"]");
		WebDriverUtils.clickButton(driver, by);
		
		//leaner enroll in module
		User learner = new User(this.getParticipants(), Config.getInstance().getProperty("user.default.pass"));
		TestDriver.switchUser(learner);
		this.runSearchToEnroll(driver);
		
		//run daily schedule task
		/*for(TestObject setup: sysSetup){
			TSSystem setup_ins = (TSSystem) setup;
			User user = new User(Config.getInstance().getProperty("sys.ndadmin"), Config.getInstance().getProperty("sys.ndadmin.pass"));
			TestDriver.switchUser(user);
			setup_ins.runScheduledTask(driver);
			Navigator.explicitWait(1000);
		}*/
		User user = new User(Config.getInstance().getProperty("ndadmin"), Config.getInstance().getProperty("ndadmin.pass"));
		TestDriver.switchUser(user);
		TSSystemUI.runScheduledTask(driver, this.getScheduleTask());
		
		//learner check e-mail
		TestDriver.switchUser(learner);
		EmailUI.CheckInternalEmail(driver, this.getEmailTitle(), this.getEmailContent());
	}
	
	/**Empty method since it's a test suite
	 * 
	 * @param driver
	 */
	public void runCheckScorm12(WebDriver driver){
		
	}
	
	public void runAdHoc_Enroll(WebDriver driver){
		
	}
	/**Empty method since it's a test suite
	 * 
	 * @param driver
	 */
	public void runCheckScorm2004(WebDriver driver){
		
	}
	
	/**Empty method since it's a test suite
	 * 
	 * @param driver
	 */
	public void runCheckAICC(WebDriver driver){
		
	}
	
	/*
	 * select certain organization constraints in session property of catalog editor
	 */
	public void runSelectOrgConstraints (WebDriver driver) {
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
					"ManageCenter", "Modules"));
		
		int waitTime = 1000;
		
		By by = By.id("KEYW");
		String ModuleID_search = this.getModuleID();/* input userID  'ad hoc o1'*/
		WebDriverUtils.fillin_textbox(driver, by, ModuleID_search);
		Navigator.explicitWait(waitTime);
		by =By.xpath(".//input[@value='Filter']");/*search*/
		
		WebDriverUtils.clickButton(driver, by);
		
		by = By.xpath("/descendant::*[@href='javascript:void(0)'][last()]"); /* click result and wait */
		Navigator.explicitWait(waitTime);
		WebDriverUtils.clickButton(driver, by);
		
		WebDriverUtils.switchToPopUpWin(driver);/*PopUp window*/
		WebDriverUtils.switchToFrame(driver, "BSCAT_LEFT");
		by = By.linkText(Labels.Session_Properties.toString()); /* click session properties and wait */
		Navigator.explicitWait(waitTime);
		WebDriverUtils.clickButton(driver, by);
		/*click instructors*/
		WebDriverUtils.switchToFrame(driver, "BSCAT_LEFT");
		by = By.partialLinkText(Labels.tab_Edit_Schedule.toString()); /* click Edit session and wait */
		Navigator.explicitWait(waitTime);
		WebDriverUtils.clickButton(driver, by);

		/*click Send Instructor */
		WebDriverUtils.switchToFrame(driver, "BSCAT_MAIN");
		by = By.linkText(Labels.label_Edit_LVL1_Constraints.toString()); /* click Select organization constraint(s) */
		Navigator.explicitWait(waitTime);
		WebDriverUtils.clickButton(driver, by);
		
		WebDriverUtils.switchToPopUpWin(driver);/*PopUp window*/
		Navigator.explicitWait(waitTime);
		
		SelectorsUI.PopUp_Selector(driver, SelectorsUI.PopUpSelector.TreeSelector_Chkbox,this.getOrganization());
	
		//by = By.linkText("Expand and Display Entire Hierarchy Tree");/* click Expand and Display Entire Hierarchy Tree */
		//WebDriverUtils.clickButton(driver, by);
		
		//by = By.xpath(".//descendant::*[@id='checked_organization'][5]");/* click checked_organization */
		//WebDriverUtils.check_checkbox(driver, by);
		
		//by = By.name("save");/*click OK*/
		//WebDriverUtils.clickButton(driver, by);
		
	
		
		WebDriverUtils.switchToParentWin(driver);
		WebDriverUtils.switchToFrame(driver, "BSCAT_TOP");
		by = By.xpath(".//descendant::*[@alt='Save']");/*click save*/
		Navigator.explicitWait(waitTime);
		WebDriverUtils.clickButton(driver, by);
		

	}
	
	/**
	 * possible values for "revisionInput":
	 * approve
	 * preview
	 * not started
	 * not complete
	 * not complete:stand-alone
	 * not complete:stand-alone:mandatory
	 * all
	 * @param driver
	 * @param revisionInput
	 */
	public static void publishRevision_UI(WebDriver driver, String revisionInput) {
		By by = null;
		
		String[] revision = revisionInput.split(":");
		String expectedRevisionStatus = Labels.Label_Effective.toString();//for checking result
		switch(revision[0]){
			case "preview":
				by = By.id("PREVIEW");
				expectedRevisionStatus = Labels.Label_Preview.toString();
				break;
			case "approve":
				by = By.id("PREVIEW");
				expectedRevisionStatus = Labels.Label_Approved.toString();
				break;
			case "not started":
				by = By.id("NOT_ATTEMPTED");
				break;
			case "not complete":
				by = By.id("NOT_COMPLETED_RE_ENROLL_NONE");
				if(revision.length > 1)	
					for(int i=1; i<revision.length ; i++){
						switch(revision[i]){
							case "stand-alone":
								WebDriverUtils.clickLink(driver, by);
								by = By.id("NOT_COMPLETED_RE_ENROLL_COMPLETED_IGNORE_PROGRAMS");
								break;
							case "mandatory":
								WebDriverUtils.clickLink(driver, by);
								by = By.id("NOT_COMPLETED_RE_ENROLL_COMPLETED_INCLUDE_PROGRAMS");
								break;
							default:
								JUnitAssert.assertTrue(false, "incorrect input, please input \"not complete:stand-alone:mandatory\"");
							}
					}
				break;
			case "all":
				by = By.id("ALL");
				break;
			default:
				JUnitAssert.assertTrue(false, "incorrect input, please input \"preview\\approve\\not started\\not complete:stand-alone:mandatory\\all\"");
		}
		WebDriverUtils.clickLink(driver, by);
		
		by = By.name("ok"); //click button "Publish"
		Navigator.waitForAjax(driver, by);
		WebDriverUtils.clickButton(driver, by);
		
		
		by =  By.id("viewInRevisions");//in catalog editor, click button "View In Revisions"
		if (!WebDriverUtils.isElementPresent(by)){
			by = By.name("buttonInput"); //in import AICC, click button "Open Catalog Editor"
			Navigator.waitForAjax(driver, by);
			WebDriverUtils.clickButton(driver, by);	
			WebDriverUtils.switchToPopUpWin(driver); //switch to Catalog Editor
			Navigator.explicitWait(1000);
		}  else{
			Navigator.waitForAjax(driver, by);
			WebDriverUtils.clickButton(driver, by);	
		}
		WebDriverUtils.switchToFrame(driver, "BSCAT_LEFT");
		Navigator.explicitWait(1000);
		by = By.xpath("//a[contains(text(),'"+Labels.Link_Revisions+"')]");
		Navigator.explicitWait(1000);
		WebDriverUtils.clickLink(driver, by);

		WebDriverUtils.switchToFrame(driver, "BSCAT_MAIN");
		Navigator.explicitWait(1000);
		
		if (revision[0].equalsIgnoreCase("approve")){ //mark approved
			by = By.xpath("//tr[@class='row1']/td/div/button");
			WebDriverUtils.mouseOver(driver, by);//mouse over gear button of preview revision
			Navigator.explicitWait(1000);
			by = By.xpath("//tr[@class='row1']/td/div/ul/li/a[text()='"+Labels.Mark_Approved+"']");
			WebDriverUtils.clickLink(driver, by);//click link "Mark as Approved"
			
			by = By.xpath("//button[descendant::span[contains(text(),'"+Labels.Mark_Approved+"')]]");
			Navigator.explicitWait(1000); 
			WebDriverUtils.clickButton(driver, by); //click button "Mark as Approved" to confirm
			Navigator.explicitWait(2000);
		}
		
		/********************check result*******************/
		by = By.xpath("//tr[@class='row1'][1]/td[2]/span");
		String currentRevisionStatus = WebDriverUtils.getText(driver, by);
		JUnitAssert.assertTrue(currentRevisionStatus.equals(expectedRevisionStatus),"Incorrect revision status, Expected = "+expectedRevisionStatus+" Current Result = " + currentRevisionStatus);

		/*by = By.xpath("//tr[@class='row1'][1]/td/div/button");			    
		WebDriverUtils.mouseOver(driver, by);
		Navigator.explicitWait(1000);// mouse over gear button
			
		//check items in gear button is correct
		String value_tmp = WebDriverUtils.getText(driver, by).toLowerCase();
		String[] expectedGearBtnItems = {Labels.button_preview, Labels.button_Mark_effective, Labels.Mark_Approved};
		int y=0;
		int i= 0;
		if (currentRevisionStatus.equals(Labels.Label_Effective)){
			i = 1;
		} else if (currentRevisionStatus.equals(Labels.Label_Approved)){
			i = 2;
		} else if (currentRevisionStatus.equals(Labels.Label_Preview)){
			i = 3;
		}

		for (; i>0; i--){
			by = By.xpath("//tr[@class='row1'][1]/td[1]/div[1]/ul[1]/li["+i+"]");
			if(WebDriverUtils.isElementPresent(by)){
				value_tmp = WebDriverUtils.getText(driver, by).toLowerCase();
				JUnitAssert.assertTrue(value_tmp.equals(expectedGearBtnItems[y]),"Cannot find \""+expectedGearBtnItems[y]+"\" in Gear button");
				y = y+1;
			}
		}	*/
	}
	
	/**
	 * default publish revision by "Import Resource"
	 * @param driver
	 */
	public void runPublishRevisions(WebDriver driver){
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
				"ManageCenter", "Modules"));
		
		//open catalog editor
		this.searchModule(driver);
		WebDriverUtils.switchToPopUpWin(driver);
		
		WebDriverUtils.switchToFrame(driver, "BSCAT_LEFT");
		Navigator.explicitWait(1000);
		
		//click "Revisions
		By by = By.xpath("//a[contains(text(),'"+Labels.Link_Revisions+"')]");
		Navigator.explicitWait(1000);
		WebDriverUtils.clickLink(driver, by);

		WebDriverUtils.switchToFrame(driver, "BSCAT_MAIN");
		Navigator.explicitWait(1000);
		
		//click button "Import New Revision
		by = By.xpath("//span[text()='"+Labels.button_Import_Revision+"']");
		WebDriverUtils.mouseOver(driver, by);
		
		
		//Import Resource
		Navigator.explicitWait(500);
		by = By.linkText(Labels.link_import_resource.toString());
		WebDriverUtils.clickLink(driver, by);
		
		Navigator.explicitWait(500);
		this.importResource_UI(driver);
		
		Navigator.explicitWait(500);
		Online.publishRevision_UI(driver, this.getRevision());
	}
	
	/**
	 * in  catalog Editor > Revisions > Import Resources page
	 * click "Next"
	 * can add codes for selecting resources or exam later if needed
	 * @param driver
	 */
	public void importResource_UI(WebDriver driver){
		//may add codes later to select thing from repository or exam
		Navigator.explicitWait(1000);
		ArrayList<By> bys = new ArrayList<By>();
		
		//navigator to iframe
		By by = By.id("BSCAT_MAIN");
		bys.add(by);
		by = By.xpath("//iframe[@class='content-package-selector-iframe full-width']");
		bys.add(by);
		//By by = By.xpath("//iframe[@class='content-package-selector-iframe full-width']");
		
		WebDriverUtils.switchToNestedFrame(driver, bys);

		//click button "Next"
		by = By.xpath("//input[@name='continueButton']");
		WebDriverUtils.clickButton(driver, by);

	}
	
}
