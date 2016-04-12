package com.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.config.Config;
import com.pageObjects.MoneyHero_Page;
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
		
		MoneyHero_Page moneyHeroPage = PageFactory.initElements(driver, MoneyHero_Page.class);
		moneyHeroPage.enterMoneyHeroPage();
		
		PersonalLoan_Page personalLoanPage = PageFactory.initElements(driver, PersonalLoan_Page.class);
		personalLoanPage.enterPersonalLoanPage();
		personalLoanPage.clickFindLoanBtn();
		personalLoanPage.selectCategory(this.getCategory());

		final int numOfApplyBtnsToCheck = 3;
		personalLoanPage.checkProductProviderBasedOnCompanyAndProductName(numOfApplyBtnsToCheck);
	}
}