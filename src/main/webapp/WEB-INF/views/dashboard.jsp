<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@include file="header.jsp"%>

<section id="main">
	<div class="container">
		<h1 id="homeTitle">${computersFound}
			<spring:message code="computer.found" />
		</h1>
		<div id="actions" class="form-horizontal">
			<div class="pull-left">

				<form id="searchForm" action="./dashboard" method="GET"
					class="form-inline">

					<input type="search" id="searchbox" name="search"
						class="form-control" placeholder="Search name" /> <input
						type="submit" id="searchsubmit" value="<spring:message code="computer.filter" />"
						class="btn btn-primary" />
				</form>

			</div>
			<div class="pull-right">
				<a class="btn btn-success" id="addComputer" href="./addComputer">
					<spring:message code="computer.add" /> </a> <a class="btn btn-default" id="editComputer" href="#"
					onclick="$.fn.toggleEditMode();"> <spring:message code="computer.edit" /> </a>
			</div>
		</div>
	</div>

	<form id="deleteForm" action="./dashboard" method="POST">
		<input type="hidden" name="selection" value="">
	</form>

	<div class="container" style="margin-top: 10px;">
		<table class="table table-striped table-bordered">
			<thead>
				<tr>
					<!-- Variable declarations for passing labels as parameters -->
					<!-- Table header for Computer Name -->

					<th class="editMode" style="width: 60px; height: 22px;"><input
						type="checkbox" id="selectall" /> <span
						style="vertical-align: top;"> - <a href="#"
							id="deleteSelected" onclick="$.fn.deleteSelected();"> <i
								class="fa fa-trash-o fa-lg"></i>
						</a>
					</span></th>
					<th><spring:message code="computer.name" /></th>
					<th><spring:message code="computer.introduced" /></th>
					<!-- Table header for Discontinued Date -->
					<th><spring:message code="computer.discontinued" /></th>
					<!-- Table header for Company -->
					<th><spring:message code="company.name" /></th>
				</tr>
			</thead>
			<!-- Browse attribute computers -->
			<tbody id="results">
				<c:forEach var="computer" items="${page.computersDTO}">
					<tr>
						<td class="editMode"><input type="checkbox" name="cb"
							class="cb" value="${computer.id}"></td>

						<td><a href="./editComputer?id=${computer.id}" onclick="">${computer.name}</a>
						</td>
						<td>${computer.dateIntroduced}</td>
						<td>${computer.dateDiscontinued}</td>
						<td>${computer.companyName}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</section>

<%@include file="footer.jsp"%>


<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/dashboard.js"></script>

</body>
</html>