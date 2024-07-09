package thidk.codelean.jdbc;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

@WebServlet("/FeedbackControllerServlet")
public class FeedbackControllerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private FeedbackDbUitl feedbackDbUtil;

    @Resource(name = "jdbc/project_restaurant")
    private DataSource dataSource;

    @Override
    public void init() throws ServletException {
        super.init();

        // create our student db util ... and pass in the conn pool / datasource
        try {
            feedbackDbUtil = new FeedbackDbUitl(dataSource);
        } catch (Exception exc) {
            throw new ServletException(exc);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            // read the "command" parameter
            String theCommand = request.getParameter("command");
            // if the command is missing, then default to listing students
            if(theCommand == null)
                theCommand = "list";
            // route to the appropriate method
            switch (theCommand) {
                case "ADD":
                    addFeedback(request, response);
                    break;
                case "LOAD":
                    loadFeedback(request, response);
                    break;
                case "UPDATE":
                    updateFeedback(request, response);
                    break;
                case "DELETE":
                    deleteFeedback(request, response);
                    break;
                default:
                    listFeedbacks(request, response);
            }

        } catch (Exception exc) {
            throw new ServletException(exc);
        }

    }

    private void deleteFeedback(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // read student id from form data
        String theFeedbackId = request.getParameter("feedbackId");

        // delete student from database
        feedbackDbUtil.deleteFeedback(theFeedbackId);

        // send them back to "list students" page
        listFeedbacks(request, response);
    }

    private void updateFeedback(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        // read student info from form data
        int id = Integer.parseInt(request.getParameter("feedbackId"));
        String Name = request.getParameter("name");
        String Phone = request.getParameter("phone");
        String Email = request.getParameter("email");
        String Message = request.getParameter("message");

        // create a new student object
        Feedback theFeedback = new Feedback(id, Name, Phone, Email, Message);

        // perform update on database
        feedbackDbUtil.updateFeedback(theFeedback);

        // send them back to the "list students" page
        listFeedbacks(request, response);

    }

    private void loadFeedback(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        // read student id from form data
        String theFeedbackId = request.getParameter("feedbackId");

        // get student from database (db util)
        Feedback theFeedback = feedbackDbUtil.getFeedback(theFeedbackId);

        // place student in the request attribute
        request.setAttribute("THE_FEEDBACK", theFeedback);

        // send to jsp page:
        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/update-feedback-form.jsp");
        dispatcher.forward(request, response);
    }

    private void addFeedback(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // read student info from form data
        String Name = request.getParameter("name");
        String Phone = request.getParameter("phone");
        String Email = request.getParameter("email");
        String Message = request.getParameter("message");

        // create a new student object
        Feedback theFeedback = new Feedback(Name, Phone, Email, Message);

        // add the student to the database
        feedbackDbUtil.addFeedback(theFeedback);

        // send back to main page (the student list)
        listFeedbacks(request, response);
    }

    private void listFeedbacks(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        // get students from db util
        List<Feedback> feedbacks = feedbackDbUtil.getFeedback();

        // add students to the request
        request.setAttribute("FEEDBACK_LIST", feedbacks);

        // send to JSP page (view)
        RequestDispatcher dispatcher = request.getRequestDispatcher("/list-feedbacks.jsp");
        dispatcher.forward(request, response);
    }
}
