package com.jbuild.forms.jbuildforms.model.questionnaire;

import java.io.Serializable;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;


/**
 * Contains the XML specification of the sections, questions, related to a recruitment type.
 * 
 * 
 */
@XStreamAlias("questionnaire")
public class Questionnaire implements Serializable {
	private static final long serialVersionUID = -684689284900297409L;

	/**
	 * unique id
	 */
	@XStreamAsAttribute
	@XStreamAlias("id")
	private Integer id;
	
	/*
	 * doc-guide besed on questionnaire documents are generated this is the template name for this 
	 * 
	*/
	@XStreamAsAttribute
	@XStreamAlias("doc-guide")
	private String docGuide;

	public String getDocGuide() {
		return docGuide;
	}

	public void setDocGuide(String docGuide) {
		this.docGuide = docGuide;
	}

	/**
	 * a single translated label in application messages
	 */
	@XStreamAlias("label")
	private Label label;

	/**
	 * translations for the questionnaire name
	 */
	@XStreamAlias("labels")
	private List<Label> labels;

	/**
	 * sections that contain the questions
	 */
	@XStreamAlias("sections")
	private List<Section> sections;
	
	/**
	 * Question access rights on the top level
	 */
	@XStreamAlias("access-rights")
	private List<AccessRight> accessRights;

	/**
	 * Messages to be displayed on the views as warning,info and errors => added to workflow_messages column in dossier_status table
	 */
	@XStreamAlias("workflow-messages")
	private List<String> workflowMessages;
	
	/**
	 * default constructor
	 */
	public Questionnaire() {
		super();
	}

	/**
	 * @return id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return list of labels in all languages
	 */
	public List<Label> getLabels() {
		return labels;
	}

	/**
	 * @param labels
	 */
	public void setLabels(List<Label> labels) {
		this.labels = labels;
	}

	/**
	 * @return all sections
	 */
	public List<Section> getSections() {
		return sections;
	}

	/**
	 * @param sections
	 */
	public void setSections(List<Section> sections) {
		this.sections = sections;
	}

	public Label getLabel() {
		return label;
	}

	public void setLabel(Label label) {
		this.label = label;
	}
	
	public List<AccessRight> getAccessRights() {
		return accessRights;
	}

	public void setAccessRights(List<AccessRight> accessRights) {
		this.accessRights = accessRights;
	}

	public List<String> getWorkflowMessages() {
		return workflowMessages;
	}

	public void setWorkflowMessages(List<String> workflowMessages) {
		this.workflowMessages = workflowMessages;
	}
	
	
}