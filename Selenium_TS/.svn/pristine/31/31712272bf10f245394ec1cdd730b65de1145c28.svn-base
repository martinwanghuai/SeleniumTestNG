package com.netdimen.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.netdimen.config.Labels;
import com.netdimen.utils.WebDriverUtils;
import com.netdimen.view.Navigator;

/**
 * 
 * @author martin.wang
 *
 */
public class Domain extends com.netdimen.abstractclasses.TestObject {
	private String Domain = "", Organization = "";

	public boolean equals(com.netdimen.abstractclasses.TestObject para0) {
		boolean result = false;
		return result;
	}

	public Domain() {
		super();
	}

	public String getDomain() {
		return Domain;
	}

	public String getOrganization() {
		return Organization;
	}

	public void setDomain(String domain) {
		Domain = domain;
	}

	public void setOrganization(String organization) {
		Organization = organization;
	}

	public void setDomain_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
		}
	}

	public void setOrganization_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
		}
	}

	// should make Organization as generate func
	public void runCreate(WebDriver driver) {
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
				"ManageCenter", "Domains"));

		By by = By.xpath("//div[@class='action-nav']/button");
		WebDriverUtils.clickButton(driver, by);

		by = By.id("DOMNAME");

		WebDriverUtils.fillin_textbox(driver, by, this.getDomain());

		by = By.linkText(Labels.Link_Org.toString());
		WebDriverUtils.clickLink(driver, by);

		WebDriverUtils.switchToPopUpWin(driver);
		Navigator.explicitWait(1000);
		WebDriverUtils.checkSelect_Radio(driver, this.getOrganization());
		WebDriverUtils.switchToParentWin(driver);

		by = By.xpath("//button/span[contains(text(),'Save')]");
		WebDriverUtils.clickButton(driver, by);

	}

	public void runUpdate(WebDriver driver) {
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
				"ManageCenter", "Domains"));

		By by = By.xpath("//a[contains(text(),'Domain_New')]");
		WebDriverUtils.clickLink(driver, by);

		by = By.linkText("Organization");
		WebDriverUtils.clickLink(driver, by);

		if (this.getOrganization() != "") {
			WebDriverUtils.switchToPopUpWin(driver);
			Navigator.explicitWait(1000);
			WebDriverUtils.checkSelect_Radio(driver, this.getOrganization());
			WebDriverUtils.switchToParentWin(driver);
		}

		by = By.xpath("//button/span[contains(text(),'Save')]");
		WebDriverUtils.clickButton(driver, by);

	}

/*	public String toString() {
		return new StringBuilder().append(this.getClass().getName())
				.append("_").append(this.getFuncType()).append("_")
				.append(this.getDomain()).toString();
	}*/
}