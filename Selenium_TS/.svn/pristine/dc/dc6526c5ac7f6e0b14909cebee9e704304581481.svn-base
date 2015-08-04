package com.netdimen.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.netdimen.utils.WebDriverUtils;

/**
 * 
 * @author martin.wang
 *
 */
public class QuestionRating extends com.netdimen.model.Question{
	private String REFINFO="",lowValueLabel="",highValueLabel="",spread="",HINT="",PRE="",PST="";

	public QuestionRating(){
		super();		
	}

	public String getREFINFO(){
		return REFINFO;
	}

	public String getlowValueLabel(){
		return lowValueLabel;
	}

	public String gethighValueLabel(){
		return highValueLabel;
	}

	public String getspread(){
		return spread;
	}

	public String getHINT(){
		return HINT;
	}

	public String getPRE(){
		return PRE;
	}

	public String getPST(){
		return PST;
	}

	public void setREFINFO(String refinfo){
		REFINFO=refinfo;
	}

	public void setlowValueLabel(String lowvaluelabel){
		lowValueLabel=lowvaluelabel;
	}

	public void sethighValueLabel(String highvaluelabel){
		highValueLabel=highvaluelabel;
	}

	public void setspread(String spread){
		spread=spread;
	}

	public void setHINT(String hint){
		HINT=hint;
	}

	public void setPRE(String pre){
		PRE=pre;
	}

	public void setPST(String pst){
		PST=pst;
	}

	public void setREFINFO_UI(WebDriver driver, String str){
		if(!str.equalsIgnoreCase("")){
			By by = By.id("REFINFO");
			WebDriverUtils.fillin_textbox(driver, by, str);
		}
	}

	public void setlowValueLabel_UI(WebDriver driver, String str){
		if(!str.equalsIgnoreCase("")){
			By by = By.name("lowValueLabel");
			WebDriverUtils.fillin_textbox(driver, by, str);
		}
	}

	public void sethighValueLabel_UI(WebDriver driver, String str){
		if(!str.equalsIgnoreCase("")){
			By by = By.name("highValueLabel");
			WebDriverUtils.fillin_textbox(driver, by, str);
		}
	}

	public void setspread_UI(WebDriver driver, String str){
		if(!str.equalsIgnoreCase("")){
			By by = By.name("spread");
			WebDriverUtils.select_selector(driver, by, str);
		}
	}

	public void setHINT_UI(WebDriver driver, String str){
		if(!str.equalsIgnoreCase("")){
			By by = By.id("HINT");
			WebDriverUtils.fillin_textbox(driver, by, str);
		}
	}

	public void setPRE_UI(WebDriver driver, String str){
		if(!str.equalsIgnoreCase("")){
			By by = By.id("PRE");
			WebDriverUtils.fillin_textbox(driver, by, str);
		}
	}

	public void setPST_UI(WebDriver driver, String str){
		if(!str.equalsIgnoreCase("")){
			By by = By.id("PST");
			WebDriverUtils.fillin_textbox(driver, by, str);
		}
	}

	public void runCreateQ(WebDriver driver){
		super.runCreateQ(driver);

		//setup Rating question

		this.setREFINFO_UI(driver, this.getREFINFO());
		this.setlowValueLabel_UI(driver, this.getlowValueLabel());
		this.sethighValueLabel_UI(driver, this.gethighValueLabel());
		this.setspread_UI(driver, this.getspread());

		By by = By.name("displayCmtButt");
		WebDriverUtils.clickButton(driver, by);
		this.setHINT_UI(driver, this.getHINT());
		this.setPRE_UI(driver, this.getPRE());
		this.setPST_UI(driver, this.getPST());

		WebDriverUtils.switchToFrame(driver, "QFR_TOP");
		by = By.cssSelector("img[alt=\"Save question\"]");
		WebDriverUtils.clickButton(driver, by);
	}

}