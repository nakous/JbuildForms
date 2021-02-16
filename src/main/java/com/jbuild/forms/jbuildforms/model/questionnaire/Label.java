package com.jbuild.forms.jbuildforms.model.questionnaire;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import com.thoughtworks.xstream.converters.extended.ToAttributedValueConverter;

/**
 * Represents a text, in a particular language. Ex. The label of a section/question in English
 * 
 */
@XStreamAlias("label")
@XStreamConverter(value = ToAttributedValueConverter.class, strings = { "text" })
public class Label implements Serializable {
	private static final long serialVersionUID = -684689284900297409L;

	public static final String NO_LANGUAGE = "N/A";

	private String text;

	/**
	 * the language ISO code
	 */
	@XStreamAsAttribute
	@XStreamAlias("language")
	private String language;

	/**
	 * if set to true, then no need for multiple languages, language will be set to "N/A"
	 */
	@XStreamAlias("is-translated")
	private Boolean isTranslated;

	
	
	
	/**
	 * used to display or not the label in view, with @XStreamOmitField means that this attribute is not mapped in XML,used only in java
	 * */
	@XStreamOmitField
	private Boolean isDisplayed;
	/**
	 * default constructor
	 */
	public Label() {
		super();
	}

	/**
	 * constructor with language and text parameters
	 * 
	 * @param language
	 * @param text
	 */
	public Label(String language, String text) {
		super();
		this.language = language;
		this.text = text;
	}

	/**
	 * @return language ISO code
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * @param language
	 */
	public void setLanguage(String language) {
		this.language = language;
	}

	/**
	 * @return the translated text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text
	 */
	public void setText(String text) {
		this.text = text;
	}

	public Boolean getIsTranslated() {
		return isTranslated;
	}

	public void setIsTranslated(Boolean isTranslated) {
		this.isTranslated = isTranslated;
	}
	

	public Boolean getIsDisplayed() {
		return isDisplayed;
	}

	public void setIsDisplayed(Boolean isDisplayed) {
		this.isDisplayed = isDisplayed;
	}
}