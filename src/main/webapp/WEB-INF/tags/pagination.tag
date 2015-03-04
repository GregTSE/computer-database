<%@ tag body-content="empty" %>
<%@ attribute name="page" required="true" type="com.excilys.formation.cdb.model.Page" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<ul class="pagination">
	<li><a
		href="./DashBoard?index=${page.num-1}&offset=${page.offset}&search=${page.word}"
		aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
	</a></li>
	<c:forEach var="index" begin="${page.beginPage}" end="${page.endPage}">
		<c:if test="${ page.nbComputersPerPage > 0 }">
			<li><a
				href="./DashBoard?index=${index}&offset=${page.offset}&search=${page.word}">
					<c:if test="${ index == page.num }">
						<b>
					</c:if> ${index} <c:if test="${ index == page.num }">
						</b>
					</c:if>
			</a></li>
		</c:if>
	</c:forEach>
	<li><a
		href="./DashBoard?index=${page.num+1}&offset=${page.offset}"
		aria-label="Next"> <span aria-hidden="true">&raquo;</span>
	</a></li>
</ul>