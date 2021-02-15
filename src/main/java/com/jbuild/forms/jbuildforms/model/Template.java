package com.jbuild.forms.jbuildforms.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne; 
import javax.persistence.Table;
import javax.persistence.Transient;

import com.jbuild.forms.jbuildforms.enumeration.TemplateName;
import com.jbuild.forms.jbuildforms.enumeration.TemplateType;
import com.jbuild.forms.jbuildforms.model.tools.Language;


@Entity
@Table(name = "JBF_TEMPLATE")
public class Template implements Serializable {
	private static final long serialVersionUID = 4076329083720081930L;

	/** The Template identifier. */
	private Integer id;

	/**
	 * Template type; used to group templates under the same group (e.g. group
	 * of all email templates)
	 */
	private TemplateType type;

	/**
	 * Template name; e.g. a template can be of type email and have name
	 * "MEDICAL_APPOINTMENTS_BOOKED"
	 */
	private TemplateName name;

	/** Body of template on {@link #language} */
	private String body;

	/** The Template language. */
	private Language language;

	/** The Template language id. */
	private Integer languageId;

	/** Transient field, representing the body after replacement of Velocity parameters. */
	private String translatedBody = "";

	/** Active (0/1). */
	private Boolean isActive;

	/** The Template subject. */
	private String templateSubject;

	/** Transient field, representing the subject after replacement of Velocity parameters. */
	private String translatedSubject = "";

	/** Display-order. */
	private Integer displayOrder;

	public Template() {
		super();
	}

	/**
	 * @param copyTemplate
	 * @param pIsActive
	 */
	public Template(final Template copyTemplate, final Boolean pIsActive) {
		super();
		this.type = copyTemplate.getType();
		this.name = copyTemplate.getName();
		this.body = copyTemplate.getBody();
		this.languageId = copyTemplate.getLanguageId();
		this.isActive = pIsActive;
		this.templateSubject = copyTemplate.getTemplateSubject();
		this.displayOrder = copyTemplate.getDisplayOrder();
	}

	@Id
	//@SequenceGenerator(name = "TEMPLATE_SEQUENCE_GENERATOR", sequenceName = "XXHR_IHRM_TEMPLATE_SEQ", initialValue = 1000, allocationSize = 1)
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TEMPLATE_SEQUENCE_GENERATOR")
	@Column(name = "ID")
	 @GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "TEMPLATE_TYPE", length = 100)
	public TemplateType getType() {
		return type;
	}

	public void setType(TemplateType type) {
		this.type = type;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "TEMPLATE_NAME", length = 100)
	public TemplateName getName() {
		return name;
	}

	public void setName(TemplateName name) {
		this.name = name;
	}

	@Lob
	@Column(name = "TEMPLATE_BODY")
	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	@OneToOne
	@JoinColumn(name = "LANGUAGE_ID", insertable = false, updatable = false)
	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	/**
	 * @return the languageId
	 */
	@Column(name = "LANGUAGE_ID", insertable = true, updatable = true)
	public Integer getLanguageId() {
		return languageId;
	}

	/**
	 * @param languageId
	 *            the languageId to set
	 */
	public void setLanguageId(Integer languageId) {
		this.languageId = languageId;
	}

	/**
	 * @return the translatedBody
	 */
	@Transient
	public String getTranslatedBody() {
		return translatedBody;
	}

	/**
	 * @param translatedBody
	 *            the translatedBody to set
	 */
	public void setTranslatedBody(String translatedBody) {
		this.translatedBody = translatedBody;
	}

	@Column(name = "IS_ACTIVE")
	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	/**
	 * @return the templateSubject
	 */
	@Lob
	@Column(name = "TEMPLATE_SUBJECT")
	public String getTemplateSubject() {
		return templateSubject;
	}

	/**
	 * @param templateSubject
	 *            the templateSubject to set
	 */
	public void setTemplateSubject(String templateSubject) {
		this.templateSubject = templateSubject;
	}

	/**
	 * @return the translatedSubject
	 */
	@Transient
	public String getTranslatedSubject() {
		return translatedSubject;
	}

	/**
	 * @param translatedSubject
	 *            the translatedSubject to set
	 */
	public void setTranslatedSubject(String translatedSubject) {
		this.translatedSubject = translatedSubject;
	}

	/**
	 * @return the displayOrder
	 */
	@Column(name = "DISPLAY_ORDER")
	public Integer getDisplayOrder() {
		return displayOrder;
	}

	/**
	 * @param displayOrder
	 *            the displayOrder to set
	 */
	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}

	/**
	 * Returns a string representation for this instance.
	 * 
	 * @return <code>String</code> The string representation of this instance.
	 */
	@Override
	public String toString() {
		return "eu.europa.europarl.apapeople.model.Template[id=" + id + "]";
	}

	/**
	 * @return <code>int</code> Hash value for the caller object.
	 */
	@Override
	public int hashCode() {
		int hash = 0;
		if (id != null) {
			hash = id.hashCode();
		} else {
			hash = super.hashCode();
		}
		return hash;
	}

	/**
	 * Checks equality by id.
	 * 
	 * @param object
	 *            <code>Object</code>
	 * @return <code>boolean</code>
	 */
	@Override
	public boolean equals(final Object object) {
		boolean isEqual = false;
		if (object instanceof Template) {
			Template other = (Template) object;
			if (this == other) {
				isEqual = true;
			} else if (other.getId() != null && id != null && other.getId().equals(id)) {
				isEqual = true;
			}
		}
		return isEqual;
	}
}