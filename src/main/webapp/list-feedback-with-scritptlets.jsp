<%@ page import="java.util.*,thidk.codelean.jdbc.*" %>
<!DOCTYPE html>
<html>
<head>
    <title>Student Tracker App</title>
    <link type="text/css" rel="stylesheet" href="css/style.css">
</head>
<%
    // get the students from the request object (sent by servlet)
    List<Feedback> theFeedbacks =
            (List<Feedback>) request.getAttribute("FEEDBACK_LIST");
%>
<body>
<div id="wrapper">
    <div id="header">
        <h2>CodeLean Academy</h2>
    </div>
</div>
<div id="container">
    <div id="content">
        <table>
            <tr>
                <th>Name</th>
                <th>Email</th>
                <th>Phone</th>
                <th>Message</th>
            </tr>
            <% for (Feedback tempFeedback : theFeedbacks) { %>
            <tr>
                <td> <%= tempFeedback.getName() %> </td>
                <td> <%= tempFeedback.getEmail() %> </td>
                <td> <%= tempFeedback.getPhone() %> </td>
                <td> <%= tempFeedback.getMessage() %> </td>
            </tr>
            <% } %>
        </table>
    </div>
</div>
</body>
</html>








