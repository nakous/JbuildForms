<%@ tag language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>

<%@ attribute name="label" type="com.jbuild.forms.jbuildforms.model.questionnaire.Label" required="true"%>
<%@ attribute name="mandatory" type="java.lang.Boolean" required="true"%>


<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${label!=null}">
	<label for="" id="label_4">
		<spring:message code="${label.text}"/>
		<c:if test="${mandatory}">
			<abbr class="abbr_mandatory" title="<spring:message code="obligatory.information"/>">*</abbr>
		</c:if>
	</label>
</c:if>