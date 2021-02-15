package com.jbuild.forms.jbuildforms.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "JBF_VALUE_SET")
public class ValueSet implements Serializable {
	private static final long serialVersionUID = -2964077385499766106L;

	/*
	 * Column name ID
	 */
	private Integer id;

	/*
	 * Column name LABEL
	 */
	private String label;

	/*
	 * Column name GROUP_ID
	 */
	private Integer groupId;

	/*
	 * Column name LANGUAGE_ID
	 */
	private Integer languageId;

	/*
	 * Column name EXTERNAL_ID
	 */
	private String externalId;

	public ValueSet() {
		super();
	}

	public ValueSet(String label, Integer groupId) {
		this.label = label;
		this.groupId = groupId;
	}

	@Id
	@Column(name = "ID")
	 @GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "LABEL", nullable = false, length = 50)
	public String getLabel() {
		return this.label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@Column(name = "GROUP_ID")
	public Integer getGroupId() {
		return this.groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	@Column(name = "LANGUAGE_ID")
	public Integer getLanguageId() {
		return this.languageId;
	}

	public void setLanguageId(Integer languageId) {
		this.languageId = languageId;
	}

	@Column(name = "EXTERNAL_ID")
	public String getExternalId() {
		return this.externalId;
	}

	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}
}