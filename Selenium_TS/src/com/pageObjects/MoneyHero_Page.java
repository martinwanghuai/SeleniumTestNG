package com.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.config.Config;
import com.utils.WebDriverUtils;

public class MoneyHero_Page {

	final WebDriver driver;
	
	@FindBy(how = How.PARTIAL_LINK_TEXT, using = "Personal Loans")
	private WebElement personalLoanPage;
	
	public MoneyHero_Page(final WebDriver driver){
		
		this.driver = driver;
	}
	
	public void enterMoneyHeroPage(){
		
		final String moneyHeroPage = Config.getInstance().getProperty("moneyhero.homepage");
		
		WebDriverUtils.gotoPage(driver, moneyHeroPage);
	}
}
