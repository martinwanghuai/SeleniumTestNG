package com.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.abstractclasses.TestObject;
import com.config.Config;
import com.junit.JUnitAssert;
import com.utils.CriteriaParser;
import com.utils.WebDriverUtils;
import com.view.FunctionUI;
import com.view.Navigator;
import com.view.SelectorsUI;

/**A good example to show how to modulize test cases.
 * 
 * @author martin.wang
 *
 */
public class Report extends TestObject{

	private String ReportType= "", ReportID= "", OutputFormat = "", RunMethod = ""; //mandatory properties;
	private String Org= "", Module= "", Status= "", Catalog= "", Program= "", Competency= "", JobProfile= "", 
			UserGroup= "", Goal= "", Appraisal= "", User= "", Certification= "", CertProgram= "", 
			Subject= "", Exam= "", Question= "", QuestionPool= "", Vendor="", ExamPool="", QuestionStatus="",
			AuditItem="", BeginDate="", EndDate="", Fields = ""; //optional properties;
	

	public void setFields(String fields) {
		Fields = fields;
	}

	public Report(){
		super();
	}

	public void checkExpectedResult_UI(WebDriver driver, String expectedResult){
		super.checkExpectedResult_UI(driver, expectedResult);
		
		By by_table = By.id("t1");/*By.xpath("//table[@id='t1']");*/
		By by_rows;
		int rowCount;
		String text;
		
		Navigator.explicitWait(1000);
		
		by_rows = By.xpath("//table[@class='sortable' and @id='t1']/tbody/tr");
		rowCount = WebDriverUtils.getTableRowCount(driver, by_rows); 


		int rowIndex = rowCount - 1;
		text = WebDriverUtils.getTableRowText(driver, by_table, rowIndex).trim();
		
		
		if(!expectedResult.equals("")){
			//check result by: 1. screenshot-based comparison; or 2. text-based comparison
			int index = expectedResult.indexOf(Config.DELIMIT); //split() is bad since data may also contain "|" 
			if(index > 0){
				rowCount = Integer.parseInt(expectedResult.substring(0, index));
				String rowData = expectedResult.substring(index + Config.DELIMIT.length()).trim();
				if(rowData.equalsIgnoreCase("")){
					//Screenshot-based comparison
					/*String screenshotFile = this.getReportID() + ".png";
					assertTrue(SikuliUtils.screenshotExistInWin(screenshotFile));*/
				}else{
					JUnitAssert.assertEquals(rowData, text);
				}
			}
		}else{
			//generate expected results for reports:
			//format: rowCount|lastRowData
			
			if(text.equalsIgnoreCase("")){
//				System.out.println(this.getReportID());
			}
			/*
			 * Following script is obsolete, pls find Martin for detail
			String fileName = Config.getInstance().getProperty("testDataFile");
			String sheetName = "Report";
			String keyword_search = this.getReportID();
			int keyword_ColumnIndex = 2;
			int data_ColumnIndex= 28;
			String data = rowCount + Config.DELIMIT + text;
			POIUtils.writeTestResultToCurrentTestCase(fileName, sheetName, keyword_ColumnIndex, keyword_search, data_ColumnIndex, data);	
			*/
		}
	}

	@Override
	public boolean equals(TestObject obj){
		if(obj instanceof Report && ((Report)obj).toString().equals(this.toString())){
			return true;
		}else{
			return false;
		}
	}


	public void runScheduledReport(WebDriver driver){
		//Navigator.navigate(driver, Navigator.URL.ScheduledReport);
		Navigator.navigate(driver,Navigator.xmlWebElmtMgr.getNavigationPathList("ManageCenter","ScheduleRpt"),this);
		By by = By.xpath("//a[contains(text(),'" + this.getReportID() + "')]");
		Navigator.waitForAjax(driver, by);
		WebDriverUtils.clickLink(driver, by);

		by = By.name("runNow");
		Navigator.waitForAjax(driver, by);
//		Navigator.explicitWait();
		WebDriverUtils.clickButton(driver, by);
		WebDriverUtils.switchToPopUpWin(driver);
		WebDriverUtils.switchToFrame(driver, "REPORT_MAIN_FRAME");

		this.checkExpectedResult_UI(driver, this.getExpectedResult());
	}
	
	public void executeReport(WebDriver driver){
		Navigator.navigate(driver,Navigator.xmlWebElmtMgr.getNavigationPathList("ManageCenter","Reports"),this);
		By by = By.linkText(this.getReportType());
		WebDriverUtils.clickLink(driver, by);
		by = By.xpath("//a[contains(text(),'" + this.getReportID() + "')]");
		WebDriverUtils.clickLink(driver, by);
		WebDriverUtils.switchToPopUpWin(driver);
		WebDriverUtils.switchToFrame(driver, "REPORT_MAIN_FRAME");

		//optional properties	
		this.setOutputFormat_UI(driver, this.getOutputFormat());
		this.setOrg_UI(driver, this.getOrg());
		this.setModule_UI(driver, this.getModule());
		this.setStatus_UI(driver, this.getStatus());
		this.setCatalog_UI(driver, this.getCatalog());
		this.setProgram_UI(driver, this.getProgram());
		this.setCompetency_UI(driver, this.getCompetency());
		this.setJobProfile_UI(driver, this.getJobProfile());
		this.setUserGroup_UI(driver, this.getUserGroup());
		this.setGoal_UI(driver, this.getGoal());
		this.setAppraisal_UI(driver, this.getAppraisal());
		this.setUser_UI(driver, this.getUser());
		this.setCertification_UI(driver, this.getCertification());
		this.setSubject_UI(driver, this.getSubject());
		this.setExam_UI(driver, this.getExam());
		this.setQuestion_UI(driver, this.getQuestion());
		this.setQuestionPool_UI(driver, this.getQuestionPool());
		this.setVendor_UI(driver, this.getVendor());
		this.setExamPool_UI(driver, this.getExamPool());		
		this.setQuestionStatus_UI(driver, this.getQuestionStatus());		
		this.setAuditItem_UI(driver, this.getAuditItem());
		this.setBeginDate_UI(driver, this.getBeginDate());
		this.setEndDate_UI(driver, this.getEndDate());
		
		by = By.xpath("//input[contains(@value,'" + this.getRunMethod() + "')]");
		WebDriverUtils.clickLink(driver, by);

	}
	
	public void runCheckReport(WebDriver driver){
		//1. Execute R109
		this.executeReport(driver);
		
		//2. Check R109 records
		//HashMap<String, ArrayList<String>> user_attrs = UIFunctionUtils.parseParticipants(this.getExpectedResult());
		HashMap<String, ArrayList<String>> user_attrs = CriteriaParser.parseKeyValueList(":", ";", this.getExpectedResult()); 
		Iterator<String> users = user_attrs.keySet().iterator();
		
		By by = null;
		
		int size = -1;
		while(users.hasNext()){
			String user = users.next();
			
			StringBuilder sb = new StringBuilder();
			sb.append("//tr[descendant::td[contains(text(),'"+ user.trim()+"')]");
			
			ArrayList<String> attrs = user_attrs.get(user);
			
			for(String attr: attrs){
				sb.append(" and ./td[contains(text(),'" + attr.trim() + "')]");	
			}
		
			sb.append("]");
			
			by = By.xpath(sb.toString());
			size = WebDriverUtils.getHowManyByPresntInPage(driver,by, true);
			JUnitAssert.assertTrue(size > 0, "Cannot find record with user:" + user + " attributes:" + attrs);
		}
	}
	
	public void runReportWizard(WebDriver driver){
		Navigator.navigate(driver,Navigator.xmlWebElmtMgr.getNavigationPathList("ManageCenter","RptWizard"),this);
		Navigator.explicitWait(1000);
		By by = By.linkText(this.getReportID());
		WebDriverUtils.clickLink(driver, by);
		
		WebDriverUtils.switchToPopUpWin(driver);
		Navigator.explicitWait(1000);
		WebDriverUtils.switchToFrame(driver, "REPORT_MAIN_FRAME");
		Navigator.explicitWait(1000);
		by = By.linkText("Columns");
		WebDriverUtils.clickLink(driver, by);
		Navigator.explicitWait(1000);
		this.setFields_UI(driver, this.getFields());
		
		by = By.linkText("Summary");
		WebDriverUtils.clickLink(driver, by);Navigator.explicitWait(1000);
		this.setOutputFormat_UI(driver, this.getOutputFormat());
		
		by = By.xpath("//input[contains(@value,'" + this.getRunMethod() + "')]");
		WebDriverUtils.clickLink(driver, by);
		
		super.checkExpectedResult_UI(driver, this.getExpectedResult());
	}

	public void runReport(WebDriver driver){
		this.executeReport(driver);
		this.checkExpectedResult_UI(driver, this.getExpectedResult());
	}
	
	public void setOutputFormat_UI(WebDriver driver, String outputFormat){
		if(!outputFormat.equals("")){
			By by = By.xpath("//tr[descendant::td/label[contains(text(),'" + OutputFormat + "')]]/td/input");
			WebDriverUtils.checkRadio(driver, by);
		}
	}

	public void setFields_UI(WebDriver driver, String fields){
		if(!fields.equals("")){
			By by = null;
			HashMap<String, ArrayList<String>> cata_fields =  CriteriaParser.parseKeyValueList(":", ";", this.getFields()); 
					//UIFunctionUtils.parseParticipants(this.getFields());
			
			Iterator<String> catas = cata_fields.keySet().iterator();
			String xpath = "";
			while(catas.hasNext()){
				String cata = catas.next().trim();
				xpath = "//span[contains(text(),'" + cata +"')]";
				by = By.xpath(xpath);
				WebDriverUtils.clickLink(driver, by);
				Navigator.explicitWait(1000);
				
				ArrayList<String> fieldList = cata_fields.get(cata);
				for(String fieldStr: fieldList){
					xpath = "//td[descendant::dfn/label[contains(text(),'"+fieldStr.trim()+"')]]/input[@type='CHECKBOX']";
					by = By.xpath(xpath);
					WebDriverUtils.check_checkbox(driver, by);	
			
				}
			}
			
			xpath = "//input[@type='BUTTON' and @value='Save']";
			by = By.xpath(xpath);
			WebDriverUtils.clickButton(driver, by);
		}
	}

	public void setEndDate_UI(WebDriver driver, String endDate){
		if(!endDate.equals("")){
			String xpath_calendar = "(//img[@alt='Calendar'])[2]";
			WebDriverUtils.dateSelect_Calandar(driver, endDate, xpath_calendar);
			WebDriverUtils.switchToFrame(driver, "REPORT_MAIN_FRAME");
		}
	} 


	public void setBeginDate_UI(WebDriver driver, String beginDate){
		if(!beginDate.equals("")){
			String xpath_calendar = "(//img[@alt='Calendar'])[1]";
			WebDriverUtils.dateSelect_Calandar(driver, beginDate, xpath_calendar);
			WebDriverUtils.switchToFrame(driver, "REPORT_MAIN_FRAME");
		}
	} 

	public void setAuditItem_UI(WebDriver driver, String str){
		if(!str.equals("")){
			By by = By.name("SELECTED_AUDIT_TABLE_VAL");
			int size = driver.findElements(by).size();
			if(size==1){
				WebDriverUtils.select_selector(driver, by, str);
			}
		}
	} 

	public void setQuestionStatus_UI(WebDriver driver, String str){
		if(!str.equals("")){
			By by = By.name("QUESTIONSTATUS_VAL");
			WebDriverUtils.select_selector(driver, by, str);
		}
	}

	public void setExamPool_UI(WebDriver driver, String str){
		if(!str.equals("")){
			By by = By.id("TESTPOOL_VAL");
			WebDriverUtils.select_selector(driver, by, str);
		}
	}

	public void setVendor_UI(WebDriver driver, String str){
		if(!str.equals("")){
			By by = By.linkText("Vendor");
			WebDriverUtils.clickLink(driver, by);
			WebDriverUtils.switchToPopUpWin(driver);
			SelectorsUI.PopUp_Selector(driver,  SelectorsUI.PopUpSelector.TopDownSelector, str);  
			WebDriverUtils.switchToParentWin(driver);
			WebDriverUtils.switchToFrame(driver, "REPORT_MAIN_FRAME");
		}
	}


	public void setQuestionPool_UI(WebDriver driver, String str){
		if(!str.equals("")){
			By by = By.linkText("Question Pool");
			WebDriverUtils.clickLink(driver, by);
			WebDriverUtils.switchToPopUpWin(driver);
			SelectorsUI.PopUp_Selector(driver,  SelectorsUI.PopUpSelector.TopDownSelector, str);  
			WebDriverUtils.switchToParentWin(driver);
			WebDriverUtils.switchToFrame(driver, "REPORT_MAIN_FRAME");
		}
	}


	public void setQuestion_UI(WebDriver driver, String question){
		if(!question.equals("")){
			By by = By.linkText("Question");
			WebDriverUtils.clickLink(driver, by);
			WebDriverUtils.switchToPopUpWin(driver);
			SelectorsUI.PopUp_Selector(driver,  SelectorsUI.PopUpSelector.LeftRightSelector, question);  
			WebDriverUtils.switchToParentWin(driver);
			WebDriverUtils.switchToFrame(driver, "REPORT_MAIN_FRAME");
		}
	}

	public void setExam_UI(WebDriver driver, String exam){
		if(!exam.equals("")){
			By by = By.linkText("Exam");
			WebDriverUtils.clickLink(driver, by);
			WebDriverUtils.switchToPopUpWin(driver);
			SelectorsUI.PopUp_Selector(driver,  SelectorsUI.PopUpSelector.TopDownSelector, exam);  
			WebDriverUtils.switchToParentWin(driver);
			WebDriverUtils.switchToFrame(driver, "REPORT_MAIN_FRAME");
		}
	}

	public void setSubject_UI(WebDriver driver, String subject){
		if(!subject.equals("")){
			By by = By.linkText("Subject");
			WebDriverUtils.clickLink(driver, by);
			WebDriverUtils.switchToPopUpWin(driver);
			SelectorsUI.PopUp_Selector(driver,  SelectorsUI.PopUpSelector.LeftRightSelector, subject);  
			WebDriverUtils.switchToParentWin(driver);
			WebDriverUtils.switchToFrame(driver, "REPORT_MAIN_FRAME");
		}
	} 

	/*public void setCertProgram_UI(WebDriver driver, String certProgram){
		if(!certProgram.equals("")){

			driver.findElement(By.linkText("Certification")).click();			
			Utilities.switchToPopUpWin(driver);
			if(Config.DEBUG_MODE){
				System.out.println("This window:" + driver.getTitle());	
			}

			Utilities.singleSelect_Selector(driver, certification);

			Utilities.switchToParentWin(driver);
			if(Config.DEBUG_MODE){
				System.out.println("This window:" + driver.getTitle());	
			}	 
			driver.switchTo().defaultContent();
			driver.switchTo().frame("REPORT_MAIN_FRAME");	
	 }
	}*/


	public void setCertification_UI(WebDriver driver, String str){
		if(!str.equals("")){
			By by = By.linkText("Certification");
			WebDriverUtils.clickLink(driver, by);
			WebDriverUtils.switchToPopUpWin(driver);			
			SelectorsUI.PopUp_Selector(driver,  SelectorsUI.PopUpSelector.TopDownSelector, str);  
			WebDriverUtils.switchToParentWin(driver);
			WebDriverUtils.switchToFrame(driver, "REPORT_MAIN_FRAME");
		}
	} 

	public void setUser_UI(WebDriver driver, String str){
		if(!str.equals("")){
			By by = By.id("USERID_VAL-button-id");
			int size = driver.findElements(by).size();
			if(size == 1){
				WebDriverUtils.clickLink(driver, by);
			}else{
				by = By.id("INSTRUCTORID_VAL-button-id");
				size = driver.findElements(by).size();
				if(size ==1){
					WebDriverUtils.clickLink(driver, by);
				}
			}
			Navigator.explicitWait(1000);
			ArrayList<String> keywords = new ArrayList();
			keywords.add(str);
			SelectorsUI.PopUp_Selector(driver, SelectorsUI.PopUpSelector.InnerUserSelector, keywords);
			
//			WebDriverUtils.switchToPopUpWin(driver);
//			SelectorsUtils.PopUp_Selector(driver, str);
//			WebDriverUtils.switchToParentWin(driver);
			
			WebDriverUtils.switchToFrame(driver, "REPORT_MAIN_FRAME");	
		}
	}

	public void setAppraisal_UI(WebDriver driver, String str){
		if(!str.equals("")){
			By by = By.linkText("Appraisal Templates");
			WebDriverUtils.clickLink(driver, by);
			WebDriverUtils.switchToPopUpWin(driver);
			SelectorsUI.PopUp_Selector(driver,  SelectorsUI.PopUpSelector.TopDownSelector, str);  
			WebDriverUtils.switchToParentWin(driver);
			WebDriverUtils.switchToFrame(driver, "REPORT_MAIN_FRAME");
		}
	}

	public void setGoal_UI(WebDriver driver, String str){
		if(!str.equals("")){
			By by = By.linkText("Goal Program");
			WebDriverUtils.clickLink(driver, by);
			WebDriverUtils.switchToPopUpWin(driver);
			SelectorsUI.PopUp_Selector(driver,  SelectorsUI.PopUpSelector.TopDownSelector, str);  
			WebDriverUtils.switchToParentWin(driver);
			WebDriverUtils.switchToFrame(driver, "REPORT_MAIN_FRAME");
		}
	}

	public void setUserGroup_UI(WebDriver driver, String str){
		if(!str.equals("")){
			By by = By.linkText("User Group");
			WebDriverUtils.clickLink(driver, by);
			WebDriverUtils.switchToPopUpWin(driver);
			SelectorsUI.PopUp_Selector(driver,  SelectorsUI.PopUpSelector.TopDownSelector, str);  
			WebDriverUtils.switchToParentWin(driver);
			WebDriverUtils.switchToFrame(driver, "REPORT_MAIN_FRAME");	
		}
	}

	public void setJobProfile_UI(WebDriver driver, String str){
		if(!str.equals("")){
			By by = By.linkText("Job Profile");
			WebDriverUtils.clickLink(driver, by);
			WebDriverUtils.switchToPopUpWin(driver);
			SelectorsUI.PopUp_Selector(driver,  SelectorsUI.PopUpSelector.TopDownSelector, str);  
			WebDriverUtils.switchToParentWin(driver);
			WebDriverUtils.switchToFrame(driver, "REPORT_MAIN_FRAME");
		}
	}




	public void setCompetency_UI(WebDriver driver, String str){
		if(!str.equals("")){
			By by = By.linkText("Competency");
			WebDriverUtils.clickLink(driver, by);
			WebDriverUtils.switchToPopUpWin(driver);
			SelectorsUI.PopUp_Selector(driver,  SelectorsUI.PopUpSelector.TopDownSelector, str);  
			WebDriverUtils.switchToParentWin(driver);			
			WebDriverUtils.switchToFrame(driver, "REPORT_MAIN_FRAME");
		}
	}

	public void setProgram_UI(WebDriver driver, String str){
		if(!str.equals("")){
			By by = By.linkText("Learning Program/Module Session");
			int size = driver.findElements(by).size();
			if(size == 1){
				WebDriverUtils.clickLink(driver, by);
			}else{
				by = By.linkText("Learning Program");
				size = driver.findElements(by).size();
				if(size == 1){
					WebDriverUtils.clickLink(driver, by);
				}
			}
			WebDriverUtils.switchToPopUpWin(driver);
			SelectorsUI.PopUp_Selector(driver,  SelectorsUI.PopUpSelector.TopDownSelector, str);  
			WebDriverUtils.switchToParentWin(driver);
			WebDriverUtils.switchToFrame(driver, "REPORT_MAIN_FRAME");
		}
	}

	public void setCatalog_UI(WebDriver driver, String str){
		if(!str.equals("")){
			By by =By.linkText("Catalog");
			WebDriverUtils.clickLink(driver, by);
			WebDriverUtils.switchToPopUpWin(driver);

			by = By.linkText("Expand and Display Entire Hierarchy Tree");
			WebDriverUtils.clickLink(driver, by);

			by = By.xpath("//tr[descendant::td[contains(text(),'" + str + "')]]" +
					"/td/input[@id='checked_catalogs']");
			int size = driver.findElements(by).size();
			if(size == 1){
				WebDriverUtils.clickLink(driver, by);
			}else{
				by = By.xpath("//tr[descendant::td[contains(text(),'" + str + "')]]" +
						"/td/input[@name='checked_catalogs']");
				size = driver.findElements(by).size();
				if(size ==1 ){
					WebDriverUtils.clickLink(driver, by);
				}
			}
			by = By.name("save");
			WebDriverUtils.clickLink(driver, by);
			WebDriverUtils.switchToParentWin(driver);
			WebDriverUtils.switchToFrame(driver, "REPORT_MAIN_FRAME");
		}
	}

	public void setStatus_UI(WebDriver driver, String str){
		if(!str.equals("")){
			By by = By.linkText("Status");
			WebDriverUtils.clickLink(driver, by);
			WebDriverUtils.switchToPopUpWin(driver);
			SelectorsUI.PopUp_Selector(driver,  SelectorsUI.PopUpSelector.LeftRightSelector, str);  
			WebDriverUtils.switchToParentWin(driver);
			WebDriverUtils.switchToFrame(driver, "REPORT_MAIN_FRAME");
		}
	}

	public void setOrg_UI(WebDriver driver, String str){
		if(!str.equals("")){
			By by = By.linkText("Organization");
			WebDriverUtils.clickLink(driver, by);
			WebDriverUtils.switchToPopUpWin(driver);
			WebDriverUtils.checkSelect_Radio(driver, str);
			WebDriverUtils.switchToParentWin(driver);
			WebDriverUtils.switchToFrame(driver, "REPORT_MAIN_FRAME");
		}
	}

	public void setModule_UI(WebDriver driver, String str){
		if(!str.equals("")){
			By by = By.linkText("Learning Module");
			int size = driver.findElements(by).size();
			if(size == 1){
				WebDriverUtils.clickLink(driver, by);
			}else{
				by = By.linkText("Learning Module with Evaluation");
				size = driver.findElements(by).size();
				if(size == 1){
					WebDriverUtils.clickLink(driver, by);
				}else{
					by = By.linkText("Learning Module/Session");
					size = driver.findElements(by).size();
					if(size == 1){
						WebDriverUtils.clickLink(driver, by);
					}
				}
			}
			WebDriverUtils.switchToPopUpWin(driver);
			SelectorsUI.PopUp_Selector(driver,  SelectorsUI.PopUpSelector.TopDownSelector, str);  
			WebDriverUtils.switchToParentWin(driver);
			WebDriverUtils.switchToFrame(driver, "REPORT_MAIN_FRAME");
		}
	}

	public String getFields(){
		return Fields;
	}
	
	public String getReportType() {
		return ReportType;
	}

	public void setReportType(String reportType) {
		ReportType = reportType;
	}

	public String getReportID() {
		return ReportID;
	}

	public void setReportID(String reportID) {
		ReportID = reportID;
	}

	public String getOutputFormat() {
		return OutputFormat;
	}

	public void setOutputFormat(String outputFormat) {
		OutputFormat = outputFormat;
	}

	public String getRunMethod() {
		return RunMethod;
	}

	public void setRunMethod(String runMethod) {
		RunMethod = runMethod;
	}

	public String getOrg() {
		return Org;
	}

	public void setOrg(String org) {
		Org = org;
	}

	public String getModule() {
		return Module;
	}

	public void setModule(String module) {
		Module = module;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public String getCatalog() {
		return Catalog;
	}

	public void setCatalog(String catalog) {
		Catalog = catalog;
	}

	public String getProgram() {
		return Program;
	}

	public void setProgram(String program) {
		Program = program;
	}

	public String getCompetency() {
		return Competency;
	}

	public void setCompetency(String competency) {
		Competency = competency;
	}

	public String getJobProfile() {
		return JobProfile;
	}

	public void setJobProfile(String jobProfile) {
		JobProfile = jobProfile;
	}

	public String getUserGroup() {
		return UserGroup;
	}

	public void setUserGroup(String userGroup) {
		UserGroup = userGroup;
	}

	public String getGoal() {
		return Goal;
	}

	public void setGoal(String goal) {
		Goal = goal;
	}

	public String getAppraisal() {
		return Appraisal;
	}

	public void setAppraisal(String appraisal) {
		Appraisal = appraisal;
	}

	public String getUser() {
		return User;
	}

	public void setUser(String user) {
		User = user;
	}

	public String getCertification() {
		return Certification;
	}

	public void setCertification(String certification) {
		Certification = certification;
	}

	public String getCertProgram() {
		return CertProgram;
	}

	public void setCertProgram(String certProgram) {
		CertProgram = certProgram;
	}

	public String getSubject() {
		return Subject;
	}

	public void setSubject(String subject) {
		Subject = subject;
	}

	public String getExam() {
		return Exam;
	}

	public void setExam(String exam) {
		Exam = exam;
	}

	public String getQuestion() {
		return Question;
	}

	public void setQuestion(String question) {
		Question = question;
	}

	public String getQuestionPool() {
		return QuestionPool;
	}

	public void setQuestionPool(String questionPool) {
		QuestionPool = questionPool;
	}

	public String getVendor() {
		return Vendor;
	}

	public void setVendor(String vendor) {
		Vendor = vendor;
	}

	public String getExamPool() {
		return ExamPool;
	}

	public void setExamPool(String examPool) {
		ExamPool = examPool;
	}

	public String getQuestionStatus() {
		return QuestionStatus;
	}

	public void setQuestionStatus(String questionStatus) {
		QuestionStatus = questionStatus;
	}

	public String getAuditItem() {
		return AuditItem;
	}

	public void setAuditItem(String auditItem) {
		AuditItem = auditItem;
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

	public String getExpectedResult() {
		return ExpectedResult;
	}

	public void setExpectedResult(String expectedResult) {
		ExpectedResult = expectedResult;
	}

	public String getFuncType() {
		return FuncType;
	}

	public void setFuncType(String funcType) {
		FuncType = funcType;
	}

	public String getUID() {
		return UID;
	}

	public void setUID(String uID) {
		UID = uID;
	}

	public String getPWD() {
		return PWD;
	}

	public void setPWD(String pWD) {
		PWD = pWD;
	}
}
