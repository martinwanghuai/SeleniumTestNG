package com.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.io.*;
import java.nio.file.AccessDeniedException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.python.antlr.PythonParser.else_clause_return;

import com.abstractclasses.TestObject;
import com.config.Config;
import com.config.Labels;
import com.controller.TestDriver;
import com.junit.JUnitAssert;
import com.utils.CriteriaParser;
import com.utils.SikuliUtils;
import com.utils.WebDriverUtils;
import com.view.FunctionUI;
import com.view.Navigator;
import com.view.SelectorsUI;
import com.view.TSSystemUI;
import com.view.WebElementWrapper;


/**
 * 
 * @author martin.wang
 *
 */
public class User extends com.abstractclasses.TestObject{
	private String SignatureEnabled="",CourseTitle="",CourseID="", ExamStatus="", LearningPathImageFile = "", Name = "", Criteria = "",
			UserID = "", Role = "", DA = "", ExpectedResult = "",Template ="";

	public String getTemplate() {
		return Template;
	}


	public void setTemplate(String template) {
		Template = template;
	}


	public String getCriteria() {
		return Criteria;
	}


	public String getDA() {
		return DA;
	}


	public void setDA(String dA) {
		DA = dA;
	}


	public void setCriteria(String criteria) {
		Criteria = criteria;
	}


	public String getUserID() {
		return UserID;
	}
	
	public String getRole() {
		return Role;
	}
	
	
	public String getExpectedResult() {
		return ExpectedResult;
	}
	
	public void setExpectedResult(String expectedResult) {
		ExpectedResult = expectedResult;
	}

	public void setUserID(String userID) {
		UserID = userID;
	}


	public void setRole_UI(WebDriver driver, String str){
		if(!str.equals("")){
			By by = By.id("ROL");
			WebDriverUtils.select_selector(driver, by, str);
		}
	}
	
	public void setDA_UI(WebDriver driver, String str){
		if(!str.equals("")){
			By by = By.id("DAP-button-id");
			WebDriverUtils.clickButton(driver, by);
			Navigator.explicitWait(1000);
			
			String criteria = this.getCriteria();
			if(!criteria.equals("")){
				//set DA based on org. attr.
				by = By.xpath("//span[text()='Specify Additional Attributes']");
				WebDriverUtils.clickLink(driver, by);
				Navigator.explicitWait(1000);
				
				by = By.xpath("//div[@class='action-nav status-header']/span[descendant::h3[text()='"+Labels.Link_Org_Attrs+"']]");
				FunctionUI.setParticipants_UI(driver, criteria, by);
				
				Navigator.explicitWait(2000);
				by = By.xpath("//span[text()='Search']");
				WebDriverUtils.clickButton(driver, by);
				Navigator.explicitWait(2000);
				
				//check user exists
				by = By.xpath("//select[@id='avlParmSelector']/option[contains(text(), '" + str.toUpperCase() + "')]");				
				int size = WebDriverUtils.getHowManyByPresntInPage(driver,by, true);
				JUnitAssert.assertTrue(size>0, "Cannot find DA:" + str);
				
			}else{
				ArrayList<String> keywords = new ArrayList<String>();
				keywords.add(str);
				SelectorsUI.PopUp_Selector(driver, SelectorsUI.PopUpSelector.InnerUserSelector, keywords);
			}
			
		}
	}
	
	
	public void setRole(String role) {
		Role = role;
	}


	public String getName() {
		return Name;
	}


	public void setName(String name) {
		Name = name;
	}


	public User(){
		super();
	}

	
	public User(String UID, String PWD){
		this.setUID(UID);
		this.setPWD(PWD);
	}
	
	
	public String getExamStatus() {
		return ExamStatus;
	}


	public void setExamStatus(String examStatus) {
		ExamStatus = examStatus;
	}


	public boolean equals(TestObject obj){
		return true;
	}

	public void checkExpectedResult_UI(WebDriver driver, String expectedResult){
		super.checkExpectedResult_UI(driver, expectedResult);
	}
	
	public String getSignatureEnabled(){
		return SignatureEnabled;
	}

	public String getCourseTitle(){
		return CourseTitle;
	}

	public String getCourseID(){
		return CourseID;
	}

	public void setSignatureEnabled(String signatureenabled){
		SignatureEnabled=signatureenabled;
	}

	public void setCourseTitle(String coursetitle){
		CourseTitle=coursetitle;
	}

	public void setCourseID(String courseid){
		CourseID=courseid;
	}

	public void setSignatureEnabled_UI(WebDriver driver, String str){
	}

	public void setCourseTitle_UI(WebDriver driver, String str){
	}

	public void setCourseID_UI(WebDriver driver, String str){
	}

	
	public void runMarkExam(WebDriver driver){
		String CID = "Onln_StdE_1FixSec1_UMA";
	    String EID = "StdE_1FixSec1_UMA";
	    // Search course
	    By by = By.linkText("My Current Courses");
	    WebDriverUtils.clickLink(driver, by);
	    
	    by = By.xpath("//li[descendant::div/h4[contains(text(),'" + CID + "')]]/div[@class='module-actions']/a[contains(text(),'Knowledge Center')]/i");
	    WebDriverUtils.clickLink(driver, by);
	    
	    by = By.xpath("//a[contains(text(),'" + EID + "')]");
	    WebDriverUtils.clickLink(driver, by);

	    by = By.xpath("//input[contains(@name,'Button')]");
	    WebDriverUtils.clickButton(driver, by);
	    
	    by = By.id("submitbutton");
	    WebDriverUtils.clickButton(driver, by);

	    by = By.id("A1_3");
	    WebDriverUtils.clickButton(driver, by);

	    by = By.id("submitbutton");
	    WebDriverUtils.clickButton(driver, by);

	    by = By.xpath("//select[@name='A2']");
	    String str = "True";
	    WebDriverUtils.select_selector(driver, by, str);

	    by =By.id("submitbutton");
	    WebDriverUtils.clickButton(driver, by);

	    by = By.id("submitbutton");
	    WebDriverUtils.clickButton(driver, by);

	    JUnitAssert.assertTrue(WebDriverUtils.closeAlertAndGetItsText().equalsIgnoreCase(Labels.Btn_test_attempt_Confirm.toString()),Labels.Btn_test_attempt_Confirm.toString());
	    
	    
	    by = By.name("closeButton");
	    WebDriverUtils.clickButton(driver, by);
	}
	
	
	/**Self-enroll module via catalog search
	 * 
	 * @param driver
	 * 
	 * There are three condition for enrollment, the default one is successful enrollment; 
	 * another two are : re-enrollment(Enrolled) and failed enrollment(failed).
	 * if your expected result is one of the last two, change the ExpectedResult column in 
	 * excel sheet.
	 * Note: check the input of ExpectedResult.
	 */
	public void runEnrollCourse(WebDriver driver){
		
		By by = By.name("KEYW");
		String str = this.getCourseID();
		WebDriverUtils.fillin_textbox(driver, by, str);

		by = By.name("SEARCH");
		WebDriverUtils.clickButton(driver, by);
	    
	    Navigator.explicitWait(3000);

	    by = By.xpath("//a[em[contains(text(), '" + this.getCourseID() + "')]]");
	    WebDriverUtils.clickLink(driver, by);
	    WebDriverUtils.switchToPopUpWin(driver);
	    
	    if(getExpectedResult().equalsIgnoreCase("Enrolled")){
	    	JUnitAssert.assertTrue(WebDriverUtils.textPresentInPage(driver, Labels.EnrolledMsg.toString()),Labels.EnrolledMsg.toString());
	    	//WebDriverUtils.textPresentInPage(driver, Labels.EnrolledMsg);
	    	//System.out.println(UID+" : " + Labels.EnrolledMsg);
	    }
	    else if(getExpectedResult().equalsIgnoreCase("Failed")){
	    	JUnitAssert.assertTrue(WebDriverUtils.textPresentInPage(driver, Labels.FailedMsg.toString()),Labels.FailedMsg.toString());
	    	//WebDriverUtils.textPresentInPage(driver, Labels.FailedMsg);
	    	//System.out.println(UID+": "+ Labels.FailedMsg);	
	    }
	    else if(getExpectedResult().equals("")){
	    	by = By.name("enroll");
			WebDriverUtils.clickButton(driver, by);
    
			by = By.xpath("/descendant::*[@value='Confirm enrollment']");
			WebDriverUtils.clickButton(driver, by);
	    }
	    //else if (!getExpectedResult().equals("Failed") && !getExpectedResult().equals("Enrolled")&& getExpectedResult().equals("")){
	    //	try
		//	{
		//		by = By.name("enroll");
		//		WebDriverUtils.clickButton(driver, by);
	    //
		//		by = By.xpath("/descendant::*[@value='Confirm enrollment']");
		//		WebDriverUtils.clickButton(driver, by);	
		//	}
		//	catch (IllegalArgumentException e)
		//	{
		//		e.printStackTrace();// TODO: handle exception
		//	}
	    	
	    //}
	}
	

	

	public void login(WebDriver driver, String URL, String UID, String PWD){	 
		WebDriverUtils.openURL(driver, URL);
		By by = By.id("UID");
		WebDriverUtils.fillin_textbox(driver, by, UID);

		by = By.id("PWD");
		WebDriverUtils.fillin_textbox(driver, by, PWD);

		by = By.name("login");
		WebDriverUtils.clickButton(driver, by);
		
	/*	WebDriverUtils.acceptAlert(driver);
		Navigator.explicitWait(1000);*/
	}



	/**Login as a default user in config.properties
	 * 
	 * @param driver
	 */
	public void login(WebDriver driver){	  
		String URL =  Config.getInstance().getProperty("loginURL");
		this.login(driver, URL, this.UID, this.PWD);
		Locale userLocale= this.getLogonDBUser().getUser_Locale();
		Config.getInstance().setUserLocale(userLocale);
		
		//System.out.println("UserLocale:"+ Config.getInstance().getUserLocale());
	}

	public void logout(WebDriver driver){
		WebDriverUtils.closeAllPopUpWins(driver);
		Navigator.waitForPageLoad(driver);
		By by = By.linkText(Config.getInstance().getProperty("skin.logout"));
		WebDriverUtils.clickLink(driver, by);
	}
	

	
	public String getLearningPathImageFile() {
		return LearningPathImageFile;
	}


	public void setLearningPathImageFile(String learningPathImageFile) {
		LearningPathImageFile = learningPathImageFile;
	}


/*	public String toString(){
		StringBuilder sb =  new StringBuilder().
				append(this.getClass().getName()).
				append("_").
				append(this.getFuncType()).
				append("_").
				append(this.getUID());
		if(!this.getCourseID().equalsIgnoreCase("")){
			sb.append("_").append(this.getCourseID());
		}
		
		return sb.toString();
	}*/
	
	/**Checkout user's learning path
	 * 
	 * @param driver
	 */
	public void runCheckLearningPath(WebDriver driver){
		//1. Goto My Learning Path
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
				"LearningCenter", "LearningPath"));
		
		Navigator.explicitWait(1000);
		//2. Sikuli: Image-based comparison
		String screenshotFile = this.getLearningPathImageFile();
		boolean exist = SikuliUtils.screenshotExistInWin(screenshotFile);
		JUnitAssert.assertTrue(exist, "Cannot find image:" + screenshotFile);
	}
	
	public void searchUser(WebDriver driver, String UserID){
		//1. Click search
		WebDriverUtils.switchToFrame(driver, "USERMENU");
		Navigator.explicitWait(1000);
		By by = By.xpath("//img[@alt='Search']");
		WebDriverUtils.clickLink(driver, by);
				
		//2. Search users
		WebDriverUtils.switchToFrame(driver, "USERMAIN");
		Navigator.explicitWait(1000);
		by = By.id("PID");		
		WebDriverUtils.fillin_textbox(driver, by, UserID);
		by = By.xpath("//input[@value='Search']");
		WebDriverUtils.clickButton(driver, by);

	}
	
	public void runCheckVisibility(WebDriver driver){
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
				"ManageCenter", "UserEditor"));
		WebDriverUtils.switchToPopUpWin(driver);
		//HashMap<String, ArrayList<String>> criteria_users = UIFunctionUtils.parseParticipants(this.getExpectedResult());
		HashMap<String, ArrayList<String>> criteria_users =CriteriaParser.parseKeyValueList(":", ";", this.getExpectedResult()); 
		Iterator<String> criteria = criteria_users.keySet().iterator();
		while(criteria.hasNext()){
			String criterion = criteria.next();
			ArrayList<String> users = criteria_users.get(criterion);
			for(String user: users){
				//2.1 search user
				this.searchUser(driver, user);
				
				//2.2 check user visibility
				WebDriverUtils.switchToFrame(driver, "USERMAIN");
				Navigator.explicitWait(1000);
				By by = By.xpath("//tr/td[text()='" + user + "']");
				int size;
				switch(criterion.toLowerCase()){
				case "visible":
					Navigator.waitForAjax(driver, by);
					size = WebDriverUtils.getHowManyByPresntInPage(driver,by, true);
					JUnitAssert.assertTrue(size > 0, "User is invisible:" + user);
					break;
				case "invisible":
					size = WebDriverUtils.getHowManyByPresntInPage(driver,by, false);
					JUnitAssert.assertTrue(size == 0, "User is visible:" + user);
					break;
				}	
			}
		}
		
		WebDriverUtils.closeAllPopUpWins(driver);
	}
	
	
	
	public void runUpdate(WebDriver driver){
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
				"ManageCenter", "UserEditor"));
		WebDriverUtils.switchToPopUpWin(driver);
		//1. Search user
		this.searchUser(driver, this.getUserID());
		
		//2. click user
		WebDriverUtils.switchToFrame(driver, "USERMAIN");
		Navigator.explicitWait(1000);
		By by = By.xpath("//tr[descendant::td[text()='" + this.getUserID() + "']]/td[1]/a");
		WebDriverUtils.clickLink(driver, by);
		Navigator.explicitWait(1000);
		
		// Update user
		this.setRole_UI(driver, this.getRole());
		this.setDA_UI(driver, this.getDA());
		if(this.getTemplate().equalsIgnoreCase("enable")){
			by = By.id("PROFILE");
			WebDriverUtils.check_checkbox(driver, by);
		} else if(this.getTemplate().equalsIgnoreCase("disable")){
			by = By.id("PROFILE");
			WebDriverUtils.uncheck_checkbox(driver, by);
		} else {
			//do nothing
		}
		//3. Save changes
		WebDriverUtils.switchToFrame(driver, "USERMENU");
		Navigator.explicitWait(1000);
		by = By.xpath("//img[@alt='Save']");
		WebDriverUtils.clickLink(driver, by);
		
		//check "You do not have the authority to change to this role"
		
		//4. close pop-up window
		WebDriverUtils.closeAllPopUpWins(driver);
	}
	
	public void runUniversalSearch(WebDriver driver){
		if(!this.getSysConf().equals("")){
			User login_user = new User(Config.getInstance().getProperty("ndadmin"), Config.getInstance().getProperty("ndadmin.pass"));
			TestDriver.switchUser(login_user);
			TSSystemUI.setupSystemConf(driver, this.getSysConf());
			
			login_user = new User(this.getUID(), this.getPWD());
			TestDriver.switchUser(login_user);
		}
		
		
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
				"LearningCenter", "Search"));
		
	
		By by = By.id("ajaxInlineSearchBox_input");		
		WebDriverUtils.fillin_textbox(driver, by, this.getCourseID());
		by = By.xpath("//button[@name='SEARCH' and @role='button']");
		WebDriverUtils.clickButton(driver, by);
		
		//HashMap<String, ArrayList<String>> criteria_fields = UIFunctionUtils.parseParticipants(this.getExpectedResult());
		HashMap<String, ArrayList<String>> criteria_fields = CriteriaParser.parseKeyValueList(":", ";", this.getExpectedResult()); 
		Iterator<String> criteria = criteria_fields.keySet().iterator();
		while(criteria.hasNext()){
			String criterion = criteria.next();
			ArrayList<String> fields = criteria_fields.get(criterion);
			for(String field: fields){
				//2.2 check field visibility
				switch(criterion.toLowerCase()){
				case "visible":
					JUnitAssert.assertTrue(WebDriverUtils.textPresentInCatalogSearch(driver, field), "Cannot find:" + field);
					break;
				case "invisible":
					JUnitAssert.assertTrue(!WebDriverUtils.textPresentInCatalogSearch(driver, field), "Can find:" + field);
					break;
				}	
			}
		}
	}
	
	/**Empty body since it's a test suite
	 * 
	 * @param driver
	 */
	public void runCheckUniversalSearchFormat(WebDriver driver){
		
	}
	
	/**Empty body since it's a test suite
	 * 
	 * @param driver
	 */
	public void runCheckSessionSearchResult(WebDriver driver){
		
	}
	
	
	public void runCheckSessionSearch(WebDriver driver){
		if(!this.getSysConf().equals("")){
			User login_user = new User(Config.getInstance().getProperty("ndadmin"), Config.getInstance().getProperty("ndadmin.pass"));
			TestDriver.switchUser(login_user);
			TSSystemUI.setupSystemConf(driver, this.getSysConf().split("\n")[0]);
			
			login_user = new User(this.getUID(), this.getPWD());
			TestDriver.switchUser(login_user);
		}
		
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
				"LearningCenter", "Search"));
		
		//1. Search All
		By by = By.id("ajaxInlineSearchBox_input");		
		WebDriverUtils.fillin_textbox(driver, by, this.getCourseID());
		by = By.xpath("//button[@name='SEARCH' and @role='button']");
		WebDriverUtils.clickButton(driver, by);
		
		//2. Narrow search result
		//HashMap<String, ArrayList<String>> criteria_values = UIFunctionUtils.parseParticipants(this.getCriteria());
		
		HashMap<String, ArrayList<String>> criteria_values =CriteriaParser.parseKeyValueList(":", ";", this.getCriteria()); 
		Iterator<String> criteria = criteria_values.keySet().iterator();
		
		while(criteria.hasNext()){
			String criterion = criteria.next();
			ArrayList<String> values = criteria_values.get(criterion);
			for(String value: values){
				//2.2 check field visibility
				switch(criterion.toLowerCase()){
					case "location":
						by=By.xpath("//li/label[contains(text(),'"+value+"')]/input");
						WebDriverUtils.check_checkbox(driver, by);
						break;
					case "learningtype":
						by=By.xpath("//li/label[contains(text(),'"+value+"')]/input");
						WebDriverUtils.check_checkbox(driver, by);
						break;
					default:
						break;
				}	
				
			}
		}
		
		
		//Set pagination
		//	By by = By.id("resultsPerPage");
		//	WebDriverUtils.select_selector(driver, by, "All");
			
		//4. Check result
		//HashMap<String, ArrayList<String>> criteria_fields = UIFunctionUtils.parseParticipants(this.getExpectedResult());
		HashMap<String, ArrayList<String>> criteria_fields =CriteriaParser.parseKeyValueList(":", ";", this.getExpectedResult()); 
		criteria = criteria_fields.keySet().iterator();
		while(criteria.hasNext()){
			String criterion = criteria.next();
			ArrayList<String> fields = criteria_fields.get(criterion);
			for(String field: fields){
				//2.2 check field visibility
				switch(criterion.toLowerCase()){
				case "visible":
					JUnitAssert.assertTrue(WebDriverUtils.textPresentInCatalogSearch(driver, field), "Cannot find:" + field);
					break;
				case "invisible":
					JUnitAssert.assertTrue(!WebDriverUtils.textPresentInCatalogSearch(driver, field), "Can find:" + field);
					break;
				}	
			}
		}
		if(!this.getSysConf().equals("")){
			User login_user = new User(Config.getInstance().getProperty("ndadmin"), Config.getInstance().getProperty("ndadmin.pass"));
			TestDriver.switchUser(login_user);
			TSSystemUI.setupSystemConf(driver, this.getSysConf().split("\n")[1]);
			
			login_user = new User(this.getUID(), this.getPWD());
			TestDriver.switchUser(login_user);
		}
	}

}