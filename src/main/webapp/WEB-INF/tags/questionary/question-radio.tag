<%@ tag language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>

<%@ attribute name="question" type="com.jbuild.forms.jbuildforms.model.questionnaire.Question" required="true"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="b" uri="/WEB-INF/taglib/basetags.tld"%>
<%@ taglib prefix="q" uri="/WEB-INF/taglib/questionary.tld"%>

<c:choose>
	<c:when test="${question.type == 'list' && question.appearence == 'radio'}">
		<q:form-element-container
			path="answerSheet.answers[${question.id}].values"
			required="${question.mandatory}"
			label="${question.label.text}"
		>
		<fieldset class="radio_group">
			<c:forEach items="${question.options}" var="option">
				<label style="padding-top:7px" class=" add-margin-right">
					<form:radiobutton style="padding-top:7px" disabled="${question.enabled == false || readOnly ? 'true' : 'false'}" path="answerSheet.answers[${question.id}].values" value="${option.value}"/>
					&nbsp;<spring:message code="${option.label.text}"/>
				</label>
			</c:forEach>
			<span id="inputError2Status" class="sr-only">(Error)</span>
			<div class="text-danger hidden" id="answerSheet.answers${question.id}.values.error.text">
				<small><spring:message code="choose.value"/></small>
			</div>
			<div class="text-warning hidden" id="answerSheet.answers${question.id}.values.warning.text">
				<small></small>
			</div>
			<c:if test="${question.dependsOnQuestion != ''}"> <%-- For this kind of hard coded solution we only want to include the script once --%>
				<script type="text/javascript">
					dependsOnEvent($("[name^='answerSheet.answers[${question.id}].values']"), "${question.dependsOnQuestion}");
				</script>
			</c:if>
			</fieldset>
		</q:form-element-container>
		<c:if test="${question.id == '1'}"> <%-- For this kind of hard coded solution we only want to include the script once --%>
			<script type="text/javascript"></script>
		</c:if>
	</c:when>
</c:choose>