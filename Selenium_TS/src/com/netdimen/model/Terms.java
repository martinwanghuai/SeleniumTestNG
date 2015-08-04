package com.netdimen.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.netdimen.junit.JUnitAssert;
import com.netdimen.utils.WebDriverUtils;

/**
 * 
 * @author martin.wang
 *
 */
public class Terms extends com.netdimen.abstractclasses.TestObject {
	private String Terms = "";

	public boolean equals(com.netdimen.abstractclasses.TestObject para0) {
		boolean result = false;
		return result;
	}

	public Terms() {
		super();
	}

	public String getTerms() {
		return Terms;
	}

	public void setTerms(String terms) {
		Terms = terms;
	}

	public void setTerms_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
		}
	}

	public void runAcceptTerms(WebDriver driver) {

		By by = By.xpath("//div[text()='" + this.getTerms() + "']");
		int size = WebDriverUtils.getHowManyByPresntInPage(driver,by,true);
		JUnitAssert.assertTrue(size == 1, "Cannot find term:" + this.getTerms());
		by = By.id("agree");
		WebDriverUtils.check_checkbox(driver, by);
		by = By.id("accept");
		WebDriverUtils.clickButton(driver, by);
		by = By.xpath("//div[@id='home-page']");
		// Check HomePage
	}

	public void runDeclineTerms(WebDriver driver) {
		// Navigator.navigate(driver,
		// Navigator.webElmtMgr.getNavigationPathList("ManageCenter","1.Overview"));
		// this.setTerms_UI(driver, this.getTerms());
		By by = By.xpath("//div[text()='" + this.getTerms() + "']");
		int size = WebDriverUtils.getHowManyByPresntInPage(driver,by,true);
		JUnitAssert.assertTrue(size == 1, "Cannot find term:" + this.getTerms());
		by = By.id("agree");
		WebDriverUtils.check_checkbox(driver, by);
		by = By.id("decline");
		WebDriverUtils.clickButton(driver, by);
		// Check if it returns to Login Page
		by = By.xpath("//div[@id='login-box']");

	}

}