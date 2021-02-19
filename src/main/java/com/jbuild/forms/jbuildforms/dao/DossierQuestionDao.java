package com.jbuild.forms.jbuildforms.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jbuild.forms.jbuildforms.model.DossierQuestion;


@Repository
public interface DossierQuestionDao extends CrudRepository<DossierQuestion, Integer> {

	List<DossierQuestion> findByDossierId(Integer dossierId);
}
