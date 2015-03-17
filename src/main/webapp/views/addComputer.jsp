<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="header.jsp"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<section id="main">
	<div class="container">
		<div class="row">
			<div class="col-xs-8 col-xs-offset-2 box">
				<h1><spring:message code="computer.add" /></h1>
				<form action="./dashboard" name="addform" method="POST">
					<fieldset>
						<div class="form-group">
							<label for="computerName"><spring:message code="computer.name" /></label> <input
								type="text" class="form-control" id="computerName"
								name="computerName" placeholder="<spring:message code="computer.name" />">
						</div>
						<div class="form-group">
							<label for="introduced"><spring:message code="computer.introduced" /></label> <input
								type="date" class="form-control" id="introduced"
								name="introduced" placeholder="<spring:message code="computer.introduced" />">
						</div>
						<div class="form-group">
							<label for="discontinued"><spring:message code="computer.discontinued" /></label> <input
								type="date" class="form-control" id="discontinued"
								name="discontinued" placeholder="<spring:message code="computer.discontinued" />">
						</div>
						<div class="form-group">
							<label for="companyId"><spring:message code="company.name" /></label> <select
								class="form-control" id="companyId" name="companyId">
								<c:forEach var="comp" items="${companies}">
									<option value="${comp.id}">${comp.name}</option>
								</c:forEach>
							</select>
						</div>
					</fieldset>
					<div class="actions pull-right">
						<input type="submit" id="add" value="<spring:message code="computer.add" />" class="btn btn-primary">
						<spring:message code="add.or" /> <a href="dashboard" class="btn btn-default"><spring:message code="add.cancel" /></a>
					</div>
				</form>
			</div>
		</div>
	</div>
</section>
<script src="${request.getContextPath()}js/jquery.min.js"></script>
<script src="${request.getContextPath()}js/addcomputer.js"></script>
</body>
</html>