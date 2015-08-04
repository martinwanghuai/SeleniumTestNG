package com.netdimen.abstractclasses;

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

import com.netdimen.config.Config;
import com.netdimen.controller.TestDriver;
import com.netdimen.utils.POIUtils;

/**
 * @author lester.li
 *
 */
public class TestReport extends TestWatcher{

	private static int current_report_row_in_failed_case =1;
	private static int current_report_row_in_passed_case =1;
	private static int current_row=-1;

	private static TestReport instance;
	
	public TestReport() {

	}
	public static TestReport getInstance(){
		
		 if (instance == null) {
				synchronized (TestReport.class) { // Add a synch block
					if (instance == null) { // verify some other synch block didn't
											// create a WebElementManager yet...
						instance = new TestReport();
					}
				}
			}
			return instance;
	}

	public static synchronized int getCurrent_report_row_in_failed_case() {
		return TestReport.current_report_row_in_failed_case;
	}
	public static synchronized void setCurrent_report_row_in_failed_case(int row) {
		TestReport.current_report_row_in_failed_case = row;
	}
	public static synchronized int getCurrent_report_row_in_passed_case() {
		return TestReport.current_report_row_in_passed_case;
	}
	public static synchronized void setCurrent_report_row_in_passed_case(int row) {
		TestReport.current_report_row_in_passed_case = row;
	}
	public static synchronized int getCurrent_row() {
		return TestReport.current_row;
	}
	public static synchronized void setCurrent_row(int row) {
		TestReport.current_row = row;
	}
	public HSSFWorkbook getReportTemplate(){ 
		
		try {
			File template;
			FileInputStream fileIS;
			if (current_report_row_in_failed_case==1 && current_report_row_in_passed_case==1)
				template = new File(Config.getInstance().getProperty("test.report.excel.template"));
			else 
				template= new File(Config.getInstance().getProperty("test.report.excel"));
			fileIS = new FileInputStream(template);
			return new HSSFWorkbook(fileIS);
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	public void SaveSuccessTestReportToExcel(){
			 
			String sheetName;
			sheetName= Config.getInstance().getProperty("test.report.passed.sheet");
			HSSFSheet sheet= getReportTemplate().getSheet(sheetName);
			if (sheet== null) {
				sheet=getReportTemplate().createSheet(sheetName); 
			}
			IncrementTheCurrentRowOfReportingCase(sheet);
			writeToExcel(true, sheet, TestDriver.getCurrentTestObject().toString(), "Pass");
	}
	
	public void SaveFailReportToExcel(String errorDetail){
		String sheetName;
		sheetName= Config.getInstance().getProperty("test.report.failed.sheet");
		HSSFSheet sheet= getReportTemplate().getSheet(sheetName);
		if (sheet== null) {
			sheet=getReportTemplate().createSheet(sheetName); 
		}
		IncrementTheCurrentRowOfReportingCase(sheet);
		writeToExcel(false, sheet, TestDriver.getCurrentTestObject().toString(), errorDetail);
	}
	
	public void IncrementTheCurrentRowOfReportingCase(HSSFSheet sheet){
		String sheetName;
		sheetName= sheet.getSheetName();
		if (sheetName.equalsIgnoreCase(Config.getInstance().getProperty("test.report.passed.sheet"))){
			setCurrent_row(TestReport.current_report_row_in_passed_case);
			setCurrent_report_row_in_passed_case(TestReport.current_row+1);

		}
		else{
	
			if (TestReport.caseExisted(sheet)){
				TestReport.current_row=failedCaseRowNumInReport(sheet);
			}else{
				setCurrent_row(TestReport.current_report_row_in_failed_case);
				setCurrent_report_row_in_failed_case(TestReport.current_row+1);

			}
		}
	}
	
	public static int failedCaseRowNumInReport(HSSFSheet sheet){
		return POIUtils.getRowNum(sheet, 0,
				TestDriver.getCurrentTestObject().toString());
		
	}
	
	public static boolean caseExisted(HSSFSheet sheet){
		if (failedCaseRowNumInReport(sheet)>0){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * 
	 * @param Passed = the flag for pass for fail
	 * @param sheet = the excel sheet to write result
	 * @param casename = the case to mark result
	 * @param detail = the detail of fail or just "Pass" for success case
	 */
	private void writeToExcel(boolean Passed, HSSFSheet sheet, String casename, String detail){
		
		String orginalDetail="";
		if (casename.length()==0){ 
			casename="empty";
			detail="empty";
		}
		if (detail.length()==0){ 
			detail="empty";
		}
	
		HSSFRow row = sheet.getRow(TestReport.current_row);
		
		
		if (row == null){
			row=sheet.createRow(TestReport.current_row);
		}
		Cell caseNameCell = row.getCell(0);
		if (caseNameCell == null) caseNameCell = row.createCell(0);
		Cell detailCell;
		if (TestReport.caseExisted(sheet)){
			detailCell = row.getCell(1);
			if (detailCell == null){ 

				//orginalDetail="Pls check this case in sheet="+sheet.getSheetName() +" case= "+casename+" row "+ TestReport.current_row;
//				System.out.println("Pls check this case in sheet="+sheet.getSheetName() +" case= "+casename+" row "+ TestReport.current_row);
				detailCell = row.createCell(1);
			}
			else
				orginalDetail=System.lineSeparator()+detailCell.getStringCellValue();
		}else{
			detailCell = row.createCell(1);
		}
		caseNameCell.setCellValue(casename);
		if (Passed){
			//detailCell.setCellValue(detail+System.lineSeparator()+"TestReport Debug: current_report_row_in_passed_case=" + TestReport.current_report_row_in_passed_case);
			detailCell.setCellValue(detail);
		}else{
			//detailCell.setCellValue(orginalDetail+detail+System.lineSeparator()+"TestReport Debug: current_report_row_in_failed_case=" + TestReport.current_report_row_in_failed_case);
			detailCell.setCellValue(orginalDetail+detail);
		}
		
		setErrorRptCellStyle(sheet);
		try {
			FileOutputStream outFile = new FileOutputStream(Config.getInstance().getProperty("test.report.excel"));
			HSSFWorkbook wb= sheet.getWorkbook();
			wb.write(outFile);
			outFile.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	
	}
	public void setErrorRptCellStyle( HSSFSheet sheet){
		//by default hypelrinks are blue and underlined and are top
		CellStyle hlink_style =  sheet.getWorkbook().createCellStyle();
		Font hlink_font =  sheet.getWorkbook().createFont();
		hlink_font.setUnderline(Font.U_SINGLE);
		hlink_font.setColor(IndexedColors.BLUE.getIndex());
		hlink_style.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);
		hlink_style.setFont(hlink_font);
	}
	
	

}
