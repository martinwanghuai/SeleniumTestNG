package com.netdimen.model;


import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.netdimen.abstractclasses.TestObject;
import com.netdimen.config.Config;
import com.netdimen.config.Labels;
import com.netdimen.junit.JUnitAssert;
import com.netdimen.utils.WebDriverUtils;
import com.netdimen.view.FunctionUI;
import com.netdimen.view.Navigator;
import com.netdimen.view.PermissionUI;
import com.netdimen.view.SelectorsUI;
import com.netdimen.view.WebElementWrapper;

public class EqRule extends TestObject {
	private String TargetModuleID = "", SubstituteModuleIDs = "", Audience = "";
	private String TargetModuleTitle;
	


	public boolean equals(com.netdimen.abstractclasses.TestObject para0) {
		boolean result = false;
		return result;
	}

	public EqRule() {
		super();
	}


	public String getAudience() {
		return Audience;
	}

	public void setAudience(String audience) {
		Audience = audience;
	}

	public void SetEquivalentRule(WebDriver driver, String moduleTitle) {
		WebElementWrapper webElem = Navigator.xmlWebElmtMgr.getWebElementWrapper("ManageCenter", "LearningGearBtn");
		String[] paramters = { moduleTitle };
		webElem.setParamters(paramters);
		WebDriverUtils.mouseOver(driver, webElem.getBy());
		WebDriverUtils.clickButton(driver, By.linkText(Labels.Gear_EQ_Btn.toString()));
		ArrayList<String> Titles = new ArrayList<String>();
		String [] SubstituteArray =null;
		if (!(this.SubstituteModuleIDs.isEmpty())) {
			SubstituteArray = SubstituteModuleIDs.trim().split(";"); // module are separated by "|"
			for (String SubstituteID : SubstituteArray) {
				System.out.println(SubstituteID);
				Titles.add(SubstituteID);
			}
			CreateRule(driver, Titles);

		}
		By by = By.xpath("//tr[descendant::td/ul/li[contains(text(),'"+SubstituteArray[0]+"')]]/td/div[1]/button");
		WebDriverUtils.mouseOver(driver, by);
		WebDriverUtils.clickButton(driver, By.linkText(Labels.Gear_Audience_Btn.toString()));
		PermissionUI.setPermission_UI(driver, true, "Org include:" + Audience);

		Titles.clear();

		// Check output
		by = By.xpath("//table[@class='netd-table full-width ']");
		String output = WebDriverUtils.getText(driver, by).trim();
		if (!(SubstituteModuleIDs.isEmpty()))
			JUnitAssert.assertTrue(output.contains(SubstituteArray[0]), output + " does not contain " + SubstituteArray[0]);
	}

	public void CreateRule(WebDriver driver, ArrayList<String> Titles) {
		By by;
		by = By.xpath("//button[descendant::span[text()='" + Labels.Btn_Create_Rule
				+ "']]");
		WebDriverUtils.clickButton(driver, by);
		by = By.linkText(Labels.Add_Module_Link.toString());
		WebDriverUtils.clickButton(driver, by);
		//WebElementManager.getInstance().getWebElementWrapper("TopDownSelector", "1.SearchText");
		WebDriverUtils.switchToPopUpWin(driver);
		SelectorsUI.PopUp_Selector(driver,SelectorsUI.PopUpSelector.TopDownSelector, Titles);
		WebDriverUtils.switchToParentWin(driver);
		by = By.xpath("//button[descendant::span[text()='" + Labels.Btn_Create + "']]");
		WebDriverUtils.clickButton(driver, by);
	}

	public void runCreateEQRule(WebDriver driver) {
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
				"ManageCenter", "Modules"), this);
		FunctionUI.SearchModuleInManagerCenter(driver, this.TargetModuleID);
		SetEquivalentRule(driver, TargetModuleTitle);
	}

	public String getTargetModuleID() {
		return TargetModuleID;
	}

	public void setTargetModuleID(String targetModuleID) {
		TargetModuleID = targetModuleID;
	}

	public String getSubstituteModuleIDs() {
		return SubstituteModuleIDs;
	}

	public void setSubstituteModuleIDs(String substituteModuleIDs) {
		SubstituteModuleIDs = substituteModuleIDs;
	}

	public String getTargetModuleTitle() {
		return TargetModuleTitle;
	}

	public void setTargetModuleTitle(String targetModuleTitle) {
		TargetModuleTitle = targetModuleTitle;
	}

}