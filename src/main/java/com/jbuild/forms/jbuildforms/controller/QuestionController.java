package com.jbuild.forms.jbuildforms.controller;



import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression; 
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.jbuild.forms.jbuildforms.dao.DossierDao;
import com.jbuild.forms.jbuildforms.dao.DossierStatusDao;
import com.jbuild.forms.jbuildforms.dao.LanguageDao;
import com.jbuild.forms.jbuildforms.dao.ProcessDao;
import com.jbuild.forms.jbuildforms.dao.ProcessDossierStatusTypeDao;
import com.jbuild.forms.jbuildforms.dao.ProcessStatusTypePropertyDao;
import com.jbuild.forms.jbuildforms.dao.UserDao;
import com.jbuild.forms.jbuildforms.model.Dossier;
import com.jbuild.forms.jbuildforms.model.DossierStatus;
import com.jbuild.forms.jbuildforms.model.DossierStatusType;
import com.jbuild.forms.jbuildforms.model.Process;
import com.jbuild.forms.jbuildforms.model.ProcessStatusTypeProperty;
import com.jbuild.forms.jbuildforms.model.User;
import com.jbuild.forms.jbuildforms.model.questionnaire.AnswerSheet;
import com.jbuild.forms.jbuildforms.model.questionnaire.Questionnaire;
import com.jbuild.forms.jbuildforms.model.tools.Language;
import com.jbuild.forms.jbuildforms.model.tools.ParentForm;
import com.jbuild.forms.jbuildforms.service.QuestionService;
import com.jbuild.forms.jbuildforms.tools.QuestionnaireUtil;
import com.jbuild.forms.jbuildforms.tools.LogUtils;



 
 
 

@Controller
@ConditionalOnExpression("${controller.enabled:true}")
public class QuestionController  {

	@Autowired
	ProcessDao processDao;

	@Autowired
	LanguageDao languageDao;

	@Autowired
	DossierDao dossierDao;
	
	@Autowired
	DossierStatusDao dossierStatusDao;
	
	@Autowired
	ProcessDossierStatusTypeDao pocessDossierStatusTypeDao;
	
	@Autowired
	ProcessStatusTypePropertyDao processStatusTypePropertyDao;
	
	@Autowired
	QuestionService questionService;
	
	@Autowired
	UserDao userDao;
	
	//TEMPLATE NAME
	private final static String TNAME = "QUESTIONNAIRE_TEMPLATE_NAME";
	
	//View NAME
	private final static String VNAME = "VIEW";
	
	private static final Logger LOG = LoggerFactory.getLogger(QuestionnaireUtil.class);
	
	@GetMapping("/question")
	public String  getProcess( 
			Model model,
			Locale locale,
			@ModelAttribute("questionnaireform") Form form,
			@RequestParam(value = "viewproperty", required = false) String viewProperty,
			@RequestParam(value = "dossierid", required = false) Integer dossierId,
			@RequestParam(value = "saveDocForLater", required = false) boolean saveDocForLater,
			@RequestParam(value = "processid", required = false) String processName) throws Exception {
		
		try {
		Map<String,Object > attributes = new HashMap<String, Object>();
		
		Dossier dossier = null;
		String templateName; 
		AnswerSheet answerSheet = null;
		DossierStatusType dossierStatusType = null;
		DossierStatus dossierStatus = null;
		Questionnaire questionnaire = null;
		
		
		 
		
		User user = userDao.findByUserId(new Long(1));
		
		Language language =   languageDao.findByIsoCode(locale.getLanguage());
		Process process = processDao.findByName(processName);
		Optional<Dossier> d = dossierDao.findById(dossierId);
		Form preparedForm = form;
		if (preparedForm == null) {
			preparedForm = new Form();
		}
		preparedForm.setProcessId(process.getId());
		
		if(d.isPresent()) {
			  dossier = d.get();
		} else {
			  dossier = new Dossier();
		}
		
		dossierStatus = dossierStatusDao.findCurrentDossierStatusByDossierIdAndProcess(dossierId, process);
		dossierStatusType = dossierStatus.getStatus();
		
		ProcessStatusTypeProperty templateNameProperty = processStatusTypePropertyDao.ProcessStatusTypePropertyByPdn(process, dossierStatusType, TNAME);
		templateName = templateNameProperty.getValue();
		
		questionnaire = questionService.getQuestionnaire(templateName, dossier);
		QuestionnaireUtil.prepareQuestionsSec(questionnaire, model, preparedForm.getAnswerSheet(), user, dossierStatusType);
		
		attributes.put("questionnaire", questionnaire);
		
		attributes.put("dossierId", dossierId);
		attributes.put("processId", processName);
		attributes.put("pagenumber", 1);
		attributes.put("saveDocForLater", saveDocForLater);
		attributes.put("templateName", templateName);
		model.addAllAttributes(attributes);
	} catch (Exception e) {
		 
		 
			LogUtils.logError(LOG, e, processName ,  " Error while calling prepareQuestions");
		 
	}
		return "questions";
		
	} 
	
	public static class Form extends ParentForm {
		private AnswerSheet answerSheet;

		private List<String> questionsRendered;

		public AnswerSheet getAnswerSheet() {
			return answerSheet;
		}

		public void setAnswerSheet(AnswerSheet answerSheet) {
			this.answerSheet = answerSheet;
		}

		public List<String> getQuestionsRendered() {
			return questionsRendered;
		}

		public void setQuestionsRendered(List<String> questionsRendered) {
			this.questionsRendered = questionsRendered;
		}
	}
}
