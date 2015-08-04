package com.netdimen.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.netdimen.utils.WebDriverUtils;

/**
 * 
 * @author martin.wang
 *
 */
public class QuestionMC extends com.netdimen.model.Question{
	private String REFINFO="",QTEXT1="",FO="",SC="",TextBox0="",TextBox1="",TextBox2="",TextBox3="",HINT="",PRE="",PST="";

	public QuestionMC(){
		super();		
	}

	public String getREFINFO(){
		return REFINFO;
	}

	public String getQTEXT1(){
		return QTEXT1;
	}

	public String getFO(){
		return FO;
	}

	public String getSC(){
		return SC;
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

	public void setFO(String fo){
		FO=fo;
	}

	public void setSC(String sc){
		SC=sc;
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

	public void setFO_UI(WebDriver driver, String str){
		if(!str.equalsIgnoreCase("")){
			By by = By.name("FO");
			WebDriverUtils.select_selector(driver, by, str);
		}
	}

	public void setSC_UI(WebDriver driver, String str){
		if(!str.equalsIgnoreCase("")){
			By by = By.name("SC");
			WebDriverUtils.select_selector(driver, by, str);
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

		//setup MC question

		this.setREFINFO_UI(driver, this.getREFINFO());
		this.setQTEXT1_UI(driver, this.getQTEXT1());
		this.setFO_UI(driver, this.getFO());
		this.setSC_UI(driver, this.getSC());
		this.setTextBox0_UI(driver, this.getTextBox0());

		By by = By.id("choiceButton0");
		WebDriverUtils.clickButton(driver, by);
		this.setTextBox1_UI(driver, this.getTextBox1());

		by = By.id("choiceButton1");
		WebDriverUtils.clickButton(driver, by);
		this.setTextBox2_UI(driver, this.getTextBox2());
		this.setTextBox3_UI(driver, this.getTextBox3());

		by = By.name("displayCmtButt");
		WebDriverUtils.clickButton(driver, by);
		this.setHINT_UI(driver, this.getHINT());
		this.setPRE_UI(driver, this.getPRE());
		this.setPST_UI(driver, this.getPST());

		WebDriverUtils.switchToFrame(driver, "QFR_TOP");
		by = By.cssSelector("img[alt=\"Save question\"]");
		WebDriverUtils.clickButton(driver, by);
	}

}