package com.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

import com.abstractclasses.TestObject;
import com.dao.ExcelSheetObject;
import com.dao.ExcelSheetObjectMapping;
import com.google.common.base.Function;
import com.google.common.base.Predicates;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;

/**
 * 
 * @author martin.wang
 *
 */
public class POIUtils {

	private POIUtils() {
		throw new AssertionError();
	}

	public static String getCellValue(final Workbook wb, Cell cell) {

		final FormulaEvaluator evaluator = wb.getCreationHelper()
				.createFormulaEvaluator();
		String cellValue = "";
		if (cell != null) {
			if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
				cell = evaluator.evaluateInCell(cell);
			}
			cellValue = getCellValue(cell);
		}
		return cellValue;
	}

	public static String getCellValue(final Cell cell) {
		String cellValue = "";

		if (cell != null) {
			switch (cell.getCellType()) {
			case Cell.CELL_TYPE_STRING:
				cellValue = cell.getRichStringCellValue().getString();
				break;

			case Cell.CELL_TYPE_NUMERIC:
				if (DateUtil.isCellDateFormatted(cell)) {
					final Date date = cell.getDateCellValue();
					cellValue = DataUtils.dateToString(date);
				} else {
					cell.setCellType(Cell.CELL_TYPE_STRING); // treat numeric
																// cells as
																// String type
					cellValue = cell.getRichStringCellValue().getString();
				}
				break;

			case Cell.CELL_TYPE_BOOLEAN:
				cellValue = cell.getBooleanCellValue() + "";
				break;

			case Cell.CELL_TYPE_FORMULA:
			default:
				cellValue = "";
				break;
			}
		}

		return cellValue;
	}

	public static TestObject mapExcelRowToTestObject(final Workbook wb, final Row row,
			final String className, final ArrayList<String> fieldNames) {
		Object obj = null;

		try {
			System.out.println("loading class=" + className + "; row="
					+ (row.getRowNum() + 1));
			final Class clz = Class.forName(className);
			obj = clz.newInstance();
			String fieldName = "";
			String funcType = "";
			Field field = null;
			for (int i = 0; i < fieldNames.size(); i++) {
				fieldName = fieldNames.get(i);

				final Cell cell = row.getCell(i);
				String cellValue = "";

				if (cell != null) {
					if (wb == null) {
						cellValue = POIUtils.getCellValue(cell);
					} else {
						cellValue = POIUtils.getCellValue(wb, cell);
					}
				}

				if (fieldName.equalsIgnoreCase("functype")) {
					funcType = cellValue;
				}

				if (!cellValue.equals("")) {

					field = ReflectionUtils.getField(clz, fieldName);

					if (field != null) {
						/*
						 * //1. Set field directly field.setAccessible(true);
						 * field.set(obj, cellValue);
						 */

						// 2. Invoke setter to set field indirectly: e.g.,
						// Assessment.java -> setAssessment_Reviewer()
						final String methodName = "set" + fieldName;
						final Method method = clz.getMethod(methodName, String.class);
						method.invoke(obj, cellValue);
					}

					// reset
					cellValue = "";
				}

			}

			field = ReflectionUtils.getField(clz, "ID");

			if (field != null) {
				field.setAccessible(true);
				final String[] strs = className.split("\\.");
				final String sheetName = strs[strs.length - 1];
				field.set(
						obj,
						TestObject.genObjectID(sheetName, funcType,
								row.getRowNum()));
			}

		} catch (final ClassNotFoundException e) {
			System.out.println("Error when loading " + className + "; row="
					+ row.getRowNum());
			e.printStackTrace();
		} catch (final InstantiationException e) {
			e.printStackTrace();
		} catch (final IllegalAccessException e) {
			e.printStackTrace();
		} catch (final SecurityException e) {
			e.printStackTrace();
		} catch (final NoSuchMethodException e) {
			e.printStackTrace();
		} catch (final InvocationTargetException e) {
			e.printStackTrace();
		}

		return (TestObject) obj;
	}

	public static TestObject mapExcelRowToObject(final Row row, final String className,
			final ArrayList<String> fieldNames) {
		return mapExcelRowToTestObject(null, row, className, fieldNames);
	}

	/**
	 * Read a column from excel (include startIndex and endIndex)
	 * 
	 * @param sheet
	 * @param columnIndex
	 * @param rowIndex_start
	 *            :include
	 * @param rowIndex_end
	 *            :exclude
	 * @return
	 */
	public static ArrayList<String> getColumnFromExcel(final HSSFSheet sheet,
			final int columnIndex, final int rowIndex_start, final int rowIndex_end) {

		final ArrayList<String> columnData = new ArrayList<String>();
		for (int i = rowIndex_start; i <= rowIndex_end; i++) {
			final Row row = sheet.getRow(i);
			if (row != null) {
				final Cell cell = row.getCell(columnIndex);
				if (cell != null) {
					final String cellValue = POIUtils.getCellValue(cell);
					if (!cellValue.equals("")) {
						columnData.add(cellValue);
					} else {
						columnData.add("");
					}
				} else {
					columnData.add("");
				}
			} else {
				columnData.add("");
			}
		}

		return columnData;
	}

	public static ArrayList<String> getColumnFromExcel(final HSSFSheet sheet,
			final int columnIndex, final int rowIndex_start) {

		final ArrayList<String> columnData = new ArrayList<String>();
		final int rows = sheet.getPhysicalNumberOfRows();
		for (int i = rowIndex_start; i < rows; i++) {
			final Row row = sheet.getRow(i);
			if (row != null) {
				final Cell cell = row.getCell(columnIndex);
				if (cell != null) {
					final String cellValue = POIUtils.getCellValue(cell);
					if (!cellValue.equals("")) {
						columnData.add(cellValue);
					}
				}
			}

		}

		return columnData;
	}

	public static ArrayList<String> getColumnFromExcel(final HSSFSheet sheet,
			final int columnIndex) {
		return getColumnFromExcel(sheet, columnIndex, 0);
	}

	public static ArrayList<String> getColumnFromExcel(final HSSFSheet sheet,
			final String columnName) {
		int columnIndex = -1;
		final ArrayList<String> row = POIUtils.getRowFromExcel(sheet, 0);
		for (int i = 0; i < row.size(); i++) {
			final String rowData = row.get(i);
			if (rowData.equals(columnName)) {
				columnIndex = i;
				break;
			}
		}

		if (columnIndex == -1) {
			return null;
		} else {
			return getColumnFromExcel(sheet, columnIndex);
		}
	}

	public static ArrayList<String> getRowFromExcel(final HSSFSheet sheet,
			final int rowIndex, final int columnIndex_start) {
		final ArrayList<String> rowData = new ArrayList<String>();
		final Row row = sheet.getRow(rowIndex);
		Cell cell = null;
		String cellValue = "";
		for (int i = columnIndex_start; i < row.getPhysicalNumberOfCells(); i++) {
			cell = row.getCell(i);
			cellValue = POIUtils.getCellValue(cell);
			if (!cellValue.equals("")) {
				rowData.add(cellValue);
			}
		}

		return rowData;
	}

	public static ArrayList<String> getRowFromExcel(final HSSFSheet sheet,
			final int rowIndex) {
		return getRowFromExcel(sheet, rowIndex, 0);
	}

	public static HSSFSheet getSheetByName(final HSSFWorkbook wb, final String strSheetName) {
		return wb.getSheet(strSheetName);
	}

	public static HashMap<String, ArrayList<String>> getColumnPairs(
			final HSSFSheet sheet, final int dataRowIndex_start, final int key_columnIndex,
			final int value_columnIndex) {
		final HashMap<String, ArrayList<String>> key_values_map = new HashMap<String, ArrayList<String>>();

		ArrayList<String> values = null;

		Row row;
		Cell cell;
		String key = "", value = "";

		for (int i = dataRowIndex_start; i < sheet.getPhysicalNumberOfRows(); i++) {
			row = sheet.getRow(i); // for each row
			if (row != null) {
				cell = row.getCell(key_columnIndex); // for key column
				if (cell != null) {
					key = cell.getRichStringCellValue().getString();
				}

				cell = row.getCell(value_columnIndex); // for value column
				if (cell != null) {
					value = cell.getRichStringCellValue().getString();
				}
			}

			if (!key.equals("") && !value.equals("")) {

				if (key_values_map.containsKey(key)) {
					values = key_values_map.get(key);
				} else {
					values = new ArrayList<String>();
				}

				if (!values.contains(value)) {
					values.add(value);
				}

				key_values_map.put(key, values);
				key = "";
				value = "";
			}
		}

		return key_values_map;
	}

	public static int getRowNumByKeyWord(final HSSFSheet sheet, final int columnIndex,
			final String search_keyword) {
		Row row;
		Cell cell;
		String value = "";

		int rowNum = -1;
		for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
			row = sheet.getRow(i); // for each row
			if (row != null) {
				cell = row.getCell(columnIndex); // for key column
				if (cell != null) {
					value = cell.getRichStringCellValue().getString();
					if (value.equals(search_keyword)) {
						rowNum = i;
						break;
					}
				}
			}

		}
		return rowNum;
	}

	/**
	 * This is call by Test Case instance to catch information in Testing Page
	 * and fill into the current test case
	 * 
	 * @param fileName
	 * @param sheetName
	 * @param keyword_ColumnIndex
	 * @param keyword_search
	 * @param data_ColumnIndex
	 * @param data
	 */
	public static void writeTestResultToCurrentTestCase(final String fileName,
			final String sheetName, final int keyword_ColumnIndex, final String keyword_search,
			final int data_ColumnIndex, final String data) {
		try {
			final FileInputStream file = new FileInputStream(fileName);
			final HSSFWorkbook wb = new HSSFWorkbook(file);

			HSSFSheet sheet = wb.getSheet(sheetName);
			if (sheet == null) {
				sheet = wb.createSheet(sheetName);
			}

			final int rowNum = POIUtils.getRowNumByKeyWord(sheet,
					keyword_ColumnIndex, keyword_search);
			final HSSFRow row = sheet.getRow(rowNum);
			Cell cell = row.getCell(data_ColumnIndex);
			if (cell == null) {
				cell = row.createCell(data_ColumnIndex);
			}
			cell.setCellValue(data);
			file.close();

			final FileOutputStream outFile = new FileOutputStream(fileName);
			wb.write(outFile);
			outFile.close();
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}
	
	public static TestObject loadTestCaseFromExcelRow(final ExcelSheetObject excelSheetObj, final HSSFWorkbook wb){
		
		return loadTestCaseFromExcelRow(excelSheetObj, wb, Integer.parseInt(excelSheetObj.getRowNum()));
	}

	public static TestObject loadTestCaseFromExcelRow(final ExcelSheetObject excelSheetObj, final HSSFWorkbook wb, final int rowNum){
		
		if (rowNum < 1) {
			System.out
					.println("POI:loadTestCaseFromExcelRow-->ERROR: Row Number should start from 1");
			return null;
		}

		TestObject obj = null;
		final HSSFSheet sheet = wb.getSheet(excelSheetObj.getSheetName());
		final ArrayList<String> fieldNames = POIUtils.getRowFromExcel(sheet, 0);
		final Row row = sheet.getRow(rowNum - 1); // rowNum-1 since rowNum parameter
											// start from 1, not 0
		if (row != null) {
			obj = POIUtils.mapExcelRowToTestObject(wb, row,
					"com.model." + excelSheetObj.getSheetName(), fieldNames);
			final String funcTypeValue = ReflectionUtils.getFieldValueAsString(obj,
					"FuncType");
			if (funcTypeValue.trim().isEmpty()
					|| !funcTypeValue.equals(excelSheetObj.getFuncName())) {
				obj = null;
			}
		}

		return obj;
	}

	public static int getRowNum(final HSSFSheet sheet, final int columnIndex,
			final String search_keyword) {
		Row row;
		Cell cell;
		String value = "";

		int rowNum = -1;
		for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
			row = sheet.getRow(i); // for each row
			if (row != null) {
				cell = row.getCell(columnIndex); // for key column
				if (cell != null) {
					value = cell.getRichStringCellValue().getString();
					if (value.equals(search_keyword)) {
						rowNum = i;
						break;
					}
				}
			}

		}
		return rowNum;
	}

	/**
	 * Martin: save row data into excel file
	 * 
	 * @param fileName
	 * @param sheetName
	 * @param data
	 *            : a set of row data
	 */
	public static void saveIntoExcel(final String fileName, final String sheetName,
			final ArrayList<ArrayList<String>> data) {
		try {
			final HSSFWorkbook wb = new HSSFWorkbook();
			final HSSFSheet sheet = wb.createSheet(sheetName);

			for (int i = 0; i < data.size(); i++) {
				final HSSFRow row = sheet.createRow(i);

				final ArrayList<String> columnData = data.get(i);
				for (int j = 0; j < columnData.size(); j++) {
					final Cell cell = row.createCell(j);
					cell.setCellValue(columnData.get(j));
				}
			}

			final FileOutputStream out = new FileOutputStream(new File(fileName));
			wb.write(out);
			out.close();

		} catch (final FileNotFoundException e) {
			e.printStackTrace();
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	public static void writeExcelData(final String fileName, final String sheetName,
			final int keyword_ColumnIndex, final String keyword_search,
			final int data_ColumnIndex, final String data) {
		try {
			final FileInputStream file = new FileInputStream(fileName);
			final HSSFWorkbook wb = new HSSFWorkbook(file);

			final HSSFSheet sheet = wb.getSheet(sheetName);

			final int rowNum = POIUtils.getRowNum(sheet, keyword_ColumnIndex,
					keyword_search);
			final HSSFRow row = sheet.getRow(rowNum);
			Cell cell = row.getCell(data_ColumnIndex);
			if (cell == null) {
				cell = row.createCell(data_ColumnIndex);
			}
			cell.setCellValue(data);
			file.close();

			final FileOutputStream outFile = new FileOutputStream(fileName);
			wb.write(outFile);
			outFile.close();
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	public static void filterDebugMsg(final Exception e, final TestObject testObject) {
		System.out.println("Error in test case:" + testObject.toString());
		System.out.println("Caused by:" + e.getCause());

		System.out.println("Calling stack:");
		StackTraceElement[] elems = e.getStackTrace();
		if (e instanceof InvocationTargetException) {
			final Throwable s = ((InvocationTargetException) e).getTargetException();
			elems = s.getStackTrace();
		}

		for (final StackTraceElement elem : elems) {
			if (elem.getClassName().startsWith("com.netdimen")) {
				System.out.println(elem.toString());
			}
		}
	}

	public static List<ExcelSheetObject> getExcelSheetObjectFromExcel(
			final HSSFSheet sheet, final int startRowIndex) {

		return getExcelSheetObjectFromExcel(sheet, startRowIndex,
				sheet.getPhysicalNumberOfRows());
	}

	public static List<ExcelSheetObject> getExcelSheetObjectFromExcel(
			final HSSFSheet sheet, final int startRowIndex,
			final int endRowIndex) {

		final List<Row> rowList = Lists.newArrayList();
		for (int i = startRowIndex; i < endRowIndex; i++) {
			final Row row = sheet.getRow(i);
			if (row != null) {
				rowList.add(row);
			}
		}

		return FluentIterable.from(rowList)
				.transform(new Function<Row, ExcelSheetObject>() {

					@Override
					public ExcelSheetObject apply(final Row row) {

						final String funcName = getCellValue(row
								.getCell(ExcelSheetObjectMapping.FUNCNAME
										.getColumnIndex()));
						final String sheetName = getCellValue(row
								.getCell(ExcelSheetObjectMapping.SHEETNAME
										.getColumnIndex()));

						if (Checker.isBlank(funcName)
								|| Checker.isBlank(sheetName)) {
							return null;
						}

						final String rowNum = getCellValue(row
								.getCell(ExcelSheetObjectMapping.ROWNUM
										.getColumnIndex()));
						final String label = getCellValue(row
								.getCell(ExcelSheetObjectMapping.LABEL
										.getColumnIndex()));
						final String author = getCellValue(row
								.getCell(ExcelSheetObjectMapping.AUTHOR
										.getColumnIndex()));
						final String country = getCellValue(row
								.getCell(ExcelSheetObjectMapping.COUNTRY
										.getColumnIndex()));
						final String language = getCellValue(row
								.getCell(ExcelSheetObjectMapping.LANGUAGE
										.getColumnIndex()));
						final String browser = getCellValue(row
								.getCell(ExcelSheetObjectMapping.BROWSER
										.getColumnIndex()));
						
						return new ExcelSheetObject(funcName, rowNum,
								sheetName, label, author, country, language, browser);
					}
				}).filter(Predicates.notNull()).toList();
	}
}
