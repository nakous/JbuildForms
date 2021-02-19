<%@ tag language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>

<%@ attribute name="questionnaire" type="com.jbuild.forms.jbuildforms.model.questionnaire.Questionnaire" required="true"%>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<h2>
	<spring:message code="${questionnaire.label.text}"/>
</h2>
