package com.netdimen.dao;

public class eSignture {

	public enum eSigntureType {
		Inherite, Enable, Disable
	}

	public eSigntureType courseCSVloader = eSigntureType.Inherite;
	public eSigntureType ProgCSVloader = eSigntureType.Inherite;
	public eSigntureType withdraws = eSigntureType.Inherite;
	public eSigntureType courseUpdate = eSigntureType.Inherite;
	public eSigntureType courselaunch = eSigntureType.Inherite;
	public eSigntureType courseimporter = eSigntureType.Inherite;
	public eSigntureType coursefinish = eSigntureType.Inherite;
	public eSigntureType examlaunch = eSigntureType.Inherite;
	public eSigntureType questionStatus = eSigntureType.Inherite;
	public eSigntureType questionImporter = eSigntureType.Inherite;
	public eSigntureType manualGrading = eSigntureType.Inherite;
	public eSigntureType transcriptModifiedByReviewer = eSigntureType.Inherite;
	public eSigntureType transcriptModifiedViaCatalogEditor = eSigntureType.Inherite;
	public eSigntureType transcriptAttendanceModification = eSigntureType.Inherite;
	public eSigntureType transcriptModifiedViaEnrollWizard = eSigntureType.Inherite;
	public eSigntureType externalTrainingRecord = eSigntureType.Inherite;
	public eSigntureType certUpdate = eSigntureType.Inherite;
	public eSigntureType certDelete = eSigntureType.Inherite;

	public String getESignTypeEnableTextifItisEnabled(final eSigntureType e) {
		if (e.compareTo(eSigntureType.Enable) == 0) {
			return "enable";
		}
		return "";
	}

	public eSigntureType getCertUpdate() {
		return certUpdate;
	}

	public void setCertUpdate(final eSigntureType certUpdate) {
		this.certUpdate = certUpdate;
	}

	public eSigntureType getCertDelete() {
		return certDelete;
	}

	public void setCertDelete(final eSigntureType certDelete) {
		this.certDelete = certDelete;
	}

	public eSigntureType getCourseCSVloader() {
		return courseCSVloader;
	}

	public void setCourseCSVloader(final eSigntureType courseCSVloader) {
		this.courseCSVloader = courseCSVloader;
	}

	public eSigntureType getProgCSVloader() {
		return ProgCSVloader;
	}

	public void setProgCSVloader(final eSigntureType progCSVloader) {
		ProgCSVloader = progCSVloader;
	}

	public eSigntureType getWithdraws() {
		return withdraws;
	}

	public void setWithdraws(final eSigntureType withdraws) {
		this.withdraws = withdraws;
	}

	public eSigntureType getCourseUpdate() {
		return courseUpdate;
	}

	public void setCourseUpdate(final eSigntureType courseUpdate) {
		this.courseUpdate = courseUpdate;
	}

	public eSigntureType getCourselaunch() {
		return courselaunch;
	}

	public void setCourselaunch(final eSigntureType courselaunch) {
		this.courselaunch = courselaunch;
	}

	public eSigntureType getCourseimporter() {
		return courseimporter;
	}

	public void setCourseimporter(final eSigntureType courseimporter) {
		this.courseimporter = courseimporter;
	}

	public eSigntureType getCoursefinish() {
		return coursefinish;
	}

	public void setCoursefinish(final eSigntureType coursefinish) {
		this.coursefinish = coursefinish;
	}

	public eSigntureType getExamlaunch() {
		return examlaunch;
	}

	public void setExamlaunch(final eSigntureType examlaunch) {
		this.examlaunch = examlaunch;
	}

	public eSigntureType getQuestionStatus() {
		return questionStatus;
	}

	public void setQuestionStatus(final eSigntureType questionStatus) {
		this.questionStatus = questionStatus;
	}

	public eSigntureType getQuestionImporter() {
		return questionImporter;
	}

	public void setQuestionImporter(final eSigntureType questionImporter) {
		this.questionImporter = questionImporter;
	}

	public eSigntureType getManualGrading() {
		return manualGrading;
	}

	public void setManualGrading(final eSigntureType manualGrading) {
		this.manualGrading = manualGrading;
	}

	public eSigntureType getTranscriptModifiedByReviewer() {
		return transcriptModifiedByReviewer;
	}

	public void setTranscriptModifiedByReviewer(
			final eSigntureType transcriptModifiedByReviewer) {
		this.transcriptModifiedByReviewer = transcriptModifiedByReviewer;
	}

	public eSigntureType getTranscriptModifiedViaCatalogEditor() {
		return transcriptModifiedViaCatalogEditor;
	}

	public void setTranscriptModifiedViaCatalogEditor(
			final eSigntureType transcriptModifiedViaCatalogEditor) {
		this.transcriptModifiedViaCatalogEditor = transcriptModifiedViaCatalogEditor;
	}

	public eSigntureType getTranscriptAttendanceModification() {
		return transcriptAttendanceModification;
	}

	public void setTranscriptAttendanceModification(
			final eSigntureType transcriptAttendanceModification) {
		this.transcriptAttendanceModification = transcriptAttendanceModification;
	}

	public eSigntureType getTranscriptModifiedViaEnrollWizard() {
		return transcriptModifiedViaEnrollWizard;
	}

	public void setTranscriptModifiedViaEnrollWizard(
			final eSigntureType transcriptModifiedViaEnrollWizard) {
		this.transcriptModifiedViaEnrollWizard = transcriptModifiedViaEnrollWizard;
	}

	public eSigntureType getExternalTrainingRecord() {
		return externalTrainingRecord;
	}

	public void setExternalTrainingRecord(final eSigntureType externalTrainingRecord) {
		this.externalTrainingRecord = externalTrainingRecord;
	}

	public eSignture() {
	}

}
