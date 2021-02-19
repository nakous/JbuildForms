<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="q" uri="/WEB-INF/taglib/questionary.tld"%>
<html lang="en">

<jsp:include page="common/head.jsp"/>
<body>
<jsp:include page="common/header.jsp"/>

<main id="layout-main">
	<div class="container layoutSize box-wrapper">
 
		<div class="box">
		<%-- 
			<h1><spring:message code="${currentStatuslabel}"/></h1>
 
				<c:if test="${saveDocForLaterMsg}">
					<div class="alert alert-info alert-dismissible fade in" role="alert">
						<div class="alert-icon"> <span class="hr-icon__info-circle hr-icon-38 text-info"></span> </div>
						<div class="alert-body">
							<button class="close" aria-label="Close" data-dismiss="alert" type="button"> <span class="hr-icon__close" aria-hidden="true"></span> </button>
							<strong>Info</strong>
							<br/>
							<spring:message code="questionnaire.saved"/>
						</div>
					</div>
				</c:if>

				 

			--%>	 

			 
					<form:form modelAttribute="questionnaireform" id="mainForm" cssClass="form-horizontal" method="post" action="${pageContext.request.contextPath}${actionpath}">
					
						<h4 id="section.title">
							<!-- Filled in by js -->
						</h4>

						<c:if test='${pagenumurl != null && pagenumurl != "0"}'>
							<c:set var="pagenumber" value="${pagenumurl}" scope="page"/>
						</c:if>
						<form:input type="hidden" path="answerSheet.questionnaireId" value="${questionnaire.id}"/>
						<form:input type="hidden" path="answerSheet.templateName" value="${templateName}"/>
						<form:input type="hidden" path="dossierId" value="${dossierId}"/>
						<form:input type="hidden" path="processId" value="${processId}"/>
						<form:input type="hidden" path="pageNumber" value="${pagenumber}"/>
						<form:input type="hidden" path="answerSheet.activePage" value="${pagenumber}"/>
						<form:input type="hidden" path="saveDocForLater" value="${saveDocForLater}"/>

						<c:set var="answerValue1" value="" scope="page"/>
						<c:forEach items="${questionnaire.sections}" var="section">
							<c:if test="${section.order == pagenumber}">
							<%-- 	<spring:message code="${section.label.text}" var="section_label"/>
								<spring:message code="${section.description.text}" var="section_description"/>
								<script>
									$("[id^='questionnaire.title']").append('${section_label}');
									$("[id^='section.title']").append('${section_description}');
								</script>
								--%>
							</c:if>
							<div class="row">
								<c:set var="visibleQuestionIndex" value="0" scope="page"/>
								<c:forEach items="${section.questions}" var="question" varStatus="stat">
									<c:if test="${question.visible}">
										<c:if test="${!question.accessible}">
											<c:if test="${question.appearence == 'subsection'}">
												<c:set var="visibleQuestionIndex" value="0" scope="page"/>
												<c:if test="${question.render}">
													<div class="col-md-12">
														<hr/>
														<h2 style="margin-top: 0px">
															<spring:message code="${question.label.text}"></spring:message>
														</h2>
													</div>
												</c:if>
											</c:if>
											<c:if test="${question.appearence != 'subsection'}">
												<div class="col-md-6">
													<q:question-readonly question="${question}" answerSheet="${form.answerSheet}"></q:question-readonly>
												</div>
											</c:if>
										</c:if>
										<c:set var="nextQuestionOrder" value="${section.questions[stat.index+1].order}" scope="page"/>
										<c:if test="${question.accessible}">
											<c:set var="answerValue" value=""/>
											<c:if test="${section.order == pagenumber}">
												<c:forEach items="${question.validations}" var="validation">
													<c:if test="${validation.messageType == 'warning'}">
														<script>
															questions_warnings[questions_warnings.length] = ${question.id};
														</script>
													</c:if>
												</c:forEach>

												<form:input type="hidden" path="answerSheet.answers[${question.id}].questionId" value="${question.id}"/>
												<c:if test="${questionid != null || questionid != ''}">
													<c:set var="questionid" value="${questionid},${question.id}"/>
												</c:if>
												<c:if test="${questionid == null || questionid == ''}">
													<c:set var="questionid" value="${question.id}"/>
												</c:if>
												<c:if test="${question.label!=null}">
													<c:if test="${question.appearence == 'subsection'}">
														<c:set var="visibleQuestionIndex" value="0" scope="page"/>

														<div name ="answerSheet.answers[${question.id}].values" id="answerSheet.answers[${question.id}].values-parent-container" class="col-md-12" dependsonquestion="">
															<hr style="margin-bottom: 10px"/>
															<h2 style="margin-top: 0px; margin-bottom: 10px" id= "answerSheet.answers[${question.id}].values-label">
																<spring:message code="${question.label.text}"></spring:message>
																<c:if test="${not empty question.labels}">
																	&nbsp; ${question.labels.get(0).text}
																</c:if>
															</h2>
														</div>
														<script type="text/javascript">
															dependsOnEvent($("[name^='answerSheet.answers[${question.id}].values']"), "${question.dependsOnQuestion}");
														</script>
													</c:if>
													<c:if test="${question.appearence != 'subsection'}">
														<c:if test="${question.newLineDecale}">
															<c:set var="visibleQuestionIndex" value="${visibleQuestionIndex + 1}" scope="page"/>
															<div class="clearfix">
																<!-- clearfix lg cols every 2 -->
															</div>
														</c:if>
														<c:if test="${question.appearence != 'select' && question.decaleInView}">
															<div class="col-md-6"></div>

															<c:if test="${visibleQuestionIndex % 2 != 0}">
																<div class="clearfix">
																	<!-- clearfix lg cols every 2 -->
																</div>
																<c:set var="visibleQuestionIndex" value="${visibleQuestionIndex + 1}" scope="page"/>
															</c:if>
														</c:if>
														<c:set var="colSpan" value="6"/>
														<c:if test="${question.appearence == 'select' && question.decaleInView}">
															<c:set var="colSpan" value="12"/>
														</c:if>
														<div class="col-md-${colSpan}" id="answerSheet.answers[${question.id}].values-parent-container">
															<c:if test="${question.type == 'field' && question.appearence == 'hidden'}">
																<c:forEach items="${questionnaireform.answerSheet.answers[question.id].values}" var="answer_text">
																	<c:set var="answerValue" value="${answer_text}"/>
																</c:forEach>

																<c:if test="${question.id == 1}">
																	<c:set var="answerValue1" value="${answerValue}" scope="page"/>
																</c:if>

																<form:input type="hidden" path="answerSheet.answers[${question.id}].values" value="${answerValue}"/>
															</c:if>
															<q:question-radio question="${question}"/>
															<q:question-text question="${question}"/>
															<q:question-select question="${question}"/>
															<q:question-multi-select question="${question}"/>
															<q:question-date question="${question}"/>
															<q:question-textarea question="${question}"/>
															<q:question-link question="${question}"/>
															<script>
																setcutommandatory("${question.id}");
															</script>
														</div>
														<c:if test="${nextQuestionOrder != question.order}">
															<c:set var="visibleQuestionIndex" value="${visibleQuestionIndex + 1}" scope="page"/>
														</c:if>
														<c:if test="${visibleQuestionIndex % 2 == 0}">
															<div class="clearfix">
																<!-- clearfix lg cols every 2 -->
															</div>
														</c:if>
													</c:if>

													<c:if test="${question.render ? false : true }">
														<script>
															clearformelement("${question.id}", "${question.appearence}");
														</script>
													</c:if>
												</c:if>
											</c:if>
											<c:if test="${section.order != pagenumber}">
												<form:input type="hidden" path="answerSheet.answers[${question.id}].questionId" value="${question.id}"/>
												<c:if test="${questionid != null || questionid != ''}">
													<c:set var="questionid" value="${questionid},${question.id}"/>
												</c:if>
												<c:if test="${questionid == null || questionid == ''}">
													<c:set var="questionid" value="${question.id}"/>
												</c:if>
												<c:if test="${question.label!=null}">
													<q:question-readonly3 question="${question}" answerSheet="${questionnaireform.answerSheet}"></q:question-readonly3>
												</c:if>
											</c:if>
										</c:if>
									</c:if>
									<c:set var="prevQuestionOrder" value="${question.order}" scope="page"/>
								</c:forEach>
								<c:set var="visibleQuestionIndex" value="${visibleQuestionIndex + 1}" scope="page"/>
							</div>
						</c:forEach>
						<form:input type="hidden" path="questionsRendered" value="${questionid}"/>
						<input id="action" type="hidden" name="action" value=""/>

						<br/>
						<div class="row">
							<c:if test="${previousstep != null && previousstep == true}">
								<div class="col-md-4">
									<c:set var="localdonotredir" value="&donotredir=true"/>
									<a class="btn btn-default pull-right" role="button" href="entitlementsQuestions.html?pagenumurl=<c:out value='${pagenumber-1}'/> <c:out value='${localdonotredir}'/>">
										<span class="fc-icon fc-icon-left-single-arrow"></span>
										<spring:message code="previous.step"/>
									</a>
								</div>
							</c:if>
							 
						</div>
					</form:form>
			 
		
		</div>
		<!-- box -->
	</div>
</main>

<jsp:include page="common/footer.jsp"/>
</body>
</html>