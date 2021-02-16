package com.jbuild.forms.jbuildforms.model.questionnaire;

import java.io.Serializable;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

@XStreamAlias("section")
public class Section implements Serializable, Orderable {
	private static final long serialVersionUID = -684689284900297409L;

	/**
	 * Unique id
	 */
	@XStreamAsAttribute
	@XStreamAlias("id")
	private Integer id;

	/**
	 * the order is used for display purposes when the section is rendered in the GUI
	 */
	@XStreamAsAttribute
	@XStreamAlias("order")
	private Short order;

	/**
	 * a single translated label in application messages
	 */
	@XStreamAlias("label")
	private Label label;

	/**
	 * a single translated label in application messages
	 */
	@XStreamAlias("description")
	private Label description;

	public Label getDescription() {
		return description;
	}

	public void setDescription(Label description) {
		this.description = description;
	}

	/**
	 * translations for the section name
	 */
	@XStreamAlias("labels")
	private List<Label> labels;

	/**
	 * the section related questions
	 */
	@XStreamAlias("questions")
	private List<Question> questions;

	/**
	 * the section related questions
	 */
	@XStreamAlias("answers")
	private List<Answer> answers;

	@XStreamAlias("conditions")
	private String conditions;

	/**
	 * what to display
	 */
	@XStreamOmitField
	private Boolean render;

	/**
	 * defult constructor
	 */
	public Section() {
		super();
	}

	/**
	 * @return unique id
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

	/*
	 * (non-Javadoc)
	 * @see eu.europa.europarl.apapeople.model.questionnaire.Orderable#getOrder()
	 */
	public Short getOrder() {
		return order;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.europa.europarl.apapeople.model.questionnaire.Orderable#setOrder(java.lang.Short)
	 */
	public void setOrder(Short order) {
		this.order = order;
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
	 * @return section related questions
	 */
	public List<Question> getQuestions() {
		return questions;
	}

	/**
	 * @param questions
	 */
	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	/**
	 * @return list of answers to questions
	 */
	public List<Answer> getAnswers() {
		return answers;
	}

	public Boolean getRender() {
		return render;
	}

	public void setRender(Boolean render) {
		this.render = render;
	}

	/**
	 * @param answers
	 */
	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public String getConditions() {
		return conditions;
	}

	public void setConditions(String conditions) {
		this.conditions = conditions;
	}

	public Label getLabel() {
		return label;
	}

	public void setLabel(Label label) {
		this.label = label;
	}
}