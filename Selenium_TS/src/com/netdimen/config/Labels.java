package com.netdimen.config;

import org.apache.commons.lang3.StringEscapeUtils;

public enum Labels {
	// Naming pattern
	// Link_xx for all link
	// Btn_xxx for all btn
	// Msg_xxx for msg show in page
	// Heading_xxx for heading checking
	// Tab_xxx for tab menu item
	// Alert_xxx for javascript alert messagebox
	// Desc_xxx for desc feild in page
	// Gear_xxx for gear btn
	// Text_xxx for only text presented in page

	// general
	Btn_OK("button.ok"), Btn_Search("button.Search"), Btn_Submit(
			"button.Submit"), Btn_Cancel("button.Cancel"), Btn_Deny(
			"button.deny"), Btn_Edit("button.Edit"), Btn_Save("button.Save"), Btn_Enroll(
			"lable.Enroll_Now"), Btn_join_waitlist("label.Join_waitlist"), Btn_Continue(
			"button.Continue"), Btn_Fiter("label.filter"),

	// Manager>my team review
	Gear_assign_course("button.assign_course"), Gear_reviewlearningcenter(
			"button.reviewlearningcenter"), Link_review_learning(
			"label.review_learning"),

	// Email
	Text_From("label.from"),

	// Appraisal
	Exceeded_Expected_Performance("label.Exceeded_Expected_Performance"), Perf_Goal_Plan(
			"appraisal.sectionType.performance_goal_plan"), Dev_Goal_Plan(
			"appraisal.sectionType.development_goal_plan"), Promt_Potential_Report(
			"heading.plot_9box_promotion_potential_report"), Retent_Risk_Report(
			"heading.plot_9box_retention_risk_report"), Appraisal_Saved_Msg(
			"appraisal.msg.dataSavedComplete"), Other_Achievements(
			"label.Other_Significant_Achievements"), Create_Goal(
			"label.goal_create"), Proceed_Comp_Assessment(
			"button.proceed_to_comp_assessment"), Success_Msg(
			"label.numberUserComplete"), Failed_Msg("label.numberUserFail"),

	// CDC
	Btn_SessionTransfer("button.enrolled.courses.session.transfer"),

	// User Editor
	Msg_user_no_right_to_edit("msg.You_do_not_right_to_change_user_role_\\!"),

	// User selector
	Entry_Form("label.UserIdDirectForm"),

	// Link:Organization
	Link_Org("desc.Organization"),

	// Third party account
	TempEmailWebSite("TempEmailWebSite"), AdobeConnect("label.vc_adobe_connect"), Webex(
			"label.vc_webex"), GoToTraining("label.vc_gtt"), HorizonWimba(
			"label.vc_horizon_wimba"),

	// Catalog Editor

	Edit_Catalog_Properties("button.edit_properties"), Tab_Edit_Session_Schedule(
			"tab.Edit_Session_Schedule"), Link_GEnroll_Select(
			"label.GEnroll_Desc3"), Msg_GEnroll_Success("label.registered"), Link_Sess_Properties(
			"label.SessionProperties"), Msg_Confirm_Add_Schedule(
			"desc.confirm_add_schedule"), Ques_Group_Sure(
			"msg.Are_you_sure_group"), Link_Edit_Policy("text.EditPolicy"), Link_Edit_Withdraw(
			"text.EditWithdraw"), Msg_Confirm_Delete("msg.confirm_delete"), Msg_Group_Assign(
			"msg.Are_you_sure_group"), Link_Assign_Catalog(
			"label.EditLO_Desc19"), Link_Assign_Test(
			"label.Assign_Test/Cert/Eval"), Link_Launch_Properties(
			"label.Define_Launch_Properties"), Link_Group_Enroll(
			"tab.Group_Enroll"), Link_Define_Enroll_Policy(
			"label.Define_Enrollment_Policy"), Link_Setup_Options(
			"label.Setup_Options"), Link_Auto_Enroll("tab.Auto_Enroll"), Link_Define_Module_Security(
			"label.Define_Module_Security"), Link_Participants(
			"heading.participants"), Link_Update_Deadline(
			"label.action.update_deadline"), Link_Transfer_Session(
			"label.action.transfer_session"), Link_Substitute_participant(
			"button.Execute_Substitution"), Btn_Substitute("button.Substitute"), Link_Select_Participants(
			"label.Select_Participants"), Btn_Delete_Course(
			"label.delete_course"), Btn_Test_AutoEnroll(
			"button.Test_AutoEnroll_Targets"), Btn_Set_AutoEnroll(
			"button.Set_AutoEnroll_Targets"), Text_Cancelled("label.Cancelled"), Link_Edit_Session_Schedule(
			"tab.Edit_Session_Schedule"), Link_Email_Preference_Setup(
			"label.Email_Preference_Setup"), Heading_Gentle_Deadline_Reminder(
			"label.Gentle_Deadline_Reminder"), Session_Properties(
			"label.SessionProperties"), tab_Edit_Schedule("tab.Edit_Schedule"), label_Edit_LVL1_Constraints(
			"label.Edit_LVL1_Constraints"),

	// Revisions
	Link_Revisions("heading.Revisions"), Label_Preview(
			"label.catalog.revisions.status_preview"), Label_Effective(
			"label.catalog.revisions.status_effective"), Label_Approved(
			"label.catalog.revisions.status_approved"), button_preview(
			"button.catalog.revisions.preview"), Mark_Approved(
			"button.catalog.revisions.mark_as_approved"), button_Mark_effective(
			"button.catalog.revisions.mark_as_effective"), button_Import_Revision(
			"button.catalog.revisions.import_revision"), link_import_resource(
			"heading.importSCO"),

	// Waitlist Handling Options
	Text_Auto_Upgrade("label.AutoWaitList"), Text_Manual_Upgrade(
			"label.ManualWaitList"), Text_waitlist_all(
			"label.EveryoneWaitListed"), Text_no_waitlist("label.no_waitlist"),

	// Learning Module Status
	Text_Not_Started("label.Not_Started"), // Not Started
	Text_In_Process("label.status_2"), // In Process
	Text_Completed("label.status_3"), // Completed
	Text_Waitlisted("label.status_4"), // Waitlisted
	Text_Withdrawn("label.status_5"), // Withdrawn
	Text_Pending_Approval("label.status_6"), // Pending Approval
	Text_Self_Asserted("label.overallstatus.completed_self_asserted"), // Completed
																		// (Self-Asserted)
	Text_Deactivated("label.overallstatus.deactivated"), // Deactivated
	EnrolledMsg("msg.C03_register_error4"), Text_Retired(
			"label.qstatus.Retired"), // Retired
	FailedMsg("desc.Sorry_This_course_does_not_have_a_scheduled_date"),

	// KC
	Link_KC("label.Learning_Space"), ActiveModuleHeading(
			"heading.Active_and_Completed_Modules"), AvailableModuleHeading(
			"heading.Available_Modules"), RequiredModuleHeading(
			"label.required.modules"), ElectiveModuleHeading(
			"label.elective.modules"), Btn_Mark_Completed(
			"button.Completed_Self_Asserted"), Btn_Go_To_CLM(
			"button.Go_To_Current_Learning_Modules"), Link_Assessment_Workflow(
			"label.assessment_workflow"),

	// My Learning
	Btn_show_subModules("label.transcript.learningprogram.show.submodules"), Btn_Filter(
			"label.filter"), Btn_Withdraw_Enrollment(
			"button.withdraw_enrollment"), Btn_Withdraw("button.Withdraw"),

	// Goal
	Tab_Personal_Goal("tab.personal_goal.name"), Tab_Dev_Goal(
			"tab.development_goal.name"), Label_Personal_Goal(
			"label.personal_goal"), Label_Dev_Goal("label.development_goal"), Msg_Delete_Dev_Goal(
			"delete.edit.development_goal.confirm_message"), Msg_Delete_Perf_Goal(
			"delete.edit.personal_goal.confirm_message"), Link_My_Perf_Goal(
			"menu.my.performance.goals"), Link_My_Dev_Goal(
			"menu.my.development.goals"), Btn_Perf_Goal(
			"button.personal_goal.new"), Btn_Dev_Goal(
			"button.development_goal.new"), Btn_Dev_New(
			"button.development_goal.new"),

	// TEACH
	Msg_Update_Scuess("msg.updateSuccessfully"),

	// JobProfile
	Msg_JobProfile_Assigned("msg_jobprofile_autoassing_now_in_effect"),

	// Msg:Are you sure you want to change the auto-enrollment targeting
	// criteria?
	Msg_Auto_Sure("msg.are_you_sure_auto"),

	// Org. Attribute link
	Link_Org_Attrs("heading.org.attrs"),

	// EQ
	Btn_Create_Rule("button.equivalency.create.rule"), Btn_Create(
			"button.Create"), Gear_EQ_Btn("label.equivalency.manager"), Add_Module_Link(
			"link.Add_Module(s)..."), Gear_Audience_Btn(
			"button.equivalency_rule.target_audience"),

	// Exam
	Btn_CloseTest("button.closetest"),

	// External Training Rec
	Label_PendingApproval("label.pendingApproval"), Msg_PendingApprWarn(
			"msg.pendingApprWarn"), Msg_ExtCreateSuccess(
			"msg.externalCreateSuccessful"), Msg_ExtUpdateSuccess(
			"msg.externalUpdateSuccessful"),

	// Forum
	Msg_Create_Forum("desc.Create_New_Forum"), Btn_Post_Topic(
			"button.Post_New_Topic"),

	// Multi_language
	Label_Multi_lang("display.system_language_activation.multiple_language"),

	// Home Template
	Btn_Create_HomeTempl("list_homepages.button.newTemplate.label"),

	// Target Audience
	Tab_Target_Audience("tab.Target_Audience"),

	// Enrolled Learning module
	Msg_Tasks_Completed("heading.Tasks_Completed"), Msg_Module_NoAvailable(
			"desc.No_longer_available"), Msg_Session_Finished(
			"msg.session_finished"),

	// User class
	Btn_test_attempt_Confirm("label.test_attempt.confirm_end_exam"),

	// User_Group
	Btn_Create_User_Group("button.CreateNewUserGroup"),

	// Role Access Control
	Btn_List_Permission("button.List_Permissions"), Btn_Update_Acess_Control_Setting(
			"button.Update_Access_Control_Settings");

	private final String labelId;

	private Labels(String aLabelId) {
		labelId = aLabelId;
	}

	@Override
	public String toString() {
		return StringEscapeUtils.unescapeJava(Config.getInstance().getProperty(
				labelId));
	}

}
