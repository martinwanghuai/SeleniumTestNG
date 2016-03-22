package com.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.abstractclasses.TestObject;
import com.utils.WebDriverUtils;
import com.view.Navigator;

/**
 * 
 * @author martin.wang
 *
 */
public class ExamPool extends TestObject {
	private String ExamPoolName = "";
	
	public void setExamPoolName_UI(WebDriver driver, String str){
		if(!str.equalsIgnoreCase("")){
			By by = By.name("NAME");
			WebDriverUtils.fillin_textbox(driver, by, str);
		}
	}

	public void runCreateExamPool(WebDriver driver){
		//Navigator.navigate(driver, Navigator.URL.ExamPoolManager);
		Navigator.navigate(driver,Navigator.xmlWebElmtMgr.getNavigationPathList("ManageCenter","ExamPool"),this);
		WebDriverUtils.switchToPopUpWin(driver);
		
//		Navigator.explicitWait();
		
		By by = By.xpath("//tr[descendant::td[contains(text(),'Pool Level 1')]]/td/input[@name='ADD']");
		WebDriverUtils.clickLink(driver, by);
		
//		Navigator.explicitWait();
		
		this.setExamPoolName_UI(driver, this.getExamPoolName());

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
				append(this.getExamPoolName()).
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

	public String getExamPoolName() {
		return ExamPoolName;
	}

	public void setExamPoolName(String examPoolName) {
		ExamPoolName = examPoolName;
	}

}
