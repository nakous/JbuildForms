package com.jbuild.forms.jbuildforms.model;

import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;
import javax.persistence.Table;


@Entity
@Table(name="JBF_ROLE")
public class Role implements java.io.Serializable {

	private static final long serialVersionUID = 5828492693619230858L;
	
	@Id
	@Column(name= "ROLE_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long role_id;

	
	@Column(name= "NAME")
	private String name;
	
	@Column(name= "DESCRIPTION")
	private String description;

     
    @ManyToMany(mappedBy = "roles")
    private List<User> users;
    
    @ManyToMany
    @JoinTable(
        name = "JFB_roles_privileges", 
        joinColumns = @JoinColumn(
          name = "role_id", referencedColumnName = "role_id"), 
        inverseJoinColumns = @JoinColumn(
          name = "privilege_id", referencedColumnName = "id"))
    private Collection<Privilege> privileges;


	public Long getRole_id() {
		return role_id;
	}


	public void setRole_id(Long role_id) {
		this.role_id = role_id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public List<User> getUsers() {
		return users;
	}


	public void setUsers(List<User> users) {
		this.users = users;
	}
    
 


}
