<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Servlet task</title>
<script src="js/jquery.min.js"></script>
<script src="js/script.js"></script>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	 <form action="index.html" method="post">
		<!-- Create fields for new employee -->
    	<fieldset>
            <legend>Employee input</legend>
            <table>
                <tr>
                    <td>First name: * </td>
                    <td><input type="text" name="first_name"></td>
                </tr>
                <tr>
                    <td>Last name: * </td>
                    <td><input type="text" name="last_name"></td>
                </tr>
                <tr>
                    <td>Email: * </td>
                    <td><input type="text" name="email_in"></td>
                </tr>
            </table>
            <input type="submit" name="addEmp" value="add employee">
        </fieldset>
    </form>
	<div class="errMsg">
		<c:out value="${errMsg}" />
	</div>
	<div class="empTable">
		<c:choose>
			<c:when test="${empty applicationScope.employees}">
				<!-- Inform user that there's no employee yet -->
			</c:when>
			<c:otherwise>
				<c:forEach var="employee" items="${applicationScope.employees}">
					<tr>
						<td><c:out value="${employee.fname}"></c:out></td>
						<td><c:out value="${employee.lname}"></c:out></td>
						<td><c:out value="${employee.email}"></c:out></td>
					</tr>
                </c:forEach>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>