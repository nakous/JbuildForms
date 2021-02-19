package com.jbuild.forms.jbuildforms.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jbuild.forms.jbuildforms.model.Role;

@Repository
public interface RoleDao extends CrudRepository<Role, Long> {

}
