<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">



<!-- Bootstrap -->
<link href="${request.getContextPath()}css/bootstrap.min.css"
	rel="stylesheet" media="screen">
<link href="${request.getContextPath()}css/font-awesome.css"
	rel="stylesheet" media="screen">
<link href="${request.getContextPath()}css/main.css" rel="stylesheet"
	media="screen">
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="./DashBoard"> Application -
				Computer Database </a>
		</div>
	</header>

	<section id="main">
		<div class="container">
			<h1 id="homeTitle">
				${page.countComputers} Computers found 
			</h1>
			<div id="actions" class="form-horizontal">
				<div class="pull-left">
					<form id="searchForm"  action="./Filter" method="GET" class="form-inline">

						<input type="search" id="searchbox" name="search"
							class="form-control" placeholder="Search name" />
						<input type="submit" id="searchsubmit" value="Filter by name"
							class="btn btn-primary" />
					</form>
				</div>
				<div class="pull-right">
					<a class="btn btn-success" id="addComputer" href="./ToAddComputer">Add
						Computer</a> <a class="btn btn-default" id="editComputer" href="#"
						onclick="$.fn.toggleEditMode();">Edit</a>
				</div>
			</div>
		</div>

		<form id="deleteForm" action="#" method="POST">
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
						<th>Computer name</th>
						<th>Introduced date</th>
						<!-- Table header for Discontinued Date -->
						<th>Discontinued date</th>
						<!-- Table header for Company -->
						<th>Company</th>

					</tr>
				</thead>
				<!-- Browse attribute computers -->
				<tbody id="results">
					<c:forEach var="computer" items="${page.computersDTO}">
						<tr>
							<td class="editMode"><input type="checkbox" name="cb"
								class="cb" value="0"></td>

							<td>
								<a href="./views/editComputer.jsp" onclick="">${computer.name}</a>
							</td>
							<td>${computer.dateIntroduced}</td>
							<td>${computer.dateDiscontinued}</td>
							<td>${computer.company.name}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</section>

	<footer class="navbar-fixed-bottom">
		<div class="btn-group btn-group-sm pull-right" role="group">
			<form action="./DashBoard" Method="GET">
                <input type="submit" name="offset" value="10" />
                <input type="submit" name="offset" value="50" />
                <input type="submit" name="offset" value="100" />
            </form>
		</div>
		
		<div class="container text-center">
			<ul class="pagination">
				<li><a href="./DashBoard?index=${page.num+1}&offset=${page.offset}" aria-label="Previous"> <span
						aria-hidden="true">&laquo;</span>
				</a></li>
				<c:forEach var="index" begin="${page.begin}" end="${page.end}">
					<li><a href="./DashBoard?index=${index}&offset=${page.offset}">
							<c:if test= "${ index == page.num }"> <b> </c:if>
								 ${index}
							 <c:if test= "${ index == page.num }"> </b> </c:if>
						</a></li>
				</c:forEach>
				<li><a href="./DashBoard?index=${page.num+1}&offset=${page.offset}" aria-label="Next"> <span aria-hidden="true">&raquo;</span>
				</a></li>
			</ul>
		</div>
		
		
		
	</footer>
	
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/dashboard.js"></script>

</body>
</html>