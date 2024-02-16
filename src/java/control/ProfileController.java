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

/**
 *
 * @author Admin
 */
public class ProfileController extends HttpServlet {

  CommonDao commonDAO;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    commonDAO = new CommonDao();
    // Lấy thông tin tài khoản từ session
    HttpSession session = request.getSession(false); // Sử dụng tham số false để không tạo session mới nếu không tồn tại
    if (session != null && session.getAttribute(Constant.SESSION_ACCOUNT) != null) {
      Account account = (Account) session.getAttribute(Constant.SESSION_ACCOUNT);
      // Gọi phương thức trong DAO để lấy chi tiết tài khoản dựa trên accountId
      Account_Detail accountDetail = commonDAO.getAccountDetailByAccountId(account.getId());
      // Đặt thuộc tính "accountDetail" vào request để hiển thị trên giao diện
      request.setAttribute("accountDetail", accountDetail);
      // Chuyển hướng sang trang hiển thị thông tin cá nhân
      request.getRequestDispatcher("views/common/profile.jsp").forward(request, response);
    } else {
      // Nếu không có session hoặc không có thông tin tài khoản trong session, có thể chuyển hướng người dùng đến trang đăng nhập hoặc xử lý một cách phù hợp.
      response.sendRedirect("views/common/login.jsp");
    }
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
  }
}
