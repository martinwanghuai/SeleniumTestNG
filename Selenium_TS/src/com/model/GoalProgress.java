package com.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.utils.WebDriverUtils;
import com.view.FunctionUI;

/**
 * 
 * @author martin.wang
 *
 */
public class GoalProgress extends com.abstractclasses.TestObject {
	private String ProgressDetails = "", ProgressDate = "", Progress = "",
			Status = "";

	public boolean equals(com.abstractclasses.TestObject para0) {
		boolean result = false;
		return result;
	}

	public GoalProgress() {
		super();
	}

	public String getProgressDetails() {
		return ProgressDetails;
	}

	public String getProgressDate() {
		return ProgressDate;
	}

	public String getProgress() {
		return Progress;
	}

	public String getStatus() {
		return Status;
	}

	public void setProgressDetails(String progressdetails) {
		ProgressDetails = progressdetails;
	}

	public void setProgressDate(String progressdate) {
		ProgressDate = progressdate;
	}

	public void setProgress(String progress) {
		Progress = progress;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public void setProgressDetails_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
			By by = By.id("RESULT_TODATE");
			WebDriverUtils.fillin_textbox(driver, by, str);
		}
	}
/*
	public void setProgressDate_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
			String xpath = "//tr[descendant::td[contains(text(),'Progress Date')]]/td/div/div[@class='date-button-container']/a";
			WebDriverUtils.dateSelect_Calandar(driver, str, xpath);
		}
	}
*/
	public void setProgress_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
			By by = By.id("PROGRESS");
			WebDriverUtils.select_selector(driver, by, str);
		}
	}

	public void setStatus_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
			By by = By.id("STATUS");
			WebDriverUtils.select_selector(driver, by, str);
		}
	}

	public void create(WebDriver driver) {
		this.setProgressDetails_UI(driver, this.getProgressDetails());
		//this.setProgressDate_UI(driver, this.getProgressDate());
		FunctionUI.setDates_UI(driver, this.getProgressDate(),"RESULTS_ACHIEVED_DATEdatebox");	
		this.setProgress_UI(driver, this.getProgress());
		this.setStatus_UI(driver, this.getStatus());
		By by = By.xpath("//div[@class='ui-dialog-buttonset']/button[descendant::span[text()='Add']]");
		WebDriverUtils.clickButton(driver, by);
	}

}