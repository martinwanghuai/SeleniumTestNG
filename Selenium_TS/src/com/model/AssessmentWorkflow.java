package com.model;
import java.util.ArrayList;
import java.util.Arrays;

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
public class AssessmentWorkflow extends com.abstractclasses.TestObject{
	private String Name="";

	public boolean equals(TestObject obj){
		return true;
	}

	public void checkExpectedResult_UI(WebDriver driver, String str){
		super.checkExpectedResult_UI(driver, str);
	}

	public String getName(){
		return Name;
	}
	
	public void setName(String Name){
		this.Name = Name;
	}

	public void setName_UI(WebDriver driver, String str){
		if(!str.equals("")){
			By by = By.name("DISPLAYNAME");
			WebDriverUtils.fillin_textbox(driver, by, str);
		}
	}
	
	public void runCreate(WebDriver driver, ArrayList<TestObject> objs){
		Navigator.navigate(driver,Navigator.xmlWebElmtMgr.getNavigationPathList("ManageCenter","Workflow"),this);
		Navigator.explicitWait(1000);
		By by = By.id("addAssessmentButton");
		WebDriverUtils.clickButton(driver, by);
		
		Navigator.explicitWait(1000);
		this.setName_UI(driver, this.getName());
	
		for(int i = 0; i < objs.size(); i ++){
			TestObject obj = (TestObject)objs.get(i);
			if(obj instanceof Workflow){
				Workflow ins = (Workflow)obj;
				ins.setIndex((i+1)+"");
				ins.create(driver);
			}
			Navigator.explicitWait(1000);
		}
		
		Navigator.explicitWait(3000);
		
		by = By.name("saveButton");
		WebDriverUtils.clickButton(driver, by);
	}

	/**Empty body since it's a test suite
	 * 
	 * @param driver
	 */
	public void runSetupAndPassAssessmentExam(WebDriver driver){
		
	}
}