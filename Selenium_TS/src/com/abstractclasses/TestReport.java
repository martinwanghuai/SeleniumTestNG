package com.abstractclasses;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.junit.rules.TestWatcher;

import com.config.Config;
import com.controller.TestDriver;
import com.utils.POIUtils;

/**
 * @author lester.li
 *
 */
public class TestReport extends TestWatcher {

	private static int current_report_row_in_failed_case = 1;
	private static int current_report_row_in_passed_case = 1;
	private static int current_row = -1;

	private static TestReport instance;

	public TestReport() {

	}

	public static TestReport getInstance() {

		if (instance == null) {
			synchronized (TestReport.class) {
				if (instance == null) { 
					instance = new TestReport();
				}
			}
		}
		return instance;
	}

	public static synchronized int getCurrent_report_row_in_failed_case() {
		return TestReport.current_report_row_in_failed_case;
	}

	public static synchronized void setCurrent_report_row_in_failed_case(final int row) {
		TestReport.current_report_row_in_failed_case = row;
	}

	public static synchronized int getCurrent_report_row_in_passed_case() {
		return TestReport.current_report_row_in_passed_case;
	}

	public static synchronized void setCurrent_report_row_in_passed_case(final int row) {
		TestReport.current_report_row_in_passed_case = row;
	}

	public static synchronized int getCurrent_row() {
		return TestReport.current_row;
	}

	public static synchronized void setCurrent_row(final int row) {
		TestReport.current_row = row;
	}

	public HSSFWorkbook getReportTemplate() {

		try {
			final String fileName = current_report_row_in_failed_case == 1
					&& current_report_row_in_passed_case == 1 ? "test.report.excel.template":"test.report.excel";
			return new HSSFWorkbook(new FileInputStream(new File(Config.getInstance().getProperty(fileName))));

		} catch (final FileNotFoundException e) {
			e.printStackTrace();
		} catch (final IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void SaveSuccessTestReportToExcel() {
		
		final String sheetName = Config.getInstance().getProperty(
				"test.report.passed.sheet");
		this.writeToExcel(sheetName, "Pass", true);
	}

	public void SaveFailReportToExcel(final String errorDetail) {
		
		final String sheetName = Config.getInstance().getProperty(
				"test.report.failed.sheet");
		this.writeToExcel(sheetName, errorDetail, false);
	}

	public void IncrementTheCurrentRowOfReportingCase(final HSSFSheet sheet) {

		final String sheetName = sheet.getSheetName();
		if (sheetName.equalsIgnoreCase(Config.getInstance().getProperty(
				"test.report.passed.sheet"))) {
			setCurrent_row(TestReport.current_report_row_in_passed_case);
			setCurrent_report_row_in_passed_case(TestReport.current_row + 1);
		} else {
			if (TestReport.caseExisted(sheet)) {
				TestReport.current_row = failedCaseRowNumInReport(sheet);
			} else {
				setCurrent_row(TestReport.current_report_row_in_failed_case);
				setCurrent_report_row_in_failed_case(TestReport.current_row + 1);
			}
		}
	}

	public static int failedCaseRowNumInReport(final HSSFSheet sheet) {
		return POIUtils.getRowNum(sheet, 0, TestDriver.getCurrentTestObject()
				.toString());

	}

	public static boolean caseExisted(final HSSFSheet sheet) {
		
		return failedCaseRowNumInReport(sheet) > 0 ? true: false; 
	}

	private void writeToExcel(final String sheetName, final String info, final boolean passed){
		
		HSSFSheet sheet = getReportTemplate().getSheet(sheetName);
		if (sheet == null) {
			sheet = getReportTemplate().createSheet(sheetName);
		}
		IncrementTheCurrentRowOfReportingCase(sheet);
		writeToExcel(passed, sheet, TestDriver.getCurrentTestObject().toString(),
				info);
	}

	/**
	 * 
	 * @param Passed
	 *            = the flag for pass for fail
	 * @param sheet
	 *            = the excel sheet to write result
	 * @param casename
	 *            = the case to mark result
	 * @param detail
	 *            = the detail of fail or just "Pass" for success case
	 */
	private void writeToExcel(final boolean Passed, final HSSFSheet sheet, String casename,
			String detail) {

		String orginalDetail = "";
		if (casename.length() == 0) {
			casename = "empty";
			detail = "empty";
		}
		if (detail.length() == 0) {
			detail = "empty";
		}

		HSSFRow row = sheet.getRow(TestReport.current_row);

		if (row == null) {
			row = sheet.createRow(TestReport.current_row);
		}
		Cell caseNameCell = row.getCell(0);
		if (caseNameCell == null)
			caseNameCell = row.createCell(0);
		Cell detailCell;
		if (TestReport.caseExisted(sheet)) {
			detailCell = row.getCell(1);
			if (detailCell == null) {
				detailCell = row.createCell(1);
			} else{
				orginalDetail = System.lineSeparator()
						+ detailCell.getStringCellValue();
			}
		} else {
			detailCell = row.createCell(1);
		}
		caseNameCell.setCellValue(casename);
		if (Passed) {
			detailCell.setCellValue(detail);
		} else {
			detailCell.setCellValue(orginalDetail + detail);
		}

		setErrorRptCellStyle(sheet);
		try {
			final FileOutputStream outFile = new FileOutputStream(Config
					.getInstance().getProperty("test.report.excel"));
			final HSSFWorkbook wb = sheet.getWorkbook();
			wb.write(outFile);
			outFile.close();
		} catch (final FileNotFoundException e) {
			e.printStackTrace();
		} catch (final IOException e) {
			e.printStackTrace();
		}

	}

	public void setErrorRptCellStyle(final HSSFSheet sheet) {
		// by default hypelrinks are blue and underlined and are top
		final CellStyle hlink_style = sheet.getWorkbook().createCellStyle();
		final Font hlink_font = sheet.getWorkbook().createFont();
		hlink_font.setUnderline(Font.U_SINGLE);
		hlink_font.setColor(IndexedColors.BLUE.getIndex());
		hlink_style.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);
		hlink_style.setFont(hlink_font);
	}

}