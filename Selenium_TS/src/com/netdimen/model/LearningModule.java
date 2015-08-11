package com.netdimen.model;

import static com.netdimen.utils.WebDriverUtils.closeAlertAndGetItsText;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.netdimen.abstractclasses.TestObject;
import com.netdimen.config.Config;
import com.netdimen.config.Labels;
import com.netdimen.controller.TestDriver;
import com.netdimen.junit.JUnitAssert;
import com.netdimen.utils.CriteriaParser;
import com.netdimen.utils.Checker;
import com.netdimen.utils.WebDriverUtils;
import com.netdimen.view.FunctionUI;
import com.netdimen.view.Navigator;
import com.netdimen.view.PermissionUI;
import com.netdimen.view.SelectorsUI;

/**
 * 
 * @author martin.wang
 *
 */
public class LearningModule extends TestObjectSignature {

	private String ModuleID = "", ModuleType = "", PolicyID = "",
			ModuleTitle = "", Desc = "", Catalog = "", Visibility = "",
			Participants = "", Exam = "", isMandatoryExam = "",
			Evaluation = "", isMandatoryEval = "", Certification = "",
			Status = "", Permission = "", Score = "", Credits = "",
			otherInfo = "",LaunchArea = "", ModuleLevel = "", ReadPerm="", 
			WritePerm="", Instructor="", EmailTitle = "", EmailContent = "", 
			EnrollDeadline ="", IsMultiLang="", Revision="";
	public String getRevision() {
		return Revision;
	}

	public void setRevision(String revision) {
		Revision = revision;
	}

	public boolean isMultiLang() {
		if (IsMultiLang.trim().equalsIgnoreCase("yes")){
			return true;
		}else{
			return false;
		}
	}

	public void setIsMultiLang(String isMultiLang) {
		this.IsMultiLang = isMultiLang;
	}

	private int counter = 0;

	
	
	public String getEnrollDeadline() {
		return EnrollDeadline;
	}

	public void setEnrollDeadline(String enrollDeadline) {
		EnrollDeadline = enrollDeadline;
	}
	
	public String getEmailTitle() {
		return EmailTitle;
	}

	public void setEmailTitle(String emailTitle) {
		EmailTitle = emailTitle;
	}

	public String getEmailContent() {
		return EmailContent;
	}

	public void setEmailContent(String emailContent) {
		EmailContent = emailContent;
	}

	public String getModuleLevel() {
		return ModuleLevel;
	}

	public void setModuleLevel(String moduleLevel) {
		ModuleLevel = moduleLevel;
	}
	
	public String getInstructor() {
		return Instructor;
	}

	public void setInstructor(String instructor) {
		Instructor = instructor;
	}

	public String getIsMandatoryExam() {
		return isMandatoryExam;
	}

	public String getIsMandatoryEval() {
		return isMandatoryEval;
	}

	public String getReadPerm() {
		return ReadPerm;
	}

	public void setReadPerm(String readPerm) {
		ReadPerm = readPerm;
	}

	public String getWritePerm() {
		return WritePerm;
	}

	public void setWritePerm(String writePerm) {
		WritePerm = writePerm;
	}

	public String getScore() {
		return Score;
	}

	public String getCredits() {
		return Credits;
	}

	public String getOtherInfo() {
		return otherInfo;
	}

	public String getLaunchArea() {
		return LaunchArea;
	}

	public void setLaunchArea(String LaunchArea) {
		this.LaunchArea = LaunchArea;
	}

	public void setIsMandatoryExam(String isMandatoryExam) {
		this.isMandatoryExam = isMandatoryExam;
	}

	public void setIsMandatoryEval(String isMandatoryEval) {
		this.isMandatoryEval = isMandatoryEval;
	}

	public void setScore(String score) {
		Score = score;
	}

	public void setCredits(String credits) {
		Credits = credits;
	}

	public void setOtherInfo(String otherInfo) {
		this.otherInfo = otherInfo;
	}

	public String getisMandatoryExam() {
		return isMandatoryExam;
	}

	public void setisMandatoryExam(String isMandatoryExam) {
		this.isMandatoryExam = isMandatoryExam;
	}

	public String getisMandatoryEval() {
		return isMandatoryEval;
	}

	public void setisMandatoryEval(String isMandatoryEval) {
		this.isMandatoryEval = isMandatoryEval;
	}

	public String getPermission() {
		return Permission;
	}

	public void setPermission(String permission) {
		Permission = permission;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public String getDesc() {
		return Desc;
	}

	public void setDesc(String desc) {
		Desc = desc;
	}
	
	public String getModuleID() {
		return ModuleID;
	}

	public void setModuleID(String moduleID) {
		ModuleID = moduleID;
	}

	public String getPolicyID() {
		return PolicyID;
	}

	public void setPolicyID(String policyID) {
		PolicyID = policyID;
	}

	public String getModuleTitle() {
		return ModuleTitle;
	}

	public void setModuleTitle(String moduleTitle) {
		ModuleTitle = moduleTitle;
	}

	public String getCatalog() {
		return Catalog;
	}

	public void setCatalog(String catalog) {
		Catalog = catalog;
	}

	public String getVisibility() {
		return Visibility;
	}

	public void setVisibility(String visibility) {
		Visibility = visibility;
	}

	public String getParticipants() {
		return Participants;
	}

	public void setParticipants(String participants) {
		Participants = participants;
	}

	public String getExam() {
		return Exam;
	}

	public void setExam(String exam) {
		Exam = exam;
	}

	public String getEvaluation() {
		return Evaluation;
	}

	public void setEvaluation(String evaluation) {
		Evaluation = evaluation;
	}

	public String getCertification() {
		return Certification;
	}

	public void setCertification(String certification) {
		Certification = certification;
	}

	public String getModuleType() {
		return ModuleType;
	}

	public void setModuleType(String moduleType) {
		ModuleType = moduleType;
	}

/*	public String toString() {
		return new StringBuilder().append(this.getClass().getName())
				.append("_").append(this.getFuncType()).append("_")
				.append(this.getModuleID()).toString();
	}
*/
	public void setExam_UI(WebDriver driver, String str) {
		if (!str.equalsIgnoreCase("")) {
			By by = By.linkText("Assign exam(s)");
			WebDriverUtils.clickButton(driver, by);

			WebDriverUtils.switchToPopUpWin(driver);
			
			String[] str_array = str.split(";");                
            ArrayList<String> exams = new ArrayList<String>(Arrays.asList(str_array));        
            SelectorsUI.PopUp_Selector(driver,
                            SelectorsUI.PopUpSelector.TopDownSelector, exams);
            
			
			         
			WebDriverUtils.switchToParentWin(driver);
		}
	}

	public void setisMandatoryExam_UI(WebDriver driver, String str) {
		if (str.equalsIgnoreCase("yes")) {
			By by = By.id("MANTESTS");
			WebDriverUtils.check_checkbox(driver, by);
		}
	}

	public void setCertification_UI(WebDriver driver, String str) {
		if (!str.equalsIgnoreCase("")) {
			By by = By.id("SELECT_BUTTON2");
			WebDriverUtils.clickButton(driver, by);
			WebDriverUtils.switchToPopUpWin(driver);
		
			SelectorsUI.PopUp_Selector(driver,  SelectorsUI.PopUpSelector.TopDownSelector, str);
	            
			WebDriverUtils.switchToParentWin(driver);
		}
	}

	public void setEvaluation_UI(WebDriver driver, String str) {
		if (!str.equalsIgnoreCase("")) {
			By by = By.id("SELECT_BUTTON3");
			WebDriverUtils.clickButton(driver, by);
			WebDriverUtils.switchToPopUpWin(driver);
			
			SelectorsUI.PopUp_Selector(driver,  SelectorsUI.PopUpSelector.TopDownSelector, str);
			WebDriverUtils.switchToParentWin(driver);
		}
	}

	public void setisMandatoryEvaluation_UI(WebDriver driver, String str) {
		if (str.equalsIgnoreCase("yes")) {
			By by = By.id("EVLREQ");
			WebDriverUtils.check_checkbox(driver, by);
		}
	}

	public void setParticipant_UI(WebDriver driver, String UIDs) {

		String[] str_array = UIDs.split(";");		
		ArrayList<String> UID_Array = new ArrayList<String>(Arrays.asList(str_array));	
		SelectorsUI.PopUp_Selector(driver,
				SelectorsUI.PopUpSelector.UserSelector, UID_Array);
			
	}

	public void setDesc_UI(WebDriver driver, String str) {
		if (!str.equalsIgnoreCase("")) {
			By by = By.id("DESC1");
			WebDriverUtils.fillin_textbox(driver, by, str);
		}
	}

	public void setPolicyID_UI(WebDriver driver, String policyID) {
		if (!policyID.equals("")) {
			By by = By.id("POLICY_SELECT_POLICYID");
			WebDriverUtils.clickButton(driver, by);

			WebDriverUtils.switchToPopUpWin(driver);
			WebDriverUtils.switchToFrame(driver, "POLICYTREE");
			Navigator.explicitWait(3000);
			by = By.xpath("//a[contains(text(),'" + policyID + "')]");
			WebDriverUtils.clickLink(driver, by);
			WebDriverUtils.switchToFrame(driver, "POLICYMENU");
			by = By.cssSelector("img[alt=\"Select\"]");
			WebDriverUtils.clickButton(driver, by);

			WebDriverUtils.switchToParentWin(driver);
		}
	}

	public void setCourseID_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
			By by = By.id("LID");
			WebDriverUtils.fillin_textbox(driver, by, str);
		}
	}

	public void setCourseTitle_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
			By by = By.id("aTextDesc");
			WebDriverUtils.fillin_textbox(driver, by, str);
		}
	}

	public void setVisibility_UI(WebDriver driver, String str) {
		By by = By.id("SHOWIN");
		if (str.equalsIgnoreCase("true")) {
			WebDriverUtils.check_checkbox(driver, by);
		} else if (str.equalsIgnoreCase("false")) {
			WebDriverUtils.uncheck_checkbox(driver, by);
		}
	}

	public void setCatalog_UI(WebDriver driver, String catalog, boolean isTopDownSelector) {
		if (!catalog.equals("")) {
			if (isTopDownSelector) {
				By by = By.linkText("Assign catalog");
				WebDriverUtils.clickLink(driver, by);
				WebDriverUtils.switchToPopUpWin(driver);
				
				String[] catalog_array = catalog.split(";");                
                ArrayList<String> catalogs = new ArrayList<String>(Arrays.asList(catalog_array));        
                SelectorsUI.PopUp_Selector(driver,
                                SelectorsUI.PopUpSelector.TopDownSelector, catalogs);
                
				WebDriverUtils.switchToParentWin(driver);

			} else {
				By by = By.xpath("//a/label[contains(text(),'"+Labels.Link_Assign_Catalog+"')]");
					WebDriverUtils.clickLink(driver, by);
					WebDriverUtils.switchToPopUpWin(driver);
					WebDriverUtils.checkSelect_CheckBox(driver, catalog);
					WebDriverUtils.switchToParentWin(driver);
			}
		}
	}

	public void setCourseType_UI(WebDriver driver, String str) {
		if (!str.equalsIgnoreCase("")) {
			By by = By.id("LT");
			WebDriverUtils.select_selector(driver, by, str);
		}
	}

	public void runCreate(WebDriver driver) {
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
				"ManageCenter", "ModuleCreate"), this);

		WebDriverUtils.switchToPopUpWin(driver);
		WebDriverUtils.switchToFrame(driver, "BSCAT_MAIN");

		// Navigator.explicitWait();

		this.setCourseID_UI(driver, this.getModuleID());
		this.setCourseType_UI(driver, this.getModuleType());
		By by = By.name("CREATE");
		WebDriverUtils.clickButton(driver, by);

		this.setCourseTitle_UI(driver, this.getModuleTitle());
		this.setDesc_UI(driver, this.getDesc());
		this.setVisibility_UI(driver, this.getVisibility());
		this.setCatalog_UI(driver, this.getCatalog(), false);
		
		this.goToSetup(driver, Labels.Link_Define_Enroll_Policy.toString());
		
		
		WebDriverUtils.switchToFrame(driver, "BSCAT_MAIN");
		this.setPolicyID_UI(driver, this.getPolicyID());

		this.goToSetup(driver, Labels.Link_Assign_Test.toString());

		WebDriverUtils.switchToFrame(driver, "BSCAT_MAIN");

		// Config Exam
		this.setExam_UI(driver, this.getExam());
		WebDriverUtils.switchToFrame(driver, "BSCAT_MAIN");
		this.setisMandatoryExam_UI(driver, this.getisMandatoryExam());

		// Config Certification

		this.setCertification_UI(driver, this.getCertification());

		// Config Evaluation
		WebDriverUtils.switchToFrame(driver, "BSCAT_MAIN");
		this.setEvaluation_UI(driver, this.getEvaluation());
		WebDriverUtils.switchToFrame(driver, "BSCAT_MAIN");
		this.setisMandatoryEvaluation_UI(driver, this.getisMandatoryEval());

		WebDriverUtils.switchToFrame(driver, "BSCAT_TOP");
		by = By.cssSelector("img[alt=\""+Labels.Btn_Save+"\"]");
		WebDriverUtils.clickButton(driver, by);
//		Navigator.waitForAjaxElementLoad(driver, by);
		Navigator.explicitWait(1000);

	}

	public void runUpdateKC(WebDriver driver) {
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
				"ManageCenter", "Modules"), this);

		this.searchModule(driver);
   
		WebDriverUtils.switchToPopUpWin(driver);

		this.goToSetup(driver, Labels.Link_Setup_Options.toString());
		Navigator.explicitWait(2000);

		WebDriverUtils.switchToFrame(driver, "BSCAT_MAIN");
		By by = By.id("isUseKc");
		WebDriverUtils.check_checkbox(driver, by);
		Navigator.explicitWait(2000);
		WebDriverUtils.switchToFrame(driver, "BSCAT_TOP");
		by = By.cssSelector("img[alt=\""+Labels.Btn_Save+"\"]");
		WebDriverUtils.clickButton(driver, by);
		Navigator.explicitWait(3000);
	}

	/**
	 * In Catalog Editor > Module Properties/Session Properties
	 * click on the Setup link (e.g. 1.1 Define Module Properties)
	 * @param driver
	 * @param setUpName   (should get from labels)
	 */
	public void goToSetup(WebDriver driver, String setUpName) {
		WebDriverUtils.switchToFrame(driver, "BSCAT_LEFT");
		By by = By.partialLinkText(setUpName);
		Navigator.waitForAjax(driver, by);
		WebDriverUtils.clickLink(driver, by);
	}
	
	public void searchModule(WebDriver driver) {
		By by = null;

		String funcType = this.getFuncType().toLowerCase();
		if (funcType.contains("create")) {
			by = By.id("createButton");
			WebDriverUtils.clickButton(driver, by);
		} else {
			// not creation
			by = By.id("KEYW");
			WebDriverUtils.fillin_textbox(driver, by, this.getModuleID());
			Navigator.explicitWait(1000);
			by = By.name("apply-filters");
			WebDriverUtils.clickButton(driver, by);
			Navigator.explicitWait(3000);
			
			String course = "";
			if (funcType.contains("delete")) {
				// for deleting
				course = this.getModuleTitle() + " (" + this.getModuleID()
						+ ")";
			} else {
				// for editing
				course = this.getModuleID();
			}

			by = By.linkText(course);
			int size = driver.findElements(by).size();
			if (size == 1) {
				WebDriverUtils.clickLink(driver, by);
			} else {
				course = this.getModuleTitle();
				by = By.linkText(course);
				size = driver.findElements(by).size();
				if (size == 1) {
					WebDriverUtils.clickLink(driver, by);
				}
			}
		}
	}

	public void runUpdate(WebDriver driver) {
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
				"ManageCenter", "Modules"), this);

		this.searchModule(driver);

		WebDriverUtils.switchToPopUpWin(driver);
		WebDriverUtils.switchToFrame(driver, "BSCAT_MAIN");
		this.setCourseTitle_UI(driver, this.getModuleTitle());
		this.setVisibility_UI(driver, this.getVisibility());
		this.setCatalog_UI(driver, this.getCatalog(), false);
		
		this.goToSetup(driver, Labels.Link_Define_Enroll_Policy.toString());
		
		WebDriverUtils.switchToFrame(driver, "BSCAT_MAIN");
		this.setPolicyID_UI(driver, this.getPolicyID());

		Navigator.explicitWait(3000);
		WebDriverUtils.switchToFrame(driver, "BSCAT_TOP");
		By by = By.cssSelector("img[alt=\""+Labels.Btn_Save+"\"]");
		WebDriverUtils.clickButton(driver, by);
		Navigator.explicitWait(3000);
	}

	public void runSetLaunchAsExam(WebDriver driver) {
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
				"ManageCenter", "Modules"), this);

		this.searchModule(driver);

		WebDriverUtils.switchToPopUpWin(driver);
		
		this.goToSetup(driver, Labels.Link_Launch_Properties.toString());
		//Navigator.explicitWait(2000);


		int size;
//		int size = driver.findElements(by).size();
//		if (size > 0) {
//			WebDriverUtils.clickLink(driver, by);
//		} else {
//			by = By.linkText("3.1 Define Launch Properties");
//			size = driver.findElements(by).size();
//			if (size > 0) {
//				WebDriverUtils.clickLink(driver, by);
//			}
//		}

		//Navigator.explicitWait(2000);

		WebDriverUtils.switchToFrame(driver, "BSCAT_MAIN");
		By by = By.id("runTimeEnvironment_t");
		String str = "";
		size = driver.findElements(by).size();
		if (size == 0) {
			by = By.id("LINT");
			str = "Resource";
			WebDriverUtils.select_selector(driver, by, str);
		}
		//Navigator.explicitWait(2000);

		by = By.id("runTimeEnvironment_t");
		WebDriverUtils.clickButton(driver, by);

		Navigator.explicitWait(2000);
		by = By.id("select_button");
		WebDriverUtils.clickButton(driver, by);
		WebDriverUtils.switchToPopUpWin(driver);
	
		SelectorsUI.PopUp_Selector(driver, SelectorsUI.PopUpSelector.TopDownSelector,  this.getExam());      		
		
		Navigator.explicitWait(2000);
		WebDriverUtils.switchToParentWin(driver);
		WebDriverUtils.switchToFrame(driver, "BSCAT_TOP");

		by = By.cssSelector("img[alt=\""+Labels.Btn_Save+"\"]");
		WebDriverUtils.clickButton(driver, by);
		Navigator.explicitWait(2000);
	}

	public void runDelete(WebDriver driver) {
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
				"ManageCenter", "Modules"), this);

		this.searchModule(driver);

		WebDriverUtils.switchToPopUpWin(driver);
		WebDriverUtils.switchToFrame(driver, "BSCAT_TOP");

		By by = By.cssSelector("img[alt=\""+Labels.Btn_Delete_Course+"\"]");
		WebDriverUtils.clickButton(driver, by);

		// WebDriverUtils.closeAlertAndGetItsText();
		JUnitAssert.assertTrue(closeAlertAndGetItsText().equalsIgnoreCase(Labels.Msg_Confirm_Delete.toString()), "Cannot find text:"+Labels.Msg_Confirm_Delete);

	}

	/**
	 * Self-enroll course via catalog searcher
	 * If there is more than one session (more than one Enroll button), the first one will be enrolled
	 * @param driver
	 */
	public void searchToEnroll_UI(WebDriver driver) {
		By by = By.id("KEYW");
		String str = this.getModuleID();
		WebDriverUtils.fillin_textbox(driver, by, str);
		Navigator.explicitWait(1000);
		by = By.name("SEARCH");
		WebDriverUtils.clickButton(driver, by);
		Navigator.explicitWait(3000);

		by = By.xpath("//a[descendant::em[text()='" + str + "']]");
		WebDriverUtils.clickLink(driver, by);
		
		WebDriverUtils.switchToPopUpWin(driver);
		Navigator.explicitWait(2000);
		
		by = By.xpath("(//input[@name='enroll'])[1]");
			
		WebDriverUtils.clickButton(driver, by);
		Navigator.explicitWait(2000);
		by = By.name("submitButtonJS");
		WebDriverUtils.clickButton(driver, by);
		Navigator.explicitWait(2000);
		
	}

	/**
	 * Self-enroll and finish course, check the course status in
	 * "My Training History"
	 * 
	 * @param driver
	 */
	public void runSearchToEnrollAndFinish(WebDriver driver) {
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
				"LearningCenter", "HOME"), this);

		// 1. Self-enroll courses
		this.searchToEnroll_UI(driver);
		Navigator.explicitWait(1000);
		// 2. Finish courses
		By by = By.linkText("Launch this course!");
		WebDriverUtils.clickLink(driver, by);

		WebDriverUtils.switchToPopUpWin(driver);
		Navigator.explicitWait(1000);
		WebDriverUtils.switchToFrame(driver, "course_button");
		Navigator.explicitWait(1000);
		by = By.xpath("//input[@value='"+Labels.Btn_Mark_Completed+"']");
		WebDriverUtils.clickLink(driver, by);
		Navigator.explicitWait(1000);
		// 3. Close all pop-up windows
		WebDriverUtils.closeAllPopUpWins(driver);

		// 4. Check results: disabled since it's too time-consuming
		/*Navigator.navigate(driver, Navigator.webElmtMgr.getNavigationPathList(
				"LearningCenter", "3.MyTrainingHistory"), this);
		
		
		by = By.id("RESULTS_PER_PAGE");
		WebDriverUtils.select_selector(driver, by, "All");
		Navigator.explicitWait(6000);
		
		by = By.xpath("//tr[descendant::td[3]/div/a[contains(text(),'"
				+ this.getModuleTitle()
				+ "')] and ./td[4][text()='Completed (Self-Asserted)']]");
		JUnitAssert.assertTrue(WebDriverUtils.isElementPresent(by), "Cannot find module transcript of " + this.getModuleTitle());*/
	}
	
	/**
	 * check the course status in "My Training History"
	 * 
	 * @param driver
	 */
	public void runCheckComplete(WebDriver driver){

		//Check the course is completed(self-asserted)
		
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
				"LearningCenter", "MyTrainingHistory"), this);

		By by = By.id("RESULTS_PER_PAGE");
		WebDriverUtils.select_selector(driver, by, "200");
		
		by = By.xpath("//tr[descendant::td//*[text()='"+ this.getModuleTitle()+ "']]//*[text()='"+Labels.Text_Completed+"']");
		
		JUnitAssert.assertTrue(WebDriverUtils.isElementPresent(by), "Cannot find module transcript:" + this.getModuleTitle());
		
	}

	/**
	 * Self-enroll course, check the course status in "My Training History"
	 * 
	 * @param driver
	 */
	public void runSearchToEnroll(WebDriver driver) {
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
				"LearningCenter", "Search"), this);

		// 1. Self-enroll courses
		this.searchToEnroll_UI(driver);
		Navigator.explicitWait(2000);
		WebDriverUtils.closeAllPopUpWins(driver);
		
		// 2. Check results
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
				"LearningCenter", "MyTrainingHistory"), this);

		By by = By.id("RESULTS_PER_PAGE");
		WebDriverUtils.select_selector(driver, by, "200");

		by = By.xpath("//tr[descendant::td//*[text()='"+ this.getModuleTitle()+ "']]//*[text()='"+Labels.Text_Not_Started+"']");

		JUnitAssert.assertTrue(WebDriverUtils.isElementPresent(by), "Cannot find module transcript:" + this.getModuleTitle());
	}

	public void runGroupEnroll(WebDriver driver) {
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
				"ManageCenter", "Modules"), this);

		this.searchModule(driver);

		WebDriverUtils.switchToPopUpWin(driver);
		WebDriverUtils.switchToFrame(driver, "BSCAT_LEFT");

		By by = By.xpath("//a[contains(text(),'"+Labels.Link_Sess_Properties+"')]");
//		Navigator.waitForAjaxElementLoad(driver, by);
		Navigator.explicitWait(1000);
		WebDriverUtils.clickLink(driver, by);

		this.goToSetup(driver, Labels.Link_Group_Enroll.toString());
//		Navigator.waitForAjaxElementLoad(driver, by);


		WebDriverUtils.switchToFrame(driver, "BSCAT_MAIN");

		by = By.xpath("//a[contains(text(),'"+Labels.Link_Select_Participants+"')]");
		driver.findElement(by).click();

		WebDriverUtils.switchToPopUpWin(driver);
		this.setParticipant_UI(driver, this.getParticipants());
		WebDriverUtils.switchToParentWin(driver);

		WebDriverUtils.switchToFrame(driver, "BSCAT_MAIN");
		by = By.name("tableButton");
		WebDriverUtils.clickButton(driver, by);
		JUnitAssert.assertTrue(closeAlertAndGetItsText().equalsIgnoreCase(Labels.Msg_Group_Assign.toString()), "Cannot find text:"+Labels.Msg_Group_Assign);

		WebDriverUtils.switchToPopUpWin(driver);
		by = By.name("submitButton");
		WebDriverUtils.clickButton(driver, by);
		WebDriverUtils.switchToParentWin(driver);

		WebDriverUtils.switchToFrame(driver, "BSCAT_TOP");

		Navigator.explicitWait(3000);
		by = By.cssSelector("img[alt=\""+Labels.Btn_Save+"\"]");
		Navigator.waitForAjax(driver, by);
//		Navigator.explicitWait();
		WebDriverUtils.clickButton(driver, by);
		this.checkExpectedResult_UI(driver, this.getExpectedResult());
	}

	private void checkEnrollment_UI(WebDriver driver, ArrayList<String> users) {
		for (String UID : users) {
			this.checkEnrollment_UI(driver, UID, true);
		}
	}
	// just check if user enroll into a course
	public void runCheckEnrollment(WebDriver driver) {
		this.checkEnrollment_UI(driver, this.getUID(),false);
	}
	/**
	 * @param driver
	 * @param UID
	 * @param waitForAlert: true if checking auto enrollment result
	 */
	private void checkEnrollment_UI(WebDriver driver, String UID, boolean waitForAlert) {
		// 1. login user
		User user = new User(UID, Config.getInstance().getProperty("user.default.pass"));
		TestDriver.switchUser(user);

		// 2. wait for a while and handle with pop-up alert(time is specified in
		// Manager Center -> Auto-Enroll Console)
		if (waitForAlert) WebDriverUtils.waitAlertAndAccept(driver);

		// 3. Goto My Current Learning
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
				"LearningCenter", "Learning"), this);
		Navigator.explicitWait(1000);
		JUnitAssert.assertTrue(WebDriverUtils.textPresentInPage(driver, this.getModuleID()), "Cannot find module transcript:" + this.getModuleID());
	}

	private void checkUnEnrollment_UI(WebDriver driver, String UID) {
		User user = new User(UID, Config.getInstance().getProperty("user.default.pass"));
		TestDriver.switchUser(user);

		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
				"LearningCenter", "Learning"), this);
		// Navigator.explicitWait(1000);
		JUnitAssert.assertTrue(!WebDriverUtils
				.textPresentInPage(driver, this.getModuleID()), "Can find module transcript:" + this.getModuleID());
	}

	private void checkUnEnrollment_UI(WebDriver driver, ArrayList<String> users) {
		for (String UID : users) {
			this.checkUnEnrollment_UI(driver, UID);
		}
	}

	/**
	 * Login user to checkout auto-enroll results
	 * 
	 * @param driver
	 * @param criteria_users
	 */
	private void checkExpectedResult_AutoEnroll_UI(WebDriver driver,
			HashMap<String, ArrayList<String>> criteria_users) {
		if (criteria_users != null) {
			Iterator<String> criteria_ite = criteria_users.keySet().iterator();
			while (criteria_ite.hasNext()) {
				String criteria_ins = criteria_ite.next();
				ArrayList<String> users_ins = criteria_users.get(criteria_ins);

				switch (criteria_ins) {
				case "enrollment":
					this.checkEnrollment_UI(driver, users_ins);
					break;
				case "non-enrollment":
					this.checkUnEnrollment_UI(driver, users_ins);
					break;
				}
			}
		}
	}

	public void runAutoEnroll(WebDriver driver) {
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
				"ManageCenter", "Modules"), this);

		this.searchModule(driver);

		WebDriverUtils.switchToPopUpWin(driver);
		Navigator.explicitWait(1000);
		

		WebDriverUtils.switchToFrame(driver, "BSCAT_LEFT");
		Navigator.explicitWait(1000);
		By by = By.xpath("//a[contains(text(),'"+Labels.Link_Sess_Properties+"')]");

		WebDriverUtils.clickLink(driver, by);

		Navigator.explicitWait(3000);
		this.goToSetup(driver, Labels.Link_Auto_Enroll.toString());


		WebDriverUtils.switchToFrame(driver, "BSCAT_MAIN");
		Navigator.explicitWait(1000);
		
		by = By.xpath("//div[descendant::span/h3[text()='"
				+ Labels.Link_Org_Attrs
				+ "'] and @class='action-nav status-header']");
		FunctionUI.setParticipants_UI(driver,
				this.getParticipants(), by);
		
		by = By.xpath("//button[@title='"+Labels.Btn_Set_AutoEnroll+"']");
		WebDriverUtils.clickButton(driver, by);
		

		JUnitAssert.assertTrue(WebDriverUtils.closeAlertAndGetItsText().equalsIgnoreCase(
				Labels.Msg_Auto_Sure.toString()), "Cannot find msg: "+Labels.Msg_Auto_Sure.toString());
		
		Navigator.explicitWait(1000);
		WebDriverUtils.acceptAlert(driver);
		
		Navigator.explicitWait(1000);
		// 1. Test AutoEnroll
		by = By.xpath("//button[@title='"+Labels.Btn_Test_AutoEnroll+"']");
		WebDriverUtils.clickButton(driver, by);
	
		WebDriverUtils.switchToFrame(driver, "BSCAT_MAIN");
		
		by = By.xpath("//input[@value='"+Labels.Btn_Test_AutoEnroll+"']");
		
		WebDriverUtils.clickButton(driver, by);

		// 2. Check enrollment/non-enrollment here
		HashMap<String, ArrayList<String>> criteria_users = CriteriaParser.parseKeyValueList(":", ";", this.getExpectedResult());

		Iterator<String> criteria_ite = criteria_users.keySet().iterator();
		while (criteria_ite.hasNext()) {
			String criteria_ins = criteria_ite.next();
			ArrayList<String> users_ins = criteria_users.get(criteria_ins);

			switch (criteria_ins) {
			case "enrollment":
				for (String user : users_ins) {
					// 1. check Test AutoEnroll here
					JUnitAssert.assertTrue(WebDriverUtils.textPresentInPage(driver, user), "User:" + user + " not auto-enroll course:" + this.getModuleID());
				}

				break;
			case "non-enrollment":
				for (String user : users_ins) {
					// 1. check Test AutoEnroll here
					JUnitAssert.assertTrue(!WebDriverUtils.textPresentInPage(driver, user), "User:" + user + " auto-enroll course:" + this.getModuleID());
				}

				break;
			}
		}

		WebDriverUtils.closeAllPopUpWins(driver);

		// 2. login user to check auto-enroll
		this.checkExpectedResult_AutoEnroll_UI(driver, criteria_users);
	}

	

	public void runUpdatePermission(WebDriver driver) {
		this.runUpdate(driver);

		this.goToSetup(driver, Labels.Link_Define_Module_Security.toString());

		WebDriverUtils.switchToFrame(driver, "BSCAT_MAIN");
		
		
		if (!Checker.isBlank(this.getReadPerm())){
			By by = By.name("permissionButton");
			Navigator.explicitWait(1000);
			WebDriverUtils.clickButton(driver, by);
			
			WebDriverUtils.switchToPopUpWin(driver);
			Navigator.explicitWait(1000);
			PermissionUI.setPermission_UI(driver, false, this.getReadPerm());
			WebDriverUtils.switchToParentWin(driver);
		}
		
		if (!Checker.isBlank(this.getWritePerm())){
			By by = By.name("permissionButton");
			Navigator.explicitWait(1000);
			WebDriverUtils.clickButton(driver, by);
			
			WebDriverUtils.switchToPopUpWin(driver);
			Navigator.explicitWait(1000);
			PermissionUI.setPermission_UI(driver, true, this.getWritePerm());
			WebDriverUtils.switchToParentWin(driver);
		}


		WebDriverUtils.switchToParentWin(driver);
	}

	
	public void runAttachAssessmentExam(WebDriver driver, ArrayList<TestObject> objs){
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
				"ManageCenter", "Modules"), this);

		this.searchModule(driver);
		Navigator.explicitWait();
		WebDriverUtils.switchToPopUpWin(driver);
		WebDriverUtils.switchToFrame(driver, "BSCAT_LEFT");
		Navigator.explicitWait(1000);
		
		By by = By.xpath("//a[contains(text(),'"+Labels.Link_Sess_Properties+")]");
//		Navigator.waitForAjaxElementLoad(driver, by);
		Navigator.explicitWait(1000);
		WebDriverUtils.clickLink(driver, by);
		Navigator.explicitWait(1000);
		WebDriverUtils.switchToFrame(driver, "BSCAT_MAIN");
		
		//attach assessment
		AssessmentWorkflow assessment = (AssessmentWorkflow)objs.get(0);		
		by = By.name("EWID");
		WebDriverUtils.select_selector(driver, by, assessment.getName());
		
		WebDriverUtils.switchToFrame(driver, "BSCAT_TOP");
		Navigator.explicitWait(1000);
		by = By.cssSelector("img[alt=\""+Labels.Btn_Save+"\"]");
		WebDriverUtils.clickButton(driver, by);
	}
	/**
	 * go to My Learning > My Current Courses, open KC
	 * ModuleLevel stores the parent program name of a module (not include module itself)
	 * @param driver
	 */
	public void openKC(WebDriver driver){
		By by = null;
		//if module is a subModule
		if (!(this.getModuleLevel().trim().equals(""))) {
			String[] Progs = this.getModuleLevel().split("/");
			this.CLM_ExpandModules(driver, Progs);	
		}	
		
		if(!this.getModuleTitle().trim().equals("")){
			String module = this.getModuleTitle();
			by = By.xpath("//*[@class='module-title' and text()='" + module + "']/../..//a[text()='"+Labels.Link_KC+"']");
			WebDriverUtils.clickLink(driver, by);
		} 
		
		Navigator.explicitWait();
	}
	
	public void runPassAssessmentExam(WebDriver driver){
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
				"LearningCenter", "MyCurrentCourses"), this);
		Navigator.explicitWait(3000);
		this.openKC(driver);
		
		//1. Click Assessment workflow
		By by = By.linkText(""+Labels.Link_Assessment_Workflow+"");
		WebDriverUtils.clickLink(driver, by);
		Navigator.explicitWait(1000);
		//2. Click Exam
		Exam ins = new Exam();
		ins.passExam(driver, this.getExam());
		
		//3. Check status
		by = By.xpath("//td[contains(text(),'" + this.getExam() + "')]/span");
		String status = WebDriverUtils.getText(driver, by);
		JUnitAssert.assertTrue(status.equalsIgnoreCase(this.getStatus()), 
				"Expected status:"+ this.getStatus() + ";actual status:"+ status);
	}
	
	
	/**
	 * Go to Instructor > My Active Courses
	 * in EKPTestData, input in "Score" column
	 * format- moduleTitle:user id:score
	 * separate by ":"
	 * @param driver
	 */
	public void inputScore(WebDriver driver) {
		String[] scoresTmp = this.getScore().split("\n");
		String[] scores = null;
		By by = null;
		for (String tmp : scoresTmp) {
			Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
					"LearningCenter", "MyActiveCourses"), this);
			scores = tmp.split(":");
			
			//input module title for search
			by = By.id("SEARCHBOX");
			Navigator.waitForAjax(driver, by);
			WebDriverUtils.fillin_textbox(driver, by, scores[0]);
			
			//click Search button
			by = By.name("GO");
			Navigator.waitForAjax(driver, by);
			WebDriverUtils.clickButton(driver, by);
			
			//click on the link of the Module
			by = By.linkText(scores[0]);
			Navigator.waitForAjax(driver, by);
			WebDriverUtils.clickLink(driver, by);
			
			//input Assessment Score
			by = By.xpath("//a[contains(text(),'"+scores[1].toUpperCase()+"')]/../..//input[contains(@id,'ASSESSSCORE')]");
			Navigator.waitForAjax(driver, by);
			WebDriverUtils.fillin_textbox(driver, by, scores[2]);
			Navigator.explicitWait(1000);
			//Click Submit Button
			by = By.xpath("//input[@value='"+Labels.Btn_Submit+"']");
			Navigator.waitForAjax(driver, by);
			WebDriverUtils.clickButton(driver, by);
			
			//check if update successful
			by = By.xpath("//p[text()='"+Labels.Msg_Update_Scuess+"']");
			Navigator.waitForAjax(driver, by);
			JUnitAssert.assertTrue(WebDriverUtils.isElementPresent(by),"Failed to Update Score in Teach");
		}
			
	}
	
	/**
	 * Launch and complete courses in KC (done) or CLM(not yet done)
	 * 
	 * @param driver
	 */
	public void runComplete(WebDriver driver) {

		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
				"LearningCenter", "MyCurrentCourses"), this);

		//String[] Progs = null;
		By by = null;
		if (this.getLaunchArea().equals("KC")) {
			// find out the module and click KC button
			this.openKC(driver);

		} else if (this.getLaunchArea().equals("CLM")) {
			// Not yet implemented
			if (this.getModuleLevel().trim() == "") {
				by = By.xpath("//li[descendant::div/h4[text()='"
						+ this.getModuleTitle()
						+ "']]/div[@class='module-actions']/div[@class='module-launch']/form/input");
			}
			WebDriverUtils.clickButton(driver, by);
		} else {
			JUnitAssert.assertTrue(false, "please input values(KC/CLM) in \"LaunchArea\" in EKPTestData.xls, only KC is supported currently");
		}
		switch (this.getModuleType()) {
		//program and classroom run the same step
		case "Program":
		case "Classroom":
			//click mark as complete button
			by = By.xpath("//input[@value='"+Labels.Btn_Mark_Completed+"']");
			WebDriverUtils.clickButton(driver, by);
			//click OK to confirm the pop up
			by = By.xpath("//button/span[text()='"+Labels.Btn_OK+"']");
			WebDriverUtils.clickButton(driver, by);
			Navigator.explicitWait(1000);
			
			
			break;
		case "Online":
			// In KC, click Launch Button
			by = By.xpath("//div[@class='module-main-actions']/button");
			WebDriverUtils.clickButton(driver, by);
			//Navigator.waitForAjaxElementLoad(driver, by);
			Navigator.explicitWait(1000);
			
			// Mark as Complete
			WebDriverUtils.switchToPopUpWin(driver);
			
			if (this.isMultiLang()){
				by = By.name("CONTINUE");
				WebDriverUtils.clickButton(driver, by);
			}
			
			WebDriverUtils.switchToFrame(driver, "course_button");
			Navigator.explicitWait(1000);

			by = By.name("_SUBMIT");
			WebDriverUtils.clickButton(driver, by);
			Navigator.explicitWait(1000);//10.2 is very slow to mark course as completed
			
			//close pop up windows
			WebDriverUtils.closeAllPopUpWins(driver);
			Navigator.explicitWait(1000);
			
			// check if complete successfully by checking message on page
			WebDriverUtils.switchToBaseWin(driver);
			String msg = Labels.Msg_Module_NoAvailable.toString();
			by = By.xpath("//div[@class='message-view-messagekey' and contains(text(),'" + msg + "')]");
			JUnitAssert.assertTrue(WebDriverUtils.isElementPresent(by), "");
			by = By.xpath("//input[@value='"+Labels.Btn_Go_To_CLM+"']");
			WebDriverUtils.clickButton(driver, by);
			break;
		default:
			JUnitAssert.assertTrue(false, "Incorrect/empty ModuleType");
			break;
		}

	}

	
	public void runFinish(WebDriver driver) {
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
				"LearningCenter", "MyCurrentCourses"), this);

		// 1. Launch course
		By by = By
				.xpath("//li[descendant::div/h4[text()='"
						+ this.getModuleTitle()
						+ "']]/div[@class='module-actions']/div[@class='module-launch']/form/input");
		WebDriverUtils.clickButton(driver, by);
		WebDriverUtils.switchToPopUpWin(driver);
		Navigator.explicitWait(1000);
		WebDriverUtils.closeAllPopUpWins(driver);
		Navigator.explicitWait(1000);
		// 2. Finish course
		by = By.xpath("//li[descendant::div/h4[text()='"
				+ this.getModuleTitle()
				+ "']]/div[@class='module-status-change-buttons float-right']/form[@name='COURSE_FINISHED']/input[@value='Finished']");
//		Navigator.waitForAjaxElementLoad(driver, by);
		Navigator.explicitWait(1000);
		WebDriverUtils.clickButton(driver, by);

		// 3. confirm Finish
		by = By.xpath("//div[@aria-describedby='jMessage']/div/div/button[descendant::span[text()='"+Labels.Btn_OK+"']]");
		WebDriverUtils.clickButton(driver, by);

		// 4. check confirm msg
		by = By.xpath("//ul[@class='success messagebar']/li");
		String text = WebDriverUtils.getText(driver, by);
		Navigator.explicitWait(1000);
		JUnitAssert.assertEquals(Labels.Msg_Session_Finished.toString(), text);
	}
	
	/**
	 * this method aims to withdraw all the courses except the course-withdraw that
	 * need reasons. If needed, can be added later.
	 * 
	 * @author tony.tan
	 * @param driver
	 */
	public void runAllCourseWithdraw(WebDriver driver) {
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
				"LearningCenter", "MyCurrentCourses"), this);

		// 1. Find withdraw button, if no withdraw button, then print.
		try{
			By by = By.xpath(".//*[@value='Withdraw Enrollment']");// assign
		
		
			while(WebDriverUtils.isElementPresent(by))
			{
				++counter;
				System.out.println(counter);// for test
				if(counter>10){ 
					break;//avoid useless loop
				};
			
				WebDriverUtils.clickButton(driver, by);//click withdraw
				Navigator.explicitWait(1000);
			
				WebDriverUtils.switchToPopUpWin(driver);
				by = By.xpath(".//*[@id='divWithdrawMsg']/div[3]/div/button[1]");//click confirm
				WebDriverUtils.clickButton(driver, by);
				try{
					by = By.xpath(".//*[@value='Withdraw Enrollment']");// re-assign
				}
				catch(Exception ex){
					break;
				}
			}
		}
		catch(Exception ex){
			System.out.println("you have no course to withdraw");
		}
	}
		
	/**
	 * go to Enrollment History Page and check if learner is not enrolled the module
	 * @param driver
	 */
	public void runCheckNotEnrolled(WebDriver driver) {
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
				"LearningCenter", "MyTrainingHistory"), this);

		By by = By.id("RESULTS_PER_PAGE");
		Navigator.explicitWait(1000);
		WebDriverUtils.select_selector(driver, by, "200");
		Navigator.explicitWait(1000);
		by = By.xpath("//tr[descendant::td/div/a[descendant-or-self::*[text()='"+this.getModuleTitle()+"']]]/td[text()='"+Labels.Text_Not_Started+"']");
		JUnitAssert.assertTrue(!WebDriverUtils.isElementPresent(by), this.getUID() + " should not enrolled in " + this.getModuleTitle());
	}
	
	/**
	 * Check transcript status in Learning Center -> My Training History
	 * 
	 * @param driver
	 */
	public void runCheckTranscript(WebDriver driver) {
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
				"LearningCenter", "MyTrainingHistory"), this);

		By by = By.id("RESULTS_PER_PAGE");
		Navigator.explicitWait(1000);
		WebDriverUtils.select_selector(driver, by, "200");
		Navigator.explicitWait(1000);

		String[] Progs = null;

		if (!(this.getModuleLevel().trim().equals(""))) {
			Progs = this.getModuleLevel().split("/");
			this.RT_ExpandModules(driver, Progs, "");
		}
		
		String module_row ="//tr[descendant::td/div/a[descendant-or-self::*[text()='"+this.getModuleTitle()+"']]";
		Navigator.explicitWait();
		
		if (!this.getStatus().equals("")) {
			module_row = module_row + " and descendant::td[4][text()='"+this.getStatus()+"']";
		}

		// 2.Check score
		if (!this.getScore().equals("")) {
			module_row = module_row + " and descendant::td[6][text()='"+this.getScore()+"']";
		}

		// 2.Check credits
		if (!this.getCredits().equals("")) {
			module_row = module_row + " and descendant::td[7][text()='"+this.getCredits()+"']";
		}
		
		if (!this.getRevision().equals("")) {
			module_row = module_row + " and descendant::td[8]//*[contains(text(),'"+this.getRevision()+"')]";
		}
		
		module_row = module_row + "]";
		by = By.xpath(module_row);
		JUnitAssert.assertTrue(WebDriverUtils.isElementPresent(by),"cannot find the transcript with inputed status/score/credits/other information");
		
		

	}
	/**
	 * In My Learning > My current Courses, check if module is submodule, if yes, expand the program by "Show sub modules"
	 * 
	 * @param driver
	 * @param ArrayProgs e.g. Prog_Composite/Prog_Child
	 * 
	 */
	private void CLM_ExpandModules(WebDriver driver, String ArrayProgs[]) {
		By by = null;
		
		for (String prog : ArrayProgs) {
			by = By.xpath("//*[@class='module-title' and text()='"+prog+"']/../../div/a[@class='module-drilldown-btn button']");
			Navigator.explicitWait();
			WebDriverUtils.clickLink(driver, by);
		}
	}
	/**
	 * My Learning > My Training History
	 * @param driver
	 * @param ArrayProgs  e.g. Prog_Composite/Prog_Child
	 * 
	 */
	private void RT_ExpandModules(WebDriver driver, String ArrayProgs[],
			String action) {
		By by = null;
		
		for (String prog : ArrayProgs) {
			by = By.xpath("//span[@class='program-title' and text()='"+prog+"']/ancestor::td/a[@class='module-drilldown-btn button']");
			Navigator.explicitWait();
			WebDriverUtils.clickLink(driver, by);
		}
		
	}
	
	/**
	 * Self-enroll course which passed enroll limits via catalog searcher
	 * If there is more than one session (more than one Enroll button), the first one will be enrolled
	 * need to add "Waitlisted" in Status for checking (EKPTestData)
	 * 
	 * @param driver
	 */
	public void runEnrollWaitlist(WebDriver driver) {
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
				"LearningCenter", "HOME"), this);
		By by = By.id("KEYW");
		String str = this.getModuleID();
		WebDriverUtils.fillin_textbox(driver, by, str);
		Navigator.explicitWait(1000);
		by = By.name("SEARCH");
		WebDriverUtils.clickButton(driver, by);
		Navigator.explicitWait(3000);

		by = By.xpath("//a[descendant::em[text()='" + str + "']]");
		WebDriverUtils.clickLink(driver, by);
		
		WebDriverUtils.switchToPopUpWin(driver);
		Navigator.explicitWait(2000);
		by = By.xpath("//input[@value='"+Labels.Btn_join_waitlist+"']");
		WebDriverUtils.clickButton(driver, by);
		Navigator.explicitWait(2000);
		by = By.name("submitButtonJS");
		WebDriverUtils.clickButton(driver, by);
		Navigator.explicitWait(2000);
		
		this.runCheckTranscript(driver);
	}
	
		/**
	 * learner go to CLM and withdraw module
	 * then check the status in My Training History
	 * please input "Withdrawn"in status in EKPTestData for checking if withdraw successful
	 * @param driver
	 */
	public void runSelfWithdrawModule(WebDriver driver){
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
				"LearningCenter", "MyCurrentCourses"), this);
		By by = By.id("RESULTS_PER_PAGE");
		WebDriverUtils.select_selector(driver, by, "All");
		Navigator.explicitWait(2000);
		//if module to withdraw is a sub-module, click "Show Sub-Modules" button
		if (!this.getModuleLevel().trim().equals("")) {
			this.CLM_ExpandModules(driver,this.getModuleLevel().split("/"));
		}
		//click withdraw button
		by = By.xpath("//*[@class='module-title' and text()='"+this.getModuleTitle()+"']/../../div//input[@value='"+Labels.Btn_Withdraw_Enrollment+"']");
		Navigator.waitForAjax(driver, by);
		WebDriverUtils.clickButton(driver, by);
		
		//confirm withdraw
		by = By.xpath("//button/span[text()='"+Labels.Btn_Withdraw+"']");
		Navigator.waitForAjax(driver, by);
		WebDriverUtils.clickButton(driver, by);
		//check if module status becomes "Withdrawn"
		this.runCheckTranscript(driver);
	}
	
	/**
	 * in Enrollment Request, click the filter which inputed in EKPTestData, and check if module exist
	 * @param driver
	 */
	public void runCheckEnrollRequestFilter(WebDriver driver){
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
				"LearningCenter", "EnrollRequest"), this);
		//choose filter
		By by = By.id("OVERALLSTATUSFILTER");
		WebDriverUtils.select_selector(driver, by, this.getStatus());
		//check if module exist
		by = By.linkText(this.getExpectedResult());
		JUnitAssert.assertTrue(WebDriverUtils.isElementPresent(by),"Cannot find "+this.getExpectedResult()+" in " +this.getStatus());
	}
	
	/**
	 * in CLM, check if class schedule (date+venue) is correct
	 * @param driver
	 */
	public void runCheckClsSchedule(WebDriver driver){
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
				"LearningCenter", "MyCurrentCourses"), this);
		By by = By.id("RESULTS_PER_PAGE");
		WebDriverUtils.select_selector(driver, by, "All");
		Navigator.explicitWait(2000);
		//if module is a sub-module, click "Show Sub-Modules" button
		if (!this.getModuleLevel().trim().equals("")) {
			this.CLM_ExpandModules(driver,this.getModuleLevel().split("/"));
		}
		
		//click calendar button
		by = By.xpath("//*[@class='module-title' and text()='"+this.getModuleTitle()+"']/../..//a[@class='class-schedule-btn course-dropdown-btn']");
		Navigator.waitForAjax(driver, by);
		WebDriverUtils.clickButton(driver, by);
		
		//get module date+venue
		by = By.xpath("//*[@class='module-title' and text()='"+this.getModuleTitle()+"']/../..//div[@class='class-schedule-list course-dropdown-list']/ul");
		Navigator.explicitWait(1000);
		String content = WebDriverUtils.getText(driver, by);
		JUnitAssert.assertTrue(content.contains(this.getExpectedResult()), "Test FAIL, class schedule does not match, Expected: " + this.getExpectedResult() + " , Current: " + content);
	}
	

}
