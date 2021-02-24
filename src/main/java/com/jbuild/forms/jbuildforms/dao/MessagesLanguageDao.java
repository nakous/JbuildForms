package com.jbuild.forms.jbuildforms.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jbuild.forms.jbuildforms.model.tools.Language;
import com.jbuild.forms.jbuildforms.model.tools.MessagesLanguage;

@Repository
public interface MessagesLanguageDao extends CrudRepository<MessagesLanguage, Integer> {
	
	MessagesLanguage findByKeyAndLocale(String key, Language locale);
}
