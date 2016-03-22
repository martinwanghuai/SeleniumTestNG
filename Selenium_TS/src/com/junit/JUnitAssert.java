package com.junit;

import static org.junit.Assert.fail;

/**
 * This class overwrites JUnit.Assertion to provide more debug info.
 * 
 * @author martin.wang
 *
 */
public class JUnitAssert {

	public static void assertEquals(final String expectedResult, final String actualResult) {
		
		final boolean matched = expectedResult.equalsIgnoreCase(actualResult);
		if (!matched) {
			System.out.println("Expected:" + expectedResult + "; actual:"
					+ actualResult);
			fail("Expected:" + expectedResult + "; actual:" + actualResult);
		}
	}

	public static void assertTrue(final boolean condition, final String msgForFail) {
		if (!condition) {
			System.out.println(msgForFail);
			fail(msgForFail);
		}
	}
}
