package com.dao;

import java.util.Iterator;
import java.util.Map;

import com.google.common.collect.Maps;
import com.utils.Checker;

/**
 * @author lester.li This is a class which is to use as general class to map db
 *         table as java objects
 */
public abstract class DBObject {

	private final Map<String, String> dbMappingTable;
	private String EKPID;

	public DBObject() {
		dbMappingTable = Maps.newHashMap();
		dbMappingTable.put("*NONE*", "");
		dbMappingTable.put("NULL", "");
	}

	public String MapDBValue(String valueToMap) {
		
		valueToMap = Checker.isBlank(valueToMap)? "null": valueToMap;
		final Iterator<String> itr = dbMappingTable.keySet().iterator();
		while (itr.hasNext()) {
			final String key = itr.next();
			final String temp = dbMappingTable.get(key);
			if (key.equalsIgnoreCase(valueToMap)) {
				return temp;
			}
		}
		return valueToMap;
	}

	public String getEKPID() {
		return EKPID;
	}

	public void setEKPID(final String eKPID) {
		EKPID = eKPID;
	}

	public boolean ekpEquals(final String ekpId) {
		
		return this.EKPID.equalsIgnoreCase(ekpId)?true:false;
	}
}
