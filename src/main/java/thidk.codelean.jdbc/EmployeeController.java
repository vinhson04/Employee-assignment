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

@WebServlet("/EmployeeController")
public class EmployeeController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private EmployeeDbUtil employeeDbUtil;

    @Resource(name = "jdbc/hr_manage") // adjust the name as per your context.xml or server configuration
    private DataSource dataSource;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            employeeDbUtil = new EmployeeDbUtil(dataSource);
        } catch (Exception exc) {
            throw new ServletException(exc);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String theCommand = request.getParameter("command");
            if (theCommand == null) {
                theCommand = "list";
            }
            switch (theCommand) {
                case "LOAD":
                    loadEmployee(request, response);
                    break;
                case "UPDATE":
                    updateEmployee(request, response);
                    break;
                case "DELETE":
                    deleteEmployee(request, response);
                    break;
                case "ADD":
                    addEmployee(request, response);
                    break;
                default:
                    listEmployee(request, response);
            }
        } catch (Exception exc) {
            throw new ServletException(exc);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response); // Redirect POST requests to doGet
    }

    private void listEmployee(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Employee> employee = employeeDbUtil.getEmployee();
        request.setAttribute("EMPLOYEE_LIST", employee);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/list-employees.jsp");
        dispatcher.forward(request, response);
    }

    private void loadEmployee(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // read student id from form data
        String theEmployeeId = request.getParameter("employeeId");

        // get student from database (db util)
        Employee theEmployee = employeeDbUtil.getEmployee(theEmployeeId);

        // place student in the request attribute
        request.setAttribute("THE_EMPLOYEE", theEmployee);

        // send to jsp page:
        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/update-employee-form.jsp");
        dispatcher.forward(request, response);
    }

    private void updateEmployee(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int employee_id = Integer.parseInt(request.getParameter("employee_id"));
        String employee_name = request.getParameter("employee_name");
        String birthday = request.getParameter("birthday");
        String phone = request.getParameter("phone_number");
        String email = request.getParameter("email");



        Employee employee = new Employee(employee_id, employee_name, birthday, phone, email);
        employeeDbUtil.updateEmployee(employee);

        listEmployee(request, response);
    }

    private void deleteEmployee(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String theEmployeeId = request.getParameter("employee_id");

        employeeDbUtil.deleteEmployee(theEmployeeId);
        listEmployee(request, response);
    }

    private void addEmployee(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String employee_name = request.getParameter("employee_name");
        String birthday = request.getParameter("birthday");
        String phone = request.getParameter("phone_number");
        String email = request.getParameter("email");

        Employee employee = new Employee(employee_name, birthday, phone, email);
        employeeDbUtil.addEmployee(employee);

        listEmployee(request, response);
    }
}
