package com.netdimen.model;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.netdimen.config.Labels;
import com.netdimen.junit.JUnitAssert;
import com.netdimen.utils.CriteriaParser;
import com.netdimen.utils.Checker;
import com.netdimen.utils.WebDriverUtils;
import com.netdimen.view.FunctionUI;
import com.netdimen.view.Navigator;
import com.netdimen.view.PermissionUI;

/**
 * 
 * @author martin.wang
 *
 */
public class Catalog extends com.netdimen.abstractclasses.TestObject {
	private String CatalogPath = "", Permission = "", ReadPerm ="", WritePerm="";
	
	

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

	public boolean equals(com.netdimen.abstractclasses.TestObject para0) {
		boolean result = false;
		return result;
	}

	public Catalog() {
		super();
	}

	public String getCatalogPath() {
		return CatalogPath;
	}


	public String getPermission() {
		return Permission;
	}

	public void setPermission(String permission) {
		Permission = permission;
	}

	public void setCatalogPath(String catalogpath) {
		CatalogPath = catalogpath;
	}

	public void setCatalogPath_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
		}
	}
	
	public void runEditProperty(WebDriver driver){
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
				"ManageCenter", "Catalog"));
		
		FunctionUI.expandTree_UI(driver, this.getCatalogPath());
		By by = By.xpath("//div[@id='catalog-structure-gear']/button[@is-drop-down-button='true']");
		Navigator.explicitWait(1000);
//		Navigator.waitForAjaxElementLoad(driver, by);
		WebDriverUtils.mouseOver(driver, by);
		
		by = By.xpath("//div[contains(@id,'catalog-structure-gear')]/ul/li/a[contains(text(),'"+Labels.Edit_Catalog_Properties+"')]");
		Navigator.explicitWait(1000);
//		Navigator.waitForAjaxElementLoad(driver, by);
		WebDriverUtils.clickButton(driver, by);

		WebDriverUtils.switchToPopUpWin(driver);
		Navigator.explicitWait(1000);
		
		if (!Checker.isBlank(this.getReadPerm())){
			by = By.name("permissionButton");			
			WebDriverUtils.clickButton(driver, by);
			
			WebDriverUtils.switchToPopUpWin(driver);
			Navigator.explicitWait(1000);
			PermissionUI.setPermission_UI(driver, false, this.getReadPerm());
			WebDriverUtils.switchToParentWin(driver);
		}
		
		if (!Checker.isBlank(this.getWritePerm())){
			by = By.name("permissionButton");			
			WebDriverUtils.clickButton(driver, by);
			
			WebDriverUtils.switchToPopUpWin(driver);
			Navigator.explicitWait(1000);
			PermissionUI.setPermission_UI(driver, true, this.getWritePerm());
			WebDriverUtils.switchToParentWin(driver);
		}
		//UIFunctionUtils.setPermission_UI(driver, this.getPermission());
		
		WebDriverUtils.closeAllPopUpWins(driver);
	}

	public void runCheckVisibility(WebDriver driver) {
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
				"LearningCenter", "Search"));
		
		//Click Search button to show catalogs 
		By by = By.xpath("//div[@id='ajaxInlineSearchBox']/button[@name='SEARCH']");
		WebDriverUtils.clickButton(driver, by);
		
		
		String[] catalogs = this.getCatalogPath().split("\n");//visible:NETD/NETD ACC DEPT;A2
		for(String catalog_tmp: catalogs){
			String[] catalogs1 = catalog_tmp.split(":");
			String criteria = catalogs1[0]; //visible or invisible;
			
			String catalogPaths = catalogs1[1]; //NETD/NETD ACC DEPT;A2
			
			String[] catalogPathArray = catalogPaths.split(";");
			for(String catalogPath: catalogPathArray){
				
				String[] catalogSeq = catalogPath.split("/");
				String catalog = catalogSeq[catalogSeq.length-1];//last catalog as search keyword
				by  = By.id("ajaxTreeSearchBox_input");
				WebDriverUtils.fillin_textbox(driver, by, catalog);
				
				by = By.xpath("//div[@id='ajaxTreeSearchBox_ctr']/div[@class='content']/div[@val='"+catalog+"']");//auto-suggest results
				
				int size = driver.findElements(by).size();
				
				if(criteria.equalsIgnoreCase("visible")){
					Navigator.waitForAjax(driver, by);
					WebDriverUtils.clickLink(driver, by);
					Navigator.waitForAjax(driver, by);
					by = By.xpath("//div[@id='ajaxTreeSearchBox-result']//span[text()='"+catalog+"']");
					size = driver.findElements(by).size();
					Navigator.explicitWait(1000);
					JUnitAssert.assertTrue(size>0, "Catalog path is invisible:" + catalogPath);
				}else if(criteria.equalsIgnoreCase("invisible")){
					JUnitAssert.assertTrue(size==0, "Catalog path is visible:" + catalogPath);
				}
			}
		}
	}

	/**
	 * in Browse Catalog Page, check if the catalogs stated in EKPTestData is visible or not
	 * @param driver
	 */
	public void runBrowseCatalog (WebDriver driver) {
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
				"LearningCenter", "BrowseCatalog"));
		//separate the test data and put into HashMap
		HashMap<String, ArrayList<String>> catPath = CriteriaParser.parseKeyValueList(":", ";", this.getCatalogPath());
		Iterator<String> keySetIterator = catPath.keySet().iterator();
		By by=null;
		
		ArrayList<String> list =null;
		String visibility="";
		String [] path;
		String [] tempPath;
		int size = 0;
		while (keySetIterator.hasNext()){
			visibility = keySetIterator.next();//"visible" or "invisible"
			list = catPath.get(visibility);//get the catalog paths needs to be check
			
			for(String temp : list){//start checking of each catalog path
				path = temp.split("/");
				for (int i=0;i<path.length;i++){
					tempPath = path[i].split(",");
					for(String temp2 : tempPath){
						by = By.xpath("//a[descendant::span[text()='"+temp2+"']]");
						size = driver.findElements(by).size();//check if catalog present in page
						
						if(i!=path.length-1){//if currently is not checking the childmost catalog, i.e. if it's page catalog
							WebDriverUtils.clickLink(driver, by); //click parent link
						}else if(visibility.equals("visible")){ //otherwise, check if catalog is visible or not
							JUnitAssert.assertTrue(size>0, "Catalog path is invisible:" + temp);
						}else if(visibility.equals("invisible")){
							JUnitAssert.assertTrue(size==0, "Catalog path is visible:" + temp);
						}else {
							JUnitAssert.assertTrue(false,"incorrect setup in EKPTestData");
						}
					}
				}
				//go back to "Top" to check next path
				by = By.linkText("Top");
				if(WebDriverUtils.isElementPresent(by)){
					WebDriverUtils.clickLink(driver, by);
				}
			}		
		}	
	}
/*	public String toString(){
		return new StringBuilder().
				append(this.getClass().getName()).
				append("_").
				append(this.getFuncType()).
				append("_").
				append(this.getCatalogPath()).
				append("_").
				append(this.getPermission()).
				toString();
	}*/
}