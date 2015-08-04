package com.netdimen.model;


import java.util.ArrayList;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.netdimen.abstractclasses.TestObject;
import com.netdimen.config.Config;
import com.netdimen.config.Labels;
import com.netdimen.controller.TestDriver;
import com.netdimen.junit.JUnitAssert;
import com.netdimen.utils.CriteriaParser;
import com.netdimen.utils.WebDriverUtils;
import com.netdimen.view.FunctionUI;
import com.netdimen.view.Navigator;

/**
 * 
 * @author martin.wang
 *
 */
public class Appraisal extends com.netdimen.abstractclasses.TestObject {
	private String AppraisalID = "", DirectAppraisal = "", SuperAppraisal = "",
			PA1 = "", PA2 = "", GR = "", GR_StartDate = "", GR_EndDate = "",
			CP1 = "", AD1 = "", RS_Avg = "", RS_Weighted = "", RS_Percent = "",
			CP2 = "", AD2 = "", Rate = "", leaving = "", risk = "",
			PerformScore = "", PPScore = "", RRScore = "", Appraisee="", Organization="", Goals = "";
	
	//Support goal-creation within appraisal
	
	private ArrayList<Goal> goalArray = new ArrayList<Goal>();
	
	public String getGoals() {
		return Goals;
	}

	public void setGoals(String goals) {
		Goals = goals;
		ArrayList<TestObject> testCases = this.loadTestCases(goals);
		if(testCases!= null){
			for(TestObject testCase:testCases){
				goalArray.add((Goal)testCase);
			}
		}
	}

	public String getLeaving() {
		return leaving;
	}

	public void setLeaving(String leaving) {
		this.leaving = leaving;
	}

	public String getRisk() {
		return risk;
	}

	public void setRisk(String risk) {
		this.risk = risk;
	}

	public String getAppraisee() {
		return Appraisee;
	}

	public void setAppraisee(String appraisee) {
		Appraisee = appraisee;
	}

	public String getOrganization() {
		return Organization;
	}

	public void setOrganization(String organization) {
		Organization = organization;
	}

	private HashMap<String, ArrayList<String>> criteria_status = null;

	public boolean equals(com.netdimen.abstractclasses.TestObject para0) {
		boolean result = false;
		return result;
	}

	public Appraisal() {
		super();
	}

	public String getAppraisalID() {
		return AppraisalID;
	}

	public String getDirectAppraisal() {
		return DirectAppraisal;
	}

	public String getSuperAppraisal() {
		return SuperAppraisal;
	}

	public String getPA1() {
		return PA1;
	}

	public String getPA2() {
		return PA2;
	}

	public String getGR() {
		return GR;
	}

	public String getGR_StartDate() {
		return GR_StartDate;
	}

	public String getGR_EndDate() {
		return GR_EndDate;
	}

	public String getCP1() {
		return CP1;
	}

	public String getAD1() {
		return AD1;
	}

	public String getRS_Avg() {
		return RS_Avg;
	}

	public String getRS_Weighted() {
		return RS_Weighted;
	}

	public String getRS_Percent() {
		return RS_Percent;
	}

	public String getCP2() {
		return CP2;
	}

	public String getAD2() {
		return AD2;
	}

	public String getRate() {
		return Rate;
	}

	public String getleaving() {
		return leaving;
	}

	public String getrisk() {
		return risk;
	}

	public String getPerformScore() {
		return PerformScore;
	}

	public String getPPScore() {
		return PPScore;
	}

	public String getRRScore() {
		return RRScore;
	}

	public void setAppraisalID(String appraisalid) {
		AppraisalID = appraisalid;
	}

	public void setDirectAppraisal(String directappraisal) {
		DirectAppraisal = directappraisal;
	}

	public void setSuperAppraisal(String superappraisal) {
		SuperAppraisal = superappraisal;
	}

	public void setPA1(String pa1) {
		PA1 = pa1;
	}

	public void setPA2(String pa2) {
		PA2 = pa2;
	}

	public void setGR(String gr) {
		GR = gr;
	}

	public void setGR_StartDate(String gr_startdate) {
		GR_StartDate = gr_startdate;
	}

	public void setGR_EndDate(String gr_enddate) {
		GR_EndDate = gr_enddate;
	}

	public void setCP1(String cp1) {
		CP1 = cp1;
	}

	public void setAD1(String ad1) {
		AD1 = ad1;
	}

	public void setRS_Avg(String rs_avg) {
		RS_Avg = rs_avg;
	}

	public void setRS_Weighted(String rs_weighted) {
		RS_Weighted = rs_weighted;
	}

	public void setRS_Percent(String rs_percent) {
		RS_Percent = rs_percent;
	}

	public void setCP2(String cp2) {
		CP2 = cp2;
	}

	public void setAD2(String ad2) {
		AD2 = ad2;
	}

	public void setRate(String rate) {
		Rate = rate;
	}

	public void setleaving(String leaving) {
		leaving = leaving;
	}

	public void setrisk(String risk) {
		risk = risk;
	}

	public void setPerformScore(String performscore) {
		PerformScore = performscore;
	}

	public void setPPScore(String ppscore) {
		PPScore = ppscore;
	}

	public void setRRScore(String rrscore) {
		RRScore = rrscore;
	}

	public void setAppraisalID_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
		}
	}

	public void setDirectAppraisal_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
		}
	}

	public void setSuperAppraisal_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
		}
	}

	public void setPA1_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
		}
	}

	public void setPA2_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
		}
	}

	public void setGR_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
		}
	}

	public void setGR_StartDate_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
		}
	}

	public void setGR_EndDate_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
		}
	}

	public void setCP1_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
		}
	}

	public void setAD1_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
		}
	}

	public void setRS_Avg_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
		}
	}

	public void setRS_Weighted_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
		}
	}

	public void setRS_Percent_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
		}
	}

	public void setCP2_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
		}
	}

	public void setAD2_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
		}
	}

	public void setRate_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
		}
	}

	public void setleaving_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
		}
	}

	public void setrisk_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
		}
	}

	public void setPerformScore_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
		}
	}

	public void setPPScore_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
		}
	}

	public void setRRScore_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
		}
	}

	public void runStartAppraisal(WebDriver driver){
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
				"LearningCenter", "MyAppraisal"));
		this.startAppraisal(driver);
	}
	
	public void startAppraisal(WebDriver driver) {
		By by = By.id("createNewButton");
		WebDriverUtils.clickButton(driver, by);

		by = By.id("TEMPLATEID");
		WebDriverUtils.select_selector(driver, by, this.getAppraisalID());

		by = By.xpath("//button[descendant::span[text()='Save']]");
		WebDriverUtils.clickButton(driver, by);

		WebDriverUtils.switchToPopUpWin(driver);
		Navigator.explicitWait(1000);
		by = By.name("STARTAPPRAISAL");
		WebDriverUtils.clickButton(driver, by);

		//2. Future Planning:
		//2.1. Create goals if necessary
		if(!this.getGoals().equals("")){
			
			by = By.linkText("2. Future Planning");
			WebDriverUtils.clickLink(driver, by);
			//Navigator.explicitWait(1000);
			
			for(Goal goal:goalArray){
				if(goal instanceof PerformanceGoal){
					by = By.xpath("//div[descendant::h4[text()='"+Labels.Perf_Goal_Plan+"']]/span/span[text()='"+Labels.Create_Goal+"']");	
				}else if(goal instanceof DevelopmentGoal){
					by = By.xpath("//div[descendant::h4[text()='"+Labels.Dev_Goal_Plan+"']]/span/span[text()='"+Labels.Create_Goal+"']");
				}
				Navigator.waitForAjax(driver, by);
				WebDriverUtils.clickButton(driver, by);
				goal.runCreateWithoutNavigation(driver, false);			
			}
			
			by = By.id("SAVE2");
			Navigator.waitForAjax(driver, by);
			WebDriverUtils.clickButton(driver, by);
			//Navigator.explicitWait(1000);
			
		}
		
		//1.1.Past Achievements: fill in other significant archievements
		by = By.linkText("1. Past Achievements");
		Navigator.waitForAjax(driver, by);
		WebDriverUtils.clickLink(driver, by);
		//Navigator.explicitWait(1000);
		
		by = By.xpath("//div[descendant::h4[text()='"+Labels.Other_Achievements+"']]/p/textarea");
		Navigator.waitForAjax(driver, by);
		WebDriverUtils.fillin_textbox(driver, by,
				"Nothing significant to report");

		//1.2.Past Achievement:fill in goal result if necessary
		String row_xpath = "//div[descendant::h4[text()='Goal Results']]/table[@id='goalTbl']/tbody/tr[@class='goal-row']";
		String employee_comment_xpath = row_xpath + "/td/textarea[starts-with(@id,'APPRAISEE_COMMENTS_')]";
		by = By.xpath(employee_comment_xpath);
		
		if(WebDriverUtils.getHowManyByPresntInPage(driver,by, false)> 0){
			WebDriverUtils.fillin_textbox(driver, by, "appraisee's comments");
			String employee_rating_xpath = row_xpath + "/td[descendant::input[starts-with(@id,'APPRAISEE_RATING_')]]/table/tbody/tr/td/div[text()='1']";
			by = By.xpath(employee_rating_xpath);
			Navigator.waitForAjax(driver, by);
			WebDriverUtils.clickButton(driver, by);
			
			String agreed_rating_xpath = row_xpath + "/td[descendant::input[starts-with(@id,'AGREED_RATING_')]]/table/tbody/tr/td/div[text()='1']";
			by =By.xpath(agreed_rating_xpath);
			Navigator.waitForAjax(driver, by);
			WebDriverUtils.clickButton(driver, by);
		}
		
		
		by = By.id("SAVE2");
		Navigator.waitForAjax(driver, by);
		WebDriverUtils.clickButton(driver, by);
		this.checkSaveMsg_UI(driver);

		//1.3. Past Achievement: check performance assessment rating
		by = By.id("APPRAISALVIEW_0_2");
		Navigator.waitForAjax(driver, by);
		WebDriverUtils.clickLink(driver, by);

		by = By.xpath("//tr[descendant::td[text()='"+Labels.Exceeded_Expected_Performance+"']]/td[1]/input[starts-with(@id,\"SC\")]");
		Navigator.waitForAjax(driver, by);
		WebDriverUtils.checkRadio(driver, by);

		by = By.id("SAVE2");
		Navigator.waitForAjax(driver, by);
		WebDriverUtils.clickButton(driver, by);
		this.checkSaveMsg_UI(driver);

	
		// 3. sign-off
		by = By.linkText("3. Sign Off");
		Navigator.waitForAjax(driver, by);
		WebDriverUtils.clickLink(driver, by);
		//Navigator.explicitWait(1000);

		by = By.xpath("(//input[starts-with(@id,\"CAREER_DEV_DIALOGUE_\")])[1]");
		Navigator.waitForAjax(driver, by);
		WebDriverUtils.check_checkbox(driver, by);

		by = By.xpath("(//input[starts-with(@id,\"APPRAISEE_ACCEPTANCE_\")])[1]");
		Navigator.waitForAjax(driver, by);
		WebDriverUtils.check_checkbox(driver, by);

		by = By.id("SAVE2");
		Navigator.waitForAjax(driver, by);
		WebDriverUtils.clickButton(driver, by);
		this.checkSaveMsg_UI(driver);
		
		WebDriverUtils.closeAllPopUpWins(driver);
	}
	
	private void checkGoalInclusion(WebDriver driver, By by, String criteria){
		
		int size;
		if (criteria.equalsIgnoreCase("include")) {
			size= WebDriverUtils.getHowManyByPresntInPage(driver,by, true);
			JUnitAssert.assertTrue(size > 0, "Do not include goal:"+ by);
		} else if (criteria.equalsIgnoreCase("exclude")) {
			size =WebDriverUtils.getHowManyByPresntInPage(driver,by, false);
			JUnitAssert.assertTrue(size == 0, "Do not exclude goal" + by);
		}
	}
	
	public void checkGoalInclusion(WebDriver driver){
		By by = By.linkText(this.getAppraisalID());
		Navigator.waitForAjax(driver, by);
		WebDriverUtils.clickLink(driver, by);
		WebDriverUtils.switchToPopUpWin(driver);
		//Navigator.explicitWait(1000);
		
		String[] goals = this.getExpectedResult().split("\n");//visible:NETD/NETD ACC DEPT;A2
		for(String goal: goals){
			String[] goals1 = goal.split(":");
			String session = goals1[0];
			String criteria = goals1[1]; //include or exclude;
			String[] goalsArray = goals1[2].split(";");
			
			
			if(session.equalsIgnoreCase("GR")){
				by = By.linkText("1. Past Achievements");
				//Navigator.explicitWait();
				Navigator.waitForAjax(driver, by);
				WebDriverUtils.clickLink(driver, by);
				
				for (String goal_temp : goalsArray) {
					by = By.xpath("//div[descendant::h4[text()='Goal Results']]/table[@id='goalTbl']/tbody/tr/td/span/a[text()='"
							+ goal_temp + "']");
					this.checkGoalInclusion(driver, by, criteria);
				}

				
			}else if(session.equalsIgnoreCase("PGP") || session.equalsIgnoreCase("DGP")){
				by = By.linkText("2. Future Planning");
				//Navigator.explicitWait();
				Navigator.waitForAjax(driver, by);
				WebDriverUtils.clickLink(driver, by);
				
				for (String goal_temp : goalsArray) {
					if(session.equalsIgnoreCase("PGP")){
						by = By.xpath("//div[descendant::h4[text()='"+Labels.Perf_Goal_Plan+"']]/table[@id='individual-goal-list']/tbody/tr/td[contains(text(),'"
								+ goal_temp + "')]");	
					}else{
						by = By.xpath("//div[descendant::h4[text()='"+Labels.Dev_Goal_Plan+"']]/table[@id='individual-goal-list']/tbody/tr/td[contains(text(),'"
								+ goal_temp + "')]");	
					}
					this.checkGoalInclusion(driver, by, criteria);
				}
			}
		}
		
		WebDriverUtils.closeAllPopUpWins(driver);
	}

	/**Check inclusion/exclusion of a specific goal in appraisal.
	 * Rules:
	 * When Target Date is:
			1) Before Appr_Period
			--> PG/DG/OG ALL excluded
			2) Within Appr_Period
			--> PG-GR; DG-excluded; OG-GR
			3) After Appr_Period
			--> PG-PGP; DG-DGP; OG-excluded
	 * @param driver
	 */
	public void runCheckGoalInclusion(WebDriver driver){
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
				"LearningCenter", "MyAppraisal"));
		this.checkGoalInclusion(driver);
	}

	/**Submit appraisal, shared by appraisee and appraiser
	 * 
	 * @param driver
	 */
	private void submitAppraisal(WebDriver driver, boolean is_DA_Submit){
		// submit appraisal
		By by;
		if (!is_DA_Submit){
			by = By.linkText(this.getAppraisalID());
			Navigator.waitForAjax(driver, by);
			WebDriverUtils.clickLink(driver, by);
			WebDriverUtils.switchToPopUpWin(driver);
		}
		by = By.linkText("3. Sign Off");
		Navigator.waitForAjax(driver, by);
		WebDriverUtils.clickLink(driver, by);

		by = By.xpath("(//input[starts-with(@id,\"CAREER_DEV_DIALOGUE_\")])[1]");
		//Navigator.explicitWait(1000);
		Navigator.waitForAjax(driver, by);	
		WebDriverUtils.check_checkbox(driver, by);

		by = By.xpath("(//input[starts-with(@id,\"APPRAISEE_ACCEPTANCE_\")])[1]");
		if(WebDriverUtils.isElementPresent(by)){
			WebDriverUtils.check_checkbox(driver, by);	
		}
		
		by = By.xpath("(//input[starts-with(@id,\"APPRAISER_ACCEPTANCE_\")])[1]");
		if(WebDriverUtils.isElementPresent(by)){
			WebDriverUtils.check_checkbox(driver, by);	
		}
		
		by = By.id("SUBMITREV");
		Navigator.waitForAjax(driver, by);
		WebDriverUtils.clickButton(driver, by);

		by = By.name("submitButton");
		Navigator.waitForAjax(driver, by);
		WebDriverUtils.clickButton(driver, by);
		
		WebDriverUtils.closeAllPopUpWins(driver);
	}

	/**Controller to submit appraisee's appraisal
	 * 
	 * @param driver
	 */
	public void runSubmitAppraisal(WebDriver driver) {
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
				"LearningCenter", "MyAppraisal"));
		
		this.submitAppraisal(driver, false);
	}

	public void completeAppraisal_DA(WebDriver driver, boolean only_submit_page){
		By by = By.id("STATUS");
		//Navigator.explicitWait(1000);
		Navigator.waitForAjax(driver, by);	
		WebDriverUtils.select_selector(driver, by, "All");
		
		by = By.xpath("//div[@class='netd-filter-form']/input[@name='SEARCH']");
		//Navigator.explicitWait(1000);
		Navigator.waitForAjax(driver, by);		
		WebDriverUtils.clickButton(driver, by);
		
//		String expectedResult = this.loadExpectedResult("Status2");
		by = By.xpath("//tr[descendant::td[3][contains(text(),'("+this.getAppraisee().toUpperCase()+")')] and ./td[4][text()='"+this.getAppraisalID()+"']][1]/td[2]/div/button/span/i");
		//Navigator.explicitWait(1000);
		Navigator.waitForAjax(driver, by);	
		WebDriverUtils.mouseOver(driver, by);
		by = By.xpath("//tr[descendant::td[3][contains(text(),'("+this.getAppraisee().toUpperCase()+")')] and ./td[4][text()='"+this.getAppraisalID()+"']][1]/td[2]/div/ul/li[descendant::a[contains(text(),'Continue')]]/a");
		//Navigator.explicitWait(1000);
		Navigator.waitForAjax(driver, by);	
		WebDriverUtils.clickLink(driver, by);
		WebDriverUtils.switchToPopUpWin(driver);
		
		
		
		//1. Fill-in goal results if necessary
		String row_xpath = "//div[descendant::h4[text()='Goal Results']]/table[@id='goalTbl']/tbody/tr[@class='goal-row']";
		String employee_comment_xpath = row_xpath + "/td/textarea[starts-with(@id,'APPRAISER_COMMENTS_')]";
		by = By.xpath(employee_comment_xpath);
		Navigator.explicitWait(1000);
		
		if( WebDriverUtils.getHowManyByPresntInPage(driver,by, false)> 0){
			WebDriverUtils.fillin_textbox(driver, by, "appraisee's comments");
			String employee_rating_xpath = row_xpath + "/td[descendant::input[starts-with(@id,'APPRAISER_RATING_')]]/table/tbody/tr/td/div[text()='1']";
			by = By.xpath(employee_rating_xpath);
			Navigator.waitForAjax(driver, by);	
			WebDriverUtils.clickButton(driver, by);
			
			by = By.id("SAVE2");
			Navigator.waitForAjax(driver, by);	
			WebDriverUtils.clickButton(driver, by);
		}
		// Case study
		//by = By.linkText("3. Sign Off");
		by = By.partialLinkText("Sign Off"); // this fulfill both 1. Sign Off and 3. Sign Off
		Navigator.waitForAjax(driver, by);	
		WebDriverUtils.clickLink(driver, by);
		//Navigator.explicitWait(1000);
		
		if (!only_submit_page){
			by = By.xpath("(//input[starts-with(@id,\"APPRAISER_ACCEPTANCE_\")])[1]");
			Navigator.waitForAjax(driver, by);	
			WebDriverUtils.check_checkbox(driver, by);
		}
		//submit current review
		by = By.id("SUBMITREV");
		Navigator.waitForAjax(driver, by);	
		WebDriverUtils.clickButton(driver, by);	
		
		//confirm to submit
		by = By.name("submitButton");
		Navigator.waitForAjax(driver, by);	
		WebDriverUtils.clickButton(driver, by);
		WebDriverUtils.closeAllPopUpWins(driver);
	}
	
	public void runCompleteAppraisal_DA(WebDriver driver){
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
				"LearningCenter", "TeamAppraisal"));
		
		this.completeAppraisal_DA(driver, false);
	}
	
	public void submitAppraisal_DA(WebDriver driver, String appraisalID){
		By by = By.id("TEMPLATEID");
		WebDriverUtils.select_selector(driver, by, appraisalID);
		
		by = By.xpath("//div[@class='netd-filter-form']/input[@name='SEARCH']");
		WebDriverUtils.clickButton(driver, by);
		
		String expectedResult = this.loadExpectedResult("Status1");
		//by = By.xpath("//tr[descendant::td[3][contains(text(),'"+this.getAppraisee().toUpperCase()+"')] and ./td[4][text()='"+appraisalID+"'] and ./td[9][text()='"+expectedResult+"']][1]/td[2]/div/button/span/i");
		by=  By.xpath("//tr//td[contains(text(),'"+this.getAppraisee().toUpperCase()+"')]/../td//div/button");
		
		//Navigator.explicitWait(1000);
		Navigator.waitForAjax(driver, by);	
		WebDriverUtils.mouseOver(driver, by);

		//by = By.xpath("//tr[descendant::td[3][contains(text(),'("+this.getAppraisee().toUpperCase()+")')] and ./td[4][text()='"+appraisalID+"'] and ./td[9][text()='"+expectedResult+"']][1]/td[2]/div/ul/li[descendant::a[contains(text(),'Continue')]]/a");
		by = By.xpath("//tr//td[contains(text(),'"+this.getAppraisee().toUpperCase()+"')]/../td//div/ul/li[descendant::a[contains(text(),'Continue')]]/a");
		Navigator.waitForAjax(driver, by);	
		WebDriverUtils.clickLink(driver, by);
		
		WebDriverUtils.switchToPopUpWin(driver);
		
		this.submitAppraisal(driver, true);
		
		WebDriverUtils.closeAllPopUpWins(driver);
	}
	
	public void runSubmitAppraisal_DA(WebDriver driver){
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
				"LearningCenter", "TeamAppraisal"));
		
		this.submitAppraisal_DA(driver, this.getAppraisalID());
	}
	
	public void runCheckRateSumReport(WebDriver driver){
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
				"LearningCenter", "TeamAppraisal"));
		By by;
//		by = By.xpath("//div[@class='netd-filter-form']/input[@name='SEARCH']");
//		Navigator.waitForAjax(driver, by);	
//		WebDriverUtils.clickButton(driver, by);
		//1. Open the appraisal        
		String user = this.getAppraisee();
		String appraisalID = this.getAppraisalID();
		String expectedResult = "Completed";
		
		//Changed to meet the UI of Ver11.1
		
		user.toUpperCase();
		
		//set Appraisal Template as appraisalID;
		by = By.id("TEMPLATEID");
		Navigator.waitForElementLoad(driver, by);
		WebDriverUtils.select_selector(driver, by, appraisalID);
		
		//set Status Category as expectedResult;
		by = By.id("STATUS");
		Navigator.waitForElementLoad(driver, by);
		WebDriverUtils.select_selector(driver, by, expectedResult);
		
		//set Employee Last Name as user
		by = By.id("LASTN");
		Navigator.waitForElementLoad(driver, by);
		WebDriverUtils.fillin_textbox(driver, by, user);
		
		//CLICK SEARCH
		by = By.xpath("//div[@class='netd-filter-form']/input[@name='SEARCH']");
		Navigator.waitForAjax(driver, by);	
		WebDriverUtils.clickButton(driver, by);
		
		by = By.xpath("//button[@type='button']");
		Navigator.waitForAjax(driver, by);	
		WebDriverUtils.clickLink(driver, by);
		
		by = By.xpath("//a[text()='View']");
		Navigator.waitForAjax(driver, by);	
		WebDriverUtils.clickLink(driver, by);
		
		//2. Switch to pop-up window
		WebDriverUtils.switchToPopUpWin(driver);
		by = By.linkText("3. Sign Off");
		Navigator.waitForAjax(driver, by);	
		WebDriverUtils.clickLink(driver, by);
		
		//3. Checkout results
		by = By.xpath("//div[descendant::h4[text()='Rating Summary (Average)']]/table[@class='netd-table']"
				+ "/tbody/tr/td/b[contains(text(),'Overall Rating')]");
		Navigator.waitForAjax(driver, by);	
		String text = WebDriverUtils.getText(driver, by);//5.0
		JUnitAssert.assertTrue(text.contains(this.getRS_Avg()), "Not find Rating Summary (Average) = " + this.getRS_Avg());
		
		by = By.xpath("//div[descendant::h4[text()='Rating Summary (Weighted)']]/table[@class='netd-table']"
				+ "/tbody/tr/td/b[contains(text(),'Overall Rating')]");
		Navigator.waitForAjax(driver, by);	
		text = WebDriverUtils.getText(driver, by);//4.0
		JUnitAssert.assertTrue(text.contains(this.getRS_Weighted()), "Not find Rating Summary (Weighted) = " + this.getRS_Weighted());
		
		
		by = By.xpath("//div[descendant::h4[text()='Rating Summary (Percentage)']]/table[@class='netd-table']"
				+ "/tbody/tr/td/b[contains(text(),'Overall Rating')]");
		Navigator.waitForAjax(driver, by);	
		text = WebDriverUtils.getText(driver, by);//8.0(105%)	
		JUnitAssert.assertTrue(text.contains(this.getRS_Percent()), "Not find Rating Summary (Percentage) = " + this.getRS_Percent());
		
		WebDriverUtils.closeAllPopUpWins(driver);
	}
	
	
	public void runCheck9BoxReport(WebDriver driver){
		//1.submit the appraisal
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
				"LearningCenter", "TeamAppraisal"));

		this.submitAppraisal_DA(driver, this.getAppraisalID());

		//2.Check 9-box Promotion Potential Report
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
				"LearningCenter", "OrgUserReview"));
 
		/*By by = By.xpath("//a[descendant::span[text()='Org/User Review']]");
		WebDriverUtils.clickLink(driver, by);
		*/
		
		//3. Setup org.
		By by = By.linkText("Organization");
		Navigator.waitForAjax(driver, by);	
		WebDriverUtils.clickLink(driver, by);
		WebDriverUtils.switchToPopUpWin(driver);
		WebDriverUtils.checkSelect_Radio(driver, this.getOrganization());
		WebDriverUtils.switchToParentWin(driver);
		
		//by = By.name("apply-filters");
		by = By.xpath("//input[@type='SUBMIT' and @value='Filter']");
		Navigator.waitForAjax(driver, by);	
		WebDriverUtils.clickButton(driver, by);
		
		//4. check all users
		by = By.xpath("//div[@class='grid_16 user-list-nav']/div/input[@group-name='USER']");
		Navigator.waitForAjax(driver, by);	
		WebDriverUtils.check_checkbox(driver, by);
		
		//5. deploy 9-box promotion potential report
		by = By.id("reviewActionDropdown");
		//Navigator.explicitWait(1000);
		Navigator.waitForAjax(driver, by);	
		WebDriverUtils.select_selector(driver, by, Labels.Promt_Potential_Report.toString());
		
		
		by = By.xpath("//div[descendant::p[contains(text(),'"+this.getAppraisee()+"')]]/p[2]/a");
		//Navigator.explicitWait(1000);
		Navigator.waitForAjax(driver, by);
		WebDriverUtils.mouseOver(driver, by);
		
		by = By.xpath("//div[@id='tooltip']/span/div[@class='appraisal-result']/p[@class='performance-score']");
//		Navigator.explicitWait();
		Navigator.waitForAjax(driver, by);		
		String performScore = WebDriverUtils.getText(driver, by);//80.77
		JUnitAssert.assertTrue(performScore.contains(this.getPerformScore()),"Performance score !=" + this.getPerformScore());
		
		by = By.xpath("//div[@id='tooltip']/span/div[@class='appraisal-result']/p[@class='potential-scroe']");
//		Navigator.explicitWait();
		Navigator.waitForAjax(driver, by);		
		String potentialScore = WebDriverUtils.getText(driver, by);//75
		JUnitAssert.assertTrue(potentialScore.contains(this.getPPScore()),"Potential score !=" + this.getPPScore());
		
		//Check 9-box Retention Risk Report
		by = By.name("buttonInput");
//		Navigator.explicitWait();
		Navigator.waitForAjax(driver, by);		
		WebDriverUtils.clickButton(driver, by);
		
		by = By.linkText("Organization");
//		Navigator.explicitWait();
		Navigator.waitForAjax(driver, by);		
		WebDriverUtils.clickLink(driver, by);
		WebDriverUtils.switchToPopUpWin(driver);
		WebDriverUtils.checkSelect_Radio(driver, this.getOrganization());
		WebDriverUtils.switchToParentWin(driver);
		
		//by = By.name("apply-filters");
		by = By.xpath("//input[@type='SUBMIT' and @value='Filter']");
		WebDriverUtils.clickButton(driver, by);
	
		by = By.xpath("//div[@class='grid_16 user-list-nav']/div/input[@group-name='USER']");
		WebDriverUtils.check_checkbox(driver, by);
		
		by = By.id("reviewActionDropdown");
		WebDriverUtils.select_selector(driver, by, Labels.Retent_Risk_Report.toString());
		
		
		by = By.xpath("//div[descendant::p[contains(text(),'"+this.getAppraisee()+"')]]/p[2]/a");
//		Navigator.explicitWait();
		Navigator.waitForAjax(driver, by);		
		WebDriverUtils.mouseOver(driver, by);
		
		by = By.xpath("//div[@id='tooltip']/span/div[@class='appraisal-result']/p[@class='potential-scroe']");
//		Navigator.explicitWait();
		Navigator.waitForAjax(driver, by);		
		String leaveRiskScore = WebDriverUtils.getText(driver, by);//100
		JUnitAssert.assertTrue(leaveRiskScore.contains(this.getRRScore()),"Leave risk score!=" + this.getRRScore());
	}
	
	public void runCheckStatus(WebDriver driver) {
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
				"LearningCenter", "MyAppraisal"));
		this.startAppraisal(driver);
		
		//check status1
		//Navigator.explicitWait(1000);
		By by = By.xpath("//tr[descendant::td[2]/a[text()='" + this.getAppraisalID() + "']][1]/td[3]");
		Navigator.waitForAjax(driver, by);	
		String status = WebDriverUtils.getText(driver, by);
		String expectedStatus = this.loadExpectedResult("Status1");
		JUnitAssert.assertEquals(expectedStatus, status);
		
		
		
		this.submitAppraisal(driver, false);
		//check status2
		//Navigator.explicitWait(1000);
		by = By.xpath("//tr[descendant::td[2]/a[text()='" + this.getAppraisalID() + "']][1]/td[3]");
		
		Navigator.waitForAjax(driver, by);	
		status = WebDriverUtils.getText(driver, by);
		expectedStatus = this.loadExpectedResult("Status2");
		JUnitAssert.assertEquals(expectedStatus, status);
		
		//3. DA completes appraisal
		User user = new User(this.getDirectAppraisal(),Config.getInstance().getProperty("user.default.pass"));
		TestDriver.switchUser(user);
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
				"LearningCenter", "TeamAppraisal"));
		this.completeAppraisal_DA(driver, true);
		
		//check status3
		//Navigator.explicitWait(1000);
		expectedStatus = this.loadExpectedResult("Status3");
		
		by = By.id("STATUS");
		Navigator.waitForAjax(driver, by);	
		WebDriverUtils.select_selector(driver, by, expectedStatus);;
		
		by = By.xpath("//div[@class='netd-filter-form']/input[@name='SEARCH']");
		Navigator.waitForAjax(driver, by);	
		WebDriverUtils.clickButton(driver, by);
		
		by = By.xpath("//tr[descendant::td[3][contains(text(),'("+this.getAppraisee().toUpperCase()+")')] and ./td[4][text()='"+this.getAppraisalID()+"'] and ./td[8][not(text()='-')]][1]/td[9]");
		Navigator.waitForAjax(driver, by);	
		status = WebDriverUtils.getText(driver, by);
		JUnitAssert.assertEquals(expectedStatus, status);
	}
	

	/**
	 * 
	 * @param criteria
	 */
	private String loadExpectedResult(String criteria) {
		if(criteria_status == null){
			//criteria_status = UIFunctionUtils.parseParticipants(this.getExpectedResult());	
			criteria_status = CriteriaParser.parseKeyValueList(":", null, this.getExpectedResult());
		}
		
		String result = "";
		if(criteria_status.containsKey(criteria)){
			result = criteria_status.get(criteria).get(0); // uma_feng = Started;
		}
		return result;
	}

	private void checkSaveMsg_UI(WebDriver driver) {
		By by = By.xpath("//ul[@class='success messagebar']/li");
		Navigator.waitForAjax(driver, by);
		String msg = WebDriverUtils.getText(driver, by);
		JUnitAssert.assertEquals(Labels.Appraisal_Saved_Msg.toString(), msg);
	}
}