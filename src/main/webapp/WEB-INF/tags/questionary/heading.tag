<%@ tag language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>

<%@ attribute name="label" type="com.jbuild.forms.jbuildforms.model.questionnaire.Label" required="true"%>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${label!=null}">
	<h3>
		<spring:message code="${label.text}"/>
	</h3>
</c:if>