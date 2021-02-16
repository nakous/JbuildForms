package com.jbuild.forms.jbuildforms.model.questionnaire;

import java.io.Serializable;
import java.util.List;

import com.jbuild.forms.jbuildforms.enumeration.TemplateName;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

 

/**
 * Contains the XML with the candidate's answers to questions, related to a recruitment type.
 * 
 */
@XStreamAlias("answer-sheet")
public class AnswerSheet implements Serializable {
	private static final long serialVersionUID = -684689284900297409L;

	/**
	 * unique id
	 */
	@XStreamAsAttribute
	@XStreamAlias("id")
	private Integer id;

	@XStreamAsAttribute
	@XStreamAlias("questionnaire-id")
	private Integer questionnaireId;
	
	@XStreamAsAttribute
	@XStreamAlias("active-page")
	private Integer activePage;
	
	@XStreamAsAttribute
	@XStreamAlias("template-name")
	private TemplateName templateName;

	public TemplateName getTemplateName() {
		return templateName;
	}

	public void setTemplateName(TemplateName templateName) {
		this.templateName = templateName;
	}

	/**
	 * sections that contain the questions
	 */
	@XStreamAlias("answers")
	private List<Answer> answers;

	/**
	 * default constructor
	 */
	public AnswerSheet() {
		super();
	}

	/**
	 * @return unique id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return list of answers to questions
	 */
	public List<Answer> getAnswers() {
		return answers;
	}

	/**
	 * @param answers
	 */
	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public Integer getQuestionnaireId() {
		return questionnaireId;
	}

	public void setQuestionnaireId(Integer questionnaireId) {
		this.questionnaireId = questionnaireId;
	}
	
	public Integer getActivePage() {
		return activePage;
	}

	public void setActivePage(Integer activePage) {
		this.activePage = activePage;
	}

	
}