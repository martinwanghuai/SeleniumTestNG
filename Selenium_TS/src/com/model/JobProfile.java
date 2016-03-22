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
import com.junit.JUnitAssert;
import com.utils.CriteriaParser;
import com.utils.WebDriverUtils;
import com.view.FunctionUI;
import com.view.Navigator;

/**
 * 
 * @author martin.wang
 *
 */
public class JobProfile extends TestObject {
	private String	Catalog = "",	Code="",	Title="",	Desc="",	isActive="", Participants="", Competencies = "";
	
	private ArrayList<Competency> competencyList = new ArrayList<Competency>();
	

	public ArrayList<Competency> getCompetencyList(){
		return this.competencyList;
	}
	
	public void addCompetency(Competency ins){
		this.competencyList.add(ins);
	}
	
	public String getCompetencies() {
		return Competencies;
	}

	public void setCompetencies(String competencies) {
		Competencies = competencies;
	}

	public String getParticipants() {
		return Participants;
	}

	public void setParticipants(String participants) {
		Participants = participants;
	}

	public void setCatalog_UI(WebDriver driver, String str){
		if(!str.equalsIgnoreCase("")){
			By by = By.linkText(str);
			WebDriverUtils.clickLink(driver, by);
		}
	}

	public void setCode_UI(WebDriver driver, String str){
		if(!str.equalsIgnoreCase("")){
			By by = By.id("referenceCode");
			WebDriverUtils.fillin_textbox(driver, by, str);
		}
	}

	public void setTitle_UI(WebDriver driver, String str){
		if(!str.equalsIgnoreCase("")){
			By by = By.id("name");
			WebDriverUtils.fillin_textbox(driver, by, str);
		}
	}

	public void setDesc_UI(WebDriver driver, String str){
		if(!str.equalsIgnoreCase("")){
			By by = By.id("description");
			WebDriverUtils.fillin_textbox(driver, by, str);
		}
	}

	public void setActive_UI(WebDriver driver, String str){
		if(str.equalsIgnoreCase("yes")){
			By by = By.id("isActive");
			WebDriverUtils.check_checkbox(driver, by);
		}
	}

	public void setProficiencyLevel_UI(WebDriver driver, String str){
		if(!str.equalsIgnoreCase("")){
			By by = By.id("levelGroup");
			WebDriverUtils.select_selector(driver, by, str);
		}
	}

	
	public void runCreate(WebDriver driver){
		Navigator.navigate(driver,Navigator.xmlWebElmtMgr.getNavigationPathList("ManageCenter","JobProfile"));

		this.setCatalog_UI(driver, this.getCatalog());
		By by = By.id("addJobProfileBtn");
		WebDriverUtils.clickButton(driver, by);
		
		this.setCode_UI(driver, this.getCode());
		this.setTitle_UI(driver, this.getTitle());
		this.setDesc_UI(driver, this.getDesc());
		this.setActive_UI(driver, this.getisActive());
		
		by = By.id("saveButton");
		WebDriverUtils.clickButton(driver, by);
	}
	
	public void runAutoEnroll(WebDriver driver){
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList("ManageCenter", "JobProfile"));
		this.setCatalog_UI(driver, this.getCatalog());
		
		//1. expand job profile
		By by = By.linkText(this.getCode());
//		Navigator.waitForAjaxElementLoad(driver, by);
		Navigator.explicitWait();
		WebDriverUtils.clickLink(driver, by);
		
		//2. click "Auto-assign"
		by = By.xpath("//a[descendant::span[text()='Auto-assign']]");
//		Navigator.waitForAjaxElementLoad(driver, by);
		Navigator.explicitWait();
		WebDriverUtils.clickLink(driver, by);
		
		//3. auto-enroll users based on org.attributes
		by = By.xpath("//div[descendant::span/h3[text()='"
				+ Labels.Link_Org_Attrs
				+ "'] and @class='action-nav status-header']");
		FunctionUI.setParticipants_UI(driver, this.getParticipants(),by);
		
		by = By.xpath("//button[@title='Set Auto-Enroll Targets']");
		if (WebDriverUtils.isElementPresent(by)) {
			WebDriverUtils.clickButton(driver, by);
		} else {
			by = By.xpath("//button[@title='Set Auto-Assign Targets']");
			WebDriverUtils.clickButton(driver, by);
		}
		String result=WebDriverUtils.closeAlertAndGetItsText();
		JUnitAssert.assertTrue(result.equalsIgnoreCase(Labels.Msg_Auto_Sure.toString()), "Cannot find assertion msg:"+Labels.Msg_Auto_Sure);
		
		Navigator.explicitWait(2000);
		//4 check auto-enroll results
		JUnitAssert.assertTrue(WebDriverUtils.textPresentInPage(driver, Labels.Msg_JobProfile_Assigned.toString()), "Cannot find text in page:" + Labels.Msg_JobProfile_Assigned);
		
		WebDriverUtils.closeAllPopUpWins(driver);		
		this.checkExpectedResult_AutoEnroll_UI(driver, 
				CriteriaParser.parseKeyValueList(":", ";", this.getExpectedResult()));
	}

	private void checkEnrollment_UI(WebDriver driver, ArrayList<String> users){		
		for(String UID: users){			
			this.checkEnrollment_UI(driver, UID);
		}		
	}
	
	private void checkEnrollment_UI(WebDriver driver, String UID){
		User user = new User(UID, Config.getInstance().getProperty("user.default.pass"));
		TestDriver.switchUser(user);	
		
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList("LearningCenter","JobProfile"),this);
		
		boolean flag =WebDriverUtils.refreshingAndCheckTextPresentedInPage(driver, this.getCode());
		JUnitAssert.assertTrue(flag, "User:"+ user.getUserID()+" NOT enroll into course:" + this.getCode());
	}
	
	private void checkUnEnrollment_UI(WebDriver driver, String UID){
		User user = new User(UID, Config.getInstance().getProperty("user.default.pass"));
		TestDriver.switchUser(user);	
		
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList("LearningCenter","JobProfile"),this);
		Navigator.explicitWait(1000);
		JUnitAssert.assertTrue(!WebDriverUtils.textPresentInPage(driver, this.getCode()), "User:"+ user.getUserID()+" enrolls into course:" + this.getCode());
	}
	
	private void checkUnEnrollment_UI(WebDriver driver, ArrayList<String> users){
		for(String UID: users){
			this.checkUnEnrollment_UI(driver, UID);
		}
	}
	
	private void checkExpectedResult_AutoEnroll_UI(WebDriver driver, HashMap<String, ArrayList<String>> criteria_users){
		//2. login user to checkout auto-enroll results
		Iterator<String> criteria_ite = criteria_users.keySet().iterator();
		while(criteria_ite.hasNext()){
			String criteria_ins = criteria_ite.next();
			ArrayList<String> users_ins = criteria_users.get(criteria_ins);
			
			switch(criteria_ins){
			case "enrollment":
				this.checkEnrollment_UI(driver, users_ins);
				break;
			case "non-enrollment":
				this.checkUnEnrollment_UI(driver, users_ins);
				break;
			}
		}		
	}
	
/*	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return new StringBuilder().append(this.getClass().getName()).
				append("_").
				append(this.getFuncType()).
				append("_").
				append(this.getCode()).
				toString();
	}*/

	@Override
	public boolean equals(TestObject obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void checkExpectedResult_UI(WebDriver driver, String expectedResult) {
		// TODO Auto-generated method stub
		super.checkExpectedResult_UI(driver, expectedResult);
	}

	public String getCatalog() {
		return Catalog;
	}

	public void setCatalog(String catalog) {
		Catalog = catalog;
	}

	public String getCode() {
		return Code;
	}

	public void setCode(String code) {
		Code = code;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getDesc() {
		return Desc;
	}

	public void setDesc(String desc) {
		Desc = desc;
	}

	public String getisActive() {
		return isActive;
	}

	public void setisActive(String isActive) {
		this.isActive = isActive;
	}

}
