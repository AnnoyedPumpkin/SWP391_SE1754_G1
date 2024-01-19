/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package control;

import constant.Constant;
import dao.CommonDao;
import entity.Account;
import entity.Account_Detail;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
@WebServlet(name = "ChangePassController", urlPatterns = {"/changepass"})
public class ChangePassController extends HttpServlet {

    private CommonDao commonDAO;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("profile.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        switch (action) {
            case "profile":
                profile(request, response);
                break;
            case "changePass":
                changePass(request, response);
                break;
            default:
                throw new AssertionError();
        }
    }

    private void changePass(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        commonDAO = new CommonDao();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String newPassword = request.getParameter("newPassword");
        String newPassword2 = request.getParameter("newPassword2");

        if (!newPassword.equals(newPassword2) || newPassword.isEmpty()) {
            request.setAttribute("error1", "Mật khẩu mới không khớp hoặc không hợp lệ");
            request.getRequestDispatcher("views/common/profile.jsp").forward(request, response);
            return;
        }

        Account account = new Account();
        account.setEmail(email);
        account.setPassword(password);

        HttpSession session = request.getSession();
        Account accountSession = (Account) session.getAttribute(Constant.SESSION_ACCOUNT);

        if (password.equals(accountSession.getPassword())) {
            commonDAO.updatePassword(account);
            accountSession.setPassword(newPassword);
            session.setAttribute(Constant.SESSION_ACCOUNT, accountSession);
        } else {
            request.setAttribute("error1", "Mật khẩu không đúng");
        }

        // Redirect or forward to the appropriate page
        response.sendRedirect("profile.jsp");
    }

    private void profile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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

