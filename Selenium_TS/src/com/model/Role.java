package com.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.abstractclasses.TestObject;
import com.config.Labels;
import com.utils.WebDriverUtils;
import com.view.Navigator;

/**
 * 
 * @author martin.wang
 *
 */
public class Role extends TestObject {
	private String Role = "", FeatureName = "", Permission = "";
	
	public Role(){
		super();
	}
	
	public String getFeatureName() {
		return FeatureName;
	}

	public void setFeatureName(String featureName) {
		FeatureName = featureName;
	}
	
	public String getPermission() {
		return Permission;
	}

	public void setPermission(String permission) {
		Permission = permission;
	}
	
	
	
	public void runRoleAccessControl(WebDriver driver){
		//Navigator.navigate(driver, Navigator.URL.RoleAccessControl);
		Navigator.navigate(driver,Navigator.xmlWebElmtMgr.getNavigationPathList("ManageCenter","RoleAccess"),this);
		By by = By.id("ROL");
		String str = this.getRole();
		WebDriverUtils.select_selector(driver, by, str);
		
		by = By.name("DELETE");
		WebDriverUtils.clickButton(driver, by);

		
	    // Learn Features
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Learn Menu')]]/td[3]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Current Learning Modules')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'My Enrollment Requests')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Records/Transcript')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Printer-Friendly Exam Transcripts')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Certifications')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Knowledge Center')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Career Development Center')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Competencies')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Job Profiles')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Performance Appraisal')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Overall Status')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Skills')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Training Plan')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Training Gap Analysis')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Accounts')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Payment History')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Personal Calendar')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Personal Notebook')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Peer Comments')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Performance Appraisal')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Learning Path')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'My Files')]]/td[4]/input")).click();
	    // Explore Features
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Catalog Menu')]]/td[3]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Catalog Browser')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Allow Session Enrollment')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'News Search')]]/td[3]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Skills Assessments')]]/td[3]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Certification Programs')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Know Your Colleagues')]]/td[3]/input")).click();
	    // Communicate Features
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Communicate Menu')]]/td[3]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Forum')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Mail')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Message Board')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Chat')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Mass E-mail Sender')]]/td[4]/input")).click();
	    // Personalization Features
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Preferences Menu')]]/td[3]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'User Preferences')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Address Change')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Profile Summary')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Employment Information')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Contact Details')]]/td[4]/input")).click();
	    driver.findElement(By.id("feature753_2")).click(); //for resume
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Education')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Work History')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Language Skills')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Relocation Interests')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Password Change')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'My Orgs')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Terms of Use')]]/td[4]/input")).click();
	    // Other Menus
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'News Menu')]]/td[3]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Wiki')]]/td[4]/input")).click();
	    // Review Features
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Review Menu')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Organization Review')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Instructor')]]/td[3]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Detailed Review by Instructor')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Report Manager')]]/td[3]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Dashboard')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Direct Appraiser Review')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Appraisal Search')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Group Review')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Task Approval')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Enrollment Approval')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Ext. Training Approval')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Supervisor Assessment')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Enrollment Wizard')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Change Enrollment Status')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Review Terms of Use')]]/td[4]/input")).click();
	    // Review Submenu Features
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Learning Center Summary')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Review Records/Transcript')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Review Skills')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Review Certifications')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Review Accounts')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Review Enrollment Requests')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//thead[descendant::tr/th[contains(text(),'Review Submenu Features')]]/following-sibling::tbody/tr[descendant::td[contains(text(),'Profile Summary')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//thead[descendant::tr/th[contains(text(),'Review Submenu Features')]]/following-sibling::tbody/tr[descendant::td[contains(text(),'Employment Information')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//thead[descendant::tr/th[contains(text(),'Review Submenu Features')]]/following-sibling::tbody/tr[descendant::td[contains(text(),'Contact Details')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//thead[descendant::tr/th[contains(text(),'Review Submenu Features')]]/following-sibling::tbody/tr[descendant::td[contains(text(),'Education')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//thead[descendant::tr/th[contains(text(),'Review Submenu Features')]]/following-sibling::tbody/tr[descendant::td[contains(text(),'Work History')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//thead[descendant::tr/th[contains(text(),'Review Submenu Features')]]/following-sibling::tbody/tr[descendant::td[contains(text(),'Language Skills')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//thead[descendant::tr/th[contains(text(),'Review Submenu Features')]]/following-sibling::tbody/tr[descendant::td[contains(text(),'Relocation Interests')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//thead[descendant::tr/th[contains(text(),'Review Submenu Features')]]/following-sibling::tbody/tr[descendant::td[contains(text(),'Assign Module')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//thead[descendant::tr/th[contains(text(),'Review Submenu Features')]]/following-sibling::tbody/tr[descendant::td[contains(text(),'Training Plan')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//thead[descendant::tr/th[contains(text(),'Review Submenu Features')]]/following-sibling::tbody/tr[descendant::td[contains(text(),'Competency Assessments')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//thead[descendant::tr/th[contains(text(),'Review Submenu Features')]]/following-sibling::tbody/tr[descendant::td[contains(text(),'Career Development Center')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//thead[descendant::tr/th[contains(text(),'Review Submenu Features')]]/following-sibling::tbody/tr[descendant::td[contains(text(),'Career Center Summary')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//thead[descendant::tr/th[contains(text(),'Review Submenu Features')]]/following-sibling::tbody/tr[descendant::td[contains(text(),'Competencies')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//thead[descendant::tr/th[contains(text(),'Review Submenu Features')]]/following-sibling::tbody/tr[descendant::td[contains(text(),'Job Profiles')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//thead[descendant::tr/th[contains(text(),'Review Submenu Features')]]/following-sibling::tbody/tr[descendant::td[contains(text(),'Performance Appraisal')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//thead[descendant::tr/th[contains(text(),'Review Submenu Features')]]/following-sibling::tbody/tr[descendant::td[contains(text(),'Review My Files')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//thead[descendant::tr/th[contains(text(),'Review Submenu Features')]]/following-sibling::tbody/tr[descendant::td[contains(text(),'Performance Appraisal')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//thead[descendant::tr/th[contains(text(),'Review Submenu Features')]]/following-sibling::tbody/tr[descendant::td[contains(text(),'Training Gap Analysis')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//thead[descendant::tr/th[contains(text(),'Review Submenu Features')]]/following-sibling::tbody/tr[descendant::td[contains(text(),'Learning Path')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//thead[descendant::tr/th[contains(text(),'Review Submenu Features')]]/following-sibling::tbody/tr[descendant::td[contains(text(),'SCORM Global Objectives')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//thead[descendant::tr/th[contains(text(),'Review Submenu Features')]]/following-sibling::tbody/tr[descendant::td[contains(text(),'Learning Group')]]/td[4]/input")).click();
	    // Manage Features
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Manage Menu')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'News Manager')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Repository Manager')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'mEKP Administration')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Appraisal Manager')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Terms of Use Manager')]]/td[4]/input")).click();
	    // Compliance Analytics
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Compliance Analytics')]]/td[4]/input")).click();
	    // Catalog Manager Features
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Catalog Manager')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Catalog Editor - Module Management')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Catalog Editor - Session Management')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Catalog Configuration')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Catalog Structure')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Cost Accounting')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Facility Maintenance')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'E-mail Template Editor')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Enrollment Policy Editor')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Courseware Editor')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'View Course Coupon')]]/td[3]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Edit Course Coupon')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Auto/Group Enroll')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Auto-Enroll Console')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Catalog Assignment CSV Loader')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Course CSV Loader')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Program CSV Loader')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'External Training CSV Loader')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Content Package, AICC Course Structure, Resource, Web Catalogs and PENS Import')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Integrated Instructor Calendar')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Training Records CSV Loader')]]/td[4]/input")).click();
	    // Exam Manager Features
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Exam Manager')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Exam Utilities')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Exam Template Editor')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Question Editor')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Exam Configuration')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Data Loader')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Exam Review')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Allow the user to modify the exam after the end date')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Exam Generator')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Exam Participants Review')]]/td[4]/input")).click();
	    // User Manager Features
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'User Manager')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'User Editor')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Role Permissions')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'User Attributes Configuration')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'User Data Loader')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'User Profile Data Loader')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'User Groups')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'User Group Data Loader')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Bulk Role Update')]]/td[4]/input")).click();
	    // Community Manager Features
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Community Manager')]]/td[3]/input")).click();
	    driver.findElement(By.xpath("//thead[descendant::tr/th[contains(text(),'Community Manager Features')]]/following-sibling::tbody/tr[descendant::td[contains(text(),'Forum')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//thead[descendant::tr/th[contains(text(),'Community Manager Features')]]/following-sibling::tbody/tr[descendant::td[contains(text(),'Chat')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//thead[descendant::tr/th[contains(text(),'Community Manager Features')]]/following-sibling::tbody/tr[descendant::td[contains(text(),'Message Board')]]/td[4]/input")).click();
	    // Report Categories
	    driver.findElement(By.xpath("//thead[descendant::tr/th[contains(text(),'Report Categories')]]/following-sibling::tbody/tr[descendant::td[contains(text(),'Report Manager')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Report Wizard')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Organization Reports')]]/td[3]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Exam/Survey Reports')]]/td[3]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'System Reports')]]/td[3]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Course Reports')]]/td[3]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Compliance Reports')]]/td[3]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Certification Reports')]]/td[3]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Published Customizer Reports')]]/td[3]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Report Scheduler')]]/td[4]/input")).click();
	    // Competency Manager Features
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Competency Manager')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Competency Assessment Templates')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Competency Library')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Competency Group Editor')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Profile Auto-Assign Console')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//thead[descendant::tr/th[contains(text(),'Competency Manager Features')]]/following-sibling::tbody/tr[descendant::td[contains(text(),'Skills Assessment')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Competency Data Loader')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Competency Models')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Proficiency Levels')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//thead[descendant::tr/th[contains(text(),'Competency Manager Features')]]/following-sibling::tbody/tr[descendant::td[contains(text(),'Job Profiles')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Active Assessments')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'User Search')]]/td[3]/input")).click();
	    // System Administration
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'System Administration')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Page Statistics')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Transaction Statistics')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Connection Statistics')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Cache Statistics')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'User Sessions')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Access Violations')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Screen Layout Manager')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'System Configuration')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Broadcast Messenger')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Database Object Statistics')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Switch User')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Home Page Manager')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Content Server Configuration')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Login Reminder')]]/td[4]/input")).click();
	    // Payment Manager
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Payment Manager')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//thead[descendant::tr/th[contains(text(),'Payment Manager')]]/following-sibling::tbody/tr[descendant::td[contains(text(),'Payment History')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Token Packages')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Organization Token Accounts')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Token Account Data Loader')]]/td[4]/input")).click();
	    // Certification Manager
	    driver.findElement(By.xpath("//thead[descendant::tr/th[contains(text(),'Certification Manager')]]/following-sibling::tbody/tr[descendant::td[contains(text(),'Certifications')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Certification Awarding CSV Loader')]]/td[4]/input")).click();
	    // Goals
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Goal Programs')]]/td[4]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Goal Templates')]]/td[4]/input")).click();
	    // Data Access Control
	    by = By.xpath("//tr[descendant::td/label[contains(text(),'Highest Organization Level Visible')]]/td[2]/select");
	    str = "Root";
	    WebDriverUtils.select_selector(driver, by, str);
	    
	    
	    // Home Page Templates
	    by = By.xpath("//tr[descendant::td/label[contains(text(),'Home Page Template for This Role')]]/td[2]/select");
	    str = "Default";
	    WebDriverUtils.select_selector(driver, by, str);
	    // Title and ID Format
	    by = By.xpath("//tr[descendant::td/label[contains(text(),'Title and ID Format')]]/td[2]/select");
	    str = "Title (ID)";
	    WebDriverUtils.select_selector(driver, by, str);
	    // Role General Permissions
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Allow Look and Feel Change')]]/td[3]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Allow Admin Online Help')]]/td[3]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Allow Organization Maintenance')]]/td[3]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Allow Global Upload Maintenance')]]/td[3]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Allow Course Deletes')]]/td[3]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Allow User Deletes')]]/td[3]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Allow User Creation')]]/td[3]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Allow Attachment in New Mail Form')]]/td[3]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Allow Enrollment Override')]]/td[3]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Allow Question Creation')]]/td[3]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Allow Question Review')]]/td[3]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Allow Question Approval')]]/td[3]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Allow Question Open for Editing')]]/td[3]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Allow Exam Creation')]]/td[3]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Allow Exam Generation')]]/td[3]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Allow Exam Grading')]]/td[3]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Allow Exam Instance Manager')]]/td[3]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Display Exam Password')]]/td[3]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Is External Question Approver')]]/td[2]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Allow Question Approval Override')]]/td[3]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Allow Personal Reminders')]]/td[3]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Allow Forum Moderation')]]/td[3]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Allow Global Approval')]]/td[3]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Allow Exam Remedial Training Comments')]]/td[3]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Allow Bulk Session Status Update')]]/td[3]/input")).click();
	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Show Tokens Tab')]]/td[3]/input")).click();


	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Show Only Top Level Learning Objects in Enrolled Learning Modules')]]/td[3]/input")).click();

	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Allow Token Manual Adjustment')]]/td[3]/input")).click();

	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Allow User Editor Group View')]]/td[3]/input")).click();

	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Is Organizational External Training Approver')]]/td[3]/input")).click();

	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Allow User Appraisal Administration')]]/td[3]/input")).click();

	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Allow Review Employee All User Appraisals')]]/td[3]/input")).click();

	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Show Biographies and Activities of Other Users in the Same Learning Group')]]/td[3]/input")).click();

	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Allow Assessment Deployment')]]/td[3]/input")).click();

	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Allow Unrestricted Delegation')]]/td[3]/input")).click();

	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Allow 9-Box Report Deployment')]]/td[3]/input")).click();

	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Allow Full Organization View of Participants')]]/td[3]/input")).click();

	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Allow Skillsoft (OLSA) Search')]]/td[3]/input")).click();

	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Allow Global Homework Files Access')]]/td[3]/input")).click();

	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'OWASP Restrictions Override')]]/td[3]/input")).click();

	    driver.findElement(By.xpath("//tr[descendant::td[contains(text(),'Display Details, Progress, and Course Interactions when Reviewing Learner Transcript Detail')]]/td[3]/input")).click();

	    by = By.xpath("//tr[descendant::td/label[contains(text(),'Privilege Level')]]/td[2]/select");
		
	    str = "0";
	    WebDriverUtils.select_selector(driver, by, str);

	    by = By.name("SUB");
	    WebDriverUtils.clickButton(driver, by);
	}
	
	/**
	 * 
	 * In role access control, change to permission stated in EKPTestData
	 * Need to indicate Role, FeatureName and Permission to change
	 */
	public void runSetPermission(WebDriver driver) {
		//Go to Role Access Control page
		Navigator.navigate(driver,Navigator.xmlWebElmtMgr.getNavigationPathList("ManageCenter","RoleAccess"),this);
		
		//Choose which Role to Review permission
		By by = By.id("ROL");
		String str = this.getRole();
		Navigator.waitForAjax(driver, by);
		WebDriverUtils.select_selector(driver, by, str);
		
		by = By.xpath("//input[@value='"+Labels.Btn_List_Permission+"']");
		Navigator.waitForAjax(driver, by);
		WebDriverUtils.clickButton(driver, by);
		
		//Change the permission of the Feature as in excel file
		String permission = this.getPermission();
		by = By.xpath("//tr[child::td[text()='"+this.getFeatureName()+"']]/td[@class='"+permission+"']/input");
		Navigator.waitForAjax(driver, by);
		WebDriverUtils.checkRadio(driver, by);
		
		//Save change
		by = By.xpath("//input[@value='"+Labels.Btn_Update_Acess_Control_Setting+"']");
		Navigator.waitForAjax(driver, by);
		WebDriverUtils.clickButton(driver, by);
	}
	
/*	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return new StringBuilder().
				append(this.getClass().getName()).
				append("_").
				append(this.getFuncType()).
				append("_").
				append(this.getRole()).
				toString();
	}*/

	@Override
	public boolean equals(TestObject obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void checkExpectedResult_UI(WebDriver driver, String expectedResult) {
		// TODO Auto-generated method stub
		super.checkExpectedResult_UI(driver, expectedResult);
	}

	public String getRole() {
		return Role;
	}

	public void setRole(String role) {
		Role = role;
	}

}
