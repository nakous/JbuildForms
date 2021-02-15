package com.jbuild.forms.jbuildforms.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "JBF_MENU_PROGRESS_BAR")
public class MenuProgressBar implements Serializable{

	private static final long serialVersionUID = -6182931711156657387L;
	private Integer id;
	private Integer dossierStatus;
	private String role;
	private Integer process;
	private String menu;
	
	@Id
	 @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "ID")
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
	@Column(name = "D_STATUS")
	public Integer getDossierStatus() {
		return dossierStatus;
	}

	public void setDossierStatus(Integer dossierStatus) {
		this.dossierStatus = dossierStatus;
	}

	@Column(name = "ROLE")
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Column(name = "PROCESS")
	public Integer getProcess() {
		return process;
	}

	public void setProcess(Integer process) {
		this.process = process;
	}

	@Column(name = "MENU")
	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}
	

	@Override
	public String toString() {
		return "MenuProgressBar: [ID="+id+"  D_STATUS="+dossierStatus+"  ROLE="+role+"  PROCESS="+process+"  MENU="+menu+"]";
	}
}
