package com.jbuild.forms.jbuildforms.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jbuild.forms.jbuildforms.model.DossierStatusType;

@Repository
public interface DossierStatusTypeDao extends CrudRepository<DossierStatusType, Integer>{

	Optional<DossierStatusType>  findByName(String name);
}
