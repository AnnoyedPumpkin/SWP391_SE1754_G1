/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package control;

import constant.Constant;
import dao.CommonDao;
import entity.Account;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author LENOVO
 */

@WebServlet(name = "AuthentController", urlPatterns = {"/authen"})
public class AuthentController extends HttpServlet {
    CommonDao commonDAO;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url ="";
        String action = request.getParameter("action") == null ? "login" : request.getParameter("action");
        switch (action) {
            case "login":
                Cookie arr[] = request.getCookies();
                if (arr != null) {
                    for (Cookie cookie : arr) {
                        if (cookie.getName().equals("userC")) {
                            request.setAttribute("username", cookie.getValue());
                        }
                        if (cookie.getName().equals("passC")) {
                            request.setAttribute("password", cookie.getValue());
                        }
                    }
                }
                url = "login.jsp";
                break;
            default:
                url = "login.jsp";
                break;
        }
        request.getRequestDispatcher(url).forward(request, response);
    }
  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "login" : request.getParameter("action");
        switch (action) {
            case "login":
                login(request, response);
                break;
            default:
                throw new AssertionError();
        }
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        commonDAO = new CommonDao();
        String remember = request.getParameter("remember");
        String email = request.getParameter("username");
        String password = request.getParameter("password");
        Account account = Account.builder()
                .email(email)
                .password(password)
                .build();
        account = commonDAO.CheckExistOfAcc(account);

        if (account == null) {
            request.setAttribute("err", "Nhap sai ten dang nhap hoac mat khau");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {

            HttpSession session = request.getSession();
            session.setAttribute(Constant.SESSION_ACCOUNT, account);
            Cookie userC = new Cookie("userC", email);
            Cookie passC = new Cookie("passC", password);
            if (remember != null) {
                userC.setMaxAge(60 * 60 * 24);
                passC.setMaxAge(60 * 60 * 24);
            } else {
                userC.setMaxAge(0);
                passC.setMaxAge(0);
            }
            response.addCookie(userC);
            response.addCookie(passC);
            response.sendRedirect("home");
        }
    }

}
