package com.jbuild.forms.jbuildforms.model;

import java.io.Serializable;

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
@Table(name = "JBF_DOSSIER_STATUS_TYPE")
public class DossierStatusType implements Serializable {
	private static final long serialVersionUID = -279467170362025643L;

	/*
	 * Column name ID
	 */
	private Integer id;

	/*
	 * Column name NAME
	 */
	private String name;

	/*
	 * Column name PREFIX
	 */
	private String prefix;

	private String placement;
	
	private Process process;
	
	@ManyToOne(targetEntity = Process.class, optional = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "PROCESS_ID", nullable = true, updatable = false)
	public Process getProcess() {
		return process;
	}
	
	public void setProcess(Process process) {
		this.process = process;
	}


	public DossierStatusType() {
		super();
	}

	@Id
	 @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "ID")
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "NAME", nullable = false, length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "PREFIX", nullable = false, length = 100)
	public String getPrefix() {
		return this.prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	@Column(name = "PLACEMENT", nullable = false, length = 100)
	public String getPlacement() {
		return placement;
	}

	public void setPlacement(String placement) {
		this.placement = placement;
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
		if (object instanceof DossierStatusType) {
			DossierStatusType other = (DossierStatusType) object;
			if (this == other) {
				isEqual = true;
			} else if (other.getId() != null && id != null && other.getId().equals(id)) {
				isEqual = true;
			}
		}
		return isEqual;
	}
}