package control;

import dao.CommonDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "ChangePassword", urlPatterns = {"/change_password"})
public class ChangePassword extends HttpServlet {

    CommonDao commonDAO = new CommonDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("change_password.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String contactInfo = request.getParameter("contactInfo");
        String newPassword = request.getParameter("newPassword");

        boolean isEmail = contactInfo.contains("@");
        boolean accountExists = isEmail ? commonDAO.checkAccountExistByEmail(contactInfo) : commonDAO.checkAccountExistByPhoneNumber(contactInfo);
        int accountID = isEmail ? commonDAO.getAccountIdByEmail(contactInfo) : commonDAO.getAccountIdByPhoneNumber(contactInfo);

        if (accountExists && (accountID != -1)) {
            commonDAO.changePassword(newPassword, accountID);
            response.getWriter().println("Password updated successfully!");
            response.sendRedirect("login.jsp");
        } else {
            request.setAttribute("errorMessage", "Incorrect username or contact information.");
            request.getRequestDispatcher("change_password+.jsp").forward(request, response);
        }
    }

}
