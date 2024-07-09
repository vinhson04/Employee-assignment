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
        <h1>CodeLean Academy</h1>
    </header>
</div>

<div id="container">
    <div id="content">
        <!-- Add Student button -->
        <input type="button" value="Add Feedback?" onclick="window.location.href='add-feedback-form.jsp'; return false;" class="add-student-button" />

        <table>
            <tr>
                <th>Name</th>
                <th>Email</th>
                <th>Phone</th>
                <th>Message</th>
                <th>Action</th>
            </tr>
            <c:forEach var="tempFeedback" items="${FEEDBACK_LIST}">
                <!-- Set up a link for each student -->
                <c:url var="tempLink" value="FeedbackControllerServlet">
                    <c:param name="command" value="LOAD"/>
                    <c:param name="feedbackId" value="${tempFeedback.id}"/>
                </c:url>
                <!-- Set up a link to delete a student -->
                <c:url var="deleteLink" value="FeedbackControllerServlet">
                    <c:param name="command" value="DELETE"/>
                    <c:param name="feedbackId" value="${tempFeedback.id}"/>
                </c:url>
                <tr>
                    <td>${tempFeedback.name}</td>
                    <td>${tempFeedback.phone}</td>
                    <td>${tempFeedback.email}</td>
                    <td>${tempFeedback.message}</td>
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
