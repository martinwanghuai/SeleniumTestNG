package com.model;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.utils.WebDriverUtils;
import com.view.Navigator;

public class PersonalLoan extends com.abstractclasses.TestObject {
	private String Category = "";

	@Override
	public boolean equals(final com.abstractclasses.TestObject para0) {
		final boolean result = false;
		return result;
	}

	public PersonalLoan() {
		super();
	}

	public String getCategory() {
		return Category;
	}

	public void setCategory(final String Category) {
		this.Category = Category;
	}

	public void runCheckProducts(final WebDriver driver) {
		
		driver.get("http://www.moneyhero.com.hk/en");
		Navigator.explicitWait();
		
		By by = By.partialLinkText("Personal Loans");
		WebDriverUtils.clickLink(driver, by);
		Navigator.explicitWait();
		
		by = By.partialLinkText("Find a loan");
		WebDriverUtils.clickLink(driver, by);
		Navigator.explicitWait();
		
		by = By.xpath("//a[span[contains(text(),'" + this.getCategory() + "')]]");
		WebDriverUtils.clickLink(driver, by);  
		Navigator.explicitWait();
		
		by = By.partialLinkText("APPLY");
		final List<WebElement> applyBtns = driver.findElements(by);
		
		for(int i = 0; i < applyBtns.size() && i < 3; i ++){
			applyBtns.get(i).click();
			Navigator.explicitWait();
			WebDriverUtils.switchToPopUpWin(driver);
			Navigator.explicitWait();
			WebDriverUtils.switchToBaseWin(driver);
			Navigator.explicitWait();
		}
	}

}