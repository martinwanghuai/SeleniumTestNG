package com.netdimen.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.netdimen.utils.WebDriverUtils;

/**
 * 
 * @author martin.wang
 *
 */
public class QuestionEssay extends com.netdimen.model.Question{
	private String REFINFO="",QTEXT1="",HINT="",PRE="",PST="";
	
	public QuestionEssay(){
		super();		
	}

	public String getREFINFO(){
			return REFINFO;
	}

	public String getQTEXT1(){
			return QTEXT1;
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
		
		//setup Essay question
	    	    
	    this.setREFINFO_UI(driver, this.getREFINFO());
	    this.setQTEXT1_UI(driver, this.getQTEXT1());
	    
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