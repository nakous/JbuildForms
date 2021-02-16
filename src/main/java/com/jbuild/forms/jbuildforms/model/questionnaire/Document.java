package com.jbuild.forms.jbuildforms.model.questionnaire;

import java.io.Serializable;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("document")
public class Document implements Serializable, Orderable {
	private static final long serialVersionUID = -684689284900297409L;

	/**
	 * the order is used for display purposes in the GUI
	 */
	@XStreamAsAttribute
	@XStreamAlias("order")
	private Short order;

	/**
	 * the document type, usually N/A
	 */
	@XStreamAsAttribute
	@XStreamAlias("type")
	private String type;

	/**
	 * a single translated label in application messages
	 */
	@XStreamAlias("label")
	private Label label;

	/**
	 * translations for the section name
	 */
	@XStreamAlias("labels")
	private List<Label> labels;

	/**
	 * produce multiple documents
	 */
	@XStreamAsAttribute
	@XStreamAlias("multiple")
	private Boolean multiple;

	/**
	 * minimum number of documents
	 */
	@XStreamAsAttribute
	@XStreamAlias("min")
	private Short min;

	/**
	 * maximum number of documents
	 */
	@XStreamAsAttribute
	@XStreamAlias("max")
	private Short max;

	/**
	 * which question to use as a multiplier for the generated documents. Usually this is a multiple selection question and we use the number of answers (value) as the multiplier
	 */
	@XStreamAsAttribute
	@XStreamAlias("multiplier-question-id")
	private String multiplierQuestionId;

	/**
	 * a velocity expression to decide whether to enable the group or not. default is NULL which means, true ...
	 */
	@XStreamAlias("valid-when")
	private String validWhen;

	@XStreamAsAttribute
	@XStreamAlias("multiplier-bean-name")
	private String multiplierBeanName;

	/**
	 * default constructor
	 */
	public Document() {
		super();
	}

	public String getMultiplierBeanName() {
		return multiplierBeanName;
	}

	public void setMultiplierBeanName(String multiplierBeanName) {
		this.multiplierBeanName = multiplierBeanName;
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
	 * @return document type, default is N/A
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return single translation
	 */
	public Label getLabel() {
		return label;
	}

	/**
	 * @param label
	 */
	public void setLabel(Label label) {
		this.label = label;
	}

	/**
	 * @return translation labels
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
	 * @return maximum number of documents
	 */
	public Short getMax() {
		return max;
	}

	/**
	 * @param max
	 */
	public void setMax(Short max) {
		this.max = max;
	}

	/**
	 * @return minimum number of documents
	 */
	public Short getMin() {
		return min;
	}

	/**
	 * @param min
	 */
	public void setMin(Short min) {
		this.min = min;
	}

	public Boolean getMultiple() {
		return multiple;
	}

	public void setMultiple(Boolean multiple) {
		this.multiple = multiple;
	}

	public String getMultiplierQuestionId() {
		return multiplierQuestionId;
	}

	public void setMultiplierQuestionId(String multiplierQuestionId) {
		this.multiplierQuestionId = multiplierQuestionId;
	}

	public String getValidWhen() {
		return validWhen;
	}

	public void setValidWhen(String validWhen) {
		this.validWhen = validWhen;
	}
}