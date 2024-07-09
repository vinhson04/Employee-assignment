<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Student Tracker App</title>
    <link type="text/css" rel="stylesheet" href="css/style.css">
    <%-- <link type="text/css" rel="stylesheet" href="css/list.css"> --%>
</head>
<body>
<div id="wrapper">
    <header id="header">
        <h1>ABC Company Employee List</h1>
    </header>
</div>

<div id="container">
    <div id="content">

        <input type="button" value="Add Employee?" onclick="window.location.href='add-employee-form.jsp'; return false;" class="add-student-button" />

        <table>
            <tr>
                <th>Employee ID</th>
                <th>Employee Name</th>
                <th>Birth Day</th>
                <th>Phone</th>
                <th>Email</th>
                <th>Action</th>
            </tr>
            <c:forEach var="tempEmployee" items="${EMPLOYEE_LIST}">

                <c:url var="tempLink" value="EmployeeController">
                    <c:param name="command" value="LOAD"/>
                    <c:param name="feedbackId" value="${tempEmployee.employee_id}"/>
                </c:url>

                <c:url var="deleteLink" value="EmployeeController">
                    <c:param name="command" value="DELETE"/>
                    <c:param name="feedbackId" value="${tempEmployee.employee_id}"/>
                </c:url>
                <tr>
                    <td>${tempEmployee.employee_id}</td>
                    <td>${tempEmployee.employee_name}</td>
                    <td>${tempEmployee.birthday}</td>
                    <td>${tempEmployee.phone}</td>
                    <td>${tempEmployee.email}</td>
                    <td>
                        <a href="${tempLink}" class="update-link">Update</a> |
                        <a href="${deleteLink}" class="delete-link" onclick="if (!(confirm('Are you sure you want to delete this feedback?'))) return false">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>
