<%@ tag language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>

<%@ attribute name="question" type="com.jbuild.forms.jbuildforms.model.questionnaire.Question" required="true"%>
<%@ attribute name="answerSheet" type="com.jbuild.forms.jbuildforms.model.questionnaire.AnswerSheet" required="true"%>


<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="b" uri="/WEB-INF/taglib/basetags.tld"%>
<%@ taglib prefix="q" uri="/WEB-INF/taglib/questionary.tld"%>

<c:choose>
	<c:when test="${question.type == 'list' && question.appearence == 'radio'}">
		<c:forEach items="${answerSheet.answers[question.id].values}" var="answer_radio">
			<c:set var="answerValue" value="${answer_radio}"/>
		</c:forEach>
		<form:input type="hidden" path="answerSheet.answers[${question.id}].values" value="${answerValue}"/>
	</c:when>
	<c:when test="${question.type == 'field' && (question.appearence == 'text' || question.appearence == 'hidden' || question.appearence == 'date')}">
		<c:set var="answerValuetst" value=""/>
		<c:forEach items="${answerSheet.answers[question.id].values}" var="answer_text">
			<c:set var="answerValue" value="${answer_text}"/>
			<c:set var="answerValuetst" value="${answer_text}"/>
		</c:forEach>
		<c:if test="${question.id == 1}">
			<c:set var="answerValue1" value="${answerValue}" scope="page"/>
		</c:if>
		<form:input type="hidden" path="answerSheet.answers[${question.id}].values" value="${answerValue}"/>
	</c:when>
	<c:when test="${question.type == 'list' && question.appearence == 'select'}">
		<c:if test="${question.optionsBeanName != null}">
			<c:set var="optionListName" value="${question.optionsBeanName}" scope="page"/>
			<q:option-list-bean optionListName="${question.optionsBeanName}" var="optionList"/>
		</c:if>
		<c:set var="answerhidden" value=""/>
		<c:forEach items="${answerSheet.answers[question.id].values}" var="answer">
			<c:forEach var="option" items="${optionList}">
				<c:if test="${option.key eq answer}">
					<c:set var="answerhidden" value="${answer}"/>
				</c:if>
			</c:forEach>
		</c:forEach>
		<form:input type="hidden" path="answerSheet.answers[${question.id}].values" value="${answerhidden}"/>
	</c:when>
</c:choose>