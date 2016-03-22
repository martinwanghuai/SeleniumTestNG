package com.model;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.abstractclasses.TestObject;
import com.config.Labels;
import com.junit.JUnitAssert;
import com.utils.WebDriverUtils;
import com.view.Navigator;

/**AICC is a special-kind-of Online, so we inherit from Online rather than TestObject.
 * 
 * @author martin.wang
 *
 */
public class AICC extends Online {
	private String AUFile = "", CRSFile = "", CSTFile = "", DESFile = "", 
			Encoding = "", Revision = "";

	public String getCRSFile() {
		return CRSFile;
	}

	public void setCRSFile(String cRSFile) {
		CRSFile = cRSFile;
	}

	public AICC(){
		super();
	}

	public void checkExpectedResult_UI(org.openqa.selenium.WebDriver driver,java.lang.String para1){
		super.checkExpectedResult_UI(driver, para1);
	}
	
	public boolean setSystemConfig_UI(WebDriver driver, boolean changed){
		if(!changed){
			Navigator.navigate(driver,Navigator.xmlWebElmtMgr.getNavigationPathList("ManageCenter","SystemConf"),this);		
			By by = By.id("security_allowedFiles");
			String str = ",.AU,.CRS,.CST,.DES";

			
			WebDriverUtils.append_textbox(driver, by, str);
			
			by = By.name("SAVE");
			WebDriverUtils.clickButton(driver, by);
			changed = true;
		}
		return changed;
	}
	
	public void runImportAICC(WebDriver driver){

//		boolean ESigEnabled = ESignature.isESignatureEnabled(driver, this, "ImportCourse");
		
		/*boolean changed = false;
		changed = this.setSystemConfig_UI(driver, changed);*/
		
		Navigator.navigate(driver,Navigator.xmlWebElmtMgr.getNavigationPathList("ManageCenter","ImportAICC"),this);
		
		// Set course structure files
		this.setCourseStructure_UI(driver, this.getAUFile(), 
				this.getCRSFile(), this.getCSTFile(), this.getDESFile());
		this.setEncoding_UI(driver, this.getEncoding());
		
		By by = By.name("importButton");
		WebDriverUtils.clickButton(driver, by);
		
		this.setCourseID_UI(driver, this.getModuleID());
		
		if (!this.getRevision().trim().isEmpty()){
			by = By.id("updateChkbox");
			WebDriverUtils.clickButton(driver, by);
			
			this.setVisibility_UI(driver, this.getVisibility());
			this.setCatalog_UI(driver, this.getCatalog(), true);
			by = By.name("updateExistingBundleRevision");
			WebDriverUtils.clickButton(driver, by);
		}else{
			by = By.name("importAsNewModuleButton");
			WebDriverUtils.clickButton(driver, by);
		}
	
		this.setCourseTitle_UI(driver, this.getModuleTitle());
		
		by = By.name("importButton");
		WebDriverUtils.clickButton(driver, by);
		
	
		
	/*	if(ESigEnabled){
	    	JUnitAssert.assertTrue(WebDriverUtils.isElementPresent(By.id("ESIGNATURE-username")),"No esign window");
	    	UIFunctionUtils.fillESignature(driver, this.getUID(), this.getPWD());
	    }*/
		
		
		this.setRevision_UI(driver, this.getRevision());
	}

	
	

	public void setRevision_UI(WebDriver driver, String revision){
		By by = null;
		if(revision.equals("")){ //effective by default
			
			by = By.name("buttonInput");
			WebDriverUtils.clickButton(driver, by);
			
			WebDriverUtils.switchToPopUpWin(driver);
			Navigator.explicitWait(1000);
			WebDriverUtils.switchToFrame(driver, "BSCAT_LEFT");
			Navigator.explicitWait(1000);
			
			by = By.linkText("1.9 Revisions");
			WebDriverUtils.clickLink(driver, by);
			
			WebDriverUtils.switchToFrame(driver, "BSCAT_MAIN");
			Navigator.explicitWait(1000);
			by = By.xpath("//tr[@class='row1']/td[1]/div[1]/ul");
			
			Navigator.explicitWait(3000);
			String value_DropDown = WebDriverUtils.getText(driver, by);
			String expectedValue = "";
			JUnitAssert.assertEquals(expectedValue, value_DropDown);
		}else{
	
			if(revision.equalsIgnoreCase("Preview")){
				
				by = By.id("PREVIEW");	
				Navigator.explicitWait(1000);
//				Navigator.waitForAjaxElementLoad(driver, by);
				WebDriverUtils.clickButton(driver, by);
				
				by = By.name("ok");			
				Navigator.explicitWait(1000);
//				Navigator.waitForAjaxElementLoad(driver, by);
				WebDriverUtils.clickButton(driver, by);
				
				by = By.name("buttonInput");
				Navigator.explicitWait(1000);
//				Navigator.waitForAjaxElementLoad(driver, by);
				WebDriverUtils.clickButton(driver, by);
				
				WebDriverUtils.switchToPopUpWin(driver);
				Navigator.explicitWait(1000);
				WebDriverUtils.switchToFrame(driver, "BSCAT_LEFT");
				Navigator.explicitWait(1000);
				by = By.linkText("1.9 Revisions");
				WebDriverUtils.clickLink(driver, by);
				
				WebDriverUtils.switchToFrame(driver, "BSCAT_MAIN");
				Navigator.explicitWait(1000);
				
				by = By.xpath("//tr[@class='row1']/td/div/button");
				WebDriverUtils.mouseOver(driver, by);
				Navigator.explicitWait(1000); 
				
				by = By.xpath("//tr[@class='row1']/td[1]/div[1]/ul/li[1]/a");
				Navigator.explicitWait(1000); 
//				Navigator.waitForAjaxElementLoad(driver, by);
				String value_tmp = WebDriverUtils.getText(driver, by).toLowerCase();
				
		    	JUnitAssert.assertTrue(value_tmp.contains("effective"),"No effective status"); //Approve and effective
		    	
		    	by = By.xpath("//tr[@class='row1']/td[1]/div[1]/ul/li[2]/a");
		    	Navigator.explicitWait(1000); 
//		    	Navigator.waitForAjaxElementLoad(driver, by);
				
		    	value_tmp = WebDriverUtils.getText(driver, by).toLowerCase();
		    	JUnitAssert.assertTrue(value_tmp.contains("approved"),"No approved status"); //Approve and effective
				
			}else if(revision.equalsIgnoreCase("Approve")){
				
				by = By.id("PREVIEW");
				Navigator.explicitWait(1000); 
//				Navigator.waitForAjaxElementLoad(driver, by);
				WebDriverUtils.clickButton(driver, by);
				
				by = By.name("ok");
				Navigator.explicitWait(1000); 
//				Navigator.waitForAjaxElementLoad(driver, by);
				WebDriverUtils.clickButton(driver, by);
				
				by =  By.name("buttonInput");
				Navigator.explicitWait(1000); 
//				Navigator.waitForAjaxElementLoad(driver, by);
				WebDriverUtils.clickButton(driver, by);
				
				WebDriverUtils.switchToPopUpWin(driver);
				Navigator.explicitWait(1000);
				WebDriverUtils.switchToFrame(driver, "BSCAT_LEFT");
				Navigator.explicitWait(1000);
				by = By.linkText("1.9 Revisions");
				WebDriverUtils.clickLink(driver, by);

				WebDriverUtils.switchToFrame(driver, "BSCAT_MAIN");
				Navigator.explicitWait(1000);
				by = By.xpath("//tr[@class='row1']/td/div/button");
				WebDriverUtils.mouseOver(driver, by);
				Navigator.explicitWait(1000);
				by = By.xpath("//tr[@class='row1']/td/div/ul/li/a[contains(text(),'Approve')]");
				WebDriverUtils.clickLink(driver, by);
				
				//do it here
				by = By.xpath("//button[descendant::span[contains(text(),'"+Labels.Mark_Approved+"')]]");
				Navigator.explicitWait(1000); 
				WebDriverUtils.clickButton(driver, by);
				
				by = By.xpath("//tr[@class='row1']/td/div/button");			    
				WebDriverUtils.mouseOver(driver, by);
				Navigator.explicitWait(1000);
				
				by = By.xpath("//tr[@class='row1']/td[1]/div[1]/ul[1]");
				Navigator.explicitWait(1000); 
//				Navigator.waitForAjaxElementLoad(driver, by);
				String value_tmp = WebDriverUtils.getText(driver, by).toLowerCase();
		    	JUnitAssert.assertTrue(value_tmp.contains("effective"),"No effective status"); //only effective (no preview)

		    	Navigator.explicitWait(1000);
		    	by = By.xpath("//tr[@class='row1']/td[1]/div[1]/ul[2]");
		    	boolean exist = WebDriverUtils.isElementPresent(by);
		    	JUnitAssert.assertTrue(!exist,"Wrong status: only effective (no preview) status");   	//only effective (no preview)
		    	
			}else{
				String revision_tmp = revision.toLowerCase();
				
				if(revision_tmp.contains("effective")){					
					if(revision_tmp.contains("in progress")){
						by = By.id("NOT_ATTEMPTED");
						WebDriverUtils.clickButton(driver, by);
						
					}else if(revision_tmp.contains("complete")){
						by = By.id("NOT_COMPLETED_RE_ENROLL_NONE");
						WebDriverUtils.clickButton(driver, by);
						
						if(revision_tmp.contains("stand-alone")){

							by = By.id("NOT_COMPLETED_RE_ENROLL_COMPLETED_IGNORE_PROGRAMS");
							Navigator.explicitWait(1000); 
//							Navigator.waitForAjaxElementLoad(driver, by);
							WebDriverUtils.check_checkbox(driver, by);
														
							if(revision_tmp.contains("mandatory")){
								by = By.id("NOT_COMPLETED_RE_ENROLL_COMPLETED_INCLUDE_PROGRAMS");
								WebDriverUtils.check_checkbox(driver, by);
							}
						}
					}else if(revision_tmp.contains("all")){
						by = By.id("ALL");
						WebDriverUtils.clickButton(driver, by);
					}
					
					by = By.name("ok");
					Navigator.explicitWait(1000); 
//					Navigator.waitForAjaxElementLoad(driver, by);
					WebDriverUtils.clickButton(driver, by);
					
					by = By.name("buttonInput");
					Navigator.explicitWait(1000);
//					Navigator.waitForAjaxElementLoad(driver, by);
					WebDriverUtils.clickButton(driver, by);
					
					WebDriverUtils.switchToPopUpWin(driver);
					Navigator.explicitWait(1000);
					WebDriverUtils.switchToFrame(driver, "BSCAT_LEFT");
					Navigator.explicitWait(1000);
					
					by = By.linkText("1.9 Revisions");
					WebDriverUtils.clickLink(driver, by);
					WebDriverUtils.switchToFrame(driver, "BSCAT_MAIN");
					Navigator.explicitWait(1000);
					
					by = By.xpath("//tr[@class='row1']/td[1]/div[1]/ul");
					String value_DropDown = WebDriverUtils.getText(driver, by);
					String expectedValue = "";
					JUnitAssert.assertEquals(expectedValue, value_DropDown);
				}
			}
		}
		
	}

	public void setEncoding_UI(WebDriver driver, String str){
		if(!str.equals("")){
			By by = By.name("encoding");
			WebDriverUtils.select_selector(driver, by, str);
		}
	}
	
	public void setCourseTitle_UI(WebDriver driver, String str){
		if(!str.equals("")){
			By by = By.id("addLangSelector");
			WebDriverUtils.select_selector(driver, by, "English");
			By bytext = By.id("moduleTitle");
			WebDriverUtils.fillin_textbox(driver, bytext, str);
		}		
	}

	public void setCourseID_UI(WebDriver driver, String str){
		if(!str.equals("")){
			
			By by = By.id("id");
			WebDriverUtils.fillin_textbox(driver, by, str);
		}
	}

	public void setVisibility_UI(WebDriver driver, String str){
		if(!str.equals("")){
			By by = By.id("showInCatalog");
			WebDriverUtils.check_checkbox(driver, by);
		}
	}

	public void setCourseStructure_UI(WebDriver driver, String file_AU, String file_CRS, String file_CST, String file_DES){
		if(!file_AU.equals("") && !file_CRS.equals("") 
				&& !file_CST.equals("") && !file_DES.equals("")){
			WebDriverUtils.importFile_ID(driver, "assignableUnit", file_AU);
			WebDriverUtils.importFile_ID(driver, "course", file_CRS);
			WebDriverUtils.importFile_ID(driver, "courseStructure", file_CST);
			WebDriverUtils.importFile_ID(driver, "descriptor", file_DES);	
		}
	}

	@Override
	public boolean equals(TestObject obj){
		if(obj instanceof AICC && ((AICC)obj).toString().equals(this.toString())){
			return true;
		}else{
			return false;
		}
	}

/*	@Override
	public String toString() {
		return new StringBuilder().
				append(this.getClass().getName()).
				append("_").
				append(this.getFuncType()).
				append("_").
				append(this.getModuleID()).
				append("_").
				append(this.getRevision()).
				toString();
	}*/

	public String getAUFile() {
		return AUFile;
	}

	public void setAUFile(String aUFile) {
		AUFile = aUFile;
	}

	public String getCSTFile() {
		return CSTFile;
	}

	public void setCSTFile(String cSTFile) {
		CSTFile = cSTFile;
	}

	public String getDESFile() {
		return DESFile;
	}

	public void setDESFile(String dESFile) {
		DESFile = dESFile;
	}

	public String getEncoding() {
		return Encoding;
	}

	public void setEncoding(String encoding) {
		Encoding = encoding;
	}
		
	public String getRevision() {
		return Revision;
	}

	public void setRevision(String revision) {
		Revision = revision;
	}

	/**Empty body since it's a test suite
	 * 
	 * @param driver
	 */
	public void runCheckRevision(WebDriver driver){
		
	}
}
