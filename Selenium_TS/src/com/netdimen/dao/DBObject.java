package com.netdimen.dao;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;
/**
 * @author lester.li
 * This is a class which is to use as general class to map db table as java objects 
 */
public abstract class DBObject {

	private Hashtable<String, String> dbMappingTable; 
	private String EKPID;
	public DBObject() {
		// TODO Auto-generated constructor stub
		dbMappingTable = new Hashtable<String, String>();
		dbMappingTable.put("*NONE*", "");
		dbMappingTable.put("NULL", "");		
	}
	public String MapDBValue(String valueToMap){
		String temp;
		if (valueToMap ==null) {
			valueToMap ="null";
		}
		Set<String> set=dbMappingTable.keySet();
		Iterator<String> itr = set.iterator();
		while (itr.hasNext()) {
	      String key = itr.next();
	      temp= dbMappingTable.get(key);
	      if (key.equalsIgnoreCase(valueToMap) ){
	    	  valueToMap=temp;
	      }
	    }
		return valueToMap;
	}
	public String getEKPID() {
		return EKPID;
	}
	public void setEKPID(String eKPID) {
		EKPID = eKPID;
	}
	public boolean ekpEquals(String ekpId){
		if (this.EKPID.equalsIgnoreCase(ekpId)){
			return true;
		}else{
			return false;
		}
	}

}
