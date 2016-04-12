package com.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.config.Config;
import com.pageObjects.PersonalLoan_Page;
import com.utils.WebDriverUtils;


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
		
		final String moneyHeroPage = Config.getInstance().getProperty("moneyhero.homepage");
		WebDriverUtils.gotoPage(driver, moneyHeroPage);
		
		PersonalLoan_Page page = PageFactory.initElements(driver, PersonalLoan_Page.class);
		page.enterPersonalLoanPage();
		page.clickFindLoanBtn();
		page.selectCategory(this.getCategory());

		final int numOfApplyBtnsToCheck = 3;
		page.checkProductProviderBasedOnCompanyAndProductName(numOfApplyBtnsToCheck);
	}
}