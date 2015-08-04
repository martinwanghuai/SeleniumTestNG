package com.netdimen.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.netdimen.abstractclasses.TestObject;
import com.netdimen.utils.WebDriverUtils;

/**
 * 
 * @author martin.wang
 *
 */
public class Template extends TestObject {
	private String Name = "";

	public void setName_UI(WebDriver driver, String str){
		if(!str.equalsIgnoreCase("")){
			By by = By.id("name");
			WebDriverUtils.fillin_textbox(driver, by, str);
		}
	}
	
	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	/*@Override
	public String toString() {
		// TODO Auto-generated method stub
		return new StringBuilder().append(this.getClass().getName()).
				append("_").
				append(this.getFuncType()).
				append("_").
				append(this.getName()).
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

}
