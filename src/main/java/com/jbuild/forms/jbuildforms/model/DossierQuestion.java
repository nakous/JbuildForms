package com.jbuild.forms.jbuildforms.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.jbuild.forms.jbuildforms.enumeration.DossierQuestionStatusType;



@Entity
@Table(name = "JBF_DOSSIER_QUESTION")
public class DossierQuestion implements Serializable {
	private static final long serialVersionUID = -8884665549962432440L;

	private Integer id;

	private Dossier dossier;

	private Template template;

	private Integer dossierId;

	private Integer templateId;

	private String answerBody;

	private DossierQuestionStatusType status;

	public DossierQuestion() {
		super();
	}

	public DossierQuestion(Integer dossierId) {
		this.dossierId = dossierId;
	}

	public DossierQuestion(Integer dossierId, Integer templateId) {
		this.dossierId = dossierId;
		this.templateId = templateId;
	}

	@Id
	//@SequenceGenerator(name = "DOSSIER_QUESTION_SEQUENCE_GENERATOR", sequenceName = "XXHR_IHRM_DOSSIER_QUESTION_SEQ", initialValue = 1000, allocationSize = 1)
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DOSSIER_QUESTION_SEQUENCE_GENERATOR")
	@Column(name = "ID")
	 @GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@OneToOne
	@JoinColumn(name = "TEMPLATE_ID", updatable = false, insertable = false)
	public Template getTemplate() {
		return template;
	}

	public void setTemplate(Template template) {
		this.template = template;
	}

	@OneToOne
	@JoinColumn(name = "DOSSIER_ID", updatable = false, insertable = false)
	public Dossier getDossier() {
		return dossier;
	}

	public void setDossier(Dossier dossier) {
		this.dossier = dossier;
	}

	@Column(name = "TEMPLATE_ID", nullable = false)
	public Integer getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}

	@Column(name = "DOSSIER_ID", nullable = false)
	public Integer getDossierId() {
		return dossierId;
	}

	public void setDossierId(Integer dossierId) {
		this.dossierId = dossierId;
	}

	/**
	 * Important! fetching is lazy
	 * 
	 * @return
	 */
	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(name = "ANSWER_BODY")
	public String getAnswerBody() {
		return answerBody;
	}

	public void setAnswerBody(String answerBody) {
		this.answerBody = answerBody;
	}

	/**
	 * Returns a string representation for this instance.
	 * 
	 * @return <code>String</code> The string representation of this instance.
	 */
	@Override
	public String toString() {
		return "eu.europa.europarl.apapeople.model.DossierQuestion[id=" + id + "]";
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
		if (object instanceof DossierQuestion) {
			DossierQuestion other = (DossierQuestion) object;
			if (this == other) {
				isEqual = true;
			} else if (other.getId() != null && id != null && other.getId().equals(id)) {
				isEqual = true;
			}
		}
		return isEqual;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "STATUS")
	public DossierQuestionStatusType getStatus() {
		return status;
	}

	public void setStatus(DossierQuestionStatusType status) {
		this.status = status;
	}
}