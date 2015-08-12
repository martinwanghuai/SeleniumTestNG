package com.netdimen.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.netdimen.config.Labels;
import com.netdimen.junit.JUnitAssert;
import com.netdimen.utils.CriteriaParser;
import com.netdimen.utils.WebDriverUtils;

/**
 * 
 * @author martin.wang
 *
 */
public class TSSystemUI {

	public boolean equals(final com.netdimen.abstractclasses.TestObject para0) {
		final boolean result = true;
		return result;
	}

	private TSSystemUI() {

	}

	/**
	 * precondition: switch to ndadmin in EKPTestData filed "ScheduleTask", just
	 * use ":" to separate frequency and type if have more than one schedule
	 * task need to run, separate by line example- Weekly:All Daily:All
	 * 
	 * @param driver
	 * @param task
	 */
	public static void runScheduledTask(final WebDriver driver, final String task) {
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr
				.getNavigationPathList("ManageCenter", "ScheduledTasks"));

		String frequency = "", type = "";
		final HashMap<String, ArrayList<String>> tasks = CriteriaParser
				.parseKeyValueList(":", "", task);
		final Iterator<String> keySetIterator = tasks.keySet().iterator();
		By by = null;
		while (keySetIterator.hasNext()) {
			frequency = keySetIterator.next();
			type = tasks.get(frequency).get(0); // assume the arraylist size = 1

			by = By.xpath("//input[@value='" + frequency.toLowerCase() + "']");
			WebDriverUtils.checkRadio(driver, by);

			by = By.id("tasktype");
			WebDriverUtils.select_selector(driver, by, type);

			by = By.xpath("//input[@value='Run scheduled tasks']");
			WebDriverUtils.clickButton(driver, by);

			JUnitAssert.assertTrue(WebDriverUtils.textPresentInPage(driver,
					Labels.Msg_Tasks_Completed.toString()),
					"Fail as Schedule Task is not completed successfully, task type="
							+ type);
		}
	}

	/**
	 * precondition: switch to ndadmin in EKPTestData field "SysConf", just use
	 * ":" to separate sys config type and value if have more than one sys
	 * config need to set, separate by line example- Enable Session-level
	 * Catalog Search Results:disable Search result format.:Learning
	 * Module/Program Name, Learning Type, Catalog, and Brief Description
	 * 
	 * @param driver
	 * @param conf
	 */
	public static void setupSystemConf(final WebDriver driver, final String conf) {
		String key = "", value = "";
		final HashMap<String, ArrayList<String>> confs = CriteriaParser
				.parseKeyValueList(":", "", conf);
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr
				.getNavigationPathList("ManageCenter", "SystemConf"));

		final Iterator<String> keySetIterator = confs.keySet().iterator();
		By by = null;
		while (keySetIterator.hasNext()) {
			key = keySetIterator.next();
			value = confs.get(key).get(0); // assume the arraylist size = 1

			// 1.1: apply to "Module Launches", "Module Launches" and so on
			by = By.xpath("//tr[descendant::td/div[contains(text(),'" + key
					+ "')]]/td/div[descendant::label[contains(text(),'" + value
					+ "')]]/input");
			int size = WebDriverUtils.getHowManyByPresntInPage(driver, by,
					false);
			if (size > 0) {
				WebDriverUtils.checkRadio(driver, by);
			} else {
				// 1.2:apply to "Sort users by last name?",
				// "Show last name first?" and so on
				by = By.xpath("//tr[descendant::td/label[contains(text(),'"
						+ key + "')]]/td/input");
				size = WebDriverUtils.getHowManyByPresntInPage(driver, by,
						false);
				if (size > 0) {
					if (value.toLowerCase().contains("disable")) {
						WebDriverUtils.uncheck_checkbox(driver, by);
					} else if (value.toLowerCase().contains("enable")) {
						WebDriverUtils.check_checkbox(driver, by);
					}
				} else {
					// 1.3: apply to "Default Pop-up Window Size" and so on.
					by = By.xpath("//tr[descendant::td[contains(text(),'" + key
							+ "')]]/td/input[@type='TEXT']");
					size = WebDriverUtils.getHowManyByPresntInPage(driver, by,
							false);
					if (size > 0) {
						WebDriverUtils.fillin_textbox(driver, by, value);
					} else {
						// 1.4: apply to
						// "Trusted Sites for Proxied Course Launches " and so
						// on.
						by = By.xpath("//tr[descendant::td[contains(text(),'"
								+ key + "')]]/td/label/textarea");
						size = WebDriverUtils.getHowManyByPresntInPage(driver,
								by, false);
						if (size > 0) {
							WebDriverUtils.fillin_textbox(driver, by, value);
						} else {
							// 1.5: apply to "Course Player", "Other Name Field"
							// and so on.
							by = By.xpath("//tr[descendant::td[contains(text(),'"
									+ key
									+ "')]]/td/label[contains(text(),'"
									+ value + "')]/input");
							size = WebDriverUtils.getHowManyByPresntInPage(
									driver, by, false);
							if (size > 0) {
								WebDriverUtils.checkRadio(driver, by);
							} else {
								// 1.6: apply to
								// "Default Working Days Start Day" and so on.
								by = By.xpath("//tr[descendant::td[contains(text(),'"
										+ key + "')]]/td/select");
								size = WebDriverUtils.getHowManyByPresntInPage(
										driver, by, false);
								if (size > 0) {
									WebDriverUtils.select_selector(driver, by,
											value);
									;
								} else {
									// 1.7: apply to
									// "Competency Revocation E-mail:" and so
									// on.
									by = By.xpath("//tr[descendant::td[contains(text(),'"
											+ key + "')]]/td/select");
									size = WebDriverUtils
											.getHowManyByPresntInPage(driver,
													by, false);
									if (size > 0) {
										WebDriverUtils.select_selector(driver,
												by, value);
										;
									}
								}
							}
						}
					}
				}
			}

		}

		// 2. Save changes
		by = By.xpath("//input[@name='SAVE' and @value='Save']");
		WebDriverUtils.clickButton(driver, by);
	}

}