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
        String action = request.getParameter("action") == null ? "login" : request.getParameter("action");
        switch (action) {
            case "login":
                request.getRequestDispatcher("login.jsp").forward(request, response);
                break;
            case "register":
                request.getRequestDispatcher("signup.jsp").forward(request, response);
                break;
            default:
                throw new AssertionError();
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "login" : request.getParameter("action");
        switch (action) {
            case "login":
                login(request, response);
                break;
            case "register":
                register(request, response);
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

   private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    commonDAO = new CommonDao();
    String email = request.getParameter("email");
    String password = request.getParameter("password");
    String password2 = request.getParameter("password2");

    // Kiểm tra xem mật khẩu và mật khẩu nhập lại có giống nhau không
    if (!password.equals(password2)) {
        request.setAttribute("error", "Mật khẩu phải giống nhau");
        request.getRequestDispatcher("signup.jsp").forward(request, response);
        return; // Kết thúc phương thức để không thực hiện các bước tiếp theo nếu mật khẩu không khớp
    }

    Account account = Account.builder()
            .email(email)
            .password(password)
            .build();

    boolean isExist = commonDAO.CheckAccount(account);

    if (!isExist) {
        // Nếu tài khoản chưa tồn tại thì thêm vào cơ sở dữ liệu
        boolean isInserted = commonDAO.createaccount(account);
        if (isInserted) {
            // Chuyển hướng đến trang home nếu insert thành công
            response.sendRedirect("home");
        } else {
            // Xử lý lỗi nếu insert không thành công
            request.setAttribute("error", "Lỗi khi thêm tài khoản");
            request.getRequestDispatcher("signup.jsp").forward(request, response);
        }
    } else {
        // Nếu tài khoản đã tồn tại thì thông báo lỗi
        request.setAttribute("error", "Tài khoản đã tồn tại, vui lòng chọn tài khoản khác!!");
        request.getRequestDispatcher("signup.jsp").forward(request, response);
    }
}

}
