package com.netdimen.abstractclasses;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.netdimen.config.Config;
import com.netdimen.controller.TestDriver;
import com.netdimen.dao.DBUser;
import com.netdimen.dao.DBUserDAO;
import com.netdimen.interfaces.ITestObject;
import com.netdimen.junit.JUnitAssert;
import com.netdimen.utils.POIUtils;
import com.netdimen.utils.WebDriverUtils;
import com.netdimen.view.Navigator;

/**Extended by all testing objects (which are defined in "com.netdimen.model" package)
 * 
 * @author martin.wang
 *
 */
public abstract class TestObject implements ITestObject{
	protected String UID = "", PWD = "", FuncType = "", ExpectedResult = "", ID = "", TestSuite = "", 
			ObjectInputs = "", Label = "", ScheduleTask = "", SysConf = "";
	private DBUser logonDBUser;
	protected ArrayList<TestObject> testCaseArray = new ArrayList<TestObject>();
	protected ArrayList<TestObject> objectParams = new ArrayList<TestObject>();
	
	public String getUID() {
		return UID;
	}

	public void setUID(String uID) {
		UID = uID;
		DBUserDAO dbUserDAO = new DBUserDAO(TestDriver.dbManager.getConn());
		this.logonDBUser = dbUserDAO.findByUserId(UID.toLowerCase().trim());
	}
	
	public String getScheduleTask() {
		return ScheduleTask;
	}

	public void setScheduleTask(String scheduleTask) {
		ScheduleTask = scheduleTask;
	}

	public String getSysConf() {
		return SysConf;
	}

	public void setSysConf(String sysConf) {
		SysConf = sysConf;
	}

	public DBUser getLogonDBUser() {
		return logonDBUser;
	}


	public void setLogonDBUser(DBUser logonUser) {
		this.logonDBUser = logonUser;
	}


	public String getLabel() {
		return Label;
	}


	public void setLabel(String label) {
		Label = label;
	}
	
	public ArrayList<TestObject> getObjectParams() {
		return objectParams;
	}


	public void setObjectParams(ArrayList<TestObject> objectParams) {
		this.objectParams = objectParams;
	}


	public ArrayList<TestObject> getTestCaseArray() {
		return testCaseArray;
	}


	public void setTestCaseArray(ArrayList<TestObject> testCaseArray) {
		this.testCaseArray = testCaseArray;
	}


	public String getObjectInputs() {
		return ObjectInputs;
	}

	public void setObjectInputs(String str) {
		ObjectInputs = str;
		this.setObjectParams(this.loadTestCases(ObjectInputs));
	}

	public String getTestSuite() {
		return TestSuite;
	}

	public void setTestSuite(String testSuite) {
		TestSuite = testSuite;
		testCaseArray = this.loadTestCases(testSuite);
	}

	public TestObject(){
		UID = Config.getInstance().getProperty("sys.ndadmin"); 
		PWD = Config.getInstance().getProperty("sys.ndadmin.pass");
	}
	
	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String toString(){
		return this.ID+"-"+ this.getUID();
	} 
	/**
	 * 
	 * @param testCasesStr eg: CDC:runDeployGoal_CDC:2\nCDC:runDeployGoal_CDC:3
	 * seperated by "\n" between cases
	 * Chained testobject creation will happen eg. runCheckGoalLock(Goal)->runDeployGoal_CDC:2(CDC)->PerformanceGoal:1(PerformanceGoal)
	 * @return
	 */
	
	public ArrayList<TestObject> loadTestCases(String testCasesStr) throws RuntimeException{
		ArrayList<TestObject> testCaseArray = new ArrayList<TestObject>();
		String[] testCases = testCasesStr.split("\n");

		try {
			FileInputStream file = new FileInputStream(Config.getInstance()
					.getProperty("testDataFile"));
			HSSFWorkbook wb = new HSSFWorkbook(file);

			for (String testCase : testCases) {
				String[] testCase_array = testCase.split(":");
				String sheetName = testCase_array[0].trim();
				String funcType = testCase_array[1].trim();
				int rowNum = Integer.parseInt(testCase_array[2].trim());
				// try to load the testcase from here for testsuite or objectInputs
				TestObject obj = POIUtils.loadTestCaseFromExcelRow(sheetName,
						funcType, rowNum, wb);
				if (obj == null){
					throw new RuntimeException("<b>ERROR: loadTestCases In "+this.getFuncType()+"-->CAN NOT Find "+ funcType + " in " +sheetName +" row "+rowNum+"<b/>");
				}else{
					testCaseArray.add(obj);
				}
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return testCaseArray;
	}	
	
	public abstract boolean equals(TestObject obj); //compare TestObject

	/**Failed if the page contains keyword "error"
	 * 
	 * @param driver
	 * @param expectedResult
	 */
	public void checkExpectedResult_UI(WebDriver driver, String expectedResult){
		String text = "Please contact the system administrator";
		JUnitAssert.assertTrue(!WebDriverUtils.textPresentInPage(driver, text), "EKP error was found in test case");
//		WebDriverUtils.checkEKPError(driver);
	} 
	
	/** Get Current Logon user's org
	 * 
	 * @param driver
	 * @param UName
	 * @return
	 */
	public String getUserOrgPath_UI(WebDriver driver, String UName){
		//HomePage -> User Preference -> My Orgs 		  
		String orgPath = "";
		Navigator.navigate(driver,Navigator.xmlWebElmtMgr.getNavigationPathList("LearningCenter","HOME"), this);
		
		
		By by = By.xpath("//div[@class='sec-menu-container']/ul/li/a[@class='username']");
		WebDriverUtils.clickLink(driver, by);

		by = By.xpath("//div[@id='main-content']/div/ul/li[2]/a/span[contains(text(),'My Orgs')]");
		WebDriverUtils.clickLink(driver, by);

		by = By.xpath("//td/select/option[@selected=\"\"]");
		List<WebElement> elements = driver.findElements(by);

		StringBuilder sb = new StringBuilder();	  
		//ends with "UNASSIGNED"
		int size = elements.size();
		if(size > 2){
			//for "ALL/ORG1/UNASSIGNED", ignore the last one - "UNASSIGNED"
			for(int i = 0; i < elements.size() - 1; i++){
				WebElement element = elements.get(i);
				sb.append(element.getText()).append("/");  
			}  
		}else if(size == 2){
			//for "ALL/UNASSIGNED", inherite from "ALL"
			if(elements.get(1).getText().equalsIgnoreCase("UNASSIGNED")){
				sb.append(elements.get(0).getText()).append("/");
			}else{
				for(WebElement element: elements){
					sb.append(element.getText()).append("/");
				}	
			}
		}

		int index = sb.lastIndexOf("/");
		orgPath = sb.replace(index, index+1, "").toString();
		
		return orgPath;
	}	

	public String getPWD() {
		return PWD;
	}

	public void setPWD(String pWD) {
		PWD = pWD;
	}

	public String getFuncType() {
		return FuncType.trim();
	}

	public void setFuncType(String funcType) {
		FuncType = funcType;
	}


	public void run(WebDriver driver) {

	}
	
	public String getExpectedResult() {
		return ExpectedResult;
	}

	public void setExpectedResult(String expectedResult) {
		ExpectedResult = expectedResult;
	}
	public static String genObjectID(String sheetName, int rowIndex){
		return new StringBuilder().
				append(sheetName).
				append("_").
				append(rowIndex).
				toString();
	}
	/**
	 * 
	 * @param sheetName
	 * @param funcType
	 * @param rowIndex 
	 *        excel row is starting from 0, so +1 to represent the actual row for human readable
	 * @return
	 */
	public static String genObjectID(String sheetName, String funcType, int rowIndex){
		return new StringBuilder().
				append(sheetName).
				append("_").
				append(funcType).
				append("_").
				append(rowIndex+1).
				toString();
	}
	/**
	 * 
	 * @param object
	 * @param defaultValue
	 * @return object if object is not null, otherwise return defaultValue
	 */
	public static final <T> T defaultIfNull(final T object, final T defaultValue) {
		if (object == null)
			return defaultValue;
		else 
			return object;
	}
}
