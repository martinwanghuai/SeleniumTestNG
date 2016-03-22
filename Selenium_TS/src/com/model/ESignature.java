package com.model;

import java.lang.reflect.Field;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import com.abstractclasses.TestObject;
import com.utils.WebDriverUtils;
import com.view.FunctionUI;
import com.view.Navigator;


/**A good example to show how to invoke various selectors(checkbox selector for catalog/org, 
 * search text selector for user/module, date selector for start/end time)
 * 
 * @author martin.wang
 *
 */
public class ESignature extends TestObject{

	private String Org = "",	EnableAll = "",	DisableAll = "", InheritAll = "",
			CSVLoader_Course ="", CSVLoader_Program = "",
			WithdrawCourse = "",	UpdateDeleteCourse = "",	
			LaunchCourse =	"", ImportCourse = "",	
			FinishCourse = "",	LaunchExam = "",
			ChangeQuestionStatus = "", ImportQuestion = "",
			GradeAnswer	= "", ModifyTranscriptReviewer = "", ModifyTranscriptEditor = "", 
			ModifyAttendance = "",	ModifyStatusViaWizard ="",
			ModifyExtTrainingRecord = "",	UpdateDeleteCert = "",
			AwardDeleteCert = "";


	public ESignature(){
		super();
		this.setAllFields("Disable");
	}

	public void checkExpectedResult_UI(WebDriver driver, String expectedResult){
		super.checkExpectedResult_UI(driver, expectedResult);
	}

	@Override
	public boolean equals(TestObject obj){
		if(obj instanceof ESignature && ((ESignature)obj).toString().equals(this.toString())){
			return true;
		}else{
			return false;
		}
	}

/*	@Override
	public String toString(){
		return new StringBuilder().append(this.getClass().getName()).
				append("_").
				append(this.getFuncType()).
				append("_").
				append(this.getOrg()).
				toString();
	}*/



	/**
	 * 
	 * @param driver: WebDriver instance
	 * @param obj: 
	 * @param esigName: esignature name, which is defined in com.nedimen.model.ESignature and
	 * Excel sheet "ESignature"  
	 * @return
	 */
	public static boolean isESignatureEnabled(WebDriver driver, TestObjectSignature obj, String esigName){
		
		boolean enable = false;
		if(obj.getSignatureEnabled().equalsIgnoreCase("yes")){
			enable = true;
		}
		return enable;

		/*try {
			if(obj.getSignatureEnabled().equalsIgnoreCase("yes")){
				//just read it if specified in spreadsheet
				enable = true;
			}else{
				//otherwise, checkout the existing setup
				String UID = obj.getUID();
				String orgPath = Organization.getOrgPath_UI(driver, UID);

				ESignature esig_obj = new ESignature();
				esig_obj.setFuncType("runSetESignature");
				esig_obj.setOrg(orgPath);			
				String ID = esig_obj.toString();

				TestObject testObj = TestDriver.getTestObject(ID);
				if(testObj instanceof ESignature){
					ESignature esig = (ESignature)testObj;
					Field field = esig.getClass().getDeclaredField(esigName);
					String value = (String)field.get(esig);			
					if(value.equalsIgnoreCase("Enable")){
						enable = true;
					}else if(value.equalsIgnoreCase("Disable")){
						enable = false;
					}
				}	
			}
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return enable;*/
	}

	/**Set ESignature in System config.
	 * Apply to versions before TS101(e.g., TS100, 93,92,91....)
	 * 
	 * @param driver
	 */
	public void runSetESignatureConfig(WebDriver driver){
		Navigator.navigate(driver,Navigator.xmlWebElmtMgr.getNavigationPathList("ManageCenter","SystemConf"),this);
		new Select(driver.findElement(By.name("CUSTCATEGORY"))).selectByVisibleText("eSignature");
		
		By by = By.id("compliance_EnableESignatureCourseCSVLoader");
		this.setESIG_UI_checkBox(driver, by, this.getCSVLoader_Course());
		
		by = By.id("compliance_EnableESignatureProgramCSVLoader");
		this.setESIG_UI_checkBox(driver, by, this.getCSVLoader_Program());
		
		by = By.id("compliance_WithdrawCourse");
		this.setESIG_UI_checkBox(driver, by, this.getWithdrawCourse());
		
		by = By.id("compliance_catalogEditorESignature");
		this.setESIG_UI_checkBox(driver, by, this.getUpdateDeleteCourse());
		
		by = By.id("compliance_courseLaunchESignature");
		this.setESIG_UI_checkBox(driver, by, this.getLaunchCourse());
		
		by = By.id("compliance_structuredCourseImporterESignature");
		this.setESIG_UI_checkBox(driver, by, this.getImportCourse());
		
		by = By.id("compliance_courseCompleteESignature");
		this.setESIG_UI_checkBox(driver, by, this.getFinishCourse());
		
		by = By.id("compliance_examLaunchESignature");
		this.setESIG_UI_checkBox(driver, by, this.getLaunchExam());
		
		by = By.id("compliance_EnableESignatureForQuestionStatusChange");
		this.setESIG_UI_checkBox(driver, by, this.getChangeQuestionStatus());
		
		by = By.id("compliance_EnableESignatureForQuestionImporter");
		this.setESIG_UI_checkBox(driver, by, this.getImportQuestion());
		
		by = By.id("compliance_EnableESignatureManualGradingOfAnswer");
		this.setESIG_UI_checkBox(driver, by, this.getGradeAnswer());
		
		by = By.id("compliance_transcriptModificationESignature");
		this.setESIG_UI_checkBox(driver, by, this.getModifyTranscriptReviewer());
		
		by = By.id("compliance_transcriptModifiedByCatalogEditorESignature");
		this.setESIG_UI_checkBox(driver, by, this.getModifyTranscriptEditor());
		
		by = By.id("compliance_TranscriptAttendanceDetail");
		this.setESIG_UI_checkBox(driver, by, this.getModifyAttendance());
		
		by = By.id("compliance_EnrollmentWizard");
		this.setESIG_UI_checkBox(driver, by, this.getModifyStatusViaWizard());
		
		by = By.id("compliance_ExternalTrainingRecord");
		this.setESIG_UI_checkBox(driver, by, this.getModifyExtTrainingRecord());
		
		by = By.id("compliance_EnableESignatureCertificationEditor");
		this.setESIG_UI_checkBox(driver, by, this.getUpdateDeleteCert());
		
		by = By.id("compliance_EnableESignatureAwardingCertificationEditor");
		this.setESIG_UI_checkBox(driver, by, this.getAwardDeleteCert());
		
		driver.findElement(By.name("SAVE")).click();
	}


	/**Set Esignature in Org Maintenance. Apply to versions after TS101.
	 * 
	 * @param driver
	 */
	public void runSetESignature(WebDriver driver){

		Navigator.navigate(driver,Navigator.xmlWebElmtMgr.getNavigationPathList("ManageCenter","OrgMaintenance"),this);

		//Org must start with "ALL"
		if(!this.Org.equals("")){
			//1. expand organizations
			FunctionUI.expandTree_UI(driver, this.Org);

			//2. mouse over the gear
			By by = By.id("organization-maintenance-gear");
			WebDriverUtils.mouseOver(driver, by);
			
			by = By.xpath("//div[@id='organization-maintenance-gear']/ul/li/a[contains(text(),'Edit')]");
			WebDriverUtils.clickButton(driver, by);

			//3. edit org's e-signature
			if(this.getEnableAll().equalsIgnoreCase("yes")){
				
				by = By.linkText("Enable All");
				WebDriverUtils.clickLink(driver, by);
				this.setAllFields("Enable");

			}else if(this.getDisableAll().equalsIgnoreCase("yes")){

				by = By.linkText("Disable All");
				WebDriverUtils.clickLink(driver, by);
				this.setAllFields("Disable");

			}else if(this.getInheritAll().equalsIgnoreCase("yes")){

				by = By.linkText("Inherit from Parent Settings for All");
				WebDriverUtils.clickLink(driver, by);
				this.setAllFields("Inherit");

			}else{
				this.setESIG_UI(driver, "ESIG_COURSE_CSV_LOADER", this.getCSVLoader_Course());
				this.setESIG_UI(driver, "ESIG_PROGRAM_CSV_LOADER", this.getCSVLoader_Program());
				this.setESIG_UI(driver, "ESIG_COURSE_WITHDRAW", this.getWithdrawCourse());
				this.setESIG_UI(driver, "ESIG_COURSE_MODIFY", this.getUpdateDeleteCourse());
				this.setESIG_UI(driver, "ESIG_COURSE_LAUNCH", this.getLaunchCourse());
				this.setESIG_UI(driver, "ESIG_COURSE_IMPORT", this.getImportCourse());
				this.setESIG_UI(driver, "ESIG_COURSE_FINISH", this.getFinishCourse());
				this.setESIG_UI(driver, "ESIG_EXAM_LAUNCH", this.getLaunchExam());
				this.setESIG_UI(driver, "ESIG_QUESTION_MODIFY", this.getChangeQuestionStatus());
				this.setESIG_UI(driver, "ESIG_QUESTION_IMPORT", this.getImportQuestion());
				this.setESIG_UI(driver, "ESIG_GRADING_TEST_ANSWER", this.getGradeAnswer());
				this.setESIG_UI(driver, "ESIG_REVIEWER_MODIFY_TRANS", this.getModifyTranscriptReviewer());
				this.setESIG_UI(driver, "ESIG_EDITOR_MODIFY_TRANS", this.getModifyTranscriptEditor());
				this.setESIG_UI(driver, "ESIG_MODIFY_ATTENDANCE", this.getModifyAttendance());
				this.setESIG_UI(driver, "ESIG_WIZARD_MODIFY_TRANS", this.getModifyStatusViaWizard());
				this.setESIG_UI(driver, "ESIG_MODIFY_EXT_TRAINING", this.getModifyExtTrainingRecord());
				this.setESIG_UI(driver, "ESIG_MODIFY_CERT", this.getUpdateDeleteCert());
				this.setESIG_UI(driver, "ESIG_AWARD_REVERT_CERT", this.getAwardDeleteCert());
			}

			//4. save org's setting
			by = By.name("saveButton");
			WebDriverUtils.clickButton(driver, by);
		}
	}

	private void setAllFields(String value){
		try {
			Field[] fields = this.getClass().getDeclaredFields();
			for(Field field: fields){
				field.setAccessible(true);
				field.set(this, value);
			}
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setESIG_UI(WebDriver driver, String id, String config){
		if(!config.equals("")){	
			By by = By.id(id);
			WebDriverUtils.select_selector(driver, by, config);
		}				
	}
	
	public void setESIG_UI_checkBox(WebDriver driver, By by, String config){
		String str = config.toLowerCase();
		if(str.equalsIgnoreCase("enable")){
			WebDriverUtils.check_checkbox(driver, by);
		}else if(str.equalsIgnoreCase("disable")){
			WebDriverUtils.uncheck_checkbox(driver, by);
		}
	}
	

	public String getOrg() {
		return Org;
	}


	public void setOrg(String org) {
		Org = org;
	}


	public String getEnableAll() {
		return EnableAll;
	}


	public void setEnableAll(String enableAll) {
		EnableAll = enableAll;
	}


	public String getDisableAll() {
		return DisableAll;
	}


	public void setDisableAll(String disableAll) {
		DisableAll = disableAll;
	}


	public String getCSVLoader_Course() {
		return CSVLoader_Course;
	}


	public void setCSVLoader_Course(String cSVLoader_Course) {
		CSVLoader_Course = cSVLoader_Course;
	}


	public String getCSVLoader_Program() {
		return CSVLoader_Program;
	}


	public void setCSVLoader_Program(String cSVLoader_Program) {
		CSVLoader_Program = cSVLoader_Program;
	}


	public String getWithdrawCourse() {
		return WithdrawCourse;
	}


	public void setWithdrawCourse(String withdrawCourse) {
		WithdrawCourse = withdrawCourse;
	}


	public String getUpdateDeleteCourse() {
		return UpdateDeleteCourse;
	}


	public void setUpdateDeleteCourse(String updateDeleteCourse) {
		UpdateDeleteCourse = updateDeleteCourse;
	}


	public String getLaunchCourse() {
		return LaunchCourse;
	}


	public void setLaunchCourse(String launchCourse) {
		LaunchCourse = launchCourse;
	}


	public String getImportCourse() {
		return ImportCourse;
	}


	public void setImportCourse(String importCourse) {
		ImportCourse = importCourse;
	}


	public String getFinishCourse() {
		return FinishCourse;
	}


	public void setFinishCourse(String finishCourse) {
		FinishCourse = finishCourse;
	}


	public String getLaunchExam() {
		return LaunchExam;
	}


	public void setLaunchExam(String launchExam) {
		LaunchExam = launchExam;
	}


	public String getChangeQuestionStatus() {
		return ChangeQuestionStatus;
	}


	public void setChangeQuestionStatus(String changeQuestionStatus) {
		ChangeQuestionStatus = changeQuestionStatus;
	}


	public String getImportQuestion() {
		return ImportQuestion;
	}


	public void setImportQuestion(String importQuestion) {
		ImportQuestion = importQuestion;
	}


	public String getGradeAnswer() {
		return GradeAnswer;
	}


	public void setGradeAnswer(String gradeAnswer) {
		GradeAnswer = gradeAnswer;
	}


	public String getModifyAttendance() {
		return ModifyAttendance;
	}


	public void setModifyAttendance(String modifyAttendance) {
		ModifyAttendance = modifyAttendance;
	}


	public String getModifyStatusViaWizard() {
		return ModifyStatusViaWizard;
	}


	public void setModifyStatusViaWizard(String modifyStatusViaWizard) {
		ModifyStatusViaWizard = modifyStatusViaWizard;
	}


	public String getUpdateDeleteCert() {
		return UpdateDeleteCert;
	}


	public void setUpdateDeleteCert(String updateDeleteCert) {
		UpdateDeleteCert = updateDeleteCert;
	}


	public String getAwardDeleteCert() {
		return AwardDeleteCert;
	}


	public void setAwardDeleteCert(String awardDeleteCert) {
		AwardDeleteCert = awardDeleteCert;
	}


	public String getInheritAll() {
		return InheritAll;
	}


	public void setInheritAll(String inheritAll) {
		InheritAll = inheritAll;
	}


	public String getModifyTranscriptReviewer() {
		return ModifyTranscriptReviewer;
	}


	public void setModifyTranscriptReviewer(String modifyTranscriptReviewer) {
		ModifyTranscriptReviewer = modifyTranscriptReviewer;
	}


	public String getModifyTranscriptEditor() {
		return ModifyTranscriptEditor;
	}


	public void setModifyTranscriptEditor(String modifyTranscriptEditor) {
		ModifyTranscriptEditor = modifyTranscriptEditor;
	}


	public String getModifyExtTrainingRecord() {
		return ModifyExtTrainingRecord;
	}


	public void setModifyExtTrainingRecord(String modifyExtTrainingRecord) {
		ModifyExtTrainingRecord = modifyExtTrainingRecord;
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
