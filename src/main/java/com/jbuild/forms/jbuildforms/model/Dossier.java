package com.jbuild.forms.jbuildforms.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jbuild.forms.jbuildforms.tools.DateUtils;



@Entity
@Table(name = "JBF_DOSSIER")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Dossier implements Serializable {
	private static final long serialVersionUID = -2964077385499766106L;

	private Integer id;

	private BigDecimal personId;

	@Column(name = "PERSON_ID", insertable = true, updatable = true)
	public BigDecimal getPersonId() {
		return personId;
	}

	public void setPersonId(BigDecimal personId) {
		this.personId = personId;

	}

	@JsonIgnore
	private Set<DossierStatus> statuses;
	/*
	@JsonIgnore
	private Set<DossierDocumentGroup> documentGroups;

	private Set<DossierProperty> dossierProperties;
*/
	private Date creationDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATION_DATE")
	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		  this.creationDate = DateUtils.getDateCopy(creationDate);
		 
	}

	public Dossier() {
		super();
	}

	@Id
	//@SequenceGenerator(name = "DOSSIER_SEQUENCE_GENERATOR", sequenceName = "XXHR_IHRM_DOSSIER_SEQ", initialValue = 1000, allocationSize = 1)
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DOSSIER_SEQUENCE_GENERATOR")
	@Column(name = "ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * When {@link Dossier} is removed its {@link DossierStatus}es' are also
	 * removed
	 */
	@OneToMany(mappedBy = "dossier", orphanRemoval = true)
	@Cascade({ CascadeType.ALL })
	public Set<DossierStatus> getStatuses() {
		return statuses;
	}

	public void setStatuses(Set<DossierStatus> statuses) {
		this.statuses = statuses;
	}
	/**
	 * When {@link Dossier} is removed its {@link DossierDocumentGroup}s' are
	 * also removed
	 */
/*	@JsonIgnore
	@OneToMany(mappedBy = "dossier", orphanRemoval = true)

	public Set<DossierDocumentGroup> getDossierDocumentGroup() {
		return documentGroups;
	}

	@OneToMany(mappedBy = "dossier", orphanRemoval = true)
	@Cascade({ CascadeType.ALL })
	public Set<DossierProperty> getDossierProperties() {
		return dossierProperties;
	}

	public void setDossierProperties(Set<DossierProperty> dossierProperties) {
		this.dossierProperties = dossierProperties;
	}

	public void setDossierDocumentGroup(Set<DossierDocumentGroup> documentGroups) {
		this.documentGroups = documentGroups;
	}
	*/

	/**
	 * Returns a string representation for this instance.
	 * 
	 * @return <code>String</code> The string representation of this instance.
	 */
	@Override
	public String toString() {
		return "eu.europa.europarl.apapeople.model.Dossier[id=" + id + "]";
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
		if (object instanceof Dossier) {
			Dossier other = (Dossier) object;
			if (this == other) {
				isEqual = true;
			} else if (other.getId() != null && id != null && other.getId().equals(id)) {
				isEqual = true;
			}
		}
		return isEqual;
	}
}