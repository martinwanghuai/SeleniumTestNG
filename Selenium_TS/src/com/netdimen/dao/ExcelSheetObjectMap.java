package com.netdimen.dao;


public enum ExcelSheetObjectMap {

	FUNCNAME(0),
	SHEETNAME(1),
	ROWNUM(2),
	LABEL(3),
	AUTHOR(4);
	
	private final int columnIndex;

	private ExcelSheetObjectMap(final int columnIndex){
		
		this.columnIndex = columnIndex;
	}
	
    public int getColumnIndex() {
    
    	return columnIndex;
    }

}
