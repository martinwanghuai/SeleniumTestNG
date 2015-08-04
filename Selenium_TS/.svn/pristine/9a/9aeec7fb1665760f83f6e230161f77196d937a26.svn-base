package com.netdimen.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.netdimen.utils.Validate;
import com.netdimen.utils.WebDriverUtils;
import com.netdimen.view.FunctionUI;
import com.netdimen.view.Navigator;
import com.netdimen.view.PermissionUI;

/**
 * 
 * @author martin.wang
 *
 */
public class TranscriptStatus extends com.netdimen.abstractclasses.TestObject {
	private String Status = "", Permission = "", ReadPerm="", WritePerm="";

	public boolean equals(com.netdimen.abstractclasses.TestObject para0) {
		boolean result = false;
		return result;
	}

	public String getReadPerm() {
		return ReadPerm;
	}

	public void setReadPerm(String readPerm) {
		ReadPerm = readPerm;
	}

	public String getWritePerm() {
		return WritePerm;
	}

	public void setWritePerm(String writePerm) {
		WritePerm = writePerm;
	}

	public TranscriptStatus() {
		super();
	}

	public String getStatus() {
		return Status;
	}

	public String getPermission() {
		return Permission;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public void setPermission(String permission) {
		Permission = permission;
	}

	public void setStatus_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
			By by = By.xpath("//tr[descendant::td[contains(text(),'"+str+"')]]/td/div/button");
			WebDriverUtils.mouseOver(driver, by);
		}
	}

	public void setPermission_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
			By by = By.xpath("//tr[descendant::td[contains(text(),'"+this.getStatus()+"')]]/td/div/ul/li/a[contains(text(),'Permissions')]");
			WebDriverUtils.clickLink(driver, by);	
			if (!Validate.isBlank(this.getReadPerm())){
				by = By.name("permissionButton");			
				WebDriverUtils.clickButton(driver, by);
				WebDriverUtils.switchToPopUpWin(driver);
				Navigator.explicitWait(1000);
				PermissionUI.setPermission_UI(driver, false, this.getReadPerm());
				WebDriverUtils.switchToParentWin(driver);
			}
			if (!Validate.isBlank(this.getWritePerm())){
				by = By.name("permissionButton");			
				WebDriverUtils.clickButton(driver, by);
				WebDriverUtils.switchToPopUpWin(driver);
				Navigator.explicitWait(1000);
				PermissionUI.setPermission_UI(driver, true, this.getWritePerm());
				WebDriverUtils.switchToParentWin(driver);
			}
		}
	}

	public void runUpdatePermission(WebDriver driver) {
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
				"ManageCenter", "TranscriptStatus"));
		this.setStatus_UI(driver, this.getStatus());
		this.setPermission_UI(driver, this.getPermission());
	}
	
/*	public String toString(){
		return new StringBuilder().
				append(this.getStatus()).
				append("_").
				append(this.getPermission()).toString();
	}
*/
	
}