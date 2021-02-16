package com.jbuild.forms.jbuildforms.model.questionnaire;

import java.io.Serializable;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("group")
public class Group implements Serializable, Orderable {
	private static final long serialVersionUID = -684689284900297409L;

	/**
	 * Unique id
	 */
	@XStreamAsAttribute
	@XStreamAlias("id")
	private Integer id;

	/**
	 * group type
	 */
	@XStreamAsAttribute
	@XStreamAlias("type")
	private String type;

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
	 * translations for the section name
	 */
	@XStreamAlias("labels")
	private List<Label> labels;

	/**
	 * the section related questions
	 */
	@XStreamAlias("documents")
	private List<Document> documents;

	/**
	 * a velocity expression to decide whether to enable the group or not. default is NULL which means, true ...
	 */
	@XStreamAlias("valid-when")
	private String validWhen;

	/**
	 * minimum number of documents to display
	 */
	@XStreamAsAttribute
	@XStreamAlias("min")
	private Short min;

	/**
	 * maximum number of documents to display
	 */
	@XStreamAsAttribute
	@XStreamAlias("max")
	private Short max;

	/**
	 * a single translated label in application messages
	 */
	@XStreamAlias("info")
	private String info;

	/**
	 * default constructor
	 */
	public Group() {
		super();
	}

	public Integer getId() {
		return id;
	}

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

	public Label getLabel() {
		return label;
	}

	public void setLabel(Label label) {
		this.label = label;
	}

	public List<Label> getLabels() {
		return labels;
	}

	public void setLabels(List<Label> labels) {
		this.labels = labels;
	}

	public List<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}

	public String getValidWhen() {
		return validWhen;
	}

	public void setValidWhen(String validWhen) {
		this.validWhen = validWhen;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Short getMin() {
		return min;
	}

	public void setMin(Short min) {
		this.min = min;
	}

	public Short getMax() {
		return max;
	}

	public void setMax(Short max) {
		this.max = max;
	}

	/**
	 * @return String
	 */
	public String getInfo() {
		return info;
	}

	/**
	 * @param info
	 */
	public void setInfo(String info) {
		this.info = info;
	}
}