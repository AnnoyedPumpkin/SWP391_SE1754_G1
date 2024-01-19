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
//@WebServlet(name = "ChangePasswordController", urlPatterns = {"/change-password"})
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
//        // Xác thực thành công và lấy thông tin tài khoản từ cơ sở dữ liệu hoặc session
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
//        String newPassword = request.getParameter("newPassword");
//
//        if (newPassword == null || newPassword.isEmpty()) {
//            // Xử lý khi mật khẩu mới không hợp lệ
//            request.setAttribute("error", "Mật khẩu mới không được để trống");
//            request.getRequestDispatcher("changepassword.jsp").forward(request, response);
//            return;
//        }
//
//        loggedInAccount.changePassword(newPassword);
//
//        // Lưu thông tin tài khoản đã thay đổi vào cơ sở dữ liệu hoặc session
//        // Đây chỉ là ví dụ, bạn cần điều chỉnh dựa trên cách triển khai của bạn
//        // Nếu sử dụng cơ sở dữ liệu, bạn có thể gọi hàm update trong DAO
//        // Nếu sử dụng session, bạn có thể setAttribute lại cho session
//        commonDAO.updatePassword(loggedInAccount);
//
//        // Chuyển hướng đến trang thông báo thành công hoặc trang profile
//        response.sendRedirect("profile.jsp");
//    }
//}
//
//
////    private void profile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
////
////        commonDAO = new CommonDao();
////        String email = request.getParameter("email");
////
////        // Assuming you have a method in DAO to get Account_Detail by email
////        Account_Detail accountDetail = commonDAO.getAccountDetailByEmail(email);
////
////        if (accountDetail != null) {
////            request.setAttribute("accountDetail", accountDetail);
////            request.getRequestDispatcher("views/common/profile.jsp").forward(request, response);
////        } else {
////            // Handle case when Account_Detail is not found
////            response.sendRedirect("error.jsp");
////        }
////    }
//}
