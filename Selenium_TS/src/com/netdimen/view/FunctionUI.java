package com.netdimen.view;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.netdimen.config.Config;
import com.netdimen.utils.CriteriaParser;
import com.netdimen.utils.WebDriverUtils;

/**
 * This class provides common function-related APIs. For example: 1.
 * runAutoEnroll: shared by Modules and JobProfiles 2. expandTree: shared by
 * Organizations and Catalogs 3. setOrgAttributes_UI: shared by permission
 * setter and auto-enrollment of modules/job profiles
 * 
 * @author martin.wang
 *
 */
public class FunctionUI {

	// Suppress default constructor for noninstantiability
	private FunctionUI() {

		throw new AssertionError();
	}

	/**
	 * SearchModuleInManagerCenter
	 * 
	 * @param driver
	 * @param moduleid
	 */
	public static void SearchModuleInManagerCenter(WebDriver driver,
			String moduleId) {

		By by = By.id(Navigator.xmlWebElmtMgr.getWebElementWrapper(
				"ManageCenter", "SearchModule").getElementValue());
		driver.findElement(by).clear();
		driver.findElement(by).sendKeys(moduleId);
		driver.findElement(By.name("apply-filters")).click();
		Navigator.waitForPageLoad(driver);
	}

	/**
	 * Auto-enroll users based on organization attributes
	 * 
	 * @param driver
	 * @param values
	 * @param by
	 */
	public static void setOrgAttributes_UI(WebDriver driver,
			ArrayList<String> values, By by) {
		Navigator.explicitWait();
		WebDriverUtils.clickButton(driver, by);
		FunctionUI.setOrgAttributes_UI(driver, values.toArray(new String[0]));
	}

	/**
	 * A controller to set participants based on organization/organization
	 * attributes/Employment Information/User Attributes. This method can
	 * support JobProfile/LearningModule auto-enroll, User Group Creation, User
	 * DA assignment.
	 *
	 * 
	 * @param driver
	 * @param str_participants
	 * @param by
	 */
	public static void setParticipants_UI(WebDriver driver,
			String str_participants, By by) {
		FunctionUI.setParticipants_UI(driver,
				CriteriaParser.parseKeyValueList(":", null, str_participants),
				by);
	}

	/**
	 * A controller to set participants
	 * 
	 * @param driver
	 * @param criteria_values
	 * @param webElement
	 *            : Web Element for "Organization Attributes"
	 */
	public static void setParticipants_UI(WebDriver driver,
			HashMap<String, ArrayList<String>> criteria_values, By webElement) {
		Iterator<String> criteria_ite = criteria_values.keySet().iterator();
		while (criteria_ite.hasNext()) {
			// Organization Attributes:Org_Numeric=0 to 200
			String criteria = criteria_ite.next(); // criteria =
													// Organization
													// Attributes
			ArrayList<String> values = criteria_values.get(criteria);// values =
																		// Org_Numeric=0
																		// to
																		// 200

			switch (criteria.toLowerCase()) {
			case "organization attributes":
				setOrgAttributes_UI(driver, values, webElement);
				break;

			case "organization":
				By by = By.xpath("//a[contains(text(),'Organization')]");
				WebDriverUtils.clickLink(driver, by);
				WebDriverUtils.switchToPopUpWin(driver);
				Navigator.explicitWait(1000);
				WebDriverUtils.checkSelect_CheckBox(driver, values.get(0));
				WebDriverUtils.switchToParentWin(driver);
				WebDriverUtils.switchToFrame(driver, "BSCAT_MAIN");
				break;

			default:
				break;
			}
		}
	}

	/**
	 * Apply to expand trees to select catalog, organization, competency models,
	 * proficiency levels, job profile levels
	 * 
	 * @param driver
	 * @param path
	 */
	public static void expandTree_UI(WebDriver driver, String path) {
		if (!path.equals("")) {
			String[] nodes = path.split("/");
			String xpath_parent = "//li[descendant::span/a[contains(text(),'"
					+ nodes[0].trim() + "')]]/";
			By by = null;
			String node = "";
			for (int i = 1; i < nodes.length; i++) {
				node = nodes[i].trim();
				xpath_parent += "ul/li[descendant::span/a[contains(text(),'"
						+ node + "')]]/";
				by = By.xpath(xpath_parent
						+ "span/span[starts-with(@id, \"EXPAND_NODE_ID\")]");
				WebDriverUtils.clickLink(driver, by);
			}
		}
	}

	/**
	 * Set org. attributes. for permission selector/auto-enrollment
	 * 
	 * @param driver
	 * @param orgAttributes
	 *            : e.g., Org_DropDown=list2-CS;Org_Numeric=0 to 200\n
	 */
	public static void setOrgAttributes_UI(WebDriver driver,
			String[] orgAttributes) {

		By by = null;
		// values = Org_DropDown=list2-CS;Org_Numeric=0 to 200\n
		for (String value : orgAttributes) { // value: Org_Numeric = 0 to 200
			String value_tmp = value.toLowerCase();
			if (value_tmp.contains("org_numeric")) {
				by = By.xpath("//label[contains(text(),'Org_Numeric')]/input[1]");
				Navigator.explicitWait(1000);
				WebDriverUtils.check_checkbox(driver, by);

				String[] strs = value.split("=")[1].split("to");

				by = By.xpath("//label[contains(text(),'Org_Numeric')]/input[2]");
				Navigator.explicitWait(1000);
				WebDriverUtils.fillin_textbox(driver, by, strs[0].trim());

				by = By.xpath("//label[contains(text(),'Org_Numeric')]/input[3]");
				Navigator.explicitWait(1000);
				WebDriverUtils.fillin_textbox(driver, by, strs[1].trim());
			} else if (value_tmp.contains("org_freetext")) {
				by = By.xpath("//label[contains(text(),'Org_FreeText')]/input[1]");
				Navigator.explicitWait(1000);
				WebDriverUtils.check_checkbox(driver, by);

				String str = value.split("=")[1];

				by = By.xpath("//div[descendant::div/label[contains(text(),'Org_FreeText')]]/div[2]/div/input");
				Navigator.explicitWait(1000);
				WebDriverUtils.fillin_textbox(driver, by, str.trim());
			} else if (value_tmp.contains("org_dropdown")) {
				by = By.xpath("//label[contains(text(),'Org_DropDown')]/input[1]");
				Navigator.explicitWait(1000);
				WebDriverUtils.check_checkbox(driver, by);

				// value = Org_DropDown=list2-CS
				String str = value.split("=")[1];

				by = By.xpath("//div[contains(text(),'" + str
						+ "')]/input[@type='CHECKBOX']");
				Navigator.explicitWait(1000);
				WebDriverUtils.check_checkbox(driver, by);
				;
			} else if (value_tmp.contains("org_textarea")) {
				by = By.xpath("//label[contains(text(),'Org_Textarea')]/input[1]");
				Navigator.explicitWait(1000);
				WebDriverUtils.check_checkbox(driver, by);

				String str = value.split("=")[1];
				by = By.xpath("//textarea[@class='org-attr-textarea large required']");
				WebDriverUtils.fillin_textbox(driver, by, str);
			}
		}
	}

	public static void fillESignature(WebDriver driver, String UID, String PWD) {
		driver.findElement(By.id("ESIGNATURE-username")).clear();
		driver.findElement(By.id("ESIGNATURE-username")).sendKeys(UID);
		driver.findElement(By.id("ESIGNATURE-password")).clear();
		driver.findElement(By.id("ESIGNATURE-password")).sendKeys(PWD);
		driver.findElement(By.id("ESIGN-submit")).click();
	}

	/**
	 * Search keyword in Ajax search box. This applies to assign learning
	 * modules to competency, assign competency to job profiles/ad-hoc
	 * assessment.
	 * 
	 * @param driver
	 * @param keywords
	 */
	public static void searchInAjaxSearchBox(WebDriver driver,
			ArrayList<String> keywords) {
		if (keywords != null) {
			for (String keyword : keywords) {
				Navigator.explicitWait(1000);
				By by = By.id("SEARCHMODELCOMP_input");
				driver.findElement(by).clear();
				driver.findElement(by).sendKeys("");
				driver.findElement(by).sendKeys(keyword);

				by = By.xpath("//div[@id='SEARCHMODELCOMP_ctr']/div/div[@val='"
						+ keyword + "']");
				Navigator.explicitWait(1000);
				WebDriverUtils.mouseDown(driver, by);
				Navigator.explicitWait(1000);
				WebDriverUtils.mouseUp(driver, by);
			}
		}
	}

	public static String XpathCalendarIcon(WebDriver driver, String name) {
		String xpath = "//div[@class='date-container'][descendant::div/input[@name='"
				+ name + "']]/div[@class='date-button-container']/a";
		return xpath;
	}

	public static void setDates_UI(WebDriver driver, String sDate,
			String inputname) {
		if (!sDate.equals("")) {
			String xpath_calendar = FunctionUI.XpathCalendarIcon(driver,
					inputname);
			WebDriverUtils.dateSelect_Calandar(driver, sDate, xpath_calendar);
		}
	}

}
