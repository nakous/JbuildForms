package com.jbuild.forms.jbuildforms.service;

import java.text.MessageFormat;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.stereotype.Component;

import com.jbuild.forms.jbuildforms.dao.LanguageDao;
import com.jbuild.forms.jbuildforms.dao.MessagesLanguageDao;
import com.jbuild.forms.jbuildforms.model.tools.Language;
import com.jbuild.forms.jbuildforms.model.tools.MessagesLanguage;

@Component("messageSource")
public class DBMessageSource extends AbstractMessageSource  {
	
	@Autowired
	MessagesLanguageDao messagesLanguageDao;
	
	@Autowired
	LanguageDao languageDao;
	
	private static final String DEFAULT_LOCALE_CODE = "FR";

	@Override
	protected MessageFormat resolveCode(String code, Locale locale) {
		// TODO Auto-generated method stub
		Language language= languageDao.findByIsoCode(locale.getLanguage());
		if(language == null) {
			language= languageDao.findByIsoCode(DEFAULT_LOCALE_CODE);
		}
		MessagesLanguage messagesLanguage = messagesLanguageDao.findByKeyAndLocale(code, language);
		if(messagesLanguage == null) {
			MessagesLanguage messagesLanguageNew = new MessagesLanguage(code, "", language);
			messagesLanguageDao.save(messagesLanguageNew);
			return new MessageFormat(code, locale);
		}
		if(messagesLanguage.getContent().isEmpty()) {
			return new MessageFormat(code, locale);
		}
		
		return new MessageFormat(messagesLanguage.getContent(), locale);
	}

}
