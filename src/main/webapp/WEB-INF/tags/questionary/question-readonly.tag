<%@ tag language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>

<%@ attribute name="question" type="com.jbuild.forms.jbuildforms.model.questionnaire.Question" required="true"%>
<%@ attribute name="answerSheet" type="com.jbuild.forms.jbuildforms.model.questionnaire.AnswerSheet" required="true"%>


<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="b" uri="/WEB-INF/taglib/basetags.tld"%>
<%@ taglib prefix="q" uri="/WEB-INF/taglib/questionary.tld"%>

<c:forEach items="${answerSheet.answers[question.id].values}" var="answer_text">
	<c:if test="${question.appearence != 'select' && question.decaleInView}">
		<div class="col-sm-6 add-margin-bottom"></div>
	</c:if>
	<c:if test="${question.newLineDecale}">
		<div class="clearfix">
		</div>
		<br/>
	</c:if>
	<c:set var="colSpan" value="6"/>
	<c:set var="colSpanLabel" value="6"/>
	<c:set var="colSpanSelect" value="6"/>
	<c:if test="${question.appearence == 'select' && question.decaleInView}">
		<c:set var="colSpan" value="12"/>
		<c:set var="colSpanLabel" value="3"/>
		<c:set var="colSpanSelect" value="9"/>
	</c:if>
	<div class="col-sm-${colSpan} add-margin-bottom">
		<c:set var="answer" value="${answer_text}" scope="page"/>
		<div class="col-sm-${colSpanLabel} text-right">
			<spring:message code="${question.label.text}"/>
			<!-- qid: ${question.id} -->
		</div>
		<c:choose>
			<c:when test="${question.type == 'list' && question.appearence == 'radio'}">
				<c:forEach items="${question.options}" var="option">
					<c:if test="${option.value eq answer}">
						<c:set var="answerValue" value="${option.label.text}"/>
					</c:if>
				</c:forEach>
				<div class="col-sm-6">
					<b><spring:message code="${answerValue}"/></b>
				</div>
			</c:when>
			<c:when test="${question.appearence == 'text' || question.appearence == 'date' || question.appearence == 'textarea' || question.appearence == 'start-date'  || question.appearence == 'end-date'}">
				<div class="col-sm-6">
					<b>${answer}</b>
				</div>
			</c:when>
			<c:when test="${question.type == 'list' && question.appearence == 'select'}">
				<c:if test="${question.optionsBeanName != null}">
					<c:set var="optionListName" value="${question.optionsBeanName}" scope="page"/>
				</c:if>
				<q:option-list-bean optionListName="${question.optionsBeanName}" var="optionList"/>
				<c:forEach var="option" items="${optionList}">
					<c:if test="${option.key eq answer}">
						<c:if test="${question.optionsBeanIsTranslated}">
							<c:set var="answerValue">
								<spring:message code="${option.value}"/>
							</c:set>
						</c:if>
						<c:if test="${!question.optionsBeanIsTranslated}">
							<c:set var="answerValue" value="${option.value}"/>
						</c:if>
					</c:if>
				</c:forEach>
				<div class="col-sm-${colSpanSelect}">
					<b>${answerValue}</b>
				</div>
			</c:when>
		</c:choose>
		<c:set var="answerValue" value=""/>
		<c:set var="answer" value=""/>
	</div>
</c:forEach>