package com.jbuild.forms.jbuildforms.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
 
import com.jbuild.forms.jbuildforms.model.ProcessDossierStatusType;

@Repository
public interface ProcessDossierStatusTypeDao extends CrudRepository<ProcessDossierStatusType, Integer> {

	
}
