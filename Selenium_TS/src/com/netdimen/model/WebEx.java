package com.netdimen.model;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.netdimen.config.Config;
import com.netdimen.utils.WebDriverUtils;
import com.netdimen.view.Navigator;

/**
 * 
 * @author martin.wang
 *
 */
public class WebEx extends com.netdimen.model.Classroom{
	private String Type="", DisplayName="",
			UserName="",UserPWD="",SiteName="",SiteID="",
			PartnerID="";

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	public WebEx(){
		super();
	}

	public String getDisplayName(){
		return DisplayName;
	}

	public String getUserName(){
		return UserName;
	}

	public String getUserPWD(){
		return UserPWD;
	}

	public String getSiteName(){
		return SiteName;
	}

	public String getSiteID(){
		return SiteID;
	}

	public String getPartnerID(){
		return PartnerID;
	}

	public void setDisplayName(String displayname){
		DisplayName=displayname;
	}

	public void setUserName(String username){
		UserName=username;
	}

	public void setUserPWD(String userpwd){
		UserPWD=userpwd;
	}

	public void setSiteName(String sitename){
		SiteName=sitename;
	}

	public void setSiteID(String siteid){
		SiteID=siteid;
	}

	public void setPartnerID(String partnerid){
		PartnerID=partnerid;
	}

	public void setDisplayName_UI(WebDriver driver, String str){
		if(!str.equals("")){
		}
	}

	public void setUserName_UI(WebDriver driver, String str){
		if(!str.equals("")){
		}
	}

	public void setUserPWD_UI(WebDriver driver, String str){
		if(!str.equals("")){
		}
	}

	public void setSiteName_UI(WebDriver driver, String str){
		if(!str.equals("")){
		}
	}

	public void setSiteID_UI(WebDriver driver, String str){
		if(!str.equals("")){
		}
	}

	public void setPartnerID_UI(WebDriver driver, String str){
		if(!str.equals("")){
		}
	}

	private String getSessionID(WebDriver driver){
		
		String URL ="https://apidemoeu.webex.com/" +
				"cmp0307l/webcomponents/widget/detect.do?siteurl=apidemoeu&LID=1&RID=2&TID=4&rnd=1296773315&DT=480&DL=en-US&isDetected=true&backUrl=%2Fmw0307l%2Fmywebex%2Fdefault.do%3Fsiteurl%3Dapidemoeu"; 
		WebDriverUtils.openURL(driver, URL);
		Navigator.explicitWait(3000);
		
		By by = By.xpath("//frame[@name='header']");
		WebDriverUtils.switchToFrame(driver, by);
		
		by = By.id("wcc-lnk-loginLink");
		String str = "";
		boolean Exist = WebDriverUtils.isElementPresent(by);
		if(Exist){
			WebDriverUtils.clickLink(driver, by);
			
			Navigator.explicitWait(1000);
			
			by = By.xpath("//frame[@name='mainFrame']");
			WebDriverUtils.switchToFrame(driver, by);
			by = By.xpath("//input[@name='userName']");
			str = this.getUserName();
			WebDriverUtils.fillin_textbox(driver, by, str);

			by = By.xpath("//input[@name='password']");
			str = this.getUserPWD();
			WebDriverUtils.fillin_textbox(driver, by, str);
			Navigator.explicitWait(3000);
			by = By.xpath("//input[@value='Log In']");
			WebDriverUtils.clickButton(driver, by);
			Navigator.explicitWait(3000);
		}
		
		
		by = By.xpath("//a[contains(text(),'Remind me later')]");
		Exist = WebDriverUtils.isElementPresent(by);
		if(Exist){
			WebDriverUtils.clickButton(driver, by);
			Navigator.explicitWait(3000);
		}
		
		by = By.xpath("//frame[@name='header']");
		WebDriverUtils.switchToFrame(driver, by);
		by = By.id("wcc-lnk-TC");
		WebDriverUtils.clickLink(driver, by);
		Navigator.explicitWait(3000);
		
		ArrayList<By> bys = new ArrayList<By>();
		bys.add(By.xpath("//frame[@name='mainFrame']")); 
		bys.add(By.xpath("//frame[@name='menu']"));
		bys.add(by = By.xpath("//frame[@name='treemenu']"));
		WebDriverUtils.switchToNestedFrame(driver, bys);
		
		Navigator.explicitWait(3000);
		by = By.xpath("//a/span[contains(text(),'Schedule Training')]");
		WebDriverUtils.clickLink(driver, by);

		Navigator.explicitWait(5000);
		ArrayList<By> bys_temp = new ArrayList<By>();
		bys_temp.add(By.xpath("//frame[@name='mainFrame']")); 
		bys_temp.add(By.xpath("//frame[@name='main']"));
		WebDriverUtils.switchToNestedFrame(driver, bys_temp);
		Navigator.explicitWait(3000);
		by = By.linkText("Copy from...");
		WebDriverUtils.clickLink(driver, by);

		WebDriverUtils.switchToPopUpWin(driver);
		Navigator.explicitWait(5000);
		by = By.xpath("//input[@name='Submit']");
		WebDriverUtils.clickButton(driver, by);
		
		Navigator.explicitWait(7000);
		WebDriverUtils.switchToParentWin(driver);
		Navigator.explicitWait(2000);
		WebDriverUtils.switchToNestedFrame(driver, bys_temp);
		Navigator.explicitWait(3000);
	
		
		by = By.name("startTimeOfHour");
		str = "11";
		WebDriverUtils.select_selector(driver, by, str);
		
		by = By.xpath("//input[@type='radio' and @value='1' and @name='startTimeOfAmPm']");
		WebDriverUtils.checkRadio(driver, by);
		
		String xpath_calendar="//a[@alt='Calendar' and @title='Calendar']";
		String dateString = this.getSessionBeginDates();
		WebDriverUtils.dateSelect_Calandar(driver, dateString, xpath_calendar);
		
		
		WebDriverUtils.switchToNestedFrame(driver, bys_temp);
		by = By.name("timeZoneId");
		str = "Beijing (China Time, GMT+08:00)";
		WebDriverUtils.select_selector(driver, by, str);
		
		by = By.xpath("//input[@value='Schedule']");
		WebDriverUtils.clickButton(driver, by);

		Navigator.explicitWait(5000);
		WebDriverUtils.switchToNestedFrame(driver, bys_temp);
		by = By.linkText("Session Information page");
		WebDriverUtils.clickLink(driver, by);

		Navigator.explicitWait(5000);		
		by = By.xpath("//tr[descendant::td/b[contains(text(),'Session number')]]/td[3]");		
		String Temp_SessionNum = WebDriverUtils.getText(driver, by).replaceAll("\\s+", "");//replace whitespace char
//		System.out.println("Session ID for WebEx module:" + Temp_SessionNum);
		return Temp_SessionNum;
	}

	public void runCreate(WebDriver driver){
		//get session ID
		String SID = this.getSessionID(driver);
//		String SID="621687232";
		if(Config.DEBUG_MODE){
			System.out.println("Session ID:"+SID);
		}
		
		//set up account
		if(!this.getUserName().equals("") 
				&& !this.getUserPWD().equals("") 
				&& !this.getSiteName().equals("") 
				&& !this.getSiteID().equals("") 
				&& !this.getPartnerID().equals("")){
			Account account = new Account();
			account.setType(this.getType());
			account.setDisplayName(this.getDisplayName());			
			account.setSiteName(this.getSiteName());
			account.setSiteID(this.getSiteID());
			account.setPartnerID(this.getPartnerID());
			account.runCreate(driver);
		}
		
		
	}

}