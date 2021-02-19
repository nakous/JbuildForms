package com.jbuild.forms.jbuildforms.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jbuild.forms.jbuildforms.model.DossierStatusType;
import com.jbuild.forms.jbuildforms.model.ProcessStatusTypeProperty;
import com.jbuild.forms.jbuildforms.model.Process;

@Repository
public interface ProcessStatusTypePropertyDao extends CrudRepository<ProcessStatusTypeProperty, Integer> {

	@Query( "SELECT pstp from ProcessStatusTypeProperty As pstp WHERE pstp.name = :name AND pstp.process = :process AND pstp.dossierStatusType = :dossierStatusType")
	ProcessStatusTypeProperty ProcessStatusTypePropertyByPdn(@Param("process") Process process,@Param("dossierStatusType") DossierStatusType dossierStatusType,@Param("name")  String name );
	
	Optional<ProcessStatusTypeProperty> findByName(String name);
}
