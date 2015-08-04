package com.netdimen.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.netdimen.utils.WebDriverUtils;

/**
 * 
 * @author martin.wang
 *
 */
public class QuestionMatching extends com.netdimen.model.Question{
	private String REFINFO="",QTEXT1="",TextBox0="",TextBox1="",TextBox2="",TextBox3="",MatchBox0="",MatchBox1="",MatchBox2="",MatchBox3="",HINT="",PRE="",PST="";

	public QuestionMatching(){
		super();		
	}

	public String getREFINFO(){
		return REFINFO;
	}

	public String getQTEXT1(){
		return QTEXT1;
	}

	public String getTextBox0(){
		return TextBox0;
	}

	public String getTextBox1(){
		return TextBox1;
	}

	public String getTextBox2(){
		return TextBox2;
	}

	public String getTextBox3(){
		return TextBox3;
	}

	public String getMatchBox0(){
		return MatchBox0;
	}

	public String getMatchBox1(){
		return MatchBox1;
	}

	public String getMatchBox2(){
		return MatchBox2;
	}

	public String getMatchBox3(){
		return MatchBox3;
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

	public void setTextBox0(String textbox0){
		TextBox0=textbox0;
	}

	public void setTextBox1(String textbox1){
		TextBox1=textbox1;
	}

	public void setTextBox2(String textbox2){
		TextBox2=textbox2;
	}

	public void setTextBox3(String textbox3){
		TextBox3=textbox3;
	}

	public void setMatchBox0(String matchbox0){
		MatchBox0=matchbox0;
	}

	public void setMatchBox1(String matchbox1){
		MatchBox1=matchbox1;
	}

	public void setMatchBox2(String matchbox2){
		MatchBox2=matchbox2;
	}

	public void setMatchBox3(String matchbox3){
		MatchBox3=matchbox3;
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

	public void setTextBox0_UI(WebDriver driver, String str){
		if(!str.equalsIgnoreCase("")){
			By by = By.id("TextBox0");
			WebDriverUtils.fillin_textbox(driver, by, str);
		}
	}

	public void setTextBox1_UI(WebDriver driver, String str){
		if(!str.equalsIgnoreCase("")){
			By by = By.id("TextBox1");
			WebDriverUtils.fillin_textbox(driver, by, str);
		}
	}

	public void setTextBox2_UI(WebDriver driver, String str){
		if(!str.equalsIgnoreCase("")){
			By by = By.id("TextBox2");
			WebDriverUtils.fillin_textbox(driver, by, str);
		}
	}

	public void setTextBox3_UI(WebDriver driver, String str){
		if(!str.equalsIgnoreCase("")){
			By by = By.id("TextBox3");
			WebDriverUtils.fillin_textbox(driver, by, str);
		}
	}

	public void setMatchBox0_UI(WebDriver driver, String str){
		if(!str.equalsIgnoreCase("")){
			By by = By.id("MatchBox0");
			WebDriverUtils.fillin_textbox(driver, by, str);
		}
	}

	public void setMatchBox1_UI(WebDriver driver, String str){
		if(!str.equalsIgnoreCase("")){
			By by = By.id("MatchBox1");
			WebDriverUtils.fillin_textbox(driver, by, str);
		}
	}

	public void setMatchBox2_UI(WebDriver driver, String str){
		if(!str.equalsIgnoreCase("")){
			By by = By.id("MatchBox2");
			WebDriverUtils.fillin_textbox(driver, by, str);
		}
	}

	public void setMatchBox3_UI(WebDriver driver, String str){
		if(!str.equalsIgnoreCase("")){
			By by = By.id("MatchBox3");
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

		//setup Matching question

		this.setREFINFO_UI(driver, this.getREFINFO());
		this.setQTEXT1_UI(driver, this.getQTEXT1());
		this.setTextBox0_UI(driver, this.getTextBox0());
		this.setTextBox1_UI(driver, this.getTextBox1());
		this.setTextBox2_UI(driver, this.getTextBox2());
		this.setTextBox3_UI(driver, this.getTextBox3());
		this.setMatchBox0_UI(driver, this.getMatchBox0());
		this.setMatchBox1_UI(driver, this.getMatchBox1());
		this.setMatchBox2_UI(driver, this.getMatchBox2());
		this.setMatchBox3_UI(driver, this.getMatchBox3());    

		By by = By.name("displayCmtButt");
		WebDriverUtils.clickButton(driver, by);
		this.setHINT_UI(driver, this.getHINT());
		this.setPRE_UI(driver, this.getPRE());
		this.setPST_UI(driver, this.getPST());

		WebDriverUtils.switchToFrame(driver, "QFR_TOP");
		by = By.cssSelector("img[alt=\"Save question\"]");
		WebDriverUtils.clickLink(driver, by);
	}

}