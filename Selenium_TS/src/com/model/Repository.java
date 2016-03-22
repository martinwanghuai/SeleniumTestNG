package com.model;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.junit.JUnitAssert;
import com.utils.CriteriaParser;
import com.utils.WebDriverUtils;
import com.view.FunctionUI;
import com.view.Navigator;

/**
 * 
 * @author martin.wang
 *
 */
public class Repository extends com.abstractclasses.TestObject {
	private String ParentFolder = "", FolderName = "", Files = "",
			Permission = "", FolderDesc = "";

	public boolean equals(com.abstractclasses.TestObject para0) {
		boolean result = false;
		return result;
	}

	public Repository() {
		super();
	}

	public String getFolderDesc() {
		return FolderDesc;
	}

	public void setFolderDesc(String folderDesc) {
		FolderDesc = folderDesc;
	}

	public String getParentFolder() {
		return ParentFolder;
	}

	public String getFolderName() {
		return FolderName;
	}

	public String getFiles() {
		return Files;
	}

	public String getPermission() {
		return Permission;
	}

	public void setParentFolder(String parentfolder) {
		ParentFolder = parentfolder;
	}

	public void setFolderName(String foldername) {
		FolderName = foldername;
	}

	public void setFiles(String files) {
		Files = files;
	}

	public void setPermission(String permission) {
		Permission = permission;
	}

	public void setParentFolder_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
			String[] parents = str.split("/");
			By by = null;
			for(String parent: parents){
				by = By.linkText(parent);
				WebDriverUtils.clickLink(driver, by);
			}
		}
	}

	public void setFolderDesc_UI(WebDriver driver, String str){
		if(!str.equals("")){
			By by = By.id("DESCRIPTION");
			WebDriverUtils.fillin_textbox(driver, by, str);
		}
	}
	
	public void setFolderName_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
			By by = By.name("CREATEBTN");
			WebDriverUtils.clickButton(driver, by);
			
			by = By.id("FOLDERNAME");
			WebDriverUtils.fillin_textbox(driver, by, str);
		}
	}
	
	public void getFolder_UI(WebDriver driver, String str){
		if(!str.equals("")){
			By by = By.linkText(str);
			WebDriverUtils.clickLink(driver, by);
		}
	}
	
	/**Upload file to a folder 
	 * 
	 * @param driver
	 * @param str
	 */
	public void setFiles_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
		    // Upload files
			String[] files = str.split(";");
			for(String file: files){
				By by = By.name("UPLOADBTN");
				WebDriverUtils.clickButton(driver, by);
				
				by = By.id("USERFILE");
				String path = System.getProperty("user.dir") + "\\resource\\" + file; 
//				String path = "E:\\eclipse\\workspace\\Selenium_TS101\\resource\\" + file;
				WebDriverUtils.uploadFile(driver, by, path);;
				
				by = By.id("DESCRIPTION");
				WebDriverUtils.fillin_textbox(driver, by, this.getFolderDesc());
				
				by = By.name("uploadButton");
			    WebDriverUtils.clickButton(driver, by);
			    
			    by = By.name("okButton");
			    WebDriverUtils.clickButton(driver, by);
			    
			 /*   by = By.partialLinkText("Up One Level");
			    WebDriverUtils.clickLink(driver, by);
			    
			    by = By.xpath("//img[@alt='Select'])[2]");
			    WebDriverUtils.clickButton(driver, by);*/
			}
		}
	}
	
	/**Select a file from a folder
	 * 
	 * @param driver
	 * @param str
	 */
	public void getFiles_UI(WebDriver driver, String str){
		if(!str.equals("")){
			String[] files = str.split(";");
			By by = null;
			
			for(String file: files){
				by = By.xpath("//tr[descendant::td/a[contains(text(),'"+file+"')]]/td/input[@type='CHECKBOX']");
				
				int size = WebDriverUtils.getHowManyByPresntInPage(driver,by,false);
				if(size == 0){
					//upload file first if not exist
					this.setFiles_UI(driver, str);	
				}
				
				//select file if exist
				WebDriverUtils.check_checkbox(driver, by);
			}
			
			by = By.xpath("//form[@name='SELECT' and @method='POST']/input[@value='Select']");
			WebDriverUtils.clickButton(driver, by);
		}
	}

	public void setPermission_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
		}
	}

	public void runCreate(WebDriver driver) {
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
				"ManageCenter", "RepositoryManager"));
		WebDriverUtils.switchToPopUpWin(driver);
		WebDriverUtils.switchToFrame(driver, "FR_MAIN");
		
		//Expand parent folder
		this.setParentFolder_UI(driver, this.getParentFolder());
		
		//Setup folder
		this.setFolderName_UI(driver, this.getFolderName());
		this.setFolderDesc_UI(driver, this.getFolderDesc());
		
		By by = By.name("CREATE");
		WebDriverUtils.clickButton(driver, by);

		by = By.name("backButton");
		WebDriverUtils.clickButton(driver, by);
	    
		//Go to the folder
		by = By.linkText(this.getFolderName());
		WebDriverUtils.clickLink(driver, by);
		
		//Upload files to the folder
		this.setFiles_UI(driver, this.getFiles());
		
		//Setup permission
		this.setPermission_UI(driver, this.getPermission());
		
		WebDriverUtils.closeAllPopUpWins(driver);
	}
	
	/**Martin: expand to specific folder to select files 
	 * 
	 * @param driver
	 */
	public void selectFile(WebDriver driver){
		this.setParentFolder_UI(driver, this.getParentFolder());
		this.getFolder_UI(driver, this.getFolderName());
		this.getFiles_UI(driver, this.getFiles());
	}
	
	public void runCheckVisibility(WebDriver driver){
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
				"ManageCenter", "RepositoryManager"));
		WebDriverUtils.switchToPopUpWin(driver);

		//1.1 Check visibility and editability
		WebDriverUtils.switchToFrame(driver, "FR_LEFT");
		Navigator.explicitWait(1000);
		//HashMap<String, ArrayList<String>> criteria_folders = UIFunctionUtils.parseParticipants(this.getExpectedResult());
		
		HashMap<String, ArrayList<String>> criteria_folders =CriteriaParser.parseKeyValueList(":", ";", this.getExpectedResult()); 
		Iterator<String> criteria = criteria_folders.keySet().iterator();
		By by = null;
		boolean noParent = false;
		
		while(criteria.hasNext()){
			String criterion = criteria.next();
			ArrayList<String> folders = criteria_folders.get(criterion);
			for(String folder: folders){
					int index = folder.lastIndexOf("/");
					String folderName = ""; 
					if(index > 0){
						noParent = false;
						String parentFolder = folder.substring(0, index);
						folderName = folder.substring(index+1);
						
						//1.2 Expand parent folder 
						if(!parentFolder.equals("") && criterion.equalsIgnoreCase("visible")){
							//1.2.1 click left panel to expand Tier-1 parent folder
							WebDriverUtils.switchToFrame(driver, "FR_LEFT");
							Navigator.explicitWait(1000);
							
							String[] parents = parentFolder.split("/");
							by = By.linkText(parents[0]);
							WebDriverUtils.clickLink(driver, by);
							
							//1.2.2 click central panel to fully expand parent folder
							if(parents.length > 1){
								WebDriverUtils.switchToFrame(driver, "FR_MAIN");
								Navigator.explicitWait(1000);
								this.setParentFolder_UI(driver, this.getParentFolder());
							}
						}
						WebDriverUtils.switchToFrame(driver, "FR_MAIN");
						Navigator.explicitWait(1000);
					}else{
						//1.3 No parent folder
						noParent = true;
						folderName = folder;
						WebDriverUtils.switchToFrame(driver, "FR_LEFT");
						Navigator.explicitWait(1000);
					}
					
					int size = -1;
					switch(criterion.toLowerCase()){
					case "visible":
						by = By.linkText(folderName);	
						size = WebDriverUtils.getHowManyByPresntInPage(driver,by,true);
						JUnitAssert.assertTrue(size > 0, "Folder is invisible:" + folderName);
						break;
						
					case "invisible":
						by = By.linkText(folderName);
						size = WebDriverUtils.getHowManyByPresntInPage(driver,by,false);
						JUnitAssert.assertTrue(size == 0, "Folder is visible:" + folderName);
						break;
						
					case "editable":
						if(noParent){
							WebDriverUtils.switchToFrame(driver, "FR_LEFT");
							Navigator.explicitWait(1000);
							by = By.linkText(folderName);
							WebDriverUtils.clickLink(driver, by);
							WebDriverUtils.switchToFrame(driver, "FR_MAIN");
							Navigator.explicitWait(1000);
							by = By.partialLinkText("Up One Level");
							WebDriverUtils.clickLink(driver, by);	
						}
						
						by = By.xpath("//tr[descendant::td/a[text()='"+folderName+"']]/td/a[text()='Properties']");
						size = WebDriverUtils.getHowManyByPresntInPage(driver,by,true);
						JUnitAssert.assertTrue(size > 0 , "Folder is uneditable:" + folderName);
						break;
						
					case "uneditable":
						if(noParent){
							WebDriverUtils.switchToFrame(driver, "FR_LEFT");
							Navigator.explicitWait(1000);
							by = By.linkText(folderName);
							WebDriverUtils.clickLink(driver, by);
							WebDriverUtils.switchToFrame(driver, "FR_MAIN");
							Navigator.explicitWait(1000);
							by = By.partialLinkText("Up One Level");
							WebDriverUtils.clickLink(driver, by);	
						}
						
						by = By.xpath("//tr[descendant::td/a[text()='"+folderName+"']]/td/a[text()='Properties']");
						size = WebDriverUtils.getHowManyByPresntInPage(driver,by,false);
						JUnitAssert.assertTrue(size == 0 , "Folder is editable:" + folderName);
						break;
					}		
			}
		}
		
	}
}