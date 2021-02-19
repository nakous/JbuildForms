package com.jbuild.forms.jbuildforms.tools;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern; 
import java.util.stream.Collectors;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.ui.Model;
import org.springframework.validation.ObjectError;

import com.jbuild.forms.jbuildforms.model.DossierStatusType;
import com.jbuild.forms.jbuildforms.model.Role;
import com.jbuild.forms.jbuildforms.model.User;
import com.jbuild.forms.jbuildforms.model.questionnaire.AccessRight;
import com.jbuild.forms.jbuildforms.model.questionnaire.Answer;
import com.jbuild.forms.jbuildforms.model.questionnaire.AnswerSheet;
import com.jbuild.forms.jbuildforms.model.questionnaire.Document;
import com.jbuild.forms.jbuildforms.model.questionnaire.Label;
import com.jbuild.forms.jbuildforms.model.questionnaire.Question;
import com.jbuild.forms.jbuildforms.model.questionnaire.Questionnaire;
import com.jbuild.forms.jbuildforms.model.questionnaire.Section;
import com.jbuild.forms.jbuildforms.model.questionnaire.Validation;

 

/**
 * Utility class to handle {@link Questionnaire}s and {@link AnswerSheet}s. E.g.
 * it can extract answers from a {@link AnswerSheet}.
 */
public class QuestionnaireUtil {
	private static final Logger LOG = LoggerFactory.getLogger(QuestionnaireUtil.class);

	private static final String RENDER = "render";

	private static final String ERROR = "ERROR";

	private static final String WARNING = "WARNING";

	private static final String OPTIONAL = "optional";

	/**
	 * Validate a questionnaire and its answers. E.g. are all mandatory fields
	 * given? Is input on the correct format (a regex may check it)?
	 * 
	 * @param questionnaire
	 * @param answerSheet
	 * @param locale
	 * @param messageSource
	 * @return rsuchecki added validation expression using Spell new element can be
	 *         added to questionnaire template(xml) needs to be added <validation
	 *         type="spel"
	 */
	/*
	public static Map<String, List<ObjectError>> validateQuestions(Questionnaire questionnaire, AnswerSheet answerSheet, Locale locale, MessageSource messageSource,
			ApplicationContext applicationContext, Map<String, Object> additionalParameters ) {
		List<ObjectError> errors = new ArrayList<ObjectError>();
		List<ObjectError> warnings = new ArrayList<ObjectError>();
		Map<String, List<ObjectError>> warnAndError = new HashMap<String, List<ObjectError>>();
		if (questionnaire != null) {
			Map<Object, List<String>> answersPerQuestionId = QuestionnaireUtil.getAnswersPerQuestionId(answerSheet);
			for (Section section : questionnaire.getSections()) {
				for (Question question : section.getQuestions()) {
					if (!(question.getRender() == null || !question.getRender()) && (question.getEnabled() == null || question.getEnabled()) && question.getVisible()) {
						List<String> answers = answersPerQuestionId.get(question.getId().toString());
						if ((answers == null || answers.isEmpty()) && question.getMandatory() != null && question.getMandatory()) {
							errors.add(new ObjectError("answerSheet.answers[" + question.getId() + "].values", new String[] { "validation.mandatory.this.field" }, null, null));
						} else {
							if (!(question.getValidations() == null || question.getValidations().isEmpty())) {
								for (Validation validation : question.getValidations()) {
									// TODO: later or never ... introduce dynamic validation via reflection etc

									// REG EXPRESSION VALIDATION
									if ("regexp".equalsIgnoreCase(validation.getType())) {
										Pattern pattern = Pattern.compile(validation.getRule());
										boolean matches = true;
										for (String answer : answers) {
											Matcher matcher = pattern.matcher(answer);
											matches &= matcher.matches();
											if (!matches && validation.getMessageType().equalsIgnoreCase(ERROR)) {
												errors.add(new ObjectError("answerSheet.answers[" + question.getId() + "].values", new String[] { validation.getLabel().getText() }, null, null));
											} else if (!matches && validation.getMessageType().equalsIgnoreCase(WARNING)) {
												warnings.add(new ObjectError("answerSheet.answers[" + question.getId() + "].values", new String[] { validation.getLabel().getText() }, null, null));
												if (validation.getForwardAsMessage() != null && validation.getForwardAsMessage()) {
													// Add validation text to workflowMessages
//													if (workflowMessages == null) {
														workflowMessages = new WorkflowMessageList();
													}

													addMessageToWorkflowMessages(workflowMessages, WARNING, validation.getLabel().getText(), "");
												}
											}
										}
									}

									// SPEL VALIDATION
									if ("spel".equalsIgnoreCase(validation.getType())) {
										if (Boolean.TRUE.equals(question.getMultiple())) {
											validateSpelAnswers(applicationContext, validation, question, answers, additionalParameters, errors, warnings, workflowMessages);
										} else {
											if (answers != null && answers.size() > 1) {
												throw new IllegalStateException("Answer to non multiple selection question can only contain a single answer");
											}
											validateSpelAnswers(applicationContext, validation, question, (answers != null ? answers.get(0) : null), additionalParameters, errors, warnings,
													workflowMessages);
										}
									}
								}
							}
						}
					}
				} // End for questions
			} // End for sections
		} // End if not null
		warnAndError.put("errors", errors);
		warnAndError.put("warnings", warnings);
		return warnAndError;
	}
*/
	/**
	 * Set the questions as rendered if they appear in the list
	 * 
	 * @param questionnaire
	 * @param questionsRendered
	 */
	public static void setQuestionsRendered(Questionnaire questionnaire, List<String> questionsRendered) {
		if (!(questionnaire == null || questionsRendered == null)) {
			for (Section section : questionnaire.getSections()) {
				for (Question question : section.getQuestions()) {
					Integer questionId = question.getId();
					if (questionsRendered.contains(questionId.toString())) {
						question.setRender(Boolean.TRUE);
					} else {
						question.setRender(Boolean.FALSE);
					} // End if/else contains
				} // End for questions
			} // End for sections
		} // End if not null
	}

	protected static VelocityContext prepareContext(Integer questionId, Object values, VelocityContext ctx) {
		if (values != null) {
			// TODO Handle multiple values later...
			String question = "q" + questionId;
			ctx.put(question, values);
		}
		return ctx;
	}

	protected static VelocityContext prepareContext(String questionId, Object values, VelocityContext ctx) {
		if (values != null) {
			// TODO Handle multiple values later...
			String question = "q" + questionId;
			ctx.put(question, values);
		}
		return ctx;
	}

	protected static VelocityContext prepareContext(Integer[] questionId, Object values, VelocityContext ctx) {
		if (values != null) {
			// TODO Handle multiple values later...
			for (int i = 0; i < questionId.length; i++) {
				String question = "q" + questionId[i];
				ctx.put(question, values);
			}
		}
		return ctx;
	}

	public static VelocityContext prepareContext(AnswerSheet answerSheet, VelocityContext ctx) {
		if (answerSheet != null) {
			ctx = prepareContext("active_page", answerSheet.getActivePage(), ctx);
			for (Answer answer : answerSheet.getAnswers()) {
				if (answer.getValues() != null && !(answer.getValues().isEmpty())) {
					ctx = prepareContext(answer.getQuestionId(), answer.getValues(), ctx);
				}
			}
		}

		return ctx;
	}

	public static boolean checkSectionRendered(Questionnaire questionnaire, Integer sectionnum, VelocityContext ctx) {
		boolean result = false;
		VelocityEngine engine = new VelocityEngine();
		engine.setProperty(RuntimeConstants.RUNTIME_LOG_LOGSYSTEM_CLASS, "org.apache.velocity.runtime.log.Log4JLogChute");
		engine.setProperty("runtime.log.logsystem.log4j.logger", LOG.getName());
		engine.init();
		// Prepare the velocity engine

		List<Section> sections = questionnaire.getSections();
		Section section = sections.get(sectionnum - 1);
		ctx.remove(RENDER);
		if (!StringUtils.isEmptyOrNull(section.getConditions())) {
			StringWriter writer = new StringWriter();
			engine.evaluate(ctx, writer, ERROR, section.getConditions());
			if (ctx.get(RENDER) != null) {
				result = (Boolean) ctx.get(RENDER);
			}
		}

		return result;
	}

	/**
	 * Orders all orderable fields based on the order attribute. Assumes that order
	 * attribute is set properly for all fields and is not null.
	 *
	 * @param questionnaire
	 * @return
	 */
	public static Questionnaire sortQuestionnaire(Questionnaire questionnaire) {
		// Is naturally sorted
		if (questionnaire.getSections() == null) {
			return questionnaire;
		}

		// Sections have their own ordering and questions will be sorted inside the sections
		Collections.sort(questionnaire.getSections(), new Comparator<Section>() {
			public int compare(Section o1, Section o2) {
				return o1.getOrder().compareTo(o2.getOrder());
			}
		});

		// It is expected that questions can not breach their sections and must be sorted within the section
		for (Section section : questionnaire.getSections()) {
			if (section.getQuestions() != null) {
				Collections.sort(section.getQuestions(), new Comparator<Question>() {
					public int compare(Question o1, Question o2) {
						return o1.getOrder().compareTo(o2.getOrder());
					}
				});
			}
		}

		return questionnaire;
	}

	public static void setValuesInAnswerSheet(AnswerSheet answerSheet, Integer responseNum, String value) {
		List<String> values = null;
		if (value != null && value != "") {
			values = new ArrayList<String>();
			values.add(value);
		}

		for (Answer a : answerSheet.getAnswers()) {
			if (responseNum != null && a.getQuestionId() != null && a.getQuestionId().equals(responseNum)) {
				a.setValues(values);
				;
				break;
			}
		}
	}

	public static Map<Object, List<String>> getAnswersPerQuestionId(AnswerSheet answerSheet) {
		Map<Object, List<String>> answersPerQuestionId = new HashMap<Object, List<String>>();
		if (answerSheet != null) {
			for (Answer answer : answerSheet.getAnswers()) {
				if (!(answer.getValues() == null || answer.getValues().isEmpty())) {
					answersPerQuestionId.put(answer.getQuestionId().toString(), answer.getValues());
				}
			}
		}
		return answersPerQuestionId;
	}

	/**
	 * Determine if a group is renderable based on the answers provided
	 *
	 * @param condition
	 * @param model
	 * @param answerSheet
	 * @return
	 */
	public static boolean evaluateValidWhen(String condition, Map<String, Object> model, AnswerSheet answerSheet) {
		VelocityEngine engine = new VelocityEngine();
		engine.setProperty(RuntimeConstants.RUNTIME_LOG_LOGSYSTEM_CLASS, "org.apache.velocity.runtime.log.Log4JLogChute");
		engine.setProperty("runtime.log.logsystem.log4j.logger", LOG.getName());
		engine.init();
		Boolean result = true;
		if (!StringUtils.isEmptyOrNull(condition)) {
			VelocityContext ctx = new VelocityContext();
			ctx = QuestionnaireUtil.prepareContext(answerSheet, ctx);

			if (model != null) {
				for (Map.Entry<String, Object> entry : model.entrySet()) {
					ctx.put(entry.getKey(), entry.getValue());
				}
			}

			StringWriter writer = new StringWriter();
			engine.evaluate(ctx, writer, ERROR, condition);
			result = (Boolean) ctx.get(RENDER);
		}
		return result;
	}

	/** Returns null if none answer found with given questionId */
	public static Answer getAnswerByQuestionId(AnswerSheet answerSheet, Integer questionId) {
		Answer answer = null;
		for (Answer a : answerSheet.getAnswers()) {
			if (a != null && questionId != null) {
				if (a.getQuestionId() != null) {
					int qi = a.getQuestionId();
					int qqi = questionId;
					if (qi == qqi) {
						answer = a;
						break;
					}
				}
			}
		}
		return answer;
	}

	/**
	 * Determine if a group is renderable based on the answers provided
	 *
	 * @param condition
	 * @param model
	 * @param answerSheet
	 * @return
	 */
	public static Integer evaluateInfoForOptional(String statement, Map<String, Object> model, AnswerSheet answerSheet) {
		VelocityEngine engine = new VelocityEngine();
		engine.setProperty(RuntimeConstants.RUNTIME_LOG_LOGSYSTEM_CLASS, "org.apache.velocity.runtime.log.Log4JLogChute");
		engine.setProperty("runtime.log.logsystem.log4j.logger", LOG.getName());
		engine.init();
		Integer result = 0;
		if (!StringUtils.isEmptyOrNull(statement)) {
			VelocityContext ctx = new VelocityContext();

			ctx = QuestionnaireUtil.prepareContext(answerSheet, ctx);

			if (model != null) {
				for (Map.Entry<String, Object> entry : model.entrySet()) {
					ctx.put(entry.getKey(), entry.getValue());
				}
			}

			StringWriter writer = new StringWriter();
			engine.evaluate(ctx, writer, ERROR, statement);
			if (ctx.get(OPTIONAL) != null) {
				result = (((Boolean) ctx.get(OPTIONAL))) ? 1 : 0;
			}
		}
		return result;
	}

	/**
	 * Get the answer values of a multiple answer
	 *
	 * @param document
	 * @param answersPerQuestionId
	 * @param model
	 * @return
	 */
	public static List<String> getMultiplierAnswerValues(Document document, Map<Object, List<String>> answersPerQuestionId, Map<String, Object> model) {
		VelocityEngine engine = new VelocityEngine();
		engine.setProperty(RuntimeConstants.RUNTIME_LOG_LOGSYSTEM_CLASS, "org.apache.velocity.runtime.log.Log4JLogChute");
		engine.setProperty("runtime.log.logsystem.log4j.logger", LOG.getName());
		engine.init();
		// Prepare the velocity engine

		String strMultiplierQuestionIds = document.getMultiplierQuestionId();

		List<String> answerValues = new ArrayList<String>();
		List<String> resultValues = new ArrayList<String>();
		if (!StringUtils.isEmptyOrNull(strMultiplierQuestionIds)) {
			String[] multiplierQuestionIds = strMultiplierQuestionIds.split(",");
			for (int i = 0; i < multiplierQuestionIds.length; i++) {
				Integer multiplierQuestionId = Integer.valueOf(multiplierQuestionIds[i]);
				List<String> answersPerQuestion = answersPerQuestionId.get(multiplierQuestionId);
				if (answersPerQuestion != null) {
					answerValues.addAll(answersPerQuestion);
				}
			}

			if (answerValues != null) {
				Set<String> values = new TreeSet<String>();
				values.addAll(answerValues);
				// If we have specified a service bean name to add to the answered values
				if (!(model == null || model.isEmpty() || StringUtils.isEmptyOrNull(document.getMultiplierBeanName()))) {
					String multiplierBeanName = document.getMultiplierBeanName();
					@SuppressWarnings("unchecked")
					Collection<String> nationalityIds = (Collection<String>) model.get(multiplierBeanName);
					values.addAll(nationalityIds);
					answerValues.clear();
					answerValues.addAll(values);
				}

				if (StringUtils.isEmptyOrNull(document.getValidWhen())) {
					resultValues = answerValues;
				} else {
					for (String answerValue : answerValues) {
						if (StringUtils.isEmptyOrNull(document.getValidWhen())) {
							resultValues.add(answerValue);
						} else {
							// Handle each answer individually
							VelocityContext ctx = new VelocityContext();
							List<String> templValueList = new ArrayList<String>();
							templValueList.add(answerValue);
							for (int i = 0; i < multiplierQuestionIds.length; i++) {
								ctx.put("q" + multiplierQuestionIds[i], templValueList);
							}

							// Add model in context
							for (Map.Entry<String, Object> modelEntry : model.entrySet()) {
								ctx.put(modelEntry.getKey(), modelEntry.getValue());
							}

							StringWriter writer = new StringWriter();
							engine.evaluate(ctx, writer, ERROR, document.getValidWhen());
							Boolean render = (Boolean) ctx.get(RENDER);
							if (render) {
								resultValues.add(answerValue);
							}
						}
					}
				}
			}
		}
		return resultValues;
	}

	private static boolean hasRole(User user, String roleName) {
		boolean result = false;

		 
			List<Role> role = user.getRoles().stream().filter(x -> x.getName().equals(roleName)).collect(Collectors.toList());
			if (role != null && !role.isEmpty()) {
				result = true;
			}
		

		return result;
	}

	/**
	 * Decide which questions are to be rendered based on the current model and dossier status and user roles.
	 *
	 * @param questionnaire
	 * @param model
	 * @param answerSheet
	 * @return
	 */
	public static boolean prepareQuestionsSec(Questionnaire questionnaire,Model model, AnswerSheet answerSheet,User userProfils, DossierStatusType dossierStatusType) {
		boolean hasChanged = false;
		VelocityEngine engine = new VelocityEngine();
		engine.setProperty(RuntimeConstants.RUNTIME_LOG_LOGSYSTEM_CLASS, "org.apache.velocity.runtime.log.Log4JLogChute");
		engine.setProperty("runtime.log.logsystem.log4j.logger", LOG.getName());
		engine.init();
		// Prepare the velocity engine
		VelocityContext ctx = new VelocityContext();

		ctx = QuestionnaireUtil.prepareContext(answerSheet, ctx);
		 

		if (model != null) {
			Map<String,Object> modelMap = model.asMap();
			
			for (Map.Entry<String, Object> entry : modelMap.entrySet()) {
				ctx.put(entry.getKey(), entry.getValue());
			}
		}

		for (Section section : questionnaire.getSections()) {
			section.setRender(true);
			if (!StringUtils.isEmptyOrNull(section.getConditions())) {
				StringWriter writer = new StringWriter();
				engine.evaluate(ctx, writer, ERROR, section.getConditions());
				Boolean render = (Boolean) ctx.get(RENDER);
				section.setRender(render == null ? true : render);
			}

			if (section.getRender()) {

				boolean renderSection = false;

				for (Question question : section.getQuestions()) {
					// Reinitialize depends_on_question value which should be cleared from the context
					ctx.remove("additionalLabel");
					if ((questionnaire.getAccessRights() == null || questionnaire.getAccessRights().isEmpty()) && (question.getAccessRights() == null || question.getAccessRights().isEmpty())) {
						question.setEnabled(false);
						question.setRender(false);
					} else {
						question.setVisible(false);
						question.setAccessible(false);

						List<AccessRight> accessRights = question.getAccessRights() != null ? question.getAccessRights() : questionnaire.getAccessRights();

						for (AccessRight accessRight : accessRights) {
							if (hasRole(userProfils, accessRight.getRole()) && dossierStatusType.getName().equals(accessRight.getStatus())) {
								// If question is going to be displayed then we display the label of the questionnaire and show the question
								if (questionnaire.getLabel() != null) {
									questionnaire.getLabel().setIsDisplayed(true);
								}
								if (accessRight.getType().equals("view")) {
									question.setVisible(true);
								} else if (accessRight.getType().equals("modify")) {
									question.setVisible(true);
									question.setAccessible(true);
								}
							}
						}

						question.setRender(true);
						if (!StringUtils.isEmptyOrNull(question.getRenderWhen())) {
							StringWriter writer = new StringWriter();
							engine.evaluate(ctx, writer, ERROR, question.getRenderWhen());
							Boolean render = (Boolean) ctx.get(RENDER);
							String dependsOnQuestion = (String) ctx.get("dependsOnQuestion");
							question.setRender(render);
							question.setDependsOnQuestion(dependsOnQuestion);
							String additionalLabel = (String) ctx.get("additionalLabel");
							// Add to dynamic labels an additional label set in render when tag
							if (additionalLabel != null) {
								List<Label> labels = new ArrayList<Label>();
								labels.add(new Label(null, additionalLabel));
								question.setLabels(labels);
							}
							renderSection = true;
						}
						if (!StringUtils.isEmptyOrNull(question.getEnabledWhen())) {
							StringWriter writer = new StringWriter();
							engine.evaluate(ctx, writer, ERROR, question.getEnabledWhen());
							Boolean enabled = (Boolean) ctx.get("enabled");
							String dependsOnQuestion = (String) ctx.get("dependsOnQuestion");
							// If the new render is not null and the question has information about its state and the state has changed
							if (enabled != null && question.getEnabled() != null && !question.getEnabled().equals(enabled)) {
								hasChanged = true;
							}
							question.setDependsOnQuestion(dependsOnQuestion);
							question.setEnabled(enabled);
						}
						if (question.getRender() && !StringUtils.isEmptyOrNull(question.getDefaultValueWhen())) {
							StringWriter writer = new StringWriter();
							engine.evaluate(ctx, writer, ERROR, question.getDefaultValueWhen());
							Object defaultValue = ctx.get("default");
							// If the new render is not null and the question has information about its state and the state has changed
							if (defaultValue != null) {
								question.setDefaultValue(defaultValue.toString());
							} else {
								question.setDefaultValue("");
							}
						} else {
							question.setDefaultValue("");
						}

						renderSection = renderSection ? true : question.getRender();
					}
				}

				section.setRender(renderSection);
			}
		}

		return hasChanged;
	}

	public static boolean checkQuestionRendered(Questionnaire questionnaire, Map<String, Object> model, Integer questionCheckId, Map<String, Object> additionalAttributes) {
		boolean result = false;
		VelocityEngine engine = new VelocityEngine();
		engine.setProperty(RuntimeConstants.RUNTIME_LOG_LOGSYSTEM_CLASS, "org.apache.velocity.runtime.log.Log4JLogChute");
		engine.setProperty("runtime.log.logsystem.log4j.logger", LOG.getName());
		engine.init();
		// Prepare the velocity engine
		VelocityContext ctx = new VelocityContext();

		for (Map.Entry<String, Object> entry : additionalAttributes.entrySet()) {
			ctx.put(entry.getKey(), entry.getValue());
		}

		ctx.put("DateUtils", new DateUtils());
		ctx.put("NumberUtils", new NumberUtils());
		if (model != null) {
			for (Map.Entry<String, Object> entry : model.entrySet()) {
				Integer qid = Integer.valueOf(entry.getKey());
				List<Object> vals = new ArrayList<Object>();
				vals.add(entry.getValue());
				ctx = QuestionnaireUtil.prepareContext(qid, vals, ctx);
			}
		}

		for (Section section : questionnaire.getSections()) {
			for (Question question : section.getQuestions()) {
				if (questionCheckId.equals(question.getId()) && !StringUtils.isEmptyOrNull(question.getRenderWhen())) {
					StringWriter writer = new StringWriter();
					engine.evaluate(ctx, writer, ERROR, question.getRenderWhen());
					result = (Boolean) ctx.get(RENDER);
					ctx.get("dependsOnQuestion");
				}
			}
		}

		return result;
	}

	/**
	 * evaluates condition in <enabled-when>
	 * 
	 * @param questionnaire
	 * @param qvalue
	 * @return
	 */
	public static String checkSingleQuestionwarning(Questionnaire questionnaire, Map<String, Object> model) {
		Boolean warning = false;
		Integer conditional = new Integer(239);
		String warningMessage = "";
		VelocityEngine engine = new VelocityEngine();
		engine.setProperty(RuntimeConstants.RUNTIME_LOG_LOGSYSTEM_CLASS, "org.apache.velocity.runtime.log.Log4JLogChute");
		engine.setProperty("runtime.log.logsystem.log4j.logger", LOG.getName());
		VelocityContext ctx = new VelocityContext();
		engine.init();

		if (model != null) {
			for (Map.Entry<String, Object> entry : model.entrySet()) {
				Integer qid = Integer.valueOf(entry.getKey());
				List<Object> vals = new ArrayList<Object>();
				vals.add(entry.getValue());
				ctx = QuestionnaireUtil.prepareContext(qid, vals, ctx);
				for (Section section : questionnaire.getSections()) {
					for (Question question : section.getQuestions()) {
						if (conditional.equals(question.getId())) {
							for (Validation validation : question.getValidations()) {
								if ("velocity".equalsIgnoreCase(validation.getType())) {
									StringWriter writer = new StringWriter();
									engine.evaluate(ctx, writer, ERROR, validation.getRule());
									warning = (Boolean) ctx.get("warning");
									if (warning) {
										warningMessage = validation.getLabel().getText();
									}
								}
							}
						}
					}
				}
			}
		}

		return warningMessage;
	}

	/**
	 * evaluates condition in <enabled-when>
	 * 
	 * @param questionnaire
	 * @param applicationContext
	 * @param qvalue
	 * @return
	 */
	public static String checkSingleQuestionwarning(Questionnaire questionnaire, Map<String, Object> model, ApplicationContext applicationContext) {
		String warningMessage = null;
		VelocityEngine engine = new VelocityEngine();
		engine.setProperty(RuntimeConstants.RUNTIME_LOG_LOGSYSTEM_CLASS, "org.apache.velocity.runtime.log.Log4JLogChute");
		engine.setProperty("runtime.log.logsystem.log4j.logger", LOG.getName());
		engine.init();

		if (model != null) {
			for (Map.Entry<String, Object> entry : model.entrySet()) {
				Integer questionId = Integer.valueOf(entry.getKey());
				Object answerAsObject = entry.getValue();
				List<String> answers = new ArrayList<String>();
				if (answerAsObject instanceof Collection) {
					@SuppressWarnings("unchecked")
					List<String> answersObjs = (List<String>) answerAsObject;
					for (Object answerobj : answersObjs) {
						answers.add(answerobj.toString());
					}
				} else {
					answers.add(answerAsObject.toString());
				}
				for (Section section : questionnaire.getSections()) {
					for (Question question : section.getQuestions()) {
						if (questionId.equals(question.getId())) {
							if (!(question.getValidations() == null || question.getValidations().isEmpty())) {
								for (Validation validation : question.getValidations()) {
									// TODO: later or never ... introduce dynamic validation via reflection etc
									if (validation.getMessageType().equals("warning")) {
										boolean valid = true;
										if ("regexp".equalsIgnoreCase(validation.getType())) {
											Pattern pattern = Pattern.compile(validation.getRule());
											for (String answer : answers) {
												Matcher matcher = pattern.matcher(answer);
												valid &= matcher.matches();
											}
										}
										if ("spel".equalsIgnoreCase(validation.getType())) {
											Map<String, Object> additionalParameters = new HashMap<String, Object>();
											if (Boolean.TRUE.equals(question.getMultiple())) {
												valid = SPELUtils.evaluateValidationExpression(applicationContext, validation.getRule(), null, additionalParameters).booleanValue();
											} else {
												if (answers.size() > 1) {
													throw new IllegalStateException("Answer to non multiple selection question can only contain a single answer");
												}
												valid = SPELUtils.evaluateValidationExpression(applicationContext, validation.getRule(), answers.get(0), additionalParameters).booleanValue();
											}
										}
										if (!valid) {
											warningMessage = validation.getLabel().getText();
										}
									}
								}
							}
						}
					}
				}
			}
		}

		return warningMessage;
	}

	/**
	 * Validates spel question and process validation
	 * updates errors , warnings, workflowMessages
	 * 
	 * @param applicationContext
	 * @param validation
	 * @param question
	 * @param answers
	 * @param additionalParameters
	 * @param errors
	 * @param warnings
	 * @param workflowMessages
	 */
	private static void validateSpelAnswers(ApplicationContext applicationContext, Validation validation, Question question, Object answers, Map<String, Object> additionalParameters,
			List<ObjectError> errors, List<ObjectError> warnings) {
		Boolean valid = SPELUtils.evaluateValidationExpression(applicationContext, validation.getRule(), answers, additionalParameters);
		if (!valid && validation.getMessageType().equalsIgnoreCase(ERROR)) {
			errors.add(new ObjectError("answerSheet.answers[" + question.getId() + "].values", new String[] { validation.getLabel().getText() }, null, null));
		} else if (!valid && validation.getMessageType().equalsIgnoreCase(WARNING)) {
			warnings.add(new ObjectError("answerSheet.answers[" + question.getId() + "].values", new String[] { validation.getLabel().getText() }, null, null));
			if (validation.getForwardAsMessage() != null && validation.getForwardAsMessage()) {
				//addMessageToWorkflowMessages(workflowMessages, WARNING, "", validation.getLabel().getText());
			}
		}
	}

	/**
	 * adds message to workflow messages which are used in approval flow
	 *
	 * @param workflowMessageList
	 *            WorkflowMessageList
	 * @param messageType
	 *            String
	 * @param messageLabel
	 *            String
	 */
	/*public static void addMessageToWorkflowMessages(WorkflowMessageList workflowMessageList, String messageType, String messageLabel, String systemLabel) {
	 
		if (workflowMessageList.getWorkflowMessages() == null) {
			workflowMessageList.setWorkflowMessages(new ArrayList<WorkflowMessage>());
		}
		WorkflowMessage workflowMessage = new WorkflowMessage(messageType, messageLabel, systemLabel);

		workflowMessageList.getWorkflowMessages().add(workflowMessage);
	}
*/
}