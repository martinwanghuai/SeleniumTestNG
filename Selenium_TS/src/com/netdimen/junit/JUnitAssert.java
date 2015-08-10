package com.netdimen.junit;

import static org.junit.Assert.fail;

/**
 * This class overwrites JUnit.Assertion to provide more debug info.
 * 
 * @author martin.wang
 *
 */
public class JUnitAssert {

	public static void assertEquals(String expectedResult, String actualResult) {
		boolean matched = expectedResult.equalsIgnoreCase(actualResult);
		if (!matched) {
			System.out.println("Expected:" + expectedResult + "; actual:"
					+ actualResult);
			// TestReport.getInstance().SaveFailReportToExcel("Expected:" +
			// expectedResult + "; actual:" +
			// actualResult+System.lineSeparator());
			fail("Expected:" + expectedResult + "; actual:" + actualResult);
		}
	}

	public static void assertTrue(boolean condition, String msgForFail) {
		if (!condition) {
			System.out.println(msgForFail);
			// TestReport.getInstance().SaveFailReportToExcel(msgForFail+System.lineSeparator());
			fail(msgForFail);
		}
	}
}
