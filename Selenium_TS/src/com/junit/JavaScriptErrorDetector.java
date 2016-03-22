package com.junit;

import java.util.List;

import net.jsourcerer.webdriver.jserrorcollector.JavaScriptError;

import org.junit.runner.Description;
import org.openqa.selenium.WebDriver;

/**
 * This class will detect JavaScript error after test case execution.
 * 
 * @author martin.wang
 *
 */
public class JavaScriptErrorDetector extends TestReport {
	
	private final WebDriver driver;

	public JavaScriptErrorDetector(final WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	@Override
	protected void failed(final Throwable e, final Description description) {
		this.checkJSError();
		super.failed(e, description);
	}

	@Override
	protected void succeeded(final Description description) {
		this.checkJSError();
	}

	@Override
	protected void finished(final Description description) {
		this.checkJSError();
	}

	private void checkJSError() {
		final List<JavaScriptError> jsErrors = JavaScriptError.readErrors(driver);
		for (final JavaScriptError jsError : jsErrors) {
			System.out.println("JS errors occured:" + jsError);
		}
		JUnitAssert.assertTrue(jsErrors.isEmpty(), "JS error is found");
	}
}
