package com.netdimen.view;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.netdimen.abstractclasses.TestObject;
import com.netdimen.config.Config;
import com.netdimen.utils.WebDriverUtils;

public class Navigator {

	private static ArrayList<WebElementWrapper> prevWebElementList = null;

	private Navigator() {
		throw new AssertionError();
	}

	public static ArrayList<WebElementWrapper> getPrevWebElementList() {
		return prevWebElementList;
	}

	public static void setPrevWebElementList(
			final ArrayList<WebElementWrapper> prevWebElementList) {
		Navigator.prevWebElementList = prevWebElementList;
	}

	public static final XMLWebElementManager xmlWebElmtMgr = XMLWebElementManager
			.getInstance();

	public static void navigate(final WebDriver driver,
			final ArrayList<WebElementWrapper> webElementList) {
		navigate(driver, webElementList, null);
	}

	public static void navigate(final WebDriver driver,
			final ArrayList<WebElementWrapper> webElementList, final TestObject obj) {

		WebDriverUtils.acceptAlertIfPresent(driver);
		WebDriverUtils.closeAllPopUpWins(driver);
		final WebElementWrapper parentWE = webElementList.get(0);
		WebElementWrapper prevParentWE;
		if (prevWebElementList != null) {
			prevParentWE = prevWebElementList.get(0);
		}

		if (parentWE.getId().equalsIgnoreCase("LearningCenter")) {
			driver.get(Config.getInstance().getProperty("HomePage"));
			Navigator.waitForPageLoad(driver);
		} else if (parentWE.getId().equalsIgnoreCase("ManageCenter")) {
			driver.get(Config.getInstance().getProperty("ManageCenter"));
			Navigator.waitForPageLoad(driver);
		}

		for (int i = 1; i < webElementList.size(); i++) {
			final WebElementWrapper we = webElementList.get(i);
			final By by = we.getBy();
			WebDriverUtils.acceptAlertIfPresent(driver);
			Navigator.waitForPageLoad(driver);
			if (WebDriverUtils.getHowManyByPresntInPage(driver, by, false) == 0) {
				Navigator.explicitWait();
			}
			if (we.isTopMenu()) {
				WebDriverUtils.mouseOver(driver, by);
			} else {
				WebDriverUtils.clickLink(driver, by);
			}

		}
		prevWebElementList = webElementList;
	}

	public static void explicitWait() {
		explicitWait(Integer.parseInt(Config.getInstance().getProperty(
				"ExplicitWait_millis")));
	}

	public static void explicitWait(final long wait_millis) {
		try {
			Thread.sleep(wait_millis);
		} catch (final InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Wait for Elements to occur
	 * 
	 * @param driver
	 * @param by
	 */
	public static void waitForElementLoad(final WebDriver driver, final By by) {
		/*
		 * Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
		 * .withTimeout(
		 * Integer.parseInt(Config.getInstance().getProperty("WaitAjaxElment_millis"
		 * )), TimeUnit.MILLISECONDS) .pollingEvery(300, TimeUnit.MILLISECONDS)
		 * .ignoring(Exception.class);
		 * wait.until(ExpectedConditions.presenceOfElementLocated(by));
		 */
		waitForPageLoad(driver);
		double startTime;
		double endTime, totalTime;
		final Double period = Double.parseDouble(Config.getInstance().getProperty(
				"WaitAjaxElment_millis"));
		int size = WebDriverUtils.getHowManyByPresntInPage(driver, by, false);
		startTime = System.currentTimeMillis();
		while (size <= 0) {

			endTime = System.currentTimeMillis();
			totalTime = endTime - startTime;

			if (totalTime > period) {
				// explicitWait();
				throw new RuntimeException("Timeout finding webelement "
						+ by.toString()
						+ " PLS CHECK report.xls for screen captured");
			}
			try {
				Navigator.explicitWait(1000);
				size = WebDriverUtils.getHowManyByPresntInPage(driver, by,
						false);
			} catch (final StaleElementReferenceException ser) {
				System.out.println("waitForElementLoad: " + ser.getMessage());
			} catch (final NoSuchElementException nse) {
				System.out.println("waitForElementLoad: " + nse.getMessage());
			} catch (final Exception e) {
				System.out.println("waitForElementLoad: " + e.getMessage());
			}
		}
	}

	/**
	 * Wait for javascript Ajax to finish at back-end in browser.
	 * 
	 * @param driver
	 * @param by
	 */
	public static void waitForAjax(final WebDriver driver, final By by) {
		
		final int timeoutInSeconds = Integer.parseInt(Config.getInstance()
				.getProperty("WaitAjaxElment_millis")) / 1000;
		waitForPageLoad(driver);
		if (driver instanceof JavascriptExecutor) {
			final JavascriptExecutor jsDriver = (JavascriptExecutor) driver;

			for (int i = 0; i < timeoutInSeconds; i++) {
				try {
					final Object numberOfAjaxConnections = jsDriver
							.executeScript("return jQuery.active");
					// return should be a number
					if (numberOfAjaxConnections instanceof Long) {
						final Long n = (Long) numberOfAjaxConnections;
						// check n=0
						if (n.intValue() == 0) {
							break;
						}
						explicitWait(500);
					}
				} catch (final WebDriverException e) {
					break;
				}
			}
			// after finish loading Ajax Element, then look for it
			waitForElementLoad(driver, by);
		} else {
			System.out.println("Web driver: " + driver
					+ " cannot execute javascript");
		}

	}

	/**
	 * Wait until page is fully loaded when switching windows. And
	 * FirefoxWebDriver has implemented automatically
	 * 
	 * @param driver
	 */
	public static void waitForPageLoad(final WebDriver driver) {
		final ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(final WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript(
						"return document.readyState").equals("complete");
			}
		};

		final WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(Config
				.getInstance().getProperty("WaitAjaxElment_millis")) / 1000);
		wait.until(pageLoadCondition);
	}

	/**
	 * Disable JQuery Animation to speed up execution and make execution more
	 * stable
	 * 
	 * @param driver
	 */
	public static void disableJQueryAnimation(final WebDriver driver) {
		((JavascriptExecutor) driver).executeScript("jQuery.fx.off=true");
	}
}
