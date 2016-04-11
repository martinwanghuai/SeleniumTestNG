package com.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.collections.Lists;

import com.abstractclasses.TestObject;
import com.config.Config;
import com.dao.ExcelSheetObject;
import com.utils.POIUtils;
import com.utils.WebDriverUtils;

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
public class TestDriverTestNG {

	private static WebDriver driver = WebDriverUtils.getWebDriver_new();

	@BeforeClass(alwaysRun = true)
	public void beforeClass() {

		try {
			final File file = new File(Config.getInstance().getProperty(
					"test.result"));
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

	@AfterClass(alwaysRun = true)
	public void afterClass() {

		try {
			// close the window that uses plugin container before driver.quit();
			Runtime.getRuntime().exec("taskkill /F /IM plugin-container.exe");
			driver.manage().deleteAllCookies(); // clear cache
			driver.quit();

		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	private static int testSuiteNo = 1;
	private static int totalTestSuite = 0;

	@Test(dataProvider = "param", dataProviderClass = TestDriverTestNG.class)
	public static void test(final TestObject testObj) throws Exception {

		// 1. print test case info (author, test case no.)
		System.out.print("(" + testSuiteNo + "/" + totalTestSuite + "):");
		testSuiteNo++;
	}

	@DataProvider(name = "param")
	public static Object[][] loadTestDataFromExcel() {

		final List<Object[]> testCaseList = Lists.newArrayList();

		FileInputStream file = null;
		try {
			file = new FileInputStream(Config.getInstance().getProperty(
					"testDataFile"));
			final HSSFWorkbook wb = new HSSFWorkbook(file);

			final String sheetName_main = "MainSheet";
			final int dataRowIndex_start = 1;

			HSSFSheet sheet = wb.getSheet(sheetName_main);

			for (final ExcelSheetObject excelSheetObj : POIUtils
					.getExcelSheetObjectFromExcel(sheet, dataRowIndex_start)) {

				sheet = wb.getSheet(excelSheetObj.getSheetName());
				if (sheet == null) {
					System.out.println("Cannot find sheet:"
							+ excelSheetObj.getSheetName());
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
					for (final String rowNum_str : excelSheetObj.getRowNum()
							.split(";")) {
						rowNumsToScan.add(Integer.parseInt(rowNum_str));
					}
				}
				addTestCases(testCaseList, wb, sheet, excelSheetObj,
						rowNumsToScan, hasSpecifyRowNum);
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

		final Object[][] results = new Object[testCaseList.size()][];
		for (int i = 0; i < testCaseList.size(); i++) {
			results[i] = testCaseList.get(i);
		}
		return results;
	}

	private static Collection<Object[]> addTestCases(
			final Collection<Object[]> testCaseList, final HSSFWorkbook wb,
			final HSSFSheet sheet, final ExcelSheetObject excelSheetObj,
			final List<Integer> rowNumsToScan, final boolean hasSpecifyRowNum) {

		boolean found = false;
		for (final Integer rowNum : rowNumsToScan) {
			final TestObject obj = POIUtils.loadTestCaseFromExcelRow(
					excelSheetObj, wb, rowNum);
			found = addTestCaseToList(testCaseList, obj);
			if (obj != null) {
				obj.setLabel(excelSheetObj.getLabel());
				obj.setAuthor(excelSheetObj.getAuthor());
			} else if (hasSpecifyRowNum) {
				System.out.println("TestDriver: Cannot find method:"
						+ excelSheetObj.getFuncName() + " in sheet:"
						+ excelSheetObj.getSheetName() + " row:" + rowNum);
			}
		}

		if (!found && !hasSpecifyRowNum) {
			System.out.println("TestDriver: Cannot find method:"
					+ excelSheetObj.getFuncName() + " in sheet:"
					+ excelSheetObj.getSheetName());
		}

		return testCaseList;
	}

	private static boolean addTestCaseToList(
			final Collection<Object[]> testCaseList, final TestObject obj) {

		boolean found = false;
		if (obj != null & !testCaseList.contains(obj)) {
			testCaseList.add(new Object[] { obj });
			found = true;
		}

		return found;
	}

}
