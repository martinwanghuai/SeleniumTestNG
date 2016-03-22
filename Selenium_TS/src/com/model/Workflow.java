package com.model;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.abstractclasses.TestObject;
import com.utils.WebDriverUtils;
import com.view.Navigator;
import com.view.SelectorsUI;

public class Workflow extends com.abstractclasses.TestObject {
	private String Exam = "", IsPreCourse = "", IsPostCourse = "",
			AvailableDate = "", Assessor = "", Reminder = "",
			ReminderSendDate = "", index = "";

	public String getIndex() {
		return index;
	}

	/**Martin: index starts from 1 not 0
	 * 
	 * @param index
	 */
	public void setIndex(String index) {
		this.index = index;
	}

	public boolean equals(com.abstractclasses.TestObject para0) {
		boolean result = false;
		return result;
	}

	public Workflow() {
		super();
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


	public void setIsPreCourse_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
			By by = By.xpath("(//input[@value='R'])["+this.getIndex()+"]");
			WebDriverUtils.checkRadio(driver, by);
		}
	}

	public void setIsPostCourse_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
			By by = By.xpath("(//input[@value='O'])["+this.getIndex()+"]");
			WebDriverUtils.checkRadio(driver, by);
		}
	}

	public void setAvailableDate_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
			//1. Set up date
			int index = str.indexOf("Days");
			String day = str.substring(0, index).trim();
			By by = By.xpath("(//input[@name='NO_DAYS'])["+ this.getIndex()+"]");
			WebDriverUtils.fillin_textbox(driver, by, day);
			
			//2. Set up before/after
			by = By.xpath("(//select[@id='IS_AFTER'])["+this.getIndex()+"]");
			if(str.contains("Before")){
				WebDriverUtils.select_selector(driver, by, "Before");
			}else{
				WebDriverUtils.select_selector(driver, by, "After");
			}
			
			//3. Setup course end date/course start date
			by = By.xpath("(//select[@name='IS_AT_COURSE_START'])["+this.getIndex()+"]");
			if(str.contains("start")){
				WebDriverUtils.select_selector(driver, by, "Course start date");
			}else{
				WebDriverUtils.select_selector(driver, by, "Course end date");
			}
			
		}
	}

	public void setAssessor_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
			By by = null;
			if(str.contains("Participant")){
				by = By.xpath("(//input[@name='ASSESSOR' and @value='P'])["+this.getIndex()+"]");
			}else if(str.contains("Direct Appraiser")){
				by = By.xpath("(//input[@name='ASSESSOR' and @value='A'])["+this.getIndex()+"]");
			}else if(str.contains("Instructor")){
				by = By.xpath("(//input[@name='ASSESSOR' and @value='I'])["+this.getIndex()+"]");
			}
			
			WebDriverUtils.checkRadio(driver, by);
		}
	}

	public void setReminder_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
			By by = By.xpath("(//button[@id='SELECT_BUTTON'])["+this.getIndex()+"]");
			WebDriverUtils.clickButton(driver, by);
			
			WebDriverUtils.switchToPopUpWin(driver);
			EMailTemplate template  = new EMailTemplate();
			template.selectEmail(driver, this.getReminder());
			WebDriverUtils.switchToParentWin(driver);
		}
	}

	public void setReminderSendDate_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
			//1. Set up date
			int index = str.indexOf("Days");
			String day = str.substring(0, index).trim();
			By by = By.xpath("(//input[@name='REMINDER_SEND_LIMIT'])["+ this.getIndex()+"]");
			WebDriverUtils.fillin_textbox(driver, by, day);
			
			//2. Set up frequency
			by = By.xpath("(//select[@id='FREQUENCY'])["+this.getIndex()+"]");
			if(str.contains("Daily")){
				WebDriverUtils.select_selector(driver, by, "Daily");
			}else if(str.contains("Weekly")){
				WebDriverUtils.select_selector(driver, by, "Weekly");
			}else if(str.contains("Monthly")){
				WebDriverUtils.select_selector(driver, by, "Monthly");
			}
		}
	}
	
	public void setExam_UI(WebDriver driver, String str){
		if(!str.equals("")){
			By by = By.linkText("+ Add exam/evaluation");
			WebDriverUtils.clickLink(driver, by);
			Navigator.explicitWait(1000);
			
			by = By.xpath("(//button[@id='EXAM_SELECT'])["+this.getIndex()+"]");
			WebDriverUtils.clickButton(driver, by);
			ArrayList<String> keywordArray = new ArrayList<String>();
			keywordArray.add(str);
			WebDriverUtils.switchToPopUpWin(driver);
			SelectorsUI.PopUp_Selector(driver, SelectorsUI.PopUpSelector.TopDownSelector, keywordArray);
			WebDriverUtils.switchToParentWin(driver);
		}
	}

	public void runCreate(WebDriver driver) {
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
				"ManageCenter", "Overview"));
		this.create(driver);
	}
	
	public void create(WebDriver driver){
		this.setExam_UI(driver, this.getExam());
		this.setIsPreCourse_UI(driver, this.getIsPreCourse());
		this.setIsPostCourse_UI(driver, this.getIsPostCourse());
		this.setAvailableDate_UI(driver, this.getAvailableDate());
		this.setAssessor_UI(driver, this.getAssessor());
		this.setReminder_UI(driver, this.getReminder());
		this.setReminderSendDate_UI(driver, this.getReminderSendDate());
	}

}