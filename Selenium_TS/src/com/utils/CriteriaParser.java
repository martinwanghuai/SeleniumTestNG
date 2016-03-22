package com.utils;

import java.util.ArrayList;
import java.util.HashMap;

public class CriteriaParser {

	public static HashMap<String, ArrayList<String>> parseKeyValueList(
			String keySeperator, String valueSeperator, String strToParse) {
		return parseKeyValueList(keySeperator, valueSeperator, "\n", strToParse);
	}

	/**
	 * Parse expected results w.r.t. a list Example: JobProfile sheet
	 * runAutoEnroll case enrollment:uma_rob;uma_qa1;\n non-enrollment:bsam;"
	 * for auto-enroll into ":" is the keySeperator ";" is the valueSeperator
	 * 
	 * @param strToParse
	 * @return: A Map of key=ArrayList(values) 1. key=enrollment
	 *          values=ArrayList(1.uma_rob,2.uma_qa1) 2. key=non-enrollment
	 *          values=ArrayList(1.bsam)
	 */
	public static HashMap<String, ArrayList<String>> parseKeyValueList(
			String keySeperator, String valueSeperator, String listSeperator,
			String strToParse) {
		HashMap<String, ArrayList<String>> key_ValuesList_Map = new HashMap<String, ArrayList<String>>();
		if (listSeperator == null)
			listSeperator = "\n";

		if (!strToParse.equals("")) {
			String[] strs = strToParse.split(listSeperator);// default
															// listSeperator="\n"

			ArrayList<String> values = null;
			String criteria = "";
			for (String str_tmp : strs) { // str_tmp = enrollment:
											// uma_rob;uma_qa1;
				String[] strs_tmp = str_tmp.split(keySeperator);// keySeperator=":"
				criteria = strs_tmp[0].trim();// enrollment or non-enrollment

				if (key_ValuesList_Map.containsKey(criteria)) {
					values = key_ValuesList_Map.get(criteria);
				} else {
					values = new ArrayList<String>();
				}

				if (Checker.isBlank(valueSeperator)) { // can also be blank,
														// then pass null or ""
					values.add(strs_tmp[1]);
				} else {
					String[] values_tmp = strs_tmp[1].split(valueSeperator);// valueSeperator=";"
					for (String value : values_tmp) {
						values.add(value.trim());
					}
				}
				key_ValuesList_Map.put(criteria, values);
			}
		}

		return key_ValuesList_Map;
	}
}
