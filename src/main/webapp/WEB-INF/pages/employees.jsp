<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<title>Servlet task</title>
<script src="<c:url value="/resources/js/jquery.min.js"/> "></script>
<script src="<c:url value="/resources/js/script.js"/> "></script>
<link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>">
</head>
<body>
    <sf:form method="POST" modelAttribute="employee">
        <fieldset>
            <table>
                <tr>
                    <th><sf:label path="fname">First name:</sf:label> </th>
                    <td><sf:input path="fname"/></td>
                </tr>
                <tr>
                    <th><sf:label path="lname">Last name:</sf:label> </th>
                    <td><sf:input path="lname"/></td>
                </tr>
                <tr>
                    <th><sf:label path="email">Email:</sf:label> </th>
                    <td><sf:input path="email"/></td>
                </tr>
                <tr>
                    <th></th>
                    <td><input name="commit" type="submit" value="Add employee"></td>
                </tr>
            </table>
        </fieldset>
    </sf:form>
	<div class="errMsg">
		<c:out value="${errMsg}" />
	</div>
	<div class="empTable">
		<c:choose>
			<c:when test="${empty employees}">
				<!-- Inform user that there's no employee yet -->
                <c:out value="Employee list is empty"></c:out>
			</c:when>
			<c:otherwise>
                <table>
				<c:forEach var="employee" items="${employees}">
					<tr>
                        <td><c:out value="${employee.id}"></c:out> </td>
						<td><c:out value="${employee.fname}"></c:out></td>
						<td><c:out value="${employee.lname}"></c:out></td>
						<td><c:out value="${employee.email}"></c:out></td>
					</tr>
                </c:forEach>
                </table>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>