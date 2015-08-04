package com.netdimen.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.netdimen.config.Labels;
import com.netdimen.junit.JUnitAssert;
import com.netdimen.utils.WebDriverUtils;
import com.netdimen.view.Navigator;

/**
 * 
 * @author martin.wang
 *
 */
public class PerformanceGoal extends com.netdimen.model.Goal {
	private String LinkedOrgGoal = "", LinkedGoalProgram = "";

	public String getLinkedGoalProgram() {
		return LinkedGoalProgram;
	}

	public void setLinkedGoalProgram(String linkedGoalProgram) {
		LinkedGoalProgram = linkedGoalProgram;
	}

	public PerformanceGoal() {
		super();
	}

	public String getLinkedOrgGoal() {
		return LinkedOrgGoal;
	}

	public void setLinkedOrgGoal(String linkedorggoal) {
		LinkedOrgGoal = linkedorggoal;
	}

	public void setLinkedOrgGoal_UI(WebDriver driver, String goalProgram,
			String str) {
		if (!goalProgram.equals("") && !str.equals("")) {
			// 1. Click link goal button
			By by = By.id("linkGoalBtn");
			Navigator.waitForAjax(driver, by);
			WebDriverUtils.clickButton(driver, by);

			// 2. select goal program
			by = By.id("goalProgramSelect");
			Navigator.waitForAjax(driver, by);
			WebDriverUtils.select_selector(driver, by, goalProgram);

			//Navigator.explicitWait(1000);

			// 3. expand org goal path
			OrgGoal ins = new OrgGoal();
			String xpath_prefix = ins.setOrgGoalPath_UI(driver, str)
					+ "/div/div/button";
			Navigator.waitForAjax(driver, by);
			WebDriverUtils.mouseOver(driver, By.xpath(xpath_prefix));

			//Navigator.explicitWait(1000);

			// 4. select org goal
			by = By.linkText("Link Goal");
			Navigator.waitForAjax(driver, by);
			WebDriverUtils.clickLink(driver, by);
		}
	}

	/**
	 * Can delete the goal if the user is the creator and the goal is not
	 * included in the appraisal
	 * 
	 * 
	 * @param driver
	 */
	public void runCheckCanDelete(WebDriver driver) {
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
				"LearningCenter", "MyPerformanceGoals"));
		super.checkCanDelete(driver);
	}

	/**
	 * Cannot delete the goal if the user is NOT the creator or the goal is
	 * included in the appraisal
	 * 
	 * @param driver
	 */
	public void runCheckCannotDelete(WebDriver driver) {
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
				"LearningCenter", "MyPerformanceGoals"));
		super.checkCannotDelete(driver);
	}
	
	public void runDelete(WebDriver driver){
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
				"LearningCenter", "MyPerformanceGoals"));
		
		By by = By.linkText(this.getGoalTitle());
		Navigator.waitForAjax(driver, by);
		WebDriverUtils.clickLink(driver, by);
		
		by = By.xpath("//span[text()='Delete']");
		//Navigator.explicitWait(1000);
		Navigator.waitForAjax(driver, by);
		WebDriverUtils.clickButton(driver, by);
		
		by = By.xpath("//button[descendant::span[text()='OK']]");
		Navigator.waitForAjax(driver, by);
		WebDriverUtils.clickButton(driver, by);
		
		//check goal has been deleted
		/*by = By.xpath("//a[descendant::span[text()='My Performance Goals']]");
		Navigator.waitForAjaxElementLoad(driver, by);
		WebDriverUtils.clickButton(driver, by);*/
		
		by = By.linkText(this.getGoalTitle());
		int size = WebDriverUtils.getHowManyByPresntInPage(driver,by,false);
		JUnitAssert.assertTrue(size==0, "Can find goal:" + this.getGoalTitle());
	}


	public void runCreate(WebDriver driver) {
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
				"LearningCenter", "MyPerformanceGoals"));

		By by = By
				.xpath("//button[descendant::span[text()='"+Labels.Btn_Perf_Goal+"']]");
		Navigator.waitForAjax(driver, by);
		WebDriverUtils.clickButton(driver, by);
		this.runCreateWithoutNavigation(driver, false);
		
		//check goal has been created
		by = By.linkText(this.getGoalTitle());
		int size=WebDriverUtils.getHowManyByPresntInPage(driver,by,true);
		JUnitAssert.assertTrue(size==1, "Cannot find goal:" + this.getGoalTitle());
	}
	
	
	/**Check goal hierarchy with image comparison techniques
	 * 
	 * @param driver
	 */
	public void runCheckGoalHierarchy(WebDriver driver){
		runCheckGoalExisted(driver);
		
		//3. Open goal hierarchy
		By by = By.linkText("Goal Hierarchy");
		Navigator.waitForAjax(driver, by);
		//Navigator.explicitWait(1000);
		WebDriverUtils.clickLink(driver, by);
		
		//4. Check goal hierarchy
		super.checkGoalHierarchy(driver);
	}

	/**Check goal hierarchy with image comparison techniques
	 * 
	 * @param driver
	 */
	public void runCheckGoalExisted(WebDriver driver){
		//1. Goto My Performance Goal
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
				"LearningCenter", "MyPerformanceGoals"));
		//2. Mouse-over the goal
		String row_goal = "//tr[descendant::td/a[text()='" + this.getGoalTitle() + "']]";
		By by = By.xpath(row_goal + "/td[2]/div/div/button");
		//Navigator.explicitWait(1000);
		Navigator.waitForAjax(driver, by);
		WebDriverUtils.mouseOver(driver, by);
				
	}
	
	/**
	 * This method does not navigate to goal creation page. It's designed for
	 * method sharing, e.g., CDC class may call this method without navigation.
	 * 
	 * @param driver
	 * @param AppraiserAssign- True for DA created; False for User self created 
	 */
	
	public void runCreateWithoutNavigation(WebDriver driver, boolean AppraiserAssign) {
		super.runCreateWithoutNavigation(driver, false);
		this.setLinkedOrgGoal_UI(driver, this.getLinkedGoalProgram(),
				this.getLinkedOrgGoal());
		By by = By.id("saveButton");
		Navigator.waitForAjax(driver, by);
		WebDriverUtils.clickButton(driver, by);
		if (AppraiserAssign){
			//confirm send notification
			final By by_final = By.xpath("//button[descendant::span[text()='OK']]");
			WebDriverUtils.clickButton(driver, by_final);
		}
	}


	/**
	 * 
	 * @param driver
	 */
	public void runUnlinkGoal(WebDriver driver) {
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
				"LearningCenter", "MyGoals"));

		By by = By.xpath("//a[descendant::span[text()='"+Labels.Link_My_Perf_Goal+"']]");
		Navigator.waitForAjax(driver, by);
		WebDriverUtils.clickButton(driver, by);
		by = By.linkText(this.getGoalTitle());
		Navigator.waitForAjax(driver, by);
		WebDriverUtils.clickLink(driver, by);

		by = By.xpath("//span[text()='Unlink Goal']");
		Navigator.waitForAjax(driver, by);
		WebDriverUtils.clickButton(driver, by);
		
		by = By.xpath("//span[text()='Save']");
		Navigator.waitForAjax(driver, by);
		WebDriverUtils.clickButton(driver, by);
	}
	
	

}