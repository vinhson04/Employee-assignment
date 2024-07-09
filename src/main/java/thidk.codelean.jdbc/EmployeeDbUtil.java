package thidk.codelean.jdbc;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDbUtil {
    private DataSource dataSource;

    public EmployeeDbUtil(DataSource theDataSource) {
        dataSource = theDataSource;
    }

    public List<Employee> getEmployee() throws Exception {
        List<Employee> employee = new ArrayList<>();
        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;

        try {
            // Establish connection
            String url = "jdbc:mysql://localhost:3306/hr_manage";
            String username = "root";
            String password = "";
            Class.forName("com.mysql.jdbc.Driver");
            myConn = DriverManager.getConnection(url, username, password);

            // SQL query
            String sql = "SELECT * FROM employee ORDER BY employee_name";
            myStmt = myConn.createStatement();

            // Execute query
            myRs = myStmt.executeQuery(sql);

            // Process result set
            while (myRs.next()) {

                int employee_id = myRs.getInt("employee_id");
                String employee_name = myRs.getString("employee_name");
                String birthday = myRs.getString("birthday");
                String phone = myRs.getString("phone_number");
                String email = myRs.getString("email");

                Employee tempEmployee = new Employee( employee_id, employee_name , birthday, email, phone);
                employee.add(tempEmployee);
            }
        } finally {
            // Close JDBC objects in finally block
            close(myConn, myStmt, myRs);
        }

        return employee;
    }

    public void addEmployee(Employee theEmployee) throws Exception {
        Connection myConn = null;
        PreparedStatement myStmt = null;

        try {
            // Establish connection
            String url = "jdbc:mysql://localhost:3306/hr_manage";
            String username = "root";
            String password = "";
            Class.forName("com.mysql.jdbc.Driver");
            myConn = DriverManager.getConnection(url, username, password);

            // SQL insert statement
            String sql = "INSERT INTO employee (employee_name, birthday, phone, email ) "
                    + "VALUES (?, ?, ?, ?)";

            myStmt = myConn.prepareStatement(sql);

            // Set parameters
            myStmt.setString(1, theEmployee.getEmployee_name());
            myStmt.setString(2, theEmployee.getBirthday());
            myStmt.setString(3, theEmployee.getEmail());
            myStmt.setString(4, theEmployee.getPhone());

            // Execute SQL statement
            myStmt.execute();
        } finally {
            // Close JDBC objects in finally block
            close(myConn, myStmt, null);
        }
    }

    public Employee getEmployee(String theEmployeeId) throws Exception {
        Employee theEmployee = null;
        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;
        int employeeId;

        try {

            employeeId = Integer.parseInt(theEmployeeId);
            // Establish connection
            String url = "jdbc:mysql://localhost:3306/hr_manage";
            String username = "root";
            String password = "";
            Class.forName("com.mysql.jdbc.Driver");
            myConn = DriverManager.getConnection(url, username, password);

            // SQL query to retrieve user by ID
            String sql = "SELECT * FROM employee WHERE employee_id=?";
            myStmt = myConn.prepareStatement(sql);

            // Set parameter
            myStmt.setInt(1, employeeId);

            // Execute query
            myRs = myStmt.executeQuery();

            // Process result set
            if (myRs.next()) {
                int employee_id = myRs.getInt("employee_id");
                String employee_name = myRs.getString("employee_name");
                String birthday = myRs.getString("birthday");
                String phone = myRs.getString("phone_number");
                String email = myRs.getString("email");

                // Create User object
                theEmployee = new Employee( employee_id ,employee_name, birthday, email, phone);
            } else {
                throw new Exception("Employee ID not found: " + employeeId);
            }
        } finally {
            // Close JDBC objects in finally block
            close(myConn, myStmt, myRs);
        }

        return theEmployee;
    }

    public void updateEmployee(Employee theEmployee) throws Exception {
        Connection myConn = null;
        PreparedStatement myStmt = null;

        try {
            // Establish connection
            String url = "jdbc:mysql://localhost:3306/hr_manage";
            String username = "root";
            String password = "";
            Class.forName("com.mysql.jdbc.Driver");
            myConn = DriverManager.getConnection(url, username, password);

            // SQL update statement
            String sql = "UPDATE employee "
                    + "SET employee_name=?, birthday=?, email=?, phone=? "
                    + "WHERE employee_id=?";

            myStmt = myConn.prepareStatement(sql);

            // Set parameters
            myStmt.setString(1, theEmployee.getEmployee_name());
            myStmt.setString(2, theEmployee.getBirthday());
            myStmt.setString(3, theEmployee.getEmail());
            myStmt.setString(4, theEmployee.getPhone());
            myStmt.setInt(5, theEmployee.getEmployee_id());

            // Execute SQL statement
            myStmt.execute();
        } finally {
            // Close JDBC objects in finally block
            close(myConn, myStmt, null);
        }
    }

    public void deleteEmployee(String theEmployeeId) throws Exception {
        Connection myConn = null;
        PreparedStatement myStmt = null;


        try {

            int employeeId = Integer.parseInt(theEmployeeId);
            // Establish connection
            String url = "jdbc:mysql://localhost:3306/hr_manage";
            String username = "root";
            String password = "";
            Class.forName("com.mysql.jdbc.Driver");
            myConn = DriverManager.getConnection(url, username, password);

            // SQL delete statement
            String sql = "DELETE FROM employee WHERE employee_id=?";

            myStmt = myConn.prepareStatement(sql);

            // Set parameter
            myStmt.setInt(1, employeeId);

            // Execute SQL statement
            myStmt.execute();
        } finally {
            // Close JDBC objects in finally block
            close(myConn, myStmt, null);
        }
    }

    private void close(Connection myConn, Statement myStmt, ResultSet myRs) {
        try {
            if (myRs != null) {
                myRs.close();
            }

            if (myStmt != null) {
                myStmt.close();
            }

            if (myConn != null) {
                myConn.close(); // doesn't really close it ... just puts back in connection pool
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}
