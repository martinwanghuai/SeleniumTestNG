package com.netdimen.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.netdimen.utils.WebDriverUtils;

/**
 * 
 * @author martin.wang
 *
 */
public class QuestionFIB extends com.netdimen.model.Question{
	private String REFINFO="",QTEXT1="",AN="",HINT="",PRE="",PST="";

	public QuestionFIB(){
		super();		
	}

	public String getREFINFO(){
		return REFINFO;
	}

	public String getQTEXT1(){
		return QTEXT1;
	}

	public String getAN(){
		return AN;
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

	public void setQTEXT1(String qtext1){
		QTEXT1=qtext1;
	}

	public void setAN(String an){
		AN=an;
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

	public void setQTEXT1_UI(WebDriver driver, String str){
		if(!str.equalsIgnoreCase("")){
			By by = By.id("QTEXT1");
			WebDriverUtils.fillin_textbox(driver, by, str);
		}
	}

	public void setAN_UI(WebDriver driver, String str){
		if(!str.equalsIgnoreCase("")){
			By by = By.id("AN");
			WebDriverUtils.fillin_textbox(driver, by, str);
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

		//setup Fill-in-the-Blank question

		this.setREFINFO_UI(driver, this.getREFINFO());
		this.setQTEXT1_UI(driver, this.getQTEXT1());
		this.setAN_UI(driver, this.getAN());
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