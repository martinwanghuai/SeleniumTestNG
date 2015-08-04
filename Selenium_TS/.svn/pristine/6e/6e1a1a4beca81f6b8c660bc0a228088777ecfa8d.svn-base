package com.netdimen.model;

import java.util.ArrayList;
import java.util.Arrays;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.netdimen.config.Config;
import com.netdimen.config.Labels;
import com.netdimen.utils.WebDriverUtils;
import com.netdimen.view.Navigator;
import com.netdimen.view.SelectorsUI;

/**
 * 
 * @author martin.wang
 *
 */
public class EnrollmentPolicy extends Template {
	private String sendGroupEnroll="",	sendSelfEnroll="",	sendAutoEnroll="",
			allowWithdraw="", withdrawReason="",	orgPay="",
			parallelApproval="",	withdrawDetails="",	numOfWithdrawDaysForModule="",
			RefundPercOfModule="",	numOfWithdrawDaysForOptItems="",
			RefundPercOfOptItems="",	StepNames="",
			ApproverType="",	DesignatedApprover="",
			ApproalRequest="",	isStepExpire="",	
			ExpirePeriod="",	ExpireationAction="",
			ExpirationNotification="",	ReminderDay="",	
			ApprovalReminder="",	StepRemarks="",
			isManualStep="",	isParallelStep="",
			showCostCenter="",	sendEmail="",
			notifyEmail="", enrollConfirmMail="";

	public void expandPolicy_UI(WebDriver driver, String str){
		String str1 = "//a[contains(text(),'" + str + "') and img[@alt='Expand']]";
		By by = By.xpath(str1);
		int size = driver.findElements(by).size();
		if(size == 1){
			WebDriverUtils.clickLink(driver, by);
		}
	}
	
	public void setSendGroupEnroll_UI(WebDriver driver, String str){
		By by = By.id("send_group_enroll");
		if(str.equalsIgnoreCase("yes")){			
			WebDriverUtils.check_checkbox(driver, by);
		}else{
			WebDriverUtils.uncheck_checkbox(driver, by);
		}
	}

	public void setSendSelfEnroll_UI(WebDriver driver, String str){
		By by = By.id("send_self_enroll");
		if(str.equalsIgnoreCase("yes")){			
			WebDriverUtils.check_checkbox(driver, by);
		}else{
			WebDriverUtils.uncheck_checkbox(driver, by);
		}
	}
	
	public void setSendAutoEnroll_UI(WebDriver driver, String str){
		By by = By.id("send_auto_enroll");
		if(str.equalsIgnoreCase("yes")){			
			WebDriverUtils.check_checkbox(driver, by);
		}else{
			WebDriverUtils.uncheck_checkbox(driver, by);
		}
	}
	
	public void setAllowWithdraw_UI(WebDriver driver, String str){
		By by = By.id("allow_approval_withdrawal");
		if(str.equalsIgnoreCase("yes")){			
			WebDriverUtils.check_checkbox(driver, by);
		}else{
			WebDriverUtils.uncheck_checkbox(driver, by);
		}
	}
	
	public void setWithdrawReason_UI(WebDriver driver, String str){
		By by = By.id("promptForReason");
		if(str.equalsIgnoreCase("yes")){			
			WebDriverUtils.check_checkbox(driver, by);
		}else{
			WebDriverUtils.uncheck_checkbox(driver, by);
		}
	}
	
	public void setOrgPay_UI(WebDriver driver, String str){
		By by = By.id("consider_payment_plan");
		if(str.equalsIgnoreCase("yes")){			
			WebDriverUtils.check_checkbox(driver, by);
		}else{
			WebDriverUtils.uncheck_checkbox(driver, by);
		}
	}

	public void setEnrollConfirmMail_UI(WebDriver driver, String str){
		if(!str.equalsIgnoreCase("")){

			WebDriverUtils.switchToPopUpWin(driver);
			WebDriverUtils.switchToFrame(driver, "EMAILTEMPLATEMENU");
			
			By by = By.cssSelector("img[alt=\"Search\"]");
			WebDriverUtils.clickButton(driver, by);
			
			WebDriverUtils.switchToFrame(driver, "EMAILTEMPLATEMAIN");
			by = By.name("PROFILE_NAME");
			WebDriverUtils.fillin_textbox(driver, by, str);

			by = By.name("SEARCH");
			WebDriverUtils.clickButton(driver, by);
			
			by = By.xpath("//a[contains(text(),'" + str + "')]");
			WebDriverUtils.clickLink(driver, by);
			
			WebDriverUtils.switchToFrame(driver, "EMAILTEMPLATEMENU");
			by = By.cssSelector("img[alt=\"Select\"]");
			WebDriverUtils.clickLink(driver, by);
		}
	}
	
	public void setParallelApproval_UI(WebDriver driver, String str){
		String str1 = str.toLowerCase();
		if(str1.contains("first response determines outcome")){
			By by = By.id("allParallelStepsMustSucceed_false");
			WebDriverUtils.check_checkbox(driver, by);
		}else if(str1.contains("all prarallel steps must succeed")){
			By by = By.id("allParallelStepsMustSucceed_true");
			WebDriverUtils.check_checkbox(driver, by);
		}
	}
	
	public void setWithdrawDetail_UI(WebDriver driver, String str){
		if(!str.equalsIgnoreCase("")){
			By by = By.id("cancel_policy");
			WebDriverUtils.fillin_textbox(driver, by, str);
		}		
	}
	
	public void setWithdrawDayAndRefundForModule_UI(WebDriver driver, String day, String refund){
		By by = null;
		if(!day.equalsIgnoreCase("") && !refund.equalsIgnoreCase("")){
			by = By.id("day");
			WebDriverUtils.fillin_textbox(driver, by, day);

			by = By.id("deduction");
			WebDriverUtils.fillin_textbox(driver, by, refund);
			
			by = By.name("SUBMIT");
			WebDriverUtils.clickButton(driver, by);
		}
	}
	
	public void setWithdrawDayAndRefundForOptItem_UI(WebDriver driver, String day, String refund){
		By by = null;
		if(!day.equalsIgnoreCase("") && !refund.equalsIgnoreCase("")){
			by = By.id("dayOptPayItems");
			WebDriverUtils.fillin_textbox(driver, by, day);

			by = By.id("deductionOptPayItems");
			WebDriverUtils.fillin_textbox(driver, by, refund);
			
			by = By.name("SUBMIT");
			WebDriverUtils.clickButton(driver, by);
		}
	}
	
	public void setStepName_UI(WebDriver driver, String str){
		if(!str.equalsIgnoreCase("")){
			By by = By.id("step_name");
			WebDriverUtils.fillin_textbox(driver, by, str);
		}
	}
	
	public void setApproverType_UI(WebDriver driver, String str){
		if(!str.equalsIgnoreCase("")){
			By by = By.id("aMenu");
			WebDriverUtils.select_selector(driver, by, str);
		}
	}
	
	public void setDesignedApprover_UI(WebDriver driver, String str){
		if(!str.equalsIgnoreCase("")){
			By by = By.xpath("(//img[@alt='Select'])[2]");
			WebDriverUtils.clickLink(driver, by);
			WebDriverUtils.switchToPopUpWin(driver);
			
			SelectorsUI.PopUp_Selector(driver, SelectorsUI.PopUpSelector.UserSelector, str);
		
			
		}
	}
	
	public void setApprovalRequest_UI(WebDriver driver, String str){
		if(!str.equalsIgnoreCase("")){
			By by = By.xpath("(//img[@alt='Select'])[2]");
			WebDriverUtils.clickLink(driver, by);
			WebDriverUtils.switchToPopUpWin(driver);
			this.setEnrollConfirmMail_UI(driver, str);
		}
	}
	

	public void runCreate(WebDriver driver){
		//Navigator.navigate(driver, Navigator.URL.EnrollmentPolicyEditor);
		Navigator.navigate(driver,Navigator.xmlWebElmtMgr.getNavigationPathList("ManageCenter","PolicyEditor"),this);
		
		WebDriverUtils.switchToPopUpWin(driver);
		WebDriverUtils.switchToFrame(driver, "POLICYMENU");
		
		By by = By.cssSelector("img[alt=\"Create policy\"]");
		WebDriverUtils.clickButton(driver, by);

		WebDriverUtils.switchToFrame(driver, "POLICYMAIN");
		this.setName_UI(driver, this.getName());
		this.setSendGroupEnroll_UI(driver, this.getsendGroupEnroll());
		this.setSendAutoEnroll_UI(driver, this.getsendAutoEnroll());
		this.setSendSelfEnroll_UI(driver, this.getsendSelfEnroll());
		
		by = By.id("select_button");
		WebDriverUtils.clickButton(driver, by);
		this.setEnrollConfirmMail_UI(driver, this.getenrollConfirmMail());
		
		WebDriverUtils.switchToFrame(driver, "POLICYMENU");
		by = By.cssSelector("img[alt=\"Save\"]");
		WebDriverUtils.clickButton(driver, by);
		//Expand policy
		WebDriverUtils.switchToFrame(driver, "POLICYTREE");
		this.expandPolicy_UI(driver, this.getName());
		
		// Edit policy detail		
		by = By.xpath("//a[contains(text(),'" + this.getName() + "')]/following-sibling::a[contains(text(),'"+Labels.Link_Edit_Policy+"')]");
		WebDriverUtils.clickLink(driver, by);
		WebDriverUtils.switchToFrame(driver, "POLICYMAIN");
		this.setAllowWithdraw_UI(driver, this.getallowWithdraw());
		this.setWithdrawReason_UI(driver, this.getwithdrawReason());
		this.setOrgPay_UI(driver, this.getorgPay());
		this.setParallelApproval_UI(driver, this.getparallelApproval());
		
		
		WebDriverUtils.switchToFrame(driver, "POLICYMENU");
		by = By.cssSelector("img[alt=\"Save\"]");
		WebDriverUtils.clickButton(driver, by);
		
		//Edit withdraw detail
		WebDriverUtils.switchToFrame(driver, "POLICYTREE");
		this.expandPolicy_UI(driver, this.getName());
		by = By.xpath("//a[contains(text(),'" + this.getName() + "')]/following-sibling::a[contains(text(),'"+Labels.Link_Edit_Withdraw+"')]");
		WebDriverUtils.clickLink(driver, by);
		
		WebDriverUtils.switchToFrame(driver, "POLICYMAIN");
		this.setWithdrawDetail_UI(driver, this.getwithdrawDetails());
		
		String[] days = this.getnumOfWithdrawDaysForModule().split(";");
		String[] refunds = this.getRefundPercOfModule().split(";");
		
		if(days.length!=refunds.length){
			System.out.println("The number of days and refunds does not match for modules");		
		}else{
			for(int i = 0; i < days.length; i++){				
				String day = days[i];
				String refund = refunds[i];
				this.setWithdrawDayAndRefundForModule_UI(driver, day, refund);
			}
		}
		
		String[] days_OptItem = this.getnumOfWithdrawDaysForOptItems().split(";");
		String[] refunds_OptItem = this.getRefundPercOfOptItems().split(";");
		
		if(days_OptItem.length!=refunds_OptItem.length){
			System.out.println("The number of days and refunds does not match for modules");		
		}else{
			for(int i = 0; i < days_OptItem.length; i++){
				String day = days_OptItem[i];
				String refund = days_OptItem[i];
				this.setWithdrawDayAndRefundForOptItem_UI(driver, day, refund);
			}
		}
		WebDriverUtils.switchToFrame(driver, "POLICYMENU");
		
		by = By.cssSelector("img[alt=\"Save\"]");
		WebDriverUtils.clickButton(driver, by);
		
		// Approval steps
		WebDriverUtils.switchToFrame(driver, "POLICYTREE");
		this.expandPolicy_UI(driver, this.getName());
		
		by = By.xpath("//a[contains(text(),'" + this.getName() + "')]/following-sibling::a[contains(text(),'Add a step')]");
		WebDriverUtils.clickLink(driver, by);
		
		// Edit policy detail	
		WebDriverUtils.switchToFrame(driver, "POLICYMAIN");
		
		this.setStepName_UI(driver, this.getStepNames());
		this.setApproverType_UI(driver, this.getApproverType());
		this.setApprovalRequest_UI(driver, this.getApproalRequest());
		WebDriverUtils.switchToParentWin(driver);
		
		WebDriverUtils.switchToFrame(driver, "POLICYMENU");
		
		by = By.cssSelector("img[alt=\"Save\"]");
		WebDriverUtils.clickButton(driver, by);
	}

	public String getsendGroupEnroll() {
		return sendGroupEnroll;
	}

	public void setsendGroupEnroll(String sendGroupEnroll) {
		this.sendGroupEnroll = sendGroupEnroll;
	}

	public String getsendSelfEnroll() {
		return sendSelfEnroll;
	}

	public void setsendSelfEnroll(String sendSelfEnroll) {
		this.sendSelfEnroll = sendSelfEnroll;
	}

	public String getsendAutoEnroll() {
		return sendAutoEnroll;
	}

	public void setsendAutoEnroll(String sendAutoEnroll) {
		this.sendAutoEnroll = sendAutoEnroll;
	}

	public String getallowWithdraw() {
		return allowWithdraw;
	}

	public void setallowWithdraw(String allowWithdraw) {
		this.allowWithdraw = allowWithdraw;
	}

	public String getwithdrawReason() {
		return withdrawReason;
	}

	public void setwithdrawReason(String withdrawReason) {
		this.withdrawReason = withdrawReason;
	}

	public String getorgPay() {
		return orgPay;
	}

	public void setorgPay(String orgPay) {
		this.orgPay = orgPay;
	}

	public String getparallelApproval() {
		return parallelApproval;
	}

	public void setparallelApproval(String parallelApproval) {
		this.parallelApproval = parallelApproval;
	}

	public String getwithdrawDetails() {
		return withdrawDetails;
	}

	public void setwithdrawDetails(String withdrawDetails) {
		this.withdrawDetails = withdrawDetails;
	}

	public String getnumOfWithdrawDaysForModule() {
		return numOfWithdrawDaysForModule;
	}

	public void setnumOfWithdrawDaysForModule(String numOfWithdrawDaysForModule) {
		this.numOfWithdrawDaysForModule = numOfWithdrawDaysForModule;
	}

	public String getRefundPercOfModule() {
		return RefundPercOfModule;
	}

	public void setRefundPercOfModule(String refundPercOfModule) {
		RefundPercOfModule = refundPercOfModule;
	}

	public String getnumOfWithdrawDaysForOptItems() {
		return numOfWithdrawDaysForOptItems;
	}

	public void setnumOfWithdrawDaysForOptItems(String numOfWithdrawDaysForOptItems) {
		this.numOfWithdrawDaysForOptItems = numOfWithdrawDaysForOptItems;
	}

	public String getRefundPercOfOptItems() {
		return RefundPercOfOptItems;
	}

	public void setRefundPercOfOptItems(String refundPercOfOptItems) {
		RefundPercOfOptItems = refundPercOfOptItems;
	}

	public String getStepNames() {
		return StepNames;
	}

	public void setStepNames(String stepNames) {
		StepNames = stepNames;
	}

	public String getApproverType() {
		return ApproverType;
	}

	public void setApproverType(String approverType) {
		ApproverType = approverType;
	}

	public String getDesignatedApprover() {
		return DesignatedApprover;
	}

	public void setDesignatedApprover(String designatedApprover) {
		DesignatedApprover = designatedApprover;
	}

	public String getApproalRequest() {
		return ApproalRequest;
	}

	public void setApproalRequest(String approalRequest) {
		ApproalRequest = approalRequest;
	}

	public String getisStepExpire() {
		return isStepExpire;
	}

	public void setisStepExpire(String isStepExpire) {
		this.isStepExpire = isStepExpire;
	}

	public String getExpirePeriod() {
		return ExpirePeriod;
	}

	public void setExpirePeriod(String expirePeriod) {
		ExpirePeriod = expirePeriod;
	}

	public String getExpireationAction() {
		return ExpireationAction;
	}

	public void setExpireationAction(String expireationAction) {
		ExpireationAction = expireationAction;
	}

	public String getExpirationNotification() {
		return ExpirationNotification;
	}

	public void setExpirationNotification(String expirationNotification) {
		ExpirationNotification = expirationNotification;
	}

	public String getReminderDay() {
		return ReminderDay;
	}

	public void setReminderDay(String reminderDay) {
		ReminderDay = reminderDay;
	}

	public String getApprovalReminder() {
		return ApprovalReminder;
	}

	public void setApprovalReminder(String approvalReminder) {
		ApprovalReminder = approvalReminder;
	}

	public String getStepRemarks() {
		return StepRemarks;
	}

	public void setStepRemarks(String stepRemarks) {
		StepRemarks = stepRemarks;
	}

	public String getisManualStep() {
		return isManualStep;
	}

	public void setisManualStep(String isManualStep) {
		this.isManualStep = isManualStep;
	}

	public String getisParallelStep() {
		return isParallelStep;
	}

	public void setisParallelStep(String isParallelStep) {
		this.isParallelStep = isParallelStep;
	}

	public String getshowCostCenter() {
		return showCostCenter;
	}

	public void setshowCostCenter(String showCostCenter) {
		this.showCostCenter = showCostCenter;
	}

	public String getsendEmail() {
		return sendEmail;
	}

	public void setsendEmail(String sendEmail) {
		this.sendEmail = sendEmail;
	}

	public String getnotifyEmail() {
		return notifyEmail;
	}

	public void setnotifyEmail(String notifyEmail) {
		this.notifyEmail = notifyEmail;
	}

	public String getenrollConfirmMail() {
		return enrollConfirmMail;
	}

	public void setenrollConfirmMail(String enrollConfirmMail) {
		this.enrollConfirmMail = enrollConfirmMail;
	}

}
