package com.jbuild.forms.jbuildforms.model.questionnaire;

import java.io.Serializable;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

@XStreamAlias("question")
public class Question implements Serializable, Orderable {
	private static final long serialVersionUID = -684689284900297409L;

	/**
	 * unique id
	 */
	@XStreamAsAttribute
	@XStreamAlias("id")
	private Integer id;

	/**
	 * the order is used for display purposes when the question is rendered in the GUI
	 */
	@XStreamAsAttribute
	@XStreamAlias("order")
	private Short order;

	/**
	 * the question type
	 */
	@XStreamAsAttribute
	@XStreamAlias("type")
	private String type;

	/**
	 * how to render a question of type list: ex: radio, check, drop, list
	 */
	@XStreamAsAttribute
	@XStreamAlias("appearence")
	private String appearence;

	/**
	 * translations for the section name
	 * labels is used with questions having 
	 * -appearance = subsection
	 * 		* label[0] is added to subsection label
	 * -appearance = link
	 * 		* label[0] is put inside "<a>" tag
	 * 		* label[1] is put inside "<a>" tag
	 */
	@XStreamAlias("labels")
	private List<Label> labels;

	/**
	 * a single translated label in application messages
	 */
	@XStreamAlias("label")
	private Label label;

	@XStreamAlias("options")
	private List<Option> options;

	@XStreamAsAttribute
	@XStreamAlias("mandatory")
	private Boolean mandatory;
	
	@XStreamAsAttribute
	@XStreamAlias("is-numeric")
	private Boolean isNumeric = false;

	@XStreamAsAttribute
	@XStreamAlias("multiple")
	private Boolean multiple;

	@XStreamAsAttribute
	@XStreamAlias("options-bean-name")
	private String optionsBeanName;

	@XStreamAsAttribute
	@XStreamAlias("options-bean-trasnlated")
	private Boolean optionsBeanIsTranslated;
	
	@XStreamAsAttribute
	@XStreamAlias("query")
	private String query;

	@XStreamAsAttribute
	@XStreamAlias("decale-in-view")
	private Boolean decaleInView;
	
	/**
	 * a boolean value. if true then the question is displayed in new line.
	 */
	@XStreamAsAttribute
	@XStreamAlias("new-line-decale")
	private Boolean newLineDecale;
	

	/**
	 * a velocity expression to decide whether to display the question or not. default is NULL which means, always ...
	 */
	@XStreamAlias("render-when")
	private String renderWhen;

	/**
	 * a velocity expression to decide whether to enable the question or not. default is NULL which means, always ...
	 */
	@XStreamAlias("enabled-when")
	private String enabledWhen;

	@XStreamAlias("default-value-when")
	private String defaultValueWhen;

	@XStreamOmitField
	private String defaultValue;

	/**
	 * what to display
	 */
	@XStreamOmitField
	private Boolean render;

	/**
	 * enabled / disabled
	 */
	@XStreamOmitField
	private Boolean enabled;
	
	/**
	 * Security feature: controls if the field should be editable. Different from enabled. Should use a label instead of the input element. 
	 */
	@XStreamOmitField
	private Boolean accessible;
	
	/**
	 * Security feature: controls if the field should be displayed. Different from render. Should completely omit the field from the output.
	 */
	@XStreamOmitField
	private Boolean visible;

	@XStreamOmitField
	private String dependsOnQuestion;

	@XStreamAlias("validations")
	private List<Validation> validations;
	
	/**
	 * Used for list, checkbox and radio buttons to define datatype of the answer to be stored in database
	 */
	@XStreamAsAttribute
	@XStreamAlias("storage-data-type")
	private String storageDataType;
	
	@XStreamAsAttribute
	@XStreamAlias("dossierProperties")
	private String dossierPoperties;
	
	/**
	 * Question access rights
	 */
	@XStreamAlias("access-rights")
	private List<AccessRight> accessRights;
	
	
	/**
	 * default constructor
	 */
	public Question() {
		super();
		this.render = Boolean.TRUE;
	}

	/**
	 * @return unique id
	 */
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

	/**
	 * @param order
	 */
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAppearence() {
		return appearence;
	}

	public void setAppearence(String appearence) {
		this.appearence = appearence;
	}

	public Boolean getMandatory() {
		return mandatory;
	}

	public void setMandatory(Boolean mandatory) {
		this.mandatory = mandatory;
	}

	public Boolean getIsNumeric() {
		return isNumeric;
	}

	public void setIsNumeric(Boolean isNumeric) {
		this.isNumeric = isNumeric;
	}
	
	public Label getLabel() {
		return label;
	}

	public void setLabel(Label label) {
		this.label = label;
	}

	public Boolean getMultiple() {
		return multiple;
	}

	public void setMultiple(Boolean multiple) {
		this.multiple = multiple;
	}

	public List<Option> getOptions() {
		return options;
	}

	public void setOptions(List<Option> options) {
		this.options = options;
	}

	public String getOptionsBeanName() {
		return optionsBeanName;
	}

	public void setOptionsBeanName(String optionsBeanName) {
		this.optionsBeanName = optionsBeanName;
	}

	public String getRenderWhen() {
		return renderWhen;
	}

	public void setRenderWhen(String renderWhen) {
		this.renderWhen = renderWhen;
	}

	public Boolean getRender() {
		return render;
	}

	public void setRender(Boolean render) {
		this.render = render;
	}

	public Boolean getOptionsBeanIsTranslated() {
		return optionsBeanIsTranslated;
	}

	public void setOptionsBeanIsTranslated(Boolean optionsBeanIsTranslated) {
		this.optionsBeanIsTranslated = optionsBeanIsTranslated;
	}

	public String getEnabledWhen() {
		return enabledWhen;
	}

	public void setEnabledWhen(String enabledWhen) {
		this.enabledWhen = enabledWhen;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * @return the dependsOnQuestion
	 */
	public String getDependsOnQuestion() {
		return dependsOnQuestion;
	}

	/**
	 * @param dependsOnQuestion
	 *            the dependsOnQuestion to set
	 */
	public void setDependsOnQuestion(String dependsOnQuestion) {
		this.dependsOnQuestion = dependsOnQuestion;
	}

	public String getDefaultValueWhen() {
		return defaultValueWhen;
	}

	public void setDefaultValueWhen(String defaultValueWhen) {
		this.defaultValueWhen = defaultValueWhen;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public List<Validation> getValidations() {
		return validations;
	}

	public void setValidations(List<Validation> validations) {
		this.validations = validations;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getStorageDataType() {
		return storageDataType;
	}

	public void setStorageDataType(String storageDataType) {
		this.storageDataType = storageDataType;
	}

	public Boolean getDecaleInView() {
		return decaleInView;
	}

	public void setDecaleInView(Boolean decaleInView) {
		this.decaleInView = decaleInView;
	}

	public String getDossierPoperties() {
		return dossierPoperties;
	}

	public void setDossierPoperties(String dossierPoperties) {
		this.dossierPoperties = dossierPoperties;
	}
	
	public List<AccessRight> getAccessRights() {
		return accessRights;
	}

	public void setAccessRights(List<AccessRight> accessRights) {
		this.accessRights = accessRights;
	}
	
	public Boolean getAccessible() {
		return accessible;
	}

	public void setAccessible(Boolean accessible) {
		this.accessible = accessible;
	}

	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	public Boolean getNewLineDecale() {
		return newLineDecale;
	}

	public void setNewLineDecale(Boolean newLineDecale) {
		this.newLineDecale = newLineDecale;
	}
	
	
	
}