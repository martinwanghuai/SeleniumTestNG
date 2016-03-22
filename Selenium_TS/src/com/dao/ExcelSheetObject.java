/**
 * @author martin.wang
 *
 */

package com.dao;

public class ExcelSheetObject {

	private final String sheetName;

	private final String rowNum;

	private final String funcName;

	private final String label;

	private final String author;

	public ExcelSheetObject(final String funcName, final String rowNum,
			final String sheetName, final String label, final String author) {

		this.sheetName = sheetName;
		this.rowNum = rowNum;
		this.funcName = funcName;
		this.label = label;
		this.author = author;
	}

	public String getSheetName() {

		return sheetName;
	}

	public String getRowNum() {

		return rowNum;
	}

	public String getFuncName() {

		return funcName;
	}

	public String getLabel() {

		return label;
	}

	public String getAuthor() {

		return author;
	}
}
