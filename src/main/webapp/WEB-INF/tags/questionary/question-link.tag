<%@ tag language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>

<%@ attribute name="question" type="com.jbuild.forms.jbuildforms.model.questionnaire.Question" required="true"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="b" uri="/WEB-INF/taglib/basetags.tld"%>
<%@ taglib prefix="q" uri="/WEB-INF/taglib/questionary.tld"%>

<c:choose>
	<c:when test="${question.type == 'field' && question.appearence == 'link'}">
		<q:form-element-container
			path="answerSheet.answers[${question.id}].values"
			required="${question.mandatory}"
			label="${question.label.text}"
		>
<%-- 			<form:input --%>
<%-- 				type="text" --%>
<%-- 				cssClass="form-control" --%>
<%-- 				disabled="${question.enabled == false || readOnly ? 'true' : 'false'}" --%>
<%-- 				path="answerSheet.answers[${question.id}].values" --%>
<%-- 				dependsonquestion="" --%>
<%-- 			/> --%>
			 <div style="padding-top: 7px;">
				<b>
					<a href="${question.labels.get(1).text}" 
					target="_blank" class="link_" id="answerSheet.answers[${question.id}].values" name="answerSheet.answers[${question.id}].values">
					<spring:message code="${question.labels.get(0).text}" /></a>
				</b>
			</div>		
			<c:if test="${question.dependsOnQuestion != ''}"> <%-- For this kind of hard coded solution we only want to include the script once --%>
				<script type="text/javascript">
					dependsOnEvent($("[name^='answerSheet.answers[${question.id}].values']"), "${question.dependsOnQuestion}");
				</script>
			</c:if>
		</q:form-element-container>
	</c:when>
</c:choose>