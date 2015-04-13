<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<section id="main">
	<div class="container">
		<div class="row">
			<div class="col-xs-8 col-xs-offset-2 box">
				<div id="id" class="label label-default pull-right">id:
					${computer.id}</div>
				<h1>
					<spring:message code="button.edit" />
				</h1>

				<form:form id="editComputer" action="./editComputer" method="POST"
					modelAttribute="computerDTO">
					<input type="hidden" name="id" value="${computer.id}" />
					<fieldset>
						<div class="form-group">
							<label for="name"><spring:message code="field.name" /></label> <input
								type="text" class="form-control" id="name" name="name"
								value="${computer.name}" onkeyup="$.fn.initName()">
								<form:errors path="name" element="div" cssClass="alert alert-danger"/>
						</div>
						<div class="form-group">
							<label for="dateIntroduced"><spring:message
									code="date.introduced" /></label> <input type="date"
								class="form-control" id="dateIntroduced" name="dateIntroduced"
								value="${computer.dateIntroduced}"
								onkeyup="$.fn.initIntroduced()">
								<form:errors path="dateIntroduced" element="div" cssClass="alert alert-danger"/>
						</div>
						
						
						<div class="form-group">
							<label for="dateDiscontinued"><spring:message
									code="date.discontinued" /></label> <input type="date"
								class="form-control" id="dateDiscontinued"
								name="dateDiscontinued" value="${computer.dateDiscontinued}"
								onkeyup="$.fn.initDiscontinued()">
								<form:errors path="dateDiscontinued" element="div" cssClass="alert alert-danger"/>
						</div>
						
						<div class="form-group">
							<label for="companyId"><spring:message
									code="company.name" /></label> <select class="form-control"
								id="companyId" name="companyId">
								<option value="0">--</option>
								<c:forEach var="comp" items="${companies}">
									<c:choose>
										<c:when test="${comp.id == computer.companyId }">
											<option value="${comp.id}" selected="true"><c:out
													value="${comp.name}" /></option>
										</c:when>
										<c:otherwise>
											<option value="${comp.id}"><c:out
													value="${comp.name}" /></option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
						</div>
					</fieldset>
					<div class="actions pull-right">
						<input type="submit" id="edit" value="<spring:message code="button.edit" />"
							class="btn btn-primary">
						<spring:message code="word.or" />
						<a href="./dashboard" class="btn btn-default"><spring:message
								code="button.cancel" /></a>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</section>

<script type="text/javascript">
  var strings = new Array();
  strings['error.name'] = "<spring:message code='error.name' javaScriptEscape='true' />";
  strings['error.date'] = "<spring:message code='error.date' javaScriptEscape='true' />";
  strings['lang'] = "<spring:message code='lang' javaScriptEscape='true' />";
</script>

<script src="${request.getContextPath()}js/jquery.min.js"></script>
<script src="${request.getContextPath()}js/editcomputer.js"></script>

</body>
</html>