package com.jbuild.forms.jbuildforms.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.jbuild.forms.jbuildforms.enumeration.AccessPermission;
 
@Entity
@Table(name = "JBF_PROCESS_STEP_ROLE")
public class ProcessStepRole implements Serializable {
	private static final long serialVersionUID = 5916574135949322066L;

	/**
	 * Column name ID
	 */
	private Integer id;

	/**
	 * Column name processId
	 */
	private Integer processId;

	/**
	 * Column name dossier_status_type_id
	 */
	private Integer dossierStatusTypeId;

	/**
	 * Column name role
	 */
	private String role;

	/**
	 * Read / write
	 */
	private AccessPermission accessPermission;

	@Id
	 @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "ID")
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "PROCESS_ID", nullable = false)
	public Integer getProcessId() {
		return this.processId;
	}

	public void setProcessId(Integer processId) {
		this.processId = processId;
	}

	@Column(name = "DOSSIER_STATUS_TYPE_ID", nullable = false)
	public Integer getDossierStatusTypeId() {
		return this.dossierStatusTypeId;
	}

	public void setDossierStatusTypeId(Integer dossierStatusTypeId) {
		this.dossierStatusTypeId = dossierStatusTypeId;
	}

	@Column(name = "ROLE", nullable = false)
	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "PERMISSION")
	public AccessPermission getAccessPermission() {
		return accessPermission;
	}

	public void setAccessPermission(AccessPermission accessPermission) {
		this.accessPermission = accessPermission;
	}

	@Override
	public String toString() {
		return "ProcessStepRole [id=" + id + ", processId=" + processId + ", dossierStatusTypeId=" + dossierStatusTypeId + ", role=" + role + "]";
	}
}