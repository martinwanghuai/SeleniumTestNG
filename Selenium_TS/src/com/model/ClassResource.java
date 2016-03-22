package com.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.abstractclasses.TestObject;
import com.utils.WebDriverUtils;
import com.view.Navigator;

/**
 * 
 * @author martin.wang
 *
 */
public class ClassResource extends TestObject {
	private String TrainingCenterName="",	Desc="",
			Venue="",	Contact="",	Directions="",
			Map="",	FacilityCode="",	FacilityName ="",
			Status="",	isExpandable="",	hasPC="",
			minSeats ="",	maxSeats ="",
			numOfVGAProjectors ="",	numOfPCs ="",
			numOfWhiteBoards ="",	numOfOverheadProjectors="";
	
/*	public String toString(){
		return new StringBuilder().append(this.getClass().getName()).
				append("_").
				append(this.getFuncType()).
				append("_").
				append(this.getTrainingCenterName()).
				append("_").
				append(this.getFacilityCode()).
				toString();
	}*/
	
	public void setFacilityCode_UI(WebDriver driver, String str){
		if(!str.equalsIgnoreCase("")){
			By by =By.id("FACILITYCODE");
			WebDriverUtils.fillin_textbox(driver, by, str);
		}
	}
	
	public void setDesc_UI(WebDriver driver, String str){
		if(!str.equals("")){
			By by = By.id("DESC");
			WebDriverUtils.fillin_textbox(driver, by, str);
		}
	}
	
	public void setTrainingCenter_UI(WebDriver driver, String str){
		if(!str.equals("")){
			By by = By.id("TCNAME");
			WebDriverUtils.fillin_textbox(driver, by, str);
		}
	}
	
	public void selectTrainingCenter_UI(WebDriver driver, String str){
		if(!str.equals("")){
			By by = By.xpath("//tr[descendant::td[contains(text(),'Training Center')]]/td/select[@name='TRAININGCENTERID']");
			WebDriverUtils.select_selector(driver, by, str);
		}
	}
	
	public void setStatus_UI(WebDriver driver, String str){
		if(!str.equals("")){
			By by = By.id("ST");
			WebDriverUtils.select_selector(driver, by, str);
		}
	}
	
	public void setVenue_UI(WebDriver driver, String str){
		if(!str.equals("")){
			By by = By.id("LOC");
			WebDriverUtils.fillin_textbox(driver, by, str);
		}
	}
	
	public void setContactInfo_UI(WebDriver driver, String str){
		if(!str.equals("")){
			By by = By.id("CIN");
			WebDriverUtils.fillin_textbox(driver, by, str);
		}
	}
	
	public void setDirection_UI(WebDriver driver, String str){
		if(!str.equals("")){
			By by = By.id("DIR");
			WebDriverUtils.fillin_textbox(driver, by, str);
		}
	}
	
	public void setIsExpandable_UI(WebDriver driver, String str){
		if(str.equals("yes")){
			By by = By.id("RISEXP");
			WebDriverUtils.check_checkbox(driver, by);
		}
	}
	
	
	public void setHasPC_UI(WebDriver driver, String str){
		if(str.equals("yes")){
			By by = By.id("BINPC");
			WebDriverUtils.check_checkbox(driver, by);
		}
	}
	
	public void setMinSeats_UI(WebDriver driver, String str){
		if(!str.equals("")){
			By by = By.id("MINS");
			WebDriverUtils.fillin_textbox(driver, by, str);
		}		 
	}
	
	public void setMaxSeats_UI(WebDriver driver, String str){
		if(!str.equals("")){
			By by = By.id("MAXS");
			WebDriverUtils.fillin_textbox(driver, by, str);
		}		 
	}
	
	public void setNVGA_UI(WebDriver driver, String str){
		if(!str.equals("")){
			By by = By.id("NVGA");
			WebDriverUtils.fillin_textbox(driver, by, str);
		}		 
	}
	
	public void setNPC_UI(WebDriver driver, String str){
		if(!str.equals("")){
			By by = By.id("NSPCS");
			WebDriverUtils.fillin_textbox(driver, by, str);
		}		 
	}
	
	public void setNWhiteBoard_UI(WebDriver driver, String str){
		if(!str.equals("")){
			By by = By.id("NWHITE");
			WebDriverUtils.fillin_textbox(driver, by, str);
		}		 
	}
	
	public void setNOverheadVGA_UI(WebDriver driver, String str){
		if(!str.equals("")){
			By by = By.id("NOVER");
			WebDriverUtils.fillin_textbox(driver, by, str);
		}		 
	}
	
	public void runCreateTrainingCenter(WebDriver driver){
		Navigator.navigate(driver,Navigator.xmlWebElmtMgr.getNavigationPathList("ManageCenter","Resource"),this);
		WebDriverUtils.switchToPopUpWin(driver);
		
		//Create training center
		WebDriverUtils.switchToFrame(driver, "FACILITY_TOP");
		
		By by = By.cssSelector("img[alt=\"Create\"]");
		WebDriverUtils.clickButton(driver, by);
	    WebDriverUtils.switchToFrame(driver, "FACILITY_MAIN");
	    
	    by = By.linkText("Create training center");
	    WebDriverUtils.clickLink(driver, by);
	 
	    this.setTrainingCenter_UI(driver, this.getTrainingCenterName());
	    this.setDesc_UI(driver, this.getDesc());
	    this.setVenue_UI(driver, this.getVenue());
	    this.setContactInfo_UI(driver, this.getContact());
	    this.setDirection_UI(driver, this.getDirections());
	    
	    by = By.name("SAVEACTION");
	    WebDriverUtils.clickButton(driver, by);
	}
	
	public void runCreateFacility(WebDriver driver){

		Navigator.navigate(driver,Navigator.xmlWebElmtMgr.getNavigationPathList("ManageCenter","Resource"),this);
		
		WebDriverUtils.switchToPopUpWin(driver);
		
	    // Create facilities
		WebDriverUtils.switchToFrame(driver, "FACILITY_TOP");
		
		By by = By.cssSelector("img[alt=\"Create\"]");
		WebDriverUtils.clickButton(driver, by);
	    WebDriverUtils.switchToFrame(driver, "FACILITY_MAIN");
	    
	    by = By.linkText("Create facility");
	    WebDriverUtils.clickLink(driver, by);
	    
	    this.setFacilityCode_UI(driver, this.getFacilityCode());
	    this.selectTrainingCenter_UI(driver, this.getTrainingCenterName());
	    this.setStatus_UI(driver, this.getStatus());
	    this.setVenue_UI(driver, this.getVenue());
	    this.setContactInfo_UI(driver, this.getContact());
	    this.setDirection_UI(driver, this.getDirections());
	    this.setIsExpandable_UI(driver, this.getIsExpandable());
	    this.setHasPC_UI(driver, this.getHasPC());
	    this.setMinSeats_UI(driver, this.getMinSeats());
	    this.setMaxSeats_UI(driver, this.getMaxSeats());
	    this.setNVGA_UI(driver, this.getNumOfVGAProjectors());
	    this.setNPC_UI(driver, this.getNumOfPCs());
	    this.setNWhiteBoard_UI(driver, this.getNumOfWhiteBoards());
	    this.setNOverheadVGA_UI(driver, this.getNumOfOverheadProjectors());
	    
	    by = By.name("ACTION");
	    WebDriverUtils.clickButton(driver, by);
	}
	

	public String getTrainingCenterName() {
		return TrainingCenterName;
	}


	public void setTrainingCenterName(String trainingCenterName) {
		TrainingCenterName = trainingCenterName;
	}


	public String getDesc() {
		return Desc;
	}


	public void setDesc(String desc) {
		Desc = desc;
	}


	public String getVenue() {
		return Venue;
	}


	public void setVenue(String venue) {
		Venue = venue;
	}


	public String getContact() {
		return Contact;
	}


	public void setContact(String contact) {
		Contact = contact;
	}


	public String getDirections() {
		return Directions;
	}


	public void setDirections(String directions) {
		Directions = directions;
	}


	public String getMap() {
		return Map;
	}


	public void setMap(String map) {
		Map = map;
	}


	public String getFacilityCode() {
		return FacilityCode;
	}


	public void setFacilityCode(String facilityCode) {
		FacilityCode = facilityCode;
	}


	public String getFacilityName() {
		return FacilityName;
	}


	public void setFacilityName(String facilityName) {
		FacilityName = facilityName;
	}


	public String getStatus() {
		return Status;
	}


	public void setStatus(String status) {
		Status = status;
	}


	public String getIsExpandable() {
		return isExpandable;
	}


	public void setIsExpandable(String isExpandable) {
		this.isExpandable = isExpandable;
	}


	public String getHasPC() {
		return hasPC;
	}


	public void setHasPC(String hasPC) {
		this.hasPC = hasPC;
	}


	public String getMinSeats() {
		return minSeats;
	}


	public void setMinSeats(String minSeats) {
		this.minSeats = minSeats;
	}


	public String getMaxSeats() {
		return maxSeats;
	}


	public void setMaxSeats(String maxSeats) {
		this.maxSeats = maxSeats;
	}


	public String getNumOfVGAProjectors() {
		return numOfVGAProjectors;
	}


	public void setNumOfVGAProjectors(String numOfVGAProjectors) {
		this.numOfVGAProjectors = numOfVGAProjectors;
	}


	public String getNumOfPCs() {
		return numOfPCs;
	}


	public void setNumOfPCs(String numOfPCs) {
		this.numOfPCs = numOfPCs;
	}


	public String getNumOfWhiteBoards() {
		return numOfWhiteBoards;
	}


	public void setNumOfWhiteBoards(String numOfWhiteBoards) {
		this.numOfWhiteBoards = numOfWhiteBoards;
	}


	public String getNumOfOverheadProjectors() {
		return numOfOverheadProjectors;
	}


	public void setNumOfOverheadProjectors(String numOfOverheadProjectors) {
		this.numOfOverheadProjectors = numOfOverheadProjectors;
	}

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

}
