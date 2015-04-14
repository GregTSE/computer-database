<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@include file="header.jsp"%>

<section id="main">
	<div class="container">
		<h1 id="homeTitle">
			${computersFound}
			<spring:message code="computers.found" />
		</h1>
		<div id="actions" class="form-horizontal">
			<div class="pull-left">

				<form id="searchForm" action="./dashboard" method="GET"
					class="form-inline">

					<input type="search" id="searchbox" name="search"
						class="form-control"
						placeholder="<spring:message code="field.search" />" /> <input
						type="submit" id="searchsubmit"
						value="<spring:message code="button.filter" />"
						class="btn btn-primary" />
				</form>

			</div>
			<sec:authorize ifAnyGranted="ROLE_ADMIN">
				<div class="pull-right">
					<a class="btn btn-success" id="addComputer" href="./addComputer">
						<spring:message code="button.add" />
					</a> <a class="btn btn-default" id="editComputer" href="#"
						onclick="$.fn.toggleEditMode();"> <spring:message
							code="button.edit" />
					</a>
				</div>
			</sec:authorize>
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
					<th><spring:message code="field.name" /> <a href='dashboard?offset=${page.offset}&sort=${page.toogle}'
						style="float: right"> <i class="fa fa-sort"></i>
						<input type="hidden" id="offset" name="offset" value="0"/>
					</a></th>
					<th><spring:message code="date.introduced" /></th>
					<!-- Table header for Discontinued Date -->
					<th><spring:message code="date.discontinued" /></th>
					<!-- Table header for Company -->
					<th><spring:message code="company.name" /></th>
				</tr>
			</thead>
			<!-- Browse attribute computers -->
			<tbody id="results">
				<c:forEach var="computer" items="${computersDTO}">
					<tr>
						<td class="editMode"><input type="checkbox" name="cb"
							class="cb" value="${computer.id}"></td>
							
						<td>
							<sec:authorize ifAnyGranted="ROLE_ADMIN">
								<a href="./editComputer?id=${computer.id}" onclick="">${computer.name}</a>
							</sec:authorize>
							<sec:authorize ifAnyGranted="ROLE_USER">
								${computer.name}
							</sec:authorize>
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

<script type="text/javascript">
  var strings = new Array();
  strings['button.view'] = "<spring:message code='button.view' javaScriptEscape='true' />";
  strings['button.edit'] = "<spring:message code='button.edit' javaScriptEscape='true' />";
  strings['confirm.delete'] = "<spring:message code='confirm.delete' javaScriptEscape='true' />";
</script>

</body>
</html>