//package control;
//
//import constant.Constant;
//import dao.CommonDao;
//import entity.Account;
//
//import java.io.IOException;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//
//@WebServlet(name = "ChangePasswordController", urlPatterns = {"/changepass"})
//public class ChangePasswordController extends HttpServlet {
//
//    private CommonDao commonDAO;
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//
//        commonDAO = new CommonDao();
//
//        // Xác thực thành công và lấy thông tin tài khoản từ session
//        HttpSession session = request.getSession();
//        Account loggedInAccount = (Account) session.getAttribute(Constant.SESSION_ACCOUNT);
//
//        if (loggedInAccount == null) {
//            // Người dùng chưa đăng nhập, chuyển hướng đến trang đăng nhập
//            response.sendRedirect("login.jsp");
//            return;
//        }
//
//        // Thay đổi mật khẩu
//        String password = request.getParameter("password");
//        String newPassword = request.getParameter("newPassword");
//        String newPassword2 = request.getParameter("newPassword2");
//
//        if (!newPassword.equals(newPassword2) || newPassword.isEmpty()) {
//            // Xử lý khi mật khẩu mới không khớp hoặc không hợp lệ
//            request.setAttribute("error1", "Mật khẩu mới không khớp hoặc không hợp lệ");
//            request.getRequestDispatcher("profile.jsp").forward(request, response);
//            return;
//        }
//
//        // Kiểm tra mật khẩu cũ
//        if (!password.equals(loggedInAccount.getPassword())) {
//            // Xử lý khi mật khẩu cũ không đúng
//            request.setAttribute("error1", "Mật khẩu cũ không đúng");
//            request.getRequestDispatcher("profile.jsp").forward(request, response);
//            return;
//        }
//
//        // Thực hiện thay đổi mật khẩu
//        loggedInAccount.changePassword(newPassword);
//        commonDAO.updatePassword(loggedInAccount);
//
//        // Cập nhật thông tin tài khoản trong session
//        session.setAttribute(Constant.SESSION_ACCOUNT, loggedInAccount);
//
//        // Chuyển hướng đến trang profile với thông báo thành công
//        response.sendRedirect("profile.jsp");
//    }
//}
