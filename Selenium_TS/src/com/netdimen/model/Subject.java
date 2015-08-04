package com.netdimen.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.netdimen.abstractclasses.TestObject;
import com.netdimen.utils.WebDriverUtils;
import com.netdimen.view.Navigator;

/**
 * 
 * @author martin.wang
 *
 */
public class Subject extends TestObject {


	private String SubjectName = "";

	public void setSubjectName_UI(WebDriver driver, String str){
		if(!str.equalsIgnoreCase("")){
			By by = By.id("DESC");
			WebDriverUtils.fillin_textbox(driver, by, str);
		}
	}

	public void runCreate(WebDriver driver){
		//Navigator.navigate(driver, Navigator.URL.Subject);
		Navigator.navigate(driver,Navigator.xmlWebElmtMgr.getNavigationPathList("ManageCenter","Subject"),this);
		//WebDriverUtils.addVisitedWin(driver);
		WebDriverUtils.switchToPopUpWin(driver);

		By by = By.id("create-config-button");
		WebDriverUtils.clickButton(driver, by);
		this.setSubjectName_UI(driver, this.getSubjectName());

		by = By.xpath("//button[span[contains(text(),'Save')]]");
		WebDriverUtils.clickLink(driver, by);
	}
	
	
/*	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return new StringBuilder().
				append(this.getClass().getName()).
				append("_").
				append(this.getFuncType()).
				append("_").
				append(this.getSubjectName()).
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

	public String getSubjectName() {
		return SubjectName;
	}

	public void setSubjectName(String subjectName) {
		SubjectName = subjectName;
	}

}
