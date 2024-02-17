/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package control.admin;

import constant.Constant;
import dao.AdminDao;
import dao.SellerDao;
import entity.Account;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Random;

/**
 *
 * @author LENOVO
 */
public class AuthentAdminController extends HttpServlet {

    AdminDao adminDAO = new AdminDao();
    SellerDao sellerDAO = new SellerDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String action = request.getParameter("action") == null ? "" : request.getParameter("action");
        String url = "";
        switch (action) {
            case "login":
                url = "../views/admin/login.jsp";
                break;
            case "register":
                url = "../views/admin/signup.jsp";
                break;
            case "logout":
                logout(request, response);
                url = "../views/admin/login.jsp";
                break;
            default:
                url = "../views/admin/login.jsp";
                break;
        }
        request.getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String action = request.getParameter("action") == null ? "" : request.getParameter("action");
        String url = "";
        switch (action) {
            case "login":
                login(request, response);
                break;
            case "register":
                sellerRegister(request, response);
                break;
            default:
                throw new AssertionError();
        }
    }

    /**
     * Method description: Admin and seller login
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        adminDAO = new AdminDao();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Account account = Account.builder()
                .email(username)
                .password(password)
                .build();
        account = adminDAO.checkExistOfAdmin(account);

        if (account == null) {
            request.setAttribute("err", "Nhap sai ten dang nhap hoac mat khau");
            request.getRequestDispatcher("../views/admin/login.jsp").forward(request, response);
        } else {
            if (account.getRole_Id() == 2) {
                HttpSession session = request.getSession();
                session.setAttribute(Constant.SESSION_ACCOUNT, account);
                response.sendRedirect("../admin/dashboard");
            }
            if (account.getRole_Id() == 3) {
                HttpSession session = request.getSession();
                session.setAttribute(Constant.SESSION_ACCOUNT, account);
                response.sendRedirect("../views/admin/sellerDashboard.jsp");
            } else {
                request.setAttribute("err", "You don't have permission!");
                request.getRequestDispatcher("../views/admin/login.jsp").forward(request, response);
            }
        }
    }

    /**
     * Method description: Create account for seller
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void sellerRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        sellerDAO = new SellerDao();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String password2 = request.getParameter("password2");

        if (!email.matches(Constant.EMAIL_REGEX)) {
            request.setAttribute("error", "Email không hợp lệ");
            request.getRequestDispatcher("../views/admin/signup.jsp").forward(request, response);
        } else if (!password.matches(Constant.PASSWORD_REGEX)) {
            request.setAttribute("error", "Mật khẩu phải chứa ít nhất 8 kí tự (1 số, 1 chữ in hoa, 1 kí tự đặc biệt(trừ khoảng trắng)");
            request.getRequestDispatcher("../views/admin/signup.jsp").forward(request, response);
        } else {
            // Kiểm tra xem mật khẩu và mật khẩu nhập lại có giống nhau không
            if (!password.equals(password2)) {
                request.setAttribute("error", "Mật khẩu phải giống nhau");
                request.getRequestDispatcher("../views/admin/signup.jsp").forward(request, response);
                return; // Kết thúc phương thức để không thực hiện các bước tiếp theo nếu mật khẩu không khớp
            }

            Account account = Account.builder()
                    .email(email)
                    .password(password)
                    .build();

            // Tạo mã thành viên
            String memberCode = generateMemberCode();
            account.setMember_code(memberCode);

            boolean isExist = sellerDAO.CheckSellerAccount(account);

            if (!isExist) {
                // Nếu tài khoản chưa tồn tại thì thêm vào cơ sở dữ liệu
                boolean isInserted = sellerDAO.createAccountSeller(account);
                if (isInserted) {
                    // Chuyển hướng đến trang login nếu insert thành công
                    request.getRequestDispatcher("../views/admin/login.jsp").forward(request, response);
                } else {
                    // Xử lý lỗi nếu insert không thành công
                    request.setAttribute("error", "Lỗi khi thêm tài khoản");
                    request.getRequestDispatcher("../views/admin/signup.jsp").forward(request, response);
                }
            } else {
                // Nếu tài khoản đã tồn tại thì thông báo lỗi
                request.setAttribute("error", "Tài khoản đã tồn tại, vui lòng chọn tài khoản khác!!");
                request.getRequestDispatcher("../views/admin/signup.jsp").forward(request, response);
            }
        }

    }

    /**
     * Method description: Seller or admin log out system
     *
     * @param request
     * @param response
     */
    private void logout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.removeAttribute(Constant.SESSION_ACCOUNT);
    }

    private String generateMemberCode() {
        Random random = new Random();
        int code = random.nextInt(999999);
        return String.format("%06d", code);
    }
}
