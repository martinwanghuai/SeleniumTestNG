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
public class Vendor extends TestObject {
	public String Desc = "";
	
	public Vendor(){
		super();
	}
	
	public void setDesc_UI(WebDriver driver, String str){
		if(!str.equalsIgnoreCase("")){
			By by = By.id("DESC");
			WebDriverUtils.fillin_textbox(driver, by, str);
		}
	}
	
	public void runCreate(WebDriver driver){
		//Navigator.navigate(driver, Navigator.URL.ManageCenter);
		Navigator.navigate(driver,Navigator.xmlWebElmtMgr.getNavigationPathList("ManageCenter","Vendor"),this);
		By by; //= By.linkText("Vendors");
		//WebDriverUtils.clickLink(driver, by);
		by = By.id("create-config-button");
		WebDriverUtils.clickButton(driver, by);
	    this.setDesc_UI(driver, this.getDesc());
	    by = By.xpath("//button/span[text()='Save']");
	    WebDriverUtils.clickLink(driver, by);
	}
	
	public String getDesc() {
		return Desc;
	}

	public void setDesc(String desc) {
		Desc = desc;
	}

/*	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return new StringBuilder().
				append(this.getClass().getName()).
				append("_").
				append(this.getFuncType()).
				append("_").
				append(this.getDesc()).
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
