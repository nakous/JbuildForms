package com.jbuild.forms.jbuildforms.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "JBF_USER")
public class User implements Serializable {
	 
 
		private static final long serialVersionUID = 1L;

		@Id
		@Column(name = "USER_ID")
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long user_id;
		 
		@Column(name = "USER_NAME")
		private String user_name;
		
		@Column(name = "EMAIL")
		private String email;
		
		@Column(name = "LABEL")
		private String label;
		
		@Column(name = "LANG")
		private String lang;

		@ManyToMany 
		@JoinTable( 
	        name = "users_roles", 
	        joinColumns = @JoinColumn(
	          name = "user_id", referencedColumnName = "user_id"), 
	        inverseJoinColumns = @JoinColumn(
	          name = "role_id", referencedColumnName = "role_id")) 
	    private List<Role> roles;
		
		
		public Long getUser_id() {
			return user_id;
		}

		public void setUser_id(Long user_id) {
			this.user_id = user_id;
		}

		public String getUser_name() {
			return user_name;
		}

		public void setUser_name(String user_name) {
			this.user_name = user_name;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getLabel() {
			return label;
		}

		public void setLabel(String label) {
			this.label = label;
		}

		public String getLang() {
			return lang;
		}

		public void setLang(String lang) {
			this.lang = lang;
		}

		public List<Role> getRoles() {
			return roles;
		}

		public void setRoles(List<Role> roles) {
			this.roles = roles;
		}
		
		
		

}
