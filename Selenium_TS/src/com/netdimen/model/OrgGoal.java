package com.netdimen.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.netdimen.junit.JUnitAssert;
import com.netdimen.utils.WebDriverUtils;
import com.netdimen.view.Navigator;

/**
 * 
 * @author martin.wang
 *
 */
public class OrgGoal extends Goal {
	private String OrgGoalID = "", GoalProgram = "", GoalCategory = "",
			OrgGoalPath = "", Desc = "", Track ="",	Progress ="", Status = "";

	public String getTrack() {
		return Track;
	}

	public String getProgress() {
		return Progress;
	}

	public String getStatus() {
		return Status;
	}

	public void setTrack(String track) {
		Track = track;
	}

	public void setProgress(String progress) {
		Progress = progress;
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

	public boolean equals(com.netdimen.abstractclasses.TestObject para0) {
		boolean result = false;
		return result;
	}

	public OrgGoal() {
		super();
	}

	public String getOrgGoalID() {
		return OrgGoalID;
	}

	public String getGoalProgram() {
		return GoalProgram;
	}

	public String getGoalCategory() {
		return GoalCategory;
	}

	public String getOrgGoalPath() {
		return OrgGoalPath;
	}

	public void setOrgGoalID(String orggoalid) {
		OrgGoalID = orggoalid;
	}

	public void setGoalProgram(String goalprogram) {
		GoalProgram = goalprogram;
	}

	public void setGoalCategory(String goalcategory) {
		GoalCategory = goalcategory;
	}

	public void setOrgGoalPath(String orggoalpath) {
		OrgGoalPath = orggoalpath;
	}

	public void setOrgGoalID_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
			By by = By.id("TITLE");
			WebDriverUtils.fillin_textbox(driver, by, str);
		}
	}

	public void getGoalProgram_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
			By by =  By.xpath("//li[span='" + str + "']/span");
//			Navigator.waitForAjaxElementLoad(driver, by);
			Navigator.explicitWait(1000);
			WebDriverUtils.clickLink(driver, by);
		}
	}
	
	public void setGoalProgram_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
			By by = By.id("GOAL_CAT_ID");
//			Navigator.waitForAjaxElementLoad(driver, by);
			Navigator.explicitWait(1000);
			WebDriverUtils.select_selector(driver, by, str);
		}
	}

	public void getGoalCategory_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
			By by = By.id("CategoryFilter");
//			Navigator.waitForAjaxElementLoad(driver, by);
			Navigator.explicitWait(1000);
			WebDriverUtils.select_selector(driver, by, str);
		}
	}
	
	public void setGoalCategory_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
			By by = By.id("CategoryFilter");
//			Navigator.waitForAjaxElementLoad(driver, by);
			Navigator.explicitWait(1000);
			WebDriverUtils.select_selector(driver, by, str);
		}
	}
	
	
	public void setDesc_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
			By by = By.id("DESC");
			WebDriverUtils.fillin_textbox(driver, by, str);
		}
	}

	/**Expand the org goals based on goal path 
	 * 
	 * @param driver
	 * @param str: goal path (e.g., "OrgGoal2/asd/asd/asddas/")
	 * @return: the xpath corresponding to the row of the last org goal 
	 */
	public String setOrgGoalPath_UI(WebDriver driver, String str) {
		String xpath_prefix = "";
		if (!str.equals("")) {			
			String[] orgs = str.split("/");
			String xpath_parent = "";
			
			String org = orgs[0];
			//click button			
			xpath_prefix = "//li[descendant::div/span/span/a[contains(text(),'"+ org +"')]]";
			String xpath = xpath_prefix + "/div/span/span";
			By by = By.xpath(xpath);
			WebDriverUtils.clickLink(driver, by);
			
			
			//re-organize xpath			
			for(int i = 1; i < orgs.length; i ++){
				org = orgs[i];
				xpath_prefix += "/ul/li[descendant::div/span/span/a[contains(text(),'" + org + "')]]";
				xpath = xpath_prefix + "/div/span/span";
				//click button
				by = By.xpath(xpath);
				WebDriverUtils.clickButton(driver, by);
			}
		}
		
		return xpath_prefix;
	}

	/**Check goal hierarchy with image comparison techniques
	 * 
	 * @param driver
	 */         
	public void runCheckGoalHierarchy(WebDriver driver){
		//1. Goto My Performance Goal
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
				"LearningCenter", "MyOrgGoals"));
		
		//2. Mouse-over the goal
		String row_goal = "//tr[descendant::td/a[text()='" + this.getOrgGoalID() + "']]";
		By by = By.xpath(row_goal + "/td[1]/div/div/button");
		Navigator.explicitWait(1000);
//		Navigator.waitForAjaxElementLoad(driver, by);
		WebDriverUtils.mouseOver(driver, by);
		
		//3. Open goal hierarchy
		by = By.linkText("Goal Hierarchy");
//		Navigator.waitForAjaxElementLoad(driver, by);
		Navigator.explicitWait(1000);
		WebDriverUtils.clickLink(driver, by);
		
		//4. Check goal hierarchy
		super.checkGoalHierarchy(driver);
	}

	
	/**Expand parent org goal based on goal path and return its xpath
	 * 
	 * @param driver
	 * @return
	 */
	private String expandParentOrgGoal(WebDriver driver){
		return this.setOrgGoalPath_UI(driver, this.getOrgGoalPath());
	}
	
	public void runCreate(WebDriver driver) {
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
				"ManageCenter", "GoalPrograms"), this);
		
		Navigator.explicitWait(1000);
		
		this.getGoalProgram_UI(driver, this.getGoalProgram());		
		this.getGoalCategory_UI(driver, this.getGoalCategory());
		String xpath_prefix = this.expandParentOrgGoal(driver);
		By by = By.xpath(xpath_prefix + "/div/div/button");
		WebDriverUtils.mouseOver(driver, by);
		
		by = By.xpath(xpath_prefix + "/div/div/ul/li/a[text()='Add']");
		WebDriverUtils.clickLink(driver, by);
	    

		this.setOrgGoalID_UI(driver, this.getOrgGoalID());
		this.setGoalCategory_UI(driver, this.getGoalCategory());
		this.setDesc_UI(driver, this.getDesc());
		
		by = By.id("saveOrgGoalButton");
		WebDriverUtils.clickButton(driver, by);
	}
	
	public void runCheckProgress(WebDriver driver){
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
				"ManageCenter", "GoalPrograms"), this);
		
		Navigator.explicitWait(2000);
		
		this.getGoalProgram_UI(driver, this.getGoalProgram());		
		this.getGoalCategory_UI(driver, this.getGoalCategory());
		this.expandParentOrgGoal(driver);
		By by = By.linkText(this.getOrgGoalID());
		WebDriverUtils.clickLink(driver, by);
		
		Navigator.explicitWait(1000);
		
		by = By.xpath("//table[@id='trackingHistoryTable']/tbody/tr[1]/td[6]");
		String status = WebDriverUtils.getText(driver, by);
		String expectedStatus = this.getStatus();
		
		boolean matched = false;
		if(expectedStatus.contains(";")){
			String[] status_array = expectedStatus.split(";");
			
			for(String status_tmp: status_array){
				if(status_tmp.equalsIgnoreCase(status)){
					matched = true;
					break;
				}
			}
		}else{
			if(expectedStatus.equalsIgnoreCase(status)){
				matched = true;
			}
		}

		JUnitAssert.assertTrue(matched, "Expected status:" + expectedStatus + "; actual status:" + status);
		
		by = By.xpath("//table[@id='trackingHistoryTable']/tbody/tr[1]/td[5]");
		String actualProgress = WebDriverUtils.getText(driver, by);
		String expectedProgress = this.getProgress();
		JUnitAssert.assertEquals(expectedProgress, actualProgress);
	}
	
/*	public String toString(){
		return new StringBuilder().
				append(this.getClass().getName()).
				append("_").
				append(this.getFuncType()).
				append("_").
				append(this.getGoalProgram()).
				append("_").
				append(this.getGoalCategory()).
				append("_").
				append(this.getOrgGoalPath()).
				append("_").
				append(this.getUID()).
				append("_").
				append(this.getOrgGoalID()).
				toString();
	}*/

}