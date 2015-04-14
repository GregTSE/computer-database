<%@ tag body-content="empty"%>
<%@ attribute name="page" required="true"
	type="com.excilys.formation.cdb.model.Page"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<ul class="pagination">
	<li><a
		href="./dashboard?index=${page.index-1}&offset=${page.offset}&search=${page.search}&sort=${page.sort}"
		aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
	</a></li>
	<c:forEach var="index" begin="${page.beginPage}" end="${page.getEndPage(computersFound)}">
		<li><a
			href="./dashboard?sort=${page.sort}&index=${index-1}&offset=${page.offset}&search=${page.search}">
				<c:if test="${ index-1 == page.index }">
					<b>
				</c:if> ${index} <c:if test="${ index-1 == page.index }">
					</b>
				</c:if>
		</a></li>
	</c:forEach>
	<li><a
		href="./dashboard?index=${page.getNextIndex(computersFound)}&offset=${page.offset}&sort=${page.sort}"
		aria-label="Next"> <span aria-hidden="true">&raquo;</span>
	</a></li>
</ul>