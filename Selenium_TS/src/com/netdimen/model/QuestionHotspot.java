package com.netdimen.model;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import com.netdimen.utils.WebDriverUtils;

/**
 * 
 * @author martin.wang
 *
 */
public class QuestionHotspot extends com.netdimen.model.Question {
	private String ParentFolder = "", FolderName = "", BackgroundFile = "",
			xOffset = "", yOffset = "", Height = "", Width = "", Hint = "",
			Precomment = "", Explanation = "";

	public QuestionHotspot() {
		super();
	}

	public String getParentFolder() {
		return ParentFolder;
	}

	public String getFolderName() {
		return FolderName;
	}

	public String getBackgroundFile() {
		return BackgroundFile;
	}

	public String getxOffset() {
		return xOffset;
	}

	public String getyOffset() {
		return yOffset;
	}

	public String getHeight() {
		return Height;
	}

	public String getWidth() {
		return Width;
	}

	public String getHint() {
		return Hint;
	}

	public String getPrecomment() {
		return Precomment;
	}

	public String getExplanation() {
		return Explanation;
	}

	public void setParentFolder(String parentfolder) {
		ParentFolder = parentfolder;
	}

	public void setFolderName(String foldername) {
		FolderName = foldername;
	}

	public void setBackgroundFile(String backgroundfile) {
		BackgroundFile = backgroundfile;
	}

	public void setxOffset(String xoffset) {
		xOffset = xoffset;
	}

	public void setyOffset(String yoffset) {
		yOffset = yoffset;
	}

	public void setHeight(String height) {
		Height = height;
	}

	public void setWidth(String width) {
		Width = width;
	}

	public void setHint(String hint) {
		Hint = hint;
	}

	public void setPrecomment(String precomment) {
		Precomment = precomment;
	}

	public void setExplanation(String explanation) {
		Explanation = explanation;
	}

	public void setParentFolder_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
		}
	}

	public void setFolderName_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
		}
	}

	public void setBackgroundFile_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
		}
	}

	public void setxOffset_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
		}
	}

	public void setyOffset_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
		}
	}

	public void setHeight_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
		}
	}

	public void setWidth_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
		}
	}

	public void setHint_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
		}
	}

	public void setPrecomment_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
		}
	}

	public void setExplanation_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
		}
	}
	
	public void createQ(WebDriver driver){
		super.runCreateQ(driver);
		By by = By.xpath("//tr[descendant::td[contains(text(),'Background Image')]]/td/table/tbody/tr/td/button[@id='SELECT_BUTTON']");
		WebDriverUtils.clickLink(driver, by);
		WebDriverUtils.switchToPopUpWin(driver);
		WebDriverUtils.switchToFrame(driver, "FR_MAIN");
		//1. Set background file
		Repository repo = new Repository();
		repo.setParentFolder(this.getParentFolder());
		repo.setFolderName(this.getFolderName());
		String files = this.getBackgroundFile() + ";" ;
		repo.setFiles(files);
		repo.selectFile(driver);
		
		WebDriverUtils.switchToParentWin(driver);
		WebDriverUtils.switchToFrame(driver, "QFR_MAIN");
		
	    
	    //2. Set hot area
		by = By.xpath("//input[@value='Edit hot area']");
		WebDriverUtils.clickButton(driver, by);
		
		//Make fields editable
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("document.getElementById(\"dx1\").removeAttribute(\"readonly\")");
		js.executeScript("document.getElementById(\"dy1\").removeAttribute(\"readonly\")");
		js.executeScript("document.getElementById(\"dw1\").removeAttribute(\"readonly\")");
		js.executeScript("document.getElementById(\"dh1\").removeAttribute(\"readonly\")");
		
		by = By.id("dx1");
		WebDriverUtils.fillin_textbox(driver, by, this.getxOffset());
		
		by = By.id("dy1");
		WebDriverUtils.fillin_textbox(driver, by, this.getyOffset());
		
		by = By.id("dw1");
		WebDriverUtils.fillin_textbox(driver, by, this.getWidth());
		
		by = By.id("dh1");
		WebDriverUtils.fillin_textbox(driver, by, this.getHeight());
		
		//3. Show addtional comments
		by = By.name("displayCmtButt");
		WebDriverUtils.clickButton(driver, by);
		
		by = By.id("HINT");
		WebDriverUtils.fillin_textbox(driver, by, this.getHint());

		by = By.id("PRE");
		WebDriverUtils.fillin_textbox(driver, by, this.getPrecomment());

		by = By.id("PST");
		WebDriverUtils.fillin_textbox(driver, by, this.getExplanation());
	}

	public void runCreate(WebDriver driver) {
		this.createQ(driver);

		//Save questions
		WebDriverUtils.switchToFrame(driver, "QFR_TOP");
		By by = By.cssSelector("img[alt=\"Save question\"]");
	    WebDriverUtils.clickButton(driver, by);
	    
	    //Close pop-up windows
	    WebDriverUtils.closeAllPopUpWins(driver);
	}

}