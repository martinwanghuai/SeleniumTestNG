package com.netdimen.sikuli;

import java.io.File;
import java.net.URL;

import org.openqa.selenium.By;
import org.sikuli.basics.ImageLocator;
import org.sikuli.script.App;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;
import org.sikuli.webdriver.ImageElement;
import org.sikuli.webdriver.SikuliFirefoxDriver;

import com.netdimen.utils.WebDriverUtils;
import com.netdimen.view.Navigator;

public class ScreenshotComparison {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			SikuliFirefoxDriver driver = new SikuliFirefoxDriver();
			driver.get("http://localhost:8280/ekp1/servlet/ekp/login?getnews=Y");
			By by = By.id("UID");
			WebDriverUtils.fillin_textbox(driver, by, "ndadmin");

			by = By.id("PWD");
			WebDriverUtils.fillin_textbox(driver, by, "123456");

			// click login button
			URL url = SikuliFirefoxDriver.class.getResource("login.png");
			ImageElement image = driver.findImageElement(url);
			;
			image.click();

			// Navigator.navigate(driver, Navigator.URL.ScheduledReport);
			Navigator.navigate(driver, Navigator.xmlWebElmtMgr
					.getNavigationPathList("ManageCenter", "Reports"), null);
			by = By.linkText("R301 - Exam Summary Report");
			WebDriverUtils.clickLink(driver, by);
			by = By.name("runNow");
			WebDriverUtils.clickButton(driver, by);

			// Method 1
			/*
			 * WebDriverUtils.addVisitedWin(driver);
			 * WebDriverUtils.switchToPopUpWin(driver);
			 * 
			 * url = SikuliWebDriver.class.getResource("R301.png"); image =
			 * driver.findImageElement(url);;
			 * 
			 * if(image== null){ System.out.println("Not match"); }else{
			 * System.out.println("Matched"); }
			 */

			//
			Region win = App.focusedWindow().grow(-20);
			ImageLocator.setBundlePath(new File(System.getProperty("user.dir"),
					"images.sikuli").getAbsolutePath());

			int debugLight = 3; // set to 0 to switch off highlighting
			Screen s = new Screen();
			String str = "R301_true.png";
			Pattern target = new Pattern(str);
			// Method 2:
			// to eat up primary Sikuli initialization
			Match m = s.find(target.exact());

			if (m != null) {
				m.highlight(debugLight);
				System.out.println("Matched");
			}

			// Method 3:
			/*
			 * Finder fndr = new Finder(s.capture()); fndr.findAll(str); while
			 * (fndr.hasNext()) { System.out.println("Matched"); }
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
