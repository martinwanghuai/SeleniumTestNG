package com.netdimen.model;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.netdimen.config.Labels;
import com.netdimen.junit.JUnitAssert;
import com.netdimen.utils.WebDriverUtils;
import com.netdimen.view.Navigator;
import com.netdimen.view.SelectorsUI;

/**
 * 
 * @author martin.wang
 *
 */
public class DevelopmentGoal extends com.netdimen.model.Goal {
	private String LinkedModules = "";

	public DevelopmentGoal() {
		super();
	}

	public String getLinkedModules() {
		return LinkedModules;
	}

	public void setLinkedModules(String linkedmodules) {
		LinkedModules = linkedmodules;
	}

	public void setLinkedModules_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
			//1. Click link goal button
			By by = By.id("linkLearningModuleButton");
			//Navigator.explicitWait();
			Navigator.waitForAjax(driver, by);
			WebDriverUtils.clickButton(driver, by);
			
			//2. select linked module
			ArrayList<String> links = new ArrayList<String>();
			links.add(getLinkedModules());
			WebDriverUtils.switchToPopUpWin(driver);
			SelectorsUI.PopUp_Selector(driver, SelectorsUI.PopUpSelector.TopDownSelector, links);
			WebDriverUtils.switchToParentWin(driver);
		}
	}

	/**Can delete the goal if the user is the creator and the goal is not included in the appraisal
	 * 
	 * @param driver
	 */
	public void runCheckCanDelete(WebDriver driver){
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
				"LearningCenter", "MyDevelopmentGoals"));
		super.checkCanDelete(driver);
	}
	
	/**Cannot delete the goal if the user is NOT the creator or the goal is included in the appraisal
	 * 
	 * @param driver
	 */
	public void runCheckCannotDelete(WebDriver driver){
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
				"LearningCenter", "MyDevelopmentGoals"));
		super.checkCannotDelete(driver);
	}
	
	public void runCreate(WebDriver driver) {
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
				"LearningCenter", "MyDevelopmentGoals"));
		
		By by = By.xpath("//button[descendant::span[text()='"+Labels.Btn_Dev_New+"']]");
		//Navigator.explicitWait(1000);
		Navigator.waitForAjax(driver, by);
		WebDriverUtils.clickButton(driver, by);
		
		this.runCreateWithoutNavigation(driver, false);
		
		//check goal has been created
		by = By.xpath("//a[descendant::span[text()='My Development Goals']]");
		//Navigator.explicitWait(1000);
		Navigator.waitForAjax(driver, by);
		WebDriverUtils.clickButton(driver, by);
		
		by = By.linkText(this.getGoalTitle());
		Navigator.waitForAjax(driver, by);
		JUnitAssert.assertTrue(WebDriverUtils.getHowManyByPresntInPage(driver,by, true)==1, "Cannot find goal:" + this.getGoalTitle());
	}
	
	public void runDelete(WebDriver driver){
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
				"LearningCenter", "MyDevelopmentGoals"));
		
		By by = By.linkText(this.getGoalTitle());
		Navigator.waitForAjax(driver, by);
		WebDriverUtils.clickLink(driver, by);
		
		by = By.xpath("//span[text()='Delete']");
		Navigator.waitForAjax(driver, by);
		WebDriverUtils.clickButton(driver, by);
		
		by = By.xpath("//button[descendant::span[text()='OK']]");
		Navigator.waitForAjax(driver, by);
		WebDriverUtils.clickButton(driver, by);
		
		//check goal has been deleted
		/*by = By.xpath("//a[descendant::span[text()='My Development Goals']]");
		Navigator.waitForAjaxElementLoad(driver, by);
		WebDriverUtils.clickButton(driver, by);*/
		
		by = By.linkText(this.getGoalTitle());
		JUnitAssert.assertTrue(WebDriverUtils.getHowManyByPresntInPage(driver,by,false)==0, "Find goal:" + this.getGoalTitle());
	}
	

	/**
	 * This method does not navigate to goal creation page. It's designed for
	 * method sharing, e.g., CDC class may call this method without navigation.
	 * 
	 * @param driver
	 * @param AppraiserAssign- True for DA created; False for User self created 
	 */
	
	public void runCreateWithoutNavigation(WebDriver driver, boolean AppraiserAssign ) {
		super.runCreateWithoutNavigation(driver, false);
		this.setLinkedModules_UI(driver, this.getLinkedModules());
		By by = By.id("saveButton");
		//Navigator.explicitWait(1000);
		Navigator.waitForAjax(driver, by);
		WebDriverUtils.clickButton(driver, by);
		if (AppraiserAssign){
			//confirm send notification
			final By by_final = By.xpath("//button[descendant::span[text()='OK']]");
			WebDriverUtils.clickButton(driver, by_final);
		}
	}
	
	/**Check goal hierarchy with image comparison techniques
	 * 
	 * @param driver
	 */
	public void runCheckGoalHierarchy(WebDriver driver){
		
		runCheckGoalExisted(driver);
		//3. Open goal hierarchy
		By by = By.linkText("Goal Hierarchy");
		//Navigator.explicitWait();
		Navigator.waitForAjax(driver, by);
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
				"LearningCenter", "MyDevelopmentGoals"));
		//2. Mouse-over the goal
		String row_goal = "//tr[descendant::td/a[text()='" + this.getGoalTitle() + "']]";
		By by = By.xpath(row_goal + "/td[2]/div/div/button");
		//Navigator.explicitWait(1000);
		Navigator.waitForAjax(driver, by);
		WebDriverUtils.mouseOver(driver, by);
				
	}

}