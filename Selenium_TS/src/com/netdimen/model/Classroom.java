package com.netdimen.model;

import static com.netdimen.utils.WebDriverUtils.closeAlertAndGetItsText;

import java.util.ArrayList;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.netdimen.abstractclasses.TestObject;
import com.netdimen.config.Config;
import com.netdimen.config.Labels;
import com.netdimen.controller.TestDriver;
import com.netdimen.junit.JUnitAssert;
import com.netdimen.utils.WebDriverUtils;
import com.netdimen.view.EmailUI;
import com.netdimen.view.FunctionUI;
import com.netdimen.view.Navigator;
import com.netdimen.view.SelectorsUI;
import com.netdimen.view.TSSystemUI;

/**
 * 
 * @author martin.wang
 *
 */
public class Classroom extends LearningModule {
	private String SessionIDs = "", SessionTitles = "", SessionStatus = "", 
			EnrollBeginDates ="", EnrollEndDates = "", Credits = "",
			SessionBeginDates = "", SessionEndDates = "", isCustomVenue = "", Location="", Room = "",WaitlistHandling ="",
			AssessmentID = "", TransferSessionTitle ="", SubstitueParticipant="";
	
	public String getWaitlistHandling() {
		return WaitlistHandling;
	}

	public void setWaitlistHandling(String waitlistHandling) {
		WaitlistHandling = waitlistHandling;
	}
	
	public String getSessionIDs() {
		return SessionIDs;
	}

	public void setSessionIDs(String sessionIDs) {
		SessionIDs = sessionIDs;
	}
	
	public String getTransferSessionTitle() {
		return TransferSessionTitle;
	}

	public void setTransferSessionTitle(String transferSessionTitle) {
		TransferSessionTitle = transferSessionTitle;
	}
	
	public String getSubstitueParticipant() {
		return SubstitueParticipant;
	}

	public void setSubstitueParticipant(String substitueParticipant) {
		SubstitueParticipant = substitueParticipant;
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

	public String getEnrollBeginDates() {
		return EnrollBeginDates;
	}

	public void setEnrollBeginDates(String enrollBeginDates) {
		EnrollBeginDates = enrollBeginDates;
	}

	public String getEnrollEndDates() {
		return EnrollEndDates;
	}

	public void setEnrollEndDates(String enrollEndDates) {
		EnrollEndDates = enrollEndDates;
	}

	public String getCredits() {
		return Credits;
	}

	public void setCredits(String credits) {
		Credits = credits;
	}

	public String getSessionBeginDates() {
		return SessionBeginDates;
	}

	public void setSessionBeginDates(String sessionBeginDates) {
		SessionBeginDates = sessionBeginDates;
	}

	public String getSessionEndDates() {
		return SessionEndDates;
	}

	public void setSessionEndDates(String sessionEndDates) {
		SessionEndDates = sessionEndDates;
	}

	public String getisCustomVenue() {
		return isCustomVenue;
	}

	public void setisCustomVenue(String isCustomVenue) {
		this.isCustomVenue = isCustomVenue;
	}

	public String getLocation() {
		return Location;
	}

	public void setLocation(String location) {
		Location = location;
	}

	public String getRoom() {
		return Room;
	}

	public void setRoom(String room) {
		Room = room;
	}
	
	public String getAssessmentID() {
		return AssessmentID;
	}

	public void setAssessmentID(String assessmentID) {
		AssessmentID = assessmentID;
	}

	public Classroom(){
		super();
	}
	
	public void setSessionID_UI(WebDriver driver, String str){
		if(!str.equalsIgnoreCase("")){
			By by = By.id("SESSIONCODE");
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
			By by = By.id("EST");
			WebDriverUtils.select_selector(driver, by, str);
		}
	}
	/*
	public void setBeginDates_UI(WebDriver driver, String beginDate){
		if(!beginDate.equals("")){
	
			String xpath_calendar = UIFunctionUtils.XpathCalendarIcon(driver, "EBDdatebox");
			WebDriverUtils.dateSelect_Calandar(driver, beginDate, xpath_calendar);
			
		}
	} 
	
	public void setEndDates_UI(WebDriver driver, String endDate){
		if(!endDate.equals("")){

			String xpath_calendar = UIFunctionUtils.XpathCalendarIcon(driver, "DLdatebox");
			WebDriverUtils.dateSelect_Calandar(driver, endDate, xpath_calendar);
		}
	} 
*/
	

	
	
	public void setBeginHours_UI(WebDriver driver, String str){
		if(!str.equals("")){
			By by = By.id("SCHHOUR");
			WebDriverUtils.select_selector(driver, by, str);
		}
	}
	
	public void setisCustomVenue_UI(WebDriver driver, String str){
		if(str.equalsIgnoreCase("yes")){
			By by = By.id("useCustomVenue");
			WebDriverUtils.check_checkbox(driver, by);
		}
	}
	
	public void setLocation_UI(WebDriver driver, String str){
		if(!str.equalsIgnoreCase("")){
			By by = By.id("LOC");
			WebDriverUtils.fillin_textbox(driver, by, str);
		}
	}
	
	public void setRoom_UI(WebDriver driver, String str){
		if(!str.equalsIgnoreCase("")){
			By by = By.id("ROOM_NAME");
			WebDriverUtils.fillin_textbox(driver, by, str);
		}
	}
	
	public void runAttachAssessment(WebDriver driver){
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
				"ManageCenter", "Modules"), this);

		this.searchModule(driver);
		WebDriverUtils.switchToFrame(driver, "BSCAT_LEFT");
		Navigator.explicitWait(1000);
		
		//Click Session Properties button
		By by = By.xpath("//a[contains(text(),'"+Labels.Link_Sess_Properties+"')]");
		Navigator.explicitWait();
		WebDriverUtils.clickLink(driver, by);
		Navigator.explicitWait();
		this.goToSession(driver, this.getSessionStatus(), this.getSessionTitles());
		
		WebDriverUtils.switchToFrame(driver, "BSCAT_MAIN");
		Navigator.explicitWait(1000);
	
		by = By.name("EWID");
		WebDriverUtils.select_selector(driver, by, this.getAssessmentID());
		
		WebDriverUtils.switchToFrame(driver, "BSCAT_TOP");
		by = By.cssSelector("img[alt=\"Save\"]");
		WebDriverUtils.clickButton(driver, by);
	}
	
	
	public void runCreate(WebDriver driver){
		super.runCreate(driver);

		//edit session
		WebDriverUtils.switchToFrame(driver, "BSCAT_LEFT");
		By by = By.linkText("Session Properties");
//		Navigator.explicitWait(3000);
		WebDriverUtils.clickLink(driver, by);

		by = By.id("ADD_SESSION_IMG");
		WebDriverUtils.clickLink(driver, by);
		String result =WebDriverUtils.closeAlertAndGetItsText();
		JUnitAssert.assertTrue(
				result.equalsIgnoreCase(Labels.Msg_Confirm_Add_Schedule.toString()), 
				"Cannot find text:"+Labels.Msg_Confirm_Add_Schedule);
		Navigator.explicitWait(1000);
//		Navigator.waitForAjaxElementLoad(driver, by);
		
		WebDriverUtils.switchToFrame(driver, "BSCAT_MAIN");
		this.setSessionID_UI(driver, this.getSessionIDs());
		this.setSessionTitle_UI(driver, this.getSessionTitles());
		this.setSessionStatus_UI(driver, this.getSessionStatus());

		FunctionUI.setDates_UI(driver, this.getEnrollBeginDates(),"EBDdatebox");
		WebDriverUtils.switchToFrame(driver, "BSCAT_MAIN");
		FunctionUI.setDates_UI(driver, this.getEnrollEndDates(),"DLdatebox");
		
		
		WebDriverUtils.switchToFrame(driver, "BSCAT_TOP");
		by = By.cssSelector("img[alt=\"Save\"]");
		WebDriverUtils.clickButton(driver, by);
		Navigator.explicitWait(1000);
//		Navigator.waitForAjaxElementLoad(driver, by);
		
		this.goToSetup(driver, Labels.Tab_Edit_Session_Schedule.toString());

		WebDriverUtils.switchToFrame(driver, "BSCAT_MAIN");
		by = By.name("ADDNEW");
		WebDriverUtils.clickButton(driver, by);
		WebDriverUtils.switchToPopUpWin(driver);
		Navigator.explicitWait(1000);
		FunctionUI.setDates_UI(driver, this.getSessionBeginDates(),"SCHdatebox");	
		this.setBeginHours_UI(driver, "11");
		FunctionUI.setDates_UI(driver, this.getSessionEndDates(),"ENDdatebox");
	
		
		this.setisCustomVenue_UI(driver, this.getisCustomVenue());
		this.setLocation_UI(driver, this.getLocation());
		this.setRoom_UI(driver, this.getRoom());

		by = By.name("CHECK_ANS");
		WebDriverUtils.clickButton(driver, by);
		/*by = By.name("OK");
		WebDriverUtils.clickButton(driver, by);*/
		
		WebDriverUtils.switchToParentWin(driver);
		WebDriverUtils.switchToFrame(driver, "BSCAT_TOP");
		by = By.cssSelector("img[alt=\"Save\"]");
		WebDriverUtils.clickButton(driver, by);
	}

	/**
	 * In Catalog Editor for Classroom Module, go to the session defined by user
	 * @param driver
	 * @param sessionStatus
	 * @param sessionTitle
	 */
	public void goToSession(WebDriver driver, String sessionStatus, String sessionTitle){	
		//select session status
		By by = By.name("STATUSIDOPT");
		WebDriverUtils.select_selector(driver, by, sessionStatus);
		
		//select session
		String name_selector = "EVENTIDOPT";
		WebDriverUtils.select_selector_partialTexts(driver, name_selector, sessionTitle);
		by = By.name("SEARCH");
		WebDriverUtils.clickButton(driver, by);
		Navigator.explicitWait(1000);

	}
	
	/**
	 * This test case go to Catalog Editor > session > participants, and do the below actions
	 * 1. Update deadline
	 * 2. Substitute Participant
	 * 3. Transfer Session 
	 * @param driver
	 */
	public void runUpdateParticipant(WebDriver driver) {
		if(!this.getSysConf().equals("")){
			User login_user = new User(Config.getInstance().getProperty("ndadmin"), Config.getInstance().getProperty("ndadmin.pass"));
			TestDriver.switchUser(login_user);
			TSSystemUI.setupSystemConf(driver, this.getSysConf());
			
			login_user = new User(this.getUID(), this.getPWD());
			TestDriver.switchUser(login_user);
		}
		
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
				"ManageCenter", "Modules"), this);

		//go to Catalog Editor of the course
		this.searchModule(driver);

		WebDriverUtils.switchToPopUpWin(driver);
		Navigator.explicitWait(1000);
		
		WebDriverUtils.switchToFrame(driver, "BSCAT_LEFT");
		Navigator.explicitWait(1000);
		
		//Click Session Properties button
		By by = By.linkText(Labels.Link_Sess_Properties.toString());
		Navigator.explicitWait();
		WebDriverUtils.clickLink(driver, by);
		Navigator.explicitWait();
		
		//go to the correct session
		this.goToSession(driver, this.getSessionStatus(), this.getSessionTitles());
		
		//go to Participants
		this.goToSetup(driver, Labels.Link_Participants.toString());
		WebDriverUtils.switchToFrame(driver, "BSCAT_MAIN");
		Navigator.explicitWait(1000);
		
		this.updateParticipantDeadline(driver);
		this.sessionTransfer(driver);
		this.substituteParticipant(driver);
		
	}
	/**
	 * Substitute Participant and check if it success 
	 * @param driver
	 */
	public void substituteParticipant(WebDriver driver){
		WebDriverUtils.switchToFrame(driver, "BSCAT_MAIN");
		Navigator.explicitWait(1000);
		
		//click gear button
		By by = By.xpath("//tr[child::td[@class='learner-name' and descendant::span[text()='"+this.getParticipants().toUpperCase()+"']]]//button");
		Navigator.waitForAjax(driver, by);
		WebDriverUtils.mouseOver(driver, by);
				
		//click link to Transfer Session
		by = By.linkText(Labels.Link_Substitute_participant.toString());
		Navigator.waitForAjax(driver, by);
		WebDriverUtils.clickLink(driver, by);
				
		//click button to open user selector
		by = By.id("substituteUser-button-id");	
		Navigator.waitForAjax(driver, by);
		WebDriverUtils.clickButton(driver, by);
		
		WebDriverUtils.switchToPopUpWin(driver);
		Navigator.explicitWait(1000);
		
		//select participant to substitute
		ArrayList<String> participants = new ArrayList<String>();
		participants.add(this.getSubstitueParticipant());
		SelectorsUI.PopUp_Selector(driver,
				SelectorsUI.PopUpSelector.InnerUserSelector, participants);
		
		//press "Substitute button to confirm action
		WebDriverUtils.switchToFrame(driver, "BSCAT_MAIN");
		by = By.xpath("//button[descendant::*[text()='"+Labels.Btn_Substitute+"']]");
		Navigator.waitForAjax(driver, by);
		WebDriverUtils.clickButton(driver, by);
		Navigator.explicitWait(4000);
		
		//check old participant is removed
		by = By.xpath("//span[text()='"+this.getParticipants().toUpperCase()+"']");
		JUnitAssert.assertTrue(!WebDriverUtils.isElementPresent(by),"old participant is still found in current session");
		
		//check new participant present
		by = By.xpath("//span[text()='"+this.getSubstitueParticipant().toUpperCase()+"']");
		JUnitAssert.assertTrue(WebDriverUtils.isElementPresent(by),"Substitue Participant Failed, cannot find" + this.getParticipants()+ " in " + this.getTransferSessionTitle());

	}
	/**
	 * Transfer session of participant and check if transfer success
	 * @param driver
	 */
	public void sessionTransfer(WebDriver driver){	
		WebDriverUtils.switchToFrame(driver, "BSCAT_MAIN");
		Navigator.explicitWait(1000);
		
		//click gear button
		By by = By.xpath("//tr[child::td[@class='learner-name' and descendant::span[text()='"+this.getParticipants().toUpperCase()+"']]]//button");
		Navigator.waitForAjax(driver, by);
		WebDriverUtils.mouseOver(driver, by);
		
		
		//click link to Transfer Session
		by = By.linkText(Labels.Link_Transfer_Session.toString());
		Navigator.waitForAjax(driver, by);
		WebDriverUtils.clickLink(driver, by);
		
		//select the session to transfer
		by = By.id("session-transfer-sessions");
		WebDriverUtils.select_selector_partialTexts(driver, "session-transfer-sessions", this.getTransferSessionTitle());
		
		//click button to update
		by = By.id("session-transfer-button");
		WebDriverUtils.clickButton(driver, by);
		
		//check participant is not exist in current session
		Navigator.explicitWait(4000);
		by = By.xpath("//span[text()='"+this.getParticipants().toUpperCase()+"']");
		JUnitAssert.assertTrue(!WebDriverUtils.isElementPresent(by), this.getParticipants()+ " still found in current session");
		
		//go to new session
		WebDriverUtils.switchToFrame(driver, "BSCAT_LEFT");
		Navigator.explicitWait(1000);
		this.goToSession(driver, this.getSessionStatus(), this.getTransferSessionTitle());
		
		//go to Participants
		this.goToSetup(driver, Labels.Link_Participants.toString());
		WebDriverUtils.switchToFrame(driver, "BSCAT_MAIN");
		Navigator.explicitWait(1000);
		by = By.xpath("//span[text()='"+this.getParticipants().toUpperCase()+"']");
		
		
		JUnitAssert.assertTrue(WebDriverUtils.isElementPresent(by),"Transfer Session Failed, cannot find " + this.getParticipants()+ " in " + this.getTransferSessionTitle());

	}
	
	/**
	 * update participant deadline in Catalog Editor, and check if deadline is updated
	 * online support "Specific Day" for Deadline Method
	 * @param driver
	 */
	public void updateParticipantDeadline(WebDriver driver){	

		String deadlineMethod = "Specific Day", deadlineHour = "20", deadlineMin = "00", newDeadline = "";
		WebDriverUtils.switchToFrame(driver, "BSCAT_MAIN");
		//click gear button
		By by = By.xpath("//tr[child::td[@class='learner-name' and descendant::span[text()='"+this.getParticipants().toUpperCase()+"']]]//button");
		Navigator.waitForAjax(driver, by);
		WebDriverUtils.mouseOver(driver, by);
				
		//click link to Update Deadline
		by = By.linkText(Labels.Link_Update_Deadline.toString());
		Navigator.waitForAjax(driver, by);
		WebDriverUtils.clickLink(driver, by);
				
		//select Deadline Method, use specific date
		by = By.id("deadlineMethod");
		Navigator.waitForAjax(driver, by);
		WebDriverUtils.select_selector(driver, by, deadlineMethod);
				
		//select date, hour, min for deadline
		FunctionUI.setDates_UI(driver, this.getEnrollDeadline(),"completeDatedatebox");
		WebDriverUtils.switchToFrame(driver, "BSCAT_MAIN");
				
		by = By.id("completeDateHOUR");
		Navigator.waitForAjax(driver, by);
		WebDriverUtils.select_selector(driver, by, deadlineHour);
				
		by = By.id("completeDateMIN");
		Navigator.waitForAjax(driver, by);
		WebDriverUtils.select_selector(driver, by, deadlineMin);
				
		by = By.name("previewButton");
		Navigator.waitForAjax(driver, by);
		WebDriverUtils.clickButton(driver, by);
				
		by = By.id("update-deadline-button");
		Navigator.waitForAjax(driver, by);
		WebDriverUtils.clickButton(driver, by);

		//get the updated Deadline
		by = By.xpath("//tr[child::td[@class='learner-name' and descendant::span[text()='"+this.getParticipants().toUpperCase()+"']]]//td[@class='complete-deadline-cell']");
		Navigator.explicitWait(2000);
		newDeadline = WebDriverUtils.getText(driver, by);
				
		//format the date of expected deadline
		SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy HH:mm");
		String dateInString = this.getEnrollDeadline()+ " " + deadlineHour +":" + deadlineMin;
		Date date = null;
				
		try {
			date = formatter.parse(dateInString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		formatter = new SimpleDateFormat("MMM d, yyyy h:mm a z");
		String expectedDeadline = formatter.format(date);

		JUnitAssert.assertTrue(newDeadline.equals(expectedDeadline),"Incorrect deadline, Current result = "+newDeadline + ", Expected result =" + expectedDeadline);

	}
	
	
	public void runGroupEnroll(WebDriver driver){	
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
				"ManageCenter", "Modules"), this);

		this.searchModule(driver);

		WebDriverUtils.switchToPopUpWin(driver);
		Navigator.explicitWait(1000);
		WebDriverUtils.switchToFrame(driver, "BSCAT_LEFT");
		Navigator.explicitWait(1000);
		
		//Click Session Properties button
		By by = By.xpath("//a[contains(text(),'"+Labels.Link_Sess_Properties+"')]");
		Navigator.explicitWait();
		WebDriverUtils.clickLink(driver, by);
		Navigator.explicitWait();
		this.goToSession(driver, this.getSessionStatus(), this.getSessionTitles());
		
		this.goToSetup(driver, Labels.Link_Group_Enroll.toString());
		
		WebDriverUtils.switchToFrame(driver, "BSCAT_MAIN");
		Navigator.explicitWait(1000);

		by = By.xpath("//a[contains(text(),'"+Labels.Link_GEnroll_Select+"')]");
		driver.findElement(by).click();

		WebDriverUtils.switchToPopUpWin(driver);
		Navigator.explicitWait(1000);
		this.setParticipant_UI(driver, this.getParticipants());
		WebDriverUtils.switchToParentWin(driver);
		Navigator.explicitWait(1000);

		WebDriverUtils.switchToFrame(driver, "BSCAT_MAIN");
		by = By.name("tableButton");
		WebDriverUtils.clickButton(driver, by); //Execute Group Enroll

		String result =WebDriverUtils.closeAlertAndGetItsText();
		JUnitAssert.assertTrue(result
				.equalsIgnoreCase(Labels.Ques_Group_Sure.toString()), 
				"Cannot find text:"+Labels.Ques_Group_Sure);
		Navigator.explicitWait(1000);
		WebDriverUtils.switchToPopUpWin(driver);
		Navigator.explicitWait(1000);
		
		by = By.xpath("//input[@type='SUBMIT' and @name='buttonInput']");
//		Navigator.waitForAjaxElementLoad(driver, by);
		Navigator.explicitWait(1000);
		//check out the enrollment result
	    this.checkGroupEnrollResult_UI(driver, this.getParticipants());
		
		WebDriverUtils.clickButton(driver, by);
		WebDriverUtils.switchToParentWin(driver);
		Navigator.explicitWait(1000);
		WebDriverUtils.switchToFrame(driver, "BSCAT_TOP");
		Navigator.explicitWait(1000);

	}
	
	private void checkGroupEnrollResult_UI(WebDriver driver, String participants) {
		String[] UID_Array = this.getParticipants().split(";");
		By by = null;
		for(String UID: UID_Array){				 
			by = By.xpath("//tr[descendant::td[contains(text(),'" + UID.toUpperCase() + "')]]/td[2]");
			String result = WebDriverUtils.getText(driver, by);
			String expectedResult = Labels.Msg_GEnroll_Success.toString();
			JUnitAssert.assertEquals(expectedResult, result);
		}
	}

	public void runSendAutoEmails(WebDriver driver){
		
		// Search the Course & Enroll
		By by = By.id("KEYW");
		WebDriverUtils.fillin_textbox(driver, by, this.getModuleTitle());
		
		by = By.id("search-button");
		Navigator.waitForAjax(driver, by);
		WebDriverUtils.clickLink(driver, by);
		Navigator.explicitWait(1000);
		
		by = By.xpath("//em");
		Navigator.waitForAjax(driver, by);
		WebDriverUtils.clickLink(driver, by);
		WebDriverUtils.switchToPopUpWin(driver);

		by = By.xpath("//input[@value='Enroll']");
		Navigator.waitForAjax(driver, by);
		WebDriverUtils.clickLink(driver, by);
		
		WebDriverUtils.switchToPopUpWin(driver);
		by = By.xpath("//input[@value='Confirm enrollment']");
		Navigator.waitForAjax(driver, by);
		WebDriverUtils.clickLink(driver, by);
		
		WebDriverUtils.switchToPopUpWin(driver);
		by = By.xpath("//input[@value='Go to Course Sessions']");
		Navigator.waitForAjax(driver, by);
		WebDriverUtils.clickLink(driver, by);
		
		
		//Go to Learning Modules & Search the modules
		Navigator.navigate(driver,Navigator.xmlWebElmtMgr.getNavigationPathList("ManageCenter","Modules"),this);
		Navigator.explicitWait(1000);
		
		by = By.id("KEYW");
		WebDriverUtils.fillin_textbox(driver, by, this.getModuleTitle());
		by = By.xpath("//input[@value='Filter']");
		Navigator.waitForAjax(driver, by);
		WebDriverUtils.clickButton(driver, by);
		Navigator.explicitWait(1000);
		
		
		//Open Cata-editor, Go to EMAIL SETUP
		
		by = By.xpath("//tr[2]/td[3]/a");
		WebDriverUtils.clickLink(driver, by);
		WebDriverUtils.switchToPopUpWin(driver);
		WebDriverUtils.switchToFrame(driver, "BSCAT_LEFT");
		
		by = By.xpath("//a[contains(text(),'"+Labels.Link_Sess_Properties+"')]");
		Navigator.waitForAjax(driver, by);
		WebDriverUtils.clickLink(driver, by);
		Navigator.explicitWait(1000);
		
		by = By.xpath("//input[@value='Go']");
		Navigator.waitForAjax(driver, by);
		WebDriverUtils.clickLink(driver, by);
		Navigator.explicitWait(1000);
		
		WebDriverUtils.switchToFrame(driver, "BSCAT_LEFT");
		by = By.xpath("//a[contains(text(),'E-mail Preferences Setup')]");
		WebDriverUtils.clickLink(driver, by);
		Navigator.explicitWait(1000);
		
		//set all the configurations;
		WebDriverUtils.switchToFrame(driver, "BSCAT_MAIN");
		by = By.xpath("(//button[@id='SELECT_BUTTON'])[2]");
		Navigator.waitForAjax(driver, by);
		WebDriverUtils.clickButton(driver, by);
		
		// Select "Not-Yet-Accessed Reminder" is hardcoded;
		WebDriverUtils.switchToPopUpWin(driver);
		EMailTemplate.selectEmail(driver,"Not-Yet-Accessed Reminder");
		
		WebDriverUtils.switchToParentWin(driver);
	    WebDriverUtils.switchToFrame(driver, "BSCAT_TOP");
		by = By.xpath("//img[@alt='Save']");
		WebDriverUtils.clickLink(driver, by);
		Navigator.explicitWait(10000);
		
		
		// Go to 5.Participants
		WebDriverUtils.switchToFrame(driver, "BSCAT_LEFT");
		by = By.xpath("//a[contains(text(),'Participants')]");
		WebDriverUtils.clickLink(driver, by);
		Navigator.explicitWait(1000);
		
		//gear button -> send module not yet accessed reminder
		WebDriverUtils.switchToFrame(driver, "BSCAT_MAIN");
		by = By.xpath("//td[2]/div/button");
		WebDriverUtils.clickLink(driver, by);
		Navigator.explicitWait(1000);
		
		by = By.xpath("//a[contains(text(),'Send Module Not-Yet-Accessed Reminder')]");
		WebDriverUtils.clickLink(driver, by);
		Navigator.explicitWait(1000);
				
	    // Go to Home page
		
		WebDriverUtils.closeAllPopUpWins(driver);
		by = By.id("home-btn");
		Navigator.waitForAjax(driver, by);
		WebDriverUtils.clickLink(driver, by);
		Navigator.explicitWait(1000);
		
		// Check whether the email is received " My notifications"
		//Navigator.navigate(driver, Navigator.webElmtMgr.getNavigationPathList("LearningCenter","2.MyEmails"),this);
		
		EmailUI.CheckInternalEmail(driver, this.getEmailTitle(), this.getModuleTitle());
		
		Navigator.explicitWait(10000);
	}
	
	
	
	

	/**
	 * Change session status to "Cancelled", then check both learner and instructor can get the cancellation email
	 */
	public void runSendSessionCancelEmail(WebDriver driver) {
		Navigator.navigate(driver,Navigator.xmlWebElmtMgr.getNavigationPathList("ManageCenter","Modules"),this);
		
		//search module in Manage Center > Learning Modules, then open Catalog Editor
		this.searchModule(driver);
		WebDriverUtils.switchToPopUpWin(driver);
		WebDriverUtils.switchToFrame(driver, "BSCAT_LEFT");
		Navigator.explicitWait(1000);
		
		//go to Edit Session
		By by = By.linkText(Labels.Link_Sess_Properties.toString());
		WebDriverUtils.clickLink(driver, by);
		Navigator.explicitWait(1000);
		this.goToSession(driver, this.getSessionStatus(), this.getSessionTitles());
		
		WebDriverUtils.switchToFrame(driver, "BSCAT_MAIN");
		Navigator.explicitWait(1000);
		
		//Change the status to "Cancelled"
		by = By.id("EST");
		WebDriverUtils.select_selector(driver, by, Labels.Text_Cancelled.toString());
		//click ok in Alert box
		WebDriverUtils.acceptAlert(driver);
		WebDriverUtils.switchToFrame(driver, "BSCAT_TOP");
		//save change
		by = By.cssSelector("img[alt=\""+Labels.Btn_Save+"\"]");
		WebDriverUtils.clickButton(driver, by);
		Navigator.explicitWait(1000);
		WebDriverUtils.closeAllPopUpWins(driver);

		String emailTitleTemp = this.getEmailTitle();// in EKPTestData, use ";" to separate email title of participant and instructor
		String emailContentTemp = this.getEmailContent(); //same as title
		String[] emailTitle = emailTitleTemp.split(";");
		String[] emailContent = emailContentTemp.split(";");
		
		//check learner's email, then check instructor's email
		User user = new User(this.getParticipants(), Config.getInstance().getProperty("user.default.pass"));
		TestDriver.switchUser(user);
		for (int i=0; i < emailTitle.length; i++){
			EmailUI.CheckInternalEmail(driver, emailTitle[i], emailContent[i]);
			user = new User(this.getInstructor(), Config.getInstance().getProperty("user.default.pass"));
			TestDriver.switchUser(user);
		}
	}
	
	/**
	 * leaner enroll in classroom module, admin change module start/end date and venue
	 * check instructor and learner both receive session changed email
	 * @param driver
	 */
	public void runSendSessionChangeEmail(WebDriver driver) {
		Navigator.navigate(driver,Navigator.xmlWebElmtMgr.getNavigationPathList("ManageCenter","Modules"),this);
		
		//search module in Manage Center > Learning Modules, then open Catalog Editor
		this.searchModule(driver);
		WebDriverUtils.switchToPopUpWin(driver);
		WebDriverUtils.switchToFrame(driver, "BSCAT_LEFT");
		Navigator.explicitWait(1000);
		
		//go to Edit Session
		By by = By.linkText(Labels.Link_Sess_Properties.toString());
		WebDriverUtils.clickLink(driver, by);
		Navigator.explicitWait(1000);
		this.goToSession(driver, this.getSessionStatus(), this.getSessionTitles());
		
		//go to Edit session class schedule
		WebDriverUtils.switchToFrame(driver, "BSCAT_LEFT");
		this.goToSetup(driver, Labels.Link_Edit_Session_Schedule.toString());
		WebDriverUtils.switchToFrame(driver, "BSCAT_MAIN");
		Navigator.explicitWait(1000);
		//click to edit session start/end date or venue
		by = By.xpath("//img[contains(@onclick,'editsession')]");
		Navigator.waitForAjax(driver, by);
		WebDriverUtils.clickLink(driver, by);
		
		WebDriverUtils.switchToPopUpWin(driver);
		Navigator.explicitWait(1000);
		
		//change start date if inputed
		if(!this.getSessionBeginDates().trim().equals("")) {
			FunctionUI.setDates_UI(driver, this.getSessionBeginDates(),"SCHdatebox");	
		}
		//change end date if inputed
		if(!this.getSessionEndDates().trim().equals("")) {
			FunctionUI.setDates_UI(driver, this.getSessionEndDates(),"ENDdatebox");
		}
		
		//change venue if inputed (use custom venue by default)
		if (this.getisCustomVenue().equalsIgnoreCase("yes")) {
			this.setisCustomVenue_UI(driver, this.getisCustomVenue());
			this.setLocation_UI(driver, this.getLocation());
			this.setRoom_UI(driver, this.getRoom());
		}

		//click Book Selected Resources
		by = By.name("CHECK_ANS");
		WebDriverUtils.clickButton(driver, by);
		Navigator.explicitWait(1000);
		//save change
		WebDriverUtils.switchToParentWin(driver);
		WebDriverUtils.switchToFrame(driver, "BSCAT_TOP");
		by = By.cssSelector("img[alt=\"Save\"]");
		WebDriverUtils.clickButton(driver, by);
		
		String emailTitleTemp = this.getEmailTitle();// in EKPTestData, use ";" to separate email title of participant and instructor
		String emailContentTemp = this.getEmailContent(); //same as title
		String[] emailTitle = emailTitleTemp.split(";");
		String[] emailContent = emailContentTemp.split(";");
		
		//check learner's email, then check instructor's email
		User user = new User(this.getParticipants(), Config.getInstance().getProperty("user.default.pass"));
		TestDriver.switchUser(user);
		for (int i=0; i < emailTitle.length; i++){
			EmailUI.CheckInternalEmail(driver, emailTitle[i], emailContent[i]);
			user = new User(this.getInstructor(), Config.getInstance().getProperty("user.default.pass"));
			TestDriver.switchUser(user);
		}
		
	}
	
	
	/*
	 * send instructor notification in session property of catalog editor
	 */
	
	public void runSendInstrcNotification (WebDriver driver) {
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
					"ManageCenter", "Modules"));
		
		By by = By.id("KEYW");
		String ModuleID_search = this.getModuleID();/* input userID  'ad hoc o1'*/
		WebDriverUtils.fillin_textbox(driver, by, ModuleID_search);
		
		by =By.xpath(".//input[@value='Filter']");/*search*/
		WebDriverUtils.clickButton(driver, by);
		
		by = By.xpath("/descendant::*[@href='javascript:void(0)'][last()]"); /* click result and wait */
		WebDriverUtils.clickButton(driver, by);

		
		WebDriverUtils.switchToPopUpWin(driver);/*PopUp window*/
		WebDriverUtils.switchToFrame(driver, "BSCAT_LEFT");
		by = By.linkText("Session Properties"); /* click session properties and wait */
		WebDriverUtils.clickButton(driver, by);
		Navigator.explicitWait(1000);
		
		/*click instructors*/
		WebDriverUtils.switchToFrame(driver, "BSCAT_LEFT");
		by = By.xpath("//a[contains(text(),'Instructors')]");/* click instructor and wait */
		WebDriverUtils.clickButton(driver, by);

		/*click Send Instructor */
		WebDriverUtils.switchToFrame(driver, "BSCAT_MAIN");
		by = By.linkText("Send Instructor Notifications"); /* click send instructor notification */
		WebDriverUtils.clickButton(driver, by);
		
		WebDriverUtils.switchToPopUpWin(driver);/*PopUp window*/
		by = By.id("token-input-netd_token_input_CC");/* input CC */
		WebDriverUtils.fillin_textbox(driver, by, UID);

		by = By.xpath("html/body/div[3]/ul/li[1]");
		WebDriverUtils.clickButton(driver, by);
		by = By.id("SUBJECT");/*input subject*/
		WebDriverUtils.fillin_textbox(driver, by, UID);	
		by = By.id("TAREA1");/*input content*/
		WebDriverUtils.fillin_textbox(driver, by, UID);
		by = By.id("aBtn");/*click submit*/
		WebDriverUtils.clickButton(driver, by);

		EmailUI.CheckInternalEmail(driver, UID, UID);
	}

	
	
	/**
	 * Go to catalog Editor, and change the waitlist handling option as inputed in EKPTestData
	 * @param driver
	 */
	public void runChangeWaitlistHandling(WebDriver driver) {
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
				"ManageCenter", "Modules"));
		//open catalog editor of module
		this.searchModule(driver);
		WebDriverUtils.switchToPopUpWin(driver);
		WebDriverUtils.switchToFrame(driver, "BSCAT_LEFT");
		Navigator.explicitWait(1000);
		
		//go to Edit Session
		By by = By.xpath("//a[contains(text(),'"+Labels.Link_Sess_Properties+"')]");
		Navigator.explicitWait(1000);
		WebDriverUtils.clickLink(driver, by);
		Navigator.explicitWait(1000);
		this.goToSession(driver, this.getSessionStatus(), this.getSessionTitles());
		WebDriverUtils.switchToFrame(driver, "BSCAT_MAIN");
		
		//choose waitlist handling option
		by = By.id("WAITLIST");
		WebDriverUtils.select_selector(driver, by, this.getWaitlistHandling());
		WebDriverUtils.switchToFrame(driver, "BSCAT_TOP");
		by = By.xpath(".//descendant::*[@alt='Save']");/*click save*/
		WebDriverUtils.clickButton(driver, by);
	}
	
	/**
	 * Check if "Enroll"button exist in Catalog information page
	 * @param driver
	 */
	public void runCheckEnrollButton(WebDriver driver) {
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
				"LearningCenter", "HOME"), this);
		//input module name in universal search textbox
		By by = By.id("KEYW");
		String str = this.getModuleID();
		WebDriverUtils.fillin_textbox(driver, by, str);
		Navigator.explicitWait(1000);
		
		//click search
		by = By.name("SEARCH");
		WebDriverUtils.clickButton(driver, by);
		Navigator.explicitWait(3000);
		//click the module
		by = By.xpath("//a[descendant::em[text()='" + str + "']]");
		WebDriverUtils.clickLink(driver, by);
		//go to Catalog Information page
		WebDriverUtils.switchToPopUpWin(driver);
		Navigator.explicitWait(2000);
		
		by = By.name("enroll");
		JUnitAssert.assertTrue(!WebDriverUtils.isElementPresent(by), "Expected Result: No Enroll Button, Current Result: Enroll Button exsit");
	}
	
	/**
	 * after a session's status is changed to "Retired", learner should receive an email
	 * @param driver
	 * @param emails
	 */
	public void sendSesRetiredEmail(WebDriver driver, ArrayList<TestObject> emails) {
		Navigator.navigate(driver,Navigator.xmlWebElmtMgr.getNavigationPathList("ManageCenter","Modules"),this);
		
		//search module in Manage Center > Learning Modules, then open Catalog Editor
		this.searchModule(driver);
		WebDriverUtils.switchToPopUpWin(driver);
		WebDriverUtils.switchToFrame(driver, "BSCAT_LEFT");
		Navigator.explicitWait(1000);
		
		//go to Edit Session
		By by = By.linkText(Labels.Link_Sess_Properties.toString());
		Navigator.waitForAjax(driver, by);
		WebDriverUtils.clickLink(driver, by);
		Navigator.explicitWait(1000);
		this.goToSession(driver, this.getSessionStatus(), this.getSessionTitles());

		WebDriverUtils.switchToFrame(driver, "BSCAT_MAIN");
		//change status to "Retired"
		by = By.id("EST");
		WebDriverUtils.select_selector(driver, by, Labels.Text_Retired.toString());
		WebDriverUtils.acceptAlert(driver);
		
		WebDriverUtils.switchToFrame(driver, "BSCAT_TOP");
		by = By.xpath(".//descendant::*[@alt='Save']");//click save
		WebDriverUtils.clickButton(driver, by);
		User user = null;
		//check if learner received email, and DA&instructor should not receive email
		for(TestObject email: emails){
			LearningModule email_ins = (LearningModule) email;
			user = new User(email.getUID(), email.getPWD());
			TestDriver.switchUser(user);
			if(!email.getFuncType().equals("learner")){
				JUnitAssert.assertTrue(!EmailUI.checkIfEmailReceived(driver,email_ins.getEmailTitle()),email.getFuncType() + " should not receive Email from Retired session");
			} else {
				EmailUI.CheckInternalEmail(driver, email_ins.getEmailTitle(), email_ins.getEmailContent());
			}
		}
	}
	
}
