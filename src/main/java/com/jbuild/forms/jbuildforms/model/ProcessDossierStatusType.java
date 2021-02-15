package com.jbuild.forms.jbuildforms.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.jbuild.forms.jbuildforms.enumeration.TransitionName;
 

@Entity
@Table(name = "JBF_PROC_DOSS_STS_TYPE")
public class ProcessDossierStatusType implements Serializable {
	private static final long serialVersionUID = 5927932638643121553L;

	private Integer id;

	private Process process;

	private TransitionName name;

	private DossierStatusType fromDossierStatusType;

	private DossierStatusType toDossierStatusType;

	public ProcessDossierStatusType() {
		super();
	}

	@Id
	 @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "ID")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "PROCESS_ID")
	public Process getProcess() {
		return process;
	}

	public void setProcess(Process process) {
		this.process = process;
	}

	@Column(name = "NAME", nullable = false, length = 100)
	@Enumerated(EnumType.STRING)
	public TransitionName getName() {
		return this.name;
	}

	public void setName(TransitionName name) {
		this.name = name;
	}

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "FROM_DOSSIER_STATUS_TYPE_ID")
	public DossierStatusType getFromDossierStatusType() {
		return fromDossierStatusType;
	}

	public void setFromDossierStatusType(DossierStatusType fromDossierStatusType) {
		this.fromDossierStatusType = fromDossierStatusType;
	}

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "TO_DOSSIER_STATUS_TYPE_ID")
	public DossierStatusType getToDossierStatusType() {
		return toDossierStatusType;
	}

	public void setToDossierStatusType(DossierStatusType toDossierStatusType) {
		this.toDossierStatusType = toDossierStatusType;
	}

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
		if (object instanceof ProcessDossierStatusType) {
			ProcessDossierStatusType other = (ProcessDossierStatusType) object;
			if (this == other) {
				isEqual = true;
			} else if (other.getId() != null && id != null && other.getId().equals(id)) {
				isEqual = true;
			}
		}
		return isEqual;
	}
}