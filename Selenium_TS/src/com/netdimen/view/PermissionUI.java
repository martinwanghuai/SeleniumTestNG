package com.netdimen.view;

import java.util.ArrayList;
import java.util.Arrays;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.netdimen.utils.WebDriverUtils;
import com.netdimen.view.SelectorsUI.PopUpSelector;
import com.netdimen.config.Config;

public class PermissionUI {
	private PermissionUI() {
		// Suppress default constructor for non-instantiability
		throw new AssertionError();
	}

	/**
	 * Pre-condition: On Permission Selector
	 * 
	 * @param driver
	 * @param bWrite
	 *            = If true, navigate to "Unrestricted Access" else remains on
	 *            Read Access page (same as Target Audience page)
	 * @param permissionStr
	 *            e.g. User:uma_qa1;uma_qa2; OrgInclude:Org1/Org2;
	 *            OrgExclude:Org1/Org2; OrgAttribute:Org_DropDown=list2-CS
	 */
	public static void setPermission_UI(WebDriver driver, Boolean bWrite,
			String permissionStr) {

		// WebDriverUtils.switchToPopUpWin(driver);
		// Navigator.explicitWait(1000);
		String[] permissionArray = permissionStr.split("\n");
		By by = null;

		if (bWrite) {
			by = By.xpath("//span[contains(text(),'"
					+ Config.getInstance().getProperty(
							"label.permission.selector.write_access") + "')]");
			Navigator.waitForAjax(driver, by);
			WebDriverUtils.clickLink(driver, by);
		}

		for (String permission : permissionArray) {

			String[] strs1 = permission.split(":"); // User Read:uma_qa1;uma_qa2
			String criterion = strs1[0];

			Navigator.explicitWait(1000);
			String[] values = strs1[1].split(";");

			String OrgLink;
			switch (criterion) {
			case "OrgInclude":
				OrgLink = "desc.include.Organization";
				PermissionUI.setOrgPerm(driver, values, OrgLink);
				break;
			case "OrgExclude":
				OrgLink = "desc.exclude.Organization";
				PermissionUI.setOrgPerm(driver, values, OrgLink);
				break;

			case "OrgAttribute":
				PermissionUI.setOrgAttrPerm(driver, values);
				break;
			case "User":
				PermissionUI.setUserPerm(driver, values);
				break;

			}
			by = By.id("saveButton");
			Navigator.explicitWait(1000);
			WebDriverUtils.clickButton(driver, by);
		}

		by = By.name("Cancel");
		Navigator.explicitWait(1000);
		WebDriverUtils.clickButton(driver, by);

		// WebDriverUtils.switchToParentWin(driver);
	}

	private static void setOrgAttrPerm(WebDriver driver, String[] values) {
		// TODO Auto-generated method stub
		// set up permission based on organization attributes
		By by = null;
		by = By.id("SELECTOR");
		Navigator.waitForAjax(driver, by);

		WebDriverUtils.select_selector(driver, by, Config.getInstance()
				.getProperty("heading.org.attrs"));

		by = By.name("goButton");
		Navigator.waitForAjax(driver, by);

		WebDriverUtils.clickButton(driver, by);

		FunctionUI.setOrgAttributes_UI(driver, values);
	}

	private static void setOrgPerm(WebDriver driver, String[] values,
			String OrgLink) {
		// set up permission based on org (include, exclude)
		By by = null;
		by = By.id("SELECTOR");
		Navigator.waitForAjax(driver, by);

		WebDriverUtils.select_selector(driver, by, "Organization");

		by = By.name("goButton");
		Navigator.waitForAjax(driver, by);
		WebDriverUtils.clickButton(driver, by);

		by = By.linkText(Config.getInstance().getProperty(OrgLink));
		Navigator.waitForAjax(driver, by);
		WebDriverUtils.clickLink(driver, by);
		WebDriverUtils.switchToPopUpWin(driver);

		Navigator.explicitWait(1000);
		WebDriverUtils.checkSelect_CheckBox(driver, values);

		WebDriverUtils.switchToParentWin(driver);

	}

	private static void setUserPerm(WebDriver driver, String[] values) {
		// set up permission based on users
		By by = null;
		by = By.linkText("Users");
		Navigator.waitForAjax(driver, by);
		WebDriverUtils.clickLink(driver, by);

		WebDriverUtils.switchToPopUpWin(driver);
		Navigator.explicitWait(1000);

		ArrayList<String> Modules = new ArrayList<String>(Arrays.asList(values));
		SelectorsUI.PopUp_Selector(driver,
				SelectorsUI.PopUpSelector.UserSelector, Modules);

		WebDriverUtils.switchToParentWin(driver);

	}

}
