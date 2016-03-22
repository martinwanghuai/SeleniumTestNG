package com.model;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.junit.JUnitAssert;
import com.utils.WebDriverUtils;
import com.view.FunctionUI;
import com.view.Navigator;

/**
 * 
 * @author martin.wang
 *
 */
public class Organization extends com.abstractclasses.TestObject {
	private String Org = "";
	private String UserID_search = "";
	private String ExpectResult = "";

	public boolean equals(com.abstractclasses.TestObject para0) {
		boolean result = false;
		return result;
	}

	public Organization() {
		super();
	}

	public String getOrg() {
		return Org;
	}

	public void setOrg(String org) {
		Org = org;
	}
	public String getUserID_search() {
		return UserID_search;
	}
	
	public void setUserID_search(String userID_search) {
		UserID_search = userID_search;
	}
		
	public void setOrg_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
		}
	}

	public void runCheckVisibility(WebDriver driver) {
		Navigator.navigate(driver,Navigator.xmlWebElmtMgr.getNavigationPathList("ManageCenter","OrgMaintenance"), this);
		
		String[] orgs = this.getOrg().split("\n");
		for(String org_tmp: orgs){
			String[] orgs1 = org_tmp.split(":");
			String criteria = orgs1[0]; //visible or invisible;
			
			//expand the tree
			String orgPaths = orgs1[1]; //ALL/My Company One/Org1_IT/Org1_AdHoc;ALL/Netdimensions/Netd_AdHoc
			
			String[] orgPathArray = orgPaths.split(";");
			for(String orgPath: orgPathArray){
				String[] orgPath_tmp = orgPath.split("/");
				
				StringBuilder sb = new StringBuilder();
				for(int i = 0; i < orgPath_tmp.length -1 ; i ++){
					sb.append(orgPath_tmp[i]).append("/");
				}
				
				String orgFinal = orgPath_tmp[orgPath_tmp.length -1];
				//check visibility of final org
				By by = By.linkText(orgFinal);
				int size;
				
				if(criteria.equalsIgnoreCase("visible")){
					FunctionUI.expandTree_UI(driver, sb.toString());
					Navigator.waitForAjax(driver, by);
					size = WebDriverUtils.getHowManyByPresntInPage(driver,by, false);
					JUnitAssert.assertTrue(size > 0, "Org is invisible:" + orgFinal);	
				}else if(criteria.equalsIgnoreCase("invisible")){
					size = WebDriverUtils.getHowManyByPresntInPage(driver,by, false);
					JUnitAssert.assertTrue(size==0, "Org is visible:" + orgFinal);
				}
				
			}			
		}
	}
	
	public void checkExpectedResult_UI(WebDriver driver, String expectedResult){
		super.checkExpectedResult_UI(driver, expectedResult);
	}
	
	public void runLogicalDomain(WebDriver driver) {
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
				"ManageCenter", "UserEditor"));
		
		WebDriverUtils.switchToPopUpWin(driver);
		WebDriverUtils.switchToFrame(driver, "USERMENU");
		By by =By.cssSelector("img[alt='Search']");/* search button */
		/* click search button in the top division */
		WebDriverUtils.clickLink(driver, by);
		
		WebDriverUtils.switchToFrame(driver, "USERMAIN");
		By by2 = By.id("PID");
		String UserID = this.getUserID_search();/* input userID */
		WebDriverUtils.fillin_textbox(driver, by2, UserID);
		Navigator.explicitWait(1000);
		by = By.name("SEARCH"); /* search and wait */
		WebDriverUtils.clickButton(driver, by);
		Navigator.explicitWait(3000);
		
		WebDriverUtils.switchToFrame(driver, "USERMAIN");
		Boolean searchResult_bool = WebDriverUtils.isElementPresent(By.xpath("//tr[@class='row1']/td[1]"));/* userID://*[@id='main-content']/table/tbody/tr/td[2] */
		if(searchResult_bool) {
			By by3 = By.xpath("//*[@id='main-content']/table/tbody/tr/td[2]");	
			String searchResult = WebDriverUtils.getText(driver, by3);	
			String msg = "found in the table";
			JUnitAssert.assertTrue(this.getUserID_search().toString().equalsIgnoreCase(searchResult),msg);
		}
		else {
			String msg = this.getUserID_search()+" does not exist in the table";
			JUnitAssert.assertTrue(ExpectedResult.equalsIgnoreCase("false"),msg);
		}
	}	
	
/*	public String toString(){
		return new StringBuilder().
				append(this.getClass().getName()).
				append("_").
				append(this.getUID()).
				append("_").
				append(this.getFuncType()).
				append("_").
				append(this.getOrg()).
				toString();
	}
*/



//	public static String getOrgPath_UI(WebDriver driver, String UName){
//		//HomePage -> User Preference -> My Orgs 		  
//		String orgPath = "";
//		Navigator.navigate(driver,Navigator.webElmtMgr.getNavigationPathList("LearningCenter","HOME"));
//		
//		
//		By by = By.xpath("//div[@class='sec-menu-container']/ul/li/a[@class='username']");
//		WebDriverUtils.clickLink(driver, by);
//
//		by = By.xpath("//div[@id='main-content']/div/ul/li[2]/a/span[contains(text(),'My Orgs')]");
//		WebDriverUtils.clickLink(driver, by);
//
//		by = By.xpath("//td/select/option[@selected=\"\"]");
//		List<WebElement> elements = driver.findElements(by);
//
//		StringBuilder sb = new StringBuilder();	  
//		//ends with "UNASSIGNED"
//		int size = elements.size();
//		if(size > 2){
//			//for "ALL/ORG1/UNASSIGNED", ignore the last one - "UNASSIGNED"
//			for(int i = 0; i < elements.size() - 1; i++){
//				WebElement element = elements.get(i);
//				sb.append(element.getText()).append("/");  
//			}  
//		}else if(size == 2){
//			//for "ALL/UNASSIGNED", inherite from "ALL"
//			if(elements.get(1).getText().equalsIgnoreCase("UNASSIGNED")){
//				sb.append(elements.get(0).getText()).append("/");
//			}else{
//				for(WebElement element: elements){
//					sb.append(element.getText()).append("/");
//				}	
//			}
//		}
//
//		int index = sb.lastIndexOf("/");
//		orgPath = sb.replace(index, index+1, "").toString();
//
//		Navigator.navigate(driver,Navigator.webElmtMgr.getNavigationPathList("ManageCenter","2.Org.Maintenance"));
//		
//		return orgPath;
//	}


}