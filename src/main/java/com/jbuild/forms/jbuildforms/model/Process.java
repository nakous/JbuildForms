package com.jbuild.forms.jbuildforms.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "JBF_PROCESS")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Process implements Serializable {
	private static final long serialVersionUID = 9074125516408900196L;

	private String statusPrefix;

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

	public Process() {
		super();
		statusPrefix = "";
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

	@Transient
	public String getStatusPrefix() {
		return statusPrefix;
	}

	public void setStatusPrefix(String value) {
		statusPrefix = value;
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
		if (object instanceof Process) {
			Process other = (Process) object;
			if (this == other) {
				isEqual = true;
			} else if (other.getId() != null && id != null && other.getId().equals(id)) {
				isEqual = true;
			}
		}
		return isEqual;
	}

	@Override
	public String toString() {
		return this.getName();
	}
}