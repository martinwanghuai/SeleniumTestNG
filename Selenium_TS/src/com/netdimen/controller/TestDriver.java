package com.netdimen.controller;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

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
import com.google.common.collect.Lists;
import com.netdimen.abstractclasses.TestObject;
import com.netdimen.annotation.NetDTestWatcher;
import com.netdimen.annotation.NetdTestRule;
import com.netdimen.annotation.TimeLogger;
import com.netdimen.config.Config;
import com.netdimen.dao.ExcelSheetObject;
import com.netdimen.junit.ScreenShotOnFailed;
import com.netdimen.junit.TestReport;
import com.netdimen.model.User;
import com.netdimen.sql.DBManager;
import com.netdimen.utils.POIUtils;
import com.netdimen.utils.PropertiesFileUtils;
import com.netdimen.utils.ReflectionUtils;
import com.netdimen.utils.WebDriverUtils;

/**
 * Starting point for all test cases. This test driver do the following things
 * in order: 1. Load test cases from Excel: 1.a. read EKPMain page to load
 * sheetName (=test object name, indicate which class should be tested) ->
 * FuncType (= methodName, indicate which method should be tested) pairs 1.b.
 * for each sheet in Step 1.a, filter rows based on FuncType. Each row is mapped
 * into test object via Java Reflection; 1.c. save all test objects into HashMap
 * for further reference 2. Execute test cases: 2.a. For each test object, check
 * whether it needs to switch users 2.b. For each test object, only execute
 * specific method (specified by FuncType); 2.c. If test fails, take screenshot
 * and re-login the system;
 * 
 * @author martin.wang
 *
 */
@RunWith(Parameterized.class)
public class TestDriver {

	private static WebDriver driver;

	private final TestObject testObject;

	private static TestObject curentTestObject;

	private static User user_current;

	private static int intFailCases = 0;

	public static DBManager dbManager = new DBManager();

	private static HashMap<String, TestObject> ID_testObjects = new HashMap<String, TestObject>();

	public TestDriver(final TestObject testObject, final String objID) {

		this.testObject = testObject;
		curentTestObject = testObject;
	}

	public static void addTestObject(final String ID, final TestObject obj) {

		if (ID_testObjects.containsKey(ID)) {
				System.out.println("Duplicate ID:" + ID);
		} else {
			ID_testObjects.put(ID, obj);
		}
	}

	public static TestObject getTestObject(final String ID) {

		TestObject obj_tmp = null;
		if (ID_testObjects.containsKey(ID)) {
			obj_tmp = ID_testObjects.get(ID);
		}

		return obj_tmp;
	}

	public static TestObject getCurrentTestObject() {

		return curentTestObject;
	}

	public static void setCurrentTestObject(final TestObject obj) {

		curentTestObject = obj;
	}

	@BeforeClass
	public static void setUp() throws Exception {

		driver = WebDriverUtils.getWebDriver_new();
		try {
			final File file = new File(Config.getInstance()
					.getProperty("test.result"));
			if (file.delete()) {
				System.out.println(file.getAbsolutePath() + " is deleted!");
			} else {
				System.out.println("Delete " + file.getAbsolutePath()
						+ " operation fails.");
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	private static int totalTestSuite = 0;

	// Transform each row in excel into java object
	@Parameterized.Parameters(name = "{1}")
	// name = "{1}"=Use TestObject.toString() as test case name
	public static Collection<Object[]> data() {

		final Collection<Object[]> objList = new ArrayList<Object[]>();

		FileInputStream file = null;
		try {
			file = new FileInputStream(Config.getInstance().getProperty(
					"testDataFile"));
			final HSSFWorkbook wb = new HSSFWorkbook(file);

			// load all tests: all test cases are configured in EKPMain page
			final String sheetName_main = "EKPMain";
			final int dataRowIndex_start = 1;

			HSSFSheet sheet = wb.getSheet(sheetName_main);

			for (final ExcelSheetObject excelSheetObj : POIUtils
					.getExcelSheetObjectFromExcel(sheet, dataRowIndex_start)) {

				sheet = wb.getSheet(excelSheetObj.getSheetName());
				if (sheet == null) {
					System.out.println("Cannot find file:" + sheetName_main);
					continue;
				}

				final List<Integer> rowNumsToScan = Lists.newArrayList();
				boolean hasSpecifyRowNum = false;
				if (excelSheetObj.getRowNum().equals("")) {
					hasSpecifyRowNum = false;
					for (int i = 2; i <= sheet.getPhysicalNumberOfRows(); i++) {
						rowNumsToScan.add(i);
					}
				} else {
					hasSpecifyRowNum = true;
					for (final String rowNum_str : excelSheetObj.getRowNum().split(";")) {
						rowNumsToScan.add(Integer.parseInt(rowNum_str));
					}
				}
				addTestCases(objList, wb, sheet, excelSheetObj, rowNumsToScan,
						hasSpecifyRowNum);
			}
		} catch (final FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (final IOException ex) {
			ex.printStackTrace();
		} finally {
			if (file != null) {
				try {
					file.close();
				} catch (final IOException ex) {
					ex.printStackTrace();
				}
			}
		}

		totalTestSuite = objList.size();
		return objList;
	}

	private static Collection<Object[]> addTestCases(
			final Collection<Object[]> objList, final HSSFWorkbook wb, final HSSFSheet sheet,
			final ExcelSheetObject excelSheetObj, final List<Integer> rowNumsToScan,
			final boolean hasSpecifyRowNum) {

		boolean found = false;
		for (final Integer rowNum : rowNumsToScan) {
			final TestObject obj = POIUtils.loadTestCaseFromExcelRow(excelSheetObj, wb);
			found = addTestCaseToList(objList, obj);
			if (obj != null) {
				obj.setLabel(excelSheetObj.getLabel());
				obj.setAuthor(excelSheetObj.getAuthor());
			} else if (hasSpecifyRowNum) {
				System.out.println("TestDriver: data()-2:Cannot find method:"
						+ excelSheetObj.getFuncName() + " in sheet:"
						+ excelSheetObj.getSheetName() + " row:" + rowNum);
			}
		}

		if (!found && !hasSpecifyRowNum) {
			System.out.println("TestDriver: data()-1: Cannot find method:"
					+ excelSheetObj.getFuncName() + " in sheet:"
					+ excelSheetObj.getSheetName());
		}

		return objList;
	}

	private static boolean addTestCaseToList(final Collection<Object[]> objList,
			final TestObject obj) {

		boolean found = false;
		if (obj != null & !objList.contains(obj)) {
			objList.add(new Object[] { obj, obj.toString() });
			addTestObject(obj.getID(), obj);
			found = true;
		}

		return found;
	}

	@NetdTestRule
	public TimeLogger logger = new TimeLogger(driver);

	@Rule
	public TestReport testReport = new TestReport(driver);

	@Rule
	public ScreenShotOnFailed screenShootRule = new ScreenShotOnFailed(driver);

	public static void switchUser(final TestObject testObject) {

		try {
			String testObject_UID = testObject.getUID();
			if (testObject_UID.equals("")) {// not setup -> defined in super
											// class.
				final String fieldName = "UID";
				// Search it in super class.ie: Online>LearningModule>TestObject
				final Field UID_field = ReflectionUtils.getField_superClz(
						testObject.getClass(), fieldName);
				if (UID_field != null) {
					UID_field.setAccessible(true);
					testObject_UID = (String) UID_field.get(testObject);
				} else {
					// do not switch user: set as default user
					user_current.setUID(Config.getInstance().getProperty(
							"sys.ndadmin"));
					user_current.setPWD(Config.getInstance().getProperty(
							"sys.ndadmin.pass"));
					testObject_UID = user_current.getUID();
				}
			}
			// Initialized logon user object when first time start up
			if (TestDriver.getUser_current() == null) {
				final User user = new User(testObject.getUID(), testObject.getPWD());
				user.login(driver);
				TestDriver.setUser_current(user);
			} else {
				final User logonUser = TestDriver.getUser_current();
				if (!testObject_UID.equalsIgnoreCase(logonUser.getUID())) {
					// different user, then logout first
					logonUser.logout(driver);
					final User user = new User(testObject.getUID(),
							testObject.getPWD());
					user.login(driver);
					TestDriver.setUser_current(user);
				}
			}

		} catch (final SecurityException e) {
			e.printStackTrace();
		} catch (final IllegalArgumentException e) {
			e.printStackTrace();
		} catch (final IllegalAccessException e) {
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
				// 5. do tasks after execution, will not reach this code if
				// exception occur
				logger.succeeded(testObject);
			}
		} catch (final IllegalAccessException e) {
			handFailCaseReporting(e, testObject);
		} catch (final NoSuchMethodException e) {
			handFailCaseReporting(e, testObject);
		} catch (final StaleElementReferenceException e) {
			handFailCaseReporting(e, testObject);
		} catch (final WebDriverException e) {
			handFailCaseReporting(e, testObject);
		} catch (final RuntimeException e) {
			handFailCaseReporting(e, testObject);
		} catch (final InvocationTargetException e) {
			handFailCaseReporting(e, testObject);
		} finally {
			// 6 do finish task
			logger.finished(testObject);
		}

	}

	private void handFailCaseReporting(final Exception e, final TestObject obj) {

		intFailCases++;
		POIUtils.filterDebugMsg(e, testObject);
		logger.failed(e, obj);
		fail(Throwables.getRootCause(e).toString());

	}

	private ArrayList<Field> getAnnotationOfNetdTestRule() {

		final Field[] fields = TestDriver.class.getDeclaredFields();
		final ArrayList<Field> list = new ArrayList<Field>();
		for (final Field field : fields) {
			final Annotation[] annotations = field.getDeclaredAnnotations();
			for (final Annotation annotation : annotations) {
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
	public boolean skipNonScheduled(final TestObject testObj) {

		boolean skipTest = false;
		final ArrayList<Field> fields = getAnnotationOfNetdTestRule();
		// 1. Invoke listeners before execution: only concern about listeners
		// annotated with "NetdTestRule" and is subclass of "NetDTestWatcher"
		for (final Field field : fields) {

			final Class<NetDTestWatcher> fieldClz = (Class<NetDTestWatcher>) field
					.getType();
			Object fieldObj;
			try {
				fieldObj = fieldClz.newInstance();

				// 1. judge whether can skip a class
				final NetDTestWatcher watchDog = (NetDTestWatcher) fieldObj;

				if (watchDog.isSkipClass(testObj)) {
					skipTest = true;
				} else {

					final String methodName = testObj.getFuncType();

					if (!methodName.equals("")) {
						final ArrayList<TestObject> objectParams = testObj
								.getObjectParams();

						Method method = null;
						if (objectParams.size() == 0) {
							method = testObj.getClass().getMethod(methodName,
									WebDriver.class);
						} else {
							method = testObj.getClass().getMethod(methodName,
									WebDriver.class, ArrayList.class);
						}
						// 2. judge whether can skip a method
						if (watchDog.isSkipMethod(method)) {
							skipTest = true;
						}
					} else {
						if (Config.DEBUG_MODE) {
							System.out.println("methodName:"
									+ testObj.getFuncType()
									+ "() is not defined in class:"
									+ testObj.getClass().getName());
						}
					}
				}
			} catch (final InstantiationException e) {
				e.printStackTrace();
			} catch (final IllegalAccessException e) {
				e.printStackTrace();
			} catch (final NoSuchMethodException e) {
				e.printStackTrace();
			} catch (final SecurityException e) {
				e.printStackTrace();
			}
		}

		return skipTest;
	}

	/**
	 * Execute method(Test Case) specified by funcType based on the following
	 * rule: A. if field "TestSuite" != null, then ignore this method because it
	 * is a test suite and will execute test cases in order. B. if field
	 * "objectParam" != null, then pass objectParam field value as one param to
	 * invoke method. C. if field "TestSuite"==null and "objectParam" == null,
	 * then WebDriver.class is the only param to invoke the method.
	 * 
	 * @param testObject
	 *            : TestObject-typed instance
	 * @param driver
	 *            : Web Driver
	 * @return boolean test execute result
	 */
	private boolean executeTestMethod(final TestObject testObject, final WebDriver driver)
			throws Exception {

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

						method = testObject.getClass().getMethod(
								testObject.getFuncType(), WebDriver.class);
						method.invoke(testObject, driver);

						testReport.SaveSuccessTestReportToExcel();
						endtime = System.currentTimeMillis();
						System.out
								.println("\t Time used: "
										+ (endtime - startTime) / 1000
										+ " secs on test case:"
										+ testObject.toString());
						success = true;

					} else {
						// 3.2 if has object params, WebDriver and ObjectInput
						// are the params
						final StringBuilder sb = new StringBuilder();
						sb.append(testObject.toString());
						sb.append(" with object inputs:");
						for (final TestObject objectParam : testObject
								.getObjectParams()) {
							sb.append(System.lineSeparator() + "\t\"")
									.append(objectParam.toString())
									.append("\"");
						}
						totalExecution++;
						TestDriver.setCurrentTestObject(testObject);

						method = testObject.getClass().getMethod(
								testObject.getFuncType(), WebDriver.class,
								ArrayList.class);
						method.invoke(testObject, driver,
								testObject.getObjectParams());
						testReport.SaveSuccessTestReportToExcel();
						endtime = System.currentTimeMillis();
						System.out.println("\t Time used: "
								+ (endtime - startTime) / 1000
								+ " secs on test case:" + sb.toString());
						success = true;

					}
				} else {
					success = true;
				}

			} else {
				// 2.2 If "TestSuite" field is not empty, ignore test suite
				// but execute its test cases
				startTime = System.currentTimeMillis();
				final ArrayList<TestObject> testCases = testObject.getTestCaseArray();
				if (testCases != null) {
					System.out.println("Run test suite:\""
							+ testObject.toString() + "\" with test cases:");

					for (final TestObject testCase : testCases) {
						if (testCase != null) {
							// stored original ID
							final String ID = testCase.getID();
							// IMPORTANT: modify id for reporting purpose
							// only
							testCase.setID(testObject.getID() + "{"
									+ testCase.getID() + "}");
							final boolean testResult = executeTestMethod(testCase,
									driver);
							// reset back to original ID'
							testCase.setID(ID);
							if (!testResult) {
								System.out
										.println("ERROR: one test case fail, then skip all coming test cases in test suite");
								break;
							}
						}
					}
					endtime = System.currentTimeMillis();
					System.out.println("Time used: " + (endtime - startTime)
							/ 1000 + " secs on test suite:\""
							+ testObject.toString() + "\"");
					success = true;
				}
			}
		} else {
			if (Config.DEBUG_MODE) {
				System.out.println("methodName:" + testObject.getFuncType()
						+ "() is not defined in class:"
						+ testObject.getClass().getName());
			}
		}
		return success;

	}

	private static int totalExecution = 0;

	public static int getTotalExecution() {

		return totalExecution;
	}

	public static void setTotalExecution(final int totalExecution) {

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
		final Hashtable<String, String> properties = new Hashtable<String, String>();
		properties.put("Total.Cases", String.valueOf(getTotalExecution()));
		properties.put("Total.Pass.Cases",
				String.valueOf(getTotalExecution() - intFailCases));
		properties.put("Total.Fail.Cases", String.valueOf(intFailCases));
		PropertiesFileUtils.SaveAsPropertiesFile(Config.getInstance()
				.getProperty("test.result"), properties);

	}

	public static User getUser_current() {

		return user_current;
	}

	public static void setUser_current(final User user_current) {

		TestDriver.user_current = user_current;
	}

}
