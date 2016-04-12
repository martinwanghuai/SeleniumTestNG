package com.pageObjects;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.utils.WebDriverUtils;
import com.view.Navigator;


public class PersonalLoan_Page {
	
	final WebDriver driver;
	
	@FindBy(how = How.PARTIAL_LINK_TEXT, using = "Personal Loans")
	private WebElement personalLoanPage;
	
	@FindBy(how = How.PARTIAL_LINK_TEXT, using = "Find a loan")
	private WebElement findLoanBtn;
	
	@FindBy(how = How.PARTIAL_LINK_TEXT, using = "APPLY")
	private List<WebElement> applyBtns;

	public PersonalLoan_Page(final WebDriver driver){
		
		this.driver = driver;
	}
	
	public void enterPersonalLoanPage(){
		
		WebDriverUtils.clickLink(driver, personalLoanPage);
		//Navigator.explicitWait();
	}
	
	public void clickFindLoanBtn(){
		
		WebDriverUtils.clickLink(driver, findLoanBtn);
		//Navigator.explicitWait();
	}
	
	public void selectCategory(final String category){
		
		By by = By.xpath("//a[span[contains(text(),'" + category + "')]]");
		WebDriverUtils.clickLink(driver, by);  
		Navigator.explicitWait();
	}
	
	public void clickApplyBtn(final WebElement applyBtn){
		
		WebDriverUtils.clickButton(driver, applyBtn);
//		Navigator.explicitWait();
	}
	
	private String getCompanyName(final WebElement applyBtn){
		
		return WebDriverUtils.getAttributeValue(driver, applyBtn, "data-companyname");
	}
	
	private String getProductName(final WebElement applyBtn){
		
		return WebDriverUtils.getAttributeValue(driver, applyBtn, "data-productname");
	} 
	
	public void checkProductProviderBasedOnCompanyAndProductName(final int numOfApplyBtnsToCheck){
		
		if(applyBtns == null){
			return;
		}
		
		for(int i = 0; i < applyBtns.size() && i < numOfApplyBtnsToCheck; i ++){
			WebElement applyBtn = applyBtns.get(i);
			checkProductProviderBasedOnCompanyAndProductName(applyBtn);
		}
	}

	private void checkProductProviderBasedOnCompanyAndProductName(final WebElement applyBtn) {

		final String companyName = this.getCompanyName(applyBtn);
		final String productName = this.getProductName(applyBtn);
		
		this.clickApplyBtn(applyBtn);
		WebDriverUtils.switchToPopUpWin(driver);
		
		final String winTitle = WebDriverUtils.getWindowTitle(driver);
		assertTrue(winTitle.contains(companyName) || winTitle.contains(productName));
		WebDriverUtils.closeAllPopUpWins(driver);
		
		WebDriverUtils.switchToBaseWin(driver);
//			Navigator.explicitWait();
	}
	
}
