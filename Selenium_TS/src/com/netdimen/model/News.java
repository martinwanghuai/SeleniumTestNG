package com.netdimen.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.netdimen.abstractclasses.TestObject;
import com.netdimen.config.Config;
import com.netdimen.config.Labels;
import com.netdimen.utils.CriteriaParser;
import com.netdimen.utils.WebDriverUtils;
import com.netdimen.view.FunctionUI;
import com.netdimen.view.Navigator;
import com.netdimen.view.PermissionUI;
import com.netdimen.view.SelectorsUI;

/**
 * 
 * @author martin.wang
 *
 */
public class News extends com.netdimen.abstractclasses.TestObject {
	private String isPoll = "", Survey = "", ExpireDate = "",
			AutoDeleteDate = "", IsAutoDeleted = "", Status = "", Format = "",
			Category = "", Order = "", Title = "", PictureURL = "",
			FileURL = "", Teaser = "", Text = "", ReplaceNewLine = "",
			TargetAudience = "", Answer = "";

	public boolean equals(TestObject obj) {

		boolean result = false;
		if (obj instanceof News && this.toString().equals(obj.toString())) {
			result = true;
		}

		return result;
	}

	public void checkExpectedResult_UI(WebDriver driver, String str) {
		super.checkExpectedResult_UI(driver, str);
	}

/*	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getClass().getName()).append("_")
				.append(this.getFuncType()).append("_").append(this.getTitle());

		if (!this.getSurvey().equalsIgnoreCase("")) {
			sb.append("_").append(this.getSurvey());
		}

		return sb.toString();
	}
*/
	public String getisPoll() {
		return isPoll;
	}

	public String getSurvey() {
		return Survey;
	}

	public String getExpireDate() {
		return ExpireDate;
	}

	public String getAutoDeleteDate() {
		return AutoDeleteDate;
	}

	public String getisAutoDeleted() {
		return IsAutoDeleted;
	}

	public String getStatus() {
		return Status;
	}

	public String getFormat() {
		return Format;
	}

	public String getCategory() {
		return Category;
	}

	public String getOrder() {
		return Order;
	}

	public String getTitle() {
		return Title;
	}

	public String getPictureURL() {
		return PictureURL;
	}

	public String getFileURL() {
		return FileURL;
	}

	public String getTeaser() {
		return Teaser;
	}

	public String getText() {
		return Text;
	}

	public String getReplaceNewLine() {
		return ReplaceNewLine;
	}

	public String getTargetAudience() {
		return TargetAudience;
	}

	public void setisPoll(String ispoll) {
		isPoll = ispoll;
	}

	public void setSurvey(String survey) {
		Survey = survey;
	}

	public void setExpireDate(String expiredate) {
		ExpireDate = expiredate;
	}

	public void setAutoDeleteDate(String autodeletedate) {
		AutoDeleteDate = autodeletedate;
	}

	public void setIsAutoDeleted(String isautodeleted) {
		IsAutoDeleted = isautodeleted;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public void setFormat(String format) {
		Format = format;
	}

	public void setCategory(String category) {
		Category = category;
	}

	public void setOrder(String order) {
		Order = order;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public void setPictureURL(String pictureurl) {
		PictureURL = pictureurl;
	}

	public void setFileURL(String fileurl) {
		FileURL = fileurl;
	}

	public void setTeaser(String teaser) {
		Teaser = teaser;
	}

	public void setText(String text) {
		Text = text;
	}

	public void setReplaceNewLine(String replacenewline) {
		ReplaceNewLine = replacenewline;
	}

	public void setTargetAudience(String targetaudience) {
		TargetAudience = targetaudience;
	}

	public String getAnswer() {
		return Answer;
	}

	public void setAnswer(String answer) {
		Answer = answer;
	}

	public void setAnswer_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
			By by = By.xpath("//label[contains(text(),'" + str + "')]");
			WebDriverUtils.checkRadio(driver, by);

			by = By.name("SUBMIT");
			WebDriverUtils.clickButton(driver, by);
		}
	}

	public void setName_UI(WebDriver driver, String str) {
	}

	public void setisPoll_UI(WebDriver driver, String str) {
		By by = By.id("POLL");
		if (str.equalsIgnoreCase("yes")) {
			WebDriverUtils.check_checkbox(driver, by);
			by = By.name("GETTID");
			WebDriverUtils.clickButton(driver, by);
			WebDriverUtils.switchToPopUpWin(driver);			
			SelectorsUI.PopUp_Selector(driver,  SelectorsUI.PopUpSelector.TopDownSelector, this.getSurvey());           
			WebDriverUtils.switchToParentWin(driver);

		} else {
			WebDriverUtils.uncheck_checkbox(driver, by);
		}
	}

	public void setSurvey_UI(WebDriver driver, String str) {
	}

	public void setExpireDate_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
			String xpath_calendar = "(//img[@alt='Calendar'])[1]";
			WebDriverUtils.dateSelect_Calandar(driver, str, xpath_calendar);
		}
	}

	public void setAutoDeleteDate_UI(WebDriver driver, String str) {
	}

	public void setIsAutoDeleted_UI(WebDriver driver, String str) {
	}

	public void setStatus_UI(WebDriver driver, String str) {
		if (!str.equalsIgnoreCase("")) {
			By by = By.id("AS");
			WebDriverUtils.select_selector(driver, by, str);
		}
	}

	public void setFormat_UI(WebDriver driver, String str) {
	}

	public void setCategory_UI(WebDriver driver, String str) {
	}

	public void setOrder_UI(WebDriver driver, String str) {
	}

	public void setTitle_UI(WebDriver driver, String str) {
		if (!str.equalsIgnoreCase("")) {
			By by = By.id("SJ");
			WebDriverUtils.fillin_textbox(driver, by, str);
		}
	}

	public void setPictureURL_UI(WebDriver driver, String str) {
	}

	public void setFileURL_UI(WebDriver driver, String str) {
	}

	public void setTeaser_UI(WebDriver driver, String str) {
		if (!str.equalsIgnoreCase("")) {
			By by = By.id("TSR");
			WebDriverUtils.fillin_textbox(driver, by, str);
		}
	}

	public void setText_UI(WebDriver driver, String str) {
		if (!str.equalsIgnoreCase("")) {
			By by = By.id("QTEXT1");
			WebDriverUtils.fillin_textbox(driver, by, str);
		}
	}

	public void setReplaceNewLine_UI(WebDriver driver, String str) {
	}

	public void setTargetAudience_UI(WebDriver driver, String str) {
		if (!str.equalsIgnoreCase("")) {
			By by = By.xpath("//span[contains(text(),'"+Labels.Tab_Target_Audience+"')]");
			WebDriverUtils.clickLink(driver, by);

			by = By.linkText("Organization");
			WebDriverUtils.clickLink(driver, by);

			WebDriverUtils.switchToPopUpWin(driver);
			WebDriverUtils.checkSelect_CheckBox(driver, "ALL");
			WebDriverUtils.switchToParentWin(driver);

			by = By.id("LOGIN");
			WebDriverUtils.check_checkbox(driver, by);
			by = By.id("MEKP");
			WebDriverUtils.check_checkbox(driver, by);
			by = By.xpath("//input[@value='Save']");
			WebDriverUtils.clickButton(driver, by);
		}
	}

	public void runCreate(WebDriver driver) {
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
				"ManageCenter", "News"), this);
		By by = By.id("createNews");
		WebDriverUtils.clickButton(driver, by);

		by = By.xpath("//span[@class='netd-icon icon-remove-sign']");
		WebDriverUtils.clickButton(driver, by);
		//this.setExpireDate_UI(driver, this.getExpireDate());
		this.setStatus_UI(driver, this.getStatus());
		this.setTitle_UI(driver, this.getTitle());
		this.setTeaser_UI(driver, this.getTeaser());
		this.setText_UI(driver, this.getText());
		this.setisPoll_UI(driver, this.getisPoll());
		this.setTargetAudience_UI(driver, this.getTargetAudience());

		by = By.xpath("//input[@value='Permissions']");
		WebDriverUtils.clickButton(driver, by);
		//UIFunctionUtils.setFullPermission_UI(driver, by);
		WebDriverUtils.switchToPopUpWin(driver);
		PermissionUI.setPermission_UI(driver, true, "OrgInclude:ALL;");
		WebDriverUtils.switchToParentWin(driver);
		by = By.xpath("//input[@value='Save']");
		WebDriverUtils.clickButton(driver, by);
	}

	public void runSubmitAndCheckPoll(WebDriver driver) {
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
				"LearningCenter", "HOME"), this);

		// 1. submit quick poll
		this.setAnswer_UI(driver, this.getAnswer());

		// 2. Interact with Adobe Flash to check poll statistics
		By srcBy = By.xpath("//object[@id='BarChart']/param[@name='FlashVars']");

		// 3. Check statistic
		//HashMap<String, ArrayList<String>> answer_statistics = UIFunctionUtils.parseParticipants(this.getExpectedResult());
		HashMap<String, ArrayList<String>> answer_statistics = CriteriaParser.parseKeyValueList(":", ";", this.getExpectedResult()); 
		Iterator<String> answers = answer_statistics.keySet().iterator();
		
		String[] keys = new String[answer_statistics.size()];
		String[] values = new String[keys.length];
		
		int counter = 0;
		while (answers.hasNext()) {
			String answer = answers.next();
			keys[counter] = answer;
			ArrayList<String> statistics = answer_statistics.get(answer);
			for (String expectedStatistic : statistics) {			
					values[counter] = expectedStatistic;
			}
			counter++;
		}
		
		// 3.1 check answer statistic
		WebDriverUtils.checkAdobeFlashResults(driver, srcBy, keys, values);
		
		// go back to website
		WebDriverUtils.openURL(driver, Config.getInstance().getProperty("loginURL"));
	
	}

}