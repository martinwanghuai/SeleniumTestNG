package com.netdimen.model;


import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.netdimen.abstractclasses.TestObject;
import com.netdimen.config.Labels;
import com.netdimen.junit.JUnitAssert;
import com.netdimen.utils.SikuliUtils;
import com.netdimen.utils.WebDriverUtils;
import com.netdimen.view.FunctionUI;
import com.netdimen.view.Navigator;

/**
 * 
 * @author martin.wang
 *
 */
public class Goal extends com.netdimen.abstractclasses.TestObject {
	private String GoalTitle = "", GoalTemplate = "", Weight = "", Desc = "",
			Milestone = "", ProgramName = "", ProgramDesc = "",
			StartDate = "", TargetDate = "", CloseDate = "", RevieweeID = "", RevieweeName="", GoalHierarchyFile = ""; 
	
	public String getGoalHierarchyFile() {
		return GoalHierarchyFile;
	}

	public void setGoalHierarchyFile(String goalHierarchyFile) {
		GoalHierarchyFile = goalHierarchyFile;
	}

	public String getRevieweeID() {
		return RevieweeID;
	}

	public String getRevieweeName() {
		return RevieweeName;
	}

	public void setRevieweeID(String revieweeID) {
		RevieweeID = revieweeID;
	}

	public void setRevieweeName(String revieweeName) {
		RevieweeName = revieweeName;
	}

	public boolean equals(com.netdimen.abstractclasses.TestObject para0) {
		boolean result = false;
		return result;
	}

	public Goal() {
		super();
	}

	public String getGoalTitle() {
		return GoalTitle;
	}

	public String getGoalTemplate() {
		return GoalTemplate;
	}

	public String getWeight() {
		return Weight;
	}

	public String getDesc() {
		return Desc;
	}

	public String getMilestone() {
		return Milestone;
	}

	

	public String getProgramName() {
		return ProgramName;
	}

	public String getProgramDesc() {
		return ProgramDesc;
	}

	public String getStartDate() {
		return StartDate;
	}

	public String getTargetDate() {
		return TargetDate;
	}

	public String getCloseDate() {
		return CloseDate;
	}


	public void setGoalTitle(String goaltitle) {
		GoalTitle = goaltitle;
	}

	public void setGoalTemplate(String goaltemplate) {
		GoalTemplate = goaltemplate;
	}

	public void setWeight(String weight) {
		Weight = weight;
	}

	public void setDesc(String desc) {
		Desc = desc;
	}

	public void setMilestone(String milestone) {
		Milestone = milestone;
	}

	
	public void setProgramName(String programname) {
		ProgramName = programname;
	}

	public void setProgramDesc(String programdesc) {
		ProgramDesc = programdesc;
	}

	public void setStartDate(String startDate) {
		StartDate =startDate;
	}

	public void setTargetDate(String targetDate) {
		TargetDate =targetDate;
	}

	public void setCloseDate(String closedate) {
		CloseDate = closedate;
	}

	public void setGoalTitle_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
			By by = By.id("title");
//			Navigator.waitForAjaxElementLoad(driver, by);
			//Navigator.explicitWait();
			Navigator.waitForAjax(driver, by);
			WebDriverUtils.fillin_textbox(driver, by, str);
		}
	}

	public void setGoalTemplate_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
			//1. Select template
			By by = By.id("template-name");
			WebDriverUtils.select_selector(driver, by, str);
			
			//2. confirm the selection
			by = By.xpath("//button[descendant::span[text()='OK']]");
			WebDriverUtils.clickButton(driver, by);
		}
	}

	public void setWeight_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
			By by = By.id("weight");
			WebDriverUtils.fillin_textbox(driver, by, str);
		}
	}

	public void setDesc_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
			By by = By.id("description");
			WebDriverUtils.fillin_textbox(driver, by, str);
		}
	}

	public void setMilestone_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
			By by = By.id("measurement");
			WebDriverUtils.fillin_textbox(driver, by, str);
		}
	}

	

	public void setProgramName_UI(WebDriver driver, String str) {
		if(!str.equalsIgnoreCase("")){
			By by = By.id("PROGRAM_NAME");
			WebDriverUtils.fillin_textbox(driver, by, str);
		}
	}

	public void setProgramDesc_UI(WebDriver driver, String str) {
		if(!str.equalsIgnoreCase("")){
			By by = By.id("PROGRAM_DESC");
			WebDriverUtils.fillin_textbox(driver, by, str);
		}
	}
/*
	public void setStartDate_UI(WebDriver driver, String str) {
		if(!str.equals("")){
			String xpath_calendar = "//div[descendant::div[@class='date-field-container']/input[@name='startdatedatebox']]/div[@class='date-button-container']/a";
			WebDriverUtils.dateSelect_Calandar(driver, str, xpath_calendar);
		}
	}

	public void setTargetDate_UI(WebDriver driver, String str) {
		if(!str.equals("")){
			String xpath_calendar = "//div[descendant::div[@class='date-field-container']/input[@name='targetdatedatebox']]/div[@class='date-button-container']/a";
			WebDriverUtils.dateSelect_Calandar(driver, str, xpath_calendar);
		}
	}

	public void setCloseDate_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
//			String xpath_calendar = "(//img[@alt='Calendar'])[3]";
			String xpath_calendar = "//div[descendant::div[@class='date-field-container']/input[@name='datecloseddatebox']]/div[@class='date-button-container']/a";
			WebDriverUtils.dateSelect_Calandar(driver, str, xpath_calendar);
			WebDriverUtils.switchToFrame(driver, "REPORT_MAIN_FRAME");
		}
	}
*/
	public void setType_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
		}
	}

	
	@Override
	public void checkExpectedResult_UI(WebDriver driver, String expectedResult) {
		// TODO Auto-generated method stub
		super.checkExpectedResult_UI(driver, expectedResult);
	}

	/**Create goal program
	 * 
	 * @param driver
	 */
	public void runCreateGoalProgram(WebDriver driver){
		//Navigator.navigate(driver, Navigator.URL.GoalManager);
		Navigator.navigate(driver,Navigator.xmlWebElmtMgr.getNavigationPathList("ManageCenter","GoalProg"),this);
		By by = By.id("addButton");
		WebDriverUtils.clickButton(driver, by);

		this.setProgramName_UI(driver, this.getProgramName());
		this.setProgramDesc_UI(driver, this.getProgramDesc());
		FunctionUI.setDates_UI(driver, this.getStartDate(),"startdatedatebox");	
		//this.setStartDate_UI(driver, this.getStartDate());
		FunctionUI.setDates_UI(driver, this.getTargetDate(),"targetdatedatebox");	
		//this.setTargetDate_UI(driver, this.getTargetDate());

		by = By.id("saveButton");
		WebDriverUtils.clickButton(driver, by);
	}
	
	
	/**
	 * This method does not navigate to goal creation page. It's designed for
	 * method sharing, e.g., CDC class may call this method without navigation.
	 * 
	 * @param driver
	 * @param AppraiserAssign- no used for this class, but used in sub class
	 */
	
	public void runCreateWithoutNavigation(WebDriver driver, boolean AppraiserAssign){
		this.setGoalTemplate_UI(driver, this.getGoalTemplate());
		this.setGoalTitle_UI(driver, this.getGoalTitle());
		this.setWeight_UI(driver, this.getWeight());
		this.setDesc_UI(driver, this.getDesc());
		this.setMilestone_UI(driver, this.getMilestone());
		this.setProgramName_UI(driver, this.getProgramName());
		this.setProgramDesc_UI(driver, this.getProgramDesc());
		FunctionUI.setDates_UI(driver, this.getStartDate(),"startdatedatebox");		
		FunctionUI.setDates_UI(driver, this.getTargetDate(),"targetdatedatebox");	
		FunctionUI.setDates_UI(driver, this.getCloseDate(),"datecloseddatebox");	

		
	}
	
	/**Can delete the goal if the user is the creator and the goal is not included in the appraisal
	 * 
	 * @param driver
	 */
	public void checkCanDelete(WebDriver driver){
		String row_goal = "//tr[descendant::td/a[text()='" + this.getGoalTitle() + "']]";
		By by = By.xpath(row_goal + "/td[2]/div/div/button");
		//Navigator.explicitWait();
		Navigator.waitForAjax(driver, by);
//		Navigator.waitForAjaxElementLoad(driver, by);
		WebDriverUtils.mouseOver(driver, by);
		
		by = By.linkText("Delete");
		//Navigator.explicitWait();
		Navigator.waitForAjax(driver, by);
//		Navigator.waitForAjaxElementLoad(driver, by);
		int size = WebDriverUtils.getHowManyByPresntInPage(driver,by,true);
		JUnitAssert.assertTrue(size > 0, "Cannot delete" + this.getGoalTitle());
		
		WebDriverUtils.clickLink(driver, by);
		String delMsg = Labels.Msg_Delete_Dev_Goal.toString();
		if(this instanceof PerformanceGoal){
			delMsg = Labels.Msg_Delete_Perf_Goal.toString();
		}
		by = By.xpath("//div[descendant::div[@id='jMessage' and text()='" + delMsg + "']]/div/div/button[descendant::span[text()='OK']]");
		WebDriverUtils.clickButton(driver, by);
	}

	/**Add goal progress tracks
	 * 
	 * @param driver
	 * @param objs: a set of GoalProgress
	 */
	public void runAddTrack(WebDriver driver, ArrayList<TestObject> objs){
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
				"LearningCenter", "MyGoals"));
		
		By by = By.xpath("//a[descendant::span[text()='"+Labels.Link_My_Perf_Goal+"']]");
		if(this instanceof DevelopmentGoal){
			by = By.xpath("//a[descendant::span[text()='"+Labels.Link_My_Dev_Goal+"']]");
		}
		//Navigator.explicitWait(1000);
		Navigator.waitForAjax(driver, by);
//		Navigator.waitForAjaxElementLoad(driver, by);
		WebDriverUtils.clickButton(driver, by);
		by = By.linkText(this.getGoalTitle());
		WebDriverUtils.clickLink(driver, by);
		
		by = By.id("open-progress-dialog");
		WebDriverUtils.clickLink(driver, by);
		
		for(TestObject obj: objs){
			((GoalProgress)obj).create(driver);	
		}
		
		by = By.xpath("//span[text()='Save']");
		WebDriverUtils.clickButton(driver, by);
		
	}
	
	/**Cannot delete the goal if the user is NOT the creator or the goal is included in the appraisal
	 * 
	 * @param driver
	 */
	public void checkCannotDelete(WebDriver driver){
		String row_goal = "//tr[descendant::td/a[text()='" + this.getGoalTitle() + "']]";
		By by = By.xpath(row_goal + "/td[2]/div/div/button");
		//Navigator.explicitWait();
		Navigator.waitForAjax(driver, by);
//		Navigator.waitForAjaxElementLoad(driver, by);
		WebDriverUtils.mouseOver(driver, by);
		
		
		by = By.linkText("Delete");
		int size = WebDriverUtils.getHowManyByPresntInPage(driver,by,false);
		JUnitAssert.assertTrue(size == 0, "Can delete " + this.getGoalTitle());
	}

	/**Check visibility of goal templates
	 * 
	 * @param driver
	 */
	public void runCheckGoalTemplateVisibility(WebDriver driver){
		String[] visibilities = this.getExpectedResult().split("\n");
		for(String visibility: visibilities){
			String[] str_tmp = visibility.split(":");
			String criteria = str_tmp[0];
			
			String[] goals = str_tmp[1].split(";");
			By by = null;
			//1. Create goals
			if(criteria.contains("PG")){
				//check performance goal template
				Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
						"LearningCenter", "BtnCreatePerfGoal"));

//				by = By.xpath("//button[descendant::span[text()='"+Labels.Btn_Perf_Goal+"']]");
////				Navigator.waitForAjaxElementLoad(driver, by);
//				//Navigator.explicitWait(1000);
//				Navigator.waitForAjax(driver, by);
//				WebDriverUtils.clickButton(driver, by);
				
				
			}else if(criteria.contains("DG")){
				//check development goal template
				Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
						"LearningCenter", "BtnCreateDevGoal"));
//				by = By.xpath("//button[descendant::span[text()='"+Labels.Btn_Dev_Goal+"']]");
////				Navigator.waitForAjaxElementLoad(driver, by);
//				//Navigator.explicitWait(1000);
//				Navigator.waitForAjax(driver, by);
//				WebDriverUtils.clickButton(driver, by);
			}

			//2.check goal template visibility
			for(String goal: goals){
				//1. Select template
				by = By.xpath("//select[@id='template-name']/option[text()='" + goal + "']");
				//int size = WebDriverUtils.getHowManyByPresntInPage(by);
				if(criteria.contains("invisible")){
					JUnitAssert.assertTrue(WebDriverUtils.getHowManyByPresntInPage(driver,by,false)== 0, "Goal is visible:" + goal);
				}else if(criteria.contains("visible")){
					JUnitAssert.assertTrue(WebDriverUtils.getHowManyByPresntInPage(driver,by,true)> 0,"Goal is invisible:" + goal);
				}	
			}
			
			//3. Cancel 
			by = By.xpath("//span[text()='"+Labels.Btn_Cancel+"']");
			WebDriverUtils.clickButton(driver, by);
		}
	}
	
	/**Use image comparison techniques to check goal hierarchy
	 * 
	 * @param driver
	 */
	public void checkGoalHierarchy(WebDriver driver){
		//1. Sikuli: Image-based comparison
		String screenshotFile = this.getGoalHierarchyFile();
		boolean exist = SikuliUtils.screenshotExistInWin(screenshotFile);
		JUnitAssert.assertTrue(exist, "Cannot find image:" + screenshotFile);
	}
	/**
	 * 
	 * Empty method body since this is a test suite
	 * 
	 * @param driver
	 */
	public void runAutoTrackGoalProgress(WebDriver driver){
		
	}
	
	
	/**Goal in appraisal
		- PG/DG/OG with diff. target date (/Goal Program Period) should fall in diff. sections
		- All goals included in appraisal are NOT delete-able
	 * Empty method body since this is a test suite
	 * 
	 * @param driver
	 */
	public void runCheckGoalInAppraisal(WebDriver driver){
		
	}
	
	/**Confirm that performance goal/development goal cannot be deleted when
	 * a). the user is not the "creator"
	 * b). they are included in appraisals 
	 * 
	 * Empty method body since this is a test suite
	 * @param driver
	 */
	public void runCheckGoalLock(WebDriver driver){
	}
	
}