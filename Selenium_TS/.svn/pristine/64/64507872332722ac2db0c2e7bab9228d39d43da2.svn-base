package com.netdimen.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.netdimen.utils.WebDriverUtils;

/**
 * 
 * @author martin.wang
 *
 */
public class QuestionDragAndDrop extends com.netdimen.model.QuestionHotspot {
	private String DragFile = "";

	public QuestionDragAndDrop() {
		super();
	}

	public String getDragFile() {
		return DragFile;
	}

	public void setDragFile(String dragfile) {
		DragFile = dragfile;
	}

	public void setDragFile_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
		}
	}

	public void runCreate(WebDriver driver) {
		super.createQ(driver);

		// 1. dismiss warning alert
		// WebDriverUtils.acceptAlert(driver);
		// WebDriverUtils.switchToFrame(driver, "FR_MAIN");

		// 2. Set drag file
		By by = By.xpath("//tr[descendant::td[contains(text(),'Draggable Image')]]/td/table/tbody/tr/td/button[@id='SELECT_BUTTON']");

		WebDriverUtils.clickButton(driver, by);
		WebDriverUtils.switchToPopUpWin(driver);
		WebDriverUtils.switchToFrame(driver, "FR_MAIN");
		Repository repo = new Repository();
		repo.setParentFolder(this.getParentFolder());
		repo.setFolderName(this.getFolderName());
		repo.setFiles(this.getDragFile());
		repo.selectFile(driver);

		WebDriverUtils.switchToParentWin(driver);
		WebDriverUtils.switchToFrame(driver, "QFR_MAIN");

		// 3. Save questions
		WebDriverUtils.switchToFrame(driver, "QFR_TOP");
		by = By.cssSelector("img[alt=\"Save question\"]");
		WebDriverUtils.clickButton(driver, by);

		// 4. Close pop-up windows
		WebDriverUtils.closeAllPopUpWins(driver);
	}

}