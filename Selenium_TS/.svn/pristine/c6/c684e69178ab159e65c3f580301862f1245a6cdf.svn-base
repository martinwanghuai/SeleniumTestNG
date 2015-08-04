package com.netdimen.model;

import org.openqa.selenium.WebDriver;

import com.netdimen.view.Navigator;

/**
 * 
 * @author martin.wang
 *
 */
public class OrgAttribute extends com.netdimen.abstractclasses.TestObject {
	private String AttrID = "", Name = "", Type = "";

	public boolean equals(com.netdimen.abstractclasses.TestObject para0) {
		boolean result = false;
		return result;
	}

	public OrgAttribute() {
		super();
	}

	public String getAttrID() {
		return AttrID;
	}

	public String getName() {
		return Name;
	}

	public String getType() {
		return Type;
	}

	public void setAttrID(String attrid) {
		AttrID = attrid;
	}

	public void setName(String name) {
		Name = name;
	}

	public void setType(String type) {
		Type = type;
	}

	public void setAttrID_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
		}
	}

	public void setName_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
		}
	}

	public void setType_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
		}
	}

	public void runCreate(WebDriver driver) {
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
				"ManageCenter", "Overview"));
		this.setAttrID_UI(driver, this.getAttrID());
		this.setName_UI(driver, this.getName());
		this.setType_UI(driver, this.getType());
	}

}