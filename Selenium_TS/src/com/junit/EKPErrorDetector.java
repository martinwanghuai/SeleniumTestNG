package com.junit;

import org.junit.runner.Description;
import org.openqa.selenium.WebDriver;

import com.abstractclasses.TestReport;
import com.google.common.base.Throwables;

/**
 * This class will detect ekp error after test case execution
 * 
 * @author martin.wang
 *
 */
public class EKPErrorDetector extends TestReport {

	private final WebDriver driver;

	public EKPErrorDetector(final WebDriver driver) {
		this.driver = driver;
	}

	@Override
	protected void failed(final Throwable e, final Description description) {

		SaveFailReportToExcel(Throwables.getStackTraceAsString(e));
	}

	@Override
	protected void succeeded(final Description description) {

	}

	@Override
	protected void finished(final Description description) {

	}
}
