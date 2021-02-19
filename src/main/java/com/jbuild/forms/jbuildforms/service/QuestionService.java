package com.jbuild.forms.jbuildforms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jbuild.forms.jbuildforms.dao.DossierQuestionDao;
import com.jbuild.forms.jbuildforms.dao.TemplateDao;
import com.jbuild.forms.jbuildforms.enumeration.TemplateName;
import com.jbuild.forms.jbuildforms.enumeration.TemplateType;
import com.jbuild.forms.jbuildforms.model.Dossier;
import com.jbuild.forms.jbuildforms.model.DossierQuestion;
import com.jbuild.forms.jbuildforms.model.Template;
import com.jbuild.forms.jbuildforms.model.questionnaire.Questionnaire;
import com.jbuild.forms.jbuildforms.tools.QuestionnaireUtil;
import com.jbuild.forms.jbuildforms.tools.XStreamProvider;
import com.thoughtworks.xstream.XStream;

 
@Service("questionService")
public class QuestionService {

	@Autowired
	DossierQuestionDao dossierQuestionDao;
	
	@Autowired
	TemplateDao templateDao;

	public Questionnaire getQuestionnaire(String templateName, Dossier dossier) {

		
		Template templateQuestionnaire = null;
		//List<DossierQuestion> dossierQuestions = dossierQuestionDao.findByDossierId(dossier.getId());
		/* 
		List<Template> templates = templateDao.findActiveTemplatesByTypePlusInactive(TemplateType.QUESTION, TemplateName.valueOf(templateName), null);

		// Find question template and relates answers
		for (DossierQuestion dossierQuestionLoop : dossierQuestions) {
			for (Template template : templates) {
				if (template.getIsActive()) {
					templateQuestionnaire = template;
				}
				if (dossierQuestionLoop.getTemplateId().equals(template.getId())) {
					templateQuestionnaire = template;
				}
			}
		}
		/ * ----- */

		//if (dossierQuestions.isEmpty()) {
			templateQuestionnaire = templateDao.findByName(TemplateName.valueOf( templateName));
		//}
		String templateBody = templateQuestionnaire.getBody(); 
		XStream x = XStreamProvider.getInstance().getXStream();
		Questionnaire questionnaire = (Questionnaire) x.fromXML(templateBody); 
		questionnaire = QuestionnaireUtil.sortQuestionnaire(questionnaire);

		return questionnaire;
	}
}
