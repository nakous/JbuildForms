<%@ tag language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>

<%@ attribute name="question" type="com.jbuild.forms.jbuildforms.model.questionnaire.Question" required="true"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="b" uri="/WEB-INF/taglib/basetags.tld"%>
<%@ taglib prefix="q" uri="/WEB-INF/taglib/questionary.tld"%>

<c:choose>
	<c:when test="${question.type == 'field' && question.appearence == 'textarea'}">
		<q:form-element-container
			path="answerSheet.answers[${question.id}].values"
			required="${question.mandatory}"
			label="${question.label.text}"
		>
			<form:textarea
				type="text"
				cssClass="form-control"
				disabled="${question.enabled == false || readOnly ? 'true' : 'false'}"
				path="answerSheet.answers[${question.id}].values"
				dependsonquestion=""
				rows="4"
				cols="50"
			/>
			<span id="inputError2Status" class="sr-only">(Error)</span>
			<div class="text-danger hidden" id="answerSheet.answers${question.id}.values.error.text">
				<small><spring:message code="field.is.empty"/></small>
			</div>
			<div class="text-warning hidden" id="answerSheet.answers${question.id}.values.warning.text">
				<small></small>
			</div>
			<c:if test="${question.dependsOnQuestion != ''}"> <%-- For this kind of hard coded solution we only want to include the script once --%>
				<script type="text/javascript">
					console.log("text field")
					$("[name^='answerSheet.answers[${question.id}].values']").attr("dependsonquestion", "${question.dependsOnQuestion}");
					$("[name^='answerSheet.answers[${question.dependsOnQuestion}].values']").each(function() {
						var $changeCaller = $(this);
						$(this).change(function() {
							$('[dependsonquestion]').each(function() {
								var depquestion = "answerSheet.answers["+$(this).attr('dependsonquestion')+"].values";
								if (depquestion == $changeCaller.attr('name')) {
									checkRenderedQuestion($changeCaller, $(this).attr('dependsonquestion'), ${question.id}, ${question.id});
								}
							});
						});
					});
				</script>
			</c:if>
		</q:form-element-container>
		<script type="text/javascript">
			$(document).ready(function() {
				$("input[id^='answerSheet.answers2.values']").keypress(function(event) {
					var charCode = event.charCode || 0;
					if (charCode >= 48 && charCode <= 57) { // Numbers
						if ($("input[id^='answerSheet.answers2.values']").val().length < 6) { // Up to 6 numbers
							return true;
						} else {
							return false;
						}
					} else if (charCode == 0 || event.ctrlKey) { // (Not a character key or unrecognized) or CTRL pressed
						return true;
					} else {
						return false; // Most likely a character that is not allowed in input
					}
				});
			});
		</script>
	</c:when>
</c:choose>