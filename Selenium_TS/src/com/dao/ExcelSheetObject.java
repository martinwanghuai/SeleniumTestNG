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
	
	private final String country;
	
	private final String language;
	
	private final String browser; 

	public ExcelSheetObject(final String funcName, final String rowNum,
			final String sheetName, final String label, final String author) {

		this(funcName, rowNum, sheetName, label, author, "", "", "");
	}

	public ExcelSheetObject(final String funcName, final String rowNum,
			final String sheetName, final String label, final String author, final String country,
			final String language, final String browser) {

		this.sheetName = sheetName;
		this.rowNum = rowNum;
		this.funcName = funcName;
		this.label = label;
		this.author = author;
		this.country = country;
		this.language = language;
		this.browser = browser;
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

	public String getCountry() {
		return country;
	}

	public String getLanguage() {
		return language;
	}

	public String getBrowser() {
		return browser;
	}
}
