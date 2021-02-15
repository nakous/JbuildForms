package com.jbuild.forms.jbuildforms.model;

import java.io.Serializable;
import java.util.Date;

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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.jbuild.forms.jbuildforms.enumeration.DossierResult;
import com.jbuild.forms.jbuildforms.tools.DateUtils;

 

/** Logging all the {@link Dossier} status changes. The current status is the {@link } */
@Entity
@Table(name = "JBF_DOSSIER_STATUS")
public class DossierStatus implements Serializable {
	private static final long serialVersionUID = 8863689998032954864L;

	private Integer id;

	private Dossier dossier;

	/** Date the {@link status} was set */
	private Date dossierStatusDate;

	private DossierStatusType status;

	/**
	 * URL that should be visited to fulfill current status and the workflow to
	 * progress to next status.
	 */
	private String url;

	private String byUser;

	private DossierResult result;

	private Process process;

	// Gets populated by TranslateAspect
	private String translation;

	/**
	 * Messages to be sent to the next flow step As XML
	 * Check WorkflowMessageList for information about XML structure
	 */
	private String workflowMessages;

	public DossierStatus() {
		super();
	}

	public DossierStatus(Dossier dossier, DossierStatusType status, Date dossierStatusDate, Process process, String workflowMessages) {
		this.dossier = dossier;
		this.status = status;
		this.dossierStatusDate = DateUtils.getDateCopy(dossierStatusDate);
		this.process = process;
		this.workflowMessages = workflowMessages;
	}

	@Id
	//@SequenceGenerator(name = "DOSSIER_STATUS_SEQUENCE_GENERATOR", sequenceName = "XXHR_IHRM_DOSSIER_STATUS_SEQ", initialValue = 1000, allocationSize = 1)
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DOSSIER_STATUS_SEQUENCE_GENERATOR")
	@Column(name = "ID")
	 @GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DOSSIER_ID", nullable = false)
	public Dossier getDossier() {
		return dossier;
	}

	public void setDossier(Dossier dossier) {
		this.dossier = dossier;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DOSSIER_STATUS_DATE")
	public Date getDossierStatusDate() {
		return DateUtils.getDateCopy(dossierStatusDate);
	}

	public void setDossierStatusDate(final Date dossierStatusDate) {
		this.dossierStatusDate = DateUtils.getDateCopy(dossierStatusDate);
	}

	@ManyToOne(targetEntity = DossierStatusType.class, optional = true, fetch = FetchType.EAGER)
	@JoinColumn(name = "DOSSIER_STATUS_TYPE_ID", nullable = true, updatable = false)
	public DossierStatusType getStatus() {
		return status;
	}

	public void setStatus(DossierStatusType status) {
		this.status = status;
	}

	@Column(name = "BY_USER", length = 100)
	public String getByUser() {
		return byUser;
	}

	public void setByUser(String byUser) {
		this.byUser = byUser;
	}

	@Column(name = "URL", length = 50)
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@ManyToOne(targetEntity = Process.class, optional = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "PROCESS_ID", nullable = true, updatable = false)
	public Process getProcess() {
		return process;
	}

	public void setProcess(Process process) {
		this.process = process;
	}

	@Transient
	public String getTranslation() {
		return translation;
	}

	public void setTranslation(String translation) {
		this.translation = translation;
	}

	/**
	 * Returns a string representation for this instance.
	 * 
	 * @return <code>String</code> The string representation of this instance.
	 */
	@Override
	public String toString() {
		return "eu.europa.europarl.apapeople.model.DossierStatus[id=" + id + "]";
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
		if (object instanceof DossierStatus) {
			DossierStatus other = (DossierStatus) object;
			if (this == other) {
				isEqual = true;
			} else if (other.getId() != null && id != null && other.getId().equals(id)) {
				isEqual = true;
			}
		}
		return isEqual;
	}

	@Column(name = "result", length = 50)
	@Enumerated(EnumType.STRING)
	public DossierResult getResult() {
		return result;
	}

	public void setResult(DossierResult result) {
		this.result = result;
	}

	@Lob
	@Column(name = "WORKFLOW_MESSAGES")
	public String getWorkflowMessages() {
		return workflowMessages;
	}

	public void setWorkflowMessages(String workflowMessages) {
		this.workflowMessages = workflowMessages;
	}
}