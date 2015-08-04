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
public class QuestionPool extends TestObject {
	private String QuestionPoolName = "";
	
	public void setQuestionPoolName_UI(WebDriver driver, String str){
		if(!str.equalsIgnoreCase("")){
			By by = By.id("NAME");
			WebDriverUtils.fillin_textbox(driver, by, str);
		}
	}

	public void runCreateQuestionPool(WebDriver driver){
		//Navigator.navigate(driver, Navigator.URL.QuestionPoolManager);
		Navigator.navigate(driver,Navigator.xmlWebElmtMgr.getNavigationPathList("ManageCenter","QuesPool"),this);
		WebDriverUtils.switchToPopUpWin(driver);

		By by = By.name("ADD");
		WebDriverUtils.clickButton(driver, by);
		this.setQuestionPoolName_UI(driver, this.getQuestionPoolName());

		by = By.name("SUBBUTTON");
		WebDriverUtils.clickButton(driver, by);
	}
	
/*	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return new StringBuilder().
				append(this.getClass().getName()).
				append("_").
				append(this.getFuncType()).
				append("_").
				append(this.getQuestionPoolName()).
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

	public String getQuestionPoolName() {
		return QuestionPoolName;
	}

	public void setQuestionPoolName(String questionPoolName) {
		QuestionPoolName = questionPoolName;
	}

}
