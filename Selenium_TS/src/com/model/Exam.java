package com.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.abstractclasses.TestObject;
import com.config.Config;
import com.config.Labels;
import com.utils.WebDriverUtils;
import com.view.Navigator;

/**
 * 
 * @author martin.wang
 *
 */
public class Exam extends com.abstractclasses.TestObject {

	private String ExamID = "", ExamTitle = "", ExamType = "", Desc = "",
			isTemplate = "", Navigation = "", IDDisplay = "", TimeAllow = "",
			TimeQues = "", AutoGrade = "", NumTake = "", MarkType = "",
			ReqPoint = "", AllowHint = "", ShowNum = "", AllowRev = "",
			RevType = "", SCDisplay = "", HowRev = "", CanResume = "",
			CanPrint = "", CanAbandon = "", STitleF = "", SDescF = "",
			SQTypeF = "", SQPageF = "", SMarkTypeF = "", SReqPointF = "",
			STimeLimitF = "", SFQ1 = "", SFQ2 = "", STitleR = "", SDescR = "",
			SQTypeR = "", SQPageR = "", SMarkTypeR = "", SReqPointR = "",
			STimeLimitR = "", SRQNum = "", QPool = "", TPool = "",
			BeginDate = "", EndDate = "", ExamPool = "", accessCode = "";

	public String getExamPool() {
		return ExamPool;
	}

	public void setExamPool(String examPool) {
		ExamPool = examPool;
	}

	public String getIsTemplate() {
		return isTemplate;
	}

	public void setIsTemplate(String isTemplate) {
		this.isTemplate = isTemplate;
	}

	public String getBeginDate() {
		return BeginDate;
	}

	public void setBeginDate(String beginDate) {
		BeginDate = beginDate;
	}

	public String getEndDate() {
		return EndDate;
	}

	public void setEndDate(String endDate) {
		EndDate = endDate;
	}

	public boolean equals(TestObject obj) {
		return true;
	}

	public void checkExpectedResult_UI(WebDriver driver, String str) {

	}

	public String getExamID() {
		return ExamID;
	}

	public String getExamTitle() {
		return ExamTitle;
	}

	public String getExamType() {
		return ExamType;
	}

	public String getDesc() {
		return Desc;
	}

	public String getisTemplate() {
		return isTemplate;
	}

	public String getNavigation() {
		return Navigation;
	}

	public String getIDDisplay() {
		return IDDisplay;
	}

	public String getTimeAllow() {
		return TimeAllow;
	}

	public String getTimeQues() {
		return TimeQues;
	}

	public String getAutoGrade() {
		return AutoGrade;
	}

	public String getNumTake() {
		return NumTake;
	}

	public String getMarkType() {
		return MarkType;
	}

	public String getReqPoint() {
		return ReqPoint;
	}

	public String getAllowHint() {
		return AllowHint;
	}

	public String getShowNum() {
		return ShowNum;
	}

	public String getAllowRev() {
		return AllowRev;
	}

	public String getRevType() {
		return RevType;
	}

	public String getSCDisplay() {
		return SCDisplay;
	}

	public String getHowRev() {
		return HowRev;
	}

	public String getCanResume() {
		return CanResume;
	}

	public String getCanPrint() {
		return CanPrint;
	}

	public String getCanAbandon() {
		return CanAbandon;
	}

	public String getSTitleF() {
		return STitleF;
	}

	public String getSDescF() {
		return SDescF;
	}

	public String getSQTypeF() {
		return SQTypeF;
	}

	public String getSQPageF() {
		return SQPageF;
	}

	public String getSMarkTypeF() {
		return SMarkTypeF;
	}

	public String getSReqPointF() {
		return SReqPointF;
	}

	public String getSTimeLimitF() {
		return STimeLimitF;
	}

	public String getSFQ1() {
		return SFQ1;
	}

	public String getSFQ2() {
		return SFQ2;
	}

	public String getSTitleR() {
		return STitleR;
	}

	public String getSDescR() {
		return SDescR;
	}

	public String getSQTypeR() {
		return SQTypeR;
	}

	public String getSQPageR() {
		return SQPageR;
	}

	public String getSMarkTypeR() {
		return SMarkTypeR;
	}

	public String getSReqPointR() {
		return SReqPointR;
	}

	public String getSTimeLimitR() {
		return STimeLimitR;
	}

	public String getSRQNum() {
		return SRQNum;
	}

	public String getQPool() {
		return QPool;
	}

	public String getTPool() {
		return TPool;
	}

	public void setExamID(String examid) {
		ExamID = examid;
	}

	public void setExamTitle(String examtitle) {
		ExamTitle = examtitle;
	}

	public void setExamType(String examtype) {
		ExamType = examtype;
	}

	public void setDesc(String desc) {
		Desc = desc;
	}

	public void setisTemplate(String istemplate) {
		isTemplate = istemplate;
	}

	public void setNavigation(String navigation) {
		Navigation = navigation;
	}

	public void setIDDisplay(String iddisplay) {
		IDDisplay = iddisplay;
	}

	public void setTimeAllow(String timeallow) {
		TimeAllow = timeallow;
	}

	public void setTimeQues(String timeques) {
		TimeQues = timeques;
	}

	public void setAutoGrade(String autograde) {
		AutoGrade = autograde;
	}

	public void setNumTake(String numtake) {
		NumTake = numtake;
	}

	public void setMarkType(String marktype) {
		MarkType = marktype;
	}

	public void setReqPoint(String reqpoint) {
		ReqPoint = reqpoint;
	}

	public void setAllowHint(String allowhint) {
		AllowHint = allowhint;
	}

	public void setShowNum(String shownum) {
		ShowNum = shownum;
	}

	public void setAllowRev(String allowrev) {
		AllowRev = allowrev;
	}

	public void setRevType(String revtype) {
		RevType = revtype;
	}

	public void setSCDisplay(String scdisplay) {
		SCDisplay = scdisplay;
	}

	public void setHowRev(String howrev) {
		HowRev = howrev;
	}

	public void setCanResume(String canresume) {
		CanResume = canresume;
	}

	public void setCanPrint(String canprint) {
		CanPrint = canprint;
	}

	public void setCanAbandon(String canabandon) {
		CanAbandon = canabandon;
	}

	public void setSTitleF(String stitlef) {
		STitleF = stitlef;
	}

	public void setSDescF(String sdescf) {
		SDescF = sdescf;
	}

	public void setSQTypeF(String sqtypef) {
		SQTypeF = sqtypef;
	}

	public void setSQPageF(String sqpagef) {
		SQPageF = sqpagef;
	}

	public void setSMarkTypeF(String smarktypef) {
		SMarkTypeF = smarktypef;
	}

	public void setSReqPointF(String sreqpointf) {
		SReqPointF = sreqpointf;
	}

	public void setSTimeLimitF(String stimelimitf) {
		STimeLimitF = stimelimitf;
	}

	public void setSFQ1(String sfq1) {
		SFQ1 = sfq1;
	}

	public void setSFQ2(String sfq2) {
		SFQ2 = sfq2;
	}

	public void setSTitleR(String stitler) {
		STitleR = stitler;
	}

	public void setSDescR(String sdescr) {
		SDescR = sdescr;
	}

	public void setSQTypeR(String sqtyper) {
		SQTypeR = sqtyper;
	}

	public void setSQPageR(String sqpager) {
		SQPageR = sqpager;
	}

	public void setSMarkTypeR(String smarktyper) {
		SMarkTypeR = smarktyper;
	}

	public void setSReqPointR(String sreqpointr) {
		SReqPointR = sreqpointr;
	}

	public void setSTimeLimitR(String stimelimitr) {
		STimeLimitR = stimelimitr;
	}

	public void setSRQNum(String srqnum) {
		SRQNum = srqnum;
	}

	public void setQPool(String qpool) {
		QPool = qpool;
	}

	public void setTPool(String tpool) {
		TPool = tpool;
	}

	public String getAccessCode() {
		return accessCode;
	}

	public void setAccessCode(String accessCode) {
		this.accessCode = accessCode;
	}

	public void setExamID_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
			By by = By.id("TID");
			WebDriverUtils.fillin_textbox(driver, by, str);
		}

	}

	public void setExamTitle_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
			By by = By.id("TITLE");
			WebDriverUtils.fillin_textbox(driver, by, str);
		}
	}

	public void setExamType_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
			By by = By.id("TTYPE");
			int size = driver.findElements(by).size();
			if (size == 1) {
				WebDriverUtils.select_selector(driver, by, str);
			}
		}
	}

	public void setDesc_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
			By by = By.id("DESC");
			WebDriverUtils.fillin_textbox(driver, by, str);
		}
	}

	public void setisTemplate_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
			By by = By.id("TEMPLATE");
			if (str.equalsIgnoreCase("yes")) {
				WebDriverUtils.check_checkbox(driver, by);
			} else {
				WebDriverUtils.uncheck_checkbox(driver, by);

			}
		}
	}

	public void setNavigation_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
			By by = By.id("Navigation");
			if (str.equalsIgnoreCase("yes")) {
				WebDriverUtils.check_checkbox(driver, by);
			} else {
				WebDriverUtils.uncheck_checkbox(driver, by);
			}
		}
	}

	public void setIDDisplay_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
			By by = By.id("IdDisplay");
			if (str.equalsIgnoreCase("yes")) {
				WebDriverUtils.check_checkbox(driver, by);
			} else {
				WebDriverUtils.uncheck_checkbox(driver, by);
			}
		}
	}

	public void setTimeAllow_UI(WebDriver driver, String str) {
	}

	public void setTimeQues_UI(WebDriver driver, String str) {
	}

	public void setAutoGrade_UI(WebDriver driver, String str) {
	}

	public void setNumTake_UI(WebDriver driver, String str) {
	}

	public void setMarkType_UI(WebDriver driver, String str) {
	}

	public void setReqPoint_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
			By by = By.id("REQ");
			WebDriverUtils.fillin_textbox(driver, by, str);
		}
	}

	public void setAllowHint_UI(WebDriver driver, String str) {
	}

	public void setShowNum_UI(WebDriver driver, String str) {
	}

	public void setAllowRev_UI(WebDriver driver, String str) {
	}

	public void setRevType_UI(WebDriver driver, String str) {
	}

	public void setSCDisplay_UI(WebDriver driver, String str) {
	}

	public void setHowRev_UI(WebDriver driver, String str) {
	}

	public void setCanResume_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
			By by = By.id("resumeEnabled");
			if (str.equalsIgnoreCase("yes")) {
				WebDriverUtils.check_checkbox(driver, by);
			} else {
				WebDriverUtils.uncheck_checkbox(driver, by);
			}
		}
	}

	public void setCanPrint_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
			By by = By.id("printEnabled");
			if (str.equalsIgnoreCase("yes")) {
				WebDriverUtils.check_checkbox(driver, by);
			} else {
				WebDriverUtils.uncheck_checkbox(driver, by);
			}
		}
	}

	public void setCanAbandon_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
			By by = By.id("disallowAbandon");
			if (str.equalsIgnoreCase("no")) {
				WebDriverUtils.check_checkbox(driver, by);
			} else {
				WebDriverUtils.uncheck_checkbox(driver, by);
			}
		}
	}

	public void setSTitleF_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
			By by = By.id("TITLE");
			WebDriverUtils.fillin_textbox(driver, by, str);
		}
	}

	public void setSDescF_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
			By by = By.id("DESC");
			WebDriverUtils.fillin_textbox(driver, by, str);
		}
	}

	public void setSQTypeF_UI(WebDriver driver, String str) {
	}

	public void setSQPageF_UI(WebDriver driver, String str) {
	}

	public void setSMarkTypeF_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
			By by = null;
			if (str.equalsIgnoreCase("point")) {
				by = By.id("MTPOINTS");
			} else if (str.equalsIgnoreCase("%")) {
				by = By.id("MTPERCENT");
			}

			WebDriverUtils.checkRadio(driver, by);
		}
	}

	public void setSReqPointF_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
			By by = By.id("REQ");
			WebDriverUtils.fillin_textbox(driver, by, str);
		}
	}

	public void setSTimeLimitF_UI(WebDriver driver, String str) {
	}

	public void setSFQ1_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
			By by = By.xpath("//tr[td/a[contains(text(),'" + str
					+ "')]]/td/input");
			WebDriverUtils.clickLink(driver, by);
		}
	}

	public void setSFQ2_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
			By by = By.xpath("//tr[td/a[contains(text(),'" + str
					+ "')]]/td/input");
			WebDriverUtils.clickLink(driver, by);
		}
	}

	public void setSTitleR_UI(WebDriver driver, String str) {
		this.setSTitleF_UI(driver, str);
	}

	public void setSDescR_UI(WebDriver driver, String str) {
		this.setSDescF_UI(driver, str);
	}

	public void setSQTypeR_UI(WebDriver driver, String str) {
		this.setSQTypeF_UI(driver, str);
	}

	public void setSQPageR_UI(WebDriver driver, String str) {
		this.setSQPageF_UI(driver, str);
	}

	public void setSMarkTypeR_UI(WebDriver driver, String str) {
		this.setSMarkTypeF_UI(driver, str);
	}

	public void setSReqPointR_UI(WebDriver driver, String str) {
		this.setSReqPointF_UI(driver, str);
	}

	public void setSTimeLimitR_UI(WebDriver driver, String str) {
		this.setSTimeLimitF_UI(driver, str);
	}

	public void setSRQNum_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
			By by = By.xpath("//tr[descendant::td/a[contains(text(),'"
					+ this.getQPool() + "')]]/td/input[@type='TEXT']");
			WebDriverUtils.fillin_textbox(driver, by, str);
		}
	}

	public void setQPool_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
			By by = By.name("PNAME0");
			WebDriverUtils.select_selector(driver, by, str);
		}
	}

	public void setTPool_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
			By by = By.name("PNAME1");
			WebDriverUtils.select_selector(driver, by, str);
		}
	}

	public void setExamPool_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
			By by = By.id("FILTER1");
			WebDriverUtils.select_selector(driver, by, str);
		}
	}

	public void runCreateExam(WebDriver driver) {
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
				"ManageCenter", "ExamTempl"), this);

		this.setExamPool_UI(driver, this.getExamPool());

		By by = By.name("CREATE");
		WebDriverUtils.clickButton(driver, by);

		WebDriverUtils.switchToPopUpWin(driver);
		WebDriverUtils.switchToFrame(driver, "PALMAIN");
		this.setExamType_UI(driver, this.getExamType());

		by = By.name("_SUBMIT");
		WebDriverUtils.clickButton(driver, by);

		this.setExamID_UI(driver, this.getExamID());
		by = By.name("SUB");
		WebDriverUtils.clickButton(driver, by);

		Navigator.explicitWait(3000);
		WebDriverUtils.switchToFrame(driver, "PALMAIN");
		this.setExamTitle_UI(driver, this.getExamTitle());

		this.setDesc_UI(driver, this.getDesc());

		this.setNavigation_UI(driver, this.getNavigation());
		this.setisTemplate_UI(driver, this.getisTemplate());
		this.setIDDisplay_UI(driver, this.getIDDisplay());
		this.setCanResume_UI(driver, this.getCanResume());
		this.setCanPrint_UI(driver, this.getCanPrint());
		this.setCanAbandon_UI(driver, this.getCanAbandon());
		WebDriverUtils.switchToFrame(driver, "PALMENU");

		by = By.cssSelector("img[alt=\"Save\"]");
		WebDriverUtils.clickButton(driver, by);

		Navigator.explicitWait(3000);

		this.addFixSection(driver);
		this.addRandSection(driver);
	}

	public void runAddSection(WebDriver driver) {
		this.findExam(driver);
		this.addFixSection(driver);
		this.addRandSection(driver);
	}

	public void runAddRandSection(WebDriver driver) {
		this.findExam(driver);
		this.addRandSection(driver);
	}

	public void runUpdateExam(WebDriver driver) {
		this.findExam(driver);
		Navigator.explicitWait(3000);
		WebDriverUtils.switchToFrame(driver, "PALMAIN");
		this.setCanAbandon_UI(driver, "no");
		WebDriverUtils.switchToFrame(driver, "PALMENU");

		By by = By.cssSelector("img[alt=\"Save\"]");
		WebDriverUtils.clickButton(driver, by);
		Navigator.explicitWait(1000);
	}

	public void runAddFixSection(WebDriver driver) {
		this.findExam(driver);
		this.addFixSection(driver);
	}

	public void findExam(WebDriver driver) {
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
				"ManageCenter", "ExamTempl"), this);

		this.setExamPool_UI(driver, this.getExamPool());

		By by = By.id("SEARCHFIELD");
		WebDriverUtils.fillin_textbox(driver, by, this.getExamID());

		by = By.name("apply-filters");
		WebDriverUtils.clickButton(driver, by);

		by = By.xpath("//a[contains(text(),'" + this.getExamID() + "')]");
		WebDriverUtils.clickLink(driver, by);

		WebDriverUtils.switchToPopUpWin(driver);
	}

	public void addRandSection(WebDriver driver) {
		if (!this.getSRQNum().equalsIgnoreCase("")
				&& !this.getQPool().equalsIgnoreCase("")) {
			WebDriverUtils.switchToFrame(driver, "PALMENU");

			By by = By.id("editorcreatebutton");
			WebDriverUtils.clickButton(driver, by);
			WebDriverUtils.switchToFrame(driver, "PALMAIN");

			this.setSTitleR_UI(driver, this.getSTitleR());
			this.setSDescR_UI(driver, this.getSDescR());
			this.setSReqPointR_UI(driver, this.getSReqPointR());
			this.setSMarkTypeR_UI(driver, this.getSMarkTypeR());

			by = By.id("TTYPE");
			int size = driver.findElements(by).size();
			String str = "Generate random questions";
			if (size > 0) {
				WebDriverUtils.select_selector(driver, by, str);
			}

			by = By.name("SUB");
			WebDriverUtils.clickButton(driver, by);
			Navigator.explicitWait(3000);

			// Assign Questions
			WebDriverUtils.switchToFrame(driver, "PALMAIN");
			Navigator.explicitWait(3000);
			this.setQPool_UI(driver, this.getQPool());
			this.setTPool_UI(driver, this.getTPool());

			this.setSRQNum_UI(driver, this.getSRQNum());

			by = By.name("SUB");
			WebDriverUtils.clickButton(driver, by);
			Navigator.explicitWait(3000);
			WebDriverUtils.switchToFrame(driver, "PALMENU");
			by = By.cssSelector("img[alt=\"Save\"]");
			WebDriverUtils.clickButton(driver, by);
		}
	}

	public void setEndDate_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
			String xpath_calendar = "(//img[@alt='Calendar'])[2]";
			WebDriverUtils.dateSelect_Calandar(driver, str, xpath_calendar);
			WebDriverUtils.switchToFrame(driver, "REPORT_MAIN_FRAME");
		}
	}

	public void setBeginDate_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
			String xpath_calendar = "(//img[@alt='Calendar'])[1]";
			WebDriverUtils.dateSelect_Calandar(driver, str, xpath_calendar);
			WebDriverUtils.switchToFrame(driver, "REPORT_MAIN_FRAME");
		}
	}

	public void runPublishExam(WebDriver driver) {
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
				"ManageCenter", "ExamGenerator"), this);

		this.setExamPool_UI(driver, this.getExamPool());

		String str = this.getExamTitle() + " ("
				+ this.getExamID().toUpperCase() + ")";
		By by = By.name("TESTID");
		WebDriverUtils.select_selector(driver, by, str);

		this.setBeginDate_UI(driver, this.getBeginDate());
		this.setEndDate_UI(driver, this.getEndDate());

		Navigator.explicitWait(3000);
		by = By.name("PUBLISH");
		WebDriverUtils.clickButton(driver, by);
		Navigator.explicitWait(3000);

		by = By.id("PW");
		this.setAccessCode(WebDriverUtils.getAttribute(driver, by, "value"));

		if (Config.DEBUG_MODE) {
			System.out.println(this.toString() + " access code:"
					+ this.getAccessCode());
		}
	}

	public void addFixSection(WebDriver driver) {
		if (!this.getSFQ1().equalsIgnoreCase("")
				|| !this.getSFQ2().equalsIgnoreCase("")) {
			WebDriverUtils.switchToFrame(driver, "PALMENU");

			By by = By.id("editorcreatebutton");
			WebDriverUtils.clickButton(driver, by);
			WebDriverUtils.switchToFrame(driver, "PALMAIN");

			this.setSTitleF_UI(driver, this.getSTitleF());
			this.setSDescF_UI(driver, this.getSDescF());
			this.setSReqPointF_UI(driver, this.getSReqPointF());
			this.setSMarkTypeF_UI(driver, this.getSMarkTypeF());

			by = By.id("TTYPE");
			int size = driver.findElements(by).size();
			String str = "Select specific questions";
			if (size > 0) {
				WebDriverUtils.select_selector(driver, by, str);
			}

			by = By.name("SUB");
			WebDriverUtils.clickButton(driver, by);

			Navigator.explicitWait(3000);

			WebDriverUtils.switchToFrame(driver, "PALMAIN");

			// Assign Questions
			this.setQPool_UI(driver, this.getQPool());
			this.setTPool_UI(driver, this.getTPool());
			by = By.name("_SUBMIT");
			WebDriverUtils.clickButton(driver, by);

			Navigator.explicitWait(3000);

			this.setSFQ1_UI(driver, this.getSFQ1());
			this.setSFQ2_UI(driver, this.getSFQ2());

			by = By.name("SUBMIT");
			WebDriverUtils.clickButton(driver, by);
			Navigator.explicitWait(3000);
			WebDriverUtils.switchToFrame(driver, "PALMENU");

			by = By.id("editorvalidatebutton");
			WebDriverUtils.clickButton(driver, by);
			WebDriverUtils.switchToFrame(driver, "PALMAIN");
			by = By.name("validateButton");
			WebDriverUtils.clickButton(driver, by);
		}
	}

	/**
	 * Click exam to pass it with all correct answers
	 * 
	 * @param driver
	 * @param examName
	 */
	public void passExam(WebDriver driver, String examName) {
		By by = By.xpath("//a[contains(text(),'" + examName + "')]");
		WebDriverUtils.clickLink(driver, by);

		int waitTime = 5000;
		WebDriverUtils.switchToPopUpWin(driver);
		Navigator.explicitWait(waitTime);
		// 1. Start the exam
		by = By.xpath("//input[@value='Start the exam']");
		Navigator.explicitWait(waitTime);
//		Navigator.waitForAjaxElementLoad(driver, by);
		WebDriverUtils.clickButton(driver, by);

		// 2. Continue
		WebDriverUtils.switchToFrame(driver, "questionsframe");
		Navigator.explicitWait(waitTime);
		by = By.xpath("//input[@value='Continue']");
		Navigator.explicitWait(waitTime);
//		Navigator.waitForAjaxElementLoad(driver, by);
		WebDriverUtils.clickButton(driver, by);

		Navigator.explicitWait(waitTime);
		// 3. Select answers
		WebDriverUtils.switchToFrame(driver, "questionsframe");
		Navigator.explicitWait(waitTime);
		by = By.xpath("//label[contains(text(),'Correct Answer')]/input[@type='RADIO']");
		WebDriverUtils.checkRadio(driver, by);

		by = By.xpath("//tr[descendant::td/a[contains(text(),'Correct Answer1')]]/td/input[@type='CHECKBOX']");
		WebDriverUtils.check_checkbox(driver, by);

		by = By.xpath("//tr[descendant::td/a[contains(text(),'Correct Answer2')]]/td/input[@type='CHECKBOX']");
		WebDriverUtils.check_checkbox(driver, by);

		// 4. Submit answers
		by = By.xpath("//input[@value='Submit response']");
		WebDriverUtils.clickButton(driver, by);

		// 5. Continue
		WebDriverUtils.switchToFrame(driver, "questionsframe");
		Navigator.explicitWait(waitTime);
		by = By.xpath("//input[@value='Continue']");
		Navigator.explicitWait(waitTime);
//		Navigator.waitForAjaxElementLoad(driver, by);
		WebDriverUtils.clickButton(driver, by);

		// 6. End Exam
		WebDriverUtils.switchToFrame(driver, "questionsframe");
		Navigator.explicitWait(waitTime);
		by = By.xpath("//input[@value='End Exam']");
		Navigator.explicitWait(waitTime);
//		Navigator.waitForAjaxElementLoad(driver, by);
		WebDriverUtils.clickButton(driver, by);
		
		Navigator.explicitWait(waitTime);
		WebDriverUtils.acceptAlert(driver);

		Navigator.explicitWait(waitTime);

		// 7. Close exam window
		by = By.xpath("//input[@value='Close Exam Window']");
		Navigator.explicitWait(waitTime);
		WebDriverUtils.clickButton(driver, by);
		
		Navigator.explicitWait(waitTime);
		WebDriverUtils.switchToParentWin(driver);
	}

	/**
	 * Click exam to pass it without correct answers
	 * 
	 * @param driver
	 * @param examName
	 */
	public void failExam(WebDriver driver, String examName) {
		int waitTime = 5000;
		
		By by = By.xpath("//a[contains(text(),'" + examName + "')]");
		WebDriverUtils.clickLink(driver, by);

		WebDriverUtils.switchToPopUpWin(driver);
		Navigator.explicitWait(3000);
		// 1. Start the exam
		by = By.xpath("//input[@value='Start the exam']");
		Navigator.explicitWait(waitTime);
//		Navigator.waitForAjaxElementLoad(driver, by);
		WebDriverUtils.clickButton(driver, by);

		// 2. Continue
		WebDriverUtils.switchToFrame(driver, "questionsframe");
		Navigator.explicitWait(3000);
		by = By.xpath("//input[@value='Continue']");
		Navigator.explicitWait(waitTime);
//		Navigator.waitForAjaxElementLoad(driver, by);
		WebDriverUtils.clickButton(driver, by);

		Navigator.explicitWait(3000);
		// 3. Select answers
		by = By.xpath("//label[contains(text(),'Wrong Answer1')]/input[@type='RADIO']");
		WebDriverUtils.checkRadio(driver, by);

		by = By.xpath("//tr[descendant::td/a[contains(text(),'Wrong Answer1')]]/td/input[@type='CHECKBOX']");
		WebDriverUtils.check_checkbox(driver, by);

		by = By.xpath("//tr[descendant::td/a[contains(text(),'Wrong Answer2')]]/td/input[@type='CHECKBOX']");
		WebDriverUtils.check_checkbox(driver, by);

		// 4. Submit answers
		Navigator.explicitWait(waitTime);
		by = By.xpath("//input[@value='Submit response']");
		WebDriverUtils.clickButton(driver, by);

		// 5. Continue
		WebDriverUtils.switchToFrame(driver, "questionsframe");
		Navigator.explicitWait(waitTime);
		by = By.xpath("//input[@value='Continue']");
		Navigator.explicitWait(waitTime);
//		Navigator.waitForAjaxElementLoad(driver, by);
		WebDriverUtils.clickButton(driver, by);

		// 6. End Exam
		WebDriverUtils.switchToFrame(driver, "questionsframe");
		Navigator.explicitWait(3000);
		by = By.xpath("//input[@value='End Exam']");
		Navigator.explicitWait(waitTime);
//		Navigator.waitForAjaxElementLoad(driver, by);
		WebDriverUtils.clickButton(driver, by);

		WebDriverUtils.acceptAlertIfPresent(driver);

		Navigator.explicitWait(3000);

		// 7. Close exam window
		by = By.xpath("//input[@value='"+Labels.Btn_CloseTest+"']");
		Navigator.explicitWait(waitTime);
//		Navigator.waitForAjaxElementLoad(driver, by);
		WebDriverUtils.clickButton(driver, by);

		WebDriverUtils.switchToParentWin(driver);
	}

	/*
	 * public String toString(){ return new StringBuilder().
	 * append(this.getClass().getName()). append("_").
	 * append(this.getFuncType()). append("_"). append(this.getExamID()).
	 * toString(); }
	 */
}