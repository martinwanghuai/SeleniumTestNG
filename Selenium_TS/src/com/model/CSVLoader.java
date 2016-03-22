package com.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import com.abstractclasses.TestObject;
import com.junit.JUnitAssert;
import com.utils.WebDriverUtils;
import com.view.FunctionUI;
import com.view.Navigator;

/**CSVLoader is a good example to demonstrate 
 * 1). the benefits of using
 * Java Reflection to load classes since some attributes can be added/deleted in Excel
 * 2). how to handle with e-signature
 * 
 * @author martin.wang
 *
 */
public class CSVLoader extends TestObjectSignature{
	private String FileName = "", Desc = "", Status = "", Encoding = "", Delimiter = "", Permission = "", 
			SendNote = "", CreateCatalog = "", chkAttrExist = "", chkOrgExist = "", chkUserGroupExist = "", Profile="";

	public CSVLoader(){
		super();
	}

	
	@Override
	public boolean equals(TestObject obj){
		if(obj instanceof CSVLoader && ((CSVLoader)obj).toString().equals(this.toString())){
			return true;
		}else{
			return false;
		}
	}

/*	public String toString(){
		return new StringBuilder().append(this.getClass().getName()).
				append("_").
				append(this.getFuncType()).
				toString();
	}
	*/
	
	
	
	public void runUserCSVLoader(WebDriver driver){
		
		Navigator.navigate(driver,Navigator.xmlWebElmtMgr.getNavigationPathList("ManageCenter","UserLoader"),this);

		By by = By.name("uploadButton");
	    WebDriverUtils.clickButton(driver, by);
		this.setFileName_UI(driver, this.getFileName());
	    
	    this.setChkAttrExist_UI(driver, this.getChkAttrExist());
	    this.setOrgExist_UI(driver, this.getChkOrgExist());
	    this.setUserGroupExist_UI(driver,this.getChkUserGroupExist());
	    
	    this.setDesc_UI(driver, this.getDesc());
		this.setProfile_UI(driver, this.getProfile());
	    
		by = By.name("importButton");
	    WebDriverUtils.clickButton(driver, by);
	    
//	    Navigator.explicitWait(3000);
	    
	    by = By.name("uploadButton");
	    WebDriverUtils.clickButton(driver, by);
		
		String output = this.getExpectedResult();
		if(!output.equals("")){
			String[] outputs = output.split(":");
			String ID = outputs[0];
			by = By.xpath("//tr[descendant::td[contains(text(),'" + ID + "')]]/td[2]");
			String value = WebDriverUtils.getText(driver, by);
			String expectedValue = outputs[1];
			JUnitAssert.assertEquals(expectedValue, value);
		}
	}
	
	public void runUserProfileCSVLoader(WebDriver driver){

		//Navigator.navigate(driver, Navigator.URL.ManageCenter, this);
		Navigator.navigate(driver,Navigator.xmlWebElmtMgr.getNavigationPathList("ManageCenter","UserProf"),this);
		By by;
		by = By.name("uploadButton");
	    WebDriverUtils.clickButton(driver, by);
		this.setFileName_UI(driver, this.getFileName());
	    
	    this.setDesc_UI(driver, this.getDesc());

	    by = By.name("importButton");
	    WebDriverUtils.clickButton(driver, by);
	    by = By.name("uploadButton");
	    WebDriverUtils.clickButton(driver, by);
	    
		String output = this.getExpectedResult();
		if(!output.equals("")){
			String[] outputs = output.split(":");
			String ID = outputs[0];
			String value = driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'" + ID + "')]]/td[2]")).getText();
			JUnitAssert.assertTrue(value.equals(outputs[1]), value +" != " + outputs[1]);
		}
	}

	public void runUserGroupCSVLoader(WebDriver driver){
	
		Navigator.navigate(driver,Navigator.xmlWebElmtMgr.getNavigationPathList("ManageCenter","UserGroup"),this);
	    driver.findElement(By.name("uploadButton")).click();
	    
	    this.setFileName_UI(driver, this.getFileName());
	    this.setDesc_UI(driver, this.getDesc());
	    this.setUserGroupExist_UI(driver, this.getChkUserGroupExist());
	    
	    driver.findElement(By.name("importButton")).click();
	    driver.findElement(By.name("uploadButton")).click();
		
		String output = this.getExpectedResult();
		if(!output.equals("")){
			String[] outputs = output.split(":");
			String ID = outputs[0];
			String value = driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'" + ID + "')]]/td[2]")).getText();
			String expectedValue = outputs[1];
			JUnitAssert.assertEquals(expectedValue, value);
		}
		
	}
	
	public void runCertCSVLoader(WebDriver driver){
	
		
		boolean ESigEnabled = ESignature.isESignatureEnabled(driver, this, "AwardDeleteCert");
		
		Navigator.navigate(driver,Navigator.xmlWebElmtMgr.getNavigationPathList("ManageCenter","CertAward"),this);		
	    driver.findElement(By.name("uploadButton")).click();
	    this.setFileName_UI(driver, this.getFileName());
	    this.setDesc_UI(driver, this.getDesc());
	    driver.findElement(By.name("importButton")).click();
	    driver.findElement(By.name("uploadButton")).click();
		
		if(ESigEnabled){
			JUnitAssert.assertTrue(WebDriverUtils.isElementPresent(By.id("ESIGNATURE-username")), "No esign popup window!");
			FunctionUI.fillESignature(driver, this.getUID(), this.getPWD());
		}

	    String output = this.getExpectedResult();
		if(!output.equals("")){
			String[] outputs = output.split(":");
			String ID = outputs[0];
			String value = driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'" + ID + "')]]/td[2]")).getText();
			String expectedValue = outputs[1];
			JUnitAssert.assertEquals(expectedValue, value);
		}
		
	}


	public void runCourseCSVLoader(WebDriver driver){

		//check whether esig is enabled for login user
		boolean ESigEnabled = ESignature.isESignatureEnabled(driver, this, "CSVLoader_Course");

		Navigator.navigate(driver,Navigator.xmlWebElmtMgr.getNavigationPathList("ManageCenter","CourseLoader"),this);	
		
		driver.findElement(By.name("uploadButton")).click();	    

		this.setFileName_UI(driver, this.getFileName());

		this.setDesc_UI(driver, this.getDesc());

		this.setCreateCatalog_UI(driver, this.getCreateCatalog());
		
		this.setSendNote_UI(driver, this.getSendNote());

		driver.findElement(By.name("importButton")).click();

		driver.findElement(By.name("uploadButton")).click();

		if(ESigEnabled){
			JUnitAssert.assertTrue(WebDriverUtils.isElementPresent(By.id("ESIGNATURE-username")), "No esign popup window!");
			FunctionUI.fillESignature(driver, this.getUID(), this.getPWD());
		}

		this.checkExpectedResult_UI(driver, this.getExpectedResult());
	}


	public void runProgramCSVLoader(WebDriver driver){


		//check whether esig is enabled for login user
		
		boolean ESigEnabled = ESignature.isESignatureEnabled(driver, this, "CSVLoader_Program");

		Navigator.navigate(driver,Navigator.xmlWebElmtMgr.getNavigationPathList("ManageCenter","ProgramLoader"),this);

		driver.findElement(By.name("uploadButton")).click();
		this.setFileName_UI(driver, this.getFileName());

		this.setDesc_UI(driver, this.getDesc());
		
		this.setSendNote_UI(driver, this.getSendNote());
		
		driver.findElement(By.name("importButton")).click();
		driver.findElement(By.name("uploadButton")).click();

		if(ESigEnabled){
			JUnitAssert.assertTrue(WebDriverUtils.isElementPresent(By.id("ESIGNATURE-username")), "No esign popup window!");
			FunctionUI.fillESignature(driver, this.getUID(), this.getPWD());
		}

		this.checkExpectedResult_UI(driver, this.getExpectedResult());
	}

	public void runQuestionCSVLoader(WebDriver driver){
	

		//check whether esig is enabled for login user
		boolean ESigEnabled = ESignature.isESignatureEnabled(driver, this, "ImportQuestion");

		Navigator.navigate(driver,Navigator.xmlWebElmtMgr.getNavigationPathList("ManageCenter","QuestionLoader"),this);

		//for 100, 101
		By by = By.linkText("Question CSV Loader");
		WebDriverUtils.clickButton(driver, by);
		

		WebDriverUtils.switchToPopUpWin(driver);
		driver.findElement(By.name("uploadButton")).click();
		
		this.setFileName_UI(driver, this.getFileName());
		
		this.setDesc_UI(driver, this.getDesc());
		
		this.setEncoding_UI(driver, this.getEncoding());
		
		this.setDelimiter_UI(driver, this.getDelimiter());
		
		this.setPermission_UI(driver, this.getPermission());
		
		this.setStatus_UI(driver, this.getStatus());

		
		driver.findElement(By.name("importButton")).click();
		driver.findElement(By.name("uploadButton")).click();

		if(ESigEnabled){
			JUnitAssert.assertTrue(WebDriverUtils.isElementPresent(By.id("ESIGNATURE-username")), "No esign popup window!");
			FunctionUI.fillESignature(driver, this.getUID(), this.getPWD());
		}

		driver.findElement(By.name("LOADER")).click();

		String output = this.getExpectedResult();
		if(!output.equals("")){
			String[] outputs = output.split(":");
			String ID = outputs[0];
			String value = driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'" + ID + "')]]/td[2]")).getText();
			JUnitAssert.assertTrue(value.equals(outputs[1]), value +" != " + outputs[1]);
		}
		
		WebDriverUtils.closeAllPopUpWins(driver);
	}
	
	
	public String getChkUserGroupExist() {
		return chkUserGroupExist;
	}


	public void setChkUserGroupExist(String chkUserGroupExist) {
		this.chkUserGroupExist = chkUserGroupExist;
	}


	public String getChkAttrExist() {
		return chkAttrExist;
	}


	public void setChkAttrExist(String chkAttrExist) {
		this.chkAttrExist = chkAttrExist;
	}


	public String getChkOrgExist() {
		return chkOrgExist;
	}


	public void setChkOrgExist(String chkOrgExist) {
		this.chkOrgExist = chkOrgExist;
	}
	
	public void setFileName_UI(WebDriver driver, String fileName){
		if(!fileName.equals("")){
			driver.findElement(By.id("filename")).sendKeys(fileName);
		}
	}

	public void setDesc_UI(WebDriver driver, String desc){
		if(!desc.equalsIgnoreCase("")){
			driver.findElement(By.id("DESC")).sendKeys(desc);	
		}
	}

	public void setEncoding_UI(WebDriver driver, String encoding){
		if(!encoding.equals("")){
			new Select(driver.findElement(By.name("encoding"))).selectByVisibleText(encoding);	
		}
	}
	
	
	public void setCreateCatalog_UI(WebDriver driver, String createCatalog){
		if(createCatalog.equalsIgnoreCase("yes")){
			By by = By.id("CREATE_CATS");
			WebDriverUtils.check_checkbox(driver, by);
		}
	}
	
	
	public void setDelimiter_UI(WebDriver driver, String delimiter){
		if(!delimiter.equals("")){
			new Select(driver.findElement(By.name("delimiter"))).selectByVisibleText(delimiter);	
		}
	}
	
	public void setPermission_UI(WebDriver driver, String permission){
		if(!permission.equals("")){
			new Select(driver.findElement(By.id("QPERM"))).selectByVisibleText(permission);
		}
	}
	
	public void setStatus_UI(WebDriver driver, String status){
		if(!status.equals("")){
			new Select(driver.findElement(By.name("QSTATUS"))).selectByVisibleText(status);		
		}
	}
	
	public void setSendNote_UI(WebDriver driver, String sendNote){
		if(sendNote.equalsIgnoreCase("yes")){
			By by = By.id("SEND_NOTIFICATION");
			int size = driver.findElements(by).size();
			if(size == 1){
				WebDriverUtils.check_checkbox(driver, by);	
			}
		}
	}
	
	public void checkExpectedResult_UI(WebDriver driver, String expectedResult){
		super.checkExpectedResult_UI(driver, expectedResult);
		if(!expectedResult.equals("")){	
			String[] outputs = expectedResult.split(":");
			String ID = outputs[0];
			String value = driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'" + ID + "')]]/td[2]")).getText();
			String expectedValue = outputs[1];
			JUnitAssert.assertEquals(expectedValue, value);
		}
	}
	
	public void setChkAttrExist_UI(WebDriver driver, String chkAttrExist){
		By by = By.id("chkAttrExist");
		if(chkAttrExist.equalsIgnoreCase("yes")){
			WebDriverUtils.check_checkbox(driver, by);
		}else if(chkAttrExist.equalsIgnoreCase("no")){
			WebDriverUtils.uncheck_checkbox(driver, by);
		}
	}
	
	public void setOrgExist_UI(WebDriver driver, String chkOrgExist){
		By by = By.id("chkOrgExist");
		if(chkOrgExist.equalsIgnoreCase("yes")){
			WebDriverUtils.check_checkbox(driver, by);
		}else if(chkOrgExist.equalsIgnoreCase("no")){
			WebDriverUtils.uncheck_checkbox(driver, by);
		}
	}
	
	public void setUserGroupExist_UI(WebDriver driver, String chkUserGroupExist){
		By by = By.id("chkUserGroupExist");
		int size = driver.findElements(by).size();
		if(size == 0){
			by = By.id("chkGroupExist");
		}
		
		if(chkUserGroupExist.equalsIgnoreCase("yes")){
			WebDriverUtils.check_checkbox(driver, by);
		}else if(chkUserGroupExist.equalsIgnoreCase("no")){
			WebDriverUtils.uncheck_checkbox(driver, by);
		}
	}
	
	
	public void setProfile_UI(WebDriver driver, String str) {
		if(!str.equals("")){			
			By by = null;
			if(str.toLowerCase().contains("default")){
				by = By.id("PROFILE_DEFAULT");
				WebDriverUtils.checkRadio(driver, by);
			}else{
				by = By.id("PROFILE_SELECT");
				WebDriverUtils.checkRadio(driver, by);
				by = By.name("PROFILE");
				WebDriverUtils.select_selector(driver, by, str);
			}
		}
	}
	

	public String getFileName() {
		return FileName;
	}

	public void setFileName(String fileName) {
		FileName = fileName;
	}

	public void runCSVLoader(WebDriver driver){

	}

	public String getDesc() {
		return Desc;
	}

	public void setDesc(String desc) {
		Desc = desc;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public String getEncoding() {
		return Encoding;
	}

	public void setEncoding(String encoding) {
		Encoding = encoding;
	}

	public String getDelimiter() {
		return Delimiter;
	}

	public void setDelimiter(String delimiter) {
		Delimiter = delimiter;
	}

	public String getPermission() {
		return Permission;
	}

	public void setPermission(String permission) {
		Permission = permission;
	}

	public String getSendNote() {
		return SendNote;
	}

	public void setSendNote(String sendNote) {
		SendNote = sendNote;
	}

	public String getCreateCatalog() {
		return CreateCatalog;
	}

	public void setCreateCatalog(String createCatalog) {
		CreateCatalog = createCatalog;
	}


	public String getProfile() {
		return Profile;
	}


	public void setProfile(String profile) {
		Profile = profile;
	}

}
