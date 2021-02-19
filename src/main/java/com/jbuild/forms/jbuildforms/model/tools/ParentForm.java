package com.jbuild.forms.jbuildforms.model.tools;

public class ParentForm {
	/**
	 * The dossier id
	 */
	private Integer dossierId;

	/**
	 * The answers to the questionnaire questions
	 */

	private Integer pageNumber;

	private Integer processId;

	private String saveFinal;

	private String saveDraft;

	private Boolean saveDocForLater;

 

	public Integer getDossierId() {
		return dossierId;
	}

	public void setDossierId(Integer dossierId) {
		this.dossierId = dossierId;
	}

	public Integer getProcessId() {
		return processId;
	}

	public void setProcessId(Integer processId) {
		this.processId = processId;
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public String getSaveFinal() {
		return saveFinal;
	}

	public void setSaveFinal(String saveFinal) {
		this.saveFinal = saveFinal;
	}

	public String getSaveDraft() {
		return saveDraft;
	}

	public void setSaveDraft(String saveDraft) {
		this.saveDraft = saveDraft;
	}

	public Boolean getSaveDocForLater() {
		return saveDocForLater;
	}

	public void setSaveDocForLater(Boolean saveDocForLater) {
		this.saveDocForLater = saveDocForLater;
	}

	 
 
}