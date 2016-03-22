package com.model;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.abstractclasses.TestObject;
import com.utils.WebDriverUtils;
import com.view.FunctionUI;
import com.view.Navigator;

/**
 * 
 * @author martin.wang
 *
 */
public class Proficiency extends com.abstractclasses.TestObject {
	private String ProficiencyCatalog = "", ProficiencyTitle = "";

	public boolean equals(com.abstractclasses.TestObject para0) {
		boolean result = false;
		return result;
	}

	public Proficiency() {
		super();
	}

	public String getProficiencyCatalog() {
		return ProficiencyCatalog;
	}

	public String getProficiencyTitle() {
		return ProficiencyTitle;
	}

	public void setProficiencyCatalog(String proficiencycatalog) {
		ProficiencyCatalog = proficiencycatalog;
	}

	public void setProficiencyTitle(String proficiencytitle) {
		ProficiencyTitle = proficiencytitle;
	}

	public void setProficiencyCatalog_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
			FunctionUI.expandTree_UI(driver, str);
		}
	}

	public void setProficiencyTitle_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
			By by = By.id("addButton");
			WebDriverUtils.clickButton(driver, by);
			by = By.id("enterNode");
			WebDriverUtils.fillin_textbox(driver, by, str);
			
			by = By.linkText("All");
			WebDriverUtils.clickLink(driver, by);
		}
	}

	public void runCreate(WebDriver driver, ArrayList<TestObject> proficiencyLevels) {
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
				"ManageCenter", "Proficiency"));
		this.setProficiencyCatalog_UI(driver, this.getProficiencyCatalog());
		this.setProficiencyTitle_UI(driver, this.getProficiencyTitle());
		
		By by = By.linkText(this.getProficiencyTitle());
		WebDriverUtils.clickLink(driver, by);		

		for(TestObject level: proficiencyLevels){
			by = By.id("addRowButton");
			WebDriverUtils.clickButton(driver, by);
			Navigator.explicitWait(1000);
			ProficiencyLevel level_ins = (ProficiencyLevel)level;
			level_ins.create(driver);
		}
	}
}