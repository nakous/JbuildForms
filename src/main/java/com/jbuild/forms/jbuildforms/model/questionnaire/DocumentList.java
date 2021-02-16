package com.jbuild.forms.jbuildforms.model.questionnaire;

import java.io.Serializable;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * Contains the XML specification of the documents applicable to a specific questionnaire
 * 
 * 
 */
@XStreamAlias("document-list")
public class DocumentList implements Serializable {
	private static final long serialVersionUID = -684689284900297409L;

	/**
	 * unique id
	 */
	@XStreamAsAttribute
	@XStreamAlias("id")
	private Integer id;

	/**
	 * questionnaire id
	 */
	@XStreamAsAttribute
	@XStreamAlias("questionnaire-id")
	private Integer questionnaireId;

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
	@XStreamAlias("groups")
	private List<Group> groups;

	/**
	 * default constructor
	 */
	public DocumentList() {
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

	public Integer getQuestionnaireId() {
		return questionnaireId;
	}

	public void setQuestionnaireId(Integer questionnaireId) {
		this.questionnaireId = questionnaireId;
	}

	public Label getLabel() {
		return label;
	}

	public void setLabel(Label label) {
		this.label = label;
	}

	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}
}