package com.netdimen.model;

import java.util.Calendar;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.netdimen.abstractclasses.TestObject;
import com.netdimen.config.Config;
import com.netdimen.utils.DataUtils;
import com.netdimen.utils.WebDriverUtils;
import com.netdimen.view.Navigator;

/**
 * 
 * @author martin.wang
 *
 */
public class Token extends com.netdimen.abstractclasses.TestObject {
	private String PackageName = "", TokenValue = "", Cost = "", TaxRate = "",
			ExpiryDate = "", Quantity = "";

	public boolean equals(com.netdimen.abstractclasses.TestObject para0) {
		boolean result = false;
		return result;
	}

	public Token() {
		super();
	}

	public String getPackageName() {
		return PackageName;
	}

	public String getTokenValue() {
		return TokenValue;
	}

	public String getCost() {
		return Cost;
	}

	public String getTaxRate() {
		return TaxRate;
	}

	public String getExpiryDate() {
		return ExpiryDate;
	}

	public String getQuantity() {
		return Quantity;
	}

	public void setPackageName(String packagename) {
		PackageName = packagename;
	}

	public void setTokenValue(String tokenvalue) {
		TokenValue = tokenvalue;
	}

	public void setCost(String cost) {
		Cost = cost;
	}

	public void setTaxRate(String taxrate) {
		TaxRate = taxrate;
	}

	public void setExpiryDate(String expirydate) {
		ExpiryDate = expirydate;
	}

	public void setQuantity(String quantity) {
		Quantity = quantity;
	}

	public void setPackageName_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
		}
	}

	public void setTokenValue_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
		}
	}

	public void setCost_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
		}
	}

	public void setTaxRate_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
		}
	}

	public void setExpiryDate_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
		}
	}

	public void setQuantity_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
		}
	}

	public void runBuyToken(WebDriver driver, TestObject payment) {
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
				"LearningCenter", "MyTokens"));
		
		By by = By.xpath("//tr[descendant::td[text()='"+this.getPackageName()+"']]/td/input[starts-with(@name,'amount_')]");
		WebDriverUtils.fillin_textbox(driver, by, this.getQuantity());
	
		by = By.xpath("//div[@id='submitButtonDiv']/input");
		WebDriverUtils.clickButton(driver, by);
		
		if(payment instanceof Payment){
			Payment pay = (Payment)payment;
			
			by = By.id("CARDNO");
			WebDriverUtils.fillin_textbox(driver, by, pay.getCardNum());
			
			Calendar cal = DataUtils.strToCalendarDate(pay.getExpirationDate());
			int year = cal.get(Calendar.YEAR);
			int month = cal.get(Calendar.MONTH) + 1; 
			
			by = By.name("EXPIRESMM");
			WebDriverUtils.select_selector(driver, by, month);
			
			by = By.name("EXPIRESYYYY");
			WebDriverUtils.select_selector(driver, by, year);
			
			by = By.id("SECCODE");
			WebDriverUtils.fillin_textbox(driver, by, pay.getSecureCode());
			
			by = By.id("CARDHOLDER");
			WebDriverUtils.fillin_textbox(driver, by, pay.getHolderName());
			
			by = By.id("ADDRESS1");
			WebDriverUtils.fillin_textbox(driver, by, pay.getAddress());
			
			by = By.id("CITY");
			WebDriverUtils.fillin_textbox(driver, by, pay.getCity());
			
			by = By.name("STATE");
			WebDriverUtils.select_selector(driver, by, pay.getState());;
			
			by = By.id("ZIP");
			WebDriverUtils.fillin_textbox(driver, by, pay.getZIP());
			
		}else{
			if(Config.DEBUG_MODE){
				System.out.println("Need an instance of Payment");
			}
		}
		
		
	}
	
	public void runCheckMail(WebDriver driver){
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
				"LearningCenter", "MyEmails"));
		
		By by = By.xpath("");
		
		
	}
	

}