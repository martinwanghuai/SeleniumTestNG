package com.model;

import java.util.ArrayList;
import java.util.Arrays;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.abstractclasses.TestObject;
import com.config.Config;
import com.config.Labels;
import com.junit.JUnitAssert;
import com.utils.WebDriverUtils;
import com.view.Navigator;
import com.view.SelectorsUI;

/**
 * 
 * @author martin.wang
 *
 */
public class EnrollmentWizard extends TestObject {

	private String	ModuleID="", ModuleTitle="", Participants ="",	Status="";

	private By addParticipantBy = By.xpath("//div[text()='Participant(s)']//span[contains(@class,'icon-plus')]"),
			removesPartcipantBy = By.xpath("//div[text()='Participant(s)']//span[contains(@class,'icon-minus')]"),
			addModuleBy = By.xpath("//div[text()='Module(s)']//span[contains(@class,'icon-plus')]"),
			removeModuleBy = By.xpath("//div[text()='Module(s)']//span[contains(@class,'icon-minus')]"),
			executeBtnBy = By.name("execute"); //the button to Assign Completed Task, Enroll, Change Enrollment Status all use this button
	
	
	public String getModuleID() {
		return ModuleID;
	}

	public void setModuleID(String moduleID) {
		ModuleID = moduleID;
	}

	public String getModuleTitle() {
		return ModuleTitle;
	}

	public void setModuleTitle(String moduleTitle) {
		ModuleTitle = moduleTitle;
	}

	public String getParticipants() {
		return Participants;
	}

	public void setParticipants(String participants) {
		Participants = participants;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public void runChangeStatus(WebDriver driver) {
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
				"LearningCenter", "EnrollWizard"), this);

	   this.setEnrollmentWizardCriteria(driver, "Change Enrollment Status");

		By by = By.id("selChangeEnrollOptions");
		String str = "Change overall status";
		WebDriverUtils.select_selector(driver, by, str);

		Navigator.explicitWait(1000);
		by = By.id("SUPPRESSEMAIL");
		WebDriverUtils.check_checkbox(driver, by);

		by = By.id("selTargetStatus");
		str = this.getStatus();
		WebDriverUtils.select_selector(driver, by, str);

		by = executeBtnBy;
		WebDriverUtils.clickButton(driver, by);

		Navigator.explicitWait(3000);

		int expectedPassed = this.getParticipants().split(";").length; 
		this.checkPassedResult_EnrollmentWizard_UI(driver, expectedPassed+"");
	}

	/**Check out the result of passed enrollment
	 * 
	 * @param driver
	 * @param expectedPassed
	 */
	private void checkPassedResult_EnrollmentWizard_UI(WebDriver driver, String expectedPassed){
		By by = By.xpath("//tr[descendant::td[contains(text(),'"+Labels.Success_Msg+"')]]/td[2]");
		String failed = WebDriverUtils.getText(driver, by);
		JUnitAssert.assertEquals(expectedPassed, failed);
	}
	
	/**check out the result of failed enrollment
	 * 
	 * @param driver
	 */
	private void checkFailedResult_EnrollmentWizard_UI(WebDriver driver, String expectedFailed) {		
		By by = By.xpath("//tr[descendant::td[contains(text(),'"+Labels.Failed_Msg+"')]]/td[2]");
		String failed = WebDriverUtils.getText(driver, by);
		JUnitAssert.assertEquals(expectedFailed, failed);
	}

	public void runEnroll(WebDriver driver) {
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
				"LearningCenter", "EnrollWizard"), this);

		this.setEnrollmentWizardCriteria(driver, "Enroll");
		
		By by = By.id("SUPPRESSEMAIL");
		WebDriverUtils.check_checkbox(driver, by);
		by = executeBtnBy;
		WebDriverUtils.clickButton(driver, by);
		Navigator.explicitWait(1000);

		// check out the result
		this.checkFailedResult_EnrollmentWizard_UI(driver,"0");
	}

	/**
	 * select a participant from other domain and see if the participant can be found
	 * @param driver
	 */
	public void runSelectDomainUsers(WebDriver driver) {
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
				"LearningCenter", "EnrollWizard"), this);

		By by = addParticipantBy;
		WebDriverUtils.clickButton(driver, by);

		WebDriverUtils.switchToPopUpWin(driver);
		Navigator.explicitWait(1000);
		by = By.xpath("//span[contains(text(),'"+Labels.Entry_Form+"')]");
		WebDriverUtils.clickButton(driver, by);
		WebDriverUtils.switchToPopUpWin(driver);
		Navigator.explicitWait(1000);
		String SearchText = this.getParticipants().replaceAll(";", "\n");
		by = By.id("IDLIST");
		WebDriverUtils.fillin_textbox(driver, by, SearchText);

		by = By.name("VALIDATEBUTTON");
		WebDriverUtils.clickButton(driver, by);
		Navigator.explicitWait(1000);
		
		by = By.id("IDLIST");
		String selectedUser = WebDriverUtils.getText(driver, by);
		if (this.getExpectedResult().equals("notFound")) {
			JUnitAssert.assertTrue(selectedUser.contains("User Not Found"), "User from other domain is found");
		} else if (this.getExpectedResult().equals("Found")) {
			JUnitAssert.assertTrue(!selectedUser.contains("User Not Found"), "User from other domain is not found");
		}
	}

	/**
	 * Select Action, Modules, Participants 
	 * @param driver
	 * @param action
	 */
	private void setEnrollmentWizardCriteria(WebDriver driver, String action){
		//add participants
		By by = addParticipantBy;
		WebDriverUtils.clickButton(driver, by);

		WebDriverUtils.switchToPopUpWin(driver);
		Navigator.explicitWait(1000);
		by = By.xpath("//span[contains(text(),'UserID Direct Entry Form')]");
		WebDriverUtils.clickButton(driver, by);
		WebDriverUtils.switchToPopUpWin(driver);
		Navigator.explicitWait(1000);
		
//		by = By.id("IDLIST");
//		WebDriverUtils.fillin_textbox(driver, by, SearchText);
//
//		by = By.name("VALIDATEBUTTON");
//		WebDriverUtils.clickButton(driver, by);
//		Navigator.explicitWait(1000);
//		by = By.name("SAVEBUTTON");
//		WebDriverUtils.clickButton(driver, by);
		
		String SearchText = this.getParticipants().replaceAll(";", "\n");
		ArrayList<String> participants = new ArrayList<String>();
		participants.add(SearchText);
		SelectorsUI.PopUp_Selector(driver, SelectorsUI.PopUpSelector.UserIDValidator,participants);
		

		WebDriverUtils.switchToParentWin(driver);
		Navigator.explicitWait(1000);
		by = By.name("save");
		WebDriverUtils.clickButton(driver, by);

		// Select Action
		WebDriverUtils.switchToParentWin(driver);
		Navigator.explicitWait(1000);
		by = By.id("selAction");
		WebDriverUtils.select_selector(driver, by, action);
		
		Navigator.explicitWait(1000);
		// Select Modules
		by = addModuleBy;
		WebDriverUtils.clickLink(driver, by);
		WebDriverUtils.switchToPopUpWin(driver);
		Navigator.explicitWait(1000);
		
		String module = this.getModuleID();
		String[] modules = module.split(";");		
		ArrayList<String> Modules = new ArrayList<String>(Arrays.asList(modules));	
		SelectorsUI.PopUp_Selector(driver,
				SelectorsUI.PopUpSelector.TopDownSelector, Modules);
		

		WebDriverUtils.switchToParentWin(driver);
		Navigator.explicitWait(1000);
	}
	
	@Override
	public boolean equals(TestObject obj) {
		// TODO Auto-generated method stub
		return false;
	}
	

}
