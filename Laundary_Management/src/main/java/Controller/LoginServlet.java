package Controller;

import DAO.StudentDao;
import model.Student;
import java.io.IOException;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 🔹 Get login form parameters
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // 🔹 Simple validation (in case of empty fields)
        if (email == null || email.trim().isEmpty() ||
            password == null || password.trim().isEmpty()) {
            request.setAttribute("error", "Please enter both email and password.");
            request.getRequestDispatcher("StudentLogin.jsp").forward(request, response);
            return;
        }

        try {
            // 🔹 Check credentials from DB
            Student student = StudentDao.getStudentByEmailPassword(email.trim(), password.trim());

            if (student != null) {
                // ✅ Login successful
                HttpSession session = request.getSession();
                session.setAttribute("student", student);
                session.setMaxInactiveInterval(30 * 60); // 30 min session timeout

                // Redirect to dashboard
                response.sendRedirect("studentDashboard.jsp");

            } else {
                // ❌ Invalid login
                request.setAttribute("error", "Invalid email or password!");
                request.getRequestDispatcher("StudentLogin.jsp").forward(request, response);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("Database error during login", e);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Unexpected error", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Redirect GET requests to login page
        response.sendRedirect("StudentLogin.jsp");
    }
}
