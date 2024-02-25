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
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.AccountDetailUM;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Admin
 */
public class ProfileController extends HttpServlet {

    CommonDao commonDAO;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = "";
        commonDAO = new CommonDao();
        HttpSession session = request.getSession(false); // Sử dụng tham số false để không tạo session mới nếu không tồn tại
        String action = request.getParameter("action") == null ? "profile" : request.getParameter("action");
        if (session != null && session.getAttribute(Constant.SESSION_ACCOUNT) != null) {
            // Lấy thông tin tài khoản từ session
            Account account = (Account) session.getAttribute(Constant.SESSION_ACCOUNT);
            // Gọi phương thức trong DAO để lấy chi tiết tài khoản dựa trên accountId
            Account_Detail accountDetail = commonDAO.getAccountDetailByAccountId(account.getId());
            // Đặt thuộc tính "accountDetail" vào request để hiển thị trên giao diện
            if (accountDetail != null) {
                request.setAttribute("accountDetail", accountDetail);
                request.setAttribute("username", accountDetail.getUserName());
                request.setAttribute("email", account.getEmail());
                switch (action) {
                    case "profile":
                        // Chuyển hướng sang trang hiển thị thông tin cá nhân
                        url = "views/common/profile.jsp";
                        break;
                    case "editProfile":
                        url = "views/common/editprofile.jsp";
                        break;
                }

            } else {
                System.out.println("Error cannot get account detail in profile controller");
            }
        } else {
            // Nếu không có session hoặc không có thông tin tài khoản trong session, có thể chuyển hướng người dùng đến trang đăng nhập hoặc xử lý một cách phù hợp.
            url = "views/common/login.jsp";
        }
        request.getRequestDispatcher(url).forward(request, response);
    }

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
            commonDAO = new CommonDao();
            // Lấy thông tin tài khoản từ session
            HttpSession session = request.getSession(false); // Sử dụng tham số false để không tạo session mới nếu không tồn tại
            if (session != null && session.getAttribute(Constant.SESSION_ACCOUNT) != null) {
                Account account = (Account) session.getAttribute(Constant.SESSION_ACCOUNT);
                Account_Detail accountDetail = commonDAO.getAccountDetailByAccountId(account.getId());

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

                accountDetailUM = commonDAO.editProfile(accountDetailUM);
                System.out.println("Reuslt " + accountDetailUM.toString());
                // Gọi phương thức trong DAO để lấy chi tiết tài khoản dựa trên accountId
                // Đặt thuộc tính "accountDetail" vào request để hiển thị trên giao diện
                request.setAttribute("accountDetail", accountDetailUM);
                // Chuyển hướng sang trang hiển thị thông tin cá nhân
                request.getRequestDispatcher("views/common/profile.jsp").forward(request, response);
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
                Account_Detail accountDetail = commonDAO.getAccountDetailByAccountId(account.getId());
                // Đặt thuộc tính "accountDetail" vào request để hiển thị trên giao diện
                request.setAttribute("accountDetail", accountDetail);
                request.setAttribute("username", accountDetail.getUserName());
                request.setAttribute("email", account.getEmail());
                String password = request.getParameter("password");
                String newpassword = request.getParameter("newPassword");
                String passwordConfirm = request.getParameter("newPassword2");
                account = Account.builder()
                        .email(account.getEmail())
                        .password(password)
                        .build();
                account = commonDAO.checkExistOfAcc(account);
                // check nguoi dung da nhap dung mat khau.
                if (account != null) {
                    if (!newpassword.matches(Constant.PASSWORD_REGEX)) {
                        request.setAttribute("error1", "Mật khẩu phải chứa ít nhất 8 kí tự (1 số, 1 chữ in hoa, 1 kí tự đặc biệt(trừ khoảng trắng)");
                        request.getRequestDispatcher("views/common/profile.jsp").forward(request, response);
                    } else {
                        // Kiểm tra xem mật khẩu và mật khẩu nhập lại có giống nhau không
                        if (!newpassword.equals(passwordConfirm)) {
                            request.setAttribute("error1", "Mật khẩu phải giống nhau");
                            request.getRequestDispatcher("views/common/profile.jsp").forward(request, response);
                            return; // Kết thúc phương thức để không thực hiện các bước tiếp theo nếu mật khẩu không khớp
                        }
                        BCrypt bcryp = new BCrypt();
                        String passwordBcryp = bcryp.hashpw(newpassword, bcryp.gensalt());
                        int result = commonDAO.changePassword(passwordBcryp, account.getId());
                        if (result > 0) {
                            request.setAttribute("message", "Thay doi mat khau thanh cong");
                            request.getRequestDispatcher("views/common/profile.jsp").forward(request, response);
                            return;
                        }
                    }
                } else {
                    request.setAttribute("error1", "Mật khẩu khong dung");
                    request.getRequestDispatcher("views/common/profile.jsp").forward(request, response);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
