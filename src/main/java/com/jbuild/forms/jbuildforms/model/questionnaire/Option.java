package com.jbuild.forms.jbuildforms.model.questionnaire;

import java.io.Serializable;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * Represents an option in a list type question.
 *
 */
@XStreamAlias("option")
public class Option implements Serializable, Orderable {
	private static final long serialVersionUID = -684689284900297409L;

	/**
	 * the value for the option
	 */
	@XStreamAsAttribute
	@XStreamAlias("value")
	private String value;

	/**
	 * the order to render in the GUI, 1st 2nd etc
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
	 * translations for the option text
	 */
	@XStreamAlias("labels")
	private List<Label> labels;

	public Option() {
		super();
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
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

	public List<Label> getLabels() {
		return labels;
	}

	public void setLabels(List<Label> labels) {
		this.labels = labels;
	}

	public Label getLabel() {
		return label;
	}

	public void setLabel(Label label) {
		this.label = label;
	}
}