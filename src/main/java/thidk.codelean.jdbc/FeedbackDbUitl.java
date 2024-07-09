package thidk.codelean.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class FeedbackDbUitl {
    private DataSource dataSource;

    public FeedbackDbUitl(DataSource theDataSource) {
        dataSource = theDataSource;
    }

    public List<Feedback> getFeedback() throws Exception {

        List<Feedback> feedbacks = new ArrayList<>();

        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;

        try {
            // get a connection
            String url = "jdbc:mysql://localhost:3306/project_restaurant";
            String username = "root";
            String password = "";
            Class.forName("com.mysql.jdbc.Driver");

            myConn = DriverManager.getConnection(url,username,password);
//			myConn = dataSource.getConnection();

            // create sql statement
            String sql = "select * from feedback order by Name";

            myStmt = myConn.createStatement();

            // execute query
            myRs = myStmt.executeQuery(sql);

            // process result set
            while (myRs.next()) {

                // retrieve data from result set row
                int id = myRs.getInt("id");
                String Name = myRs.getString("name");
                String Email = myRs.getString("email");
                String Phone = myRs.getString("phone");
                String Message = myRs.getString("message");
                // create new student object
                Feedback tempFeedback = new Feedback(id, Name, Email, Phone, Message);

                // add it to the list of students
                feedbacks.add(tempFeedback);
            }

            return feedbacks;
        }
        finally {
            // close JDBC objects
            close(myConn, myStmt, myRs);
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
                myConn.close();   // doesn't really close it ... just puts back in connection pool
            }
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    public void addFeedback(Feedback theFeedback) throws Exception {

        Connection myConn = null;
        PreparedStatement myStmt = null;

        try {
            // get db connection
//			myConn = dataSource.getConnection();
            // get a connection
            String url = "jdbc:mysql://localhost:3306/project_restaurant";
            String username = "root";
            String password = "";
            Class.forName("com.mysql.jdbc.Driver");

            myConn = DriverManager.getConnection(url,username,password);

            // create sql for insert
            String sql = "insert into feedback "
                    + "(Name, Email, Phone, Message) "
                    + "values (?, ?, ?, ?)";

            myStmt = myConn.prepareStatement(sql);

            // set the param values for the student
            myStmt.setString(1, theFeedback.getName());
            myStmt.setString(2, theFeedback.getEmail());
            myStmt.setString(3, theFeedback.getPhone());
            myStmt.setString(4, theFeedback.getMessage());

            // execute sql insert
            myStmt.execute();
        }
        finally {
            // clean up JDBC objects
            close(myConn, myStmt, null);
        }
    }

    public Feedback getFeedback(String theFeedbackId) throws Exception {

        Feedback theFeedback = null;

        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;
        int feedbackId;

        try {
            // convert student id to int
            feedbackId = Integer.parseInt(theFeedbackId);

            // get connection to database
//			myConn = dataSource.getConnection();
            // get a connection
            String url = "jdbc:mysql://localhost:3306/project_restaurant";
            String username = "root";
            String password = "";
            Class.forName("com.mysql.jdbc.Driver");

            myConn = DriverManager.getConnection(url,username,password);

            // create sql to get selected student
            String sql = "select * from feedback where id=?";

            // create prepared statement
            myStmt = myConn.prepareStatement(sql);

            // set params
            myStmt.setInt(1, feedbackId);

            // execute statement
            myRs = myStmt.executeQuery();

            // retrieve data from result set row
            if (myRs.next()) {
                String Name = myRs.getString("Name");
                String Email = myRs.getString("Email");
                String Phone = myRs.getString("Phone");
                String Message = myRs.getString("Message");

                // use the studentId during construction
                theFeedback = new Feedback(feedbackId, Name, Email, Phone, Message);
            }
            else {
                throw new Exception("Could not find feedback id: " + feedbackId);
            }

            return theFeedback;
        }
        finally {
            // clean up JDBC objects
            close(myConn, myStmt, myRs);
        }
    }

    public void updateFeedback(Feedback theFeedback) throws Exception {

        Connection myConn = null;
        PreparedStatement myStmt = null;

        try {
            // get db connection
//			myConn = dataSource.getConnection();
            // get a connection
            String url = "jdbc:mysql://localhost:3306/project_restaurant";
            String username = "root";
            String password = "";
            Class.forName("com.mysql.jdbc.Driver");

            myConn = DriverManager.getConnection(url,username,password);

            // create SQL update statement
            String sql = "update feedback "
                    + "set Name=?, Email=?, Phone=?, Message=? "
                    + "where id=?";

            // prepare statement
            myStmt = myConn.prepareStatement(sql);

            // set params
            myStmt.setString(1, theFeedback.getName());
            myStmt.setString(2, theFeedback.getEmail());
            myStmt.setString(3, theFeedback.getPhone());
            myStmt.setString(4, theFeedback.getMessage());
            myStmt.setInt(5, theFeedback.getId());

            // execute SQL statement
            myStmt.execute();
        }
        finally {
            // clean up JDBC objects
            close(myConn, myStmt, null);
        }
    }

    public void deleteFeedback(String theFeedbackId) throws Exception {

        Connection myConn = null;
        PreparedStatement myStmt = null;

        try {
            // convert student id to int
            int feedbackId = Integer.parseInt(theFeedbackId);

            // get connection to database
//			myConn = dataSource.getConnection();
            // get a connection
            String url = "jdbc:mysql://localhost:3306/project_restaurant";
            String username = "root";
            String password = "";
            Class.forName("com.mysql.jdbc.Driver");

            myConn = DriverManager.getConnection(url,username,password);

            // create sql to delete student
            String sql = "delete from feedback where id=?";

            // prepare statement
            myStmt = myConn.prepareStatement(sql);

            // set params
            myStmt.setInt(1, feedbackId);

            // execute sql statement
            myStmt.execute();
        }
        finally {
            // clean up JDBC code
            close(myConn, myStmt, null);
        }
    }
}
