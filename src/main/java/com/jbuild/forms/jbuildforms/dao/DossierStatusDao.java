package com.jbuild.forms.jbuildforms.dao;

 

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jbuild.forms.jbuildforms.model.DossierStatus;
import com.jbuild.forms.jbuildforms.model.Process;;

 

@Repository
public interface DossierStatusDao  extends CrudRepository<DossierStatus, Integer>{
	
	@Query("SELECT status FROM DossierStatus AS status INNER JOIN status.dossier AS dossier WHERE dossier.id = :dossierId AND status.process = :process ORDER BY dossier.id desc, status.dossierStatusDate DESC")
	public DossierStatus findCurrentDossierStatusByDossierIdAndProcess(Integer dossierId, Process process) ;

}
