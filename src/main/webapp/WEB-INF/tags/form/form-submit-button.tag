<%@ tag language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>

<%@ attribute name="name" required="false"%>
<%@ attribute name="label" required="false"%>
<%@ attribute name="cssClass" required="false"%>
<%@ attribute name="id" required="false"%>
<%@ attribute name="disabled" type="java.lang.Boolean" required="false"%>
<%@ attribute name="hidden" type="java.lang.Boolean" required="true"%>
<%@ attribute name="submitParameter" type="java.lang.Boolean" required="false"%>
<%@ attribute name="form" required="false"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/taglib/basetags.tld" prefix="b"%>

<%-- Submit button component. 

Example usage:

<b:form-submit-button/>

--%>
<c:if test="${!hidden}">
	<c:set var="label" value="${(empty label) ? 'prompt.submit' : label}"/>
	<c:set var="cssClass" value="${(empty cssClass) ? 'btn-primary' : cssClass} "/>
	<c:set var="disabled" value="${(empty disabled) ? false : disabled}"/>
	<c:set var="id" value="${(empty id) ? 'btnSubmit' : id}"/>
	<c:set var="submitParameter" value="${(empty submitParameter) ? false : submitParameter}"/>

	<input
		name="${name}"
		type="button"
		value="<spring:message code='${label}'/>"
		id="${id}"
		class="btn ${cssClass} ${disabled ? 'disabled' : ''}"
		${disabled ? 'disabled' : ''}
		${sessionScope.isFM ? 'onclick="alertIsFM();"' : ''}
	/>
	<c:if test="${submitParameter}">
		<script>
			var additionalParam = "#${name}";
			submitWithParam = function(button, param) {
				button.disabled = true;
				//alert("Submit parameter = ${submitParameter} and id = ${id} and param = |" + param + "|");
				var input = $("<input>").attr("type", "hidden").attr("name", param).attr("value", param);
				//alert("INPUT : |" + input + "|");
				var localform = $(button).parents("form:first");
				localform.append($(input));

				localform.submit();
			};
			$("#${id}").click(function() {
				console.log("Clicking ..... on ${id}");

				if ("${id}" == "btnNext"){
					$("#saveDocForLater").val(false);
					$('#mainForm').attr('onsubmit', 'return submitForm();');
				}
				if ("${id}" == "btnSaveDocForLater"){
					$("#saveDocForLater").val(true);
					$('#mainForm').attr('onsubmit', 'return true;');
				}
				if ("${id}" == "btnCancellation"){
					$('#mainForm').attr('onsubmit', 'return false;');
				}
				// Submit with hidden attribute
				submitWithParam("#${id}", "${name}");
			});
		</script>
	</c:if>
</c:if>