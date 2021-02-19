package com.jbuild.forms.jbuildforms.model.tools;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "JBF_LANGUAGE")
public class Language implements Serializable {
	private static final long serialVersionUID = -2964077385499766106L;

	
	private Integer id;

	private String name;

	/** ISO 639 locale code */
	private String isoCode;
	
	/** Label displayed in the user interface */
	private String label;

	public Language() {
		super();
	}

	public Language(String name, String isoCode) {
		this.name = name;
		this.isoCode = isoCode;
	}

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "NAME", nullable = false, length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "ISO_CODE", nullable = false, length = 2)
	public String getIsoCode() {
		return this.isoCode;
	}

	public void setIsoCode(String isoCode) {
		this.isoCode = isoCode;
	}
	
	@Column(name = "LABEL", nullable = false, length = 100)
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * Returns a string representation for this instance.
	 * 
	 * @return <code>String</code> The string representation of this instance.
	 */
	@Override
	public String toString() {
		return "model.reference.Language[id=" + id + "]";
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
		if (object instanceof Language) {
			Language other = (Language) object;
			if (this == other) {
				isEqual = true;
			} else if (other.getId() != null && id != null && other.getId().equals(id)) {
				isEqual = true;
			}
		}
		return isEqual;
	}
}