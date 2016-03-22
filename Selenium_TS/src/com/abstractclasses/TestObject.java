package com.abstractclasses;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.WebDriver;

import com.config.Config;
import com.controller.TestDriver;
import com.dao.DBUser;
import com.dao.DBUserDAO;
import com.dao.ExcelSheetObject;
import com.google.common.collect.Lists;
import com.interfaces.ITestObject;
import com.junit.JUnitAssert;
import com.utils.POIUtils;
import com.utils.WebDriverUtils;

/**
 * Extended by all testing objects (which are defined in "com.netdimen.model"
 * package)
 * 
 * @author martin.wang
 *
 */
public abstract class TestObject implements ITestObject {

	protected String UID = "", PWD = "", FuncType = "", ExpectedResult = "",
			ID = "", TestSuite = "", ObjectInputs = "", Label = "",
			ScheduleTask = "", SysConf = "", author = "";

	public String getAuthor() {
		return author;
	}

	public void setAuthor(final String author) {
		this.author = author;
	}

	private DBUser logonDBUser;
	protected ArrayList<TestObject> testCaseArray = Lists.newArrayList();
	protected ArrayList<TestObject> objectParams = Lists.newArrayList();

	public String getUID() {
		return UID;
	}

	public void setUID(final String uID) {
		
		UID = uID;
		final DBUserDAO dbUserDAO = new DBUserDAO(TestDriver.dbManager.getConn());
		this.logonDBUser = dbUserDAO.findByUserId(UID.toLowerCase().trim());
	}

	public String getScheduleTask() {
		return ScheduleTask;
	}

	public void setScheduleTask(final String scheduleTask) {
		ScheduleTask = scheduleTask;
	}

	public String getSysConf() {
		return SysConf;
	}

	public void setSysConf(final String sysConf) {
		SysConf = sysConf;
	}

	public DBUser getLogonDBUser() {
		return logonDBUser;
	}

	public void setLogonDBUser(final DBUser logonUser) {
		this.logonDBUser = logonUser;
	}

	public String getLabel() {
		return Label;
	}

	public void setLabel(final String label) {
		Label = label;
	}

	public ArrayList<TestObject> getObjectParams() {
		return objectParams;
	}

	public void setObjectParams(final ArrayList<TestObject> objectParams) {
		this.objectParams = objectParams;
	}

	public ArrayList<TestObject> getTestCaseArray() {
		return testCaseArray;
	}

	public void setTestCaseArray(final ArrayList<TestObject> testCaseArray) {
		this.testCaseArray = testCaseArray;
	}

	public String getObjectInputs() {
		return ObjectInputs;
	}

	public void setObjectInputs(final String str) {
		ObjectInputs = str;
		this.setObjectParams(this.loadTestCases(ObjectInputs));
	}

	public String getTestSuite() {
		return TestSuite;
	}

	public void setTestSuite(final String testSuite) {
		TestSuite = testSuite;
		testCaseArray = this.loadTestCases(testSuite);
	}

	public TestObject() {
		UID = Config.getInstance().getProperty("sys.ndadmin");
		PWD = Config.getInstance().getProperty("sys.ndadmin.pass");
	}

	public String getID() {
		return ID;
	}

	public void setID(final String iD) {
		ID = iD;
	}

	@Override
	public String toString() {
		return this.ID + "-" + this.getUID();
	}

	/**
	 * 
	 * @param testCasesStr
	 *            eg: CDC:runDeployGoal_CDC:2\nCDC:runDeployGoal_CDC:3 seperated
	 *            by "\n" between cases Chained testobject creation will happen
	 *            eg. runCheckGoalLock(Goal)->runDeployGoal_CDC:2(CDC)->
	 *            PerformanceGoal:1(PerformanceGoal)
	 * @return
	 */

	public ArrayList<TestObject> loadTestCases(final String testCasesStr)
			throws RuntimeException {
		
		final ArrayList<TestObject> testCaseArray = Lists.newArrayList();
		final String[] testCases = testCasesStr.split("\n");

		try {
			final FileInputStream file = new FileInputStream(Config.getInstance()
					.getProperty("testDataFile"));
			final HSSFWorkbook wb = new HSSFWorkbook(file);

			for (final String testCase : testCases) {
				final String[] testCase_array = testCase.split(":");
				
				final ExcelSheetObject excelSheetObj = new ExcelSheetObject(
						testCase_array[1].trim(), testCase_array[2].trim(),
						testCase_array[0].trim(), "", "");
				
				// try to load the testcase from here for testsuite or
				// objectInputs
				final TestObject obj = POIUtils.loadTestCaseFromExcelRow(excelSheetObj, wb);
				if (obj == null) {
					throw new RuntimeException("<b>ERROR: loadTestCases In "
							+ this.getFuncType() + "-->CAN NOT Find "
							+ excelSheetObj.getFuncName() + " in " + excelSheetObj.getSheetName() + " row " + excelSheetObj.getRowNum()
							+ "<b/>");
				} else {
					testCaseArray.add(obj);
				}
			}
		} catch (final NumberFormatException e) {
			e.printStackTrace();
		} catch (final FileNotFoundException e) {
			e.printStackTrace();
		} catch (final IOException e) {
			e.printStackTrace();
		}

		return testCaseArray;
	}

	public abstract boolean equals(TestObject obj); // compare TestObject

	/**
	 * Failed if the page contains keyword "error"
	 * 
	 * @param driver
	 * @param expectedResult
	 */
	public void checkExpectedResult_UI(final WebDriver driver, final String expectedResult) {
		final String text = "Please contact the system administrator";
		JUnitAssert.assertTrue(!WebDriverUtils.textPresentInPage(driver, text),
				"EKP error was found in test case");
	}

	public String getPWD() {
		return PWD;
	}

	public void setPWD(final String pWD) {
		PWD = pWD;
	}

	public String getFuncType() {
		return FuncType.trim();
	}

	public void setFuncType(final String funcType) {
		FuncType = funcType;
	}

	@Override
	public void run(final WebDriver driver) {

	}

	public String getExpectedResult() {
		return ExpectedResult;
	}

	public void setExpectedResult(final String expectedResult) {
		ExpectedResult = expectedResult;
	}

	public static String genObjectID(final String sheetName, final int rowIndex) {
		return new StringBuilder().append(sheetName).append("_")
				.append(rowIndex).toString();
	}

	/**
	 * 
	 * @param sheetName
	 * @param funcName
	 * @param rowIndex: excel row is starting from 0, so +1 to represent the actual
	 *            row for human readable
	 * @return
	 */
	public static String genObjectID(final String sheetName, final String funcName,
			final int rowIndex) {
		return new StringBuilder().append(sheetName).append("_")
				.append(funcName).append("_").append(rowIndex + 1).toString();
	}

	/**
	 * 
	 * @param object
	 * @param defaultValue
	 * @return object if object is not null, otherwise return defaultValue
	 */
	public static final <T> T defaultIfNull(final T object, final T defaultValue) {
		
		return object == null ? defaultValue:object; 
	}
}
