<%@ tag language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>

<%@ attribute name="question" type="com.jbuild.forms.jbuildforms.model.questionnaire.Question" required="true"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="b" uri="/WEB-INF/taglib/basetags.tld"%>
<%@ taglib prefix="q" uri="/WEB-INF/taglib/questionary.tld"%>

<c:choose>
	<c:when test="${question.type == 'field' && question.appearence == 'end-date'}">
		<script>
			$("#form").submit(function(event) {
				// Disable buttons accept/reject
				var date = $("input[name^='answerSheet.answers[${question.id}].date_values']").val();
				var hour = $("select[id^='answerSheet.answers[${question.id}].values.hour']").val();
				if (date != "") {
					$("input[name^='answerSheet.answers[${question.id}].values']").val(date + " " + hour);
				}
			});
		</script>
		<q:form-element-container
			path="answerSheet.answers[${question.id}].values"
			required="${question.mandatory}"
			label="${question.label.text}"
		>
			<span class="col-md-8" style="padding-left:0px">
				<input
					type="text"
					placeholder="dd/mm/yyyy"
					class="field datepicker form-control"
					<c:if test="${question.enabled == false || readOnly}">disabled</c:if>
					name="answerSheet.answers[${question.id}].date_values"
					dependsonquestion=""
				/>
			</span>
			<form:input type="hidden" path="answerSheet.answers[${question.id}].values"/>
			<span class="col-md-4 pull-right" style="padding-right: 0px;">
				<select
					class="form-control"
					<c:if test="${question.enabled == false || readOnly}">disabled</c:if>
					id="answerSheet.answers[${question.id}].values.hour"
				>
					<option value="AM">
						<spring:message code="AM"/>
					</option>
					<option value="PM" selected>
						<spring:message code="PM"/>
					</option>
				</select>
			</span>
			<span id="inputError2Status" class="sr-only">(Error)</span>
			<div class="text-danger hidden" id="answerSheet.answers${question.id}.values.error.text">
				<small><spring:message code="field.is.empty"/></small>
			</div>
			<div class="text-warning hidden" id="answerSheet.answers${question.id}.values.warning.text">
				<small></small>
			</div>
			<c:if test="${question.dependsOnQuestion != ''}"> <%-- For this kind of hard coded solution we only want to include the script once --%>
				<script type="text/javascript">
					dependsOnEvent($("[name^='answerSheet.answers[${question.id}].values']"),"${question.dependsOnQuestion}");
				</script>
			</c:if>
		</q:form-element-container>
		<script>
			$(document).ready(function() {
				var date_hour = $("input[name^='answerSheet.answers[${question.id}].values']").val();
				if (date_hour != "") {
					var date = date_hour.substr(0, date_hour.indexOf(' '));
					var hour = date_hour.substr(date_hour.indexOf(' ') + 1);
					$("input[name^='answerSheet.answers[${question.id}].date_values']").val(date);
					$("select[id^='answerSheet.answers[${question.id}].values.hour']").val(hour);
				}
			});
		</script>
	</c:when>
</c:choose>