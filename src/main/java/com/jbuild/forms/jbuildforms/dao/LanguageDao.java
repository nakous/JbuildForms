package com.jbuild.forms.jbuildforms.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jbuild.forms.jbuildforms.model.tools.Language;

@Repository
public interface LanguageDao extends CrudRepository<Language, Integer> {
	 Language  findByIsoCode(String isoCode);
}
