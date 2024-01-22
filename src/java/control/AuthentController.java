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
import java.util.Random;

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

        String url = "";
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
                url = "views/common/login.jsp";
                break;
            case "register":
                url = "views/common/signup.jsp";
                break;
            default:
                url = "views/common/login.jsp";
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
            case "register":
                CreateAccount(request, response);
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
            request.getRequestDispatcher("views/common/login.jsp").forward(request, response);
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
            request.getRequestDispatcher("views/common/homepage.jsp").forward(request, response);
        }
    }

    private void CreateAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        commonDAO = new CommonDao();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String password2 = request.getParameter("password2");

         if (!email.matches(Constant.EMAIL_REGEX)) {
            request.setAttribute("error", "Email không hợp lệ");
            request.getRequestDispatcher("views/common/signup.jsp").forward(request, response);
        }else if (!password.matches(Constant.PASSWORD_REGEX)){
            request.setAttribute("error", "Mật khẩu phải chứa ít nhất 8 kí tự (1 số, 1 chữ in hoa, 1 kí tự đặc biệt(trừ khoảng trắng)");
            request.getRequestDispatcher("views/common/signup.jsp").forward(request, response);    
        }else{
            // Kiểm tra xem mật khẩu và mật khẩu nhập lại có giống nhau không
            if (!password.equals(password2)) {
                request.setAttribute("error", "Mật khẩu phải giống nhau");
                request.getRequestDispatcher("views/common/signup.jsp").forward(request, response);
                return; // Kết thúc phương thức để không thực hiện các bước tiếp theo nếu mật khẩu không khớp
            }
        }

        Account account = Account.builder()
                .email(email)
                .password(password)
                .member_code(email)
                .build();

        // Kiểm tra xem mật khẩu và mật khẩu nhập lại có giống nhau không
        if (!password.equals(password2)) {
            request.setAttribute("error", "Mật khẩu phải giống nhau");
            request.getRequestDispatcher("views/common/signup.jsp").forward(request, response);
            return; // Kết thúc phương thức để không thực hiện các bước tiếp theo nếu mật khẩu không khớp
        }

// Tạo mã thành viên
        String memberCode = generateMemberCode();
        account.setMember_code(memberCode);

// Kiểm tra xem tài khoản chưa tồn tại thì thêm vào cơ sở dữ liệu
        boolean isExist = commonDAO.CheckAccount(account);

        if (!isExist) {
            // Nếu tài khoản chưa tồn tại thì thêm vào cơ sở dữ liệu
            boolean isInserted = commonDAO.CreateAccount(account);
            if (isInserted) {
                // Chuyển hướng đến trang login nếu insert thành công
                request.getRequestDispatcher("views/common/login.jsp").forward(request, response);
            } else {
                // Xử lý lỗi nếu insert không thành công
                request.setAttribute("error", "Lỗi khi thêm tài khoản");
                request.getRequestDispatcher("views/common/signup.jsp").forward(request, response);
            }
        } else {
            // Nếu tài khoản đã tồn tại thì thông báo lỗi
            request.setAttribute("error", "Tài khoản đã tồn tại, vui lòng chọn tài khoản khác!!");
            request.getRequestDispatcher("views/common/signup.jsp").forward(request, response);
        }
    }

    private String generateMemberCode() {
        Random random = new Random();
        int code = random.nextInt(999999);
        return String.format("%06d", code);
    }

}
