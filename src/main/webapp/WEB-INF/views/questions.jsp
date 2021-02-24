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
					
						<h4 id="questionnaire.title">
							<spring:message code="${questionnaire.label.text}"  />
						</h4>

						 <p>
							<spring:message code="${questionnaire.description.text}"/>
						</p>
						<form:input type="hidden" path="answerSheet.questionnaireId" value="${questionnaire.id}"/>
						<form:input type="hidden" path="answerSheet.templateName" value="${templateName}"/>
						<form:input type="hidden" path="dossierId" value="${dossierId}"/>
						<form:input type="hidden" path="processId" value="${processId}"/>
						<form:input type="hidden" path="pageNumber" value="${pagenumber}"/>
						<form:input type="hidden" path="answerSheet.activePage" value="${pagenumber}"/>
						<form:input type="hidden" path="saveDocForLater" value="${saveDocForLater}"/>

						<c:set var="answerValue1" value="" scope="page"/>
						<c:forEach items="${questionnaire.sections}" var="section">
						  <div id="section-${section.id}" class="card mt-2">
							  <div class="card-body">
								<h4 id="section.title">
									<spring:message code="${section.label.text}"  />
								</h4>
								<p>
									<spring:message code="${section.description.text}"/>
								</p>
								<div class="row">
									<c:set var="visibleQuestionIndex" value="0" scope="page"/>
									<c:forEach items="${section.questions}" var="question" varStatus="stat">
										 
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
									<c:set var="prevQuestionOrder" value="${question.order}" scope="page"/>
								</c:forEach>
								<c:set var="visibleQuestionIndex" value="${visibleQuestionIndex + 1}" scope="page"/>
							</div>
							</div>
							</div><!-- Section End -->
						</c:forEach>
						<form:input type="hidden" path="questionsRendered" value="${questionid}"/>
						<input id="action" type="hidden" name="action" value=""/>

						 
					</form:form>
			 
		
		</div>
		<!-- box -->
	</div>
</main>

<jsp:include page="common/footer.jsp"/>
</body>
</html>