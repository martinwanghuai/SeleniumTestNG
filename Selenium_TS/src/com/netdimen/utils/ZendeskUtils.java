package com.netdimen.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import com.netdimen.config.Config;
import com.netdimen.view.Navigator;

/**
 * 
 * @author martin.wang
 *
 */
public class ZendeskUtils {

	/**
	 * Download release notes template
	 * 
	 * @param destDir
	 * @param fileName
	 */
	public static void downloadReleaseNotes(String destDir, String fileName) {
		String uid = "martin.wang@netdimensions.com";
		String pwd = "abcd1234";
		String URL = "https://secure.gooddata.com/account.html?lastUrl=%252F#/login";

		// 1. Setup profile
		FirefoxProfile fxProfile = new FirefoxProfile();
		fxProfile.setPreference("browser.download.folderList", 2);// 0: desktop;
																	// 1:
																	// default
																	// dir; 2:
																	// browser.download.dir;
		fxProfile.setPreference("browser.download.dir", destDir);
		fxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk",
				"application/vnd.ms-excel");
		fxProfile.setPreference("browser.download.manager.showWhenStarting",
				false);
		WebDriver driver = WebDriverUtils.getWebDriver_new(fxProfile);

		// 2. Delete existing file before downloading
		String outputFileName = destDir + "\\" + fileName + ".xls";
		File file = new File(outputFileName);
		if (file.exists()) {
			file.delete();
		}

		// 2. Login
		WebDriverUtils.openURL(driver, URL);
		Navigator.explicitWait(3000);

		By by = By.xpath("//input[@type='email' and @name='email']");
		WebDriverUtils.fillin_textbox(driver, by, uid);
		Navigator.explicitWait(3000);

		by = By.xpath("//input[@type='password' and @name='password']");
		WebDriverUtils.fillin_textbox(driver, by, pwd);
		Navigator.explicitWait(3000);

		by = By.xpath("//button[descendant::span[contains(text(), 'Sign in')]]");
		WebDriverUtils.clickButton(driver, by);
		Navigator.explicitWait(8000);

		// 3. Save Reports
		by = By.linkText("Reports");
		WebDriverUtils.clickLink(driver, by);
		Navigator.explicitWait(8000);

		by = By.linkText(fileName);
		WebDriverUtils.clickLink(driver, by);
		Navigator.explicitWait(8000);

		by = By.xpath("//span[text()='Export']");
		WebDriverUtils.clickButton(driver, by);
		Navigator.explicitWait(8000);

		by = By.xpath("//a[text()='Excel XLS' and @href='xls']");
		WebDriverUtils.clickLink(driver, by);
		Navigator.explicitWait(8000);

		driver.quit();
	}

	/**
	 * Generete release notes for a specific ekp version
	 * 
	 * @param inputFileName
	 * @param columnName
	 * @param filterValue
	 *            : ekp version as a filter
	 * @param outputFileName
	 */
	public static void generateReleaseNotes(String inputFileName,
			String columnName, String filterValue, String outputFileName) {
		try {
			ArrayList<ArrayList<String>> results = new ArrayList<ArrayList<String>>();
			FileInputStream file = new FileInputStream(inputFileName);
			HSSFWorkbook wb = new HSSFWorkbook(file);

			// load all tests: all test cases are configured in EKPMain page
			int filterColumn = -1;
			for (int i = 0; i < wb.getNumberOfSheets(); i++) {
				HSSFSheet sheet = wb.getSheetAt(i);

				ArrayList<String> rowData = POIUtils.getRowFromExcel(sheet, 1);// labels
																				// row
				results.add(rowData);
				for (int j = 0; j < rowData.size(); j++) {
					String columnData = rowData.get(j);
					if (columnData.equals(columnName)) {
						filterColumn = j;
						break;
					}
				}

				for (int j = 2; j < sheet.getPhysicalNumberOfRows(); j++) {// data
																			// row
					Row row = sheet.getRow(j);

					if (j % 10 == 0) {
						System.out.println("Handling Row:" + j);
					}

					if (row != null) {
						ArrayList<String> rowData_tmp = POIUtils
								.getRowFromExcel(sheet, j);
						if (rowData_tmp != null) {
							String testData = rowData_tmp.get(filterColumn);
							if (testData.contains(filterValue)) {
								results.add(rowData_tmp);
							}
						}
					}
				}

				POIUtils.saveIntoExcel(outputFileName, sheet.getSheetName(),
						results);
			}
			file.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// 1. Manually Download release notes template, name it as
		// "All Fixes Report.xls" and place it in
		// Config.getInstance().getProperty("test.report.dir") folder
		// Click "Insights" -> "GoodData" -> "Reports" -> "Release Notes" in the
		// left-pane -> "All
		String fileName = "All Fixes Report";
		String destDir = System.getProperty("user.dir")
				+ "\\"
				+ Config.getInstance().getProperty("test.report.dir")
						.substring(2);

		// 2. Generate release notes for a specific ekp version
		String columnName = "Build Commit Information!";
		String filterValue = "11.1";
		String outputFileName = destDir + "\\ReleaseNotes_" + filterValue
				+ ".xls";
		ZendeskUtils.generateReleaseNotes(destDir + "\\" + fileName + ".xls",
				columnName, filterValue, outputFileName);
		System.out.println("Done to generate release note");
	}
}
