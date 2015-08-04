package com.netdimen.utils;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.support.ui.Select;

import com.netdimen.config.Config;
import com.netdimen.dao.ChartType;
import com.netdimen.junit.JUnitAssert;
import com.netdimen.view.Navigator;
import com.netdimen.view.WebElementWrapper;

/**
 * 
 * @author martin.wang
 *
 */
public class WebDriverUtils {

	static WebDriver driver = null;
	public static ArrayList<String> visitedWins = new ArrayList<String>();
	
	// Suppress default constructor for noninstantiability
	private WebDriverUtils() {

		throw new AssertionError();
	}
	
	public enum Type {
		FireFox, Safari, Chrome, InternetExplorer
	}

	public static WebDriver getWebDriver_existing() {
		return driver;
	}

	public static boolean textPresentInPage(WebDriver driver, String text) {
		By by = By.xpath("//body");
		if(getHowManyByPresntInPage(driver, by, false) > 0){
			return driver.findElement(by).getText().contains(text);	
		}else{
			return false;
		}
		
	}
	
	public static boolean textPresentInCatalogSearch(WebDriver driver, String text) {
		By by = By.xpath("//div[@id='contentDivBody']");
		if(getHowManyByPresntInPage(driver, by, true) > 0){
	//		Navigator.waitForAjax(driver, by);
			return driver.findElement(by).getText().contains(text);	
		}else{
			return false;
		}
		
	}
	/**
	 * 
	 * @param WebDriver driver
	 * @param String textToFound 
	 * @return boolean textPresented
	 */
	public static boolean refreshingAndCheckTextPresentedInPage(WebDriver driver, String textToFound){
		boolean textPresented=false;
		int counter = 0;
		int loop_max = 20;
		while(!textPresented && counter < loop_max){
			//try 20 times to wait for system auto trigger rule to show out in UI
			driver.navigate().refresh();
			Navigator.explicitWait();
			textPresented = WebDriverUtils.textPresentInPage(driver, textToFound);;
			counter++;
		}
		return textPresented;
	}
	
	/** count how many webelement given with "by" object present in same page
	 * if no webelement is found then print out in console
	 * @param by
	 * @return
	 */
	private static int getHowManyByPresntInPage(By by){ 
		//Navigator.explicitWait(1000);
		int size = driver.findElements(by).size();

		if (size == 0) {
			if(Config.PRINTELEMENTNOTFOUNDMSG){
				System.out.println("warning: cannot find web element:"+by.toString());	
				
			}
		}
		
		return size;
	}
	
	/** count how many webelement given with "by" object present in same page
	 * if no webelement is found then print out in console
	 * @param WebDriver driver
	 * @param By by
	 * @param Boolean hasToFindIt, true for by must present; false for no need to present
	 * @return
	 */
	public static int getHowManyByPresntInPage(WebDriver driver, By by, boolean hasToFindIt){ 
		if (hasToFindIt){
			Navigator.waitForAjax(driver, by);
			return getHowManyByPresntInPage(by);
		}else{
			return getHowManyByPresntInPage(by);
		}
		
	}
	
	public static WebDriver getWebDriver_new() {
		driver = getWebDriver_new(WebDriverUtils.Type.FireFox);
		return driver;
	}
	
	public static WebDriver getWebDriver_new(FirefoxProfile profile){
		driver = new FirefoxDriver(profile);
		return driver;
	}

	public static WebDriver getWebDriver_new(Type browser_type) {

		switch (browser_type) {
		case FireFox:
			driver = new FirefoxDriver();
			break;
		case Safari:
			driver = new FirefoxDriver();
			break;
		case Chrome:
			driver = new ChromeDriver();
			break;
		case InternetExplorer:
			driver = new InternetExplorerDriver();
			break;
		}

		driver.manage()
				.timeouts()
				.implicitlyWait(
						Integer.parseInt(Config.getInstance().getProperty(
								"ImplicitWait_millis")), TimeUnit.MILLISECONDS);
		return driver;
	}

	public static void addVisitedWin(WebDriver driver) {
		addVisitedWin(driver.getWindowHandle());
	}
	
	public static void addVisitedWin(String currentWin){
		if(!visitedWins.contains(currentWin)){
			visitedWins.add(currentWin);	
		}
	}

	public static void switchToNextTab(WebDriver driver){
		WebDriverUtils.switchToPopUpWin(driver);
	}
	
	public static void switchToPreviousTab(WebDriver driver){
		WebDriverUtils.switchToParentWin(driver);
	}
	
	public static void switchToPopUpWin(WebDriver driver) {
		Set<String> wins = driver.getWindowHandles();
		wins.removeAll(visitedWins);// remove the visited pop up windows, but not the newly pop up window
		String[] wins_temp = wins.toArray(new String[0]);
		if (wins_temp.length == 1) {
			String currentWin = wins_temp[0];
			driver.switchTo().window(currentWin);
			visitedWins.add(currentWin);
		}
	}

	
	public static boolean hasPopUpWin(WebDriver driver){
		boolean hasPopUpWin = false;
		Set<String> wins = driver.getWindowHandles();
		wins.removeAll(visitedWins);
		String[] wins_temp = wins.toArray(new String[0]);
		if (wins_temp.length == 1) {
			hasPopUpWin = true;
		}
		
		return hasPopUpWin;
	}
	
	public static void switchToParentWin(WebDriver driver) {
		int size = visitedWins.size();
		visitedWins.remove(size - 1);// remove current pop up window
		if (size - 2 < 0) {
			// reaching this code means closing parent windows more than it has
			driver.switchTo().window(visitedWins.get(0));
		} else {
			driver.switchTo().window(visitedWins.get(size - 2));// return to the parent window 
		}
	}

	/**Switch to the first window but not close pop-up window
	 * 
	 * @param driver
	 */
	public static void switchToBaseWin(WebDriver driver) {
		String currentWin = driver.getWindowHandle();
		clearVisitedWins();
		addVisitedWin(currentWin);
		driver.switchTo().window(currentWin); //switch to the current window
	}

	public static void clearVisitedWins() {
		visitedWins.clear();
	}

	/**Switch to a frame in the window
	 * 
	 * @param driver
	 * @param frameID: ID of the frame
	 */
	public static void switchToFrame(WebDriver driver, String frameID) {
		By by = By.id(frameID);
		switchToFrame(driver, by);
	}

	public static void switchToFrame(WebDriver driver, By frame) {
		driver.switchTo().defaultContent();
		Navigator.waitForAjax(driver, frame);
		WebElement we = driver.findElement(frame);
		driver.switchTo().frame(we);
		
	}

	public static void switchToNestedFrame(WebDriver driver, ArrayList<By> bys) {
		driver.switchTo().defaultContent();

		int size = -1;
		for (By by : bys) {
			size = getHowManyByPresntInPage(driver, by, false);
			if (size == 1) {
				WebElement we = driver.findElement(by);
				driver.switchTo().frame(we);
			}
		}
	}

	/**Close all windows and tear down web driver
	 * 
	 * @param driver
	 */
	public static void closeAllWins(WebDriver driver) {
		Set<String> wins = driver.getWindowHandles();
		String[] wins_temp = wins.toArray(new String[0]);

		for (int i = wins_temp.length - 1; i > -1; i--) {
			String currentWin = wins_temp[i];
			driver.switchTo().window(currentWin);
			driver.close();
		}

		WebDriverUtils.clearVisitedWins();
	}

	/**Close all pop-up windows and switch to the base(first) window
	 * 
	 * @param driver
	 */
	public static void closeAllPopUpWins(WebDriver driver) {
		Set<String> wins = driver.getWindowHandles();

		String[] wins_temp = wins.toArray(new String[0]);
		if (visitedWins.size() > 0) {
			for (int i = 0; i < wins_temp.length; i++) {
				String currentWin = wins_temp[i];
				if (!currentWin.equals(visitedWins.get(0))) {
					driver.switchTo().window(currentWin);
					driver.close();
				}
			}

			
			driver.switchTo().window(visitedWins.get(0));
			clearVisitedWins();
			addVisitedWin(driver);
		}else{
			WebDriverUtils.switchToBaseWin(driver);	
		}
	}
	
	
	
	
	
	
	/**Apply to select catalog or organization.
	 * 
	 * @param driver
	 * @param keywords
	 */
	public static void checkSelect_CheckBox(WebDriver driver, String[] keywords){
		By by = By.linkText("Expand and Display Entire Hierarchy Tree");
		WebDriverUtils.clickLink(driver, by);
		
		for(String keyword: keywords){
			WebDriverUtils.checkSelect_CheckBox_single(driver, keyword);
		}
		
		by = By.name("save");
		WebDriverUtils.clickButton(driver, by);		
	}
	

	
	private static void uncheckSelect_CheckBox_single(WebDriver driver, String keyword){
		By by = null;
		
		if (!keyword.equals("")) {
			String xpath = "";
			if (!keyword.contains("/")) {
				xpath = "//tr[descendant::td[contains(text(), '" + keyword
						+ "')]]/td/input[@type='CHECKBOX'][1]";
				by = By.xpath(xpath);
				int size = getHowManyByPresntInPage(driver, by, false);
				if (size == 0) {// for TS100, 93, 92
					keyword = keyword.toUpperCase();
					xpath = "//tr[descendant::td[contains(text(), '" + keyword
							+ "')]]/td/input[@type='CHECKBOX'][1]";
					by = By.xpath(xpath);
				}
				uncheck_checkbox(driver, by);
			} else {
				 String[] keywords = keyword.split("/");
				 String str = keywords[keywords.length-1];
				 WebDriverUtils.checkSelect_CheckBox_single(driver, str);				 
			}
		}	
	}
	
	
	private static void checkSelect_CheckBox_single(WebDriver driver, String keyword){
		By by = null;
		
		if (!keyword.equals("")) {
			String xpath = "";
			
			
			if (!keyword.contains("/")) {
				/*xpath = "//tr[descendant::td[contains(text(),'" + keyword
						+ "')]]/td/input[@type='CHECKBOX'][1]";*/
				
				xpath = "//tr[descendant::td[text()='" + keyword.trim()
						+ "']]/td/input[@type='CHECKBOX' or @type='RADIO'][1]";
				
				by = By.xpath(xpath);
				check_checkbox(driver, by);
			} else {
				 String[] keywords = keyword.split("/");
				 String str = keywords[keywords.length-1];
				 WebDriverUtils.checkSelect_CheckBox_single(driver, str);				 
			}
		}	
	}
	
	
	private static void checkSelect_Radio_single(WebDriver driver, String keyword){
		By by = null;
		
		if (!keyword.equals("")) {
			String xpath = "";
			
			
			if (!keyword.contains("/")) {
				/*xpath = "//tr[descendant::td[contains(text(),'" + keyword
						+ "')]]/td/input[@type='CHECKBOX'][1]";*/
				
				xpath = "//tr[descendant::td[text()='" + keyword
						+ "']]/td/input[@type='RADIO' or @type='CHECKBOX'][1]";
				
				by = By.xpath(xpath);
				check_checkbox(driver, by);
			} else {
				 String[] keywords = keyword.split("/");
				 String str = keywords[keywords.length-1];
				 WebDriverUtils.checkSelect_CheckBox_single(driver, str);				 
			}
		}	
	}

	/**
	 * Apply to choose catalog and org
	 * 
	 * @param driver
	 * @param keyword
	 */
	public static void checkSelect_CheckBox(WebDriver driver, String keyword) {
		By by = By.linkText(Config.getInstance().getProperty("link.ExpandTree"));
		WebDriverUtils.clickLink(driver, by);
		
		checkSelect_CheckBox_single(driver, keyword);		
		by = By.name("save");
		WebDriverUtils.clickButton(driver, by);		
	}
	
	/**Use to select org and catalog
	 * 
	 * @param driver
	 * @param keyword
	 */
	public static void checkSelect_Radio(WebDriver driver, String keyword) {
		By by = By.linkText(Config.getInstance().getProperty("link.ExpandTree"));
		WebDriverUtils.clickLink(driver, by);
		
		checkSelect_Radio_single(driver, keyword);		
		by = By.name("save");
		WebDriverUtils.clickButton(driver, by);		
	}

	/**
	 * Apply to choose start date and end date
	 * 
	 * @param driver
	 * @param dateString
	 * @param xpath_calendar
	 */
	public static void dateSelect_Calandar(WebDriver driver, String dateString,
			String xpath_calendar) {
		if (!dateString.equals("") && !xpath_calendar.equals("")) {
			Calendar cal = DataUtils.strToCalendarDate(dateString);

			int year = cal.get(Calendar.YEAR);
			int month = cal.get(Calendar.MONTH) + 1; // the months are numbered from 0 (January) to 11 (December).
			int day = cal.get(Calendar.DAY_OF_MONTH);

			By by = By.xpath(xpath_calendar);
			WebDriverUtils.clickLink(driver, by);

			WebDriverUtils.switchToPopUpWin(driver);
			Navigator.explicitWait(5000);
			by = By.name("syear");
			String str = year + "";
			int size = getHowManyByPresntInPage(driver, by, false);
			if (size == 1) {
				WebDriverUtils.select_selector(driver, by, str);
			} else {
				by = By.name("year");
				size = getHowManyByPresntInPage(driver, by, false);
				if (size == 1) {
					WebDriverUtils.select_selector(driver, by, str);
				}
			}

			by = By.name("smon");
			size = getHowManyByPresntInPage(driver, by, false);
			if (size == 1) {
				WebDriverUtils.select_selector(driver, by, month - 1); // 
			} else {
				by = By.name("month");
				size = getHowManyByPresntInPage(driver, by, false);
				if (size == 1) {
					WebDriverUtils.select_selector(driver, by, month - 1);
				}
			}

			by = By.linkText(day + "");
			size = WebDriverUtils.getHowManyByPresntInPage(driver, by, false);
			if(size==0){
				by = By.xpath("//font[contains(text(),'Today')]/a");
			}
			WebElement we = driver.findElement(by);
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", we);
//			WebDriverUtils.clickLink(driver, by);
			WebDriverUtils.switchToParentWin(driver);
		}
	}


	public static void importFile_ID(WebDriver driver, String HTML_ID,
			String fileName) {
		String currentPath="";
		try {
			currentPath = new java.io.File( "." ).getCanonicalPath();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.id(HTML_ID)).sendKeys(currentPath+fileName);
	}

	public static boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static void mouseOver(WebDriver driver, By by) {
		Navigator.waitForAjax(driver, by);
		WebDriverUtils.highlightElement(driver, by);
		WebElement we = driver.findElement(by);
		Actions action = new Actions(driver);
		action.moveToElement(we).build().perform();
		
	}

	public static void check_checkbox(WebDriver driver, By by) {
		Navigator.waitForAjax(driver, by);
		WebDriverUtils.highlightElement(driver, by);
		WebElement we = driver.findElement(by);
		if (!we.isSelected()) {
				we.click();
		}
	}

	public static void uncheck_checkbox(WebDriver driver, By by) {
		Navigator.waitForAjax(driver, by);
		WebDriverUtils.highlightElement(driver, by);
		WebElement we = driver.findElement(by);
			
		if (we.isSelected()) {
			we.click();
		}
	}

	public static void fillin_textbox(WebDriver driver, By by, String str) {
		Navigator.waitForAjax(driver, by);
		WebDriverUtils.highlightElement(driver, by);
		WebElement element = driver.findElement(by);
		try{
			element.clear(); 
			element = driver.findElement(by); //AICC set revision will fail if uncomment this
			element.sendKeys(str);
		}catch ( StaleElementReferenceException e ) {
			e.printStackTrace();
		}
		element.sendKeys(Keys.TAB);
	}
	
	public static void append_textbox(WebDriver driver, By by, String str){
		
		Navigator.waitForAjax(driver, by);
		WebDriverUtils.highlightElement(driver, by);
		driver.findElement(by).sendKeys(str);
		
	}

	public static void select_selector(WebDriver driver, By by, String str) {	
			Navigator.waitForAjax(driver, by);
			WebDriverUtils.highlightElement(driver, by);
			new Select(driver.findElement(by)).selectByVisibleText(str);
	}

	public static void select_selector_partialTexts(WebDriver driver, String name_selector, String str){
		By by = By.xpath("//select[@name='"+name_selector+"']/option[contains(text(),'" + str + "')]");
		Navigator.waitForAjax(driver, by);
		WebDriverUtils.highlightElement(driver, by);;
		driver.findElement(by).click();
	}
	
	public static void select_selector(WebDriver driver, By by, int index) {
		Navigator.waitForAjax(driver, by);
		WebDriverUtils.highlightElement(driver, by);
		new Select(driver.findElement(by)).selectByIndex(index);	
	}

	public static void checkRadio(WebDriver driver, By by) {
		clickButton(driver, by);
	}

	public static void clickButton(WebDriver driver, By by) {
		Navigator.waitForAjax(driver, by);
		WebDriverUtils.highlightElement(driver, by);
		driver.findElement(by).click();
	}

	public static String getTableRowText(WebDriver driver, By by_table,
			int rowIndex) {
		String rowText = "";
		int size = driver.findElements(by_table).size();
		if (size == 1) {
			By by = By.tagName("tr");
			WebDriverUtils.highlightElement(driver, by);
			rowText = driver.findElement(by_table).findElements(by)
					.get(rowIndex).getText();
		}
		return rowText;
	}

	public static int getTableRowCount(WebDriver driver, By by_rows) {
		int size = driver.findElements(by_rows).size();
		if (size >= 1) {
			WebDriverUtils.highlightElement(driver, by_rows);
		}
		return size;
	}

	public static int getTableColumnCount(WebDriver driver, By by_columns) {
		int size = driver.findElements(by_columns).size();
		if (size >= 1) {
			WebDriverUtils.highlightElement(driver, by_columns);
		}
		return size;
	}

	public static void clickLink(WebDriver driver, By by) {
		clickButton(driver, by);
	}

	public static String getTextWithoutChecking(WebDriver driver, By by){
		int size = driver.findElements(by).size();
		if (size >= 1) {
			WebDriverUtils.highlightElement(driver, by);
			return driver.findElement(by).getText();
		}
		return "";
	}
	
	public static String getText(WebDriver driver, By by) {
		Navigator.waitForAjax(driver, by);
		WebDriverUtils.highlightElement(driver, by);
		return  driver.findElement(by).getText();
	}

	/**Get the value for a given attribute
	 * 
	 * @param driver
	 * @param by
	 * @param attr
	 * @return
	 */
	public static String getAttribute(WebDriver driver, By by, String attr) {
		String result = "";
		int size = getHowManyByPresntInPage(driver, by, false);
		if (size > 0) {
			WebDriverUtils.highlightElement(driver, by);
			result = driver.findElement(by).getAttribute(attr);
		}

		return result;
	}

	public static String closeAlertAndGetItsText() {
		boolean isAccept=true;
		String alertText="Cannot get the pop up alert text, pls check it@WebDriverUtils.closeAlertAndGetItsText";

		try {
			 Alert javascriptAlert = driver.switchTo().alert();
			 alertText=javascriptAlert.getText(); // Get text on alert box
			 if (isAccept){
				 javascriptAlert.accept(); //click OK
			 }else{
				 javascriptAlert.dismiss();//click cancel
			 }
			
		}catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
		return alertText;	
	}

	public static boolean isAlertPresent(WebDriver driver) {

		boolean present = false;
		try {
			//1. Solution 1: work
			driver.switchTo().alert();
			present = true;
			
			//2. Solution 2: not work
			/*driver.getTitle();
			present = true;*/
		} catch (Exception e) {
			/*if (Config.DEBUG_MODE) {
				System.out.println(e);
			}*/
			present = false;
		}
		return present;
	}

	public static void acceptAlertIfPresent(WebDriver driver) {
		boolean exist = WebDriverUtils.isAlertPresent(driver);
		if (exist) {
			acceptAlert(driver);
		}
	}
	
	public static void acceptAlert(WebDriver driver){
		driver.switchTo().alert().accept();
	}
	
	/**Wait for alert to occur and accept it
	 * 
	 * @param driver
	 */
	public static void waitAlertAndAccept(WebDriver driver){
		boolean flag = false;
		int loop_max = 10, counter = 0;
		do{
			Navigator.explicitWait(1000);
			WebDriverUtils.refreshWindow(driver);
			flag = WebDriverUtils.isAlertPresent(driver);
			counter ++;
		}while(!flag && counter < loop_max);
		
		if(flag){
			WebDriverUtils.acceptAlert(driver);;	
		}
	}		
	
	public static void refreshWindow(WebDriver driver){
		driver.navigate().refresh();
	}
	
	public static void mouseUp(WebDriver driver, By by){
		WebElement we = driver.findElement(by);
		Locatable hoverItem = (Locatable)we;
		Mouse mouse = ((HasInputDevices)driver).getMouse();
		mouse.mouseUp(hoverItem.getCoordinates());
	}
	
	public static void mouseDown(WebDriver driver, By by){
		WebElement we = driver.findElement(by);
		Locatable hoverItem = (Locatable)we;
		Mouse mouse = ((HasInputDevices)driver).getMouse();
		mouse.mouseDown(hoverItem.getCoordinates());
	}
	
	public static void uploadFile(WebDriver driver, By by, String path){
		driver.findElement(by).sendKeys(path);
	}
	
	/**Click at a web element at (x, y) coordinate
	 * 
	 * @param driver
	 * @param by
	 * @param xOffset
	 * @param yOffset
	 */
	public static void clickAt(WebDriver driver, By by, int xOffset, int yOffset){
		Actions builder = new Actions(driver);
		WebElement toElement = driver.findElement(by);
		builder.moveToElement(toElement, xOffset, yOffset).build().perform();
	}
	
	/**Click at a web element at (x, y) coordinate
	 * 
	 * @param driver
	 * @param by
	 * @param xOffset
	 * @param yOffset
	 */
	public static void clickAt(WebDriver driver, By by, String xOffset, String yOffset){
		clickAt(driver, by, Integer.parseInt(xOffset), Integer.parseInt(yOffset));
	}
	
	/**Mouse down an elment at (x,y) coordinate
	 * 
	 * @param driver
	 * @param by
	 * @param xOffset
	 * @param yOffset
	 */
	private static void mouseDownAt(WebDriver driver, By by, int xOffset, int yOffset){
		Actions builder = new Actions(driver);
		WebElement toElement = driver.findElement(by);
		builder.keyDown(Keys.CONTROL).click(toElement).moveByOffset(xOffset, yOffset).click().build().perform();
	}
	
	/**Use JavaScript to click hidden elemnts
	 * 
	 * @param driver
	 * @param by
	 */
	public static void clickHiddenElement(WebDriver driver, By by){
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click()", driver.findElement(by));
	}
	
	/**Pre-condition:need to make both src and target elements into view
	 * 
	 * @param driver
	 * @param src
	 * @param target
	 */
	public static void dragAndDrop(WebDriver driver, By src, By target){
		Actions builder = new Actions(driver);
		WebElement srcEle = driver.findElement(src);
		WebElement destEle = driver.findElement(target);
//		builder.keyDown(Keys.CONTROL).click(srcEle).click(destEle).keyUp(Keys.CONTROL); //Method 1
		builder.clickAndHold(srcEle).moveToElement(destEle).release(destEle).build().perform(); //Method 2
//		builder.dragAndDrop(srcEle, destEle).build().perform(); //Method 3
	}
	
	/**Focus on web element
	 * 
	 * @param driver
	 * @param target
	 */
	public static void getFocus(WebDriver driver, By target){
		WebElement targetEle = driver.findElement(target);
		if(targetEle.getTagName().equals("input")){
			targetEle.sendKeys("");
		}else{
			Actions builder = new Actions(driver);
			builder.moveToElement(targetEle).perform();
		}
	
	}
	
	/**Scroll window automatically to specific web element
	 * 
	 * @param driver
	 * @param target
	 */
	public static void scrollWindowToElement(WebDriver driver, By target){
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView();", driver.findElement(target));
	}
	
	public static void scrollWindowToElementAt(WebDriver driver, By target, int xOffset){
		Point hoverItem =driver.findElement(target).getLocation();
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,"+(hoverItem.getX()+xOffset)+");"); 
	}
	
	public static void maxWindow(WebDriver driver){
		driver.manage().window().maximize();
	}
	
	public static void highlightElement(WebDriver driver, By by){
		if(Config.getInstance().enableHighlighter){
			JavascriptExecutor js = (JavascriptExecutor)driver;
			WebElement elem = driver.findElement(by); 
			js.executeScript("arguments[0].setAttribute('style', arguments[1]);", elem, "color: red; border: 2px solid red;");
			elem = driver.findElement(by); 
			String time =  Config.getInstance().getProperty("HighlightElement_millis");
			Navigator.explicitWait(Integer.parseInt(time));	
			js.executeScript("arguments[0].setAttribute('style', arguments[1]);", elem, "");
		}
	}
	
	public static void openURL(WebDriver driver, String url){
		driver.get(url);
		addVisitedWin(driver);
		//WebDriverUtils.checkEKPError(driver);
	}
	
	/**
	 * 
	 * @param driver
	 * @param srcAdobe
	 * @param key
	 * @param expectedResult
	 */
	public static void checkAdobeFlashResults(WebDriver driver, By srcBy, String key, String expectedResult){
		// 1. Interact with Adobe Flash to check poll statistics
		String temp = DataUtils.decodeURL(WebDriverUtils.getAttribute(driver,
				srcBy, "value"));
		int index = temp.indexOf("=");
		String dataURL = temp.substring(index + 1);
		String url = "http://" + Config.getInstance().getProperty("IP") + ":"
				+ Config.getInstance().getProperty("port") + dataURL;
		WebDriverUtils.openURL(driver, url);

		// 2. Check actual result
		By by = By.xpath("//set[@name='" + key + "']");
		String actualStatistic = WebDriverUtils.getAttribute(driver, by, "value");
		JUnitAssert.assertTrue(expectedResult.contains(actualStatistic), expectedResult + " not contain:" + actualStatistic); //25% vs. 25
	}
	
	public static void checkAdobeFlashResults(WebDriver driver, ChartType chartType, String url, String[] keys, String[] expectedResults){
		// 1. Interact with Adobe Flash to check poll statistics
		driver.get(url);
//		System.out.println("url="+url);
		
		// 2. Check actual result
		int counter = 0;
		switch(chartType){
			case Pie:
				for(String expectedResult: expectedResults){
					String key = keys[counter];
					By by = By.xpath("//set[@name='" + key + "']");
					String actualStatistic = WebDriverUtils.getAttribute(driver, by, "value");
					JUnitAssert.assertTrue(expectedResult.contains(actualStatistic), expectedResult + " not contain:" + actualStatistic); //25% vs. 25
					counter ++;
				}
				break;
			case Histogram:
				for(String expectedResult: expectedResults){
					String key = keys[counter];
					By by = By.xpath("//dataset[@seriesname='"+key+"']/set");
					String actualStatistic = WebDriverUtils.getAttribute(driver, by, "value");
					JUnitAssert.assertTrue(actualStatistic.contains(expectedResult),actualStatistic + " not contain:" +expectedResult); //25% vs. 25
					counter ++;
				}
				break;
			case Table:
				HashMap<String,Integer> key_level = WebDriverUtils.getLevels(keys);
				
				for(String expectedResult: expectedResults){
					String key = keys[counter];
					int level = key_level.get(key);
					
					By by = null;
					switch(level){
						case 1:
							by = By.xpath("//dataset/set");
							break;
						case 2:
							by = By.xpath("//dataset/set/set");
							break;
						case 3:
							by = By.xpath("//dataset/set/set/set");
							break;
						case 4:
						default:
							by = By.xpath("//dataset/set/set/set/set");
							break;
					}
					
					String actualStatistic = WebDriverUtils.getAttribute(driver, by, "value");
					JUnitAssert.assertTrue(actualStatistic.contains(expectedResult),actualStatistic + " not contain:" +expectedResult); //25% vs. 25
					counter ++;
				}
//				System.out.println("");
				break;
				
		}
		driver.get(Config.getInstance().getProperty("HomePage"));
		Navigator.waitForPageLoad(driver);
	}
	
	private static HashMap<String, Integer> getLevels(String[] keys){
		HashMap<String, Integer> key_level = new HashMap();
		
		for(String key:keys){
			int level = -1;
			By by = By.xpath("//categories/category");
			String key_temp = WebDriverUtils.getAttribute(driver, by, "name");
			if(key_temp.equals(key)){
				level = 1;
			}else{
				by = By.xpath("//categories/category/category");
				key_temp = WebDriverUtils.getAttribute(driver, by, "name");
				if(key_temp.equals(key)){
					level = 2;
				}else{
					by = By.xpath("//categories/category/category/category");
					key_temp = WebDriverUtils.getAttribute(driver, by, "name");
					if(key_temp.equals(key)){
						level = 3;
					}else{
						by = By.xpath("//categories/category/category/category/category");
						key_temp = WebDriverUtils.getAttribute(driver, by, "name");
						if(key_temp.equals(key)){
							level = 4;
						}	
					}
				}
			}
			
			key_level.put(key, level);
		}
		
		return key_level;
	}
	
	
	public static void checkAdobeFlashResults(WebDriver driver, By srcBy, String[] keys, String[] expectedResults){
		// 1. Interact with Adobe Flash to check poll statistics
		String temp = DataUtils.decodeURL(WebDriverUtils.getAttribute(driver,
				srcBy, "value"));
		int index = temp.indexOf("=");
		String dataURL = temp.substring(index + 1);
		String url = "http://" + Config.getInstance().getProperty("IP") + ":"
				+ Config.getInstance().getProperty("port") + dataURL;
		WebDriverUtils.openURL(driver, url);
//		System.out.println("url="+url);
		
		// 2. Check actual result
		int counter = 0;
		for(String expectedResult: expectedResults){
			String key = keys[counter];
			By by = By.xpath("//set[@name='" + key + "']");
			String actualStatistic = WebDriverUtils.getAttribute(driver, by, "value");
			JUnitAssert.assertTrue(expectedResult.contains(actualStatistic), expectedResult + " not contain:" + actualStatistic); //25% vs. 25
			counter ++;
		}
	}
	
	public static void takeScreenShot(WebDriver driver, String destFile) {
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;

		File scrFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

		try {
			FileUtils.copyFile(scrFile, new File(destFile));
		} catch (IOException ioe) {
			throw new RuntimeException(ioe);
		}

		WebDriverUtils.closeAllPopUpWins(driver);
	}

}
