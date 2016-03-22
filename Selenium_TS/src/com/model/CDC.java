package com.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.abstractclasses.TestObject;
import com.config.Config;
import com.config.Labels;
import com.controller.TestDriver;
import com.dao.DBUser;
import com.dao.DBUserDAO;
import com.junit.JUnitAssert;
import com.utils.CriteriaParser;
import com.utils.SikuliUtils;
import com.utils.WebDriverUtils;
import com.view.FunctionUI;
import com.view.Navigator;
import com.view.SelectorsUI;
import com.view.TSSystemUI;

/**
 * 
 * @author martin.wang
 *
 */
public class CDC extends com.abstractclasses.TestObject {
	private String RevieweeID = "", RevieweeName = "", Org = "",
			ModuleTitle = "", Cert = "", Competency = "", JobProfile = "",
			LearningPathImageFile_Reviewee = "",
			LearningPathImageFile_Reviewer = "", AssessmentID = "",
			Reviewer_Assessment = "", ExpectedResult_CP = "",
			ExpectedResult_JP = "", ExpectedResult_Module = "",
			CPAcquisitionMethod = "", TransferSessionTitle="", CPLevelCode="";
	
	
	
	public String getCPLevelCode() {
		return CPLevelCode;
	}

	public void setCPLevelCode(String cPLevelCode) {
		CPLevelCode = cPLevelCode;
	}

	public String getCPAcquisitionMethod() {
		return CPAcquisitionMethod;
	}

	public void setCPAcquisitionMethod(String cPAcquisitionMethod) {
		CPAcquisitionMethod = cPAcquisitionMethod;
	}

	private DBUser revieweeDBUser;

	public DBUser getReviewDBUser() {
		return this.revieweeDBUser;
	}
	// data are set up @setRevieweeID method
	public void setReviewDBUser(DBUser revieweeDBUser) {
		this.revieweeDBUser = revieweeDBUser;
	}

	public String getExpectedResult_Module() {
		return ExpectedResult_Module;
	}

	public void setExpectedResult_Module(String expectedResult_Module) {
		ExpectedResult_Module = expectedResult_Module;
	}

	public String getExpectedResult_CP() {
		return ExpectedResult_CP;
	}

	public void setExpectedResult_CP(String expectedResult_CP) {
		ExpectedResult_CP = expectedResult_CP;
	}

	public String getModuleTitle() {
		return ModuleTitle;
	}

	public void setModuleTitle(String moduleTitle) {
		ModuleTitle = moduleTitle;
	}
	
	public String getTransferSessionTitle() {
		return TransferSessionTitle;
	}

	public void setTransferSessionTitle(String sessionTitle) {
		TransferSessionTitle = sessionTitle;
	}

	public String getAssessmentID() {
		return AssessmentID;
	}

	public void setAssessmentID(String assessmentID) {
		AssessmentID = assessmentID;
	}

	public String getReviewer_Assessment() {
		return Reviewer_Assessment;
	}

	public void setReviewer_Assessment(String reviewer_Assessment) {
		Reviewer_Assessment = reviewer_Assessment;
	}

	public String getLearningPathImageFile_Reviewee() {
		return LearningPathImageFile_Reviewee;
	}

	public void setLearningPathImageFile_Reviewee(
			String learningPathImageFile_Reviewee) {
		LearningPathImageFile_Reviewee = learningPathImageFile_Reviewee;
	}

	public String getLearningPathImageFile_Reviewer() {
		return LearningPathImageFile_Reviewer;
	}

	public void setLearningPathImageFile_Reviewer(
			String learningPathImageFile_Reviewer) {
		LearningPathImageFile_Reviewer = learningPathImageFile_Reviewer;
	}

	public String getRevieweeID() {
		return RevieweeID;
	}

	public void setRevieweeID(String revieweeID) {
		RevieweeID = revieweeID;
		DBUserDAO dbUserDAO = new DBUserDAO(TestDriver.dbManager.getConn());
		DBUser revieweeDBUser = dbUserDAO.findByUserId(revieweeID);
		this.setReviewDBUser(revieweeDBUser);
	}

	public String getRevieweeName() {
		return RevieweeName;
	}

	public void setRevieweeName(String revieweeName) {
		RevieweeName = revieweeName;
	}

	public boolean equals(com.abstractclasses.TestObject para0) {
		boolean result = false;
		return result;
	}

	public CDC() {
		super();
	}

	public String getOrg() {
		return Org;
	}

	public String getCert() {
		return Cert;
	}

	public String getCompetency() {
		return Competency;
	}

	public String getJobProfile() {
		return JobProfile;
	}

	public void setOrg(String org) {
		Org = org;
	}

	public void setCert(String cert) {
		Cert = cert;
	}

	public void setCompetency(String competency) {
		Competency = competency;
	}

	public void setJobProfile(String jobprofile) {
		JobProfile = jobprofile;
	}

	public void setReviewee_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
		}
	}

	public void setOrg_UI(WebDriver driver, String str) {
		By by = null;
		if (!str.equals("")) {
			by = By.linkText("Organization");
			WebDriverUtils.clickLink(driver, by);
			WebDriverUtils.switchToPopUpWin(driver);
			WebDriverUtils.checkSelect_Radio(driver, str);
			WebDriverUtils.switchToParentWin(driver);
		}

		by = By.name("apply-filters");
		WebDriverUtils.clickButton(driver, by);
	}

	public void setModule_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
		}
	}

	public void setCert_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
		}
	}

	public void setCompetency_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
		}
	}

	public void setJobProfile_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
		}
	}

	public void setPagination_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
			By by = By.id("RESULTS_PER_PAGE");
			WebDriverUtils.select_selector(driver, by, str);
			Navigator.explicitWait(1000);
		}
	}

	/**
	 * Direct Appraiser assigns goals (performance goal or development goal) to
	 * reviewee in CDC
	 * 
	 * @param driver
	 * @param goal
	 *            : goal object (performance goal or development goal)
	 */
	public void runDeployGoal_CDC(WebDriver driver, ArrayList<TestObject> goals) {
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
				"LearningCenter", "OrgUserReview"));
		for (TestObject goal : goals) {
			/*
			 * //1. Setup org. this.setOrg_UI(driver, this.getOrg());
			 * Navigator.explicitWait(1000); //2. Setup pagination
			 * this.setPagination_UI(driver, "200");
			 * Navigator.explicitWait(1000);
			 */

			this.filterUserInCDCByUserName(driver, this.getRevieweeName());

			// 3. Select the reviewee
			this.selectReviewee_CDC_UI(driver, this.getRevieweeName());
			Navigator.explicitWait(1000);
			// 4. Deploy goal
			By by = By.id("reviewActionDropdown");
			String str = "Assign ";
			Goal goal_ins = null;
			if (goal instanceof PerformanceGoal) {
				goal_ins = (PerformanceGoal) goal;
				str += Labels.Label_Personal_Goal.toString();
			} else if (goal instanceof DevelopmentGoal) {
				goal_ins = (DevelopmentGoal) goal;
				str += Labels.Label_Dev_Goal.toString();
			} else {
				if (Config.DEBUG_MODE) {
					System.out.println("Should pass in a Goal-type instance");
				}
			}
			WebDriverUtils.select_selector(driver, by, str);
			Navigator.explicitWait(1000);
			if (goal_ins != null) {
				// 5. create goal
				goal_ins.runCreateWithoutNavigation(driver, true);	
				
				// 6. Check output
				// logon as reviewee to check goal if created successfully
				
//				User user = new User(this.getRevieweeID(), this.getPWD());
//				User.logout(driver);
//				user.login(driver);
//				
//				if (goal instanceof PerformanceGoal) {
//					((PerformanceGoal)goal_ins).runCheckGoalExisted(driver);
//				}else if (goal instanceof DevelopmentGoal) {
//					((DevelopmentGoal)goal_ins).runCheckGoalExisted(driver);
//				}
//				User.logout(driver);
//				User user2 = new User(this.getUID(), this.getPWD());
//				user2.login(driver);
			}
		}
	}

	/**
	 * Can delete the goal if the user is the creator and the goal is not
	 * included in the appraisal
	 * 
	 * @param driver
	 * @param goals
	 */
	public void runCheckCanDeleteGoal_CDC(WebDriver driver,
			ArrayList<TestObject> goals) {
		for (TestObject goal : goals) {
			// 1. Goto CDC->Learning Center of a reviewee
			this.gotoCDC_Reviewee_UI(driver, "CareerCenter",
					this.getRevieweeName());

			// 2. Click Career Center -> Goals
			By by = By.linkText("Goals");
			WebDriverUtils.clickLink(driver, by);

			Goal goal_ins = null;
			if (goal instanceof PerformanceGoal) {
				by = By.xpath("//a[descendant::span[text()='"+Labels.Tab_Personal_Goal+"']]");
				goal_ins = (PerformanceGoal) goal;
			} else if (goal instanceof DevelopmentGoal) {
				by = By.xpath("//a[descendant::span[text()='"+Labels.Tab_Dev_Goal+"']]");
				goal_ins = (DevelopmentGoal) goal;
			} else {
				if (Config.DEBUG_MODE) {
					System.out.println("Should pass in a Goal-type instance");
				}
			}
			WebDriverUtils.clickButton(driver, by);
			// Navigator.explicitWait(1000);
			if (goal_ins != null) {
				// 5. Select goal
				goal_ins.checkCanDelete(driver);
			}
			WebDriverUtils.closeAllPopUpWins(driver);
		}
	}

	/**
	 * Cannot delete the goal if the user is NOT the creator or the goal is
	 * included in the appraisal
	 * 
	 * @param driver
	 * @param goal
	 */
	public void runCheckCannotDeleteGoal_CDC(WebDriver driver,
			ArrayList<TestObject> goals) {
		for (TestObject goal : goals) {
			// 1. Goto CDC->Learning Center of a reviewee
			this.gotoCDC_Reviewee_UI(driver, "CareerCenter",
					this.getRevieweeName());

			// 2. Click Career Center -> Goals
			By by = By.linkText("Goals");
			WebDriverUtils.clickLink(driver, by);

			Goal goal_ins = null;
			if (goal instanceof PerformanceGoal) {
				by = By.xpath("//a[descendant::span[text()='"+Labels.Tab_Personal_Goal+"']]");
				goal_ins = (PerformanceGoal) goal;
			} else if (goal instanceof DevelopmentGoal) {
				by = By.xpath("//a[descendant::span[text()='"+Labels.Tab_Dev_Goal+"']]");
				goal_ins = (DevelopmentGoal) goal;
			} else {
				if (Config.DEBUG_MODE) {
					System.out.println("Should pass in a Goal-type instance");
				}
			}
			WebDriverUtils.clickButton(driver, by);
			Navigator.explicitWait(1000);
			if (goal_ins != null) {
				// 5. Select goal
				goal_ins.checkCannotDelete(driver);
			}
			WebDriverUtils.closeAllPopUpWins(driver);
		}
	}

	/**
	 * Direct Appraiser filter reviewee, deploys performance assessment in Team Review.
	 * Requirement: on Team Review page
	 * @param driver
	 * @param reviewee
	 * @param assessmentID
	 * @param secondaryReviewer
	 * @param adHocCompetency
	 */
	public void runDeployAssessment_CDC(WebDriver driver, String reviewee,
			String assessmentID, String secondaryReviewer,
			String adHocCompetency) {

	
		/*
		 * //1. Setup org. this.setOrg_UI(driver, this.getOrg());
		 * Navigator.explicitWait(1000); //2. Setup pagination
		 * this.setPagination_UI(driver, "200"); Navigator.explicitWait(1000);
		 */

		this.filterUserInCDCByUserName(driver, reviewee);

		// 3. Select the reviewee
		this.selectReviewee_CDC_UI(driver, reviewee);
		Navigator.explicitWait(1000);
		// 4. Deploy assessment
		By by = By.id("reviewActionDropdown");
		String str = "Deploy Assessment";
		WebDriverUtils.select_selector(driver, by, str);

		// 5. Select assessment
		by = By.name("templateId");
		WebDriverUtils.select_selector(driver, by, assessmentID);
		by = By.id("nextButton");
		WebDriverUtils.clickButton(driver, by);

		// 6. Select secondary reviewers
		by = By.id("Users-button-id");
		WebDriverUtils.clickLink(driver, by);

		ArrayList<String> Reviewers = new ArrayList<String>();
		Reviewers.add(secondaryReviewer);
		SelectorsUI.PopUp_Selector(driver,
				SelectorsUI.PopUpSelector.InnerUserSelector, Reviewers);
		Navigator.explicitWait(1000);

		// 7. Select ad-hoc competency
		if (adHocCompetency.equals("")) {
			// 8. Save
			by = By.id("finishButton");
			WebDriverUtils.clickButton(driver, by);
		} else {
			by = By.id("nextButton");
			WebDriverUtils.clickButton(driver, by);
			Navigator.explicitWait(1000);

			ArrayList<String> keywords = new ArrayList<String>();
			keywords.add(adHocCompetency);
			FunctionUI.searchInAjaxSearchBox(driver, keywords);
			Navigator.explicitWait(1000);

			by = By.id("finishButton");
			WebDriverUtils.clickButton(driver, by);
		}

	
		// 9. Go to Competency Assessment
		by = By.xpath("//button[descendant::span[contains(text(),'"+Labels.Proceed_Comp_Assessment+"')]]");
		WebDriverUtils.clickButton(driver, by);
		//JUnitAssert.assertTrue(size > 0, "Do not include goal:"+ by);
	}

	/**
	 * Select a single reviewee in CDC
	 * 
	 * @param driver
	 * @param reviewee
	 *            : user name (last name + family name)
	 */
	public void selectReviewee_CDC_UI(WebDriver driver, String reviewee) {
		if (!reviewee.equals("")) {
			String user_info_row = "//div[@id='" + this.revieweeDBUser.getEKPID()
					+ "']";
			By by = By.xpath(user_info_row
					+ "/div/div[@class='user-checkbox']/input");
			//Navigator.waitForAjax(driver, by);
			WebDriverUtils.check_checkbox(driver, by);
		}
	}

	/**
	 * Martin: set action for a single reviewee in CDC
	 * 
	 * @param driver
	 * @param reviewee
	 *            : user name (last name + family name)
	 * @param action
	 *            :Review Employee Profile, Review Learning Center, Review
	 *            Career Center, Deploy Assessment, Assign Performance Goal,
	 *            Assign Development Goal, Assign competency, Assign learning
	 *            module
	 */
	public void setAction_Reviewee_CDC_UI(WebDriver driver, String reviewee,
			String action) {
		if (!reviewee.equals("") && !action.equals("")) {
			// 1. select reviewee;
			this.selectReviewee_CDC_UI(driver, reviewee);

			// 2. mouse-over the gear button
			String user_info_row = "//div[@id='" + this.revieweeDBUser.getEKPID()
					+ "']";

			By by = By.xpath(user_info_row
					+ "/div/div[@class='user-action']/div/button");
			WebDriverUtils.mouseOver(driver, by);
			;

			// 3. set action
			by = By.linkText(action);
			WebDriverUtils.clickLink(driver, by);
		}
	}

	/**
	 * Select a number of reviewees in CDC
	 * 
	 * @param driver
	 * @param reviewees
	 *            : an array to keep user names (last name + family name)
	 */
	public void selectReviewee_CDC_UI(WebDriver driver, String[] reviewees) {
		if (reviewees.length > 0) {
			for (String reviewee : reviewees) {
				this.selectReviewee_CDC_UI(driver, reviewee);
			}
		}
	}

	/**
	 * Navigate to CDC -> Learning Center, Employee Profile, or Career Center
	 * for a specific user
	 * 
	 * @param driver
	 * @param url
	 *            : LearningCenter, EmployeeProfile, or CareerCenter
	 * @param reviewee
	 *            : user name (last name + family name)
	 */
	public void gotoCDC_Reviewee_UI(WebDriver driver, String url,
			String reviewee) {
		if (!url.equals("") && !reviewee.equals("")) {
			Navigator
					.navigate(driver, Navigator.xmlWebElmtMgr
							.getNavigationPathList("LearningCenter",
									"OrgUserReview"));

			/*
			 * //1. Setup org. this.setOrg_UI(driver, this.getOrg());
			 * Navigator.explicitWait(1000); //2. Setup pagination
			 * this.setPagination_UI(driver, "200");
			 * Navigator.explicitWait(1000);
			 */

			this.filterUserInCDCByUserName(driver, reviewee);
			// 3. Goto CDC-> Learning Center, Employee Profile, or Career Center
			By by = null;

			String user_info_row = "//div[@id='" + this.revieweeDBUser.getEKPID()
					+ "']";

			String path_target = user_info_row
					+ "/div/div[@class='user-action-link']";
			if (url.equalsIgnoreCase("LearningCenter")) {
				path_target += "/a[2]";
			} else if (url.equalsIgnoreCase("EmployeeProfile")) {
				path_target += "/a[1]";
			} else if (url.equalsIgnoreCase("CareerCenter")) {
				path_target += "/a[3]";
			}
			by = By.xpath(path_target);
			WebDriverUtils.clickLink(driver, by);

			WebDriverUtils.switchToPopUpWin(driver);
		}
	}

	/**
	 * Check CDC -> Learning Center -> Learning Path
	 * 
	 * @param driver
	 */
	public void runCheckLearningPath_LearningCenter_CDC(WebDriver driver) {
		// 1. Goto CDC-> Learning Center of a reviewee
		this.gotoCDC_Reviewee_UI(driver, "LearningCenter",
				this.getRevieweeName());

		// 2. Click Learning Center -> Learning Path
		By by = By.linkText("Learning Path");
		Navigator.explicitWait(1000);
		// Navigator.waitForAjaxElementLoad(driver, by);
		WebDriverUtils.clickLink(driver, by);

		// 3. Check learning path: Sikuli Image-based comparison
		Navigator.explicitWait(1000);
		String screenshotFile = this.getLearningPathImageFile_Reviewer();
		boolean exist = SikuliUtils.screenshotExistInWin(screenshotFile);
		JUnitAssert.assertTrue(exist, "Cannot find image in screenshot:"
				+ screenshotFile);

		WebDriverUtils.closeAllPopUpWins(driver);
	}

	public void runAssignModules(WebDriver driver){
		// 1. Goto CDC -> search reviewee
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList("LearningCenter","OrgUserReview"));
		this.filterUserInCDCByUserName(driver, this.getRevieweeName());
		
		// 2. Click Gear Button -> Assign learning module
		By by = By.xpath("//div[@id='"+this.revieweeDBUser.getEKPID()+"']/button");
		Navigator.waitForAjax(driver, by);
		WebDriverUtils.mouseOver(driver, by);
		Navigator.explicitWait();
		by = By.linkText(Labels.Gear_assign_course.toString());
		Navigator.waitForAjax(driver, by);
		WebDriverUtils.clickLink(driver, by);
		
		// 3. Search the Module
		WebDriverUtils.switchToPopUpWin(driver);
		by = By.id("ajaxInlineSearchBox_input");
		WebDriverUtils.fillin_textbox(driver, by, this.getModuleTitle());
		
		by = By.xpath("//button[@name = 'SEARCH']");
		Navigator.waitForAjax(driver, by);
		WebDriverUtils.clickButton(driver, by);
		
		// 4. Select the Module
		by = By.xpath("//div[@id='contentDivBody']/table/tbody/tr/td[2]/a/em");
		Navigator.waitForAjax(driver, by);
		WebDriverUtils.clickLink(driver, by);
		WebDriverUtils.switchToPopUpWin(driver);
		
		// 7. Click Enroll users
	    by = By.xpath("//input[@name = 'enroll']");
		Navigator.waitForAjax(driver, by);
		WebDriverUtils.clickLink(driver, by);
				
		by = By.xpath("//input[@name = 'submitButtonJS']");
		Navigator.waitForAjax(driver, by);
		WebDriverUtils.clickLink(driver, by);
		
		by = By.xpath("//input[@name = 'closeButton']");
		Navigator.waitForAjax(driver, by);
		WebDriverUtils.clickLink(driver, by);
		WebDriverUtils.closeAllPopUpWins(driver);
		Navigator.explicitWait();
		//try to refersh the page to fix frequency fail in following button
		this.filterUserInCDCByUserName(driver, this.getRevieweeName());
		// 8.Verify Enrollment
		by = By.xpath("//div[@id='"+this.revieweeDBUser.getEKPID()+"']/button");
		Navigator.explicitWait();
		Navigator.waitForAjax(driver, by);
		WebDriverUtils.mouseOver(driver, by);
		
		by = By.linkText(Labels.Gear_reviewlearningcenter.toString());
		Navigator.explicitWait();
		Navigator.waitForAjax(driver, by);
		WebDriverUtils.clickLink(driver, by);
		WebDriverUtils.switchToPopUpWin(driver);
		
		by = By.linkText(Labels.Link_review_learning.toString());
		Navigator.waitForAjax(driver, by);
		WebDriverUtils.clickLink(driver, by);
		
		by=By.xpath("//ul[@class='top-level-module-list']");
		String ActualContent=WebDriverUtils.getText(driver, by);
		JUnitAssert.assertTrue(ActualContent.contains(this.getModuleTitle()), "Enrollment failed. Expected Result:"+this.getModuleTitle());
		
		
	}
	
	public void runSessionTransfer(WebDriver driver) {
		// 1. Goto CDC-> Learning Center of a reviewee
		this.gotoCDC_Reviewee_UI(driver, "LearningCenter",
				this.getRevieweeName());

		// 2. Click Learning Center -> Learning Path
		By by = By.linkText("Learning");
		Navigator.waitForAjax(driver, by);
		WebDriverUtils.clickLink(driver, by);
		// click Session Tranfer Button
		by = By.xpath("//input[@value='"+Labels.Btn_SessionTransfer+"']");
		Navigator.waitForAjax(driver, by);
		WebDriverUtils.clickButton(driver, by);
		
		//Select the Session to Transfer
		by = By.id("session-transfer-sessions");
		Navigator.waitForAjax(driver, by);
		WebDriverUtils.select_selector_partialTexts(driver, "session-transfer-sessions", this.getTransferSessionTitle());
		
		//Submit the change
		by = By.id("session-transfer-button");
		Navigator.waitForAjax(driver, by);
		WebDriverUtils.clickButton(driver, by);
		
		//click class schedule drop down button
		Navigator.explicitWait(2000);
		by = By.xpath("//a[contains(@class,'class-schedule-btn course-dropdown-btn')]");
		WebDriverUtils.clickLink(driver, by);
				
		Navigator.explicitWait(2000);
		//get the new Class Schedule Info, copy the info under the title "Class Schedule", not the one in bullets
		by = By.xpath("//ul[@class='class-schedule-dropdown']/p[1]");
		String schedule = WebDriverUtils.getText(driver, by);
				

		JUnitAssert.assertTrue(schedule.equals(this.getExpectedResult()), "Session Transfer Failed, Current Result= " + schedule +", Expected Result= " + this.getExpectedResult());
	}
	
	
	
	private void filterUserInCDCByUserName(WebDriver driver, String userName) {
		String[] userNames = userName.split(" ");
		String lastName = userNames[0];
		String firstName = userNames[1];
		By byname = By.id("FIRSTNAME");
		WebDriverUtils.fillin_textbox(driver, byname, firstName);

		byname = By.id("LASTNAME");
		WebDriverUtils.fillin_textbox(driver, byname, lastName);
			
		//By by = By.name("apply-filters");
		By by = By.xpath("//input[@type='SUBMIT' and @value='"+Labels.Btn_Filter+"']");
		//WebElement targetEle = driver.findElement(by);
		//Actions builder = new Actions(driver);
		//builder.moveToElement(targetEle).perform();
		WebDriverUtils.clickButton(driver, by);

		Navigator.explicitWait();
	}

	/**
	 * Drill down job profile -> Competency in CDC to check module status for a
	 * specific reviewee.
	 * 
	 * @param driver
	 * @param JobProfile
	 * @param Competency
	 * @param LearningModule
	 * @param reviewee
	 */
	public void runCheckCP_DrillDown(WebDriver driver) {
		if(!this.getScheduleTask().equals("")){
			User login_user = new User(Config.getInstance().getProperty("ndadmin"), Config.getInstance().getProperty("ndadmin.pass"));
			TestDriver.switchUser(login_user);
			TSSystemUI.runScheduledTask(driver, this.getScheduleTask());
			
			login_user = new User(this.getUID(), this.getPWD());
			TestDriver.switchUser(login_user);
		}
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
				"LearningCenter", "OrgUserReview"));

		this.filterUserInCDCByUserName(driver, this.getRevieweeName());

		/*
		 * User reviewee = new
		 * User(this.getRevieweeID().toLowerCase(),this.getPWD());
		 * reviewee.setName(this.getRevieweeName()); //1. Checkout reviewee's
		 * learning path // Comment it out as it is not stable for image compare
		 * to check learning path
		 * reviewee.setLearningPathImageFile(this.getLearningPathImageFile_Reviewee
		 * ()); reviewee.runCheckLearningPath(driver);
		 * 
		 * //2. login as reviewer User reviewer = new User(this.getUID(),
		 * this.getPWD()); TestDriver.switchUser(reviewer);
		 */

		// 3. check module status based on job profile drill-down in CDC
		this.runCheckDrillDown(driver, this.getJobProfile(),
				this.getCompetency(), this.getCPAcquisitionMethod(),
				this.getModuleTitle());

		// 4. check Learning path in CDC -> Learning Center
		/* this.runCheckLearningPath_LearningCenter_CDC(driver); */
	}

	public String getExpectedResult_JP() {
		return ExpectedResult_JP;
	}

	public void setExpectedResult_JP(String expectedResult_JP) {
		ExpectedResult_JP = expectedResult_JP;
	}

	/**
	 * Check competency progress in CDC -> Learning Center -> Summary
	 * 
	 * @param driver
	 */
	public void runCheckCompetency_LearningCenter_CDC(WebDriver driver) {
		// 1. Goto CDC->Learning Center of a reviewee
		this.gotoCDC_Reviewee_UI(driver, "LearningCenter",
				this.getRevieweeName());

		// 2. Click Learning Center -> Learning Path
		By by = By.linkText("Summary");
		WebDriverUtils.clickLink(driver, by);

		// 3. Check out progress of a competency
		by = By.xpath("//tr[descendant::td/div/a[text()='"
				+ this.getCompetency() + "']]/td[2]/span/span");
		String str = WebDriverUtils.getText(driver, by);
		String expectedStr = "25%";
		JUnitAssert.assertEquals(expectedStr, str);

		WebDriverUtils.closeAllPopUpWins(driver);
	}

	/**
	 * Check competency assessessment in CDC -> Career Center -> Competencies ->
	 * Competency Assessments
	 * 
	 * @param driver
	 */
	public void runCheckCompetencyAssessments_CareerCenter_CDC(WebDriver driver) {
		if(!this.getSysConf().equals("")){
			TSSystemUI.setupSystemConf(driver, this.getSysConf());
		}
		
		
		// 1. Goto CDC->Career Center of a reviewee
		this.gotoCDC_Reviewee_UI(driver, "CareerCenter", this.getRevieweeName());

		// 2. Click Career Center -> Competencies
		By by = By.linkText("Competencies");
		WebDriverUtils.clickLink(driver, by);

		// 3. Check out progress of a competency
		by = By.xpath("//tr[descendant::td[text()='" + this.getAssessmentID()
				+ "']]/td/a");
		WebDriverUtils.clickLink(driver, by);
		HashMap<String, ArrayList<String>> CP_Proficiencies =CriteriaParser.parseKeyValueList(":", ";", this.getExpectedResult());

		Iterator<String> CPs = CP_Proficiencies.keySet().iterator();

		while (CPs.hasNext()) {
			String CP = CPs.next();

			ArrayList<String> Proficiencies = CP_Proficiencies.get(CP);
			for (String expectedProficiency : Proficiencies) {
				Navigator.explicitWait();
				by = By.xpath("//div[descendant::div/span[text()='"
						+ CP
						+ "']]/div[@class='competency-gap']/div[@class='text-right padding-right-10']");
				String actualProficiency = WebDriverUtils.getText(driver, by);
				JUnitAssert.assertTrue(
						actualProficiency.contains(expectedProficiency),
						"Actual Proficiency:(" + actualProficiency
								+ ") for Competency (" + CP
								+ ") does not match with expected proficiency:"
								+ expectedProficiency);
			}
		}
		WebDriverUtils.closeAllPopUpWins(driver);
	}
/*
	public void runCheckCP_AcquisitionMethod_DrillDown(WebDriver driver) {
		Navigator.navigate(driver, Navigator.webElmtMgr.getNavigationPathList(
				"LearningCenter", "2.TeamReview"));
		this.runCheckDrillDown(driver, this.getJobProfile(),
				this.getCompetency(), this.getCPAcquisitionMethod(),
				this.getModuleTitle());

	}*/
    
	/*
	 * Case failed due to Timezone issue (Dev#8344)
	 */
	public void runCheckDrillDown(WebDriver driver, String JobProfile,
			String Competency, String AcqMethod, String LearningModule) {

		String user_info_row = "//div[@id='" + this.revieweeDBUser.getEKPID()
				+ "']";

		if (!JobProfile.equals("") && !Competency.equals("")
				&& !this.revieweeDBUser.getEKPID().equals("")) {

			// 1. Expand job profile for a specific user
			By by = By.xpath(user_info_row + "/div/div[@class='user-info']/i");
			WebDriverUtils.clickLink(driver, by);

			// 2. Further expand the job profile
			String job_profile_row = user_info_row
					+ "/descendant::li[@class='data-job-profile']/div[text()='"+ JobProfile + "']";
			by = By.xpath(job_profile_row + "/i[1]");
			WebDriverUtils.clickLink(driver, by);

			// 2.1. check job profile status
			by = By.xpath(job_profile_row + "/i[2]");
			String attr = "title";
			Navigator.waitForAjax(driver, by);

			String status = WebDriverUtils.getAttribute(driver, by, attr);
			JUnitAssert.assertTrue(this.getExpectedResult_JP().equalsIgnoreCase(status),"JP status (Expected:"+this.getExpectedResult_JP()+"Actual:"+status+" Dev#8344");
			

			// 2.2 check competency acquisition method

			if (!AcqMethod.equals("")) {
				String competency_row = user_info_row
						+ "/descendant::span/div[contains(text(),'" + AcqMethod + "')]";
				by = By.xpath(competency_row);
				
				JUnitAssert.assertTrue(WebDriverUtils.getHowManyByPresntInPage(driver,by, true)> 0,
						"Acquisition Method is not shown properly" + Competency);

			}

			if (!LearningModule.equals("")) {
				// 3. Expand competency
				String competency_row = user_info_row
						+ "/descendant::li[@class='data-competency']/div[text()='"
						+ Competency + "']";
				by = By.xpath(competency_row + "/i[1]");
				WebDriverUtils.clickLink(driver, by);

				// 3.1. check competency status
				by = By.xpath(job_profile_row + "/i[2]");
				attr = "title";
				Navigator.waitForAjax(driver, by);

				status = WebDriverUtils.getAttribute(driver, by, attr);
				JUnitAssert.assertTrue(this.getExpectedResult_CP().equalsIgnoreCase(status),"CP status Expected:"+this.getExpectedResult_CP()+"Actual:"+status+" Dev#8344");
		

				// 4. Check learning module
				String module_row = user_info_row
						+ "/descendant::li[@class='data-learning-module']/div[text()='"
						+ LearningModule + "']";
				by = By.xpath(module_row + "/i[2]");
				attr = "title";
				Navigator.waitForAjax(driver, by);
				status = WebDriverUtils.getAttribute(driver, by, attr);
				JUnitAssert.assertTrue(this.getExpectedResult_Module().equalsIgnoreCase(status),"Learning Module status Expected:"+this.getExpectedResult_Module()+"Actual:"+status+" Dev#8344");
		
			
			}
		}

	}
	/**
	 * go to Manager > My team review > Org/User Review > gear button > Assign Competency
	 * award competency to user
	 * @param driver
	 */
	public void runAwardAdHocCP(WebDriver driver) {
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
				"LearningCenter", "OrgUserReview"));

		this.filterUserInCDCByUserName(driver, this.getRevieweeName());

		// 3. Select the reviewee
		this.selectReviewee_CDC_UI(driver, this.getRevieweeName());
		Navigator.explicitWait(1000);

		By by = By.id("reviewActionDropdown");
		String str = "Assign competency";
		WebDriverUtils.select_selector(driver, by, str);
		Navigator.explicitWait(1000);

		//select competency
		by=By.xpath("//tr[descendant::td[contains(text(),'"+this.getCompetency()+"')]]/td[3]/label/select");
		WebDriverUtils.clickLink(driver, by);
		
		//select level
		by = By.xpath("//div[descendant::span[text()='"+this.getCompetency()+"']]/table[@id='proficiencyLevelTable']/tbody/tr/td[contains(text(),'"
				+ this.getCPLevelCode() + "')]");
		Navigator.waitForAjax(driver, by);
		WebDriverUtils.clickLink(driver, by);
		
		//click save button
		by = By.name("saveButton");
		Navigator.waitForAjax(driver, by);
		WebDriverUtils.clickButton(driver, by);
		Navigator.explicitWait(1000);
		
		//check if save successful
		by = By.xpath("//b[text() = 'Competency Awarded']");
		JUnitAssert.assertTrue(WebDriverUtils.isElementPresent(by),"Failed to award competency for " + this.getRevieweeName());
		
		//reviewee check competency awarded
		User reviewee = new User(this.getRevieweeID(), Config.getInstance().getProperty("user.default.pass"));
		TestDriver.switchUser(reviewee);
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
				"LearningCenter", "MyCompetency"));
		
		by = By.xpath("//a[text()='"+this.getCompetency()+"']/../..//td[text()='"+this.getCPLevelCode()+"']");
		JUnitAssert.assertTrue(WebDriverUtils.isElementPresent(by),"cannot find awarded competency of " + this.getRevieweeName());
	} 
}