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
public class Question extends TestObject {
	private String QID="", QT="", QPOOL0="", QSTATUS="";
	

	public Question(){
		super();
	}
	
	public void setQID_UI(WebDriver driver, String str){
		if(!str.equalsIgnoreCase("")){
			By by = By.id("QID");
			WebDriverUtils.fillin_textbox(driver, by, str);
		}
	}

	public void setQuesType_UI(WebDriver driver, String str){
		if(!str.equalsIgnoreCase("")){
			By by = By.name("QT");
			WebDriverUtils.select_selector(driver, by, str);
		}
	}
	
	public void setQuesPool0_UI(WebDriver driver, String str){
		if(!str.equalsIgnoreCase("")){
			By by = By.name("QPOOL0");
			WebDriverUtils.select_selector(driver, by, str);
		}
	}
	
	
	public void setQuesStatus_UI(WebDriver driver, String str){
		if(!str.equalsIgnoreCase("")){
			By by = By.name("QSTATUS");
			WebDriverUtils.select_selector(driver, by, str);
		}
	}
	
	public void runCreateQ(WebDriver driver){
		    //Navigator.navigate(driver, Navigator.URL.ManageCenter);
			Navigator.navigate(driver,Navigator.xmlWebElmtMgr.getNavigationPathList("ManageCenter","QuesEditor"), this);
		    By by;
		    by=By.id("createButton"); //Version 112
		    WebDriverUtils.clickButton(driver, by);
		    WebDriverUtils.switchToPopUpWin(driver);
		    WebDriverUtils.switchToFrame(driver, "QFR_TOP");
				
		    // Create Questions
		    by = By.cssSelector("img[alt=\"Create question\"]");
		    WebDriverUtils.clickButton(driver, by);
		    
		    WebDriverUtils.switchToFrame(driver, "QFR_MAIN");
		    this.setQID_UI(driver, this.getQID());
		    this.setQuesType_UI(driver, this.getQT());
		    this.setQuesPool0_UI(driver, this.getQPOOL0());

		    by = By.name("createButton");
		    WebDriverUtils.clickButton(driver, by);
		    
			this.setQuesStatus_UI(driver, this.getQSTATUS());
	}
		
	
	
	
	public String getQID() {
		return QID;
	}

	public void setQID(String qID) {
		QID = qID;
	}
	
	public String getQT() {
		return QT;
	}

	public void setQT(String qT) {
		QT = qT;
	}
	
	public String getQPOOL0() {
		return QPOOL0;
	}

	public void setQPOOL0(String qPOOL0) {
		QPOOL0 = qPOOL0;
	}

	public String getQSTATUS() {
		return QSTATUS;
	}

	public void setQSTATUS(String qSTATUS) {
		QSTATUS = qSTATUS;
	}
	

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

}
