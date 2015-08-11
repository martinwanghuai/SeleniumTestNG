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

import com.google.common.collect.Lists;
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
	public static void downloadReleaseNotes(final String destDir, final String fileName) {
		final String uid = "martin.wang@netdimensions.com";
		final String pwd = "abcd1234";
		final String URL = "https://secure.gooddata.com/account.html?lastUrl=%252F#/login";

		// 1. Setup profile
		final FirefoxProfile fxProfile = new FirefoxProfile();
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
		final WebDriver driver = WebDriverUtils.getWebDriver_new(fxProfile);

		// 2. Delete existing file before downloading
		final String outputFileName = destDir + "\\" + fileName + ".xls";
		final File file = new File(outputFileName);
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
	public static void generateReleaseNotes(final String inputFileName,
			final String columnName, final String filterValue, final String outputFileName) {
		try {
			final ArrayList<ArrayList<String>> results = Lists.newArrayList();
			final FileInputStream file = new FileInputStream(inputFileName);
			final HSSFWorkbook wb = new HSSFWorkbook(file);

			// load all tests: all test cases are configured in EKPMain page
			int filterColumn = -1;
			for (int i = 0; i < wb.getNumberOfSheets(); i++) {
				final HSSFSheet sheet = wb.getSheetAt(i);

				final ArrayList<String> rowData = POIUtils.getRowFromExcel(sheet, 1);// labels
																				// row
				results.add(rowData);
				for (int j = 0; j < rowData.size(); j++) {
					final String columnData = rowData.get(j);
					if (columnData.equals(columnName)) {
						filterColumn = j;
						break;
					}
				}

				for (int j = 2; j < sheet.getPhysicalNumberOfRows(); j++) {// data
																			// row
					final Row row = sheet.getRow(j);

					if (j % 10 == 0) {
						System.out.println("Handling Row:" + j);
					}

					if (row != null) {
						final ArrayList<String> rowData_tmp = POIUtils
								.getRowFromExcel(sheet, j);
						if (rowData_tmp != null) {
							final String testData = rowData_tmp.get(filterColumn);
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
		} catch (final FileNotFoundException e) {
			e.printStackTrace();
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(final String[] args) {
		// 1. Manually Download release notes template, name it as
		// "All Fixes Report.xls" and place it in
		// Config.getInstance().getProperty("test.report.dir") folder
		// Click "Insights" -> "GoodData" -> "Reports" -> "Release Notes" in the
		// left-pane -> "All
		final String fileName = "All Fixes Report";
		final String destDir = System.getProperty("user.dir")
				+ "\\"
				+ Config.getInstance().getProperty("test.report.dir")
						.substring(2);

		// 2. Generate release notes for a specific ekp version
		final String columnName = "Build Commit Information!";
		final String filterValue = "11.1";
		final String outputFileName = destDir + "\\ReleaseNotes_" + filterValue
				+ ".xls";
		ZendeskUtils.generateReleaseNotes(destDir + "\\" + fileName + ".xls",
				columnName, filterValue, outputFileName);
		System.out.println("Done to generate release note");
	}
}
