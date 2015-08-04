package com.netdimen.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.netdimen.config.Config;
import com.netdimen.config.Labels;
import com.netdimen.junit.JUnitAssert;
import com.netdimen.utils.WebDriverUtils;
import com.netdimen.view.FunctionUI;
import com.netdimen.view.Navigator;

/**
 * 
 * @author martin.wang
 *
 */
public class ExtRec extends com.netdimen.abstractclasses.TestObject {
	private String Title = "", Type = "", Subject = "", StartDate = "",
			EndDate = "", Venue = "", Language = "", Duration = "", Cost = "",
			Grade = "", Score = "", VendorInfo = "", Comment = "",
			Attachement = "", Status = "", RevieweeUID = "";

	public boolean equals(com.netdimen.abstractclasses.TestObject para0) {
		boolean result = false;
		return result;
	}

	public String getRevieweeUID() {
		return RevieweeUID;
	}

	public void setRevieweeUID(String revieweeUID) {
		RevieweeUID = revieweeUID;
	}

	public ExtRec() {
		super();
	}

	public String getTitle() {
		return Title;
	}

	public String getType() {
		return Type;
	}

	public String getSubject() {
		return Subject;
	}

	public String getStartDate() {
		return StartDate;
	}

	public String getEndDate() {
		return EndDate;
	}

	public String getVenue() {
		return Venue;
	}

	public String getLanguage() {
		return Language;
	}

	public String getDuration() {
		return Duration;
	}

	public String getCost() {
		return Cost;
	}

	public String getGrade() {
		return Grade;
	}

	public String getScore() {
		return Score;
	}

	public String getVendorInfo() {
		return VendorInfo;
	}

	public String getComment() {
		return Comment;
	}

	public String getAttachement() {
		return Attachement;
	}

	public String getStatus() {
		return Status;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public void setType(String type) {
		Type = type;
	}

	public void setSubject(String subject) {
		Subject = subject;
	}

	public void setStartDate(String startdate) {
		StartDate = startdate;
	}

	public void setEndDate(String enddate) {
		EndDate = enddate;
	}

	public void setVenue(String venue) {
		Venue = venue;
	}

	public void setLanguage(String language) {
		Language = language;
	}

	public void setDuration(String duration) {
		Duration = duration;
	}

	public void setCost(String cost) {
		Cost = cost;
	}

	public void setVendorInfo(String vendorInfo) {
		VendorInfo = vendorInfo;
	}

	public void setGrade(String grade) {
		Grade = grade;
	}

	public void setScore(String score) {
		Score = score;
	}

	public void setComment(String comment) {
		Comment = comment;
	}

	public void setAttachement(String attachement) {
		Attachement = attachement;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public void setTitle_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
		}
	}

	public void setType_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
		}
	}

	public void setSubject_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
		}
	}

	public void setStartDate_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
		}
	}

	public void setEndDate_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
		}
	}

	public void setVenue_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
		}
	}

	public void setLanguage_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
		}
	}

	public void setDuration_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
		}
	}

	public void setCost_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
		}
	}

	public void setGrade_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
		}
	}

	public void setScore_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
		}
	}

	public void setVendorInfo_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
		}
	}

	public void setComment_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
		}
	}

	public void setAttachement_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
		}
	}

	public void setStatus_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
		}
	}

	public void runCreate(WebDriver driver) {
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
				"LearningCenter", "ExternalTrainingRecords"));
		By by = By.name("create-ext-rec");
		WebDriverUtils.clickButton(driver, by);
		this.UpdateExtRec_UI(driver, "CREATE");

	}

	public void runReviewerUpdate(WebDriver driver) {
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
				"LearningCenter", "ExtRecApproval"));
		By by = By.xpath("//tr[descendant::td[contains(text(),'"
				+ this.getTitle() + "')]]/td/a[contains(text(),'"
				+ this.getRevieweeUID().toUpperCase() + "')]");
		
//		Navigator.waitForAjaxElementLoad(driver, by);
		Navigator.explicitWait(1000);
		
		WebDriverUtils.clickLink(driver, by);
		
		WebDriverUtils.switchToPopUpWin(driver);
//		Navigator.waitForAjaxElementLoad(driver, by);
		Navigator.explicitWait(1000);
		this.UpdateExtRec_UI(driver, "UPDATE");

	}

	public void UpdateExtRec_UI(WebDriver driver, String action) {

		By by = null;

		String str = this.getTitle();
		if (!str.equals("")) {
			by = By.name("TITLE");
			WebDriverUtils.fillin_textbox(driver, by, str);
		}

		str = this.getType();
		if (!str.equals("")) {
			by = By.name("TYPE");
			WebDriverUtils.select_selector(driver, by, str);
		}

			
		FunctionUI.setDates_UI(driver, this.getStartDate(),"SDdatebox");			
		FunctionUI.setDates_UI(driver, this.getEndDate(),"EDdatebox");	
		

		str = this.getVenue();
		if (!str.equals("")) {
			by = By.name("VENUE");
			WebDriverUtils.fillin_textbox(driver, by, str);
		}

		str = this.getLanguage();
		if (!str.equals("")) {
			by = By.name("LANG");
			WebDriverUtils.select_selector(driver, by, str);
		}

		str = this.getDuration();
		by = By.name("TRAININGHOURS");
		if (!str.equals("")) {
			WebDriverUtils.fillin_textbox(driver, by, str);
		}

		str = this.getCost();
		if (!str.equals("")) {
			by = By.name("COST");
			WebDriverUtils.fillin_textbox(driver, by, str);
		}

		str = this.getGrade();
		if (!str.equals("")) {
			by = By.name("GRADE");
			WebDriverUtils.fillin_textbox(driver, by, str);
		}

		str = this.getScore();
		if (!str.equals("")) {
			by = By.name("SCORE");
			WebDriverUtils.fillin_textbox(driver, by, str);
		}

		if (!str.equals("")) {
			str = this.getVendorInfo();
			by = By.name("VENDINFO");
			WebDriverUtils.fillin_textbox(driver, by, str);
		}

		if (!str.equals("")) {
			str = this.getComment();
			by = By.name("DESC");
			WebDriverUtils.fillin_textbox(driver, by, str);
		}

		str = this.getStatus();
		if (!str.equals("")) {
			by = By.name("STATUS");
			WebDriverUtils.select_selector(driver, by, str);
		}

		by = By.name(action);
		WebDriverUtils.clickButton(driver, by);

		String msg = "";
		if (str.equals(Labels.Label_PendingApproval)) {
			msg = Labels.Msg_PendingApprWarn.toString();
			JUnitAssert.assertTrue(WebDriverUtils.closeAlertAndGetItsText()
					.contains(msg),
					"Cannot find alert message when submit approval");
		}

		if (action.equals("CREATE")) {

			msg = Labels.Msg_ExtCreateSuccess.toString();
			// by =
			// By.xpath("//div/p[contains(text(),'This external training record has been updated.')]");
			JUnitAssert.assertTrue(
					WebDriverUtils.textPresentInPage(driver, msg),
					"Cannot add external training record");
		} else if (action.equals("UPDATE")) {
			msg = Labels.Msg_ExtUpdateSuccess.toString();
			// by =
			// By.xpath("//div/p[contains(text(),'This external training record has been updated.')]");
			JUnitAssert.assertTrue(
					WebDriverUtils.textPresentInPage(driver, msg),
					"Cannot update external training record");
		}

	}
}