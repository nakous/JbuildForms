package com.jbuild.forms.jbuildforms.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "JBF_PROC_STS_TYP_PRO")
public class ProcessStatusTypeProperty implements Serializable {
	private static final long serialVersionUID = 7775507806134422582L;

	private Integer id;

	private Process process;

	private DossierStatusType dossierStatusType;

	private String name;

	private String value;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	@JoinColumn(name = "PROCESS_ID")
	public Process getProcess() {
		return process;
	}

	public void setProcess(Process process) {
		this.process = process;
	}

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	@JoinColumn(name = "DOSSIER_STATUS_TYPE_ID")
	public DossierStatusType getDossierStatusType() {
		return dossierStatusType;
	}

	public void setDossierStatusType(DossierStatusType dossierStatusType) {
		this.dossierStatusType = dossierStatusType;
	}

	@Column(name = "NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "VALUE")
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
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
		if (object instanceof ProcessStatusTypeProperty) {
			ProcessStatusTypeProperty other = (ProcessStatusTypeProperty) object;
			if (this == other) {
				isEqual = true;
			} else if (other.getId() != null && id != null && other.getId().equals(id)) {
				isEqual = true;
			}
		}
		return isEqual;
	}
}