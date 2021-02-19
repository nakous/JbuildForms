<%@ tag language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>

<%@ attribute name="section" type="com.jbuild.forms.jbuildforms.model.questionnaire.Section" required="true"%>

<%@ attribute name="var" required="true" type="java.lang.String" rtexprvalue="false"%>
<%@ variable alias="question" name-from-attribute="var" scope="NESTED" variable-class="com.jbuild.forms.jbuildforms.model.questionnaire.Question"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:forEach items="${section.questions}" var="question" varStatus="stat">
	<form:input type="hidden" path="answerSheet.answers[${question.id}].questionId" value="${question.id}"/>
	<form:input type="hidden" path="questionsRendered" value="${question.id}"/>
	<jsp:doBody/>
	<script>
		setcutommandatory("${question.id}");
	</script>
	<c:if test="${question.render ? false : true}">
		<script>
			clearformelement("${question.id}", "${question.appearence}");
		</script>
	</c:if>
</c:forEach>