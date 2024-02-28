/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package control.seller;

import constant.Constant;

import dao.SellerDao;
import entity.Account;
import entity.Account_Detail;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import model.AccountDetailUM;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Win 10
 */
public class SellerProfileController extends HttpServlet {

    SellerDao sellerDao;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = "";
        sellerDao = new SellerDao();
        HttpSession session = request.getSession(false); // Sử dụng tham số false để không tạo session mới nếu không tồn tại
        String action = request.getParameter("action") == null ? "sellerProfile" : request.getParameter("action");
        if (session != null && session.getAttribute(Constant.SESSION_ACCOUNT) != null) {
            // Lấy thông tin tài khoản từ session
            Account account = (Account) session.getAttribute(Constant.SESSION_ACCOUNT);
            // Gọi phương thức trong DAO để lấy chi tiết tài khoản dựa trên accountId
            Account_Detail accountDetail = sellerDao.getAccountDetailByAccountId(account.getId());
            // Đặt thuộc tính "accountDetail" vào request để hiển thị trên giao diện
            request.setAttribute("accountDetail", accountDetail);
            request.setAttribute("username", accountDetail.getUserName());
            request.setAttribute("email", account.getEmail());
            request.setAttribute("member_code", account.getMember_code());
            if (accountDetail != null) {
                request.setAttribute("accountDetail", accountDetail);
                request.setAttribute("username", accountDetail.getUserName());
                request.setAttribute("email", account.getEmail());
                switch (action) {
                    case "sellerProfile":
                        // Chuyển hướng sang trang hiển thị thông tin cá nhân
                        url = "views/admin/sellerProfile.jsp";
                        break;
                    case "editProfile":
                        url = "views/admin/editSellerProfile.jsp";
                        break;
                }
            } else {
                System.out.println("Error cannot get account detail in profile controller");
            }

        } else {
            // Nếu không có session hoặc không có thông tin tài khoản trong session, có thể chuyển hướng người dùng đến trang đăng nhập hoặc xử lý một cách phù hợp.
            url = "views/admin/login.jsp";
        }
        request.getRequestDispatcher(url).forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "updateProfile" : request.getParameter("action");
        switch (action) {
            case "updateProfile":
                updateProfile(request, response);
                break;
            case "changePassword":
                changePassword(request, response);
                break;
            default:
                throw new AssertionError();
        }
    }

    private void updateProfile(HttpServletRequest request, HttpServletResponse response) {
        try {
            sellerDao = new SellerDao();
            // Lấy thông tin tài khoản từ session
            HttpSession session = request.getSession(false); // Sử dụng tham số false để không tạo session mới nếu không tồn tại
            if (session != null && session.getAttribute(Constant.SESSION_ACCOUNT) != null) {
                Account account = (Account) session.getAttribute(Constant.SESSION_ACCOUNT);
                Account_Detail accountDetail = sellerDao.getAccountDetailByAccountId(account.getId());

                String userName = request.getParameter("userName");
                String email = request.getParameter("email");
                String phone = request.getParameter("phone");
                // Nam la true, nu la false
                boolean gender = request.getParameter("gender") == "male" ? true : false;
                String date = request.getParameter("date");
                String address = request.getParameter("address");

                //Tao update model de day data vao cho de xu ly sau.
                AccountDetailUM accountDetailUM = new AccountDetailUM();
                accountDetailUM.setAccount_id(account.getId());
                accountDetailUM.setEmail(email);
                accountDetailUM.setAddress(address);
                accountDetailUM.setGender(gender);
                accountDetailUM.setDob(date);
                accountDetailUM.setUserName(userName);
                accountDetailUM.setPhone_number(phone);

                accountDetailUM = sellerDao.editProfile(accountDetailUM);
                System.out.println("Reuslt " + accountDetailUM.toString());
                // Gọi phương thức trong DAO để lấy chi tiết tài khoản dựa trên accountId
                // Đặt thuộc tính "accountDetail" vào request để hiển thị trên giao diện
                request.setAttribute("accountDetail", accountDetailUM);
                // Chuyển hướng sang trang hiển thị thông tin cá nhân
                request.getRequestDispatcher("views/admin/sellerProfile.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void changePassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession(false); // Sử dụng tham số false để không tạo session mới nếu không tồn tại
            if (session != null && session.getAttribute(Constant.SESSION_ACCOUNT) != null) {
                Account account = (Account) session.getAttribute(Constant.SESSION_ACCOUNT);
                // Gọi phương thức trong DAO để lấy chi tiết tài khoản dựa trên accountId
                Account_Detail accountDetail = sellerDao.getAccountDetailByAccountId(account.getId());
                // Đặt thuộc tính "accountDetail" vào request để hiển thị trên giao diện
                request.setAttribute("accountDetail", accountDetail);
                request.setAttribute("username", accountDetail.getUserName());
                request.setAttribute("email", account.getEmail());
                String password = request.getParameter("password");
                String newpassword = request.getParameter("newPassword");
                String passwordConfirm = request.getParameter("newPassword2");
                // check nguoi dung da nhap dung mat khau.
                if (account != null) {
                    //Check validate cua mat khau
                    if (!newpassword.matches(Constant.PASSWORD_REGEX)) {
                        request.setAttribute("error1", "Mật khẩu phải chứa ít nhất 8 kí tự (1 số, 1 chữ in hoa, 1 kí tự đặc biệt(trừ khoảng trắng)");
                        request.getRequestDispatcher("views/admin/sellerProfile.jsp").forward(request, response);
                    } else {
                        // Kiểm tra xem mật khẩu và mật khẩu nhập lại có giống nhau không
                        if (!newpassword.equals(passwordConfirm)) {
                            request.setAttribute("error1", "Mật khẩu phải giống nhau");
                            request.getRequestDispatcher("views/admin/sellerProfile.jsp").forward(request, response);
                            return; // Kết thúc phương thức để không thực hiện các bước tiếp theo nếu mật khẩu không khớp
                        }
                        BCrypt bcryp = new BCrypt();
                        String passwordBcryp = bcryp.hashpw(newpassword, bcryp.gensalt());
                        int result = sellerDao.changePassword(passwordBcryp, account.getId());
                        if (result > 0) {
                            request.setAttribute("message", "Thay doi mat khau thanh cong");
                            request.getRequestDispatcher("views/admin/sellerProfile.jsp").forward(request, response);
                            return;
                        }
                    }
                } else {
                    request.setAttribute("error1", "Mật khẩu khong dung");
                    request.getRequestDispatcher("views/admin/sellerProfile.jsp").forward(request, response);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
