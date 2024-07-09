<!DOCTYPE html>
<html>

<head>
    <title>Add Employee</title>

    <link type="text/css" rel="stylesheet" href="css/style.css">
    <link type="text/css" rel="stylesheet" href="css/add-student-style.css">
</head>

<body>
<div id="wrapper">
    <div id="header">
        <h2>ABC Company</h2>
    </div>
</div>

<div id="container">
    <h3>Add Employee</h3>

    <form action="EmployeeController" method="GET">

        <input type="hidden" name="command" value="ADD" />

        <table>
            <tbody>
            <tr>
                <td><label>Employee Name:</label></td>
                <td><input type="text" name="employee_name" /></td>
            </tr>

            <tr>
                <td><label>Birth Day:</label></td>
                <td><input type="date" name="birthday" /></td>
            </tr>

            <tr>
                <td><label>Phone:</label></td>
                <td><input type="text" name="phone" /></td>
            </tr>

            <tr>
                <td><label>Email:</label></td>
                <td><input type="text" name="email" /></td>
            </tr>

            <tr>
                <td><label></label></td>
                <td><input type="submit" value="Save" class="save" /></td>
            </tr>

            </tbody>
        </table>
    </form>

    <div style="clear: both;"></div>

    <p>
        <a href="EmployeeController">Back to List</a>
    </p>
</div>
</body>

</html>











