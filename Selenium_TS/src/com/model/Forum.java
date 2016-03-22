package com.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.config.Config;
import com.config.Labels;
import com.junit.JUnitAssert;
import com.utils.WebDriverUtils;
import com.view.Navigator;

/**
 * 
 * @author martin.wang
 *
 */
public class Forum extends com.abstractclasses.TestObject {
	private String CourseID = "", Category = "", ForumID = "",
			TopicSubject = "", TopicText = "", ModuleTitle = "";

	public String getTopicSubject() {
		return TopicSubject;
	}

	public String getForumID() {
		return ForumID;
	}

	public String getModuleTitle() {
		return ModuleTitle;
	}

	public void setModuleTitle(String moduleTitle) {
		ModuleTitle = moduleTitle;
	}

	public void setForumID(String forumID) {
		ForumID = forumID;
	}

	public String getTopicText() {
		return TopicText;
	}

	public void setTopicSubject(String topicSubject) {
		TopicSubject = topicSubject;
	}

	public void setTopicText(String topicText) {
		TopicText = topicText;
	}

	public String getCategory() {
		return Category;
	}

	public void setCategory(String category) {
		Category = category;
	}

	public void setCategory_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
			By by = By.id("BID");
			WebDriverUtils.select_selector(driver, by, str);
		}
	}

	public void setForumID_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
			final By by = By.linkText(str);
			int size = WebDriverUtils.getHowManyByPresntInPage(driver,by,false);

			if (size == 0) {
				// 1.1 create it if not exist
				By byTemp = By.xpath("//input[@type='SUBMIT' and @value='"+Labels.Msg_Create_Forum+"']");
				WebDriverUtils.clickButton(driver, byTemp);

				byTemp = By.id("TOPIC");
				WebDriverUtils.fillin_textbox(driver, byTemp, str);

				byTemp = By.xpath("//input[@name='createButton' and @value='Create']");
				WebDriverUtils.clickButton(driver, byTemp);

				byTemp = By.xpath("//input[@name='SUBMIT' and @value='Continue']");
				WebDriverUtils.clickButton(driver, byTemp);
			}
			// 1.2 click it if exist
			WebDriverUtils.clickLink(driver, by);
		}
	}

	public boolean equals(com.abstractclasses.TestObject para0) {
		boolean result = false;
		return result;
	}

	public Forum() {
		super();
	}

	/*
	 * public String toString(){ StringBuilder sb = new StringBuilder().
	 * append(this.getClass().getName()). append("_").
	 * append(this.getFuncType()). append("_"). append(this.getUID()).
	 * append("_"). append(this.getForumID());
	 * 
	 * 
	 * return sb.toString(); }
	 */
	public String getCourseID() {
		return CourseID;
	}

	public void setCourseID(String courseid) {
		CourseID = courseid;
	}

	public void setTopicSubject_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
			By by = By.id("TOPIC");
			WebDriverUtils.fillin_textbox(driver, by, str);
		}
	}

	public void setTopicText_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
			By by = By.id("TAREA1");
			WebDriverUtils.fillin_textbox(driver, by, str);
		}
	}

	public void runLaunchForumInKC(WebDriver driver) {
		// System.out.println(this.getCourseID());

		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
				"LearningCenter", "MyCurrentCourses"), this);

		// 1. Click into KC
		By by = By.xpath("//li[descendant::div/h4[text()='"
				+ this.getModuleTitle() + "']]/div[@class='module-actions']/a");

		WebDriverUtils.clickLink(driver, by);
		Navigator.explicitWait(1000);

		by = By.xpath("//div[@class='sidebar']/ul/li[descendant::a[@data-tag='LISTFORUM']]/a");
		WebDriverUtils.clickLink(driver, by);

		by = By.linkText("Course Comments");
		WebDriverUtils.clickLink(driver, by);

		WebDriverUtils.switchToPopUpWin(driver);
		this.setCategory_UI(driver, this.getCategory());

		// 2. try forum
		this.setForumID_UI(driver, this.getForumID());

		// 3. create topic
		if (!this.getTopicSubject().equals("")
				|| !this.getTopicText().equals("")) {
			by = By.xpath("//input[@type='SUBMIT' and @value='"+Labels.Btn_Post_Topic+"']");
			WebDriverUtils.clickButton(driver, by);
			System.out.println("Clicked Create New Topice");
			
			this.setTopicSubject_UI(driver, this.getTopicSubject());
			this.setTopicText_UI(driver, this.getTopicText());

			by = By.xpath("//input[@type='SUBMIT' and @value='Create']");
			WebDriverUtils.clickButton(driver, by);
			System.out.println("Clicked Create");
			
			by = By.xpath("//input[@type='SUBMIT' and @value='Continue']");
			WebDriverUtils.clickButton(driver, by);

			by = By.xpath("//tr[descendant::td/font/a[text()='"
					+ this.getTopicSubject() + "']]");
			int size = WebDriverUtils.getHowManyByPresntInPage(driver,by,true);
			JUnitAssert.assertTrue(size > 0, "Fail to find new created topic:"
					+ this.getTopicSubject());
		}
	}

}