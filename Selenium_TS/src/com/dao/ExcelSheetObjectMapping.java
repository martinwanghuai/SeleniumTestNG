package com.dao;

public enum ExcelSheetObjectMapping {

	FUNCNAME(0), SHEETNAME(1), ROWNUM(2), LABEL(3), AUTHOR(4), COUNTRY(5), LANGUAGE(6), BROWSER(7);

	private final int columnIndex;

	private ExcelSheetObjectMapping(final int columnIndex) {

		this.columnIndex = columnIndex;
	}

	public int getColumnIndex() {

		return columnIndex;
	}
}
