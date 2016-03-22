package com.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.abstractclasses.TestObject;
import com.config.Config;
import com.config.Labels;
import com.controller.TestDriver;
import com.junit.JUnitAssert;
import com.utils.WebDriverUtils;
import com.view.Navigator;
import com.view.SelectorsUI;
/**
 * 
 * @author martin.wang
 *
 */
public class Assessment extends com.abstractclasses.TestObject {
	private String Name = "", Exam = "", IsPreCourse = "", IsPostCourse = "",
			AvailableDate = "", Assessor = "", Reminder = "",
			ReminderSendDate = "", RevieweeID = "", RevieweeName,
			Reviewer = "", PeerReviewer = "", Assessment_Reviewee = "",
			Assessment_Reviewer = "", Assessment_PeerReviewer = "",
			Comment_Reviewer = "", Comment_Reviewee = "",
			Comment_PeerReviewer = "", AdHocCompetency = "";

	public String getAdHocCompetency() {
		return AdHocCompetency;
	}

	public void setAdHocCompetency(String adHocCompetency) {
		AdHocCompetency = adHocCompetency;
	}

	private ArrayList<JobProfile> jp_assessment_reviewee = new ArrayList<JobProfile>();
	private ArrayList<JobProfile> jp_assessment_reviewer = new ArrayList<JobProfile>();
	private ArrayList<JobProfile> jp_assessment_peer_reviewer = new ArrayList<JobProfile>();

	public ArrayList<JobProfile> getJp_assessment_reviewee() {
		return jp_assessment_reviewee;
	}

	public ArrayList<JobProfile> getJp_assessment_reviewer() {
		return jp_assessment_reviewer;
	}

	public ArrayList<JobProfile> getJp_assessment_peer_reviewer() {
		return jp_assessment_peer_reviewer;
	}

	public void setJp_assessment_reviewee(
			ArrayList<JobProfile> jp_assessment_reviewee) {
		this.jp_assessment_reviewee = jp_assessment_reviewee;
	}

	public void setJp_assessment_reviewer(
			ArrayList<JobProfile> jp_assessment_reviewer) {
		this.jp_assessment_reviewer = jp_assessment_reviewer;
	}

	public void setJp_assessment_peer_reviewer(
			ArrayList<JobProfile> jp_assessment_peer_reviewer) {
		this.jp_assessment_peer_reviewer = jp_assessment_peer_reviewer;
	}

	public String getComment_Reviewer() {
		return Comment_Reviewer;
	}

	public void setComment_Reviewer(String comment_Reviewer) {
		Comment_Reviewer = comment_Reviewer;
	}

	public String getComment_Reviewee() {
		return Comment_Reviewee;
	}

	public void setComment_Reviewee(String comment_Reviewee) {
		Comment_Reviewee = comment_Reviewee;
	}

	public String getComment_PeerReviewer() {
		return Comment_PeerReviewer;
	}

	public void setComment_PeerReviewer(String comment_PeerReviewer) {
		Comment_PeerReviewer = comment_PeerReviewer;
	}

	public String getAssessment_Reviewee() {
		return Assessment_Reviewee;
	}

	public void setAssessment_Reviewee(String assessment_Reviewee) {
		Assessment_Reviewee = assessment_Reviewee;
		jp_assessment_reviewee = this
				.parseJobProfile_Competency_Proficiency_Comments(assessment_Reviewee);
	}

	public String getAssessment_Reviewer() {
		return Assessment_Reviewer;
	}

	public void setAssessment_Reviewer(String assessment_Reviewer) {
		Assessment_Reviewer = assessment_Reviewer;
		jp_assessment_reviewer = this
				.parseJobProfile_Competency_Proficiency_Comments(assessment_Reviewer);
	}

	public String getAssessment_PeerReviewer() {
		return Assessment_PeerReviewer;
	}

	public void setAssessment_PeerReviewer(String assessment_PeerReviewer) {
		Assessment_PeerReviewer = assessment_PeerReviewer;
		jp_assessment_peer_reviewer = this
				.parseJobProfile_Competency_Proficiency_Comments(assessment_PeerReviewer);
	}

	public String getRevieweeID() {
		return RevieweeID;
	}

	public void setRevieweeID(String revieweeID) {
		RevieweeID = revieweeID;
	}

	public String getRevieweeName() {
		return RevieweeName;
	}

	public void setRevieweeName(String revieweeName) {
		RevieweeName = revieweeName;
	}

	public String getReviewer() {
		return Reviewer;
	}

	public void setReviewer(String reviewer) {
		Reviewer = reviewer;
	}

	public String getPeerReviewer() {
		return PeerReviewer;
	}

	public void setPeerReviewer(String peerReviewer) {
		PeerReviewer = peerReviewer;
	}

	public boolean equals(TestObject obj) {
		return true;
	}

	public void checkExpectedResult_UI(WebDriver driver, String str) {
		super.checkExpectedResult_UI(driver, str);
	}

	public String getName() {
		return Name;
	}

	public String getExam() {
		return Exam;
	}

	public String getIsPreCourse() {
		return IsPreCourse;
	}

	public String getIsPostCourse() {
		return IsPostCourse;
	}

	public String getAvailableDate() {
		return AvailableDate;
	}

	public String getAssessor() {
		return Assessor;
	}

	public String getReminder() {
		return Reminder;
	}

	public String getReminderSendDate() {
		return ReminderSendDate;
	}

	public void setName(String name) {
		Name = name;
	}

	public void setExam(String exam) {
		Exam = exam;
	}

	public void setIsPreCourse(String isprecourse) {
		IsPreCourse = isprecourse;
	}

	public void setIsPostCourse(String ispostcourse) {
		IsPostCourse = ispostcourse;
	}

	public void setAvailableDate(String availabledate) {
		AvailableDate = availabledate;
	}

	public void setAssessor(String assessor) {
		Assessor = assessor;
	}

	public void setReminder(String reminder) {
		Reminder = reminder;
	}

	public void setReminderSendDate(String remindersenddate) {
		ReminderSendDate = remindersenddate;
	}

	public void setName_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
			By by = By.name("DISPLAYNAME");
			WebDriverUtils.fillin_textbox(driver, by, str);
		}
	}

	public void setIsPreCourse_UI(WebDriver driver, String str) {
	}

	public void setIsPostCourse_UI(WebDriver driver, String str) {
	}

	public void setAvailableDate_UI(WebDriver driver, String str) {
	}

	public void setAssessor_UI(WebDriver driver, String str) {
	}

	public void setReminder_UI(WebDriver driver, String str) {
	}

	public void setReminderSendDate_UI(WebDriver driver, String str) {
	}

	/*
	 * public String toString() { return new StringBuilder().
	 * append(this.getClass().getName()). append("_").
	 * append(this.getFuncType()). append("_"). append(this.getName()).
	 * toString(); }
	 */

	public void setExam_UI(WebDriver driver, String str) {
		By by = By.linkText("+ Add exam/evaluation");
		WebDriverUtils.clickLink(driver, by);

		by = By.id("EXAM_SELECT");
		WebDriverUtils.clickButton(driver, by);

		WebDriverUtils.switchToPopUpWin(driver);
		
		SelectorsUI.PopUp_Selector(driver, SelectorsUI.PopUpSelector.TopDownSelector, str);


		/*
		 * by = By.name("SEARCHTEXT"); WebDriverUtils.fillin_textbox(driver, by,
		 * str); driver.findElement().clear();
		 * driver.findElement(By.name("SEARCHTEXT")).sendKeys(str);
		 * driver.findElement(By.name("SEARCH")).click(); new
		 * Select(driver.findElement
		 * (By.id("avlParmSelector"))).selectByIndex(0);
		 * driver.findElement(By.id("ADDSINGLE")).click();
		 * driver.findElement(By.name("save")).click();
		 */

		WebDriverUtils.switchToParentWin(driver);
	}

	public void runCreate(WebDriver driver) {

		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
				"ManageCenter", "Workflow"), this);

		By by = By.id("addAssessmentButton");
		WebDriverUtils.clickButton(driver, by);

		this.setName_UI(driver, this.getName());
		this.setExam_UI(driver, this.getExam());
		// Navigator.explicitWait(3000);
		by = By.name("saveButton");
		WebDriverUtils.clickButton(driver, by);
		Navigator.explicitWait(3000);
	}

	public void runAttachExam(WebDriver driver) {
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
				"ManageCenter", "Workflow"), this);

		By by = By.linkText(this.getName());
		WebDriverUtils.clickLink(driver, by);

		this.setExam_UI(driver, this.getExam());
		Navigator.explicitWait(3000);
		by = By.name("saveButton");
		WebDriverUtils.clickButton(driver, by);
	}

	/**
	 * Parse JobProfile, Competency, Proficiency, and comments based on strings
	 * 
	 * @param str
	 *            :JP_Assess2:CP_Assess2:2:good;\nJP_Full_G5R7:CP_Full_G5R7:2:
	 *            good;\n
	 * @return
	 */
	private ArrayList<JobProfile> parseJobProfile_Competency_Proficiency_Comments(
			String str) {
		ArrayList<JobProfile> jps = new ArrayList<JobProfile>();
		if (!str.equals("")) {
			for (String str_line : str.split("\n")) {
				JobProfile jp = new JobProfile();
				String[] strs = str_line.split(":");
				String id_jp = strs[0];
				String id_competency = strs[1];
				String id_proficiency = strs[2];
				String comments = strs[3];

				Competency comp = new Competency();
				comp.setCode(id_competency);
				comp.setProficiency(id_proficiency);
				comp.setDesc(comments);
				jp.setCode(id_jp);
				jp.addCompetency(comp);
				jps.add(jp);
			}
		}

		return jps;
	}

	/**
	 * Start assessment based on job profiles.
	 * Requirement: On CompetencyAssessment page
	 * @param driver
	 * @param jps
	 * @param comments
	 */
	public void runStartAssessment(WebDriver driver, ArrayList<JobProfile> jps,
			String comments) {
		//Navigator.navigate(driver, Navigator.webElmtMgr.getNavigationPathList(
		//		"LearningCenter", "2.CompetencyAssessment"));

		// 1. Select assessment
		String[] users_name = this.getRevieweeName().split(" ");
		String username = users_name[0] + " " + users_name[1];
		By by = By.xpath("//tr[descendant::td[contains(text(),'" + username
				+ "')] and ./td[text()='" + this.getName()
				+ "']]/td/div/button");

		// Navigator.waitForAjaxElementLoad(driver, by);
		Navigator.explicitWait(1000);

		WebDriverUtils.mouseOver(driver, by);

		by = By.linkText("Assess");

		// Navigator.waitForAjaxElementLoad(driver, by);
		Navigator.explicitWait(1000);

		WebDriverUtils.clickLink(driver, by);

		// 2. start assess
		for (JobProfile jp : jps) {
			// 2.1 expand the job profile
			String jp_row = "";
			if (jps.size() == 1) {
				jp_row = "//div[contains(text(),'Stand-Alone Competencies')  and following-sibling::*[position()=1][@style='display: none;']]";
			} else {
				jp_row = "//div[contains(text(),'"
						+ jp.getCode()
						+ "')  and following-sibling::*[position()=1][@style='display: none;']]";
			}

			by = By.xpath(jp_row);
			
			// expand if collapsed
			if ( WebDriverUtils.getHowManyByPresntInPage(driver,by, false)> 0) {
				WebDriverUtils.clickButton(driver, by);
				Navigator.explicitWait(1000);
			}

			ArrayList<Competency> comps = jp.getCompetencyList();
			for (Competency comp : comps) {
				// 2.2 edit proficiency level
				String comp_row = "";
				if (jps.size() == 1) {
					comp_row = "//div[contains(text(),'Stand-Alone Competencies')  and following-sibling::*[position()=1][@style='display: block;']]/following-sibling::div[1]";
				} else {
					comp_row = "//div[contains(text(),'"
							+ jp.getCode()
							+ "')  and following-sibling::*[position()=1][@style='display: block;']]/following-sibling::div[1]";
				}

				String proficiency_row = comp_row
						+ "/table/tbody/tr[descendant::td[text()='"
						+ comp.getCode() + "']]/td[2]/select";
				by = By.xpath(proficiency_row);

				Navigator.explicitWait(1000);
				// Navigator.waitForAjaxElementLoad(driver, by);

				WebDriverUtils.clickLink(driver, by);

				by = By.xpath("//div[descendant::span[text()='"
						+ comp.getCode()
						+ "']]/table/tbody/tr/td[contains(text(),'"
						+ comp.getProficiency() + "')]");

				Navigator.explicitWait(1000);
				// Navigator.waitForAjaxElementLoad(driver, by);

				WebDriverUtils.clickLink(driver, by);

				Navigator.explicitWait(1000);

				// 2.3 edit comments
				String comment_row = comp_row
						+ "/table/tbody/tr[descendant::td[text()='"
						+ comp.getCode() + "']]/td[3]/textarea";
				by = By.xpath(comment_row);

				Navigator.explicitWait(1000);
				// Navigator.waitForAjaxElementLoad(driver, by);
				WebDriverUtils.fillin_textbox(driver, by, comp.getDesc());
			}
		}

		// Overall comments
		by = By.xpath("//fieldset[descendant::legend[text()='Overall Comments']]/textarea");
		Navigator.explicitWait();
		// Navigator.waitForAjaxElementLoad(driver, by);
		WebDriverUtils.fillin_textbox(driver, by, comments);

		by = By.xpath("//span[text()='Save']");
		Navigator.explicitWait();
		// Navigator.waitForAjaxElementLoad(driver, by);
		WebDriverUtils.clickButton(driver, by);

		Navigator.explicitWait(1000);
		// 3. check result
		by = By.xpath("//tr[descendant::td[contains(text(),'" + username
				+ "')] and ./td[text()='" + this.getName()
				+ "'] and ./td[text()='In Progress']]"
				+ "/td[6]/span[@class='progressbar']/span");
		String progress = WebDriverUtils.getText(driver, by).trim();
		// assertTrue(progress.equalsIgnoreCase("80%"));
	}

	/**
	 * Finish assessment. 
	 * Requirement: on Competency Assessment page
	 * @param driver
	 */
	public void runFinishAssessment(WebDriver driver) {
	
		// 1. Select assessment
		String[] users_name = this.getRevieweeName().split(" ");
		String username = users_name[0] + " " + users_name[1];
		By by = By.xpath("//tr[descendant::td[contains(text(),'" + username
				+ "')] and ./td[text()='" + this.getName()
				+ "'] and ./td[text()='In Progress']]/td/div/button");
		Navigator.explicitWait();
		// Navigator.waitForAjaxElementLoad(driver, by);
		WebDriverUtils.mouseOver(driver, by);

		Navigator.explicitWait(1000);
		by = By.linkText("Assess");
		Navigator.explicitWait();
		// Navigator.waitForAjaxElementLoad(driver, by);
		WebDriverUtils.clickLink(driver, by);

		Navigator.explicitWait(1000);
		// 2. Finish assessment
		by = By.xpath("//span[text()='Finish']");
		Navigator.explicitWait();
		// Navigator.waitForAjaxElementLoad(driver, by);
		WebDriverUtils.clickButton(driver, by);

		Navigator.explicitWait();
		// 3. Check result
		by = By.xpath("//tr[descendant::td[contains(text(),'" + username
				+ "')] and ./td[text()='" + this.getName()
				+ "'] and ./td[text()='Finished']]"
				+ "/td[6]/span[@class='progressbar']/span");
		String progress = WebDriverUtils.getText(driver, by).trim();
		Navigator.explicitWait();
		String expectedProgress = "100%";
		JUnitAssert.assertEquals(expectedProgress, progress);
	}

	/**
	 * Go to signoff page and click signoff button use this method if 1st
	 * reviewer has already done assessment before
	 * 
	 * @param driver
	 */
	public void runSignOffAssessment(WebDriver driver) {
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
				"LearningCenter", "CompetencyAssessment"));

		// 1. Select assessment
		String[] users_name = this.getRevieweeName().split(" ");
		String username = users_name[0] + " " + users_name[1];
		By by = By.xpath("//tr[descendant::td[contains(text(),'" + username
				+ "')] and ./td[text()='" + this.getName()
				+ "'] and ./td[text()='Finished']]/td/div/button");
		
		if (WebDriverUtils.getHowManyByPresntInPage(driver,by, false) == 0) {
			by = By.xpath("//tr[descendant::td[contains(text(),'"
					+ username
					+ "')] and ./td[text()='"
					+ this.getName()
					+ "'] and ./td[text()='Waiting For Rating Control']]/td/div/button");
		}
		//Navigator.explicitWait();
		// Navigator.waitForAjaxElementLoad(driver, by);
		WebDriverUtils.mouseOver(driver, by);

		by = By.linkText("Sign Off");
		Navigator.explicitWait();
		// Navigator.waitForAjaxElementLoad(driver, by);
		WebDriverUtils.clickLink(driver, by);

		by = By.xpath("//span[text()='Sign Off']");
		Navigator.explicitWait();
		// Navigator.waitForAjaxElementLoad(driver, by);
		WebDriverUtils.clickButton(driver, by);

	}

	/**
	 * Go to signoff page, do assessment & click signoff button use this method
	 * if 1st reviewer is the final person who does assessment
	 * 
	 * @param driver
	 * @param jps
	 * @param comments
	 */
	public void runSignOffAssessment(WebDriver driver,
			ArrayList<JobProfile> jps, String comments) {
		// 1. goto sign off page
		this.runSignOffAssessment(driver);

		// 2. start assess
		By by = null;
		String[] users_name = this.getRevieweeName().split(" ");
		String username = users_name[0] + " " + users_name[1];

		for (JobProfile jp : jps) {
			// 2.1 expand the job profile
			// Navigator.explicitWait(1000);
			ArrayList<Competency> comps = jp.getCompetencyList();
			for (Competency comp : comps) {
				// 2.2 edit proficiency level
				String comp_row = "//div[descendant::div/span[text()='"
						+ comp.getCode() + "']]";

				String proficiency_row = comp_row + "/table/tbody/tr/td/select";
				by = By.xpath(proficiency_row);
				Navigator.explicitWait();
				// Navigator.waitForAjaxElementLoad(driver, by);

				WebDriverUtils.clickLink(driver, by);
				Navigator.explicitWait(1000);

				by = By.xpath("//div[descendant::span[text()='"
						+ comp.getCode()
						+ "']]/table[@id='proficiencyLevelTable']/tbody/tr/td[contains(text(),'"
						+ comp.getProficiency() + "')]");

				Navigator.explicitWait();
				// Navigator.waitForAjaxElementLoad(driver, by);
				WebDriverUtils.clickLink(driver, by);

				// Navigator.explicitWait(1000);

				// 2.3 edit comments
				String comment_row = comp_row + "/table/tbody/tr/td/textarea";
				by = By.xpath(comment_row);
				Navigator.explicitWait();
				// Navigator.waitForAjaxElementLoad(driver, by);
				WebDriverUtils.fillin_textbox(driver, by, comp.getDesc());
				// Navigator.explicitWait(1000);
			}
		}

		// Overall comment
		by = By.xpath("//fieldset[descendant::legend[text()='Overall Comments']]/table/tbody/tr/td/textarea");
		// Navigator.waitForAjaxElementLoad(driver, by);
		Navigator.explicitWait();
		WebDriverUtils.fillin_textbox(driver, by, comments);

		by = By.xpath("//span[text()='Sign Off']");
		// Navigator.waitForAjaxElementLoad(driver, by);
		Navigator.explicitWait();
		WebDriverUtils.clickButton(driver, by);

		// Navigator.explicitWait(1000);
	}

	/**
	 * The logic here is to delopy 360 deg assessment to a reviewee, then assign
	 * multiple reviewers to assess the level of job profile/competency, and
	 * then check out assessment result.
	 * 
	 * @param driver
	 */
	public void runJobProfileAssessment(WebDriver driver) {

		// 1. Reviewer Deploy assessment in CDC;
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
				"LearningCenter", "OrgUserReview"));
		CDC cdcInstance = new CDC();
		cdcInstance.setRevieweeID(this.getRevieweeID());		
		cdcInstance.runDeployAssessment_CDC(driver, this.getRevieweeName(),
				this.getName(), this.getPeerReviewer(),
				this.getAdHocCompetency());

		// 2. Reviewer starts and finishes assessment
		this.runStartAssessment(driver, this.jp_assessment_reviewer,
				this.getComment_Reviewer());
		this.runFinishAssessment(driver);

		// 3. Reviewee starts and finishes assessment
		User reviewee = new User();
		reviewee.setUID(this.getRevieweeID());
		reviewee.setPWD(Config.getInstance().getProperty("user.default.pass"));
		reviewee.setName(this.getRevieweeName());

		TestDriver.switchUser(reviewee);
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
				"LearningCenter", "CompetencyAssessment"));
		this.runStartAssessment(driver, this.jp_assessment_reviewee,
				this.getComment_Reviewee());
		this.runFinishAssessment(driver);

		// 4. Peer reviewee starts and finishes assessment
		User peerReviewee = new User();
		peerReviewee.setUID(this.getPeerReviewer());
		peerReviewee.setPWD(Config.getInstance().getProperty("user.default.pass"));
		peerReviewee.setName(this.getRevieweeName());

		TestDriver.switchUser(peerReviewee);
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
				"LearningCenter", "CompetencyAssessment"));
		this.runStartAssessment(driver, this.jp_assessment_peer_reviewer,
				this.getComment_PeerReviewer());
		this.runFinishAssessment(driver);

		// 5. Reviewer signoff assessment
		User reviewer = new User();
		reviewer.setUID(this.getUID());
		reviewer.setPWD(this.getPWD());
		TestDriver.switchUser(reviewer);
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
				"LearningCenter", "CompetencyAssessment"));
		this.runSignOffAssessment(driver);

		// 6. Check signoff results

	}

	/**
	 * 
	 * @param driver
	 */
	public void runSetProficiencyOfAssessment(WebDriver driver) {
		// 1. Reviewer finishes assessment
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
				"LearningCenter", "CompetencyAssessment"));
		this.runFinishAssessment(driver);
		Navigator.explicitWait(1000);

		// 2. Reviewee finishes assessment
		User reviewee = new User();
		reviewee.setUID(this.getRevieweeID());
		reviewee.setPWD(Config.getInstance().getProperty("user.default.pass"));
		reviewee.setName(this.getRevieweeName());

		TestDriver.switchUser(reviewee);
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
				"LearningCenter", "CompetencyAssessment"));
		this.runFinishAssessment(driver);
		Navigator.explicitWait(1000);

		// 3. Peer reviewee starts and finishes assessment
		User peerReviewee = new User();
		peerReviewee.setUID(this.getPeerReviewer());
		peerReviewee.setPWD(Config.getInstance().getProperty("user.default.pass"));
		peerReviewee.setName(this.getRevieweeName());

		TestDriver.switchUser(peerReviewee);
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
				"LearningCenter", "CompetencyAssessment"));
		this.runSignOffAssessment(driver, this.jp_assessment_peer_reviewer,
				this.getComment_PeerReviewer());
	}
	
	/**
	 * deploy assessment
	 * 
	 * @author tony.tan
	 * @param driver
	 */
	
	public void runDeployAssessment(WebDriver driver){
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
				"LearningCenter", "OrgUserReview"));
		int waitTime = 1000;
		
		By by = By.id("FIRSTNAME");
		Navigator.explicitWait(waitTime);
		WebDriverUtils.fillin_textbox(driver, by, Reviewer);
		
		by = By.xpath("//input[@type='SUBMIT' and @value='"+Labels.Btn_Filter+"']");
		Navigator.explicitWait(waitTime);
		WebDriverUtils.clickButton(driver, by);
		
		by = By.xpath("/descendant::*[@type='button'][1]");
		WebDriverUtils.mouseOver(driver, by);
		Navigator.explicitWait(waitTime);
		by = By.linkText("Deploy Assessment");
		Navigator.explicitWait(waitTime);
		WebDriverUtils.clickHiddenElement(driver, by);
		
		by = By.name("templateId");
		Navigator.explicitWait(waitTime);
		WebDriverUtils.select_selector(driver, by, "Full Assessment");
		
		by = By.id("nextButton");
		Navigator.explicitWait(waitTime);
		WebDriverUtils.clickButton(driver, by);
		
		by = By.id("nextButton");
		Navigator.explicitWait(waitTime);
		WebDriverUtils.clickButton(driver, by);
		
		by = By.id("finishButton");
		Navigator.explicitWait(waitTime);
		WebDriverUtils.clickButton(driver, by);
	}
	/**
	 * select the secondary reviewer(peer reviewer)
	 * 
	 * @author tony.tan
	 * @param driver
	 */
	public void runSecondaryReviewer(WebDriver driver){
		Navigator.navigate(driver,Navigator.xmlWebElmtMgr.getNavigationPathList(
				"LearningCenter","CompetencyAssessment"));
		int waitTime = 1000;
		
		By by = By.xpath("/descendant::*[@type='button'][1]");
		WebDriverUtils.mouseOver(driver, by);
		Navigator.explicitWait(waitTime);
		by = By.linkText("Select reviewers");
		WebDriverUtils.clickHiddenElement(driver, by);
		
		by = By.id("token-input-netd_token_input_Users");
		WebDriverUtils.fillin_textbox(driver, by, PeerReviewer);
		Navigator.explicitWait(waitTime);
		by = By.xpath("html/body/div[3]/ul/li");
		Navigator.explicitWait(waitTime);
		WebDriverUtils.clickButton(driver, by);
		
		by = By.id("saveButton");
		Navigator.explicitWait(waitTime);
		WebDriverUtils.clickButton(driver, by);
		
	}
	
	/**
	 * this program check the reviewer name and secondary reviewer name before approve.
	 * 
	 * @author tony.tan
	 * @param driver
	 */
	public void runApproveReviewer(WebDriver driver) {
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
				"LearningCenter","CompetencyAssessment"));
		
		//By by = By.id("filter-participant-status");
		//Navigator.explicitWait(3000);
		//WebDriverUtils.select_selector(driver, by, "Not Started");
		//Navigator.explicitWait(3000);
		By by = By.xpath("/descendant::*[@type='button'][1]");
		WebDriverUtils.mouseOver(driver, by);
		by = By.linkText("Approve");
		WebDriverUtils.clickHiddenElement(driver, by);
		Navigator.explicitWait(1000);
		
		by = By.id("innerdiv_netd_token_input_Users");// secondary reviewer
		if(WebDriverUtils.getHowManyByPresntInPage(driver, by, false) > 0){
			boolean peerReviewer = driver.findElement(by).getText().contains(PeerReviewer.toUpperCase());
			String msgForFail = "the peer reviewer that you are approving is mismatching ";
			JUnitAssert.assertTrue(peerReviewer, msgForFail);
		}
		by = By.id("revieweeList");
		if(WebDriverUtils.getHowManyByPresntInPage(driver, by, false) > 0){
			boolean reviewer = driver.findElement(by).getText().contains(Reviewer.toUpperCase());
			String msgForFail = "the reviewer that you are approving is mismatching ";
			JUnitAssert.assertTrue(reviewer, msgForFail);
		}
		
		by = By.id("saveButton");
		Navigator.explicitWait(1000);
		WebDriverUtils.clickButton(driver, by);	
	}

}