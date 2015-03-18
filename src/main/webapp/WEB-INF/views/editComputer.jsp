<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

    <section id="main">
        <div class="container">
            <div class="row">
                <div class="col-xs-8 col-xs-offset-2 box">
                    <div class="label label-default pull-right">
                        id: ${computer.id}
                    </div>
                    <h1><spring:message code="computer.edit" /></h1>

                    <form action="editComputer" method="POST">
                        <input type="hidden" value="0"/>
                        <fieldset>
                            <div class="form-group">
                                <label for="computerName"><spring:message code="computer.name" /></label>
                                <input type="text" class="form-control" id="computerName" placeholder="${computer.name}">
                            </div>
                            <div class="form-group">
                                <label for="introduced"><spring:message code="computer.introduced" /></label>
                                <input type="date" class="form-control" id="introduced" placeholder="<spring:message code="computer.introduced" />">
                            </div>
                            <div class="form-group">
                                <label for="discontinued"><spring:message code="computer.discontinued" /></label>
                                <input type="date" class="form-control" id="discontinued" placeholder="<spring:message code="computer.discontinued" />">
                            </div>
                            <div class="form-group">
                                <label for="companyId"><spring:message code="company.name" /></label>
                                <label for="companyId"><spring:message code="company.name" /></label> <select
								class="form-control" id="companyId" name="companyId">
								<c:forEach var="comp" items="${companies}">
									<option value="${comp.id}">${comp.name}</option>
								</c:forEach>
							</select>
                            </div>            
                        </fieldset>
                        <div class="actions pull-right">
                            <input type="submit" value="<spring:message code="computer.edit" />" class="btn btn-primary">
                            <spring:message code="add.or" />
                            <a href="./dashboard" class="btn btn-default"><spring:message code="add.cancel" /></a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>
</body>
</html>