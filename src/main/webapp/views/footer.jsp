<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="p" tagdir="/WEB-INF/tags/" %>
	<footer class="navbar-fixed-bottom">
		<div class="btn-group btn-group-sm pull-right" role="group">
			<form action="./DashBoard" Method="GET">
				<input type="submit" name="offset" value="10" />
				<input type="submit" name="offset" value="50" /> <input type="submit"
					name="offset" value="100" />
			</form>
		</div>

		<div class="container text-center">
			<p:pagination page="${page}"></p:pagination>
		</div>

	</footer>