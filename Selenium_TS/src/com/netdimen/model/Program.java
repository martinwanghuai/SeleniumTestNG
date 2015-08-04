package com.netdimen.model;

import java.util.ArrayList;
import java.util.Arrays;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.netdimen.abstractclasses.TestObject;
import com.netdimen.config.Config;
import com.netdimen.config.Labels;
import com.netdimen.controller.TestDriver;
import com.netdimen.junit.JUnitAssert;
import com.netdimen.utils.Validate;
import com.netdimen.utils.WebDriverUtils;
import com.netdimen.view.Navigator;
import com.netdimen.view.SelectorsUI;

/**
 * 
 * @author martin.wang
 *
 */
public class Program extends LearningModule {
	private String SessionIDs = "", SessionTitles = "", SessionStatus = "", SubModules = "";
	
	public void setSessionID_UI(WebDriver driver, String str){
		if(!str.equalsIgnoreCase("")){
			By by = By.id("SID");
			WebDriverUtils.fillin_textbox(driver, by, str);
		}
	}
	
	public void setSessionTitle_UI(WebDriver driver, String str){
		if(!str.equalsIgnoreCase("")){
			By by = By.id("SESSIONTITLE");
			WebDriverUtils.fillin_textbox(driver, by, str);
		}
	}

	public void setSessionStatus_UI(WebDriver driver, String str){
		if(!str.equalsIgnoreCase("")){
			By by = By.id("SSTATUS");
			WebDriverUtils.select_selector(driver, by, str);
		}
	}
	
	public void setSubModules_UI(WebDriver driver, String str){
		
		if (!Validate.isBlank(str)){		
			String[] modules = str.split(";");		
			ArrayList<String> Modules = new ArrayList<String>(Arrays.asList(modules));	
			SelectorsUI.PopUp_Selector(driver,
					SelectorsUI.PopUpSelector.TopDownSelector, Modules);

		}
	}
	
	public void runCreate(WebDriver driver){
		super.runCreate(driver);
		
		//setup session
		WebDriverUtils.switchToFrame(driver, "BSCAT_LEFT");
		By by = By.linkText(Labels.Link_Sess_Properties.toString());
		WebDriverUtils.clickLink(driver, by);
		
		this.CreateSession(driver);
		WebDriverUtils.switchToFrame(driver, "BSCAT_TOP");
		by = By.cssSelector("img[alt=\"Save\"]");
		WebDriverUtils.clickButton(driver, by);
	}

	public void CreateSession(WebDriver driver) {
		String [] sessionID = this.getSessionIDs().split(";");
		String [] sessionTitle = this.getSessionTitles().split(";");
		String [] sessionStatus = this.getSessionStatus().split(";");
		String [] subModules = this.getSubModules().split("\n");

		for(int x=0; x<=sessionID.length-1 ; x++) {
			WebDriverUtils.switchToFrame(driver, "BSCAT_MAIN");
			WebDriverUtils.switchToFrame(driver, "BSCAT_LEFT");
			By by = By.id("ADD_REORDER_SESSION_IMG");
			WebDriverUtils.clickLink(driver, by);
	
			WebDriverUtils.switchToFrame(driver, "BSCAT_MAIN");
		    this.setSessionID_UI(driver, sessionID[x]);

		    
		    by = By.cssSelector("input.topbutton");
		    WebDriverUtils.clickButton(driver, by);
	//	    Navigator.waitForAjaxElementLoad(driver, by);
		    Navigator.explicitWait(3000);
		    WebDriverUtils.switchToFrame(driver, "BSCAT_LEFT");

		    WebDriverUtils.select_selector_partialTexts(driver, "EVENTIDOPT", sessionID[x]);
		    by = By.name("SEARCH");
		    WebDriverUtils.clickButton(driver,by);
		    Navigator.explicitWait(3000);
		    
		    WebDriverUtils.switchToFrame(driver, "BSCAT_MAIN");
		    this.setSessionStatus_UI(driver, sessionStatus[x]);
		    this.setSessionTitle_UI(driver, sessionTitle[x]);
		    
		    WebDriverUtils.switchToFrame(driver, "BSCAT_TOP");
		    by = By.cssSelector("img[alt=\"Save\"]");
		    WebDriverUtils.clickButton(driver, by);
	
		    // assign sub modules
		    WebDriverUtils.switchToFrame(driver, "BSCAT_MAIN");
		    by = By.name("AC");
		    WebDriverUtils.clickLink(driver, by);
		    WebDriverUtils.switchToPopUpWin(driver);
		    this.setSubModules_UI(driver, subModules[x]);
		    Navigator.explicitWait(3000);
		    WebDriverUtils.switchToParentWin(driver);
		}
	}
	
	public void runEnrollAutoProgModule(WebDriver driver){
		String[] result = this.getExpectedResult().split("\n");
		String expActReqMod = "", expActEleMod = "", expAvaReqMod = "", expAvaEleMod = "";
		By by;
		
		//enroll to the assigned module
		this.searchToEnroll_UI(driver);
		WebDriverUtils.switchToParentWin(driver);
		Navigator.explicitWait(1000);
		by = By.className("home");
		WebDriverUtils.clickLink(driver,by);
		Navigator.explicitWait(1000);
		WebDriverUtils.acceptAlertIfPresent(driver);
		
		
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
				"LearningCenter", "MyCurrentCourses"), this);
		
		//open Knowledge Center to check enrollment Result
		this.openKC(driver);

		for(String modules_tmp: result){
			String[] modules = modules_tmp.split(":");
			if (modules[0].equals("ActReq")){ //check if the module is under "Active and Completed Modules" -> "Required Modules"
				expActReqMod = modules[1];
				by = By.xpath("//div[child::*[text()='"+Labels.ActiveModuleHeading+"']]/*[@class='modules-list' and preceding-sibling::*[text()='"+Labels.RequiredModuleHeading+"']][1]//*[@class='module-title']");
				String actReqMod = WebDriverUtils.getText(driver,by);
				JUnitAssert.assertTrue(actReqMod.equals(expActReqMod), "Incorrect Active Required Module" + actReqMod);
				
				
			} else if (modules[0].equals("ActEle")){//check if the module is under "Active and Completed Modules" -> "Elective Modules"
				expActEleMod = modules[1];
				by = By.xpath("//div[child::*[text()='"+Labels.ActiveModuleHeading+"']]/*[@class='modules-list' and preceding-sibling::*[text()='"+Labels.ElectiveModuleHeading+"']][1]//*[@class='module-title']");
				String actEleMod = WebDriverUtils.getText(driver,by);
				JUnitAssert.assertTrue(actEleMod.equals(expActEleMod), "Incorrect Active Elective Module" + actEleMod);
				
				
			} else if (modules[0].equals("AvaReq")){//check if the module is under "Available Modules" -> "Required Modules"
				expAvaReqMod = modules[1];
				by = By.xpath("//div[child::*[text()='"+Labels.AvailableModuleHeading+"']]/*[@class='modules-list' and preceding-sibling::*[text()='"+Labels.RequiredModuleHeading+"']][1]//*[@class='module-title']");
				String avaReqMod = WebDriverUtils.getText(driver,by);
				JUnitAssert.assertTrue(avaReqMod.equals(expAvaReqMod), "Incorrect Available Required Module" + avaReqMod);
				
				
			} else if (modules[0].equals("AvaEle")){//check if the module is under "Available Modules" -> "Elective Modules"
				expAvaEleMod = modules[1];
				by = By.xpath("//div[child::*[text()='"+Labels.AvailableModuleHeading+"']]/*[@class='modules-list' and preceding-sibling::*[text()='"+Labels.ElectiveModuleHeading+"']][1]//*[@class='module-title']");
				String avaEleMod = WebDriverUtils.getText(driver,by);
				JUnitAssert.assertTrue(avaEleMod.equals(expAvaEleMod), "Incorrect Available Elective Module" + avaEleMod);
				
				
			}
		}
	
	}
	
	/**
	 * This test case let learner enroll in program, let instructor input score for sub-modules
	 * and learner check if module/sub-module status and score is correct
	 * @param transcripts
	 * 				- includes module tile, expected status/score etc
	 * @param driver
	 * @author miyavi.tsui
	 */
	public void runModuleWeighting(WebDriver driver, ArrayList<TestObject> transcripts) {
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
				"LearningCenter", "MyTrainingHistory"), this);

		//check if module status is expected
		for(TestObject transcript: transcripts){
			if (transcript.getFuncType().equals("beforeScoreChange")) {
				LearningModule transcript_ins = (LearningModule) transcript;
				transcript_ins.runCheckTranscript(driver);
			}
		}
		
		//log in as Instructor
		User user = new User(this.getInstructor(),Config.getInstance().getProperty("user.default.pass"));
		TestDriver.switchUser(user);
		
		//input score for different modules of user
		this.inputScore(driver);
		
		//login as learner
		user = new User(this.getUID(),this.getPWD());
		TestDriver.switchUser(user);
		
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
				"LearningCenter", "MyTrainingHistory"), this);
		
		//check if module status and scores are expected
		for(TestObject transcript: transcripts){
			if (transcript.getFuncType().equals("afterScoreChange")) {
				LearningModule transcript_ins = (LearningModule) transcript;
				transcript_ins.runCheckTranscript(driver);
			}
		}
		
	}
	
	public String getSessionIDs() {
		return SessionIDs;
	}

	public void setSessionIDs(String sessionIDs) {
		SessionIDs = sessionIDs;
	}

	public String getSessionTitles() {
		return SessionTitles;
	}

	public void setSessionTitles(String sessionTitles) {
		SessionTitles = sessionTitles;
	}

	public String getSessionStatus() {
		return SessionStatus;
	}

	public void setSessionStatus(String sessionStatus) {
		SessionStatus = sessionStatus;
	}

	public String getSubModules() {
		return SubModules;
	}

	public void setSubModules(String subModules) {
		SubModules = subModules;
	}

	/**
	 * Empty method since it's a test suite
	 * 
	 * @param driver
	 */
	public void runCostAccounting(WebDriver driver) {

	}

}
