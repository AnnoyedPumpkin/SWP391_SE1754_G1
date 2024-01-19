package control;

import dao.CommonDao;
import entity.Account_Detail;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "ProfileController", urlPatterns = {"/profile"})
public class ProfileController extends HttpServlet {

    private CommonDao commonDAO;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        commonDAO = new CommonDao();
        String email = request.getParameter("email");

        // Assuming you have a method in DAO to get Account_Detail by email
        Account_Detail accountDetail = commonDAO.getAccountDetailByEmail(email);

        if (accountDetail != null) {
            request.setAttribute("accountDetail", accountDetail);
            request.getRequestDispatcher("views/common/profile.jsp").forward(request, response);
        } else {
            // Handle case when Account_Detail is not found
            response.sendRedirect("error.jsp");
        }
    }
}
