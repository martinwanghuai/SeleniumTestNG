package com.netdimen.controller;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileInputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Hashtable;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

import com.google.common.base.Throwables;
import com.netdimen.abstractclasses.TestObject;
import com.netdimen.annotation.NetDTestWatcher;
import com.netdimen.annotation.NetdTestRule;
import com.netdimen.annotation.TimeLogger;
import com.netdimen.config.Config;
import com.netdimen.junit.ScreenShotOnFailed;
import com.netdimen.junit.TestReport;
import com.netdimen.model.User;
import com.netdimen.sql.DBManager;
import com.netdimen.utils.POIUtils;
import com.netdimen.utils.PropertiesFileUtils;
import com.netdimen.utils.ReflectionUtils;
import com.netdimen.utils.WebDriverUtils;

/**
 * Starting point for all test cases. This test driver do the following things in order: 1. Load test cases from Excel:
 * 1.a. read EKPMain page to load sheetName (=test object name, indicate which class should be tested) -> FuncType (=
 * methodName, indicate which method should be tested) pairs 1.b. for each sheet in Step 1.a, filter rows based on
 * FuncType. Each row is mapped into test object via Java Reflection; 1.c. save all test objects into HashMap for
 * further reference 2. Execute test cases: 2.a. For each test object, check whether it needs to switch users 2.b. For
 * each test object, only execute specific method (specified by FuncType); 2.c. If test fails, take screenshot and
 * re-login the system;
 * 
 * @author martin.wang
 *
 */
@RunWith(Parameterized.class)
public class TestDriver {

	private static WebDriver driver;

	private TestObject testObject;

	private static TestObject curentTestObject;

	private static User user_current;

	private static int intFailCases = 0;

	public static DBManager dbManager = new DBManager();

	private static HashMap<String, TestObject> ID_testObjects = new HashMap<String, TestObject>();

	public TestDriver(TestObject testObject, String objID) {

		this.testObject = testObject;
		curentTestObject = testObject;
	}

	public static void addTestObject(String ID, TestObject obj) {

		if (ID_testObjects.containsKey(ID)) {
			if (Config.DEBUG_MODE) {
				System.out.println("Duplicate ID:" + ID);
			}
		} else {
			ID_testObjects.put(ID, obj);
		}
	}

	public static TestObject getTestObject(String ID) {

		TestObject obj_tmp = null;
		if (ID_testObjects.containsKey(ID)) {
			obj_tmp = ID_testObjects.get(ID);
		}

		return obj_tmp;
	}

	public static TestObject getCurrentTestObject() {

		return curentTestObject;
	}

	public static void setCurrentTestObject(TestObject obj) {

		curentTestObject = obj;
	}

	@BeforeClass
	public static void setUp() throws Exception {

		driver = WebDriverUtils.getWebDriver_new();
		try {
			File file = new File(Config.getInstance().getProperty("test.result"));
			if (file.delete()) {
				System.out.println(file.getAbsolutePath() + " is deleted!");
			} else {
				System.out.println("Delete " + file.getAbsolutePath() + " operation is failed.");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static int totalTestSuite = 0;

	// Transform each row in excel into java object
	@Parameterized.Parameters(name = "{1}")
	// name = "{1}"=Use TestObject.toString() as test case name
	        public static Collection
	        data() {

		Collection<Object[]> objList = new ArrayList<Object[]>();

		try {
			FileInputStream file = new FileInputStream(Config.getInstance().getProperty("testDataFile"));
			HSSFWorkbook wb = new HSSFWorkbook(file);

			// load all tests: all test cases are configured in EKPMain page
			String sheetName_main = "EKPMain";
			int dataRowIndex_start = 1;
			int funcType_columnIndex = 0;
			int sheetName_columnIndex = 1;
			int rowNum_columnIndex = 2;
			int label_columnIndex = 3;
			int author_columnIndex = 4;

			HSSFSheet sheet = wb.getSheet(sheetName_main);

			ArrayList<String> funcTypes = POIUtils.getColumnFromExcel(sheet, funcType_columnIndex, dataRowIndex_start);

			ArrayList<String> sheetNames = POIUtils.getColumnFromExcel(sheet, sheetName_columnIndex, dataRowIndex_start, funcTypes.size());

			ArrayList<String> rowNums = POIUtils.getColumnFromExcel(sheet, rowNum_columnIndex, dataRowIndex_start, funcTypes.size());

			ArrayList<String> labels = POIUtils.getColumnFromExcel(sheet, label_columnIndex, dataRowIndex_start, funcTypes.size());

			ArrayList<String> authors = POIUtils.getColumnFromExcel(sheet, author_columnIndex, dataRowIndex_start, funcTypes.size());

			for (int j = 0; j < funcTypes.size(); j++) {
				String funcType = funcTypes.get(j);
				String sheetName = sheetNames.get(j);
				String rowNum = "", label = labels.get(j), author = authors.get(j);

				if (rowNums != null && rowNums.size() > j) {
					rowNum = rowNums.get(j);
				}

				sheet = wb.getSheet(sheetName);
				if (sheet != null) {
					int row = 1;

					if (rowNum.equals("")) {
						// Iterate through each rows one by one if not define
						// line number, skip row 1 as it is feild names
						boolean found = false;
						for (int i = 2; i <= sheet.getPhysicalNumberOfRows(); i++) {
							row = i;
							TestObject obj = POIUtils.loadTestCaseFromExcelRow(sheetName, funcType, row, wb);
							if (obj != null & !objList.contains(obj)) { // avoid
								                                        // duplicate
								                                        // test
								                                        // cases
								objList.add(new Object[] { obj, obj.toString() });
								addTestObject(obj.getID(), obj);
								found = true;
							}
							if (obj != null) {
								obj.setLabel(label);
							}
						}
						if (!found) {
							System.out.println("TestDriver: data()-1: Cannot find method:" + funcType + " in sheet:" + sheetName);
						}
					} else {
						// only iterate rows specified by rowLine
						String[] rowNum_array = rowNum.split(";");
						for (String rowNum_str : rowNum_array) {
							row = Integer.parseInt(rowNum_str);
							TestObject obj = POIUtils.loadTestCaseFromExcelRow(sheetName, funcType, row, wb);
							if (obj != null & !objList.contains(obj)) { // avoid
								                                        // duplicate
								                                        // test
								                                        // cases
								objList.add(new Object[] { obj, obj.toString() });
								addTestObject(obj.getID(), obj);
							}
							if (obj == null) {
								System.out.println("TestDriver: data()-2:Cannot find method:"
								                   + funcType
								                   + " in sheet:"
								                   + sheetName
								                   + " row:"
								                   + rowNum);
							} else {
								obj.setLabel(label);
							}
						}
					}
					file.close();
				} else {
					if (Config.DEBUG_MODE) {
						System.out.println("Cannot find file:" + sheetName_main);
					}
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		totalTestSuite = objList.size();
		return objList;
	}

	@NetdTestRule
	public TimeLogger logger = new TimeLogger(driver);

	@Rule
	public TestReport testReport = new TestReport(driver);

	@Rule
	public ScreenShotOnFailed screenShootRule = new ScreenShotOnFailed(driver);

	public static void switchUser(TestObject testObject) {

		try {
			String testObject_UID = testObject.getUID();
			if (testObject_UID.equals("")) {// not setup -> defined in super class.
				String fieldName = "UID";
				// Search it in super class.ie: Online>LearningModule>TestObject
				Field UID_field = ReflectionUtils.getField_superClz(testObject.getClass(), fieldName);
				if (UID_field != null) {
					UID_field.setAccessible(true);
					testObject_UID = (String) UID_field.get(testObject);
				} else {
					// do not switch user: set as default user
					user_current.setUID(Config.getInstance().getProperty("sys.ndadmin"));
					user_current.setPWD(Config.getInstance().getProperty("sys.ndadmin.pass"));
					testObject_UID = user_current.getUID();
				}
			}
			// Initialized logon user object when first time start up
			if (TestDriver.getUser_current() == null) {
				User user = new User(testObject.getUID(), testObject.getPWD());
				user.login(driver);
				TestDriver.setUser_current(user);
			} else {
				User logonUser = TestDriver.getUser_current();
				if (!testObject_UID.equalsIgnoreCase(logonUser.getUID())) { // check if previous case use same user id
					// different user, then logout first
					logonUser.logout(driver);
					User user = new User(testObject.getUID(), testObject.getPWD());
					user.login(driver);
					TestDriver.setUser_current(user);
				}
			}

		}
		catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static int testSuiteNo = 1;

	// static HashMap<String, User> id_dbUsers = new HashMap<>();
	@Test
	public void test() throws Exception {

		// 1. print test case info (author, test case no.)
		System.out.print("(" + testSuiteNo + "/" + totalTestSuite + "):");
		testSuiteNo++;

		// 3. do tasks before execution
		logger.start(testObject);

		try {
			// 4. execute the test case
			if (executeTestMethod(testObject, driver)) {
				// 5. do tasks after execution, will not reach this code if exception occur
				logger.succeeded(testObject);
			}
		}
		catch (IllegalAccessException e) {
			handFailCaseReporting(e, testObject);
		}
		catch (NoSuchMethodException e) {
			handFailCaseReporting(e, testObject);
		}
		catch (StaleElementReferenceException e) {
			handFailCaseReporting(e, testObject);
		}
		catch (WebDriverException e) {
			handFailCaseReporting(e, testObject);
		}
		catch (RuntimeException e) {
			handFailCaseReporting(e, testObject);
		}
		catch (InvocationTargetException e) {
			handFailCaseReporting(e, testObject);
			// TODO Auto-generated catch block
		}
		finally {
			// 6 do finish task
			logger.finished(testObject);
		}

	}

	private void handFailCaseReporting(Exception e, TestObject obj) {

		intFailCases++;
		POIUtils.filterDebugMsg(e, testObject);
		logger.failed(e, obj);
		fail(Throwables.getRootCause(e).toString());

	}

	private ArrayList<Field> getAnnotationOfNetdTestRule() {

		Field[] fields = TestDriver.class.getDeclaredFields();
		ArrayList<Field> list = new ArrayList<Field>();
		for (Field field : fields) {
			Annotation[] annotations = field.getDeclaredAnnotations();
			for (Annotation annotation : annotations) {
				if (annotation instanceof NetdTestRule) {
					list.add(field);
				}
			}
		}
		return list;
	}

	/*
	 * Filter the test case not need to run with annotation schedule checking
	 */
	public boolean skipNonScheduled(TestObject testObj) {

		boolean skipTest = false;
		ArrayList<Field> fields = getAnnotationOfNetdTestRule();
		// 1. Invoke listeners before execution: only concern about listeners
		// annotated with "NetdTestRule" and is subclass of "NetDTestWatcher"
		for (Field field : fields) {

			Class<NetDTestWatcher> fieldClz = (Class<NetDTestWatcher>) field.getType();
			Object fieldObj;
			try {
				fieldObj = fieldClz.newInstance();

				// 1. judge whether can skip a class
				NetDTestWatcher watchDog = (NetDTestWatcher) fieldObj;

				if (watchDog.isSkipClass(testObj)) {
					skipTest = true;
				} else {

					String methodName = testObj.getFuncType();

					if (!methodName.equals("")) {
						ArrayList<TestObject> objectParams = testObj.getObjectParams();

						Method method = null;
						if (objectParams.size() == 0) {
							method = testObj.getClass().getMethod(methodName, WebDriver.class);
						} else {
							method = testObj.getClass().getMethod(methodName, WebDriver.class, ArrayList.class);
						}
						// 2. judge whether can skip a method
						if (watchDog.isSkipMethod(method)) {
							skipTest = true;
						}
					} else {
						if (Config.DEBUG_MODE) {
							System.out.println("methodName:" + testObj.getFuncType() + "() is not defined in class:" + testObj.getClass().getName());
						}
					}
				}
			}
			catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return skipTest;
	}

	/**
	 * Execute method(Test Case) specified by funcType based on the following rule: A. if field "TestSuite" != null,
	 * then ignore this method because it is a test suite and will execute test cases in order. B. if field
	 * "objectParam" != null, then pass objectParam field value as one param to invoke method. C. if field
	 * "TestSuite"==null and "objectParam" == null, then WebDriver.class is the only param to invoke the method.
	 * 
	 * @param testObject
	 *            : TestObject-typed instance
	 * @param driver
	 *            : Web Driver
	 * @return boolean test execute result
	 */
	private boolean executeTestMethod(TestObject testObject, WebDriver driver) throws Exception {

		boolean success = false;
		double startTime, endtime;
		if (testObject.getFuncType().length() > 0) {
			// 1. Switch user if new test case use different logon user
			TestDriver.switchUser(testObject);

			Method method = null;
			// 2.1 Execute method directly if no test suites

			if (testObject.getTestSuite().trim().length() == 0) {

				if (!skipNonScheduled(testObject)) {
					startTime = System.currentTimeMillis();
					if (testObject.getObjectParams().size() == 0) {
						// 3.1 if no object param, WebDriver is the only param
						totalExecution++;
						TestDriver.setCurrentTestObject(testObject);

						method = testObject.getClass().getMethod(testObject.getFuncType(), WebDriver.class);
						method.invoke(testObject, driver);

						testReport.SaveSuccessTestReportToExcel();
						endtime = System.currentTimeMillis();
						System.out.println("\t Time used: " + (endtime - startTime) / 1000 + " secs on test case:" + testObject.toString());
						success = true;

					} else {
						// 3.2 if has object params, WebDriver and ObjectInput
						// are the params
						StringBuilder sb = new StringBuilder();
						sb.append(testObject.toString());
						sb.append(" with object inputs:");
						for (TestObject objectParam : testObject.getObjectParams()) {
							sb.append(System.lineSeparator() + "\t\"").append(objectParam.toString()).append("\"");
						}
						totalExecution++;
						TestDriver.setCurrentTestObject(testObject);

						method = testObject.getClass().getMethod(testObject.getFuncType(), WebDriver.class, ArrayList.class);
						method.invoke(testObject, driver, testObject.getObjectParams());
						testReport.SaveSuccessTestReportToExcel();
						endtime = System.currentTimeMillis();
						System.out.println("\t Time used: " + (endtime - startTime) / 1000 + " secs on test case:" + sb.toString());
						success = true;

					}
				} else {
					success = true;
				}

			} else {
				// 2.2 If "TestSuite" field is not empty, ignore test suite
				// but execute its test cases
				startTime = System.currentTimeMillis();
				ArrayList<TestObject> testCases = testObject.getTestCaseArray();
				if (testCases != null) {
					System.out.println("Run test suite:\"" + testObject.toString() + "\" with test cases:");

					for (TestObject testCase : testCases) {
						if (testCase != null) {
							// stored original ID
							String ID = testCase.getID();
							// IMPORTANT: modify id for reporting purpose
							// only
							testCase.setID(testObject.getID() + "{" + testCase.getID() + "}");
							boolean testResult = executeTestMethod(testCase, driver);
							// reset back to original ID'
							testCase.setID(ID);
							if (!testResult) {
								System.out.println("ERROR: one test case fail, then skip all coming test cases in test suite");
								break;
							}
						}
					}
					endtime = System.currentTimeMillis();
					System.out.println("Time used: " + (endtime - startTime) / 1000 + " secs on test suite:\"" + testObject.toString() + "\"");
					success = true;
				}
			}
		} else {
			if (Config.DEBUG_MODE) {
				System.out.println("methodName:" + testObject.getFuncType() + "() is not defined in class:" + testObject.getClass().getName());
			}
		}
		return success;

	}

	private static int totalExecution = 0;

	public static int getTotalExecution() {

		return totalExecution;
	}

	public static void setTotalExecution(int totalExecution) {

		TestDriver.totalExecution = totalExecution;
	}

	@After
	public void deleteCookies() {

		// driver.manage().deleteAllCookies(); //need to re-login for the next
		// test case
	}

	@AfterClass
	public static void tearDown() throws Exception {

		// close the window that uses plugin container before driver.quit();
		Runtime.getRuntime().exec("taskkill /F /IM plugin-container.exe");
		System.out.println("Total test case run:" + getTotalExecution());
		System.out.println("Total test suite fail:" + intFailCases);
		System.out.println("Total test suite run:" + totalTestSuite);
		driver.manage().deleteAllCookies(); // clear cache
		driver.quit();
		Hashtable<String, String> properties = new Hashtable<String, String>();
		properties.put("Total.Cases", String.valueOf(getTotalExecution()));
		properties.put("Total.Pass.Cases", String.valueOf(getTotalExecution() - intFailCases));
		properties.put("Total.Fail.Cases", String.valueOf(intFailCases));
		PropertiesFileUtils.SaveAsPropertiesFile(Config.getInstance().getProperty("test.result"), properties);

	}

	public static User getUser_current() {

		return user_current;
	}

	public static void setUser_current(User user_current) {

		TestDriver.user_current = user_current;
	}

}
